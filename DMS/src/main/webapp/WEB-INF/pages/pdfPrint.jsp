<%@ page language="java" contentType="application/pdf; charset=UTF-8" pageEncoding="UTF-8"%>

<%
 response.setContentType("application/pdf");
 response.setHeader("content-disposition","inline; filename="+"Filename.pdf"); 
//What ever u write inside this JSP file will be exported as pdf file when you send request and response to this page
%>
HIIIIIIIIIIIIIIII
<% 
	response.flushBuffer();
%>