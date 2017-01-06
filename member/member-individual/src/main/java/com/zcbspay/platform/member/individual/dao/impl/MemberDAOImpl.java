/* 
 * MemeberDAOImpl.java  
 * 
 * version TODO
 *
 * 2015年9月13日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zcbspay.platform.member.individual.dao.impl;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.zcbspay.platform.member.individual.bean.enums.MemberType;
import com.zcbspay.platform.member.individual.dao.MemberDAO;
import com.zcbspay.platform.member.individual.pojo.PojoMember;
import com.zlebank.zplatform.member.commons.dao.impl.HibernateBaseDAOImpl;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年9月13日 下午6:30:07
 * @since 
 */
@Repository
public class MemberDAOImpl extends HibernateBaseDAOImpl<PojoMember>
        implements
           MemberDAO {

    /**
     *
     * @param phone
     * @return
     */
    @Override
    public PojoMember getMemberByphone(String phone) {
      return (PojoMember) this.getSession().createCriteria(PojoMember.class)
        .add(Restrictions.eq("phone", phone)).uniqueResult();
    }

    /**
     *
     * @param email
     * @return
     */
    @Override
    public PojoMember getMemberByEmail(String email) {
        return (PojoMember) this.getSession().createCriteria(PojoMember.class)
                .add(Restrictions.eq("email", email)).uniqueResult();
    }

    /**
     *
     * @param memberId
     * @return
     */
    @Override 
    public PojoMember getMemberByMemberId(String memberId,MemberType type) {
        Criteria crite=   this.getSession().createCriteria(PojoMember.class);
            crite .add(Restrictions.eq("memberId", memberId));
        if (type != null) {
           crite .add(Restrictions.eq("memberType", type)); 
       }
        PojoMember member = (PojoMember) crite.uniqueResult();
        if (member != null)
            getSession().evict(member);
        return member;
    }

    /**
     * 通过登陆名得到会员信息
     * @param loginName
     * @return
     */
    @Override
    public List<PojoMember> getMemberByLoginName(String loginName) {
        Criteria crite=this.getSession().createCriteria(PojoMember.class);
        crite.add(Restrictions.eq("loginame", loginName));
        return crite.list();
    }

    /**
     *
     * @param loginName
     * @param instiCode
     * @return
     */
    @Override
    public List<PojoMember> getMemberByLoginNameAndCoopInsti(String loginName, long instiId) {
        Criteria crite=   this.getSession().createCriteria(PojoMember.class);
        crite .add(Restrictions.eq("loginName", loginName));
        crite .add(Restrictions.eq("instiId", instiId));
        return crite.list();
    }

    /**
     *
     * @param phone
     * @param instiCode
     * @return
     */
    @Override
    public List<PojoMember> getMemberByPhoneAndCoopInsti(String phone,
            long instiCode) {
        Criteria crite=   this.getSession().createCriteria(PojoMember.class);
        crite .add(Restrictions.eq("phone", phone));
        crite .add(Restrictions.eq("instiId", instiCode));
        return crite.list();
    }
}
