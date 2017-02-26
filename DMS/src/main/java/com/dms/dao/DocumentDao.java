package com.dms.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.slf4j.LoggerFactory;

import com.dms.beans.DocSubType;
import com.dms.beans.Doctype;
import com.dms.beans.FormFields;
import com.dms.util.CommomUtility;
import com.dms.util.ConnectionPoolManager;
import com.dms.util.DMSQueries;

public class DocumentDao {
	
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(DocumentDao.class);
	QueryRunner qr;
	
	public List<Doctype> getAllDocumentTypes(List<Doctype> docTypes) {
			Connection conn = null;
			ResultSetHandler<List<Doctype>> rsh;
			try{
				qr = new QueryRunner();
				conn = ConnectionPoolManager.getInstance().getConnection();
				rsh = new BeanListHandler<Doctype>(Doctype.class);
				docTypes = qr.query(conn, DMSQueries.getAllDocumentTypes,rsh);
			}catch(Exception e){
				logger.error("Error authenticating user :: "+e.getMessage());
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

	public Doctype insertOrUpdateDocType(Doctype doctype) {
		Connection conn = null;
		ResultSetHandler<Object> rsh;
		long doctypeId = 0L;
		try{
			qr = new QueryRunner();
			conn = ConnectionPoolManager.getInstance().getConnection();
			rsh = new ScalarHandler<Object>();
			Object obj  = qr.insert(conn, DMSQueries.insertNewDoctype,rsh,
					doctype.getDoctypename(),
					doctype.getDoctypedesc(),
					doctype.getActive(),
					doctype.getCreatedby()
					);
			
			doctypeId = CommomUtility.convertToLong(obj);
			doctype.setDoctypeid(doctypeId);
			
		}catch(Exception e){
			logger.error("Error Saving Doctype :: "+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				logger.error("Error releasing connection :: "+e.getMessage());
			}
		}
	return doctype;
}

	public List<DocSubType> getAllDocumentSubTypes(List<DocSubType> docSubType) {
		Connection conn = null;
		ResultSetHandler<List<DocSubType>> rsh;
		try{
			qr = new QueryRunner();
			conn = ConnectionPoolManager.getInstance().getConnection();
			rsh = new BeanListHandler<DocSubType>(DocSubType.class);
			docSubType = qr.query(conn, DMSQueries.getAllDocumentSubTypes,rsh);
		}catch(Exception e){
			logger.error("Error authenticating user :: "+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				logger.error("Error releasing connection :: "+e.getMessage());
			}
		}
	return docSubType;
}

	public DocSubType insertOrUpdateDocSubType(DocSubType docSubType) {
		Connection conn = null;
		ResultSetHandler<Object> rsh;
		long docsubtypeId = 0L;
		try{
			qr = new QueryRunner();
			conn = ConnectionPoolManager.getInstance().getConnection();
			rsh = new ScalarHandler<Object>();
			Object obj  = qr.insert(conn, DMSQueries.insertNewDocSubtype,rsh,
					docSubType.getDoctypeid(),
					docSubType.getDocsubtypename(),
					docSubType.getDocsubtypedesc(),
					docSubType.getCreatedby(),
					docSubType.getActive()
					);
			
			docsubtypeId = CommomUtility.convertToLong(obj);
			docSubType.setDoctypeid(docsubtypeId);
		}catch(Exception e){
			logger.error("Error Saving Doctype :: "+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				logger.error("Error releasing connection :: "+e.getMessage());
			}
		}
	return docSubType;
}

	public FormFields insertOrUpdateFormFields(FormFields formFields) {
		Connection conn = null;
		ResultSetHandler<Object> rsh;
		long fieldid = 0L;
		try{
			qr = new QueryRunner();
			conn = ConnectionPoolManager.getInstance().getConnection();
			rsh = new ScalarHandler<Object>();
			Object obj  = qr.insert(conn, DMSQueries.insertNewFormField,rsh,
					formFields.getDocsubtypeid(),
					formFields.getFieldname(),
					formFields.getFieldtype(),
					formFields.getDatatype(),
					formFields.getSequence(),
					formFields.getActive(),
					formFields.getCreatedby()
					);
			
			fieldid = CommomUtility.convertToLong(obj);
			formFields.setFieldid(fieldid);
		}catch(Exception e){
			logger.error("Error Saving Doctype :: "+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				logger.error("Error releasing connection :: "+e.getMessage());
			}
		}
	return formFields;
}

	public List<FormFields> getFieldsForDocSubtype(long docSubtypeid, List<FormFields> formFields) {
		Connection conn = null;
		ResultSetHandler<List<FormFields>> rsh;
		try{
			qr = new QueryRunner();
			conn = ConnectionPoolManager.getInstance().getConnection();
			rsh = new BeanListHandler<FormFields>(FormFields.class);
			formFields = qr.query(conn, DMSQueries.getAllDocumentFormFieldsBySubTypes,rsh,docSubtypeid);
		}catch(Exception e){
			logger.error("Error fetching form fields :: "+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				logger.error("Error releasing connection :: "+e.getMessage());
			}
		}
		//System.out.println(formFields);
	return formFields;
}

	public List<Doctype> getDoctypeBySocId(long societyid, List<Doctype> doctypes) {
		Connection conn = null;
		ResultSetHandler<List<Doctype>> rsh;
		try{
			qr = new QueryRunner();
			conn = ConnectionPoolManager.getInstance().getConnection();
			rsh = new BeanListHandler<Doctype>(Doctype.class);
			doctypes = qr.query(conn, DMSQueries.getAllDoctypeFromSocid,rsh,societyid);
		}catch(Exception e){
			logger.error("Error getting doctypes by soc id :: "+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				logger.error("Error releasing connection :: "+e.getMessage());
			}
		}
	return doctypes;
}

	public List<DocSubType> getDocSubtypeByDocId(long doctypeid, List<DocSubType> docSubTypes) {
		Connection conn = null;
		ResultSetHandler<List<DocSubType>> rsh;
		try{
			qr = new QueryRunner();
			conn = ConnectionPoolManager.getInstance().getConnection();
			rsh = new BeanListHandler<DocSubType>(DocSubType.class);
			docSubTypes = qr.query(conn, DMSQueries.getAllDocSubTypeFromDocid,rsh,doctypeid);
		}catch(Exception e){
			logger.error("Error getting doctypes by soc id :: "+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				logger.error("Error releasing connection :: "+e.getMessage());
			}
		}
	return docSubTypes;
}
}
