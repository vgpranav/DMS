package com.dms.beans;

import java.util.Date;

public class Bill {

	private long billid;
	private long billstructureid;
	private long userid;
	private long societyid;
	private long billdataid;
	private long createdby;
	private Date createdon;
	private int isactive;
	private int ispaid;
	private Date ispaidon;
	private String paymode;
	private double payamount;
	
	public long getBillid() {
		return billid;
	}
	public void setBillid(long billid) {
		this.billid = billid;
	}
	public long getBillstructureid() {
		return billstructureid;
	}
	public void setBillstructureid(long billstructureid) {
		this.billstructureid = billstructureid;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public long getSocietyid() {
		return societyid;
	}
	public void setSocietyid(long societyid) {
		this.societyid = societyid;
	}
	public long getBilldataid() {
		return billdataid;
	}
	public void setBilldataid(long billdataid) {
		this.billdataid = billdataid;
	}
	public long getCreatedby() {
		return createdby;
	}
	public void setCreatedby(long createdby) {
		this.createdby = createdby;
	}
	public Date getCreatedon() {
		return createdon;
	}
	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}
	public int getIsactive() {
		return isactive;
	}
	public void setIsactive(int isactive) {
		this.isactive = isactive;
	}
	public int getIspaid() {
		return ispaid;
	}
	public void setIspaid(int ispaid) {
		this.ispaid = ispaid;
	}
	public Date getIspaidon() {
		return ispaidon;
	}
	public void setIspaidon(Date ispaidon) {
		this.ispaidon = ispaidon;
	}
	public String getPaymode() {
		return paymode;
	}
	public void setPaymode(String paymode) {
		this.paymode = paymode;
	}
	public double getPayamount() {
		return payamount;
	}
	public void setPayamount(double payamount) {
		this.payamount = payamount;
	}

	@Override
	public String toString() {
		return "Bill [billid=" + billid + ", billstructureid=" + billstructureid + ", userid=" + userid + ", societyid="
				+ societyid + ", billdataid=" + billdataid + ", createdby=" + createdby + ", createdon=" + createdon
				+ ", isactive=" + isactive + ", ispaid=" + ispaid + ", ispaidon=" + ispaidon + ", paymode=" + paymode
				+ ", payamount=" + payamount + "]";
	}
	
}
