package com.dms.beans;

import java.util.Date;

public class UserSCNominee {

	private long userscnomineeid;
	private long userid;
	private String nominee;
	private String percent; 
	private Date createdon;
	private String randomHash;
	private String createdby;
	
	private String nomineerelation;
	private Date nomineedob;
	private String nomineeaddress;
	
	public String getNomineerelation() {
		return nomineerelation;
	}
	public void setNomineerelation(String nomineerelation) {
		this.nomineerelation = nomineerelation;
	}
	public Date getNomineedob() {
		return nomineedob;
	}
	public void setNomineedob(Date nomineedob) {
		this.nomineedob = nomineedob;
	}
	public String getNomineeaddress() {
		return nomineeaddress;
	}
	public void setNomineeaddress(String nomineeaddress) {
		this.nomineeaddress = nomineeaddress;
	}
	public long getUserscnomineeid() {
		return userscnomineeid;
	}
	public void setUserscnomineeid(long userscnomineeid) {
		this.userscnomineeid = userscnomineeid;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public String getNominee() {
		return nominee;
	}
	public void setNominee(String nominee) {
		this.nominee = nominee;
	}
	public String getPercent() {
		return percent;
	}
	public void setPercent(String percent) {
		this.percent = percent;
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
		return "UserSCNominee [userscnomineeid=" + userscnomineeid + ", userid=" + userid + ", nominee=" + nominee
				+ ", percent=" + percent + ", createdon=" + createdon + ", randomHash=" + randomHash + ", createdby="
				+ createdby + ", nomineerelation=" + nomineerelation + ", nomineedob=" + nomineedob
				+ ", nomineeaddress=" + nomineeaddress + "]";
	}
	
	}
