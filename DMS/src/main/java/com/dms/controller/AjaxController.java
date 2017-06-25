package com.dms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.dms.beans.Builder;
import com.dms.beans.CallReference;
import com.dms.beans.Committee;
import com.dms.beans.DocSubType;
import com.dms.beans.Doctype;
import com.dms.beans.Document;
import com.dms.beans.EmailBean;
import com.dms.beans.Files;
import com.dms.beans.FormFields;
import com.dms.beans.GenericBean;
import com.dms.beans.Parking;
import com.dms.beans.Project;
import com.dms.beans.Society;
import com.dms.beans.User;
import com.dms.beans.UserSCNominee;
import com.dms.beans.Userprofile;
import com.dms.beans.Vendor;
import com.dms.dao.DocumentDao;
import com.dms.dao.LoginDao;
import com.dms.dao.SocietyDao;

@Controller
public class AjaxController
{
  private static final Logger reqreslogger = LoggerFactory.getLogger("reqreslogger");
	
  public AjaxController() {}
  
  private static final Logger logger = LoggerFactory.getLogger(AjaxController.class);
  
  @RequestMapping(value={"/societyAutosuggest"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, headers={"Accept=*/*"}, produces={"application/json"})
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public List<String> societyAutosuggest(@RequestParam("searchText") String searchText) {
	  
	  reqreslogger.info("[REQUEST]"+searchText.toString());
	  
    SocietyDao societyDao = new SocietyDao();
    List<String> codeList1 = new ArrayList();
    try {
      List<Society> societyList = societyDao.getSocietyAutosuggest(searchText);
      for (int i = 0; i < societyList.size(); i++) {
        Society soc = (Society)societyList.get(i);
        String name = soc.getSocietyid() + "--" + soc.getSocietyname();
        codeList1.add(i, name);
      }
    }
    catch (Exception e) {
      logger.error(e.getMessage());
    }
    return codeList1;
  }
  
  
  @RequestMapping(value={"/userAutosuggest"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, headers={"Accept=*/*"}, produces={"application/json"})
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public List<String> userAutosuggest(@RequestParam("searchText") String searchText) {
	  
	  reqreslogger.info("[REQUEST]"+searchText.toString());
	  
    SocietyDao societyDao = new SocietyDao();
    List<String> codeList1 = new ArrayList();
    
    try
    {
      List<User> userList = societyDao.getUserAutosuggestByUsername(searchText);
      for (int i = 0; i < userList.size(); i++) {
        User user = (User)userList.get(i);
        String name = user.getUserid() + "--" + user.getFirstName() + " " + user.getLastName();
        codeList1.add(i, name);
      }
    }
    catch (Exception e) {
      logger.error(e.getMessage());
    }
    return codeList1;
  }
  
  
  @RequestMapping(value={"/userAutosuggestForSociety"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, headers={"Accept=*/*"}, produces={"application/json"})
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public List<String> userAutosuggestForSociety(@RequestParam("searchText") String searchText, @RequestParam("societyid") String societyid) {

	  reqreslogger.info("[REQUEST]"+searchText.toString()+"/"+societyid);
	  
    SocietyDao societyDao = new SocietyDao();
    List<String> codeList1 = new ArrayList();
    
    try
    {
      List<User> userList = societyDao.getUserAutosuggest(searchText, societyid);
      for (int i = 0; i < userList.size(); i++) {
        User user = (User)userList.get(i);
        String name = user.getUserid() + "--" + user.getFirstName() + " " + user.getLastName();
        codeList1.add(i, name);
      }
    }
    catch (Exception e) {
      logger.error(e.getMessage());
    }
    return codeList1;
  }

   
  @RequestMapping(value={"/saveFormFields"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public FormFields saveDocumentType(@ModelAttribute FormFields formFields,HttpServletRequest request)
  {
	  reqreslogger.info("[REQUEST]"+formFields.toString());
	  
    DocumentDao documentDao = new DocumentDao();
    User user = null;
    try {
    	user = (User)request.getSession().getAttribute("userObject");
    	formFields.setCreatedby(String.valueOf(user.getUserid()));
    	formFields = documentDao.insertOrUpdateFormFields(formFields);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return formFields;
  }
  


  @RequestMapping(value={"/getFieldsForDocSubtype"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<FormFields> getFieldsForDocSubtype(@ModelAttribute FormFields formField)
  {
	  
	  reqreslogger.info("[REQUEST]"+formField.toString());
	  
    DocumentDao documentDao = new DocumentDao();
    List<FormFields> formFields = null;
    try {
      formFields = documentDao.getFieldsForDocSubtype(formField.getDocsubtypeid(), formFields);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return formFields;
  }
  


  @RequestMapping(value={"/getDoctypeBySocId"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<Doctype> getDoctypeBySocId(@ModelAttribute Society society)
  {
	  reqreslogger.info("[REQUEST]"+society.toString());
	  
    DocumentDao documentDao = new DocumentDao();
    List<Doctype> doctypes = null;
    try {
      doctypes = documentDao.getDoctypeBySocId(society.getSocietyid(), doctypes);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return doctypes;
  }
  


  @RequestMapping(value={"/getDocSubtypeByDocId"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<DocSubType> getDocSubtypeByDocId(@ModelAttribute DocSubType docSubType)
  {
	  reqreslogger.info("[REQUEST]"+docSubType.toString());
	  
    DocumentDao documentDao = new DocumentDao();
    List<DocSubType> docSubTypes = null;
    try {
      docSubTypes = documentDao.getDocSubtypeByDocId(docSubType.getDoctypeid(), docSubTypes);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return docSubTypes;
  }
  


  @RequestMapping(value={"/getDocumentListForView"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<Document> getDocumentListForView(@ModelAttribute Document document)
  {
	  reqreslogger.info("[REQUEST]"+document.toString());
	  
    DocumentDao documentDao = new DocumentDao();
    List<Document> documents = null;
    try {
      documents = documentDao.getDocumentListForView(document, documents);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return documents;
  }
  


  @RequestMapping(value={"/saveMemberDetails"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public Userprofile saveMemberDetails(@ModelAttribute Userprofile userprofile,HttpServletRequest request)
  {
	  reqreslogger.info("[REQUEST]"+userprofile.toString());
	  
	User user = null;
    SocietyDao societyDao = new SocietyDao();
    try {
    	user = (User)request.getSession().getAttribute("userObject");
    	userprofile = societyDao.saveMemberDetails(userprofile,user);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return userprofile;
  }
  


  @RequestMapping(value={"/getMembersForSociety"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<Userprofile> getMembersForSociety(@ModelAttribute Userprofile userprofile)
  {
	  reqreslogger.info("[REQUEST]"+userprofile.toString());
	  
    SocietyDao societyDao = new SocietyDao();
    List<Userprofile> profiles = null;
    try {
      profiles = societyDao.getMembersForSociety(userprofile.getSocietyid(), profiles);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return profiles;
  }
  


  @RequestMapping(value={"/addCommitteeMember"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public Committee addCommitteeMember(@ModelAttribute Committee committee)
  {
	  reqreslogger.info("[REQUEST]"+committee.toString());
	  
    SocietyDao societyDao = new SocietyDao();
    try {
      committee = societyDao.addCommitteeMember(committee);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return committee;
  }
  


  @RequestMapping(value={"/getCommitteMembersForSociety"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public Map<String, List<Committee>> getCommitteMembersForSociety(@ModelAttribute Committee committee)
  {
	  reqreslogger.info("[REQUEST]"+committee.toString());
	  
    SocietyDao societyDao = new SocietyDao();
    Map<String, List<Committee>> committees = new HashMap();
    try {
      committees = societyDao.getCommitteMembersForSociety(committee.getSocietyid(), committees);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return committees;
  }
  


  @RequestMapping(value={"/removeCommitteeMember"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public String removeCommitteeMember(@ModelAttribute Committee committee)
  {
	  reqreslogger.info("[REQUEST]"+committee.toString());
	  
    int rowsUpdated = 0;
    SocietyDao societyDao = new SocietyDao();
    try {
      rowsUpdated = societyDao.removeCommitteeMember(committee);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    
    if (rowsUpdated > 0)
      return "success";
    return "failed";
  }
  
  @RequestMapping(value={"/saveVendorDetails"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public Vendor saveVendorDetails(@ModelAttribute Vendor vendor,HttpServletRequest request, HttpServletResponse response)
  {
	  reqreslogger.info("[REQUEST]"+vendor.toString());
	  
	User user = null;
    SocietyDao societyDao = new SocietyDao();
    try {
    	user = (User)request.getSession().getAttribute("userObject");
    	vendor.setCreatedby(String.valueOf(user.getUserid()));
    	vendor = societyDao.saveVendorDetails(vendor);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return vendor;
  }
  
  @RequestMapping(value={"/getVendorsBySocId"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<Vendor> getVendorsBySocId(@ModelAttribute Vendor vendor)
  {
	  reqreslogger.info("[REQUEST]"+vendor.toString());
	  
    SocietyDao societyDao = new SocietyDao();
     List<Vendor>  vendors = new ArrayList<Vendor>();
    try {
    	vendors = societyDao.getVendorsBySocId(vendor.getSocietyid(), vendors);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return vendors;
  }
  
  @RequestMapping(value={"/getSocietyDetailsById"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public Society getSocietyDetailsById(@ModelAttribute Society society)
  {
	  reqreslogger.info("[REQUEST]"+society.toString());
	  
    SocietyDao societyDao = new SocietyDao();
    try {
    	society = societyDao.getSocietyDetailsById(society);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return society;
  }
  
  @RequestMapping(value={"/getDocumentTypeById"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public Doctype getDocumentTypeById(@ModelAttribute Doctype doctype)
  {
	  reqreslogger.info("[REQUEST]"+doctype.toString());
	  
    SocietyDao societyDao = new SocietyDao();
    try {
    	doctype = societyDao.getDocumentTypeById(doctype);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return doctype;
  }
  
  @RequestMapping(value={"/getDocumentSubTypeById"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public DocSubType getDocumentSubTypeById(@ModelAttribute DocSubType docSubType)
  {
	  reqreslogger.info("[REQUEST]"+docSubType.toString());
	  
    SocietyDao societyDao = new SocietyDao();
    try {
    	docSubType = societyDao.getDocumentSubTypeById(docSubType);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return docSubType;
  }
  
  @RequestMapping(value={"/getFormFieldById"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public FormFields getFormFieldById(@ModelAttribute FormFields formFields)
  {
	  reqreslogger.info("[REQUEST]"+formFields.toString());
	  
    SocietyDao societyDao = new SocietyDao();
    try {
    	formFields = societyDao.getFormFieldsById(formFields);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return formFields;
  }
  
  
  @RequestMapping(value={"/getUserDataById"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public Userprofile getUserDataById(@ModelAttribute Userprofile userprofile)
  {
	  reqreslogger.info("[REQUEST]"+userprofile.toString());
	  
    SocietyDao societyDao = new SocietyDao();
    try {
    	userprofile = societyDao.getUserDataById(userprofile);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return userprofile;
  }
  
  @RequestMapping(value={"/getVendorDataById"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public Vendor getVendorDataById(@ModelAttribute Vendor vendor)
  {
	  reqreslogger.info("[REQUEST]"+vendor.toString());
	  
    SocietyDao societyDao = new SocietyDao();
    try {
    	vendor = societyDao.getVendorDataById(vendor);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return vendor;
  }
  
  
  @RequestMapping(value={"/getManagersForSociety"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<Committee> getManagersForSociety(@ModelAttribute Society society)
  {
	  reqreslogger.info("[REQUEST]"+society.toString());
	  
    SocietyDao societyDao = new SocietyDao();
    List<Committee> committees = null;
    try {
    	committees = societyDao.getManagersForSociety(society, committees);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return committees;
  }
  
  @RequestMapping(value={"/removeSocietyManager"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public String removeSocietyManager(@ModelAttribute Committee committee)
  {
	  reqreslogger.info("[REQUEST]"+committee.toString());
	  
    int rowsUpdated = 0;
    SocietyDao societyDao = new SocietyDao();
    try {
      rowsUpdated = societyDao.removeSocietyManager(committee);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    
    if (rowsUpdated > 0)
      return "success";
    return "failed";
  }
  
  
  @RequestMapping(value={"/addSocietyManager"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public Committee addSocietyManager(@ModelAttribute Committee committee)
  {
	  reqreslogger.info("[REQUEST]"+committee.toString());
	  
    SocietyDao societyDao = new SocietyDao();
    try {
      committee = societyDao.addSocietyManager(committee);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return committee;
  }
  
  @RequestMapping(value={"/getBuilderDetailsById"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public Builder getBuilderDetailsById(@ModelAttribute Builder builder)
  {
	  reqreslogger.info("[REQUEST]"+builder.toString());
	  
    SocietyDao societyDao = new SocietyDao();
    try {
    	builder = societyDao.getBuilderDetailsById(builder);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return builder;
  }
  
  @RequestMapping(value={"/getProjectDetailsById"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public Project getProjectDetailsById(@ModelAttribute Project project)
  {
	  reqreslogger.info("[REQUEST]"+project.toString());
	  
    SocietyDao societyDao = new SocietyDao();
    try {
    	project = societyDao.getProjectDetailsById(project);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return project;
  }
  
  
  @RequestMapping(value={"/removeSocietyDocmapping"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public String removeSocietyDocmapping(@ModelAttribute GenericBean bean)
  {
	  reqreslogger.info("[REQUEST]"+bean.toString());
	  
    int rowsUpdated = 0;
    SocietyDao societyDao = new SocietyDao();
    try {
      rowsUpdated = societyDao.removeSocietyDocmapping(bean);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    
    if (rowsUpdated > 0)
      return "success";
    return "failed";
  }
  
  
  @RequestMapping(value={"/editAdminUser"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public User editAdminUser(@ModelAttribute User adminUser)
  {
	  reqreslogger.info("[REQUEST]"+adminUser.toString());
	  
    SocietyDao societyDao = new SocietyDao();
    try {
    	adminUser = societyDao.editAdminUser(adminUser);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return adminUser;
  }
  
  @RequestMapping(value={"/getNeighborDetails"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<Userprofile> getNeighborDetails(@ModelAttribute User user)
  {
	  reqreslogger.info("[REQUEST]"+user.toString());
	  
    SocietyDao societyDao = new SocietyDao();
    List<Userprofile> profiles = null;
    try {
      profiles = societyDao.getNeighborDetails(user.getUserid(), profiles);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return profiles;
  }
  
  
 
  
  @RequestMapping(value={"/getContactsByCallRefId"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<CallReference> getContactsByCallRefId(@ModelAttribute CallReference callref)
  {
	  reqreslogger.info("[REQUEST]"+callref.toString());
	  
    SocietyDao societyDao = new SocietyDao();
    List<CallReference> profiles = null;
    try {
      profiles = societyDao.getContactsByCallRefId(callref.getCallrefid(), profiles);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return profiles;
  }
  
  
  @RequestMapping(value={"/saveCallrefMeeting"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public CallReference saveCallrefMeeting(@ModelAttribute CallReference callref)
  {
	  reqreslogger.info("[REQUEST]"+callref.toString());
	  
    SocietyDao societyDao = new SocietyDao();
    try {
    	callref = societyDao.saveCallrefMeeting(callref);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return callref;
  }
  
  @RequestMapping(value={"/saveCallrefContact"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public CallReference saveCallrefContact(@ModelAttribute CallReference callref)
  {
	  reqreslogger.info("[REQUEST]"+callref.toString());
	  
    SocietyDao societyDao = new SocietyDao();
    try {
    	callref = societyDao.saveCallrefContact(callref);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return callref;
  }
  
  @RequestMapping(value={"/getMeetingsByCallRefId"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<CallReference> getMeetingsByCallRefId(@ModelAttribute CallReference callref)
  {
	  reqreslogger.info("[REQUEST]"+callref.toString());
	  
    SocietyDao societyDao = new SocietyDao();
    List<CallReference> profiles = null;
    try {
      profiles = societyDao.getMeetingsByCallRefId(callref.getCallrefid(), profiles);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return profiles;
  }
  
  @RequestMapping(value={"/deleteDocById"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public String deleteDocById(@ModelAttribute Document document)
  {
	  reqreslogger.info("[REQUEST]"+document.toString());
	  
    int rowsUpdated = 0;
    DocumentDao documentDao = new DocumentDao();
    try {
      rowsUpdated = documentDao.deleteDocById(document);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    
    if (rowsUpdated > 0)
      return "success";
    return "failed";
  }



  @RequestMapping(value={"/saveSocViewMapping"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public GenericBean saveSocViewMapping(@ModelAttribute GenericBean gbean,HttpServletRequest request)
  {
	  reqreslogger.info("[REQUEST]"+gbean.toString());
	  
    SocietyDao societyDao = new SocietyDao();
    User user = null;
    try {
    	user = (User)request.getSession().getAttribute("userObject");
    	gbean.setCreatedby(String.valueOf(user.getUserid()));
    	gbean = societyDao.saveSocViewMapping(gbean);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return gbean;
  }

  @RequestMapping(value={"/getMappedDocsBySocId"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<GenericBean> getMappedDocsBySocId(@ModelAttribute GenericBean gbean)
  {
	  reqreslogger.info("[REQUEST]"+gbean.toString());
	  
    SocietyDao societyDao = new SocietyDao();
    List<GenericBean> beans = null;
    try {
    	beans = societyDao.getMappedDocsBySocId(gbean.getSocietyid(), beans);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return beans;
  }


  @RequestMapping(value={"/removeSocDocViewMapping"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public String removeSocDocViewMapping(@ModelAttribute GenericBean bean)
  {
	  reqreslogger.info("[REQUEST]"+bean.toString());
	  
    int rowsUpdated = 0;
    SocietyDao societyDao = new SocietyDao();
    try {
      rowsUpdated = societyDao.removeSocDocViewMapping(bean);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    
    if (rowsUpdated > 0)
      return "success";
    return "failed";
  }


  @RequestMapping(value={"/generateAndSendOTP"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public String generateAndSendOTP(@ModelAttribute User user)
  {
	  reqreslogger.info("[REQUEST]"+user.toString());
	  
    int rowsUpdated = 0;
    LoginDao loginDao = new LoginDao();
    try {
    	if(user.getOtpType().equals("confidential")){
    		if(!loginDao.verifyConfidentialAccess(user)){
    			return "unauthorized";
    		}
    	}
    	
    	rowsUpdated = loginDao.generateAndSendOTP(user);
    	
    } catch (Exception e) {
      logger.error(e.getMessage());
    } 
    if (rowsUpdated > 0)
      return "success";
    return "failed";
  }

  @RequestMapping(value={"/validateAndSetNewPW"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public String validateAndSetNewPW(@ModelAttribute User user)
  {
	  reqreslogger.info("[REQUEST]"+user.toString());
	  
	int otpaValid = 0;
	int passwordSet = 0;
    LoginDao loginDao = new LoginDao();
    try {
    	otpaValid = loginDao.validateOTP(user);
      if(otpaValid>0){
    	  if(user.getPassword()!=null && user.getPassword().trim().length()>5)
    		  passwordSet = loginDao.setNewpasswordForUser(user);
      }
    } catch (Exception e) {
      logger.error(e.getMessage());
    } 
    if (passwordSet > 0)
      return "success";
    return "failed";
  }
  
  @RequestMapping(value={"/validateOTPForDocAccess"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public String validateOTPForDocAccess(@ModelAttribute User user)
  {
	  reqreslogger.info("[REQUEST]"+user.toString());
	  
	int otpaValid = 0;
    LoginDao loginDao = new LoginDao();
    try {
    	otpaValid = loginDao.validateOTP(user);
    } catch (Exception e) {
      logger.error(e.getMessage());
    } 
    if (otpaValid > 0)
      return "success";
    return "failed";
  }

  
  @RequestMapping(value={"/getDesignationById"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public GenericBean getDesignationById(@ModelAttribute GenericBean gbean)
  {
	  reqreslogger.info("[REQUEST]"+gbean.toString());
	  
    SocietyDao societyDao = new SocietyDao();
    try {
    	gbean = societyDao.getDesignationById(gbean);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return gbean;
  }
  
  @RequestMapping(value={"/saveDesignationDetails"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public GenericBean saveDesignationDetails(@ModelAttribute GenericBean gbean,HttpServletRequest request, HttpServletResponse response)
  {
	  reqreslogger.info("[REQUEST]"+gbean.toString());
	  
	User user = null;
    SocietyDao societyDao = new SocietyDao();
    try {
    	user = (User)request.getSession().getAttribute("userObject");
    	gbean.setCreatedby(String.valueOf(user.getUserid()));
    	gbean = societyDao.saveDesignationDetails(gbean);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return gbean;
  }
  
  
  @RequestMapping(value={"/deleteDocumentPage"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public String deleteDocumentPage(@ModelAttribute Files files)
  {
	  reqreslogger.info("[REQUEST]"+files.toString());
	  
    int rowsUpdated = 0;
    DocumentDao docDao = new DocumentDao();
    try {
      rowsUpdated = docDao.deleteDocumentPage(files);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    
    if (rowsUpdated > 0)
      return "success";
    return "failed";
  }
  
   @RequestMapping(value={"/saveMemberparkingDetails"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public Parking saveMemberparkingDetails(@ModelAttribute Parking parking,HttpServletRequest request, HttpServletResponse response)
  {
	   reqreslogger.info("[REQUEST]"+parking.toString());
	   
	User user = null;
    SocietyDao societyDao = new SocietyDao();
    try {
    	user = (User)request.getSession().getAttribute("userObject");
    	parking.setCreatedby(String.valueOf(user.getUserid()));
    	parking = societyDao.saveMemberparkingDetails(parking);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return parking;
  }
   
   
   @RequestMapping(value={"/getParkingDetailsForMember"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   @ResponseBody
   public List<Parking> getParkingDetailsForMember(@ModelAttribute Parking parking)
   {
	   reqreslogger.info("[REQUEST]"+parking.toString());
	   
     SocietyDao societyDao = new SocietyDao();
     List<Parking> parkingList = null;
     try {
    	 parkingList = societyDao.getParkingDetailsForMember(parking, parkingList);
     } catch (Exception e) {
       logger.error(e.getMessage());
     }
     return parkingList;
   }
  
   
   @RequestMapping(value={"/removeParkingData"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   @ResponseBody
   public String removeParkingData(@ModelAttribute Parking parking)
   {
	   reqreslogger.info("[REQUEST]"+parking.toString());
	   
     int rowsUpdated = 0;
     SocietyDao docDao = new SocietyDao();
     try {
       rowsUpdated = docDao.removeParkingData(parking);
     } catch (Exception e) {
       logger.error(e.getMessage());
     }
     
     if (rowsUpdated > 0)
       return "success";
     return "failed";
   }
   
   
   @RequestMapping(value={"/addShareCertDetails"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   @ResponseBody
   public UserSCNominee addShareCertDetails(@ModelAttribute UserSCNominee userSCNominee,HttpServletRequest request, HttpServletResponse response)
   {
	   reqreslogger.info("[REQUEST]"+userSCNominee.toString());
	   
 	User user = null;
     SocietyDao societyDao = new SocietyDao();
     try {
     	user = (User)request.getSession().getAttribute("userObject");
     	userSCNominee.setCreatedby(String.valueOf(user.getUserid()));
     	userSCNominee = societyDao.addShareCertDetails(userSCNominee);
     } catch (Exception e) {
       logger.error(e.getMessage());
     }
     return userSCNominee;
   }
 
   
   
   @RequestMapping(value={"/getShareCertDetails"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   @ResponseBody
   public List<UserSCNominee> getShareCertDetails(@ModelAttribute UserSCNominee userSCNominee)
   {
	   reqreslogger.info("[REQUEST]"+userSCNominee.toString());
	   
     SocietyDao societyDao = new SocietyDao();
     List<UserSCNominee> scList = null;
     try {
    	 scList = societyDao.getShareCertDetails(userSCNominee, scList);
     } catch (Exception e) {
       logger.error(e.getMessage());
     }
     return scList;
   }
  
   
   @RequestMapping(value={"/removeShareCertDetails"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   @ResponseBody
   public String removeParkingData(@ModelAttribute UserSCNominee userSCNominee)
   {
	   reqreslogger.info("[REQUEST]"+userSCNominee.toString());
	   
     int rowsUpdated = 0;
     SocietyDao docDao = new SocietyDao();
     try {
       rowsUpdated = docDao.removeShareCertDetails(userSCNominee);
     } catch (Exception e) {
       logger.error(e.getMessage());
     }
     
     if (rowsUpdated > 0)
       return "success";
     return "failed";
   }
   
   @RequestMapping(value={"/saveConfDocAccess"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   @ResponseBody
   public String saveConfDocAccess(@RequestParam("userid") String userid,HttpServletRequest request)
   {
	   reqreslogger.info("[REQUEST]"+userid.toString());
	   
     int rowsUpdated = 0;
     SocietyDao docDao = new SocietyDao();
     User user;
     try {
    	 user = (User)request.getSession().getAttribute("userObject");
         rowsUpdated = docDao.saveConfDocAccess(userid,user.getUserid());
     } catch (Exception e) {
       logger.error(e.getMessage());
     }
     
     if (rowsUpdated > 0)
       return "success";
     return "failed";
   }
   
   
   @RequestMapping(value={"/getConfDocAccessList"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   @ResponseBody
   public List<GenericBean> getConfDocAccessList()
   {
     DocumentDao documentDao = new DocumentDao();
     List<GenericBean> formFields = null;
     try {
       formFields = documentDao.getConfDocAccessList(formFields);
     } catch (Exception e) {
       logger.error(e.getMessage());
     }
     return formFields;
   }
   
   @RequestMapping(value={"/getProjectsByBuilderId"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   @ResponseBody
   public List<Project> getProjectsByBuilderId(@ModelAttribute Builder builder)
   {
	   reqreslogger.info("[REQUEST]"+builder.toString());
	   
     DocumentDao documentDao = new DocumentDao();
     List<Project> docSubTypes = null;
     try {
       docSubTypes = documentDao.getProjectsByBuilderId(builder.getBuilderid(), docSubTypes);
     } catch (Exception e) {
       logger.error(e.getMessage());
     }
     return docSubTypes;
   }
   
   @RequestMapping(value={"/getSubProjectsByProjectId"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   @ResponseBody
   public List<Society> getSubProjectsByProjectId(@ModelAttribute Project project)
   {
	   reqreslogger.info("[REQUEST]"+project.toString());
	   
     DocumentDao documentDao = new DocumentDao();
     List<Society> docSubTypes = null;
     try {
       docSubTypes = documentDao.getSubProjectsByProjectId(project.getProjectid(), docSubTypes);
     } catch (Exception e) {
       logger.error(e.getMessage());
     }
     return docSubTypes;
   }
   
   
   @RequestMapping(value={"/genericRemove"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   @ResponseBody
   public String genericRemove(@ModelAttribute GenericBean gbean)
   {
	   reqreslogger.info("[REQUEST]"+gbean.toString());
	   
     int rowsUpdated = 0;
     SocietyDao societyDao = new SocietyDao();
     try {
       rowsUpdated = societyDao.genericRemove(gbean);
     } catch (Exception e) {
       logger.error(e.getMessage());
     }
     
     if (rowsUpdated > 0)
       return "success";
     return "failed";
   }
   
   
   @RequestMapping(value={"/addTenantToHistory"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   @ResponseBody
   public String addTenantToHistory(@ModelAttribute User user)
   {
	   reqreslogger.info("[REQUEST]"+user.toString());
	   
     int rowsUpdated = 0;
     SocietyDao societyDao = new SocietyDao();
     try {
       rowsUpdated = societyDao.addTenantToHistory(user);
     } catch (Exception e) {
       logger.error(e.getMessage());
     }
     
     if (rowsUpdated > 0)
       return "success";
     return "failed";
   }
   
   @RequestMapping(value={"/getTenantHistory"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   @ResponseBody
   public List<Userprofile> getTenantHistory(@ModelAttribute User user)
   {
	   reqreslogger.info("[REQUEST]"+user.toString());
	   
     DocumentDao documentDao = new DocumentDao();
     List<Userprofile> docSubTypes = null;
     try {
       docSubTypes = documentDao.getTenantHistory(user.getUserid(), docSubTypes);
     } catch (Exception e) {
       logger.error(e.getMessage());
     }
     return docSubTypes;
   }
   
   @RequestMapping(value={"/sendDocumentAsMail"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   @ResponseBody
   public String sendDocumentAsMail(@ModelAttribute EmailBean eBean,HttpServletRequest request)
   {
	   reqreslogger.info("[REQUEST]"+eBean.toString());
	   
     int rowsUpdated = 0;
     DocumentDao docDao = new DocumentDao();
     User user = null;
     try {
     	user = (User)request.getSession().getAttribute("userObject"); 
     	
     	eBean.setSenderName(user.getFirstName()+" "+user.getLastName());
     	
        rowsUpdated = docDao.sendDocumentAsMail(eBean);
     } catch (Exception e) {
       logger.error(e.getMessage());
       e.printStackTrace();
     }
     
     if (rowsUpdated > 0)
       return "success";
     return "failed";
   }
   
   
   @RequestMapping(value={"/addDeleteAuth"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   @ResponseBody
   public String addDeleteAuth(@ModelAttribute User user)
   {
 	  reqreslogger.info("[REQUEST]"+user.toString());
 	  
     int rowsUpdated = 0;
     SocietyDao societyDao = new SocietyDao();
     try {
       rowsUpdated = societyDao.addDeleteAuth(user);
     } catch (Exception e) {
       logger.error(e.getMessage());
     }
     
     if (rowsUpdated > 0)
       return "success";
     return "failed";
   }
   
   @RequestMapping(value={"/removeDeleteAuth"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   @ResponseBody
   public String removeDeleteAuth(@ModelAttribute User user)
   {
 	  reqreslogger.info("[REQUEST]"+user.toString());
 	  
     int rowsUpdated = 0;
     SocietyDao societyDao = new SocietyDao();
     try {
       rowsUpdated = societyDao.removeDeleteAuth(user);
     } catch (Exception e) {
       logger.error(e.getMessage());
     }
     
     if (rowsUpdated > 0)
       return "success";
     return "failed";
   }
   
   @RequestMapping(value={"/getDeleteAuth"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   @ResponseBody
   public List<User> getDeleteAuth()
   {
	   
     DocumentDao documentDao = new DocumentDao();
     List<User> users = null;
     try {
    	 users = documentDao.getDeleteAuth(users);
     } catch (Exception e) {
       logger.error(e.getMessage());
     }
     return users;
   }
} //end of class

