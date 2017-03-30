<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

 <div class="col-md-12 col-sm-12 col-xs-12">
              <div class="x_panel tile">
                <div class="x_title">
                  <h2>Existing Document Type</h2>
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
                            <th class="column-title">Sr No</th>
                            <th class="column-title">Reference No </th>
                            <th class="column-title">Initiator Name </th>
                            <th class="column-title">Bldg type </th>
                            <th class="column-title">Bldg No. </th>
                            <th class="column-title">Street Name </th>
                            <th class="column-title">Landmark </th>
                            <th class="column-title">Area </th>
                            <th class="column-title">Pincode </th>
                            <th class="column-title">City </th>
                            <th class="column-title">State </th>
                            <th class="column-title">Country </th>
                            <th class="column-title">No. of Residence </th>
                            <th class="column-title">No. of Shops </th>
                            <th class="column-title">Initiate Date </th>
                            <th class="column-title">Contact Persons</th>
                            <th class="column-title">Meeting/Call Details</th>
                            <th class="column-title">Remark </th>
                            <th class="column-title">Closing Chance </th>
                          </tr>
                        </thead>

                        <tbody>
                        	<c:forEach items="${calls}" var="myItem" varStatus="loopStatus">
								<c:if test="${loopStatus.index%2==0}">
									<tr class="even pointer">
								</c:if>
								<c:if test="${loopStatus.index%2!=0}">
									<tr class="odd pointer">
								</c:if>
									<td class=" ">${loopStatus.index+1}</td>
									<td class=" ">${myItem.refno}</td>
									<td class=" ">${myItem.initiatorname}</td>
									<td class=" ">${myItem.societytype}</td>
									<td class=" ">${myItem.buildingno}</td>
									<td class=" ">${myItem.streetname}</td>
									<td class=" ">${myItem.landmark}</td>
									<td class=" ">${myItem.area}</td>
									<td class=" ">${myItem.pincode}</td>
									<td class=" ">${myItem.city}</td>
									<td class=" ">${myItem.state}</td>
									<td class=" ">${myItem.country}</td>
									<td class=" ">${myItem.resno}</td>
									<td class=" ">${myItem.shopsno}</td>
									<td class=" ">${myItem.initiatedate}</td>
									<td class=" " align="center"><a href="getRefContactpage.do?callrefid=${myItem.callrefid}" class="btn btn-sm btn-default"><i class="fa fa-external-link"></i></a></td>
									<td class=" " align="center"><a href="getRefMeetingpage.do?callrefid=${myItem.callrefid}" class="btn btn-sm btn-default"><i class="fa fa-external-link"></i></a></td>
									<td class=" ">${myItem.remark}</td>
									<td class=" ">${myItem.closingchance}</td>
									
								<%-- 	<td class=" ">
										<c:if test="${myItem.active==1}">
											Active
										</c:if>
										<c:if test="${myItem.active!=1}">
											Inactive
										</c:if>
									</td> --%>								
									
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
	$(document).ready(function(){
		$('#thetable').DataTable();
	});
</script>
 