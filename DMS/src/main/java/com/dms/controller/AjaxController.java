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
import com.dms.logging.LoggingHelper;

@Controller
public class AjaxController {
	private static final Logger reqreslogger = LoggerFactory.getLogger("reqreslogger");

	public AjaxController() {
	}

	private static final Logger logger = LoggerFactory.getLogger(AjaxController.class);

	@RequestMapping(value = { "/societyAutosuggest" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET }, headers = {
					"Accept=*/*" }, produces = { "application/json" })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<String> societyAutosuggest(@RequestParam("searchText") String searchText,HttpServletRequest request) {

		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"societyAutosuggest", searchText);

		SocietyDao societyDao = new SocietyDao();
		List<String> codeList1 = new ArrayList();
		try {
			List<Society> societyList = societyDao.getSocietyAutosuggest(searchText);
			for (int i = 0; i < societyList.size(); i++) {
				Society soc = (Society) societyList.get(i);
				String name = soc.getSocietyid() + "--" + soc.getSocietyname();
				codeList1.add(i, name);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		LoggingHelper.logAjaxResponse("societyAutosuggest", codeList1);

		return codeList1;
	}

	@RequestMapping(value = { "/userAutosuggest" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET }, headers = {
					"Accept=*/*" }, produces = { "application/json" })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<String> userAutosuggest(@RequestParam("searchText") String searchText,HttpServletRequest request) {

		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"userAutosuggest",searchText);


		SocietyDao societyDao = new SocietyDao();
		List<String> codeList1 = new ArrayList();

		try {
			List<User> userList = societyDao.getUserAutosuggestByUsername(searchText);
			for (int i = 0; i < userList.size(); i++) {
				User user = (User) userList.get(i);
				String name = user.getUserid() + "--" + user.getFirstName() + " " + user.getLastName();
				codeList1.add(i, name);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		LoggingHelper.logAjaxResponse("userAutosuggest",codeList1);
		return codeList1;
	}

	@RequestMapping(value = { "/userAutosuggestForSociety" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET }, headers = {
					"Accept=*/*" }, produces = { "application/json" })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<String> userAutosuggestForSociety(@RequestParam("searchText") String searchText,
			@RequestParam("societyid") String societyid,HttpServletRequest request) {

		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"userAutosuggestForSociety",searchText);


		SocietyDao societyDao = new SocietyDao();
		List<String> codeList1 = new ArrayList();

		try {
			List<User> userList = societyDao.getUserAutosuggest(searchText, societyid);
			for (int i = 0; i < userList.size(); i++) {
				User user = (User) userList.get(i);
				String name = user.getUserid() + "--" + user.getFirstName() + " " + user.getLastName();
				codeList1.add(i, name);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		LoggingHelper.logAjaxResponse("userAutosuggestForSociety",codeList1);

		return codeList1;
	}

	@RequestMapping(value = { "/saveFormFields" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public FormFields saveDocumentType(@ModelAttribute FormFields formFields, HttpServletRequest request) {
		
		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"saveFormFields",formFields);

		DocumentDao documentDao = new DocumentDao();
		User user = null;
		try {
			user = (User) request.getSession().getAttribute("userObject");
			formFields.setCreatedby(String.valueOf(user.getUserid()));
			formFields = documentDao.insertOrUpdateFormFields(formFields);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		LoggingHelper.logAjaxResponse("saveFormFields",formFields);

		return formFields;
	}

	@RequestMapping(value = { "/getFieldsForDocSubtype" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public List<FormFields> getFieldsForDocSubtype(@ModelAttribute FormFields formField,HttpServletRequest request) {

		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"getFieldsForDocSubtype",formField);

		DocumentDao documentDao = new DocumentDao();
		List<FormFields> formFields = null;
		try {
			formFields = documentDao.getFieldsForDocSubtype(formField.getDocsubtypeid(), formFields);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		LoggingHelper.logAjaxResponse("getFieldsForDocSubtype",formFields);

		return formFields;
	}

	@RequestMapping(value = { "/getDoctypeBySocId" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public List<Doctype> getDoctypeBySocId(@ModelAttribute Society society,HttpServletRequest request) {
		
		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"getDoctypeBySocId",society);

		DocumentDao documentDao = new DocumentDao();
		List<Doctype> doctypes = null;
		try {
			doctypes = documentDao.getDoctypeBySocId(society.getSocietyid(), doctypes);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		LoggingHelper.logAjaxResponse("getDoctypeBySocId",doctypes);

		return doctypes;
	}

	@RequestMapping(value = { "/getDocSubtypeByDocId" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public List<DocSubType> getDocSubtypeByDocId(@ModelAttribute DocSubType docSubType,HttpServletRequest request) {
		
		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"getDocSubtypeByDocId",docSubType);

		DocumentDao documentDao = new DocumentDao();
		List<DocSubType> docSubTypes = null;
		try {
			docSubTypes = documentDao.getDocSubtypeByDocId(docSubType.getDoctypeid(), docSubTypes);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		LoggingHelper.logAjaxResponse("getDocSubtypeByDocId",docSubTypes);

		return docSubTypes;
	}

	@RequestMapping(value = { "/getDocumentListForView" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public List<Document> getDocumentListForView(@ModelAttribute Document document,HttpServletRequest request) {
		
		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"getDocumentListForView",document);

		DocumentDao documentDao = new DocumentDao();
		List<Document> documents = null;
		try {
			documents = documentDao.getDocumentListForView(document, documents);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		LoggingHelper.logAjaxResponse("getDocumentListForView",documents);

		return documents;
	}

	@RequestMapping(value = { "/saveMemberDetails" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public Userprofile saveMemberDetails(@ModelAttribute Userprofile userprofile, HttpServletRequest request) {
		
		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"saveMemberDetails",userprofile);

		User user = null;
		SocietyDao societyDao = new SocietyDao();
		try {
			user = (User) request.getSession().getAttribute("userObject");
			userprofile = societyDao.saveMemberDetails(userprofile, user);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		LoggingHelper.logAjaxResponse("saveMemberDetails",userprofile);

		return userprofile;
	}

	@RequestMapping(value = { "/getMembersForSociety" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public List<Userprofile> getMembersForSociety(@ModelAttribute Userprofile userprofile,HttpServletRequest request) {
		
		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"getMembersForSociety",userprofile);

		SocietyDao societyDao = new SocietyDao();
		List<Userprofile> profiles = null;
		try {
			profiles = societyDao.getMembersForSociety(userprofile.getSocietyid(), profiles);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		LoggingHelper.logAjaxResponse("getMembersForSociety",profiles);

		return profiles;
	}

	@RequestMapping(value = { "/addCommitteeMember" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public Committee addCommitteeMember(@ModelAttribute Committee committee,HttpServletRequest request) {
		
		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"addCommitteeMember",committee);

		SocietyDao societyDao = new SocietyDao();
		try {
			committee = societyDao.addCommitteeMember(committee);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		LoggingHelper.logAjaxResponse("addCommitteeMember",committee);

		return committee;
	}

	@RequestMapping(value = { "/getCommitteMembersForSociety" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public Map<String, List<Committee>> getCommitteMembersForSociety(@ModelAttribute Committee committee,HttpServletRequest request) {
		
		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"getCommitteMembersForSociety",committee);

		SocietyDao societyDao = new SocietyDao();
		Map<String, List<Committee>> committees = new HashMap();
		try {
			committees = societyDao.getCommitteMembersForSociety(committee.getSocietyid(), committees);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		LoggingHelper.logAjaxResponse("getCommitteMembersForSociety",committees);

		return committees;
	}

	@RequestMapping(value = { "/removeCommitteeMember" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public String removeCommitteeMember(@ModelAttribute Committee committee,HttpServletRequest request) {
		
		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"removeCommitteeMember",committee);

		int rowsUpdated = 0;
		SocietyDao societyDao = new SocietyDao();
		try {
			rowsUpdated = societyDao.removeCommitteeMember(committee);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		LoggingHelper.logAjaxResponse("removeCommitteeMember",rowsUpdated);

		if (rowsUpdated > 0)
			return "success";
		return "failed";
	}

	@RequestMapping(value = { "/saveVendorDetails" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public Vendor saveVendorDetails(@ModelAttribute Vendor vendor, HttpServletRequest request,
			HttpServletResponse response) {
		
		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"saveVendorDetails",vendor);

		User user = null;
		SocietyDao societyDao = new SocietyDao();
		try {
			user = (User) request.getSession().getAttribute("userObject");
			vendor.setCreatedby(String.valueOf(user.getUserid()));
			vendor = societyDao.saveVendorDetails(vendor);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		LoggingHelper.logAjaxResponse("saveVendorDetails",vendor);

		return vendor;
	}

	@RequestMapping(value = { "/getVendorsBySocId" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public List<Vendor> getVendorsBySocId(@ModelAttribute Vendor vendor,HttpServletRequest request) {

		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"getVendorsBySocId",vendor);

		SocietyDao societyDao = new SocietyDao();
		List<Vendor> vendors = new ArrayList<Vendor>();
		try {
			vendors = societyDao.getVendorsBySocId(vendor.getSocietyid(), vendors);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		LoggingHelper.logAjaxResponse("getVendorsBySocId",vendors);

		return vendors;
	}

	@RequestMapping(value = { "/getSocietyDetailsById" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public Society getSocietyDetailsById(@ModelAttribute Society society,HttpServletRequest request) {

		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"getSocietyDetailsById",society);

		SocietyDao societyDao = new SocietyDao();
		try {
			society = societyDao.getSocietyDetailsById(society);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		LoggingHelper.logAjaxResponse("getSocietyDetailsById",society);

		return society;
	}

	@RequestMapping(value = { "/getDocumentTypeById" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public Doctype getDocumentTypeById(@ModelAttribute Doctype doctype,HttpServletRequest request) {
		
		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"getDocumentTypeById",doctype);

		SocietyDao societyDao = new SocietyDao();
		try {
			doctype = societyDao.getDocumentTypeById(doctype);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		LoggingHelper.logAjaxResponse("getDocumentTypeById",doctype);

		return doctype;
	}

	@RequestMapping(value = { "/getDocumentSubTypeById" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public DocSubType getDocumentSubTypeById(@ModelAttribute DocSubType docSubType,HttpServletRequest request) {

		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"getDocumentSubTypeById",docSubType);

		SocietyDao societyDao = new SocietyDao();
		try {
			docSubType = societyDao.getDocumentSubTypeById(docSubType);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		LoggingHelper.logAjaxResponse("getDocumentSubTypeById",docSubType);

		return docSubType;
	}

	@RequestMapping(value = { "/getFormFieldById" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public FormFields getFormFieldById(@ModelAttribute FormFields formFields,HttpServletRequest request) {
		
		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"getFormFieldById",formFields);

		SocietyDao societyDao = new SocietyDao();
		try {
			formFields = societyDao.getFormFieldsById(formFields);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		LoggingHelper.logAjaxResponse("getFormFieldById",formFields);

		return formFields;
	}

	@RequestMapping(value = { "/getUserDataById" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public Userprofile getUserDataById(@ModelAttribute Userprofile userprofile,HttpServletRequest request) {
		
		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"getUserDataById",userprofile);

		SocietyDao societyDao = new SocietyDao();
		try {
			userprofile = societyDao.getUserDataById(userprofile);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		LoggingHelper.logAjaxResponse("getUserDataById",userprofile);

		return userprofile;
	}

	@RequestMapping(value = { "/getVendorDataById" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public Vendor getVendorDataById(@ModelAttribute Vendor vendor,HttpServletRequest request) {

		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"getVendorDataById",vendor);

		SocietyDao societyDao = new SocietyDao();
		try {
			vendor = societyDao.getVendorDataById(vendor);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		LoggingHelper.logAjaxResponse("getVendorDataById",vendor);

		return vendor;
	}

	@RequestMapping(value = { "/getManagersForSociety" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public List<Committee> getManagersForSociety(@ModelAttribute Society society,HttpServletRequest request) {

		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"getManagersForSociety",society);

		SocietyDao societyDao = new SocietyDao();
		List<Committee> committees = null;
		try {
			committees = societyDao.getManagersForSociety(society, committees);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		LoggingHelper.logAjaxResponse("getManagersForSociety",committees);

		return committees;
	}

	@RequestMapping(value = { "/removeSocietyManager" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public String removeSocietyManager(@ModelAttribute Committee committee,HttpServletRequest request) {

		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"removeSocietyManager",committee);

		int rowsUpdated = 0;
		SocietyDao societyDao = new SocietyDao();
		try {
			rowsUpdated = societyDao.removeSocietyManager(committee);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		LoggingHelper.logAjaxResponse("removeSocietyManager",rowsUpdated);

		if (rowsUpdated > 0)
			return "success";
		return "failed";
	}

	@RequestMapping(value = { "/addSocietyManager" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public Committee addSocietyManager(@ModelAttribute Committee committee,HttpServletRequest request) {

		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"addSocietyManager",committee);

		SocietyDao societyDao = new SocietyDao();
		try {
			committee = societyDao.addSocietyManager(committee);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		LoggingHelper.logAjaxResponse("addSocietyManager",committee);

		return committee;
	}

	@RequestMapping(value = { "/getBuilderDetailsById" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public Builder getBuilderDetailsById(@ModelAttribute Builder builder,HttpServletRequest request) {

		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"getBuilderDetailsById",builder);

		SocietyDao societyDao = new SocietyDao();
		try {
			builder = societyDao.getBuilderDetailsById(builder);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		LoggingHelper.logAjaxResponse("getBuilderDetailsById",builder);

		return builder;
	}

	@RequestMapping(value = { "/getProjectDetailsById" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public Project getProjectDetailsById(@ModelAttribute Project project,HttpServletRequest request) {

		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"getProjectDetailsById",project);

		SocietyDao societyDao = new SocietyDao();
		try {
			project = societyDao.getProjectDetailsById(project);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		LoggingHelper.logAjaxResponse("getProjectDetailsById",project);

		return project;
	}

	@RequestMapping(value = { "/removeSocietyDocmapping" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public String removeSocietyDocmapping(@ModelAttribute GenericBean bean,HttpServletRequest request) {

		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"removeSocietyDocmapping",bean);

		int rowsUpdated = 0;
		SocietyDao societyDao = new SocietyDao();
		try {
			rowsUpdated = societyDao.removeSocietyDocmapping(bean);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		LoggingHelper.logAjaxResponse("removeSocietyDocmapping",rowsUpdated);

		if (rowsUpdated > 0)
			return "success";
		return "failed";
	}

	@RequestMapping(value = { "/editAdminUser" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public User editAdminUser(@ModelAttribute User adminUser,HttpServletRequest request) {

		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"editAdminUser",adminUser);

		SocietyDao societyDao = new SocietyDao();
		try {
			adminUser = societyDao.editAdminUser(adminUser);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		LoggingHelper.logAjaxResponse("editAdminUser",adminUser);

		return adminUser;
	}

	@RequestMapping(value = { "/getNeighborDetails" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public List<Userprofile> getNeighborDetails(@ModelAttribute User user,HttpServletRequest request) {

		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"getNeighborDetails",user);

		SocietyDao societyDao = new SocietyDao();
		List<Userprofile> profiles = null;
		try {
			profiles = societyDao.getNeighborDetails(user.getUserid(), profiles);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		LoggingHelper.logAjaxResponse("getNeighborDetails",profiles);

		return profiles;
	}

	@RequestMapping(value = { "/getContactsByCallRefId" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public List<CallReference> getContactsByCallRefId(@ModelAttribute CallReference callref,HttpServletRequest request) {

		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"getContactsByCallRefId",callref);

		SocietyDao societyDao = new SocietyDao();
		List<CallReference> profiles = null;
		try {
			profiles = societyDao.getContactsByCallRefId(callref.getCallrefid(), profiles);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		LoggingHelper.logAjaxResponse("getContactsByCallRefId",profiles);

		return profiles;
	}

	@RequestMapping(value = { "/saveCallrefMeeting" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public CallReference saveCallrefMeeting(@ModelAttribute CallReference callref,HttpServletRequest request) {

		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"saveCallrefMeeting",callref);

		SocietyDao societyDao = new SocietyDao();
		try {
			callref = societyDao.saveCallrefMeeting(callref);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		LoggingHelper.logAjaxResponse("saveCallrefMeeting",callref);

		return callref;
	}

	@RequestMapping(value = { "/saveCallrefContact" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public CallReference saveCallrefContact(@ModelAttribute CallReference callref,HttpServletRequest request) {

		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"saveCallrefContact",callref);

		SocietyDao societyDao = new SocietyDao();
		try {
			callref = societyDao.saveCallrefContact(callref);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		LoggingHelper.logAjaxResponse("saveCallrefContact",callref);

		return callref;
	}

	@RequestMapping(value = { "/getMeetingsByCallRefId" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public List<CallReference> getMeetingsByCallRefId(@ModelAttribute CallReference callref,HttpServletRequest request) {

		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"getMeetingsByCallRefId",callref);

		SocietyDao societyDao = new SocietyDao();
		List<CallReference> profiles = null;
		try {
			profiles = societyDao.getMeetingsByCallRefId(callref.getCallrefid(), profiles);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		LoggingHelper.logAjaxResponse("getMeetingsByCallRefId",profiles);

		return profiles;
	}

	@RequestMapping(value = { "/deleteDocById" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public String deleteDocById(@ModelAttribute Document document,HttpServletRequest request) {

		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"deleteDocById",document);

		int rowsUpdated = 0;
		DocumentDao documentDao = new DocumentDao();
		try {
			rowsUpdated = documentDao.deleteDocById(document);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		LoggingHelper.logAjaxResponse("deleteDocById",rowsUpdated);

		if (rowsUpdated > 0)
			return "success";
		return "failed";
	}

	@RequestMapping(value = { "/saveSocViewMapping" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public GenericBean saveSocViewMapping(@ModelAttribute GenericBean gbean, HttpServletRequest request) {

		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"saveSocViewMapping",gbean);

		SocietyDao societyDao = new SocietyDao();
		User user = null;
		try {
			user = (User) request.getSession().getAttribute("userObject");
			gbean.setCreatedby(String.valueOf(user.getUserid()));
			gbean = societyDao.saveSocViewMapping(gbean);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		LoggingHelper.logAjaxResponse("saveSocViewMapping",gbean);

		return gbean;
	}

	@RequestMapping(value = { "/getMappedDocsBySocId" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public List<GenericBean> getMappedDocsBySocId(@ModelAttribute GenericBean gbean,HttpServletRequest request) {

		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"getMappedDocsBySocId",gbean);

		SocietyDao societyDao = new SocietyDao();
		List<GenericBean> beans = null;
		try {
			beans = societyDao.getMappedDocsBySocId(gbean.getSocietyid(), beans);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		LoggingHelper.logAjaxResponse("getMappedDocsBySocId",beans);

		return beans;
	}

	@RequestMapping(value = { "/removeSocDocViewMapping" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public String removeSocDocViewMapping(@ModelAttribute GenericBean bean,HttpServletRequest request) {
		
		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"removeSocDocViewMapping",bean);

		int rowsUpdated = 0;
		SocietyDao societyDao = new SocietyDao();
		try {
			rowsUpdated = societyDao.removeSocDocViewMapping(bean);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		LoggingHelper.logAjaxResponse("removeSocDocViewMapping",rowsUpdated);

		if (rowsUpdated > 0)
			return "success";
		return "failed";
	}

	@RequestMapping(value = { "/generateAndSendOTP" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public String generateAndSendOTP(@ModelAttribute User user,HttpServletRequest request) {

		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"generateAndSendOTP",user);

		int rowsUpdated = 0;
		LoginDao loginDao = new LoginDao();
		try {
			if (user.getOtpType().equals("confidential")) {
				if (!loginDao.verifyConfidentialAccess(user)) {
					return "unauthorized";
				}
			}

			rowsUpdated = loginDao.generateAndSendOTP(user);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		LoggingHelper.logAjaxResponse("generateAndSendOTP",rowsUpdated);

		if (rowsUpdated > 0)
			return "success";
		return "failed";
	}

	@RequestMapping(value = { "/validateAndSetNewPW" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public String validateAndSetNewPW(@ModelAttribute User user,HttpServletRequest request) {

		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"validateAndSetNewPW",user);

		int otpaValid = 0;
		int passwordSet = 0;
		LoginDao loginDao = new LoginDao();
		try {
			otpaValid = loginDao.validateOTP(user);
			if (otpaValid > 0) {
				if (user.getPassword() != null && user.getPassword().trim().length() > 5)
					passwordSet = loginDao.setNewpasswordForUser(user);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		LoggingHelper.logAjaxResponse("validateAndSetNewPW",passwordSet);

		if (passwordSet > 0)
			return "success";
		return "failed";
	}

	@RequestMapping(value = { "/validateOTPForDocAccess" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public String validateOTPForDocAccess(@ModelAttribute User user,HttpServletRequest request) {

		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"validateOTPForDocAccess",user);

		int otpaValid = 0;
		LoginDao loginDao = new LoginDao();
		try {
			otpaValid = loginDao.validateOTP(user);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		LoggingHelper.logAjaxResponse("validateOTPForDocAccess",otpaValid);

		if (otpaValid > 0)
			return "success";
		return "failed";
	}

	@RequestMapping(value = { "/getDesignationById" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public GenericBean getDesignationById(@ModelAttribute GenericBean gbean,HttpServletRequest request) {

		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"getDesignationById",gbean);

		SocietyDao societyDao = new SocietyDao();
		try {
			gbean = societyDao.getDesignationById(gbean);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		LoggingHelper.logAjaxResponse("getDesignationById",gbean);

		return gbean;
	}

	@RequestMapping(value = { "/saveDesignationDetails" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public GenericBean saveDesignationDetails(@ModelAttribute GenericBean gbean, HttpServletRequest request,
			HttpServletResponse response) {

		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"saveDesignationDetails",gbean);

		User user = null;
		SocietyDao societyDao = new SocietyDao();
		try {
			user = (User) request.getSession().getAttribute("userObject");
			gbean.setCreatedby(String.valueOf(user.getUserid()));
			gbean = societyDao.saveDesignationDetails(gbean);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		LoggingHelper.logAjaxResponse("saveDesignationDetails",gbean);

		return gbean;
	}

	@RequestMapping(value = { "/deleteDocumentPage" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public String deleteDocumentPage(@ModelAttribute Files files,HttpServletRequest request) {

		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"deleteDocumentPage",files);

		int rowsUpdated = 0;
		DocumentDao docDao = new DocumentDao();
		try {
			rowsUpdated = docDao.deleteDocumentPage(files);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		LoggingHelper.logAjaxResponse("deleteDocumentPage",rowsUpdated);

		if (rowsUpdated > 0)
			return "success";
		return "failed";
	}

	@RequestMapping(value = { "/saveMemberparkingDetails" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public Parking saveMemberparkingDetails(@ModelAttribute Parking parking, HttpServletRequest request,
			HttpServletResponse response) {
		
		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"saveMemberparkingDetails",parking);

		User user = null;
		SocietyDao societyDao = new SocietyDao();
		try {
			user = (User) request.getSession().getAttribute("userObject");
			parking.setCreatedby(String.valueOf(user.getUserid()));
			parking = societyDao.saveMemberparkingDetails(parking);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		LoggingHelper.logAjaxResponse("saveMemberparkingDetails",parking);

		return parking;
	}

	@RequestMapping(value = { "/getParkingDetailsForMember" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public List<Parking> getParkingDetailsForMember(@ModelAttribute Parking parking,HttpServletRequest request) {
		
		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"getParkingDetailsForMember",parking);

		SocietyDao societyDao = new SocietyDao();
		List<Parking> parkingList = null;
		try {
			parkingList = societyDao.getParkingDetailsForMember(parking, parkingList);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		LoggingHelper.logAjaxResponse("getParkingDetailsForMember",parkingList);

		return parkingList;
	}

	@RequestMapping(value = { "/removeParkingData" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public String removeParkingData(@ModelAttribute Parking parking,HttpServletRequest request) {

		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"removeParkingData",parking);

		int rowsUpdated = 0;
		SocietyDao docDao = new SocietyDao();
		try {
			rowsUpdated = docDao.removeParkingData(parking);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		LoggingHelper.logAjaxResponse("removeParkingData",rowsUpdated);

		if (rowsUpdated > 0)
			return "success";
		return "failed";
	}

	@RequestMapping(value = { "/addShareCertDetails" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public UserSCNominee addShareCertDetails(@ModelAttribute UserSCNominee userSCNominee, HttpServletRequest request,
			HttpServletResponse response) {
		
		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"addShareCertDetails",userSCNominee);

		User user = null;
		SocietyDao societyDao = new SocietyDao();
		try {
			user = (User) request.getSession().getAttribute("userObject");
			userSCNominee.setCreatedby(String.valueOf(user.getUserid()));
			userSCNominee = societyDao.addShareCertDetails(userSCNominee);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		LoggingHelper.logAjaxResponse("addShareCertDetails",userSCNominee);

		return userSCNominee;
	}

	@RequestMapping(value = { "/getShareCertDetails" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public List<UserSCNominee> getShareCertDetails(@ModelAttribute UserSCNominee userSCNominee,HttpServletRequest request) {

		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"getShareCertDetails",userSCNominee);

		SocietyDao societyDao = new SocietyDao();
		List<UserSCNominee> scList = null;
		try {
			scList = societyDao.getShareCertDetails(userSCNominee, scList);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		LoggingHelper.logAjaxResponse("getShareCertDetails",scList);

		return scList;
	}

	@RequestMapping(value = { "/removeShareCertDetails" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public String removeParkingData(@ModelAttribute UserSCNominee userSCNominee,HttpServletRequest request) {

		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"removeShareCertDetails",userSCNominee);

		int rowsUpdated = 0;
		SocietyDao docDao = new SocietyDao();
		try {
			rowsUpdated = docDao.removeShareCertDetails(userSCNominee);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		LoggingHelper.logAjaxResponse("removeShareCertDetails",rowsUpdated);

		if (rowsUpdated > 0)
			return "success";
		return "failed";
	}

	@RequestMapping(value = { "/saveConfDocAccess" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public String saveConfDocAccess(@RequestParam("userid") String userid, HttpServletRequest request) {
		
		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"saveConfDocAccess",userid);

		int rowsUpdated = 0;
		SocietyDao docDao = new SocietyDao();
		User user;
		try {
			user = (User) request.getSession().getAttribute("userObject");
			rowsUpdated = docDao.saveConfDocAccess(userid, user.getUserid());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		LoggingHelper.logAjaxResponse("saveConfDocAccess",rowsUpdated);

		if (rowsUpdated > 0)
			return "success";
		return "failed";
	}

	@RequestMapping(value = { "/getConfDocAccessList" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public List<GenericBean> getConfDocAccessList(HttpServletRequest request) {
		
		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"getConfDocAccessList","");

		DocumentDao documentDao = new DocumentDao();
		List<GenericBean> formFields = null;
		try {
			formFields = documentDao.getConfDocAccessList(formFields);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		LoggingHelper.logAjaxResponse("getConfDocAccessList",formFields);

		return formFields;
	}

	@RequestMapping(value = { "/getProjectsByBuilderId" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public List<Project> getProjectsByBuilderId(@ModelAttribute Builder builder,HttpServletRequest request) {
		
		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"getProjectsByBuilderId",builder);

		DocumentDao documentDao = new DocumentDao();
		List<Project> docSubTypes = null;
		try {
			docSubTypes = documentDao.getProjectsByBuilderId(builder.getBuilderid(), docSubTypes);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		LoggingHelper.logAjaxResponse("getProjectsByBuilderId",docSubTypes);

		return docSubTypes;
	}

	@RequestMapping(value = { "/getSubProjectsByProjectId" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public List<Society> getSubProjectsByProjectId(@ModelAttribute Project project,HttpServletRequest request) {

		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"getSubProjectsByProjectId",project);

		DocumentDao documentDao = new DocumentDao();
		List<Society> docSubTypes = null;
		try {
			docSubTypes = documentDao.getSubProjectsByProjectId(project.getProjectid(), docSubTypes);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		LoggingHelper.logAjaxResponse("getSubProjectsByProjectId",docSubTypes);

		return docSubTypes;
	}

	@RequestMapping(value = { "/genericRemove" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public String genericRemove(@ModelAttribute GenericBean gbean,HttpServletRequest request) {

		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"genericRemove",gbean);

		int rowsUpdated = 0;
		SocietyDao societyDao = new SocietyDao();
		try {
			rowsUpdated = societyDao.genericRemove(gbean);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		LoggingHelper.logAjaxResponse("genericRemove",rowsUpdated);

		if (rowsUpdated > 0)
			return "success";
		return "failed";
	}

	@RequestMapping(value = { "/addTenantToHistory" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public String addTenantToHistory(@ModelAttribute User user,HttpServletRequest request) {

		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"addTenantToHistory",user);

		int rowsUpdated = 0;
		SocietyDao societyDao = new SocietyDao();
		try {
			rowsUpdated = societyDao.addTenantToHistory(user);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		LoggingHelper.logAjaxResponse("addTenantToHistory",rowsUpdated);

		if (rowsUpdated > 0)
			return "success";
		return "failed";
	}

	@RequestMapping(value = { "/getTenantHistory" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public List<Userprofile> getTenantHistory(@ModelAttribute User user) {
		reqreslogger.info("[REQUEST]" + user.toString());

		DocumentDao documentDao = new DocumentDao();
		List<Userprofile> docSubTypes = null;
		try {
			docSubTypes = documentDao.getTenantHistory(user.getUserid(), docSubTypes);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return docSubTypes;
	}

	@RequestMapping(value = { "/sendDocumentAsMail" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public String sendDocumentAsMail(@ModelAttribute EmailBean eBean, HttpServletRequest request) {

		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"sendDocumentAsMail",eBean);

		int rowsUpdated = 0;
		DocumentDao docDao = new DocumentDao();
		User user = null;
		try {
			user = (User) request.getSession().getAttribute("userObject");

			eBean.setSenderName(user.getFirstName() + " " + user.getLastName());

			rowsUpdated = docDao.sendDocumentAsMail(eBean);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		LoggingHelper.logAjaxResponse("sendDocumentAsMail",rowsUpdated);

		if (rowsUpdated > 0)
			return "success";
		return "failed";
	}

	@RequestMapping(value = { "/addDeleteAuth" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public String addDeleteAuth(@ModelAttribute User user,HttpServletRequest request) {

		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"addDeleteAuth",user);

		int rowsUpdated = 0;
		SocietyDao societyDao = new SocietyDao();
		try {
			rowsUpdated = societyDao.addDeleteAuth(user);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		LoggingHelper.logAjaxResponse("addDeleteAuth",rowsUpdated);

		if (rowsUpdated > 0)
			return "success";
		return "failed";
	}

	@RequestMapping(value = { "/removeDeleteAuth" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public String removeDeleteAuth(@ModelAttribute User user,HttpServletRequest request) {
		
		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"removeDeleteAuth",user);

		int rowsUpdated = 0;
		SocietyDao societyDao = new SocietyDao();
		try {
			rowsUpdated = societyDao.removeDeleteAuth(user);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		LoggingHelper.logAjaxResponse("removeDeleteAuth",rowsUpdated);

		if (rowsUpdated > 0)
			return "success";
		return "failed";
	}

	@RequestMapping(value = { "/getDeleteAuth" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public List<User> getDeleteAuth(HttpServletRequest request) {

		LoggingHelper.logAjaxRequest(request.getSession().getAttribute("userId").toString(),"getDeleteAuth","");

		DocumentDao documentDao = new DocumentDao();
		List<User> users = null;
		try {
			users = documentDao.getDeleteAuth(users);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		LoggingHelper.logAjaxResponse("getDeleteAuth",users);

		return users;
	}
} // end of class
