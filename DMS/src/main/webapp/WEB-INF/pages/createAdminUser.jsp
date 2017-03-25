<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="col-md-12 col-sm-12 col-xs-12">
              <div class="x_panel tile">
                <div class="x_title">
                  <h2>Quick Settings</h2>
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
							method="post">

							<input type="hidden" id="societyid" name="societyid" value="0">
									
							<div class="form-group">
							<label class="control-label col-md-4 col-sm-4 col-xs-12"
								for="first-name">Name <span class="required">*</span>
							</label>
							<div class="col-md-8 col-sm-8 col-xs-12">
								<input type="text" id="societyname" name="societyname"
									required="required" class="form-control col-md-7 col-xs-12">
							</div>
							</div>
							
							<div class="form-group">
							<label class="control-label col-md-4 col-sm-4 col-xs-12"
								for="first-name">Mobile No <span class="required">*</span>
							</label>
							<div class="col-md-8 col-sm-8 col-xs-12">
								<input type="text" id="societyname" name="societyname"
									required="required" class="form-control col-md-7 col-xs-12">
							</div>
							</div>
							
							<div class="form-group">
							<label class="control-label col-md-4 col-sm-4 col-xs-12"
								for="first-name">Password <span class="required">*</span>
							</label>
							<div class="col-md-8 col-sm-8 col-xs-12">
								<input type="text" id="societyname" name="societyname"
									required="required" class="form-control col-md-7 col-xs-12">
							</div>
							</div>
							
							<div class="form-group">
							<label class="control-label col-md-4 col-sm-4 col-xs-12"
								for="first-name">Role <span class="required">*</span>
							</label>
							<div class="col-md-8 col-sm-8 col-xs-12">
								<select name="projectid" id="projectid"
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