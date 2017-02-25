package com.dms.beans;

public class SocietyType {

	private long societytypeid;
	private String societytypename;
	private String societytypedesc;
	private int isactive;
	
	public long getSocietytypeid() {
		return societytypeid;
	}
	public void setSocietytypeid(long societytypeid) {
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
