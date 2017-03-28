<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="col-md-12 col-sm-12 col-xs-12">
              <div class="x_panel tile">
                <div class="x_title">
                  <h2>Create / Edit Society</h2>
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
					class="form-horizontal form-label-left" action="saveSociety.do"
					method="post">

					<input type="hidden" id="societyid" name="societyid" value="0">
								
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name"> Society Name <span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<input type="text" id="societyname" name="societyname"
								required="required" class="form-control col-md-7 col-xs-12">
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name"> Select Project  
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<select name="projectid" id="projectid"
								class="form-control">
								<option value="0">Individual</option>
								<c:forEach items="${projectList}" var="myItem"
									varStatus="loopStatus">
									<option value="${myItem.projectid}">${myItem.projectname}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name"> Society Type <span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<select name="societytypeid" id="societytypeid"
								class="form-control">
								<c:forEach items="${societytypeList}" var="myItem"
									varStatus="loopStatus">
									<option value="${myItem.societytypeid}">${myItem.societytypename}</option>
								</c:forEach>
							</select>
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name"> Address Line 1 <span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<input type="text" id="addressline1" name="addressline1"
								required="required" class="form-control col-md-7 col-xs-12">
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name"> Address Line 2 <span class="required"></span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<input type="text" id="addressline2" name="addressline2"
								required="required" class="form-control col-md-7 col-xs-12">
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name"> Ward / Zone / Sector <span
							class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<input type="text" id="ward" name="ward" required="required"
								class="form-control col-md-7 col-xs-12">
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name"> District <span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<input type="text" id="district" name="district"
								required="required" class="form-control col-md-7 col-xs-12">
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name"> State <span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<input type="text" id="state" name="state" required="required"
								class="form-control col-md-7 col-xs-12">
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name"> Pincode <span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<input type="text" id="pincode" name="pincode"
								required="required" class="form-control col-md-7 col-xs-12">
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name"> Society Registration No. <span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<input type="text" id="registrationno" name="registrationno"
								required="required" class="form-control col-md-7 col-xs-12">
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name">Establishment Date<span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<input type="text" id="estdate" name="estdate"
								readonly required="required" class="form-control col-md-7 col-xs-12 customdatepicker">
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
			
			<!-- <div class="col-md-5 col-sm-5 col-xs-12 widget widget_tally_box">
				<div class="x_panel">
					<div class="x_title">
						<h2>Search Society to Edit</h2>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<div class="form-group">
							<div class="col-md-12 col-sm-12 col-xs-12">
								<input type="text" id="searchText" name="searchText"
									placeholder="Enter Society Name" required="required"
									class="form-control col-md-12 col-xs-12">
							</div>
						</div>
						<div></div>
					</div>
				</div>
			</div> -->
			
			
                  </div>
                </div>
              </div>
 </div>




<div class="col-md-12 col-sm-12 col-xs-12">
              <div class="x_panel tile">
                <div class="x_title">
                  <h2>Existing Society</h2>
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
                            <th class="column-title">Society Name</th>
                            <th class="column-title">Project Name</th>
                            <th class="column-title">Created On </th>
                            <th class="column-title">Status </th>
                            <th class="column-title no-link last">
                            	<span class="nobr">Edit</span>
                            </th>
                             
                          </tr>
                        </thead>

                        <tbody>
                        	  <c:forEach items="${societyList}" var="myItem" varStatus="loopStatus">
								<c:if test="${loopStatus.index%2==0}">
									<tr class="even pointer">
								</c:if>
								<c:if test="${loopStatus.index%2!=0}">
									<tr class="odd pointer">
								</c:if>
									<td class=" ">${myItem.societyname}</td>
									<td class=" ">${myItem.projectname}</td>
									<td class=" ">${myItem.createdon}</td>
									<td class=" ">
										<c:if test="${myItem.isactive==1}">
											Active
										</c:if>
										<c:if test="${myItem.isactive!=1}">
											Inactive
										</c:if>
									</td>								
									<td class=" ">
										<a class="btn btn-default btn-sm" onclick="getSocietyDetailsById('${myItem.societyid}')">
											<i class="fa fa-edit"></i>
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
		
		$( "#searchText").autocomplete({
			 source: function (request, response) {
		            $.getJSON("${pageContext. request. contextPath}/societyAutosuggest.do", {
		            	searchText: request.term,
		            }, response);
		        },
			focus: function () {
		       // prevent value inserted on focus
		       return false;
		   },
		   select: function (event, ui) {
			   var socId;
		       var thisValue = ui.item.value;
		       var str = thisValue.split("--");
		       for (var i = 0; i < str.length; i++) {
		       	 ui.item.value=str[1]; 
		       	socId=str[0];
		       }
		       getSocietyDetailsById(socId);
			}
		});
		
	});
	

	function getSocietyDetailsById(societyid){
		
		//var societyId = $('#searchText').val();
		
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/getSocietyDetailsById.do",
	        data :"societyid="+societyid ,
	        success: function(response){
	        	if(response.societyid>0) {
	        		//notify('success','SUCCESS','Added Successfully',2000);
	        		$('#societyid').val(response.societyid);
	        		$('#societyname').val(response.societyname);
	        		$('#addressline1').val(response.addressline1);
	        		$('#addressline2').val(response.addressline2);
	        		$('#ward').val(response.ward);
	        		$('#district').val(response.district);
	        		$('#state').val(response.state);
	        		$('#pincode').val(response.pincode);
	        		$('#registrationno').val(response.registrationno);
	        		$('#estdate').val(new Date(response.estdate).toString("yyyy/MM/dd"));
	        		
	        		$('#societytypeid option[value="'+response.societytypeid+'"]').prop("selected",true).change();
	        		$('#projectid option[value="'+response.projectid+'"]').prop("selected",true).change();
	        		//$('#societyname').val(response.societyname);
	        		
	        	}  
	        },
				error : function(e) {
					notify('error','ERROR','Error occured',2000);
				}
			});
		 
	}
</script>