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
          
          <div id="login-section">
          
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
 			</form>
 				
 				 <p class="change_link">
                	<a class="" href="#" onclick="showOtpSection()">Lost your password?</a>
                </p>
                
 			</div>
 			
              <div class="clearfix"></div>

              <div class="separator">
               
                <div class="clearfix"></div>
                <br />
			
			<div id="otp-section" style="display: none;">
 			<form method="post" action="#">
              <div>
                <input type="text" name="mobileNoOTP" id="mobileNoOTP" class="form-control" placeholder="Registered 10 Digit Mobile Number"/>
              </div>
              <div align="center">
              <button class="btn btn-warning" onclick="generateOTPInit();return false;" id="otpbtn">Send OTP</button>
              </div>
              <hr/>
              <div>
                <input type="password" autocomplete="off" name ="otp" id ="otp" class="form-control" placeholder="Enter OTP Here" />
              </div>
               <div>
                <input type="password" autocomplete="off" name ="passwordotp" id ="passwordotp" class="form-control" placeholder="Enter New Password" />
              </div>
               <div>
                <input type="password" autocomplete="off" name ="passwordotp1" id ="passwordotp1" class="form-control" placeholder="Repeat New password" />
              </div>
              <div align="center">
               
                <button class="btn btn-success" style="margin-top: 10px;" onclick="validateAndSetNewPW();return false;">Reset Password</button>
                 
                
              </div>
              
 			</form>
			</div>
			


                <div>
                  <img src="<%= request.getContextPath() %>/resources/images/ods-logo.png" height="80">
                  <p>©20167-18 All Rights Reserved.</p>
                </div>
              </div>
           
          </section>
        </div>
 		</div>
    </div>
   
     <tiles:insertAttribute name="importJScript" />
       
  </body>
</html>


<script>

	function showOtpSection(){
		$('#login-section').hide("fold", { direction: "up" }, "slow" );
		$('#otp-section').show();
	}
	
	function showLoginSection(){
		$('#otp-section').hide("fold", { direction: "up" }, "slow" );
		$('#login-section').show();
	}

	function generateOTPInit(){
		
		var counter = 30;
		var interval = setInterval(function() {
		    counter--;
		    console.log("genOPT cntr "+counter);
		    $('#otpbtn').attr('disabled','disabled').html('Resend in '+counter+' sec');
		    if (counter == 0) {
		    	clearInterval(interval);
		    	$('#otpbtn').removeAttr('disabled').html('Resend OTP');
		    }
		}, 1000);
		
		generateOTP(interval);
	}
	
	function generateOTP(interval){
		var mobileNo = $('#mobileNoOTP').val();
		blockUI();
		$.ajax({
		        type: "GET",
		        url: "<%=request.getContextPath()%>/generateAndSendOTP.do",
		       data :"mobileNo="+mobileNo
		       		+"&otpType=login",
		        success: function(response){
		        //alert()
		        	if(response=='success') {
		        		notify('success','OTP SENT','You Will Receive OTP Shortly',2000);
		        	}  else {
		        		notify('error','FAILED','Invalid Mobile Number',2000);
		        		clearInterval(interval);
				    	$('#otpbtn').removeAttr('disabled').html('Send OTP');
		        	}
		        unblockUI();
		        },
					error : function(e) {
						notify('error','ERROR','Error occured',2000);
						unblockUI();
					}
				});
	}
	
	
	function validateAndSetNewPW(){
		var mobileNo = $('#mobileNoOTP').val();
		var otp = $('#otp').val();
		var passwordotp = $('#passwordotp').val();
		var passwordotp1 = $('#passwordotp1').val();
		
		if(otp.length>0){
			if(passwordotp.length<6){
				alert('Passoword should be Min 6 characters');
			}else{
				if(passwordotp!=passwordotp1){
					alert('Both Passwords Do Not Match');
					return false;
				}else{
					blockUI();
					$.ajax({
				        type: "GET",
				        url: "<%=request.getContextPath()%>/validateAndSetNewPW.do",
				        data :"mobileNo="+mobileNo
			        	+"&otp="+otp
			        	+"&password="+passwordotp,
				        success: function(response){
				        	if(response=='success') {
				        		notify('success','NEW PASSWORD SET','Login with your new password',2000);
				        		showLoginSection();
				        	}  else {
				        		notify('error','FAILED','Invalid OTP or Mobile Number',2000);
						    	$('#otpbtn').removeAttr('disabled').html('Send OTP');
				        	}
				        	unblockUI();
				        },
							error : function(e) {
								notify('error','ERROR','Error occured',2000);
								unblockUI();
							}
						});
					
					
				}
			}
		}else{
			alert('Please Enter OTP');
		}
		
	}
	
</script>
 