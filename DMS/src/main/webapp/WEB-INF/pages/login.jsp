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

  <body class="login">
    <div>
      <a class="hiddenanchor" id="signup"></a>
      <a class="hiddenanchor" id="signin"></a>

      <div class="login_wrapper">
        <div class="animate form login_form">
          <section class="login_content">
            <form method="post" action="authenticateUser.do">
              <h1>Login Here</h1>
              <div>
                <input type="text" class="form-control" placeholder="Username"/>
              </div>
              <div>
                <input type="password" class="form-control" placeholder="Password" />
              </div>
              <div align="center">
                <input type="submit" class="btn btn-success" value="Log In" style="display: block;"/>
                <c:if test="${not empty errorMessage}">
				  ${errorMessage}
				</c:if>
              </div>

              <div class="clearfix"></div>

              <div class="separator">
                <p class="change_link">
                	<a class="" href="#">Lost your password?</a>
                </p>

                <div class="clearfix"></div>
                <br />

                <div>
                  <h1><i class="fa fa-leaf"></i> Document Management System</h1>
                  <p>©2016 All Rights Reserved. Document Management System. Privacy and Terms</p>
                </div>
              </div>
            </form>
          </section>
        </div>
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
 