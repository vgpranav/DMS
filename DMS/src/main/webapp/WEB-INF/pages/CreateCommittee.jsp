<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="col-md-12 col-sm-12 col-xs-12">
              <div class="x_panel tile">
                <div class="x_title">
                  <h2>Create Committee</h2>
                  <div class="pull-right">
					<select name="societyid" id="societyid" class="form-control" onchange="getCommitteMembersForSociety()">
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
                    <div class="row">
			<div class="col-md-12  col-sm-12 col-xs-12">
 				<form id="addCommitteeMember" data-parsley-validate
					class="form-horizontal form-label-left" action="#"
					method="post" onsubmit="return addCommitteeMember()">

				
					<div class="form-group">
						<label class="control-label col-md-2 col-sm-2 col-xs-12"
							for="first-name">Select Member<span class="required">*</span>
						</label> 
						<div class="col-md-2 col-sm-2 col-xs-12">
							 	<input type="hidden" id="userid" name="userid"
								required="required" class="form-control col-md-7 col-xs-12">	
								<input type="text" id="username" name="username"
								required="required" class="form-control col-md-7 col-xs-12">
						</div>
						
						<label class="control-label col-md-2 col-sm-2 col-xs-12"
							for="first-name">Select Designation<span class="required">*</span>
						</label> 
						<div class="col-md-2 col-sm-2 col-xs-12">
							<select name="positionid" id="positionid" class="form-control">
								<option value=""> -- select designation -- </option>
								<c:forEach items="${committeeMasterList}" var="myItem" varStatus="loopStatus">
									<option value="${myItem.positionid}">${myItem.positionname}</option>
								</c:forEach>
							</select>
						</div>
						
						<label class="control-label col-md-2 col-sm-2 col-xs-12"
							for="first-name">Appointment Date<span class="required">*</span>
						</label> 
						<div class="col-md-2 col-sm-2 col-xs-12">
							 	<input type="text" id="appointedon" name="appointedon"
								required="required" class="form-control col-md-7 col-xs-12 customdatepicker">
						</div>
						
					</div>
					
					<div class="form-group" align="center">
					<br/>
						<button class="btn btn-warning" type="reset">Reset</button>
						<button class="btn btn-success">Add</button>
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
                  <h2>Current Members</h2>
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
                            <th class="column-title">Sr.No</th>
                            <th class="column-title">Member Name</th>
                            <th class="column-title">Designation</th>
                            <th class="column-title">Appointed On</th>
                            <th class="column-title">Remove</th>
                          </tr>
                        </thead>

                        <tbody></tbody>
                      </table>
                    </div>
                  </div>
                </div>
              </div>
 </div>
 
 
 <div class="col-md-12 col-sm-12 col-xs-12">
              <div class="x_panel tile">
                <div class="x_title">
                  <h2>Past Members</h2>
                  <ul class="nav navbar-right panel_toolbox">
                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                    </li> 
                  </ul>
                  <div class="clearfix"></div>
                </div>
                <div class="x_content">
                  <div class="dashboard-widget-content">
                    <table class="table table-striped jambo_table bulk_action" id="thetable1">
                        <thead>
                          <tr class="headings">
                            <th class="column-title">Sr.No</th>
                            <th class="column-title">Member Name</th>
                            <th class="column-title">Designation</th>
                            <th class="column-title">Appointed On</th>
                            <th class="column-title">Removed On</th>
                          </tr>
                        </thead>

                        <tbody></tbody>
                      </table>
                  </div>
                </div>
              </div>
 </div>
 
 
 
 <script>
 
 	function addCommitteeMember(){
 		var societyid 		= $('#societyid').val();
 		var userid 			= $('#userid').val();
 		var positionid 		= $('#positionid').val();
 		var appointedon		= $('#appointedon').val();
 		
 		if(societyid.length < 1 || userid.length < 1 || positionid.length < 1 || appointedon.length < 1 ){
 			alert('Some Mandatory Fields  are Missing');
 			return false;
 		} else {
 			$('#userid').val("");
 			$.ajax({
 		        type: "GET",
 		        url: "<%=request.getContextPath()%>/addCommitteeMember.do",
 		       data :"societyid="+societyid
	        	+"&userid="+userid
	        	+"&positionid="+positionid
	        	+"&appointedon="+appointedon,
 		        success: function(response){
 		        	if(response.committeememberid>0) {
 		        	notify('success','SUCCESS','Added Successfully',2000);
 		        		getCommitteMembersForSociety();
 		        	}  
 		        },
 					error : function(e) {
 						notify('error','ERROR','Error occured',2000);
 					}
 				});
 		}
 		return false;
 	}
  
	$(document).ready(function(){
	
		$('#thetable').DataTable();
		$('#thetable1').DataTable();
		
		$("#username").click(function(){
		//$('#userid').val("");
			if($('#societyid').val().length<1)
				{ alert('Select Society');
					$('#societyid').focus();
				}
		});
		
		$( "#username").autocomplete({
			 source: function (request, response) {
		            $.getJSON("${pageContext. request. contextPath}/userAutosuggestForSociety.do", {
		            	searchText: request.term,
		            	societyid: $('#societyid').val(),
		            }, response);
		        },
			focus: function () {
		       // prevent value inserted on focus
		       return false;
		   },
		   select: function (event, ui) {
		       var thisValue = ui.item.value;
		       var str = thisValue.split("--");
		       for (var i = 0; i < str.length; i++) {		       	
		    	   $('#userid').val(str[0]); 
		       	   ui.item.value=str[1]; 
		       }
			}
		});
		
	});

	function removeCommitteeMember(committeememberid){
		
		if(confirm('Are you Sure?')){
			if(committeememberid.length < 1){
 			alert('Some Mandatory Fields  are Missing');
 			return false;
 		} else { 
 			$.ajax({
 		        type: "GET",
 		        url: "<%=request.getContextPath()%>/removeCommitteeMember.do",
 		       data :"committeememberid="+committeememberid,
 		        success: function(response){
 		        //alert()
  		        	if(response=='success') {
 		        		notify('success','SUCCESS','Removed Successfully',2000);
 		        		getCommitteMembersForSociety();
 		        	}  else {
 		        		notify('error','Failed','Failed to Remove Member',2000);
 		        	}
 		        },
 					error : function(e) {
 						notify('error','ERROR','Error occured',2000);
 					}
 				});
 		}
		}
 		return false;
	}
	
	function getCommitteMembersForSociety(){
		var societyid = $('#societyid').val();
		var table = $('#thetable').DataTable();
		var table1 = $('#thetable1').DataTable();
			
		if(societyid.length<1)
			return false;
			
		table.clear().draw();
		table1.clear().draw();
		
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/getCommitteMembersForSociety.do",
	        data :"societyid="+societyid,
	        success: function(response){
	        var k=1;
	        var j=1;
	        
	        	$.each(response, function(i, item) {
	        		$.each(item, function(i, item1) {
	        				if(item1.isactive==1){
	        				var removebtn = '<button class="btn btn-xs btn-danger" onClick="removeCommitteeMember(\'' + item1.committeememberid + '\')"><i class="fa fa-times"></i></button>';
	        				
			        					table.row.add( [
			        					k,
					        			item1.userName,
					        			item1.positionname, 
					        			new Date(item1.appointedon).toString("dd MMM yyyy"),
					        			removebtn,
				                ] ).draw( false ); 
				                k++;
	        				}else{
	        					table1.row.add( [
	        						j,
				        			item1.userName,
				        			item1.positionname, 
				        			new Date(item1.appointedon).toString("dd MMM yyyy"),
				        			new Date(item1.removedon).toString("dd MMM yyyy"),
				                ] ).draw( false ); 
				                j++;
	        				}
	        		 });
	        		 
	        	  });
	        },
				error : function(e) {
					notify('error','ERROR','Error occured',2000);
				}
			});
	}
</script>