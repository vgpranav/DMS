<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="col-md-12 col-sm-12 col-xs-12">
	<div class="x_panel tile ">
		<div class="x_title">
			<h2>Edit My Profile</h2>

			<div class="pull-right">
				<input type="hidden" name="societyid" id="societyid" value="${userprofile.societyid}">
					 
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
									for="first-name">First Name <span class="required">*</span>
								</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="firstName" name="firstName"
											class="form-control col-md-12 col-xs-12 toDisable" required="required">
									</div>
								</div>

								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Middle Name
								</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="middleName" name="middleName"
											class="form-control col-md-12 col-xs-12 toDisable">
									</div>
								</div>
								
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Last Name <span class="required">*</span>
								</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="lastName" name="lastName"
											class="form-control col-md-12 col-xs-12 toDisable" required="required">
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
											class="form-control col-md-12 col-xs-12 toDisable" required="required">
									</div>
								</div>
								
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Alternate Number </label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="alternateno" name="alternateno"
											class="form-control col-md-12 col-xs-12 toDisable">
									</div>
								</div>

								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Email </label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="email" name="email"
											class="form-control col-md-12 col-xs-12 toDisable">
									</div>
								</div>


							</div>

							<div class="form-group">
							
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Aadhar No. </label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="aadharno" name="aadharno"
											class="form-control col-md-12 col-xs-12 toDisable">
									</div>
								</div>
								

								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Password <span class="required">*</span>
								</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="password" id="password" name="password"
											class="form-control col-md-12 col-xs-12">
									</div>
								</div>
								
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Blood Group  
								</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="bloodgroup" name="bloodgroup"
											class="form-control col-md-12 col-xs-12 toDisable">
									</div>
								</div> 

							</div>
							
							<div class="form-group">
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Joint Owners </label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="jointowners" name="jointowners"
											class="form-control col-md-12 col-xs-12  toDisable">
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
											class="form-control col-md-12 col-xs-12 toDisable" >
									</div>
								</div>

								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Possession Date </label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="possessiondate" name="possessiondate"
											class="form-control col-md-12 col-xs-12 toDisable" >
									</div>
								</div>

								 <label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Floor </label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="floor" name="floor"
											class="form-control col-md-12 col-xs-12 toDisable" placeholder="">
									</div>
								</div>
								 
								 
							</div>

							<div class="form-group">

								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Flat No <span class="required">*</span>
								</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="flatno" name="flatno"
											class="form-control col-md-12 col-xs-12 toDisable" required="required">
									</div>
								</div>

								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Wing <span class="required">*</span>
								</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="wing" name="wing"
											class="form-control col-md-12 col-xs-12 toDisable" required="required">
									</div>
								</div>

								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Tower </label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="tower" name="tower"
											class="form-control col-md-12 col-xs-12 toDisable">
									</div>
								</div>
							</div>
							<div class="form-group">

								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Built-up Area </label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="builtuparea" name="builtuparea"
											class="form-control col-md-12 col-xs-12 toDisable"
											placeholder="sq. ft.">
									</div>
								</div>

								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Carpet Area </label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="carpetarea" name="carpetarea"
											class="form-control col-md-12 col-xs-12 toDisable"
											placeholder="sq. ft.">
									</div>
								</div>

							</div>
							
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
											class="form-control col-md-12 col-xs-12 toDisable">
									</div>
								</div>
							</div>
							
							 <div class="table-responsive col-sm-12 col-md-12 col-xs-12"  >
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
							
							<div class="col-md-12 col-sm-12 col-xs-12">
								<br /><h2><span class="label label-success label-md">Parking Details</span></h2> <hr />
							</div>
							<div class="clearfix"></div>

							<div class="table-responsive  col-sm-12 col-md-12 col-xs-12"  >
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
							 
							<div class="clearfix"></div>

							<div class="form-group" align="center">
								<br />
								<br />
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


<script>
	 
	$(document).ready(function(){
		$('#thetable').DataTable();
		
		editUserData('${userObject.userid}');
		
		$('.toDisable').attr('readonly','readonly');
		$('select.toDisable :not(:selected)').attr('disabled','disabled');
		
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
		
		getSCDetailsForMember('${userObject.userid}');
		getParkingDetailsForMember('${userObject.userid}');
		
	});


	function getParkingDetailsForMember(userid){
		var randomHash = '${randomHash}';
		 
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
	
	function getSCDetailsForMember(userid){
		var randomHash = '${randomHash}';
		 
		
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
	
	function saveMemberDetails(){
		
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
		var vehicleno = $('#vehicleno').val();
		
		
		if(occupancy=='leased'){
			if(tenantname.length<1 || tenantaddress.length<1 || tenantcontactnumber.length<1 || tenantaadharno.length<1 ){
				alert("Tenant Details Are Mandatory");
				return false;
			}
			
			if(tenantname2.length>1)
				tenantname = tenantname+' '+tenantname2;
			
			if(tenantname3.length>1)
				tenantname = tenantname+' '+tenantname3;
		}
			
		if(firstName.length<1 || lastName.length<1 || mobileNo.length<1 || occupancy.length<1 || flatno.length<1 || wing.length<1 || password.length<1 || societyid.length<1){
			alert("Some Mandatory Fields Are Empty");
			return false;
		}
		
		blockUI();
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/saveMemberDetails.do",
	        data :"userid="+userid
	        		+"&firstName="+firstName
	        		+"&lastName="+lastName
	        		+"&middleName="+middleName
			        +"&mobileNo="+mobileNo
			        +"&alternateno="+alternateno
			        +"&email="+email
			        +"&aadharno="+aadharno
			        +"&password="+password
			        +"&jointowners="+jointowners
			        +"&purchasedate="+purchasedate
			        +"&possessiondate="+possessiondate
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
			        +"&vehicleno="+vehicleno,
			        
	        success: function(response){
	        	if(response.userid>0) {
	        		getMembersForSociety();
	        		notify('success','SUCCESS','Added Successfully',2000);
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
	        		var delBtn = '<a class="btn btn-default btn-sm" onclick="deleteUserData(\'' + item.userid + '\')"><i class="fa fa-times"></i></a>';

	        		table.row.add( [
	        			srno,
	        			editBtn,
	        			/* delBtn, */
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
	
	function showhidetenant(){
		var occupancy = $('#occupancy').val();
		if(occupancy=='self')
		 $('#tenantdetails').hide('slideDown');
		else
			$('#tenantdetails').show('slideDown');
	}
	 
	function editUserData(userid){
		blockUI();
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/getUserDataById.do",
	        data :"userid="+userid,
	        success: function(response){
	        	if(response.userid>0) {
	        		//notify('success','SUCCESS','Added Successfully',2000);
	        		$('#userid').val(response.userid);
	        		$('#firstName').val(response.firstName);
	        		$('#middleName').val(response.middleName);
	        		$('#lastName').val(response.lastName);
	        		$('#mobileNo').val(response.mobileNo);
	        		$('#alternateno').val(response.alternateno);
	        		$('#email').val(response.email);
	        		$('#aadharno').val(response.aadharno);
	        		$('#jointowners').val(response.jointowners);
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
	        		}
	        		
	        		
	        		$('#tenantaddress').val(response.tenantaddress);
	        		$('#tenantcontactnumber').val(response.tenantcontactnumber);
	        		$('#tenantaltnumber').val(response.tenantaltnumber);
	        		$('#tenantemail').val(response.tenantemail);
	        		$('#tenantaadharno').val(response.tenantaadharno);
	        		$('#vehicletype').val(response.vehicletype);
	        		$('#parkingtype').val(response.parkingtype);
	        		$('#parkingallotmentno').val(response.parkingallotmentno);
	        		$('#bloodgroup').val(response.bloodgroup);
	        		$('#sharecertno').val(response.sharecertno);
	        		$('#nominee1').val(response.nominee1);
	        		$('#percent1').val(response.percent1);
	        		$('#nominee2').val(response.nominee2);
	        		$('#percent2').val(response.percent2);
	        		$('#nominee3').val(response.nominee3);
	        		$('#percent3').val(response.percent3);
	        		$('#vehicleno').val(response.vehicleno);
	        		
	        		$('.editmodeind').css({"background-color":"#ffffe0"});
	        		$('.editmodeicon').show();
	        		$('#firstName').focus();
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

<style>
	.headcolor{
		color: red;
	}
</style>

