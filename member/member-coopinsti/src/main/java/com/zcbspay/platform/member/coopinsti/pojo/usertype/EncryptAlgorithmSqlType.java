package com.zcbspay.platform.member.coopinsti.pojo.usertype;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;

import com.zcbspay.platform.member.coopinsti.bean.enums.EncryptAlgorithm;
import com.zlebank.zplatform.member.commons.enums.BaseEnumSqlType;

public class EncryptAlgorithmSqlType extends BaseEnumSqlType<EncryptAlgorithm>{
    @Override
    public Object nullSafeGet(ResultSet rs,
            String[] names,
            SessionImplementor arg2,
            Object owner) throws HibernateException, SQLException {
        return EncryptAlgorithm.fromValue(rs.getString(names[0]));
    }

    @Override
    public void nullSafeSet(PreparedStatement st,
            Object value,
            int index,
            SessionImplementor arg2) throws HibernateException, SQLException {
        EncryptAlgorithm encryptAlgorithm = value != null
                ? (EncryptAlgorithm) value
                : EncryptAlgorithm.UNKNOW;
        st.setString(index, encryptAlgorithm.getAlgName());
    }
}
