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
 
 <div id="confOTPDialog">
 	<div class="row">
 		<div class="col-sm-10">
 		<br><br>
 		   
 		<form id="addDocSubTypeForm" data-parsley-validate
					class="form-horizontal form-label-left" action="saveDocumentSubType.do"
					method="post">
			
			 <input type="hidden" id="billIdTopay" value="">		
			
			 <div class="form-group">
				<label class="control-label col-md-4 col-sm-4 col-xs-12"
					for="first-name">Mode of payment<span class="required">*</span>
				</label>
				<div class="col-md-8 col-sm-8 col-xs-12">
					<select name="payMode" id="payMode" class="form-control" onchange="changePayType()">
						 <option value="cheque">Cheque</option>
						 <option value="cash">Cash</option>
						 <option value="netbanking">Netbanking</option>
					</select>
				</div>
			</div>
			
			<div class="form-group"  id="ROWbankName">
				<label class="control-label col-md-4 col-sm-4 col-xs-12"
					for="first-name">Name of Bank
				</label>
				<div class="col-md-8 col-sm-8 col-xs-12">
					 <input type="text" id="bankName" name="bankName"
								required="required" class="form-control col-md-7 col-xs-12">
				</div>
			</div>
			
			<div class="form-group"  id="ROWaccountNo">
				<label class="control-label col-md-4 col-sm-4 col-xs-12"
					for="first-name">Account Number
				</label>
				<div class="col-md-8 col-sm-8 col-xs-12">
					 <input type="text" id="accountNo" name="accountNo"
								required="required" class="form-control col-md-7 col-xs-12">
				</div>
			</div>
			
			<div class="form-group"  id="ROWchequeNo">
				<label class="control-label col-md-4 col-sm-4 col-xs-12"
					for="first-name">Cheque Number
				</label>
				<div class="col-md-8 col-sm-8 col-xs-12">
					 <input type="text" id="chequeNo" name="chequeNo"
								required="required" class="form-control col-md-7 col-xs-12">
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-4 col-sm-4 col-xs-12"
					for="first-name">Amount<span class="required">*</span>
				</label>
				<div class="col-md-8 col-sm-8 col-xs-12">
					 <input type="text" id="amount" name="amount"
								required="required" class="form-control col-md-7 col-xs-12" readonly="readonly">
				</div>
			</div>
			 
			<div class="form-group" align="right">
				<button class="btn btn-success" onclick="payBill();return false;">Confirm Payment</button>
			</div>
			
		</form>
		
 		</div>
 	</div>
 </div>
 
 
 <script>
 
 
 $(document).ready(function(){
		 
			$( "#confOTPDialog" ).dialog({
				  autoOpen: false,
				  modal: true,
				  title: "Payment Details",
				  width: 600,
				  height: 400
			});
			
 });			

 
 function changePayType(){
	 var payMode = $('#payMode').val();
	 if(payMode=='cheque'){
		 $('#ROWbankName').show();
		 $('#ROWaccountNo').show();
		 $('#ROWchequeNo').show();
	 }else if(payMode=='cash'){
		 $('#ROWbankName').hide();
		 $('#ROWaccountNo').hide();
		 $('#ROWchequeNo').hide();
	 }else if(payMode=='netbanking'){
		 $('#ROWbankName').show();
		 $('#ROWaccountNo').show();
		 $('#ROWchequeNo').hide();
	 }
 }
 
 
 function payBillByBillId(billid,payamount){
	 $('#billIdTopay').val(billid);
	 $('#amount').val(payamount);
	 $("#confOTPDialog").dialog("open");
 }
 
 function payBill(){
	 
	 var billIdTopay = $('#billIdTopay').val();
	 var payMode = $('#payMode').val();
	 var bankName = $('#bankName').val();
	 var accountNo = $('#accountNo').val();
	 var chequeNo = $('#chequeNo').val();
	 var amount = $('#amount').val();
	 
	 var dataText = 'billid='+billIdTopay;
	  	 dataText += '&payMode='+payMode;

	 if(payMode=='cheque'){
		  
		  if(bankName.length>0 && accountNo.length>0 && chequeNo.length>0 && amount.length>0){
			  dataText += '&bankName='+bankName;
			  dataText += '&accountNo='+accountNo;
			  dataText += '&chequeNo='+chequeNo;
			  dataText += '&amount='+amount;
		  }else
			  return false;
		  
	 	}else if(payMode=='cash'){
		 
		 if(amount.length>0){
			  dataText += '&amount='+amount;
		  }else
			  return false;
		 
	 	}else if(payMode=='netbanking'){
		 
		 if(bankName.length>0 && accountNo.length>0 && amount.length>0){
			 dataText += '&bankName='+bankName;
			 dataText += '&accountNo='+accountNo;
			 dataText += '&amount='+amount;
		  }else
			  return false; 
	 }
	 
	 console.log(dataText);
	 blockUI();
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/addBillPaymentByAdmin.do",
	        data : dataText,
	        success: function(response){
	        	if(response=='success') {
	        		getMemberBillbyId();
	        		$("#confOTPDialog").dialog("close");
	        		notify('success','SUCCESS','Payment Added Successfully',2000);
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
		        	
		        	var paybtn="";
		        	if(response.bill.ispaid!=1)
		        		paybtn = '<a class="btn btn-success btn-sm" onclick="payBillByBillId(\'' + response.bill.billid + '\',\'' + response.bill.payamount + '\')"> <i class="fa fa-play"></i> Pay Now</a>';
		        		else
		        			paybtn = '<font class="text-success">Already paid</font>';
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
 
 </script>