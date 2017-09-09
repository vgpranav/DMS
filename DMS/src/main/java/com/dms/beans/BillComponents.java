package com.dms.beans;

import java.util.Date;

public class BillComponents {

	private int billcomponentid;
	private int billstructureid;
	private int expenseid;
	private int isactive;
	private Date createdon;
	
	public int getBillcomponentid() {
		return billcomponentid;
	}
	public void setBillcomponentid(int billcomponentid) {
		this.billcomponentid = billcomponentid;
	}
	public int getBillstructureid() {
		return billstructureid;
	}
	public void setBillstructureid(int billstructureid) {
		this.billstructureid = billstructureid;
	}
	public int getExpenseid() {
		return expenseid;
	}
	public void setExpenseid(int expenseid) {
		this.expenseid = expenseid;
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
		return "BillComponents [billcomponentid=" + billcomponentid + ", billstructureid=" + billstructureid
				+ ", expenseid=" + expenseid + ", isactive=" + isactive + ", createdon=" + createdon + "]";
	}
}
