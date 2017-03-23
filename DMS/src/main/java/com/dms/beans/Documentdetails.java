package com.dms.beans;

import java.util.Date;

public class Documentdetails {

	public long documentdetailsid;
	public long documentid;
	public String datakey;
	public String datavalue;
	public String createdby;
	public Date createdon;
	public long getDocumentdetailsid() {
		return documentdetailsid;
	}
	public void setDocumentdetailsid(long documentdetailsid) {
		this.documentdetailsid = documentdetailsid;
	}
	public long getDocumentid() {
		return documentid;
	}
	public void setDocumentid(long documentid) {
		this.documentid = documentid;
	}
	public String getDatakey() {
		return datakey;
	}
	public void setDatakey(String datakey) {
		this.datakey = datakey;
	}
	public String getDatavalue() {
		return datavalue;
	}
	public void setDatavalue(String datavalue) {
		this.datavalue = datavalue;
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
		return "Documentdetails [documentdetailsid=" + documentdetailsid + ", documentid=" + documentid + ", datakey="
				+ datakey + ", datavalue=" + datavalue + ", createdby=" + createdby + ", createdon=" + createdon + "]";
	}
	
	
	
	
}
