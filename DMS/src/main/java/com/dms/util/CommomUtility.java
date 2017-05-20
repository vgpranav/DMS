package com.dms.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.slf4j.LoggerFactory;

public class CommomUtility {
	
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(CommomUtility.class);
	
	public static long convertToLong(Object obj){
		if(obj instanceof java.lang.Long)
			return (Long) obj;
		if(obj instanceof java.math.BigInteger)
			return ((java.math.BigInteger) obj).longValue();
		return 0L;
	}
	
	
	public static String getConfig(String configkey){
		Connection conn = null;
		ResultSetHandler<String> rsh;
		QueryRunner qr;
		String configvalue="";
		try{
			qr = new QueryRunner();
			conn = ConnectionPoolManager.getInstance().getConnection();
			rsh = new ScalarHandler<String>();
			configvalue = qr.query(conn, DMSQueries.getConfigValue,rsh,configkey);
		}catch(Exception e){
			logger.error("Error getting config key [ "+configkey+" ] "+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				logger.error("Error releasing connection :: "+e.getMessage());
			}
		}
	return configvalue;
}
	
	public static Date formatDate(Date inDate) throws ParseException{
		
		Date outDate=null;
		
		if(inDate!=null){
			SimpleDateFormat dt = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss"); 
			String dtString = dt.format(inDate);
			SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			outDate = dt1.parse(dtString); 
		}
		
		return outDate;
	}
	
}
