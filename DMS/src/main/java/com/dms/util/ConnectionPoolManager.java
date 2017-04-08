package com.dms.util;

import java.beans.PropertyVetoException;

import org.slf4j.LoggerFactory;

import com.dms.dao.LoginDao;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionPoolManager {	
	
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(ConnectionPoolManager.class);

	private static ComboPooledDataSource cpds;
	
	 
		private static String SQLDriver = CommonDA.getProperties().getProperty("SQLDriver").trim();
		private static String SQLURL = CommonDA.getProperties().getProperty("SQLURL").trim();
		private static String SQLUsername = CommonDA.getProperties().getProperty("SQLUsername").trim();
		private static String SQLPassword = CommonDA.getProperties().getProperty("SQLPassword").trim();
		private static String C3P0_InitialPoolSize = CommonDA.getProperties().getProperty("C3P0_InitialPoolSize").trim();
		private static String C3P0_setMinPoolSize = CommonDA.getProperties().getProperty("C3P0_setMinPoolSize").trim();
		private static String C3P0_setAcquireIncrement = CommonDA.getProperties().getProperty("C3P0_setAcquireIncrement").trim();
		private static String C3P0_setMaxPoolSize = CommonDA.getProperties().getProperty("C3P0_setMaxPoolSize").trim();
		private static String C3P0_setMaxStatements = CommonDA.getProperties().getProperty("C3P0_setMaxStatements").trim();
	 
	public ConnectionPoolManager(){
	}
	
	public static ComboPooledDataSource createConnectionPool(){
		try{
			cpds = new ComboPooledDataSource();
			cpds.setDriverClass(SQLDriver);
			cpds.setJdbcUrl(SQLURL);
			cpds.setUser(SQLUsername);
			cpds.setPassword(SQLPassword);
			cpds.setInitialPoolSize(Integer.valueOf(C3P0_InitialPoolSize));
			cpds.setMinPoolSize(Integer.valueOf(C3P0_setMinPoolSize));
			cpds.setAcquireIncrement(Integer.valueOf(C3P0_setAcquireIncrement));
			cpds.setMaxPoolSize(Integer.valueOf(C3P0_setMaxPoolSize));
			cpds.setMaxStatements(Integer.valueOf(C3P0_setMaxStatements)); 
			
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
