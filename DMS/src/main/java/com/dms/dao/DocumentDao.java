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

import com.dms.beans.Doctype;
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
}
