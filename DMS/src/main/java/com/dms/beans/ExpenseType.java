package com.dms.beans;

import java.util.Date;

public class ExpenseType {
 
	private int expensetypeid;
	private String expensetypename;
	private int isactive;
	private Date createdon;
	
	
	public int getExpensetypeid() {
		return expensetypeid;
	}
	public void setExpensetypeid(int expensetypeid) {
		this.expensetypeid = expensetypeid;
	}
	public String getExpensetypename() {
		return expensetypename;
	}
	public void setExpensetypename(String expensetypename) {
		this.expensetypename = expensetypename;
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
		return "ExpenseType [expensetypeid=" + expensetypeid + ", expensetypename=" + expensetypename + ", isactive="
				+ isactive + ", createdon=" + createdon + "]";
	}
}
