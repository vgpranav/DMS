package com.dms.util;

import java.time.LocalDate;

public class Test {

	public static void main(String args[]){
		
		try{LocalDate today = LocalDate.now();
		LocalDate yesterday = today.minusDays(1);
		
		System.out.println(yesterday + " 00:00:00");
		
		
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
}
