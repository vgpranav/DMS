package com.dms.beans;

import java.util.Date;

public class Project {
	
	private long projectid; 
	private long builderid;
	private String projectname;
	private String siteaddress;
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
	
	public long getProjectid() {
		return projectid;
	}
	public void setProjectid(long projectid) {
		this.projectid = projectid;
	}
	public String getSiteaddress() {
		return siteaddress;
	}
	public void setSiteaddress(String siteaddress) {
		this.siteaddress = siteaddress;
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
	
	@Override
	public String toString() {
		return "Project [projectid=" + projectid + ", builderid=" + builderid + ", projectname=" + projectname
				+ ", siteaddress=" + siteaddress + ", plotarea=" + plotarea + ", registrationdate=" + registrationdate
				+ ", towernos=" + towernos + ", resnos=" + resnos + ", bungnos=" + bungnos + ", pentanos=" + pentanos
				+ ", shopnos=" + shopnos + ", galanos=" + galanos + ", createdby=" + createdby + ", createdon="
				+ createdon + ", active=" + active + ", buildername=" + buildername + "]";
	}
	
}
