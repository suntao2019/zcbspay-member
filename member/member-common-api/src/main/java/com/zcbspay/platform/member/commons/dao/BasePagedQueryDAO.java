/* 
 * BasePagedQueryDAO.java  
 * 
 * version TODO
 *
 * 2015年9月8日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zcbspay.platform.member.commons.dao;

import java.util.List;

/**
 * 基础分页类接口，这里只能使用hibernate对象类实现分页查询，对于复杂查询建议使用SQL进行查询
 *
 * @author guojia
 * @version
 * @date 2015年9月8日 上午11:12:04
 * @since 
 */
public interface BasePagedQueryDAO<T,E> extends BaseDAO<T>  {
    public long count(E example);
    public List<T> getListByQuery(int offset,int size,E example);
}
