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
package com.zcbspay.platform.member.commons.service;

import com.zcbspay.platform.member.commons.bean.PagedResult;

/**
 * 基础分页接口
 *
 * @author yangying
 * @version
 * @date 2015-7-13 下午04:11:26
 * @since 
 */
public interface IBasePageService<T,E> {
    public PagedResult<E> queryPaged(int page, int pageSize, T example);
}
