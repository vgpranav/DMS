package com.dms.util;

import org.slf4j.LoggerFactory;

import com.dms.controller.ViewController;

public class Test {
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(ViewController.class);

	public static void main(String args[]){
		System.out.println("Test Msg");
		logger.debug("[welcome] counter : {}", "123");
		try{
			throw new RuntimeException("MYCustEXP");
		}catch(Exception e){
			logger.error("[welcome] counter : {}", e.getMessage() );
		}
		//
	}
}
