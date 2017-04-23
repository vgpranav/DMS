package com.dms.beans;

import java.util.Date;

public class Otphandler {

	private long otphandlerid;
	private String mobileNo;
	private String otp;
	private int active;
	private Date createdon;
	
	
	public long getOtphandlerid() {
		return otphandlerid;
	}
	public void setOtphandlerid(long otphandlerid) {
		this.otphandlerid = otphandlerid;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public Date getCreatedon() {
		return createdon;
	}
	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}
	
	
	@Override
	public String toString() {
		return "Otphandler [otphandlerid=" + otphandlerid + ", mobileNo=" + mobileNo + ", otp=" + otp + ", active="
				+ active + ", createdon=" + createdon + "]";
	}
	
}
