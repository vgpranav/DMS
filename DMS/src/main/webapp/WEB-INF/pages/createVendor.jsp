<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="col-md-12 col-sm-12 col-xs-12">
              <div class="x_panel tile">
                <div class="x_title">
                  <h2>Create Vendor</h2>
                  <div class="pull-right">
					<select name="societyid" id="societyid" class="form-control" onchange="getVendorsBySocId()">
						<option value=""> -- select society -- </option>
						<c:forEach items="${societyList}" var="myItem" varStatus="loopStatus">
							<option value="${myItem.societyid}">${myItem.societyname}</option>
						</c:forEach>
					</select>
				</div>
                  <ul class="nav navbar-right panel_toolbox">
                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                    </li> 
                  </ul>
                  <div class="clearfix"></div>
                </div>
                <div class="x_content">
                  <div class="dashboard-widget-content">
                    
                    <div class="col-md-8  col-sm-8 col-xs-12">
 				<form id="addCommitteeMember" data-parsley-validate
					class="form-horizontal form-label-left" action="#"
					method="post" onsubmit="return saveVendorDetails()">
					
					<input type="hidden" id="vendorid" name="vendorid" value="0">
					
					 <div class="form-group">
						<label class="control-label col-md-6 col-sm-6 col-xs-12"
							for="first-name">Company Name<span class="required">*</span>
						</label> 
						<div class="col-md-6 col-sm-6 col-xs-12">
								<input type="text" id="companyname" name="companyname"
								required="required" class="form-control col-md-7 col-xs-12">
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-6 col-sm-6 col-xs-12"
							for="first-name">Job Nature<span class="required">*</span>
						</label> 
						<div class="col-md-6 col-sm-6 col-xs-12">
								<input type="text" id="jobnature" name="jobnature"
								required="required" class="form-control col-md-7 col-xs-12">
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-6 col-sm-6 col-xs-12"
							for="first-name">Contact Person<span class="required">*</span>
						</label> 
						<div class="col-md-6 col-sm-6 col-xs-12">
								<input type="text" id="contactperson" name="contactperson"
								required="required" class="form-control col-md-7 col-xs-12">
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-6 col-sm-6 col-xs-12"
							for="first-name">Address<span class="required">*</span>
						</label> 
						<div class="col-md-6 col-sm-6 col-xs-12">
							<textarea id="address" name="address" rows="2" cols="" 
							class="form-control col-md-7 col-xs-12"></textarea>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-6 col-sm-6 col-xs-12"
							for="first-name">Contact Number<span class="required">*</span>
						</label> 
						<div class="col-md-6 col-sm-6 col-xs-12">
								<input type="text" id="contactno" name="contactno"
								required="required" class="form-control col-md-7 col-xs-12">
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-6 col-sm-6 col-xs-12"
							for="first-name">Alternate Number<span class="required">*</span>
						</label> 
						<div class="col-md-6 col-sm-6 col-xs-12">
								<input type="text" id="alternateno" name="alternateno"
								required="required" class="form-control col-md-7 col-xs-12">
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-6 col-sm-6 col-xs-12"
							for="first-name">E Mail<span class="required">*</span>
						</label> 
						<div class="col-md-6 col-sm-6 col-xs-12">
								<input type="text" id="email" name="email"
								required="required" class="form-control col-md-7 col-xs-12">
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-6 col-sm-6 col-xs-12"
							for="first-name">Contract Period From 
						</label> 
						<div class="col-md-2 col-sm-2 col-xs-12">
								<input type="text" id="contractfrom" name="contractfrom"
								 readonly="readonly" class="form-control col-md-7 col-xs-12 customdatepicker">
						</div>
						<label class="control-label col-md-2 col-sm-2 col-xs-12"
							for="first-name">To  
						</label> 
						<div class="col-md-2 col-sm-2 col-xs-12">
								<input type="text" id="contractto" name="contractto"
								 readonly="readonly" class="form-control col-md-7 col-xs-12 customdatepicker">
						</div>
					</div>
					
					
					<div class="form-group">
						<label class="control-label col-md-6 col-sm-6 col-xs-12"
							for="first-name">Remark<span class="required">*</span>
						</label> 
						<div class="col-md-6 col-sm-6 col-xs-12">
							<textarea id="remark" name="remark" rows="2" cols="" 
							class="form-control col-md-7 col-xs-12"></textarea>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-6 col-sm-6 col-xs-12"
							for="first-name">Status<span class="required">*</span>
						</label>
						<div class="col-md-6 col-sm-6 col-xs-12" style="padding-top:6px;">
							<input type="radio" name="isactive" id="isactive" value="1" checked="checked"> Active
							<input type="radio" name="isactive" id="isactive" value="0"> Inactive
						</div>
					</div>
					
					<div class="form-group" align="right">
					<br/>
						<button class="btn btn-warning" type="reset">Reset</button>
						<button class="btn btn-success">Save</button>
					</div>
					
					</form>
					</div>
                  </div>
                </div>
              </div>
 </div>
 
 <div class="col-md-12 col-sm-12 col-xs-12">
              <div class="x_panel tile">
                <div class="x_title">
                  <h2>Existing Vendors</h2>
                  <ul class="nav navbar-right panel_toolbox">
                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                    </li> 
                  </ul>
                  <div class="clearfix"></div>
                </div>
                <div class="x_content">
                  <div class="dashboard-widget-content">
                    <div class="table-responsive">
                      <table class="table table-striped jambo_table bulk_action" id="thetable">
                        <thead>
                          <tr class="headings">
                            <th class="column-title">Company Name</th>
                            <th class="column-title">Job Nature</th>
                            <th class="column-title">Contact Person</th>
                            <th class="column-title">Contact Number</th>
                            <th class="column-title">Alternate Number</th>
                            <th class="column-title">Contract From </th>
                            <th class="column-title">Contract To </th>
                            <th class="column-title">Created On </th>
                            <th class="column-title no-link last">
                            	<span class="nobr">Action</span>
                            </th>
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
 
 <script>
 $(document).ready(function(){
		$('#thetable').DataTable();
	});
 
 
 function saveVendorDetails(){
		var vendorid = $('#vendorid').val();
		var societyid = $('#societyid').val();
		var companyname = $('#companyname').val();
		var jobnature = $('#jobnature').val();
		var contactperson = $('#contactperson').val();
		var address = $('#address').val();
		var contactno = $('#contactno').val();
		var alternateno = $('#alternateno').val();
		var email = $('#email').val();
		var remark = $('#remark').val();
		var isactive = $('#isactive:checked').val();
		var contractto = $('#contractto').val();
		var contractfrom = $('#contractfrom').val();
		
		
		
		if(societyid.length<1 ||  companyname.length<1 || jobnature.length<1 || contactperson.length<1 ||
				address.length<1 || contactno.length<1 || alternateno.length<1 || email.length<1 ||
				remark.length<1 || isactive.length<1){
			alert("Some Mandatory Fields Missing");
			return false;
		}
		blockUI();
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/saveVendorDetails.do",
	        data :"vendorid="+vendorid
			        +"&societyid="+societyid
			        +"&companyname="+companyname
			        +"&jobnature="+jobnature
			        +"&contactperson="+contactperson
			        +"&address="+address
			        +"&contactno="+contactno
			        +"&alternateno="+alternateno
			        +"&email="+email
			        +"&remark="+remark
			        +"&contractfrom="+contractfrom
			        +"&contractto="+contractto
			        +"&isactive="+isactive,
	        success: function(response){
	        	//alert();
	        	if(response.vendorid>0) {
	        		getVendorsBySocId();
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
 
	function getVendorsBySocId(){
		var societyid = $('#societyid').val();
		var table = $('#thetable').DataTable();
			
		table .clear() .draw();
		blockUI();
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/getVendorsBySocId.do",
	        data :"societyid="+societyid,
	        success: function(response){
	        
	        if(response.length>0){
	        	$.each(response, function(i, item) {
	  
	        		var editBtn = '<a class="btn btn-default btn-sm" onclick="editVendor(\'' + item.vendorid + '\')"><i class="fa fa-edit"></i></a>';
	        		
	        		table.row.add( [
	        			item.companyname,
	        			item.jobnature,
	        			item.contactperson,
	        			item.contactno,
	        			item.alternateno,
	        			new Date(item.contractfrom).toString("dd MMM yyyy"),
	        			new Date(item.contractto).toString("dd MMM yyyy"),
	        			new Date(item.createdon).toString("dd MMM yyyy"),
	        			editBtn,
	                ] ).draw( false );
	        	    
	        	  });
	        	}
	        unblockUI();
	        },
				error : function(e) {
					notify('error','ERROR','Error occured',2000);
					unblockUI();
				}
			});
	}
	
	
	function editVendor(vendorId){
		
		editMode();
		blockUI();
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/getVendorDataById.do",
	        data :"vendorid="+vendorId,
	        success: function(response){
	        	if(response.vendorid>0) {
	        		//notify('success','SUCCESS','Added Successfully',2000);
	        		$('#vendorid').val(response.vendorid);
	        		$('#companyname').val(response.companyname);
	        		$('#jobnature').val(response.jobnature);
	        		$('#contactperson').val(response.contactperson);
	        		$('#address').val(response.address);
	        		$('#contactno').val(response.contactno);
	        		$('#alternateno').val(response.alternateno);
	        		$('#email').val(response.email);
	        		$('#remark').val(response.remark);
	        		$('input[name=isactive][value="'+response.isactive+'"]').prop("checked","checked").change();
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