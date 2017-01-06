package com.zcbspay.platform.member.merchant.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zcbspay.platform.member.merchant.bean.Pages;
import com.zcbspay.platform.member.merchant.dao.ManagerMerchApplyDao;
import com.zcbspay.platform.member.merchant.pojo.PojoMerchDetaApply;
import com.zlebank.zplatform.member.commons.dao.impl.HibernateBaseDAOImpl;

@Repository
public class ManagerMerchApplyDaoImpl extends HibernateBaseDAOImpl<PojoMerchDetaApply> implements ManagerMerchApplyDao {

	@Override
	@Transactional(readOnly = true)
	public void setNetmember(PojoMerchDetaApply merchDetaApply) {

		this.saveEntity(merchDetaApply);
	}

	// 通过id获取商户信息
	@Override
	@Transactional(readOnly = true)
	public PojoMerchDetaApply selectMerchById(String merchId) {
		Criteria criteria = getSession().createCriteria(PojoMerchDetaApply.class);
		criteria.add(Restrictions.eq("memberId", merchId));
		return (PojoMerchDetaApply) criteria.uniqueResult();
	}

	// 查询所有商户
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Pages<PojoMerchDetaApply> selectAll(int page, int size) {

		Pages<PojoMerchDetaApply> pages = new Pages<PojoMerchDetaApply>();
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(PojoMerchDetaApply.class);
		// 总行数
		Long rowCount = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
		criteria.setFirstResult(page);
		criteria.setMaxResults(size);
		List<PojoMerchDetaApply> lists = criteria.list();

		pages.setList(lists);
		pages.setRow(rowCount);

		return pages;
	}

	@Override
	@Transactional
	public void delectById(String memberId) {
		Session session = getSession();
		Criteria criteria = session.createCriteria(PojoMerchDetaApply.class);
		criteria.add(Restrictions.eq("memberId", memberId));
		PojoMerchDetaApply uniqueResult = (PojoMerchDetaApply) criteria.uniqueResult();
		if (uniqueResult != null)
			session.delete(uniqueResult);

	}

}