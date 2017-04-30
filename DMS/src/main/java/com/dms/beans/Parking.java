package com.dms.beans;

import java.util.Date;

public class Parking {

	private long userparkingdetailsid;
	private long userid;
	private String parkingtype;
	private String vehicletype;
	private String parkingallotmentno;
	private String vehicleno;
	private Date createdon;
	private String randomHash;
	private String createdby;
	
	
	public long getUserparkingdetailsid() {
		return userparkingdetailsid;
	}
	public void setUserparkingdetailsid(long userparkingdetailsid) {
		this.userparkingdetailsid = userparkingdetailsid;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public String getParkingtype() {
		return parkingtype;
	}
	public void setParkingtype(String parkingtype) {
		this.parkingtype = parkingtype;
	}
	public String getVehicletype() {
		return vehicletype;
	}
	public void setVehicletype(String vehicletype) {
		this.vehicletype = vehicletype;
	}
	public String getParkingallotmentno() {
		return parkingallotmentno;
	}
	public void setParkingallotmentno(String parkingallotmentno) {
		this.parkingallotmentno = parkingallotmentno;
	}
	public String getVehicleno() {
		return vehicleno;
	}
	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}
	public Date getCreatedon() {
		return createdon;
	}
	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}
	public String getRandomHash() {
		return randomHash;
	}
	public void setRandomHash(String randomHash) {
		this.randomHash = randomHash;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	
	@Override
	public String toString() {
		return "Parking [userparkingdetails=" + userparkingdetailsid + ", userid=" + userid + ", parkingtype="
				+ parkingtype + ", vehicletype=" + vehicletype + ", parkingallotmentno=" + parkingallotmentno
				+ ", vehicleno=" + vehicleno + ", createdon=" + createdon + ", randomHash=" + randomHash
				+ ", createdby=" + createdby + "]";
	}
	
	}
