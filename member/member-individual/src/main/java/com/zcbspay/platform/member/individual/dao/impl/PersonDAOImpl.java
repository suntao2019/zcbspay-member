/* 
 * PersonDAOImpl.java  
 * 
 * version TODO
 *
 * 2015年9月9日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zcbspay.platform.member.individual.dao.impl;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.zcbspay.platform.member.individual.dao.PersonDAO;
import com.zcbspay.platform.member.individual.pojo.PojoPersonDeta;
import com.zlebank.zplatform.member.commons.dao.impl.HibernateBaseDAOImpl;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年9月9日 下午6:37:38
 * @since 
 */
@Repository
public class PersonDAOImpl extends HibernateBaseDAOImpl<PojoPersonDeta> implements PersonDAO  {

    /**
     *
     * @param phone
     * @return
     */
    @Override
    public PojoPersonDeta getPersonByPhone(String phone) {
        return (PojoPersonDeta) this.getSession()
                .createCriteria(PojoPersonDeta.class)
                .add(Restrictions.eq("bindPhone", phone)).uniqueResult();
    }

    /**
     *
     * @param email
     * @return
     */
    @Override
    public PojoPersonDeta getPersonByEmail(String email) {
        return (PojoPersonDeta) this.getSession()
                .createCriteria(PojoPersonDeta.class)
                .add(Restrictions.eq("bindEmail", email)).uniqueResult();
    }

    /**
     *
     * @param memberId
     * @return
     */
    @Override
    public PojoPersonDeta getPersonByMemberId(String memberId) {
        return (PojoPersonDeta) this.getSession()
                .createCriteria(PojoPersonDeta.class)
                .add(Restrictions.eq("parentMemberId", memberId)).uniqueResult();
    }
        
    /**
    *
    * @param memberId
    * @param pwd
    * @return
    */
   @Override
   public PojoPersonDeta personLoninByMid(String memberId, String pwd) {
       Criteria crite=    this.getSession().createCriteria(PojoPersonDeta.class);
       crite.add(Restrictions.eq("parentMemberId", memberId));
       crite.add(Restrictions.eq("loginPwd", pwd));
       return (PojoPersonDeta)crite.uniqueResult();
   }

   /**
    *
    * @param phone
    * @param pwd
    * @return
    */
   @Override
   public PojoPersonDeta personLoninByPhone(String phone, String pwd) {
       Criteria crite=    this.getSession().createCriteria(PojoPersonDeta.class);
       crite.add(Restrictions.eq("bindPhone", phone));
       crite.add(Restrictions.eq("loginPwd", pwd));
       return (PojoPersonDeta)crite.uniqueResult();
   }

   /**
    *
    * @param email
    * @param pwd
    * @return
    */
   @Override
   public PojoPersonDeta personLoninByEmail(String email, String pwd) {
       Criteria crite=    this.getSession().createCriteria(PojoPersonDeta.class);
       crite.add(Restrictions.eq("bindEmail", email));
       crite.add(Restrictions.eq("loginPwd", pwd));
       return (PojoPersonDeta)crite.uniqueResult();
   }
}
