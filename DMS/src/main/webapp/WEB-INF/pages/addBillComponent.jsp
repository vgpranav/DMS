<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<div class="col-md-12 col-sm-12 col-xs-12">
              <div class="x_panel tile">
                <div class="x_title">
                  <h2>Add Bill Components</h2>
                  <ul class="nav navbar-right panel_toolbox">
                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                    </li> 
                  </ul>
                  <div class="clearfix"></div>
                </div>
                <div class="x_content">
                  <div class="dashboard-widget-content">

					<div class="row">
						<div
							class="col-md-6 col-md-offset-3 col-sm-offset-3 col-sm-6 col-xs-12">
	
							<form id="addDocSubTypeForm" data-parsley-validate
								class="form-horizontal form-label-left"
								action="saveBillComponent.do" method="post">
								
								<input type="hidden" id="expenseid" name="expenseid" value="0">
								
								<div class="form-group">
									<label class="control-label col-md-4 col-sm-4 col-xs-12"
										for="first-name">Bill Component Name <span class="required">*</span>
									</label>
									<div class="col-md-8 col-sm-8 col-xs-12">
										<input type="text" id="expensename" name="expensename"
											required="required" class="form-control col-md-7 col-xs-12">
									</div>
								</div>
					
								<div class="form-group">
									<label class="control-label col-md-4 col-sm-4 col-xs-12"
										for="first-name">Parent Document <span class="required">*</span>
									</label>
									<div class="col-md-8 col-sm-8 col-xs-12">
										<select name="expensetypeid" id="expensetypeid" class="form-control">
										<c:forEach items="${ExpenseTypeList}" var="myItem" varStatus="loopStatus">
											<option value="${myItem.expensetypeid}">${myItem.expensetypename}</option>
										</c:forEach>
										</select>
									</div>
								</div>
					
								<div class="form-group">
									<label class="control-label col-md-4 col-sm-4 col-xs-12"
										for="first-name">Active<span class="required">*</span>
									</label>
									<div class="col-md-8 col-sm-8 col-xs-12" style="padding-top:6px;">
										<input type="radio" name="isactive" value="1" > Yes
										<input type="radio" name="isactive" value="0" checked="checked"> No
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
 </div>
 
 
 
 <div class="col-md-12 col-sm-12 col-xs-12">
              <div class="x_panel tile">
                <div class="x_title">
                  <h2>Existing Bill Components</h2>
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
                            <th class="column-title">Expense Name</th>
                            <th class="column-title">Expense Type </th>
                            <th class="column-title">Created On </th>
                            <th class="column-title">Status </th>
                            <th class="column-title">Edit</th>
                            <th class="column-title">Delete</th> 
                          </tr>
                        </thead>
  
                        <tbody>
                        	<c:forEach items="${ExpenseMasterList}" var="myItem" varStatus="loopStatus">
								
								<c:if test="${loopStatus.index%2==0}">
									<tr class="even pointer">
								</c:if>
								<c:if test="${loopStatus.index%2!=0}">
									<tr class="odd pointer">
								</c:if>
								 
									<td class=" ">${myItem.expensename}</td>
									<td class=" ">${ExpenseTypeList[myItem.expensetypeid].expensetypename}</td> 
									<td class=" "><fmt:formatDate type = "date" value = "${myItem.createdon}" /></td>
									<td class=" ">
									<c:if test="${myItem.isactive==1}">
											Active
										</c:if>
										<c:if test="${myItem.isactive!=1}">
											Inactive
									</c:if>
									</td> 								
									<td class=" ">
										<a class="btn btn-default btn-sm" onclick="editDocSubtype('${myItem.expenseid}')">
											<i class="fa fa-edit"></i>
										</a>
									</td>
									<td class=" ">
										<a class="btn btn-default btn-sm" onclick="genericRemove('${myItem.expenseid}','IPR_ExpenseMaster','expenseid','reload')">
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
	$(document).ready(function(){
		$('#thetable').DataTable();
	});
	

function editDocSubtype(expenseid){
		
		editMode();
		blockUI();
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/getExpenseByid.do",
	        data :"expenseid="+expenseid,
	        success: function(response){
	        	if(response.expenseid>0) {
	        		//notify('success','SUCCESS','Added Successfully',2000);
	        		$('#expenseid').val(response.expenseid);
	        		$('#expensename').val(response.expensename); 
	        		$('#expensetypeid option[value="'+response.expensetypeid+'"]').prop("selected",true).change();
	        		$('input[name=isactive][value="'+response.isactive+'"]').prop("checked","checked").change();
	        	} 
	        	unblockUI();
	        },
				error : function(e) {
					notify('error','ERROR','Error occured',2000);
					unblockUI();
				}
			}); 
	}
</script>