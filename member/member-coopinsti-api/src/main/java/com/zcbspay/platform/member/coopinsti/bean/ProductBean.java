/* 
 * ProductBean.java  
 * 
 * version TODO
 *
 * 2016年10月9日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zcbspay.platform.member.coopinsti.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Class Description
 *
 * @author guojia
 * @version
 * @date 2016年10月9日 下午12:01:03
 * @since
 */
public class ProductBean implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6396764966231479663L;
	private String prdtver;
	private Long prdtid;
	private String prdtname;
	private String status;
	private Date intime;
	private Long inuser;
	private Date uptime;
	private Long upuser;
	private String notes;
	private String remarks;

	/**
	 * @return the prdtver
	 */
	public String getPrdtver() {
		return prdtver;
	}

	/**
	 * @param prdtver
	 *            the prdtver to set
	 */
	public void setPrdtver(String prdtver) {
		this.prdtver = prdtver;
	}

	/**
	 * @return the prdtid
	 */
	public Long getPrdtid() {
		return prdtid;
	}

	/**
	 * @param prdtid
	 *            the prdtid to set
	 */
	public void setPrdtid(Long prdtid) {
		this.prdtid = prdtid;
	}

	/**
	 * @return the prdtname
	 */
	public String getPrdtname() {
		return prdtname;
	}

	/**
	 * @param prdtname
	 *            the prdtname to set
	 */
	public void setPrdtname(String prdtname) {
		this.prdtname = prdtname;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the intime
	 */
	public Date getIntime() {
		return intime;
	}

	/**
	 * @param intime
	 *            the intime to set
	 */
	public void setIntime(Date intime) {
		this.intime = intime;
	}

	/**
	 * @return the inuser
	 */
	public Long getInuser() {
		return inuser;
	}

	/**
	 * @param inuser
	 *            the inuser to set
	 */
	public void setInuser(Long inuser) {
		this.inuser = inuser;
	}

	/**
	 * @return the uptime
	 */
	public Date getUptime() {
		return uptime;
	}

	/**
	 * @param uptime
	 *            the uptime to set
	 */
	public void setUptime(Date uptime) {
		this.uptime = uptime;
	}

	/**
	 * @return the upuser
	 */
	public Long getUpuser() {
		return upuser;
	}

	/**
	 * @param upuser
	 *            the upuser to set
	 */
	public void setUpuser(Long upuser) {
		this.upuser = upuser;
	}

	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @param notes
	 *            the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks
	 *            the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
