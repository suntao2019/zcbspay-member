/* 
 * AbstractPagedQueryDAOImpl.java  
 * 
 * version TODO
 *
 * 2015-7-8 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zcbspay.platform.member.commons.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;

import com.zcbspay.platform.member.commons.dao.BasePagedQueryDAO;


/**
 * A dao implement of  {@link HibernateBaseDAOImpl}. This implement support for page query by QBE
 *
 * @author yangying
 * @version
 * @date 2015-7-8 上午09:53:58
 * @since 
 */
public abstract class AbstractPagedQueryDAOImpl<T, E>
        extends
            HibernateBaseDAOImpl<T> implements BasePagedQueryDAO<T, E>{
    
	/**
	 * a template method for create {@link Criteria}
	 * @param t
	 * @return
	 */
    protected abstract Criteria buildCriteria(E e);
    
    /**
     * count the number of records which  matched query conditions
     * @param example query conditions
     * @return
     */
    @Override
    public long count(E example){
        Criteria criteria = buildCriteria(example);
        return (Long) criteria.setProjection(Projections.rowCount())
                .uniqueResult(); 
    }
    
    /**
     * get records matched query conditions by paged
     * @param offset the first record offset
     * @param size  the number of the records need to return
     * @param example query conditions
     * @return records list
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<T> getListByQuery(int offset,int size,E example){
        Criteria criteria = buildCriteria(example);
        criteria.setFirstResult(offset).setMaxResults(size);
        return (List<T>) criteria.list();
    }
}
