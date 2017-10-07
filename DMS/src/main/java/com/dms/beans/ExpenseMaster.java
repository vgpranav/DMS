package com.dms.beans;

import java.util.Date;

public class ExpenseMaster {

	private long expenseid; 
	private String expensename; 
	private int expensetypeid; 
	private int isactive;
	private Date createdon;
	private double expensevalue;
	
	 
	public double getExpensevalue() {
		return expensevalue;
	}
	public void setExpensevalue(double expensevalue) {
		this.expensevalue = expensevalue;
	}
	public long getExpenseid() {
		return expenseid;
	}
	public void setExpenseid(long expenseid) {
		this.expenseid = expenseid;
	}
	public String getExpensename() {
		return expensename;
	}
	public void setExpensename(String expensename) {
		this.expensename = expensename;
	}
	public int getExpensetypeid() {
		return expensetypeid;
	}
	public void setExpensetypeid(int expensetypeid) {
		this.expensetypeid = expensetypeid;
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
		return "ExpenseMaster [expenseid=" + expenseid + ", expensename=" + expensename + ", expensetypeid="
				+ expensetypeid + ", isactive=" + isactive + ", createdon=" + createdon + ", expensevalue="
				+ expensevalue + "]";
	}

}