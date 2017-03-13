package com.dms.beans;

import java.util.Date;

public class Files
{

    private long filesid;
    private long societyid;
    private long doctypeid;
    private long docsubtypeid;
    private long documentid;
    private String filename;
    private String filepath;
    private String mimetype;
    private Date createdon;
    private String createdby;

    public Files()
    {
    }

    public long getFilesid()
    {
        return filesid;
    }

    public void setFilesid(long filesid)
    {
        this.filesid = filesid;
    }

    public long getSocietyid()
    {
        return societyid;
    }

    public void setSocietyid(long societyid)
    {
        this.societyid = societyid;
    }

    public long getDoctypeid()
    {
        return doctypeid;
    }

    public void setDoctypeid(long doctypeid)
    {
        this.doctypeid = doctypeid;
    }

    public long getDocsubtypeid()
    {
        return docsubtypeid;
    }

    public void setDocsubtypeid(long docsubtypeid)
    {
        this.docsubtypeid = docsubtypeid;
    }

    public long getDocumentid()
    {
        return documentid;
    }

    public void setDocumentid(long documentid)
    {
        this.documentid = documentid;
    }

    public String getFilename()
    {
        return filename;
    }

    public void setFilename(String filename)
    {
        this.filename = filename;
    }

    public String getFilepath()
    {
        return filepath;
    }

    public void setFilepath(String filepath)
    {
        this.filepath = filepath;
    }

    public String getMimetype()
    {
        return mimetype;
    }

    public void setMimetype(String mimetype)
    {
        this.mimetype = mimetype;
    }

    public Date getCreatedon()
    {
        return createdon;
    }

    public void setCreatedon(Date createdon)
    {
        this.createdon = createdon;
    }

    public String getCreatedby()
    {
        return createdby;
    }

    public void setCreatedby(String createdby)
    {
        this.createdby = createdby;
    }

    public String toString()
    {
        return (new StringBuilder("Files [filesid=")).append(filesid).append(", societyid=").append(societyid).append(", doctypeid=").append(doctypeid).append(", docsubtypeid=").append(docsubtypeid).append(", documentid=").append(documentid).append(", filename=").append(filename).append(", filepath=").append(filepath).append(", mimetype=").append(mimetype).append(", createdon=").append(createdon).append(", createdby=").append(createdby).append("]").toString();
    }
}
