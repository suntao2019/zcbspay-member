/* 
 * MemberService.java  
 * 
 * version TODO
 *
 * 2015年9月10日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zcbspay.platform.member.individual.service;

import java.util.List;

import com.zcbspay.platform.member.individual.bean.MemberQuery;
import com.zcbspay.platform.member.individual.bean.PoMemberApplyBean;
import com.zcbspay.platform.member.individual.bean.PoMemberBean;
import com.zcbspay.platform.member.individual.bean.enums.MemberType;
import com.zlebank.zplatform.acc.bean.AccEntry;
import com.zlebank.zplatform.acc.bean.BusiAcct;
import com.zlebank.zplatform.acc.bean.BusiAcctQuery;
import com.zlebank.zplatform.acc.bean.QueryAccount;
import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.member.commons.bean.PagedResult;
import com.zlebank.zplatform.member.exception.InvalidMemberDataException;
import com.zlebank.zplatform.member.exception.MemberBussinessException;


/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年9月10日 上午11:11:20
 * @since
 */
public interface MemberService {

    /**
     * 根据业务账户号得到账户信息
     * 
     * @param busiAcctCode
     * @return List<BusiAcctQuery>
     */
    public BusiAcctQuery getBusiQueryBybCode(String busiAcctCode);
    /**
     * 根据会员号得到所有账户信息
     * 
     * @param memberId
     * 
     * @return List<BusiAcctQuery>
     */
    public List<BusiAcctQuery> getAllBusiByMId(String memberId);
    /**
     * 接口调用根据条件得到分录流水
     * 
     * @param accountId
     * @return List<AccEntry>
     * @throws MemberBussinessException
     */
    public PagedResult<AccEntry> getAccEntryByQuery(int page,
            int pageSize,
            MemberQuery mQuery) throws MemberBussinessException;
    /**
     * 开通账户
     * 
     * @param memberId
     * @param userId
     * @return
     */
    public List<BusiAcct> openBusiAcct(String name, String memberId, long userId)
            throws AbstractBusiAcctException, MemberBussinessException;
    /**
     * 通过 会员ID、状态得到会员
     * 
     * @param memberId
     * @param type
     * @return
     */
    public PoMemberBean getMbmberByMemberId(String memberId, MemberType type);

    /**
     * 通过邮箱得到会员信息
     * 
     * @param email
     * @return PojoMember
     */
    public PoMemberBean getMemberByEmail(String email);
    /**
     * 通过电话得到会员信息
     * 
     * @param phone
     * @return PojoMember
     */
    public PoMemberBean getMemberByphone(String phone);

    /**
     * 内部调用分录流水查询
     * 
     * @param page
     * @param pageSize
     * @param mQuery
     * @return
     */
    public PagedResult<AccEntry> getAccEntryByQueryN(int page,
            int pageSize,
            MemberQuery mQuery);

    /**
     * 账户查询
     * 
     * @param qa
     *            用户账户查询Bean
     * @return
     */
    public PagedResult<BusiAcctQuery> getBusiAccount(QueryAccount qa,
            Integer page,
            Integer pageSize);
    
    /**
     * 通过登陆名和合作机构得到会员信息
     * @param loginName
     * @param phone
     * @return 
     * @throws InvalidMemberDataException 
     */
    public List<PoMemberBean> getMemberByLoginNameAndCoopInsti(String loginName, long instiCode);
    /**
     * 通过手机号和合作机构得到会员信息
     * @param loginName
     * @param phone
     * @return 
     */
    public List<PoMemberBean> getMemberByPhoneAndCoopInsti(String phone, long instiCode);

    /**
     * 通过会员号取得审核表中的会员信息
     * @param memberId
     * @return
     */
    public PoMemberApplyBean getMemberApply(String memberId);
}
