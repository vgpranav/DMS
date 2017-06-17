package com.dms.dao;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dms.beans.DocSubType;
import com.dms.beans.Doctype;
import com.dms.beans.Document;
import com.dms.beans.Documentdetails;
import com.dms.beans.EmailBean;
import com.dms.beans.Files;
import com.dms.beans.FormFields;
import com.dms.beans.GenericBean;
import com.dms.beans.Photos;
import com.dms.beans.Project;
import com.dms.beans.Society;
import com.dms.beans.User;
import com.dms.beans.Userprofile;
import com.dms.util.CommomUtility;
import com.dms.util.ConnectionPoolManager;
import com.dms.util.DMSQueries;
import com.dms.util.FtpWrapper;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class DocumentDao
{
  private static final Logger logger = LoggerFactory.getLogger(DocumentDao.class);
  
  public DocumentDao() {}
  
  public List<Doctype> getAllDocumentTypes(List<Doctype> docTypes) { Connection conn = null;
    try
    {
      qr = new QueryRunner();
      conn = ConnectionPoolManager.getInstance().getConnection();
      ResultSetHandler<List<Doctype>> rsh = new BeanListHandler(Doctype.class);
      docTypes = (List)qr.query(conn, DMSQueries.getAllDocumentTypes, rsh);
    } catch (Exception e) {
      logger.error("Error getAllDocumentTypes :: " + e.getMessage());
      e.printStackTrace();
      try
      {
        DbUtils.close(conn);
      } catch (SQLException ex) {
        logger.error("Error releasing connection :: " + ex.getMessage());
      }
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
  public Doctype insertOrUpdateDocType(Doctype doctype) { 
	
	Connection conn = null;
    
    long doctypeId = 0L;
    try {
      qr = new QueryRunner();
      conn = ConnectionPoolManager.getInstance().getConnection();
      ResultSetHandler<Object> rsh = new ScalarHandler<Object>();
      
      if(doctype.getDoctypeid()==0){
    	  Object obj = qr.insert(conn, DMSQueries.insertNewDoctype, rsh, new Object[] {
    		        doctype.getDoctypename(), 
    		        doctype.getDoctypedesc(), 
    		        Integer.valueOf(doctype.getActive()), 
    		        doctype.getCreatedby()});
    		      doctypeId = CommomUtility.convertToLong(obj);
    		      doctype.setDoctypeid(doctypeId);
      }else{
    	  qr.insert(conn, DMSQueries.updateDoctype, rsh, new Object[] {
  		        doctype.getDoctypename(), 
  		        doctype.getDoctypedesc(), 
  		        Integer.valueOf(doctype.getActive()), 
  		        doctype.getDoctypeid() });
      }
    }
    catch (Exception e) {
      logger.error("Error Saving Doctype :: " + e.getMessage());
      e.printStackTrace();
      try
      {
        DbUtils.close(conn);
      } catch (SQLException ex) {
        logger.error("Error releasing connection :: " + ex.getMessage());
      }
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
    return doctype;
  }
  
  public List<DocSubType> getAllDocumentSubTypes(List<DocSubType> docSubType) {
    Connection conn = null;
    try
    {
      qr = new QueryRunner();
      conn = ConnectionPoolManager.getInstance().getConnection();
      ResultSetHandler<List<DocSubType>> rsh = new BeanListHandler<DocSubType>(DocSubType.class);
      docSubType = (List<DocSubType>)qr.query(conn, DMSQueries.getAllDocumentSubTypes, rsh);
    } catch (Exception e) {
      logger.error("Error authenticating user :: " + e.getMessage());
      e.printStackTrace();
      try
      {
        DbUtils.close(conn);
      } catch (SQLException ex) {
        logger.error("Error releasing connection :: " + ex.getMessage());
      }
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
    return docSubType;
  }
  
  public DocSubType insertOrUpdateDocSubType(DocSubType docSubType) {
    Connection conn = null;
    
    long docsubtypeId = 0L;
    try {
      qr = new QueryRunner();
      conn = ConnectionPoolManager.getInstance().getConnection();
      ResultSetHandler<Object> rsh = new ScalarHandler<Object>();
      
      //System.out.println("docSubType :: "+docSubType);
      
      if(docSubType.getDocsubtypeid()==0){
    	  Object obj = qr.insert(conn, DMSQueries.insertNewDocSubtype, rsh, new Object[] {
    			  Long.valueOf(docSubType.getDoctypeid()), 
    			  docSubType.getDocsubtypename(), 
    			  docSubType.getDocsubtypedesc(), 
    			  docSubType.getCreatedby(), 
    			  Integer.valueOf(docSubType.getActive()),
    			  docSubType.getDisplayflag()});
    	  
    	  docsubtypeId = CommomUtility.convertToLong(obj);
    	  docSubType.setDoctypeid(docsubtypeId);
      } else{
    	  		qr.update(conn, DMSQueries.updateDocSubtype,
    			  docSubType.getDoctypeid(),
    			  docSubType.getDocsubtypename(),
    			  docSubType.getDocsubtypedesc(),
    			  docSubType.getActive(),
    			  docSubType.getDisplayflag(),
    			  docSubType.getDocsubtypeid()
    			  );
      }
      
    } catch (Exception e) {
      logger.error("Error Saving Doctype :: " + e.getMessage());
      e.printStackTrace();
      try
      {
        DbUtils.close(conn);
      } catch (SQLException ex) {
        logger.error("Error releasing connection :: " + ex.getMessage());
      }
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
    return docSubType;
  }
  
  public FormFields insertOrUpdateFormFields(FormFields formFields) {
    Connection conn = null;
    
    long fieldid = 0L;
    try {
      qr = new QueryRunner();
      conn = ConnectionPoolManager.getInstance().getConnection();
      ResultSetHandler<Object> rsh = new ScalarHandler<Object>();
      
      if(formFields.getFieldid()==0){
    	  Object obj = qr.insert(conn, DMSQueries.insertNewFormField, rsh, new Object[] {
          Long.valueOf(formFields.getDocsubtypeid()), 
    	  formFields.getFieldname(), 
    	  formFields.getFieldtype(), 
    	  formFields.getDatatype(), 
    	  Integer.valueOf(formFields.getSequence()), 
    	  Integer.valueOf(formFields.getActive()), 
    	  formFields.getCreatedby() });
    	  fieldid = CommomUtility.convertToLong(obj);
    	  formFields.setFieldid(fieldid);
      }else{
    	  qr.update(conn, DMSQueries.updateFormFieldData,
    			  formFields.getFieldname(),
    			  formFields.getFieldid(),
    			  formFields.getDatatype(),
    			  formFields.getSequence(),
    			  formFields.getActive(),
    			  formFields.getDocsubtypeid(),
    			  formFields.getFieldid()
    			  );
      }
       
      	
    } catch (Exception e) {
      logger.error("Error Saving Doctype :: " + e.getMessage());
      e.printStackTrace();
      try
      {
        DbUtils.close(conn);
      } catch (SQLException ex) {
        logger.error("Error releasing connection :: " + ex.getMessage());
      }
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
    return formFields;
  }
  
  public List<FormFields> getFieldsForDocSubtype(long docSubtypeid, List<FormFields> formFields) {
    Connection conn = null;
    try
    {
      qr = new QueryRunner();
      conn = ConnectionPoolManager.getInstance().getConnection();
      ResultSetHandler<List<FormFields>> rsh = new BeanListHandler<FormFields>(FormFields.class);
      formFields = (List<FormFields>)qr.query(conn, DMSQueries.getAllDocumentFormFieldsBySubTypes, rsh, new Object[] { Long.valueOf(docSubtypeid) });
    } catch (Exception e) {
      logger.error("Error fetching form fields :: " + e.getMessage());
      e.printStackTrace();
      try
      {
        DbUtils.close(conn);
      } catch (SQLException ex) {
        logger.error("Error releasing connection :: " + ex.getMessage());
      }
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
    
    return formFields;
  }
  
  public List<Doctype> getDoctypeBySocId(long societyid, List<Doctype> doctypes) {
    Connection conn = null;
    try
    {
      qr = new QueryRunner();
      conn = ConnectionPoolManager.getInstance().getConnection();
      ResultSetHandler<List<Doctype>> rsh = new BeanListHandler(Doctype.class);
      doctypes = (List)qr.query(conn, DMSQueries.getAllDoctypeFromSocid, rsh, new Object[] { Long.valueOf(societyid) });
    } catch (Exception e) {
      logger.error("Error getting doctypes by soc id :: " + e.getMessage());
      e.printStackTrace();
      try
      {
        DbUtils.close(conn);
      } catch (SQLException ex) {
        logger.error("Error releasing connection :: " + ex.getMessage());
      }
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
    return doctypes;
  }
  
  public List<DocSubType> getDocSubtypeByDocId(long doctypeid, List<DocSubType> docSubTypes) {
    Connection conn = null;
    try
    {
      qr = new QueryRunner();
      conn = ConnectionPoolManager.getInstance().getConnection();
      ResultSetHandler<List<DocSubType>> rsh = new BeanListHandler(DocSubType.class);
      docSubTypes = (List)qr.query(conn, DMSQueries.getAllDocSubTypeFromDocid, rsh, new Object[] { Long.valueOf(doctypeid) });
    } catch (Exception e) {
      logger.error("Error getting doctypes by soc id :: " + e.getMessage());
      e.printStackTrace();
      try
      {
        DbUtils.close(conn);
      } catch (SQLException ex) {
        logger.error("Error releasing connection :: " + ex.getMessage());
      }
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
    return docSubTypes;
  }
  
  public long saveDocumentHeadAndDetails(Map<String, String> params, User user) {
    Connection conn = null;
    String societyid = "";
    String doctypeid = "";
    String docsubtypeid = "";
    String userid = "";
    long documentId = 0L;
    try
    {
      if (params.containsKey("societyid"))
        societyid = (String)params.get("societyid");
      if (params.containsKey("doctypeid"))
        doctypeid = (String)params.get("doctypeid");
      if (params.containsKey("docsubtypeid")) {
          docsubtypeid = (String)params.get("docsubtypeid");
        }
      if (params.containsKey("userid")) {
    	  userid = (String)params.get("userid");
        }
      
      
      if ((societyid.length() < 1) || (doctypeid.length() < 1) || (docsubtypeid.length() < 1) || (userid.length() < 1)) {
        return 0L;
      }
      qr = new QueryRunner();
      conn = ConnectionPoolManager.getInstance().getConnection();
      conn.setAutoCommit(false);
      
      Object obj = qr.insert(conn, DMSQueries.insertDocHead, new ScalarHandler<Object>(), new Object[] {
        societyid, 
        doctypeid, 
        docsubtypeid, 
        user.getUserid(),
        userid});
      
      documentId = CommomUtility.convertToLong(obj);
      
      if (documentId != 0L) {
        for (String key : params.keySet()) {
          if (!key.equals("UserId")) {
            qr.insert(conn, DMSQueries.insertDocDetails, new ScalarHandler<Object>(), new Object[] {
              Long.valueOf(documentId), 
              key, 
              params.get(key), 
              user.getUserid() });
          }
        }
        conn.commit();
      }
      else {
        conn.rollback();
      }
    } catch (Exception e) {
      logger.error("Error saving doctypes :: " + e.getMessage());
      e.printStackTrace();
      try
      {
        DbUtils.close(conn);
      } catch (SQLException ex) {
        logger.error("Error releasing connection :: " + ex.getMessage());
      }
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
    return documentId;
  }
  
  public List<Document> getDocumentListForView(Document document, List<Document> documents) {
    Connection conn = null;
    try
    {
      qr = new QueryRunner();
      conn = ConnectionPoolManager.getInstance().getConnection();
      ResultSetHandler<List<Document>> rsh = new BeanListHandler<Document>(Document.class);
      
      
      String getDocumentListForView = " select GROUP_CONCAT(concat(f.fieldname,' - ',d.datavalue) SEPARATOR  ',' )  as description,d.documentid, "
      		+ " dt.doctypename,dst.docsubtypename,d.createdby,d.createdon "
      		+ " from dms.formstructure f,dms.documentdetails d,dms.document doc,dms.doctype dt,dms.docsubtype dst "
      		+ " where f.fieldid = d.datakey and d.documentid = doc.documentid and doc.doctypeid=dt.doctypeid "
      		+ " and doc.docsubtypeid=dst.docsubtypeid "
      		+ " and doc.societyid=? ";
      					
      if(document.getDoctypeid()!=0)
    	  getDocumentListForView = getDocumentListForView + " and doc.doctypeid= "+document.getDoctypeid();
      
      if(document.getUserid()!=0)
    	  getDocumentListForView = getDocumentListForView + " and doc.userid= "+document.getUserid();
      
      if(document.getDocsubtypeid()!=0)
    	  getDocumentListForView = getDocumentListForView + " and doc.docsubtypeid= "+document.getDocsubtypeid();
      
      getDocumentListForView= getDocumentListForView + " group by d.documentid order by f.sequence ";
      
      System.out.println("getDocumentListForView :: "+getDocumentListForView);
      
      documents = qr.query(conn,getDocumentListForView, rsh, document.getSocietyid());
      
    }
    catch (Exception e) {
      logger.error("Error getDocumentListForView :: " + e.getMessage());
      e.printStackTrace();
      try
      {
        DbUtils.close(conn);
      } catch (SQLException ex) {
        logger.error("Error releasing connection :: " + ex.getMessage());
      }
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
    return documents;
  }
  
  public List<Files> getDocPathsByDocId(String documentId, List<Files> docs) {
    Connection conn = null;
    try
    {
      qr = new QueryRunner();
      conn = ConnectionPoolManager.getInstance().getConnection();
      ResultSetHandler<List<Files>> rsh = new BeanListHandler(Files.class);
      docs = (List)qr.query(conn, DMSQueries.getDocPathsByDocId, rsh, new Object[] {
        documentId });
    }
    catch (Exception e) {
      logger.error("Error getDocumentListForView :: " + e.getMessage());
      e.printStackTrace();
      try
      {
        DbUtils.close(conn);
      } catch (SQLException ex) {
        logger.error("Error releasing connection :: " + ex.getMessage());
      }
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
    return docs;
  }
  
  public long savePhotoInfo(String phototype, int docid, String docname, String docpath, String contentType,boolean keepOld) {
    Connection conn = null;
    
    long fieldid = 0L;
    try {
      qr = new QueryRunner();
      conn = ConnectionPoolManager.getInstance().getConnection();
      ResultSetHandler<Object> rsh = new ScalarHandler();
      
      conn.setAutoCommit(false);
      
      if(!keepOld)
    	  qr.update(conn,DMSQueries.deactOldPhotos,docid,phototype);
      
      Object obj = qr.insert(conn, DMSQueries.insertPhotoInfo, rsh, new Object[] {
        phototype, 
        Integer.valueOf(docid), 
        docpath, 
        docname, 
        contentType });
      
      conn.commit();

      fieldid = CommomUtility.convertToLong(obj);
    } catch (Exception e) {
      logger.error("Error Saving Doctype :: " + e.getMessage());
      e.printStackTrace();
      try
      {
        DbUtils.close(conn);
      } catch (SQLException ex) {
        logger.error("Error releasing connection :: " + ex.getMessage());
      }
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
    return fieldid;
  }

public List<Documentdetails> getExistingDocumentDetails(Document document, List<Documentdetails> documentdetails) {
    Connection conn = null;

    try {
      qr = new QueryRunner();
      conn = ConnectionPoolManager.getInstance().getConnection();
      ResultSetHandler<List<Documentdetails>> rsh = new BeanListHandler<Documentdetails>(Documentdetails.class);
      
      documentdetails = qr.query(conn,DMSQueries.getExistingDocDetails,rsh,
    		  	document.getSocietyid(),
    		  	document.getDoctypeid(),
    		  	document.getDocsubtypeid(),
    		  	document.getUserid()
    		  );
      
      //System.out.println("documentdetails :: "+documentdetails);
      
      return documentdetails;
    } catch (Exception e) {
      logger.error("Error Saving Doctype :: " + e.getMessage());
      e.printStackTrace();
      try
      {
        DbUtils.close(conn);
      } catch (SQLException ex) {
        logger.error("Error releasing connection :: " + ex.getMessage());
      }
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

public GenericBean insertSocDocMapping(GenericBean bean) { 
	
	Connection conn = null;
    
    long doctypeId = 0L;
    try {
      qr = new QueryRunner();
      conn = ConnectionPoolManager.getInstance().getConnection();
      ResultSetHandler<Object> rsh = new ScalarHandler<Object>();

      Object obj = qr.insert(conn, DMSQueries.insertSocDocMapping, rsh,  
    		  		bean.getDoctypeid(),
    		  		bean.getSocietyid(),
    		        bean.getCreatedby());
      
      
      doctypeId = CommomUtility.convertToLong(obj);
      bean.setSocietydocmappingid(doctypeId);
       
    }
    catch (Exception e) {
      logger.error("Error Saving Doctype :: " + e.getMessage());
      e.printStackTrace();
      try
      {
        DbUtils.close(conn);
      } catch (SQLException ex) {
        logger.error("Error releasing connection :: " + ex.getMessage());
      }
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
    return bean;
  }

public List<DocSubType> getDocStubtypesToDispay(List<DocSubType> docSubType,String societyid) {
    Connection conn = null;

    try {
      qr = new QueryRunner();
      conn = ConnectionPoolManager.getInstance().getConnection();
      ResultSetHandler<List<DocSubType>> rsh = new BeanListHandler<DocSubType>(DocSubType.class);
      
      docSubType = qr.query(conn,DMSQueries.getDocStubtypesToDispay,rsh,societyid);
      
      return docSubType;
    } catch (Exception e) {
      logger.error("Error Saving Doctype :: " + e.getMessage());
      e.printStackTrace();
      try
      {
        DbUtils.close(conn);
      } catch (SQLException ex) {
        logger.error("Error releasing connection :: " + ex.getMessage());
      }
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

public List<GenericBean> getdisplayData(String doctypeid, String userid, List<GenericBean> data) {
    Connection conn = null;

    try {
      qr = new QueryRunner();
      conn = ConnectionPoolManager.getInstance().getConnection();
      ResultSetHandler<List<GenericBean>> rsh = new BeanListHandler<GenericBean>(GenericBean.class);
      
      data = qr.query(conn,DMSQueries.getDocumentDatabyDoctypeIdUserId,rsh,doctypeid,userid);
      
      return data;
    } catch (Exception e) {
      logger.error("Error Saving Doctype :: " + e.getMessage());
      e.printStackTrace();
      try
      {
        DbUtils.close(conn);
      } catch (SQLException ex) {
        logger.error("Error releasing connection :: " + ex.getMessage());
      }
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


public List<GenericBean> getdisplayDataByDocId(String documentid, List<GenericBean> data) {
    Connection conn = null;

    try {
      qr = new QueryRunner();
      conn = ConnectionPoolManager.getInstance().getConnection();
      ResultSetHandler<List<GenericBean>> rsh = new BeanListHandler<GenericBean>(GenericBean.class);
      
      data = qr.query(conn,DMSQueries.getDocumentDatabyDocId,rsh,documentid);
      
      return data;
    } catch (Exception e) {
      logger.error("Error Saving Doctype :: " + e.getMessage());
      e.printStackTrace();
      try
      {
        DbUtils.close(conn);
      } catch (SQLException ex) {
        logger.error("Error releasing connection :: " + ex.getMessage());
      }
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

public int deleteDocById(Document document) {
    Connection conn = null;
    try {
      qr = new QueryRunner();
      conn = ConnectionPoolManager.getInstance().getConnection();
      int rowsUpdated = qr.update(conn, DMSQueries.deleteDocumentByDocId,document.getDocumentid());
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

 public int deleteDocumentPage(Files files) {
    Connection conn = null;
    try {
      qr = new QueryRunner();
      conn = ConnectionPoolManager.getInstance().getConnection();
      int rowsUpdated = qr.update(conn, DMSQueries.deleteDocumentPage,files.getFilesid());
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

 	public List<GenericBean> getConfDocAccessList(List<GenericBean> formFields) {
    Connection conn = null;
    try
    {
      qr = new QueryRunner();
      conn = ConnectionPoolManager.getInstance().getConnection();
      ResultSetHandler<List<GenericBean>> rsh = new BeanListHandler<GenericBean>(GenericBean.class);
      formFields = (List<GenericBean>)qr.query(conn, DMSQueries.getAllConfDocAccessList,rsh);
    } catch (Exception e) {
      logger.error("Error fetching form fields :: " + e.getMessage());
      e.printStackTrace();
      try
      {
        DbUtils.close(conn);
      } catch (SQLException ex) {
        logger.error("Error releasing connection :: " + ex.getMessage());
      }
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
    
    return formFields;
  }

	public List<Project> getProjectsByBuilderId(long builderid, List<Project> docSubTypes) {
	    Connection conn = null;
	    try
	    {
	      qr = new QueryRunner();
	      conn = ConnectionPoolManager.getInstance().getConnection();
	      ResultSetHandler<List<Project>> rsh = new BeanListHandler<Project>(Project.class);
	      docSubTypes =  qr.query(conn, DMSQueries.getProjectsByBuilderId, rsh,builderid);
	    } catch (Exception e) {
	      logger.error("Error getting doctypes by soc id :: " + e.getMessage());
	      e.printStackTrace();
	      try
	      {
	        DbUtils.close(conn);
	      } catch (SQLException ex) {
	        logger.error("Error releasing connection :: " + ex.getMessage());
	      }
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
	    return docSubTypes;
	  }

	public List<Society> getSubProjectsByProjectId(long projectid, List<Society> docSubTypes) {
	    Connection conn = null;
	    try
	    {
	      qr = new QueryRunner();
	      conn = ConnectionPoolManager.getInstance().getConnection();
	      ResultSetHandler<List<Society>> rsh = new BeanListHandler<Society>(Society.class);
	      docSubTypes =  qr.query(conn, DMSQueries.getSubProjectsByProjectId,rsh,projectid);
	    } catch (Exception e) {
	      logger.error("Error getting doctypes by soc id :: " + e.getMessage());
	      e.printStackTrace();
	      try
	      {
	        DbUtils.close(conn);
	      } catch (SQLException ex) {
	        logger.error("Error releasing connection :: " + ex.getMessage());
	      }
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
	    return docSubTypes;
	  }

	public List<Userprofile> getTenantHistory(long userid, List<Userprofile> docSubTypes) {
	    Connection conn = null;
	    try
	    {
	      qr = new QueryRunner();
	      conn = ConnectionPoolManager.getInstance().getConnection();
	      ResultSetHandler<List<Userprofile>> rsh = new BeanListHandler<Userprofile>(Userprofile.class);
	      docSubTypes =  qr.query(conn, DMSQueries.getTenantHistory,rsh,userid);
	    } catch (Exception e) {
	      logger.error("Error getting doctypes by soc id :: " + e.getMessage());
	      e.printStackTrace();
	      try
	      {
	        DbUtils.close(conn);
	      } catch (SQLException ex) {
	        logger.error("Error releasing connection :: " + ex.getMessage());
	      }
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
	    return docSubTypes;
	  }

	public byte[] getPhotoFromFTP(long docId, String photoType ,String Path, boolean isThumb) {
	    Connection conn = null;
	    List<Photos> photoBeans = null;
	    FtpWrapper ftp = new FtpWrapper();
	    String hostDomain = ftp.getServerName();
	    String Id = ftp.getUsername();
	    String Password = ftp.getPassword();
	    byte[] bytes=null;
	    try { 
	    	
	      qr = new QueryRunner();
	      conn = ConnectionPoolManager.getInstance().getConnection();
	      ResultSetHandler<List<Photos>> rsh = new BeanListHandler<Photos>(Photos.class);
	      
	      photoBeans =  qr.query(conn,DMSQueries.getOnePhotoInfo,rsh,docId,photoType);
	      
	      if (photoBeans.size() > 0) {
	        if (ftp.connectAndLogin(hostDomain, Id, Password))
	        {
	          ftp.setPassiveMode(true);
	          ftp.binary();
	          ftp.setBufferSize(1024000);
	          ftp.changeWorkingDirectory("DMS/"+Path+"/");
	          
	          for (Photos photo : photoBeans)
	          {
	        	String FileName = photo.getDocname();
	        	
	        	if(isThumb){
	        		String  NewFileName = FileName.substring(0, FileName.lastIndexOf("."))+"_thumbs"+FileName.substring(FileName.lastIndexOf("."));
	        		InputStream stream = ftp.retrieveFileStream(NewFileName);
	        		
	        		if(stream!=null)
		            bytes = IOUtils.toByteArray(stream);
		            //ftp.completePendingCommand();
		            
		            if(bytes==null){
		            	
		            	 InputStream stream1 = ftp.retrieveFileStream(FileName);
		            	 BufferedImage bImageFromConvert = ImageIO.read(stream1);
		            	 //compressImage(
			   			 byte[] bufferThumb = resizeImageWithHint(bImageFromConvert,1,50,50);
			   			 ftp.storeFile(NewFileName,new ByteArrayInputStream(bufferThumb));
				         ftp.sendSiteCommand("chmod 777 " + NewFileName);
			   			 
				         return bufferThumb;
		            }
	        		
	        	}else{
	        		
	        		InputStream stream = ftp.retrieveFileStream(FileName);
		            bytes = IOUtils.toByteArray(stream);
		            ftp.completePendingCommand();
	        	}
	            
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
	    return bytes;
	  }
 
	
	
	 private byte[] compressImage(BufferedImage image,float compression){
		 byte[] img = null;

		 try{
			 
			 ByteArrayOutputStream compressed = new ByteArrayOutputStream();
			 ImageOutputStream outputStream = ImageIO.createImageOutputStream(compressed);
			 ImageWriter jpgWriter = ImageIO.getImageWritersByFormatName("jpg").next();
			 ImageWriteParam jpgWriteParam = jpgWriter.getDefaultWriteParam();
			 jpgWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
			 jpgWriteParam.setCompressionQuality(compression);
			 jpgWriter.setOutput(outputStream);
			 jpgWriter.write(null, new IIOImage(image, null, null), jpgWriteParam);
			 jpgWriter.dispose();
			 img = compressed.toByteArray();
			 
		 }catch(Exception e ){
			 e.printStackTrace();
		 }
		 
		return img;
	 }
	
	 private byte[] resizeImageWithHint(BufferedImage originalImage, int type,int width,int height)
	  {
	    BufferedImage resizedImage = new BufferedImage(width, height, type);
	    Graphics2D g = resizedImage.createGraphics();
	    g.drawImage(originalImage, 0, 0, 50, 50, null);
	    g.dispose();
	    g.setComposite(AlphaComposite.Src);
	    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, 
	      RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g.setRenderingHint(RenderingHints.KEY_RENDERING, 
	      RenderingHints.VALUE_RENDER_QUALITY);
	    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
	      RenderingHints.VALUE_ANTIALIAS_ON);
	    return convertToBytes(resizedImage);
	  }
	 
	 private byte[] convertToBytes(BufferedImage resizedImage) {
		    byte[] imageInByte = null;
		    try {
		      ByteArrayOutputStream baos = new ByteArrayOutputStream();
		      ImageIO.write(resizedImage, "jpg", baos);
		      baos.flush();
		      imageInByte = baos.toByteArray();
		      baos.close();
		    } catch (Exception ex) {
		      ex.printStackTrace();
		    }
		    return imageInByte;
		  }

	public byte[] getFileToDisplay(String filename,boolean isThumb) {
		FtpWrapper ftp = new FtpWrapper();
	    String hostDomain = ftp.getServerName();
	    String Id = ftp.getUsername();
	    String Password = ftp.getPassword();
	    byte[] bytes=null;
	    
	    try{
	    
	    if (ftp.connectAndLogin(hostDomain, Id, Password))
	    {
          ftp.setPassiveMode(true);
          ftp.binary();
          ftp.setBufferSize(1024000);
          ftp.changeWorkingDirectory("DMS/");
          InputStream stream = ftp.retrieveFileStream(filename);
          if(isThumb){
        	  BufferedImage bImageFromConvert = ImageIO.read(stream);
         	  bytes = compressImage(bImageFromConvert,0.3f);//resizeImageWithHint(bImageFromConvert,1,150,200);
          }else{
        	  bytes = IOUtils.toByteArray(stream);
          }
          
     	  ftp.completePendingCommand();
        }
	    }
	    catch (Exception e)
	    {
	      logger.error("Error getCommitteMembersForSociety :: " + e.getMessage());
	      e.printStackTrace();
	    }
	    finally
	    { 
	      try {
	        if (ftp.isConnected()) {
	          ftp.logout();
	          ftp.disconnect();
	        }
	      } catch (IOException e) {
	        e.printStackTrace();
	      }
	    }
	    return bytes;
	}

	public int sendDocumentAsMail(EmailBean eBean) {
		
		final String username = "info@ourdigitalsociety.com";
		final String password = "Hot@1234";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtpout.secureserver.net");
		props.put("mail.smtp.port", "3535");
		
		
		Session session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				  });
			try {
	
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("info@ourdigitalsociety.com"));
				message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(eBean.getToEmailId()));
				message.setSubject("Document From ODS");
				message.setContent("Hello, <br><br><br> You have received a document from "+eBean.getSenderName()+".<br><br><br> <a href='www.ourdigitalsociety.com/downloadAsPdf.do?documentId="+eBean.getDocumentId()+"'>click here to view.</a> <br><br><br><br><br>Regards,<br><a href='www.ourdigitalsociety.com'>OurDigitalSociety.com</a>","text/html");
	
				Transport.send(message);
	
				
				System.out.println("Done");
				return 1;
	
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		
	}
}