package com.dms.util;

public class DMSQueries {

	public static String authenticateUser = "select * from user where userName=? and password=?";  // and active=1
	public static String getAllActiveSocietyTypes = "select * from societytypemaster where isactive=1";
}
