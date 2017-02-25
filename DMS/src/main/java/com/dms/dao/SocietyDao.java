package com.dms.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.slf4j.LoggerFactory;

import com.dms.beans.Society;
import com.dms.beans.SocietyType;
import com.dms.util.CommomUtility;
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


	public Society insertOrUpdateSociety(Society society) {
		Connection conn = null;
		ResultSetHandler<Long> rsh;
		long societyId=0L;
		long societyProfileId=0L;
		try{
			qr = new QueryRunner();
			conn = ConnectionPoolManager.getInstance().getConnection();
			rsh = new ScalarHandler<Long>();
			Object obj = qr.insert(conn,DMSQueries.insertNewSociety,rsh,
					society.getSocietytypeid(),
					society.getSocietyname(),
					123
					);
			societyId = CommomUtility.convertToLong(obj);
			
			if(societyId!=0L){
				Object obj1 = qr.insert(conn,DMSQueries.insertNewSocietyProfile,rsh,
						society.getSocietyid(),
						society.getAddressline1(),
						society.getAddressline2(),
						society.getWard(),
						society.getDistrict(),
						society.getState(),
						society.getPincode(),
						society.getCreatedby()
						);
				societyProfileId = CommomUtility.convertToLong(obj1);
				
			}
			society.setSocietyid(societyId);
			society.setSocietyprofileid(societyProfileId);
			
			return society;
			
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
