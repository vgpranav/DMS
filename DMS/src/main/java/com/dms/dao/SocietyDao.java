package com.dms.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
//import java.time.LocalDate;
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
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;

import com.dms.beans.Actionlogger;
import com.dms.beans.Builder;
import com.dms.beans.BuilderManager;
import com.dms.beans.CallReference;
import com.dms.beans.Committee;
import com.dms.beans.CommitteeMaster;
import com.dms.beans.DocSubType;
import com.dms.beans.Doctype;
import com.dms.beans.FileMonitoring;
import com.dms.beans.Files;
import com.dms.beans.FormFields;
import com.dms.beans.GenericBean;
import com.dms.beans.Loginhistory;
import com.dms.beans.Parking;
import com.dms.beans.Photos;
import com.dms.beans.Project;
import com.dms.beans.Society;
import com.dms.beans.SocietyType;
import com.dms.beans.User;
import com.dms.beans.UserSCNominee;
import com.dms.beans.Userprofile;
import com.dms.beans.Vendor;
import com.dms.util.CommomUtility;
import com.dms.util.ConnectionPoolManager;
import com.dms.util.DMSQueries;
import com.dms.util.FtpWrapper;

public class SocietyDao
{
  private final static org.slf4j.Logger dblogger = LoggerFactory.getLogger("dblogger");
  
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
      dblogger.error("Error fetching SocType List :: ", e);
      e.printStackTrace();
    }
    finally
    {
      try
      {
        DbUtils.close(conn);
      } catch (SQLException e) {
        dblogger.error("Error releasing connection :: ", e);
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
      dblogger.error("Error getting soc list :: ", e);
      e.printStackTrace();
    } finally {
      try {
        DbUtils.close(conn);
      } catch (SQLException e) {
        dblogger.error("Error releasing connection :: ", e);
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
			        society.getProjectid(),
			        society.getSocietytype()
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
		    CommomUtility.formatDate(society.getEstdate()),
		    society.getLandmark(),
		    society.getCity(),
		    society.getCountry(),
		    society.getNoofshop(),
		    society.getNoofflat(),
		    society.getNoof1rk(),
		    society.getNoof1bhk(),
		    society.getNoof1p5bhk(),
		    society.getNoof2bhk(),
		    society.getNoof2p5bhk(),
		    society.getNoof3bhk(),
		    society.getNoof3p5bhk()
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
      				CommomUtility.formatDate(society.getEstdate()),
      				society.getLandmark(),
      			    society.getCity(),
      			    society.getCountry(),
      			    society.getNoofshop(),
      			  	society.getNoofflat(),
      			    society.getNoof1rk(),
	      		    society.getNoof1bhk(),
	      		    society.getNoof1p5bhk(),
	      		    society.getNoof2bhk(),
	      		    society.getNoof2p5bhk(),
	      		    society.getNoof3bhk(),
	      		    society.getNoof3p5bhk(),
      				society.getSocietyid());
    	  
      }

      conn.commit();
      
      society.setSocietyid(societyId);
      society.setSocietyprofileid(societyProfileId);
      
      return society;
    }
    catch (Exception e) {
      dblogger.error("Error getting soc list :: ", e);
      e.printStackTrace();
    } finally {
      try {
        DbUtils.close(conn);
      } catch (SQLException e) {
        dblogger.error("Error releasing connection :: ", e);
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
      dblogger.error("Error fetching Society List :: ", e);
      e.printStackTrace();
    }
    finally
    {
      try
      {
        DbUtils.close(conn);
      } catch (SQLException e) {
        dblogger.error("Error releasing connection :: ", e);
      }
    }
    return societyList;
  }
  
  
  
  
  
  public List<Society> getAllActiveSocietyDetails(List<Society> societyList)
  {
    Connection conn = null;
    try
    {
      qr = new QueryRunner();
      conn = ConnectionPoolManager.getInstance().getConnection();
      ResultSetHandler<List<Society>> rsh = new BeanListHandler<Society>(Society.class);
      societyList = qr.query(conn, DMSQueries.getAllActiveSocietyDetails, rsh);
    } catch (Exception e) {
      dblogger.error("Error fetching Society List :: ", e);
      e.printStackTrace();
    }
    finally
    {
      try
      {
        DbUtils.close(conn);
      } catch (SQLException e) {
        dblogger.error("Error releasing connection :: ", e);
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
    		        userprofile.getMobileNo(),
    		        userprofile.getMiddleName()
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
    		          CommomUtility.formatDate(userprofile.getPurchasedate()), 
    		          CommomUtility.formatDate(userprofile.getPossessiondate()), 
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
    		          userprofile.getPercent3(),
    		          userprofile.getVehicleno(),
    		          userprofile.getCompanyname(),
    		          userprofile.getCompanytype(),
    		          userprofile.getGumastalicno(),
    		          userprofile.getMembertype(),
    		          userprofile.getCommercialtype(),
    		          userprofile.getFlattype()
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
    	    			  userprofile.getTenantaadharno(),
    	    			  userprofile.getTenantType(),
    	    			  userprofile.getTenantPVstatus(),
    	    			  userprofile.getTenantfrom(),
        		          userprofile.getTenantto(),
        		          userprofile.getTenantcompanyname(),
        		          userprofile.getTenantcompanytype(),
        		          userprofile.getTenantgumastalicno()
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
  		        userprofile.getMiddleName(),
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
		          CommomUtility.formatDate(userprofile.getPurchasedate()), 
		          CommomUtility.formatDate(userprofile.getPossessiondate()), 
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
		          userprofile.getVehicleno(),
		          userprofile.getCompanyname(),
		          userprofile.getCompanytype(),
		          userprofile.getGumastalicno(),
		          userprofile.getMembertype(),
		          userprofile.getCommercialtype(),
		          userprofile.getFlattype(),
		          userprofile.getUserid()
	          );
    	  
    	  if(userprofile.getOccupancy().equalsIgnoreCase("leased")){
    		  
    		  ResultSetHandler<Userprofile> rsh1 = new BeanHandler<Userprofile>(Userprofile.class);
    		  Userprofile tenantExists = qr.query(conn,DMSQueries.getTenantByUserId,rsh1,userprofile.getUserid()); 
    				  
    		  if(tenantExists!=null){
    			  
    			  qr.update(conn, DMSQueries.updateNewTenant, 
    	    			  userprofile.getTenantname(),
    	    			  userprofile.getTenantaddress(),
    	    			  userprofile.getTenantcontactnumber(),
    	    			  userprofile.getTenantaltnumber(),
    	    			  userprofile.getTenantemail(),
    	    			  userprofile.getTenantaadharno(),
    	    			  userprofile.getTenantType(),
    	    			  userprofile.getTenantPVstatus(),
    			          userprofile.getTenantfrom(),
    			          userprofile.getTenantto(),
    			          userprofile.getTenantcompanyname(),
        		          userprofile.getTenantcompanytype(),
        		          userprofile.getTenantgumastalicno(),
    	    			  userprofile.getUserid()
    	    			  );
    		  } else {
    			  
    			  qr.insert(conn, DMSQueries.insertNewTenant, rsh,
    					  userprofile.getUserid(),
    	    			  userprofile.getTenantname(),
    	    			  userprofile.getTenantaddress(),
    	    			  userprofile.getTenantcontactnumber(),
    	    			  userprofile.getTenantaltnumber(),
    	    			  userprofile.getTenantemail(),
    	    			  userprofile.getTenantaadharno(),
    	    			  userprofile.getTenantType(),
    	    			  userprofile.getTenantPVstatus(),
    	    			  userprofile.getTenantfrom(),
        		          userprofile.getTenantto(),
        		          userprofile.getTenantcompanyname(),
        		          userprofile.getTenantcompanytype(),
        		          userprofile.getTenantgumastalicno()
    	    			  );
    			  
    		  }
    		  
	      }
      }
      
      qr.update(conn, DMSQueries.updateParkingDetails, 
			  userprofile.getUserid(),
			  userprofile.getRandomHash()
			  );
      
      qr.update(conn, DMSQueries.updateSCDetails, 
			  userprofile.getUserid(),
			  userprofile.getRandomHash()
			  );
      
      
      conn.commit();
      
      return userprofile;
    }
    catch (Exception e) {
      dblogger.error("Error getting soc list :: ", e);
      e.printStackTrace();
    } finally {
      try {
        DbUtils.close(conn);
      } catch (SQLException e) {
        dblogger.error("Error releasing connection :: ", e);
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
      dblogger.error("Error getMembersForSociety :: ", e);
      e.printStackTrace();
    }
    finally
    {
      try
      {
        DbUtils.close(conn);
      } catch (SQLException e) {
        dblogger.error("Error releasing connection :: ", e);
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
      dblogger.error("Error getCommitteeMaster :: ", e);
      e.printStackTrace();
    }
    finally
    {
      try
      {
        DbUtils.close(conn);
      } catch (SQLException e) {
        dblogger.error("Error releasing connection :: ", e);
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
      dblogger.error("Error getting soc list :: ", e);
      e.printStackTrace();
    } finally {
      try {
        DbUtils.close(conn);
      } catch (SQLException e) {
        dblogger.error("Error releasing connection :: ", e);
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
      dblogger.error("Error getting soc list :: ", e);
      e.printStackTrace();
    } finally {
      try {
        DbUtils.close(conn);
      } catch (SQLException e) {
        dblogger.error("Error releasing connection :: ", e);
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
      dblogger.error("Error getCommitteMembersForSociety :: ", e);
      e.printStackTrace();
    }
    finally
    {
      try
      {
        DbUtils.close(conn);
      } catch (SQLException e) {
        dblogger.error("Error releasing connection :: ", e);
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
      dblogger.error("Error getting soc list :: ", e);
      e.printStackTrace();
    } finally {
      try {
        DbUtils.close(conn);
      } catch (SQLException e) {
        dblogger.error("Error releasing connection :: ", e);
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
      
      System.out.println("userid :: "+userid+"/nsocietyList :: "+societyList);
    } catch (Exception e) {
      dblogger.error("Error getCommitteMembersForSociety :: ", e);
      e.printStackTrace();
    }
    finally
    {
      try
      {
        DbUtils.close(conn);
      } catch (SQLException e) {
        dblogger.error("Error releasing connection :: ", e);
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
      dblogger.error("Error getCommitteMembersForSociety :: ", e);
      e.printStackTrace();
    }
    finally
    {
      try
      {
        DbUtils.close(conn);
      } catch (SQLException e) {
        dblogger.error("Error releasing connection :: ", e);
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
        		  vendor.getCreatedby(),
        		  vendor.getContractfrom(),
        		  vendor.getContractto()
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
        		   vendor.getContractfrom(),
        		  vendor.getContractto(),
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
      dblogger.error("Error getting soc list :: ", e);
      e.printStackTrace();
    } finally {
      try {
        DbUtils.close(conn);
      } catch (SQLException e) {
        dblogger.error("Error releasing connection :: ", e);
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
      dblogger.error("Error getCommitteMembersForSociety :: ", e);
      e.printStackTrace();
    }
    finally
    {
      try
      {
        DbUtils.close(conn);
      } catch (SQLException e) {
        dblogger.error("Error releasing connection :: ", e);
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
      dblogger.error("Error getting soc list :: ", e);
      e.printStackTrace();
    } finally {
      try {
        DbUtils.close(conn);
      } catch (SQLException e) {
        dblogger.error("Error releasing connection :: ", e);
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
      dblogger.error("Error getting soc list :: ", e);
      e.printStackTrace();
    } finally {
      try {
        DbUtils.close(conn);
      } catch (SQLException e) {
        dblogger.error("Error releasing connection :: ", e);
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
      dblogger.error("Error getting getDocumentSubTypeById :: ", e);
      e.printStackTrace();
    } finally {
      try {
        DbUtils.close(conn);
      } catch (SQLException e) {
        dblogger.error("Error releasing connection :: ", e);
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
      dblogger.error("Error in getFormFieldsById :: ", e);
      e.printStackTrace();
    } finally {
      try {
        DbUtils.close(conn);
      } catch (SQLException e) {
        dblogger.error("Error releasing connection :: ", e);
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
      
       if(userprofile==null)
    	   return null;
       
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
    	   userprofile.setTenantType(tenant.getTenantType());
    	   userprofile.setTenantPVstatus(tenant.getTenantPVstatus());
    	   userprofile.setTenantfrom(tenant.getTenantfrom());
    	   userprofile.setTenantto(tenant.getTenantto());
    	   tenant=null;
       }
      return userprofile;
    } catch (Exception e) {
      dblogger.error("Error getting getDocumentSubTypeById :: ", e);
      e.printStackTrace();
    } finally {
      try {
        DbUtils.close(conn);
      } catch (SQLException e) {
        dblogger.error("Error releasing connection :: ", e);
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
      dblogger.error("Error getting getDocumentSubTypeById :: ", e);
      e.printStackTrace();
    } finally {
      try {
        DbUtils.close(conn);
      } catch (SQLException e) {
        dblogger.error("Error releasing connection :: ", e);
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
      dblogger.error("Error getCommitteMembersForSociety :: ", e);
      e.printStackTrace();
    }
    finally
    {
      try
      {
        DbUtils.close(conn);
      } catch (SQLException e) {
        dblogger.error("Error releasing connection :: ", e);
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
      dblogger.error("Error getting soc list :: ", e);
      e.printStackTrace();
    } finally {
      try {
        DbUtils.close(conn);
      } catch (SQLException e) {
        dblogger.error("Error releasing connection :: ", e);
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
      dblogger.error("Error getting soc list :: ", e);
      e.printStackTrace();
    } finally {
      try {
        DbUtils.close(conn);
      } catch (SQLException e) {
        dblogger.error("Error releasing connection :: ", e);
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
      			  builder.getActive(),
      			  builder.getContact(),
      			  builder.getAltcontact(),
      			  builder.getEmail(),
      			  builder.getDistrict()
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
      			  builder.getContact(),
    			  builder.getAltcontact(),
    			  builder.getEmail(),
    			  builder.getDistrict(),
      			  builder.getBuilderid());
      }
      
      conn.commit();
      
      return builder;
    }
    catch (Exception e) {
      dblogger.error("Error getting soc list :: ", e);
      e.printStackTrace();
    } finally {
      try {
        DbUtils.close(conn);
      } catch (SQLException e) {
        dblogger.error("Error releasing connection :: ", e);
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
	      dblogger.error("Error fetching getAllBuilder List :: ", e);
	      e.printStackTrace();
	    }
	    finally
	    {
	      try
	      {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        dblogger.error("Error releasing connection :: ", e);
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
	      dblogger.error("Error getting soc list :: ", e);
	      e.printStackTrace();
	    } finally {
	      try {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        dblogger.error("Error releasing connection :: ", e);
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
		      dblogger.error("Error fetching getAllProjects List :: ", e);
		      e.printStackTrace();
		    }
		    finally
		    {
		      try
		      {
		        DbUtils.close(conn);
		      } catch (SQLException e) {
		        dblogger.error("Error releasing connection :: ", e);
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
	      dblogger.error("Error getting soc list :: ", e);
	      e.printStackTrace();
	    } finally {
	      try {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        dblogger.error("Error releasing connection :: ", e);
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
	    			  project.getPlotarea(),
	    			  CommomUtility.formatDate(project.getRegistrationdate()),
	    			  project.getTowernos(),
	    			  project.getResnos(),
	    			  project.getBungnos(),
	    			  project.getPentanos(),
	    			  project.getShopnos(),
	    			  project.getGalanos(),
	    			  project.getCreatedby(),
	    			  
	    			  project.getStreet(),
	    			  project.getLandmark(),
	    			  project.getArea(),
	    			  project.getCity(),
	    			  project.getDistrict(),
	    			  project.getState(),
	    			  project.getCountry(),
	    			  project.getPincode()
			        );

	    	  projectId = CommomUtility.convertToLong(obj);
	    	  project.setProjectid(projectId);
			
	      }
	      	else {
	      		qr.update(conn,DMSQueries.updateProject,
	      				  project.getProjectname(),
		    			  project.getBuilderid(),
		    			  project.getPlotarea(),
		    			  CommomUtility.formatDate(project.getRegistrationdate()),
		    			  project.getTowernos(),
		    			  project.getResnos(),
		    			  project.getBungnos(),
		    			  project.getPentanos(),
		    			  project.getShopnos(),
		    			  project.getGalanos(),
		    			  project.getCreatedby(),
		    			  project.getActive(),
		    			  project.getStreet(),
		    			  project.getLandmark(),
		    			  project.getArea(),
		    			  project.getCity(),
		    			  project.getDistrict(),
		    			  project.getState(),
		    			  project.getCountry(),
		    			  project.getPincode(),
		    			  project.getProjectid()); 
	      		}
	      
	      conn.commit();
	      
	      return project;
	    }
	    catch (Exception e) {
	      dblogger.error("Error getting soc list :: ", e);
	      e.printStackTrace();
	    } finally {
	      try {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        dblogger.error("Error releasing connection :: ", e);
	      }
	    }
	    return null;
	  }

	public List<Society> getAllSociety(List<Society> societyList, String societytype) { 
		  Connection conn = null;
		  List<Society> tempSocietyList;
		    try
		    {
		      qr = new QueryRunner();
		      conn = ConnectionPoolManager.getInstance().getConnection();
		      ResultSetHandler<List<Society>> rsh = new BeanListHandler<Society>(Society.class);
		      tempSocietyList = qr.query(conn, DMSQueries.getAllSociety, rsh,societytype);
		      
		      //System.out.println("tempSocietyList :: "+tempSocietyList);
		      
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
		      dblogger.error("Error fetching getAllProjects List :: ", e);
		      e.printStackTrace();
		    }
		    finally
		    {
		      try
		      {
		        DbUtils.close(conn);
		      } catch (SQLException e) {
		        dblogger.error("Error releasing connection :: ", e);
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
		      dblogger.error("Error fetching getAllProjects List :: ", e);
		      e.printStackTrace();
		    }
		    finally
		    {
		      try
		      {
		        DbUtils.close(conn);
		      } catch (SQLException e) {
		        dblogger.error("Error releasing connection :: ", e);
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
	      dblogger.error("Error getting soc list :: ", e);
	      e.printStackTrace();
	    } finally {
	      try {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        dblogger.error("Error releasing connection :: ", e);
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
		      dblogger.error("Error fetching getAllBuilder List :: ", e);
		      e.printStackTrace();
		    }
		    finally
		    {
		      try
		      {
		        DbUtils.close(conn);
		      } catch (SQLException e) {
		        dblogger.error("Error releasing connection :: ", e);
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
	      
	      if(adminUser.getUserType().equalsIgnoreCase("new")){
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
	      } else {
	    	  Object obj = qr.update(conn,DMSQueries.editAdminRole,1,adminUser.getUserid());
	      }
	      	    
	      conn.commit();
	      
	      return adminUser;
	    }
	    catch (Exception e) {
	      dblogger.error("Error getting soc list :: ", e);
	      e.printStackTrace();
	    } finally {
	      try {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        dblogger.error("Error releasing connection :: ", e);
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
		      dblogger.error("Error fetching getAllBuilder List :: ", e);
		      e.printStackTrace();
		    }
		    finally
		    {
		      try
		      {
		        DbUtils.close(conn);
		      } catch (SQLException e) {
		        dblogger.error("Error releasing connection :: ", e);
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
	      dblogger.error("Error getting soc list :: ", e);
	      e.printStackTrace();
	    } finally {
	      try {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        dblogger.error("Error releasing connection :: ", e);
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
	            if(stream!=null){
	            	byte[] bytes = IOUtils.toByteArray(stream);
		            
		            //System.out.println("Base64Utils.encode(bytes)" +  new String(Base64Utils.encode(bytes)));
		            HashMap<String, Object> hmap = new HashMap<String, Object>();
		            hmap.put("filename", file.getFilename());
		            hmap.put("documentid", file.getDocumentid());
		            hmap.put("contenttype", file.getMimetype());
		            hmap.put("createdon", file.getCreatedon());
		            hmap.put("file",  new String(Base64Utils.encode(bytes)));
		            photos.add(hmap);
	            }
	            ftp.completePendingCommand();
	          }
	           
	        }
	      } else System.out.println("No Files");
	    }
	    catch (Exception e)
	    {
	      dblogger.error("Error getCommitteMembersForSociety :: ", e);
	      e.printStackTrace();
	    }
	    finally
	    {
	      try
	      {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        dblogger.error("Error releasing connection :: ", e);
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
			String societyid, List<HashMap<String, Object>> docList) {
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
	      
	      fileList =  qr.query(conn,DMSQueries.getDocumentsToDisplay,rsh,doctypeid,userid,societyid);
	      
	      if (fileList.size() > 0) {
	    	  for (Files file : fileList)
	          {
	    		  HashMap<String, Object> hmap = new HashMap<String, Object>();
		            hmap.put("fileid", file.getFilesid());
		            hmap.put("filename", file.getFilename());
		            hmap.put("documentid", file.getDocumentid());
		            hmap.put("contenttype", file.getMimetype());
		            hmap.put("createdon", file.getCreatedon());
		            docList.add(hmap);
	          }
	    	  
	    	  /*
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
	            hmap.put("fileid", file.getFilesid());
	            hmap.put("filename", file.getFilename());
	            hmap.put("documentid", file.getDocumentid());
	            hmap.put("contenttype", file.getMimetype());
	            hmap.put("createdon", file.getCreatedon());
	            hmap.put("file",  new String(Base64Utils.encode(bytes)));
	            docList.add(hmap);
	            ftp.completePendingCommand();
	          }
	           
	        }
	      */} else 
	    	  System.out.println("No Files");
	    }
	    catch (Exception e)
	    {
	      dblogger.error("Error getCommitteMembersForSociety :: ", e);
	      e.printStackTrace();
	    }
	    finally
	    {
	      try
	      {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        dblogger.error("Error releasing connection :: ", e);
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
		    	  for (Files file : fileList)
		          {
		    		  HashMap<String, Object> hmap = new HashMap<String, Object>();
			            hmap.put("fileid", file.getFilesid());
			            hmap.put("filename", file.getFilename());
			            hmap.put("documentid", file.getDocumentid());
			            hmap.put("contenttype", file.getMimetype());
			            hmap.put("createdon", file.getCreatedon());
			            docList.add(hmap);
		          }
		    	  
		    	  } else 
		    	  System.out.println("No Files");
	      
	    }
	    catch (Exception e)
	    {
	      dblogger.error("Error getCommitteMembersForSociety :: ", e);
	      e.printStackTrace();
	    }
	    finally
	    {
	      try
	      {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        dblogger.error("Error releasing connection :: ", e);
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

	public List<GenericBean> getAllExistingDocsForSoc(Society society, List<GenericBean> docs) { 
		  Connection conn = null;
		    try
		    {
		      qr = new QueryRunner();
		      conn = ConnectionPoolManager.getInstance().getConnection();
		      ResultSetHandler<List<GenericBean>> rsh = new BeanListHandler<GenericBean>(GenericBean.class);
		      docs = qr.query(conn, DMSQueries.getDocSummaryforAdminpanel,rsh,society.getSocietyid(),society.getSocietyid());
		    } catch (Exception e) {
		      dblogger.error("Error fetching getAllBuilder List :: ", e);
		      e.printStackTrace();
		    }
		    finally
		    {
		      try
		      {
		        DbUtils.close(conn);
		      } catch (SQLException e) {
		        dblogger.error("Error releasing connection :: ", e);
		      }
		    }
		    return docs;
		  }

	public List<Userprofile> getNeighborDetails(long userid, List<Userprofile> profiles) {
	    Connection conn = null;
	    try
	    {
	      qr = new QueryRunner();
	      conn = ConnectionPoolManager.getInstance().getConnection();
	      ResultSetHandler<List<Userprofile>> rsh = new BeanListHandler<Userprofile>(Userprofile.class);
	      profiles =  qr.query(conn, DMSQueries.getNeighborProfile, rsh,userid);
	    }
	    catch (Exception e) {
	      dblogger.error("Error getMembersForSociety :: ", e);
	      e.printStackTrace();
	    }
	    finally
	    {
	      try
	      {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        dblogger.error("Error releasing connection :: ", e);
	      }
	    }
	    return profiles;
	  }

	public CallReference saveCallRef(CallReference callref) {
	    Connection conn = null;
	    
	    long builderId = 0L;
	    try {
	      qr = new QueryRunner();
	      conn = ConnectionPoolManager.getInstance().getConnection();
	      ResultSetHandler<Object> rsh = new ScalarHandler<Object>();
	      
	      conn.setAutoCommit(false);
	      long callrefId=0;
	      //Insert new
	      if(callref.getCallrefid()==0){
	    	  
	    	  
	    	  Object obj = qr.insert(conn, DMSQueries.addNewCallRef, rsh, 
	    			  
	    			  callref.getRefno(),
	    			  callref.getInitiatorname(),
	    			  callref.getSocietytype(),
	    			  callref.getBuildingno(),
	    			  callref.getStreetname(),
	    			  callref.getLandmark(),
	    			  callref.getArea(),
	    			  callref.getPincode(),
	    			  callref.getCity(),
	    			  callref.getState(),
	    			  callref.getCountry(),
	    			  callref.getResno(),
	    			  callref.getShopsno(),
	    			  CommomUtility.formatDate(callref.getInitiatedate()),
	    			  callref.getRemark(),
	    			  callref.getClosingchance()
	    			  
			        );

	    	  callrefId = CommomUtility.convertToLong(obj);
	    	  
			callref.setCallrefid(callrefId);
			
	      }
	      	else {
	      		qr.update(conn,DMSQueries.updateCallRef,
	      				callref.getRefno(),
		    			  callref.getInitiatorname(),
		    			  callref.getSocietytype(),
		    			  callref.getBuildingno(),
		    			  callref.getStreetname(),
		    			  callref.getLandmark(),
		    			  callref.getArea(),
		    			  callref.getPincode(),
		    			  callref.getCity(),
		    			  callref.getState(),
		    			  callref.getCountry(),
		    			  callref.getResno(),
		    			  callref.getShopsno(),
		    			  CommomUtility.formatDate(callref.getInitiatedate()),
		    			  callref.getRemark(),
		    			  callref.getClosingchance(),
		    			  callref.getCallrefid());
	      }
	      
	      conn.commit();
	      
	      return callref;
	    }
	    catch (Exception e) {
	      dblogger.error("Error getting soc list :: ", e);
	      e.printStackTrace();
	    } finally {
	      try {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        dblogger.error("Error releasing connection :: ", e);
	      }
	    }
	    return null;
	  }

	public CallReference saveCallrefContact(CallReference callref) {
	    Connection conn = null;
	    long userid = 0L;
	    try {
	      qr = new QueryRunner();
	      conn = ConnectionPoolManager.getInstance().getConnection();
	      ResultSetHandler<Object> rsh = new ScalarHandler<Object>();
	      conn.setAutoCommit(false);
	    	  Object obj = qr.insert(conn, DMSQueries.insertNewCallRefContact, rsh, 
	    			  callref.getCallrefid(),
	    			  callref.getContactname(),
	    			  callref.getContactdesignation(),
	    			  callref.getContactmobileno(),
	    			  callref.getContactlandlineno(),
	    			  callref.getContactemail()
			        );
	      conn.commit();
	      return callref;
	    }
	    catch (Exception e) {
	      dblogger.error("Error getting soc list :: ", e);
	      e.printStackTrace();
	    } finally {
	      try {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        dblogger.error("Error releasing connection :: ", e);
	      }
	    }
	    return null;
	  }

	public List<CallReference> getContactsByCallRefId(long callrefid, List<CallReference> profiles) {
	    Connection conn = null;
	    try
	    {
	      qr = new QueryRunner();
	      conn = ConnectionPoolManager.getInstance().getConnection();
	      ResultSetHandler<List<CallReference>> rsh = new BeanListHandler<CallReference>(CallReference.class);
	      profiles =  qr.query(conn, DMSQueries.getContactsByCallRefId, rsh,callrefid);
	    }
	    catch (Exception e) {
	      dblogger.error("Error getMembersForSociety :: ", e);
	      e.printStackTrace();
	    }
	    finally
	    {
	      try
	      {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        dblogger.error("Error releasing connection :: ", e);
	      }
	    }
	    return profiles;
	  }

	public CallReference saveCallrefMeeting(CallReference callref) {
	    Connection conn = null;
	    long userid = 0L;
	    try {
	      qr = new QueryRunner();
	      conn = ConnectionPoolManager.getInstance().getConnection();
	      ResultSetHandler<Object> rsh = new ScalarHandler<Object>();
	      conn.setAutoCommit(false);
	    	  Object obj = qr.insert(conn, DMSQueries.saveCallrefMeeting, rsh, 
	    			  callref.getCallrefid(),
	    			  CommomUtility.formatDate(callref.getMeetingdate()),
	    			  callref.getMeetingperson(),
	    			  callref.getMeetingremarks()
			        );
	      conn.commit();
	      return callref;
	    }
	    catch (Exception e) {
	      dblogger.error("Error getting soc list :: ", e);
	      e.printStackTrace();
	    } finally {
	      try {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        dblogger.error("Error releasing connection :: ", e);
	      }
	    }
	    return null;
	  }

	public List<CallReference> getMeetingsByCallRefId(long callrefid, List<CallReference> profiles) {
	    Connection conn = null;
	    try
	    {
	      qr = new QueryRunner();
	      conn = ConnectionPoolManager.getInstance().getConnection();
	      ResultSetHandler<List<CallReference>> rsh = new BeanListHandler<CallReference>(CallReference.class);
	      profiles =  qr.query(conn, DMSQueries.getMeetingsByCallRefId, rsh,callrefid);
	    }
	    catch (Exception e) {
	      dblogger.error("Error getMembersForSociety :: ", e);
	      e.printStackTrace();
	    }
	    finally
	    {
	      try
	      {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        dblogger.error("Error releasing connection :: ", e);
	      }
	    }
	    return profiles;
	  }

	public List<CallReference> getAllCallRefs(List<CallReference> calls) {
	    Connection conn = null;
	    try
	    {
	      qr = new QueryRunner();
	      conn = ConnectionPoolManager.getInstance().getConnection();
	      ResultSetHandler<List<CallReference>> rsh = new BeanListHandler<CallReference>(CallReference.class);
	      calls =  qr.query(conn, DMSQueries.getAllCallRefs, rsh);
	    }
	    catch (Exception e) {
	      dblogger.error("Error getMembersForSociety :: ", e);
	      e.printStackTrace();
	    }
	    finally
	    {
	      try
	      {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        dblogger.error("Error releasing connection :: ", e);
	      }
	    }
	    return calls;
	  }

	public long checkIfSocietyManager(long userid) {
	    Connection conn = null;
	    Object countObj=null;
	    long count=0;
	    try
	    {
	      qr = new QueryRunner();
	      conn = ConnectionPoolManager.getInstance().getConnection();
	      countObj =  qr.query(conn, DMSQueries.getCountOfSocsAsManager,new ScalarHandler<Object>(),userid);
	      count = CommomUtility.convertToLong(countObj);
	    }
	    catch (Exception e) {
	      dblogger.error("Error getMembersForSociety :: ", e);
	      e.printStackTrace();
	    }
	    finally
	    {
	      try
	      {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        dblogger.error("Error releasing connection :: ", e);
	      }
	    }
	    return count;
	  }

	public GenericBean saveSocViewMapping(GenericBean gbean) {
	    Connection conn = null;
	    long mappingId = 0L;
	    try {
	      qr = new QueryRunner();
	      conn = ConnectionPoolManager.getInstance().getConnection();
	      ResultSetHandler<Object> rsh = new ScalarHandler<Object>();
	      String key = gbean.getSocietyid()+","+gbean.getDoctypeid()+","+gbean.getDocsubtypeid();
	      
	    	  Object obj = qr.insert(conn, DMSQueries.saveSocViewMapping, rsh, 
	    			  gbean.getSocietyid(),
	    			  gbean.getDoctypeid(),
	    			  gbean.getDocsubtypeid(),
	    			  gbean.getDisplayflag(),
	    			  gbean.getConfFlag(),
	    			  gbean.getCreatedby(),
	    			  key
			        );
	    	  
	    	  mappingId = CommomUtility.convertToLong(obj);  
	    	  gbean.setSocdocviewmappingid(mappingId);
	    	  
	      return gbean;
	    }
	    catch (Exception e) {
	      dblogger.error("Error getting soc list :: ", e);
	      e.printStackTrace();
	    } finally {
	      try {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        dblogger.error("Error releasing connection :: ", e);
	      }
	    }
	    return null;
	  }

	public List<GenericBean> getMappedDocsBySocId(long societyid, List<GenericBean> beans) {
	    Connection conn = null;
	    try
	    {
	      qr = new QueryRunner();
	      conn = ConnectionPoolManager.getInstance().getConnection();
	      ResultSetHandler<List<GenericBean>> rsh = new BeanListHandler<GenericBean>(GenericBean.class);
	      beans =  qr.query(conn, DMSQueries.getSocDocMappingBySocId, rsh,societyid);
	    }
	    catch (Exception e) {
	      dblogger.error("Error getMembersForSociety :: ", e);
	      e.printStackTrace();
	    }
	    finally
	    {
	      try
	      {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        dblogger.error("Error releasing connection :: ", e);
	      }
	    }
	    return beans;
	  }

	public int removeSocDocViewMapping(GenericBean bean) {
		Connection conn = null;
	    try {
	      qr = new QueryRunner();
	      conn = ConnectionPoolManager.getInstance().getConnection();
	      int rowsUpdated = qr.update(conn, DMSQueries.removeSocDocViewMapping, 
	    		  bean.getSocdocviewmappingid());
	      return rowsUpdated;
	    } catch (Exception e) {
	      dblogger.error("Error getting soc list :: ", e);
	      e.printStackTrace();
	    } finally {
	      try {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        dblogger.error("Error releasing connection :: ", e);
	      }
	    }
	    return 0;
	}

	public List<GenericBean> getAllDesignations(List<GenericBean> desigList) {
	    Connection conn = null;
	    try
	    {
	      qr = new QueryRunner();
	      conn = ConnectionPoolManager.getInstance().getConnection();
	      ResultSetHandler<List<GenericBean>> rsh = new BeanListHandler<GenericBean>(GenericBean.class);
	      desigList =  qr.query(conn, DMSQueries.getAllDesignations, rsh);
	    }
	    catch (Exception e) {
	      dblogger.error("Error getAllDesignations :: ", e);
	      e.printStackTrace();
	    }
	    finally
	    {
	      try
	      {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        dblogger.error("Error releasing connection :: ", e);
	      }
	    }
	    return desigList;
	  }

	public GenericBean getDesignationById(GenericBean gbean) {
	    Connection conn = null;
	    
	    try {
	      qr = new QueryRunner();
	      conn = ConnectionPoolManager.getInstance().getConnection();
	      ResultSetHandler<GenericBean> rsh = new BeanHandler<GenericBean>(GenericBean.class);
	      gbean = qr.query(conn, DMSQueries.getDesignationById, rsh, 
	    		  gbean.getPositionid()
	        );
	      return gbean;
	    } catch (Exception e) {
	      dblogger.error("Error getting getDesignationById :: ", e);
	      e.printStackTrace();
	    } finally {
	      try {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        dblogger.error("Error releasing connection :: ", e);
	      }
	    }
	    return null;
	  }
 
public GenericBean saveDesignationDetails(GenericBean gbean) {

    Connection conn = null;
    
    long gbeanId = 0L;
    try {
      qr = new QueryRunner();
      conn = ConnectionPoolManager.getInstance().getConnection();
      
      conn.setAutoCommit(false);
      
      if(gbean.getPositionid()==0){
    	  
    	  ResultSetHandler<Object> rsh = new ScalarHandler<Object>();
          Object obj = qr.insert(conn, DMSQueries.insertNewDesignation, rsh, 
        		  gbean.getPositionname(),
        		  gbean.getIsactive()
            );
          
          gbeanId = CommomUtility.convertToLong(obj);
          
          
          
          gbean.setPositionid(gbeanId);
          
      } else {
    					  
          Object obj = qr.update(conn, DMSQueries.updateNewDesignation,  
        		  gbean.getPositionname(),
        		  gbean.getIsactive(),
        		  gbean.getPositionid()
            );
          
      }
      
      conn.commit();
      return gbean;
    } catch (Exception e) {
      dblogger.error("Error getting soc list :: ", e);
      e.printStackTrace();
    } finally {
      try {
        DbUtils.close(conn);
      } catch (SQLException e) {
        dblogger.error("Error releasing connection :: ", e);
      }
    }
    return null;
  }

public boolean checkIfNewNoticeAdded(String societyid) { 
	    Connection conn = null;
	    boolean flag= false;
	    long cnt=0;
	    try {
	      qr = new QueryRunner();
	      conn = ConnectionPoolManager.getInstance().getConnection();
	      ResultSetHandler<Object> rsh = new ScalarHandler<Object>();
	      
	      //LocalDate today = LocalDate.now();
		  //LocalDate yesterday = today.minusDays(1);
	      Date today = new Date();
	      
		  String dateStr = today + " 00:00:00";
	      
	      Object obj  = qr.query(conn, DMSQueries.checkIfNewNoticeAdded, rsh, societyid,dateStr);
	      cnt = CommomUtility.convertToLong(obj);
	      
	      System.out.println("Notice existe ::> "+cnt);
	      
	      if(cnt>0)
	      return true;
	    } catch (Exception e) {
	      dblogger.error("Error getting getDesignationById :: ", e);
	      e.printStackTrace();
	    } finally {
	      try {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        dblogger.error("Error releasing connection :: ", e);
	      }
	    }
	    return false;
	  }

public Parking saveMemberparkingDetails(Parking parking) {
    Connection conn = null;
    long gbeanId = 0L;
    try {
      qr = new QueryRunner();
      conn = ConnectionPoolManager.getInstance().getConnection();
      conn.setAutoCommit(false);
    	  
    	  ResultSetHandler<Object> rsh = new ScalarHandler<Object>();
          Object obj = qr.insert(conn, DMSQueries.saveMemberparkingDetails, rsh, 
        		  parking.getUserid(),parking.getParkingtype(),parking.getVehicletype(),parking.getParkingallotmentno(),parking.getVehicleno(),parking.getRandomHash()
            );
          gbeanId = CommomUtility.convertToLong(obj);
          parking.setUserparkingdetailsid(gbeanId); 
      conn.commit();
      return parking;
    } catch (Exception e) {
      dblogger.error("Error getting soc list :: ", e);
      e.printStackTrace();
    } finally {
      try {
        DbUtils.close(conn);
      } catch (SQLException e) {
        dblogger.error("Error releasing connection :: ", e);
      }
    }
    return null;
  }

public List<Parking> getParkingDetailsForMember(Parking parking, List<Parking> parkingList) { 
	  Connection conn = null;
	    try
	    {
	      qr = new QueryRunner();
	      conn = ConnectionPoolManager.getInstance().getConnection();
	      ResultSetHandler<List<Parking>> rsh = new BeanListHandler<Parking>(Parking.class);
	      
	      if(parking.getUserid()!=0)
	    	  parkingList = qr.query(conn, DMSQueries.getParkingDetailsForMemberByUserId, rsh,parking.getUserid());
	      else
	    	  parkingList = qr.query(conn, DMSQueries.getParkingDetailsForMemberByHashValue, rsh,parking.getRandomHash());
	      
	    } catch (Exception e) {
	      dblogger.error("Error fetching SocType List :: ", e);
	      e.printStackTrace();
	    }
	    finally
	    {
	      try
	      {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        dblogger.error("Error releasing connection :: ", e);
	      }
	    }
	    return parkingList;
	  }

	public int removeParkingData(Parking parking) {
	    Connection conn = null;
	    try {
	      qr = new QueryRunner();
	      conn = ConnectionPoolManager.getInstance().getConnection();
	      int rowsUpdated = qr.update(conn, DMSQueries.removeParkingData,parking.getUserparkingdetailsid());
	      return rowsUpdated;
	    } catch (Exception e) {
	      dblogger.error("Error getting soc list :: ", e);
	      e.printStackTrace();
	    } finally {
	      try {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        dblogger.error("Error releasing connection :: ", e);
	      }
	    }
	    return 0;
	  }

	public UserSCNominee addShareCertDetails(UserSCNominee userSCNominee) {
	    Connection conn = null;
	    long gbeanId = 0L;
	    try {
	      qr = new QueryRunner();
	      conn = ConnectionPoolManager.getInstance().getConnection();
	      conn.setAutoCommit(false);
	    	  
	    	  ResultSetHandler<Object> rsh = new ScalarHandler<Object>();
	          Object obj = qr.insert(conn, DMSQueries.addShareCertDetails, rsh, 
	        		  userSCNominee.getNominee(),
	        		  userSCNominee.getPercent(),
	        		  userSCNominee.getUserid(),
	        		  userSCNominee.getRandomHash(),
	        		  userSCNominee.getNomineerelation(),
	        		  userSCNominee.getNomineedob(),
	        		  userSCNominee.getNomineeaddress()
	            );
	          gbeanId = CommomUtility.convertToLong(obj);
	          userSCNominee.setUserscnomineeid(gbeanId);
	      conn.commit();
	      return userSCNominee;
	    } catch (Exception e) {
	      dblogger.error("Error getting soc list :: ", e);
	      e.printStackTrace();
	    } finally {
	      try {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        dblogger.error("Error releasing connection :: ", e);
	      }
	    }
	    return null;
	  }

	public List<UserSCNominee> getShareCertDetails(UserSCNominee userSCNominee, List<UserSCNominee> scList) { 
		  Connection conn = null;
		    try
		    {
		      qr = new QueryRunner();
		      conn = ConnectionPoolManager.getInstance().getConnection();
		      ResultSetHandler<List<UserSCNominee>> rsh = new BeanListHandler<UserSCNominee>(UserSCNominee.class);
		      
		      if(userSCNominee.getUserid()!=0)
		    	  scList = qr.query(conn, DMSQueries.getShareCertDetailsForMemberByUserId, rsh,userSCNominee.getUserid());
		      else
		    	  scList = qr.query(conn, DMSQueries.getShareCertDetailsForMemberByHashValue, rsh,userSCNominee.getRandomHash());
		      
		    } catch (Exception e) {
		      dblogger.error("Error fetching SocType List :: ", e);
		      e.printStackTrace();
		    }
		    finally
		    {
		      try
		      {
		        DbUtils.close(conn);
		      } catch (SQLException e) {
		        dblogger.error("Error releasing connection :: ", e);
		      }
		    }
		    return scList;
		  }

	public int removeShareCertDetails(UserSCNominee userSCNominee) {
	    Connection conn = null;
	    try {
	      qr = new QueryRunner();
	      conn = ConnectionPoolManager.getInstance().getConnection();
	      int rowsUpdated = qr.update(conn, DMSQueries.removeShareCertDetails,userSCNominee.getUserscnomineeid());
	      return rowsUpdated;
	    } catch (Exception e) {
	      dblogger.error("Error getting soc list :: ", e);
	      e.printStackTrace();
	    } finally {
	      try {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        dblogger.error("Error releasing connection :: ", e);
	      }
	    }
	    return 0;
	  }

	public List<User> getUserAutosuggestByUsername(String searchText) {
	    Connection conn = null;
	    try
	    {
	      qr = new QueryRunner();
	      conn = ConnectionPoolManager.getInstance().getConnection();
	      ResultSetHandler<List<User>> rsh = new BeanListHandler<User>(User.class);
	      String SQL = " select u.userid,u.firstName,u.lastName from user u,userprofile up where u.userid=up.userid and " + 
	        " (lower(u.firstName) like '%" + searchText.toLowerCase() + "%' or lower(u.lastName) like '%" + searchText.toLowerCase() + "%') "
	        		+ " union all "
	        		+ " select userid,firstName,concat(lastName,' (Admin)') as lastName from user where userroleid <> 0 and (lower(firstName) like "
	        		+ " '%" + searchText.toLowerCase() + "%' or lower(lastName) like '%" + searchText.toLowerCase() + "%') ";
	      return qr.query(conn,SQL,rsh);
	    } catch (Exception e) {
	      dblogger.error("Error getting soc list :: ", e);
	      e.printStackTrace();
	    } finally {
	      try {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        dblogger.error("Error releasing connection :: ", e);
	      }
	    }
	    return null;
	  }

	public int saveConfDocAccess(String userid, long createdBy) {
	    Connection conn = null;
	    try {
	      qr = new QueryRunner();
	      conn = ConnectionPoolManager.getInstance().getConnection();
	      int rowsUpdated = qr.update(conn, DMSQueries.insertConfDocAccess,userid,createdBy);
	      return rowsUpdated;
	    } catch (Exception e) {
	      dblogger.error("Error getting soc list :: ", e);
	      e.printStackTrace();
	    } finally {
	      try {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        dblogger.error("Error releasing connection :: ", e);
	      }
	    }
	    return 0;
	  }

	public List<HashMap<String, Object>> getBrochure(GenericBean gbean, List<HashMap<String, Object>> photos) {
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
	      
	      fileList =  qr.query(conn,DMSQueries.getBrochure,rsh,gbean.getGenId(),gbean.getTypeVal());
	      
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
	            if(stream!=null){
	            	byte[] bytes = IOUtils.toByteArray(stream);
		            
		            //System.out.println("Base64Utils.encode(bytes)" +  new String(Base64Utils.encode(bytes)));
		            HashMap<String, Object> hmap = new HashMap<String, Object>();
		            hmap.put("filename", file.getFilename());
		            hmap.put("documentid", file.getDocumentid());
		            hmap.put("contenttype", file.getMimetype());
		            hmap.put("createdon", file.getCreatedon());
		            hmap.put("file",  new String(Base64Utils.encode(bytes)));
		            photos.add(hmap);
	            }
	            ftp.completePendingCommand();
	          }
	           
	        }
	      } else System.out.println("No Files");
	    }
	    catch (Exception e)
	    {
	      dblogger.error("Error getCommitteMembersForSociety :: ", e);
	      e.printStackTrace();
	    }
	    finally
	    {
	      try
	      {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        dblogger.error("Error releasing connection :: ", e);
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

	public int genericRemove(GenericBean gbean) {
	    Connection conn = null;
	    int rowsUpdated=0;
	    String SQL=null;
	    try {
	      qr = new QueryRunner();
	      conn = ConnectionPoolManager.getInstance().getConnection();
	      
	      if(gbean.getTypeVal().equalsIgnoreCase("user")){
	    	  rowsUpdated = removeAdminUser(gbean);
	      }else{
	    	  SQL = "delete from "+gbean.getTypeVal()+" where "+gbean.getPkName()+" = '"+gbean.getGenId()+"'"; 
		      rowsUpdated = qr.update(conn, SQL);  
	      }
	      

	      System.out.println("Generic remove SQL: "+SQL);
	      
	      return rowsUpdated;
	    } catch (Exception e) {
	      dblogger.error("Error getting soc list :: ", e);
	      e.printStackTrace();
	    } finally {
	      try {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        dblogger.error("Error releasing connection :: ", e);
	      }
	    }
	    return 0;
	  }

	
	public int removeAdminUser(GenericBean gbean){
		Connection conn = null;
	    int rowsUpdated=0;
	    String SQL=null;
	    Userprofile userProfList=null;
	    try {
	    	  ResultSetHandler<Userprofile> rsh = new BeanHandler<Userprofile>(Userprofile.class);
		      qr = new QueryRunner();
		      conn = ConnectionPoolManager.getInstance().getConnection();
		      userProfList = qr.query(conn,DMSQueries.getUserProfile,rsh,gbean.getGenId());
		      if(userProfList!=null){
		    	  rowsUpdated = qr.update(conn, DMSQueries.editAdminRole,0,gbean.getGenId());
		      }else{
		    	  SQL = "delete from "+gbean.getTypeVal()+" where "+gbean.getPkName()+" = '"+gbean.getGenId()+"'"; 
			      rowsUpdated = qr.update(conn, SQL);
		      }
		      return rowsUpdated;
	    } catch (Exception e) {
		      dblogger.error("Error getting soc list :: ", e);
		      e.printStackTrace();
		    } finally {
		      try {
		        DbUtils.close(conn);
		      } catch (SQLException e) {
		        dblogger.error("Error releasing connection :: ", e);
		      }
		    }
		    return 0;
		
	}
	
	
	public int addTenantToHistory(User user) {
	    Connection conn = null;
	    try {
	      qr = new QueryRunner();
	      conn = ConnectionPoolManager.getInstance().getConnection();
	       
	      int rowsUpdated = qr.update(conn, DMSQueries.addTenantToHistory,user.getUserid());
	      
	      return rowsUpdated;
	    } catch (Exception e) {
	      dblogger.error("Error getting soc list :: ", e);
	      e.printStackTrace();
	    } finally {
	      try {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        dblogger.error("Error releasing connection :: ", e);
	      }
	    }
	    return 0;
	  }

	 

	public List<HashMap<String, Object>> getSocietyPolicyDocuments(long societyid, List<HashMap<String, Object>> photos) {
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
	      
	      fileList =  qr.query(conn,DMSQueries.getSocietyPolicyDocuments,rsh,societyid);
	      
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
	            if(stream!=null){
	            	byte[] bytes = IOUtils.toByteArray(stream);
		            
		            //System.out.println("Base64Utils.encode(bytes)" +  new String(Base64Utils.encode(bytes)));
		            HashMap<String, Object> hmap = new HashMap<String, Object>();
		            hmap.put("filename", file.getFilename());
		            hmap.put("documentid", file.getDocumentid());
		            hmap.put("contenttype", file.getMimetype());
		            hmap.put("createdon", file.getCreatedon());
		            hmap.put("file",  new String(Base64Utils.encode(bytes)));
		            photos.add(hmap);
	            }
	            ftp.completePendingCommand();
	          }
	           
	        }
	      } else System.out.println("No Files");
	    }
	    catch (Exception e)
	    {
	      dblogger.error("Error getCommitteMembersForSociety :: ", e);
	      e.printStackTrace();
	    }
	    finally
	    {
	      try
	      {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        dblogger.error("Error releasing connection :: ", e);
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

	public int addDeleteAuth(User user) {
	    Connection conn = null;
	    try {
	      qr = new QueryRunner();
	      conn = ConnectionPoolManager.getInstance().getConnection();
	       
	      int rowsUpdated = qr.update(conn, DMSQueries.addDeleteAuth,user.getUserid());
	      
	      return rowsUpdated;
	    } catch (Exception e) {
	      dblogger.error("Error getting soc list :: ", e);
	      e.printStackTrace();
	    } finally {
	      try {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        dblogger.error("Error releasing connection :: ", e);
	      }
	    }
	    return 0;
	  }

	public int removeDeleteAuth(User user) {
	    Connection conn = null;
	    try {
	      qr = new QueryRunner();
	      conn = ConnectionPoolManager.getInstance().getConnection();
	       
	      int rowsUpdated = qr.update(conn, DMSQueries.removeDeleteAuth,user.getUserid());
	      
	      return rowsUpdated;
	    } catch (Exception e) {
	      dblogger.error("Error getting soc list :: ", e);
	      e.printStackTrace();
	    } finally {
	      try {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        dblogger.error("Error releasing connection :: ", e);
	      }
	    }
	    return 0;
	  }

	public Builder getBuilderBySocietyId(Society society) {
	    Connection conn = null;
	    try
	    {
	      qr = new QueryRunner();
	      conn = ConnectionPoolManager.getInstance().getConnection();
	      ResultSetHandler<Builder> rsh = new BeanHandler<Builder>(Builder.class);
	      return qr.query(conn,DMSQueries.getBuilderBySocietyId,rsh,society.getSocietyid());
	    } catch (Exception e) {
	      dblogger.error("Error getting soc list :: ", e);
	      e.printStackTrace();
	    } finally {
	      try {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        dblogger.error("Error releasing connection :: ", e);
	      }
	    }
	    return null;
	  }

	public Project getProjectBySocietyId(Society society) {
	    Connection conn = null;
	    try
	    {
	      qr = new QueryRunner();
	      conn = ConnectionPoolManager.getInstance().getConnection();
	      ResultSetHandler<Project> rsh = new BeanHandler<Project>(Project.class);
	      return qr.query(conn,DMSQueries.getProjectBySocietyId,rsh,society.getSocietyid());
	    } catch (Exception e) {
	      dblogger.error("Error getting soc list :: ", e);
	      e.printStackTrace();
	    } finally {
	      try {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        dblogger.error("Error releasing connection :: ", e);
	      }
	    }
	    return null;
	  }

	public List<Builder> getBuilderList(List<Builder> builderList) {
	    Connection conn = null;
	    try
	    {
	      qr = new QueryRunner();
	      conn = ConnectionPoolManager.getInstance().getConnection();
	      ResultSetHandler<List<Builder>> rsh = new BeanListHandler<Builder>(Builder.class);
	      builderList = qr.query(conn, DMSQueries.getAllActiveBuilders, rsh);
	    } catch (Exception e) {
	      dblogger.error("Error fetching Society List :: ", e);
	      e.printStackTrace();
	    }
	    finally
	    {
	      try
	      {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        dblogger.error("Error releasing connection :: ", e);
	      }
	    }
	    return builderList;
	  }

	public List<BuilderManager> getManagersForBuilder(Builder builder, List<BuilderManager> managers) {
	    Connection conn = null;
	    try
	    {
	      qr = new QueryRunner();
	      conn = ConnectionPoolManager.getInstance().getConnection();
	      ResultSetHandler<List<BuilderManager>> rsh = new BeanListHandler<BuilderManager>(BuilderManager.class);
	      managers = qr.query(conn, DMSQueries.getAllBuildermanagersByBuilderId, rsh,builder.getBuilderid());
	    } catch (Exception e) {
	      dblogger.error("Error fetching Society List :: ", e);
	      e.printStackTrace();
	    }
	    finally
	    {
	      try
	      {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        dblogger.error("Error releasing connection :: ", e);
	      }
	    }
	    return managers;
	  }

	public int removeBuilderManagerByid(BuilderManager manager) {
	    Connection conn = null;
	    try {
	      qr = new QueryRunner();
	      conn = ConnectionPoolManager.getInstance().getConnection();
	      int rowsUpdated = qr.update(conn, DMSQueries.removeBuilderManagerByid, 
	    		  manager.getBuildermanagermappingid());
	      return rowsUpdated;
	    } catch (Exception e) {
	      dblogger.error("Error removeBuilderManagerByid :: ", e);
	      e.printStackTrace();
	    } finally {
	      try {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        dblogger.error("Error releasing connection :: ", e);
	      }
	    }
	    return 0;
	  }

	public BuilderManager addBuilderManager(BuilderManager manager) {
	    Connection conn = null;
	    
	    long societyManagerId = 0L;
	    try {
	      qr = new QueryRunner();
	      conn = ConnectionPoolManager.getInstance().getConnection();
	      ResultSetHandler<Object> rsh = new ScalarHandler<Object>();
	      Object obj = qr.insert(conn, DMSQueries.addBuilderManager, rsh, 
		        Long.valueOf(manager.getBuilderid()),
		        Long.valueOf(manager.getUserid())
	        );
	      societyManagerId = CommomUtility.convertToLong(obj);
	      manager.setBuildermanagermappingid(societyManagerId);
	      return manager;
	    } catch (Exception e) {
	      dblogger.error("Error getting soc list :: ", e);
	      e.printStackTrace();
	    } finally {
	      try {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        dblogger.error("Error releasing connection :: ", e);
	      }
	    }
	    return null;
	  }

	public List<BuilderManager> getBuilderListByManagerid(long userid, List<BuilderManager> buildermanagerList) {
	    Connection conn = null;
	    try
	    {
	      qr = new QueryRunner();
	      conn = ConnectionPoolManager.getInstance().getConnection();
	      ResultSetHandler<List<BuilderManager>> rsh = new BeanListHandler<BuilderManager>(BuilderManager.class);
	      buildermanagerList = qr.query(conn, DMSQueries.getBuilderListByManagerid, rsh,userid);
	    } catch (Exception e) {
	      dblogger.error("Error fetching Society List :: ", e);
	      e.printStackTrace();
	    }
	    finally
	    {
	      try
	      {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        dblogger.error("Error releasing connection :: ", e);
	      }
	    }
	    return buildermanagerList;
	  }

	public List<Actionlogger> getUserActivityLog(User user, List<Actionlogger> actions) {
	    Connection conn = null;
	    try
	    {
	      qr = new QueryRunner();
	      conn = ConnectionPoolManager.getInstance().getConnection();
	      ResultSetHandler<List<Actionlogger>> rsh = new BeanListHandler<Actionlogger>(Actionlogger.class);
	      
	      String SQL = "select actionloggerid,userid,action,payload,actiondate,getUsername(userid) as username from actionlogger where actiontype='view' and userid="+user.getUserid();
	      
	      if(user.getFirstName()!=null){
	    	  //System.out.println(user.getFirstName());
	    	  SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
	    	  SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd"); 
	    	  Date strDate= formatter.parse(user.getFirstName());
	    	  
	    	  SQL += " and date(actiondate) = '"+formatter1.format(strDate)+"'";
	      }
	    	  
	      SQL += " order by actiondate desc";
	      
	      //System.out.println("UserActivityLog "+SQL);
	      
	      actions = qr.query(conn,SQL,rsh);
	      
	    } catch (Exception e) {
	      dblogger.error("Error fetching UserActivityLog :: ", e);
	      e.printStackTrace();
	    }
	    finally
	    {
	      try
	      {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        dblogger.error("Error releasing connection :: ", e);
	      }
	    }
	    return actions;
	  }

	
	public List<Loginhistory> getUserLoginHistory(User user, List<Loginhistory> actions) {
	    Connection conn = null;
	    try
	    {
	      qr = new QueryRunner();
	      conn = ConnectionPoolManager.getInstance().getConnection();
	      ResultSetHandler<List<Loginhistory>> rsh = new BeanListHandler<Loginhistory>(Loginhistory.class);
	      
	      String SQL = "select loginhistoryid,userid,logintime,logouttime,ipaddress,getUsername(userid) as username from loginhistory where  userid="+user.getUserid();
	      
	      if(user.getFirstName()!=null){
	    	  //System.out.println(user.getFirstName());
	    	  SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
	    	  SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd"); 
	    	  Date strDate= formatter.parse(user.getFirstName());
	    	  
	    	  SQL += " and date(logintime) = '"+formatter1.format(strDate)+"'";
	      }
	    	  
	      SQL += " order by logintime desc";
	      
	      //System.out.println("UserActivityLog "+SQL);
	      
	      actions = qr.query(conn,SQL,rsh);
	      
	    } catch (Exception e) {
	      dblogger.error("Error fetching UserActivityLog :: ", e);
	      e.printStackTrace();
	    }
	    finally
	    {
	      try
	      {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        dblogger.error("Error releasing connection :: ", e);
	      }
	    }
	    return actions;
	  }

	public FileMonitoring getFileStats(FileMonitoring fileMonitoring) {
	    Connection conn = null;
	    try
	    {
	      qr = new QueryRunner();
	      conn = ConnectionPoolManager.getInstance().getConnection();
	      ResultSetHandler<FileMonitoring> q1rsh = new BeanHandler<FileMonitoring>(FileMonitoring.class);
	      ResultSetHandler<List<FileMonitoring>> rsh = new BeanListHandler<FileMonitoring>(FileMonitoring.class);
	      
	      FileMonitoring fm1 = null;
	      FileMonitoring fm2 = null;
	      
	      String SQL = "select soc.societyid from ";
	      
	      if(fileMonitoring.getSocietyid()!=0)
	    	  SQL += " society soc where soc.societyid="+fileMonitoring.getSocietyid();
	      else if(fileMonitoring.getProjectid()!=0)
	    	  SQL += " society soc,project proj where soc.projectid=proj.projectid and proj.projectid="+fileMonitoring.getProjectid(); 
	      else if(fileMonitoring.getBuilderid()!=0)
	    	  SQL+= " society soc,project proj,builder bul where soc.projectid=proj.projectid "
	    	  		+ " and proj.builderid=bul.builderid and bul.builderid="+fileMonitoring.getBuilderid();
	      else
	    	  SQL += " society soc";
	      
	      
	      String SQL1 = " select GROUP_CONCAT(documentid) as docstring,count(documentid) as documentcount from document doc where doc.documentid is not null ";
	      
	      if(fileMonitoring.getDoctypeid()!=0)
	    	  SQL1+=" and doc.doctypeid="+fileMonitoring.getDoctypeid();
	      if(fileMonitoring.getDocsubtypeid()!=0)
	    	  SQL1+=" and doc.docsubtypeid="+fileMonitoring.getDocsubtypeid();
	      
	      SQL1 += " and doc.societyid in ("+SQL+") "; 
	    	  
	      if(fileMonitoring.getUserid()!=0)
	    	  SQL1+= " and doc.userid="+fileMonitoring.getUserid();
	      
	      
	      fm1 = qr.query(conn,SQL1,q1rsh);
	      
	      if(fm1!=null){
	    	  
	    	  String SQL2 = " select count(filesid) as pagecount,sum(filesize) as size from files where documentid in ("+ fm1.getDocstring() +")";
	    	  fm2 = qr.query(conn,SQL2,q1rsh);
	    	  
	    	  if(fm2!=null){
	    		  fm2.setDocumentcount(fm1.getDocumentcount());
	    		  return fm2;
	    	  }
	      }
	      
	    } catch (Exception e) {
	      dblogger.error("Error fetching getFileStats :: ", e);
	      e.printStackTrace();
	    }
	    finally
	    {
	      try
	      {
	        DbUtils.close(conn);
	      } catch (SQLException e) {
	        dblogger.error("Error releasing connection :: ", e);
	      }
	    }
	    return null;
	  }
	 
}