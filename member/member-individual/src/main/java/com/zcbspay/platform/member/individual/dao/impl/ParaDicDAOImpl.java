package com.zcbspay.platform.member.individual.dao.impl;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zcbspay.platform.member.individual.dao.ParaDicDAO;
import com.zcbspay.platform.member.individual.pojo.PojoParaDic;
import com.zlebank.zplatform.member.commons.dao.impl.HibernateBaseDAOImpl;
import com.zlebank.zplatform.member.exception.MemberBussinessException;

/* 
 * PrimayKeyDAOImpl.java  
 * 
 * version TODO
 *
 * 2015年9月11日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年9月11日 下午3:14:10
 * @since 
 */
@Repository
public class ParaDicDAOImpl extends HibernateBaseDAOImpl<PojoParaDic>
        implements
            ParaDicDAO {

    /**
     * 得到memberId头
     * @return
     * @throws MemberBussinessException
     */
    @Override
    public PojoParaDic getPrimay(String paraType)  {
        Criteria crite = getSession().createCriteria(PojoParaDic.class);
            PojoParaDic primay = (PojoParaDic) crite.add(
                    Restrictions.eq("paraType", paraType)).uniqueResult();
            return primay;
    }
    /**
     * 得到序列
     *
     * @return
     */
     @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getSeq(String sequences){
         return  this.getSession().createSQLQuery(" SELECT "+sequences+".NEXTVAL FROM DUAL")
           .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
       }
     /**
      * 获取一个序列的下一个值
      *
      * @param sequences
      * @return
      */
     @Transactional
     public String getSeqNextval(String sequences){
         return  this.getSession().createSQLQuery("SELECT "+sequences+".NEXTVAL FROM DUAL")
                 .uniqueResult().toString();
     }
}
