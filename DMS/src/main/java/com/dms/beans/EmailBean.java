package com.dms.beans;

public class EmailBean {

	private String toEmailId;
	private String documentId;
	private String senderName;
	
	
	
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	 
	public String getToEmailId() {
		return toEmailId;
	}
	public void setToEmailId(String toEmailId) {
		this.toEmailId = toEmailId;
	}
	public String getDocumentId() {
		return documentId;
	}
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
	
	@Override
	public String toString() {
		return "EmailBean [toEmailId=" + toEmailId + ", documentId=" + documentId + ", senderName=" + senderName + "]";
	}
	
}
