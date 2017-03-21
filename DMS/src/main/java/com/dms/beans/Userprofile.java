package com.dms.beans;

import java.util.Date;

public class Userprofile
{

    private long userprofileid;
    private long userid;
    private String firstName;
    private String lastName;
    private String mobileNo;
    private String flatno;
    private String wing;
    private String tower;
    private String occupancy;
    private String alternateno;
    private String email;
    private String aadharno;
    private String jointowners;
    private Date purchasedate;
    private Date possessiondate;
    private String builtuparea;
    private String carpetarea;
    private String parkingtype;
    private String vehicletype;
    private String parkingallotmentno;
    private String floor;
    private String password;
    private String societyid;
    private String tenantname;
    private String tenantaddress;
    private String tenantcontactnumber;
    private String tenantaltnumber;
    private String tenantemail;
    private String tenantaadharno;
    
 

    public long getUserprofileid()
    {
        return userprofileid;
    }

    public void setUserprofileid(long userprofileid)
    {
        this.userprofileid = userprofileid;
    }

    public long getUserid()
    {
        return userid;
    }

    public void setUserid(long userid)
    {
        this.userid = userid;
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

    public String getMobileNo()
    {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo)
    {
        this.mobileNo = mobileNo;
    }

    public String getFlatno()
    {
        return flatno;
    }

    public void setFlatno(String flatno)
    {
        this.flatno = flatno;
    }

    public String getWing()
    {
        return wing;
    }

    public void setWing(String wing)
    {
        this.wing = wing;
    }

    public String getTower()
    {
        return tower;
    }

    public void setTower(String tower)
    {
        this.tower = tower;
    }

    public String getOccupancy()
    {
        return occupancy;
    }

    public void setOccupancy(String occupancy)
    {
        this.occupancy = occupancy;
    }

    public String getAlternateno()
    {
        return alternateno;
    }

    public void setAlternateno(String alternateno)
    {
        this.alternateno = alternateno;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getAadharno()
    {
        return aadharno;
    }

    public void setAadharno(String aadharno)
    {
        this.aadharno = aadharno;
    }

    public String getJointowners()
    {
        return jointowners;
    }

    public void setJointowners(String jointowners)
    {
        this.jointowners = jointowners;
    }

    public Date getPurchasedate()
    {
        return purchasedate;
    }

    public void setPurchasedate(Date purchasedate)
    {
        this.purchasedate = purchasedate;
    }

    public Date getPossessiondate()
    {
        return possessiondate;
    }

    public void setPossessiondate(Date possessiondate)
    {
        this.possessiondate = possessiondate;
    }

    public String getBuiltuparea()
    {
        return builtuparea;
    }

    public void setBuiltuparea(String builtuparea)
    {
        this.builtuparea = builtuparea;
    }

    public String getCarpetarea()
    {
        return carpetarea;
    }

    public void setCarpetarea(String carpetarea)
    {
        this.carpetarea = carpetarea;
    }

    public String getParkingtype()
    {
        return parkingtype;
    }

    public void setParkingtype(String parkingtype)
    {
        this.parkingtype = parkingtype;
    }

    public String getVehicletype()
    {
        return vehicletype;
    }

    public void setVehicletype(String vehicletype)
    {
        this.vehicletype = vehicletype;
    }

    public String getParkingallotmentno()
    {
        return parkingallotmentno;
    }

    public void setParkingallotmentno(String parkingallotmentno)
    {
        this.parkingallotmentno = parkingallotmentno;
    }

    public String getFloor()
    {
        return floor;
    }

    public void setFloor(String floor)
    {
        this.floor = floor;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getSocietyid()
    {
        return societyid;
    }

    public void setSocietyid(String societyid)
    {
        this.societyid = societyid;
    }

	public String getTenantname() {
		return tenantname;
	}

	public void setTenantname(String tenantname) {
		this.tenantname = tenantname;
	}

	public String getTenantaddress() {
		return tenantaddress;
	}

	public void setTenantaddress(String tenantaddress) {
		this.tenantaddress = tenantaddress;
	}

	public String getTenantcontactnumber() {
		return tenantcontactnumber;
	}

	public void setTenantcontactnumber(String tenantcontactnumber) {
		this.tenantcontactnumber = tenantcontactnumber;
	}

	public String getTenantaltnumber() {
		return tenantaltnumber;
	}

	public void setTenantaltnumber(String tenantaltnumber) {
		this.tenantaltnumber = tenantaltnumber;
	}

	public String getTenantemail() {
		return tenantemail;
	}

	public void setTenantemail(String tenantemail) {
		this.tenantemail = tenantemail;
	}

	public String getTenantaadharno() {
		return tenantaadharno;
	}

	public void setTenantaadharno(String tenantaadharno) {
		this.tenantaadharno = tenantaadharno;
	}

	@Override
	public String toString() {
		return "Userprofile [userprofileid=" + userprofileid + ", userid=" + userid + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", mobileNo=" + mobileNo + ", flatno=" + flatno + ", wing=" + wing
				+ ", tower=" + tower + ", occupancy=" + occupancy + ", alternateno=" + alternateno + ", email=" + email
				+ ", aadharno=" + aadharno + ", jointowners=" + jointowners + ", purchasedate=" + purchasedate
				+ ", possessiondate=" + possessiondate + ", builtuparea=" + builtuparea + ", carpetarea=" + carpetarea
				+ ", parkingtype=" + parkingtype + ", vehicletype=" + vehicletype + ", parkingallotmentno="
				+ parkingallotmentno + ", floor=" + floor + ", password=" + password + ", societyid=" + societyid
				+ ", tenantname=" + tenantname + ", tenantaddress=" + tenantaddress + ", tenantcontactnumber="
				+ tenantcontactnumber + ", tenantaltnumber=" + tenantaltnumber + ", tenantemail=" + tenantemail
				+ ", tenantaadharno=" + tenantaadharno + "]";
	}
    
    
     
}
