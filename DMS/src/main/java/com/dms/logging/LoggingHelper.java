package com.dms.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingHelper {
	
	private static final Logger reqreslogger = LoggerFactory.getLogger("reqreslogger");
	
	public static void logAction(String userId,String action){
		
	}
	
	public static void logReqResponse(String action,Object object){
		reqreslogger.info("\n\n"+action+"\n\n"+object.toString());
	}
	
	/*public static void logReqResponse(User user){
		
	}*/

}
