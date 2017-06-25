package com.dms.beans;

import java.util.Date;

public class Document
{

    private long documentid;
    private long societyid;
    private long doctypeid;
    private long userid;
    private long docsubtypeid;
    private String description;
    private String createdby;
    private Date createdon;
    private String doctypename;
    private String docsubtypename;
    private String commondoc;
    
    
    public String getCommondoc() {
		return commondoc;
	}

	public void setCommondoc(String commondoc) {
		this.commondoc = commondoc;
	}

	public long getDocumentid()
    {
        return documentid;
    }

    public void setDocumentid(long documentid)
    {
        this.documentid = documentid;
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

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getDoctypename() {
		return doctypename;
	}

	public void setDoctypename(String doctypename) {
		this.doctypename = doctypename;
	}

	public String getDocsubtypename() {
		return docsubtypename;
	}

	public void setDocsubtypename(String docsubtypename) {
		this.docsubtypename = docsubtypename;
	}

	@Override
	public String toString() {
		return "Document [documentid=" + documentid + ", societyid=" + societyid + ", doctypeid=" + doctypeid
				+ ", userid=" + userid + ", docsubtypeid=" + docsubtypeid + ", description=" + description
				+ ", createdby=" + createdby + ", createdon=" + createdon + ", doctypename=" + doctypename
				+ ", docsubtypename=" + docsubtypename + ", commondoc=" + commondoc + "]";
	}

}
