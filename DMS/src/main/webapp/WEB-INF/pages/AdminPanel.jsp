<input type="hidden" name="societyid" id="societyid" value="${society.societyid}">


<div class="col-md-12 col-sm-12 col-xs-12">
	<div class="x_panel tile">
		<div class="pull-left">
			<h3>${society.societyname}</h3>
			<em>${society.addressline1}</em>
			<br><em>${society.addressline2}</em>
			<br><em>${society.ward} ${society.district} ${society.state}</em>
		</div>
		<div class="pull-right">
		<div align="left">
			<b>Society Reg. No. ${society.registrationno}</b>
			<br><b>Estd. on ${society.estdate}</b>
		</div>
		</div>
	</div>
</div>

<div class="col-md-6 col-sm-6 col-xs-12">
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
                    <table class="table table-striped jambo_table bulk_action" id="thetable">
                        <thead>
                          <tr class="headings">
                            <th class="column-title">Sr.No</th>
                            <th class="column-title">Member Name</th>
                            <th class="column-title">Designation</th>
                            <th class="column-title">Flat</th>
                            <th class="column-title">Contact No</th>
                          </tr>
                        </thead>
                        <tbody></tbody>
                      </table>
                      </div>
                  </div>
                </div>
              </div>
 </div>
 
 <div class="col-md-6 col-sm-6 col-xs-12">
              <div class="x_panel tile">
                <div class="x_title">
                  <h2>Building Photographs</h2>
                  <ul class="nav navbar-right panel_toolbox">
                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                    </li> 
                  </ul>
                  <div class="clearfix"></div>
                </div>
                <div class="x_content">
                  <div class="dashboard-widget-content">
                    <div id="imgContainer" style="max-height: 300px !important; overflow-y:auto" ></div>
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
							<th class="column-title">Flat No</th>
							<th class="column-title">Wing</th>
							<th class="column-title">Floor</th>
							<th class="column-title">Tower</th>
							<th class="column-title">Tenant Name</th>
							<th class="column-title">Mobile No</th>
							<th class="column-title">Occupancy</th>
							<th class="column-title">Alternate No</th>
							<th class="column-title">Email</th>
							<th class="column-title">Aadhar No</th>
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
                    
                  </div>
                </div>
              </div>
 </div>
 
 <script>
 
 $(document).ready(function(){
	 $('#thetable').DataTable({
	        "paging":   false,
	        "ordering": false,
	        "info":     false,
	        "bFilter": false
	    });
	 
	 $('#thetable1').DataTable({
	        "paging":   false,
	        "ordering": false,
	        "info":     false,
	        "bFilter": false
	    });
	 
		getCommitteMembersForSociety();
		getMembersForSociety();
		getSocietyPhotos();
 });
 
 
 function getCommitteMembersForSociety(){
		var societyid = $('#societyid').val();
		var table = $('#thetable').DataTable();
		var table1 = $('#thetable1').DataTable();
			
		if(societyid.length<1)
			return false;
			
		table.clear().draw();
		table1.clear().draw();
		
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/getCommitteMembersForSociety.do",
	        data :"societyid="+societyid,
	        success: function(response){
	        var k=1;
	        var j=1;
	        
	        	$.each(response, function(i, item) {
	        		$.each(item, function(i, item1) {
	        				if(item1.isactive==1){
	        				var removebtn = '<button class="btn btn-xs btn-danger" onClick="removeCommitteeMember(\'' + item1.committeememberid + '\')"><i class="fa fa-times"></i></button>';
			        					table.row.add( [
			        					k,
					        			item1.userName,
					        			item1.positionname, 
					        			//new Date(item1.appointedon).toString("dd MMM yyyy"),
					        			item1.flat,
					        			item1.contactNo
				                ] ).draw( false ); 
				                k++;
	        				} 
	        		 });
	        		 
	        	  });
	        },
				error : function(e) {
					notify('error','ERROR','Error occured',2000);
				}
			});
	}
 
 
 function getMembersForSociety(){
		var societyid = $('#societyid').val();
		var table = $('#thetable1').DataTable();
			
		table .clear() .draw();
		
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
	        },
				error : function(e) {
					notify('error','ERROR','Error occured',2000);
				}
			});
	}
	
	function getSocietyPhotos(){
    	
    	var societyid = $('#societyid').val();
    	
    	$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/getSocietyPhotos.do",
	        data :"societyid="+societyid,
	        enctype: 'multipart/form-data',
	        processData: false,
            contentType: false 
			}).done(function(data) {
			var img='<ul class="imgslider"><li><br></li>';
			var cnt =1;
               $.each(data, function(i, item) {
               		img += '<li><img height="200" width="300" src="data:' + item.contenttype + ';base64,' +  item.file + '"/></li>';
               		if(cnt%3==0){
               			//img+='<div class="clearfix"></div><br/>';
               		}
               		cnt++;
               });
               img += '</ul>';
               $('#imgContainer').html(img);
          }).fail(function(jqXHR, textStatus) {
              alert('File Fetch failed ...');
          });;
    }
 </script>
 
 <style>
	 ul.imgslider{
	 	list-style-type:none;
	 	display: flex;
        justify-content:space-around;
	 }
 
 	ul.imgslider > li {
 		display: inline;
 	}
 	
 </style>