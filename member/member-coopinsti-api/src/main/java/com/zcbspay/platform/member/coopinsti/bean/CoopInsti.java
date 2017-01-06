package com.zcbspay.platform.member.coopinsti.bean;

import java.io.Serializable;
import java.sql.Timestamp;
/**
 * A bean represent cooperative institution
 * @author yangying
 *
 */
public class CoopInsti implements Serializable{
	
	/**
     * serialVersionUID
     */
    private static final long serialVersionUID = 5698426751690977215L;
    private long id;
	private String instiCode;
	private String instiName;
	
	private String status;
	private Timestamp inTime;
	private long inUserId;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getInstiCode() {
		return instiCode;
	}
	public void setInstiCode(String instiCode) {
		this.instiCode = instiCode;
	}
	public String getInstiName() {
		return instiName;
	}
	public void setInstiName(String instiName) {
		this.instiName = instiName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Timestamp getInTime() {
		return inTime;
	}
	public void setInTime(Timestamp inTime) {
		this.inTime = inTime;
	}
	public long getInUserId() {
		return inUserId;
	}
	public void setInUserId(long inUserId) {
		this.inUserId = inUserId;
	}
}
