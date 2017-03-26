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
                <input type="text" name="mobileNo" class="form-control" placeholder="Mobile No"/>
              </div>
              <div>
                <input type="password" name ="password" class="form-control" placeholder="Password" />
              </div>
              <div align="center">
                <input type="submit" class="btn btn-success" value="Log In" style="display: block;"/>
                <c:if test="${not empty errorMessage}">
                  <script>
		                $(document).ready(function(){
		                	notify('error','ERROR','${errorMessage}',2000);
		                });
				  </script>
				  <div class="text-danger">${errorMessage}</div>
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
                  <!-- <h1><i class="fa fa-leaf"></i> Document Management System</h1> -->
                  
                  <img src="<%= request.getContextPath() %>/resources/images/ods-logo.png" height="80">
                  <p>©20167-18 All Rights Reserved.</p>
                </div>
              </div>
            </form>
          </section>
        </div>
 		</div>
    </div>
   
     <tiles:insertAttribute name="importJScript" />
       
  </body>
</html>
 