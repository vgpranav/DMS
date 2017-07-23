package com.dms.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dms.beans.DocSubType;
import com.dms.beans.Society;
import com.dms.beans.User;
import com.dms.beans.Userprofile;
import com.dms.dao.DocumentDao;
import com.dms.dao.LoginDao;
import com.dms.dao.SocietyDao;
import com.dms.logging.LoggingHelper;

@Controller
public class LoginController {

	  private static final Logger logger = LoggerFactory.getLogger(ViewController.class);
	  private static final Logger actionlogger = LoggerFactory.getLogger("actionlogger");
	  private static final Logger reqreslogger = LoggerFactory.getLogger("reqreslogger");

	
	
	 @RequestMapping(value={"/login"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	  public String getLoginPage() {
	    return "login";
	  }
	  
	  @RequestMapping(value={"/logout"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	  public String getLogout(HttpServletRequest request) {
		  
		LoggingHelper.logMVRequest(request.getSession(),"","logout","");
		  
		String sessionKey = (String) request.getSession().getAttribute("sessionKey");
		String userId = String.valueOf(request.getSession().getAttribute("userId"));
		
		LoggingHelper.logUserLogout(sessionKey,userId);
	    return "login";
	  }
	  
	  @RequestMapping(value={"/authenticateUser"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	  public ModelAndView welcome(ModelMap model, @ModelAttribute User user, HttpServletRequest request, HttpServletResponse response)
	  {
		
		LoggingHelper.logMVRequest(request.getSession(),"","authenticateUser",user);
		  
	    LoginDao loginDao = new LoginDao();
	    SocietyDao societyddao = new SocietyDao();
	    ModelAndView mv = null;
	    User authenticatedUser = null;
	    Userprofile userprofile = new Userprofile();
	    SocietyDao societyDao = new SocietyDao();
	    DocumentDao ddao = new DocumentDao();
		List<DocSubType> docSubType = null;
		String sessionKey="";
		String modNAme="DMS";
	    try {
	      authenticatedUser = loginDao.authenticateUser(user, authenticatedUser);
	      if (authenticatedUser != null) {
	        if (authenticatedUser.getActive() == 1) {
	        	
	         boolean loginFlag=false;
	         if(authenticatedUser.getSessionactive()==0)
	        	 loginFlag=true;
	         else{
	        	 Date now = new Date();
	        	 if(now.getTime() - authenticatedUser.getSessiontime().getTime() > 10*60*1000){
	        		 loginFlag = true;
	             }
	         }
	         
	         if(loginFlag){
	        	 
	        	 mv = new ModelAndView("home");
		          userprofile.setUserid(authenticatedUser.getUserid());
		          userprofile = societyDao.getUserDataById(userprofile);
		          request.getSession().setAttribute("userprofile", userprofile);
		          if(userprofile!=null){
		        	  docSubType = ddao.getDocStubtypesToDispay(docSubType,userprofile.getSocietyid());
		        	  mv.addObject("docSubType", docSubType);
		        	  
		        	  Society soc = new Society();
			          soc.setSocietyid(Long.valueOf(userprofile.getSocietyid()));
			          
			          soc = societyddao.getSocietyDetailsById(soc);
			          
			          if(soc.getSocietytypeid()==2)
			        	  modNAme = "CS - DMS";
			          else
			        	  modNAme="HS - DMS";
			          
			          boolean newNoticeAdded = societyddao.checkIfNewNoticeAdded(userprofile.getSocietyid());
			          mv.addObject("newNoticeAdded", newNoticeAdded);
		          }
				  
		          List<HashMap<String, Object>> photos = new ArrayList<HashMap<String, Object>>();
		          photos =  societyddao.getSocietyPhotos(authenticatedUser.getUserid(),"user","UserImages",photos);
		          
		          if(photos.size()>0){
		        	  String imgBase64 = (String) photos.get(0).get("file");
		              String imgContentType = (String) photos.get(0).get("contenttype");
		              request.getSession().setAttribute("imgBase64", imgBase64);
		              request.getSession().setAttribute("imgContentType", imgContentType);
		          }
		          
		          long isSocManager = societyddao.checkIfSocietyManager(authenticatedUser.getUserid());
		          
		          request.getSession().setAttribute("userroleid", authenticatedUser.getUserroleid());
		          request.getSession().setAttribute("socmanagercount",isSocManager);
		          request.getSession().setAttribute("modNAme",modNAme);
		          request.getSession().setAttribute("userObject", authenticatedUser);
		          request.getSession().setAttribute("deleteFlag", authenticatedUser.getDeleteflag());
		          request.getSession().setAttribute("userId", authenticatedUser.getUserid());
		          
		          mv.addObject("userprofile", userprofile);
		          
		          sessionKey = LoggingHelper.logUserLogin(authenticatedUser);
		          request.getSession().setAttribute("sessionKey",sessionKey);
	        	 
	         } else {
	        	 mv = new ModelAndView("login");
		         request.setAttribute("errorMessage", "You are already logged in from another device. Please try after some time");
	         }
	          
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
	    LoggingHelper.logMVResponse("authenticateUser",mv);
	    return mv;
	  }
}
