package com.dms.dao;

import com.dms.beans.DocSubType;
import com.dms.beans.Doctype;
import com.dms.beans.Document;
import com.dms.beans.Documentdetails;
import com.dms.beans.Files;
import com.dms.beans.FormFields;
import com.dms.beans.GenericBean;
import com.dms.beans.User;
import com.dms.util.CommomUtility;
import com.dms.util.ConnectionPoolManager;
import com.dms.util.DMSQueries;
import com.mchange.v2.c3p0.ComboPooledDataSource;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




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
  
  public long savePhotoInfo(String phototype, int docid, String docname, String docpath, String contentType) {
    Connection conn = null;
    
    long fieldid = 0L;
    try {
      qr = new QueryRunner();
      conn = ConnectionPoolManager.getInstance().getConnection();
      ResultSetHandler<Object> rsh = new ScalarHandler();
      
      conn.setAutoCommit(false);
      
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

public List<DocSubType> getDocStubtypesToDispay(List<DocSubType> docSubType) {
    Connection conn = null;

    try {
      qr = new QueryRunner();
      conn = ConnectionPoolManager.getInstance().getConnection();
      ResultSetHandler<List<DocSubType>> rsh = new BeanListHandler<DocSubType>(DocSubType.class);
      
      docSubType = qr.query(conn,DMSQueries.getDocStubtypesToDispay,rsh);
      
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
}