package com.dms.beans;

import java.util.Date;

public class IncomeExpenseMaster {
 
	private long incexpmasterid; 
	private String type;
	private String subtype; 
	private String keyname;
	private int isactive;
	private Date createdon;
	
	public long getIncexpmasterid() {
		return incexpmasterid;
	}
	public void setIncexpmasterid(long incexpmasterid) {
		this.incexpmasterid = incexpmasterid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSubtype() {
		return subtype;
	}
	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}
	public String getKeyname() {
		return keyname;
	}
	public void setKeyname(String keyname) {
		this.keyname = keyname;
	}
	public int getIsactive() {
		return isactive;
	}
	public void setIsactive(int isactive) {
		this.isactive = isactive;
	}
	public Date getCreatedon() {
		return createdon;
	}
	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}
	
	
	@Override
	public String toString() {
		return "IncomeExpenseMaster [incexpmasterid=" + incexpmasterid + ", type=" + type + ", subtype=" + subtype
				+ ", keyname=" + keyname + ", isactive=" + isactive + ", createdon=" + createdon + "]";
	}
	
}
