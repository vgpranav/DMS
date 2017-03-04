<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="row">
	<div class="col-md-12 col-sm-12 col-xs-12">

		<div class="dashboard_graph">

			<div class="row x_title">
				<div class="col-md-6">
					<h3>
						Add Document <small>Fill Data</small>
					</h3>
				</div>
			</div>
			
			<div class="row">
			<div class="col-md-6 col-md-offset-3 col-sm-offset-3 col-sm-6 col-xs-12">
 				<form id="addDocSubTypeForm" data-parsley-validate
					class="form-horizontal form-label-left" action="addDocument3.do"
					method="post">
					
					<input type="hidden" name ="societyid" value="${document.societyid}">
					<input type="hidden" name ="doctypeid" value="${document.doctypeid}">
					<input type="hidden" name ="docsubtypeid" value="${document.docsubtypeid}">
					
					
					<c:forEach items="${formFieldsList}" var="myItem" varStatus="loopStatus">
						<div class="form-group">
							<label class="control-label col-md-4 col-sm-4 col-xs-12"
								for="first-name"> ${myItem.fieldname} 
								<c:if test="${myItem.fieldtype=='mandatory'}">
								<span class="required">*</span>
								</c:if>
							</label>
							<div class="col-md-8 col-sm-8 col-xs-12">
								<input 
									type="text" 
									id="${myItem.fieldid}" 
									name="${myItem.fieldid}"
										<c:if test="${myItem.fieldtype=='mandatory'}">
											required="required"
										</c:if>
										<c:if test="${myItem.datatype=='date'}">
											class="form-control col-md-7 col-xs-12 customdatepicker"
											readonly
										</c:if>
										<c:if test="${myItem.datatype!='date'}">
											class="form-control col-md-7 col-xs-12"
										</c:if> 
								/>
							</div>
						</div>
					</c:forEach>
					 
					<div class="form-group" align="right">
						<button class="btn btn-warning" type="reset">Reset</button>
						<button class="btn btn-success">Next</button>
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
			 					
			<div class="clearfix"></div>
		</div>
	</div>
</div>
<script>
$(document).ready(function(){
	 
});

</script>