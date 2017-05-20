<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<div class="col-md-12 col-sm-12 col-xs-12">
              <div class="x_panel tile">
                <div class="x_title">
                  <h2>Create Builder Profile</h2>
                  <ul class="nav navbar-right panel_toolbox">
                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                    </li> 
                  </ul>
                  <div class="clearfix"></div>
                </div>
                <div class="x_content">
                  <div class="dashboard-widget-content">
                    <div class="col-md-7 col-sm-7 col-xs-12">
                    
                    <form id="addSocietyForm" data-parsley-validate
					class="form-horizontal form-label-left" action="saveBuilder.do"
					method="post">

					<input type="hidden" id="builderid" name="builderid" value="0">
								
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name">Name of Builder <span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<input type="text" id="buildername" name="buildername"
								required="required" class="form-control col-md-7 col-xs-12">
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name">Name of Premises 
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<input type="text" id="premisesname" name="premisesname"
								 class="form-control col-md-7 col-xs-12">
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name">Street/Road/Lane 
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<input type="text" id="streetname" name="streetname"
								 class="form-control col-md-7 col-xs-12">
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name">Landmark 
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<input type="text" id="landmark" name="landmark"
							 	class="form-control col-md-7 col-xs-12">
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name">Area/Locality/Sector 
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<input type="text" id="area" name="area"
							 class="form-control col-md-7 col-xs-12">
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name">Vilage/Town/City <span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<input type="text" id="city" name="city"
								required="required" class="form-control col-md-7 col-xs-12">
						</div>
					</div>
					
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name">Pincode<span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<input type="text" id="pincode" name="pincode"
								required="required" class="form-control col-md-7 col-xs-12">
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name">District<span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<input type="text" id="district" name="district"
								required="required" class="form-control col-md-7 col-xs-12" value="">
						</div>
					</div>
					
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name">State<span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<input type="text" id="state" name="state"
								required="required" class="form-control col-md-7 col-xs-12" value="Maharashtra">
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name">Country<span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<input type="text" id="country" name="country"
								required="required" class="form-control col-md-7 col-xs-12" value="India">
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name">Contact No 
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<input type="text" id="contact" name="contact"
								 class="form-control col-md-7 col-xs-12" value="">
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name">Alternate No. 
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<input type="text" id="altcontact" name="altcontact"
								 class="form-control col-md-7 col-xs-12" value="">
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name">Email 
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<input type="text" id="email" name="email"
								 class="form-control col-md-7 col-xs-12" value="">
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name">Status<span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12" style="padding-top:6px;">
							<input type="radio" name="active" value="1" checked="checked"> Active
							<input type="radio" name="active" value="0"> Inactive
						</div>
					</div>
					
					<div class="form-group" align="right">
						<button class="btn btn-warning" type="reset">Reset</button>
						<button class="btn btn-success">Save</button>
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
                </div>
              </div>
 </div>
 
 
 <div class="col-md-12 col-sm-12 col-xs-12">
              <div class="x_panel tile">
                <div class="x_title">
                  <h2>Existing Builders</h2>
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
                            <th class="column-title">Builder Name</th>
                            <th class="column-title">Area</th>
                            <th class="column-title">City</th>
                            <th class="column-title">State</th>
                            <th class="column-title">Created On </th>
                            <th class="column-title">Status </th>
                            <th class="column-title">Edit</th>
                            <th class="column-title">Remove</th>
                          </tr>
                        </thead>

                        <tbody>
                        	<c:forEach items="${builderList}" var="myItem" varStatus="loopStatus">
								<c:if test="${loopStatus.index%2==0}">
									<tr class="even pointer">
								</c:if>
								<c:if test="${loopStatus.index%2!=0}">
									<tr class="odd pointer">
								</c:if>
									<td class=" ">${myItem.buildername}</td>
									<td class=" ">${myItem.area}</td>
									<td class=" ">${myItem.city}</td> 
									<td class=" ">${myItem.state}</td>
									<td class=" "><fmt:formatDate type = "date" value = "${myItem.createdon}" /></td>
									<td class=" ">
										<c:if test="${myItem.active==1}">
											Active
										</c:if>
										<c:if test="${myItem.active!=1}">
											Inactive
										</c:if>
									</td>								
									<td class=" ">
										<a class="btn btn-default btn-sm" onclick="editBuilder('${myItem.builderid}')">
											<i class="fa fa-edit"></i>
										</a>
									</td>
									<td class=" ">
										<a class="btn btn-default btn-sm" onclick="genericRemove('${myItem.builderid}','builder','builderid','reload')">
											<i class="fa fa-times"></i>
										</a>
									</td>
								</tr>
							</c:forEach>
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
 
		 
		 function editBuilder(builderid){
		
			 editMode();
			 blockUI();
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/getBuilderDetailsById.do",
	        data :"builderid="+builderid ,
	        success: function(response){
	        	if(response.builderid>0) {
	        		//notify('success','SUCCESS','Added Successfully',2000);
	        		$('#builderid').val(response.builderid);
	        		$('#buildername').val(response.buildername);
	        		$('#address').val(response.address);
	        		$('#blockno').val(response.blockno);
	        		$('#premisesname').val(response.premisesname);
	        		$('#streetname').val(response.streetname);
	        		$('#landmark').val(response.landmark);
	        		$('#area').val(response.area);
	        		$('#city').val(response.city);
	        		$('#pincode').val(response.pincode);
	        		$('#state').val(response.state);
	        		$('#country').val(response.country);
	        		$('input[name=active][value="'+response.active+'"]').prop("checked","checked").change();

	        		$('#contact').val(response.contact);
	        		$('#altcontact').val(response.altcontact);
	        		$('#email').val(response.email);

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