<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="col-md-12 col-sm-12 col-xs-12">
              <div class="x_panel tile">
                <div class="x_title">
                  <h2>Add Document Fields</h2>
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
					class="form-horizontal form-label-left" action="saveFormFields.do"
					method="post" onsubmit="return saveFormFields()">

					<input type="hidden" id="fieldid" name="fieldid" value="0">
								
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name">Select Document <span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<select name="docsubtypeid" id="docsubtypeid" class="form-control" onchange="getFieldsForDocSubtype()">
							<option value=""> -- select --</option>
							<c:forEach items="${docSubTypeList}" var="myItem" varStatus="loopStatus">
								<option value="${myItem.docsubtypeid}">${myItem.docsubtypename}</option>
							</c:forEach>
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name">Field Name <span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<input type="text" id="fieldname" name="fieldname"
								required="required" class="form-control col-md-7 col-xs-12">
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name">Data Type <span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<select name="datatype" id="datatype" class="form-control">
								<option value="text">Text</option>
								<option value="date">Date</option>
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name">Sequence <span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<select name="sequence"  id="sequence" class="form-control">
								<c:forEach items="${seqList}" var="myItem" varStatus="loopStatus">
									<option value="${myItem}">${myItem}</option>
								</c:forEach> 
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name">Field Type <span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<input type="radio" id="fieldtype" name="fieldtype" required="required"  value="mandatory"> Mandatory
							<input type="radio" id="fieldtype" name="fieldtype" required="required"  value="optional" checked="checked"> Optional
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name">Status<span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12" style="padding-top:6px;">
							<input type="radio" name="active" id="active" value="1" checked="checked"> Active
							<input type="radio" name="active" id="active" value="0"> Inactive
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
                  <h2>Existing Form Fields</h2>
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
                            <th class="column-title">Field Name</th>
                            <th class="column-title">Data Type </th>
                            <th class="column-title">Field Type </th>
                            <th class="column-title">Sequence </th>
                            <th class="column-title">Created By </th>
                            <th class="column-title">Created On </th>
                            <th class="column-title">Status </th>
                            <th class="column-title no-link last">
                            	<span class="nobr">Action</span>
                            </th>
                          </tr>
                        </thead>

                        <tbody>
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

	function getFieldsForDocSubtype(){
		var docsubtypeid = $('#docsubtypeid').val();
		var table = $('#thetable').DataTable();
			
		table .clear() .draw();
		blockUI();
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/getFieldsForDocSubtype.do",
	        data :"docsubtypeid="+docsubtypeid,
	        success: function(response){
	        
	        if(response.length>0){
	        	$.each(response, function(i, item) {
	  
	        		var activeflag = "Inactive";
	        		if(item.active==1)
	        			activeflag = "Active";
	        		
	        		var editBtn = '<a class="btn btn-default btn-sm" onclick="editFormField(\'' + item.fieldid + '\')"><i class="fa fa-edit"></i></a>';
	        		
	        		table.row.add( [
	        			item.fieldname,
	        			item.datatype,
	        			item.fieldtype,
	        			item.sequence,
	        			item.createdby,
	        			new Date(item.createdon).toString("dd MMM yyyy"),
	        			activeflag,
	        			editBtn,
	                ] ).draw( false );
	        	    
	        	  });
	        	}
	        unblockUI();
	        },
				error : function(e) {
					notify('error','ERROR','Error occured',2000);
					unblockUI();
				}
			});
	}
	
	function saveFormFields(){
		
		var docsubtypeid = $('#docsubtypeid').val();
		var fieldid = $('#fieldid').val();
		var fieldname = $('#fieldname').val();
		var datatype = $('#datatype').val();
		var sequence = $('#sequence').val();
		var fieldtype = $('#fieldtype:checked').val();
		var active = $('#active:checked').val();
		blockUI();
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/saveFormFields.do",
	        data :"docsubtypeid="+docsubtypeid
	        		+"&fieldid="+fieldid
	       			 +"&fieldname="+fieldname
			        +"&datatype="+datatype
			        +"&sequence="+sequence
			        +"&fieldtype="+fieldtype
			        +"&active="+active,
	        success: function(response){
	        	if(response.fieldid>0) {
	        		getFieldsForDocSubtype();
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
	
	function editFormField(fieldid){
		
		editMode();
		blockUI();
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/getFormFieldById.do",
	        data :"fieldid="+fieldid,
	        success: function(response){
	        	if(response.fieldid>0) {
	        		//notify('success','SUCCESS','Added Successfully',2000);
	        		$('#fieldid').val(response.fieldid);
	        		$('#docsubtypeid option[value="'+response.docsubtypeid+'"]').prop("selected",true).change();
	        		$('#fieldname').val(response.fieldname);
	        		$('#datatype option[value="'+response.datatype+'"]').prop("selected",true).change();
	        		$('#sequence option[value="'+response.sequence+'"]').prop("selected",true).change();
	        		$('input[name=active][value="'+response.active+'"]').prop("checked","checked").change();
	        		$('input[name=fieldtype][value="'+response.fieldtype+'"]').prop("checked","checked").change();
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