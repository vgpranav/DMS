package com.dms.beans;

import java.util.Date;

public class CallReference {

	private long callrefid;
	private String 	refno;
	private String initiatorname;
	private String societytype;
	private String buildingno;
	private String streetname;
	private String landmark;
	private String area;
	private String pincode;
	private String city;
	private String state;
	private String country;
	private String resno;
	private String shopsno;
	private Date initiatedate;
	private String  closingchance;
	private String  remark;
	private String  contactname;
	private String  contactdesignation;
	private String  contactmobileno;
	private String  contactlandlineno;
	private String  contactemail;
	
	private String  meetingdate;
	private String  meetingperson;
	private String  meetingremarks;
	
	
	
	
	public String getMeetingdate() {
		return meetingdate;
	}
	public void setMeetingdate(String meetingdate) {
		this.meetingdate = meetingdate;
	}
	public String getMeetingperson() {
		return meetingperson;
	}
	public void setMeetingperson(String meetingperson) {
		this.meetingperson = meetingperson;
	}
	public String getMeetingremarks() {
		return meetingremarks;
	}
	public void setMeetingremarks(String meetingremarks) {
		this.meetingremarks = meetingremarks;
	}
	public String getContactname() {
		return contactname;
	}
	public void setContactname(String contactname) {
		this.contactname = contactname;
	}
	public String getContactdesignation() {
		return contactdesignation;
	}
	public void setContactdesignation(String contactdesignation) {
		this.contactdesignation = contactdesignation;
	}
	public String getContactmobileno() {
		return contactmobileno;
	}
	public void setContactmobileno(String contactmobileno) {
		this.contactmobileno = contactmobileno;
	}
	public String getContactlandlineno() {
		return contactlandlineno;
	}
	public void setContactlandlineno(String contactlandlineno) {
		this.contactlandlineno = contactlandlineno;
	}
	public String getContactemail() {
		return contactemail;
	}
	public void setContactemail(String contactemail) {
		this.contactemail = contactemail;
	}
	public String getClosingchance() {
		return closingchance;
	}
	public void setClosingchance(String closingchance) {
		this.closingchance = closingchance;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public long getCallrefid() {
		return callrefid;
	}
	public void setCallrefid(long callrefid) {
		this.callrefid = callrefid;
	}
	public String getRefno() {
		return refno;
	}
	public void setRefno(String refno) {
		this.refno = refno;
	}
	public String getInitiatorname() {
		return initiatorname;
	}
	public void setInitiatorname(String initiatorname) {
		this.initiatorname = initiatorname;
	}
	public String getSocietytype() {
		return societytype;
	}
	public void setSocietytype(String societytype) {
		this.societytype = societytype;
	}
	public String getBuildingno() {
		return buildingno;
	}
	public void setBuildingno(String buildingno) {
		this.buildingno = buildingno;
	}
	public String getStreetname() {
		return streetname;
	}
	public void setStreetname(String streetname) {
		this.streetname = streetname;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getResno() {
		return resno;
	}
	public void setResno(String resno) {
		this.resno = resno;
	}
	public String getShopsno() {
		return shopsno;
	}
	public void setShopsno(String shopsno) {
		this.shopsno = shopsno;
	}
	public Date getInitiatedate() {
		return initiatedate;
	}
	public void setInitiatedate(Date initiatedate) {
		this.initiatedate = initiatedate;
	}
	@Override
	public String toString() {
		return "CallReference [callrefid=" + callrefid + ", refno=" + refno + ", initiatorname=" + initiatorname
				+ ", societytype=" + societytype + ", buildingno=" + buildingno + ", streetname=" + streetname
				+ ", landmark=" + landmark + ", area=" + area + ", pincode=" + pincode + ", city=" + city + ", state="
				+ state + ", country=" + country + ", resno=" + resno + ", shopsno=" + shopsno + ", initiatedate="
				+ initiatedate + ", closingchance=" + closingchance + ", remark=" + remark + ", contactname="
				+ contactname + ", contactdesignation=" + contactdesignation + ", contactmobileno=" + contactmobileno
				+ ", contactlandlineno=" + contactlandlineno + ", contactemail=" + contactemail + ", meetingdate="
				+ meetingdate + ", meetingperson=" + meetingperson + ", meetingremarks=" + meetingremarks + "]";
	}
	
}
