package com.dms.beans;

import java.util.Date;

public class Actionlogger {

	private long actionloggerid;
	private long userid;
	private String action;
	private String payload;
	private Date actiondate;
	private String username;
	
	public long getActionloggerid() {
		return actionloggerid;
	}
	public void setActionloggerid(long actionloggerid) {
		this.actionloggerid = actionloggerid;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getPayload() {
		return payload;
	}
	public void setPayload(String payload) {
		this.payload = payload;
	}
	public Date getActiondate() {
		return actiondate;
	}
	public void setActiondate(Date actiondate) {
		this.actiondate = actiondate;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Actionlogger [actionloggerid=" + actionloggerid + ", userid=" + userid + ", action=" + action
				+ ", payload=" + payload + ", actiondate=" + actiondate + ", username=" + username + "]";
	}
	
}
