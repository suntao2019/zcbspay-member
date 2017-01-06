/* 
 * PersonService.java  
 * 
 * version TODO
 *
 * 2015年9月9日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zcbspay.platform.member.individual.service;

import com.zcbspay.platform.member.individual.bean.PersonBean;
import com.zcbspay.platform.member.individual.bean.PersonManager;
import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.member.exception.MemberBussinessException;


/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年9月9日 下午6:42:21
 * @since 
 */
public interface PersonService {
    /**
     * 个人会员录入
     * @param pers
     * @return busiCode
     * @throws AbstractBusiAcctException 
     */
    public String save(PersonBean pers,long userId)throws MemberBussinessException, AbstractBusiAcctException;

    /**
     * 通过手机号查询会员信息
     * @param phone
     * @return
     */
    public PersonBean getPersonByPhone(String phone);
       /**
        * 通过emal查询会员信息
        * @param email
        * @return
        */
    public PersonBean getPersonByEmail(String email);
    
        /**
         * 通过memberId得到会员信息
         * @param memberId
         * @return
         */
    public PersonManager getPersonBeanByMemberId(String memberId);

    /**
     * 通过memberId得到会员信息
     * @param memberId
     * @return
     */
    public PersonBean getPersonByMemberId(String memberId);
}
