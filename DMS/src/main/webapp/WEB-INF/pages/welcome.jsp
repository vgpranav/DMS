<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
 <!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><tiles:insertAttribute name="title" /> | Document Management System</title>
    
    
        <script src="<%= request.getContextPath() %>/resources/theme/vendors/jquery/dist/jquery.min.js"></script>
		
		<!-- Bootstrap -->
		<link href="<%= request.getContextPath() %>/resources/theme/vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
		<!-- Font Awesome -->
		<link href="<%= request.getContextPath() %>/resources/theme/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
		<!-- bootstrap-daterangepicker -->
		<link href="<%= request.getContextPath() %>/resources/theme/vendors/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">
		<!-- Custom Theme Style -->
		<link href="<%= request.getContextPath() %>/resources/theme/build/css/custom.css" rel="stylesheet">
		
		<link href="<%= request.getContextPath() %>/resources/theme/vendors/pnotify/dist/pnotify.css" rel="stylesheet">
		<link href="<%= request.getContextPath() %>/resources/theme/vendors/pnotify/dist/pnotify.buttons.css" rel="stylesheet">
		<link href="<%= request.getContextPath() %>/resources/theme/vendors/pnotify/dist/pnotify.nonblock.css" rel="stylesheet">
		
		<link href="<%= request.getContextPath() %>/resources/theme/vendors/jquery-ui-1.12.1/jquery-ui.css" rel="stylesheet">
		<link href="<%= request.getContextPath() %>/resources/theme/vendors/datatables.net-bs/css/dataTables.bootstrap.css" rel="stylesheet">
		    
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
		<script src="<%= request.getContextPath() %>/resources/theme/build/js/custom.js"></script>
		<script src="<%= request.getContextPath() %>/resources/theme/vendors/pnotify/dist/pnotify.js"></script>
		<script src="<%= request.getContextPath() %>/resources/theme/vendors/pnotify/dist/pnotify.buttons.js"></script>
		<script src="<%= request.getContextPath() %>/resources/theme/vendors/pnotify/dist/pnotify.nonblock.js"></script>
		
		<script src="<%= request.getContextPath() %>/resources/theme/vendors/jquery-ui-1.12.1/jquery-ui.js"></script>
		<script src="<%= request.getContextPath() %>/resources/theme/vendors/datatables.net-bs/js/dataTables.bootstrap.js"></script>
		<script src="<%= request.getContextPath() %>/resources/theme/vendors/datatables.net/js/jquery.dataTables.js"></script>
		<script src="<%= request.getContextPath() %>/resources/theme/vendors/bootstrap-daterangepicker/daterangepicker.js"></script>
		
		
		<script src="<%= request.getContextPath() %>/resources/theme/vendors/popupimage/popImg.js"></script>
		
		
		<script>
			function notify(type,title,message,delay){
				new PNotify({
		       	 title: title,
		            text: message,
		            type: type,
		            styling: 'bootstrap3',
		            delay : delay
		       });
			}
			
			$(document).scroll(function() {
				if($(this).scrollTop()>40){
					$('#logo-scroll').css({"box-shadow":"2px 2px 2px #ccc"});
				}else{
					$('#logo-scroll').css({"box-shadow":"2px 2px 2px #fff"});
				}
			});
		</script>
    	
    	<style>
    	.row{
    		margin-left:15px !important;
    		margin-right:15px !important;
    	}
    	
    	body p,li{
    		font-size: 14px !important;
    	}
    	
    	li{
    		padding:10px !important;
    	}
    	
    	#logo-scroll{ 
    		 
    	}
    	
    	
    	@import url(https://fonts.googleapis.com/css?family=Open+Sans:400,700,300);
footer { background-color:#0c1a1e; min-height:250px; font-family: 'Open Sans', sans-serif; margin-left: 0 !important;}
.footerleft { margin-top:50px; padding:0 36px; }
.logofooter { margin-bottom:10px; font-size:25px; color:#fff; font-weight:700;}

.footerleft p { color:#fff; font-size:12px !important; font-family: 'Open Sans', sans-serif; margin-bottom:15px;}
.footerleft p i { width:20px; color:#999;}


.paddingtop-bottom {  margin-top:50px;}
.footer-ul { list-style-type:none;  padding-left:0px; margin-left:2px;}
.footer-ul li { line-height:29px; font-size:12px;}
.footer-ul li a { color:#a0a3a4; transition: color 0.2s linear 0s, background 0.2s linear 0s; }
.footer-ul i { margin-right:10px;}
.footer-ul li a:hover {transition: color 0.2s linear 0s, background 0.2s linear 0s; color:#ff670f; }

.social:hover {
     -webkit-transform: scale(1.1);
     -moz-transform: scale(1.1);
     -o-transform: scale(1.1);
 }
 
 

 
 .icon-ul { list-style-type:none !important; margin:0px; padding:0px;}
 .icon-ul li { line-height:75px; width:100%; float:left;}
 .icon { float:left; margin-right:5px;}
 
 
 .copyright { min-height:40px; background-color:#000000;}
 .copyright p { text-align:left; color:#FFF; padding:10px 0; margin-bottom:0px;}
 .heading7 { font-size:21px; font-weight:700; color:#d9d6d6; margin-bottom:22px;}
 .post p { font-size:12px; color:#FFF; line-height:20px;}
 .post p span { display:block; color:#8f8f8f;}
 .bottom_ul { list-style-type:none; float:right; margin-bottom:0px;}
 .bottom_ul li { float:left; line-height:40px;}
 .bottom_ul li:after { content:"/"; color:#FFF; margin-right:8px; margin-left:8px;}
 .bottom_ul li a { color:#FFF;  font-size:12px;}
    	
    	</style>
    
  </head>
  <body style="background-color: #fff !important;">
  
   
  <div class="col-md-12 col-sm-12 col-xs-12" align="center" style="margin-top:10%">
  		<img src="<%= request.getContextPath() %>/resources/images/ods-logo1.png" height="70">
  </div>
  <div class="col-md-12 col-sm-12 col-xs-12" align="center">
  		<h2>Website Under Construction</h2>
  </div>
  		<%-- <div id="logo-scroll" class="col-md-12 col-sm-12 col-xs-12" style="position: fixed;z-index: 9999;background-color: #0c1a1e !important;">
			 <div class="row" style=" ">
			 	<div class="col-md-4 col-sm-4 col-xs-12">
					<img src="<%= request.getContextPath() %>/resources/images/ods-logo1.png" height="70">			 	
			 	</div>
			 	<div class="col-md-4 col-sm-4 col-xs-12 pull-right">
			 		<form class="navbar-form navbar-right" action="login.do">
				      	<button type="submit" class="btn btn-success btn-md">Login</button>
				    </form>
			 	</div>
			 </div>
		</div> --%>
		
		
	<!-- <section class="container" >
		
		<div class="row" style="margin-top:90px;">
			
			
			<div class="col-md-12 col-sm-12 col-xs-12">
              <div class="x_panel tile">
                <div class="x_title">
                  <h2>What is ODS </h2> 
                  <div class="clearfix"></div>
                </div>
                <div class="x_content">
                  <div class="dashboard-widget-content">
                  <h3 class="green">Our Digital Society</h3>
                    	<p>
                    		Our Digital Society is a unique initiative to create a comlete paperless society. This is the first step toward A Digital India where we automate all society processes and transactions on the internet making it more more accountable, transparent and convinient. 
                    	</p>
                  </div>
                </div>
              </div>
 		</div>
		
			
			<div class="col-md-8 col-sm-8 col-xs-12">
 				
 				
 				
 				<div class="col-md-12 col-sm-12 col-xs-12">
              <div class="x_panel tile">
                <div class="x_title">
                  <h2>Why Go Paperless </h2> 
                  <div class="clearfix"></div>
                </div>
                <div class="x_content">
                  <div class="dashboard-widget-content">
                  <h3 class="green">
                  	More Office Space
                  </h3>
                    	<p>
                    		Document scanning can transform your office, reducing the amount of paperwork and unnecessary filing cabinets. The amount of office space freed up by digitising 
                    		your documentation can be huge, and, to underline this, the information stored on one DVD alone can replace nearly 30 filing cabinets.
							Consequently, office space is freed up and can be utilised in new and more effective ways.
                    	</p>
                    	
                   <h3 class="green">
	                  	Secure Document Storage
	               </h3>
                  			<p>Data security and protection is a key issue for businesses to consider. There is a greater risk of paper documents being damaged, stolen or lost through fire, flood or theft.</p>
                  			<ul>
                  				<li>
                  				Scanning your documents offers improved security, peace of mind and data protection compliance for the business.
                  				</li>
                  				<li>
                  				Scanned images can be securely stored in an online document management system, with security access at a user or role level. Documents can be easily shared with users in multiple offices and locations, providing secure, flexible access.
                  				</li>
                  				<li>
                  				A full electronic audit trail can be provided, showing a detailed user access history.
                  				</li>
                  				<li>
                  				Digital documents can be regularly backed up, providing continuity for your business. For businesses that like to rely on their own back up methods, a scanned document is still a better option than a paper document. If a scanned document is saved on a computer, and the files are backed up on CDs, DVDs, or a memory stick, then even if the computer suffers a fatal crash this will not be catastrophic. For any business the permanent loss of documents can prove to be traumatic, and have a significant impact financially. The chances of this happening are far greater with paper documents than they are with documents that have been scanned and backed up.
                  				</li>
                  				<li>
                  				Documents that have been scanned and then stored digitally will last a long time. The documents won't deteriorate over time, as is often the case when paper documents are going through different pairs of hands. Many years after a document has been scanned it will still remain in its original pristine condition.
                  				</li>
                  			</ul>
                  		
                  	<h3 class="green">
                  	Environmentally Friendly
                  	</h3>
	                  	<p>
	                  		In an increasingly environmentally friendly age scanning your documents helps to move towards having a paperless office. For offices that have always relied on a great deal of paperwork, the change to a paperless office can be very dramatic, but also very helpful in terms of the overall benifits. Less paper is produced, proving friendlier to the environment and also helping to reduce business costs for photocopying and stationary. Physical transport of documents can also be cut, helping your carbon footprint.
	                  	</p>
                  </div>
                </div>
              </div>
 		</div>
 		
 		<div class="col-md-12 col-sm-12 col-xs-12">
              <div class="x_panel tile">
                <div class="x_title">
                  <h2>Why ODS </h2> 
                  <div class="clearfix"></div>
                </div>
                <div class="x_content">
                  <div class="dashboard-widget-content">
                  	<h3 class="green">Features</h3>
                  	<ul>
                  		<li>Be found quickly and easily using a simple keyword search based on document type, sub type and tenant name. 
                  			An often frustrating aspect of working in an office is being buried underneath a pile of paperwork. As a result there's a greater likelihood of an important document being mislaid, or taking ages to find.
                  		 	When a file is stored on a computer these problems should no longer be an issue.
						</li>
						
						<li>
							Over the course of a year, the amount of time saved on document searches will be considerable, and aid a company's efficiency. 
							Searching in vain to find a paper document will be consigned to history, and you will now be able to find related documents from 
							years or even decades ago with just a few clicks of a mouse.
						</li>
						
						<li>
							Scanned Document can be viewed quickly online or can be downloaded as a PDF and transferred to any device. Admin has full control over what documents are to be shown to whom.
						</li>
						 
						 <li>
						 	Full digital access trail is maintained to check the access and modification of documents in the system
						 </li>
                  	</ul>
                  </div>
                 </div>
                </div>
               </div>
               
 			</div>
		
		
		</div>
</section> -->
		
  
 		
 		
            <!--  <footer>
  <div class="container">
    <div class="row">
      <div class="col-md-4 col-sm-6 footerleft ">
        <div class="logofooter">Our Digital Society</div>
        <p><i class="fa fa-map-pin"></i> 
        	Flat No. 103 B Wing, Building No 17, Stalag Seventeen Co. Ho. Society, Tilaknagar, Chembur, Mumbai 400089
        </p>
        <p><i class="fa fa-phone"></i> Phone (India) : +91 9999 999 999</p>
        <p><i class="fa fa-envelope"></i> E-mail : info@ourdigitalsociety.com</p>
        
      </div>
      
      <div class="col-md-3 col-sm-6 paddingtop-bottom pull-right">
        <div class="fb-page" data-href="https://www.facebook.com/facebook" data-tabs="timeline" data-height="300" data-small-header="false" style="margin-bottom:15px;" data-adapt-container-width="true" data-hide-cover="false" data-show-facepile="true">
          <div class="fb-xfbml-parse-ignore">
            <blockquote cite="https://www.facebook.com/facebook"><a href="https://www.facebook.com/facebook">Facebook</a></blockquote>
            <blockquote cite="https://www.facebook.com/facebook"><a href="https://www.facebook.com/facebook">Twitter</a></blockquote>
          </div>
        </div>
      </div>
    </div>
  </div>
</footer> -->
<!--footer start from here-->

<!-- <div class="copyright">
  <div class="container">
    <div class="col-md-6">
      <p>&copy; 2018 - All Rights with Our Digital Society</p>
    </div>
    <div class="col-md-6">
      <ul class="bottom_ul">
        <li><a href="#">www.ourdigitalsociety.com</a></li>
        <li><a href="#">About us</a></li>
        <li><a href="#">Contact Us</a></li>
        <li><a href="#">developed by pranav.v.g</a></li>
      </ul>
    </div>
  </div>
</div> -->
 		
   </body>
</html>
 