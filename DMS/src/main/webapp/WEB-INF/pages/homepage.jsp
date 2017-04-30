<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 

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
	  			 	
	  			 	<input type="hidden" id="userid" value="${userprofile.userid}">
	  				
	  				<div class="col-md-9 col-sm-9 col-xs-12">
	  					
	  					<form id="addDocSubTypeForm" data-parsley-validate
							class="form-horizontal form-label-left" action="#" method="post"
							onsubmit="return false;">

							<div class="editmodeind">
							
							<div class="editmodeicon" style="display: none;"><h2><span class="label label-warning label-xs "><i class="fa fa-circle-o-notch fa-spin fa-sm fa-fw"></i> Edit Mode</span></h2></div> 

							<div class="col-md-12 col-sm-12 col-xs-12">
								<br /> 
								<h2><span class="label label-success label-md">Owner Details</span></h2> 
								<hr />
							</div>
							<div class="clearfix"></div>

							<input type="hidden" id="userid" name="userid" value="0">
							
							<div class="form-group">
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">First Name 
								</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="firstName" name="firstName"
											class="form-control1 col-md-12 col-xs-12" required="required">
									</div>
								</div>

								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Middle Name
								</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="middleName" name="middleName"
											class="form-control1 col-md-12 col-xs-12">
									</div>
								</div>
								
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Last Name 
								</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="lastName" name="lastName"
											class="form-control1 col-md-12 col-xs-12" required="required">
									</div>
								</div>

								
							</div>

							<div class="form-group">

								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Mobile No 
								</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="mobileNo" name="mobileNo"
											class="form-control1 col-md-12 col-xs-12" required="required">
									</div>
								</div>
								
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Alternate Number </label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="alternateno" name="alternateno"
											class="form-control1 col-md-12 col-xs-12">
									</div>
								</div>


							</div>

							<div class="form-group">
							
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Aadhar No. </label>
								<div class="col-md-4 col-sm-4 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="aadharno" name="aadharno"
											class="form-control1 col-md-12 col-xs-12">
									</div>
								</div>
								 
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Blood Group  
								</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="bloodgroup" name="bloodgroup"
											class="form-control1 col-md-12 col-xs-12">
									</div>
								</div> 

							</div>
							
							<div class="form-group">
							<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Email </label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="email" name="email"
											class="form-control1 col-md-12 col-xs-12">
									</div>
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Joint Owners </label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="jointowners" name="jointowners"
											class="form-control1 col-md-12 col-xs-12">
									</div>
								</div>
							</div>
							
							<div class="col-md-12 col-sm-12 col-xs-12">
								<br /> <h2><span class="label label-success label-md">Flat Details</span></h2> <hr />
							</div>
							<div class="clearfix"></div>

							<div class="form-group">

								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Purchase Date </label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="purchasedate" name="purchasedate"
											class="form-control1 col-md-12 col-xs-12 customdatepicker"
											readonly="readonly">
									</div>
								</div>

								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Possession Date </label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="possessiondate" name="possessiondate"
											class="form-control1 col-md-12 col-xs-12 customdatepicker"
											readonly="readonly">
									</div>
								</div>

								 <label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Floor </label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="floor" name="floor"
											class="form-control1 col-md-12 col-xs-12" placeholder="">
									</div>
								</div>
								 
								 
							</div>

							<div class="form-group">

								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Flat No 
								</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="flatno" name="flatno"
											class="form-control1 col-md-12 col-xs-12" required="required">
									</div>
								</div>

								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Wing 
								</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="wing" name="wing"
											class="form-control1 col-md-12 col-xs-12" required="required">
									</div>
								</div>

								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Tower </label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="tower" name="tower"
											class="form-control1 col-md-12 col-xs-12">
									</div>
								</div>
							</div>
							<div class="form-group">

								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Built-up Area </label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="builtuparea" name="builtuparea"
											class="form-control1 col-md-12 col-xs-12"
											placeholder="sq. ft.">
									</div>
								</div>

								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Carpet Area </label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="carpetarea" name="carpetarea"
											class="form-control1 col-md-12 col-xs-12"
											placeholder="sq. ft.">
									</div>
								</div>

							</div>
							
							<div class="col-md-12 col-sm-12 col-xs-12">
								<br /> <h2><span class="label label-success label-md">Occupancy </span></h2> <hr />
							</div>
							<div class="form-group">
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Occupancy 
								</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<select class="form-control1" name="occupancy" id="occupancy"
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
							<div class="form-group">
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">First Name </label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="tenantname" name=tenantname
											class="form-control1 col-md-12 col-xs-12">
									</div>
								</div>
								
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Middle Name </label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="tenantname2" name=tenantname2
											class="form-control1 col-md-12 col-xs-12">
									</div>
								</div>
								
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Last Name </label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="tenantname3" name=tenantname3
											class="form-control1 col-md-12 col-xs-12">
									</div>
								</div>
								
								
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Permanent Address </label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="tenantaddress" name=tenantaddress
											class="form-control1 col-md-12 col-xs-12">
									</div>
								</div>
								
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Lease Type</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<select class="form-control1" name="tenantType" id="tenantType">
											<option value="Residential">Residential</option>
											<option value="Commercial">Commercial</option>
										</select>
									</div>
								</div>
								
							</div>
							
							
							<div class="form-group">
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Contact Number </label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="tenantcontactnumber" name=tenantcontactnumber
											class="form-control1 col-md-12 col-xs-12">
									</div>
								</div>
								
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Alternate Number</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="tenantaltnumber" name=tenantaltnumber
											class="form-control1 col-md-12 col-xs-12">
									</div>
								</div>
								
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Email</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="tenantemail" name=tenantemail
											class="form-control1 col-md-12 col-xs-12">
									</div>
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Aadhar Number </label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="tenantaadharno" name=tenantaadharno
											class="form-control1 col-md-12 col-xs-12">
									</div>
								</div>
								
								
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Tenant Police Verification Done</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<select class="form-control1" name="tenantPVstatus" id="tenantPVstatus">
											<option value="Yes">Yes</option>
											<option value="No">No</option>
										</select>
									</div>
								</div>
							</div>
							</section>
							
							 
							
							 
							</div>

						</form>
	  					
	  						  					
	  					<div class="col-md-12 col-sm-12 col-xs-12">
								<br /> <h2><span class="label label-success label-md">Share Certificate Details</span></h2> <hr />
						</div>
						
						<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="first-name">Share Certificate No </label>
								<div class="col-md-3 col-sm-3 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="sharecertno" name="sharecertno"
											class="form-control1 col-md-12 col-xs-12">
									</div>
								</div>
							</div>
							
	  					<div class="col-md-12 col-sm-12 col-xs-12">
	  					 <div class="table-responsive  col-sm-12 col-md-12 col-xs-12"  >
								<table class="table table-striped jambo_table bulk_action"
									id="thetableSC">
									<thead>
										<tr class="headings"> 
											<th class="column-title">Nominee</th>
											<th class="column-title">Percentage</th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
							</div>
	  					</div>
	  					<br/>
	  					<hr/>
	  					
	  					<div class="col-md-12 col-sm-12 col-xs-12">
								<br /> <h2><span class="label label-success label-md">Parking Details</span></h2> <hr />
						</div>
	  					<div class="col-md-12 col-sm-12 col-xs-12">
	  						 <div class="table-responsive   col-sm-12 col-md-12 col-xs-12"  >
								<table class="table table-striped jambo_table bulk_action"
									id="thetablePK">
									<thead>
										<tr class="headings"> 
											<th class="column-title">Vehicle&nbsp;Type</th>
											<th class="column-title">Parking&nbsp;Type</th>
											<th class="column-title">Parking&nbsp;Allotment&nbsp;No</th>
											<th class="column-title">Vehicle&nbsp;Reg&nbsp;No</th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
							</div>
	  				  </div>
	  					
	  				</div>
				</div>
	
							
				
				<div class="row" style="margin-top:20px !important">
				<br>	<br>	<br>	
				<hr/>
					<div class="col-md-4 col-sm-4 col-xs-12">
						<h2><a href="displaySelfSociety.do">Self Society Details</a></h2>
						<br>
					</div>
					
					<div class="col-md-4 col-sm-4 col-xs-12">
						<h2><a href="viewNoticeboard.do?societyid=${userprofile.societyid}">Society Notice Board</a></h2>
						<br>
					</div>
					
					<c:forEach items="${docSubType}" var="myItem" varStatus="loopStatus">
						<h2>
							<a onClick="displayDocumentPage('${myItem.docsubtypeid}','${userprofile.userid}','${myItem.confFlag}');" >
								${myItem.docsubtypename}
								<c:if test="${myItem.confFlag==1}">
									<i class="fa fa-lock text-warning"></i>						
								</c:if>
							</a>
						</h2>
						<br>
					</c:forEach>
					
				</div>
			</div>
		</div>
	</div>
	</div>
	
	
	<div id="confOTPDialog">
		<form method="post" action="#">
		<br>
              <div>
                <input type="text" name="mobileNoOTP" id="mobileNoOTP" class="form-control1" placeholder="Registered 10 Digit Mobile Number"/>
              </div>
              <div align="center">
              <button class="btn btn-warning" onclick="generateOTPInit();return false;" style="margin-top: 10px;" id="otpbtn">Send OTP</button>
              </div>
              <hr/>
              <div>
                <input type="password" name ="otp" id ="otp" class="form-control1" autocomplete="off" placeholder="Enter OTP Here" />
              </div>
              <div align="center">
                <button class="btn btn-success" style="margin-top: 10px;" onclick="validateOTPForDocAccess();return false;">Validate</button>
              </div>
              <input type="hidden" id="hash">
 			</form>
	</div>
<script>
	
	$(document).ready(function(){
		$( "#confOTPDialog" ).dialog({
			  autoOpen: false,
			  modal: true,
			  title: "Please Authenticate yourself",
			  width: 270,
			  height: 300
		});
		
			
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
			
			getParkingDetailsForMember();
   		 	getSCDetailsForMember();
			editUserData();
	});
	
	function showhidetenant(){
		var occupancy = $('#occupancy').val();
		if(occupancy=='self')
		 $('#tenantdetails').hide('slideDown');
		else
			$('#tenantdetails').show('slideDown');
	}
	
	function displayDocumentPage(docId,userId,confFlag){
		
		var URL = 'displayDocument.do?doctypeid='+docId+'&userid='+userId;

		if(confFlag=='1'){
			$( "#hash" ).val(URL);
			$( "#confOTPDialog" ).dialog( "open" );
		}
		else {
			 openURL(URL);
		}
	 }
		
	function openURL(URL){
		var win = window.open(URL, '_blank');
		if (win) {
		    win.focus();
		} else {
		    alert('Please allow popups for this website');
		}
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
		$('#otp').val('');
		
		$.ajax({
		        type: "GET",
		        url: "<%=request.getContextPath()%>/generateAndSendOTP.do",
		       data :"mobileNo="+mobileNo,
		        success: function(response){
		        //alert()
		        	if(response=='success') {
		        		notify('success','OTP SENT','You Will Receive OTP Shortly',2000);
		        	}  else {
		        		notify('error','FAILED','Invalid Mobile Number',2000);
		        		clearInterval(interval);
				    	$('#otpbtn').removeAttr('disabled').html('Send OTP');
		        	}
		        },
					error : function(e) {
						notify('error','ERROR','Error occured',2000);
					}
				});
	}
	
	function validateOTPForDocAccess(){
		var mobileNo = $('#mobileNoOTP').val();
		var otp = $('#otp').val();
		
		if(otp.length>0){
					$.ajax({
				        type: "GET",
				        url: "<%=request.getContextPath()%>/validateOTPForDocAccess.do",
				        data :"mobileNo="+mobileNo
			        	+"&otp="+otp,
				        success: function(response){
				        	if(response=='success') {
				        		notify('success','AUTHENTICATED','You have been authenticated',2000);
				        		var URL1 = $( "#hash" ).val();
				        		openURL(URL1);
				        		$( "#confOTPDialog" ).dialog( "close" );
				        	}  else {
				        		notify('error','FAILED','Invalid OTP or Mobile Number',2000);
						    	$('#otpbtn').removeAttr('disabled').html('Send OTP');
				        	}
				        },
							error : function(e) {
								notify('error','ERROR','Error occured',2000);
							}
						});
		}else{
			alert('Please Enter OTP');
		}
		
	}
	
	
	function getParkingDetailsForMember(){
		var randomHash = '${randomHash}';
		var userid = $('#userid').val();
		
		var table = $('#thetablePK').DataTable();
			
		
		table .clear() .draw();
		
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
	        			 
	                ] ).draw( false );
	        		srno++;
	        	 });
	        },
				error : function(e) {
					notify('error','ERROR','Error occured',2000);
				}
			});
	}
	
	
	function getSCDetailsForMember(){
		var randomHash = '${randomHash}';
		var userid = $('#userid').val();
		
		var table = $('#thetableSC').DataTable();
			
		
		table .clear() .draw();
		
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
	        			 
	                ] ).draw( false );
	        		srno++;
	        	 });
	        },
				error : function(e) {
					notify('error','ERROR','Error occured',2000);
				}
			});
	}
	
	
function editUserData(){
		
		var userid = $('#userid').val();
		
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/getUserDataById.do",
	        data :"userid="+userid,
	        success: function(response){
	        	if(response.userid>0) {
 
	        		$('#firstName').val(response.firstName);
	        		$('#middleName').val(response.middleName);
	        		$('#lastName').val(response.lastName);
	        		$('#mobileNo').val(response.mobileNo);
	        		$('#alternateno').val(response.alternateno);
	        		$('#email').val(response.email);
	        		$('#aadharno').val(response.aadharno);
	        		$('#jointowners').val(response.jointowners);
	        		$('#password').val(response.password);
	        		$('#purchasedate').val(new Date(response.purchasedate).toString("yyyy/MM/dd"));
	        		$('#possessiondate').val(new Date(response.possessiondate).toString("yyyy/MM/dd"));
	        		
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
		        		
		        		$('#tenantaddress').val(response.tenantaddress);
		        		$('#tenantcontactnumber').val(response.tenantcontactnumber);
		        		$('#tenantaltnumber').val(response.tenantaltnumber);
		        		$('#tenantemail').val(response.tenantemail);
		        		$('#tenantaadharno').val(response.tenantaadharno);
	        		}
	        		
	        		$('#vehicletype').val(response.vehicletype);
	        		$('#parkingtype').val(response.parkingtype);
	        		$('#parkingallotmentno').val(response.parkingallotmentno);
	        		$('#bloodgroup').val(response.bloodgroup);
	        		$('#sharecertno').val(response.sharecertno);
	        		 
	        		$('#occupancy').prop('disabled', 'disabled');
	        	}  
	        },
				error : function(e) {
					notify('error','ERROR','Error occured',2000);
				}
			});
	}
</script>


<c:if test="${newNoticeAdded==true}">
	<script>
	$(document).ready(function(){
		console.log($.cookie('edocAlertShown'));
		if($.cookie('edocAlertShown') == null){
 			 notify('success','Alert','New Document added to Notice Board',8000);
			 var date = new Date();
			 var minutes = 60*24;
			 date.setTime(date.getTime() + (minutes * 60 * 1000));
			 $.cookie("edocAlertShown", "true", { expires: date });
		}
		
	});
	</script>
</c:if>



<style>
	.form-control1{
		border: none;
		padding-top: 7px;
	}
</style>