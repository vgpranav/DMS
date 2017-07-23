<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<div class="col-md-12 col-sm-12 col-xs-12">
              <div class="x_panel tile">
                <div class="x_title">
                  <h2>View User Activity</h2>
                  <ul class="nav navbar-right panel_toolbox">
                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                    </li> 
                  </ul>
                  <div class="clearfix"></div>
                </div>
                <div class="x_content">
                  <div class="dashboard-widget-content">
                  <div class="col-md-4 col-sm-4 col-xs-12">
		                  <div class="row">
		                   <div class="col-md-12 col-sm-12 col-xs-12">
		                    <form id="fileForm" class="form-horizontal form-label-left" onsubmit="return getUserActivityLog();">
		                     
		                    <div class="form-group">
		                        <label class="control-label col-md-4 col-sm-4 col-xs-12"
									for="first-name">Society Name 
								</label>
		                    	<div class="col-md-8 col-sm-8 col-xs-12">
			                    	<select name="societyid" id="societyid" class="form-control " onchange="getDocTypes()">
										<c:forEach items="${societyList}" var="myItem" varStatus="loopStatus">
											<option value="${myItem.societyid}">${myItem.societyname}</option>
										</c:forEach>
									</select>
								</div>
								</div>
								<div class="form-group">
								<label class="control-label col-md-4 col-sm-4 col-xs-12"
									for="first-name">Society Member
								</label>
								<div class="col-md-8 col-sm-8 col-xs-12">
									 	<input type="hidden" id="userid" name="userid"
										required="required" class="form-control col-md-8 col-xs-12">	
										<input type="text" id="username" name="username"
										required="required" class="form-control col-md-8 col-xs-12" placeholder="Member Name">
								</div> 
								</div>
								
								<label class="control-label col-md-4 col-sm-4 col-xs-12"
									for="first-name">Date </label>
								<div class="col-md-8 col-sm-8 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="actiondate" name="actiondate"
											class="form-control col-md-12 col-xs-12 customdatepicker"
											readonly="readonly">
									</div>
								</div>
								
								
								
								<div class="form-group" align="center">
								<br/><br/><br/>
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="submit" value="View" class="btn btn-primary">
									</div>
							    </div>
							    
							</form>
		                   </div>
		                   </div>
                   </div>
                   
                   
                   <div class="col-md-7 col-md-offset-1 col-sm-7 col-sm-offset-1 col-xs-12" >
	                   <div class="row">
	                   		<div class="table-responsive   col-sm-12 col-md-12 col-xs-12"  >
								<table class="table table-striped jambo_table bulk_action"
									id="thetableUA">
									<thead>
										<tr class="headings"> 
											<th class="column-title">Sr No</th>
											<th class="column-title">Activity</th>
											<th class="column-title">Time</th>
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
    
function getUserActivityLog(){
	var userid = $('#userid').val();
	var table = $('#thetableUA').DataTable();
	var actiondate = $('#actiondate').val();
	
	if(userid.length<1)
		return false;
	
	var reqStr = "userid="+userid;
	
	if(actiondate.length>0)
		reqStr += "&firstName="+actiondate;
	
	table.clear().draw();
	
	blockUI();
	$.ajax({
        type: "GET",
        url: "<%=request.getContextPath()%>/getUserActivityLog.do",
        data :reqStr,
        
        success: function(response){
        	var srno=1;
        	$.each(response, function(i, item) {
        		
        		table.row.add([
        			srno,
        			item.action,
        			new Date(item.actiondate).toString("dd MMM yyyy HH:mm"),
                ]).draw( false );
        		
        		srno++;
        	 });
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

