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
    <tiles:insertAttribute name="imports" />  
  </head>
  <body class="nav-md" style="background-color: #f2f2f2 !important;">
	<div class="container body" >
      <div class="main_container">
		
		<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container-fluid">
    <div class="row" style="box-shadow:2px 2px 2px #ccc;">
			<div class="col-sm-4 col-md-4 col-xs-12" >
			<img src="<%= request.getContextPath() %>/resources/images/ods-logo.png" height="70">
			</div>
			
			<div class="col-sm-4 col-md-4 col-xs-12 pull-right" >
				<form class="navbar-form navbar-right" action="login.do">
		        	<button type="submit" class="btn btn-default">Login</button>
		      </form>
			</div>
		</div>
  </div><!-- /.container-fluid -->
</nav>

		
		
		<br><br><br><br><br> 
		
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
 		
 		
 		<div class="col-md-12 col-sm-12 col-xs-12">
              <div class="x_panel tile">
                <div class="x_title">
                  <h2></h2> 
                  <div class="clearfix"></div>
                </div>
                <div class="x_content">
                  <div class="dashboard-widget-content">
                  <div class="row tile_count">
                  
             <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count"> 
            </div>
            
            <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
              <span class="count_top"><i class="fa fa-user"></i> Societies on ODS</span>
              <div class="count">2500</div>
             <span class="count_bottom"><i class="green">4% </i> From last Week</span> 
            </div>
            <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
              <span class="count_top"><i class="fa fa-clock-o"></i> Documents Uploaded</span>
              <div class="count">123.50</div>
             <span class="count_bottom"><i class="green"><i class="fa fa-sort-asc"></i>3% </i> From last Week</span>
            </div>
            <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
              <span class="count_top"><i class="fa fa-user"></i> Pages Scanned</span>
              <div class="count green">2,500</div>
              <span class="count_bottom"><i class="green"><i class="fa fa-sort-asc"></i>34% </i> From last Week</span>
            </div>
            <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
              <span class="count_top"><i class="fa fa-user"></i> User Footprints</span>
              <div class="count">4,567</div>
              <span class="count_bottom"><i class="red"><i class="fa fa-sort-desc"></i>12% </i> From last Week</span>
            </div>
            <!-- <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
              <span class="count_top"><i class="fa fa-user"></i> Total Collections</span>
              <div class="count">2,315</div>
              <span class="count_bottom"><i class="green"><i class="fa fa-sort-asc"></i>34% </i> From last Week</span>
            </div>
            <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
              <span class="count_top"><i class="fa fa-user"></i> Total Connections</span>
              <div class="count">7,325</div>
              <span class="count_bottom"><i class="green"><i class="fa fa-sort-asc"></i>34% </i> From last Week</span>
            </div> -->
          </div>
                </div>
              </div>
 		</div>
 		
 		
 		<div class="row">
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
                  			Data security and protection is a key issue for businesses to consider. There is a greater risk of paper documents being damaged, stolen or lost through fire, flood or theft.
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
 			
 			<div class="col-md-4 col-sm-4 col-xs-12">
 			<br><br><br><br><br>
 			</div>
 		</div>
 		
 		
               
               <div class="col-md-12 col-sm-12 col-xs-12">
               	<hr>
               	<div class="row">
               		<div class="col-sm-4 pull-left">
						<strong>Our Digital Society &copy; 2018</strong>
					</div>
					
					<div class="col-sm-4">
						<strong>Contact Us &bull; Terms and Conditions &bull; Disclaimer</strong>
					</div>
					
					
					
					<div class="col-sm-4 pull-right" align="right">
						 <strong><em>Developed By Pranav V.G.</em></strong>
					</div>
					</div>
               </div>
 		
	</div>
	</div>
    <tiles:insertAttribute name="importJScript" />  
    </div>
   </body>
</html>
 