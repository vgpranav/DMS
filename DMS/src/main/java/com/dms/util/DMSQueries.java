package com.dms.util;

public class DMSQueries
{
	
  public static String authenticateUser = "select * from user where mobileNo=? and password=?";
  
  public static String getAllActiveUser = "select * from user where active=1 ";
  
  public static String getAllActiveSocietyTypes = "select * from societytypemaster where isactive=1";
  
  public static String getAllSociety = "select * from society where societytype=?";
  
  public static String getAllDocumentTypes = "SELECT d.doctypeid,d.doctypename,d.doctypedesc,d.createdon,d.active,getUsername(d.createdby) as "
  											+ " createdby FROM  doctype d order by doctypename ";
  
  public static String insertNewSociety = "insert into society(societytypeid,societyname,createdby,projectid,societytype) values (?,?,?,?,?)";
  public static String insertNewSocietyProfile = "insert into societyprofile(societyid,addressline1,addressline2,ward,district,state,pincode,createdby,registrationno,estdate,landmark,city,country,noofshop,noofflat,noof1rk,noof1bhk,noof1p5bhk,noof2bhk,noof2p5bhk,noof3bhk,noof3p5bhk) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
  public static String insertNewDoctype = "insert into doctype(doctypename,doctypedesc,active,createdby) values (?,?,?,?)";
  public static String getAllDocumentSubTypes = "SELECT st.displayflag,st.docsubtypeid,st.docsubtypename,st.docsubtypedesc,st.active,st.createdon,concat(u.firstname,' ',u.lastname) as createdby,dt.doctypename as doctypename,st.doctypeid FROM docsubtype st,doctype dt,user u where st.doctypeid=dt.doctypeid and st.createdby=u.userid";
  public static String insertNewDocSubtype = "insert into docsubtype(doctypeid,docsubtypename,docsubtypedesc,createdby,active,displayflag) values (?,?,?,?,?,?)";
  public static String getConfigValue = "select configvalue from config where configkey=?";
  public static String insertNewFormField = "insert into formstructure(docsubtypeid,fieldname,fieldtype,datatype,sequence,active,createdby) values (?,?,?,?,?,?,?)";
  public static String getAllDocumentFormFieldsBySubTypes = " SELECT fs.fieldid,fs.fieldname,fs.fieldtype,fs.datatype,fs.sequence,fs.active,fs.createdon,fs.docsubtypeid,concat(u.firstname,' ',u.lastname) as createdby FROM formstructure fs, user u where fs.createdby=u.userid and fs.docsubtypeid=?";
  public static String getAllActiveSocietyForUser = "select * from society where isactive=1";
  public static String getAllActiveSocietyDetails = "select * from society s,societyprofile sp where s.societyid=sp.societyid and s.isactive=1";
  public static String getAllDoctypeFromSocid = "select d.doctypeid,d.doctypename from doctype d,societydocmapping m,society s where d.doctypeid = m.doctypeid and m.societyid = s.societyid and s.isactive = 1 and s.societyid=? ";
  public static String getAllDocSubTypeFromDocid = "select * from docsubtype where doctypeid = ?";
  public static String insertDocDetails = "insert into documentdetails(documentid,datakey,datavalue,createdby) values (?,?,?,?)";
  public static String insertDocHead = "insert into document(societyid,doctypeid,docsubtypeid,createdby,userid) values (?,?,?,?,?)";
  public static String getDocumentListForView = " select GROUP_CONCAT(concat(f.fieldname,' - ',d.datavalue) SEPARATOR  ',' )  as description,  d.documentid, d.createdby,d.createdon  from formstructure f,documentdetails d,document doc  where f.fieldid = d.datakey  and d.documentid = doc.documentid  and doc.societyid=? and doc.doctypeid=? and doc.docsubtypeid=?  group by d.documentid order by f.sequence ";
  public static String getDocPathsByDocId = "select * from files where documentid = ?";
  public static String insertNewUser = "insert into user (firstName,lastName,password,createdBy,mobileNo,middlename) values (?,?,?,?,?,?)";
  public static String insertNewUserProfile = "insert into userprofile(userid,flatno,wing,floor,tower,occupancy,alternateno,email,aadharno,jointowners,purchasedate,possessiondate,builtuparea,carpetarea,parkingtype,vehicletype,parkingallotmentno,societyid,bloodgroup,sharecertno,nominee1,percent1,nominee2,percent2,nominee3,percent3,vehicleno,companyname,companytype,gumastalicno,membertype,commercialtype,flattype) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
  public static String getMembersForSociety = "select * from userprofile up,user u where up.userid = u.userid and up.societyid=?";
  public static String getAllCommitteePositions = "select * from committeemaster where isactive=1";
  public static String insertNewCommitteeMember = "insert into committee(userid,societyid,positionid,appointedon,removedon) values (?,?,?,?,?)";
  public static String getAllActiveCommitteMembersBySocietyId = "select c.*,concat(p.firstName,' ',p.lastName) as userName,s.societyname,cm.positionname,p.mobileNo as contactNo,concat(up.wing,'/',up.flatno) as flat,' ' as tower from committee c,user p,society s,committeemaster cm,userprofile up where c.userid = p.userid  and p.userid = up.userid  and c.societyid = s.societyid  and c.positionid = cm.positionid  and c.societyid=? and c.isactive=1";
  public static String getAllInActiveCommitteMembersBySocietyId = "select c.*,concat(p.firstName,' ',p.lastName) as userName,s.societyname,cm.positionname,p.mobileNo as contactNo,concat(up.wing,'/',up.flatno) as flat,up.tower,DATE_FORMAT(c.removedon,'%d%m%y') as tower from committee c,user p,society s,committeemaster cm,userprofile up where c.userid = p.userid  and p.userid = up.userid  and c.societyid = s.societyid  and c.positionid = cm.positionid  and c.societyid=? and c.isactive=0  order by c.removedon desc";
  public static String removeCommitteeMember = "update committee set isactive=0,removedon=? where committeememberid=?";
  public static String getSocietyListForManager = "select s.societyname,s.societytypeid,sp.* from societymanager m,society s,societyprofile sp where m.societyid = s.societyid and s.societyid = sp.societyid and m.userid=?";
  public static String insertPhotoInfo = "insert into photos(phototype,docid,docpath,docname,contenttype) values (?,?,?,?,?)";
  public static String getPhotoInfo = "select * from photos where docid=? and isactive=1 and phototype=?";
  public static String deactOldPhotos="update photos set isactive=0 where docid=? and phototype=?";
  public static String insertNewVendor="insert into vendors(companyname,jobnature,contactperson,address,contactno,alternateno,email,remark,isactive,createdby,contractfrom,contractto) values (?,?,?,?,?,?,?,?,?,?,?,?)";
  public static String insertVendorSocMapping = "insert into vendorsocietymapping(vendorid,societyid) values (?,?)";
  public static String getAllVendorsBySocId = "SELECT * FROM vendors v,vendorsocietymapping m where m.vendorid = v.vendorid and m.societyid=?";
  public static String getSocietyDetailsById="select * from society s,societyprofile sp where s.societyid = sp.societyid and s.societyid=?";
  public static String updateSociety = "Update society set societytypeid=?, societyname=? ,projectid=? where societyid=?";
  public static String updateSocProfile = "Update societyprofile set addressline1=?, addressline2=?, ward=?, district=?, state=?, pincode=?, registrationno=?, estdate=?,landmark=?,city=?,country=?,noofshop=?,noofflat=?,noof1rk=?,noof1bhk=?,noof1p5bhk=?,noof2bhk=?,noof2p5bhk=?,noof3bhk=?,noof3p5bhk=? where societyid=?";
  public static String getDocumentTypeById = "select * from doctype where doctypeid=?";
  public static String updateDoctype="Update doctype set doctypename=?, doctypedesc=?, active=? where doctypeid=?";
  public static String getDocumentSubTypeById = "select * from docsubtype where docsubtypeid=?";
  public static String updateDocSubtype=" Update docsubtype set doctypeid=?, docsubtypename=?, docsubtypedesc=?, active=?,displayflag=? where docsubtypeid=?";
  public static String getFormFieldDetailsById = "select * from formstructure where fieldid=?";
  public static String updateFormFieldData = "Update formstructure set fieldname=?, fieldtype=?, datatype=?, sequence=?, active=?, docsubtypeid=? where fieldid=?";
  public static String insertNewTenant = "insert into tenant(userid, tenantname, tenantaddress, tenantcontactnumber, tenantaltnumber, tenantemail, tenantaadharno,tenanttype,tenantPVstatus,tenantfrom,tenantto,tenantcompanyname,tenantcompanytype,tenantgumastalicno) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	public static String getUserDataById = "select * from user ud,userprofile up where ud.userid=up.userid and up.userid=?";
	public static String getTenantDataByUserId = "select * from tenant where userid=? and active=1";
	
	
	public static String updateNewUser = "Update user set firstname=?, lastname=?,  password=?, active=?, mobileNo=? ,middlename=? where userid=?";
	public static String updateNewUserProfile = "Update userprofile set  flatno=?, wing=?, tower=?, occupancy=?, alternateno=?, email=?, aadharno=?, jointowners=?, purchasedate=?, possessiondate=?, builtuparea=?, carpetarea=?, parkingtype=?, vehicletype=?, parkingallotmentno=?, floor=?, societyid=?,bloodgroup=?,sharecertno=?,nominee1=?,percent1=?,nominee2=?,percent2=?,nominee3=?,percent3=?,vehicleno=?,companyname=?,companytype=?,gumastalicno=?,membertype=?,commercialtype=?,flattype=? where userid=?";
	public static String updateNewTenant = "Update tenant set  tenantname=?, tenantaddress=?, tenantcontactnumber=?, tenantaltnumber=?, tenantemail=?, tenantaadharno=?,tenanttype=?,tenantPVstatus=?,tenantfrom=?,tenantto=?,tenantcompanyname=?,tenantcompanytype=?,tenantgumastalicno=? where userid=?";
	public static String getVendorDataById = "select * from vendors where vendorid=?";
	public static String updateNewVendor = "Update vendors set companyname=?, jobnature=?, contactperson=?, address=?, contactno=?, alternateno=?, email=?, remark=?, isactive=?,contractfrom=?,contractto=? where vendorid=?";
	public static String updateVendorSocMapping="Update vendorsocietymapping set societyid=? where vendorid=?";
	public static String getExistingDocDetails = "select dd.* from documentdetails dd,document d where dd.documentid=d.documentid and d.societyid=? and d.doctypeid=? and d.docsubtypeid=? and d.userid=?";
	public static String getSocietyManagerList = "select sm.societymanagerid as societymanagerid,concat(u.firstname,' ',u.lastname) as userName,sm.isactive as isactive from societymanager sm, user u where sm.userid=u.userid and sm.societyid=?";
	public static String removeSocietyManager = "delete from societymanager where societymanagerid=?";
	public static String addSocietyManager = "insert into societymanager(societyid,userid) values (?,?)";
	public static String getAllBuilders = "select * from builder";
	public static String insertNewBuilder = "insert into builder( buildername, address, blockno, premisesname, streetname, landmark, area, city, pincode, state, country, createdby,active,contact,altcontact,email,district) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	public static String updateBuilder="Update builder set buildername=?, address=?, blockno=?, premisesname=?, streetname=?, landmark=?, area=?, city=?, pincode=?, state=?, country=?, active=? ,contact=? ,altcontact=? ,email=?,district=? where builderid=?";
	public static String getBuilderDetailsById="select * from builder where builderid=?";
	public static String getAllProjects="SELECT p.*,b.builderid,b.buildername FROM project p, builder b where p.builderid=b.builderid ";
	public static String getProjectDetailsById="select * from project where projectid=?";
	public static String insertNewProject="insert into project(projectname, builderid, plotarea, registrationdate, towernos, resnos, bungnos, pentanos, shopnos, galanos, createdby,street,landmark,area,city,district,state,country,pincode) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	public static String updateProject="Update project set projectname=?, builderid=?, plotarea=?, registrationdate=?, towernos=?, resnos=?, bungnos=?, pentanos=?, shopnos=?, galanos=?, createdby=?,active=?,street=?,landmark=?,area=?,city=?,district=?,state=?,country=?,pincode=? where projectid=?";
	public static String insertSocDocMapping="insert into societydocmapping(doctypeid, societyid, createdby) values (?,?,?)";
	public static String getAllSocDocMapping= "select m.societydocmappingid,d.doctypename,s.societyname from societydocmapping m, society s, doctype d where m.societyid=s.societyid and m.doctypeid=d.doctypeid";
	public static String removeSocietyDocmapping="delete from societydocmapping where societydocmappingid=?";
	public static String getAllRoles ="select * from rolemaster";
	public static String insertNewAdminUser="insert into user(firstname, lastname, password, mobileNo, createdBy, userroleid) values (?,?,?,?,?,?)";
	public static String getAllAdminUsers = "SELECT u.*,r.rolename as userrolename FROM dms.user u,dms.rolemaster r where u.userroleid=r.roleid and userroleid <> 0" ;
	public static String getUserById = "select * from user where userid=?";
	public static String updateAdminUser="Update user set firstname=?, lastname=?, password=?, active=?, mobileNo=?, userroleid=? where  userid=?";
	public static String getNoticeboardDocBySocid = " SELECT * FROM files where societyid=? and docsubtypeid=999 order by createdon desc,documentid";
	public static String getDocStubtypesToDispay = "select * from  docsubtype dst, socdocviewmapping dm  where dst.docsubtypeid = dm.docsubtypeid and dm.displayflag=1 and dm.societyid=?";
	public static String getDocumentsToDisplay= " SELECT f.* FROM  document d, files f where d.documentid=f.documentid and d.docsubtypeid=? and (d.userid=? or d.userid=9999) and d.societyid=?";
	public static String getDocumentDatabyDoctypeIdUserId = " SELECT d.documentid,f.fieldname as fieldname,dd.datavalue as fieldvalue FROM  formstructure f, documentdetails dd, document d  "
			    									+ " where dd.datakey=f.fieldid and d.documentid=dd.documentid and d.docsubtypeid=? and (d.userid=? or d.userid=9999) and d.societyid=?";
	  
	
	public static String getDocumentsToDisplayByDocId= " SELECT f.* FROM  document d, files f where d.documentid=f.documentid and f.documentid=?";
	
	public static String getDocumentDatabyDocId = " SELECT d.userid,d.documentid,f.fieldname as fieldname,dd.datavalue as fieldvalue FROM  formstructure f, documentdetails dd, document d  "
			+ " where dd.datakey=f.fieldid and d.documentid=dd.documentid and d.documentid=?";
	
	public static String getDocSummaryforAdminpanel = " SELECT count(d.documentid) as doccount,dt.doctypeid,dt.doctypename, "
			+ " dst.docsubtypeid,dst.docsubtypename FROM dms.document d,dms.doctype dt,dms.docsubtype dst,societydocmapping sdm "
			+ " where d.doctypeid=dt.doctypeid and d.docsubtypeid=dst.docsubtypeid and d.societyid=sdm.societyid and sdm.doctypeid=d.doctypeid and d.societyid=? "
			+" and d.docsubtypeid not in (select docsubtypeid from socdocviewmapping where societyid=? and confFlag=1) "
			+ " group by dst.docsubtypename,dt.doctypename order by dt.doctypename,dst.docsubtypename";
	
	public static String getNeighborProfile = "SELECT n.*,u.* FROM dms.userprofile o,dms.userprofile n,dms.user u "
			+ " where o.wing=n.wing and o.tower=n.tower and upper(o.floor)=upper(n.floor) and o.societyid=n.societyid "
			+ " and n.userid=u.userid and o.userid!=n.userid and o.userid=?";
	
	public static String addNewCallRef = "insert into callreference(refno, initiatorname, societytype, buildingno, streetname, landmark, area, pincode, city, state, country, resno, shopsno, initiatedate, remark, closingchance) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	public static String updateCallRef ="Update callreference set refno=?, initiatorname=?, societytype=?, buildingno=?, streetname=?, landmark=?, area=?, pincode=?, city=?, state=?, country=?, resno=?, shopsno=?, initiatedate=?, remark=?, closingchance=?";
	public static String insertNewCallRefContact="insert into callreferencecontact( callrefid, contactname, contactdesignation, contactmobileno, contactlandlineno, contactemail) values ( ?,?,?,?,?,?)";
	public static String getContactsByCallRefId ="select * from callreferencecontact where callrefid=?";
	public static String saveCallrefMeeting = "insert into callreferencemeeting(callrefid, meetingdate, meetingperson, meetingremarks) values (?,?,?,?)";
	public static String getMeetingsByCallRefId ="select * from callreferencemeeting where callrefid=?";
	public static String getAllCallRefs = "select * from callreference";
	public static String getCountOfSocsAsManager="select count(societymanagerid) from societymanager where userid=? and isactive=1";
	
	public static String getmodNameBySocId="";
	
	public static String insertSmsLog="insert into smslogger(smstype, smsmsg, smscount, smsusers, smsdate, smsresponse, smshttpcode) values (?,?,?,?,?,?,?)";
	public static String deleteDocumentByDocId="delete from document where documentid=?";
	public static String saveSocViewMapping = "insert into socdocviewmapping (societyid, doctypeid, docsubtypeid, displayflag, confFlag, createdby, datakey) values (?,?,?,?,?,?,?)";
	public static String getSocDocMappingBySocId= "select dt.doctypename,dst.docsubtypename,dm.* from socdocviewmapping dm, doctype dt, docsubtype dst where dm.doctypeid = dt.doctypeid and dm.docsubtypeid = dst.docsubtypeid and dm.societyid=?";
	public static String removeSocDocViewMapping = "delete from socdocviewmapping where socdocviewmappingid=?";
	public static String authenticateUserByMobNo = "select * from user where mobileNo=?";
	public static String insertOTPHandler = "insert into otphandler(mobileNo,otp,active) values (?,?,?)" ;
	public static String deactAllOldOTP = "update otphandler set active=0 where mobileNo=?";
	public static String validateOTPMessage = "select * from otphandler where mobileNo=? and otp=? and active=1";
	public static String setNewPasswordForUser = "update user set password=? where mobileNo=?";
	public static String getAllDesignations="select * from committeemaster";
	public static String getDesignationById="select * from committeemaster where positionid=? limit 1";
	public static String insertNewDesignation = "insert into committeemaster(positionname,isactive) values (?,?)";
	public static String updateNewDesignation ="update committeemaster set positionname=?,isactive=? where positionid=?";
	public static String checkIfNewNoticeAdded="select count(*) from document where doctypeid=999 and docsubtypeid=999 and userid=999 and societyid=? and createdon>? ";
	public static String deleteDocumentPage = "delete from files where filesid=?";
	public static String saveMemberparkingDetails = "insert into userparkingdetails(userid,parkingtype,vehicletype,parkingallotmentno,vehicleno,randomhash) values (?,?,?,?,?,?)";
	public static String getParkingDetailsForMemberByUserId ="select * from userparkingdetails where userid=?";
	public static String getParkingDetailsForMemberByHashValue ="select * from userparkingdetails where randomhash=?";
	public static String removeParkingData = "delete from userparkingdetails where userparkingdetailsid=?";
	public static String addShareCertDetails ="insert into userscnominee (nominee,percent,userid,randomHash,nomineerelation,nomineedob,nomineeaddress) values (?,?,?,?,?,?,?)";
	public static String getShareCertDetailsForMemberByUserId ="select * from userscnominee where userid=?";
	public static String getShareCertDetailsForMemberByHashValue ="select * from userscnominee where randomhash=?";
	public static String removeShareCertDetails = "delete from userscnominee where userscnomineeid=?";
	public static String updateParkingDetails="update userparkingdetails set userid=?,randomHash='' where randomHash=?";
	public static String updateSCDetails="update userscnominee set userid=?,randomHash='' where randomHash=?";
	public static String getAllTxnRoles = "select * from role";
	public static String insertConfDocAccess="insert into confidentialdocaccess(userid,createdby) values (?,?)";
	public static String getAllConfDocAccessList = "SELECT da.confidentialdocaccessid,da.isactive,da.createdon, concat(u.firstname,' ' ,u.lastname) as createdby, "
													+ " concat(u1.firstname,' ' ,u1.lastname) as username FROM confidentialdocaccess da,user u,user u1 "
													+ " where da.userid=u.userid and da.createdby=u1.userid";


	public static String getBrochure = " SELECT * FROM files where societyid=? and docsubtypeid=? order by createdon desc,documentid";

	public static String getProjectsByBuilderId = "select * from project where builderid=?";

	public static String getSubProjectsByProjectId = "select * from society where projectid=?";

	public static String verifyConfidentialDocAccess = "select u.* from confidentialdocaccess da, user u where da.userid=u.userid and u.mobileNo=?";

	public static String addTenantToHistory = "update tenant set active=0 where userid=?";

	public static String getTenantByUserId ="select * from tenant where userid=? and active=1";

	public static String getTenantHistory ="select * from tenant where userid=? and active=0";

	public static String getSocietyPolicyDocuments = " SELECT * FROM files where societyid=? and docsubtypeid=995 order by createdon desc,documentid";

	public static String getOnePhotoInfo = "select * from photos where docid=? and isactive=1 and phototype=? limit 1";

	public static String addDeleteAuth = "update user set deleteflag=1 where userid=?";

	public static String removeDeleteAuth = "update user set deleteflag=0 where userid=?";

	public static String getDeleteAuth = "select * from user where deleteflag=1";

	public static String getBuilderBySocietyId="select b.* from builder b,project p,society s where s.projectid=p.projectid and p.builderid=b.builderid and s.societyid=?";

	public static String getProjectBySocietyId="select p.* from project p,society s where s.projectid=p.projectid and s.societyid=?";

	public static String logUserLogin = "insert into loginhistory(userid,logintime,sessionkey,ipaddress) values (?,?,?,?)" ;

	public static String logUserLogout = "update loginhistory set logouttime=? where sessionkey=?";

	public static String getUserProfile = "select * from userprofile where userid=?";
	
	public static String editAdminRole="Update user set userroleid=? where userid=?";

	public static String getAllActiveBuilders = "select * from builder";

	public static String getAllBuildermanagersByBuilderId = "select buildermanagermappingid,builderid,getUsername(userid) as username,createdon,createdby from buildermanagermapping where builderid=?";

	public static String removeBuilderManagerByid = "delete from buildermanagermapping where buildermanagermappingid=?";

	public static String addBuilderManager = "insert into buildermanagermapping(builderid,userid) values (?,?)";

	public static String getBuilderListByManagerid = "select * from buildermanagermapping where userid=?";

	public static String logActionsToDB = "insert into actionlogger(userid,action,payload,actiontype) values (?,?,?,?)";
	
	public static String updateSessionForUser = "update user set sessionkey=?,sessiontime=?,sessionactive=? where userid=?";

	public static String getAllUserActivityLog = "";

	public static String getAllActiveExpenseMaster="select * from IPR_ExpenseMaster where isactive=1";

	public static String saveBillStructure = "insert into IPR_billstructure(billstructurecode,societyid,year,billcycletype,billcyclevalue,createdby) values (?,?,?,?,?,?)";

	public static String saveBillComponents = "insert into IPR_billcomponents(billstructureid,expenseid) values (?,?)";

	public static String fetchOldComponentsForBill = "select GROUP_CONCAT(expenseid) as billcomponents from IPR_billcomponents "
			+ "where billstructureid = (SELECT billstructureid FROM IPR_billstructure "
			+ "where createdon = (select max(createdon) from IPR_billstructure where societyid=? and isactive=1))";

	public static String fetchAllBillsBySocietyId = "select billstructureid,billstructurecode,societyid,year,billcycletype,billcyclevalue,isactive,getUsername(createdby) as username,createdon,isgenerated from IPR_billstructure where societyid=? order by isgenerated desc,billstructurecode desc";

	public static String addBillParamData = "insert into IPR_billparamdata(billstructureid,pcdelayval,pcdelaykey,nocval,op4w,op3w,op2w,sp4w,sp3w,sp2w,mtpsqft,shop,mt3p5bhk,mt3bhk,mt2p5bhk,mt2bhk,mt1p5bhk,mt1bhk,mt1rk,mttype,createdby) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	public static String updateBillParamData= "update IPR_billparamdata set pcdelayval=?,pcdelaykey=?,nocval=?,op4w=?,op3w=?,op2w=?,sp4w=?,sp3w=?,sp2w=?,mtpsqft=?,shop=?,mt3p5bhk=?,mt3bhk=?,mt2p5bhk=?,mt2bhk=?,mt1p5bhk=?,mt1bhk=?,mt1rk=?,mttype=?,createdby=? where billparamdataid=?"; 
	
	public static String getBillParamsByStructureid = "select * from IPR_billparamdata where billstructureid=?";

	public static String getBillCompsByStructid = "select em.expenseid,em.expensename from IPR_ExpenseMaster em ,IPR_billcomponents bc where bc.expenseid=em.expenseid and bc.billstructureid=?";

	public static String deleteAllOldExtraValues = "delete from IPR_billcomponentsdata where billstructureid=?";

	public static String addNewExtraValues = "insert into IPR_billcomponentsdata(billstructureid,expenseid,expensevalue,createdby) values (?,?,?,?)";

	public static String getExistingExtraValsByBillStructId = "select GROUP_CONCAT(concat(expenseid,'=',expensevalue)) from IPR_billcomponentsdata where billstructureid=?";

	public static String getBillStructureById = "select billstructureid,billstructurecode,societyid,year,billcycletype,billcyclevalue,isactive,getUsername(createdby) as username,createdon,isgenerated from IPR_billstructure where billstructureid=?";

	public static String getExpensesBuStructId = " SELECT em.expenseid,em.expensename,bcd.expensevalue FROM IPR_billstructure bs,IPR_billcomponentsdata bcd,IPR_ExpenseMaster em where bcd.billstructureid=bs.billstructureid and bcd.expenseid=em.expenseid and bs.billstructureid=?";

	public static String getLastUserBillByUserId = "SELECT * FROM IPR_Bill where userid = ? and createdon = (SELECT max(createdon) FROM IPR_Bill where userid = ?)";

	public static String insertBillGeneratedHeaders = "insert into IPR_Bill(billstructureid,userid,societyid,createdby) values (?,?,?,?)" ;

	public static String insertBillGeneratedData = "insert into IPR_billdata(billid,componenetname,componenetvalue,createdby) values (?,?,?,?)";

	public static String updateBillGeneratedHeaders = "update IPR_Bill set payamount=? where billid=?" ;

	public static String updateBillStructure = "update IPR_billstructure set isgenerated=? where billstructureid=?";

	public static String getBillByStructureid = "SELECT * FROM IPR_Bill where billstructureid =?";
	
	public static String deleteBillHeadersByStructId = "delete from IPR_Bill where billid=?";

	public static String deleteBillDataByStructId = "delete from IPR_billdata where billid=?";

	public static String getMembersWithBillGeneratedByStructid = "select u.firstname,u.lastname,up.* from user u,userprofile up,IPR_Bill b where up.userid=b.userid and up.userid=u.userid and b.billstructureid=?";

	public static String getBillDataByBillid = "select * from IPR_billdata where billid=?";

	public static String getBillByUseridStrutId = "SELECT * FROM IPR_Bill where billstructureid =? and userid=?";

	public static String fetchAllBillsForUser = "select * from IPR_Bill b,IPR_billstructure bs where bs.billstructureid=b.billstructureid and b.userid=?";

	public static String deleteBillStructureById = "delete from IPR_billstructure where billstructureid =?";

}