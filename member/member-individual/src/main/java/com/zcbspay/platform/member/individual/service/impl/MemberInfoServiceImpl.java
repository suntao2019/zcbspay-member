/* 
 * MemberInfoServiceImpl.java  
 * 
 * version TODO
 *
 * 2016年9月29日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zcbspay.platform.member.individual.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zcbspay.platform.member.individual.bean.MemberApplyBean;
import com.zcbspay.platform.member.individual.bean.MemberBean;
import com.zcbspay.platform.member.individual.bean.enums.MemberType;
import com.zcbspay.platform.member.individual.dao.MemberApplyDAO;
import com.zcbspay.platform.member.individual.dao.MemberDAO;
import com.zcbspay.platform.member.individual.pojo.PojoMember;
import com.zcbspay.platform.member.individual.pojo.PojoMemberApply;
import com.zcbspay.platform.member.individual.service.MemberInfoService;
import com.zlebank.zplatform.member.commons.utils.BeanCopyUtil;

/**
 * Class Description
 *
 * @author guojia
 * @version
 * @date 2016年9月29日 下午5:12:10
 * @since 
 */
@Service
public class MemberInfoServiceImpl implements MemberInfoService{

	@Autowired
    private MemberDAO memberDAO;
	@Autowired
	private MemberApplyDAO memberApplyDAO;
	/**
	 *
	 * @param memberId
	 * @param type
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public MemberBean getMemberByMemberId(String memberId, MemberType type) {
		// TODO Auto-generated method stub
		PojoMember member = memberDAO.getMemberByMemberId(memberId, type);
		if(member!=null){
			return BeanCopyUtil.copyBean(MemberBean.class, member);
		}
		return null;
	}

	/**
	 *
	 * @param email
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public MemberBean getMemberByEmail(String email) {
		PojoMember member = memberDAO.getMemberByEmail(email);
		if(member!=null){
			return BeanCopyUtil.copyBean(MemberBean.class, member);
		}
		return null;
	}

	/**
	 *
	 * @param phone
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public MemberBean getMemberByphone(String phone) {
		PojoMember member = memberDAO.getMemberByphone(phone);
		if(member!=null){
			return BeanCopyUtil.copyBean(MemberBean.class, member);
		}
		return null;
	}

	/**
	 *
	 * @param loginName
	 * @param instiCode
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public List<MemberBean> getMemberByLoginNameAndCoopInsti(String loginName,
			long instiCode) {
		List<PojoMember> members = memberDAO.getMemberByLoginNameAndCoopInsti(loginName, instiCode);
		List<MemberBean> result=new ArrayList<MemberBean>();
        for (PojoMember pojoMember : members) {
            result.add(BeanCopyUtil.copyBean(MemberBean.class, pojoMember));
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
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public List<MemberBean> getMemberByPhoneAndCoopInsti(String phone, long instiCode) {
		List<PojoMember> members = memberDAO.getMemberByPhoneAndCoopInsti(phone, instiCode);
		List<MemberBean> result=new ArrayList<MemberBean>();
		for (PojoMember pojoMember : members) {
            result.add(BeanCopyUtil.copyBean(MemberBean.class, pojoMember));
        }
		return result;
	}

	/**
	 *
	 * @param memberBean
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
	public void updateMemberInfo(MemberBean memberBean) {
		// TODO Auto-generated method stub
		PojoMember member = BeanCopyUtil.copyBean(PojoMember.class,memberBean);
		memberDAO.update(member);
	}
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
	public MemberApplyBean saveMemberApply(MemberApplyBean memberApplyBean){
		PojoMemberApply pojoMemberApply = BeanCopyUtil.copyBean(PojoMemberApply.class, memberApplyBean);
		PojoMemberApply merge = memberApplyDAO.merge(pojoMemberApply);
		return BeanCopyUtil.copyBean(MemberApplyBean.class, merge);
	}

}
