package com.dms.beans;

import java.util.Date;

public class Society
{

    private long societyid;
    private long societytypeid;
    private String societyname;
    private long societyprofileid;
    private String addressline1;
    private String addressline2;
    private String ward;
    private String district;
    private String state;
    private String pincode;
    private String createdby;
    private Date createdon;
    private int isactive;
    private String registrationno;
    private Date estdate;
    private long projectid;
    private String projectname;
    private String societytype;
    
    private String country;
    private String city;
    private String landmark;
    
    private String noofshop;
    private String noofflat;
    
    public String getNoofshop() {
		return noofshop;
	}

	public void setNoofshop(String noofshop) {
		this.noofshop = noofshop;
	}

	public String getNoofflat() {
		return noofflat;
	}

	public void setNoofflat(String noofflat) {
		this.noofflat = noofflat;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public String getRegistrationno()
    {
        return registrationno;
    }

    public void setRegistrationno(String registrationno)
    {
        this.registrationno = registrationno;
    }

    public Date getEstdate()
    {
        return estdate;
    }

    public void setEstdate(Date estdate)
    {
        this.estdate = estdate;
    }

    public long getSocietyid()
    {
        return societyid;
    }

    public void setSocietyid(long societyid)
    {
        this.societyid = societyid;
    }

    public long getSocietytypeid()
    {
        return societytypeid;
    }

    public void setSocietytypeid(long societytypeid)
    {
        this.societytypeid = societytypeid;
    }

    public String getSocietyname()
    {
        return societyname;
    }

    public void setSocietyname(String societyname)
    {
        this.societyname = societyname;
    }

    public long getSocietyprofileid()
    {
        return societyprofileid;
    }

    public void setSocietyprofileid(long societyprofileid)
    {
        this.societyprofileid = societyprofileid;
    }

    public String getAddressline1()
    {
        return addressline1;
    }

    public void setAddressline1(String addressline1)
    {
        this.addressline1 = addressline1;
    }

    public String getAddressline2()
    {
        return addressline2;
    }

    public void setAddressline2(String addressline2)
    {
        this.addressline2 = addressline2;
    }

    public String getWard()
    {
        return ward;
    }

    public void setWard(String ward)
    {
        this.ward = ward;
    }

    public String getDistrict()
    {
        return district;
    }

    public void setDistrict(String district)
    {
        this.district = district;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getPincode()
    {
        return pincode;
    }

    public void setPincode(String pincode)
    {
        this.pincode = pincode;
    }

    public String getCreatedby()
    {
        return createdby;
    }

    public void setCreatedby(String createdby)
    {
        this.createdby = createdby;
    }

    public Date getCreatedon()
    {
        return createdon;
    }

    public void setCreatedon(Date createdon)
    {
        this.createdon = createdon;
    }

    public int getIsactive()
    {
        return isactive;
    }

    public void setIsactive(int isactive)
    {
        this.isactive = isactive;
    }

	public long getProjectid() {
		return projectid;
	}

	public void setProjectid(long projectid) {
		this.projectid = projectid;
	}

	public String getSocietytype() {
		return societytype;
	}

	public void setSocietytype(String societytype) {
		this.societytype = societytype;
	}

	@Override
	public String toString() {
		return "Society [societyid=" + societyid + ", societytypeid=" + societytypeid + ", societyname=" + societyname
				+ ", societyprofileid=" + societyprofileid + ", addressline1=" + addressline1 + ", addressline2="
				+ addressline2 + ", ward=" + ward + ", district=" + district + ", state=" + state + ", pincode="
				+ pincode + ", createdby=" + createdby + ", createdon=" + createdon + ", isactive=" + isactive
				+ ", registrationno=" + registrationno + ", estdate=" + estdate + ", projectid=" + projectid
				+ ", projectname=" + projectname + ", societytype=" + societytype + ", country=" + country + ", city="
				+ city + ", landmark=" + landmark + ", noofshop=" + noofshop + ", noofflat=" + noofflat + "]";
	}

}
