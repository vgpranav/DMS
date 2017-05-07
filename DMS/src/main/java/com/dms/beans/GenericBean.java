package com.dms.beans;

import java.util.Date;

public class GenericBean {
	
	private long societyid;
	private long doctypeid;
	private long societydocmappingid;
	private long docsubtypeid;
	private long roleid;
	private long socdocviewmappingid;
	private long positionid;
	private long confidentialdocaccessid;
	
	private long genId;
	private String typeVal;
	private String pkName;
	
	private int active;
	private int displayflag;
	private int confFlag;
	private int isactive;
	
	private String docsubtypename;
	private String doccount;
	private String createdby;
	private String doctypename;
	private String societyname;
	private String rolename;
	private String fieldname;
	private String fieldvalue;
	private String positionname;
	private String username;

	private Date createdon;
	
	public String getTypeVal() {
		return typeVal;
	}
	public void setTypeVal(String typeVal) {
		this.typeVal = typeVal;
	}
	
	public long getGenId() {
		return genId;
	}
	public void setGenId(long genId) {
		this.genId = genId;
	}
	public long getConfidentialdocaccessid() {
		return confidentialdocaccessid;
	}
	public void setConfidentialdocaccessid(long confidentialdocaccessid) {
		this.confidentialdocaccessid = confidentialdocaccessid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public long getPositionid() {
		return positionid;
	}
	public void setPositionid(long positionid) {
		this.positionid = positionid;
	}
	public String getPositionname() {
		return positionname;
	}
	public void setPositionname(String positionname) {
		this.positionname = positionname;
	}
	public Date getCreatedon() {
		return createdon;
	}
	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}
	public int getIsactive() {
		return isactive;
	}
	public void setIsactive(int isactive) {
		this.isactive = isactive;
	}
	public int getDisplayflag() {
		return displayflag;
	}
	public void setDisplayflag(int displayflag) {
		this.displayflag = displayflag;
	}
	public int getConfFlag() {
		return confFlag;
	}
	public void setConfFlag(int confFlag) {
		this.confFlag = confFlag;
	}
	public long getDocsubtypeid() {
		return docsubtypeid;
	}
	public void setDocsubtypeid(long docsubtypeid) {
		this.docsubtypeid = docsubtypeid;
	}
	public String getDocsubtypename() {
		return docsubtypename;
	}
	public void setDocsubtypename(String docsubtypename) {
		this.docsubtypename = docsubtypename;
	}
	public String getFieldname() {
		return fieldname;
	}
	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}
	public String getFieldvalue() {
		return fieldvalue;
	}
	public void setFieldvalue(String fieldvalue) {
		this.fieldvalue = fieldvalue;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public long getRoleid() {
		return roleid;
	}
	public void setRoleid(long roleid) {
		this.roleid = roleid;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getSocietyname() {
		return societyname;
	}
	public void setSocietyname(String societyname) {
		this.societyname = societyname;
	}
	public String getDoctypename() {
		return doctypename;
	}
	public void setDoctypename(String doctypename) {
		this.doctypename = doctypename;
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
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public long getSocietydocmappingid() {
		return societydocmappingid;
	}
	public void setSocietydocmappingid(long societydocmappingid) {
		this.societydocmappingid = societydocmappingid;
	}
	public String getDoccount() {
		return doccount;
	}
	public void setDoccount(String doccount) {
		this.doccount = doccount;
	}
	 
	public long getSocdocviewmappingid() {
		return socdocviewmappingid;
	}
	public void setSocdocviewmappingid(long socdocviewmappingid) {
		this.socdocviewmappingid = socdocviewmappingid;
	}
	
	
	@Override
	public String toString() {
		return "GenericBean [societyid=" + societyid + ", doctypeid=" + doctypeid + ", doctypename=" + doctypename
				+ ", docsubtypeid=" + docsubtypeid + ", docsubtypename=" + docsubtypename + ", doccount=" + doccount
				+ ", createdby=" + createdby + ", societydocmappingid=" + societydocmappingid + ", societyname="
				+ societyname + ", roleid=" + roleid + ", rolename=" + rolename + ", active=" + active + ", fieldname="
				+ fieldname + ", fieldvalue=" + fieldvalue + ", displayflag=" + displayflag + ", confFlag=" + confFlag
				+ ", socdocviewmappingid=" + socdocviewmappingid + ", positionname=" + positionname + ", createdon="
				+ createdon + ", isactive=" + isactive + ", positionid=" + positionid + ", username=" + username
				+ ", confidentialdocaccessid=" + confidentialdocaccessid + "]";
	}
	public String getPkName() {
		return pkName;
	}
	public void setPkName(String pkName) {
		this.pkName = pkName;
	}
	
}
