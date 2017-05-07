<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="col-md-12 col-sm-12 col-xs-12">
              <div class="x_panel tile">
                <div class="x_title">
                  <h2>Confidential Document Access</h2>
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
					method="post" onsubmit="return saveConfDocAccess()">
					 
					 
					
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
                  <h2>Society Document</h2>
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
                            <!-- <th class="column-title">Sr.No</th> -->
                            <th class="column-title">Username</th>
                            <th class="column-title">Added On</th>
                            <th class="column-title">Added By</th>
                            <th class="column-title" width="10%">Remove</th>
                          </tr>
                        </thead>
                         <tbody>
                        </tbody>
                      </table>
                  </div>
                </div>
              </div>
 </div>
 
 <script>
 $(document).ready(function(){
	 
	 $('#thetable1').DataTable();
	 
	 getConfDocAccessList();
	 
	 $( "#username").autocomplete({
		 source: function (request, response) {
	            $.getJSON("${pageContext. request. contextPath}/userAutosuggest.do", {
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
 
 function getConfDocAccessList(){
		 	
	 	var table =  $('#thetable1').DataTable();
	 	
		table .clear() .draw();
		blockUI();
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/getConfDocAccessList.do",
	        success: function(response){
	        	var srno=1;
	        	
	        	$.each(response, function(i, item) {
	        		
	        		var removebtn = '<button class="btn btn-xs btn-danger" onClick="removeSocDocViewMapping(\'' + item.confidentialdocaccessid + '\')"><i class="fa fa-times"></i></button>';
	        		
	        		table.row.add( [
	        			item.username,
	        			new Date(item.createdon).toString("dd MMM yyyy"),
	        			item.createdby,
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
 
 function saveConfDocAccess(){
		
		var userid = $('#userid').val();
		blockUI();
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/saveConfDocAccess.do",
	        data :"userid="+userid,
		        success: function(response){
		        	if(response=='success') {
 		        		notify('success','SUCCESS','Removed Successfully',2000);
 		        		getConfDocAccessList()
 		        	}  else {
 		        		notify('error','Failed','Failed to Remove Member',2000);
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
 
 function removeSocDocViewMapping(socdocviewmappingid){
	 
		if(confirm('Are you Sure?')){
			blockUI();
			$.ajax({
		        type: "GET",
		        url: "<%=request.getContextPath()%>/removeSocDocViewMapping.do",
		       data :"socdocviewmappingid="+socdocviewmappingid,
		        success: function(response){
		        //alert()
		        	if(response=='success') {
		        		notify('success','SUCCESS','Removed Successfully',2000);
		        		getMappedDocsBySocId();
		        	}  else {
		        		notify('error','Failed','Failed to Remove Member',2000);
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