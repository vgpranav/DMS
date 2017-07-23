package com.dms.logging;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.util.Date;
import java.util.Map.Entry;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.dms.beans.User;
import com.dms.dao.LoginDao;

import javax.servlet.http.HttpSession;

public class LoggingHelper {

	private static final Logger reqreslogger = LoggerFactory.getLogger("reqreslogger");
	private static final Logger actionlogger = LoggerFactory.getLogger("actionlogger");
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(LoggingHelper.class);

	public static void logAction(String userId, String action) {

	}

	
	public static void logMVRequest(HttpSession session,String userid,String Action,Object obj){
		reqreslogger.info("----------------- [REQUEST]["+Action+"][USER:"+userid+"] ----------------- ");
		reqreslogger.info(obj.toString());
		actionlogger.info(userid+" : "+Action);
		LoginDao ldao;
		try{
			ldao = new LoginDao();
			ldao.logActionsToDB(userid, Action, obj.toString(),"view");
			String sessionKey = session.getAttribute("sessionKey")!=null ? session.getAttribute("sessionKey").toString() : "";
			long userid1 = session.getAttribute("userid")!=null ? Long.valueOf(session.getAttribute("userid").toString()): 0;
			
			ldao.updateSessionForUser(sessionKey,userid1,1);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static void logMVResponse(String Action,ModelAndView mv){
		reqreslogger.info("----------------- [RESPONSE]["+Action+"] -----------------");
		for (Entry<String, Object> entry : mv.getModelMap().entrySet())
		{
			reqreslogger.info(entry.getKey() + " :: " + entry.getValue());
			reqreslogger.info("");
		}
	}
	
	public static void logAjaxRequest(HttpSession session,String userid,String Action,Object obj){
		reqreslogger.info("----------------- [AJAX REQUEST]["+Action+"][USER:"+userid+"] ----------------- ");
		reqreslogger.info(obj.toString());
		actionlogger.info(userid+" : "+Action);
		
		LoginDao ldao;
		try{
			ldao = new LoginDao();
			ldao.logActionsToDB(userid, Action, obj.toString(),"ajax");
			String sessionKey = session.getAttribute("sessionKey")!=null ? session.getAttribute("sessionKey").toString() : "";
			ldao.updateSessionForUser(sessionKey,Long.valueOf(userid),1);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void logAjaxResponse(String Action,Object obj){
		reqreslogger.info("----------------- [AJAX RESPONSE]["+Action+"] -----------------");
		 reqreslogger.info(obj.toString());
	}
	
	public static String logUserLogin(User user) {
		InetAddress addr;
		LoginDao ldao;
		String ip;
		String sessionKey = null;
		BufferedReader in = null;
		try {
			ldao = new LoginDao();

			//addr = InetAddress.getLocalHost(); ip = addr.getHostAddress();

			URL whatismyip = new URL("http://checkip.amazonaws.com");
			in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
			ip = in.readLine();

			sessionKey = RandomStringUtils.randomAlphanumeric(5) + RandomStringUtils.randomAlphanumeric(5);

			user.setLogintime(new Date());
			user.setSessionkey(sessionKey);
			user.setIpaddress(ip);
			user.setSessionactive(1);
			ldao.logUserLogin(user);

		} catch (Exception e) {
			logger.error("error", e);
			//e.printStackTrace();
		}

		return sessionKey;
	}

	
	public static void logUserLogout(String sessionKey,String userId) {
		LoginDao ldao;
		try {
			ldao = new LoginDao();
			ldao.logUserLogout(sessionKey,userId);
		} catch (Exception e) {
			logger.error("error", e);
			//e.printStackTrace();
		}
	}
}
