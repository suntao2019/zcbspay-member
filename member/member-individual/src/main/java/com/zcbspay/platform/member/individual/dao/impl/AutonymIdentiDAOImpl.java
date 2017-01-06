/* 
 * AutonymIdentiDAOImpl.java  
 * 
 * version TODO
 *
 * 2016年1月18日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zcbspay.platform.member.individual.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.zcbspay.platform.member.individual.dao.AutonymIdentiDAO;
import com.zcbspay.platform.member.individual.pojo.PojoAutonymIdenti;
import com.zlebank.zplatform.member.commons.dao.impl.HibernateBaseDAOImpl;

/**
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2016年1月18日 下午5:11:21
 * @since 
 */
@Repository
public class AutonymIdentiDAOImpl extends HibernateBaseDAOImpl<PojoAutonymIdenti>implements AutonymIdentiDAO {

    /**
     * 通过会员号得到实名认证信息
     * @param memberId
     * @return
     */
    @Override
    public PojoAutonymIdenti getByMemberId(String memberId) {
        Criteria crite=   this.getSession().createCriteria(PojoAutonymIdenti.class);
        crite .add(Restrictions.eq("memberId", memberId));
        crite .add(Restrictions.eq("status", "00"));
        PojoAutonymIdenti member = (PojoAutonymIdenti) crite.uniqueResult();
        return member;
    }
    
}
