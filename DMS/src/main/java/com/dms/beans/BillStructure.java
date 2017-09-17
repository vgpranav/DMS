package com.dms.beans;

import java.util.Date;

public class BillStructure {

	private long billstructureid;
	private String billstructurecode;  
	private int societyid;
	private int billcycletype;  
	private String billcyclevalue;  
	private String 	billcyclevalue1;
	private int isactive;  
	private long createdby;  
	private Date createdon;
	private String billcomponents;
	private String year;
	private String username;
	
	public long getBillstructureid() {
		return billstructureid;
	}
	public void setBillstructureid(long billstructureid) {
		this.billstructureid = billstructureid;
	}
	public String getBillstructurecode() {
		return billstructurecode;
	}
	public void setBillstructurecode(String billstructurecode) {
		this.billstructurecode = billstructurecode;
	}
	public int getSocietyid() {
		return societyid;
	}
	public void setSocietyid(int societyid) {
		this.societyid = societyid;
	}
	public int getBillcycletype() {
		return billcycletype;
	}
	public void setBillcycletype(int billcycletype) {
		this.billcycletype = billcycletype;
	}
	public String getBillcyclevalue() {
		return billcyclevalue;
	}
	public void setBillcyclevalue(String billcyclevalue) {
		this.billcyclevalue = billcyclevalue;
	}
	public int getIsactive() {
		return isactive;
	}
	public void setIsactive(int isactive) {
		this.isactive = isactive;
	}
	public long getCreatedby() {
		return createdby;
	}
	public void setCreatedby(long createdby) {
		this.createdby = createdby;
	}
	public Date getCreatedon() {
		return createdon;
	}
	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}
	public String getBillcomponents() {
		return billcomponents;
	}
	public void setBillcomponents(String billcomponents) {
		this.billcomponents = billcomponents;
	}
	 
	public String getBillcyclevalue1() {
		return billcyclevalue1;
	}
	public void setBillcyclevalue1(String billcyclevalue1) {
		this.billcyclevalue1 = billcyclevalue1;
	}
	 
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	 
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Override
	public String toString() {
		return "BillStructure [billstructureid=" + billstructureid + ", billstructurecode=" + billstructurecode
				+ ", societyid=" + societyid + ", billcycletype=" + billcycletype + ", billcyclevalue=" + billcyclevalue
				+ ", billcyclevalue1=" + billcyclevalue1 + ", isactive=" + isactive + ", createdby=" + createdby
				+ ", createdon=" + createdon + ", billcomponents=" + billcomponents + ", year=" + year + ", username="
				+ username + "]";
	}

	
}
