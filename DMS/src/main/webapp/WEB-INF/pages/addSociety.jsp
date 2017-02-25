 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="row">
	<div class="col-md-12 col-sm-12 col-xs-12">

		<div class="dashboard_graph">

			<div class="row x_title">
				<div class="col-md-6">
					<h3>
						Create / Edit Society
					</h3>
				</div>

			</div>

			<div class="col-md-7 col-sm-7 col-xs-12">
				<form id="addSocietyForm" data-parsley-validate class="form-horizontal form-label-left" action="saveSociety.do" method="post">
	 				
	 				<div class="form-group">
		                <label class="control-label col-md-4 col-sm-4 col-xs-12" for="first-name">	
		                	 Society Name <span class="required">*</span> 
		                </label>
		                <div class="col-md-8 col-sm-8 col-xs-12">
		                	<input type="text" id="societyname" name="societyname" required="required" class="form-control col-md-7 col-xs-12">
		                </div>
	                </div>
	                
	                <div class="form-group">
		                <label class="control-label col-md-4 col-sm-4 col-xs-12" for="first-name">	
		                	 Society Type <span class="required">*</span>  
		                </label>
		                <div class="col-md-8 col-sm-8 col-xs-12">
		                	<select name="societytypeid" id="societytypeid" class="form-control">
			                	<c:forEach items="${societytypeList}" var="myItem" varStatus="loopStatus">
		                			<option value="${myItem.societytypeid}">${myItem.societytypename}</option>
		                		</c:forEach>
		                	</select>
		                </div>
	                </div>
	 				  
	 				<div class="form-group">
		                <label class="control-label col-md-4 col-sm-4 col-xs-12" for="first-name">	
		                	Address Line 1 <span class="required">*</span> 
		                </label>
		                <div class="col-md-8 col-sm-8 col-xs-12">
		                	<input type="text" id="addressline1" name="addressline1" required="required" class="form-control col-md-7 col-xs-12">
		                </div>
	                </div>
	                
	                <div class="form-group">
		                <label class="control-label col-md-4 col-sm-4 col-xs-12" for="first-name">	
		                	Address Line 2 <span class="required"></span>  
		                </label>
		                <div class="col-md-8 col-sm-8 col-xs-12">
		                	<input type="text" id="addressline2" name="addressline2" required="required" class="form-control col-md-7 col-xs-12">
		                </div>
	                </div>
	                
	                <div class="form-group">
		                <label class="control-label col-md-4 col-sm-4 col-xs-12" for="first-name">	
		                	Ward / Zone / Sector <span class="required">*</span> 
		                </label>
		                <div class="col-md-8 col-sm-8 col-xs-12">
		                	<input type="text" id="ward" name="ward" required="required" class="form-control col-md-7 col-xs-12">
		                </div>
	                </div>
	                
	                <div class="form-group">
		                <label class="control-label col-md-4 col-sm-4 col-xs-12" for="first-name">	
		                	District <span class="required">*</span> 
		                </label>
		                <div class="col-md-8 col-sm-8 col-xs-12">
		                	<input type="text" id="district" name="district" required="required" class="form-control col-md-7 col-xs-12">
		                </div>
	                </div>
	                
	                <div class="form-group">
		                <label class="control-label col-md-4 col-sm-4 col-xs-12" for="first-name">	
		                	State <span class="required">*</span> 
		                </label>
		                <div class="col-md-8 col-sm-8 col-xs-12">
		                	<input type="text" id="state" name="state" required="required" class="form-control col-md-7 col-xs-12">
		                </div>
	                </div>
	                
	                <div class="form-group">
		                <label class="control-label col-md-4 col-sm-4 col-xs-12" for="first-name">	
		                	Pincode <span class="required">*</span> 
		                </label>
		                <div class="col-md-8 col-sm-8 col-xs-12">
		                	<input type="text" id="pincode" name="pincode" required="required" class="form-control col-md-7 col-xs-12">
		                </div>
	                </div>
	                
	               <div class="form-group" align="right">
	               		<button class="btn btn-warning" type="reset">Reset</button>
	               		<button class="btn btn-success">Save</button>
	               </div>
	                
	           </form>
			</div>	
			
			<div class="col-md-5 col-sm-5 col-xs-12" >
				     <div align="center">Search Society</div> 
					<div class="form-group" >
	               		 <div class="col-md-offset-2 col-md-8 col-sm-offset-2 col-sm-8 col-xs-12">
		                	<input type="text" id="searchText" name="searchText" placeholder="Enter Society Name" required="required" class="form-control col-md-12 col-xs-12">
		                </div>
	               </div>
			</div>
			
			<div class="clearfix"></div>
		</div>
	</div>
</div>


<script>
	$(document).ready(function(){
		
		$( "#searchText").autocomplete({
			 source: function (request, response) {
		            $.getJSON("${pageContext. request. contextPath}/societyAutosuggest.do", {
		            	searchText: request.term,
		            }, response);
		        },
			focus: function () {
		       // prevent value inserted on focus
		       return false;
		   },
		   select: function (event, ui) {
		       var thisValue = ui.item.value;
		       var str = thisValue.split("--");
		       for (var i = 0; i < str.length; i++) {
		       	 ui.item.value=str[0]; 
		       }
			}
		});
		
	});

</script>