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

    public Userprofile()
    {
    }

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

    public String toString()
    {
        return (new StringBuilder("Userprofile [userprofileid=")).append(userprofileid).append(", userid=").append(userid).append(", firstName=").append(firstName).append(", lastName=").append(lastName).append(", mobileNo=").append(mobileNo).append(", flatno=").append(flatno).append(", wing=").append(wing).append(", tower=").append(tower).append(", occupancy=").append(occupancy).append(", alternateno=").append(alternateno).append(", email=").append(email).append(", aadharno=").append(aadharno).append(", jointowners=").append(jointowners).append(", purchasedate=").append(purchasedate).append(", possessiondate=").append(possessiondate).append(", builtuparea=").append(builtuparea).append(", carpetarea=").append(carpetarea).append(", parkingtype=").append(parkingtype).append(", vehicletype=").append(vehicletype).append(", parkingallotmentno=").append(parkingallotmentno).append(", floor=").append(floor).append(", password=").append(password).append(", societyid=").append(societyid).append("]").toString();
    }
}
