<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="col-md-12 col-sm-12 col-xs-12">
              <div class="x_panel tile">
                <div class="x_title">
                  <h2>Add Noticeboard Document</h2>
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
					class="form-horizontal form-label-left" action="addDocument3.do"
					method="post">

					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name">Select Society <span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<select name="societyid" id="societyid" class="form-control" onchange="getDocTypes()">
							<option value=""> -- select -- </option>
							<c:forEach items="${societyList}" var="myItem" varStatus="loopStatus">
								<option value="${myItem.societyid}">${myItem.societyname}</option>
							</c:forEach>
							</select>
						</div>
						
						<input type="hidden" name="doctypeid" value="995">
						<input type="hidden" name="docsubtypeid" value="995">
						<input type="hidden" name="userid" value="995">
						
					</div>
					
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