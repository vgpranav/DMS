package com.dms.dao;

import com.dms.beans.Committee;
import com.dms.beans.CommitteeMaster;
import com.dms.beans.Photos;
import com.dms.beans.Society;
import com.dms.beans.SocietyType;
import com.dms.beans.User;
import com.dms.beans.Userprofile;
import com.dms.util.CommomUtility;
import com.dms.util.ConnectionPoolManager;
import com.dms.util.DMSQueries;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;
import uk.co.mmscomputing.application.imageviewer.FtpWrapper;

public class SocietyDao
{
  private static final Logger logger = LoggerFactory.getLogger(SocietyDao.class);
  
  public SocietyDao() {}
  
  public List<SocietyType> getAllActiveSocietyTypes(List<SocietyType> docTypes) { Connection conn = null;
    try
    {
      qr = new QueryRunner();
      conn = ConnectionPoolManager.getInstance().getConnection();
      ResultSetHandler<List<SocietyType>> rsh = new BeanListHandler<SocietyType>(SocietyType.class);
      docTypes = qr.query(conn, DMSQueries.getAllActiveSocietyTypes, rsh);
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
    return docTypes;
  }
  
  QueryRunner qr;
  public List<Society> getSocietyAutosuggest(String searchText) {
    Connection conn = null;
    try
    {
      qr = new QueryRunner();
      conn = ConnectionPoolManager.getInstance().getConnection();
      ResultSetHandler<List<Society>> rsh = new BeanListHandler<Society>(Society.class);
      String SQL = DMSQueries.getAllSociety + " where societyname like '%" + searchText + "%'";
      return qr.query(conn, SQL, rsh);
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
    return null;
  }
  
  public Society insertOrUpdateSociety(Society society)
  {
    Connection conn = null;
    
    long societyId = 0L;
    long societyProfileId = 0L;
    try {
      qr = new QueryRunner();
      conn = ConnectionPoolManager.getInstance().getConnection();
      ResultSetHandler<Object> rsh = new ScalarHandler<Object>();
      conn.setAutoCommit(false);
      Object obj = qr.insert(conn, DMSQueries.insertNewSociety, rsh, 
					        Long.valueOf(society.getSocietytypeid()), 
					        society.getSocietyname(), 
					        Integer.valueOf(123)
				        );
      
      societyId = CommomUtility.convertToLong(obj);
      
      if (societyId != 0L) {
        Object obj1 = qr.insert(conn, DMSQueries.insertNewSocietyProfile, rsh, 
	          Long.valueOf(societyId), 
	          society.getAddressline1(), 
	          society.getAddressline2(), 
	          society.getWard(), 
	          society.getDistrict(), 
	          society.getState(), 
	          society.getPincode(), 
	          society.getCreatedby(), 
	          society.getRegistrationno(), 
	          society.getEstdate()
          );
        
        societyProfileId = CommomUtility.convertToLong(obj1);
      }
      

      conn.commit();
      
      society.setSocietyid(societyId);
      society.setSocietyprofileid(societyProfileId);
      
      return society;
    }
    catch (Exception e) {
      logger.error("Error getting soc list :: " + e.getMessage());
      e.printStackTrace();
    } finally {
      try {
        DbUtils.close(conn);
      } catch (SQLException e) {
        logger.error("Error releasing connection :: " + e.getMessage());
      }
    }
    return null;
  }
  
  public List<Society> getSocietyListforUser(List<Society> societyList)
  {
    Connection conn = null;
    try
    {
      qr = new QueryRunner();
      conn = ConnectionPoolManager.getInstance().getConnection();
      ResultSetHandler<List<Society>> rsh = new BeanListHandler<Society>(Society.class);
      societyList = qr.query(conn, DMSQueries.getAllActiveSocietyForUser, rsh);
    } catch (Exception e) {
      logger.error("Error fetching Society List :: " + e.getMessage());
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
    return societyList;
  }
  
  public Userprofile saveMemberDetails(Userprofile userprofile)
  {
    Connection conn = null;
    
    long userId = 0L;
    long userProfileId = 0L;
    try {
      qr = new QueryRunner();
      conn = ConnectionPoolManager.getInstance().getConnection();
      ResultSetHandler<Object> rsh = new ScalarHandler<Object>();
      conn.setAutoCommit(false);
      
      Object obj = qr.insert(conn, DMSQueries.insertNewUser, rsh,  
	        userprofile.getFirstName(), 
	        userprofile.getLastName(), 
	        userprofile.getPassword(), 
	        Integer.valueOf(123), 
	        userprofile.getMobileNo()
        );
      

      userId = CommomUtility.convertToLong(obj);
      
      if (userId != 0L) {
        Object obj1 = qr.insert(conn, DMSQueries.insertNewUserProfile, rsh,
	          Long.valueOf(userId), 
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
    }
    catch (Exception e) {
      logger.error("Error getting soc list :: " + e.getMessage());
      e.printStackTrace();
    } finally {
      try {
        DbUtils.close(conn);
      } catch (SQLException e) {
        logger.error("Error releasing connection :: " + e.getMessage());
      }
    }
    return null;
  }
  
  public List<Userprofile> getMembersForSociety(String societyid, List<Userprofile> profiles)
  {
    Connection conn = null;
    try
    {
      qr = new QueryRunner();
      conn = ConnectionPoolManager.getInstance().getConnection();
      ResultSetHandler<List<Userprofile>> rsh = new BeanListHandler<Userprofile>(Userprofile.class);
      profiles =  qr.query(conn, DMSQueries.getMembersForSociety, rsh,societyid);
    }
    catch (Exception e) {
      logger.error("Error getMembersForSociety :: " + e.getMessage());
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
    return profiles;
  }
  
  public List<CommitteeMaster> getCommitteeMaster(List<CommitteeMaster> committeeMasterList)
  {
    Connection conn = null;
    try
    {
      qr = new QueryRunner();
      conn = ConnectionPoolManager.getInstance().getConnection();
      ResultSetHandler<List<CommitteeMaster>> rsh = new BeanListHandler<CommitteeMaster>(CommitteeMaster.class);
      committeeMasterList = qr.query(conn, DMSQueries.getAllCommitteePositions, rsh);
    } catch (Exception e) {
      logger.error("Error getCommitteeMaster :: " + e.getMessage());
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
    return committeeMasterList;
  }
  
  public List<User> getUserAutosuggest(String searchText, String societyid)
  {
    Connection conn = null;
    try
    {
      qr = new QueryRunner();
      conn = ConnectionPoolManager.getInstance().getConnection();
      ResultSetHandler<List<User>> rsh = new BeanListHandler<User>(User.class);
      String SQL = " select u.userid,u.firstName,u.lastName from user u,userprofile up where u.userid=up.userid and up.societyid=" + societyid + " and " + 
        " (lower(u.firstName) like '%" + searchText.toLowerCase() + "%' or lower(u.lastName) like '%" + searchText.toLowerCase() + "%') ";
      return qr.query(conn,SQL,rsh);
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
    return null;
  }
  
  public Committee addCommitteeMember(Committee committee)
  {
    Connection conn = null;
    
    long committeeMemberId = 0L;
    try {
      qr = new QueryRunner();
      conn = ConnectionPoolManager.getInstance().getConnection();
      ResultSetHandler<Object> rsh = new ScalarHandler<Object>();
      Object obj = qr.insert(conn, DMSQueries.insertNewCommitteeMember, rsh, 
	        Long.valueOf(committee.getUserid()), 
	        Long.valueOf(committee.getSocietyid()), 
	        Long.valueOf(committee.getPositionid()), 
	        committee.getAppointedon(), 
	        committee.getRemovedon()
        );
      
      committeeMemberId = CommomUtility.convertToLong(obj);
      committee.setCommitteememberid(committeeMemberId);
      return committee;
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
    return null;
  }
  
  public Map<String, List<Committee>> getCommitteMembersForSociety(long societyid, Map<String, List<Committee>> committees)
  {
    Connection conn = null;
    

    List<Committee> activeMembers = null;
    List<Committee> inactiveMembers = null;
    try {
      qr = new QueryRunner();
      conn = ConnectionPoolManager.getInstance().getConnection();
      ResultSetHandler<List<Committee>> rsh = new BeanListHandler<Committee>(Committee.class);
      
      activeMembers =  qr.query(conn, DMSQueries.getAllActiveCommitteMembersBySocietyId, rsh, Long.valueOf(societyid) );
      inactiveMembers = qr.query(conn, DMSQueries.getAllInActiveCommitteMembersBySocietyId, rsh, Long.valueOf(societyid) );
      
      committees.put("active", activeMembers);
      committees.put("inactive", inactiveMembers);
    }
    catch (Exception e) {
      logger.error("Error getCommitteMembersForSociety :: " + e.getMessage());
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
    return committees;
  }
  
  public int removeCommitteeMember(Committee committee)
  {
    Connection conn = null;
    try {
      qr = new QueryRunner();
      conn = ConnectionPoolManager.getInstance().getConnection();
      int rowsUpdated = qr.update(conn, DMSQueries.removeCommitteeMember, new Object[] {
        new Date(), 
        Long.valueOf(committee.getCommitteememberid()) });
      
      return rowsUpdated;
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
  
  public List<Society> getSocietyListForManager(int userid, List<Society> societyList)
  {
    Connection conn = null;
    try
    {
      qr = new QueryRunner();
      conn = ConnectionPoolManager.getInstance().getConnection();
      ResultSetHandler<List<Society>> rsh = new BeanListHandler<Society>(Society.class);
      societyList = qr.query(conn, DMSQueries.getSocietyListForManager, rsh,userid);
      
      //System.out.println("userid :: "+userid+"/nsocietyList :: "+societyList);
    } catch (Exception e) {
      logger.error("Error getCommitteMembersForSociety :: " + e.getMessage());
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
    return societyList;
  }
  
  public List<HashMap<String, Object>> getSocietyPhotos(long societyid, List<HashMap<String, Object>> photos)
  {
    Connection conn = null;
    
    List<Photos> photoBeans = null;
    FtpWrapper ftp = new FtpWrapper();
    String hostDomain = ftp.getServerName();
    String Id = ftp.getUsername();
    String Password = ftp.getPassword();
    try { 
    	
      qr = new QueryRunner();
      conn = ConnectionPoolManager.getInstance().getConnection();
      ResultSetHandler<List<Photos>> rsh = new BeanListHandler<Photos>(Photos.class);
      
      //System.out.println("societyid :: " + societyid);
      
      photoBeans =  qr.query(conn, DMSQueries.getSocietyPhotos, rsh, new Object[] { Long.valueOf(societyid) });
      
      if (photoBeans.size() > 0) {
        if (ftp.connectAndLogin(hostDomain, Id, Password))
        {
          ftp.setPassiveMode(true);
          ftp.binary();
          ftp.setBufferSize(1024000);
          ftp.changeWorkingDirectory("DMS/SocietyImages/");
          
          for (Photos photo : photoBeans)
          {
            InputStream stream = ftp.retrieveFileStream(photo.getDocname());
            byte[] bytes = IOUtils.toByteArray(stream);
            
            //System.out.println("Base64Utils.encode(bytes)" +  new String(Base64Utils.encode(bytes)));
            HashMap<String, Object> hmap = new HashMap<String, Object>();
            hmap.put("filename", photo.getDocname());
            hmap.put("contenttype", photo.getContenttype());
            hmap.put("file",  new String(Base64Utils.encode(bytes)));
            photos.add(hmap);
            ftp.completePendingCommand();
          }
           
        }
      } else System.out.println("No Files");
    }
    catch (Exception e)
    {
      logger.error("Error getCommitteMembersForSociety :: " + e.getMessage());
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
      try {
        if (ftp.isConnected()) {
          ftp.logout();
          ftp.disconnect();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return photos;
  }
}