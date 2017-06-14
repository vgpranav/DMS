<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 

<div class="col-md-12 col-sm-12 col-xs-12">
	<div class="x_panel tile">
		<div class="x_title">
			<h2>Tenant Details</h2>
			<ul class="nav navbar-right panel_toolbox">
				<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
				</li>
			</ul>
			<div class="clearfix"></div>
		</div>
		<div class="x_content">
			<div class="dashboard-widget-content">
				<div class="row">
	  			 	
	  			 	<input type="hidden" id="userid" value="${userprofile.userid}">
	  				
	  				<div class="col-md-9 col-sm-9 col-xs-12">
	  				
	  						<div class="form-group">
								<div class="col-md-3 col-sm-3 col-xs-12">
									<sup class="text-muted">Member Type</sup>
									<br><span class="bigger" id="membertype"></span>
								</div>
							</div>
	  			 
							<div class="col-md-12 col-sm-12 col-xs-12">
								<h2><span class="label label-success label-md" id="owndetailsbadge">Owner Details</span></h2> 
								<hr />
							</div>
							
							<div class="clearfix"></div>

							<input type="hidden" id="userid" name="userid" value="0">
							
							<div class="form-group">
								<div class="col-md-3 col-sm-3 col-xs-12">
									<sup class="text-muted">First Name</sup>
									<br><span class="bigger" id="firstName"></span>
								</div>
								<div class="col-md-3 col-sm-3 col-xs-12">
									 <sup class="text-muted">Middle Name</sup>
									 <br><span class="bigger" id="middleName"></span>
								</div>
								<div class="col-md-3 col-sm-3 col-xs-12">
								 	 <sup class="text-muted">Last Name</sup>
									 <br><span class="bigger" id="lastName"></span>
								</div>

								
							</div>
							
							<div class="clearfix"></div>
							
							<div class="form-group">
							<br>
								<label class="control-label col-md-2 col-sm-2 col-xs-12" for="first-name" style="padding-left:35px !important; ">Joint Owners</label>
								<div id="joCont">
								</div>
							</div>
							
							<div class="clearfix"></div>

							<div class="form-group">
								<div class="col-md-3 col-sm-3 col-xs-12">
								 	 <sup class="text-muted">Mobile No</sup>
									 <br><span class="bigger" id="mobileNo"></span>
								</div>
								
								<div class="col-md-3 col-sm-3 col-xs-12">
								 	 <sup class="text-muted">Alternate Number</sup>
									 <br><span class="bigger" id="alternateno"></span>
								</div>
								
								<div class="col-md-3 col-sm-3 col-xs-12">
								 	 <sup class="text-muted">Email</sup>
									 <br><span class="bigger" id="email"></span>
								</div>
							</div>
							
							<div class="clearfix"></div>
							
							<div class="form-group">
								<div class="col-md-3 col-sm-3 col-xs-12">
								 	 <sup class="text-muted">Aadhar No.</sup>
									 <br><span class="bigger" id="aadharno"></span>
								</div>
								
								<div class="col-md-3 col-sm-3 col-xs-12">
								 	 <sup class="text-muted">Blood Group</sup>
									 <br><span class="bigger" id="bloodgroup"></span>
								</div>
							</div>
							
							<div class="form-group" id="compdetails" style="display: none;">
								<div class="col-md-3 col-sm-3 col-xs-12">
									<sup class="text-muted">Company Name</sup>
									<br><span class="bigger" id="companyname"></span>
								</div>
								<div class="col-md-3 col-sm-3 col-xs-12">
									 <sup class="text-muted">Company Type</sup>
									 <br><span class="bigger" id="companytype"></span>
								</div>
								<div class="col-md-3 col-sm-3 col-xs-12">
								 	 <sup class="text-muted">Gumasta Lic. No.</sup>
									 <br><span class="bigger" id="gumastalicno"></span>
								</div>

								
							</div>
				 </div>
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
	  			</div>
	  			
	  			<div class="row">
	  			<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="col-md-12 col-sm-12 col-xs-12">
								<br /> <h2><span class="label label-success label-md" id="flatdetailsbadge">Flat Details</span></h2> <hr />
							</div>
							<div class="clearfix"></div>

							<div class="form-group">

								<div class="col-md-2 col-sm-2 col-xs-12">
								 	 <sup class="text-muted">Purchase Date</sup>
									 <br><span class="bigger" id="purchasedate"></span>
								</div>
								
								<div class="col-md-2 col-sm-2 col-xs-12">
								 	 <sup class="text-muted">Possession Date</sup>
									 <br><span class="bigger" id="possessiondate"></span>
								</div>
								
								<div class="col-md-2 col-sm-2 col-xs-12">
								 	 <sup class="text-muted">Built-up Area</sup>
									 <br><span class="bigger" id="builtuparea"></span>
								</div>
								
								<div class="col-md-2 col-sm-2 col-xs-12">
								 	 <sup class="text-muted">Carpet Area</sup>
									 <br><span class="bigger" id="carpetarea"></span>
								</div>
								
								<div class="col-md-2 col-sm-2 col-xs-12">
								 	 <sup class="text-muted">Flat Type</sup>
									 <br><span class="bigger" id="flattype"></span>
								</div>
								
							</div>

							<div class="clearfix"></div>

							<div class="form-group">

								<div class="col-md-2 col-sm-2 col-xs-12">
								 	 <sup class="text-muted">Flat No</sup>
									 <br><span class="bigger" id="flatno"></span>
								</div>
								<div class="col-md-2 col-sm-2 col-xs-12">
								 	 <sup class="text-muted">Wing</sup>
									 <br><span class="bigger" id="wing"></span>
								</div>
								<div class="col-md-2 col-sm-2 col-xs-12">
								 	 <sup class="text-muted">Floor</sup>
									 <br><span class="bigger" id="floor"></span>
								</div>
								<div class="col-md-2 col-sm-2 col-xs-12">
								 	 <sup class="text-muted">Tower</sup>
									 <br><span class="bigger" id="tower"></span>
								</div>
								
							</div>
							 
							
							<div class="col-md-12 col-sm-12 col-xs-12">
								<br /> <h2><span class="label label-success label-md">Occupancy </span></h2> <hr />
							</div>
							<div class="form-group">
							
								<div class="col-md-3 col-sm-3 col-xs-12">
								 	 <sup class="text-muted">Occupancy</sup>
									 <br><span class="bigger" id="occupancy"></span>
								</div>
								
							</div>
							
							
							<section id="tenantdetails" style="display: none;">
							<div class="col-md-12 col-sm-12 col-xs-12">
								<br /> <h2><span class="label label-success label-md">Lease Tenant Details</span></h2> <hr />
							</div>
							<div class="clearfix"></div>
							<div class="form-group">
							
								<div class="col-md-3 col-sm-3 col-xs-12">
								 	 <sup class="text-muted">First Name</sup>
									 <br><span class="bigger" id="tenantname"></span>
								</div>
								
								<div class="col-md-3 col-sm-3 col-xs-12">
								 	 <sup class="text-muted">Middle Name</sup>
									 <br><span class="bigger" id="tenantname2"></span>
								</div>
								
								<div class="col-md-3 col-sm-3 col-xs-12">
								 	 <sup class="text-muted">Last Name</sup>
									 <br><span class="bigger" id="tenantname3"></span>
								</div>
								
							</div>
							<div class="clearfix"></div>
							<div class="form-group">
								<div class="col-md-3 col-sm-3 col-xs-12">
								 	 <sup class="text-muted">Contact Number</sup>
									 <br><span class="bigger" id="tenantcontactnumber"></span>
								</div>
								
								<div class="col-md-3 col-sm-3 col-xs-12">
								 	 <sup class="text-muted">Alternate Number</sup>
									 <br><span class="bigger" id="tenantaltnumber"></span>
								</div>
								
								<div class="col-md-3 col-sm-3 col-xs-12">
								 	 <sup class="text-muted">Email</sup>
									 <br><span class="bigger" id="tenantemail"></span>
								</div>
							</div>
							
							<div class="form-group">
							
								<div class="col-md-12 col-sm-12 col-xs-12">
								 	 <sup class="text-muted">Permanent Address</sup>
									 <br><span class="bigger" id="tenantaddress"></span>
								</div>
								
							   <div class="col-md-12 col-sm-12 col-xs-12">
								 	 <sup class="text-muted">Lease Type</sup>
									 <br><span class="bigger" id="tenantType"></span>
								</div>
								
							</div>
							
							
							<div class="form-group">
								 
								 <div class="col-md-3 col-sm-3 col-xs-12">
								 	 <sup class="text-muted">Aadhar Number</sup>
									 <br><span class="bigger" id="tenantaadharno"></span>
								</div>
								
								<div class="col-md-3 col-sm-3 col-xs-12">
								 	 <sup class="text-muted">Tenant Police Verification Done</sup>
									 <br><span class="bigger" id="tenantPVstatus"></span>
								</div>
								
							</div>
							
							<div class="form-group" id="tenantComDetails" style="display: none;">
							
								<div class="col-md-12 col-sm-12 col-xs-12">
								 	 <sup class="text-muted">Company Name</sup>
									 <br><span class="bigger" id="tenantcompanyname"></span>
								</div>
								
							   <div class="col-md-6 col-sm-12 col-xs-12">
								 	 <sup class="text-muted">Company Type</sup>
									 <br><span class="bigger" id="tenantcompanytype"></span>
								</div>
								
								 <div class="col-md-6 col-sm-12 col-xs-12">
								 	 <sup class="text-muted">Gumasta Lic No.</sup>
									 <br><span class="bigger" id="tenantgumastalicno"></span>
								</div>
								
							</div>
							
							<div class="form-group">
								<div class="col-md-4 col-sm-4 col-xs-12">
									<sup class="text-muted">From Date</sup>
									 <br><span class="bigger" id="tenantfrom"></span>
								</div>
								
								<div class="col-md-4 col-sm-4 col-xs-12">
									<sup class="text-muted">To Date</sup>
									 <br><span class="bigger" id="tenantto"></span>
								</div>
							</div>
							
							<div class="table-responsive col-sm-12 col-md-12 col-xs-12"  >
							<hr/>
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
							
							 
							
							 
							</div>

	  						  					
	  					<div class="col-md-12 col-sm-12 col-xs-12">
								<br /> <h2><span class="label label-success label-md">Share Certificate Details</span></h2> <hr />
						</div>
						
						<div class="form-group">
							<div class="col-md-3 col-sm-3 col-xs-12">
								 	 <sup class="text-muted">Share Certificate No</sup>
									 <br><span class="bigger" id="sharecertno"></span>
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
											<th class="column-title">Relation</th>
											<th class="column-title">DOB</th>
											<th class="column-title">Address</th>
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
						<h2>
							<a href="viewNoticeboard.do?societyid=${userprofile.societyid}">
								Society Notice Board
								<c:if test="${newNoticeAdded==true}">
									<span class="badge newNoticeBadge success">New</span>
								</c:if>	
							</a>
						</h2>
						<br>
					</div>
					
					<div class="col-md-4 col-sm-4 col-xs-12">
						<h2>
							<a href="viewSocietyPolicy.do?societyid=${userprofile.societyid}">
								Society Policies/Amenities
							</a>
						</h2>
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
	
	
	<div id="confOTPDialog">
		<form method="post" action="#">
		<br>
              <div>
                <input type="text" name="mobileNoOTP" id="mobileNoOTP" class="form-control" placeholder="Registered 10 Digit Mobile Number"/>
              </div>
              <div align="center">
              <button class="btn btn-warning" onclick="generateOTPInit();return false;" style="margin-top: 10px;" id="otpbtn">Send OTP</button>
              </div>
              <hr/>
              <div>
                <input type="password" name ="otp" id ="otp" class="form-control" autocomplete="off" placeholder="Enter OTP Here" />
              </div>
              <div align="center">
                <button class="btn btn-success" style="margin-top: 10px;" onclick="validateOTPForDocAccess();return false;">Validate</button>
              </div>
              <input type="hidden" id="hash">
 			</form>
	</div>
<script>
	
	$(document).ready(function(){
		
		showhidetenant();
		
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
			getTenantHistory();
	});
	
	function showhidetenant(occ){
		if(occ=='self')
		 $('#tenantdetails').hide('slideDown');
		else
			$('#tenantdetails').show('slideDown');
	}
	
	function displayDocumentPage(docId,userId,confFlag){
		
		var URL = 'displayDocument.do?doctypeid='+docId+'&userid='+userId;

		if(confFlag=='1'){
			$( "#hash" ).val(URL);
			$( "#confOTPDialog" ).dialog( "open" );
			$( "#mobileNoOTP" ).val("");
			$( "#otp" ).val("");
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
		 blockUI();
		$.ajax({
		        type: "GET",
		        url: "<%=request.getContextPath()%>/generateAndSendOTP.do",
		        data :"mobileNo="+mobileNo
	       				+"&otpType=confidential",
		        success: function(response){
		        //alert()
		        	if(response=='success') {
		        		notify('success','OTP SENT','You Will Receive OTP Shortly',2000);
		        	}  else if(response=='unauthorized') {
		        		notify('error','UNAUTHORIZED','You do not have access to this document',2000);
		        		clearInterval(interval);
				    	$('#otpbtn').removeAttr('disabled').html('Send OTP');
		        	} else {
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
	
	function validateOTPForDocAccess(){
		var mobileNo = $('#mobileNoOTP').val();
		var otp = $('#otp').val();
		
		if(otp.length>0){
			 blockUI();
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
				        	 unblockUI();
				        },
							error : function(e) {
								notify('error','ERROR','Error occured',2000);
								 unblockUI();
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
	
	
function editUserData(){
		
		var userid = $('#userid').val();
		 blockUI();
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/getUserDataById.do",
	        data :"userid="+userid,
	        success: function(response){
	        	if(response.userid>0) {
	        		
	        		var comptypes = [];
        			comptypes["prilc"] = "Private Limited Company";
        			comptypes["publc"] = "Public Limited Company";
        			comptypes["uc"] = "Unlimited Company";
        			comptypes["llp"] = "Limited Liability Partnership";
        			comptypes["p"] = "Partnership";
        			comptypes["sp"] = "Sole Proprietorship";
        			comptypes["lo"] = "Liaison Office / Representative Office";
        			comptypes["po"] = "Project Office";
        			comptypes["bo"] = "Branch Office";
        			comptypes["jvc"] = "Joint Venture Company";
        			comptypes["sc"] = "Subsidiary Company";
 
	        		$('#firstName').html(response.firstName);
	        		$('#middleName').html(response.middleName);
	        		$('#lastName').html(response.lastName);
	        		$('#mobileNo').html(response.mobileNo);
	        		$('#alternateno').html(response.alternateno);
	        		$('#email').html(response.email);
	        		$('#aadharno').html(response.aadharno);
	        		
	        		$('#password').html(response.password);
	        		$('#purchasedate').html	(new Date(response.purchasedate).toString("dd/MM/yyyy"));
	        		$('#possessiondate').html(new Date(response.possessiondate).toString("dd/MM/yyyy"));
	        		
	        		$('#occupancy').html(response.occupancy.toUpperCase());
	        		showhidetenant(response.occupancy);
	        		
	        		$('#flatno').html(response.flatno);
	        		$('#wing').html(response.wing);
	        		$('#tower').html(response.tower);
	        		$('#floor').html(response.floor);
	        		$('#builtuparea').html(response.builtuparea);
	        		$('#carpetarea').html(response.carpetarea);
	        		if(response.tenantname!=null && response.tenantname.length>0){
		        		$('#tenantPVstatus').html(response.tenantPVstatus.toUpperCase());
	        			$('#tenantname').html(response.tenantname.split(' ')[0]);
		        		$('#tenantname2').html(response.tenantname.split(' ')[1]);
		        		$('#tenantname3').html(response.tenantname.split(' ')[2]);
		        		$('#tenantaddress').html(response.tenantaddress);
		        		$('#tenantcontactnumber').html(response.tenantcontactnumber);
		        		$('#tenantaltnumber').html(response.tenantaltnumber);
		        		$('#tenantemail').html(response.tenantemail);
		        		$('#tenantaadharno').html(response.tenantaadharno);
		        		$('#tenantType').html(response.tenantType);
		        		
		        		if(response.tenantfrom!="")
		        		    $('#tenantfrom').html(new Date(response.tenantfrom).toString("dd MMM yyyy"));
		        		if(response.tenantto!="")
		        			$('#tenantto').html(new Date(response.tenantto).toString("dd MMM yyyy"));
	        		}
	        		
	        		$('#vehicletype').val(response.vehicletype);
	        		$('#parkingtype').val(response.parkingtype);
	        		$('#parkingallotmentno').val(response.parkingallotmentno);
	        		
	        		$('#bloodgroup').html(response.bloodgroup.toUpperCase().replace('N',' -ve').replace('P',' +ve'));
	        		$('#sharecertno').html(response.sharecertno);
	        		
	        		$('#membertype').html(response.membertype);
	        		$('#flattype').html(response.flattype.toUpperCase());
	        		 
	        		if(response.membertype=='Commercial'){

	        			$('#compdetails').show();
	        			$('#tenantComDetails').show();
	        			$('#companyname').html(response.companyname);
	        			$('#gumastalicno').html(response.gumastalicno);
	        			$('#companytype').html(comptypes[response.companytype]);

	        			$('#tenantcompanyname').html(response.tenantcompanyname);
	        			$('#tenantgumastalicno').html(response.tenantgumastalicno);
	        			$('#tenantcompanytype').html(comptypes[response.tenantcompanytype]);
	        		
	        		}
	        		
	        		if(response.membertype=='Commercial'){
						$('#owndetailsbadge').html("Owner/ Proprietor/ Partner/ Promoters/ Director Details");
						$('#flatdetailsbadge').html("Office/ Shop/ Workshop Details ");
						
					} else {
						$('#owndetailsbadge').html("Owner Details");
						$('#flatdetailsbadge').html("Flat Details");
					}
	        		
	        		var strJo='';
	        		var jointowners = response.jointowners.split(',');
	        		$(jointowners).each(function( index ) {
	        			  var jo1 =  jointowners[index].split(' ');
	        			  
	        			 	  strJo += '<div class="clearfix"></div>';
	        			 	 strJo += '<div class="col-md-3 col-sm-3 col-xs-12">';
		        			  strJo += '<sup>First Name</sup>';
		        			  strJo += '<br>';
		        			  strJo += '<span class="bigger">'+jo1[0]+'</span>';
		        			  strJo += '</div>';
		        			  strJo += '<div class="col-md-3 col-sm-3 col-xs-12">';
		        			  strJo += '<sup>Middle Name</sup>';
		        			  strJo += '<br>';
		        			  strJo += '<span class="bigger">'+jo1[1]+'</span>';
		        			  strJo += '</div>';
		        			  strJo += '<div class="col-md-3 col-sm-3 col-xs-12">';
		        			  strJo += '<sup>Last Name</sup>';
		        			  strJo += '<br>';
		        			  strJo += '<span class="bigger">'+jo1[2]+'</span>';
		        			  strJo += '</div>';
	        		});
	        		$('#joCont').html(strJo);
	        	}  
	        	 unblockUI();
	        },
				error : function(e) {
					notify('error','ERROR','Error occured',2000);
					 unblockUI();
				}
			});
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
		}else{
			$('.newNoticeBadge').hide();
		}
		
	});
	</script>
</c:if>



<style>
	.form-control1{
		border: none;
		padding-top: 7px;
	}
	
	.bigger{
		font-size: medium;
		margin-left: 38px !important;
		font-style: italic;
		font-weight: bolder;
	}
	
	sup {
		top:0.5em !important;
		margin-left: 35px !important;
	}
	
	.form-group{
		margin-bottom: 1px !important;
	}
</style>