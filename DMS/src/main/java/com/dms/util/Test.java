package com.dms.util;

import uk.co.mmscomputing.application.imageviewer.FtpWrapper;

public class Test {

	public static void main(String args[]){
		
		try{ 
			FtpWrapper ftp = new FtpWrapper();
		    String hostDomain = ftp.getServerName();
		    String Id = ftp.getUsername();
		    String Password = ftp.getPassword();
			System.out.println(ftp.connectAndLogin(hostDomain, Id, Password));
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
}
