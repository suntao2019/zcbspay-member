package com.zcbspay.platform.member.merchant.dao;

import com.zcbspay.platform.member.merchant.bean.Pages;
import com.zcbspay.platform.member.merchant.pojo.PojoMerchDetaApply;
import com.zlebank.zplatform.member.commons.dao.BaseDAO;

/**
 * 对表apply操作
 * 
 * **/
public interface ManagerMerchApplyDao extends BaseDAO<PojoMerchDetaApply>{
	
	//新增
	void setNetmember(PojoMerchDetaApply merchDetaApply);

	PojoMerchDetaApply selectMerchById(String merchId);

	Pages<PojoMerchDetaApply> selectAll(int page,int size);
 
	void delectById(String memberId);
 
	
}
