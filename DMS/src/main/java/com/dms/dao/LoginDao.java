package com.dms.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
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
import com.dms.util.ConnectionPoolManager;
import com.dms.util.DMSQueries;
import com.dms.util.SmsApi;

public class LoginDao {

	private final static org.slf4j.Logger dblogger = LoggerFactory.getLogger("dblogger");
	
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
			dblogger.error("Error authenticating user :: ",e);
			e.printStackTrace();
		}finally{
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				dblogger.error("Error releasing connection :: ",e);
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
	    	  
	    	  qr.update(conn, DMSQueries.deactAllOldOTP,  
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
	      dblogger.error("Error getting soc list :: " , e);
	      e.printStackTrace();
	    } finally {
	      try {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        dblogger.error("Error releasing connection :: " , e);
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
	      dblogger.error("Error getting soc list :: " , e);
	      e.printStackTrace();
	    } finally {
	      try {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        dblogger.error("Error releasing connection :: " , e);
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
	      dblogger.error("Error getting soc list :: " , e);
	      e.printStackTrace();
	    } finally {
	      try {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        dblogger.error("Error releasing connection :: " , e);
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
		      dblogger.error("Error fetching SocType List :: " , e);
		      e.printStackTrace();
		    }
		    finally
		    {
		      try
		      {
		        DbUtils.close(conn);
		      } catch (SQLException e) {
		        dblogger.error("Error releasing connection :: " , e);
		      }
		    }
		    return roleList;
		  }

	public boolean verifyConfidentialAccess(User user) {
		Connection conn = null;
		User userNew = null;
	    try {
	    	qr = new QueryRunner();
	    	ResultSetHandler<User> rsh = new BeanHandler<User>(User.class);
	      	conn = ConnectionPoolManager.getInstance().getConnection();
	      	userNew = qr.query(conn, DMSQueries.verifyConfidentialDocAccess,rsh,user.getMobileNo());
	    	
	      	if(userNew!= null && userNew.getActive()==1)
	      		return true;
	      	
	    } catch (Exception e) {
	      dblogger.error("Error getting soc list :: " , e);
	      e.printStackTrace();
	    } finally {
	      try {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        dblogger.error("Error releasing connection :: " , e);
	      }
	    }
	    return false;
	}
	
	
	public boolean logUserLogin(User user) {
		Connection conn = null;
		User userNew = null;
	    try {
	    	conn = ConnectionPoolManager.getInstance().getConnection();
	    	qr = new QueryRunner();
	    	ResultSetHandler<User> rsh = new BeanHandler<User>(User.class);
	      	
	      	userNew = qr.insert(conn, DMSQueries.logUserLogin,rsh,
	      			user.getUserid(),
	      			user.getLogintime(),
	      			user.getSessionkey(),
	      			user.getIpaddress()
	      			);
	    	
	      	updateSessionForUser(user.getSessionkey(),user.getUserid(),1);
	      	
	      	if(userNew!= null && userNew.getActive()==1)
	      		return true;
	      	
	    } catch (Exception e) {
	      dblogger.error("Error getting soc list :: " , e);
	      e.printStackTrace();
	      try {
			conn.rollback();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    } finally {
	      try {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        dblogger.error("Error releasing connection :: " , e);
	      }
	    }
	    return false;
	}

	public boolean updateSessionForUser(String sessionKey,long userid,int sessionactive){
		Connection conn = null;
	    try {
	    	conn = ConnectionPoolManager.getInstance().getConnection();
	    	qr = new QueryRunner();
	      	
	    	int updated = qr.update(conn,DMSQueries.updateSessionForUser,
	    			sessionKey,
	    			new Date(),
	    			sessionactive,
	    			userid 	
	    			);
	      	
	      	if(updated>0)
	      		return true;
	      	
	    } catch (Exception e) {
	      dblogger.error("Error getting soc list :: " , e);
	      e.printStackTrace();
	    } finally {
	      try {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        dblogger.error("Error releasing connection :: " , e);
	      }
	    }
	    return false;
	}
	
	public void logUserLogout(String sessionKey,String userId) {
		Connection conn = null;
	    try {
	    	qr = new QueryRunner();
	    	ResultSetHandler<User> rsh = new BeanHandler<User>(User.class);
	      	conn = ConnectionPoolManager.getInstance().getConnection();
	      	
	      	qr.update(conn, DMSQueries.logUserLogout,
	      			new Date(),
	      			sessionKey
	      			);
	    	
	      	
	      	updateSessionForUser(sessionKey,Long.valueOf(userId),0);
	      	
	    } catch (Exception e) {
	      dblogger.error("Error getting soc list :: " , e);
	      e.printStackTrace();
	    } finally {
	      try {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        dblogger.error("Error releasing connection :: " , e);
	      }
	    }
	}
	
	
	public boolean logActionsToDB(String userid,String action,String payload, String actiontype) {
		Connection conn = null;
	    try {
	    	
	    	if(payload.length()>4999){
	    		payload = payload.substring(0, 4999);
	    	}
	    	if(userid.length()<1)
	    		userid="0";
	    	
	    	qr = new QueryRunner();
	    	ResultSetHandler<User> rsh = new BeanHandler<User>(User.class);
	      	conn = ConnectionPoolManager.getInstance().getConnection();
	      	qr.insert(conn, DMSQueries.logActionsToDB,rsh,
	      			userid,
	      			action,
	      			payload,
	      			actiontype
	      			);
	    	
	      	return true;
	      	
	    } catch (Exception e) {
	      dblogger.error("Error Logging action :: " , e);
	      e.printStackTrace();
	    } finally {
	      try {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        dblogger.error("Error releasing connection :: " , e);
	      }
	    }
	    return false;
	}
}
