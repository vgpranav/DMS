package com.dms.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {
	
	private static final Logger logger = LoggerFactory.getLogger(Test.class);

	private static final Logger actionlogger = LoggerFactory.getLogger("actionlogger");
	private static final Logger reqreslogger = LoggerFactory.getLogger("reqreslogger");

	
	public static void main(String args[]){
		
		try{
			logger.error("Testing error");
			
			actionlogger.error("actionlogger");
			reqreslogger.error("reqreslogger");
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
}
