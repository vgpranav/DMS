<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="col-md-12 col-sm-12 col-xs-12">
	<div class="x_panel tile ">
		<div class="x_title">
			<h2>Add Member</h2>

			<div class="pull-right">
				<select name="societyid" id="societyid" class="form-control"
					onchange="getMembersForSociety()">
					<option value="">-- select society --</option>
					<c:forEach items="${societyList}" var="myItem"
						varStatus="loopStatus">
						<option value="${myItem.societyid}">${myItem.societyname}</option>
					</c:forEach>
				</select>
			</div>
			<ul class="nav navbar-right panel_toolbox pull-right">
				<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
				</li>
			</ul>
			<div class="clearfix"></div>
		</div>
		<div class="x_content">
			<div class="dashboard-widget-content">
				<div class="row">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<form id="addDocSubTypeForm" data-parsley-validate
							class="form-horizontal form-label-left" action="#" method="post"
							onsubmit="return false;">

							<div class="editmodeind">

							<!-- <div class="col-md-12 col-sm-12 col-xs-12">
								<br /> 
								<h2><span class="label label-success label-md">Owner Photo</span></h2> 
								<hr />
							</div> -->
							<div class="form-group" >
								<div class="col-md-3 col-sm-3 col-xs-12 pull-right">
									<div id="photodiv" align="right"></div>
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Member Type<span class="required">*</span>
								</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<select id="membertype" name="membertype" class="form-control col-md-12 col-xs-12" onchange="changeMemberType()">
											<option value="Residential">Residential</option>
											<option value="Commercial">Commercial</option>
										</select>
									</div>
								</div>
							</div>
							
							<div class="col-md-12 col-sm-12 col-xs-12">
								<br /> 
								<h2><span class="label label-success label-md" id="owndetailsbadge">Owner Details</span></h2> 
								<hr />
							</div>
							<div class="clearfix"></div>

							<input type="hidden" id="userid" name="userid" value="0">
							
							
							
							<div class="form-group">
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">First Name <span class="required">*</span>
								</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="firstName" name="firstName"
											class="form-control col-md-12 col-xs-12" required="required">
									</div>
								</div>

								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Middle Name <span class="required">*</span>
								</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="middleName" name="middleName"
											class="form-control col-md-12 col-xs-12" required="required">
									</div>
								</div>
								
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Last Name <span class="required">*</span>
								</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="lastName" name="lastName"
											class="form-control col-md-12 col-xs-12" required="required">
									</div>
								</div>

								
							</div>

							<div class="form-group">

								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Mobile No <span class="required">*</span>
								</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="mobileNo" name="mobileNo"
											class="form-control col-md-12 col-xs-12" required="required">
									</div>
								</div>
								
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Alternate Number </label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="alternateno" name="alternateno"
											class="form-control col-md-12 col-xs-12">
									</div>
								</div>

								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Email </label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="email" name="email"
											class="form-control col-md-12 col-xs-12">
									</div>
								</div>


							</div>

							<div class="form-group">
							
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Aadhar No. <span class="required">*</span></label> 
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="aadharno" name="aadharno"
											class="form-control col-md-12 col-xs-12" required="required">
									</div>
								</div>
								

								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Password <span class="required">*</span>
								</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="password" id="password" name="password"
											class="form-control col-md-12 col-xs-12" required="required">
									</div>
								</div>
								
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Blood Group  
								</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<select id="bloodgroup" name="bloodgroup" class="form-control col-md-12 col-xs-12">
											<option value="an">A -Ve</option>
											<option value="ap">A +Ve</option>
											<option value="bn">B -Ve</option>
											<option value="bp">B +Ve</option>
											<option value="on">O -Ve</option>
											<option value="op">O +Ve</option>
											<option value="abn">AB -Ve</option>
											<option value="abp">AB +Ve</option>
										</select>
										
									</div>
								</div> 

							</div>
							
							<div class="form-group">
							<strong>Joint Owners</strong>
							<input type="hidden" id="jointowners" name="jointowners">
							<hr/>
							<button onclick="addJo();return false;" class="btn btn-success btn-sm">Add Joint Owner </button>
								<br/>
								<div id="jocontainerouter">
								<div id="jocontainer">
							</div>
							</div>
							</div>
							
							<hr/>
							
							<div id="comdetailsfields" style="display: none;">
								<div class="form-group">

									<label class="control-label col-md-2 col-sm-2 col-xs-12"
										for="first-name">Name of Company</label>
									<div class="col-md-6 col-sm-6 col-xs-12">
										<div class="col-md-12 col-sm-12 col-xs-12">
											<input type="text" id="companyname" name="companyname"
												class="form-control col-md-12 col-xs-12">
										</div>
									</div>
							
								</div>
								<div class="form-group">
							
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Type of Company</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<select class="form-control" name="companytype" id="companytype">
											<option value="prilc">Private Limited Company</option>
											<option value="publc">Public Limited Company</option>
											<option value="uc">Unlimited Company</option>
											<option value="llp">Limited Liability Partnership </option>
											<option value="p">Partnership</option>
											<option value="sp">Sole Proprietorship</option>
											<option value="lo">Liaison Office / Representative Office</option>
											<option value="po">Project Office </option>
											<option value="bo">Branch Office </option>
											<option value="jvc">Joint Venture Company</option>
											<option value="sc">Subsidiary Company</option>
										</select>
									</div>
								</div>
								
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Gumasta License no <span class="required">*</span>
								</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="gumastalicno" name="gumastalicno"
											class="form-control col-md-12 col-xs-12" required="required">
									</div>
								</div>
								
								
							</div>
							</div>
							
							<div class="col-md-12 col-sm-12 col-xs-12">
								<br /> <h2><span class="label label-success label-md"  id="flatdetailsbadge">Flat Details</span></h2> <hr />
							</div>
							<div class="clearfix"></div>

							<div class="form-group">
							
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Type</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<select class="form-control" name="flattype" id="flattype" onchange="changeFlattype()">
											<option value="1rk">1 RHK</option>
											<option value="1bhk">1 BHK</option>
											<option value="1.5bhk">1.5 BHK</option>
											<option value="2bhk">2 BHK</option>
											<option value="2.5bhk">2.5 BHK</option>
											<option value="3bhk">3 BHK</option>
											<option value="3.5bhk">3.5 BHK</option>
											<option value="shop">Shop</option>
										</select>
									</div>
								</div>

								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Purchase Date </label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="purchasedate" name="purchasedate"
											class="form-control col-md-12 col-xs-12 customdatepicker"
											readonly="readonly">
									</div>
								</div>

								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Possession Date </label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="possessiondate" name="possessiondate"
											class="form-control col-md-12 col-xs-12 customdatepicker"
											readonly="readonly">
									</div>
								</div>

								 
								 
								 
							</div>

							<div class="form-group">

								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name" id="flatnobadge">
									<span id="flatnolabel">Flat No</span> 
									<span class="required">*</span>
								</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="flatno" name="flatno"
											class="form-control col-md-12 col-xs-12" required="required">
									</div>
								</div>

								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Wing <span class="required">*</span>
								</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="wing" name="wing"
											class="form-control col-md-12 col-xs-12" required="required">
									</div>
								</div>

								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Tower </label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="tower" name="tower"
											class="form-control col-md-12 col-xs-12">
									</div>
								</div>
							</div>
							<div class="form-group">

								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Floor </label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="floor" name="floor"
											class="form-control col-md-12 col-xs-12" placeholder="">
									</div>
								</div>
								
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Built-up Area </label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="builtuparea" name="builtuparea"
											class="form-control col-md-12 col-xs-12"
											placeholder="sq. ft.">
									</div>
								</div>

								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Carpet Area </label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="carpetarea" name="carpetarea"
											class="form-control col-md-12 col-xs-12"
											placeholder="sq. ft.">
									</div>
								</div>
								
							</div>
							
							
							<!-- <div class="form-group" id="commtypebadge" style="display: none;">
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Commercial Type</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<select class="form-control" name="commercialtype" id="commercialtype">
											<option value="shop">Shop</option>
											<option value="workshop">Workshop</option>
											<option value="office">Office</option>
										</select>
									</div>
								</div>
							</div> -->
							
							
							<div class="col-md-12 col-sm-12 col-xs-12">
								<br /> <h2><span class="label label-success label-md">Occupancy </span></h2> <hr />
							</div>
							<div class="form-group">
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Occupancy <span class="required">*</span>
								</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<select class="form-control" name="occupancy" id="occupancy"
											required="required" onchange="showhidetenant()">
											<option value="self">Self Occupied</option>
											<option value="leased">Leased</option>
										</select>
									</div>
								</div>
							</div>
							
							
							<section id="tenantdetails" style="display: none;">
							<div class="col-md-12 col-sm-12 col-xs-12">
								<br /> <h2><span class="label label-success label-md">Lease Tenant Details</span></h2> <hr />
							</div>
							<div class="clearfix"></div>
							
							<div id="tenantcomdetilsbadge" style="display: none;">
								<div class="form-group">

									<label class="control-label col-md-2 col-sm-2 col-xs-12"
										for="first-name">Name of Company</label>
									<div class="col-md-6 col-sm-6 col-xs-12">
										<div class="col-md-12 col-sm-12 col-xs-12">
											<input type="text" id="tenantcompanyname" name="tenantcompanyname"
												class="form-control col-md-12 col-xs-12">
										</div>
									</div>
							
							</div>
								
							<div class="form-group">
								
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Company Type<span class="required">*</span></label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<select class="form-control" name="tenantcompanytype" id="tenantcompanytype">
											<option value="prilc">Private Limited Company</option>
											<option value="publc">Public Limited Company</option>
											<option value="uc">Unlimited Company</option>
											<option value="llp">Limited Liability Partnership </option>
											<option value="p">Partnership</option>
											<option value="sp">Sole Proprietorship</option>
											<option value="lo">Liaison Office / Representative Office</option>
											<option value="po">Project Office </option>
											<option value="bo">Branch Office </option>
											<option value="jvc">Joint Venture Company</option>
											<option value="sc">Subsidiary Company</option>
										</select>
									</div>
								</div>
								
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Gumasta License no <span class="required">*</span>
								</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="tenantgumastalicno" name="tenantgumastalicno"
											class="form-control col-md-12 col-xs-12" required="required">
									</div>
								</div>
							
							</div>
							</div>
							
							
							<div class="form-group">
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">First Name <span class="required">*</span></label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="tenantname" name=tenantname
											class="form-control col-md-12 col-xs-12">
									</div>
								</div>
								
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Middle Name <span class="required">*</span></label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="tenantname2" name=tenantname2
											class="form-control col-md-12 col-xs-12">
									</div>
								</div>
								
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Last Name <span class="required">*</span></label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="tenantname3" name=tenantname3
											class="form-control col-md-12 col-xs-12">
									</div>
								</div>
								
								
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Permanent Address <span class="required">*</span></label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="tenantaddress" name=tenantaddress
											class="form-control col-md-12 col-xs-12">
									</div>
								</div>
								
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Lease Type<span class="required">*</span></label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<select class="form-control" name="tenantType" id="tenantType">
											<option value="Residential">Residential</option>
											<option value="Commercial">Commercial</option>
										</select>
									</div>
								</div>
								
							</div>
							
							
							<div class="form-group">
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Contact Number <span class="required">*</span></label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="tenantcontactnumber" name=tenantcontactnumber
											class="form-control col-md-12 col-xs-12">
									</div>
								</div>
								
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Alternate Number</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="tenantaltnumber" name=tenantaltnumber
											class="form-control col-md-12 col-xs-12">
									</div>
								</div>
								
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Email</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="tenantemail" name=tenantemail
											class="form-control col-md-12 col-xs-12">
									</div>
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Aadhar Number <span class="required">*</span></label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="tenantaadharno" name=tenantaadharno
											class="form-control col-md-12 col-xs-12">
									</div>
								</div>
								
								
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Tenant Police Verification Done<span class="required">*</span></label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<select class="form-control" name="tenantPVstatus" id="tenantPVstatus">
											<option value="Yes">Yes</option>
											<option value="No">No</option>
										</select>
									</div>
								</div>
							</div>
							
							<div class="form-group">
							
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">From Date </label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="tenantfrom" name="tenantfrom"
											class="form-control col-md-12 col-xs-12 customdatepicker"
											readonly="readonly">
									</div>
								</div>
								
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">To Date </label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="tenantto" name="tenantto"
											class="form-control col-md-12 col-xs-12 customdatepicker"
											readonly="readonly">
									</div>
								</div>
							
							</div>
							
							<div class="form-group" align="center">
								<button id="removeTenant" class="btn btn-primary btn-sm" onclick="addTenentToHistory();return false;" disabled="disabled">Remove Current Tenant</button>
							</div>
							
							<div class="table-responsive col-sm-offset-2 col-md-offset-2 col-sm-8 col-md-8 col-xs-12"  >
							<h4>Lease Tenant History</h4>
							
								<table class="table table-striped jambo_table bulk_action"
									id="thetableTen">
									<thead>
										<tr class="headings"> 
											<th class="column-title">Name</th>
											<th class="column-title">Contact No</th>
											<th class="column-title">From Date</th>
											<th class="column-title">To Date</th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
							</div>
							
							</section>
							
							
							<div class="col-md-12 col-sm-12 col-xs-12">
								<br /> <h2><span class="label label-success label-md">Share Certificate</span></h2> <hr />
							</div>
							
							<div class="clearfix"></div>

							<div class="form-group">
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Share Certificate No </label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="sharecertno" name="sharecertno"
											class="form-control col-md-12 col-xs-12">
									</div>
								</div>
							</div>
							
							<hr/>
							
							<div class="form-group">
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Nominee</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="nominee1" name="nominee1"
											class="form-control col-md-12 col-xs-12">
									</div>
								</div>
							 
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Percentage</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="percent1" name="percent1"
											class="form-control col-md-12 col-xs-12">
									</div>
								</div>
								
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Relationship with Owner</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="nomineerelation" name="nomineerelation"
											class="form-control col-md-12 col-xs-12">
									</div>
								</div>
								
							</div>
							<div class="form-group">
							
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Date of Birth</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="nomineedob" name="nomineedob"
											class="form-control col-md-12 col-xs-12 customdatepicker">
									</div>
								</div>
								
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Permanent Address</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="nomineeaddress" name="nomineeaddress"
											class="form-control col-md-12 col-xs-12">
									</div>
								</div>
							</div>
							
							
							<div class="form-group" align="center">
								<button class="btn btn-primary btn-sm" onclick="addShareCertDetails();return false;">+ Add Nominee</button>
							</div>
							
							
							 <div class="table-responsive col-sm-offset-2 col-md-offset-2 col-sm-8 col-md-8 col-xs-12"  >
								<table class="table table-striped jambo_table bulk_action"
									id="thetableSC">
									<thead>
										<tr class="headings"> 
											<th class="column-title">Nominee</th>
											<th class="column-title">Percentage</th>
											<th class="column-title">Relation</th>
											<th class="column-title">DOB</th>
											<th class="column-title">Address</th>
											<th class="column-title">Action</th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
							</div>
							
							
							
							
							
							<div class="form-group" style="display: none;">
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Nominee 2</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="nominee2" name="nominee2"
											class="form-control col-md-12 col-xs-12">
									</div>
								</div>
							 
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Percentage</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="percent2" name="percent2"
											class="form-control col-md-12 col-xs-12">
									</div>
								</div>
							</div>
							
							<div class="form-group" style="display: none;">
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Nominee 3</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="nominee3" name="nominee3"
											class="form-control col-md-12 col-xs-12">
									</div>
								</div>
							 
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Percentage</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="percent3" name="percent3"
											class="form-control col-md-12 col-xs-12">
									</div>
								</div>
							</div>
							
							<div class="col-md-12 col-sm-12 col-xs-12">
								<br /><h2><span class="label label-success label-md">Parking Details</span></h2> <hr />
							</div>
							<div class="clearfix"></div>
							
							<div class="form-group">
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Vehicle Type </label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<select class="form-control" name="vehicletype" id="vehicletype">
											<option value=""> -- select --</option>
											<option value="2-Wheeler">2 Wheeler</option>
											<option value="3-Wheeler">3 Wheeler</option>
											<option value="4-Wheeler">4 Wheeler</option>
										</select>
									</div>
								</div>

								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Parking Type </label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<select class="form-control" name="parkingtype" id="parkingtype">
											<option value=""> -- select --</option>
											<option value="Slit">Slit</option>
											<option value="Open">Open</option>
										</select>
									</div>
								</div>

								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Parking Allotment No </label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="parkingallotmentno"
											name="parkingallotmentno"
											class="form-control col-md-12 col-xs-12">
									</div>
								</div>
							 
							 </div>
							 <div class="form-group">
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Vehicle No </label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="vehicleno"
											name="vehicleno"
											class="form-control col-md-12 col-xs-12">
									</div>
								</div>
							</div>
							<div class="form-group" align="center">
								<button class="btn btn-primary" onclick="addParkingDetails();return false;">+ Add Parking Detail</button>
							</div>
							
							
							 <div class="table-responsive col-sm-offset-2 col-md-offset-2 col-sm-8 col-md-8 col-xs-12"  >
								<table class="table table-striped jambo_table bulk_action"
									id="thetablePK">
									<thead>
										<tr class="headings"> 
											<th class="column-title">Vehicle&nbsp;Type</th>
											<th class="column-title">Parking&nbsp;Type</th>
											<th class="column-title">Parking&nbsp;Allotment&nbsp;No</th>
											<th class="column-title">Vehicle&nbsp;Reg&nbsp;No</th>
											<th class="column-title">Action</th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
							</div>
			
						<div class="clearfix"></div>
			
							<div class="form-group" align="center">
								<br />
								<br />
								<hr/>
								<button class="btn btn-warning" type="reset">Reset</button>
								<button class="btn btn-success" onclick="saveMemberDetails()">Save</button>
							</div>

							<div align="center">
								<c:if test="${not empty error}">
									<script>
				                $(document).ready(function(){
				                	notify('success','SUCCESS','${error}',2000);
				                });
						  </script>
									<div class="text-success">${error}</div>
								</c:if>
							</div>
							
							</div>

						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>


		<div class="col-md-12 col-sm-12 col-xs-12">
              <div class="x_panel tile">
                <div class="x_title">
                  <h2>Existing Members</h2>
                  <ul class="nav navbar-right panel_toolbox">
                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                    </li>
                  </ul>
                  <div class="clearfix"></div>
                </div>
                <div class="x_content">
                  <div class="dashboard-widget-content">
                    
               <div class="table-responsive">
				<table class="table table-striped jambo_table bulk_action"
					id="thetable">
					<thead>
						<tr class="headings">
							<th class="column-title">Sr.No</th>
							<th class="column-title">Edit</th>
							<th class="column-title">Remove</th>
							<th class="column-title">Flat&nbsp;No</th>
							<th class="column-title">Wing</th>
							<th class="column-title">Floor</th>
							<th class="column-title">Tower</th>
							<th class="column-title">Tenant&nbsp;Name</th>
							<th class="column-title">Mobile&nbsp;No</th>
							<th class="column-title">Joint&nbsp;Owners</th>
							<th class="column-title">Occupancy</th>
							<th class="column-title">Alternate&nbsp;No</th>
							<th class="column-title">Email</th>
							<th class="column-title">Aadhar&nbsp;No</th>
							<th class="column-title">Purchase&nbsp;Date</th>
							<th class="column-title">Possession&nbsp;Date</th>
							<th class="column-title">Builtup&nbsp;Area</th>
							<th class="column-title">Carpet&nbsp;Area</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
                    
                    
                    
                  </div>
                </div>
              </div>
 </div>
  

<div id="memberDetails"></div>


<script>
	function openMemberDetails(){
	    $( "#memberDetails" ).dialog( "open" );
	}
	
	$(document).ready(function(){
		$('#thetable').DataTable();
		
		$('#thetablePK').DataTable({
	        "paging":   false,
	        "ordering": false,
	        "bFilter": false,
	        "info":     false
	    });
		$('#thetableSC').DataTable({
	        "paging":   false,
	        "ordering": false,		       
	        "bFilter": false,
	        "info":     false
	    });
		
		$('#thetableTen').DataTable({
	        "paging":   false,
	        "ordering": false,		       
	        "bFilter": false,
	        "info":     false
	    });
		
		
		
		$( "#memberDetails" ).dialog({
		      autoOpen: false,
		      height: 600,
		      width: 1200,
		      modal: true,
		      title: "Hello"
		    });
	});

	
	
	
	function saveMemberDetails(){
		
		makeJoString();
		
		var userid = $('#userid').val();
		var firstName = $('#firstName').val();
		var lastName = $('#lastName').val();
		var mobileNo = $('#mobileNo').val();
		var alternateno = $('#alternateno').val();
		var email = $('#email').val();
		var aadharno = $('#aadharno').val();
		var jointowners = $('#jointowners').val();
		var purchasedate = $('#purchasedate').val();
		var possessiondate = $('#possessiondate').val();
		var occupancy = $('#occupancy').val();
		var flatno = $('#flatno').val();
		var wing = $('#wing').val();
		var tower = $('#tower').val();
		var floor = $('#floor').val();
		var builtuparea = $('#builtuparea').val();
		var carpetarea = $('#carpetarea').val();
		
		var vehicletype = $('#vehicletype').val();
		var parkingtype = $('#parkingtype').val();
		var parkingallotmentno = $('#parkingallotmentno').val();
		var vehicleno = $('#vehicleno').val();
		
		var societyid = $('#societyid').val();
		var password = $('#password').val();
		
		var tenantname = $('#tenantname').val();
		var tenantname2 = $('#tenantname2').val();
		var tenantname3 = $('#tenantname3').val();
		
		var tenantaddress = $('#tenantaddress').val();
		var tenantcontactnumber = $('#tenantcontactnumber').val();
		var tenantaltnumber = $('#tenantaltnumber').val();
		var tenantemail = $('#tenantemail').val();
		var tenantaadharno = $('#tenantaadharno').val();
		
		
		var bloodgroup = $('#bloodgroup').val();
		var sharecertno = $('#sharecertno').val();
		var nominee1 = $('#nominee1').val();
		var percent1 = $('#percent1').val();
		var nominee2 = $('#nominee2').val();
		var percent2 = $('#percent2').val();
		var nominee3 = $('#nominee3').val();
		var percent3 = $('#percent3').val();

		var middleName = $('#middleName').val();
		var tenantType = $('#tenantType').val();
		var tenantPVstatus = $('#tenantPVstatus').val();
		
		
		var tenantfrom =  $('#tenantfrom').val();
		var tenantto =  $('#tenantto').val();
		
		var randomHash = '${randomHash}';
		
		var flattype = $('#flattype').val();
		
		var membertype = $('#membertype').val();
		var companyname = $('#companyname').val();
		var companytype = $('#companytype').val();
		var gumastalicno = $('#gumastalicno').val();
		var tenantcompanyname = $('#tenantcompanyname').val();
		var tenantcompanytype = $('#tenantcompanytype').val();
		var tenantgumastalicno = $('#tenantgumastalicno').val();
		var commercialtype = $('#commercialtype').val();
		
		
		
		if(occupancy=='leased'){
			if(tenantname.length<1 || tenantaddress.length<1 || tenantcontactnumber.length<1 || tenantaadharno.length<1 ){
				alert("Tenant Details Are Mandatory");
				return false;
			}
			
			if(tenantaadharno.length!=	12){
				alert("Lease Owner Aadhar Invalid");
				return false;
			}
			
			if(tenantname2.length>0)
				tenantname = tenantname+' '+tenantname2;
			
			if(tenantname3.length>0)
				tenantname = tenantname+' '+tenantname3;
		}
			
		if(firstName.length<1 || lastName.length<1 || mobileNo.length<1 || occupancy.length<1 || flatno.length<1 || wing.length<1 || password.length<1 || societyid.length<1){
			alert("Some Mandatory Fields Are Empty");
			return false;
		}
		
		if(aadharno.length!=12){
			alert("Aadhar Invalid");
			return false;
		}
		
		if(password.length<5){
			alert("Password Should be min 5 characters");
			return false;
		}
		
		var datastr = "userid="+userid
				+"&firstName="+firstName
				+"&lastName="+lastName
				+"&middleName="+middleName
		        +"&mobileNo="+mobileNo
		        +"&alternateno="+alternateno
		        +"&email="+email
		        +"&aadharno="+aadharno
		        +"&password="+password
		        +"&jointowners="+jointowners
		        +"&occupancy="+occupancy
		        +"&flatno="+flatno
		        +"&wing="+wing
		        +"&floor="+floor
		        +"&tower="+tower
		        +"&societyid="+societyid
		        +"&builtuparea="+builtuparea
		        +"&carpetarea="+carpetarea
		        +"&vehicletype="+vehicletype
		        +"&parkingtype="+parkingtype
		        +"&parkingallotmentno="+parkingallotmentno
		        +"&tenantname="+tenantname
		        +"&tenantaddress="+tenantaddress
		        +"&tenantcontactnumber="+tenantcontactnumber
		        +"&tenantaltnumber="+tenantaltnumber
		        +"&tenantemail="+tenantemail
		        +"&tenantaadharno="+tenantaadharno
		        
		        +"&bloodgroup="+bloodgroup
		        +"&sharecertno="+sharecertno
		        +"&nominee1="+nominee1
		        +"&percent1="+percent1
		        +"&nominee2="+nominee2
		        +"&percent2="+percent2
		        +"&nominee3="+nominee3
		        +"&percent3="+percent3
		        
		        +"&tenantType="+tenantType
		        +"&tenantPVstatus="+tenantPVstatus
		        +"&vehicleno="+vehicleno
		        +"&randomHash="+randomHash;
		
				if(tenantfrom!=null && tenantfrom.length>0)
					datastr += "&tenantfrom="+tenantfrom
					
				if(tenantto!=null && tenantto.length>0)
					datastr += "&tenantto="+tenantto;
		
				if(purchasedate!=null && purchasedate.length>0)
					datastr += "&purchasedate="+purchasedate;
				
				if(possessiondate!=null && possessiondate.length>0)
					datastr += "&possessiondate="+possessiondate;
				
				if(membertype!=null && membertype.length>0)
					datastr += "&membertype="+membertype;
				
				if(flattype!=null && flattype.length>0)
					datastr += "&flattype="+flattype;
				
				
				if(membertype=='Commercial'){
					
						if(companyname!=null && companyname.length>0)
							datastr += "&companyname="+companyname;
						
						if(companytype!=null && companytype.length>0)
							datastr += "&companytype="+companytype;
						
						if(gumastalicno!=null && gumastalicno.length>0)
							datastr += "&gumastalicno="+gumastalicno;
						
						if(commercialtype!=null && commercialtype.length>0)
							datastr += "&commercialtype="+commercialtype;
						
							if(occupancy=='leased'){
								
								if(tenantcompanyname!=null && tenantcompanyname.length>0)
									datastr += "&tenantcompanyname="+tenantcompanyname;
								
								if(tenantcompanytype!=null && tenantcompanytype.length>0)
									datastr += "&tenantcompanytype="+tenantcompanytype;
								
								if(tenantgumastalicno!=null && tenantgumastalicno.length>0)
									datastr += "&tenantgumastalicno="+tenantgumastalicno;
								
							}
					
				}
				
		blockUI();
		
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/saveMemberDetails.do",
	        data : datastr,
	        success: function(response){
	        	if(response.userid>0) {
	        		getMembersForSociety();
	        		notify('success','SUCCESS','Added Successfully',2000);
	        		$('#addDocSubTypeForm').trigger("reset");
	        	}  
	        	unblockUI();
	        },
				error : function(e) {
					notify('error','ERROR','Error occured',2000);
					unblockUI();
				}
			});
		return false;
	}
	
	/* 	$('#userid').val("");
	$('#firstName').val("");
	$('#middleName').val("");
	$('#lastName').val("");
	$('#mobileNo').val("");
	$('#alternateno').val("");
	$('#email').val("");
	$('#aadharno').val("");
	$('#jointowners').val("");
	$('#password').val("");
	$('#purchasedate').val("");
	$('#possessiondate').val("");
	
	$('#flatno').val("");
	$('#wing').val("");
	$('#tower').val("");
	$('#floor').val("");
	$('#builtuparea').val("");
	$('#carpetarea').val("");
	$('#tenantname').val("");
	$('#tenantaddress').val("");
	$('#tenantcontactnumber').val("");
	$('#tenantaltnumber').val("");
	$('#tenantemail').val("");
	$('#tenantaadharno').val("");
	$('#vehicletype').val("");
	$('#parkingtype').val("");
	$('#parkingallotmentno').val("");
	
	$('#bloodgroup').val("");
	$('#sharecertno').val("");
	$('#nominee1').val("");
	$('#percent1').val("");
	$('#nominee2').val("");
	$('#percent2').val("");
	$('#nominee3').val("");
	$('#percent3').val("");
	$('#vehicleno').val("");
	
	$('#tenantfrom').val("");
	$('#tenantto').val("");
	
	
	$('#companyname').val("");
	$('#companytype').val("");
	$('#gumastalicno').val("");
	$('#tenantcompanyname').val("");
	$('#tenantcompanytype').val("");
	$('#tenantgumastalicno').val(""); */
	
	function getMembersForSociety(){
		var societyid = $('#societyid').val();
		var table = $('#thetable').DataTable();
			
		
		table .clear() .draw();
		blockUI();
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/getMembersForSociety.do",
	        data :"societyid="+societyid,
	        success: function(response){
	        	var srno=1;
	        	$.each(response, function(i, item) {
	        		
	        		var editBtn = '<a class="btn btn-default btn-sm" onclick="editUserData(\'' + item.userid + '\')"><i class="fa fa-edit"></i></a>';
	        		var delBtn = '<a class="btn btn-default btn-sm" onclick="genericRemove(\'' + item.userid + '\',\'user\',\'userid\',getMembersForSociety)"><i class="fa fa-times"></i></a>';

	        		table.row.add( [
	        			srno,
	        			editBtn,
	        			delBtn,
	        			item.flatno,
	        			item.wing,
	        			item.floor,
	        			item.tower,
	        			item.firstName + ' ' + item.middleName+ ' ' + item.lastName,
	        			item.mobileNo,
	        			item.jointowners,
	        			item.occupancy,
	        			item.alternateno,
	        			item.email,
	        			item.aadharno,
	        			new Date(item.purchasedate).toString("dd MMM yyyy"),
	        			new Date(item.possessiondate).toString("dd MMM yyyy"),
	        			item.builtuparea,
	        			item.carpetarea,
	                ] ).draw( false );
	        		srno++;
	        	 });
	        	unblockUI();
	        },
				error : function(e) {
					notify('error','ERROR','Error occured',2000);
					unblockUI();
				}
			});
	}
	
	function showhidetenant(){
		var occupancy = $('#occupancy').val();
		if(occupancy=='self')
		 $('#tenantdetails').hide('slideDown');
		else
			$('#tenantdetails').show('slideDown');
	}
	 
	function changeFlattype(){
		var flattype = $('#flattype').val();
		if(flattype=='shop')
		 $('#flatnolabel').html('Shop No');
		else
			$('#flatnolabel').html('Flat No');
	}
	
	function editUserData(userid){
		
		//editMode();
		blockUI();
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/getUserDataById.do",
	        data :"userid="+userid,
	        success: function(response){
	        	if(response.userid>0) {
	        		
	        		editMode();
	        		$('#photodiv').html("<img width='120' src='<%=request.getContextPath()%>/resources/images/spin.gif'>");
	        		getMemberPhoto(userid,'photodiv');
	        		
	        		//notify('success','SUCCESS','Added Successfully',2000);
	        		$('#userid').val(response.userid);
	        		$('#firstName').val(response.firstName);
	        		$('#middleName').val(response.middleName);
	        		$('#lastName').val(response.lastName);
	        		$('#mobileNo').val(response.mobileNo);
	        		$('#alternateno').val(response.alternateno);
	        		$('#email').val(response.email);
	        		$('#aadharno').val(response.aadharno);
	        		$('#password').val(response.password);
	        		$('#purchasedate').val(new Date(response.purchasedate).toString("dd/MM/yyyy"));
	        		$('#possessiondate').val(new Date(response.possessiondate).toString("dd/MM/yyyy"));
	        		
	        		$('#occupancy option[value="'+response.occupancy+'"]').prop("selected",true).change();

	        		$('#tenantType option[value="'+response.tenantType+'"]').prop("selected",true).change();

	        		$('#tenantPVstatus option[value="'+response.tenantPVstatus+'"]').prop("selected",true).change();
	        		
	        		$('#flatno').val(response.flatno);
	        		$('#wing').val(response.wing);
	        		$('#tower').val(response.tower);
	        		$('#floor').val(response.floor);
	        		$('#builtuparea').val(response.builtuparea);
	        		$('#carpetarea').val(response.carpetarea);
	        		
	        		if(response.tenantname!=null && response.tenantname.length>0){
	        			$('#tenantname').val(response.tenantname.split(' ')[0]);
		        		$('#tenantname2').val(response.tenantname.split(' ')[1]);
		        		$('#tenantname3').val(response.tenantname.split(' ')[2]);
		        		$('#tenantfrom').val(new Date(response.tenantfrom).toString("dd MMM yyyy"));
		        		$('#tenantto').val(new Date(response.tenantto).toString("dd MMM yyyy"));
	        		}
	        		
	        		$('#tenantaddress').val(response.tenantaddress);
	        		$('#tenantcontactnumber').val(response.tenantcontactnumber);
	        		$('#tenantaltnumber').val(response.tenantaltnumber);
	        		$('#tenantemail').val(response.tenantemail);
	        		$('#tenantaadharno').val(response.tenantaadharno);
	        		$('#vehicletype').val(response.vehicletype);
	        		$('#parkingtype').val(response.parkingtype);
	        		$('#parkingallotmentno').val(response.parkingallotmentno);
	        		$('#bloodgroup option[value="'+response.bloodgroup+'"]').prop("selected",true).change();
	        		$('#sharecertno').val(response.sharecertno);
	        		$('#nominee1').val(response.nominee1);
	        		$('#percent1').val(response.percent1);
	        		$('#nominee2').val(response.nominee2);
	        		$('#percent2').val(response.percent2);
	        		$('#nominee3').val(response.nominee3);
	        		$('#percent3').val(response.percent3);
	        		$('#vehicleno').val(response.vehicleno);

	        		
	        		
	        		$('#jointowners').val('');
	        		splitJo(response.jointowners);
	        		
	        		$('#membertype option[value="'+response.membertype+'"]').prop("selected",true).change();
	        		$('#companyname').val(response.companyname);
	        		$('#companytype option[value="'+response.companytype+'"]').prop("selected",true).change();
	        		$('#gumastalicno').val(response.gumastalicno);
	        		$('#commercialtype option[value="'+response.commercialtype+'"]').prop("selected",true).change();
	        		$('#tenantcompanyname').val(response.tenantcompanyname);
	        		$('#tenantcompanytype option[value="'+response.tenantcompanytype+'"]').prop("selected",true).change();
	        		$('#tenantgumastalicno').val(response.tenantgumastalicno);
	        		
	        		
	        		getParkingDetailsForMember();
	        		getSCDetailsForMember();
	        		getTenantHistory();
	        		changeMemberType();
	        		
	        		
	        		$('#removeTenant').removeAttr('disabled');
	        		
	        	}  
	        	unblockUI();
	        },
				error : function(e) {
					notify('error','ERROR','Error occured',2000);
					unblockUI();
				}
			});
	}
</script>

<script>
							
							var jocnt=0;
								function addJo(){
									var newinput = '<div class="form-group jodiv" id="jogrp'+jocnt+'">';
										newinput +='<label class="control-label col-md-2 col-sm-2 col-xs-12">Joint Owner : </label>';
										newinput += '<div class="col-md-2 col-sm-2 col-xs-12"><div class="col-md-12 col-sm-12 col-xs-12"><input type="text" id="jofn'+jocnt+'" name="jofn'+jocnt+'" placeholder="First Name" class="form-control col-md-12 col-xs-12"></div></div>';
										newinput += '<div class="col-md-2 col-sm-2 col-xs-12"><div class="col-md-12 col-sm-12 col-xs-12"><input type="text" id="jomn'+jocnt+'" name="jomn'+jocnt+'" placeholder="Middle Name" class="form-control col-md-12 col-xs-12"></div></div>';
										newinput += '<div class="col-md-2 col-sm-2 col-xs-12"><div class="col-md-12 col-sm-12 col-xs-12"><input type="text" id="joln'+jocnt+'" name="joln'+jocnt+'" placeholder="Last Name" class="form-control col-md-12 col-xs-12"></div></div>';
										newinput += '<button onclick="removeJo('+jocnt+');return false;" class="btn btn-danger btn-sm"><i class="fa fa-times"></i></button>';
										newinput += '</div>';
									$(newinput).appendTo('#jocontainer');
									jocnt++;
									return false;
								}
								
								function removeJo(divid){
									console.log(divid);
									$('#jogrp'+divid).remove();
								}
								
								function makeJoString(){
									
									var joStr = "";
									$(".jodiv").each(function() {
									    
									    var cnt = $(this).attr('id').replace('jogrp','');	
									    var internaljostr = '';
									    
									    internaljostr += $('#jofn'+cnt).val();
									    internaljostr += ' '+$('#jomn'+cnt).val();
									    internaljostr += ' '+$('#joln'+cnt).val();
									    
									    if(joStr.length>0)
									    	joStr += ',';
									    
									    if(internaljostr.length>0)
									   		joStr += internaljostr;
									    
									});
									
									$("#jointowners").val(joStr);
								}
								
								function splitJo(joString){
									
									$('#jocontainer').html('<div id="jocontainer"></div>');
									
									if(joString.trim().length > 0){
										
										var joArr = joString.split(',');
										console.log("joArr ::"+joArr.length);
										
										var arrind=0;
										
										for(arrind = 0; arrind < joArr.length ; arrind++){
											
											var joArrInt = joArr[arrind].split(' ');
											console.log("joArrInt ::"+joArrInt);
											var newinput = '<div class="form-group jodiv" id="jogrp'+jocnt+'">';
											newinput +='<label class="control-label col-md-2 col-sm-2 col-xs-12">Joint Owner : </label>';
											newinput += '<div class="col-md-2 col-sm-2 col-xs-12"><div class="col-md-12 col-sm-12 col-xs-12"><input type="text" id="jofn'+jocnt+'" name="jofn'+jocnt+'" placeholder="First Name" class="form-control col-md-12 col-xs-12" value="'+joArrInt[0]+'"></div></div>';
											newinput += '<div class="col-md-2 col-sm-2 col-xs-12"><div class="col-md-12 col-sm-12 col-xs-12"><input type="text" id="jomn'+jocnt+'" name="jomn'+jocnt+'" placeholder="Middle Name" class="form-control col-md-12 col-xs-12" value="'+joArrInt[1]+'"></div></div>';
											newinput += '<div class="col-md-2 col-sm-2 col-xs-12"><div class="col-md-12 col-sm-12 col-xs-12"><input type="text" id="joln'+jocnt+'" name="joln'+jocnt+'" placeholder="Last Name" class="form-control col-md-12 col-xs-12" value="'+joArrInt[2]+'"></div></div>';
											newinput += '<button onclick="removeJo('+jocnt+');return false;" class="btn btn-danger btn-sm"><i class="fa fa-times"></i></button>';
											newinput += '</div>';
											
											$(newinput).appendTo('#jocontainer');
											jocnt++;
											 
										}
									}
								
								}
								
								function getMemberPhoto(memberId,divId){
							    	console.log(memberId+"-"+divId);
							    	$.ajax({
								        type: "GET",
								        url: "<%=request.getContextPath()%>/getMemberPhotos.do",
								        data :"userid="+memberId,
								        enctype: 'multipart/form-data',
								        processData: false,
							            contentType: false 
										}).done(function(data) {
										$('#'+divId).html("");
										var img='';
										var cnt =1;
							               $.each(data, function(i, item) {
							               		img = '<img class="dp" height="150"  src="data:' + item.contenttype + ';base64,' +  item.file + '"/>';
							               });
							               $('#'+divId).html(img);
							               $(".dp").popImg();
							          }).fail(function(jqXHR, textStatus) {
							              alert('File Fetch failed ...');
							          });;
							    }
								
								
								function addParkingDetails(){
									var vehicletype = $('#vehicletype').val();
									var parkingtype = $('#parkingtype').val();
									var parkingallotmentno = $('#parkingallotmentno').val();
									var vehicleno = $('#vehicleno').val();
									var userid = $('#userid').val();
									var randomHash = '${randomHash}';
									
									if(vehicletype.trim().length < 1 || parkingtype.trim().length < 1 || parkingallotmentno.trim().length < 1 || vehicleno.trim().length < 1 ){
										alert('All fields are mandatory for parking details');
										return false;
									}else{ 
										blockUI();
										$.ajax({
									        type: "GET",
									        url: "<%=request.getContextPath()%>/saveMemberparkingDetails.do",
									        data :"randomHash="+randomHash 
									        +"&userid="+userid
									        +"&vehicletype="+vehicletype
											        +"&parkingtype="+parkingtype
											        +"&parkingallotmentno="+parkingallotmentno 
											        +"&vehicleno="+vehicleno,
									        success: function(response){
									        	//alert(response);
									        	if(response.userparkingdetailsid>0) {
									        		getParkingDetailsForMember();
									        		notify('success','SUCCESS','Added Successfully',2000);
 										        		$('#vehicletype').val("");
										        		$('#parkingtype').val("");
										        		$('#parkingallotmentno').val("");
										        		$('#vehicleno').val(""); 
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
								
								
								function getParkingDetailsForMember(){
									var randomHash = '${randomHash}';
									var userid = $('#userid').val();
									
									var table = $('#thetablePK').DataTable();
										
									
									table .clear() .draw();
									
									blockUI();
									
									$.ajax({
								        type: "GET",
								        url: "<%=request.getContextPath()%>/getParkingDetailsForMember.do",
								        data :"userid="+userid
								        		+"&randomHash="+randomHash,
								        success: function(response){
								        	var srno=1;
								        	$.each(response, function(i, item) {

								        		var delBtn = '<a class="btn btn-default btn-sm" onclick="deleteParkingData(\'' + item.userparkingdetailsid + '\')"><i class="fa fa-times"></i></a>';

								        		table.row.add( [
								        			item.vehicletype,
								        			item.parkingtype,
								        			item.parkingallotmentno,
								        			item.vehicleno,
								        			delBtn,
								                ] ).draw( false );
								        		srno++;
								        	 });
								        	unblockUI();
								        },
											error : function(e) {
												notify('error','ERROR','Error occured',2000);
												unblockUI();
											}
										});
								}
								
								
								function deleteParkingData(userparkingdetailsid){
									if(confirm('Are you Sure?')){
										if(userparkingdetailsid.length < 1){
							 			alert('Some Mandatory Fields  are Missing');
							 			return false;
							 		} else { 
							 			blockUI();
							 			$.ajax({
							 		        type: "GET",
							 		        url: "<%=request.getContextPath()%>/removeParkingData.do",
							 		       data :"userparkingdetailsid="+userparkingdetailsid,
							 		        success: function(response){
							 		        //alert()
							  		        	if(response=='success') {
							 		        		notify('success','SUCCESS','Removed Successfully',2000);
							 		        		getParkingDetailsForMember();
							 		        	}  else {
							 		        		notify('error','Failed','Failed to Remove Member',2000);
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
							 		return false;
								}
								
								
								function addShareCertDetails(){
									var nominee1 = $('#nominee1').val();
									var percent1 = $('#percent1').val();

									var nomineerelation = $('#nomineerelation').val();
									var nomineedob = $('#nomineedob').val();
									var nomineeaddress = $('#nomineeaddress').val();
									
									var randomHash = '${randomHash}';
									var sharecertno = $('#sharecertno').val();
									var userid = $('#userid').val();
									
									
									if(sharecertno.trim().length < 1 || nominee1.trim().length < 1 || percent1.trim().length < 1 
											|| nomineerelation.trim().length < 1 
											|| nomineedob.trim().length < 1 
											|| nomineeaddress.trim().length < 1 
										){
										alert('All fields are mandatory for Adding Nominee');
										return false;
									}else{ 
										blockUI();
										$.ajax({
									        type: "GET",
									        url: "<%=request.getContextPath()%>/addShareCertDetails.do",
									        data :"randomHash="+randomHash 
											        +"&userid="+userid
											        +"&nominee="+nominee1
											        +"&nomineerelation="+nomineerelation
											        +"&nomineedob="+nomineedob
											        +"&nomineeaddress="+nomineeaddress
											        +"&percent="+percent1,
									        success: function(response){
									        	//alert(response);
									        	if(response.userscnomineeid>0) {
									        		getSCDetailsForMember();
									        		notify('success','SUCCESS','Added Successfully',2000);
 										        		$('#nominee1').val("");
 										        		$('#percent1').val("");
 										        		$('#nomineerelation').val("");
 										        		$('#nomineedob').val("");
 										        		$('#nomineeaddress').val("");
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
								
								function getSCDetailsForMember(){
									var randomHash = '${randomHash}';
									var userid = $('#userid').val();
									
									var table = $('#thetableSC').DataTable();
										
									
									table .clear() .draw();
									blockUI();
									$.ajax({
								        type: "GET",
								        url: "<%=request.getContextPath()%>/getShareCertDetails.do",
								        data :"userid="+userid
								        		+"&randomHash="+randomHash,
								        success: function(response){
								        	var srno=1;
								        	$.each(response, function(i, item) {

								        		var delBtn = '<a class="btn btn-default btn-sm" onclick="deleteSCData(\'' + item.userscnomineeid + '\')"><i class="fa fa-times"></i></a>';

								        		table.row.add( [
								        			item.nominee,
								        			item.percent,
								        			item.nomineerelation,
								        			new Date(item.nomineedob).toString("dd MMM yyyy"),
								        			item.nomineeaddress,
								        			delBtn,
								                ] ).draw( false );
								        		srno++;
								        	 });
								        	unblockUI();
								        },
											error : function(e) {
												notify('error','ERROR','Error occured',2000);
												unblockUI();
											}
										});
								}
								
								
								function deleteSCData(userscnomineeid){
									if(confirm('Are you Sure?')){
										if(userscnomineeid.length < 1){
							 			alert('Some Mandatory Fields  are Missing');
							 			return false;
							 		} else { 
							 			blockUI();
							 			$.ajax({
							 		        type: "GET",
							 		        url: "<%=request.getContextPath()%>/removeShareCertDetails.do",
							 		       data :"userscnomineeid="+userscnomineeid,
							 		        success: function(response){
							 		        //alert()
							  		        	if(response=='success') {
							 		        		notify('success','SUCCESS','Removed Successfully',2000);
							 		        		getSCDetailsForMember();
							 		        	}  else {
							 		        		notify('error','Failed','Failed to Remove Member',2000);
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
							 		return false;
								}
								
								function addTenentToHistory(){
									
									var userid = $('#userid').val();
									
									if(confirm('Are you Sure?')){
									if(userid.trim().length < 1 ){
										alert('User Not Created');
										return false;
									}else{ 
										blockUI();
										$.ajax({
									        type: "GET",
									        url: "<%=request.getContextPath()%>/addTenantToHistory.do",
									        data :"userid="+userid,
									        success: function(response){
									        	//alert(response);
									        	if(response=='success') {
									        		//getSCDetailsForMember();
									        		notify('success','SUCCESS','Added Successfully',2000);
										        		$('#tenantto').val("");
										        		$('#tenantfrom').val("");
										        		$('#tenantaadharno').val("");
										        		$('#tenantemail').val("");
										        		$('#tenantaltnumber').val("");
										        		$('#tenantcontactnumber').val("");
										        		$('#tenantaddress').val("");
										        		$('#tenantname3').val("");
										        		$('#tenantname2').val("");
										        		$('#tenantname').val("");
										        		getTenantHistory();
									        	}  else {
							 		        		notify('error','Failed','Failed to Remove Tenant',2000);
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
								}
								
								
								function getTenantHistory(){
								 
									var userid = $('#userid').val();
									
									var table = $('#thetableTen').DataTable();
									
									table .clear() .draw();
									blockUI();
									$.ajax({
								        type: "GET",
								        url: "<%=request.getContextPath()%>/getTenantHistory.do",
								        data :"userid="+userid,
								        success: function(response){
								        	var srno=1;
								        	$.each(response, function(i, item) {

								        		table.row.add( [
								        			item.tenantname,
								        			item.tenantcontactnumber,
								        			new Date(item.tenantfrom).toString("dd MMM yyyy"),
								        			new Date(item.tenantto).toString("dd MMM yyyy"),
								                ] ).draw( false );
								        		srno++;
								        	 });
								        	unblockUI();
								        },
											error : function(e) {
												notify('error','ERROR','Error occured',2000);
												unblockUI();
											}
										});
								}
								
								
								function changeMemberType(){
									var membertype = $('#membertype').val();
									if(membertype=='Commercial'){
										$('#owndetailsbadge').html("Owner/ Proprietor/ Partner/ Promoters/ Director Details");
										$('#flatdetailsbadge').html("Office/ Shop/ Workshop Details ");
										$('#flatnobadge').html("Office/Shop/Workshop No ");
										$('#comdetailsfields').show();
										$('#tenantcomdetilsbadge').show();
										$('#commtypebadge').show();
										
										var $select = $('#tenantType');                        
						        	    $select.find('option').remove(); 
						        	    $select.append($("<option />").val('Commercial').text('Commercial'));
						        	    
						        	    var $select1 = $('#flattype');                        
						        	    $select1.find('option').remove(); 
						        	    $select1.append($("<option />").val('shop').text('Shop'));
						        	    $select1.append($("<option />").val('office').text('Office'));
						        	    $select1.append($("<option />").val('workshop').text('Workshop'));
						        	    
										
										
									} else {
										$('#owndetailsbadge').html("Owner Details");
										$('#flatdetailsbadge').html("Flat Details");
										$('#flatnobadge').html("Flat No");
										$('#comdetailsfields').hide();
										$('#tenantcomdetilsbadge').hide();
										$('#commtypebadge').hide();
										
										var $select = $('#tenantType');                        
						        	    $select.find('option').remove(); 
						        	    $select.append($("<option />").val('Residential').text('Residential'));
						        	    $select.append($("<option />").val('Commercial').text('Commercial'));
						        	    
						        	    
						        	    var $select1 = $('#flattype');                        
						        	    $select1.find('option').remove(); 
						        	    $select1.append($("<option />").val('1rk').text('1 RHK'));
						        	    $select1.append($("<option />").val('1bhk').text('1 BHK'));
						        	    $select1.append($("<option />").val('1.5bhk').text('1.5 BHK'));
						        	    $select1.append($("<option />").val('2bhk').text('2 BHK'));
						        	    $select1.append($("<option />").val('2.5bhk').text('2.5 BHK'));
						        	    $select1.append($("<option />").val('3bhk').text('3 BHK'));
						        	    $select1.append($("<option />").val('3.5bhk').text('3.5 BHK'));
						        	    $select1.append($("<option />").val('shop').text('Shop'));

									}
								}
							</script>
							
<style>
	.headcolor{
		color: red;
	}
</style>

