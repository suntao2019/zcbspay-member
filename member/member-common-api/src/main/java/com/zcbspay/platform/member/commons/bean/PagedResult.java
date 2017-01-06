/* 
 * PageResult.java  
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
 * 分页查询结果集接口
 *
 * @author guojia
 * @version
 * @date 2015-6-25 下午04:37:33
 * @since 
 */
public interface PagedResult<T> extends Serializable {     
    long getTotal();
    List<T> getPagedResult() throws  IllegalAccessException;
}
