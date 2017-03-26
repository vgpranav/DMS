package com.dms.dao;

import com.dms.beans.Builder;
import com.dms.beans.Committee;
import com.dms.beans.CommitteeMaster;
import com.dms.beans.DocSubType;
import com.dms.beans.Doctype;
import com.dms.beans.Document;
import com.dms.beans.Files;
import com.dms.beans.FormFields;
import com.dms.beans.GenericBean;
import com.dms.beans.Photos;
import com.dms.beans.Project;
import com.dms.beans.Society;
import com.dms.beans.SocietyType;
import com.dms.beans.User;
import com.dms.beans.Userprofile;
import com.dms.beans.Vendor;
import com.dms.util.CommomUtility;
import com.dms.util.ConnectionPoolManager;
import com.dms.util.DMSQueries;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
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
  
  public List<SocietyType> getAllActiveSocietyTypes(List<SocietyType> docTypes) { 
	  Connection conn = null;
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
  
  public Society insertOrUpdateSociety(Society society, long userId)
  {
    Connection conn = null;
    
    long societyId = 0L;
    long societyProfileId = 0L;
    try {
      qr = new QueryRunner();
      conn = ConnectionPoolManager.getInstance().getConnection();
      ResultSetHandler<Object> rsh = new ScalarHandler<Object>();
      
      conn.setAutoCommit(false);
      
      //Insert new
      if(society.getSocietyid()==0){
    	  
    	  Object obj = qr.insert(conn, DMSQueries.insertNewSociety, rsh, 
			        Long.valueOf(society.getSocietytypeid()), 
			        society.getSocietyname(), 
			        userId,
			        society.getProjectid()
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
		    userId, 
		    society.getRegistrationno(), 
		    society.getEstdate()
		);
		
		societyProfileId = CommomUtility.convertToLong(obj1);
		}
    	  
      }
      	else {
      		qr.update(conn,DMSQueries.updateSociety,
      				society.getSocietytypeid(),
      				society.getSocietyname(),
      				society.getProjectid(),
      				society.getSocietyid()
      				 );
    	  
      		qr.update(conn,DMSQueries.updateSocProfile,
      				society.getAddressline1(),
      				society.getAddressline2(),
      				society.getWard(),
      				society.getDistrict(),
      				society.getState(),
      				society.getPincode(),
      				society.getRegistrationno(),
      				society.getEstdate(),
      				society.getSocietyid());
    	  
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
  
  public Userprofile saveMemberDetails(Userprofile userprofile, User user)
  {
    Connection conn = null;
    
    long userId = 0L;
    long userProfileId = 0L;
    try {
      qr = new QueryRunner();
      conn = ConnectionPoolManager.getInstance().getConnection();
      ResultSetHandler<Object> rsh = new ScalarHandler<Object>();
      conn.setAutoCommit(false);
      
      if(userprofile.getUserid() == 0){
    	  
    	  Object obj = qr.insert(conn, DMSQueries.insertNewUser, rsh,  
    		        userprofile.getFirstName(), 
    		        userprofile.getLastName(), 
    		        userprofile.getPassword(), 
    		        user.getUserid(), 
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
    		          userprofile.getSocietyid(),
    		          userprofile.getBloodgroup(),
    		          userprofile.getSharecertno(),
    		          userprofile.getNominee1(),
    		          userprofile.getPercent1(),
    		          userprofile.getNominee2(),
    		          userprofile.getPercent2(),
    		          userprofile.getNominee3(),
    		          userprofile.getPercent3()
    	          );
    	        
    	        userProfileId = CommomUtility.convertToLong(obj1);
    	      }
    	      
    	      if(userprofile.getOccupancy().equalsIgnoreCase("leased")){
    	    	  qr.insert(conn, DMSQueries.insertNewTenant, rsh,
    	    			  userId,
    	    			  userprofile.getTenantname(),
    	    			  userprofile.getTenantaddress(),
    	    			  userprofile.getTenantcontactnumber(),
    	    			  userprofile.getTenantaltnumber(),
    	    			  userprofile.getTenantemail(),
    	    			  userprofile.getTenantaadharno()
    	    			  );
    	      }
    	      
    	      userprofile.setUserid(userId);
    	      userprofile.setUserprofileid(userProfileId);
    	      
      } else {
    	  qr.update(conn, DMSQueries.updateNewUser, 
  		        userprofile.getFirstName(), 
  		        userprofile.getLastName(), 
  		        userprofile.getPassword(), 
  		        1,
  		        userprofile.getMobileNo(),
  		        userprofile.getUserid()
  	        );
    	  
    	  Object obj1 = qr.update(conn, DMSQueries.updateNewUserProfile, 
		          userprofile.getFlatno(), 
		          userprofile.getWing(), 
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
		          userprofile.getFloor(), 
		          userprofile.getSocietyid(),
		          userprofile.getBloodgroup(),
		          userprofile.getSharecertno(),
		          userprofile.getNominee1(),
		          userprofile.getPercent1(),
		          userprofile.getNominee2(),
		          userprofile.getPercent2(),
		          userprofile.getNominee3(),
		          userprofile.getPercent3(),
		          userprofile.getUserid()
	          );
    	  
    	  if(userprofile.getOccupancy().equalsIgnoreCase("leased")){
	    	  qr.update(conn, DMSQueries.updateNewTenant, 
	    			  userprofile.getTenantname(),
	    			  userprofile.getTenantaddress(),
	    			  userprofile.getTenantcontactnumber(),
	    			  userprofile.getTenantaltnumber(),
	    			  userprofile.getTenantemail(),
	    			  userprofile.getTenantaadharno(),
	    			  userprofile.getUserid()
	    			  );
	      }
      }
      
      conn.commit();
      
      
      
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
        " (lower(u.firstName) like '%" + searchText.toLowerCase() + "%' or lower(u.lastName) like '%" + searchText.toLowerCase() + "%') "
        		+ " union all "
        		+ " select userid,firstName,concat(lastName,' (Admin)') as lastName from user where userroleid <> 0 and (lower(firstName) like "
        		+ " '%" + searchText.toLowerCase() + "%' or lower(lastName) like '%" + searchText.toLowerCase() + "%') ";
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
  
  public List<Society> getSocietyListForManager(long userid, List<Society> societyList)
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
  
  public List<HashMap<String, Object>> getSocietyPhotos(long docId, String photoType ,String Path,List<HashMap<String, Object>> photos)
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
      
      photoBeans =  qr.query(conn,DMSQueries.getPhotoInfo,rsh,docId,photoType);
      
      if (photoBeans.size() > 0) {
        if (ftp.connectAndLogin(hostDomain, Id, Password))
        {
          ftp.setPassiveMode(true);
          ftp.binary();
          ftp.setBufferSize(1024000);
          ftp.changeWorkingDirectory("DMS/"+Path+"/");
          
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

public Vendor saveVendorDetails(Vendor vendor) {
    Connection conn = null;
    
    long vendorId = 0L;
    try {
      qr = new QueryRunner();
      conn = ConnectionPoolManager.getInstance().getConnection();
      
      conn.setAutoCommit(false);
      
      if(vendor.getVendorid()==0){
    	  
    	  ResultSetHandler<Object> rsh = new ScalarHandler<Object>();
          Object obj = qr.insert(conn, DMSQueries.insertNewVendor, rsh, 
        		  vendor.getCompanyname(),
        		  vendor.getJobnature(),
        		  vendor.getContactperson(),
        		  vendor.getAddress(),
        		  vendor.getContactno(),
        		  vendor.getAlternateno(),
        		  vendor.getEmail(),
        		  vendor.getRemark(),
        		  vendor.getIsactive(),
        		  vendor.getCreatedby()
            );
          
          vendorId = CommomUtility.convertToLong(obj);
          
          Object obj1 = qr.insert(conn, DMSQueries.insertVendorSocMapping, rsh, 
        		  vendorId,
        		  vendor.getSocietyid()
            );
          
          vendor.setVendorid(vendorId);
          
      } else {
    					  
          Object obj = qr.update(conn, DMSQueries.updateNewVendor,  
        		  vendor.getCompanyname(),
        		  vendor.getJobnature(),
        		  vendor.getContactperson(),
        		  vendor.getAddress(),
        		  vendor.getContactno(),
        		  vendor.getAlternateno(),
        		  vendor.getEmail(),
        		  vendor.getRemark(),
        		  vendor.getIsactive(),
        		  vendor.getVendorid()
            );
          
          Object obj1 = qr.update(conn, DMSQueries.updateVendorSocMapping , 
        		  vendor.getSocietyid(),
        		  vendor.getVendorid()
            );
    	  
    	  
      }
      
      
      conn.commit();
      
      
      
      return vendor;
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

public  List<Vendor> getVendorsBySocId(long societyid, List<Vendor> vendors) {
    Connection conn = null;
    
    try {
      qr = new QueryRunner();
      conn = ConnectionPoolManager.getInstance().getConnection();
      ResultSetHandler<List<Vendor>> rsh = new BeanListHandler<Vendor>(Vendor.class);
      
      vendors =  qr.query(conn, DMSQueries.getAllVendorsBySocId,rsh,Long.valueOf(societyid));
      
     return vendors;
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
    return null;
  }

public Society getSocietyDetailsById(Society society) {
    Connection conn = null;
    
    try {
      qr = new QueryRunner();
      conn = ConnectionPoolManager.getInstance().getConnection();
      ResultSetHandler<Society> rsh = new BeanHandler<Society>(Society.class);
      society = qr.query(conn, DMSQueries.getSocietyDetailsById, rsh, 
    		  society.getSocietyid()
        );
      
      return society;
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

public Doctype getDocumentTypeById(Doctype doctype) {
    Connection conn = null;
    
    try {
      qr = new QueryRunner();
      conn = ConnectionPoolManager.getInstance().getConnection();
      ResultSetHandler<Doctype> rsh = new BeanHandler<Doctype>(Doctype.class);
      doctype = qr.query(conn, DMSQueries.getDocumentTypeById, rsh, 
    		  doctype.getDoctypeid()
        );
      
      return doctype;
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

public DocSubType getDocumentSubTypeById(DocSubType docSubType) {
    Connection conn = null;
    
    try {
      qr = new QueryRunner();
      conn = ConnectionPoolManager.getInstance().getConnection();
      ResultSetHandler<DocSubType> rsh = new BeanHandler<DocSubType>(DocSubType.class);
      docSubType = qr.query(conn, DMSQueries.getDocumentSubTypeById, rsh, 
    		  docSubType.getDocsubtypeid()
        );
      return docSubType;
    } catch (Exception e) {
      logger.error("Error getting getDocumentSubTypeById :: " + e.getMessage());
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

public FormFields getFormFieldsById(FormFields formFields) {
    Connection conn = null;
    
    try {
      qr = new QueryRunner();
      conn = ConnectionPoolManager.getInstance().getConnection();
      ResultSetHandler<FormFields> rsh = new BeanHandler<FormFields>(FormFields.class);
      formFields = qr.query(conn, DMSQueries.getFormFieldDetailsById, rsh, 
    		  formFields.getFieldid()
        );
      return formFields;
    } catch (Exception e) {
      logger.error("Error in getFormFieldsById :: " + e.getMessage());
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

public Userprofile getUserDataById(Userprofile userprofile) {
    Connection conn = null;
    
    try {
      qr = new QueryRunner();
      conn = ConnectionPoolManager.getInstance().getConnection();
      ResultSetHandler<Userprofile> rsh = new BeanHandler<Userprofile>(Userprofile.class);
      userprofile = qr.query(conn, DMSQueries.getUserDataById, rsh, 
    		  userprofile.getUserid()
        );
      
       Userprofile tenant = null;
       tenant = qr.query(conn, DMSQueries.getTenantDataByUserId, rsh, 
     		  userprofile.getUserid()
    	        );
       
       if(tenant!=null && userprofile.getOccupancy().equalsIgnoreCase("leased")){
    	   userprofile.setTenantname(tenant.getTenantname());
    	   userprofile.setTenantaddress(tenant.getTenantaddress());
    	   userprofile.setTenantcontactnumber(tenant.getTenantcontactnumber());
    	   userprofile.setTenantaltnumber(tenant.getTenantaltnumber());
    	   userprofile.setTenantemail(tenant.getTenantemail());
    	   userprofile.setTenantaadharno(tenant.getTenantaadharno());
    	   
    	   tenant=null;
       }
      return userprofile;
    } catch (Exception e) {
      logger.error("Error getting getDocumentSubTypeById :: " + e.getMessage());
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

public Vendor getVendorDataById(Vendor vendor) {
    Connection conn = null;
    
    try {
      qr = new QueryRunner();
      conn = ConnectionPoolManager.getInstance().getConnection();
      ResultSetHandler<Vendor> rsh = new BeanHandler<Vendor>(Vendor.class);
      vendor = qr.query(conn, DMSQueries.getVendorDataById, rsh, 
    		  vendor.getVendorid()
        );
      return vendor;
    } catch (Exception e) {
      logger.error("Error getting getDocumentSubTypeById :: " + e.getMessage());
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

public List<Committee> getManagersForSociety(Society society, List<Committee> committees) {
    Connection conn = null;
    
    try {
      qr = new QueryRunner();
      conn = ConnectionPoolManager.getInstance().getConnection();
      ResultSetHandler<List<Committee>> rsh = new BeanListHandler<Committee>(Committee.class);
      
      committees =  qr.query(conn, DMSQueries.getSocietyManagerList,rsh,society.getSocietyid());
      
     return committees;
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
    return null;
  }

public int removeSocietyManager(Committee committee) {
    Connection conn = null;
    try {
      qr = new QueryRunner();
      conn = ConnectionPoolManager.getInstance().getConnection();
      int rowsUpdated = qr.update(conn, DMSQueries.removeSocietyManager, 
    		  committee.getSocietymanagerid());
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

public Committee addSocietyManager(Committee committee) {
    Connection conn = null;
    
    long societyManagerId = 0L;
    try {
      qr = new QueryRunner();
      conn = ConnectionPoolManager.getInstance().getConnection();
      ResultSetHandler<Object> rsh = new ScalarHandler<Object>();
      Object obj = qr.insert(conn, DMSQueries.addSocietyManager, rsh, 
	        Long.valueOf(committee.getSocietyid()),
	        Long.valueOf(committee.getUserid())
        );
      
      societyManagerId = CommomUtility.convertToLong(obj);
      committee.setSocietymanagerid(String.valueOf(societyManagerId));
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

public Builder insertOrUpdateBuilder(Builder builder) {
    Connection conn = null;
    
    long builderId = 0L;
    try {
      qr = new QueryRunner();
      conn = ConnectionPoolManager.getInstance().getConnection();
      ResultSetHandler<Object> rsh = new ScalarHandler<Object>();
      
      conn.setAutoCommit(false);
      
      //Insert new
      if(builder.getBuilderid()==0){
    	  
    	  
    	  Object obj = qr.insert(conn, DMSQueries.insertNewBuilder, rsh, 
    			  builder.getBuildername(), 
    			  builder.getAddress(), 
    			  builder.getBlockno(), 
    			  builder.getPremisesname(), 
    			  builder.getStreetname(), 
    			  builder.getLandmark(), 
    			  builder.getArea(), 
    			  builder.getCity(), 
    			  builder.getPincode(), 
    			  builder.getState(), 
    			  builder.getCountry(), 
    			  builder.getCreatedby(),
      			  builder.getActive()
		        );

    	  builderId = CommomUtility.convertToLong(obj);
    	  builder.setBuilderid(builderId);
		
      }
      	else {
      		qr.update(conn,DMSQueries.updateBuilder,
      			  builder.getBuildername(), 
      			  builder.getAddress(), 
      			  builder.getBlockno(), 
      			  builder.getPremisesname(), 
      			  builder.getStreetname(), 
      			  builder.getLandmark(), 
      			  builder.getArea(), 
      			  builder.getCity(), 
      			  builder.getPincode(), 
      			  builder.getState(), 
      			  builder.getCountry(),
      			  builder.getActive(),
      			  builder.getBuilderid());
      }
      
      conn.commit();
      
      return builder;
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

	public List<Builder> getAllBuilder(List<Builder> builderList) { 
	  Connection conn = null;
	    try
	    {
	      qr = new QueryRunner();
	      conn = ConnectionPoolManager.getInstance().getConnection();
	      ResultSetHandler<List<Builder>> rsh = new BeanListHandler<Builder>(Builder.class);
	      builderList = qr.query(conn, DMSQueries.getAllBuilders, rsh);
	    } catch (Exception e) {
	      logger.error("Error fetching getAllBuilder List :: " + e.getMessage());
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
	    return builderList;
	  }

	public Builder getBuilderDetailsById(Builder builder) {
	    Connection conn = null;
	    
	    try {
	      qr = new QueryRunner();
	      conn = ConnectionPoolManager.getInstance().getConnection();
	      ResultSetHandler<Builder> rsh = new BeanHandler<Builder>(Builder.class);
	      builder = qr.query(conn, DMSQueries.getBuilderDetailsById, rsh, 
	    		  builder.getBuilderid()
	        );
	      return builder;
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

	public List<Project> getAllProjects(List<Project> projectList) { 
		  Connection conn = null;
		    try
		    {
		      qr = new QueryRunner();
		      conn = ConnectionPoolManager.getInstance().getConnection();
		      ResultSetHandler<List<Project>> rsh = new BeanListHandler<Project>(Project.class);
		      projectList = qr.query(conn, DMSQueries.getAllProjects, rsh);
		    } catch (Exception e) {
		      logger.error("Error fetching getAllProjects List :: " + e.getMessage());
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
		    return projectList;
		  }

	public Project getProjectDetailsById(Project project) {
	    Connection conn = null;
	    
	    try {
	      qr = new QueryRunner();
	      conn = ConnectionPoolManager.getInstance().getConnection();
	      ResultSetHandler<Project> rsh = new BeanHandler<Project>(Project.class);
	      project = qr.query(conn, DMSQueries.getProjectDetailsById, rsh, 
	    		  project.getProjectid()
	        );
	      return project;
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

	public Project insertOrUpdateProject(Project project) {
	    Connection conn = null;
	    
	    long projectId = 0L;
	    try {
	      qr = new QueryRunner();
	      conn = ConnectionPoolManager.getInstance().getConnection();
	      ResultSetHandler<Object> rsh = new ScalarHandler<Object>();
	      
	      conn.setAutoCommit(false);
	      
	      //Insert new
	      if(project.getProjectid()==0){
	    	  
	    	  
	    	  Object obj = qr.insert(conn, DMSQueries.insertNewProject, rsh, 
	    			  project.getProjectname(),
	    			  project.getBuilderid(),
	    			  project.getSiteaddress(),
	    			  project.getPlotarea(),
	    			  project.getRegistrationdate(),
	    			  project.getTowernos(),
	    			  project.getResnos(),
	    			  project.getBungnos(),
	    			  project.getPentanos(),
	    			  project.getShopnos(),
	    			  project.getGalanos(),
	    			  project.getCreatedby()
			        );

	    	  projectId = CommomUtility.convertToLong(obj);
	    	  project.setProjectid(projectId);
			
	      }
	      	else {
	      		qr.update(conn,DMSQueries.updateProject,
	      				  project.getProjectname(),
		    			  project.getBuilderid(),
		    			  project.getSiteaddress(),
		    			  project.getPlotarea(),
		    			  project.getRegistrationdate(),
		    			  project.getTowernos(),
		    			  project.getResnos(),
		    			  project.getBungnos(),
		    			  project.getPentanos(),
		    			  project.getShopnos(),
		    			  project.getGalanos(),
		    			  project.getCreatedby(),
		    			  project.getActive(),
		    			  project.getProjectid()); 
	      		}
	      
	      conn.commit();
	      
	      return project;
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

	public List<Society> getAllSociety(List<Society> societyList) { 
		  Connection conn = null;
		  List<Society> tempSocietyList;
		    try
		    {
		      qr = new QueryRunner();
		      conn = ConnectionPoolManager.getInstance().getConnection();
		      ResultSetHandler<List<Society>> rsh = new BeanListHandler<Society>(Society.class);
		      tempSocietyList = qr.query(conn, DMSQueries.getAllSociety, rsh);
		      
		      System.out.println("tempSocietyList :: "+tempSocietyList);
		      
		      for(Society society : tempSocietyList){
		    	  
		    	  if(society.getProjectid()!=0){
		    		   
		    		  Project project = qr.query(conn, DMSQueries.getProjectDetailsById, 
						    				  new BeanHandler<Project>(Project.class), 
						    				  society.getProjectid()
		    			        			);
		    		  society.setProjectname(project.getProjectname());
		    		  
		    	  }
		    	  societyList.add(society);
		      }
		      
		      
		    } catch (Exception e) {
		      logger.error("Error fetching getAllProjects List :: " + e.getMessage());
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

	public List<GenericBean> getSocDocMapping(List<GenericBean> socDocMapping) { 
		  Connection conn = null; 
		    try
		    {
		    	
		      qr = new QueryRunner();
		      conn = ConnectionPoolManager.getInstance().getConnection();
		      ResultSetHandler<List<GenericBean>> rsh = new BeanListHandler<GenericBean>(GenericBean.class);
		      socDocMapping = qr.query(conn, DMSQueries.getAllSocDocMapping, rsh); 
		      
		    } catch (Exception e) {
		      logger.error("Error fetching getAllProjects List :: " + e.getMessage());
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
		    return socDocMapping;
		  }
	
	
	public int removeSocietyDocmapping(GenericBean bean) {
	    Connection conn = null;
	    try {
	      qr = new QueryRunner();
	      conn = ConnectionPoolManager.getInstance().getConnection();
	      int rowsUpdated = qr.update(conn, DMSQueries.removeSocietyDocmapping, 
	    		  bean.getSocietydocmappingid());
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
	
	public List<GenericBean> getAllRoles(List<GenericBean> roleList) { 
		  Connection conn = null;
		    try
		    {
		      qr = new QueryRunner();
		      conn = ConnectionPoolManager.getInstance().getConnection();
		      ResultSetHandler<List<GenericBean>> rsh = new BeanListHandler<GenericBean>(GenericBean.class);
		      roleList = qr.query(conn, DMSQueries.getAllRoles, rsh);
		    } catch (Exception e) {
		      logger.error("Error fetching getAllBuilder List :: " + e.getMessage());
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
	
	public User saveAdminUser(User adminUser) {
	    Connection conn = null;
	    
	    long userid = 0L;
	    try {
	      qr = new QueryRunner();
	      conn = ConnectionPoolManager.getInstance().getConnection();
	      ResultSetHandler<Object> rsh = new ScalarHandler<Object>();
	      
	      conn.setAutoCommit(false);
	      
	      if(adminUser.getUserid()==0){
	    	  Object obj = qr.insert(conn, DMSQueries.insertNewAdminUser, rsh, 
	    			  adminUser.getFirstName(),
	    			  adminUser.getLastName(),
	    			  adminUser.getPassword(),
	    			  adminUser.getMobileNo(),
	    			  adminUser.getCreatedby(),
	    			  adminUser.getUserroleid()
			        );

	    	  userid = CommomUtility.convertToLong(obj);
	    	  adminUser.setUserid(userid);
	      }
	      else{
	    	  Object obj = qr.update(conn, DMSQueries.updateAdminUser, 
	    			  adminUser.getFirstName(),
	    			  adminUser.getLastName(),
	    			  adminUser.getPassword(),
	    			  adminUser.getActive(),
	    			  adminUser.getMobileNo(),
	    			  adminUser.getUserroleid(),
	    			  adminUser.getUserid()
			        );
	      }
	    
	      conn.commit();
	      
	      return adminUser;
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

	public List<User> getAllAdminUsers(List<User> adminUsers) { 
		  Connection conn = null;
		    try
		    {
		      qr = new QueryRunner();
		      conn = ConnectionPoolManager.getInstance().getConnection();
		      ResultSetHandler<List<User>> rsh = new BeanListHandler<User>(User.class);
		      adminUsers = qr.query(conn, DMSQueries.getAllAdminUsers, rsh);
		    } catch (Exception e) {
		      logger.error("Error fetching getAllBuilder List :: " + e.getMessage());
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
		    return adminUsers;
		  }

	
	
	public User editAdminUser(User user) {
	    Connection conn = null;
	    
	    try {
	      qr = new QueryRunner();
	      conn = ConnectionPoolManager.getInstance().getConnection();
	      ResultSetHandler<User> rsh = new BeanHandler<User>(User.class);
	      user = qr.query(conn, DMSQueries.getUserById, rsh, 
	    		  user.getUserid()
	      );
	      return user;
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
	
	
	public List<HashMap<String, Object>> getNoticeboardDocs(long societyid,List<HashMap<String, Object>> photos)
	  {
	    Connection conn = null;
	    
	    List<Files> fileList = null;
	    FtpWrapper ftp = new FtpWrapper();
	    String hostDomain = ftp.getServerName();
	    String Id = ftp.getUsername();
	    String Password = ftp.getPassword();
	    try { 
	    	
	      qr = new QueryRunner();
	      conn = ConnectionPoolManager.getInstance().getConnection();
	      ResultSetHandler<List<Files>> rsh = new BeanListHandler<Files>(Files.class);
	      
	      fileList =  qr.query(conn,DMSQueries.getNoticeboardDocBySocid,rsh,societyid);
	      
	      if (fileList.size() > 0) {
	        if (ftp.connectAndLogin(hostDomain, Id, Password))
	        {
	          ftp.setPassiveMode(true);
	          ftp.binary();
	          ftp.setBufferSize(1024000);
	          ftp.changeWorkingDirectory("DMS/");
	          
	          for (Files file : fileList)
	          {
	            InputStream stream = ftp.retrieveFileStream(file.getFilename());
	            byte[] bytes = IOUtils.toByteArray(stream);
	            
	            //System.out.println("Base64Utils.encode(bytes)" +  new String(Base64Utils.encode(bytes)));
	            HashMap<String, Object> hmap = new HashMap<String, Object>();
	            hmap.put("filename", file.getFilename());
	            hmap.put("documentid", file.getDocumentid());
	            hmap.put("contenttype", file.getMimetype());
	            hmap.put("createdon", file.getCreatedon());
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

	public List<HashMap<String, Object>> displayDocument(String doctypeid, String userid,
			List<HashMap<String, Object>> docList) {
	    Connection conn = null;
	    
	    List<Files> fileList = null;
	    FtpWrapper ftp = new FtpWrapper();
	    String hostDomain = ftp.getServerName();
	    String Id = ftp.getUsername();
	    String Password = ftp.getPassword();
	    try { 
	    	
	      qr = new QueryRunner();
	      conn = ConnectionPoolManager.getInstance().getConnection();
	      ResultSetHandler<List<Files>> rsh = new BeanListHandler<Files>(Files.class);
	      
	      fileList =  qr.query(conn,DMSQueries.getDocumentsToDisplay,rsh,doctypeid,userid);
	      
	      if (fileList.size() > 0) {
	        if (ftp.connectAndLogin(hostDomain, Id, Password))
	        {
	          ftp.setPassiveMode(true);
	          ftp.binary();
	          ftp.setBufferSize(1024000);
	          ftp.changeWorkingDirectory("DMS/");
	          
	          for (Files file : fileList)
	          {
	            InputStream stream = ftp.retrieveFileStream(file.getFilename());
	            byte[] bytes = IOUtils.toByteArray(stream);
	            
	            //System.out.println("Base64Utils.encode(bytes)" +  new String(Base64Utils.encode(bytes)));
	            HashMap<String, Object> hmap = new HashMap<String, Object>();
	            hmap.put("filename", file.getFilename());
	            hmap.put("documentid", file.getDocumentid());
	            hmap.put("contenttype", file.getMimetype());
	            hmap.put("createdon", file.getCreatedon());
	            hmap.put("file",  new String(Base64Utils.encode(bytes)));
	            docList.add(hmap);
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
	    return docList;
	  }
	
	
	public List<HashMap<String, Object>> displayDocumentFromSearch(String documentid , List<HashMap<String, Object>> docList) {
	    Connection conn = null;
	    
	    List<Files> fileList = null;
	    FtpWrapper ftp = new FtpWrapper();
	    String hostDomain = ftp.getServerName();
	    String Id = ftp.getUsername();
	    String Password = ftp.getPassword();
	    try { 
	    	
	      qr = new QueryRunner();
	      conn = ConnectionPoolManager.getInstance().getConnection();
	      ResultSetHandler<List<Files>> rsh = new BeanListHandler<Files>(Files.class);
	      
	      fileList =  qr.query(conn,DMSQueries.getDocumentsToDisplayByDocId,rsh,documentid);
	      
	      if (fileList.size() > 0) {
	        if (ftp.connectAndLogin(hostDomain, Id, Password))
	        {
	          ftp.setPassiveMode(true);
	          ftp.binary();
	          ftp.setBufferSize(1024000);
	          ftp.changeWorkingDirectory("DMS/");
	          
	          for (Files file : fileList)
	          {
	            InputStream stream = ftp.retrieveFileStream(file.getFilename());
	            byte[] bytes = IOUtils.toByteArray(stream);
	            
	            //System.out.println("Base64Utils.encode(bytes)" +  new String(Base64Utils.encode(bytes)));
	            HashMap<String, Object> hmap = new HashMap<String, Object>();
	            hmap.put("filename", file.getFilename());
	            hmap.put("documentid", file.getDocumentid());
	            hmap.put("contenttype", file.getMimetype());
	            hmap.put("createdon", file.getCreatedon());
	            hmap.put("file",  new String(Base64Utils.encode(bytes)));
	            docList.add(hmap);
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
	    return docList;
	  }
	
}