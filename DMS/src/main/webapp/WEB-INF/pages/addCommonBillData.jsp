<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="col-md-12 col-sm-12 col-xs-12">
              <div class="x_panel tile">
                <div class="x_title">
                  <h2>Add Bill Data</h2>
                  <div class="pull-right">
						<select name="societyid" id="societyid" class="form-control"
							onchange="getCommonBillData()">
							<option value="">-- select society --</option>
							<c:forEach items="${societyList}" var="myItem"
								varStatus="loopStatus">
								<option value="${myItem.societyid}">${myItem.societyname}</option>
							</c:forEach>
						</select>
					</div>
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
                            <th class="column-title">Bill Code</th>
                            <th class="column-title">Bill Cycle</th>
                            <th class="column-title">Created On</th>
                            <th class="column-title">Created By</th>
                            <th class="column-title">Action </th>
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
	 getCommonBillData();
 });
 
 function getCommonBillData(){
	 
	 var table = $('#thetable').DataTable();
	 var societyid = $('#societyid').val();
	 
	 if(societyid.length<1)
		 return false;
	 blockUI();
	 table .clear() .draw();
	 
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/fetchAllBills.do",
	        data :"societyid="+societyid,
	        success: function(response){
		        
		        if(response.length>0){
		        	$.each(response, function(i, item) {
		  
		        		var editBtn = '<a class="btn btn-default btn-sm" onclick="editFormField(\'' + item.billstructureid + '\')"><i class="fa fa-edit"></i></a>';
		        		var billCycle="";
		        		if(item.billcycletype==1){
		        			billCycle = resolveMonth(item.billcyclevalue)+" "+item.year;
		        		}else if(item.billcycletype==3){
		        			billCycle = capAll(item.billcyclevalue)+" "+item.year;
		        		} 
		        		
		        		table.row.add( [
		        			capAll(item.billstructurecode)	,
		        			billCycle, 
		        			new Date(item.createdon).toString("dd MMM yyyy"),
		        			item.username,
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
 
 function editFormField(billstructureid){
	 alert(billstructureid);
	 location.href = "<%=request.getContextPath()%>/addBillParamaters.do?billstructureid="+billstructureid;
 }
    
 </script>