<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="col-md-12 col-sm-12 col-xs-12">
              <div class="x_panel tile">
                <div class="x_title">
                  <h2>Manage Society Designations</h2>
                   
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
					
					<input type="hidden" id="positionid" name="positionid" value="0">
					
					 <div class="form-group">
						<label class="control-label col-md-6 col-sm-6 col-xs-12"
							for="first-name">Designation Name<span class="required">*</span>
						</label> 
						<div class="col-md-6 col-sm-6 col-xs-12">
								<input type="text" id="positionname" name="positionname"
								required="required" class="form-control col-md-7 col-xs-12">
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
                  <h2>Existing Designations</h2>
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
                            <th class="column-title">Designation Name</th>
                            <th class="column-title">Created On</th>
                            <th class="column-title">Status</th>
                            <th class="column-title no-link last">
                            	<span class="nobr">Action</span>
                            </th>
                          </tr>
                        </thead>

                          <tbody>
                        	<c:forEach items="${desigList}" var="myItem" varStatus="loopStatus">
								<c:if test="${loopStatus.index%2==0}">
									<tr class="even pointer">
								</c:if>
								<c:if test="${loopStatus.index%2!=0}">
									<tr class="odd pointer">
								</c:if>
									<td class=" ">${myItem.positionname}</td> 
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
										<a class="btn btn-default btn-sm" onclick="editDoc('${myItem.positionid}')">
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
 
 
 function saveVendorDetails(){
		var positionid = $('#positionid').val();
		var positionname = $('#positionname').val();
		var isactive = $('#isactive:checked').val();
		 
		
		
		
		if(positionid.length<1 ||  positionname.length<1 || isactive.length<1 ){
			alert("Some Mandatory Fields Missing");
			return false;
		}
			
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/saveDesignationDetails.do",
	        data :"positionid="+positionid
			        +"&positionname="+positionname 
			        +"&isactive="+isactive,
	        success: function(response){
	        	//alert();
	        	if(response.positionid>0) {
	        		notify('success','SUCCESS','Added Successfully',2000);
	        		location.reload();
	        	}  
	        },
				error : function(e) {
					notify('error','ERROR','Error occured',2000);
				}
			});
		
		return false;
	}
 
	 
	
 function editDoc(positionid){
		
		editMode();
		
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/getDesignationById.do",
	        data :"positionid="+positionid ,
	        success: function(response){
	        	if(response.positionid>0) {
	        		$('#positionid').val(response.positionid);
	        		$('#positionname').val(response.positionname);
	        		$('input[name=isactive][value="'+response.isactive+'"]').prop("checked","checked").change();
	        	}  
	        },
				error : function(e) {
					notify('error','ERROR','Error occured',2000);
				}
			});
		 
	}

	
	
 </script>