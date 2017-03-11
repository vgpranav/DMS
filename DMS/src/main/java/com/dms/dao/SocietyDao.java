package com.dms.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.slf4j.LoggerFactory;

import com.dms.beans.Committee;
import com.dms.beans.CommitteeMaster;
import com.dms.beans.Society;
import com.dms.beans.SocietyType;
import com.dms.beans.User;
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


	public List<CommitteeMaster> getCommitteeMaster(List<CommitteeMaster> committeeMasterList) {
		Connection conn = null;
		ResultSetHandler<List<CommitteeMaster>> rsh;
		try{
			qr = new QueryRunner();
			conn = ConnectionPoolManager.getInstance().getConnection();
			rsh = new BeanListHandler<CommitteeMaster>(CommitteeMaster.class);
			committeeMasterList = qr.query(conn, DMSQueries.getAllCommitteePositions,rsh);
		}catch(Exception e){
			logger.error("Error getCommitteeMaster :: "+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				logger.error("Error releasing connection :: "+e.getMessage());
			}
		}
	return committeeMasterList;
}


	public List<User> getUserAutosuggest(String searchText, String societyid) {
		Connection conn = null;
		ResultSetHandler<List<User>> rsh;
		try{
			qr = new QueryRunner();
			conn = ConnectionPoolManager.getInstance().getConnection();
			rsh = new BeanListHandler<User>(User.class);
			String SQL = " select u.userid,u.firstName,u.lastName from user u,userprofile up where u.userid=up.userid and up.societyid="+societyid+" and "
						+ " (lower(u.firstName) like '%"+searchText.toLowerCase()+"%' or lower(u.lastName) like '%"+searchText.toLowerCase()+"%') ";
			
			//System.out.println("SQL :; "+SQL);
			
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


	public Committee addCommitteeMember(Committee committee) {
		Connection conn = null;
		ResultSetHandler<Object> rsh;
		long committeeMemberId=0L;
		try{
			qr = new QueryRunner();
			conn = ConnectionPoolManager.getInstance().getConnection();
			rsh = new ScalarHandler<Object>();
			Object obj = qr.insert(conn,DMSQueries.insertNewCommitteeMember,rsh,
					committee.getUserid(),
					committee.getSocietyid(),
					committee.getPositionid(),
					committee.getAppointedon(),
					committee.getRemovedon()
					);
			committeeMemberId = CommomUtility.convertToLong(obj);
			committee.setCommitteememberid(committeeMemberId);
			return committee;
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


	public Map<String, List<Committee>> getCommitteMembersForSociety(long societyid, Map<String, List<Committee>> committees) {
		Connection conn = null;
		ResultSetHandler<List<Committee>> rsh;
		
		List<Committee> activeMembers=null;
		List<Committee> inactiveMembers=null;
		try{
			qr = new QueryRunner();
			conn = ConnectionPoolManager.getInstance().getConnection();
			rsh = new BeanListHandler<Committee>(Committee.class);
			
			activeMembers = qr.query(conn, DMSQueries.getAllActiveCommitteMembersBySocietyId,rsh,societyid);
			inactiveMembers = qr.query(conn, DMSQueries.getAllInActiveCommitteMembersBySocietyId,rsh,societyid);
			
			committees.put("active",activeMembers);
			committees.put("inactive",inactiveMembers);
			
		}catch(Exception e){
			logger.error("Error getCommitteMembersForSociety :: "+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				logger.error("Error releasing connection :: "+e.getMessage());
			}
		}
	return committees;
}


	public int removeCommitteeMember(Committee committee) {
		Connection conn = null;
		try{
			/*
			java.util.Date dt = new java.util.Date();
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currentTime = sdf.format(dt);*/
			
			qr = new QueryRunner();
			conn = ConnectionPoolManager.getInstance().getConnection();
			int rowsUpdated = qr.update(conn,DMSQueries.removeCommitteeMember,
					new Date(),
					committee.getCommitteememberid()
					);
			return rowsUpdated;
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
	return 0;
}
	
	
	
}
