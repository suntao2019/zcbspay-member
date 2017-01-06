/* 
 * IndustrySqlType.java  
 * 
 * version TODO
 *
 * 2015年9月9日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zcbspay.platform.member.individual.pojo.usertype;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;

import com.zcbspay.platform.member.individual.bean.enums.MemberType;
import com.zlebank.zplatform.member.commons.enums.BaseEnumSqlType;

/**
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年9月9日 下午9:57:05
 * @since 
 */
public class MemberSqlType  extends BaseEnumSqlType<MemberType>{
    
    public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor arg2,Object owner)
            throws HibernateException, SQLException {
        return MemberType.fromValue(rs.getString(names[0]));
    }

    public void nullSafeSet(PreparedStatement st, Object value, int index,SessionImplementor arg2)
            throws HibernateException, SQLException {
        MemberType status = value!=null?(MemberType)value:MemberType.UNKNOW;
        st.setString(index, status.getCode());
    }
}