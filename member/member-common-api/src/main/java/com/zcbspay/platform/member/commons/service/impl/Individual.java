/* 
 * Individual.java  
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
 * 个人会员
 *
 * @author yangying
 * @version
 * @date 2015年8月20日 下午3:48:29
 * @since 
 */
public abstract class Individual implements Serializable, BusinessActor{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 2011453996610936269L;

    @Override
    public BusinessActorType getBusinessActorType(){
        return BusinessActorType.INDIVIDUAL;
    }
}
