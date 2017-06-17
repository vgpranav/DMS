<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<input type="hidden" name="societyid" id="societyid" value="${society.societyid}">


<div class="col-md-12 col-sm-12 col-xs-12">
	<div class="x_panel tile">
		<div class="pull-left">
			<h3>${society.societyname}</h3>
				    <b>Society Reg. No. ${society.registrationno}</b>
					<br><b>Estd. on <fmt:formatDate type = "date" value = "${society.estdate}" /></b>
					<br><br><em>Address: ${society.addressline1}</em>
					<br><em>${society.addressline2}</em>
					<%-- <br><em>Landmark : ${society.landmark}</em> --%>
					<%-- <br><em>City: ${society.city}</em> --%>
					<br><em>${society.ward}, ${society.district}, ${society.state}</em>
					<br><em>${society.country}</em>
					<br><em>Pincode: ${society.pincode}</em>
					<c:if test="${society.societytypeid=='1' }">
						<br><em>No of Falt/Apartments/Rooms : ${society.noofflat}</em>
					</c:if>
					<br><em>No of Shop/Offices/Gala : ${society.noofshop}</em>
					
					
		</div>
		<div class="pull-right">
		<div align="left">
			<div id="imgContainer" >
			<img width="150" src="<%=request.getContextPath()%>/resources/images/spin.gif">
				</div>
		</div>
		</div>
	</div>
</div>


 
  	
 
 <div class="col-md-12 col-sm-12 col-xs-12">
              <div class="x_panel tile">
                <div class="x_title">
                  <h2>Tenants Summary</h2>
                  <ul class="nav navbar-right panel_toolbox">
                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                    </li> 
                  </ul>
                  <div class="clearfix"></div>
                </div>
                <div class="x_content">
                  <div class="dashboard-widget-content">
                    <div class="table-responsive">
				<table class="table table-striped jambo_table bulk_action"
					id="thetable1">
					<thead>
						<tr class="headings">
							<th class="column-title">Sr.No</th>
							<c:if test="${society.societytypeid=='1' }">
								<th class="column-title">Flat&nbsp;No</th>
							</c:if>
							<c:if test="${society.societytypeid!='1' }">
								<th class="column-title">Shop&nbsp;No</th>
							</c:if>
							<th class="column-title">Wing</th>
							<th class="column-title">Floor</th>
							<th class="column-title">Type</th>
							<th class="column-title">Tower</th>
							<th class="column-title">Tenant&nbsp;Name</th>
							<th class="column-title">Mobile&nbsp;No</th>
							<th class="column-title">Joint&nbsp;Owners</th>
							<th class="column-title">Occupancy</th>
							<th class="column-title">Alternate&nbsp;No</th>
							<th class="column-title">Email</th>
							<th class="column-title">Aadhar&nbsp;No</th>
							<th class="column-title">Purchase&nbsp;Date</th>
							<th class="column-title">Possession&nbsp;Date</th>
							<th class="column-title">Builtup&nbsp;Area</th>
							<th class="column-title">Carpet&nbsp;Area</th>
							<th class="column-title">Share Cert.</th>
							<th class="column-title">Lease Owner.</th>
							<th class="column-title">Parking Details</th>
							<th class="column-title">Photograph</th>
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
 
 		<div class="col-md-12 col-sm-12 col-xs-12">
              <div class="x_panel tile">
                <div class="x_title">
                  <h2>Existing Documents</h2>
                  <ul class="nav navbar-right panel_toolbox">
                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                    </li> 
                  </ul>
                  <div class="clearfix"></div>
                </div>
                <div class="x_content">
                  <div class="dashboard-widget-content">
                    
                    <div class="accordion" id="accordion" role="tablist" aria-multiselectable="true">
                      
                      <c:set var="udoc" value=""></c:set>
                      
                       <c:forEach items="${docs}" var="myItem" varStatus="loopStatus">
                       
                       		<c:if test="${udoc!=myItem.doctypename}">
                       			<c:set var="udoc" value="${myItem.doctypename}"></c:set>
                       			<div class="col-md-4 col-sm-4 col-xs-12">
			                      <div class="panel">
			                        <a class="panel-heading" role="tab" id="headingOne" data-toggle="collapse" data-parent="#accordion" href="#collapse${myItem.doctypeid}" aria-expanded="true" aria-controls="collapseOne">
			                          <h4 class="panel-title"> ${myItem.doctypename}</h4>
			                        </a>
			                        <div id="collapse${myItem.doctypeid}" class="panel-collapse collapse " role="tabpanel" aria-labelledby="headingOne">
			                          <div class="panel-body">
			                             <ul>
		                             		<c:forEach items="${docs}" var="mySubItem" varStatus="loopSubStatus">
		                             		 	<c:if test="${udoc==mySubItem.doctypename}">	
		                             		 		<li>
		                             		 			<a href="showDocFromAdminPanel.do?societyid=${society.societyid}&doctypeid=${myItem.doctypeid}&docsubtypeid=${mySubItem.docsubtypeid}">
		                             		 				${mySubItem.docsubtypename}
		                             		 			</a>
		                             		 		</li>
		                             		 	</c:if>	
		                            	    </c:forEach>
		                             	</ul>
		                          	  </div>
		                        	</div>
		                      	  </div>
		                     	</div>
                       		</c:if>
                       		
                       		<c:if test="${loopStatus.index!=0 && loopStatus.index%3==0}">
                       			<div class="clearfix"></div>
                       		</c:if>
                       </c:forEach>
                    </div>
                  </div>
                </div>
              </div>
 		</div>
 
 
 		<div class="col-md-12 col-sm-12 col-xs-12" id="committeeSection" style="display: none;">
              <div class="x_panel tile">
                <div class="x_title">
                  <h2>Committee Members</h2>
                  <ul class="nav navbar-right panel_toolbox">
                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                    </li> 
                  </ul>
                  <div class="clearfix"></div>
                </div>
                <div class="x_content">
                  <div class="dashboard-widget-content">
                  
                   <h4>Current Members</h4>
                   <hr>
                  <div class="table-responsive">
                    <table class="table table-striped jambo_table bulk_action" id="thetable">
                        <thead>
                          <tr class="headings">
                            <th class="column-title">Sr.No</th>
                            <th class="column-title">Member Name</th>
                            <th class="column-title">Designation</th>
                            <th class="column-title">Wing/Flat</th>
                            <th class="column-title">Tower</th>
                            <th class="column-title">Contact No</th>
                            <th class="column-title">Appointed On</th>
                            <th class="column-title">Photograph</th>
                          </tr>
                        </thead>
                        <tbody></tbody>
                      </table>
                   </div>
                   
                   <h4>Past Members</h4>
                   <hr>
                   <div id="pastMemCont"></div>
                      
                  </div>
                </div>
              </div>
 		</div>
 
 
 <div class="col-md-12 col-sm-12 col-xs-12">
              <div class="x_panel tile">
                <div class="x_title">
                  <h2>Vendor Details</h2>
                  <ul class="nav navbar-right panel_toolbox">
                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                    </li> 
                  </ul>
                  <div class="clearfix"></div>
                </div>
                <div class="x_content">
                  <div class="dashboard-widget-content">
                  <div class="table-responsive">
                    <table class="table table-striped jambo_table bulk_action" id="thetable2">
                        <thead>
                          <tr class="headings">
                            <th class="column-title">Company Name</th>
                            <th class="column-title">Job Nature</th>
                            <th class="column-title">Contact Person</th>
                            <th class="column-title">Address</th>
                            <th class="column-title">Contact Number</th>
                            <th class="column-title">Alternate Number</th>
                             <th class="column-title">Contract From</th>
                            <th class="column-title">Contract To</th>
                            <th class="column-title">Email</th>
                            <th class="column-title">Remarks</th>
                            <th class="column-title">Photo</th>
                            <th class="column-title">Visiting Card</th>
                          </tr>
                        </thead>
                        <tbody></tbody>
                      </table>
                      </div>
                  </div>
                </div>
              </div>
 </div>
 <div class="clearfix"></div>
 
 <div id="SCDialog">
 		<div class="table-responsive  col-sm-12 col-md-12 col-xs-12"  >
			<table class="table table-striped jambo_table bulk_action"
				id="thetableSC">
				<thead>
					<tr class="headings"> 
						<th class="column-title">Nominee</th>
						<th class="column-title">Percentage</th>
						<th class="column-title">Relation</th>
						<th class="column-title">DOB</th>
						<th class="column-title">Address</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
 </div>
 
 <div id="PKDialog">
  <table class="table table-striped jambo_table bulk_action"
									id="thetablePK">
									<thead>
										<tr class="headings"> 
											<th class="column-title">Vehicle&nbsp;Type</th>
											<th class="column-title">Parking&nbsp;Type</th>
											<th class="column-title">Parking&nbsp;Allotment&nbsp;No</th>
											<th class="column-title">Vehicle&nbsp;Reg&nbsp;No</th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
 </div>
 
 
 
 <div id="TDialog">
 		<div class="clearfix"></div>
							<div class="form-group">
								<div class="col-md-4 col-sm-4 col-xs-12">
								 	 <sup class="text-muted">First Name</sup>
									 <br><span class="bigger" id="tenantname"></span>
								</div>
								<div class="col-md-4 col-sm-4 col-xs-12">
								 	 <sup class="text-muted">Middle Name</sup>
									 <br><span class="bigger" id="tenantname2"></span>
								</div>
								<div class="col-md-4 col-sm-4 col-xs-12">
								 	 <sup class="text-muted">Last Name</sup>
									 <br><span class="bigger" id="tenantname3"></span>
								</div>
							</div>
							<div class="clearfix"></div>
							<div class="form-group">
								<div class="col-md-4 col-sm-4 col-xs-12">
								 	 <sup class="text-muted">Contact Number</sup>
									 <br><span class="bigger" id="tenantcontactnumber"></span>
								</div>
								
								<div class="col-md-4 col-sm-4 col-xs-12">
								 	 <sup class="text-muted">Alternate Number</sup>
									 <br><span class="bigger" id="tenantaltnumber"></span>
								</div>
								
								<div class="col-md-4 col-sm-4 col-xs-12">
								 	 <sup class="text-muted">Email</sup>
									 <br><span class="bigger" id="tenantemail"></span>
								</div>
							</div>
							<div class="form-group">
							
								<div class="col-md-12 col-sm-12 col-xs-12">
								 	 <sup class="text-muted">Permanent Address</sup>
									 <br><span class="bigger" id="tenantaddress"></span>
								</div>
								
							   <div class="col-md-12 col-sm-12 col-xs-12">
								 	 <sup class="text-muted">Lease Type</sup>
									 <br><span class="bigger" id="tenantType"></span>
								</div>
								
							</div>
							<div class="form-group">
								 <div class="col-md-4 col-sm-4 col-xs-12">
								 	 <sup class="text-muted">Aadhar Number</sup>
									 <br><span class="bigger" id="tenantaadharno"></span>
								</div>
								
								<div class="col-md-4 col-sm-4 col-xs-12">
								 	 <sup class="text-muted">Tenant Police Verification Done</sup>
									 <br><span class="bigger" id="tenantPVstatus"></span>
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-md-4 col-sm-4 col-xs-12">
									<sup class="text-muted">From Date</sup>
									 <br><span class="bigger" id="tenantfrom"></span>
								</div>
								
								<div class="col-md-4 col-sm-4 col-xs-12">
									<sup class="text-muted">To Date</sup>
									 <br><span class="bigger" id="tenantto"></span>
								</div>
							
							</div>
 </div>
 
 <div id="IMGDialog">
 
 </div>
 
 <!-- <button onclick="openSCDialog()">Test</button> -->
 <script>
 
 
 function openSCDialog(){
		$( "#SCDialog" ).dialog( "open" )
	 }
 
 function openIMGDialog(){
		$( "#IMGDialog" ).dialog( "open" )
	 }
 
 function openTDialog(){
		$( "#TDialog" ).dialog( "open" )
	 }
 
 function openPKialog(){
		$( "#PKDialog" ).dialog( "open" )
	 }
  
 
 $(document).ready(function(){
	 
	 $( "#SCDialog" ).dialog({
	      autoOpen: false,
	      height: 500,
	      width: 600,
	      modal: true,
	      title:"Share Certificate Details",
	    });
	 
	 $( "#IMGDialog" ).dialog({
	      autoOpen: false,
	      height: 500,
	      width: 600,
	      modal: true,
	      title:"Image",
	    });
	 
	 $( "#TDialog" ).dialog({
	      autoOpen: false,
	      height: 400,
	      width: 700,
	      modal: true,
	      title:"Lease Owner Details",
	    });
	 
	 $( "#PKDialog" ).dialog({
	      autoOpen: false,
	      height: 400,
	      width: 700,
	      modal: true,
	      title:"Parking Details",
	    });
	 
	 $('#thetableSC').DataTable({
	        "paging":   false,
	        "ordering": false,		       
	        "bFilter": false,
	        "info":     false
	    });
	 
	 $('#thetablePK').DataTable({
	        "paging":   false,
	        "ordering": false,		       
	        "bFilter": false,
	        "info":     false
	    });
	 
	 $('#thetable').DataTable({
		 	"paging":   false,
	        "ordering": false,
	        "info":     false,
	        "bFilter": false
	    });
	 
	 $('#thetable1').DataTable({
	        
	        "ordering": false,
	        "info":     false,
	        "bFilter": false
	    });
	 
		/* $('#thetable5').DataTable({
	        
	        "ordering": false,
	        "info":     false,
	        "bFilter": false
	    }); */
	 
	 $('#thetable2').DataTable({
	        
	        "ordering": false,
	        "info":     false,
	        "bFilter": false
	    });
	 
		getCommitteMembersForSociety();
		getMembersForSociety();
		getSocietyPhotos();
		getVendorsBySocId();
 });
 
 function getSCDetailsForMember(userid){
		var randomHash = '1';
		var table = $('#thetableSC').DataTable();
		table .clear() .draw();
		blockUI();
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/getShareCertDetails.do",
	        data :"userid="+userid
	        		+"&randomHash="+randomHash,
	        success: function(response){
	        	var srno=1;
	        	$.each(response, function(i, item) {

	        		var delBtn = '<a class="btn btn-default btn-sm" onclick="deleteSCData(\'' + item.userscnomineeid + '\')"><i class="fa fa-times"></i></a>';

	        		table.row.add( [
	        			item.nominee,
	        			item.percent,
	        			item.nomineerelation,
	        			new Date(item.nomineedob).toString("dd MMM yyyy"),
	        			item.nomineeaddress,
	                ] ).draw( false );
	        		srno++;
	        	 });
	        	unblockUI();
	        	openSCDialog();
	        },
				error : function(e) {
					notify('error','ERROR','Error occured',2000);
					unblockUI();
				}
			});
	}
 
 function getCommitteMembersForSociety(){
		var societyid = $('#societyid').val();
		var table = $('#thetable').DataTable();
			
		if(societyid.length<1)
			return false;
			
		table.clear().draw();
		
		blockUI();
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/getCommitteMembersForSociety.do",
	        data :"societyid="+societyid,
	        success: function(response){
	        var k=1;
	        var j=1;
	        var srno=1;
	        var testKey='';
	        var currTabId='';
	        
	        	$.each(response, function(i, item) {
	        		$.each(item, function(i, item1) {
	        			var divid="commem"+srno;
	        			
	        			   var photodiv = '<a class="ionimage2" href="getUserImage/user/UserImages/'+item1.userid+'.do"><img src="getUserImageThumb/user/UserImages/'+item1.userid+'.do" height="30"></a>';
	        			
<%-- 			        	var photodiv = '<div id="'+divid+'" align="center"><img width="25" src="<%=request.getContextPath()%>/resources/images/spin.gif"></div>';
 --%>			        	
	        				if(item1.isactive==1){
	        				var removebtn = '<button class="btn btn-xs btn-danger" onClick="removeCommitteeMember(\'' + item1.committeememberid + '\')"><i class="fa fa-times"></i></button>';
			        					table.row.add( [
			        						k,
						        			item1.userName,
						        			item1.positionname, 
						        			item1.flat,
						        			item1.tower,
						        			item1.contactNo,
						        			new Date(item1.appointedon).toString("dd MMM yyyy"),
						        			photodiv,
				                ] ).draw( false ); 
				                k++;
	        				} else{
	        					
	        					if(item1.tower!=testKey){
	        						testKey=item1.tower;
	        						getPastMemTable(testKey,item1.removedon);
	        						currTabId=testKey;
	        					}
	        					
	        					var table5 = $('#'+currTabId).DataTable();
	        					
	        					table5.row.add( [
	        						j,
				        			item1.userName,
				        			item1.positionname, 
				        			item1.flat,
				        			item1.tower,
				        			item1.contactNo,
				        			new Date(item1.appointedon).toString("dd MMM yyyy"),
				        			new Date(item1.removedon).toString("dd MMM yyyy"),
				        			photodiv,
				                ] ).draw( false ); 
				                j++;
	        				}
	        				//getMemberPhoto(item1.userid,divid);
	        				srno++;
	        				$('#committeeSection').show();
	        		 });
	        		 
	        	  });
	        	$(".ionimage2").ionZoom();
	        	unblockUI();
	        },
				error : function(e) {
					notify('error','ERROR','Error occured',2000);
					unblockUI();
				}
			});
	}
 
 
 function getMembersForSociety(){
		var societyid = $('#societyid').val();
		var table = $('#thetable1').DataTable();
			
		
		table .clear() .draw();
		blockUI();
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/getMembersForSociety.do",
	        data :"societyid="+societyid,
	        success: function(response){
	        	var srno=1;
	        	
	        	$.each(response, function(i, item) {
	        		var divid="soc"+srno;
		        	<%-- var photodiv = '<div id="'+divid+'" align="center"><img width="25" src="<%=request.getContextPath()%>/resources/images/spin.gif"></div>'; --%>
		        	var scbtn = '<a href="#" onclick="getSCDetailsForMember(\'' + item.userid + '\')">view</a>';
		        	var tbtn = '<a href="#" onclick="editUserData(\'' + item.userid + '\')">view</a>';
		        	var pkbtn = '<a href="#" onclick="getParkingDetailsForMember(\'' + item.userid + '\')">view</a>';
		        	var photodiv = '<a class="ionimage" href="getUserImage/user/UserImages/'+item.userid+'.do"><img src="getUserImageThumb/user/UserImages/'+item.userid+'.do" height="30"></a>';
	        		table.row.add( [
	        			srno,
	        			item.flatno,
	        			item.wing,
	        			item.floor,
	        			item.flattype,
	        			item.tower,
	        			item.firstName + ' ' + item.lastName,
	        			item.mobileNo,
	        			item.jointowners,
	        			item.occupancy,
	        			item.alternateno,
	        			item.email,
	        			item.aadharno,
	        			new Date(item.purchasedate).toString("dd MMM yyyy"),
	        			new Date(item.possessiondate).toString("dd MMM yyyy"),
	        			item.builtuparea,
	        			item.carpetarea,
	        			scbtn,
	        			tbtn,
	        			pkbtn,
	        			photodiv,
	                ] ).draw( false );
	        		
	        		//getMemberPhoto(item.userid,divid);
	        		srno++;
	        	 });
	        	unblockUI();
	        	$(".ionimage").ionZoom();
	        },
				error : function(e) {
					notify('error','ERROR','Error occured',2000);
					unblockUI();
				}
			});
	}
	
 function getSocietyPhotos(){
 	
 	var societyid = $('#societyid').val();
 	blockUI();
 	$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/getSocietyPhotos.do",
	        data :"societyid="+societyid,
	        enctype: 'multipart/form-data',
	        processData: false,
         contentType: false 
			}).done(function(data) {
			var img='<div class="slider"><ul style="margin-left:-40px;">';
			var cnt =1;
            $.each(data, function(i, item) {
            		img += '<li><img style="max-height: 200px;border: thick solid #fff;" src="data:' + item.contenttype + ';base64,' +  item.file + '"/></li><li>&nbsp;</li>';
            		cnt++;
            });
            img += '</ul></div>';
            //console.log(img);
            $('#imgContainer').html(img);
            unblockUI();
       }).fail(function(jqXHR, textStatus) {
           //alert('File Fetch failed ...');
           unblockUI();
       });;
 }
	
	
	function getVendorsBySocId(){
		var societyid = $('#societyid').val();
		var table = $('#thetable2').DataTable();
			
		table .clear() .draw();
		blockUI();
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/getVendorsBySocId.do",
	        data :"societyid="+societyid,
	        success: function(response){
	        
	        if(response.length>0){
	        	var srno=1;
	        	
	        	$.each(response, function(i, item) {
	        		var divid="vend"+srno;
	        		var divid1 = "vendc"+srno;
		        	<%-- var photodiv = '<div id="'+divid+'" align="center"><img width="25" src="<%=request.getContextPath()%>/resources/images/spin.gif"></div>'; --%>
<%-- 		        	var carddiv = '<div id="'+divid1+'" align="center"><img width="25" src="<%=request.getContextPath()%>/resources/images/spin.gif"></div>';
 --%>	        		
		        	var photodiv = '<a class="ionimage1" href="getUserImage/vendor/VendorImages/'+item.vendorid+'.do"><img src="getUserImageThumb/vendor/VendorImages/'+item.vendorid+'.do" height="30"></a>';
		        	var carddiv = '<a class="ionimage1" href="getUserImage/vendorcards/VendorCards/'+item.vendorid+'.do"><img src="getUserImageThumb/vendorcards/VendorCards/'+item.vendorid+'.do" height="30"></a>';
		        	
		        	table.row.add( [
	        			item.companyname,
	        			item.jobnature,
	        			item.contactperson,
	        			item.address,
	        			item.contactno,
	        			item.alternateno,
	        			new Date(item.contractfrom).toString("dd MMM yyyy"),
	        			new Date(item.contractto).toString("dd MMM yyyy"),
	        			item.email,
	        			item.remark,
	        			photodiv,
	        			carddiv,
	                ] ).draw( false );
	        		/* getVendorPhotos(item.vendorid,divid);
	        		getVendorCards(item.vendorid,divid1); */
	        		srno++;
	        	  });
	        	$(".ionimage1").ionZoom();
	        	}
	        
	        unblockUI();
	        },
				error : function(e) {
					notify('error','ERROR','Error occured',2000);
					unblockUI();
				}
			});
	}
	
	function getMemberPhoto(memberId,divId){
    	console.log(memberId+"-"+divId);
    	blockUI();
    	$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/getMemberPhotos.do",
	        data :"userid="+memberId,
	        enctype: 'multipart/form-data',
	        processData: false,
            contentType: false 
			}).done(function(data) {
			$('#'+divId).html("");
			var img='';
			var cnt =1;
               $.each(data, function(i, item) {
               		img = '<img class="dp" height="30"  src="data:' + item.contenttype + ';base64,' +  item.file + '"/>';
               });
               $('#'+divId).html(img);
               $(".dp").popImg();
               unblockUI();
          }).fail(function(jqXHR, textStatus) {
              //alert('File Fetch failed ...');
              unblockUI();
          });;
    }
	
	function getVendorPhotos(vendorid,divId){
    	//console.log(memberId+"-"+divId);
    	blockUI();
    	$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/getVendorPhotos.do",
	        data :"vendorid="+vendorid,
	        enctype: 'multipart/form-data',
	        processData: false,
            contentType: false 
			}).done(function(data) {
			$('#'+divId).html("");
			var img='';
			var cnt =1;
               $.each(data, function(i, item) {
               		img = '<img class="dp" height="30"  src="data:' + item.contenttype + ';base64,' +  item.file + '"/>';
               });
               $('#'+divId).html(img);
               $(".dp").popImg();
               unblockUI();
          }).fail(function(jqXHR, textStatus) {
              //alert('File Fetch failed ...');
              unblockUI();
          });;
    }
	
	
	function getVendorCards(vendorid,divId){
    	//console.log(memberId+"-"+divId);
    	blockUI();
    	$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/getVendorCards.do",
	        data :"vendorid="+vendorid,
	        enctype: 'multipart/form-data',
	        processData: false,
            contentType: false 
			}).done(function(data) {
			$('#'+divId).html("");
			var img='';
			var cnt =1;
               $.each(data, function(i, item) {
               		img = '<img class="dp" height="30"  src="data:' + item.contenttype + ';base64,' +  item.file + '"/>';
               });
               $('#'+divId).html(img);
               $(".dp").popImg();
               unblockUI();
          }).fail(function(jqXHR, textStatus) {
              //alert('File Fetch failed ...');
              unblockUI();
          });;
    }
	
	
function editUserData(userid){
		
		$('#tenantPVstatus').html('');
		$('#tenantname').html('');
		$('#tenantname2').html('');
		$('#tenantname3').html('');
		$('#tenantaddress').html('');
		$('#tenantcontactnumber').html('');
		$('#tenantaltnumber').html('');
		$('#tenantemail').html('');
		$('#tenantaadharno').html('');
		$('#tenantType').html('');
		 $('#tenantfrom').html('');
		 $('#tenantto').html('');
	 
		blockUI();
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/getUserDataById.do",
	        data :"userid="+userid,
	        success: function(response){
	        	if(response.userid>0) {
 
	        		if(response.tenantname!=null && response.tenantname.length>0){
		        		$('#tenantPVstatus').html(response.tenantPVstatus.toUpperCase());
	        			$('#tenantname').html(response.tenantname.split(' ')[0]);
		        		$('#tenantname2').html(response.tenantname.split(' ')[1]);
		        		$('#tenantname3').html(response.tenantname.split(' ')[2]);
		        		$('#tenantaddress').html(response.tenantaddress);
		        		$('#tenantcontactnumber').html(response.tenantcontactnumber);
		        		$('#tenantaltnumber').html(response.tenantaltnumber);
		        		$('#tenantemail').html(response.tenantemail);
		        		$('#tenantaadharno').html(response.tenantaadharno);
		        		$('#tenantType').html(response.tenantType);
		        		
		        		if(response.tenantfrom!="")
		        		    $('#tenantfrom').html(new Date(response.tenantfrom).toString("dd MMM yyyy"));
		        		if(response.tenantto!="")
		        			$('#tenantto').html(new Date(response.tenantto).toString("dd MMM yyyy"));
	        		}
	        	 
	        	}  
	        	unblockUI();
	        	openTDialog();
	        },
				error : function(e) {
					notify('error','ERROR','Error occured',2000);
					unblockUI();
				}
			});
	}
	
	function getPastMemTable(tblId,remOn){
		
		var remDate = new Date(remOn).toString("dd MMM yyyy");
		
		var tbl = '<strong>Dissolved On: '+remDate+'</strong><div class="table-responsive">'
				        +'<table class="table table-striped jambo_table bulk_action" id="'+tblId+'">'
				        +'<thead>'
				        +'<tr class="headings">'
				        +'<th class="column-title">Sr.No</th>'
				        +'<th class="column-title">Member Name</th>'
				        +'<th class="column-title">Designation</th>'
				        +'<th class="column-title">Wing/Flat</th>'
				        +'<th class="column-title">Tower</th>'
				        +'<th class="column-title">Contact No</th>'
				        +'<th class="column-title">Appointed On</th>'
				        +'<th class="column-title">Dissolved On</th>'
				        +'<th class="column-title">Photograph</th>'
				        +'</tr>'
				        +'</thead>'
				        +'<tbody></tbody>'
				        +'</table>'
				        +'</div>';
		
		$('#pastMemCont').append(tbl);
		
		$('#'+tblId).DataTable({
			"paging":   false,
	        "ordering": false,
	        "info":     false,
	        "bFilter": false
	    });
	}
	
	
	function getParkingDetailsForMember(userid){
		var randomHash = '1';
		
		var table = $('#thetablePK').DataTable();
			
		
		table .clear() .draw();
		
		blockUI();
		
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/getParkingDetailsForMember.do",
	        data :"userid="+userid
	        		+"&randomHash="+randomHash,
	        success: function(response){
	        	var srno=1;
	        	$.each(response, function(i, item) {

	        		var delBtn = '<a class="btn btn-default btn-sm" onclick="deleteParkingData(\'' + item.userparkingdetailsid + '\')"><i class="fa fa-times"></i></a>';

	        		table.row.add( [
	        			item.vehicletype,
	        			item.parkingtype,
	        			item.parkingallotmentno,
	        			item.vehicleno,
	        			
	                ] ).draw( false );
	        		srno++;
	        	 });
	        	unblockUI();
	        	openPKialog();
	        },
				error : function(e) {
					notify('error','ERROR','Error occured',2000);
					unblockUI();
				}
			});
	}
 </script>
 
<style>
 #imgContainer{
 	overflow-x: auto;
    white-space: nowrap;
    max-height: 240px;
    max-width: 300px;
 }
 
 .slider ul{
  list-style: none;
}
 .slider li{
  display: inline;
}
 
 </style>
 
 
 <style>
	 
	.bigger{
		font-size: medium;
		margin-left: 38px !important;
		font-style: italic;
		font-weight: bolder;
	}
	
	sup {
		top:0.5em !important;
		margin-left: 35px !important;
	}
	 
</style>