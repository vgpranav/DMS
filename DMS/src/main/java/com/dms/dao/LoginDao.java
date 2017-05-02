package com.dms.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.LoggerFactory;

import com.dms.beans.Otphandler;
import com.dms.beans.RoleTransaction;
import com.dms.beans.User;
import com.dms.beans.UserSCNominee;
import com.dms.util.ConnectionPoolManager;
import com.dms.util.DMSQueries;
import com.dms.util.SmsApi;

public class LoginDao {

	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(LoginDao.class);
	QueryRunner qr;
	
	public User authenticateUser(User user,User authenticatedUser){
		Connection conn = null;
		ResultSetHandler<User> rsh;
		try{
			qr = new QueryRunner();
			conn = ConnectionPoolManager.getInstance().getConnection();
			rsh = new BeanHandler<User>(User.class);
			authenticatedUser = qr.query(conn,DMSQueries.authenticateUser, rsh,user.getMobileNo(),user.getPassword());
		}catch(Exception e){
			logger.error("Error authenticating user :: "+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				logger.error("Error releasing connection :: "+e.getMessage());
			}
		}
		return authenticatedUser;
	}

	public int generateAndSendOTP(User user) {
		Connection conn = null;
		User authenticatedUser=null;
		ResultSetHandler<User> rsh;
	    try {
	      qr = new QueryRunner();
	      conn = ConnectionPoolManager.getInstance().getConnection();
	      rsh = new BeanHandler<User>(User.class);
	      authenticatedUser = qr.query(conn,DMSQueries.authenticateUserByMobNo,rsh,user.getMobileNo());
	      
	      if(authenticatedUser!=null && authenticatedUser.getActive()==1){
	    	  int rowsUpdated=0;
	    	  String OTP = RandomStringUtils.randomNumeric(6);
	    	  
	    	  int deactAllOldOTP = qr.update(conn, DMSQueries.deactAllOldOTP,  
	    			  user.getMobileNo());
	    	  
	    	  rowsUpdated = qr.update(conn, DMSQueries.insertOTPHandler,  
		    			  user.getMobileNo(), OTP,  1 );
		    	  
		      String OTPMsg = "OTP for "+user.getMobileNo()+" is "+OTP+". Valid for 15 Mins only - ourDigitalSociety";
		      SmsApi.sendSms(user.getMobileNo(),OTPMsg,"OTP");
	    	  
		      return rowsUpdated; 
	      } else {
	    	  return 0;
	      }
	     
	    } catch (Exception e) {
	      logger.error("Error getting soc list :: " + e.getMessage());
	      e.printStackTrace();
	    } finally {
	      try {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        logger.error("Error releasing connection :: " + e.getMessage());
	      }
	    }
	    return 0;
	}

	public int validateOTP(User user) {
		Connection conn = null;
		User authenticatedUser=null;
		ResultSetHandler<User> rsh;
	    try {
	      qr = new QueryRunner();
	      conn = ConnectionPoolManager.getInstance().getConnection();
	      rsh = new BeanHandler<User>(User.class);
	      authenticatedUser = qr.query(conn,DMSQueries.authenticateUserByMobNo,rsh,user.getMobileNo());
	      
	      if(authenticatedUser!=null && authenticatedUser.getActive()==1){
	    	  int rowsUpdated=0;
	    	  
	    	  ResultSetHandler<Otphandler> rsh1 = new BeanHandler<Otphandler>(Otphandler.class);
	    	  Otphandler otp = qr.query(conn,DMSQueries.validateOTPMessage,rsh1,user.getMobileNo(),user.getOtp());
	    	  if(otp!=null){
	    		  qr.update(conn, DMSQueries.deactAllOldOTP,user.getMobileNo());
	    		  rowsUpdated=1;
	    	  }
		      return rowsUpdated; 
	      } else {
	    	  return 0;
	      }
	     
	    } catch (Exception e) {
	      logger.error("Error getting soc list :: " + e.getMessage());
	      e.printStackTrace();
	    } finally {
	      try {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        logger.error("Error releasing connection :: " + e.getMessage());
	      }
	    }
	    return 0;
	}

	public int setNewpasswordForUser(User user) {
		Connection conn = null;
		int rowsUpdated=0;
	    try {
	    	qr = new QueryRunner();
	      	conn = ConnectionPoolManager.getInstance().getConnection();
	    	rowsUpdated = qr.update(conn, DMSQueries.setNewPasswordForUser,user.getPassword(),user.getMobileNo());
	    } catch (Exception e) {
	      logger.error("Error getting soc list :: " + e.getMessage());
	      e.printStackTrace();
	    } finally {
	      try {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        logger.error("Error releasing connection :: " + e.getMessage());
	      }
	    }
	    return rowsUpdated;
	}

	public List<RoleTransaction> getAllRoles(List<RoleTransaction> roleList) { 
		  Connection conn = null;
		    try
		    {
		      qr = new QueryRunner();
		      conn = ConnectionPoolManager.getInstance().getConnection();
		      ResultSetHandler<List<RoleTransaction>> rsh = new BeanListHandler<RoleTransaction>(RoleTransaction.class);
		      
		      roleList = qr.query(conn, DMSQueries.getAllTxnRoles, rsh);
		      
		    } catch (Exception e) {
		      logger.error("Error fetching SocType List :: " + e.getMessage());
		      e.printStackTrace();
		    }
		    finally
		    {
		      try
		      {
		        DbUtils.close(conn);
		      } catch (SQLException e) {
		        logger.error("Error releasing connection :: " + e.getMessage());
		      }
		    }
		    return roleList;
		  }
}
