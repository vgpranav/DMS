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
					<br><em>Landmark : ${society.landmark}</em>
					<br><em>City: ${society.city}</em>
					<br><em>${society.ward}, ${society.district}, ${society.state}</em>
					<br><em>${society.country}</em>
					<br><em>Pincode: ${society.pincode}</em>
					<br><em>No of Falt/Apartments/Rooms : ${society.noofflat}</em>
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
							<th class="column-title">Flat&nbsp;No</th>
							<th class="column-title">Wing</th>
							<th class="column-title">Floor</th>
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
 
 
 		<div class="col-md-12 col-sm-12 col-xs-12">
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
                   <div class="table-responsive">
                       <table class="table table-striped jambo_table bulk_action" id="thetable5">
                        <thead>
                          <tr class="headings">
                            <th class="column-title">Sr.No</th>
                            <th class="column-title">Member Name</th>
                            <th class="column-title">Designation</th>
                            <th class="column-title">Wing/Flat</th>
                            <th class="column-title">Tower</th>
                            <th class="column-title">Contact No</th>
                            <th class="column-title">Appointed On</th>
                            <th class="column-title">Dissolved On</th>
                            <th class="column-title">Photograph</th>
                          </tr>
                        </thead>
                        <tbody></tbody>
                      </table>
                    </div>
                      
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
 <script>
 
 $(document).ready(function(){
	 
	
	 
	 
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
	 
	 $('#thetable5').DataTable({
	        
	        "ordering": false,
	        "info":     false,
	        "bFilter": false
	    });
	 
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
 
 
 function getCommitteMembersForSociety(){
		var societyid = $('#societyid').val();
		var table = $('#thetable').DataTable();
		var table5 = $('#thetable5').DataTable();
			
		if(societyid.length<1)
			return false;
			
		table.clear().draw();
		table5.clear().draw();
		blockUI();
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/getCommitteMembersForSociety.do",
	        data :"societyid="+societyid,
	        success: function(response){
	        var k=1;
	        var j=1;
	        var srno=1;
	        	$.each(response, function(i, item) {
	        		$.each(item, function(i, item1) {
	        			var divid="commem"+srno;
			        	var photodiv = '<div id="'+divid+'" align="center"><img width="25" src="<%=request.getContextPath()%>/resources/images/spin.gif"></div>';
			        	
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
	        				getMemberPhoto(item1.userid,divid);
	        				srno++;
	        		 });
	        		 
	        	  });
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
		        	var photodiv = '<div id="'+divid+'" align="center"><img width="25" src="<%=request.getContextPath()%>/resources/images/spin.gif"></div>';
	        		table.row.add( [
	        			srno,
	        			item.flatno,
	        			item.wing,
	        			item.floor,
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
	        			photodiv,
	                ] ).draw( false );
	        		
	        		getMemberPhoto(item.userid,divid);
	        		srno++;
	        	 });
	        	unblockUI();
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
           alert('File Fetch failed ...');
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
		        	var photodiv = '<div id="'+divid+'" align="center"><img width="25" src="<%=request.getContextPath()%>/resources/images/spin.gif"></div>';
		        	var carddiv = '<div id="'+divid1+'" align="center"><img width="25" src="<%=request.getContextPath()%>/resources/images/spin.gif"></div>';
	        		table.row.add( [
	        			item.companyname,
	        			item.jobnature,
	        			item.contactperson,
	        			item.address,
	        			item.contactno,
	        			item.alternateno,
	        			item.email,
	        			item.remark,
	        			photodiv,
	        			carddiv,
	                ] ).draw( false );
	        	    
	        		getVendorPhotos(item.vendorid,divid);
	        		getVendorCards(item.vendorid,divid1);
	        		srno++;
	        		
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
              alert('File Fetch failed ...');
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
              alert('File Fetch failed ...');
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
              alert('File Fetch failed ...');
              unblockUI();
          });;
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