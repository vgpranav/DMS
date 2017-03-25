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
    private String doctypename;
    private int displayflag;
    
    
    public int getDisplayflag() {
		return displayflag;
	}

	public void setDisplayflag(int displayflag) {
		this.displayflag = displayflag;
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

	public String getDoctypename() {
		return doctypename;
	}

	public void setDoctypename(String doctypename) {
		this.doctypename = doctypename;
	}

	@Override
	public String toString() {
		return "DocSubType [docsubtypeid=" + docsubtypeid + ", doctypeid=" + doctypeid + ", docsubtypename="
				+ docsubtypename + ", docsubtypedesc=" + docsubtypedesc + ", createdby=" + createdby + ", createdon="
				+ createdon + ", active=" + active + ", doctypename=" + doctypename + ", displayflag=" + displayflag
				+ "]";
	}

	 
}
