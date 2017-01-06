package com.zcbspay.platform.member.coopinsti.service;

import java.util.List;

import com.zcbspay.platform.member.coopinsti.bean.ProductBean;
import com.zcbspay.platform.member.exception.AbstractCoopInstiException;

public interface CoopInstiProductService {
	/**
	 * fetch products associate to this cooperative institution
	 * 
	 * @param coopInstiId
	 *            cooperative institution id
	 * @return a product list,if has no product,the return list will be a list
	 *         with zero element
	 * @throws AbstractCoopInstiException
	 *             if cooperative institution not exist
	 */
	public List<ProductBean> getProducts(long coopInstiId)
			throws AbstractCoopInstiException;
	
	
	/**
	 * 获取合作机构默认版本
	 * @param instiCode
	 * @param busicode
	 * @param verType
	 * @return
	 */
	public String getDefaultVerInfo(String instiCode, String busicode,int verType);
}
