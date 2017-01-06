/* 
 * MemberDao.java  
 * 
 * version TODO
 *
 * 2015年9月13日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zcbspay.platform.member.individual.dao;

import java.util.List;

import com.zcbspay.platform.member.individual.bean.enums.MemberType;
import com.zcbspay.platform.member.individual.pojo.PojoMember;
import com.zlebank.zplatform.member.commons.dao.BaseDAO;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年9月13日 下午6:27:16
 * @since 
 */

public interface MemberDAO extends BaseDAO<PojoMember> {
       /**
        * 通过邮箱得到会员信息 
        * @param email
        * @return PojoMember
        */
        public PojoMember getMemberByEmail(String email);
            /**
             * 通过电话得到会员信息
             * @param phone
             * @return PojoMember
             */
        public PojoMember getMemberByphone(String phone);
        
        /**
         * 通过 会员ID、状态得到会员
         * @param memberId
         * @param type
         * @return
         */
        public PojoMember getMemberByMemberId(String memberId,MemberType type);
    
        /**
         * 通过登陆名得到会员信息
         * @param loginName
         * @return PojoMember
         */
        public List<PojoMember> getMemberByLoginName(String loginName);
        /**
         * 通过登陆名和合作机构得到会员信息
         * @param loginName
         * @param phone
         * @return 
         */
        public List<PojoMember> getMemberByLoginNameAndCoopInsti(String loginName, long instiCode);
        /**
         * 通过手机号和合作机构得到会员信息
         * @param loginName
         * @param phone
         * @return 
         */
        public List<PojoMember> getMemberByPhoneAndCoopInsti(String phone, long instiCode);
}
