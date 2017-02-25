package com.dms.util;

public class CommomUtility {

	public static long convertToLong(Object obj){
		if(obj instanceof java.lang.Long)
			return (long) obj;
		if(obj instanceof java.math.BigInteger)
			return ((java.math.BigInteger) obj).longValue();
		return 0L;
	}
}
