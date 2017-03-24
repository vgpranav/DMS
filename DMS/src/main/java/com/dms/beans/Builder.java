package com.dms.beans;

import java.util.Date;

public class Builder {
	
	public long builderid;
	public String buildername;
	public String address;
	public String blockno;
	public String premisesname; 
	public String streetname; 
	public String landmark; 
	public String area; 
	public String city; 
	public String pincode; 
	public String state; 
	public String country;
	public String createdby; 
	public Date createdon; 
	public int active;
	
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getBuilderid() {
		return builderid;
	}
	public void setBuilderid(long builderid) {
		this.builderid = builderid;
	}
	public String getBuildername() {
		return buildername;
	}
	public void setBuildername(String buildername) {
		this.buildername = buildername;
	}
	public String getBlockno() {
		return blockno;
	}
	public void setBlockno(String blockno) {
		this.blockno = blockno;
	}
	public String getPremisesname() {
		return premisesname;
	}
	public void setPremisesname(String premisesname) {
		this.premisesname = premisesname;
	}
	public String getStreetname() {
		return streetname;
	}
	public void setStreetname(String streetname) {
		this.streetname = streetname;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
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
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	
	@Override
	public String toString() {
		return "Builder [builderid=" + builderid + ", buildername=" + buildername + ", address=" + address
				+ ", blockno=" + blockno + ", premisesname=" + premisesname + ", streetname=" + streetname
				+ ", landmark=" + landmark + ", area=" + area + ", city=" + city + ", pincode=" + pincode + ", state="
				+ state + ", country=" + country + ", createdby=" + createdby + ", createdon=" + createdon + ", active="
				+ active + "]";
	}
}
