package com.dms.util;

public class DMSQueries {

	public static String authenticateUser = "select * from user where userName=? and password=?";  // and active=1
	public static String getAllActiveSocietyTypes = "select * from societytypemaster where isactive=1";
	public static String getAllSociety = "select * from society";
	public static String getAllDocumentTypes = "SELECT * FROM doctype order by doctypename";

	public static String insertNewSociety = "insert into society(societytypeid,societyname,createdby) values (?,?,?)";
	public static String insertNewSocietyProfile = "insert into societyprofile(societyid,addressline1,addressline2,ward,district,state,pincode,createdby) values (?,?,?,?,?,?,?,?)";
	
	
	public static String insertNewDoctype = "insert into doctype(doctypename,doctypedesc,active,createdby) values (?,?,?,?)";
	public static String getAllDocumentSubTypes = "select * from docsubtype" ;
	public static String insertNewDocSubtype = "insert into docsubtype(doctypeid,docsubtypename,docsubtypedesc,createdby,active) values (?,?,?,?,?)";
	public static String getConfigValue = "select configvalue from config where configkey=?";
	public static String insertNewFormField = "insert into formstructure(docsubtypeid,fieldname,fieldtype,datatype,sequence,active,createdby) values (?,?,?,?,?,?,?)";
	public static String getAllDocumentFormFieldsBySubTypes = "select * from formstructure where docsubtypeid=?";
	public static String getAllActiveSocietyForUser = "select * from society where isactive=1";
	
	public static String getAllDoctypeFromSocid = "select d.doctypeid,d.doctypename from doctype d,societydocmapping m,society s "
													+ "where d.doctypeid = m.doctypeid and m.societyid = s.societyid "
													+ "and s.isactive = 1 and s.societyid=? ";
	public static String getAllDocSubTypeFromDocid = "select * from docsubtype where doctypeid = ?";
	public static String insertDocDetails = "insert into documentdetails(documentid,datakey,datavalue,createdby) values (?,?,?,?)";
	public static String insertDocHead = "insert into document(societyid,doctypeid,docsubtypeid,createdby) values (?,?,?,?)";
	public static String getDocumentListForView = " select GROUP_CONCAT(concat(f.fieldname,' - ',d.datavalue) SEPARATOR  ',' )  as description, "
												+ " d.documentid, d.createdby,d.createdon  from formstructure f,documentdetails d,document doc "
												+ " where f.fieldid = d.datakey  and d.documentid = doc.documentid  and doc.societyid=? and doc.doctypeid=? and doc.docsubtypeid=? "
												+ " group by d.documentid order by f.sequence "; 
	
}
