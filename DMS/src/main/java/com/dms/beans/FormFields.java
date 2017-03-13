package com.dms.beans;

import java.util.Date;

public class FormFields
{

    private long fieldid;
    private String fieldname;
    private String fieldtype;
    private String datatype;
    private int sequence;
    private int active;
    private String createdby;
    private Date createdon;
    private long docsubtypeid;

    public FormFields()
    {
    }

    public long getFieldid()
    {
        return fieldid;
    }

    public void setFieldid(long fieldid)
    {
        this.fieldid = fieldid;
    }

    public String getFieldname()
    {
        return fieldname;
    }

    public void setFieldname(String fieldname)
    {
        this.fieldname = fieldname;
    }

    public String getFieldtype()
    {
        return fieldtype;
    }

    public void setFieldtype(String fieldtype)
    {
        this.fieldtype = fieldtype;
    }

    public String getDatatype()
    {
        return datatype;
    }

    public void setDatatype(String datatype)
    {
        this.datatype = datatype;
    }

    public int getSequence()
    {
        return sequence;
    }

    public void setSequence(int sequence)
    {
        this.sequence = sequence;
    }

    public int getActive()
    {
        return active;
    }

    public void setActive(int active)
    {
        this.active = active;
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

    public long getDocsubtypeid()
    {
        return docsubtypeid;
    }

    public void setDocsubtypeid(long docsubtypeid)
    {
        this.docsubtypeid = docsubtypeid;
    }

    public String toString()
    {
        return (new StringBuilder("FormFields [fieldid=")).append(fieldid).append(", fieldname=").append(fieldname).append(", fieldtype=").append(fieldtype).append(", datatype=").append(datatype).append(", sequence=").append(sequence).append(", active=").append(active).append(", createdby=").append(createdby).append(", createdon=").append(createdon).append(", docsubtypeid=").append(docsubtypeid).append("]").toString();
    }
}
