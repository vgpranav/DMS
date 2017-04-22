package com.dms.beans;

import java.util.Date;

public class Smslogger {

	private long smsloggerid;
	private String smstype;
	private String smsmsg;
	private long smscount;
	private String smsusers;
	private Date smsdate;
	private String smsresponse;
	private String smshttpcode;
	
	
	
	
	public Smslogger(String smstype, String smsmsg, long smscount, String smsusers, Date smsdate,
			String smsresponse,String smshttpcode) {
		super();
		this.smstype = smstype;
		this.smsmsg = smsmsg;
		this.smscount = smscount;
		this.smsusers = smsusers;
		this.smsdate = smsdate;
		this.smsresponse = smsresponse;
		this.smshttpcode = smshttpcode;
	}
	
	public long getSmsloggerid() {
		return smsloggerid;
	}
	public void setSmsloggerid(long smsloggerid) {
		this.smsloggerid = smsloggerid;
	}
	public String getSmstype() {
		return smstype;
	}
	public void setSmstype(String smstype) {
		this.smstype = smstype;
	}
	public String getSmsmsg() {
		return smsmsg;
	}
	public void setSmsmsg(String smsmsg) {
		this.smsmsg = smsmsg;
	}
	public long getSmscount() {
		return smscount;
	}
	public void setSmscount(long smscount) {
		this.smscount = smscount;
	}
	public String getSmsusers() {
		return smsusers;
	}
	public void setSmsusers(String smsusers) {
		this.smsusers = smsusers;
	}
	public Date getSmsdate() {
		return smsdate;
	}
	public void setSmsdate(Date smsdate) {
		this.smsdate = smsdate;
	}
	public String getSmsresponse() {
		return smsresponse;
	}
	public void setSmsresponse(String smsresponse) {
		this.smsresponse = smsresponse;
	}
	
	@Override
	public String toString() {
		return "Smslogger [smsloggerid=" + smsloggerid + ", smstype=" + smstype + ", smsmsg=" + smsmsg + ", smscount="
				+ smscount + ", smsusers=" + smsusers + ", smsdate=" + smsdate + ", smsresponse=" + smsresponse + "]";
	}

	public String getSmshttpcode() {
		return smshttpcode;
	}

	public void setSmshttpcode(String smshttpcode) {
		this.smshttpcode = smshttpcode;
	}

}
