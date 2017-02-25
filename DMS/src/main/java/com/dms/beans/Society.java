package com.dms.beans;

import java.util.Date;

public class Society {
	
	private int societyid;
	private int societytypeid;
	private String societyname;
	private int societyprofileid;
	private String addressline1; 
	private String addressline2; 
	private String ward; 
	private String district;
	private String state; 
	private String pincode; 
	private String createdby; 
	private Date createdon;
	
	
	public int getSocietyid() {
		return societyid;
	}
	public void setSocietyid(int societyid) {
		this.societyid = societyid;
	}
	public String getSocietyname() {
		return societyname;
	}
	public void setSocietyname(String societyname) {
		this.societyname = societyname;
	}
	public int getSocietyprofileid() {
		return societyprofileid;
	}
	public void setSocietyprofileid(int societyprofileid) {
		this.societyprofileid = societyprofileid;
	}
	public String getAddressline1() {
		return addressline1;
	}
	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
	}
	public String getAddressline2() {
		return addressline2;
	}
	public void setAddressline2(String addressline2) {
		this.addressline2 = addressline2;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public Date getCreatedon() {
		return createdon;
	}
	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}
	
	public int getSocietytypeid() {
		return societytypeid;
	}
	public void setSocietytypeid(int societytypeid) {
		this.societytypeid = societytypeid;
	}
	
	@Override
	public String toString() {
		return "Society [societyid=" + societyid + ", societytypeid=" + societytypeid + ", societyname=" + societyname
				+ ", societyprofileid=" + societyprofileid + ", addressline1=" + addressline1 + ", addressline2="
				+ addressline2 + ", ward=" + ward + ", district=" + district + ", state=" + state + ", pincode="
				+ pincode + ", createdby=" + createdby + ", createdon=" + createdon + "]";
	}
	
	
	
}
