package com.dms.beans;

import java.util.Date;

public class Doctype {

	private int doctypeid;
	private String doctypename;
	private String doctypedesc;
	private int active;
	private String createdby;
	private Date createdon;
	public int getDoctypeid() {
		return doctypeid;
	}
	public void setDoctypeid(int doctypeid) {
		this.doctypeid = doctypeid;
	}
	public String getDoctypename() {
		return doctypename;
	}
	public void setDoctypename(String doctypename) {
		this.doctypename = doctypename;
	}
	public String getDoctypedesc() {
		return doctypedesc;
	}
	public void setDoctypedesc(String doctypedesc) {
		this.doctypedesc = doctypedesc;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
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
	
	@Override
	public String toString() {
		return "Doctype [doctypeid=" + doctypeid + ", doctypename=" + doctypename + ", doctypedesc=" + doctypedesc
				+ ", active=" + active + ", createdby=" + createdby + ", createdon=" + createdon + "]";
	}
	
	
}
