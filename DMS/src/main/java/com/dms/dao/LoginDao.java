package com.dms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.slf4j.LoggerFactory;

import com.dms.beans.User;
import com.dms.util.ConnectionPoolManager;
import com.dms.util.DMSQueries;

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
}
