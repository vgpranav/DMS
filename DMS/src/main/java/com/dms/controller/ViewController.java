package com.dms.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dms.beans.Builder;
import com.dms.beans.CallReference;
import com.dms.beans.CommitteeMaster;
import com.dms.beans.DocSubType;
import com.dms.beans.Doctype;
import com.dms.beans.Document;
import com.dms.beans.Documentdetails;
import com.dms.beans.FormFields;
import com.dms.beans.GenericBean;
import com.dms.beans.Project;
import com.dms.beans.Society;
import com.dms.beans.SocietyType;
import com.dms.beans.User;
import com.dms.beans.Userprofile;
import com.dms.dao.DocumentDao;
import com.dms.dao.LoginDao;
import com.dms.dao.SocietyDao;
import com.dms.util.CommomUtility;
import com.dms.util.CommonDA;
import com.dms.util.FtpWrapper;


@Controller
public class ViewController
{
  public ViewController() {}
  
  private static final Logger logger = LoggerFactory.getLogger(ViewController.class);
  
  @RequestMapping(value={"/systemcheck"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public String systemcheck() {
	  try{
		  
		  	String SQLDriver = CommonDA.getProperties().getProperty("SQLDriver").trim();
			String SQLURL = CommonDA.getProperties().getProperty("SQLURL").trim();
			String SQLUsername = CommonDA.getProperties().getProperty("SQLUsername").trim();
			String SQLPassword = CommonDA.getProperties().getProperty("SQLPassword").trim();
			
			  System.out.println("Performing System check\n\n\n");
			  System.out.println("\n\nConnecting DB..");
			  System.out.println("\n\n"+SQLDriver);
			  System.out.println("\n\n"+SQLURL);
			  System.out.println("\n\n"+SQLUsername);
			  System.out.println("\n\n"+SQLPassword);
			  
			  Class.forName(SQLDriver);
			  
			  Connection con = DriverManager.getConnection(SQLURL,SQLUsername,SQLPassword);  
			  
			  System.out.println("\n\nConn Is Open"+!con.isClosed());
			  
			  System.out.println("\n\nClosing Connection");
	
			  con.close();
			  
			  FtpWrapper ftp = new FtpWrapper();
			  
			  String hostDomain = ftp.getServerName();
			  String Id = ftp.getUsername();
			  String Password = ftp.getPassword();
			  
			  System.out.println("\n\nFTP Connection :: "+ftp.connectAndLogin(hostDomain, Id, Password));
			  
			  System.out.println("\n\nWorking Directory is :: "+ftp.printWorkingDirectory());
			  
			  return "Connection Successful at "+new Date();
		  
	  }catch(Exception e){
		  e.printStackTrace();
		  return "Db Connection Failed";
	  }
  }
  
  @RequestMapping(value={"/welcome"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public String getWelcomePage() {
    return "welcome";
  }
  
  @RequestMapping(value={"/login"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public String getLoginPage() {
    return "login";
  }
  
  @RequestMapping(value={"/logout"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public String getLogout() {
    return "login";
  }
  
  @RequestMapping(value={"/showHomepage"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView showHomepage(ModelMap model, @ModelAttribute User user, HttpServletRequest request, HttpServletResponse response)
  {
	  ModelAndView mv = null;
	  User authenticatedUser = null;
	  Userprofile userprofile = new Userprofile();
	  SocietyDao societyDao = new SocietyDao();
	  DocumentDao ddao = new DocumentDao();
	  List<DocSubType> docSubType = null;
	  try {
		  authenticatedUser = (User)request.getSession().getAttribute("userObject");
		  request.getSession().setAttribute("userObject", authenticatedUser);
		  userprofile.setUserid(authenticatedUser.getUserid());
		  userprofile = societyDao.getUserDataById(userprofile);
		  
		  docSubType = ddao.getDocStubtypesToDispay(docSubType,userprofile.getSocietyid());
		  
		  mv = new ModelAndView("home");
		  mv.addObject("userprofile", userprofile);
		  mv.addObject("docSubType", docSubType);
		  
		  //System.out.println("userprofile :: "+userprofile);
	  } catch (Exception e) {
	      logger.error(e.getMessage());
	    }
	   return mv;
  }
  @RequestMapping(value={"/authenticateUser"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView welcome(ModelMap model, @ModelAttribute User user, HttpServletRequest request, HttpServletResponse response)
  {
    LoginDao loginDao = new LoginDao();
    SocietyDao societyddao = new SocietyDao();
    ModelAndView mv = null;
    User authenticatedUser = null;
    Userprofile userprofile = new Userprofile();
    SocietyDao societyDao = new SocietyDao();
    DocumentDao ddao = new DocumentDao();
	  List<DocSubType> docSubType = null;
    try {
      authenticatedUser = loginDao.authenticateUser(user, authenticatedUser);
      if (authenticatedUser != null) {
        if (authenticatedUser.getActive() == 1) {
          mv = new ModelAndView("home");
          request.getSession().setAttribute("userObject", authenticatedUser);
          userprofile.setUserid(authenticatedUser.getUserid());
		  userprofile = societyDao.getUserDataById(userprofile);
		  docSubType = ddao.getDocStubtypesToDispay(docSubType,userprofile.getSocietyid());
		  
		  request.getSession().setAttribute("userprofile", userprofile);
		  mv.addObject("docSubType", docSubType);
		  
          List<HashMap<String, Object>> photos = new ArrayList<HashMap<String, Object>>();
          photos =  societyddao.getSocietyPhotos(authenticatedUser.getUserid(),"user","UserImages",photos);
          
          if(photos.size()>0){
        	  String imgBase64 = (String) photos.get(0).get("file");
              String imgContentType = (String) photos.get(0).get("contenttype");
              request.getSession().setAttribute("imgBase64", imgBase64);
              request.getSession().setAttribute("imgContentType", imgContentType);
          }
          
          long isSocManager = societyddao.checkIfSocietyManager(authenticatedUser.getUserid());
          
          Society soc = new Society();
          soc.setSocietyid(Long.valueOf(userprofile.getSocietyid()));
          String modNAme="";
          soc = societyddao.getSocietyDetailsById(soc);
          
          if(soc.getSocietytypeid()==2)
        	  modNAme = "CS - DMS";
          else
        	  modNAme="HS - DMS";
          
          request.getSession().setAttribute("userroleid", authenticatedUser.getUserroleid());
          request.getSession().setAttribute("socmanagercount",isSocManager);
          request.getSession().setAttribute("modNAme",modNAme);
          mv.addObject("userprofile", userprofile);
          
          boolean newNoticeAdded = societyddao.checkIfNewNoticeAdded(userprofile.getSocietyid());
          mv.addObject("newNoticeAdded", newNoticeAdded);
        } else {
          mv = new ModelAndView("login");
          request.setAttribute("errorMessage", "Account is Locked. Please contact Administrator");
        }
      } else {
        mv = new ModelAndView("login");
        request.setAttribute("errorMessage", "Invalid Username/Password");
      }
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return mv;
  }
  
  @RequestMapping(value={"/addSociety"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView addSociety()
  {
    ModelAndView mv = null;
    List<SocietyType> socTypes = null;
    SocietyDao documentDao = new SocietyDao();
    List<Society>  societyList = new ArrayList<Society>();
    List<Project>  projectList = null;

    try {
        socTypes = documentDao.getAllActiveSocietyTypes(socTypes);
        societyList = documentDao.getAllSociety(societyList,"society");
        projectList = documentDao.getAllProjects(projectList);
        
      mv = new ModelAndView("addSociety");
      mv.addObject("societytypeList", socTypes);
      mv.addObject("projectList", projectList);
      mv.addObject("societyList", societyList);
      
      mv.addObject("societytype","society");

      
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return mv;
  }

  @RequestMapping(value={"/createSubProject"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView createSubProject()
  {
    ModelAndView mv = null;
    List<SocietyType> socTypes = null;
    SocietyDao documentDao = new SocietyDao();
    List<Society>  societyList = new ArrayList<Society>();
    List<Project>  projectList = null;

    try {
        socTypes = documentDao.getAllActiveSocietyTypes(socTypes);
        societyList = documentDao.getAllSociety(societyList,"subproject");
        projectList = documentDao.getAllProjects(projectList);
        
      mv = new ModelAndView("addSociety");
      mv.addObject("societytypeList", socTypes);
      mv.addObject("projectList", projectList);
      mv.addObject("societyList", societyList);

      mv.addObject("societytype","subproject");

      
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return mv;
  }
  
  
  
  
  @RequestMapping(value={"/saveSociety"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView saveSociety(@ModelAttribute Society society,HttpServletRequest request)
  {
    ModelAndView mv = null;
    List<SocietyType> socTypes = null;
    List<Society>  societyList = new ArrayList<Society>();
    List<Project>  projectList = null;
    SocietyDao documentDao = new SocietyDao();
    User user = null;
    
    try {
      user = (User)request.getSession().getAttribute("userObject");
      society = documentDao.insertOrUpdateSociety(society,user.getUserid());
      socTypes = documentDao.getAllActiveSocietyTypes(socTypes);
      
      
      societyList = documentDao.getAllSociety(societyList,society.getSocietytype());
      
      
      projectList = documentDao.getAllProjects(projectList);
      
      mv = new ModelAndView("addSociety");
      mv.addObject("societytypeList", socTypes);
      mv.addObject("societyList", societyList);
      mv.addObject("projectList", projectList);
      mv.addObject("error", "Society Added");
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return mv;
  }
  
 
  @RequestMapping(value={"/addDoctype"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView addDoctype() {
    ModelAndView mv = null;
    List<Doctype> docTypes = null;
    DocumentDao documentDao = new DocumentDao();
    try {
      docTypes = documentDao.getAllDocumentTypes(docTypes);
      mv = new ModelAndView("addDoctype");
      mv.addObject("docTypesList", docTypes);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return mv;
  }
  



  @RequestMapping(value={"/saveDocumentType"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView saveDocumentType(@ModelAttribute Doctype doctype,HttpServletRequest request)
  {
    ModelAndView mv = null;
    List<Doctype> docTypes = null;
    DocumentDao documentDao = new DocumentDao();
    User user = null;
    try {
      user = (User)request.getSession().getAttribute("userObject");
      doctype.setCreatedby(String.valueOf(user.getUserid()));
      doctype = documentDao.insertOrUpdateDocType(doctype);
      docTypes = documentDao.getAllDocumentTypes(docTypes);
      mv = new ModelAndView("addDoctype");
      mv.addObject("docTypesList", docTypes);
      mv.addObject("error", "Doctype Added");
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return mv;
  }
  
  @RequestMapping(value={"/addDocSubType"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView addDocSubType() {
    ModelAndView mv = null;
    List<Doctype> docTypes = null;
    List<DocSubType> docSubType = null;
    DocumentDao documentDao = new DocumentDao();
    try {
      docSubType = documentDao.getAllDocumentSubTypes(docSubType);
      docTypes = documentDao.getAllDocumentTypes(docTypes);
      mv = new ModelAndView("addDocSubtype");
      mv.addObject("docSubTypeList", docSubType);
      mv.addObject("docTypesList", docTypes);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return mv;
  }
  




  @RequestMapping(value={"/saveDocumentSubType"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView saveDocumentType(@ModelAttribute DocSubType docSubType,HttpServletRequest request)
  {
    ModelAndView mv = null;
    List<Doctype> docTypes = null;
    List<DocSubType> docSubTypes = null;
    User user = null;
    DocumentDao documentDao = new DocumentDao();
    try {
      user = (User)request.getSession().getAttribute("userObject");
      docSubType.setCreatedby(String.valueOf(user.getUserid()));
      docSubType = documentDao.insertOrUpdateDocSubType(docSubType);
      docTypes = documentDao.getAllDocumentTypes(docTypes);
      docSubTypes = documentDao.getAllDocumentSubTypes(docSubTypes);
      mv = new ModelAndView("addDocSubtype");
      mv.addObject("docTypesList", docTypes);
      mv.addObject("docSubTypeList", docSubTypes);
      mv.addObject("error", "Doc Sub Type Added");
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return mv;
  }
  
  @RequestMapping(value={"/addFormFields"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView addFormFields() {
    ModelAndView mv = null;
    List<DocSubType> docSubType = null;
    List<Integer> seqList = new ArrayList();
    DocumentDao documentDao = new DocumentDao();
    try {
      docSubType = documentDao.getAllDocumentSubTypes(docSubType);
      mv = new ModelAndView("addFormFields");
      mv.addObject("docSubTypeList", docSubType);
      
      int maxConfigval = Integer.parseInt(CommomUtility.getConfig("maxseqno"));
      for (int i = 1; i <= maxConfigval; i++) {
        seqList.add(Integer.valueOf(i));
      }
      
      mv.addObject("seqList", seqList);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return mv;
  }
  
  @RequestMapping(value={"/addDocument1"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView addDocument1(HttpServletRequest request) {
    ModelAndView mv = null;
    List<Society> societyList = null;
    User user = null;
    SocietyDao societyDao = new SocietyDao();
    try {
      user = (User)request.getSession().getAttribute("userObject");
      societyList = societyDao.getSocietyListForManager(user.getUserid(), societyList);
      mv = new ModelAndView("addDocument1");
      mv.addObject("societyList", societyList);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return mv;
  }
  
  @RequestMapping(value={"/addDocument2"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView addDocument2(@ModelAttribute Document document) {
    ModelAndView mv = null;
    List<FormFields> formFields = null;
    List<Documentdetails> documentdetails = null;
    
    //System.out.println("document ::> "+document);
    
    DocumentDao documentDao = new DocumentDao();
    try {
    	
      if(document.getUserid()==0){
    	  document.setUserid(9999);
      }
      
      formFields = documentDao.getFieldsForDocSubtype(document.getDocsubtypeid(), formFields);
      
      documentdetails = documentDao.getExistingDocumentDetails(document,documentdetails);
      
      
      mv = new ModelAndView("addDocument2");
      
      if(documentdetails!=null){
    	  mv.addObject("dataExists",true);
    	  mv.addObject("documentdetails",documentdetails);
      } else {
    	  mv.addObject("dataExists",false);
      }
      
      mv.addObject("formFieldsList", formFields);
      mv.addObject("document", document);
    }
    catch (Exception e) {
      logger.error(e.getMessage());
    }
    return mv;
  }
  
  @RequestMapping(value={"/addDocument3"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView addDocument3(@org.springframework.web.bind.annotation.RequestParam Map<String, String> params,HttpServletRequest request) {
    long detailsSaved = 0L;
    User user = null;
    DocumentDao ddao = new DocumentDao();
    
    ModelAndView mv = null;
    try
    {
      user = (User)request.getSession().getAttribute("userObject");
      
      boolean gotDocId=false;
    	
      if (params.containsKey("documentid")){
    	  String docId = params.get("documentid");
    	  if(docId!=null) {
    		  if(docId.trim().length()>0){
    			  if(!docId.trim().equals("0")){
    				  detailsSaved=Long.parseLong(docId);
    				  gotDocId=true;
    			  }
    		  }
    	  }
      }
      
      if(!gotDocId)
    	  detailsSaved = ddao.saveDocumentHeadAndDetails(params,user);
      
      mv = new ModelAndView("addDocument3");
      if (params.containsKey("societyid"))
        mv.addObject("societyid", params.get("societyid"));
      if (params.containsKey("doctypeid"))
        mv.addObject("doctypeid", params.get("doctypeid"));
      if (params.containsKey("docsubtypeid")) {
        mv.addObject("docsubtypeid", params.get("docsubtypeid"));
      }
      mv.addObject("documentId", Long.valueOf(detailsSaved));
    }
    catch (Exception e) {
      logger.error(e.getMessage());
    }
    return mv;
  }
  
  @RequestMapping(value={"/viewDocument"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView viewDocument() {
    ModelAndView mv = null;
    List<Society> societyList = null;
    SocietyDao societyDao = new SocietyDao();
    try {
      societyList = societyDao.getSocietyListforUser(societyList);
      mv = new ModelAndView("viewDocument");
      mv.addObject("societyList", societyList);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return mv;
  }
  
  @RequestMapping(value={"/addMember"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView addMember(HttpServletRequest request) {
    ModelAndView mv = null;
    List<Society> societyList = null;
    SocietyDao societyDao = new SocietyDao();
    User user = null;
    try {
    	user = (User)request.getSession().getAttribute("userObject");
    	societyList = societyDao.getSocietyListForManager(user.getUserid(), societyList);
    	mv = new ModelAndView("addMember");
    	mv.addObject("societyList", societyList);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return mv;
  }
  
  @RequestMapping(value={"/createCommittee"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView createCommittee(HttpServletRequest request) {
    ModelAndView mv = null;
    List<Society> societyList = null;
    List<CommitteeMaster> committeeMasterList = null;
    SocietyDao societyDao = new SocietyDao();
    User user = null;
    try {
      user = (User)request.getSession().getAttribute("userObject");
      societyList = societyDao.getSocietyListForManager(user.getUserid(), societyList);
      committeeMasterList = societyDao.getCommitteeMaster(committeeMasterList);
      mv = new ModelAndView("createCommittee");
      mv.addObject("societyList", societyList);
      mv.addObject("committeeMasterList", committeeMasterList);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return mv;
  }
  
  @RequestMapping(value={"/displayAdminPanel"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView displayAdminPanel(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mv = null;
    User user = null;
    SocietyDao sdao = new SocietyDao();
    List<Society> societyList = null;
    List<GenericBean> docs = null;
    
    try {
      user = (User)request.getSession().getAttribute("userObject");
      societyList = sdao.getSocietyListForManager(user.getUserid(), societyList);
      
      
      if (societyList.size() == 1) {
        mv = new ModelAndView("adminPanel");
        
        docs = sdao.getAllExistingDocsForSoc((Society)societyList.get(0),docs);
        
        mv.addObject("society", (Society)societyList.get(0));
        mv.addObject("docs", docs);
      }
      else {
        mv = new ModelAndView("SocietySelectionForAdmin");
        mv.addObject("societyList",societyList);
      }
    } catch (Exception e) { logger.error(e.getMessage());
      e.printStackTrace();
    }
    return mv;
  }
  
  
  @RequestMapping(value={"/displayAdminPanelBySocId"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView displayAdminPanelBySocId(@ModelAttribute Society society,HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mv = null;
    User user = null;
    SocietyDao sdao = new SocietyDao();
    List<Society> societyList = null;
    List<GenericBean> docs = null;
    
    try {
      user = (User)request.getSession().getAttribute("userObject");
      societyList = sdao.getSocietyListForManager(user.getUserid(), societyList);
      
      for(Society soc1 : societyList){
    	  if(soc1.getSocietyid()==society.getSocietyid()){
    		  society = soc1;
    	  }
      }
      
       
        mv = new ModelAndView("adminPanel");
        docs = sdao.getAllExistingDocsForSoc(society,docs);
        mv.addObject("society",society);
        mv.addObject("docs", docs);
        
    } catch (Exception e) { logger.error(e.getMessage());
      e.printStackTrace();
    }
    return mv;
  }
  
  
  
  @RequestMapping(value={"/viewCommittee"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView viewCommittee(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mv = null;
    try {
      mv = new ModelAndView("viewCommittee");
      mv.addObject("societyid", "8");
    }
    catch (Exception e) {
      logger.error(e.getMessage());
    }
    return mv;
  }
  
  @RequestMapping(value={"/addSocietyPhotos"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView addSocietyPhotos(HttpServletRequest request, HttpServletResponse response)
  {
    ModelAndView mv = null;
    List<Society> societyList = null;
    User user = null;
    SocietyDao sdao = new SocietyDao();
    try
    {
      user = (User)request.getSession().getAttribute("userObject");
      societyList = sdao.getSocietyListForManager(user.getUserid(), societyList);
      mv = new ModelAndView("addSocietyPhotos");
      mv.addObject("societyList", societyList);
    }
    catch (Exception e) {
      logger.error(e.getMessage());
    }
    return mv;
  }
  
  
  
  
  @RequestMapping(value={"/createVendor"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView createVendor(HttpServletRequest request, HttpServletResponse response)
  {
    ModelAndView mv = null;
    List<Society> societyList = null;
    User user = null;
    SocietyDao sdao = new SocietyDao();
    try
    {
      user = (User)request.getSession().getAttribute("userObject");
      societyList = sdao.getSocietyListForManager(user.getUserid(), societyList);
      mv = new ModelAndView("createVendor");
      mv.addObject("societyList", societyList);
    }
    catch (Exception e) {
      logger.error(e.getMessage());
    }
    return mv;
  }

  
  @RequestMapping(value={"/societyManagerMapping"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView societyManagerMapping() {
    ModelAndView mv = null;
    List<Society> societyList = null;
    List<CommitteeMaster> committeeMasterList = null;
    SocietyDao societyDao = new SocietyDao();
    try {
      societyList = societyDao.getSocietyListforUser(societyList);
      committeeMasterList = societyDao.getCommitteeMaster(committeeMasterList);
      mv = new ModelAndView("societyManagerMapping");
      mv.addObject("societyList", societyList);
      mv.addObject("committeeMasterList", committeeMasterList);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return mv;
  }
  
  @RequestMapping(value={"/createBuilder"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView createBuilder() {
    ModelAndView mv = null;
    SocietyDao documentDao = new SocietyDao();
    List<Builder>  builderList = null;
    try {
    	builderList = documentDao.getAllBuilder(builderList);
    	mv = new ModelAndView("createBuilder");
    	 mv.addObject("builderList", builderList);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return mv;
  }
  
  @RequestMapping(value={"/saveBuilder"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView saveBuilder(@ModelAttribute Builder builder,HttpServletRequest request)
  {
    ModelAndView mv = null;
    List<Builder>  builderList = null;
    SocietyDao documentDao = new SocietyDao();
    User user = null;
    try {
      user = (User)request.getSession().getAttribute("userObject");
      builder.setCreatedby(String.valueOf(user.getUserid()));
      builder = documentDao.insertOrUpdateBuilder(builder);
      builderList = documentDao.getAllBuilder(builderList);
      
      mv = new ModelAndView("createBuilder");
      mv.addObject("builderList", builderList);
      mv.addObject("error", "Builder Profile Added");
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return mv;
  }
  
  @RequestMapping(value={"/createProject"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView createProject() {
    ModelAndView mv = null;
    SocietyDao documentDao = new SocietyDao();
    List<Builder>  builderList = null;
    List<Project>  projectList = null;
    try {
    	builderList = documentDao.getAllBuilder(builderList);
    	projectList = documentDao.getAllProjects(projectList);
    			
     mv = new ModelAndView("createProject");
   	 mv.addObject("builderList", builderList);
	 mv.addObject("projectList", projectList);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return mv;
  }
  
  @RequestMapping(value={"/saveProject"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView saveProject(@ModelAttribute Project project,HttpServletRequest request)
  {
    ModelAndView mv = null;
    List<Builder>  builderList = null;
    List<Project>  projectList = null;
    SocietyDao documentDao = new SocietyDao();
    User user = null;
    
    try {
      user = (User)request.getSession().getAttribute("userObject");
      project.setCreatedby(String.valueOf(user.getUserid()));
      project = documentDao.insertOrUpdateProject(project);
      
      projectList = documentDao.getAllProjects(projectList);
      builderList = documentDao.getAllBuilder(builderList);
      
      mv = new ModelAndView("createProject");
      mv.addObject("builderList", builderList);
 	  mv.addObject("projectList", projectList);
 
      mv.addObject("error", "Project Added");
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return mv;
  }
  
  
  @RequestMapping(value={"/societyDocumentMapping"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView societyDocumentMapping() {
    ModelAndView mv = null;
    List<Society> societyList = null;
    List<Doctype> docTypes = null;
    List<GenericBean> socDocMapping = null;
    SocietyDao societyDao = new SocietyDao();
    DocumentDao documentDao = new DocumentDao();
    try {
      docTypes = documentDao.getAllDocumentTypes(docTypes);
      societyList = societyDao.getSocietyListforUser(societyList);
      socDocMapping = societyDao.getSocDocMapping(socDocMapping);
      
      mv = new ModelAndView("societyDocumentMapping");
      mv.addObject("societyList", societyList);
      mv.addObject("docTypes", docTypes);
      mv.addObject("socDocMapping",socDocMapping);
      
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return mv;
  }
  
  
  @RequestMapping(value={"/addSocDocMapping"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView addSocDocMapping(@ModelAttribute GenericBean bean,HttpServletRequest request)
  {
    ModelAndView mv = null;
    List<Society> societyList = null;
    List<Doctype> docTypes = null;
    List<GenericBean> socDocMapping = null;
    SocietyDao societyDao = new SocietyDao();
    DocumentDao documentDao = new DocumentDao();
    User user = null;
    
    try {
      user = (User)request.getSession().getAttribute("userObject");
      bean.setCreatedby(String.valueOf(user.getUserid()));
      bean = documentDao.insertSocDocMapping(bean);
      
      docTypes = documentDao.getAllDocumentTypes(docTypes);
      societyList = societyDao.getSocietyListforUser(societyList);
      socDocMapping = societyDao.getSocDocMapping(socDocMapping);
      
      mv = new ModelAndView("societyDocumentMapping");
      mv.addObject("societyList", societyList);
      mv.addObject("docTypes", docTypes);
      mv.addObject("socDocMapping", socDocMapping);
 
      mv.addObject("error", "Mapping Added");
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return mv;
  }
  
  @RequestMapping(value={"/createAdminUser"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView createAdminUser() {
    ModelAndView mv = null;
    SocietyDao documentDao = new SocietyDao();
    List<GenericBean>  roleList = null;
    List<User>  adminUsers = null;

    try {
    	roleList = documentDao.getAllRoles(roleList);
    	adminUsers = documentDao.getAllAdminUsers(adminUsers);
    			
    	mv = new ModelAndView("createAdminUser");
    	mv.addObject("roleList", roleList);
    	mv.addObject("adminUsers", adminUsers);
     
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return mv;
  }
  
  @RequestMapping(value={"/saveAdminUser"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView saveAdminUser(@ModelAttribute User adminUser,HttpServletRequest request)
  {
    ModelAndView mv = null;
    List<GenericBean>  roleList = null;
    SocietyDao documentDao = new SocietyDao();
    User user = null;
    List<User>  adminUsers = null;

    try {
      user = (User)request.getSession().getAttribute("userObject");
      adminUser.setCreatedby(String.valueOf(user.getUserid()));
      adminUser = documentDao.saveAdminUser(adminUser);
      adminUsers = documentDao.getAllAdminUsers(adminUsers);
      
      roleList = documentDao.getAllRoles(roleList);
      
      mv = new ModelAndView("createAdminUser");
      mv.addObject("roleList", roleList);
  	  mv.addObject("adminUsers", adminUsers);

      mv.addObject("error", "User Added");
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return mv;
  }
  
  
  @RequestMapping(value={"/addMemberPhotos"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView addMemberPhotos(HttpServletRequest request, HttpServletResponse response)
  {
    ModelAndView mv = null;
    List<Society> societyList = null;
    User user = null;
    SocietyDao sdao = new SocietyDao();
    try
    {
      user = (User)request.getSession().getAttribute("userObject");
      societyList = sdao.getSocietyListForManager(user.getUserid(), societyList);
      mv = new ModelAndView("addMemberPhotos");
      mv.addObject("societyList", societyList);
    }
    catch (Exception e) {
      logger.error(e.getMessage());
    }
    return mv;
  }
  
  @RequestMapping(value={"/addNotice"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView addNotice(HttpServletRequest request, HttpServletResponse response)
  {
    ModelAndView mv = null;
    List<Society> societyList = null;
    User user = null;
    SocietyDao sdao = new SocietyDao();
    try
    {
      user = (User)request.getSession().getAttribute("userObject");
      societyList = sdao.getSocietyListForManager(user.getUserid(), societyList);
      mv = new ModelAndView("addNotice");
      mv.addObject("societyList", societyList);
    }
    catch (Exception e) {
      logger.error(e.getMessage());
    }
    return mv;
  }
  
  @RequestMapping(value={"/viewNoticeboard"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView viewNoticeboard(@ModelAttribute Society society,HttpServletRequest request, HttpServletResponse response)
  {
	    ModelAndView mv = null;
	    List<HashMap<String, Object>> docList = new ArrayList<HashMap<String, Object>>();
	    SocietyDao sdao = new SocietyDao();
	    try
	    {
	      docList = sdao.getNoticeboardDocs(society.getSocietyid(), docList);
	      mv = new ModelAndView("viewNoticeboard");
	      mv.addObject("docList", docList);
	    }
	    catch (Exception e) {
	      logger.error(e.getMessage());
	    }
	    return mv;
	  }
  
  @RequestMapping(value={"/displaySelfSociety"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView displaySelfSociety(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mv = null;
    Userprofile userprofile = null;
    SocietyDao sdao = new SocietyDao();
    Society society = new Society();
    try {
    	userprofile = (Userprofile)request.getSession().getAttribute("userprofile");
      society.setSocietyid(Long.valueOf(userprofile.getSocietyid()));
      society = sdao.getSocietyDetailsById(society);
        mv = new ModelAndView("selfSociety");
        mv.addObject("society",society);
    } catch (Exception e) { logger.error(e.getMessage());
      e.printStackTrace();
    }
    return mv;
  }
  
  @RequestMapping(value={"/displayDocument"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView displayDocument(@RequestParam("doctypeid") String doctypeid,
		  @RequestParam("userid") String userid,
		  HttpServletRequest request, HttpServletResponse response)
        {
	    ModelAndView mv = null;
	    List<HashMap<String, Object>> docList = new ArrayList<HashMap<String, Object>>();
	    List<GenericBean> data = null;
	    SocietyDao sdao = new SocietyDao();
	    DocumentDao ddao = new DocumentDao();
	    try
	    {
	      docList = sdao.displayDocument(doctypeid,userid, docList);
	      data = ddao.getdisplayData(doctypeid,userid,data);
	      
	      mv = new ModelAndView("displayDocument");
	      mv.addObject("docList", docList);
	      mv.addObject("dataList", data);
	    }
	    catch (Exception e) {
	      logger.error(e.getMessage());
	    }
	    return mv;
	  }
  
  @RequestMapping(value={"/displayDocumentFromSearch"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView displayDocumentFromSearch(@RequestParam("documentid") String documentid,
		  HttpServletRequest request, HttpServletResponse response)
        {
	    ModelAndView mv = null;
	    List<HashMap<String, Object>> docList = new ArrayList<HashMap<String, Object>>();
	    List<GenericBean> data = null;
	    SocietyDao sdao = new SocietyDao();
	    DocumentDao ddao = new DocumentDao();
	    try
	    {
	      docList = sdao.displayDocumentFromSearch(documentid,docList);
	      data = ddao.getdisplayDataByDocId(documentid, data);
	      
	      mv = new ModelAndView("displayDocument");
	      mv.addObject("docList", docList);
	      mv.addObject("dataList", data);
	    }
	    catch (Exception e) {
	      logger.error(e.getMessage());
	    }
	    return mv;
	  }
  
  
  @RequestMapping(value={"/showDocFromAdminPanel"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView showDocFromAdminPanel(@ModelAttribute GenericBean bean,HttpServletRequest request, HttpServletResponse response)
        {
	    ModelAndView mv = null;
	    try
	    {
	      mv = new ModelAndView("viewDocumentFromAdminPanel");
	      mv.addObject("societyid", bean.getSocietyid());
	      mv.addObject("doctypeid", bean.getDoctypeid());
	      mv.addObject("docsubtypeid", bean.getDocsubtypeid());
	    }
	    catch (Exception e) {
	      logger.error(e.getMessage());
	    }
	    return mv;
	  } 
  
  
  @RequestMapping(value={"/createVendorCards"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView createVendorCards(HttpServletRequest request, HttpServletResponse response)
  {
    ModelAndView mv = null;
    List<Society> societyList = null;
    User user = null;
    SocietyDao sdao = new SocietyDao();
    try
    {
      user = (User)request.getSession().getAttribute("userObject");
      societyList = sdao.getSocietyListForManager(user.getUserid(), societyList);
      mv = new ModelAndView("createVendorCards");
      mv.addObject("societyList", societyList);
    }
    catch (Exception e) {
      logger.error(e.getMessage());
    }
    return mv;
  }
  
  @RequestMapping(value={"/addCallRef"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView addCallRef(HttpServletRequest request, HttpServletResponse response)
  {
    ModelAndView mv = null;
    User user = null;
    SocietyDao sdao = new SocietyDao();
    try
    {
    	SimpleDateFormat sf = new SimpleDateFormat("YYMMd");
      String callrefid = "ODS"+sf.format(new Date())+RandomStringUtils.randomNumeric(5);
      user = (User)request.getSession().getAttribute("userObject");
      mv = new ModelAndView("AddCallReference");
      mv.addObject("callrefid",callrefid);
    }
    catch (Exception e) {
      logger.error(e.getMessage());
    }
    return mv;
  }
  

  @RequestMapping(value={"/saveCallRef"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView saveCallRef(@ModelAttribute CallReference callref,HttpServletRequest request, HttpServletResponse response)
  {
    ModelAndView mv = null;
    SocietyDao documentDao = new SocietyDao();

    try {
    
      callref = documentDao.saveCallRef(callref);
    
      mv = new ModelAndView("AddCallReference2");
      
      SimpleDateFormat sf = new SimpleDateFormat("YYMMd");
      String callrefid = "ODS"+sf.format(new Date())+RandomStringUtils.randomNumeric(5);
      
      mv.addObject("callrefid",callrefid);
      mv.addObject("callref",callref);
      mv.addObject("error","Call Reference Added");
      
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return mv;
  }
  
  
  @RequestMapping(value={"/saveCallRef3.do"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView saveCallRef3(@ModelAttribute CallReference callref,HttpServletRequest request, HttpServletResponse response)
  {
	    ModelAndView mv = null;
	    try {
	      mv = new ModelAndView("AddCallReference3");
	      mv.addObject("callref",callref);
	      mv.addObject("error","Contact Added");
	      
	    } catch (Exception e) {
	      logger.error(e.getMessage());
	    }
	    return mv;
	  }
  
  @RequestMapping(value={"/viewAllCallRef"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView viewAllCallRef(HttpServletRequest request, HttpServletResponse response)
  {
    ModelAndView mv = null;
    User user = null;
    List<CallReference> calls=null;
    SocietyDao sdao = new SocietyDao();
    try
    {
      user = (User)request.getSession().getAttribute("userObject");
      
      calls = sdao.getAllCallRefs(calls);
      
      mv = new ModelAndView("viewAllCallRef");
      mv.addObject("calls",calls);
    }
    catch (Exception e) {
      logger.error(e.getMessage());
    }
    return mv;
  }
  
  
  @RequestMapping(value={"/getRefContactpage"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView getRefContactpage(@RequestParam("callrefid") long callrefid ,HttpServletRequest request, HttpServletResponse response)
  {
	  CallReference callref = new CallReference();
	  callref.setCallrefid(callrefid);
	  
    ModelAndView mv = null;
    try {
    
      mv = new ModelAndView("AddCallReference2");
      mv.addObject("callref",callref);
      
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return mv;
  }
  
  
  @RequestMapping(value={"/getRefMeetingpage.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView getRefMeetingpage(@RequestParam("callrefid") long callrefid ,HttpServletRequest request, HttpServletResponse response)
  {
	    ModelAndView mv = null;
	    try {
	    	
	    	 CallReference callref = new CallReference();
	   	  callref.setCallrefid(callrefid);
	      mv = new ModelAndView("AddCallReference3");
	      mv.addObject("callref",callref);
	    } catch (Exception e) {
	      logger.error(e.getMessage());
	    }
	    return mv;
	  }
  
  
  @RequestMapping(value={"/editUserProfile"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView editUserProfile(HttpServletRequest request) {
    ModelAndView mv = null;
    List<Society> societyList = null;
    SocietyDao societyDao = new SocietyDao();
    User user = null;
    try {
    	user = (User)request.getSession().getAttribute("userObject");
    	societyList = societyDao.getSocietyListForManager(user.getUserid(), societyList);
    	mv = new ModelAndView("editUserProfile");
    	mv.addObject("societyList", societyList);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return mv;
  }
  
  @RequestMapping(value={"/deleteDocument"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView deleteDocument() {
    ModelAndView mv = null;
    List<Society> societyList = null;
    SocietyDao societyDao = new SocietyDao();
    try {
      societyList = societyDao.getSocietyListforUser(societyList);
      mv = new ModelAndView("deleteDocument");
      mv.addObject("societyList", societyList);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return mv;
  }
  
  
  @RequestMapping(value={"/managerDocViewAuth"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView managerDocViewAuth(HttpServletRequest request) {
    ModelAndView mv = null;
    List<Society> societyList = null;
    List<Doctype> docTypes = null;
    List<GenericBean> socDocMapping = null;
    SocietyDao societyDao = new SocietyDao();
    DocumentDao documentDao = new DocumentDao();
    User user = null;
    try {
      
    	  user = (User)request.getSession().getAttribute("userObject");
	      docTypes = documentDao.getAllDocumentTypes(docTypes);
	      societyList = societyDao.getSocietyListForManager(user.getUserid(), societyList);
	      socDocMapping = societyDao.getSocDocMapping(socDocMapping);
	      
	      mv = new ModelAndView("managerDocViewAuth");
	      mv.addObject("societyList", societyList);
	      mv.addObject("docTypes", docTypes);
	      mv.addObject("socDocMapping",socDocMapping);
      
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return mv;
  }
  
  
  @RequestMapping(value={"/manageDesignation"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView manageDesignation() {
    ModelAndView mv = null;
    List<GenericBean> desigList = null;
    SocietyDao societyDao = new SocietyDao();
    try {
    	desigList = societyDao.getAllDesignations(desigList);
      mv = new ModelAndView("manageDesignation");
      mv.addObject("desigList", desigList);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return mv;
  }
  
  
}


