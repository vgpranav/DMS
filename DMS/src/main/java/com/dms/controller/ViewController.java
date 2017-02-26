package com.dms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dms.beans.DocSubType;
import com.dms.beans.Doctype;
import com.dms.beans.FormFields;
import com.dms.beans.Society;
import com.dms.beans.SocietyType;
import com.dms.beans.User;
import com.dms.dao.DocumentDao;
import com.dms.dao.LoginDao;
import com.dms.dao.SocietyDao;
import com.dms.util.CommomUtility;

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
	public ModelAndView welcome(ModelMap model,@ModelAttribute User user,HttpServletRequest request,HttpServletResponse response) {
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
	
	@RequestMapping(value = "/saveSociety", method = RequestMethod.POST)
	public ModelAndView saveSociety(@ModelAttribute Society society
			/*@Valid Society customer,
			BindingResult bindingResult, 
			Model model*/){
		
		ModelAndView mv = null;
		List<SocietyType> socTypes=null;
		SocietyDao documentDao = new SocietyDao();
		try{
			society = documentDao.insertOrUpdateSociety(society);
			socTypes = documentDao.getAllActiveSocietyTypes(socTypes);
			mv = new ModelAndView("addSociety");
			mv.addObject("societytypeList", socTypes);
			mv.addObject("error","Society Added");
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return mv;
	}
	
	@RequestMapping(value = "/addDoctype", method = RequestMethod.GET)
	public ModelAndView addDoctype(){
		ModelAndView mv = null;
		List<Doctype> docTypes=null;
		DocumentDao documentDao = new DocumentDao();
		try{
			docTypes = documentDao.getAllDocumentTypes(docTypes);
			mv = new ModelAndView("addDoctype");
			mv.addObject("docTypesList",docTypes);
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return mv;
	}
	
	@RequestMapping(value = "/saveDocumentType", method = RequestMethod.POST)
	public ModelAndView saveDocumentType(@ModelAttribute Doctype doctype
			/*@Valid Society customer,
			BindingResult bindingResult, 
			Model model*/){
		
		ModelAndView mv = null;
		List<Doctype> docTypes=null;
		DocumentDao documentDao = new DocumentDao();
		try{
			doctype = documentDao.insertOrUpdateDocType(doctype);
			docTypes = documentDao.getAllDocumentTypes(docTypes);
			mv = new ModelAndView("addDoctype");
			mv.addObject("docTypesList",docTypes);
			mv.addObject("error","Doctype Added");
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return mv;
	}
	
	@RequestMapping(value = "/addDocSubType", method = RequestMethod.GET)
	public ModelAndView addDocSubType(){
		ModelAndView mv = null;
		List<Doctype> docTypes=null;
		List<DocSubType> docSubType=null;
		DocumentDao documentDao = new DocumentDao();
		try{
			docSubType = documentDao.getAllDocumentSubTypes(docSubType);
			docTypes = documentDao.getAllDocumentTypes(docTypes);
			mv = new ModelAndView("addDocSubtype");
			mv.addObject("docSubTypeList",docSubType);
			mv.addObject("docTypesList",docTypes);
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return mv;
	}
	
	
	@RequestMapping(value = "/saveDocumentSubType", method = RequestMethod.POST)
	public ModelAndView saveDocumentType(@ModelAttribute DocSubType docSubType
			/*@Valid Society customer,
			BindingResult bindingResult, 
			Model model*/){
		
		ModelAndView mv = null;
		List<Doctype> docTypes=null;
		List<DocSubType> docSubTypes=null;
		DocumentDao documentDao = new DocumentDao();
		try{
			docSubType = documentDao.insertOrUpdateDocSubType(docSubType);
			docTypes = documentDao.getAllDocumentTypes(docTypes);
			docSubTypes = documentDao.getAllDocumentSubTypes(docSubTypes);
			mv = new ModelAndView("addDocSubtype");
			mv.addObject("docTypesList",docTypes);
			mv.addObject("docSubTypeList",docSubTypes);
			mv.addObject("error","Doc Sub Type Added");
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return mv;
	}
	
	@RequestMapping(value = "/addFormFields", method = RequestMethod.GET)
	public ModelAndView addFormFields(){
		ModelAndView mv = null;
		List<DocSubType> docSubType=null;
		List<Integer> seqList= new ArrayList<Integer>();
		DocumentDao documentDao = new DocumentDao();
		try{
			docSubType = documentDao.getAllDocumentSubTypes(docSubType);
			mv = new ModelAndView("addFormFields");
			mv.addObject("docSubTypeList",docSubType);
			
			int maxConfigval = Integer.parseInt(CommomUtility.getConfig("maxseqno"));
			for(int i=1;i<=maxConfigval;i++){
				seqList.add(i);
			}
			
			mv.addObject("seqList",seqList);
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return mv;
	}
	
	
}
