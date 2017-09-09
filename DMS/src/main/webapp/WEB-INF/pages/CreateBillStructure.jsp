<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>


<%
String[] months = {"blank","January",      
		   "February",
		   "March",        
		   "April",        
		   "May",          
		   "June",         
		   "July",         
		   "August",       
		   "September",    
		   "October",      
		   "November",     
		   "December"};
%>
<div class="col-md-12 col-sm-12 col-xs-12">
	<div class="x_panel tile">
		<div class="x_title">
			<h2>Create Bill Structure</h2>
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
							action="saveBillStructure.do" method="post" onsubmit="return validate();">
 
							<div class="form-group">
								<label class="control-label col-md-4 col-sm-4 col-xs-12"
									for="first-name">Select Society<span class="required">*</span>
								</label>
								<div class="col-md-8 col-sm-8 col-xs-12">
									<select name="societyid" id="societyid" class="form-control">
									<c:forEach items="${societyList}" var="myItem" varStatus="loopStatus">
										<option value="${myItem.societyid}">${myItem.societyname}</option>
									</c:forEach>
									</select>
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-4 col-sm-4 col-xs-12"
									for="first-name">Select Year<span class="required">*</span>
								</label>
								<div class="col-md-8 col-sm-8 col-xs-12">
									<select name="year" id="year" class="form-control">
									<% 
										int minYear = Integer.parseInt(request.getAttribute("minYear").toString());
										int maxYear = Integer.parseInt(request.getAttribute("maxYear").toString());
											
										for(int i=minYear;i<=maxYear;i++) {%>
											<option value="<%=i%>"><%=i%></option>
										<%}%>
										
									</select>
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-4 col-sm-4 col-xs-12"
									for="first-name">Select Bill Type<span class="required">*</span>
								</label>
								<div class="col-md-8 col-sm-8 col-xs-12">
									<select name="billcycletype" id="billcycletype" class="form-control" onchange="changeBC();">
										<option value="1">Monthly</option>
										<option value="3">Quarterly</option>
									</select>
								</div>
							</div>
							
							<div class="form-group"  id="bc">
								<label class="control-label col-md-4 col-sm-4 col-xs-12"
									for="first-name">Select Month<span class="required">*</span>
								</label>
								<div class="col-md-8 col-sm-8 col-xs-12">
									<select name="billcyclevalue" id="billcyclevalue" class="form-control">
									<% for(int i=1;i<=12;i++) {%>
											<option value="<%=i%>"><%=months[i]%></option>
										<%}%>
									</select>
								</div>
							</div>
							
							<div class="form-group" style="display: none;" id="bc1">
								<label class="control-label col-md-4 col-sm-4 col-xs-12"
									for="first-name">Select Quarter<span class="required">*</span>
								</label>
								<div class="col-md-8 col-sm-8 col-xs-12">
									<select name="billcyclevalue1" id="billcyclevalue1" class="form-control">
										<option value="q1">Q1 (Jan - Mar)</option>
										<option value="q2">Q2 (Apr - Jun)</option>
										<option value="q3">Q3 (Jul - Sep)</option>
										<option value="q4">Q4 (Oct - Dec)</option>
									</select>
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-4 col-sm-4 col-xs-12"
									for="first-name">Select Bill Components<span class="required">*</span>
								</label>
								<div class="col-md-8 col-sm-8 col-xs-12">
									<hr/>
									<c:forEach items="${expenseList}" var="myItem" varStatus="loopStatus">
										<c:if test="${myItem.expensetypeid==1 || myItem.expensetypeid==3}">
											<input type="checkbox" name="expense" value="${myItem.expenseid}"> <strong>${myItem.expensename}</strong><br/>									
										</c:if>
									</c:forEach>
								</div>
							</div>
							
							<input type="hidden" name="billcomponents" id="billcomponents">
							
							<div class="form-group" align="center">
								<button class="btn btn-warning" type="reset">Reset</button>
								<button class="btn btn-success">Save</button>
							</div>
							
							<c:if test="${not empty billstructureid}">
								   <script>
						                $(document).ready(function(){
						                	notify('success','SUCCESS','Bill Structure Added',2000);
						                });
								  </script>
								  <div class="text-success">Bill Structure Added</div>	
							</c:if>
						</form>
					</div>
				</div>


			</div>
		</div>
	</div>
</div>


<script>
function validate(){
	
	var str="";
	$('input[type=checkbox]').each(function () {
        if (this.checked) {
            if(str.length<1)
            	str+=$(this).val();
            else
            	str+=","+$(this).val()
        }
	});
	console.log("Test : "+str);
	$("#billcomponents").val(str);
	return true;
}

function changeBC(){
	var type =$('#billcycletype').val();
	if(type=="1"){
		$('#bc').show();
		$('#bc1').hide();
	}else{
		$('#bc1').show();
		$('#bc').hide();
	}
}
</script>