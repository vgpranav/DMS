package com.dms.beans;

import java.util.Date;

public class Loginhistory {

	private long loginhistoryid;
	private long userid;
	private Date logintime;
	private Date logouttime;
	private String sessionkey;
	private String ipaddress;
	private String username;
	public long getLoginhistoryid() {
		return loginhistoryid;
	}
	public void setLoginhistoryid(long loginhistoryid) {
		this.loginhistoryid = loginhistoryid;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public Date getLogintime() {
		return logintime;
	}
	public void setLogintime(Date logintime) {
		this.logintime = logintime;
	}
	public Date getLogouttime() {
		return logouttime;
	}
	public void setLogouttime(Date logouttime) {
		this.logouttime = logouttime;
	}
	public String getSessionkey() {
		return sessionkey;
	}
	public void setSessionkey(String sessionkey) {
		this.sessionkey = sessionkey;
	}
	public String getIpaddress() {
		return ipaddress;
	}
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Loginhistory [loginhistoryid=" + loginhistoryid + ", userid=" + userid + ", logintime=" + logintime
				+ ", logouttime=" + logouttime + ", sessionkey=" + sessionkey + ", ipaddress=" + ipaddress
				+ ", username=" + username + "]";
	}
	
}
