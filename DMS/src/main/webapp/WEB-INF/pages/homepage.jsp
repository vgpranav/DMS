<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- top tiles
          <div class="row tile_count">
            <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
              <span class="count_top"><i class="fa fa-user"></i> Total Users</span>
              <div class="count">2500</div>
              <span class="count_bottom"><i class="green">4% </i> From last Week</span>
            </div>
            <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
              <span class="count_top"><i class="fa fa-clock-o"></i> Average Time</span>
              <div class="count">123.50</div>
              <span class="count_bottom"><i class="green"><i class="fa fa-sort-asc"></i>3% </i> From last Week</span>
            </div>
            <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
              <span class="count_top"><i class="fa fa-user"></i> Total Males</span>
              <div class="count green">2,500</div>
              <span class="count_bottom"><i class="green"><i class="fa fa-sort-asc"></i>34% </i> From last Week</span>
            </div>
            <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
              <span class="count_top"><i class="fa fa-user"></i> Total Females</span>
              <div class="count">4,567</div>
              <span class="count_bottom"><i class="red"><i class="fa fa-sort-desc"></i>12% </i> From last Week</span>
            </div>
            <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
              <span class="count_top"><i class="fa fa-user"></i> Total Collections</span>
              <div class="count">2,315</div>
              <span class="count_bottom"><i class="green"><i class="fa fa-sort-asc"></i>34% </i> From last Week</span>
            </div>
            <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
              <span class="count_top"><i class="fa fa-user"></i> Total Connections</span>
              <div class="count">7,325</div>
              <span class="count_bottom"><i class="green"><i class="fa fa-sort-asc"></i>34% </i> From last Week</span>
            </div>
          </div>
          /top tiles -->

<div class="col-md-12 col-sm-12 col-xs-12">
	<div class="x_panel tile">
		<div class="x_title">
			<h2>Personal Details</h2>
			<ul class="nav navbar-right panel_toolbox">
				<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
				</li>
			</ul>
			<div class="clearfix"></div>
		</div>
		<div class="x_content">
			<div class="dashboard-widget-content">
				<div class="row">
					<div class="col-md-3 col-sm-3 col-xs-12">
					<% if(request.getSession().getAttribute("imgBase64")!=null && request.getSession().getAttribute("imgBase64").toString().length()>5){ %>
	 					<img class="img-circle"  width="210" src="data:<%= request.getSession().getAttribute("imgContentType") %>;base64,<%= request.getSession().getAttribute("imgBase64") %>"/>
	 					<% } else {
	            	  %>
	            	  <img src="<%= request.getContextPath() %>/resources/images/blankface.jpg" width="210">
	            	  <%
		              }
		              %>
	  				</div>
	  			 
	  				
	  				<div class="col-md-9 col-sm-9 col-xs-12">
	  					
	  					<c:if test="${fn:length(userprofile.firstName)>0}">
	  					<div class="col-md-4 col-sm-4 col-xs-12">
	  						<label class="control-label">Name of Flat Owner</label>
	  						<div>${userprofile.firstName} ${userprofile.lastName}</div> 
	  						<br>
	  					</div>
	  					</c:if>
	  					
	  					<c:if test="${fn:length(userprofile.mobileNo)>0}">
	  					<div class="col-md-4 col-sm-4 col-xs-12">
	  						<label class="control-label">Contact Number</label>
	  						<div>${userprofile.mobileNo}  </div> 
	  						<br>
	  					</div>
	  					</c:if>
	  					
	  					
	  					<c:if test="${fn:length(userprofile.alternateno)>0}">
	  					<div class="col-md-4 col-sm-4 col-xs-12">
	  						<label class="control-label">Alternate Number</label>
	  						<div>${userprofile.alternateno}  </div> 
	  						<br>
	  					</div>
	  					</c:if>
	  					
	  					<c:if test="${fn:length(userprofile.email)>0}">	
	  					<div class="col-md-4 col-sm-4 col-xs-12">
	  						<label class="control-label">Email</label>
	  						<div>${userprofile.email}  </div> 
	  						<br>
	  					</div>
	  					</c:if>
	  					
	  					<c:if test="${fn:length(userprofile.jointowners)>0}">	
		  					<c:forEach items="${fn:split(userprofile.jointowners,',')}" var="myItem" varStatus="loopStatus">
								<div class="col-md-4 col-sm-4 col-xs-12">
		  							<label class="control-label">Joint Owner ${loopStatus.index+1}</label>
		  							<div> ${myItem} </div> 
		  							<br>
		  						</div>
							</c:forEach>
	  					</c:if>
	  					
	  					 <c:if test="${fn:length(userprofile.flatno)>0}">	
	  					 <div class="col-md-4 col-sm-4 col-xs-12">
	  						<label class="control-label">Flat No.</label>
	  						<div>${userprofile.flatno}  </div> 
	  						<br>
	  					</div>
	  					</c:if>
	  					
	  					<c:if test="${fn:length(userprofile.floor)>0}">	
	  					<div class="col-md-4 col-sm-4 col-xs-12">
	  						<label class="control-label">Floor</label>
	  						<div>${userprofile.floor}</div> 
	  						<br>
	  					</div>
	  					</c:if>
	  					
	  					<c:if test="${fn:length(userprofile.tower)>0}">	
	  					<div class="col-md-4 col-sm-4 col-xs-12">
	  						<label class="control-label">Tower</label>
	  						<div>${userprofile.tower} </div> 
	  						<br>
	  					</div>
	  					</c:if>
	  					
	  					<c:if test="${fn:length(userprofile.wing)>0}">	
	  					<div class="col-md-4 col-sm-4 col-xs-12">
	  						<label class="control-label">Wing</label>
	  						<div>${userprofile.wing}</div> 
	  						<br>
	  					</div>
	  					</c:if>
	  					
	  					<c:if test="${userprofile.purchasedate!=null}">	
	  					<div class="col-md-4 col-sm-4 col-xs-12">
	  						<label class="control-label">Registration Date</label>
	  						<div>${userprofile.purchasedate}  </div> 
	  						<br>
	  					</div>
	  					</c:if>
	  					
	  					<c:if test="${userprofile.possessiondate!=null}">	
	  					<div class="col-md-4 col-sm-4 col-xs-12">
	  						<label class="control-label">Possession Date</label>
	  						<div>${userprofile.possessiondate}  </div> 
	  						<br>
	  					</div>
	  					</c:if>
	  					
	  					<c:if test="${fn:length(userprofile.bloodgroup)>0}">	
	  					<div class="col-md-4 col-sm-4 col-xs-12">
	  						<label class="control-label">Blood Group</label>
	  						<div>${userprofile.bloodgroup}  </div> 
	  						<br>
	  					</div>
	  					</c:if>
	  					
	  					<c:if test="${fn:length(userprofile.aadharno)>0}">	
	  					<div class="col-md-4 col-sm-4 col-xs-12">
	  						<label class="control-label">Aadhar No.</label>
	  						<div>${userprofile.aadharno}  </div> 
	  						<br>
	  					</div>
	  					</c:if>
	  					
	  					<c:if test="${fn:length(userprofile.sharecertno)>0}">	
	  					<div class="col-md-4 col-sm-4 col-xs-12">
	  						<label class="control-label">Share Certificate No.</label>
	  						<div>${userprofile.sharecertno}  </div> 
	  						<br>
	  					</div>
	  					</c:if>
	  					
	  					<c:if test="${fn:length(userprofile.nominee1)>0}">	
	  					<div class="col-md-4 col-sm-4 col-xs-12">
	  						<label class="control-label">Nominee 1</label>
	  						<div>${userprofile.nominee1} (${userprofile.percent1}%)</div> 
	  						<br>
	  					</div>
	  					</c:if>
	  					
	  					<c:if test="${fn:length(userprofile.nominee2)>0}">	
	  					<div class="col-md-4 col-sm-4 col-xs-12">
	  						<label class="control-label">Nominee 2</label>
	  						<div>${userprofile.nominee2} (${userprofile.percent2}%)</div> 
	  						<br>
	  					</div>
	  					</c:if>
	  					
	  					<c:if test="${fn:length(userprofile.nominee3)>0}">	
	  					<div class="col-md-4 col-sm-4 col-xs-12">
	  						<label class="control-label">Nominee 3</label>
	  						<div>${userprofile.nominee3} (${userprofile.percent3}%)</div> 
	  						<br>
	  					</div>
	  					</c:if>
	  					
	  					<c:if test="${fn:length(userprofile.occupancy)>0}">	
	  					<div class="col-md-4 col-sm-4 col-xs-12">
	  						<label class="control-label">Occupancy</label>
	  						<div>${userprofile.occupancy} </div> 
	  						<br>
	  					</div>
	  					</c:if>
	  					
	  					<div class="clearfix"></div>
	  					
	  					<c:if test="${userprofile.occupancy=='leased'}">
	  					
	  						<c:if test="${fn:length(userprofile.tenantname)>0}">	
	  						<div class="col-md-4 col-sm-4 col-xs-12">
		  						<label class="control-label">Name of Lease Owner</label>
		  						<div>${userprofile.tenantname} </div> 
		  						<br>
		  					</div>
		  					</c:if>
		  					
		  					<c:if test="${fn:length(userprofile.tenantaddress)>0}">	
		  					<div class="col-md-4 col-sm-4 col-xs-12">
		  						<label class="control-label">Address</label>
		  						<div>${userprofile.tenantaddress} </div> 
		  						<br>
		  					</div>
		  					</c:if>
		  					
		  					<c:if test="${fn:length(userprofile.tenantcontactnumber)>0}">	
		  					<div class="col-md-4 col-sm-4 col-xs-12">
		  						<label class="control-label">Contact Number</label>
		  						<div>${userprofile.tenantcontactnumber} </div> 
		  						<br>
		  					</div>
		  					</c:if>
		  					
		  					<c:if test="${fn:length(userprofile.tenantaltnumber)>0}">	
		  					<div class="col-md-4 col-sm-4 col-xs-12">
		  						<label class="control-label">Alternate Number</label>
		  						<div>${userprofile.tenantaltnumber} </div> 
		  						<br>
		  					</div>
		  					</c:if>
		  					
		  					<c:if test="${fn:length(userprofile.tenantemail)>0}">	
		  					<div class="col-md-4 col-sm-4 col-xs-12">
		  						<label class="control-label">Email</label>
		  						<div>${userprofile.tenantemail} </div> 
		  						<br>
		  					</div>
		  					</c:if>
		  					
		  					<c:if test="${fn:length(userprofile.tenantaadharno)>0}">	
		  					<div class="col-md-4 col-sm-4 col-xs-12">
		  						<label class="control-label">Aadhar No.</label>
		  						<div>${userprofile.tenantaadharno} </div> 
		  						<br>
		  					</div>
	  						</c:if>
	  						
	  					</c:if>
	  					
	  				</div>
				</div>
				
				
				<div class="row">
				<hr>
					<div class="col-md-4 col-sm-4 col-xs-12">
						<h2><a href="displaySelfSociety.do">Self Society Details</a></h2>
						<br>
					</div>
					
					<div class="col-md-4 col-sm-4 col-xs-12">
						<h2><a href="viewNoticeboard.do?societyid=${userprofile.societyid}">Society Notice Board</a></h2>
						<br>
					</div>
					
					<c:forEach items="${docSubType}" var="myItem" varStatus="loopStatus">
						<h2><a target="_blank" href="displayDocument.do?doctypeid=${myItem.doctypeid}&userid=${userprofile.userid}">${myItem.docsubtypedesc}</a></h2>
						<br>
					</c:forEach>
					
				</div>
			</div>
		</div>
	</div>
	</div>
