<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="col-md-12 col-sm-12 col-xs-12">
              <div class="x_panel tile">
                <div class="x_title">
                  <h2>View Bills</h2>
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
                            <th class="column-title">Generated On</th>
                            <th class="column-title">Paid On</th>
                            <th class="column-title">Status</th>
                            <th class="column-title">View Bill</th>
                          </tr>
                        </thead>

                        <tbody>
                        </tbody>
                      </table>
                    </div>
                  </div>
                </div>
              </div>
              
              
              <div class="x_panel tile" >
                <div class="x_title">
                  <h2>Bill</h2>
                  <ul class="nav navbar-right panel_toolbox">
                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                    </li> 
                  </ul>
                  <div class="clearfix"></div>
                </div>
                <div class="x_content">
                  <div class="dashboard-widget-content">
                     
                     <div class="col-md-8 col-md-offset-2">
	                     <div class="table-responsive" id="billHTML">
	                     </div> 
					 </div>
					
                  </div>
                </div>
              </div>
              
 </div>
 
 <script>
 
 $(document).ready(function(){
	 var table = $('#thetable').DataTable();
	 getCommonBillData();
 });
 
 function getCommonBillData(){
	 
	 var table = $('#thetable').DataTable(); 
	 var userid = '${userid}';
	 
	 if(userid.length<1)
		 return false;
	 
	 blockUI();
	 table .clear() .draw();
	 
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/fetchAllBillsForUser.do",
	        data :"createdby="+userid,
	        success: function(response){ 
	        	
		        if(response.length>0){
		        	
		        	$.each(response, function(i, item) {
		   				
		        		 
		        		var editBtn = '<a class="btn btn-success btn-sm" onclick="getMemberBillbyId(\'' + item.billstructureid + '\')"><i class="fa fa-external-link-square"></i></a>';
		        		var billCycle="";
		        		
		        		 
		        		if(item.billcycletype==1){
		        			billCycle = resolveMonth(item.billcyclevalue)+" "+item.year;
		        		}else if(item.billcycletype==3){
		        			billCycle = capAll(item.billcyclevalue)+" "+item.year;
		        		} 
		        		 
		        		var isPaid="";
		        		if(item.ispaid==1){
		        			isPaid = '<span class="label label-success">Paid</span>';
		        		}else{
		        			isPaid = '<span class="label label-danger">Pending</span>';
		        		}
		        		
		        		var ispaidon="";
		        		if(item.ispaidon!=null && item.ispaidon!="")
		        			ispaidon = new Date(item.ispaidon).toString("dd MMM yyyy");
		        		
		        		
			        		table.row.add( [
			        			capAll(item.billstructurecode)	,
			        			billCycle, 
			        			new Date(item.createdon).toString("dd MMM yyyy"), 
			        			ispaidon,
			        			isPaid,
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
  
 
 
function getMemberBillbyId(billstructureid){
	 
	 var userid = '${userid}';
	  
	 
	 blockUI();
	 $.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/getMemberBillbyId.do",
	        data :"billstructureid="+billstructureid+"&createdby="+userid,
	        success: function(response){ 
		        if(response!=""){
		        	
		        	var billHTML = "<table class='table table-bordered table-condensed table-hover'>";
		        	
		        	billHTML += ""; 
	        		billHTML += "<tr class='success'><td colspan='4' align='center'><b>Monthly Maintenance Bill</b></td></tr>";
	        		
		        			billHTML += "<tr class='active'>";
		        			billHTML += "<td colspan='4' align='center'>";
		        			billHTML += "<b><font size='+1'>"+response.society.societyname+"</font></b>";
		        			billHTML += "</td>"; 
		        			billHTML += "</tr>";
		        			
		        			billHTML += "<tr>";
		        			billHTML += "<td colspan='4' align='center'>";
		        			billHTML += "Registration No: "+response.society.registrationno+"";
		        			billHTML += "</td>"; 
		        			billHTML += "</tr>"; 
		        		 
		        	 
		        			var mthval="";
		        			
		        			billHTML += "<tr>";
		        			
		        			billHTML += "<td colspan='' align='left' width='25%'>";
		        			billHTML += "Bill Code";
		        			billHTML += "</td>"; 
		        			billHTML += "<td colspan='' align='left' width='25%'>";
		        			billHTML += ""+response.billStrut.billstructurecode+"";
		        			billHTML += "</td>"; 
		        			
		        			if(response.billStrut.billcycletype=='1'){
		        				mthval = resolveMonth(response.billStrut.billcyclevalue);
		        			}else{
		        				mthval = response.billStrut.billcyclevalue;
		        			}
		        			
		        			
		        			billHTML += "<td colspan='' align='left' width='25%'>";
		        			billHTML += "Bill Date";
		        			billHTML += "</td>"; 
		        			billHTML += "<td colspan='' align='left' width='25%'>";
		        			billHTML += mthval+" - "+response.billStrut.year+"";
		        			billHTML += "</td>"; 
		        			
		        			billHTML += "</tr>";  
		        	
		         
							billHTML += "<tr>";
		        			
		        			billHTML += "<td colspan='' align='left' width='25%'>";
		        			billHTML += "Flat No";
		        			billHTML += "</td>"; 
		        			billHTML += "<td colspan='' align='left' width='25%'>";
		        			billHTML += ""+response.user.flatno+"";
		        			billHTML += "</td>"; 
		        			 
		        			billHTML += "<td colspan='' align='left' width='25%'>";
		        			billHTML += "Wing";
		        			billHTML += "</td>"; 
		        			billHTML += "<td colspan='' align='left' width='25%'>";
		        			billHTML += ""+response.user.wing+"";
		        			billHTML += "</td>"; 
		        			 
		        			billHTML += "</tr>";  
		        			
							billHTML += "<tr>";
		        			
		        			billHTML += "<td colspan='' align='left' width='25%'>";
		        			billHTML += "Floor";
		        			billHTML += "</td>"; 
		        			billHTML += "<td colspan='' align='left' width='25%'>";
		        			billHTML += ""+response.user.floor+"";
		        			billHTML += "</td>"; 
		        			 
		        			billHTML += "<td colspan='' align='left' width='25%'>";
		        			billHTML += "Tower";
		        			billHTML += "</td>"; 
		        			billHTML += "<td colspan='' align='left' width='25%'>";
		        			billHTML += ""+response.user.tower+"";
		        			billHTML += "</td>"; 
		        			 
		        			billHTML += "</tr>"; 
		        		 
		        		
		        		billHTML += "<tr><td colspan='4'>&nbsp;</td></tr>";
		        		
		        		billHTML += "<tr class='active'>";
        				billHTML += "<td colspan='1' align='left'><strong>Sr.No.</strong></td>";
        				billHTML += "<td colspan='2' align='left'><strong>Particular</strong></td>";
        				billHTML += "<td colspan='1' align='right'><strong>Charges</strong></td>";
        				billHTML += "</tr>"; 
        				
		        		var srno = 1;
		        		$.each(response.billdata, function(i, item) {   
		        				billHTML += "<tr>";
		        				
		        				billHTML += "<td colspan='1' align='left'>"; 
		        				billHTML += srno++; 
		        				billHTML += "</td>";
		        				
		        				billHTML += "<td colspan='2' align='left'>"; 
		        				billHTML += item.componenetname; 
		        				billHTML += "</td>";
		        				
		        				billHTML += "<td colspan='1' align='right'>"; 
		        				billHTML += item.componenetvalue; 
		        				billHTML += "</td>";
		        				
		        				billHTML += "</tr>"; 
		        		});
		        	
		        		billHTML += "<tr class='active'>";
	        			  
	        			billHTML += "<td colspan='3' align='right'>";
	        			billHTML += "<b>Amount Payable</b>";
	        			billHTML += "</td>"; 
	        			billHTML += "<td colspan='1' align='right'>";
	        			billHTML += ""+response.bill.payamount+"";
	        			billHTML += "</td>"; 
	        			 
	        			billHTML += "</tr>"; 
		        		
		        	billHTML += "</table>";
		        	
		        	var paybtn="";
		        	if(response.bill.ispaid!=1)
		        		paybtn = '<a class="btn btn-success btn-sm" onclick="payBillByBillId(\'' + response.bill.billid + '\')"> <i class="fa fa-play"></i> Pay Now</a>';
		        		
		        	billHTML += "<br/><div align='center'>"+paybtn+"</div>";
		        		
		        	$('#billHTML').html(billHTML)
		        	
		        	 
		        }else {
		        	notify('error','ERROR','Failed to Fetch Bill',2000);
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
	 var societyid = $('#societyid').val();
	 location.href = "<%=request.getContextPath()%>/viewBillsForSociety.do?billstructureid="+billstructureid+"&societyid="+societyid;
 }
 </script>