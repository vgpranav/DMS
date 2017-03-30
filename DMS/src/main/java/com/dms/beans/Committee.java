package com.dms.beans;

import java.util.Date;

public class Committee
{

    private long committeememberid;
    private long userid;
    private String userName;
    private long societyid;
    private String societyname;
    private long positionid;
    private String positionname;
    private long isactive;
    private Date appointedon;
    private Date removedon;
    private String contactNo;
    private String flat;
    private String societymanagerid;
    private String tower;
   
    public String getContactNo()
    {
        return contactNo;
    }

    public void setContactNo(String contactNo)
    {
        this.contactNo = contactNo;
    }

    public String getflat()
    {
        return flat;
    }

    public void setflat(String flat)
    {
        this.flat = flat;
    }

    public long getCommitteememberid()
    {
        return committeememberid;
    }

    public void setCommitteememberid(long committeememberid)
    {
        this.committeememberid = committeememberid;
    }

    public long getUserid()
    {
        return userid;
    }

    public void setUserid(long userid)
    {
        this.userid = userid;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public long getSocietyid()
    {
        return societyid;
    }

    public void setSocietyid(long societyid)
    {
        this.societyid = societyid;
    }

    public String getSocietyname()
    {
        return societyname;
    }

    public void setSocietyname(String societyname)
    {
        this.societyname = societyname;
    }

    public long getPositionid()
    {
        return positionid;
    }

    public void setPositionid(long positionid)
    {
        this.positionid = positionid;
    }

    public String getPositionname()
    {
        return positionname;
    }

    public void setPositionname(String positionname)
    {
        this.positionname = positionname;
    }

    public long getIsactive()
    {
        return isactive;
    }

    public void setIsactive(long isactive)
    {
        this.isactive = isactive;
    }

    public Date getAppointedon()
    {
        return appointedon;
    }

    public void setAppointedon(Date appointedon)
    {
        this.appointedon = appointedon;
    }

    public Date getRemovedon()
    {
        return removedon;
    }

    public void setRemovedon(Date removedon)
    {
        this.removedon = removedon;
    }
 
	public String getSocietymanagerid() {
		return societymanagerid;
	}

	public void setSocietymanagerid(String societymanagerid) {
		this.societymanagerid = societymanagerid;
	}

	public String getTower() {
		return tower;
	}

	public void setTower(String tower) {
		this.tower = tower;
	}

	@Override
	public String toString() {
		return "Committee [committeememberid=" + committeememberid + ", userid=" + userid + ", userName=" + userName
				+ ", societyid=" + societyid + ", societyname=" + societyname + ", positionid=" + positionid
				+ ", positionname=" + positionname + ", isactive=" + isactive + ", appointedon=" + appointedon
				+ ", removedon=" + removedon + ", contactNo=" + contactNo + ", flat=" + flat + ", societymanagerid="
				+ societymanagerid + ", tower=" + tower + "]";
	}

}
