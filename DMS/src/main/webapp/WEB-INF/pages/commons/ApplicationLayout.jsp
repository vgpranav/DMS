<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
 <!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title><tiles:insertAttribute name="title" /> | Document Management System</title>

	<tiles:insertAttribute name="imports" />
	
  </head>

  <body class="nav-md">
    <div class="container body">
      <div class="main_container">
        <div class="col-md-3 left_col">
          <div class="left_col scroll-view">
            <div class="navbar nav_title" style="border: 0;">
            
              <a href="showHomepage.do" class="site_title">
               <img src="<%= request.getContextPath() %>/resources/images/ods-logo-ws.png" width="200">
              		<!-- <i class="fa fa-leaf"></i> 
              		<span>DMS</span> -->
              		
              </a>
            </div>
            <div class="clearfix"></div>
            <!-- menu profile quick info -->
            <div class="profile clearfix">
              <div class="profile_pic">
              
              <% if(request.getSession().getAttribute("imgBase64")!=null && request.getSession().getAttribute("imgBase64").toString().length()>5){ %>
              	<img class="img-circle profile_img"  src="data:<%= request.getSession().getAttribute("imgContentType") %>;base64,<%= request.getSession().getAttribute("imgBase64") %>"/>
              <% } else {
            	  %>
            	  <img src="<%= request.getContextPath() %>/resources/images/blankface.jpg" class="img-circle profile_img">
            	  <%
              }
               %>
              
              </div>
              <div class="profile_info">
                <span>Welcome,</span>
               <h2>${sessionScope.userObject.firstName} ${sessionScope.userObject.lastName}</h2>
              </div>
            </div>
            <!-- /menu profile quick info -->
            <br />
            <!-- sidebar menu -->
            	<tiles:insertAttribute name="leftmenubar" />
            <!-- /sidebar menu -->

            <!-- /menu footer buttons -->
            	<tiles:insertAttribute name="menufooter" />
            <!-- /menu footer buttons -->
            
          </div>
        </div>

        <!-- top navigation -->
        <tiles:insertAttribute name="header" />
                <!-- /top navigation -->

        <!-- page content -->
        <div class="right_col" role="main">
			<tiles:insertAttribute name="body" />        
          <br />
        </div>
        <!-- /page content -->

        <!-- footer content -->
        <footer>
          		<tiles:insertAttribute name="footer" />
          <div class="clearfix"></div>
        </footer>
        <!-- /footer content -->
        
      </div>
    </div> 
   		<tiles:insertAttribute name="importJScript" />       
  </body>
</html>
 