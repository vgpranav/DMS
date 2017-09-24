<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 

 
<div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
              <div class="menu_section">
                <h3>Menu <font color="#191919">${sessionScope.userObject.userroleid}|${sessionScope.socmanagercount}</font></h3> 
	                <ul class="nav side-menu">
	                
	                <c:if test="${sessionScope.userObject.userroleid==1 || sessionScope.socmanagercount>0}">
	                	<c:if test="${sessionScope.userObject.userroleid!=2}">
	                		  <c:if test="${sessionScope.userObject.userroleid==1}">
			                      <li>
			                      	<a><i class="fa fa-wrench"></i> Documents</a>
			                      	<ul class="nav child_menu">
			                      		 <li><a href="addDoctype.do">Create Doctype</a></li>
					                      <li><a href="addDocSubType.do">Create Doc Subtype</a></li>
					                      <li><a href="addFormFields.do">Create Doc Fields</a></li>
			                      	</ul>
			                      </li>
		                      </c:if>
		                      <c:if test="${sessionScope.userObject.userroleid==1 || sessionScope.userObject.userroleid==3}">
			                      <li>
			                      	<a><i class="fa fa-wrench"></i> Builder &amp; Developer / Architect</a>
			                      	
			                      	<ul class="nav child_menu">
			                      		 <li><a href="createBuilder.do">Create Builder &amp; Developer / Architect Profile</a></li>
			                      		 <!-- <li><a href="createBrochureBuilder.do">Add Builder Brochure</a></li>
			                      		 <li><a href="brochureSelectionBuilder.do">View Builder Brochure</a></li> -->
			                      		 
			                      		<li><a href="createProject.do">Create Project</a></li>
			                      		<!-- <li><a href="createBrochureProject.do">Add Project Brochure</a></li>
			                      		<li><a href="brochureSelectionProject.do">View Project Brochure</a></li> -->
			                      		
			                      		<li><a href="createSubProject.do">Create Sub Project</a></li>
			                      		<!-- <li><a href="createBrochureSubProject.do">Add Sub Project Brochure</a></li>
			                      		<li><a href="brochureSelectionSubProject.do">View Sub Project Brochure</a></li> -->
			                      	</ul>
			                      	
			                      </li>
		                      </c:if>
		                      <li>
		                      	<a> <i class="fa fa-wrench"></i> Society</a>
		                      	<ul class="nav child_menu">
		                      		<c:if test="${sessionScope.userObject.userroleid==1}">
		                      		 <li><a href="addSociety.do">Create Society</a></li>
		                      		 </c:if>
		                      		 <c:if test="${sessionScope.userObject.userroleid==1 || sessionScope.socmanagercount>0}">
				                      <li><a href="addSocietyPhotos.do">Add Society Photos</a></li>
				                      <li><a href="createCommittee.do">Create Committee</a></li>
				                     </c:if>
		                      	</ul>
		                      </li>
		                      <c:if test="${sessionScope.userObject.userroleid==1 || sessionScope.socmanagercount>0}">
			                      <li>
			                      	<a> <i class="fa fa-wrench"></i> Members</a>
			                      	<ul class="nav child_menu">
					                      <li><a href="addMember.do">Add Members</a></li>
					                      <li><a href="addMemberPhotos.do">Add Member Photos</a></li>
			                      	</ul>
			                      </li>
		                      </c:if>
		                      <c:if test="${sessionScope.userObject.userroleid==1 || sessionScope.socmanagercount>0}">
			                      <li>
			                      	<a> <i class="fa fa-wrench"></i> Vendors</a>
			                      	<ul class="nav child_menu">
			                      		<li><a href="createVendor.do">Create Vendor</a></li>
			                      		<li><a href="createVendorCards.do">Add Visiting Card</a></li>
			                      		<li><a href="createVendorPhoto.do">Add Vendor Photo</a></li>
			                      	</ul>
			                      </li>
		                      </c:if>
		                   </c:if>
	                </c:if>
		                  
		            <c:if test="${sessionScope.userObject.userroleid==1 || sessionScope.userObject.userroleid==3 || sessionScope.userObject.userroleid==4}">
		                  <li>
		                  	<a>
		                  		<i class="fa fa-folder"></i> 
		                  		Authorizations 
		                  		<span class="fa fa-chevron-down"></span>
		                  	</a>
		                    <ul class="nav child_menu">
		                    <c:if test="${sessionScope.userObject.userroleid==1 || sessionScope.userObject.userroleid==4}">
		                      <li><a href="manageDesignation.do">Manage Soc. Designations</a></li>
		                      <li><a href="societyManagerMapping.do">Society Manager Mapping</a></li>
		                      <c:if test="${sessionScope.userObject.userroleid==1}">
			                      <li><a href="builderMapping.do">Builder Mapping</a></li>
			                      <li><a href="deleteAuth.do">Delete Authorization</a></li>
		                      </c:if>
		                      <li><a href="societyDocumentMapping.do">Society Document Mapping</a></li>
		                      
		                    </c:if>
		                      <li><a href="managerDocViewAuth.do">Document View Access</a></li>
		                      <li><a href="addConfidentialDocAccess.do">Confidential Doc Access</a></li>
		                    </ul>
		                  </li>
		            </c:if>
		            
		            <c:if test="${sessionScope.userObject.userroleid==1}">
		                  <li>
		                  	<a>
		                  		<i class="fa fa-folder"></i> 
		                  		Monitoring 
		                  		<span class="fa fa-chevron-down"></span>
		                  	</a>
		                    <ul class="nav child_menu">
		                     	<li><a href="viewUserActivity.do">View User Activity</a></li>
		                     	<li><a href="viewLoginHistory.do">View Login History</a></li>
		                     	<li><a href="fileMonitoring.do">File Monitoring</a></li>
		                    </ul>
		                  </li>
		            </c:if>
		            
		             <c:if test="${sessionScope.userObject.userroleid==1  || sessionScope.socmanagercount>0}">   
		             <c:if test="${sessionScope.userObject.userroleid!=2}"> 
		                  <li>
		                  	<a>
		                  		<i class="fa fa-folder"></i> 
		                  		Add Information 
		                  		<span class="fa fa-chevron-down"></span>
		                  	</a>
		                    <ul class="nav child_menu">
		                    <c:if test="${sessionScope.userObject.userroleid==1 || sessionScope.socmanagercount>0}">
		                    	<c:if test="${sessionScope.userObject.userroleid!=2}">
			                      <li><a href="addDocument1.do">Add Document</a></li>
			                      <li><a href="addNotice.do">Add Notice Board Document</a></li>
			                      <li><a href="viewDocument.do">View Document</a></li>
		                       </c:if>
		                    </c:if>
		                      
		                   
		                    
		                    <c:if test="${sessionScope.userObject.userroleid==1 || sessionScope.socmanagercount>0}">
		                     <c:if test="${sessionScope.userObject.userroleid!=2}">
		    	               	  <c:if test="${sessionScope.userObject.userroleid==1}">
				                      <li><a href="deleteDocument.do">Delete Document</a></li>
			                      </c:if>
							  <li><a href="addPolicy.do">Add Policy/Amenities</a></li>
							 </c:if>
							</c:if>
		                      
		                    </ul>
		                  </li>
		                  </c:if>
		             </c:if>
		               
		              <c:if test="${sessionScope.userObject.userroleid==1 || sessionScope.socmanagercount>0 || sessionScope.userObject.userroleid==2}">   
		              	   
			                    <li>
				                  	<a>
				                  		<i class="fa fa-folder"></i> 
				                  		Administrator 
				                  		<span class="fa fa-chevron-down"></span>
				                  	</a>
				                    	<ul class="nav child_menu">
				                      		<li><a href="displayAdminPanel.do">Administrative Details</a></li>
					                      <c:if test="${sessionScope.userObject.userroleid==1}">
					                      	<li><a href="createAdminUser.do">Create Admin User</a></li>
					                      </c:if>
				                    	</ul>
			                  </li>
		                  
		             </c:if>     
		             
		             
		              <c:if test="${sessionScope.userObject.userroleid==1}">         
		                   <li>
		                  	<a>
		                  		<i class="fa fa-folder"></i> 
		                  		ODS Team 
		                  		<span class="fa fa-chevron-down"></span>
		                  	</a>
		                    <ul class="nav child_menu">
		                      <li><a href="addCallRef.do">Add Call Reference</a></li>
		                      <li><a href="viewAllCallRef.do">View Call Reference</a></li>
		                    </ul>
		                  </li>
		             </c:if>     
		             
		             <c:if test="${sessionScope.userObject.userroleid==0}">      
		                    <li>
		                  	<a>
		                  		<i class="fa fa-folder"></i> 
		                  		My ODS
		                  		<span class="fa fa-chevron-down"></span>
		                  	</a>
		                    <ul class="nav child_menu">
		                      <li><a href="editUserProfile.do">Edit My Profile</a></li>
		                    </ul>
		                  </li>
		             </c:if>
		             
		             
		              <c:if test="${sessionScope.userObject.userroleid==1}">
		                  <li>
		                  	<a>
		                  		<i class="fa fa-folder"></i> 
		                  		IPR 
		                  		<span class="fa fa-chevron-down"></span>
		                  	</a>
		                    <ul class="nav child_menu">
		                     	<li><a href="createBillStructure.do">Create Bill Structure</a></li>
		                     	<li><a href="addCommonBillData.do">Add Bill Data</a></li>
		                     	<li><a href="generateBill.do">Generate Bill</a></li>
		                    </ul>
		                  </li>
		            </c:if>
		            
	                  </ul>
                  </div>
            </div>
