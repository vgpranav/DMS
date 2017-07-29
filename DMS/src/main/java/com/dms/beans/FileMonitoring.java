package com.dms.beans;

public class FileMonitoring {

	private long userid;
	private long docsubtypeid;
	private long doctypeid;
	private long societyid;
	private long societytypeid;
	private long projectid;
	private long builderid;

	private long documentcount;
	private long pagecount;
	private long size;
	
	private String docstring;
	
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public long getDocsubtypeid() {
		return docsubtypeid;
	}
	public void setDocsubtypeid(long docsubtypeid) {
		this.docsubtypeid = docsubtypeid;
	}
	public long getDoctypeid() {
		return doctypeid;
	}
	public void setDoctypeid(long doctypeid) {
		this.doctypeid = doctypeid;
	}
	public long getSocietyid() {
		return societyid;
	}
	public void setSocietyid(long societyid) {
		this.societyid = societyid;
	}
	public long getSocietytypeid() {
		return societytypeid;
	}
	public void setSocietytypeid(long societytypeid) {
		this.societytypeid = societytypeid;
	}
	public long getProjectid() {
		return projectid;
	}
	public void setProjectid(long projectid) {
		this.projectid = projectid;
	}
	public long getBuilderid() {
		return builderid;
	}
	public void setBuilderid(long builderid) {
		this.builderid = builderid;
	}
	public long getDocumentcount() {
		return documentcount;
	}
	public void setDocumentcount(long documentcount) {
		this.documentcount = documentcount;
	}
	public long getPagecount() {
		return pagecount;
	}
	public void setPagecount(long pagecount) {
		this.pagecount = pagecount;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	
	public String getDocstring() {
		return docstring;
	}
	public void setDocstring(String docstring) {
		this.docstring = docstring;
	}
	
	@Override
	public String toString() {
		return "FileMonitoring [userid=" + userid + ", docsubtypeid=" + docsubtypeid + ", doctypeid=" + doctypeid
				+ ", societyid=" + societyid + ", societytypeid=" + societytypeid + ", projectid=" + projectid
				+ ", builderid=" + builderid + ", documentcount=" + documentcount + ", pagecount=" + pagecount
				+ ", size=" + size + ", docstring=" + docstring + "]";
	}

}
