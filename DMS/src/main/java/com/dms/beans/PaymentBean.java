package com.dms.beans;

public class PaymentBean {

	private long billid;
	private String payMode;
	private String bankName;
	private String accountNo;
	private String chequeNo;
	private double amount;
	
	
	public long getBillid() {
		return billid;
	}
	public void setBillid(long billid) {
		this.billid = billid;
	}
	public String getPayMode() {
		return payMode;
	}
	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getChequeNo() {
		return chequeNo;
	}
	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		return "PaymentBean [billid=" + billid + ", payMode=" + payMode + ", bankName=" + bankName + ", accountNo="
				+ accountNo + ", chequeNo=" + chequeNo + ", amount=" + amount + "]";
	}
	 
}
