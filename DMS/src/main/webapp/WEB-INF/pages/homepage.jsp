<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- top tiles
          <div class="row tile_count">
            <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
              <span class="count_top"><i class="fa fa-user"></i> Total Users</span>
              <div class="count">2500</div>
              <span class="count_bottom"><i class="green">4% </i> From last Week</span>
            </div>
            <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
              <span class="count_top"><i class="fa fa-clock-o"></i> Average Time</span>
              <div class="count">123.50</div>
              <span class="count_bottom"><i class="green"><i class="fa fa-sort-asc"></i>3% </i> From last Week</span>
            </div>
            <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
              <span class="count_top"><i class="fa fa-user"></i> Total Males</span>
              <div class="count green">2,500</div>
              <span class="count_bottom"><i class="green"><i class="fa fa-sort-asc"></i>34% </i> From last Week</span>
            </div>
            <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
              <span class="count_top"><i class="fa fa-user"></i> Total Females</span>
              <div class="count">4,567</div>
              <span class="count_bottom"><i class="red"><i class="fa fa-sort-desc"></i>12% </i> From last Week</span>
            </div>
            <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
              <span class="count_top"><i class="fa fa-user"></i> Total Collections</span>
              <div class="count">2,315</div>
              <span class="count_bottom"><i class="green"><i class="fa fa-sort-asc"></i>34% </i> From last Week</span>
            </div>
            <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
              <span class="count_top"><i class="fa fa-user"></i> Total Connections</span>
              <div class="count">7,325</div>
              <span class="count_bottom"><i class="green"><i class="fa fa-sort-asc"></i>34% </i> From last Week</span>
            </div>
          </div>
          /top tiles -->

<div class="col-md-12 col-sm-12 col-xs-12">
	<div class="x_panel tile">
		<div class="x_title">
			<h2>Personal Details</h2>
			<ul class="nav navbar-right panel_toolbox">
				<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
				</li>
			</ul>
			<div class="clearfix"></div>
		</div>
		<div class="x_content">
			<div class="dashboard-widget-content">
				<div class="row">
					<div class="col-md-3 col-sm-3 col-xs-12">
					<% if(request.getSession().getAttribute("imgBase64")!=null && request.getSession().getAttribute("imgBase64").toString().length()>5){ %>
	 					<img class="img-circle"  width="210" src="data:<%= request.getSession().getAttribute("imgContentType") %>;base64,<%= request.getSession().getAttribute("imgBase64") %>"/>
	 					<% } else {
	            	  %>
	            	  <img src="<%= request.getContextPath() %>/resources/images/blankface.jpg" width="210">
	            	  <%
		              }
		              %>
	  				</div>
	  			 
	  				
	  				<div class="col-md-9 col-sm-9 col-xs-12">
	  					
	  					<c:if test="${fn:length(userprofile.firstName)>0}">
	  					<div class="col-md-4 col-sm-4 col-xs-12">
	  						<label class="control-label">Name of Flat Owner</label>
	  						<div>${userprofile.firstName} ${userprofile.lastName}</div> 
	  						<br>
	  					</div>
	  					</c:if>
	  					
	  					<c:if test="${fn:length(userprofile.mobileNo)>0}">
	  					<div class="col-md-4 col-sm-4 col-xs-12">
	  						<label class="control-label">Contact Number</label>
	  						<div>${userprofile.mobileNo}  </div> 
	  						<br>
	  					</div>
	  					</c:if>
	  					
	  					
	  					<c:if test="${fn:length(userprofile.alternateno)>0}">
	  					<div class="col-md-4 col-sm-4 col-xs-12">
	  						<label class="control-label">Alternate Number</label>
	  						<div>${userprofile.alternateno}  </div> 
	  						<br>
	  					</div>
	  					</c:if>
	  					
	  					<c:if test="${fn:length(userprofile.email)>0}">	
	  					<div class="col-md-4 col-sm-4 col-xs-12">
	  						<label class="control-label">Email</label>
	  						<div>${userprofile.email}  </div> 
	  						<br>
	  					</div>
	  					</c:if>
	  					
	  					<c:if test="${fn:length(userprofile.jointowners)>0}">	
		  					<c:forEach items="${fn:split(userprofile.jointowners,',')}" var="myItem" varStatus="loopStatus">
								<div class="col-md-4 col-sm-4 col-xs-12">
		  							<label class="control-label">Joint Owner ${loopStatus.index+1}</label>
		  							<div> ${myItem} </div> 
		  							<br>
		  						</div>
							</c:forEach>
	  					</c:if>
	  					
	  					 <c:if test="${fn:length(userprofile.flatno)>0}">	
	  					 <div class="col-md-4 col-sm-4 col-xs-12">
	  						<label class="control-label">Flat No.</label>
	  						<div>${userprofile.flatno}  </div> 
	  						<br>
	  					</div>
	  					</c:if>
	  					
	  					<c:if test="${fn:length(userprofile.floor)>0}">	
	  					<div class="col-md-4 col-sm-4 col-xs-12">
	  						<label class="control-label">Floor</label>
	  						<div>${userprofile.floor}</div> 
	  						<br>
	  					</div>
	  					</c:if>
	  					
	  					<c:if test="${fn:length(userprofile.tower)>0}">	
	  					<div class="col-md-4 col-sm-4 col-xs-12">
	  						<label class="control-label">Tower</label>
	  						<div>${userprofile.tower} </div> 
	  						<br>
	  					</div>
	  					</c:if>
	  					
	  					<c:if test="${fn:length(userprofile.wing)>0}">	
	  					<div class="col-md-4 col-sm-4 col-xs-12">
	  						<label class="control-label">Wing</label>
	  						<div>${userprofile.wing}</div> 
	  						<br>
	  					</div>
	  					</c:if>
	  					
	  					<c:if test="${userprofile.purchasedate!=null}">	
	  					<div class="col-md-4 col-sm-4 col-xs-12">
	  						<label class="control-label">Registration Date</label>
	  						<div>${userprofile.purchasedate}  </div> 
	  						<br>
	  					</div>
	  					</c:if>
	  					
	  					<c:if test="${userprofile.possessiondate!=null}">	
	  					<div class="col-md-4 col-sm-4 col-xs-12">
	  						<label class="control-label">Possession Date</label>
	  						<div>${userprofile.possessiondate}  </div> 
	  						<br>
	  					</div>
	  					</c:if>
	  					
	  					<c:if test="${fn:length(userprofile.bloodgroup)>0}">	
	  					<div class="col-md-4 col-sm-4 col-xs-12">
	  						<label class="control-label">Blood Group</label>
	  						<div>${userprofile.bloodgroup}  </div> 
	  						<br>
	  					</div>
	  					</c:if>
	  					
	  					<c:if test="${fn:length(userprofile.aadharno)>0}">	
	  					<div class="col-md-4 col-sm-4 col-xs-12">
	  						<label class="control-label">Aadhar No.</label>
	  						<div>${userprofile.aadharno}  </div> 
	  						<br>
	  					</div>
	  					</c:if>
	  					
	  					<c:if test="${fn:length(userprofile.sharecertno)>0}">	
	  					<div class="col-md-4 col-sm-4 col-xs-12">
	  						<label class="control-label">Share Certificate No.</label>
	  						<div>${userprofile.sharecertno}  </div> 
	  						<br>
	  					</div>
	  					</c:if>
	  					
	  					<c:if test="${fn:length(userprofile.nominee1)>0}">	
	  					<div class="col-md-4 col-sm-4 col-xs-12">
	  						<label class="control-label">Nominee 1</label>
	  						<div>${userprofile.nominee1} (${userprofile.percent1}%)</div> 
	  						<br>
	  					</div>
	  					</c:if>
	  					
	  					<c:if test="${fn:length(userprofile.nominee2)>0}">	
	  					<div class="col-md-4 col-sm-4 col-xs-12">
	  						<label class="control-label">Nominee 2</label>
	  						<div>${userprofile.nominee2} (${userprofile.percent2}%)</div> 
	  						<br>
	  					</div>
	  					</c:if>
	  					
	  					<c:if test="${fn:length(userprofile.nominee3)>0}">	
	  					<div class="col-md-4 col-sm-4 col-xs-12">
	  						<label class="control-label">Nominee 3</label>
	  						<div>${userprofile.nominee3} (${userprofile.percent3}%)</div> 
	  						<br>
	  					</div>
	  					</c:if>
	  					
	  					<c:if test="${fn:length(userprofile.occupancy)>0}">	
	  					<div class="col-md-4 col-sm-4 col-xs-12">
	  						<label class="control-label">Occupancy</label>
	  						<div>${userprofile.occupancy} </div> 
	  						<br>
	  					</div>
	  					</c:if>
	  					
	  					<div class="clearfix"></div>
	  					
	  					<c:if test="${userprofile.occupancy=='leased'}">
	  					
	  						<c:if test="${fn:length(userprofile.tenantname)>0}">	
	  						<div class="col-md-4 col-sm-4 col-xs-12">
		  						<label class="control-label">Name of Lease Owner</label>
		  						<div>${userprofile.tenantname} </div> 
		  						<br>
		  					</div>
		  					</c:if>
		  					
		  					<c:if test="${fn:length(userprofile.tenantaddress)>0}">	
		  					<div class="col-md-4 col-sm-4 col-xs-12">
		  						<label class="control-label">Address</label>
		  						<div>${userprofile.tenantaddress} </div> 
		  						<br>
		  					</div>
		  					</c:if>
		  					
		  					<c:if test="${fn:length(userprofile.tenantcontactnumber)>0}">	
		  					<div class="col-md-4 col-sm-4 col-xs-12">
		  						<label class="control-label">Contact Number</label>
		  						<div>${userprofile.tenantcontactnumber} </div> 
		  						<br>
		  					</div>
		  					</c:if>
		  					
		  					<c:if test="${fn:length(userprofile.tenantaltnumber)>0}">	
		  					<div class="col-md-4 col-sm-4 col-xs-12">
		  						<label class="control-label">Alternate Number</label>
		  						<div>${userprofile.tenantaltnumber} </div> 
		  						<br>
		  					</div>
		  					</c:if>
		  					
		  					<c:if test="${fn:length(userprofile.tenantemail)>0}">	
		  					<div class="col-md-4 col-sm-4 col-xs-12">
		  						<label class="control-label">Email</label>
		  						<div>${userprofile.tenantemail} </div> 
		  						<br>
		  					</div>
		  					</c:if>
		  					
		  					<c:if test="${fn:length(userprofile.tenantaadharno)>0}">	
		  					<div class="col-md-4 col-sm-4 col-xs-12">
		  						<label class="control-label">Aadhar No.</label>
		  						<div>${userprofile.tenantaadharno} </div> 
		  						<br>
		  					</div>
	  						</c:if>
	  						
	  					</c:if>
	  					
	  				</div>
				</div>
				
				
				<div class="row">
				<hr>
					<div class="col-md-4 col-sm-4 col-xs-12">
						<h2><a href="displaySelfSociety.do">Self Society Details</a></h2>
						<br>
					</div>
					
					<div class="col-md-4 col-sm-4 col-xs-12">
						<h2><a href="viewNoticeboard.do?societyid=${userprofile.societyid}">Society Notice Board</a></h2>
						<br>
					</div>
					
					<c:forEach items="${docSubType}" var="myItem" varStatus="loopStatus">
						<h2>
							<a onClick="displayDocumentPage('${myItem.docsubtypeid}','${userprofile.userid}','${myItem.confFlag}');" >
								${myItem.docsubtypename}
								<c:if test="${myItem.confFlag==1}">
									<i class="fa fa-lock text-warning"></i>						
								</c:if>
							</a>
						</h2>
						<br>
					</c:forEach>
					
				</div>
			</div>
		</div>
	</div>
	</div>
	
	
	<div id="confOTPDialog">
		<form method="post" action="#">
		<br>
              <div>
                <input type="text" name="mobileNoOTP" id="mobileNoOTP" class="form-control" placeholder="Registered 10 Digit Mobile Number"/>
              </div>
              <div align="center">
              <button class="btn btn-warning" onclick="generateOTPInit();return false;" style="margin-top: 10px;" id="otpbtn">Send OTP</button>
              </div>
              <hr/>
              <div>
                <input type="password" name ="otp" id ="otp" class="form-control" autocomplete="off" placeholder="Enter OTP Here" />
              </div>
              <div align="center">
                <button class="btn btn-success" style="margin-top: 10px;" onclick="validateOTPForDocAccess();return false;">Validate</button>
              </div>
              <input type="hidden" id="hash">
 			</form>
	</div>
<script>
	
	$(document).ready(function(){
		$( "#confOTPDialog" ).dialog({
			  autoOpen: false,
			  modal: true,
			  title: "Please Authenticate yourself",
			  width: 270,
			  height: 300
		});
	});
	
	function displayDocumentPage(docId,userId,confFlag){
		
		var URL = 'displayDocument.do?doctypeid='+docId+'&userid='+userId;

		if(confFlag=='1'){
			$( "#hash" ).val(URL);
			$( "#confOTPDialog" ).dialog( "open" );
		}
		else {
			 openURL(URL);
		}
	 }
		
	function openURL(URL){
		var win = window.open(URL, '_blank');
		if (win) {
		    win.focus();
		} else {
		    alert('Please allow popups for this website');
		}
	}
	
	function generateOTPInit(){
		
		var counter = 30;
		var interval = setInterval(function() {
		    counter--;
		    console.log("genOPT cntr "+counter);
		    $('#otpbtn').attr('disabled','disabled').html('Resend in '+counter+' sec');
		    if (counter == 0) {
		    	clearInterval(interval);
		    	$('#otpbtn').removeAttr('disabled').html('Resend OTP');
		    }
		}, 1000);
		
		generateOTP(interval);
	}
	
	function generateOTP(interval){
		var mobileNo = $('#mobileNoOTP').val();
		$('#otp').val('');
		
		$.ajax({
		        type: "GET",
		        url: "<%=request.getContextPath()%>/generateAndSendOTP.do",
		       data :"mobileNo="+mobileNo,
		        success: function(response){
		        //alert()
		        	if(response=='success') {
		        		notify('success','OTP SENT','You Will Receive OTP Shortly',2000);
		        	}  else {
		        		notify('error','FAILED','Invalid Mobile Number',2000);
		        		clearInterval(interval);
				    	$('#otpbtn').removeAttr('disabled').html('Send OTP');
		        	}
		        },
					error : function(e) {
						notify('error','ERROR','Error occured',2000);
					}
				});
	}
	
	function validateOTPForDocAccess(){
		var mobileNo = $('#mobileNoOTP').val();
		var otp = $('#otp').val();
		
		if(otp.length>0){
					$.ajax({
				        type: "GET",
				        url: "<%=request.getContextPath()%>/validateOTPForDocAccess.do",
				        data :"mobileNo="+mobileNo
			        	+"&otp="+otp,
				        success: function(response){
				        	if(response=='success') {
				        		notify('success','AUTHENTICATED','You have been authenticated',2000);
				        		var URL1 = $( "#hash" ).val();
				        		openURL(URL1);
				        		$( "#confOTPDialog" ).dialog( "close" );
				        	}  else {
				        		notify('error','FAILED','Invalid OTP or Mobile Number',2000);
						    	$('#otpbtn').removeAttr('disabled').html('Send OTP');
				        	}
				        },
							error : function(e) {
								notify('error','ERROR','Error occured',2000);
							}
						});
		}else{
			alert('Please Enter OTP');
		}
		
	}
	
</script>
