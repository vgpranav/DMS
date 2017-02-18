package com.dms.util;

import java.beans.PropertyVetoException;

import org.slf4j.LoggerFactory;

import com.dms.dao.LoginDao;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionPoolManager {	
	
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(ConnectionPoolManager.class);

	private static ComboPooledDataSource cpds;
	
	/*
		private static String SQLDriver = CommonDA.getProperties().getProperty("SQLDriver");
		private static String SQLURL = CommonDA.getProperties().getProperty("SQLURL");
		private static String SQLUsername = CommonDA.getProperties().getProperty("SQLUsername");
		private static String SQLPassword = CommonDA.getProperties().getProperty("SQLPassword");
		private static String C3P0_InitialPoolSize = CommonDA.getProperties().getProperty("C3P0_InitialPoolSize");
		private static String C3P0_setMinPoolSize = CommonDA.getProperties().getProperty("C3P0_setMinPoolSize");
		private static String C3P0_setAcquireIncrement = CommonDA.getProperties().getProperty("C3P0_setAcquireIncrement");
		private static String C3P0_setMaxPoolSize = CommonDA.getProperties().getProperty("C3P0_setMaxPoolSize");
		private static String C3P0_setMaxStatements = CommonDA.getProperties().getProperty("C3P0_setMaxStatements");
	 */	
	   
	public ConnectionPoolManager(){
	}
	
	public static ComboPooledDataSource createConnectionPool(){
		try{
			cpds = new ComboPooledDataSource();
			cpds.setDriverClass("com.mysql.jdbc.Driver");
			cpds.setJdbcUrl("jdbc:mysql://localhost:3306/dms");
			cpds.setUser("root");
			cpds.setPassword("12345");
			cpds.setInitialPoolSize(5);
			cpds.setMinPoolSize(5);
			cpds.setAcquireIncrement(5);
			cpds.setMaxPoolSize(20);
			cpds.setMaxStatements(100); 
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return cpds;
	}
	
	public static ComboPooledDataSource getInstance() throws PropertyVetoException {
		if(cpds==null){
			logger.debug("New Connection Pool Created");
			return createConnectionPool();
		}
		else{
			logger.debug("Using Connection from existing Pool [ "+ cpds.getIdentityToken() +" ]");
			return cpds;
		}
	}
}
