package com.dms.beans;

import java.util.Date;

public class CommitteeMaster {

	private long positionid; 
	private String positionname; 
	private Date createdon;
	
	public long getPositionid() {
		return positionid;
	}
	public void setPositionid(long positionid) {
		this.positionid = positionid;
	}
	public String getPositionname() {
		return positionname;
	}
	public void setPositionname(String positionname) {
		this.positionname = positionname;
	}
	public Date getCreatedon() {
		return createdon;
	}
	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}
	
	@Override
	public String toString() {
		return "CommitteeMaster [positionid=" + positionid + ", positionname=" + positionname + ", createdon="
				+ createdon + "]";
	}
	
	
}
