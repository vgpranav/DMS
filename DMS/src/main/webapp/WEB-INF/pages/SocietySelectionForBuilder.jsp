<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<div class="col-md-12 col-sm-12 col-xs-12">
              <div class="x_panel tile">
                <div class="x_title">
                  <h2>Builder-Project Selection</h2>
                  <ul class="nav navbar-right panel_toolbox">
                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                    </li> 
                  </ul>
                  <div class="clearfix"></div>
                </div>
                <div class="x_content">
                  <div class="dashboard-widget-content">
                  <div class="row">
                  	
                  	<c:forEach items="${builderList}" var="myItem0" varStatus="loopStatus0">
                  		<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="x_panel tile">
								<div class="x_title">
					                  <h2>Builder &amp; Developer / Architect</h2>
					                  <div class="clearfix"></div>
					            </div>
								<div class="pull-left">
									<h3>${myItem0.buildername}</h3> 
									<em>Address: ${myItem0.premisesname}</em>
											<br><em>${myItem0.streetname}</em>
											<br><em>${myItem0.area}, ${myItem0.city}, ${myItem0.state}</em>
											<br><em>${myItem0.country}</em>
											<br><em>Pincode: ${myItem0.pincode}</em>
											
								</div>
							</div>
						</div>
						
						
						<c:forEach items="${projectList}" var="myItem1" varStatus="loopStatus1">
							<c:if test="${myItem0.builderid==myItem1.builderid}">
							
								<div style="margin-left:15px !important" class="col-md-12 col-sm-12 col-xs-12">
									<div class="x_panel tile">
										<div class="x_title">
							                  <h2>Project</h2>
							                  <div class="clearfix"></div>
							            </div>
							                
											<div class="col-md-6 col-sm-6 col-xs-12">
												<h3>${myItem1.projectname}</h3> 
												<em>Site Address: ${myItem1.street}, ${myItem1.city}, ${myItem1.state}</em>
												<br><em>${myItem1.country}</em>
												<br><em>Pincode: ${myItem1.pincode}</em>
												<br><em>Reg Date: <fmt:formatDate type = "date" value = "${myItem1.registrationdate}" /></em>
											</div>
											<div class="col-md-6 col-sm-6 col-xs-12">
													<br><em>No. of Towers: ${myItem1.towernos}</em>
													<br><em>No. of Residence: ${myItem1.resnos}</em>
													<br><em>No. of Bunglows: ${myItem1.bungnos}</em>
													<br><em>No. of Penta House: ${myItem1.pentanos}</em>
													<br><em>No. of Shops: ${myItem1.shopnos}</em>
													<br><em>No. of Commercial Workshop: ${myItem1.galanos}</em>
											</div>
									</div>
								</div>
							
							
							
								<div style="margin-left:30px !important;display: none;" class="col-md-12 col-sm-12 col-xs-12" id="sp${loopStatus1.index}">
									<div class="x_panel tile">
										<div class="x_title">
							                  <h2>Sub Project</h2>
							                  <div class="clearfix"></div>
							            </div>
							                
							            <c:forEach items="${societyList}" var="myItem2" varStatus="loopStatus2">
											<c:if test="${myItem1.projectid==myItem2.projectid}">
												<div class="col-sm-4 col-md-4 col-xs-12">
													<article class="media event">
														<a href="displayAdminPanelBySocId.do?societyid=${myItem2.societyid}" class="btn">${myItem2.societyname}</a>
														<script>
															$('#sp${loopStatus1.index}').show();
														</script>
													</article>
												</div>
											</c:if>
										</c:forEach>
											 
									</div>
								</div>
								
								
							</c:if>
						</c:forEach>
						
						
						
                  	</c:forEach>
                  	
                  
                  
                    <c:forEach items="${societyList}" var="myItem" varStatus="loopStatus">
						<%-- <option value="${myItem.societyid}">${myItem.societyname}</option> --%>
						
					</c:forEach>
                  </div>
                  </div>
                </div>
              </div>
 </div>