package com.dms.controller;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	 
	@RequestMapping(value = "/authenticateUser", method = RequestMethod.POST)
	public String welcome(ModelMap model) {
		logger.error("[welcome] counter : {}", "123");
		System.out.println("Hello");
		// Spring uses InternalResourceViewResolver and return back index.jsp
		return "index";

	}

	
}
