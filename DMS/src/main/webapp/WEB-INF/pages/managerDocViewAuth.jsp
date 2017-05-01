<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="col-md-12 col-sm-12 col-xs-12">
              <div class="x_panel tile">
                <div class="x_title">
                  <h2>Document View Access</h2>
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
					method="post" onsubmit="return saveSocViewMapping()">

				
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name"> Society Name <span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<select name="societyid" id="societyid" class="form-control" onchange="getDocTypes()">
							<option value=""> -- select society -- </option>
							<c:forEach items="${societyList}" var="myItem" varStatus="loopStatus">
								<option value="${myItem.societyid}">${myItem.societyname}</option>
							</c:forEach>
						</select>
						</div>
						
						</div>
						<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name">Document Type <span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<select name="doctypeid" id="doctypeid" class="form-control" onchange="getDocSubTypes()">
								<option value=""> -- select -- </option>
							</select>
						</div>
						</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name">Document Sub Type <span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<select name="docsubtypeid" id="docsubtypeid" class="form-control">
								<option value=""> -- select -- </option>
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name">Display To Society Members <span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12" style="padding-top:6px;">
							<input type="radio" name="displayflag" value="1" > Yes
							<input type="radio" name="displayflag" value="0" checked="checked"> No
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name">Confidential<span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12" style="padding-top:6px;">
							<input type="radio" name="confFlag" value="1" > Yes
							<input type="radio" name="confFlag" value="0" checked="checked"> No
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
                            <th class="column-title">Document Type</th>
                            <th class="column-title">Document Sub Type</th>
                            <th class="column-title">User Display</th>
                            <th class="column-title">Confidential</th>
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
	 
});
 
 function getMappedDocsBySocId(){
		var societyid = $('#societyid').val();
		var table = $('#thetable1').DataTable();
			
		
		table .clear() .draw();
		blockUI();
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/getMappedDocsBySocId.do",
	        data :"societyid="+societyid,
	        success: function(response){
	        	var srno=1;
	        	
	        	$.each(response, function(i, item) {
	        		
	        		var removebtn = '<button class="btn btn-xs btn-danger" onClick="removeSocDocViewMapping(\'' + item.socdocviewmappingid + '\')"><i class="fa fa-times"></i></button>';
	        		var disMsg = "No";
	        		var conMsg="No";
	        		
	        		if(item.displayflag=='1')
	        			disMsg = "Yes";
	        		if(item.confFlag=='1')
	        			conMsg = "Yes";
	        		
	        		table.row.add( [
	        			item.doctypename,
	        			item.docsubtypename,
	        			disMsg,
	        			conMsg,
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
 
 function saveSocViewMapping(){
		
		var societyid = $('#societyid').val();
		var doctypeid = $('#doctypeid').val();
		var docsubtypeid = $('#docsubtypeid').val();
		var displayflag = $("input[name='displayflag']:checked").val();  
		var confFlag = $("input[name='confFlag']:checked").val(); 
		blockUI();
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/saveSocViewMapping.do",
	        data :"societyid="+societyid
			        +"&doctypeid="+doctypeid
			        +"&docsubtypeid="+docsubtypeid
			        +"&displayflag="+displayflag
			        +"&confFlag="+confFlag,
		        success: function(response){
		        	if(response.socdocviewmappingid>0) {
		        		getMappedDocsBySocId();
	 	        		notify('success','SUCCESS','Added Successfully',2000);
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
 
 
 function getDocTypes(){
		var societyid = $('#societyid').val();
		blockUI();
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/getDoctypeBySocId.do",
	        data :"societyid="+societyid,
	        success: function(response){ 
	        		var $select = $('#doctypeid');                        
	        	    $select.find('option').remove(); 
	        	    $select.append($("<option />").val('').text(' -- select --'));
	        	    $.each(response, function() {
	        	    	$select.append($("<option />").val(this.doctypeid).text(this.doctypename));
	        	    });
	        	    unblockUI();
	        },
				error : function(e) {
					notify('error','ERROR','Error occured',2000);
					unblockUI();
				}
			});
		getMappedDocsBySocId();
	}
 
	function getDocSubTypes(){
		var doctypeid = $('#doctypeid').val();
		blockUI();
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/getDocSubtypeByDocId.do",
	        data :"doctypeid="+doctypeid,
	        success: function(response){ 
	        		var $select = $('#docsubtypeid');                        
	        	    $select.find('option').remove(); 
	        	    $select.append($("<option />").val('').text(' -- select --'));
	        	    $.each(response, function() {
	        	    	$select.append($("<option />").val(this.docsubtypeid).text(this.docsubtypename));
	        	    });
	        	    unblockUI();
	        },
				error : function(e) {
					notify('error','ERROR','Error occured',2000);
					unblockUI();
				}
			});
	}
 </script>