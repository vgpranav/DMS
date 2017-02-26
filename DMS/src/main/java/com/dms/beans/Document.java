package com.dms.beans;

import java.util.Date;

public class Document {

	private long documentid;
	private long societyid;
	private long doctypeid;
	private long docsubtypeid;
	private String createdby;
	private Date createdon;
	
	public long getDocumentid() {
		return documentid;
	}
	public void setDocumentid(long documentid) {
		this.documentid = documentid;
	}
	public long getSocietyid() {
		return societyid;
	}
	public void setSocietyid(long societyid) {
		this.societyid = societyid;
	}
	public long getDoctypeid() {
		return doctypeid;
	}
	public void setDoctypeid(long doctypeid) {
		this.doctypeid = doctypeid;
	}
	public long getDocsubtypeid() {
		return docsubtypeid;
	}
	public void setDocsubtypeid(long docsubtypeid) {
		this.docsubtypeid = docsubtypeid;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public Date getCreatedon() {
		return createdon;
	}
	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}
	
	@Override
	public String toString() {
		return "Document [documentid=" + documentid + ", societyid=" + societyid + ", doctypeid=" + doctypeid
				+ ", docsubtypeid=" + docsubtypeid + ", createdby=" + createdby + ", createdon=" + createdon + "]";
	}

}
