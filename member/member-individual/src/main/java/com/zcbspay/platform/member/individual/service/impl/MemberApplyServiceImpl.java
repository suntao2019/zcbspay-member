/* 
 * MemberApplyServiceImpl.java  
 * 
 * version TODO
 *
 * 2016年9月26日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zcbspay.platform.member.individual.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zcbspay.platform.member.individual.bean.MemberApplyBean;
import com.zcbspay.platform.member.individual.bean.MemberBean;
import com.zcbspay.platform.member.individual.dao.MemberApplyDAO;
import com.zcbspay.platform.member.individual.pojo.PojoMemberApply;
import com.zcbspay.platform.member.individual.service.MemberApplyService;
import com.zlebank.zplatform.member.commons.utils.BeanCopyUtil;

/**
 * Class Description
 *
 * @author guojia
 * @version
 * @date 2016年9月26日 下午12:08:00
 * @since 
 */
@Service
public class MemberApplyServiceImpl implements MemberApplyService{

	@Autowired
	private MemberApplyDAO memberApplyDAO;
	
	/**
	 *
	 * @param memberId
	 * @return
	 */
	@Override
	@Transactional(readOnly=true)
	public MemberBean getMemberApplyByMemberId(String memberId) {
		// TODO Auto-generated method stub
		PojoMemberApply memberApply = memberApplyDAO.getMemberApply(memberId);
		if (memberApply==null) {
            return null;
        }
		return BeanCopyUtil.copyBean(MemberBean.class, memberApply);
	}
	
	/**
	 * 会员注册
	 * @throws ParseException 
	 * **/
	@Override
	public void sava(MemberApplyBean memberApply) throws ParseException {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String date = simpleDateFormat.format(System.currentTimeMillis());
		Date parse = simpleDateFormat.parse(date);
		memberApply.setInTime(parse);
		
		PojoMemberApply memberApply1 = BeanCopyUtil.copyBean(PojoMemberApply.class, memberApply);
		memberApplyDAO.savaMember(memberApply1);
		
	}

}
