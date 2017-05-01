<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="col-md-12 col-sm-12 col-xs-12">
              <div class="x_panel tile">
                <div class="x_title">
                  <h2>Add Document Type</h2>
                  <ul class="nav navbar-right panel_toolbox">
                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                    </li> 
                  </ul>
                  <div class="clearfix"></div>
                </div>
                <div class="x_content">
                  <div class="dashboard-widget-content">
                    	<div class="row">
			<div class="col-md-6 col-md-offset-3 col-sm-offset-3 col-sm-6 col-xs-12">
 				<form id="addSocietyForm" data-parsley-validate
					class="form-horizontal form-label-left" action="saveDocumentType.do"
					method="post">

					<input type="hidden" id="doctypeid" name="doctypeid" value="0">
								
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name"> Document Name <span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<input type="text" id="doctypename" name="doctypename"
								required="required" class="form-control col-md-7 col-xs-12">
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name"> Document Description  
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<input type="text" id="doctypedesc" name="doctypedesc"
								 class="form-control col-md-7 col-xs-12">
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name"> Status <span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12" style="padding-top:7px;">
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
 </div>
 
 <div class="col-md-12 col-sm-12 col-xs-12">
              <div class="x_panel tile">
                <div class="x_title">
                  <h2>Existing Document Type</h2>
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
                            <th class="column-title">Document Type</th>
                            <th class="column-title">Document Description </th>
                            <th class="column-title">Created By </th>
                            <th class="column-title">Created On </th>
                            <th class="column-title">Status </th>
                            <th class="column-title no-link last">
                            	<span class="nobr">Edit</span>
                            </th>
                             
                          </tr>
                        </thead>

                        <tbody>
                        	<c:forEach items="${docTypesList}" var="myItem" varStatus="loopStatus">
								<c:if test="${loopStatus.index%2==0}">
									<tr class="even pointer">
								</c:if>
								<c:if test="${loopStatus.index%2!=0}">
									<tr class="odd pointer">
								</c:if>
									<td class=" ">${myItem.doctypename}</td>
									<td class=" ">${myItem.doctypedesc}</td>
									<td class=" ">${myItem.createdby}</td>
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
										<a class="btn btn-default btn-sm" onclick="editDoc('${myItem.doctypeid}')">
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
	
	function editDoc(doctypeid){
		
		editMode();
		blockUI();
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/getDocumentTypeById.do",
	        data :"doctypeid="+doctypeid ,
	        success: function(response){
	        	if(response.doctypeid>0) {
	        		//notify('success','SUCCESS','Added Successfully',2000);
	        		$('#doctypeid').val(response.doctypeid);
	        		$('#doctypename').val(response.doctypename);
	        		$('#doctypedesc').val(response.doctypedesc);
	        		$('input[name=active][value="'+response.active+'"]').prop("checked","checked").change();
	        		//$('#societyname').val(response.societyname);
	        		
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