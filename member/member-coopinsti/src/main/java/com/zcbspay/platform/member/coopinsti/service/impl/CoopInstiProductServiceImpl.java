package com.zcbspay.platform.member.coopinsti.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zcbspay.platform.member.coopinsti.bean.ProductBean;
import com.zcbspay.platform.member.coopinsti.dao.CoopInstiDAO;
import com.zcbspay.platform.member.coopinsti.pojo.PojoCoopInsti;
import com.zcbspay.platform.member.coopinsti.pojo.ProductModel;
import com.zcbspay.platform.member.coopinsti.service.CoopInstiProductService;
import com.zcbspay.platform.member.exception.AbstractCoopInstiException;
import com.zcbspay.platform.member.exception.CoopInstiNotExistException;

@Service
public class CoopInstiProductServiceImpl implements CoopInstiProductService {
	@Autowired
	private CoopInstiDAO coopInstiDAO;

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<ProductBean> getProducts(long coopInstiId)
			throws AbstractCoopInstiException {
		PojoCoopInsti coopInsti = coopInstiDAO.getEager(coopInstiId);
		if (coopInsti == null) {
			throw new CoopInstiNotExistException(coopInstiId);
		}
		List<ProductModel> productSourceList = coopInstiDAO.getCoopProductList(coopInstiId);
		List<ProductBean> productTargetList = new ArrayList<ProductBean>();
		for(ProductModel copyFrom:productSourceList){
			ProductBean copyTo = new ProductBean();
			BeanUtils.copyProperties(copyFrom, copyTo);
			productTargetList.add(copyTo);
		}
		return productTargetList;
	}
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public String getDefaultVerInfo(String instiCode, String busicode,int verType) {
		return coopInstiDAO.getDefaultVerInfo(instiCode, busicode, verType);
	}
}
