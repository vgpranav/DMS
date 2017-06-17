<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<input type="hidden" name="societyid" id="societyid" value="${society.societyid}">
<input type="hidden" name="userid" id="userid" value="${userObject.userid}">


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
                  <h2>Committee Members</h2>
                  <ul class="nav navbar-right panel_toolbox">
                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                    </li> 
                  </ul>
                  <div class="clearfix"></div>
                </div>
                <div class="x_content">
                  <div class="dashboard-widget-content">
                  <div class="table-responsive">
                  	Current Members
                    <table class="table table-striped jambo_table bulk_action" id="thetable">
                        <thead>
                          <tr class="headings">
                            <th class="column-title">Sr.No</th>
                            <th class="column-title">Member Name</th>
                            <th class="column-title">Designation</th>
                            <th class="column-title">Wing/Flat</th>
                            <th class="column-title">Tower</th>
                            <th class="column-title">Contact No</th>
                            <th class="column-title">Photograph</th>
                          </tr>
                        </thead>
                        <tbody></tbody>
                      </table>
                      
                      <hr/>
                      Past Members
                      
                       <div id="pastMemCont"></div>
                      
                      </div>
                  </div>
                </div>
              </div>
 </div>
 
  <div class="col-md-12 col-sm-12 col-xs-12">
              <div class="x_panel tile">
                <div class="x_title">
                  <h2>Neighbor Details</h2>
                  <ul class="nav navbar-right panel_toolbox">
                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                    </li> 
                  </ul>
                  <div class="clearfix"></div>
                </div>
                <div class="x_content">
                  <div class="dashboard-widget-content">
                  <div class="table-responsive">
                    <table class="table table-striped jambo_table bulk_action" id="thetable6">
                        <thead>
                          <tr class="headings">
                            <th class="column-title">Member Name</th>
                            <th class="column-title">Floor</th>
                            <th class="column-title">Wing/Flat</th>
                            <th class="column-title">Tower</th>
                            <th class="column-title">Contact No</th>
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
 
 
 
 <div class="clearfix"></div>
 <script>
 
 $(document).ready(function(){
	 $('#thetable').DataTable({
		 	"paging":   false,
	        "ordering": false,
	        "info":     false,
	        "bFilter": false
	    });
	 
	 $('#thetable5').DataTable({
		 	"paging":   false,
	        "ordering": false,
	        "info":     false,
	        "bFilter": false
	    });
	 
	 $('#thetable6').DataTable({
		 	"paging":   false,
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
		//getMembersForSociety();
		getSocietyPhotos();
		//getVendorsBySocId();
		getNeighborDetails();
 });
 
 
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
<%-- 			        	var photodiv = '<div id="'+divid+'" align="center"><img width="25" src="<%=request.getContextPath()%>/resources/images/spin.gif"></div>';
 --%>			        	
 
 							var photodiv = '<a class="ionimage" href="getUserImage/user/UserImages/'+item1.userid+'.do"><img src="getUserImageThumb/user/UserImages/'+item1.userid+'.do" height="30"></a>';
 
	        				if(item1.isactive==1){
	        				var removebtn = '<button class="btn btn-xs btn-danger" onClick="removeCommitteeMember(\'' + item1.committeememberid + '\')"><i class="fa fa-times"></i></button>';
			        					table.row.add( [
			        						k,
						        			item1.userName,
						        			item1.positionname, 
						        			item1.flat,
						        			item1.tower,
						        			item1.contactNo,
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
	        	$(".ionimage").ionZoom();
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
	        		table.row.add( [
	        			srno,
	        			item.flatno,
	        			item.wing,
	        			item.floor,
	        			item.tower,
	        			item.firstName + ' ' + item.lastName,
	        			item.mobileNo,
	        			item.occupancy,
	        			item.alternateno,
	        			item.email,
	        			item.aadharno,
	                ] ).draw( false );
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
 
 
 function getNeighborDetails(){
		var userid = $('#userid').val();
		
		var table = $('#thetable6').DataTable();
			
		table .clear() .draw();
		blockUI();
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/getNeighborDetails.do",
	        data :"userid="+userid,
	        success: function(response){
	        	var srno=1;
	        	$.each(response, function(i, item) {
	        		var divid="soc"+srno;
<%-- 		        	var photodiv = '<div id="'+divid+'" align="center"><img width="25" src="<%=request.getContextPath()%>/resources/images/spin.gif"></div>';
 --%>		        	
 
 					var photodiv = '<a class="ionimage1" href="getUserImage/user/UserImages/'+item.userid+'.do"><img src="getUserImageThumb/user/UserImages/'+item.userid+'.do" height="30"></a>';
	        		
 					table.row.add( [
	        			item.firstName + ' ' + item.lastName,
	        			item.floor,
	        			item.wing+'/'+item.flatno,
	        			item.tower,
	        			item.mobileNo,
	        			photodiv,
	                ] ).draw( false );
	        		//getMemberPhoto(item.userid,divid);
	        		srno++;
	        	 });
	        	$(".ionimage1").ionZoom();
	        	unblockUI();
	        },
				error : function(e) {
					notify('error','ERROR','Error occured',2000);
					unblockUI();
				}
			});
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
	        	$.each(response, function(i, item) {
	  
	        		table.row.add( [
	        			item.companyname,
	        			item.jobnature,
	        			item.contactperson,
	        			item.contactno,
	        			item.alternateno,
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
  