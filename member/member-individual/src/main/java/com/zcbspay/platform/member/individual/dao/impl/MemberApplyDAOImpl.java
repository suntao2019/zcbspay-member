/* 
 * MemberApplyDAOImpl.java  
 * 
 * version TODO
 *
 * 2016年8月19日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zcbspay.platform.member.individual.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.zcbspay.platform.member.individual.dao.MemberApplyDAO;
import com.zcbspay.platform.member.individual.pojo.PojoMemberApply;
import com.zlebank.zplatform.member.commons.dao.impl.HibernateBaseDAOImpl;

/**
 * Class Description
 *
 * @author guojia
 * @version
 * @date 2016年8月19日 上午9:16:14
 * @since 
 */
@Repository("memberApplyDAO")
public class MemberApplyDAOImpl extends HibernateBaseDAOImpl<PojoMemberApply> implements MemberApplyDAO{

	/**
	 *
	 * @param memberId
	 */
	@Override
	public PojoMemberApply getMemberApply(String memberId) {
		// TODO Auto-generated method stub
		Criteria criteria = getSession().createCriteria(PojoMemberApply.class);
		criteria.add(Restrictions.eq("memberId", memberId));
		criteria.addOrder(Order.desc("selfId"));
		@SuppressWarnings("unchecked")
		List<PojoMemberApply> list = criteria.list();
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
		
	}

	@Override
	public void savaMember(PojoMemberApply memberApply) {
		
		saveEntity(memberApply);
	}

}
