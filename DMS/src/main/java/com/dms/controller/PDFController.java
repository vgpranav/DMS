package com.dms.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;

public class PDFController
{

    public PDFController()
    {
    }

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
}
