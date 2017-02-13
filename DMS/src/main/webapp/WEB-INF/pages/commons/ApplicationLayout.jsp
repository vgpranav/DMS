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

    <!-- Bootstrap -->
    <link href="<%= request.getContextPath() %>/resources/theme/vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="<%= request.getContextPath() %>/resources/theme/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- bootstrap-daterangepicker -->
    <link href="<%= request.getContextPath() %>/resources/theme/vendors/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">
    <!-- Custom Theme Style -->
    <link href="<%= request.getContextPath() %>/resources/theme/build/css/custom.min.css" rel="stylesheet">
  </head>

  <body class="nav-md">
    <div class="container body">
      <div class="main_container">
        <div class="col-md-3 left_col">
          <div class="left_col scroll-view">
            <div class="navbar nav_title" style="border: 0;">
              <a href="index.html" class="site_title">
              		<i class="fa fa-leaf"></i> 
              		<span>DMS</span>
              </a>
            </div>
            <div class="clearfix"></div>
            <!-- menu profile quick info -->
            <div class="profile clearfix">
              <div class="profile_pic">
                <img src="<%= request.getContextPath() %>/resources/theme/production/images/img.jpg" alt="..." class="img-circle profile_img">
              </div>
              <div class="profile_info">
                <span>Welcome,</span>
                <h2>Pranav VG</h2>
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

    <!-- jQuery -->
    <script src="<%= request.getContextPath() %>/resources/theme/vendors/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap -->
    <script src="<%= request.getContextPath() %>/resources/theme/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- bootstrap-progressbar -->
    <script src="<%= request.getContextPath() %>/resources/theme/vendors/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
    <!-- iCheck -->
    <script src="<%= request.getContextPath() %>/resources/theme/vendors/iCheck/icheck.min.js"></script>
    <!-- DateJS -->
    <script src="<%= request.getContextPath() %>/resources/theme/vendors/DateJS/build/date.js"></script>
     <!-- bootstrap-daterangepicker -->
    <script src="<%= request.getContextPath() %>/resources/theme/vendors/moment/min/moment.min.js"></script>
    <script src="<%= request.getContextPath() %>/resources/theme/vendors/bootstrap-daterangepicker/daterangepicker.js"></script>
    <!-- Custom Theme Scripts -->
    <script src="<%= request.getContextPath() %>/resources/theme/build/js/custom.min.js"></script>
  </body>
</html>
 