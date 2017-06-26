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
import com.dms.beans.RoleTransaction;
import com.dms.beans.Society;
import com.dms.beans.SocietyType;
import com.dms.beans.User;
import com.dms.beans.Userprofile;
import com.dms.dao.DocumentDao;
import com.dms.dao.LoginDao;
import com.dms.dao.SocietyDao;
import com.dms.logging.LoggingHelper;
import com.dms.util.CommomUtility;
import com.dms.util.CommonDA;
import com.dms.util.FtpWrapper;

@Controller
public class ViewController {
	public ViewController() {
	}

	private static final Logger logger = LoggerFactory.getLogger(ViewController.class);
	private static final Logger actionlogger = LoggerFactory.getLogger("actionlogger");
	private static final Logger reqreslogger = LoggerFactory.getLogger("reqreslogger");

	@RequestMapping(value = { "/systemcheck" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String systemcheck() {
		try {

			logger.debug("Syscheck debug");
			logger.error("Syscheck error");
			logger.info("Syscheck info");
			logger.warn("Syscheck warn");

			actionlogger.error("actionlogger");
			reqreslogger.error("reqreslogger");

			String SQLDriver = CommonDA.getProperties().getProperty("SQLDriver").trim();
			String SQLURL = CommonDA.getProperties().getProperty("SQLURL").trim();
			String SQLUsername = CommonDA.getProperties().getProperty("SQLUsername").trim();
			String SQLPassword = CommonDA.getProperties().getProperty("SQLPassword").trim();

			System.out.println("Performing System check\n\n\n");
			System.out.println("\n\nConnecting DB..");
			System.out.println("\n\n" + SQLDriver);
			System.out.println("\n\n" + SQLURL);
			System.out.println("\n\n" + SQLUsername);
			System.out.println("\n\n" + SQLPassword);

			Class.forName(SQLDriver);

			Connection con = DriverManager.getConnection(SQLURL, SQLUsername, SQLPassword);

			System.out.println("\n\nConn Is Open" + !con.isClosed());

			System.out.println("\n\nClosing Connection");

			con.close();

			FtpWrapper ftp = new FtpWrapper();

			String hostDomain = ftp.getServerName();
			String Id = ftp.getUsername();
			String Password = ftp.getPassword();

			System.out.println("\n\nFTP Connection :: " + ftp.connectAndLogin(hostDomain, Id, Password));

			System.out.println("\n\nWorking Directory is :: " + ftp.printWorkingDirectory());

			return "Connection Successful at " + new Date();

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Syscheck error frm exception " + e.getMessage());

			return "Db Connection Failed";
		}
	}

	@RequestMapping(value = { "/welcome" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String getWelcomePage() {
		return "welcome";
	}

	@RequestMapping(value = { "/showHomepage" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView showHomepage(ModelMap model, @ModelAttribute User user, HttpServletRequest request,
			HttpServletResponse response) {
		
		LoggingHelper.logMVRequest("showHomepage",user);

		ModelAndView mv = null;
		User authenticatedUser = null;
		Userprofile userprofile = new Userprofile();
		SocietyDao societyDao = new SocietyDao();
		DocumentDao ddao = new DocumentDao();
		List<DocSubType> docSubType = null;
		try {
			authenticatedUser = (User) request.getSession().getAttribute("userObject");
			request.getSession().setAttribute("userObject", authenticatedUser);
			userprofile.setUserid(authenticatedUser.getUserid());
			userprofile = societyDao.getUserDataById(userprofile);

			docSubType = ddao.getDocStubtypesToDispay(docSubType, userprofile.getSocietyid());

			mv = new ModelAndView("home");
			mv.addObject("userprofile", userprofile);
			mv.addObject("docSubType", docSubType);

			// System.out.println("userprofile :: "+userprofile);
		} catch (Exception e) {
			logger.error("Exception : ",e);
		}

		LoggingHelper.logMVResponse("showHomepage",mv);
		return mv;
	}

	@RequestMapping(value = { "/addSociety" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView addSociety() {
		LoggingHelper.logMVRequest("addSociety","");
		ModelAndView mv = null;
		List<SocietyType> socTypes = null;
		SocietyDao documentDao = new SocietyDao();
		List<Society> societyList = new ArrayList<Society>();
		List<Project> projectList = null;

		try {
			socTypes = documentDao.getAllActiveSocietyTypes(socTypes);
			societyList = documentDao.getAllSociety(societyList, "society");
			projectList = documentDao.getAllProjects(projectList);

			mv = new ModelAndView("addSociety");
			mv.addObject("societytypeList", socTypes);
			mv.addObject("projectList", projectList);
			mv.addObject("societyList", societyList);

			mv.addObject("societytype", "society");

		} catch (Exception e) {
			logger.error("Exception : ",e);
		}
		LoggingHelper.logMVResponse("addSociety",mv);
		return mv;
	}

	@RequestMapping(value = { "/deleteAuth" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView deleteAuth() {
		LoggingHelper.logMVRequest("deleteAuth","");

		ModelAndView mv = null;
		try {
			mv = new ModelAndView("deleteAuth");
		} catch (Exception e) {
			logger.error("Exception : ",e);
		}
		LoggingHelper.logMVResponse("deleteAuth",mv);
		return mv;
	}

	@RequestMapping(value = { "/createSubProject" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView createSubProject() {
		LoggingHelper.logMVRequest("createSubProject","");

		ModelAndView mv = null;
		List<SocietyType> socTypes = null;
		SocietyDao documentDao = new SocietyDao();
		List<Society> societyList = new ArrayList<Society>();
		List<Project> projectList = null;

		try {
			socTypes = documentDao.getAllActiveSocietyTypes(socTypes);
			societyList = documentDao.getAllSociety(societyList, "subproject");
			projectList = documentDao.getAllProjects(projectList);

			mv = new ModelAndView("addSociety");
			mv.addObject("societytypeList", socTypes);
			mv.addObject("projectList", projectList);
			mv.addObject("societyList", societyList);

			mv.addObject("societytype", "subproject");

		} catch (Exception e) {
			logger.error("Exception : ",e);
		}
		LoggingHelper.logMVResponse("createSubProject",mv);
		return mv;
	}

	@RequestMapping(value = { "/saveSociety" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	public ModelAndView saveSociety(@ModelAttribute Society society, HttpServletRequest request) {

		LoggingHelper.logMVRequest("saveSociety",society);


		ModelAndView mv = null;
		List<SocietyType> socTypes = null;
		List<Society> societyList = new ArrayList<Society>();
		List<Project> projectList = null;
		SocietyDao documentDao = new SocietyDao();
		User user = null;

		try {
			user = (User) request.getSession().getAttribute("userObject");
			society = documentDao.insertOrUpdateSociety(society, user.getUserid());
			socTypes = documentDao.getAllActiveSocietyTypes(socTypes);

			societyList = documentDao.getAllSociety(societyList, society.getSocietytype());

			projectList = documentDao.getAllProjects(projectList);

			mv = new ModelAndView("addSociety");
			mv.addObject("societytypeList", socTypes);
			mv.addObject("societyList", societyList);
			mv.addObject("projectList", projectList);
			mv.addObject("error", "Society Added");
			
		} catch (Exception e) {
			logger.error("Exception : ",e);
		}
		
		LoggingHelper.logMVResponse("saveSociety",mv);
		return mv;
	}

	@RequestMapping(value = { "/addDoctype" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView addDoctype() {
		LoggingHelper.logMVRequest("addDoctype","");
		ModelAndView mv = null;
		List<Doctype> docTypes = null;
		DocumentDao documentDao = new DocumentDao();
		try {
			docTypes = documentDao.getAllDocumentTypes(docTypes);
			mv = new ModelAndView("addDoctype");
			mv.addObject("docTypesList", docTypes);
		} catch (Exception e) {
			logger.error("Exception : ",e);
		}
		LoggingHelper.logMVResponse("addDoctype",mv);
		return mv;
	}

	@RequestMapping(value = { "/saveDocumentType" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public ModelAndView saveDocumentType(@ModelAttribute Doctype doctype, HttpServletRequest request) {

		LoggingHelper.logMVRequest("saveDocumentType",doctype);

		ModelAndView mv = null;
		List<Doctype> docTypes = null;
		DocumentDao documentDao = new DocumentDao();
		User user = null;
		try {
			user = (User) request.getSession().getAttribute("userObject");
			doctype.setCreatedby(String.valueOf(user.getUserid()));
			doctype = documentDao.insertOrUpdateDocType(doctype);
			docTypes = documentDao.getAllDocumentTypes(docTypes);
			mv = new ModelAndView("addDoctype");
			mv.addObject("docTypesList", docTypes);
			mv.addObject("error", "Doctype Added");
		} catch (Exception e) {
			logger.error("Exception : ",e);
		}
		LoggingHelper.logMVResponse("saveDocumentType",mv);
		return mv;
	}

	@RequestMapping(value = { "/addDocSubType" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView addDocSubType() {
		LoggingHelper.logMVRequest("addDocSubType","");
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
			logger.error("Exception : ",e);
		}
		LoggingHelper.logMVResponse("addDocSubType",mv);
		return mv;
	}

	@RequestMapping(value = { "/saveDocumentSubType" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public ModelAndView saveDocumentType(@ModelAttribute DocSubType docSubType, HttpServletRequest request) {
		LoggingHelper.logMVRequest("saveDocumentSubType",docSubType);

		ModelAndView mv = null;
		List<Doctype> docTypes = null;
		List<DocSubType> docSubTypes = null;
		User user = null;
		DocumentDao documentDao = new DocumentDao();
		try {
			user = (User) request.getSession().getAttribute("userObject");
			docSubType.setCreatedby(String.valueOf(user.getUserid()));
			docSubType = documentDao.insertOrUpdateDocSubType(docSubType);
			docTypes = documentDao.getAllDocumentTypes(docTypes);
			docSubTypes = documentDao.getAllDocumentSubTypes(docSubTypes);
			mv = new ModelAndView("addDocSubtype");
			mv.addObject("docTypesList", docTypes);
			mv.addObject("docSubTypeList", docSubTypes);
			mv.addObject("error", "Doc Sub Type Added");
		} catch (Exception e) {
			logger.error("Exception : ",e);
		}
		LoggingHelper.logMVResponse("saveDocumentSubType",mv);
		return mv;
	}

	@RequestMapping(value = { "/addFormFields" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView addFormFields() {
		LoggingHelper.logMVRequest("addFormFields","");

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
			logger.error("Exception : ",e);
		}
		LoggingHelper.logMVResponse("addFormFields",mv);
		return mv;
	}

	@RequestMapping(value = { "/addDocument1" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView addDocument1(HttpServletRequest request) {
		LoggingHelper.logMVRequest("addDocument1","");
		ModelAndView mv = null;
		List<Society> societyList = null;
		User user = null;
		SocietyDao societyDao = new SocietyDao();
		try {
			user = (User) request.getSession().getAttribute("userObject");
			societyList = societyDao.getSocietyListForManager(user.getUserid(), societyList);
			mv = new ModelAndView("addDocument1");
			mv.addObject("societyList", societyList);
		} catch (Exception e) {
			logger.error("Exception : ",e);
		}
		LoggingHelper.logMVResponse("addDocument1",mv);
		return mv;
	}

	@RequestMapping(value = { "/addDocument2" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public ModelAndView addDocument2(@ModelAttribute Document document) {

		LoggingHelper.logMVRequest("addDocument2",document);

		ModelAndView mv = null;
		List<FormFields> formFields = null;
		List<Documentdetails> documentdetails = null;

		// System.out.println("document ::> "+document);

		DocumentDao documentDao = new DocumentDao();
		try {

			if (document.getUserid() == 0) {
				document.setUserid(9999);
			}

			formFields = documentDao.getFieldsForDocSubtype(document.getDocsubtypeid(), formFields);

			if (document.getCommondoc().equalsIgnoreCase("false"))
				documentdetails = documentDao.getExistingDocumentDetails(document, documentdetails);

			mv = new ModelAndView("addDocument2");

			if (documentdetails != null) {
				mv.addObject("dataExists", true);
				mv.addObject("documentdetails", documentdetails);
			} else {
				mv.addObject("dataExists", false);
			}

			mv.addObject("formFieldsList", formFields);
			mv.addObject("document", document);
		} catch (Exception e) {
			logger.error("Exception : ",e);
		}
		LoggingHelper.logMVResponse("addDocument2",mv);
		return mv;
	}

	@RequestMapping(value = { "/addDocument3" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public ModelAndView addDocument3(@org.springframework.web.bind.annotation.RequestParam Map<String, String> params,
			HttpServletRequest request) {

		LoggingHelper.logMVRequest("addDocument3",params);

		long detailsSaved = 0L;
		User user = null;
		DocumentDao ddao = new DocumentDao();

		ModelAndView mv = null;
		try {
			user = (User) request.getSession().getAttribute("userObject");

			boolean gotDocId = false;

			if (params.containsKey("documentid")) {
				String docId = params.get("documentid");
				if (docId != null) {
					if (docId.trim().length() > 0) {
						if (!docId.trim().equals("0")) {
							detailsSaved = Long.parseLong(docId);
							gotDocId = true;
						}
					}
				}
			}

			if (!gotDocId)
				detailsSaved = ddao.saveDocumentHeadAndDetails(params, user);

			mv = new ModelAndView("addDocument3");
			if (params.containsKey("societyid"))
				mv.addObject("societyid", params.get("societyid"));
			if (params.containsKey("doctypeid"))
				mv.addObject("doctypeid", params.get("doctypeid"));
			if (params.containsKey("docsubtypeid")) {
				mv.addObject("docsubtypeid", params.get("docsubtypeid"));
			}
			mv.addObject("documentId", Long.valueOf(detailsSaved));
		} catch (Exception e) {
			logger.error("Exception : ",e);
		}
		
		LoggingHelper.logMVResponse("addDocument3",mv);
		return mv;
	}

	@RequestMapping(value = { "/viewDocument" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView viewDocument() {
		LoggingHelper.logMVRequest("viewDocument","");

		ModelAndView mv = null;
		List<Society> societyList = null;
		SocietyDao societyDao = new SocietyDao();
		try {
			societyList = societyDao.getSocietyListforUser(societyList);
			mv = new ModelAndView("viewDocument");
			mv.addObject("societyList", societyList);
		} catch (Exception e) {
			logger.error("Exception : ",e);
		}
		
		LoggingHelper.logMVResponse("viewDocument",mv);
		return mv;
	}

	@RequestMapping(value = { "/addMember" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView addMember(HttpServletRequest request) {
		LoggingHelper.logMVRequest("addMember","");

		ModelAndView mv = null;
		List<Society> societyList = null;
		SocietyDao societyDao = new SocietyDao();
		User user = null;
		try {
			user = (User) request.getSession().getAttribute("userObject");
			societyList = societyDao.getSocietyListForManager(user.getUserid(), societyList);
			mv = new ModelAndView("addMember");
			mv.addObject("societyList", societyList);
			mv.addObject("randomHash", RandomStringUtils.randomAlphanumeric(10));
		} catch (Exception e) {
			logger.error("Exception : ",e);
		}
		LoggingHelper.logMVResponse("addMember",mv);
		return mv;
	}

	@RequestMapping(value = { "/createCommittee" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView createCommittee(HttpServletRequest request) {
		LoggingHelper.logMVRequest("createCommittee","");

		ModelAndView mv = null;
		List<Society> societyList = null;
		List<CommitteeMaster> committeeMasterList = null;
		SocietyDao societyDao = new SocietyDao();
		User user = null;
		try {
			user = (User) request.getSession().getAttribute("userObject");
			societyList = societyDao.getSocietyListForManager(user.getUserid(), societyList);
			committeeMasterList = societyDao.getCommitteeMaster(committeeMasterList);
			mv = new ModelAndView("createCommittee");
			mv.addObject("societyList", societyList);
			mv.addObject("committeeMasterList", committeeMasterList);
		} catch (Exception e) {
			logger.error("Exception : ",e);
		}
		LoggingHelper.logMVResponse("createCommittee",mv);
		return mv;
	}

	@RequestMapping(value = { "/displayAdminPanel" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView displayAdminPanel(HttpServletRequest request, HttpServletResponse response) {
		LoggingHelper.logMVRequest("displayAdminPanel","");

		ModelAndView mv = null;
		User user = null;
		SocietyDao sdao = new SocietyDao();
		List<Society> societyList = null;
		List<GenericBean> docs = null;

		try {
			user = (User) request.getSession().getAttribute("userObject");
			societyList = sdao.getSocietyListForManager(user.getUserid(), societyList);

			if (societyList.size() == 1) {
				mv = new ModelAndView("adminPanel");

				docs = sdao.getAllExistingDocsForSoc((Society) societyList.get(0), docs);

				mv.addObject("society", (Society) societyList.get(0));
				mv.addObject("docs", docs);
			} else {
				mv = new ModelAndView("SocietySelectionForAdmin");
				mv.addObject("societyList", societyList);
			}
		} catch (Exception e) {
			logger.error("Exception : ",e);
			e.printStackTrace();
		}
		LoggingHelper.logMVResponse("displayAdminPanel",mv);
		return mv;
	}

	@RequestMapping(value = { "/displayAdminPanelBySocId" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView displayAdminPanelBySocId(@ModelAttribute Society society, HttpServletRequest request,
			HttpServletResponse response) {

		LoggingHelper.logMVRequest("displayAdminPanelBySocId",society);

		ModelAndView mv = null;
		User user = null;
		SocietyDao sdao = new SocietyDao();
		List<Society> societyList = null;
		List<GenericBean> docs = null;
		Builder builder = null;
		Project project = null;
		try {
			user = (User) request.getSession().getAttribute("userObject");
			societyList = sdao.getSocietyListForManager(user.getUserid(), societyList);

			for (Society soc1 : societyList) {
				if (soc1.getSocietyid() == society.getSocietyid()) {
					society = soc1;
				}
			}

			builder = sdao.getBuilderBySocietyId(society);
			project = sdao.getProjectBySocietyId(society);

			mv = new ModelAndView("adminPanel");
			docs = sdao.getAllExistingDocsForSoc(society, docs);
			mv.addObject("society", society);
			mv.addObject("builder", builder);
			mv.addObject("project", project);
			mv.addObject("docs", docs);

		} catch (Exception e) {
			logger.error("Exception : ",e);
			e.printStackTrace();
		}
		LoggingHelper.logMVResponse("displayAdminPanelBySocId",mv);
		return mv;
	}

	@RequestMapping(value = { "/viewCommittee" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView viewCommittee(HttpServletRequest request, HttpServletResponse response) {
		LoggingHelper.logMVRequest("viewCommittee","");
		ModelAndView mv = null;
		try {
			mv = new ModelAndView("viewCommittee");
			mv.addObject("societyid", "8");
		} catch (Exception e) {
			logger.error("Exception : ",e);
		}
		LoggingHelper.logMVResponse("viewCommittee",mv);
		return mv;
	}

	@RequestMapping(value = { "/addSocietyPhotos" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView addSocietyPhotos(HttpServletRequest request, HttpServletResponse response) {
		LoggingHelper.logMVRequest("addSocietyPhotos","");
		ModelAndView mv = null;
		List<Society> societyList = null;
		User user = null;
		SocietyDao sdao = new SocietyDao();
		try {
			user = (User) request.getSession().getAttribute("userObject");
			societyList = sdao.getSocietyListForManager(user.getUserid(), societyList);
			mv = new ModelAndView("addSocietyPhotos");
			mv.addObject("societyList", societyList);
		} catch (Exception e) {
			logger.error("Exception : ",e);
		}
		LoggingHelper.logMVResponse("addSocietyPhotos",mv);
		return mv;
	}

	@RequestMapping(value = { "/createVendor" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView createVendor(HttpServletRequest request, HttpServletResponse response) {
		LoggingHelper.logMVRequest("createVendor","");
		ModelAndView mv = null;
		List<Society> societyList = null;
		User user = null;
		SocietyDao sdao = new SocietyDao();
		try {
			user = (User) request.getSession().getAttribute("userObject");
			societyList = sdao.getSocietyListForManager(user.getUserid(), societyList);
			mv = new ModelAndView("createVendor");
			mv.addObject("societyList", societyList);
		} catch (Exception e) {
			logger.error("Exception : ",e);
		}
		LoggingHelper.logMVResponse("createVendor",mv);
		return mv;
	}

	@RequestMapping(value = { "/societyManagerMapping" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView societyManagerMapping() {
		LoggingHelper.logMVRequest("societyManagerMapping","");
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
			logger.error("Exception : ",e);
		}
		LoggingHelper.logMVResponse("societyManagerMapping",mv);
		return mv;
	}

	@RequestMapping(value = { "/createBuilder" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView createBuilder() {
		LoggingHelper.logMVRequest("createBuilder","");

		ModelAndView mv = null;
		SocietyDao documentDao = new SocietyDao();
		List<Builder> builderList = null;
		try {
			builderList = documentDao.getAllBuilder(builderList);
			mv = new ModelAndView("createBuilder");
			mv.addObject("builderList", builderList);
		} catch (Exception e) {
			logger.error("Exception : ",e);
		}
		LoggingHelper.logMVResponse("createBuilder",mv);
		return mv;
	}

	@RequestMapping(value = { "/saveBuilder" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	public ModelAndView saveBuilder(@ModelAttribute Builder builder, HttpServletRequest request) {
		LoggingHelper.logMVRequest("saveBuilder",builder);
		ModelAndView mv = null;
		List<Builder> builderList = null;
		SocietyDao documentDao = new SocietyDao();
		User user = null;
		try {
			user = (User) request.getSession().getAttribute("userObject");
			builder.setCreatedby(String.valueOf(user.getUserid()));
			builder = documentDao.insertOrUpdateBuilder(builder);
			builderList = documentDao.getAllBuilder(builderList);

			mv = new ModelAndView("createBuilder");
			mv.addObject("builderList", builderList);
			mv.addObject("error", "Builder Profile Added");
		} catch (Exception e) {
			logger.error("Exception : ",e);
		}
		LoggingHelper.logMVResponse("saveBuilder",mv);
		return mv;
	}

	@RequestMapping(value = { "/createProject" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView createProject() {
		LoggingHelper.logMVRequest("createProject","");

		ModelAndView mv = null;
		SocietyDao documentDao = new SocietyDao();
		List<Builder> builderList = null;
		List<Project> projectList = null;
		try {
			builderList = documentDao.getAllBuilder(builderList);
			projectList = documentDao.getAllProjects(projectList);

			mv = new ModelAndView("createProject");
			mv.addObject("builderList", builderList);
			mv.addObject("projectList", projectList);
		} catch (Exception e) {
			logger.error("Exception : ",e);
		}
		LoggingHelper.logMVResponse("createProject",mv);
		return mv;
	}

	@RequestMapping(value = { "/saveProject" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	public ModelAndView saveProject(@ModelAttribute Project project, HttpServletRequest request) {

		LoggingHelper.logMVRequest("saveProject",project);

		ModelAndView mv = null;
		List<Builder> builderList = null;
		List<Project> projectList = null;
		SocietyDao documentDao = new SocietyDao();
		User user = null;

		try {
			user = (User) request.getSession().getAttribute("userObject");
			project.setCreatedby(String.valueOf(user.getUserid()));
			project = documentDao.insertOrUpdateProject(project);

			projectList = documentDao.getAllProjects(projectList);
			builderList = documentDao.getAllBuilder(builderList);

			mv = new ModelAndView("createProject");
			mv.addObject("builderList", builderList);
			mv.addObject("projectList", projectList);

			mv.addObject("error", "Project Added");
		} catch (Exception e) {
			logger.error("Exception : ",e);
		}
		LoggingHelper.logMVResponse("saveProject",mv);
		return mv;
	}

	@RequestMapping(value = { "/societyDocumentMapping" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView societyDocumentMapping() {
		LoggingHelper.logMVRequest("societyDocumentMapping","");

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
			mv.addObject("socDocMapping", socDocMapping);

		} catch (Exception e) {
			logger.error("Exception : ",e);
		}
		LoggingHelper.logMVResponse("societyDocumentMapping",mv);
		return mv;
	}

	@RequestMapping(value = { "/addSocDocMapping" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public ModelAndView addSocDocMapping(@ModelAttribute GenericBean bean, HttpServletRequest request) {

		LoggingHelper.logMVRequest("addSocDocMapping",bean);

		ModelAndView mv = null;
		List<Society> societyList = null;
		List<Doctype> docTypes = null;
		List<GenericBean> socDocMapping = null;
		SocietyDao societyDao = new SocietyDao();
		DocumentDao documentDao = new DocumentDao();
		User user = null;

		try {
			user = (User) request.getSession().getAttribute("userObject");
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
			logger.error("Exception : ",e);
		}
		LoggingHelper.logMVResponse("addSocDocMapping",mv);
		return mv;
	}

	@RequestMapping(value = { "/createAdminUser" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView createAdminUser() {
		LoggingHelper.logMVRequest("createAdminUser","");

		ModelAndView mv = null;
		SocietyDao documentDao = new SocietyDao();
		List<GenericBean> roleList = null;
		List<User> adminUsers = null;

		try {
			roleList = documentDao.getAllRoles(roleList);
			adminUsers = documentDao.getAllAdminUsers(adminUsers);

			mv = new ModelAndView("createAdminUser");
			mv.addObject("roleList", roleList);
			mv.addObject("adminUsers", adminUsers);

		} catch (Exception e) {
			logger.error("Exception : ",e);
		}
		LoggingHelper.logMVResponse("createAdminUser",mv);
		return mv;
	}

	@RequestMapping(value = { "/saveAdminUser" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public ModelAndView saveAdminUser(@ModelAttribute User adminUser, HttpServletRequest request) {

		LoggingHelper.logMVRequest("saveAdminUser",adminUser);
		ModelAndView mv = null;
		List<GenericBean> roleList = null;
		SocietyDao documentDao = new SocietyDao();
		User user = null;
		List<User> adminUsers = null;

		try {
			user = (User) request.getSession().getAttribute("userObject");
			adminUser.setCreatedby(String.valueOf(user.getUserid()));
			adminUser = documentDao.saveAdminUser(adminUser);
			adminUsers = documentDao.getAllAdminUsers(adminUsers);

			roleList = documentDao.getAllRoles(roleList);

			mv = new ModelAndView("createAdminUser");
			mv.addObject("roleList", roleList);
			mv.addObject("adminUsers", adminUsers);

			mv.addObject("error", "User Added");
		} catch (Exception e) {
			logger.error("Exception : ",e);
		}
		LoggingHelper.logMVResponse("saveAdminUser",mv);
		return mv;
	}

	@RequestMapping(value = { "/addMemberPhotos" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView addMemberPhotos(HttpServletRequest request, HttpServletResponse response) {
		LoggingHelper.logMVRequest("addMemberPhotos","");
		ModelAndView mv = null;
		List<Society> societyList = null;
		User user = null;
		SocietyDao sdao = new SocietyDao();
		try {
			user = (User) request.getSession().getAttribute("userObject");
			societyList = sdao.getSocietyListForManager(user.getUserid(), societyList);
			mv = new ModelAndView("addMemberPhotos");
			mv.addObject("societyList", societyList);
		} catch (Exception e) {
			logger.error("Exception : ",e);
		}
		LoggingHelper.logMVResponse("addMemberPhotos",mv);
		return mv;
	}

	@RequestMapping(value = { "/addNotice" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView addNotice(HttpServletRequest request, HttpServletResponse response) {
		LoggingHelper.logMVRequest("addNotice","");
		ModelAndView mv = null;
		List<Society> societyList = null;
		User user = null;
		SocietyDao sdao = new SocietyDao();
		try {
			user = (User) request.getSession().getAttribute("userObject");
			societyList = sdao.getSocietyListForManager(user.getUserid(), societyList);
			mv = new ModelAndView("addNotice");
			mv.addObject("societyList", societyList);
		} catch (Exception e) {
			logger.error("Exception : ",e);
		}
		LoggingHelper.logMVResponse("addNotice",mv);
		return mv;
	}

	@RequestMapping(value = { "/addPolicy" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView addPolicy(HttpServletRequest request, HttpServletResponse response) {
		LoggingHelper.logMVRequest("addPolicy","");
		ModelAndView mv = null;
		List<Society> societyList = null;
		User user = null;
		SocietyDao sdao = new SocietyDao();
		try {
			user = (User) request.getSession().getAttribute("userObject");
			societyList = sdao.getSocietyListForManager(user.getUserid(), societyList);
			mv = new ModelAndView("addPolicy");
			mv.addObject("societyList", societyList);
		} catch (Exception e) {
			logger.error("Exception : ",e);
		}
		LoggingHelper.logMVResponse("addPolicy",mv);
		return mv;
	}

	@RequestMapping(value = { "/viewNoticeboard" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView viewNoticeboard(@ModelAttribute Society society, HttpServletRequest request,
			HttpServletResponse response) {
		LoggingHelper.logMVRequest("viewNoticeboard",society);
		ModelAndView mv = null;
		List<HashMap<String, Object>> docList = new ArrayList<HashMap<String, Object>>();
		SocietyDao sdao = new SocietyDao();
		try {
			docList = sdao.getNoticeboardDocs(society.getSocietyid(), docList);
			mv = new ModelAndView("viewNoticeboard");
			mv.addObject("pageTitle", "Society Notice Board");
			mv.addObject("docList", docList);
		} catch (Exception e) {
			logger.error("Exception : ",e);
		}
		LoggingHelper.logMVResponse("viewNoticeboard",mv);
		return mv;
	}

	@RequestMapping(value = { "/viewSocietyPolicy" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView viewSocietyPolicy(@ModelAttribute Society society, HttpServletRequest request,
			HttpServletResponse response) {
		LoggingHelper.logMVRequest("viewSocietyPolicy","");
		ModelAndView mv = null;
		List<HashMap<String, Object>> docList = new ArrayList<HashMap<String, Object>>();
		SocietyDao sdao = new SocietyDao();
		try {
			docList = sdao.getSocietyPolicyDocuments(society.getSocietyid(), docList);
			mv = new ModelAndView("viewNoticeboard");
			mv.addObject("pageTitle", "Society Policies/Amenities");
			mv.addObject("docList", docList);
		} catch (Exception e) {
			logger.error("Exception : ",e);
		}
		LoggingHelper.logMVResponse("viewSocietyPolicy",mv);
		return mv;
	}

	@RequestMapping(value = { "/displaySelfSociety" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView displaySelfSociety(HttpServletRequest request, HttpServletResponse response) {
		LoggingHelper.logMVRequest("displaySelfSociety","");
		ModelAndView mv = null;
		Userprofile userprofile = null;
		SocietyDao sdao = new SocietyDao();
		Society society = new Society();
		try {
			userprofile = (Userprofile) request.getSession().getAttribute("userprofile");
			society.setSocietyid(Long.valueOf(userprofile.getSocietyid()));
			society = sdao.getSocietyDetailsById(society);
			mv = new ModelAndView("selfSociety");
			mv.addObject("society", society);
		} catch (Exception e) {
			logger.error("Exception : ",e);
			e.printStackTrace();
		}
		LoggingHelper.logMVResponse("displaySelfSociety",mv);
		return mv;
	}

	@RequestMapping(value = { "/displayDocument" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView displayDocument(@RequestParam("docsubtypeid") String docsubtypeid,
			@RequestParam("userid") String userid, @RequestParam("societyid") String societyid,
			HttpServletRequest request, HttpServletResponse response) {
		LoggingHelper.logMVRequest("displayDocument",request.getParameterMap());

		ModelAndView mv = null;
		List<HashMap<String, Object>> docList = new ArrayList<HashMap<String, Object>>();
		List<GenericBean> data = null;
		SocietyDao sdao = new SocietyDao();
		DocumentDao ddao = new DocumentDao();
		try {
			docList = sdao.displayDocument(docsubtypeid, userid, societyid, docList);
			data = ddao.getdisplayData(docsubtypeid, userid, societyid, data);

			mv = new ModelAndView("displayDocument");
			mv.addObject("docList", docList);
			mv.addObject("dataList", data);

			/*
			 * mv = new ModelAndView("viewDocumentFromAdminPanel");
			 * mv.addObject("societyid", bean.getSocietyid());
			 * mv.addObject("doctypeid", bean.getDoctypeid());
			 * mv.addObject("docsubtypeid", bean.getDocsubtypeid());
			 */

		} catch (Exception e) {
			logger.error("Exception : ",e);
		}
		LoggingHelper.logMVResponse("displayDocument",mv);
		return mv;
	}

	@RequestMapping(value = { "/displayDocumentFromSearch" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView displayDocumentFromSearch(@RequestParam("documentid") String documentid,
			HttpServletRequest request, HttpServletResponse response) {
		LoggingHelper.logMVRequest("displayDocumentFromSearch",request.getParameterMap());

		ModelAndView mv = null;
		List<HashMap<String, Object>> docList = new ArrayList<HashMap<String, Object>>();
		List<GenericBean> data = null;
		SocietyDao sdao = new SocietyDao();
		DocumentDao ddao = new DocumentDao();
		try {
			docList = sdao.displayDocumentFromSearch(documentid, docList);
			data = ddao.getdisplayDataByDocId(documentid, data);

			mv = new ModelAndView("displayDocument");
			mv.addObject("docList", docList);
			mv.addObject("dataList", data);
			mv.addObject("documentid", documentid);

		} catch (Exception e) {
			logger.error("Exception : ",e);
		}
		LoggingHelper.logMVResponse("displayDocumentFromSearch",mv);
		return mv;
	}

	@RequestMapping(value = { "/showDocFromAdminPanel" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView showDocFromAdminPanel(@ModelAttribute GenericBean bean, HttpServletRequest request,
			HttpServletResponse response) {
		LoggingHelper.logMVRequest("showDocFromAdminPanel",bean);
		ModelAndView mv = null;
		try {
			mv = new ModelAndView("viewDocumentFromAdminPanel");
			mv.addObject("societyid", bean.getSocietyid());
			mv.addObject("doctypeid", bean.getDoctypeid());
			mv.addObject("docsubtypeid", bean.getDocsubtypeid());
		} catch (Exception e) {
			logger.error("Exception : ",e);
		}
		LoggingHelper.logMVResponse("showDocFromAdminPanel",mv);
		return mv;
	}

	@RequestMapping(value = { "/createVendorCards" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView createVendorCards(HttpServletRequest request, HttpServletResponse response) {
		LoggingHelper.logMVRequest("createVendorCards","");
		ModelAndView mv = null;
		List<Society> societyList = null;
		User user = null;
		SocietyDao sdao = new SocietyDao();
		try {
			user = (User) request.getSession().getAttribute("userObject");
			societyList = sdao.getSocietyListForManager(user.getUserid(), societyList);
			mv = new ModelAndView("createVendorCards");
			mv.addObject("societyList", societyList);
		} catch (Exception e) {
			logger.error("Exception : ",e);
		}
		LoggingHelper.logMVResponse("createVendorCards",mv);
		return mv;
	}

	@RequestMapping(value = { "/createVendorPhoto" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView createVendorPhoto(HttpServletRequest request, HttpServletResponse response) {
		LoggingHelper.logMVRequest("createVendorPhoto","");
		ModelAndView mv = null;
		List<Society> societyList = null;
		User user = null;
		SocietyDao sdao = new SocietyDao();
		try {
			user = (User) request.getSession().getAttribute("userObject");
			societyList = sdao.getSocietyListForManager(user.getUserid(), societyList);
			mv = new ModelAndView("createVendorPhoto");
			mv.addObject("societyList", societyList);
		} catch (Exception e) {
			logger.error("Exception : ",e);
		}
		LoggingHelper.logMVResponse("createVendorPhoto",mv);
		return mv;
	}

	@RequestMapping(value = { "/addCallRef" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView addCallRef(HttpServletRequest request, HttpServletResponse response) {
		LoggingHelper.logMVRequest("addCallRef","");
		ModelAndView mv = null;
		User user = null;
		SocietyDao sdao = new SocietyDao();
		try {
			SimpleDateFormat sf = new SimpleDateFormat("YYMMd");
			String callrefid = "ODS" + sf.format(new Date()) + RandomStringUtils.randomNumeric(5);
			user = (User) request.getSession().getAttribute("userObject");
			mv = new ModelAndView("AddCallReference");
			mv.addObject("callrefid", callrefid);
		} catch (Exception e) {
			logger.error("Exception : ",e);
		}
		LoggingHelper.logMVResponse("addCallRef",mv);
		return mv;
	}

	@RequestMapping(value = { "/saveCallRef" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	public ModelAndView saveCallRef(@ModelAttribute CallReference callref, HttpServletRequest request,
			HttpServletResponse response) {
		LoggingHelper.logMVRequest("saveCallRef",callref);
		ModelAndView mv = null;
		SocietyDao documentDao = new SocietyDao();

		try {

			callref = documentDao.saveCallRef(callref);

			mv = new ModelAndView("AddCallReference2");

			SimpleDateFormat sf = new SimpleDateFormat("YYMMd");
			String callrefid = "ODS" + sf.format(new Date()) + RandomStringUtils.randomNumeric(5);

			mv.addObject("callrefid", callrefid);
			mv.addObject("callref", callref);
			mv.addObject("error", "Call Reference Added");

		} catch (Exception e) {
			logger.error("Exception : ",e);
		}
		LoggingHelper.logMVResponse("saveCallRef",mv);
		return mv;
	}

	@RequestMapping(value = { "/saveCallRef3.do" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public ModelAndView saveCallRef3(@ModelAttribute CallReference callref, HttpServletRequest request,
			HttpServletResponse response) {
		LoggingHelper.logMVRequest("saveCallRef3",callref);
		ModelAndView mv = null;
		try {
			mv = new ModelAndView("AddCallReference3");
			mv.addObject("callref", callref);
			mv.addObject("error", "Contact Added");

		} catch (Exception e) {
			logger.error("Exception : ",e);
		}
		LoggingHelper.logMVResponse("saveCallRef3",mv);
		return mv;
	}

	@RequestMapping(value = { "/viewAllCallRef" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView viewAllCallRef(HttpServletRequest request, HttpServletResponse response) {
		LoggingHelper.logMVRequest("viewAllCallRef","");
		ModelAndView mv = null;
		User user = null;
		List<CallReference> calls = null;
		SocietyDao sdao = new SocietyDao();
		try {
			user = (User) request.getSession().getAttribute("userObject");

			calls = sdao.getAllCallRefs(calls);

			mv = new ModelAndView("viewAllCallRef");
			mv.addObject("calls", calls);
		} catch (Exception e) {
			logger.error("Exception : ",e);
		}
		LoggingHelper.logMVResponse("viewAllCallRef",mv);
		return mv;
	}

	@RequestMapping(value = { "/getRefContactpage" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView getRefContactpage(@RequestParam("callrefid") long callrefid, HttpServletRequest request,
			HttpServletResponse response) {
		LoggingHelper.logMVRequest("getRefContactpage",request.getParameterMap());

		CallReference callref = new CallReference();
		callref.setCallrefid(callrefid);

		ModelAndView mv = null;
		try {

			mv = new ModelAndView("AddCallReference2");
			mv.addObject("callref", callref);

		} catch (Exception e) {
			logger.error("Exception : ",e);
		}
		LoggingHelper.logMVRequest("getRefContactpage","");

		return mv;
	}

	@RequestMapping(value = { "/getRefMeetingpage.do" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView getRefMeetingpage(@RequestParam("callrefid") long callrefid, HttpServletRequest request,
			HttpServletResponse response) {
		LoggingHelper.logMVRequest("getRefMeetingpage",request.getParameterMap());

		ModelAndView mv = null;
		try {

			CallReference callref = new CallReference();
			callref.setCallrefid(callrefid);
			mv = new ModelAndView("AddCallReference3");
			mv.addObject("callref", callref);
		} catch (Exception e) {
			logger.error("Exception : ",e);
		}
		LoggingHelper.logMVResponse("getRefMeetingpage",mv);
		return mv;
	}

	@RequestMapping(value = { "/editUserProfile" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView editUserProfile(HttpServletRequest request) {
		LoggingHelper.logMVRequest("editUserProfile","");
		ModelAndView mv = null;
		List<Society> societyList = null;
		SocietyDao societyDao = new SocietyDao();
		User user = null;
		try {
			user = (User) request.getSession().getAttribute("userObject");
			societyList = societyDao.getSocietyListForManager(user.getUserid(), societyList);
			mv = new ModelAndView("editUserProfile");
			mv.addObject("societyList", societyList);
		} catch (Exception e) {
			logger.error("Exception : ",e);
		}
		LoggingHelper.logMVResponse("editUserProfile",mv);
		return mv;
	}

	@RequestMapping(value = { "/deleteDocument" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView deleteDocument() {
		LoggingHelper.logMVRequest("deleteDocument","");

		ModelAndView mv = null;
		List<Society> societyList = null;
		SocietyDao societyDao = new SocietyDao();
		try {
			societyList = societyDao.getSocietyListforUser(societyList);
			mv = new ModelAndView("deleteDocument");
			mv.addObject("societyList", societyList);
		} catch (Exception e) {
			logger.error("Exception : ",e);
		}
		LoggingHelper.logMVResponse("deleteDocument",mv);
		return mv;
	}

	@RequestMapping(value = { "/managerDocViewAuth" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView managerDocViewAuth(HttpServletRequest request) {
		LoggingHelper.logMVRequest("managerDocViewAuth","");

		ModelAndView mv = null;
		List<Society> societyList = null;
		List<Doctype> docTypes = null;
		List<GenericBean> socDocMapping = null;
		SocietyDao societyDao = new SocietyDao();
		DocumentDao documentDao = new DocumentDao();
		User user = null;
		try {

			user = (User) request.getSession().getAttribute("userObject");
			docTypes = documentDao.getAllDocumentTypes(docTypes);
			societyList = societyDao.getSocietyListForManager(user.getUserid(), societyList);
			socDocMapping = societyDao.getSocDocMapping(socDocMapping);

			mv = new ModelAndView("managerDocViewAuth");
			mv.addObject("societyList", societyList);
			mv.addObject("docTypes", docTypes);
			mv.addObject("socDocMapping", socDocMapping);

		} catch (Exception e) {
			logger.error("Exception : ",e);
		}
		LoggingHelper.logMVResponse("managerDocViewAuth",mv);
		return mv;
	}

	@RequestMapping(value = { "/manageDesignation" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView manageDesignation() {
		LoggingHelper.logMVRequest("manageDesignation","");
		ModelAndView mv = null;
		List<GenericBean> desigList = null;
		SocietyDao societyDao = new SocietyDao();
		try {
			desigList = societyDao.getAllDesignations(desigList);
			mv = new ModelAndView("manageDesignation");
			mv.addObject("desigList", desigList);
		} catch (Exception e) {
			logger.error("Exception : ",e);
		}
		LoggingHelper.logMVResponse("manageDesignation",mv);
		return mv;
	}

	@RequestMapping(value = { "/createRole" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView createRole() {
		LoggingHelper.logMVRequest("createRole","");
		ModelAndView mv = null;
		List<RoleTransaction> roleList = null;
		LoginDao loginDao = new LoginDao();
		try {
			roleList = loginDao.getAllRoles(roleList);
			mv = new ModelAndView("manageDesignation");
			mv.addObject("roleList", roleList);
		} catch (Exception e) {
			logger.error("Exception : ",e);
		}
		LoggingHelper.logMVResponse("createRole",mv);
		return mv;
	}

	@RequestMapping(value = { "/addConfidentialDocAccess" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView addConfidentialDocAccess() {
		LoggingHelper.logMVRequest("addConfidentialDocAccess","");
		ModelAndView mv = null;
		List<RoleTransaction> roleList = null;
		LoginDao loginDao = new LoginDao();
		try {
			roleList = loginDao.getAllRoles(roleList);
			mv = new ModelAndView("addConfidentialDocAccess");
			mv.addObject("roleList", roleList);
		} catch (Exception e) {
			logger.error("Exception : ",e);
		}
		LoggingHelper.logMVResponse("addConfidentialDocAccess",mv);
		return mv;
	}

	@RequestMapping(value = { "/createBrochureBuilder" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView createBuilderBrochure() {
		LoggingHelper.logMVRequest("createBrochureBuilder","");
		ModelAndView mv = null;
		SocietyDao documentDao = new SocietyDao();
		List<Builder> builderList = null;
		try {
			builderList = documentDao.getAllBuilder(builderList);
			mv = new ModelAndView("createBrochure");
			mv.addObject("builderList", builderList);
			mv.addObject("pageTitle", "Add Builder Brochure");
			mv.addObject("typeVal", "998");
			mv.addObject("type", "builder");

		} catch (Exception e) {
			logger.error("Exception : ",e);
		}	
		LoggingHelper.logMVResponse("createBrochureBuilder",mv);
		return mv;
	}

	@RequestMapping(value = { "/createBrochureProject" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView createBrochureProject() {
		LoggingHelper.logMVRequest("createBrochureProject","");
		ModelAndView mv = null;
		SocietyDao documentDao = new SocietyDao();
		List<Builder> builderList = null;
		try {
			builderList = documentDao.getAllBuilder(builderList);
			mv = new ModelAndView("createBrochure");
			mv.addObject("builderList", builderList);
			mv.addObject("pageTitle", "Add Project Brochure");
			mv.addObject("typeVal", "997");
			mv.addObject("type", "project");

		} catch (Exception e) {
			logger.error("Exception : ",e);
		}
		LoggingHelper.logMVResponse("createBrochureProject",mv);
		return mv;
	}

	@RequestMapping(value = { "/createBrochureSubProject" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView createBrochureSubProject() {
		LoggingHelper.logMVRequest("createBrochureSubProject","");
		ModelAndView mv = null;
		SocietyDao documentDao = new SocietyDao();
		List<Builder> builderList = null;
		try {
			builderList = documentDao.getAllBuilder(builderList);
			mv = new ModelAndView("createBrochure");
			mv.addObject("builderList", builderList);
			mv.addObject("pageTitle", "Add Sub-Project Brochure");
			mv.addObject("typeVal", "996");
			mv.addObject("type", "subproject");

		} catch (Exception e) {
			logger.error("Exception : ",e);
		}
		LoggingHelper.logMVResponse("createBrochureSubProject",mv);
		return mv;
	}

	@RequestMapping(value = { "/brochureSelectionBuilder" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView brochureSelectionBuilder(HttpServletRequest request, HttpServletResponse response) {
		LoggingHelper.logMVRequest("brochureSelectionBuilder","");
		ModelAndView mv = null;
		SocietyDao sdao = new SocietyDao();
		List<Builder> builderList = null;
		try {
			builderList = sdao.getAllBuilder(builderList);
			mv = new ModelAndView("brochureSelection");
			mv.addObject("pageTitle", "View Builder Brochure");
			mv.addObject("type", "builder");
			mv.addObject("typeVal", "998");
			mv.addObject("builderList", builderList);
		} catch (Exception e) {
			logger.error("Exception : ",e);
		}
		LoggingHelper.logMVResponse("brochureSelectionBuilder",mv);
		return mv;
	}

	@RequestMapping(value = { "/brochureSelectionProject" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView brochureSelectionProject(HttpServletRequest request, HttpServletResponse response) {
		LoggingHelper.logMVRequest("brochureSelectionProject","");
		ModelAndView mv = null;
		SocietyDao sdao = new SocietyDao();
		List<Builder> builderList = null;
		try {
			builderList = sdao.getAllBuilder(builderList);
			mv = new ModelAndView("brochureSelection");
			mv.addObject("pageTitle", "View Project Brochure");
			mv.addObject("type", "project");
			mv.addObject("typeVal", "997");
			mv.addObject("builderList", builderList);
		} catch (Exception e) {
			logger.error("Exception : ",e);
		}
		LoggingHelper.logMVResponse("brochureSelectionProject",mv);
		return mv;
	}

	@RequestMapping(value = { "/brochureSelectionSubProject" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView brochureSelectionSubProject(HttpServletRequest request, HttpServletResponse response) {
		LoggingHelper.logMVRequest("brochureSelectionSubProject","");
		ModelAndView mv = null;
		SocietyDao sdao = new SocietyDao();
		List<Builder> builderList = null;
		try {
			builderList = sdao.getAllBuilder(builderList);
			mv = new ModelAndView("brochureSelection");
			mv.addObject("pageTitle", "View Sub Project Brochure");
			mv.addObject("type", "subproject");
			mv.addObject("typeVal", "996");
			mv.addObject("builderList", builderList);
		} catch (Exception e) {
			logger.error("Exception : ",e);
		}
		LoggingHelper.logMVResponse("brochureSelectionSubProject",mv);
		return mv;
	}

	@RequestMapping(value = { "/viewBrochure" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public ModelAndView viewBuilderBrochure(@ModelAttribute GenericBean gbean, HttpServletRequest request,
			HttpServletResponse response) {
		LoggingHelper.logMVRequest("viewBrochure","");
		ModelAndView mv = null;
		List<HashMap<String, Object>> docList = new ArrayList<HashMap<String, Object>>();
		SocietyDao sdao = new SocietyDao();
		try {
			docList = sdao.getBrochure(gbean, docList);
			mv = new ModelAndView("viewNoticeboard");
			mv.addObject("pageTitle", "Brochure");
			mv.addObject("docList", docList);
		} catch (Exception e) {
			logger.error("Exception : ",e);
		}
		LoggingHelper.logMVResponse("viewBrochure",mv);
		return mv;
	}

} // End Of class
