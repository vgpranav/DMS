package com.dms.beans;

import java.util.Date;

public class BuilderManager {

	private long buildermanagermappingid;
	private int builderid;
	private int userid;
	private Date createdon;
	private int createdby;
	private String username;
	
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public long getBuildermanagermappingid() {
		return buildermanagermappingid;
	}
	public void setBuildermanagermappingid(long buildermanagermappingid) {
		this.buildermanagermappingid = buildermanagermappingid;
	}
	public int getBuilderid() {
		return builderid;
	}
	public void setBuilderid(int builderid) {
		this.builderid = builderid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public Date getCreatedon() {
		return createdon;
	}
	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}
	public int getCreatedby() {
		return createdby;
	}
	public void setCreatedby(int createdby) {
		this.createdby = createdby;
	}
	
	@Override
	public String toString() {
		return "BuilderManager [buildermanagermappingid=" + buildermanagermappingid + ", builderid=" + builderid
				+ ", userid=" + userid + ", createdon=" + createdon + ", createdby=" + createdby + ", username="
				+ username + "]";
	}
	
	 
	
}
