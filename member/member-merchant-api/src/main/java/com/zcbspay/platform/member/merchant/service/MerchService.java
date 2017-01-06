/* 
 * MenchService.java  
 * 
 * version TODO
 *
 * 2015年9月11日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zcbspay.platform.member.merchant.service;

import com.zcbspay.platform.member.merchant.bean.MerchantBean;
import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.member.exception.MemberBussinessException;

/**
 * Class Description 商户service
 * 
 * @author yangpeng
 * @version
 * @date 2015年9月11日 上午9:32:49
 * @since
 */
public interface MerchService {
	/**
	 * 二级商户开户
	 * 
	 * @param mb
	 * @return
	 * @throws AbstractBusiAcctException 
	 */
	public String createMinorMerchant(MerchantBean mb, Long userId)
			throws MemberBussinessException,AbstractBusiAcctException;

	/**
	 * 根据父级ID得到父级商户
	 * 
	 * @param merchId
	 * @return
	 */
	public MerchantBean getParentMerch(String memberId);
	
	/**
	 * 根据memberId得到商户
	 * 
	 * @param memberId
	 * @return
	 */
	public MerchantBean getMerchBymemberId(String memberId);
	/**
	 * 
	 * 更新商户
	 * **/
	public void update(MerchantBean merchDeta);
	
	
}
