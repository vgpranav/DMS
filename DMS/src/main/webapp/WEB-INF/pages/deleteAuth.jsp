<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="col-md-12 col-sm-12 col-xs-12">
              <div class="x_panel tile">
                <div class="x_title">
                  <h2>Delete Authorization</h2>
                  <ul class="nav navbar-right panel_toolbox">
                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                    </li> 
                  </ul>
                  <div class="clearfix"></div>
                </div>
                <div class="x_content">
                  <div class="dashboard-widget-content">
                    <div class="row">
			<div class="col-md-7 col-sm-7 col-xs-12">
 				<form id="addCommitteeMember" data-parsley-validate
					class="form-horizontal form-label-left" action="#"
					method="post" onsubmit="return addDeleteAuth()">

						
						<div class="form-group">
							<label class="control-label col-md-4 col-sm-4 col-xs-12"
								for="first-name"> User
								 Name <span class="required">*</span>
							</label>
							<div class="col-md-8 col-sm-8 col-xs-12">
								 	<input type="hidden" id="userid" name="userid"
									required="required" class="form-control col-md-7 col-xs-12">	
									<input type="text" id="username" name="username"
									required="required" class="form-control col-md-7 col-xs-12">
							</div>
						</div>
						
						
						<!-- <div class="form-group">
							<label class="control-label col-md-4 col-sm-4 col-xs-12"
								for="first-name"> Delete Authorization <span class="required">*</span>
							</label>
							<div class="col-md-8 col-sm-8 col-xs-12">
								<input type="radio" name="deleteFlag" value="1" checked="checked"> Yes
								<input type="radio" name="deleteFlag" value="0"> No
							</div>
						</div> --> 
						
					
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
                  <h2>Users With Delete Access</h2>
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
                            <th class="column-title">UserName</th>
                            <th class="column-title" width="10%">Remove</th>
                          </tr>
                        </thead>

                        <tbody></tbody>
                      </table>
                  </div>
                </div>
              </div>
 </div>
 
 <script>
 $(document).ready(function(){
	 
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
			            $.getJSON("${pageContext. request. contextPath}/userAutosuggest.do", {
			            	searchText: request.term, 
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
			
			getDeleteAuthList();
			
});
 
 
 function getDeleteAuthList(){
		var table1 = $('#thetable1').DataTable();
			
		table1.clear().draw();
		blockUI();
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/getDeleteAuth.do",
	        success: function(response){
	        		$.each(response, function(j, item1) {
	        				var removebtn = '<button class="btn btn-xs btn-danger" onClick="removeDeleteAuth(\'' + item1.userid + '\')"><i class="fa fa-times"></i></button>';
	        				var k = j+1;
	        				table1.row.add( [
			        					k,
					        			item1.firstName+' '+item1.lastName,
					        			removebtn,
				                ] ).draw( false ); 
	        				});
	        		unblockUI();
	        },
				error : function(e) {
					notify('error','ERROR','Error occured',2000);
					unblockUI();
				}
			});
	}
 
 function removeDeleteAuth(userid){
	 
		if(confirm('Are you Sure?')){
			blockUI();
			$.ajax({
		        type: "GET",
		        url: "<%=request.getContextPath()%>/removeDeleteAuth.do",
		       data :"userid="+userid,
		        success: function(response){
		        //alert()
		        	if(response=='success') {
		        		notify('success','SUCCESS','Removed Successfully',2000);
		        		getDeleteAuthList();
		        	}  else {
		        		notify('error','Failed','Failed to Remove User',2000);
		        	}
		        unblockUI();
		        },
					error : function(e) {
						notify('error','ERROR','Error occured',2000);
						unblockUI();
					}
				}); 
		}
		return false;
	}
 
  
 
 function addDeleteAuth(){
		var userid = $('#userid').val();
		
		if(userid.length < 1 ){
			alert('Some Mandatory Fields  are Missing');
			return false;
		} else {
			$('#userid').val("");
			$('#username').val("");
			blockUI();
			$.ajax({
		        type: "GET",
		        url: "<%=request.getContextPath()%>/addDeleteAuth.do",
		       data :"userid="+userid,
		        success: function(response){
		        	if(response=='success') {
		        		notify('success','SUCCESS','Added Successfully',2000);
		        		getDeleteAuthList();
		        	}  else {
		        		notify('error','Failed','Failed to add authorization',2000);
		        	}
		        	unblockUI();
		        },
					error : function(e) {
						notify('error','ERROR','Error occured',2000);
						unblockUI();
					}
				});
		}
		return false;
	}
 </script>