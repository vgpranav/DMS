<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<div class="col-md-12 col-sm-12 col-xs-12">
              <div class="x_panel tile">
                <div class="x_title">
                  <h2>File Monitoring</h2>
                  <ul class="nav navbar-right panel_toolbox">
                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                    </li> 
                  </ul>
                  <div class="clearfix"></div>
                </div>
                <div class="x_content">
                  <div class="dashboard-widget-content">
                  <div class="col-md-12 col-sm-12 col-xs-12">
		                  <div class="row">
		                   <div class="col-md-12 col-sm-12 col-xs-12">
		                    <form id="fileForm" class="form-horizontal form-label-left" onsubmit="return getFileStats();">
		                     
		                    	<div class="form-group">
			                        
			                        <label class="control-label col-md-2 col-sm-2 col-xs-12"
										for="first-name">Builder
									</label>
			                    	<div class="col-md-2 col-sm-2 col-xs-12">
				                    	<select name="builderid" id="builderid" class="form-control " onchange="getProjects()">
				                    	<option value="">-- None --</option>
											<c:forEach items="${builderList}" var="myItem" varStatus="loopStatus">
												<option value="${myItem.builderid}">${myItem.buildername}</option>
											</c:forEach>
										</select>
									</div>
									
									<label class="control-label col-md-2 col-sm-2 col-xs-12"
										for="first-name">Project
									</label>
			                    	<div class="col-md-2 col-sm-2 col-xs-12">
				                    	<select name="projectid" id="projectid" class="form-control " onchange="getSubProjects()">
										</select>
									</div>
									
			                        
									
									
								</div>
								
								<div class="form-group">
									
									<label class="control-label col-md-2 col-sm-2 col-xs-12"
										for="first-name">Society Type 
									</label>
			                    	<div class="col-md-2 col-sm-2 col-xs-12">
				                    	<select name="societytypeid" id="societytypeid" class="form-control " onchange="getSubProjects()">
											<c:forEach items="${societyTypeList}" var="myItem" varStatus="loopStatus">
												<option value="${myItem.societytypeid}">${myItem.societytypename}</option>
											</c:forEach>
										</select>
									</div>
									
									 <label class="control-label col-md-2 col-sm-2 col-xs-12"
										for="first-name">Sub Project / Society
									</label>
			                    	<div class="col-md-2 col-sm-2 col-xs-12">
				                    	<select name="societyid" id="societyid" class="form-control" onchange="">
										</select>
									</div>
									
								</div>
								
								<div class="form-group">
									<hr/>
								</div>
								
								
								<div class="form-group">
								
									<label class="control-label col-md-2 col-sm-2 col-xs-12"
										for="first-name">Document Type
									</label>
									<div class="col-md-2 col-sm-2 col-xs-12">
										<select name="doctypeid" id="doctypeid" class="form-control"  onchange="getDocSubTypes()">
										<option value="">-- All --</option>
										<c:forEach items="${docTypesList}" var="myItem" varStatus="loopStatus">
											<option value="${myItem.doctypeid}">${myItem.doctypename}</option>
										</c:forEach>
										</select>
									</div>
									
									
									<label class="control-label col-md-2 col-sm-2 col-xs-12"
										for="first-name">Document Sub Type 
									</label>
									<div class="col-md-2 col-sm-2 col-xs-12">
										<select name="docsubtypeid" id="docsubtypeid" class="form-control">
											<option value=""> -- select -- </option>
										</select>
									</div>
									
								</div>
							    
							    <div class="form-group">
									<hr/>
								</div>
								
								<div class="form-group">
									<label class="control-label col-md-2 col-sm-2 col-xs-12"
										for="first-name">Member 
									</label>
									<div class="col-md-2 col-sm-2 col-xs-12">
										 	<input type="hidden" id="userid" name="userid"
											required="required" class="form-control col-md-8 col-xs-12">	
											<input type="text" id="username" name="username"  class="form-control col-md-8 col-xs-12">
									</div>
								</div>
								
								
								 <div class="form-group" align="center">
									<button class="btn btn-warning" type="reset">Reset</button>
									<button class="btn btn-success" type="submit">Show</button>
								</div>
								
							</form>
		                   </div>
		                   </div>
		                   
		                   <br><br>
		                   <hr>
		                   
		                   
		                   <div class="row tile_count" align="center">
				            <div class="col-md-4 col-sm-4 col-xs-6 tile_stats_count">
				              <span class="count_top"><i class="fa fa-book"></i> Total Documents</span>
				              <div class="count" id="documentcount">0</div>
				            </div>
				            
				            <div class="col-md-4 col-sm-4 col-xs-6 tile_stats_count">
				              <span class="count_top"><i class="fa fa-clone"></i> Total Pages</span>
				              <div class="count" id="pagecount">0</div>
				            </div>
				           
				            <div class="col-md-4 col-sm-4 col-xs-6 tile_stats_count">
				              <span class="count_top"><i class="fa fa-database"></i> Total Size (MB)</span>
				              <div class="count" id="size">0</div>
				            </div>
				           
				          </div>
                   </div>
                   
                  </div>
                </div>
              </div>
              
 </div>
 
 
 <script type="text/javascript">
    
var isJpg = function(name) {
    return name.match(/jpg$/i)
};
    
var isPng = function(name) {
    return name.match(/png$/i)
};
    
$(document).ready(function() {
    
	
	$('#thetableUA').DataTable();
	
    $("#username").click(function(){
		//$('#userid').val("");
		if($('#societyid').val()!=null)
			{
				if($('#societyid').val().length<1)
				{ alert('Select Society');
					$('#societyid').focus();
				}
			}else{
				alert('Select Society');
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
    
    
function getProjects(){
	var builderid = $('#builderid').val();
	var societytypeid = $('#societytypeid').val();
	
	if(builderid!=""){
		blockUI();
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/getProjectsByBuilderId.do",
	        data :"builderid="+builderid,
	        success: function(response){ 
	        		var $select = $('#projectid');                        
	        	    $select.find('option').remove(); 
	        	    $select.append($("<option />").val('').text(' -- select --'));
	        	    $.each(response, function() {
	        	    	$select.append($("<option />").val(this.projectid).text(this.projectname));
	        	    });
	        	    unblockUI();
	        },
				error : function(e) {
					notify('error','ERROR','Error occured',2000);
					unblockUI();
				}
			});
	}else{
		blockUI();
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/getSocietyList.do",
	        success: function(response){ 
	        		var $select = $('#societyid');                        
	        	    $select.find('option').remove(); 
	        	    $select.append($("<option />").val('').text(' -- select --'));
	        	    $.each(response, function() {
	        	    	if(societytypeid==this.societytypeid)
	        	    		$select.append($("<option />").val(this.societyid).text(this.societyname));
	        	    });
	        	    unblockUI();
	        },
				error : function(e) {
					notify('error','ERROR','Error occured',2000);
					unblockUI();
				}
			});
		$('#projectid').val("");
	}
	
}
function getDocSubTypes(){
	var doctypeid = $('#doctypeid').val();
	
	if(doctypeid.length<1)
		return false;
	
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
    
function getSubProjects(){
	var projectid = $('#projectid').val();
	var societytypeid = $('#societytypeid').val();
	blockUI();
	$.ajax({
        type: "GET",
        url: "<%=request.getContextPath()%>/getSubProjectsByProjectId.do",
        data :"projectid="+projectid,
        success: function(response){ 
        		var $select = $('#societyid');                        
        	    $select.find('option').remove(); 
        	    $select.append($("<option />").val('').text(' -- select --'));
        	    $.each(response, function() {
        	    	if(societytypeid==this.societytypeid)
        	    		$select.append($("<option />").val(this.societyid).text(this.societyname));
        	    });
        	    unblockUI();
        },
			error : function(e) {
				notify('error','ERROR','Error occured',2000);
				unblockUI();
			}
		});
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
function getFileStats(){
	var reqStr = "size=0";
	

	var userid = $('#userid').val();
	var docsubtypeid = $('#docsubtypeid').val();
	var doctypeid = $('#doctypeid').val();
	var societyid = $('#societyid').val();
	var societytypeid = $('#societytypeid').val();
	var projectid = $('#projectid').val();
	var builderid = $('#builderid').val();
	/* var documentcount = $('#documentcount').val();
	var pagecount = $('#pagecount').val();
	var size = $('#size').val(); */

	if(userid!=null && userid.length>0)
		reqStr+="&userid="+userid;
	if(docsubtypeid!=null && docsubtypeid.length>0)
		reqStr+="&docsubtypeid="+docsubtypeid;
	if(doctypeid!=null && doctypeid.length>0)
		reqStr+="&doctypeid="+doctypeid;
	if(societyid!=null && societyid.length>0)
		reqStr+="&societyid="+societyid;
	if(societytypeid!=null && societytypeid.length>0)
		reqStr+="&societytypeid="+societytypeid;
	if(projectid!=null && projectid.length>0)
		reqStr+="&projectid="+projectid;
	if(builderid!=null && builderid.length>0)
		reqStr+="&builderid="+builderid;
	
	blockUI();
	$.ajax({
        type: "GET",
        url: "<%=request.getContextPath()%>/getFileStats.do",
        data :reqStr,
        
        success: function(response){
        	$('#documentcount').html(response.documentcount);
        	$('#pagecount').html(response.pagecount);
        	$('#size').html((response.size)/1000000);
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

