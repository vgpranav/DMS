<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="col-md-12 col-sm-12 col-xs-12">
              <div class="x_panel tile">
                <div class="x_title">
                  <h2>Add Call Reference</h2>
                  <ul class="nav navbar-right panel_toolbox">
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
							class="form-horizontal form-label-left" action="saveCallRef3.do" method="post"
							>
							
							<input type="hidden" id="callrefid" name="callrefid" value="${callref.callrefid}">
							
							<div class="col-md-12 col-sm-12 col-xs-12">
								<br /> 
								<h2><span class="label label-success label-md">Contact Person Details</span></h2> 
								<hr />
							</div>
							<div class="clearfix"></div>
							
							<div class="form-group">
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Name<span class="required">*</span>
								</label>
								<div class="col-md-4 col-sm-4 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="contactname" name="contactname"
											class="form-control col-md-12 col-xs-12" required="required">
									</div>
								</div>
								
								
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Designation<span class="required">*</span>
								</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="contactdesignation" name="contactdesignation"
											class="form-control col-md-12 col-xs-12" required="required"  >
									</div>
								</div>
								
								
							</div>
							
							<div class="form-group">
							
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Mobile No <span class="required">*</span>
								</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
									 	<input type="text"  name="contactmobileno" id="contactmobileno" class="form-control col-md-12 col-xs-12">
									</div>
								</div>
								
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Landline Number <span class="required">*</span>
								</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="contactlandlineno" name="contactlandlineno"
											class="form-control col-md-12 col-xs-12" required="required">
									</div>
								</div>
								
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Email<span class="required">*</span>
								</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="contactemail" name="contactemail"
											class="form-control col-md-12 col-xs-12" required="required">
									</div>
								</div>
							</div>
							
							<div class="form-group" align="center">
								<br />
								<br />
								<button class="btn btn-warning" type="reset">Reset</button>
								<button class="btn btn-success"  type="button" onclick="saveFormFields()">Save</button>
								<button class="btn btn-success" type="submit">Next</button>
								
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
                  <h2>Contact</h2>
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
                            <th class="column-title">Contact Name</th>
                            <th class="column-title">Designation</th>
                            <th class="column-title">Mobile No</th>
                            <th class="column-title">Landline No</th>
                            <th class="column-title">Email</th>
                          </tr>
                        </thead>
                        <tbody></tbody>
                      </table>
                    </div>
                  </div>
                </div>
              </div>
 </div>
 
 
 <script>
 
 $(document).ready(function(){
		$('#thetable').DataTable();
		
		getContactsByCallRefId();
	});
 
 
 function getContactsByCallRefId(){
		var callrefid = $('#callrefid').val();
		var table = $('#thetable').DataTable();
			
		table .clear() .draw();
		
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/getContactsByCallRefId.do",
	        data :"callrefid="+callrefid,
	        success: function(response){
	        
	        if(response.length>0){
	        	$.each(response, function(i, item) {
	  
	        		table.row.add( [
	        			item.contactname,
	        			item.contactdesignation,
	        			item.contactmobileno,
	        			item.contactlandlineno,
	        			item.contactemail,
	                ] ).draw( false );
	        	    
	        	  });
	        	}
	        },
				error : function(e) {
					notify('error','ERROR','Error occured',2000);
				}
			});
	}
 
 function saveFormFields(){
		
		var callrefid = $('#callrefid').val();
		var contactname = $('#contactname').val();
		var contactdesignation = $('#contactdesignation').val();
		var contactmobileno = $('#contactmobileno').val();
		var contactlandlineno = $('#contactlandlineno').val();
		var contactemail = $('#contactemail').val();
		
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/saveCallrefContact.do",
	        data :"callrefid="+callrefid
	        		+"&contactname="+contactname
	       			 +"&contactdesignation="+contactdesignation
			        +"&contactmobileno="+contactmobileno
			        +"&contactlandlineno="+contactlandlineno
			        +"&contactemail="+contactemail,
	        success: function(response){
	        	if(response.callrefid>0) {
	        		getContactsByCallRefId();
	        		notify('success','SUCCESS','Added Successfully',2000);
	        	}  
	        },
				error : function(e) {
					notify('error','ERROR','Error occured',2000);
				}
			});
		
		return false;
	}
 
 
 
 </script>
 