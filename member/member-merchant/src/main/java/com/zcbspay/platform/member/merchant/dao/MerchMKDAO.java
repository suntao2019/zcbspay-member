/* 
 * MerchMKDAO.java  
 * 
 * version 1.0
 *
 * 2015年9月17日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zcbspay.platform.member.merchant.dao;

import com.zcbspay.platform.member.merchant.pojo.PojoMerchMK;
import com.zlebank.zplatform.member.commons.dao.BaseDAO;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年9月17日 上午9:35:41
 * @since 
 */
public interface MerchMKDAO extends BaseDAO<PojoMerchMK>{
    /**
     *  查询RSA 密钥信息（safeType：01）
     *  注意：此方法默认为RSA算法密钥（如果不是RSA密钥请另建方法）
     * @param memberId
     * @return
     */
    PojoMerchMK get(String memberId);
}
