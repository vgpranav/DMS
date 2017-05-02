package com.dms.beans;

import java.util.Date;

public class Project {
	
	private long projectid; 
	private long builderid;
	private String projectname;
 	private String plotarea;
	private Date registrationdate;
	private String towernos;
	private String resnos;
	private String bungnos;
	private String pentanos;
	private String shopnos;
	private String galanos;
	private String createdby;
	private Date createdon;
	private int active;
	private String buildername;
	
	private String	street;
	private String	landmark;
	private String	area;
	private String	city;
	private String	district;
	private String	state;
	private String	country;
	private String	pincode;
	 
	
	
	@Override
	public String toString() {
		return "Project [projectid=" + projectid + ", builderid=" + builderid + ", projectname=" + projectname
				+ ", plotarea=" + plotarea + ", registrationdate=" + registrationdate + ", towernos=" + towernos
				+ ", resnos=" + resnos + ", bungnos=" + bungnos + ", pentanos=" + pentanos + ", shopnos=" + shopnos
				+ ", galanos=" + galanos + ", createdby=" + createdby + ", createdon=" + createdon + ", active="
				+ active + ", buildername=" + buildername + ", street=" + street + ", landmark=" + landmark + ", area="
				+ area + ", city=" + city + ", district=" + district + ", state=" + state + ", country=" + country
				+ ", pincode=" + pincode + "]";
	}
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
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
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public long getProjectid() {
		return projectid;
	}
	public void setProjectid(long projectid) {
		this.projectid = projectid;
	}
	 
	public String getPlotarea() {
		return plotarea;
	}
	public void setPlotarea(String plotarea) {
		this.plotarea = plotarea;
	}
	public Date getRegistrationdate() {
		return registrationdate;
	}
	public void setRegistrationdate(Date registrationdate) {
		this.registrationdate = registrationdate;
	}
	public String getTowernos() {
		return towernos;
	}
	public void setTowernos(String towernos) {
		this.towernos = towernos;
	}
	public String getResnos() {
		return resnos;
	}
	public void setResnos(String resnos) {
		this.resnos = resnos;
	}
	public String getBungnos() {
		return bungnos;
	}
	public void setBungnos(String bungnos) {
		this.bungnos = bungnos;
	}
	public String getPentanos() {
		return pentanos;
	}
	public void setPentanos(String pentanos) {
		this.pentanos = pentanos;
	}
	public String getShopnos() {
		return shopnos;
	}
	public void setShopnos(String shopnos) {
		this.shopnos = shopnos;
	}
	public String getGalanos() {
		return galanos;
	}
	public void setGalanos(String galanos) {
		this.galanos = galanos;
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
	
	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
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
	
	 
	
}
