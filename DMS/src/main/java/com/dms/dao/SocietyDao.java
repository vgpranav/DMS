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

import com.dms.beans.Files;
import com.dms.beans.Society;
import com.dms.beans.SocietyType;
import com.dms.beans.Userprofile;
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
		ResultSetHandler<Object> rsh;
		long societyId=0L;
		long societyProfileId=0L;
		try{
			qr = new QueryRunner();
			conn = ConnectionPoolManager.getInstance().getConnection();
			rsh = new ScalarHandler<Object>();
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


	public List<Society> getSocietyListforUser(List<Society> societyList) {
		Connection conn = null;
		ResultSetHandler<List<Society>> rsh;
		try{
			qr = new QueryRunner();
			conn = ConnectionPoolManager.getInstance().getConnection();
			rsh = new BeanListHandler<Society>(Society.class);
			societyList = qr.query(conn, DMSQueries.getAllActiveSocietyForUser,rsh);
		}catch(Exception e){
			logger.error("Error fetching Society List :: "+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				logger.error("Error releasing connection :: "+e.getMessage());
			}
		}
	return societyList;
}


	public Userprofile saveMemberDetails(Userprofile userprofile) {
		Connection conn = null;
		ResultSetHandler<Object> rsh;
		long userId=0L;
		long userProfileId=0L;
		try{
			qr = new QueryRunner();
			conn = ConnectionPoolManager.getInstance().getConnection();
			rsh = new ScalarHandler<Object>();
			conn.setAutoCommit(false);
			
			Object obj = qr.insert(conn,DMSQueries.insertNewUser,rsh,
					userprofile.getFirstName(),
					userprofile.getLastName(),
					userprofile.getPassword(),
					123,
					userprofile.getMobileNo()
					);
			
			userId = CommomUtility.convertToLong(obj);
			
			if(userId!=0L){
				Object obj1 = qr.insert(conn,DMSQueries.insertNewUserProfile,rsh,
						userId,
						userprofile.getFlatno(),
						userprofile.getWing(),
						userprofile.getFloor(),
						userprofile.getTower(),
						userprofile.getOccupancy(),
						userprofile.getAlternateno(),
						userprofile.getEmail(),
						userprofile.getAadharno(),
						userprofile.getJointowners(),
						userprofile.getPurchasedate(),
						userprofile.getPossessiondate(),
						userprofile.getBuiltuparea(),
						userprofile.getCarpetarea(),
						userprofile.getParkingtype(),
						userprofile.getVehicletype(),
						userprofile.getParkingallotmentno(),
						userprofile.getSocietyid()
						);
				userProfileId = CommomUtility.convertToLong(obj1);
				
			}
			conn.commit();
			
			userprofile.setUserid(userId);
			userprofile.setUserprofileid(userProfileId);
			
			return userprofile;
			
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


	public List<Userprofile> getMembersForSociety(String societyid, List<Userprofile> profiles) {
		Connection conn = null;
		ResultSetHandler<List<Userprofile>> rsh;
		try{
			qr = new QueryRunner();
			conn = ConnectionPoolManager.getInstance().getConnection();
			rsh = new BeanListHandler<Userprofile>(Userprofile.class);
			profiles = qr.query(conn, DMSQueries.getMembersForSociety,rsh,
					societyid
					);
		}catch(Exception e){
			logger.error("Error getMembersForSociety :: "+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				logger.error("Error releasing connection :: "+e.getMessage());
			}
		}
	return profiles;
}
	
	
	
}
