package com.dms.beans;

import java.util.Date;

public class User
{

    private long userid;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private Date createDate;
    private int active;
    private String mobileNo;
    private long userroleid;
    private String userrolename;
    private String createdby;
    private String otp;
    private String otpType;
    private int deleteflag;
    
    private Date logintime;
    private Date logouttime;
    private String sessionkey;
    private String ipaddress;
    
    private String userType;
    
    
    
    public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Date getLogintime() {
		return logintime;
	}

	public void setLogintime(Date logintime) {
		this.logintime = logintime;
	}

	public Date getLogouttime() {
		return logouttime;
	}

	public void setLogouttime(Date logouttime) {
		this.logouttime = logouttime;
	}

	public String getSessionkey() {
		return sessionkey;
	}

	public void setSessionkey(String sessionkey) {
		this.sessionkey = sessionkey;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public int getDeleteflag() {
		return deleteflag;
	}

	public void setDeleteflag(int deleteflag) {
		this.deleteflag = deleteflag;
	}

	public String getOtpType() {
		return otpType;
	}

	public void setOtpType(String otpType) {
		this.otpType = otpType;
	}

	public String getUserrolename() {
		return userrolename;
	}

	public void setUserrolename(String userrolename) {
		this.userrolename = userrolename;
	}

	public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Date getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }

    public int getActive()
    {
        return active;
    }

    public void setActive(int active)
    {
        this.active = active;
    }

    public String getMobileNo()
    {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo)
    {
        this.mobileNo = mobileNo;
    }

    public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public void setUserid(int userid)
    {
        this.userid = userid;
    }
 
	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public long getUserroleid() {
		return userroleid;
	}

	public void setUserroleid(long userroleid) {
		this.userroleid = userroleid;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", firstName=" + firstName + ", lastName=" + lastName + ", userName="
				+ userName + ", password=" + password + ", createDate=" + createDate + ", active=" + active
				+ ", mobileNo=" + mobileNo + ", userroleid=" + userroleid + ", userrolename=" + userrolename
				+ ", createdby=" + createdby + ", otp=" + otp + ", otpType=" + otpType + ", deleteflag=" + deleteflag
				+ ", logintime=" + logintime + ", logouttime=" + logouttime + ", sessionkey=" + sessionkey
				+ ", ipaddress=" + ipaddress + ", userType=" + userType + "]";
	}


}
