package com.dms.beans;


public class DocSubType
{

    private long docsubtypeid;
    private long doctypeid;
    private String docsubtypename;
    private String docsubtypedesc;
    private String createdby;
    private String createdon;
    private int active;

    public DocSubType()
    {
    }

    public long getDocsubtypeid()
    {
        return docsubtypeid;
    }

    public void setDocsubtypeid(long docsubtypeid)
    {
        this.docsubtypeid = docsubtypeid;
    }

    public long getDoctypeid()
    {
        return doctypeid;
    }

    public void setDoctypeid(long doctypeid)
    {
        this.doctypeid = doctypeid;
    }

    public String getDocsubtypename()
    {
        return docsubtypename;
    }

    public void setDocsubtypename(String docsubtypename)
    {
        this.docsubtypename = docsubtypename;
    }

    public String getDocsubtypedesc()
    {
        return docsubtypedesc;
    }

    public void setDocsubtypedesc(String docsubtypedesc)
    {
        this.docsubtypedesc = docsubtypedesc;
    }

    public String getCreatedby()
    {
        return createdby;
    }

    public void setCreatedby(String createdby)
    {
        this.createdby = createdby;
    }

    public String getCreatedon()
    {
        return createdon;
    }

    public void setCreatedon(String createdon)
    {
        this.createdon = createdon;
    }

    public int getActive()
    {
        return active;
    }

    public void setActive(int active)
    {
        this.active = active;
    }

    public String toString()
    {
        return (new StringBuilder("DocSubType [docsubtypeid=")).append(docsubtypeid).append(", doctypeid=").append(doctypeid).append(", docsubtypename=").append(docsubtypename).append(", docsubtypedesc=").append(docsubtypedesc).append(", createdby=").append(createdby).append(", createdon=").append(createdon).append(", active=").append(active).append("]").toString();
    }
}
