<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="col-md-12 col-sm-12 col-xs-12">
              <div class="x_panel tile">
                <div class="x_title">
                  <h2>Create Project</h2>
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
					class="form-horizontal form-label-left" action="saveProject.do"
					method="post">

					<input type="hidden" id="projectid" name="projectid" value="0">
						
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name">Builder <span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<select name="builderid" id="builderid" class="form-control">
								<option value=""> -- Select Builder -- </option>
								<c:forEach items="${builderList}" var="myItem" varStatus="loopStatus">
									<option value="${myItem.builderid}">${myItem.buildername}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name">Name of the Project <span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<input type="text" id="projectname" name="projectname"
								required="required" class="form-control col-md-7 col-xs-12">
						</div>
					</div>
					
					
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name">Site Address <span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<textarea id="siteaddress" name="siteaddress"
								required="required" class="form-control col-md-7 col-xs-12"></textarea>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name">Plot Area<span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<input type="text" id="plotarea" name="plotarea"
								required="required" class="form-control col-md-7 col-xs-12">
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name">Registration Date<span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<input type="text" id="registrationdate" name="registrationdate"
								required="required" class="form-control col-md-7 col-xs-12 customdatepicker" readonly="readonly">
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name">No. of Towers<span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<input type="text" id="towernos" name="towernos"
								required="required" class="form-control col-md-7 col-xs-12">
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name">No. of Residence<span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<input type="text" id="resnos" name="resnos"
								required="required" class="form-control col-md-7 col-xs-12">
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name">No. of Bunglows<span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<input type="text" id="bungnos" name="bungnos"
								required="required" class="form-control col-md-7 col-xs-12">
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name">No. of Penta House<span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<input type="text" id="pentanos" name="pentanos"
								required="required" class="form-control col-md-7 col-xs-12">
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name">No. of Shops<span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<input type="text" id="shopnos" name="shopnos"
								required="required" class="form-control col-md-7 col-xs-12">
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name">No. of Commercial Gala<span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<input type="text" id="galanos" name="galanos"
								required="required" class="form-control col-md-7 col-xs-12">
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
                  <h2>Existing Projects</h2>
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
                            <th class="column-title">Project Name</th>
                            <th class="column-title">Builder Name</th>
                            <th class="column-title">Registration Date</th>
                            <th class="column-title">Created On </th>
                            <th class="column-title">Status </th>
                            <th class="column-title no-link last">
                            	<span class="nobr">Edit</span>
                            </th>
                             
                          </tr>
                        </thead>

                        <tbody>
                        	  <c:forEach items="${projectList}" var="myItem" varStatus="loopStatus">
								<c:if test="${loopStatus.index%2==0}">
									<tr class="even pointer">
								</c:if>
								<c:if test="${loopStatus.index%2!=0}">
									<tr class="odd pointer">
								</c:if>
									<td class=" ">${myItem.projectname}</td>
									<td class=" ">${myItem.buildername}</td>
									<td class=" ">${myItem.registrationdate}</td> 
									<td class=" ">${myItem.createdon}</td>
									<td class=" ">
										<c:if test="${myItem.active==1}">
											Active
										</c:if>
										<c:if test="${myItem.active!=1}">
											Inactive
										</c:if>
									</td>								
									<td class=" ">
										<a class="btn btn-default btn-sm" onclick="editProject('${myItem.projectid}')">
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
		$('#thetable').DataTable();
	});
 
		 
		 function editProject(projectid){
		
			 editMode();
			 
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/getProjectDetailsById.do",
	        data :"projectid="+projectid ,
	        success: function(response){
	        	if(response.projectid>0) {
	        		//notify('success','SUCCESS','Added Successfully',2000);
	        		$('#projectid').val(response.projectid);
	        		$('#projectname').val(response.projectname);
	        		$('#builderid option[value="'+response.builderid+'"]').prop("selected",true).change();
	        		$('#siteaddress').val(response.siteaddress);
	        		$('#plotarea').val(response.plotarea);
	        		$('#registrationdate').val(new Date(response.registrationdate).toString("yyyy/MM/dd"));
	        		$('#towernos').val(response.towernos);
	        		$('#resnos').val(response.resnos);
	        		$('#bungnos').val(response.bungnos);
	        		$('#pentanos').val(response.pentanos);
	        		$('#shopnos').val(response.shopnos);
	        		$('#galanos').val(response.galanos);
	        		$('input[name=active][value="'+response.active+'"]').prop("checked","checked").change();

	        	}  
	        },
				error : function(e) {
					notify('error','ERROR','Error occured',2000);
				}
			});
		 
	}
 </script>