package com.dms.beans;

public class SocietyType {

	private int societytypeid;
	private String societytypename;
	private String societytypedesc;
	private int isactive;
	public int getSocietytypeid() {
		return societytypeid;
	}
	public void setSocietytypeid(int societytypeid) {
		this.societytypeid = societytypeid;
	}
	public String getSocietytypename() {
		return societytypename;
	}
	public void setSocietytypename(String societytypename) {
		this.societytypename = societytypename;
	}
	public String getSocietytypedesc() {
		return societytypedesc;
	}
	public void setSocietytypedesc(String societytypedesc) {
		this.societytypedesc = societytypedesc;
	}
	public int getIsactive() {
		return isactive;
	}
	public void setIsactive(int isactive) {
		this.isactive = isactive;
	}
	@Override
	public String toString() {
		return "SocietyType [societytypeid=" + societytypeid + ", societytypename=" + societytypename
				+ ", societytypedesc=" + societytypedesc + ", isactive=" + isactive + "]";
	}
	
	
	
	
	 
}
