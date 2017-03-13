package com.dms.beans;

import java.util.Date;

public class Photos
{

    private long photosid;
    private String phototype;
    private long docid;
    private String docpath;
    private String docname;
    private Date createdon;
    private int isactive;
    private String contenttype;

    public Photos()
    {
    }

    public long getPhotosid()
    {
        return photosid;
    }

    public void setPhotosid(long photosid)
    {
        this.photosid = photosid;
    }

    public String getPhototype()
    {
        return phototype;
    }

    public void setPhototype(String phototype)
    {
        this.phototype = phototype;
    }

    public long getDocid()
    {
        return docid;
    }

    public void setDocid(long docid)
    {
        this.docid = docid;
    }

    public String getDocpath()
    {
        return docpath;
    }

    public void setDocpath(String docpath)
    {
        this.docpath = docpath;
    }

    public String getDocname()
    {
        return docname;
    }

    public void setDocname(String docname)
    {
        this.docname = docname;
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

    public String getContenttype()
    {
        return contenttype;
    }

    public void setContenttype(String contenttype)
    {
        this.contenttype = contenttype;
    }

    public String toString()
    {
        return (new StringBuilder("Photos [photosid=")).append(photosid).append(", phototype=").append(phototype).append(", docid=").append(docid).append(", docpath=").append(docpath).append(", docname=").append(docname).append(", createdon=").append(createdon).append(", isactive=").append(isactive).append(", contenttype=").append(contenttype).append("]").toString();
    }
}
