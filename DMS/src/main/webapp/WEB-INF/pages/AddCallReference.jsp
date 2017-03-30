<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="col-md-12 col-sm-12 col-xs-12">
              <div class="x_panel tile">
                <div class="x_title">
                  <h2>Add Call Reference</h2>
                  <ul class="nav navbar-right panel_toolbox">
                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                    </li> 
                  </ul>
                  <div class="clearfix"></div>
                </div>
                <div class="x_content">
                  <div class="dashboard-widget-content">
                    
                    
                    <div class="row">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<form id="addDocSubTypeForm" data-parsley-validate
							class="form-horizontal form-label-left" action="saveCallRef.do" method="post"
							 >
							
							<input type="hidden" id="callrefid" name="callrefid" value="0">
							
							<div class="col-md-12 col-sm-12 col-xs-12">
								<br /> 
								<h2><span class="label label-success label-md">Call Details</span></h2> 
								<hr />
							</div>
							<div class="clearfix"></div>
							
							<div class="form-group">
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Call Ref No. <span class="required">*</span>
								</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="refno" name="refno"
											class="form-control col-md-12 col-xs-12" required="required" value=${callrefid} readonly="readonly">
									</div>
								</div>
								
								
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Name of Initiator<span class="required">*</span>
								</label>
								<div class="col-md-4 col-sm-4 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="initiatorname" name="initiatorname"
											class="form-control col-md-12 col-xs-12" required="required">
									</div>
								</div>
								
							</div>
							
							<div class="form-group">
							
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Society Type <span class="required">*</span>
								</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<select name="societytype" id="societytype" class="form-control col-md-12 col-xs-12">
											<option value="residential">Residential</option>
											<option value="commercial">Commercial</option>
											<option value="builder">Builders & Developers</option>
										</select>
									</div>
								</div>
								
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Building Number <span class="required">*</span>
								</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="buildingno" name="buildingno	"
											class="form-control col-md-12 col-xs-12" required="required">
									</div>
								</div>
								
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Street Name<span class="required">*</span>
								</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="streetname" name="streetname"
											class="form-control col-md-12 col-xs-12" required="required">
									</div>
								</div>
								
							</div>
							
							<div class="form-group">
							
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Landmark<span class="required">*</span>
								</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="landmark" name="landmark"
											class="form-control col-md-12 col-xs-12" required="required">
									</div>
								</div>
								
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Area<span class="required">*</span>
								</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="area" name="area"
											class="form-control col-md-12 col-xs-12" required="required">
									</div>
								</div>
								
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Pincode<span class="required">*</span>
								</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="pincode" name="pincode"
											class="form-control col-md-12 col-xs-12" required="required">
									</div>
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">City<span class="required">*</span>
								</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="city" name="city"
											class="form-control col-md-12 col-xs-12" required="required">
									</div>
								</div>
								
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">State<span class="required">*</span>
								</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="state" name="state"
											class="form-control col-md-12 col-xs-12" required="required">
									</div>
								</div>
								
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Country<span class="required">*</span>
								</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="country" name="country"
											class="form-control col-md-12 col-xs-12" required="required">
									</div>
								</div>
							
							</div>
							
							
							<div class="form-group">
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">No. of Residence<span class="required">*</span>
								</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="resno" name="resno"
											class="form-control col-md-12 col-xs-12" required="required">
									</div>
								</div>
								
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">No. of Shops<span class="required">*</span>
								</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="shopsno" name="shopsno"
											class="form-control col-md-12 col-xs-12" required="required">
									</div>
								</div>
								
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Date of Initiate<span class="required">*</span>
								</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="initiatedate" name="initiatedate"
											class="customdatepicker form-control col-md-12 col-xs-12" required="required">
									</div>
								</div>
							
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Order Closing Chance<span class="required">*</span>
								</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<select name="closingchance" id="closingchance" class="form-control col-md-12 col-xs-12">
											<option value="yes">Yes</option>
											<option value="no">No</option>
										</select>
									</div>
								</div>
								
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Conclusion Remark<span class="required">*</span>
								</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<input type="text" id="remark" name="remark"
											class="form-control col-md-12 col-xs-12" required="required">
									</div>
								</div>
								
								 
							</div>
							
							
							<div class="form-group" align="center">
								<br />
								<br />
								<button class="btn btn-warning" type="reset">Reset</button>
								<button class="btn btn-success"  type="submit">Save</button>
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
 </div>