package com.dms.beans;

import java.util.Date;

public class BillData {

	private long  billdataid;
	private String  componenetname;
	private String componenetvalue;
	private int isactive;
	private Date createdon;
	
	public long getBilldataid() {
		return billdataid;
	}
	public void setBilldataid(long billdataid) {
		this.billdataid = billdataid;
	}
	public String getComponenetname() {
		return componenetname;
	}
	public void setComponenetname(String componenetname) {
		this.componenetname = componenetname;
	}
	public String getComponenetvalue() {
		return componenetvalue;
	}
	public void setComponenetvalue(String componenetvalue) {
		this.componenetvalue = componenetvalue;
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
		return "BillData [billdataid=" + billdataid + ", componenetname=" + componenetname + ", componenetvalue="
				+ componenetvalue + ", isactive=" + isactive + ", createdon=" + createdon + "]";
	}
	
}
