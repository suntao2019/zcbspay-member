/* 
 * MerchServiceImpl.java  
 * 
 * version TODO
 *
 * 2015年9月11日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zcbspay.platform.member.merchant.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zcbspay.platform.member.individual.service.MemberInfoService;
import com.zcbspay.platform.member.individual.service.MemberService;
import com.zcbspay.platform.member.individual.service.PrimayKeyService;
import com.zcbspay.platform.member.merchant.bean.MerchantBean;
import com.zcbspay.platform.member.merchant.bean.enums.MerchStatusType;
import com.zcbspay.platform.member.merchant.dao.MerchDAO;
import com.zcbspay.platform.member.merchant.pojo.PojoMerchDeta;
import com.zcbspay.platform.member.merchant.service.MerchService;
import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.member.commons.utils.BeanCopyUtil;
import com.zlebank.zplatform.member.commons.utils.StringUtil;
import com.zlebank.zplatform.member.exception.MemberBussinessException;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年9月11日 上午10:09:47
 * @since
 */
@Service
public class MerchServiceImpl implements MerchService {
	/** 个人生成memberID规则 **/
	private final static String MERCHPARATYPE = "MERCHBIN";
	@Autowired
	private MerchDAO merchDao;
	@Reference(version="1.0")
	private PrimayKeyService primayService;
	@Reference(version="1.0")
	private MemberService memberservice;
	@Reference(version="1.0")
	private MemberInfoService memberInfoservice;

	/**
	 *
	 * @return
	 * @throws MemberBussinessException
	 * @throws AbstractBusiAcctException
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public String createMinorMerchant(MerchantBean mb, Long userId)
			throws MemberBussinessException , AbstractBusiAcctException {

		if (StringUtil.isEmpty(mb.getParent())) {
			// 格式不合法
			throw new MemberBussinessException("M100004");
		}
		// 得到父级商户
		PojoMerchDeta merch = merchDao.getParentMerch(mb.getParent());
		if (merch == null) {
			// TODO 父级商户不存在
			throw new MemberBussinessException("M100005");
		}
		PojoMerchDeta merchPo = BeanCopyUtil.copyBean(PojoMerchDeta.class, mb);
		merchPo.setPrdtVer(merch.getPrdtVer());
		merchPo.setFeeVer(merch.getFeeVer());
		merchPo.setSpiltVer(merch.getSpiltVer());
		merchPo.setRiskVer(merch.getRiskVer());
		merchPo.setRoutVer(merch.getRoutVer());
		// 得到序列
		@SuppressWarnings("deprecation")
		String memberId = primayService.getNexId(MERCHPARATYPE);
		Date date = new Date();
		merchPo.setInTime(date);
		merchPo.setInTime(date);
		merchPo.setInUser(userId);
		merchPo.setMemberId(memberId);
		merchPo.setMerchStatus(MerchStatusType.NORMAL);
		//保存到t_merch_deta 表
		merchDao.saveEntity(merchPo);
		 //开通会计账户
        memberservice.openBusiAcct("", memberId, userId);// TODO: 传入商户的名称
       /* PojoMemberBase memberbasePo=  BeanCopyUtil.copyBean(PojoMemberBase.class, merchPo);
        memberbasePo.setMerchtype(BusinessActorType.ENTERPRISE);
        memberBase.saveA(memberbasePo);*/
        return memberId;
	}

	/**
	 *
	 * @param memberId
	 * @return
	 */
	@Override
	public MerchantBean getParentMerch(String memberId) {
		PojoMerchDeta parentMerch = merchDao.getParentMerch(memberId);
		if(parentMerch!=null){
			return BeanCopyUtil.copyBean(MerchantBean.class, merchDao.getParentMerch(memberId));
		}
		return null;
	}

	

	/**
	 *
	 * @param memberId
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public MerchantBean getMerchBymemberId(String memberId) {
		PojoMerchDeta merch = merchDao.getMerchBymemberId(memberId);
		if(merch!=null){
			return BeanCopyUtil.copyBean(MerchantBean.class, merch);
		}
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void update(MerchantBean merchDeta) {
		if(merchDeta!=null){
			merchDao.update(BeanCopyUtil.copyBean(PojoMerchDeta.class, merchDeta));
		}
	}

}
