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

import com.dms.beans.Committee;
import com.dms.beans.DocSubType;
import com.dms.beans.Doctype;
import com.dms.beans.Document;
import com.dms.beans.FormFields;
import com.dms.beans.Society;
import com.dms.beans.User;
import com.dms.beans.Userprofile;
import com.dms.beans.Vendor;
import com.dms.dao.DocumentDao;
import com.dms.dao.SocietyDao;

@Controller
public class AjaxController
{
  public AjaxController() {}
  
  private static final Logger logger = LoggerFactory.getLogger(AjaxController.class);
  
  @RequestMapping(value={"/societyAutosuggest"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, headers={"Accept=*/*"}, produces={"application/json"})
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public List<String> societyAutosuggest(@RequestParam("searchText") String searchText) {
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
  
  @RequestMapping(value={"/userAutosuggestForSociety"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, headers={"Accept=*/*"}, produces={"application/json"})
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public List<String> userAutosuggestForSociety(@RequestParam("searchText") String searchText, @RequestParam("societyid") String societyid) {
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
  public Userprofile saveMemberDetails(@ModelAttribute Userprofile userprofile)
  {
    SocietyDao societyDao = new SocietyDao();
    try {
      //System.out.println("userprofile :: " + userprofile);
      userprofile = societyDao.saveMemberDetails(userprofile);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return userprofile;
  }
  


  @RequestMapping(value={"/getMembersForSociety"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<Userprofile> getMembersForSociety(@ModelAttribute Userprofile userprofile)
  {
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
    SocietyDao societyDao = new SocietyDao();
    try {
    	vendor = societyDao.getVendorDataById(vendor);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return vendor;
  }
  
  
}