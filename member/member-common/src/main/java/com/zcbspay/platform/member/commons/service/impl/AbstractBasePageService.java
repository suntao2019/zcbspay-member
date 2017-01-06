/* 
 * IBasePageService.java  
 * 
 * version TODO
 *
 * 2015-7-13 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zcbspay.platform.member.commons.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.zcbspay.platform.member.commons.bean.DefaultPageResult;
import com.zcbspay.platform.member.commons.bean.PagedResult;
import com.zcbspay.platform.member.commons.dao.impl.HibernateBaseDAOImpl;

/**
 *  A dao implement of  {@link HibernateBaseDAOImpl}. This implement support for page query by QBE
 * 
 * @author yangying
 * @version
 * @date 2015-7-13 下午04:02:06
 * @since
 */
public abstract class AbstractBasePageService<T,E> {
	/**
	 * 
	 * @param page
	 *            the number of page, more than 1 at least
	 * @param pageSize
	 *            the size per page
	 * @param queryBean
	 * @return
	 */
	@Transactional
	public PagedResult<E> queryPaged(int page, int pageSize, T queryBean) {
		long total = getTotal(queryBean);
		page = currentPage(total, page, pageSize);
		if (page<1) {
            page=1;
        }
		int offset = (page - 1) * pageSize;
		List<E> items = getItem(offset, pageSize, queryBean);

		return new DefaultPageResult<E>(items, total);
	}
	/**
	 * 当前页
	 * @param total
	 * @param page
	 * @param pageSize
	 * @return
	 */
	private int currentPage(long total, int page, int pageSize) {
		long maxPage = (total % pageSize == 0) ? (total / pageSize) : (total
				/ pageSize + 1);
		if (page > maxPage) {
			return Long.valueOf(maxPage).intValue();
		}
		return page;
	}
 
	/**
	 * 总记录数
	 * @param example
	 * @return
	 */
	protected abstract long getTotal(T example);
	/**
	 * 结果集
	 * @param offset
	 * @param pageSize
	 * @param example
	 * @return
	 */
	protected abstract List<E> getItem(int offset, int pageSize, T example);
}
