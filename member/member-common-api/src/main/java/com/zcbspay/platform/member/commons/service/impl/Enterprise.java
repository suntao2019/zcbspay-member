/* 
 * Merchant.java  
 * 
 * version 1.0
 *
 * 2015年8月20日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zcbspay.platform.member.commons.service.impl;

import java.io.Serializable;

import com.zcbspay.platform.member.commons.enums.BusinessActorType;



/**
 * 企业会员
 *
 * @author yangying
 * @version
 * @date 2015年8月20日 下午3:46:56
 * @since 
 */
public abstract class Enterprise implements Serializable, BusinessActor{ 
	/**
     * serialVersionUID
     */
    private static final long serialVersionUID = -2392546889563204242L;
    public abstract String getBusinessActorName();
	public abstract String getBusinessActorNo();
	@Override
    public BusinessActorType getBusinessActorType(){
        return BusinessActorType.ENTERPRISE;
    }
}
