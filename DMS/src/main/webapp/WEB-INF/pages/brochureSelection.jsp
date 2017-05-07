<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="col-md-12 col-sm-12 col-xs-12">
              <div class="x_panel tile">
                <div class="x_title">
                  <h2>Add Builder Brochure</h2>
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
					class="form-horizontal form-label-left" action="viewBrochure.do"
					method="post">

					<div class="form-group">
						
						<c:if test="${type=='builder'}">
							
							<label class="control-label col-md-4 col-sm-4 col-xs-12" for="first-name">
								Select Builder <span class="required">*</span>
							</label>
							<div class="col-md-8 col-sm-8 col-xs-12">
								<select name="genId" id="builderid" class="form-control">
								<option value=""> -- select -- </option>
								<c:forEach items="${builderList}" var="myItem" varStatus="loopStatus">
									<option value="${myItem.builderid}">${myItem.buildername}</option>
								</c:forEach>
								</select>
							</div>
							 
						</c:if>
					
						
						<c:if test="${type=='project'}">
						
							<div class="form-group">
								<label class="control-label col-md-4 col-sm-4 col-xs-12"
									for="first-name">Select Builder <span class="required">*</span>
								</label>
								<div class="col-md-8 col-sm-8 col-xs-12">
									<select name="builderid" id="builderid" class="form-control" onchange="getProjects()">
									<option value=""> -- select -- </option>
									<c:forEach items="${builderList}" var="myItem" varStatus="loopStatus">
										<option value="${myItem.builderid}">${myItem.buildername}</option>
									</c:forEach>
									</select>
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-4 col-sm-4 col-xs-12"
									for="first-name">Select Project
								</label>
								<div class="col-md-8 col-sm-8 col-xs-12">
									<select name="genId" id="projectid" class="form-control" onchange="getSubProjects()" required="required">
										<option value=""> -- select -- </option>
									</select>
								</div>
							</div>
							
						</c:if>
						
							<c:if test="${type=='subproject'}">
						
							<div class="form-group">
								<label class="control-label col-md-4 col-sm-4 col-xs-12"
									for="first-name">Select Builder <span class="required">*</span>
								</label>
								<div class="col-md-8 col-sm-8 col-xs-12">
									<select name="builderid" id="builderid" class="form-control" onchange="getProjects()">
									<option value=""> -- select -- </option>
									<c:forEach items="${builderList}" var="myItem" varStatus="loopStatus">
										<option value="${myItem.builderid}">${myItem.buildername}</option>
									</c:forEach>
									</select>
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-4 col-sm-4 col-xs-12"
									for="first-name">Select project
								</label>
								<div class="col-md-8 col-sm-8 col-xs-12">
									<select name="projectid" id="projectid" class="form-control" onchange="getSubProjects()" required="required">
										<option value=""> -- select -- </option>
									</select>
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-4 col-sm-4 col-xs-12"
									for="first-name">Select Sub Project
								</label>
								<div class="col-md-8 col-sm-8 col-xs-12">
									<select name="genId" id="societyid" class="form-control" required="required">
										<option value=""> -- select -- </option>
									</select>
								</div>
							</div>
						</c:if>
					</div>
					
					<input type="hidden" name="typeVal" value="${typeVal}">
					
					<div class="form-group" align="right">
						<button class="btn btn-success">Next</button>
					</div>
					
					</form>
					</div>
					</div>
                  </div>
                </div>
              </div>
 </div>
 
  
 <script>
 
 
 function getProjects(){
		var builderid = $('#builderid').val();
		blockUI();
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/getProjectsByBuilderId.do",
	        data :"builderid="+builderid,
	        success: function(response){ 
	        		var $select = $('#projectid');                        
	        	    $select.find('option').remove(); 
	        	    $select.append($("<option />").val('').text(' -- select --'));
	        	    $.each(response, function() {
	        	    	$select.append($("<option />").val(this.projectid).text(this.projectname));
	        	    });
	        	    unblockUI();
	        },
				error : function(e) {
					notify('error','ERROR','Error occured',2000);
					unblockUI();
				}
			});
	}
 
 function getSubProjects(){
		var projectid = $('#projectid').val();
		blockUI();
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/getSubProjectsByProjectId.do",
	        data :"projectid="+projectid,
	        success: function(response){ 
	        		var $select = $('#societyid');                        
	        	    $select.find('option').remove(); 
	        	    $select.append($("<option />").val('').text(' -- select --'));
	        	    $.each(response, function() {
	        	    	$select.append($("<option />").val(this.societyid).text(this.societyname));
	        	    });
	        	    unblockUI();
	        },
				error : function(e) {
					notify('error','ERROR','Error occured',2000);
					unblockUI();
				}
			});
	}
 </script>