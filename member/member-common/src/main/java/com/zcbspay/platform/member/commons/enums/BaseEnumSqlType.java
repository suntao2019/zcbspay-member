/* 
 * BaseEnmuType.java  
 * 
 * version TODO
 *
 * 2015-6-26 
 * 
 * Copyright (c) 2015,.All rights reserved.
 * 
 */
package com.zcbspay.platform.member.commons.enums;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;

/**
 * A abstract class represent enumeration type in Database. For example,field
 * status,type of a table.
 *
 * @author yangying
 * @version
 * @date 2015-6-26 上午09:56:10
 * @since
 */
public abstract class BaseEnumSqlType<T> implements UserType {

	private static int[] sqlType = { Types.CHAR };

	/**
	 * 
	 *
	 * @param cached
	 * @param owner
	 * @return
	 * @throws HibernateException
	 */
	@Override
	public Object assemble(Serializable cached, Object owner)
			throws HibernateException {
		return cached;
	}

	/**
	 *
	 * @param value
	 * @return
	 * @throws HibernateException
	 */
	@Override
	public Object deepCopy(Object value) throws HibernateException {
		return value;
	}

	/**
	 *
	 * @param value
	 * @return
	 * @throws HibernateException
	 */
	@Override
	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable) value;
	}

	/**
	 * 
	 *
	 * @param x
	 * @param y
	 * @return
	 * @throws HibernateException
	 */
	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		return x == null ? y == null : x.equals(y);
	}

	/**
	 * 
	 *
	 * @param x
	 * @return
	 * @throws HibernateException
	 */
	@Override
	public int hashCode(Object x) throws HibernateException {
		return this.hashCode();
	}

	/**
	 *
	 * @return
	 */
	public boolean isMutable() {
		return false;
	}

	/**
	 * 
	 *
	 * @param original
	 * @param target
	 * @param owner
	 * @return
	 * @throws HibernateException
	 */
	@Override
	public Object replace(Object original, Object target, Object owner)
			throws HibernateException {
		return original;
	}

	/**
	 *
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	@Override
	public Class<T> returnedClass() {
		return (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
	public int[] sqlTypes() {
		return sqlType;
	}

}
