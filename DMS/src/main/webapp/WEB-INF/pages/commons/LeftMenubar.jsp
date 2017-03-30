<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
              <div class="menu_section">
                <h3>Menu</h3> 
	                <ul class="nav side-menu">
	                
	                <c:if test="${userroleid==1}">
	                	<li>
		                  	<a>
		                  		<i class="fa fa-folder"></i> 
		                  			Masters 
		                  		<span class="fa fa-chevron-down"></span>
		                  	</a>
		                    <ul class="nav child_menu">
		                      <li>
		                      	<a><i class="fa fa-folder-o"></i> Documents</a>
		                      	<ul class="nav child_menu">
		                      		 <li><a href="addDoctype.do">Create Doctype</a></li>
				                      <li><a href="addDocSubType.do">Create Doc Subtype</a></li>
				                      <li><a href="addFormFields.do">Create Doc Fields</a></li>
		                      	</ul>
		                      </li>
		                      
		                      <li>
		                      	<a><i class="fa fa-folder-o"></i> Builder/Project</a>
		                      	<ul class="nav child_menu">
		                      		 <li><a href="createBuilder.do">Create Builder Profile</a></li>
		                      		<li><a href="createProject.do">Create Project</a></li>
		                      		<li><a href="createSubProject.do">Create Sub Project</a></li>
		                      	</ul>
		                      </li>
		                    
		                      <li>
		                      	<a> <i class="fa fa-folder-o"></i> Society</a>
		                      	<ul class="nav child_menu">
		                      		 <li><a href="addSociety.do">Create Society</a></li>
				                      <li><a href="addSocietyPhotos.do">Add Society Photos</a></li>
				                      <li><a href="createCommittee.do">Create Committee</a></li>
		                      	</ul>
		                      </li>
		                      
		                      <li>
		                      	<a> <i class="fa fa-folder-o"></i> Members</a>
		                      	<ul class="nav child_menu">
				                      <li><a href="addMember.do">Add Members</a></li>
				                      <li><a href="addMemberPhotos.do">Add Member Photos</a></li>
		                      	</ul>
		                      </li>
		                      
		                      <li>
		                      	<a> <i class="fa fa-folder-o"></i> Vendors</a>
		                      	<ul class="nav child_menu">
		                      		<li><a href="createVendor.do">Create Vendor</a></li>
		                      		<li><a href="createVendorCards.do">Add Vendor Visiting Card</a></li>
		                      	</ul>
		                      </li>
		                      
		                    </ul>
		                  </li>
	                </c:if>
		                  
		            <c:if test="${userroleid==1}">
		                  <li>
		                  	<a>
		                  		<i class="fa fa-folder"></i> 
		                  		Authorizations 
		                  		<span class="fa fa-chevron-down"></span>
		                  	</a>
		                    <ul class="nav child_menu">
		                      <li><a href="societyManagerMapping.do">Society Manager Mapping</a></li>
		                      <li><a href="societyDocumentMapping.do">Society Document Mapping</a></li>
		                    </ul>
		                  </li>
		            </c:if>
		            
		            
		             <c:if test="${userroleid==1}">     
		                  <li>
		                  	<a>
		                  		<i class="fa fa-folder"></i> 
		                  		Documents 
		                  		<span class="fa fa-chevron-down"></span>
		                  	</a>
		                    <ul class="nav child_menu">
		                      <li><a href="addDocument1.do">Add Document</a></li>
		                      <li><a href="addNotice.do">Add Notice Board Document</a></li>
		                      <li><a href="viewDocument.do">View Document</a></li>
		                    </ul>
		                  </li>
		             </c:if>
		               
		              <c:if test="${userroleid==1}">         
		                   <li>
		                  	<a>
		                  		<i class="fa fa-folder"></i> 
		                  		Administrator 
		                  		<span class="fa fa-chevron-down"></span>
		                  	</a>
		                    <ul class="nav child_menu">
		                      <li><a href="displayAdminPanel.do">Administrative Details</a></li>
		                      <li><a href="createAdminUser.do">Create Admin User</a></li>
		                    </ul>
		                  </li>
		             </c:if>     
		             
		             
		              <c:if test="${userroleid==1}">         
		                   <li>
		                  	<a>
		                  		<i class="fa fa-folder"></i> 
		                  		ODS Team 
		                  		<span class="fa fa-chevron-down"></span>
		                  	</a>
		                    <ul class="nav child_menu">
		                      <li><a href="addCallRef.do">Add Call Reference</a></li>
		                    </ul>
		                  </li>
		             </c:if>     
		                   
	                  </ul>
                  </div>
                  
                 <!--  <li><a><i class="fa fa-edit"></i> Forms <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="form.html">General Form</a></li>
                      <li><a href="form_advanced.html">Advanced Components</a></li>
                      <li><a href="form_validation.html">Form Validation</a></li>
                      <li><a href="form_wizards.html">Form Wizard</a></li>
                      <li><a href="form_upload.html">Form Upload</a></li>
                      <li><a href="form_buttons.html">Form Buttons</a></li>
                    </ul>
                  </li>
                  <li><a><i class="fa fa-desktop"></i> UI Elements <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="general_elements.html">General Elements</a></li>
                      <li><a href="media_gallery.html">Media Gallery</a></li>
                      <li><a href="typography.html">Typography</a></li>
                      <li><a href="icons.html">Icons</a></li>
                      <li><a href="glyphicons.html">Glyphicons</a></li>
                      <li><a href="widgets.html">Widgets</a></li>
                      <li><a href="invoice.html">Invoice</a></li>
                      <li><a href="inbox.html">Inbox</a></li>
                      <li><a href="calendar.html">Calendar</a></li>
                    </ul>
                  </li>
                  <li><a><i class="fa fa-table"></i> Tables <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="tables.html">Tables</a></li>
                      <li><a href="tables_dynamic.html">Table Dynamic</a></li>
                    </ul>
                  </li>
                  <li><a><i class="fa fa-bar-chart-o"></i> Data Presentation <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="chartjs.html">Chart JS</a></li>
                      <li><a href="chartjs2.html">Chart JS2</a></li>
                      <li><a href="morisjs.html">Moris JS</a></li>
                      <li><a href="echarts.html">ECharts</a></li>
                      <li><a href="other_charts.html">Other Charts</a></li>
                    </ul>
                  </li>
                  <li><a><i class="fa fa-clone"></i>Layouts <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="fixed_sidebar.html">Fixed Sidebar</a></li>
                      <li><a href="fixed_footer.html">Fixed Footer</a></li>
                    </ul>
                  </li>
                </ul>
              </div>
              <div class="menu_section">
                <h3>Live On</h3>
                <ul class="nav side-menu">
                  <li><a><i class="fa fa-bug"></i> Additional Pages <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="e_commerce.html">E-commerce</a></li>
                      <li><a href="projects.html">Projects</a></li>
                      <li><a href="project_detail.html">Project Detail</a></li>
                      <li><a href="contacts.html">Contacts</a></li>
                      <li><a href="profile.html">Profile</a></li>
                    </ul>
                  </li>
                  <li><a><i class="fa fa-windows"></i> Extras <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="page_403.html">403 Error</a></li>
                      <li><a href="page_404.html">404 Error</a></li>
                      <li><a href="page_500.html">500 Error</a></li>
                      <li><a href="plain_page.html">Plain Page</a></li>
                      <li><a href="login.html">Login Page</a></li>
                      <li><a href="pricing_tables.html">Pricing Tables</a></li>
                    </ul>
                  </li>
                  <li><a><i class="fa fa-sitemap"></i> Multilevel Menu <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                        <li><a href="#level1_1">Level One</a>
                        <li><a>Level One<span class="fa fa-chevron-down"></span></a>
                          <ul class="nav child_menu">
                            <li class="sub_menu"><a href="level2.html">Level Two</a>
                            </li>
                            <li><a href="#level2_1">Level Two</a>
                            </li>
                            <li><a href="#level2_2">Level Two</a>
                            </li>
                          </ul>
                        </li>
                        <li><a href="#level1_2">Level One</a>
                        </li>
                    </ul>
                  </li>                  
                  <li><a href="javascript:void(0)"><i class="fa fa-laptop"></i> Landing Page <span class="label label-success pull-right">Coming Soon</span></a></li>
                </ul>
              </div> -->

            </div>
