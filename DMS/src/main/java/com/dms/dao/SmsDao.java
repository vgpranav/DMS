package com.dms.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.slf4j.LoggerFactory;

import com.dms.beans.Smslogger;
import com.dms.beans.User;
import com.dms.util.ConnectionPoolManager;
import com.dms.util.DMSQueries;

public class SmsDao {

	private final static org.slf4j.Logger dblogger = LoggerFactory.getLogger("dblogger");
	QueryRunner qr;
	
	
	public Smslogger logSmsToDB(Smslogger smslogger){
		Connection conn = null;
		ResultSetHandler<User> rsh;
		try{
			qr = new QueryRunner();
			conn = ConnectionPoolManager.getInstance().getConnection();
			qr.insert(conn,DMSQueries.insertSmsLog,new ScalarHandler<Object>(),
					smslogger.getSmstype(), 
					smslogger.getSmsmsg(), 
					smslogger.getSmscount(), 
					smslogger.getSmsusers(),
					smslogger.getSmsdate(), 
					smslogger.getSmsresponse(), 
					smslogger.getSmshttpcode()
					);
			
		}catch(Exception e){
			dblogger.error("Error in logSmsToDB :: ",e);
			e.printStackTrace();
		}finally{
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				dblogger.error("Error releasing connection :: ",e);
			}
		}
		return smslogger;
	}
}
