<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<div class="col-md-12 col-sm-12 col-xs-12">
              <div class="x_panel tile">
                <div class="x_title">
                  <h2>Create Admin User</h2>
                  <ul class="nav navbar-right panel_toolbox">
                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                    </li> 
                  </ul>
                  <div class="clearfix"></div>
                </div>
                <div class="x_content">
                  <div class="dashboard-widget-content">
                    <div class="col-md-7 col-sm-7 col-xs-12">
						<form id="addSocietyForm" data-parsley-validate
							class="form-horizontal form-label-left" action="saveAdminUser.do"
							method="post" onsubmit="return validate();">

							<input type="hidden" id="userid" name="userid" value="0">
							
							<div class="form-group">
							<label class="control-label col-md-4 col-sm-4 col-xs-12"
								for="first-name">User Type<span class="required">*</span>
							</label>
							<div class="col-md-8 col-sm-8 col-xs-12">
								<select name="userType" id="userType"
								class="form-control" onchange="changeUserType()">
									<option value="existing">Existing</option>
									<option value="new">New</option>
							</select>
							</div>
							</div>
							
							
							<div class="form-group" id="existinguserdiv">
							<label class="control-label col-md-4 col-sm-4 col-xs-12"
								for="first-name">Username <span class="required">*</span>
							</label>
							<div class="col-md-8 col-sm-8 col-xs-12">
								<input type="text" id="username" name="username"  class="form-control col-md-7 col-xs-12">
							</div>
							</div>
							
							<div id="newuserdiv" style="display: none;">

							<div class="form-group">
							<label class="control-label col-md-4 col-sm-4 col-xs-12"
								for="first-name">First Name <span class="required">*</span>
							</label>
							<div class="col-md-8 col-sm-8 col-xs-12">
								<input type="text" id="firstName" name="firstName"
									 class="form-control col-md-7 col-xs-12">
							</div>
							</div>
							
							
							
							<div class="form-group">
							<label class="control-label col-md-4 col-sm-4 col-xs-12"
								for="first-name">Last Name <span class="required">*</span>
							</label>
							<div class="col-md-8 col-sm-8 col-xs-12">
								<input type="text" id="lastName" name="lastName"
									 class="form-control col-md-7 col-xs-12">
							</div>
							</div>
							
							<div class="form-group">
							<label class="control-label col-md-4 col-sm-4 col-xs-12"
								for="first-name">Mobile No <span class="required">*</span>
							</label>
							<div class="col-md-8 col-sm-8 col-xs-12">
								<input type="text" id="mobileNo" name="mobileNo"
									 class="form-control col-md-7 col-xs-12">
							</div>
							</div>
							
							<div class="form-group">
							<label class="control-label col-md-4 col-sm-4 col-xs-12"
								for="first-name">Password <span class="required">*</span>
							</label>
							<div class="col-md-8 col-sm-8 col-xs-12">
								<input type="text" id="password" name="password"
									 class="form-control col-md-7 col-xs-12">
							</div>
							</div>
							
							<div class="form-group">
							<label class="control-label col-md-4 col-sm-4 col-xs-12"
								for="first-name">Role <span class="required">*</span>
							</label>
							<div class="col-md-8 col-sm-8 col-xs-12">
								<select name="userroleid" id="userroleid"
								class="form-control">
								<c:forEach items="${roleList}" var="myItem"
									varStatus="loopStatus">
									<option value="${myItem.roleid}">${myItem.rolename}</option>
								</c:forEach>
							</select>
							</div>
							</div>
							
							<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name"> Status <span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12" style="padding-top:7px;">
							<input type="radio" name="active" value="1" checked="checked"> Active
							<input type="radio" name="active" value="0"> Inactive
						</div>
					</div>


							</div>
							
							
							<div class="form-group" align="right">
						<button class="btn btn-warning" type="reset">Reset</button>
						<button class="btn btn-success">Save</button>
					</div>
					
					<div align="center">
						<c:if test="${not empty error}">
		                  <script>
				                $(document).ready(function(){
				                	notify('success','SUCCESS','${error}',2000);
				                });
						  </script>
						  <div class="text-success">${error}</div>
						</c:if>
					</div>
							
					</form>
					</div>
					
                  </div>
                </div>
              </div>
 </div>
 
 
 <div class="col-md-12 col-sm-12 col-xs-12">
              <div class="x_panel tile">
                <div class="x_title">
                  <h2>Existing Builders</h2>
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
                            <th class="column-title">User Name</th>
                            <th class="column-title">Mobile No</th>
                            <th class="column-title">Role</th>
                            <th class="column-title">Created On</th>
                            <th class="column-title">Status </th>
                            <th class="column-title">Edit</th>
                            <th class="column-title">Remove</th>
                             
                          </tr>
                        </thead>

                        <tbody>
                        	<c:forEach items="${adminUsers}" var="myItem" varStatus="loopStatus">
								<c:if test="${loopStatus.index%2==0}">
									<tr class="even pointer">
								</c:if>
								<c:if test="${loopStatus.index%2!=0}">
									<tr class="odd pointer">
								</c:if>
									<td class=" ">${myItem.firstName} ${myItem.lastName}</td>
									<td class=" ">${myItem.mobileNo}</td>
									<td class=" ">${myItem.userrolename}</td>
									<td class=" "><fmt:formatDate type = "date" value = "${myItem.createDate}" /></td>
									<td class=" ">
										<c:if test="${myItem.active==1}">
											Active
										</c:if>
										<c:if test="${myItem.active!=1}">
											Inactive
										</c:if>
									</td>								
									<td class=" ">
										<a class="btn btn-default btn-sm" onclick="editAdminUser('${myItem.userid}')">
											<i class="fa fa-edit"></i>
										</a>
									</td>
									<td class=" ">
										<a class="btn btn-default btn-sm" onclick="genericRemove('${myItem.userid}','user','userid','reload')">
											<i class="fa fa-times"></i>
										</a>
									</td>
								</tr>
							</c:forEach>
                        </tbody>
                      </table>
                    </div>
                  </div>
                </div>
              </div>
 </div>
 
 <script>
 
 
 function validate(){
	 
	 var password = $('#password').val();
	 if(password.length<5){
			alert("Password Should be min 5 characters");
			return false;
	 }
	 return true;
 }
 
 $(document).ready(function(){
		$('#thetable').DataTable();
		
		$( "#username").autocomplete({
			 source: function (request, response) {
		            $.getJSON("${pageContext. request. contextPath}/userAutosuggest.do", {
		            	searchText: request.term,
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
 
		 
 function editAdminUser(userid){
	 
	 
	 
	 blockUI();
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/editAdminUser.do",
	        data :"userid="+userid ,
	        success: function(response){
	        	if(response.userid>0) {
	        		//notify('success','SUCCESS','Added Successfully',2000);
	        		$('#userid').val(response.userid);
	        		$('#firstName').val(response.firstName);
	        		$('#lastName').val(response.lastName);
	        		$('#mobileNo').val(response.mobileNo);
	        		$('#password').val(response.password);
	        		$('#userroleid option[value="'+response.userroleid+'"]').prop("selected",true).change();
	        		$('input[name=active][value="'+response.active+'"]').prop("checked","checked").change();
	        		
	        		$('#userType option[value="new"]').prop("selected",true).change();
	        	}  
	        	unblockUI();
	        },
				error : function(e) {
					notify('error','ERROR','Error occured',2000);
					unblockUI();
				}
			});
		 
	}
 
 
 function changeUserType(){
	 var userType = $('#userType').val();
	 if(userType=='existing'){
		 $('#existinguserdiv').show();
		 $('#newuserdiv').hide();
		 
	 }else{
		 $('#existinguserdiv').hide();
		 $('#newuserdiv').show();
		 
	 }
	 
	 
 }
 </script>