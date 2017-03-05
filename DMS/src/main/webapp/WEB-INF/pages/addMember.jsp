<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="row">
	<div class="col-md-12 col-sm-12 col-xs-12">

		<div class="dashboard_graph">

			<div class="row x_title">
				<div class="col-md-6">
					<h3>
						Add Member <small> Create Member Profile</small>
					</h3>
					
					
				</div>
				<div class="pull-right">
					<select name="societyid" id="societyid" class="form-control" onchange="getMembersForSociety()">
						<option value=""> -- select society -- </option>
						<c:forEach items="${societyList}" var="myItem" varStatus="loopStatus">
							<option value="${myItem.societyid}">${myItem.societyname}</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="row">
				<div class="col-md-12 col-sm-12 col-xs-12">
					<form id="addDocSubTypeForm" data-parsley-validate
					class="form-horizontal form-label-left" action="#"
					method="post" onsubmit="return false;">
					
					
					<div class="col-md-2 col-sm-2 col-xs-12">
						<br/>
						<strong>Owner Details</strong>
						<br/>
					</div>
					<div class="clearfix"></div>
					
					<div class="form-group">
						<label class="control-label col-md-2 col-sm-2 col-xs-12"
							for="first-name">First Name <span class="required">*</span>
						</label>
						<div class="col-md-2 col-sm-2 col-xs-12">
							<div class="col-md-12 col-sm-12 col-xs-12">
								<input type="text" id="firstName" name="firstName"  class="form-control col-md-12 col-xs-12" required="required">
							</div>
						</div>
						
						<label class="control-label col-md-2 col-sm-2 col-xs-12"
							for="first-name">Last Name <span class="required">*</span>
						</label>
						<div class="col-md-2 col-sm-2 col-xs-12">
							<div class="col-md-12 col-sm-12 col-xs-12">
								<input type="text" id="lastName" name="lastName"  class="form-control col-md-12 col-xs-12" required="required">
							</div>
						</div>
						
						<label class="control-label col-md-2 col-sm-2 col-xs-12"
							for="first-name">Mobile No <span class="required">*</span>
						</label>
						<div class="col-md-2 col-sm-2 col-xs-12">
							<div class="col-md-12 col-sm-12 col-xs-12">
								<input type="text" id="mobileNo" name="mobileNo"  class="form-control col-md-12 col-xs-12" required="required">
							</div>
						</div>
					</div>
					
					<div class="form-group">
					
						<label class="control-label col-md-2 col-sm-2 col-xs-12"
							for="first-name">Alternate Number 
						</label>
						<div class="col-md-2 col-sm-2 col-xs-12">
							<div class="col-md-12 col-sm-12 col-xs-12">
								<input type="text" id="alternateno" name="alternateno"  class="form-control col-md-12 col-xs-12">
							</div>
						</div>
						
						<label class="control-label col-md-2 col-sm-2 col-xs-12"
							for="first-name">Email 
						</label>
						<div class="col-md-2 col-sm-2 col-xs-12">
							<div class="col-md-12 col-sm-12 col-xs-12">
								<input type="text" id="email" name="email"  class="form-control col-md-12 col-xs-12">
							</div>
						</div>
						
						<label class="control-label col-md-2 col-sm-2 col-xs-12"
							for="first-name">Aadhar No. 
						</label>
						<div class="col-md-2 col-sm-2 col-xs-12">
							<div class="col-md-12 col-sm-12 col-xs-12">
								<input type="text" id="aadharno" name="aadharno"  class="form-control col-md-12 col-xs-12">
							</div>
						</div>
						
						
						
						
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-2 col-sm-2 col-xs-12"
							for="first-name">Joint Owners  
						</label>
						<div class="col-md-2 col-sm-2 col-xs-12">
							<div class="col-md-12 col-sm-12 col-xs-12">
								<input type="text" id="jointowners" name="jointowners"  class="form-control col-md-12 col-xs-12">
							</div>
						</div>
						
						<label class="control-label col-md-2 col-sm-2 col-xs-12"
							for="first-name">Password <span class="required">*</span>  
						</label>
						<div class="col-md-2 col-sm-2 col-xs-12">
							<div class="col-md-12 col-sm-12 col-xs-12">
								<input type="password" id="password" name="password"  class="form-control col-md-12 col-xs-12">
							</div>
						</div>
						
					</div>
					
					<div class="col-md-2 col-sm-2 col-xs-12">
						<br/>
						<strong>Flat Details</strong>
						<br/>
					</div>
					<div class="clearfix"></div>
					
					<div class="form-group">
					
						<label class="control-label col-md-2 col-sm-2 col-xs-12"
							for="first-name">Purchase Date
						</label>
						<div class="col-md-2 col-sm-2 col-xs-12">
							<div class="col-md-12 col-sm-12 col-xs-12">
								<input type="text" id="purchasedate" name="purchasedate"  class="form-control col-md-12 col-xs-12 customdatepicker" readonly="readonly">
							</div>
						</div>
						
						<label class="control-label col-md-2 col-sm-2 col-xs-12"
							for="first-name">Possession Date
						</label>
						<div class="col-md-2 col-sm-2 col-xs-12">
							<div class="col-md-12 col-sm-12 col-xs-12">
								<input type="text" id="possessiondate" name="possessiondate"  class="form-control col-md-12 col-xs-12 customdatepicker" readonly="readonly">
							</div>
						</div>
						
						<label class="control-label col-md-2 col-sm-2 col-xs-12"
							for="first-name">Occupancy <span class="required">*</span>
						</label>
						<div class="col-md-2 col-sm-2 col-xs-12">
							<div class="col-md-12 col-sm-12 col-xs-12">
								<select class="form-control" name="occupancy" id="occupancy" required="required">
									<option value="self">Self Occupied</option>
									<option value="leased">Leased</option>
								</select>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						
						<label class="control-label col-md-2 col-sm-2 col-xs-12"
							for="first-name">Flat No <span class="required">*</span>
						</label>
						<div class="col-md-2 col-sm-2 col-xs-12">
							<div class="col-md-12 col-sm-12 col-xs-12">
								<input type="text" id="flatno" name="flatno"  class="form-control col-md-12 col-xs-12" required="required">
							</div>
						</div>
				
						 <label class="control-label col-md-2 col-sm-2 col-xs-12"
							for="first-name">Wing <span class="required">*</span>
						</label>
						<div class="col-md-2 col-sm-2 col-xs-12">
							<div class="col-md-12 col-sm-12 col-xs-12">
								<input type="text" id="wing" name="wing"  class="form-control col-md-12 col-xs-12" required="required">
							</div>
						</div>
						
						<label class="control-label col-md-2 col-sm-2 col-xs-12"
							for="first-name">Tower 
						</label>
						<div class="col-md-2 col-sm-2 col-xs-12">
							<div class="col-md-12 col-sm-12 col-xs-12">
								<input type="text" id="tower" name="tower"  class="form-control col-md-12 col-xs-12">
							</div>
						</div>
					</div>
					<div class="form-group">	
						<label class="control-label col-md-2 col-sm-2 col-xs-12"
							for="first-name">Floor
						</label>
						<div class="col-md-2 col-sm-2 col-xs-12">
							<div class="col-md-12 col-sm-12 col-xs-12">
								<input type="text" id="floor" name="floor"  class="form-control col-md-12 col-xs-12" placeholder="">
							</div>
						</div>
						
						<label class="control-label col-md-2 col-sm-2 col-xs-12"
							for="first-name">Built-up Area 
						</label>
						<div class="col-md-2 col-sm-2 col-xs-12">
							<div class="col-md-12 col-sm-12 col-xs-12">
								<input type="text" id="builtuparea" name="builtuparea"  class="form-control col-md-12 col-xs-12" placeholder="sq. ft.">
							</div>
						</div>
						
						<label class="control-label col-md-2 col-sm-2 col-xs-12"
							for="first-name">Carpet Area 
						</label>
						<div class="col-md-2 col-sm-2 col-xs-12">
							<div class="col-md-12 col-sm-12 col-xs-12">
								<input type="text" id="carpetarea" name="carpetarea"  class="form-control col-md-12 col-xs-12" placeholder="sq. ft.">
							</div>
						</div>
						
					</div>
					
					<div class="col-md-2 col-sm-2 col-xs-12">
						<br/>
						<strong>Parking Details</strong>
						<br/>
					</div>
					<div class="clearfix"></div>
					
					<div class="form-group">
						<label class="control-label col-md-2 col-sm-2 col-xs-12"
							for="first-name">Vehicle Type
						</label>
						<div class="col-md-2 col-sm-2 col-xs-12">
							<div class="col-md-12 col-sm-12 col-xs-12">
								<input type="text" id="vehicletype" name=vehicletype  class="form-control col-md-12 col-xs-12">
							</div>
						</div>
						
						<label class="control-label col-md-2 col-sm-2 col-xs-12"
							for="first-name">Parking Type
						</label>
						<div class="col-md-2 col-sm-2 col-xs-12">
							<div class="col-md-12 col-sm-12 col-xs-12">
								<input type="text" id="parkingtype" name="parkingtype"  class="form-control col-md-12 col-xs-12">
							</div>
						</div>
						
						<label class="control-label col-md-2 col-sm-2 col-xs-12"
							for="first-name">Parking Allotment No 
						</label>
						<div class="col-md-2 col-sm-2 col-xs-12">
							<div class="col-md-12 col-sm-12 col-xs-12">
								<input type="text" id="parkingallotmentno" name="parkingallotmentno"  class="form-control col-md-12 col-xs-12">
							</div>
						</div>
					</div>
					
					<div class="form-group" align="center">
					<br/><br/>
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
					
					</form>
				</div>
			</div>
			 
			<div class="clearfix"></div>
			<div class="table-responsive">
                      <table class="table table-striped jambo_table bulk_action" id="thetable">
                        <thead>
                          <tr class="headings">
                            <th class="column-title">Sr.No</th>
                            <th class="column-title">Edit</th>
                            <th class="column-title">Delete</th>
                            <th class="column-title">Flat_No</th>
                            <th class="column-title">Wing</th>
                            <th class="column-title">Floor</th>
                            <th class="column-title">Tower</th>
                            <th class="column-title">Tenant_Name</th>
                            <th class="column-title">Mobile_No</th>
                            <th class="column-title">Joint_Owners</th>
                            <th class="column-title">Occupancy</th>
                            <th class="column-title">Alternate_No</th>
                            <th class="column-title">Email</th>
                            <th class="column-title">Aadhar_No</th>
                            <th class="column-title">Purchase_Date</th>
                            <th class="column-title">Possession_Date</th>
                            <th class="column-title">Builtup_Area</th>
                            <th class="column-title">Carpet_Area</th>
                            <th class="column-title">Vehicle_Type</th>
                            <th class="column-title">Parking_Type</th>
                            <th class="column-title">Parking_Allotment_No</th>
                          </tr>
                        </thead>
                        <tbody>
                        </tbody>
                      </table>
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
		
		$( "#memberDetails" ).dialog({
		      autoOpen: false,
		      height: 600,
		      width: 1200,
		      modal: true,
		      title: "Hello"
		    });
	});

	
	
	
	function saveMemberDetails(){
		
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

		if(firstName.length<1 || lastName.length<1 || mobileNo.length<1 || occupancy.length<1 || flatno.length<1 || wing.length<1 || password.length<1 || societyid.length<1){
			alert("Some Mandatory Fields Are Empty");
			return false;
		}
		
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/saveMemberDetails.do",
	        data :"firstName="+firstName
			        +"&lastName="+lastName
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
			        +"&parkingallotmentno="+parkingallotmentno,
	        success: function(response){
	        	if(response.userid>0) {
	        		getMembersForSociety();
	        		notify('success','SUCCESS','Added Successfully',2000);
	        	}  
	        },
				error : function(e) {
					notify('error','ERROR','Error occured',2000);
				}
			});
		return false;
	}
	
	
	function getMembersForSociety(){
		var societyid = $('#societyid').val();
		var table = $('#thetable').DataTable();
			
		table .clear() .draw();
		
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/getMembersForSociety.do",
	        data :"societyid="+societyid,
	        success: function(response){
	        	var srno=1;
	        	$.each(response, function(i, item) {
	        		table.row.add( [
	        			srno,
	        			item.userprofileid,
	        			item.userprofileid,
	        			item.flatno,
	        			item.wing,
	        			item.floor,
	        			item.tower,
	        			item.firstName + ' ' + item.lastName,
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
	                ] ).draw( false );
	        		srno++;
	        	 });
	        },
				error : function(e) {
					notify('error','ERROR','Error occured',2000);
				}
			});
	}
	
	
</script>

