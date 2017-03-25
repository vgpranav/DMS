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
	 					<img class="img-circle"  width="210" src="data:<%= request.getSession().getAttribute("imgContentType") %>;base64,<%= request.getSession().getAttribute("imgBase64") %>"/>
	  				</div>
	  			
	  				<div class="col-md-9 col-sm-9 col-xs-12">
	  					<div class="col-md-4 col-sm-4 col-xs-12">
	  						<label class="control-label">Name of Flat Owner</label>
	  						<div>${userprofile.firstName} ${userprofile.lastName}</div> 
	  						<br>
	  					</div>
	  					
	  					<div class="col-md-4 col-sm-4 col-xs-12">
	  						<label class="control-label">Contact Number</label>
	  						<div>${userprofile.mobileNo}  </div> 
	  						<br>
	  					</div>
	  					
	  					 
	  					<c:forEach items="${fn:split(userprofile.jointowners,',')}" var="myItem" varStatus="loopStatus">
							<div class="col-md-4 col-sm-4 col-xs-12">
	  							<label class="control-label">Joint Owner ${loopStatus.index+1}</label>
	  							<div> ${myItem} </div> 
	  							<br>
	  						</div>
						</c:forEach>
	  						
	  					 <div class="col-md-4 col-sm-4 col-xs-12">
	  						<label class="control-label">Flat No.</label>
	  						<div>${userprofile.flatno}  </div> 
	  						<br>
	  					</div>
	  					
	  					<div class="col-md-4 col-sm-4 col-xs-12">
	  						<label class="control-label">Floor</label>
	  						<div>${userprofile.floor}  </div> 
	  						<br>
	  					</div>
	  					
	  					<div class="col-md-4 col-sm-4 col-xs-12">
	  						<label class="control-label">Wing</label>
	  						<div>${userprofile.wing}  </div> 
	  						<br>
	  					</div>
	  					
	  					<div class="col-md-4 col-sm-4 col-xs-12">
	  						<label class="control-label">Wing</label>
	  						<div>${userprofile.wing}  </div> 
	  						<br>
	  					</div>
	  					
	  					<div class="col-md-4 col-sm-4 col-xs-12">
	  						<label class="control-label">Registration Date</label>
	  						<div>${userprofile.purchasedate}  </div> 
	  						<br>
	  					</div>
	  					
	  					<div class="col-md-4 col-sm-4 col-xs-12">
	  						<label class="control-label">Possession Date</label>
	  						<div>${userprofile.possessiondate}  </div> 
	  						<br>
	  					</div>
	  					
	  					<div class="col-md-4 col-sm-4 col-xs-12">
	  						<label class="control-label">Blood Group</label>
	  						<div>${userprofile.bloodgroup}  </div> 
	  						<br>
	  					</div>
	  					
	  					<div class="col-md-4 col-sm-4 col-xs-12">
	  						<label class="control-label">Aadhar No.</label>
	  						<div>${userprofile.aadharno}  </div> 
	  						<br>
	  					</div>
	  					
	  					<div class="col-md-4 col-sm-4 col-xs-12">
	  						<label class="control-label">Share Certificate No.</label>
	  						<div>${userprofile.sharecertno}  </div> 
	  						<br>
	  					</div>
	  					
	  					
	  					<div class="col-md-4 col-sm-4 col-xs-12">
	  						<label class="control-label">Nominee 1</label>
	  						<div>${userprofile.nominee1} (${userprofile.percent1}%)</div> 
	  						<br>
	  					</div>
	  					
	  					
	  					<div class="col-md-4 col-sm-4 col-xs-12">
	  						<label class="control-label">Nominee 2</label>
	  						<div>${userprofile.nominee2} (${userprofile.percent2}%)</div> 
	  						<br>
	  					</div>
	  					
	  					
	  					<div class="col-md-4 col-sm-4 col-xs-12">
	  						<label class="control-label">Nominee 3</label>
	  						<div>${userprofile.nominee3} (${userprofile.percent3}%)</div> 
	  						<br>
	  					</div>
	  					
	  					
	  					
	  					<div class="col-md-4 col-sm-4 col-xs-12">
	  						<label class="control-label">Occupancy</label>
	  						<div>${userprofile.occupancy} </div> 
	  						<br>
	  					</div>
	  					
	  					<div class="clearfix"></div>
	  					
	  					<c:if test="${userprofile.occupancy=='leased'}">
	  					
	  						<div class="col-md-4 col-sm-4 col-xs-12">
		  						<label class="control-label">Name of Lease Owner</label>
		  						<div>${userprofile.tenantname} </div> 
		  						<br>
		  					</div>
		  					
		  					<div class="col-md-4 col-sm-4 col-xs-12">
		  						<label class="control-label">Address</label>
		  						<div>${userprofile.tenantaddress} </div> 
		  						<br>
		  					</div>
		  					
		  					<div class="col-md-4 col-sm-4 col-xs-12">
		  						<label class="control-label">Contact Number</label>
		  						<div>${userprofile.tenantcontactnumber} </div> 
		  						<br>
		  					</div>
		  					
		  					<div class="col-md-4 col-sm-4 col-xs-12">
		  						<label class="control-label">Alternate Number</label>
		  						<div>${userprofile.tenantaltnumber} </div> 
		  						<br>
		  					</div>
		  					
		  					<div class="col-md-4 col-sm-4 col-xs-12">
		  						<label class="control-label">Email</label>
		  						<div>${userprofile.tenantemail} </div> 
		  						<br>
		  					</div>
		  					
		  					<div class="col-md-4 col-sm-4 col-xs-12">
		  						<label class="control-label">Aadhar No.</label>
		  						<div>${userprofile.tenantaadharno} </div> 
		  						<br>
		  					</div>
	  						
	  						
	  					</c:if>
	  					
	  				</div>
				</div>
				
				
				<div class="row">
				<hr>
					<div class="col-md-4 col-sm-4 col-xs-12">
						<h2><a href="displaySelfSociety.do">Self Society Details</a></h2>
					</div>
					
					<div class="col-md-4 col-sm-4 col-xs-12">
						<h2><a href="viewNoticeboard.do?societyid=${userprofile.societyid}">Society Notice Board</a></h2>
					</div>
					
					<c:forEach items="${docSubType}" var="myItem" varStatus="loopStatus">
						<h2><a href="displayDocument.do?doctypeid=${myItem.doctypeid}&userid=${userprofile.userid}">${myItem.docsubtypedesc}</a></h2>
					</c:forEach>
					
				</div>
			</div>
		</div>
	</div>
	</div>
