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
							class="form-horizontal form-label-left" action="saveCallRef2.do" method="post"
							 onsubmit="return saveFormFields()">
							
							<input type="hidden" id="callrefid" name="callrefid" value="${callref.callrefid}">
							
							<div class="col-md-12 col-sm-12 col-xs-12">
								<br /> 
								<h2><span class="label label-success label-md">Meeting Details</span></h2> 
								<hr />
							</div>
							<div class="clearfix"></div>
							
							<div class="form-group">
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Meeting/Con Call Date 1<span class="required">*</span>
								</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="meetingdate" name="meetingdate"
											class="customdatepicker form-control col-md-12 col-xs-12" required="required">
									</div>
								</div>
								
								
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Person Met/Con Call Made by<span class="required">*</span>
								</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="meetingperson" name="meetingperson"
											class="form-control col-md-12 col-xs-12" required="required"  >
									</div>
								</div>
								
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Remarks<span class="required">*</span>
								</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
									 	<input type="text"  name="meetingremarks" id="meetingremarks" class="form-control col-md-12 col-xs-12">
									</div>
								</div>
								
							</div>
							
							
							<div class="form-group" align="center">
								<br />
								<br />
								<button class="btn btn-warning" type="reset">Reset</button>
								<button class="btn btn-success"  type="submit">Save</button>
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
                            <th class="column-title">Meeting Date</th>
                            <th class="column-title">Person Met/Con Call Made By</th>
                            <th class="column-title">Remarks</th>
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
		getMeetingsByCallRefId();
	});
 
 
 function getMeetingsByCallRefId(){
		var callrefid = $('#callrefid').val();
		var table = $('#thetable').DataTable();
			
		table .clear() .draw();
		
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/getMeetingsByCallRefId.do",
	        data :"callrefid="+callrefid,
	        success: function(response){
	        
	        if(response.length>0){
	        	$.each(response, function(i, item) {
	  
	        		table.row.add( [
	        			item.meetingdate,
	        			item.meetingperson,
	        			item.meetingremarks,
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
		var meetingdate = $('#meetingdate').val();
		var meetingperson = $('#meetingperson').val();
		var meetingremarks = $('#meetingremarks').val();
		
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/saveCallrefMeeting.do",
	        data :"callrefid="+callrefid
	        		+"&meetingremarks="+meetingremarks
	       			 +"&meetingperson="+meetingperson
			        +"&meetingdate="+meetingdate,
	        success: function(response){
	        	if(response.callrefid>0) {
	        		getMeetingsByCallRefId();
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
 