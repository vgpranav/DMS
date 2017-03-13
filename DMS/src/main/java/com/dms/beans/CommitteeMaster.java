package com.dms.beans;

import java.util.Date;

public class CommitteeMaster
{

    private long positionid;
    private String positionname;
    private Date createdon;

    public CommitteeMaster()
    {
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

    public Date getCreatedon()
    {
        return createdon;
    }

    public void setCreatedon(Date createdon)
    {
        this.createdon = createdon;
    }

    public String toString()
    {
        return (new StringBuilder("CommitteeMaster [positionid=")).append(positionid).append(", positionname=").append(positionname).append(", createdon=").append(createdon).append("]").toString();
    }
}
