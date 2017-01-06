/* 
 * MemberApplyDAO.java  
 * 
 * version TODO
 *
 * 2016年8月19日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zcbspay.platform.member.individual.dao;

import com.zcbspay.platform.member.individual.pojo.PojoMemberApply;
import com.zlebank.zplatform.member.commons.dao.BaseDAO;

/**
 * Class Description
 *
 * @author guojia
 * @version
 * @date 2016年8月19日 上午9:11:48
 * @since 
 */
public interface MemberApplyDAO extends BaseDAO<PojoMemberApply>{

	/**
	 * 获取审核表中的会员信息
	 * @param memberId
	 */
	public PojoMemberApply getMemberApply(String memberId);
	
	/**
	 * 
	 * 保存会员信息
	 * 
	 * **/
	public void savaMember(PojoMemberApply memberApply);
	
}
