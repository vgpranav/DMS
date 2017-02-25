<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="row">
	<div class="col-md-12 col-sm-12 col-xs-12">

		<div class="dashboard_graph">

			<div class="row x_title">
				<div class="col-md-6">
					<h3>
						Add Document Type<small></small>
					</h3>
				</div>
			</div>
			
			<div class="row">
			<div class="col-md-6 col-md-offset-3 col-sm-offset-3 col-sm-6 col-xs-12">
 				<form id="addSocietyForm" data-parsley-validate
					class="form-horizontal form-label-left" action="saveDocumentType.do"
					method="post">

					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name"> Document Name <span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<input type="text" id="doctypename" name="doctypename"
								required="required" class="form-control col-md-7 col-xs-12">
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name"> Document Description <span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<input type="text" id="doctypedesc" name="doctypedesc"
								required="required" class="form-control col-md-7 col-xs-12">
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name"> Status <span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<input type="radio" name="active" value="1"> Active
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
			 <div class="row">
				<div class="col-md-12 col-sm-12 col-xs-12">
 			<hr/>
			<div class="table-responsive">
                      <table class="table table-striped jambo_table bulk_action">
                        <thead>
                          <tr class="headings">
                            <th class="column-title">Document Type</th>
                            <th class="column-title">Document Description </th>
                            <th class="column-title">Created By </th>
                            <th class="column-title">Created On </th>
                            <th class="column-title">Status </th>
                            <th class="column-title no-link last">
                            	<span class="nobr">Action</span>
                            </th>
                             
                          </tr>
                        </thead>

                        <tbody>
                        	<c:forEach items="${docTypesList}" var="myItem" varStatus="loopStatus">
								<c:if test="${loopStatus.index%2==0}">
									<tr class="even pointer">
								</c:if>
								<c:if test="${loopStatus.index%2!=0}">
									<tr class="odd pointer">
								</c:if>
									<td class=" ">${myItem.doctypename}</td>
									<td class=" ">${myItem.doctypedesc}</td>
									<td class=" ">${myItem.createdby}</td>
									<td class=" ">${myItem.createdon}</td>
									<td class=" ">${myItem.active}</td>								
									<td class=" ">${myItem.doctypeid}</td>
								</tr>
							</c:forEach>
                        </tbody>
                      </table>
                    </div>
                    </div>
                  </div>
					
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br /> 
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br /> 
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br /> aaaaaaaaaaaaa

			<div class="clearfix"></div>
		</div>
	</div>
</div>