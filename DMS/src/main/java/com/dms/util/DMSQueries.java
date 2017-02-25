package com.dms.util;

public class DMSQueries {

	public static String authenticateUser = "select * from user where userName=? and password=?";  // and active=1
	public static String getAllActiveSocietyTypes = "select * from societytypemaster where isactive=1";
	public static String getAllSociety = "select * from society";
	public static String getAllDocumentTypes = "SELECT * FROM doctype order by doctypename";

	public static String insertNewSociety = "insert into society(societytypeid,societyname,createdby) values (?,?,?)";
	public static String insertNewSocietyProfile = "insert into societyprofile(societyid,addressline1,addressline2,ward,district,state,pincode,createdby) values (?,?,?,?,?,?,?,?)";
	
	
	public static String insertNewDoctype = "insert into doctype(doctypename,doctypedesc,active,createdby) values (?,?,?,?)";
	
	
}
