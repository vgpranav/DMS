package com.dms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dms.beans.SocietyType;
import com.dms.beans.User;
import com.dms.dao.LoginDao;
import com.dms.dao.SocietyDao;

@Controller
public class ViewController {

	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(ViewController.class);
	
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String getWelcomePage(){
		return "welcome";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage(){
		return "login";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String getLogout(){
		return "login";
	}
	
	 
	@RequestMapping(value = "/authenticateUser", method = RequestMethod.POST)
	public ModelAndView welcome(ModelMap model,@RequestAttribute User user,HttpServletRequest request,HttpServletResponse response) {
 		LoginDao loginDao = new LoginDao();
 		ModelAndView mv = null;
 		User authenticatedUser = null;
		try{
			authenticatedUser = loginDao.authenticateUser(user,authenticatedUser);
			if(authenticatedUser!=null){
				if(authenticatedUser.getActive()==1){
					mv = new ModelAndView("home");
					request.getSession().setAttribute("userObject",authenticatedUser);
				} else {
					mv = new ModelAndView("login");
					request.setAttribute("errorMessage","Account is Locked. Please contact Administrator");
				} 
			} else {
				mv = new ModelAndView("login");
				request.setAttribute("errorMessage", "Invalid Username/Password");
			}
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return mv;

	}
	
	
	@RequestMapping(value = "/addSociety", method = RequestMethod.GET)
	public ModelAndView addSociety(){
		ModelAndView mv = null;
		List<SocietyType> socTypes=null;
		SocietyDao documentDao = new SocietyDao();
		try{
			socTypes = documentDao.getAllActiveSocietyTypes(socTypes);
			mv = new ModelAndView("addSociety");
			mv.addObject("societytypeList", socTypes);
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return mv;
	}
	

	
}
