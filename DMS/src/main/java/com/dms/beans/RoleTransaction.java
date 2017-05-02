package com.dms.beans;

import java.util.Date;

public class RoleTransaction {
	
	private int roleid;
	private String rolename;
	private int isactive;
	private Date createdon;
	
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
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
		return "RoleTransaction [roleid=" + roleid + ", rolename=" + rolename + ", isactive=" + isactive
				+ ", createdon=" + createdon + "]";
	}
	
}
