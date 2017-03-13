package com.dms.dao;

import com.dms.beans.DocSubType;
import com.dms.beans.Doctype;
import com.dms.beans.Document;
import com.dms.beans.Files;
import com.dms.beans.FormFields;
import com.dms.util.CommomUtility;
import com.dms.util.ConnectionPoolManager;
import com.dms.util.DMSQueries;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.sql.Connection;
import java.sql.SQLException;
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
    return docTypes;
  }
  
  QueryRunner qr;
  public Doctype insertOrUpdateDocType(Doctype doctype) { Connection conn = null;
    
    long doctypeId = 0L;
    try {
      qr = new QueryRunner();
      conn = ConnectionPoolManager.getInstance().getConnection();
      ResultSetHandler<Object> rsh = new ScalarHandler();
      Object obj = qr.insert(conn, DMSQueries.insertNewDoctype, rsh, new Object[] {
        doctype.getDoctypename(), 
        doctype.getDoctypedesc(), 
        Integer.valueOf(doctype.getActive()), 
        doctype.getCreatedby() });
      

      doctypeId = CommomUtility.convertToLong(obj);
      doctype.setDoctypeid(doctypeId);
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
      ResultSetHandler<List<DocSubType>> rsh = new BeanListHandler(DocSubType.class);
      docSubType = (List)qr.query(conn, DMSQueries.getAllDocumentSubTypes, rsh);
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
      ResultSetHandler<Object> rsh = new ScalarHandler();
      Object obj = qr.insert(conn, DMSQueries.insertNewDocSubtype, rsh, new Object[] {
        Long.valueOf(docSubType.getDoctypeid()), 
        docSubType.getDocsubtypename(), 
        docSubType.getDocsubtypedesc(), 
        docSubType.getCreatedby(), 
        Integer.valueOf(docSubType.getActive()) });
      

      docsubtypeId = CommomUtility.convertToLong(obj);
      docSubType.setDoctypeid(docsubtypeId);
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
      ResultSetHandler<Object> rsh = new ScalarHandler();
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
      ResultSetHandler<List<FormFields>> rsh = new BeanListHandler(FormFields.class);
      formFields = (List)qr.query(conn, DMSQueries.getAllDocumentFormFieldsBySubTypes, rsh, new Object[] { Long.valueOf(docSubtypeid) });
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
  
  public long saveDocumentHeadAndDetails(Map<String, String> params) {
    Connection conn = null;
    String societyid = "";
    String doctypeid = "";
    String docsubtypeid = "";
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
      if ((societyid.length() < 1) || (doctypeid.length() < 1) || (docsubtypeid.length() < 1)) {
        return 0L;
      }
      qr = new QueryRunner();
      conn = ConnectionPoolManager.getInstance().getConnection();
      conn.setAutoCommit(false);
      
      Object obj = qr.insert(conn, DMSQueries.insertDocHead, new ScalarHandler(), new Object[] {
        societyid, 
        doctypeid, 
        docsubtypeid, 
        Integer.valueOf(123) });
      
      documentId = CommomUtility.convertToLong(obj);
      
      if (documentId != 0L) {
        for (String key : params.keySet()) {
          if (!key.equals("UserId")) {
            qr.insert(conn, DMSQueries.insertDocDetails, new ScalarHandler(), new Object[] {
              Long.valueOf(documentId), 
              key, 
              params.get(key), 
              Integer.valueOf(123) });
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
      ResultSetHandler<List<Document>> rsh = new BeanListHandler(Document.class);
      documents = (List)qr.query(conn, DMSQueries.getDocumentListForView, rsh, new Object[] {
        Long.valueOf(document.getSocietyid()), 
        Long.valueOf(document.getDoctypeid()), 
        Long.valueOf(document.getDocsubtypeid()) });
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
      Object obj = qr.insert(conn, DMSQueries.insertPhotoInfo, rsh, new Object[] {
        phototype, 
        Integer.valueOf(docid), 
        docpath, 
        docname, 
        contentType });
      

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
}