<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="row">
	<div class="col-md-12 col-sm-12 col-xs-12">

		<div class="dashboard_graph">

			<div class="row x_title">
				<div class="col-md-6">
					<h3>
						Add Document <small>Select Data</small>
					</h3>
				</div>
			</div>
			
			<div class="row">
			<div class="col-md-6 col-md-offset-3 col-sm-offset-3 col-sm-6 col-xs-12">
 				<form id="addDocSubTypeForm" data-parsley-validate
					class="form-horizontal form-label-left" action="addDocument2.do"
					method="post">

					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name">Select Society <span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<select name="societyid" id="societyid" class="form-control" onchange="getDocTypes()">
							<option value=""> -- select -- </option>
							<c:forEach items="${societyList}" var="myItem" varStatus="loopStatus">
								<option value="${myItem.societyid}">${myItem.societyname}</option>
							</c:forEach>
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name">Document Type<span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<select name="doctypeid" id="doctypeid" class="form-control" onchange="getDocSubTypes()">
								<option value=""> -- select -- </option>
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name">Document Sub Type<span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<select name="docsubtypeid" id="docsubtypeid" class="form-control">
								<option value=""> -- select -- </option>
							</select>
						</div>
					</div>
					
					<div class="form-group" align="right">
						<button class="btn btn-warning" type="reset">Reset</button>
						<button class="btn btn-success">Next</button>
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
			 					
			<div class="clearfix"></div>
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
		
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/getFieldsForDocSubtype.do",
	        data :"docsubtypeid="+docsubtypeid,
	        success: function(response){
	        	$.each(response, function(i, item) {
	  
	        		table.row.add( [
	        			item.fieldname,
	        			item.datatype,
	        			item.fieldtype,
	        			item.sequence,
	        			item.createdby,
	        			new Date(item.createdon).toString("dd MMM yyyy"),
	        			item.active,
	        			item.fieldid,
	                ] ).draw( false );
	        	    
	        	  });
	        },
				error : function(e) {
					notify('error','ERROR','Error occured',2000);
				}
			});
	}
	
	
	
	function getDocTypes(){
		var societyid = $('#societyid').val();
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
	        },
				error : function(e) {
					notify('error','ERROR','Error occured',2000);
				}
			});
	}
	
	
	function getDocSubTypes(){
		var doctypeid = $('#doctypeid').val();
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
	        },
				error : function(e) {
					notify('error','ERROR','Error occured',2000);
				}
			});
	}
	
	
	function saveFormFields(){
		
		var docsubtypeid = $('#docsubtypeid').val();
		var fieldname = $('#fieldname').val();
		var datatype = $('#datatype').val();
		var sequence = $('#sequence').val();
		var fieldtype = $('#fieldtype:checked').val();
		var active = $('#active:checked').val();
		
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/saveFormFields.do",
	        data :"docsubtypeid="+docsubtypeid
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
	        },
				error : function(e) {
					notify('error','ERROR','Error occured',2000);
				}
			});
		
		return false;
	}
</script>