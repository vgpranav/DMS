<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

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
                     
                    <div class="row">
						<div class="col-md-6 col-md-offset-3 col-sm-offset-3 col-sm-6 col-xs-12">
 							<form id="addDocSubTypeForm" data-parsley-validate
								class="form-horizontal form-label-left" action="saveDocumentSubType.do"
									method="post">
					
									<div class="form-group">
										<label class="control-label col-md-4 col-sm-4 col-xs-12"
											for="first-name">Select Member<span class="required">*</span>
										</label>
										<div class="col-md-8 col-sm-8 col-xs-12">
											<select name="userid" id="userid" class="form-control" onchange="getMemberBillbyId()">
											<c:forEach items="${users}" var="myItem" varStatus="loopStatus">
												<option value="${myItem.userid}">${myItem.firstName} ${myItem.lastName}</option>
											</c:forEach>
											</select>
										</div>
									</div>
									
							</form>
						</div>
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
 
 function getMemberBillbyId(){
	 
	 var userid = $('#userid').val();
	 var billstructureid = '${billstructureid}';
	 
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
 
 </script>