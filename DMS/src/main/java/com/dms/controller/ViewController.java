package com.dms.controller;

import com.dms.beans.CommitteeMaster;
import com.dms.beans.DocSubType;
import com.dms.beans.Doctype;
import com.dms.beans.Document;
import com.dms.beans.FormFields;
import com.dms.beans.Society;
import com.dms.beans.SocietyType;
import com.dms.beans.User;
import com.dms.dao.DocumentDao;
import com.dms.dao.LoginDao;
import com.dms.dao.SocietyDao;
import com.dms.util.CommomUtility;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController
{
  public ViewController() {}
  
  private static final Logger logger = LoggerFactory.getLogger(ViewController.class);
  
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
  
  @RequestMapping(value={"/authenticateUser"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView welcome(ModelMap model, @ModelAttribute User user, HttpServletRequest request, HttpServletResponse response)
  {
    LoginDao loginDao = new LoginDao();
    ModelAndView mv = null;
    User authenticatedUser = null;
    try {
      authenticatedUser = loginDao.authenticateUser(user, authenticatedUser);
      if (authenticatedUser != null) {
        if (authenticatedUser.getActive() == 1) {
          mv = new ModelAndView("home");
          request.getSession().setAttribute("userObject", authenticatedUser);
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
    

    try {
      socTypes = documentDao.getAllActiveSocietyTypes(socTypes);
      mv = new ModelAndView("addSociety");
      mv.addObject("societytypeList", socTypes);
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
    SocietyDao documentDao = new SocietyDao();
    User user = null;
    try {
      user = (User)request.getSession().getAttribute("userObject");
      
      society = documentDao.insertOrUpdateSociety(society,user.getUserid());
      socTypes = documentDao.getAllActiveSocietyTypes(socTypes);
      mv = new ModelAndView("addSociety");
      mv.addObject("societytypeList", socTypes);
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
  public ModelAndView addDocument1() {
    ModelAndView mv = null;
    List<Society> societyList = null;
    SocietyDao societyDao = new SocietyDao();
    try {
      societyList = societyDao.getSocietyListforUser(societyList);
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
    
    System.out.println("document ::> "+document);
    
    DocumentDao documentDao = new DocumentDao();
    try {
      formFields = documentDao.getFieldsForDocSubtype(document.getDocsubtypeid(), formFields);
      mv = new ModelAndView("addDocument2");
      mv.addObject("formFieldsList", formFields);
      mv.addObject("document", document);
    }
    catch (Exception e) {
      logger.error(e.getMessage());
    }
    return mv;
  }
  
  @RequestMapping(value={"/addDocument3"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView addDocument3(@org.springframework.web.bind.annotation.RequestParam Map<String, String> params) {
    long detailsSaved = 0L;
    
    DocumentDao ddao = new DocumentDao();
    
    System.out.println(params);
    
    ModelAndView mv = null;
    try
    {
      detailsSaved = ddao.saveDocumentHeadAndDetails(params);
      
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
  public ModelAndView addMember() {
    ModelAndView mv = null;
    List<Society> societyList = null;
    SocietyDao societyDao = new SocietyDao();
    try {
      societyList = societyDao.getSocietyListforUser(societyList);
      mv = new ModelAndView("addMember");
      mv.addObject("societyList", societyList);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return mv;
  }
  
  @RequestMapping(value={"/createCommittee"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView createCommittee() {
    ModelAndView mv = null;
    List<Society> societyList = null;
    List<CommitteeMaster> committeeMasterList = null;
    SocietyDao societyDao = new SocietyDao();
    try {
      societyList = societyDao.getSocietyListforUser(societyList);
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
    try {
      user = (User)request.getSession().getAttribute("userObject");
      societyList = sdao.getSocietyListForManager(user.getUserid(), societyList);
      if (societyList.size() == 1) {
        mv = new ModelAndView("adminPanel");
        mv.addObject("society", (Society)societyList.get(0));
      }
      else {
        mv = new ModelAndView("tobemade");
      }
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
  
}