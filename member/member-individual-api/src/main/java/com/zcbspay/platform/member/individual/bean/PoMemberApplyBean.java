/* 
 * PoMemberApplyBean.java  
 * 
 * version TODO
 *
 * 2016年11月10日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zcbspay.platform.member.individual.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Class Description
 *
 * @author houyong
 * @version
 * @date 2016年11月10日 下午2:54:27
 * @since 
 */
public class PoMemberApplyBean implements Serializable{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -935102899779534028L;
    private long selfId;
    private long memId;
    private String memberId;
    private String instiCode;
    private String memberName;
    private String loginName;
    private String pwd;
    private String payPwd;
    private String realnameLv;
    private String phone;
    private String email;
    private String memberType;
    private String status;
    private String registerIdent;
    private Date lockTime;
    private Date inTime;
    private Date upTime;
    /**
     * @return the selfId
     */
    public long getSelfId() {
        return selfId;
    }
    /**
     * @param selfId the selfId to set
     */
    public void setSelfId(long selfId) {
        this.selfId = selfId;
    }
    /**
     * @return the memId
     */
    public long getMemId() {
        return memId;
    }
    /**
     * @param memId the memId to set
     */
    public void setMemId(long memId) {
        this.memId = memId;
    }
    /**
     * @return the memberId
     */
    public String getMemberId() {
        return memberId;
    }
    /**
     * @param memberId the memberId to set
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
    /**
     * @return the instiCode
     */
    public String getInstiCode() {
        return instiCode;
    }
    /**
     * @param instiCode the instiCode to set
     */
    public void setInstiCode(String instiCode) {
        this.instiCode = instiCode;
    }
    /**
     * @return the memberName
     */
    public String getMemberName() {
        return memberName;
    }
    /**
     * @param memberName the memberName to set
     */
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
    /**
     * @return the loginName
     */
    public String getLoginName() {
        return loginName;
    }
    /**
     * @param loginName the loginName to set
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    /**
     * @return the pwd
     */
    public String getPwd() {
        return pwd;
    }
    /**
     * @param pwd the pwd to set
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    /**
     * @return the payPwd
     */
    public String getPayPwd() {
        return payPwd;
    }
    /**
     * @param payPwd the payPwd to set
     */
    public void setPayPwd(String payPwd) {
        this.payPwd = payPwd;
    }
    /**
     * @return the realnameLv
     */
    public String getRealnameLv() {
        return realnameLv;
    }
    /**
     * @param realnameLv the realnameLv to set
     */
    public void setRealnameLv(String realnameLv) {
        this.realnameLv = realnameLv;
    }
    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }
    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }
    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * @return the memberType
     */
    public String getMemberType() {
        return memberType;
    }
    /**
     * @param memberType the memberType to set
     */
    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }
    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }
    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    /**
     * @return the registerIdent
     */
    public String getRegisterIdent() {
        return registerIdent;
    }
    /**
     * @param registerIdent the registerIdent to set
     */
    public void setRegisterIdent(String registerIdent) {
        this.registerIdent = registerIdent;
    }
    /**
     * @return the lockTime
     */
    public Date getLockTime() {
        return lockTime;
    }
    /**
     * @param lockTime the lockTime to set
     */
    public void setLockTime(Date lockTime) {
        this.lockTime = lockTime;
    }
    /**
     * @return the inTime
     */
    public Date getInTime() {
        return inTime;
    }
    /**
     * @param inTime the inTime to set
     */
    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }
    /**
     * @return the upTime
     */
    public Date getUpTime() {
        return upTime;
    }
    /**
     * @param upTime the upTime to set
     */
    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }
    
    
}
