package com.dms.util;

public class DMSQueries
{
	
  public static String authenticateUser = "select * from user where mobileNo=? and password=?";
  public static String getAllActiveUser = "select * from user where active=1 ";
  public static String getAllActiveSocietyTypes = "select * from societytypemaster where isactive=1";
  public static String getAllSociety = "select * from society";
  public static String getAllDocumentTypes = "SELECT d.doctypeid,d.doctypename,d.doctypedesc,d.createdon,d.active,concat(u.firstname,' ',u.lastname) as createdby FROM  doctype d, user u where d.createdby=u.userid order by doctypename ";
  public static String insertNewSociety = "insert into society(societytypeid,societyname,createdby) values (?,?,?)";
  public static String insertNewSocietyProfile = "insert into societyprofile(societyid,addressline1,addressline2,ward,district,state,pincode,createdby,registrationno,estdate) values (?,?,?,?,?,?,?,?,?,?)";
  public static String insertNewDoctype = "insert into doctype(doctypename,doctypedesc,active,createdby) values (?,?,?,?)";
  public static String getAllDocumentSubTypes = "SELECT st.docsubtypeid,st.docsubtypename,st.docsubtypedesc,st.active,st.createdon,concat(u.firstname,' ',u.lastname) as createdby,dt.doctypename as doctypename,st.doctypeid FROM docsubtype st,doctype dt,user u where st.doctypeid=dt.doctypeid and st.createdby=u.userid";
  public static String insertNewDocSubtype = "insert into docsubtype(doctypeid,docsubtypename,docsubtypedesc,createdby,active) values (?,?,?,?,?)";
  public static String getConfigValue = "select configvalue from config where configkey=?";
  public static String insertNewFormField = "insert into formstructure(docsubtypeid,fieldname,fieldtype,datatype,sequence,active,createdby) values (?,?,?,?,?,?,?)";
  public static String getAllDocumentFormFieldsBySubTypes = " SELECT fs.fieldid,fs.fieldname,fs.fieldtype,fs.datatype,fs.sequence,fs.active,fs.createdon,fs.docsubtypeid,concat(u.firstname,' ',u.lastname) as createdby FROM formstructure fs, user u where fs.createdby=u.userid and fs.docsubtypeid=?";
  public static String getAllActiveSocietyForUser = "select * from society where isactive=1";
  public static String getAllDoctypeFromSocid = "select d.doctypeid,d.doctypename from doctype d,societydocmapping m,society s where d.doctypeid = m.doctypeid and m.societyid = s.societyid and s.isactive = 1 and s.societyid=? ";
  public static String getAllDocSubTypeFromDocid = "select * from docsubtype where doctypeid = ?";
  public static String insertDocDetails = "insert into documentdetails(documentid,datakey,datavalue,createdby) values (?,?,?,?)";
  public static String insertDocHead = "insert into document(societyid,doctypeid,docsubtypeid,createdby,userid) values (?,?,?,?,?)";
  public static String getDocumentListForView = " select GROUP_CONCAT(concat(f.fieldname,' - ',d.datavalue) SEPARATOR  ',' )  as description,  d.documentid, d.createdby,d.createdon  from formstructure f,documentdetails d,document doc  where f.fieldid = d.datakey  and d.documentid = doc.documentid  and doc.societyid=? and doc.doctypeid=? and doc.docsubtypeid=?  group by d.documentid order by f.sequence ";
  public static String getDocPathsByDocId = "select * from files where documentid = ?";
  public static String insertNewUser = "insert into user (firstName,lastName,password,createdBy,mobileNo) values (?,?,?,?,?)";
  public static String insertNewUserProfile = "insert into userprofile(userid,flatno,wing,floor,tower,occupancy,alternateno,email,aadharno,jointowners,purchasedate,possessiondate,builtuparea,carpetarea,parkingtype,vehicletype,parkingallotmentno,societyid) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
  public static String getMembersForSociety = "select * from userprofile up,user u where up.userid = u.userid and up.societyid=?";
  public static String getAllCommitteePositions = "select * from committeemaster where isactive=1";
  public static String insertNewCommitteeMember = "insert into committee(userid,societyid,positionid,appointedon,removedon) values (?,?,?,?,?)";
  public static String getAllActiveCommitteMembersBySocietyId = "select c.*,concat(p.firstName,' ',p.lastName) as userName,s.societyname,cm.positionname,p.mobileNo as contactNo,concat(up.wing,'/',up.flatno) as flat from committee c,user p,society s,committeemaster cm,userprofile up where c.userid = p.userid  and p.userid = up.userid  and c.societyid = s.societyid  and c.positionid = cm.positionid  and c.societyid=? and c.isactive=1";
  public static String getAllInActiveCommitteMembersBySocietyId = "select c.*,concat(p.firstName,' ',p.lastName) as userName,s.societyname,cm.positionname,p.mobileNo as contactNo,concat(up.wing,'/',up.flatno) as flat from committee c,user p,society s,committeemaster cm,userprofile up where c.userid = p.userid  and p.userid = up.userid  and c.societyid = s.societyid  and c.positionid = cm.positionid  and c.societyid=? and c.isactive=0";
  public static String removeCommitteeMember = "update committee set isactive=0,removedon=? where committeememberid=?";
  public static String getSocietyListForManager = "select s.societyname,sp.* from societymanager m,society s,societyprofile sp where m.societyid = s.societyid and s.societyid = sp.societyid and m.userid=?";
  public static String insertPhotoInfo = "insert into photos(phototype,docid,docpath,docname,contenttype) values (?,?,?,?,?)";
  public static String getSocietyPhotos = "select * from photos where docid=? and isactive=1 and phototype='society'";
  public static String deactOldPhotos="update photos set isactive=0 where docid=? and phototype='society'";
  public static String insertNewVendor="insert into vendors(companyname,jobnature,contactperson,address,contactno,alternateno,email,remark,isactive,vendors) values (?,?,?,?,?,?,?,?,?,?)";
  public static String insertVendorSocMapping = "insert into vendorsocietymapping(vendorid,societyid) values (?,?)";
  public static String getAllVendorsBySocId = "SELECT * FROM vendors v,vendorsocietymapping m where m.vendorid = v.vendorid and m.societyid=?";
  public static String getSocietyDetailsById="select * from society s,societyprofile sp where s.societyid = sp.societyid and s.societyid=?";
  public static String updateSociety = "Update society set societytypeid=?, societyname=? where societyid=?";
  public static String updateSocProfile = "Update societyprofile set addressline1=?, addressline2=?, ward=?, district=?, state=?, pincode=?, registrationno=?, estdate=? where societyid=?";
  public static String getDocumentTypeById = "select * from doctype where doctypeid=?";
  public static String updateDoctype="Update doctype set doctypename=?, doctypedesc=?, active=? where doctypeid=?";
  public static String getDocumentSubTypeById = "select * from docsubtype where docsubtypeid=?";
  public static String updateDocSubtype=" Update docsubtype set doctypeid=?, docsubtypename=?, docsubtypedesc=?, active=? where docsubtypeid=?";
  public static String getFormFieldDetailsById = "select * from formstructure where fieldid=?";
  public static String updateFormFieldData = "Update formstructure set fieldname=?, fieldtype=?, datatype=?, sequence=?, active=?, docsubtypeid=? where fieldid=?";
  public static String insertNewTenant = "insert into tenant(userid, tenantname, tenantaddress, tenantcontactnumber, tenantaltnumber, tenantemail, tenantaadharno) values (?,?,?,?,?,?,?)";
public static String getUserDataById = "select * from user ud,userprofile up where ud.userid=up.userid and up.userid=?";
public static String getTenantDataByUserId = "select * from tenant where userid=?";


public static String updateNewUser = "Update user set firstname=?, lastname=?,  password=?, active=?, mobileNo=? where userid=?";
public static String updateNewUserProfile = "Update userprofile set  flatno=?, wing=?, tower=?, occupancy=?, alternateno=?, email=?, aadharno=?, jointowners=?, purchasedate=?, possessiondate=?, builtuparea=?, carpetarea=?, parkingtype=?, vehicletype=?, parkingallotmentno=?, floor=?, societyid=? where userid=?";
public static String updateNewTenant = "Update tenant set  tenantname=?, tenantaddress=?, tenantcontactnumber=?, tenantaltnumber=?, tenantemail=?, tenantaadharno=? where userid=?";
public static String getVendorDataById = "select * from vendors where vendorid=?";
public static String updateNewVendor = "Update vendors set companyname=?, jobnature=?, contactperson=?, address=?, contactno=?, alternateno=?, email=?, remark=?, isactive=? where vendorid=?";
public static String updateVendorSocMapping="Update vendorsocietymapping set societyid=? where vendorid=?";
public static String getExistingDocDetails = "select dd.* from documentdetails dd,document d where dd.documentid=d.documentid and d.societyid=? and d.doctypeid=? and d.docsubtypeid=? and d.userid=?";
public static String getSocietyManagerList = "select sm.societymanagerid as societymanagerid,concat(u.firstname,' ',u.lastname) as userName,sm.isactive as isactive from societymanager sm, user u where sm.userid=u.userid and sm.societyid=?";
public static String removeSocietyManager = "delete from societymanager where societymanagerid=?";
public static String addSocietyManager = "insert into societymanager(societyid,userid) values (?,?)";
public static String getAllBuilders = "select * from builder";
public static String insertNewBuilder = "insert into builder( buildername, address, blockno, premisesname, streetname, landmark, area, city, pincode, state, country, createdby,active) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
public static String updateBuilder="Update builder set buildername=?, address=?, blockno=?, premisesname=?, streetname=?, landmark=?, area=?, city=?, pincode=?, state=?, country=?, active=? where builderid=?";
public static String getBuilderDetailsById="select * from builder where builderid=?";
  
}