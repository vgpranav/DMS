<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="col-md-12 col-sm-12 col-xs-12">
              <div class="x_panel tile">
                <div class="x_title">
                  <h2>Society Document Mapping</h2>
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
					class="form-horizontal form-label-left" action="addSocDocMapping.do"
					method="post" onsubmit="">

				
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name"> Society Name <span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<select name="societyid" id="societyid" class="form-control">
							<option value=""> -- select society -- </option>
							<c:forEach items="${societyList}" var="myItem" varStatus="loopStatus">
								<option value="${myItem.societyid}">${myItem.societyname}</option>
							</c:forEach>
						</select>
						</div>
						
						</div>
						<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name"> Document Type <span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<select name="doctypeid" id="doctypeid" class="form-control" onchange="getCommitteMembersForSociety()">
							<option value=""> -- select document -- </option>
							<c:forEach items="${docTypes}" var="myItem" varStatus="loopStatus">
								<option value="${myItem.doctypeid}">${myItem.doctypename}</option>
							</c:forEach>
						</select>
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
                            <th class="column-title">Society Name</th>
                            <th class="column-title">Document Name</th>
                            <th class="column-title" width="10%">Remove</th>
                          </tr>
                        </thead>

                         <tbody>
                        	<c:forEach items="${socDocMapping}" var="myItem" varStatus="loopStatus">
								<c:if test="${loopStatus.index%2==0}">
									<tr class="even pointer" id="ptr${loopStatus.index}">
								</c:if>
								<c:if test="${loopStatus.index%2!=0}">
									<tr class="odd pointer" id="ptr${loopStatus.index}">
								</c:if>
									<%-- <td class=" ">${loopStatus.index+1}</td> --%>
									<td class=" ">${myItem.societyname}</td>
									<td class=" ">${myItem.doctypename}</td> 
									<td class=" ">
										<a class="btn btn-default btn-sm" onclick="removeSocDocMapping('${myItem.societydocmappingid}','ptr${loopStatus.index}')">
											<i class="fa fa-times"></i>
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
 
 <script>
 $(document).ready(function(){
	 
	 $('#thetable1').DataTable();
	 
});
 
  
 
 function removeSocDocMapping(societydocmappingid,rowid){
	 
		if(confirm('Are you Sure?')){
			blockUI();
			$.ajax({
		        type: "GET",
		        url: "<%=request.getContextPath()%>/removeSocietyDocmapping.do",
		       data :"societydocmappingid="+societydocmappingid,
		        success: function(response){
		        //alert()
		        	if(response=='success') {
		        		notify('success','SUCCESS','Removed Successfully',2000);
		        		//location.reload();
		        		console.log('rowid :: '+rowid)
		        		$('#'+rowid).hide();
		        	}  else {
		        		notify('error','Failed','Failed to Remove',2000);
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
 
  
 
 function addSocietyManager(){
		var societyid 		= $('#societyid').val();
		var userid 			= $('#userid').val();
		
		if(societyid.length < 1 || userid.length < 1 ){
			alert('Some Mandatory Fields  are Missing');
			return false;
		} else {
			$('#userid').val("");
			$('#username').val("");
			blockUI();
			$.ajax({
		        type: "GET",
		        url: "<%=request.getContextPath()%>/addSocietyManager.do",
		       data :"societyid="+societyid
	        	+"&userid="+userid,
		        success: function(response){
		        	if(response.societymanagerid>0) {
		        	notify('success','SUCCESS','Added Successfully',2000);
		        		getCommitteMembersForSociety();
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