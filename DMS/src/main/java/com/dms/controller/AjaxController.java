package com.dms.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.dms.beans.DocSubType;
import com.dms.beans.Doctype;
import com.dms.beans.Document;
import com.dms.beans.FormFields;
import com.dms.beans.Society;
import com.dms.dao.DocumentDao;
import com.dms.dao.SocietyDao;

@Controller
public class AjaxController {

	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(AjaxController.class);

	@RequestMapping(value = "/societyAutosuggest.do", method = RequestMethod.GET, headers="Accept=*/*",  produces="application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody
	List<String> ajaxEmployeeIDSearch(@RequestParam("searchText") String searchText) {
		SocietyDao societyDao = new SocietyDao();
		List<String> codeList1 = new ArrayList<String>();
		try {
		List<Society> societyList = societyDao.getSocietyAutosuggest(searchText);
		for (int i = 0; i < societyList.size(); i++) {
			Society soc = (Society) societyList.get(i);
			String name = soc.getSocietyid() + "--" + soc.getSocietyname();
			codeList1.add(i, name);
			}
		}
		catch(Exception e){
			logger.error(e.getMessage());
		}
		return codeList1;
	}
	
	
	@RequestMapping(value = "/saveFormFields", method = RequestMethod.GET)
	public @ResponseBody
	FormFields saveDocumentType(@ModelAttribute FormFields formFields
			/*@Valid Society customer,
			BindingResult bindingResult, 
			Model model*/){
		DocumentDao documentDao = new DocumentDao();
		try{
			formFields = documentDao.insertOrUpdateFormFields(formFields);
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return formFields;
	}
	
	@RequestMapping(value = "/getFieldsForDocSubtype", method = RequestMethod.GET)
	public @ResponseBody
	List<FormFields> getFieldsForDocSubtype(@ModelAttribute FormFields formField
			/*@Valid Society customer,
			BindingResult bindingResult, 
			Model model*/){
		DocumentDao documentDao = new DocumentDao();
		List<FormFields> formFields=null;
		try{
			formFields = documentDao.getFieldsForDocSubtype(formField.getDocsubtypeid(),formFields);
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return formFields;
	}
	
	@RequestMapping(value = "/getDoctypeBySocId", method = RequestMethod.GET)
	public @ResponseBody
	List<Doctype> getDoctypeBySocId(@ModelAttribute Society society
			/*@Valid Society customer,
			BindingResult bindingResult, 
			Model model*/){
		DocumentDao documentDao = new DocumentDao();
		List<Doctype> doctypes=null;
		try{
			doctypes = documentDao.getDoctypeBySocId(society.getSocietyid(),doctypes);
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return doctypes;
	}
	
	@RequestMapping(value = "/getDocSubtypeByDocId", method = RequestMethod.GET)
	public @ResponseBody
	List<DocSubType> getDocSubtypeByDocId(@ModelAttribute DocSubType docSubType
			/*@Valid Society customer,
			BindingResult bindingResult, 
			Model model*/){
		DocumentDao documentDao = new DocumentDao();
		List<DocSubType> docSubTypes=null;
		try{
			docSubTypes = documentDao.getDocSubtypeByDocId(docSubType.getDoctypeid(),docSubTypes);
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return docSubTypes;
	}
	
	@RequestMapping(value = "/getDocumentListForView", method = RequestMethod.GET)
	public @ResponseBody
	List<Document> getDocumentListForView(@ModelAttribute Document document
			/*@Valid Society customer,
			BindingResult bindingResult, 
			Model model*/){
		DocumentDao documentDao = new DocumentDao();
		List<Document> documents=null;
		try{
			documents = documentDao.getDocumentListForView(document,documents);
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return documents;
	}
}
