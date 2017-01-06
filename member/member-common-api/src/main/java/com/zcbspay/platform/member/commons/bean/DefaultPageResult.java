/* 
 * DefaultPageResult.java  
 * 
 * version TODO
 *
 * 2015-6-25 
 * 
 * Copyright (c) 2015,.All rights reserved.
 * 
 */
package com.zcbspay.platform.member.commons.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 默认分页查询结果
 *
 * @author 
 * @version
 * @date 2015-6-25 下午04:41:13
 * @since
 */
public class DefaultPageResult<T> implements PagedResult<T>,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2449021195088210245L;
	private List<T> result;
	private long total;

	public DefaultPageResult(List<T> result, long total) {
		this.result = result;
		this.total = total;
	}

	/**
	 *
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */ 
	public List<T> getPagedResult() throws IllegalAccessException {
		return result;
	}

	/**
	 *
	 * @return
	 */
	public long getTotal() {
		return total;
	}

	

}
