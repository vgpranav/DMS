package com.dms.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.LoggerFactory;

import com.dms.beans.Society;
import com.dms.beans.SocietyType;
import com.dms.util.ConnectionPoolManager;
import com.dms.util.DMSQueries;

public class SocietyDao {
	
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(SocietyDao.class);
	QueryRunner qr;
	
	public List<SocietyType> getAllActiveSocietyTypes(List<SocietyType> docTypes) {
			Connection conn = null;
			ResultSetHandler<List<SocietyType>> rsh;
			try{
				qr = new QueryRunner();
				conn = ConnectionPoolManager.getInstance().getConnection();
				rsh = new BeanListHandler<SocietyType>(SocietyType.class);
				docTypes = qr.query(conn, DMSQueries.getAllActiveSocietyTypes,rsh);
			}catch(Exception e){
				logger.error("Error fetching SocType List :: "+e.getMessage());
				e.printStackTrace();
			}finally{
				try {
					DbUtils.close(conn);
				} catch (SQLException e) {
					logger.error("Error releasing connection :: "+e.getMessage());
				}
			}
		return docTypes;
	}


	public List<Society> getSocietyAutosuggest(String searchText) {
		Connection conn = null;
		ResultSetHandler<List<Society>> rsh;
		try{
			qr = new QueryRunner();
			conn = ConnectionPoolManager.getInstance().getConnection();
			rsh = new BeanListHandler<Society>(Society.class);
			String SQL = DMSQueries.getAllSociety+" where societyname like '%"+searchText+"%'";
			System.out.println("SQL "+ SQL);
			return qr.query(conn,SQL,rsh);
		}catch(Exception e){
			logger.error("Error getting soc list :: "+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				logger.error("Error releasing connection :: "+e.getMessage());
			}
		}
	return null;
}
}
