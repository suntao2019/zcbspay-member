/* 
 * MemberServiceImpl.java  
 * 
 * version v1.2.1
 *
 * 2015年9月10日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zcbspay.platform.member.individual.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zcbspay.platform.member.individual.bean.MemberQuery;
import com.zcbspay.platform.member.individual.bean.PoMemberApplyBean;
import com.zcbspay.platform.member.individual.bean.PoMemberBean;
import com.zcbspay.platform.member.individual.bean.enums.MemberType;
import com.zcbspay.platform.member.individual.dao.MemberApplyDAO;
import com.zcbspay.platform.member.individual.dao.MemberDAO;
import com.zcbspay.platform.member.individual.pojo.PojoMember;
import com.zcbspay.platform.member.individual.pojo.PojoMemberApply;
import com.zcbspay.platform.member.individual.service.MemberService;
import com.zlebank.zplatform.acc.bean.AccEntry;
import com.zlebank.zplatform.acc.bean.AccEntryQuery;
import com.zlebank.zplatform.acc.bean.BusiAcct;
import com.zlebank.zplatform.acc.bean.BusiAcctQuery;
import com.zlebank.zplatform.acc.bean.BusiAcctSubjectMappingBean;
import com.zlebank.zplatform.acc.bean.QueryAccount;
import com.zlebank.zplatform.acc.bean.enums.AccEntryStatus;
import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.acc.service.AccountQueryService;
import com.zlebank.zplatform.acc.service.BusiAcctService;
import com.zlebank.zplatform.acc.service.SubjectSelector;
import com.zlebank.zplatform.member.commons.bean.EnterpriseBusi;
import com.zlebank.zplatform.member.commons.bean.PagedResult;
import com.zlebank.zplatform.member.commons.bean.PersonBusi;
import com.zlebank.zplatform.member.commons.enums.BusinessActorType;
import com.zlebank.zplatform.member.commons.service.impl.BusinessActor;
import com.zlebank.zplatform.member.commons.utils.BeanCopyUtil;
import com.zlebank.zplatform.member.exception.MemberBussinessException;

/**
 * Class Description
 *
 * @author yangpeng,yangying
 * @version
 * @date 2015年9月10日 上午11:20:11
 * @since
 */
@Service
public class MemberServiceImpl implements MemberService {
    private final static String INDIVIDUAL = "1";// 个人会员
    private final static String ENTERPRISE = "2";// 企业会员

    @Autowired
    private MemberDAO memberDAOImpl;
    @Reference(version="1.0")
    private AccountQueryService aqs;
    @Reference(version="1.0")
    private BusiAcctService busiacc;
    @Reference(version="1.0")
    private SubjectSelector subjectSelector;
    @Autowired
    private MemberApplyDAO memberApplyDAO;

    /**
     *
     * @param busiAcctCode
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public BusiAcctQuery getBusiQueryBybCode(String busiAcctCode) {

        return aqs.getBusiQueryBybCode(busiAcctCode);
    }

    /**
     *
     * @param memberId
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public List<BusiAcctQuery> getAllBusiByMId(String memberId) {

        return aqs.getAllBusiByMId(memberId);
    }

    /**
     *
     * @param page
     * @param pageSize
     * @param mQuery
     * @return
     * @throws MemberBussinessException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public PagedResult<AccEntry> getAccEntryByQuery(int page,
            int pageSize,
            MemberQuery mQuery) throws MemberBussinessException {
        AccEntryQuery aeq = BeanCopyUtil.copyBean(AccEntryQuery.class, mQuery);
        // 业务账户号->会计账户号
        BusiAcctQuery query = aqs.getMemberQueryByID(mQuery.getAcctCode());
        if (query == null)
            throw new MemberBussinessException("M100006", mQuery.getAcctCode());
        String acctCode = query.getAcctCode();
        aeq.setAcctCode(acctCode);
        aeq.setStatus(AccEntryStatus.ACCOUNTED);
        return aqs.getAccEntryByQuery(page, pageSize, aeq);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public PagedResult<AccEntry> getAccEntryByQueryN(int page,
            int pageSize,
            MemberQuery mQuery) {
        AccEntryQuery aeq = BeanCopyUtil.copyBean(AccEntryQuery.class, mQuery);
        // 业务账户号->会计账户号
        BusiAcctQuery query = aqs.getMemberQueryByID(mQuery.getAcctCode());
        if (query != null) {
            String acctCode = query.getAcctCode();
            aeq.setAcctCode(acctCode);
        }
        return aqs.getAccEntryByQuery(page, pageSize, aeq);

    }

    /**
     * 开通 - 个人会员或 - 企业会员 - 合作机构 的【业务账户】和【会计账户】
     *
     * @param name
     *            会员名称
     * @param memberId
     *            会员号
     * @param userId
     *            操作人ID
     * @return 业务账户列表
     * @throws AbstractBusiAcctException
     * @throws MemberBussinessException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public List<BusiAcct> openBusiAcct(String name,
            String businessActorId,
            long userId) throws AbstractBusiAcctException,
            MemberBussinessException {
        
        /*
         * 获取会员号第一位，判断会员类型
         */
        String str = businessActorId;
        String mid = str.substring(0, 1);
        BusinessActorType busiActorType;
        if (ENTERPRISE.equals(mid)) {// 如果是开通企业会员
            busiActorType = BusinessActorType.ENTERPRISE;
        } else if (INDIVIDUAL.equals(mid)) {// 如果是开通个人会员
            busiActorType = BusinessActorType.INDIVIDUAL;
        } else {
            throw new MemberBussinessException("M100004");
        }
        
        List<BusiAcct> busiAcctList = null;
        busiAcctList = openDefaultIndiBusiAcct(name, businessActorId, userId,
                busiActorType);
        
        if (busiAcctList == null) {
            return new ArrayList<BusiAcct>();
        }
        return busiAcctList;
    }

    /**
     * 为会员开通所有默认开通的账户
     * 
     * @param memberName
     * @param memberId
     * @param userId
     * @param busiActorType
     * @return a list of business account
     * @throws AbstractBusiAcctException
     * @throws MemberBussinessException
     */
    private List<BusiAcct> openDefaultIndiBusiAcct(String memberName,
            String memberId,
            long userId,
            BusinessActorType busiActorType) throws AbstractBusiAcctException,
            MemberBussinessException {
        List<BusiAcctSubjectMappingBean> busiAcctSubjectMappings = subjectSelector
                .getDefaultList(busiActorType);
        List<BusiAcct> busiAccts = new ArrayList<BusiAcct>();
        for (BusiAcctSubjectMappingBean busiAcctSubjectMapping : busiAcctSubjectMappings) {
            BusiAcct busiAcct = new BusiAcct();
            busiAcct.setBusiAcctName(memberName);
            busiAcct.setUsage(busiAcctSubjectMapping.getUsage());
            BusinessActor busiActor = null;
            switch (busiActorType) {
                case INDIVIDUAL :
                    PersonBusi indivual = new PersonBusi();
                    indivual.setBusinessActorId(memberId);
                    busiActor = indivual;
                    break;
                case ENTERPRISE :
                    EnterpriseBusi enterprise = new EnterpriseBusi();
                    enterprise.setBusinessActorId(memberId);
                    busiActor = enterprise;
                    break;
                default :// never reach here
                    throw new MemberBussinessException("M100004");
            }
            String busiAccCode = busiacc.openBusiAcct(busiActor, busiAcct,
                    userId);
            busiAcct.setBusiAcctCode(busiAccCode);
            busiAccts.add(busiAcct);
        }
        return busiAccts;
    }


    /**
     *
     * @param memberId
     * @param type
     * @return
     */
    @Override
    @Transactional(readOnly=true)
    public PoMemberBean getMbmberByMemberId(String memberId, MemberType type) {
        PojoMember member= memberDAOImpl.getMemberByMemberId(memberId, type);
        if (member==null) {
            return null;
        }
        return BeanCopyUtil.copyBean(PoMemberBean.class, member);
    }

    /**
     *
     * @param email
     * @return
     */
    @Override
    @Transactional(readOnly=true)
    public PoMemberBean getMemberByEmail(String email) {
        PojoMember pojomember=memberDAOImpl.getMemberByEmail(email);
        return BeanCopyUtil.copyBean(PoMemberBean.class,pojomember);
    }

    /**
     *
     * @param phone
     * @return
     */
    @Override
    @Transactional(readOnly=true)
    public PoMemberBean getMemberByphone(String phone) {
        PojoMember pojoMember=memberDAOImpl.getMemberByphone(phone);
        return BeanCopyUtil.copyBean(PoMemberBean.class, pojoMember);
    }

    /**
     *
     * @param qa
     * @return
     */
    @Override
    @Transactional(readOnly=true)
    public PagedResult<BusiAcctQuery> getBusiAccount(QueryAccount qa,
            Integer page,
            Integer pageSize) {
        return aqs.queryPaged(page, pageSize, qa);
    }

	/**
	 *
	 * @param loginName
	 * @param instiCode
	 * @return
	 */
	@Override
	@Transactional(readOnly=true)
	public List<PoMemberBean> getMemberByLoginNameAndCoopInsti(String loginName,
			long instiCode) {
		// TODO Auto-generated method stub
	    List<PojoMember> pojoMembers =memberDAOImpl.getMemberByLoginNameAndCoopInsti(loginName, instiCode);
	    List<PoMemberBean> result=new ArrayList<PoMemberBean>();
	    for (PojoMember pojo : pojoMembers) {
	        result.add(BeanCopyUtil.copyBean(PoMemberBean.class, pojo));
        }
	    return result;
	}

	/**
	 *
	 * @param phone
	 * @param instiCode
	 * @return
	 */
	@Override
	@Transactional(readOnly=true)
	public List<PoMemberBean> getMemberByPhoneAndCoopInsti(String phone, long instiCode) {
		// TODO Auto-generated method stub\
	    List<PojoMember> pojoMembers=memberDAOImpl.getMemberByPhoneAndCoopInsti(phone, instiCode);
	    List<PoMemberBean> result=new ArrayList<PoMemberBean>();
        for (PojoMember pojo : pojoMembers) {
            result.add(BeanCopyUtil.copyBean(PoMemberBean.class, pojo));
        }
        return result;
	}
	
	@Transactional(readOnly=true,isolation=Isolation.READ_COMMITTED)
	public PoMemberApplyBean getMemberApply(String memberId){
		PojoMemberApply memberApply = memberApplyDAO.getMemberApply(memberId);
		return BeanCopyUtil.copyBean(PoMemberApplyBean.class, memberApply);
	}

}
