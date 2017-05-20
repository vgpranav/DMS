<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>



<div class="col-md-12 col-sm-12 col-xs-12">
              <div class="x_panel tile">
                <div class="x_title">
                  <h2>Add Document Sub Type</h2>
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
 				<form id="addDocSubTypeForm" data-parsley-validate
					class="form-horizontal form-label-left" action="saveDocumentSubType.do"
					method="post">

					<input type="hidden" id="docsubtypeid" name="docsubtypeid" value="0">
								
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name">Document Name <span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<input type="text" id="docsubtypename" name="docsubtypename"
								required="required" class="form-control col-md-7 col-xs-12">
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name">Document Description 
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<input type="text" id="docsubtypedesc" name="docsubtypedesc"
								 class="form-control col-md-7 col-xs-12">
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name">Parent Document <span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<select name="doctypeid" id="doctypeid" class="form-control">
							<c:forEach items="${docTypesList}" var="myItem" varStatus="loopStatus">
								<option value="${myItem.doctypeid}">${myItem.doctypename}</option>
							</c:forEach>
							</select>
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
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name">Display To User<span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12" style="padding-top:6px;">
							<input type="radio" name="displayflag" value="1" > Yes
							<input type="radio" name="displayflag" value="0" checked="checked"> No
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
                  <h2>Existing Sub Types</h2>
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
                            <th class="column-title">Document SubType Name</th>
                            <th class="column-title">Document Description </th>
                            <th class="column-title">Parent Document </th>
                            <th class="column-title">Created By </th>
                            <th class="column-title">Created On </th>
                            <th class="column-title">Status </th>
                            <th class="column-title">Display to user </th>
                            <th class="column-title">Edit</th>
                            <th class="column-title">Delete</th>
                             
                          </tr>
                        </thead>

                        <tbody>
                        	<c:forEach items="${docSubTypeList}" var="myItem" varStatus="loopStatus">
								<c:if test="${loopStatus.index%2==0}">
									<tr class="even pointer">
								</c:if>
								<c:if test="${loopStatus.index%2!=0}">
									<tr class="odd pointer">
								</c:if>
									<td class=" ">${myItem.docsubtypename}</td>
									<td class=" ">${myItem.docsubtypedesc}</td>
									<td class=" ">${myItem.doctypename}</td>
									<td class=" ">${myItem.createdby}</td>
									<td class=" "><fmt:formatDate type = "date" value = "${myItem.createdon}" /></td>
									<td class=" ">
									<c:if test="${myItem.active==1}">
											Active
										</c:if>
										<c:if test="${myItem.active!=1}">
											Inactive
									</c:if>
									</td>								
									<td class=" ">
									<c:if test="${myItem.displayflag==1}">
											Yes 
										</c:if>
										<c:if test="${myItem.displayflag!=1}">
											No
									</c:if>
									</td>								
									<td class=" ">
										<a class="btn btn-default btn-sm" onclick="editDocSubtype('${myItem.docsubtypeid}')">
											<i class="fa fa-edit"></i>
										</a>
									</td>
									<td class=" ">
										<a class="btn btn-default btn-sm" onclick="genericRemove('${myItem.docsubtypeid}','docsubtype','docsubtypeid','reload')">
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
 </div>
 

<script>
	$(document).ready(function(){
		$('#thetable').DataTable();
	});

	
function editDocSubtype(docsubtypeid){
		
		editMode();
		blockUI();
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/getDocumentSubTypeById.do",
	        data :"docsubtypeid="+docsubtypeid,
	        success: function(response){
	        	if(response.docsubtypeid>0) {
	        		//notify('success','SUCCESS','Added Successfully',2000);
	        		$('#docsubtypeid').val(response.docsubtypeid);
	        		$('#docsubtypename').val(response.docsubtypename);
	        		$('#docsubtypedesc').val(response.docsubtypedesc);
	        		$('#doctypeid option[value="'+response.doctypeid+'"]').prop("selected",true).change();
	        		$('input[name=active][value="'+response.active+'"]').prop("checked","checked").change();
	        		$('input[name=displayflag][value="'+response.displayflag+'"]').prop("checked","checked").change();
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
