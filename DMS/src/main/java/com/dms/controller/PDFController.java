package com.dms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dms.beans.Files;
import com.dms.dao.DocumentDao;

@Controller
public class PDFController
{
	@RequestMapping(value={"/downloadAsPdf"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String output = "PDF";
        String documentId = ServletRequestUtils.getStringParameter(request, "documentId");
        Map params = new HashMap();
        params.put("documentId", documentId);
        if("PDF".equals(output.toUpperCase()))
        {
            return new ModelAndView("PdfDocumentView", "params", params);
        } else
        {
            return null;
        }
    }
	
	/*@RequestMapping(value={"/downloadAsPdf"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
        throws Exception {
		List<Files> docs = null;
		ModelAndView mv = null;
		DocumentDao ddao = new DocumentDao();
		try{
			String documentId = ServletRequestUtils.getStringParameter(request, "documentId");
			docs = ddao.getDocPathsByDocId(documentId,docs);
			mv = new ModelAndView("pdfPrint", "docId", documentId);
			mv.addObject("docs",docs);
		}catch(Exception e){
			
		}
		return mv;
    }*/
}
