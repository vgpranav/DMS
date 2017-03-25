package com.dms.beans;

public class GenericBean {
	
	private long societyid;
	private long doctypeid;
	private String createdby;
	private long societydocmappingid;
	private String societyname;
	private String doctypename;
	private long roleid;
	private String rolename;
	private int active;
	private String fieldname;
	private String fieldvalue;
	
	public String getFieldname() {
		return fieldname;
	}
	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}
	public String getFieldvalue() {
		return fieldvalue;
	}
	public void setFieldvalue(String fieldvalue) {
		this.fieldvalue = fieldvalue;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public long getRoleid() {
		return roleid;
	}
	public void setRoleid(long roleid) {
		this.roleid = roleid;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getSocietyname() {
		return societyname;
	}
	public void setSocietyname(String societyname) {
		this.societyname = societyname;
	}
	public String getDoctypename() {
		return doctypename;
	}
	public void setDoctypename(String doctypename) {
		this.doctypename = doctypename;
	}
	public long getSocietyid() {
		return societyid;
	}
	public void setSocietyid(long societyid) {
		this.societyid = societyid;
	}
	public long getDoctypeid() {
		return doctypeid;
	}
	public void setDoctypeid(long doctypeid) {
		this.doctypeid = doctypeid;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public long getSocietydocmappingid() {
		return societydocmappingid;
	}
	public void setSocietydocmappingid(long societydocmappingid) {
		this.societydocmappingid = societydocmappingid;
	}
	
}
