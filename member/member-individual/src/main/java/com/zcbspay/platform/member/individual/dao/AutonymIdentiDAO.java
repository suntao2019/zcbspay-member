/* 
 * AutonymIdentiDAO.java  
 * 
 * version TODO
 *
 * 2016年1月18日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zcbspay.platform.member.individual.dao;

import com.zcbspay.platform.member.individual.pojo.PojoAutonymIdenti;
import com.zlebank.zplatform.member.commons.dao.BaseDAO;

/**
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2016年1月18日 下午5:11:13
 * @since 
 */
public interface AutonymIdentiDAO extends BaseDAO<PojoAutonymIdenti> {
    
    /**
     * 通过会员号得到实名认证信息
     * @param memberId
     * @return pojo 若无相关信息返回NULL
     */
    public PojoAutonymIdenti getByMemberId(String memberId);
}
