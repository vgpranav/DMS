package com.dms.beans;


public class SocietyType
{

    private long societytypeid;
    private String societytypename;
    private String societytypedesc;
    private int isactive;

    public SocietyType()
    {
    }

    public long getSocietytypeid()
    {
        return societytypeid;
    }

    public void setSocietytypeid(long societytypeid)
    {
        this.societytypeid = societytypeid;
    }

    public String getSocietytypename()
    {
        return societytypename;
    }

    public void setSocietytypename(String societytypename)
    {
        this.societytypename = societytypename;
    }

    public String getSocietytypedesc()
    {
        return societytypedesc;
    }

    public void setSocietytypedesc(String societytypedesc)
    {
        this.societytypedesc = societytypedesc;
    }

    public int getIsactive()
    {
        return isactive;
    }

    public void setIsactive(int isactive)
    {
        this.isactive = isactive;
    }

    public String toString()
    {
        return (new StringBuilder("SocietyType [societytypeid=")).append(societytypeid).append(", societytypename=").append(societytypename).append(", societytypedesc=").append(societytypedesc).append(", isactive=").append(isactive).append("]").toString();
    }
}
