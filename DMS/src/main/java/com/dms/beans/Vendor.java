package com.dms.beans;

import java.util.Date;

public class Vendor {
	
	private long vendorid; 
	private long societyid;
	private String companyname; 
	private String jobnature; 
	private String contactperson; 
	private String address; 
	private String contactno; 
	private String alternateno; 
	private String email; 
	private String remark;
	private int isactive;
	private Date createdon;
	private String createdby;
	
	
	
	public long getVendorid() {
		return vendorid;
	}
	public void setVendorid(long vendorid) {
		this.vendorid = vendorid;
	}
	public long getSocietyid() {
		return societyid;
	}
	public void setSocietyid(long societyid) {
		this.societyid = societyid;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getJobnature() {
		return jobnature;
	}
	public void setJobnature(String jobnature) {
		this.jobnature = jobnature;
	}
	public String getContactperson() {
		return contactperson;
	}
	public void setContactperson(String contactperson) {
		this.contactperson = contactperson;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContactno() {
		return contactno;
	}
	public void setContactno(String contactno) {
		this.contactno = contactno;
	}
	public String getAlternateno() {
		return alternateno;
	}
	public void setAlternateno(String alternateno) {
		this.alternateno = alternateno;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	@Override
	public String toString() {
		return "Vendor [vendorid=" + vendorid + ", societyid=" + societyid + ", companyname=" + companyname
				+ ", jobnature=" + jobnature + ", contactperson=" + contactperson + ", address=" + address
				+ ", contactno=" + contactno + ", alternateno=" + alternateno + ", email=" + email + ", remark="
				+ remark + ", isactive=" + isactive + ", createdon=" + createdon + ", createdby=" + createdby + "]";
	}
	
	
	
}
