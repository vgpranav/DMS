<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="col-md-12 col-sm-12 col-xs-12">
              <div class="x_panel tile">
                <div class="x_title">
                  <h2>Add Document <small>Select Data</small></h2>
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
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name">Common Document<span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<input type="checkbox" name="common" id="common" value="yes" onclick="changememreq()">&nbsp;Yes
						</div>
					</div>
					
					<div class="form-group userselector">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name">Society Member <span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							 	<input type="hidden" id="userid" name="userid"
								required="required" class="form-control col-md-8 col-xs-12">	
								<input type="text" id="username" name="username"
								required="required" class="form-control col-md-8 col-xs-12">
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
					
					<input type="hidden" name="commondoc" id="commondoc" value="false"> 
					
 				</form>
 			</div>
 			</div>
                  </div>
                </div>
              </div>
 </div>
 

<script>

	function changememreq(){
		var common =  $('#common').is(":checked")
		if(common){
			 	$('#username').attr("disabled","disabled");
			 	$('#userid').attr("disabled","disabled");
			 	$('#username').removeAttr('required');
			 	$('#userid').removeAttr('required');
			 	$('.userselector').hide('fade');
			 	$('#commondoc').val("true");
			 	
			 	}
			 else{
				 $('#username').removeAttr('disabled');
				 $('#userid').removeAttr('disabled');
				 $('#username').attr("required","required");
				 $('#userid').attr("required","required");
				 $('.userselector').show('fade');
				 $('#commondoc').val("false");
			 }
		} 
	
	$(document).ready(function(){
		$('#thetable').DataTable();
		
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
	        	unblockUI();
	        },
				error : function(e) {
					notify('error','ERROR','Error occured',2000);
					unblockUI();
				}
			});
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
	
	
	function saveFormFields(){
		
		var docsubtypeid = $('#docsubtypeid').val();
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
</script>