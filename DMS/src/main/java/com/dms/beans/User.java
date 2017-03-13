package com.dms.beans;

import java.util.Date;

public class User
{

    private int userid;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private Date createDate;
    private int active;
    private String mobileNo;

    public User()
    {
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

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Date getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }

    public int getActive()
    {
        return active;
    }

    public void setActive(int active)
    {
        this.active = active;
    }

    public String getMobileNo()
    {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo)
    {
        this.mobileNo = mobileNo;
    }

    public int getUserid()
    {
        return userid;
    }

    public void setUserid(int userid)
    {
        this.userid = userid;
    }

    public String toString()
    {
        return (new StringBuilder("User [userid=")).append(userid).append(", firstName=").append(firstName).append(", lastName=").append(lastName).append(", userName=").append(userName).append(", password=").append(password).append(", createDate=").append(createDate).append(", active=").append(active).append(", mobileNo=").append(mobileNo).append("]").toString();
    }
}
