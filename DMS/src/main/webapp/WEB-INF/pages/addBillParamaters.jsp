<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="col-md-12 col-sm-12 col-xs-12">
              <div class="x_panel tile">
                <div class="x_title">
                  <h2>Add Bill Parameters</h2>
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
			
                     <form id="addBillParamForm" data-parsley-validate
					class="form-horizontal form-label-left" action="addBillParamData.do"
					method="post" onsubmit="return saveFormFields()">	
					
					<input type="hidden" id="billstructureid" name="billstructureid" value="${billstructureid}">
					<input type="hidden" id="billparamdataid" name="billparamdataid" value="0">
					
					<div class="form-group">
						<div class=" pull-right">
							<span class="required">All charges in Rupees</span>
						</div>
					</div>
					
					
					<div class="form-group">
					<hr/>
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name">Select Maintenance charge type <span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<select name="mttype" id="mttype" class="form-control" onchange="changemttype()">
							<option value=""> -- select --</option>
								<option value="mtarea">Based on Flat Area</option>
								<option value="mtlumpsum">Lumpsum Amount</option>
							</select>
						</div>
					</div>
					
					<div  id="mtls" style="display: none;">
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12" for="first-name">
							1 RHK
						</label>
						<div class="col-md-2 col-sm-2 col-xs-12">
							<input type="text" id="mt1rk" name="mt1rk"  class="numeric form-control col-md-7 col-xs-12" placeholder="Rupees" value="0">
						</div>
					 
						<label class="control-label col-md-4 col-sm-4 col-xs-12" for="first-name">
							1 BHK
						</label>
						<div class="col-md-2 col-sm-2 col-xs-12">
							<input type="text" id="mt1bhk" name="mt1bhk"  class="numeric form-control col-md-7 col-xs-12" placeholder="Rupees" value="0">
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12" for="first-name">
							1.5 BHK
						</label>
						<div class="col-md-2 col-sm-2 col-xs-12">
							<input type="text" id="mt1p5bhk" name="mt1p5bhk"  class="numeric form-control col-md-7 col-xs-12" placeholder="Rupees" value="0">
						</div>
					 
						<label class="control-label col-md-4 col-sm-4 col-xs-12" for="first-name">
							2 BHK
						</label>
						<div class="col-md-2 col-sm-2 col-xs-12">
							<input type="text" id="mt2bhk" name="mt2bhk"  class="numeric form-control col-md-7 col-xs-12" placeholder="Rupees" value="0">
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12" for="first-name">
							2.5 BHK
						</label>
						<div class="col-md-2 col-sm-2 col-xs-12">
							<input type="text" id="mt2p5bhk" name="mt2p5bhk"  class="numeric form-control col-md-7 col-xs-12" placeholder="Rupees" value="0">
						</div>
					 
						<label class="control-label col-md-4 col-sm-4 col-xs-12" for="first-name">
							3 BHK
						</label>
						<div class="col-md-2 col-sm-2 col-xs-12">
							<input type="text" id="mt3bhk" name="mt3bhk"  class="numeric form-control col-md-7 col-xs-12" placeholder="Rupees" value="0">
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4 col-xs-12" for="first-name">
							3.5 BHK
						</label>
						<div class="col-md-2 col-sm-2 col-xs-12">
							<input type="text" id="mt3p5bhk" name="mt3p5bhk"  class="numeric form-control col-md-7 col-xs-12" placeholder="Rupees" value="0">
						</div>
					 
						<label class="control-label col-md-4 col-sm-4 col-xs-12" for="first-name">
							Shop
						</label>
						<div class="col-md-2 col-sm-2 col-xs-12">
							<input type="text" id="shop" name="shop"  class="numeric form-control col-md-7 col-xs-12" placeholder="Rupees" value="0">
						</div>
					</div>
					</div>
					
					<div class="form-group" id="mtps" style="display: none;">
						<label class="control-label col-md-4 col-sm-4 col-xs-12" for="first-name">
							Charge per Sq. Ft.
						</label>
						<div class="col-md-2 col-sm-2 col-xs-12">
							<input type="text" id="mtpsqft" name="mtpsqft"  class="numeric form-control col-md-7 col-xs-12" placeholder="Rupees" value="0">
						</div>
					</div>
					
					
					<div class="form-group">
					<hr/>
						 <strong>Parking Charges</strong> 
					</div>
					
					<div class="form-group">
						 <strong>Stilt parking</strong> 
					</div>
					<div class="form-group">
						<label class="control-label col-md-2 col-sm-2 col-xs-12" for="first-name">
							2 Wheeler
						</label>
						<div class="col-md-2 col-sm-2 col-xs-12">
							<input type="text" id="sp2w" name="sp2w"  class="numeric form-control col-md-7 col-xs-12" placeholder="Rupees" value="0">
						</div>
						<label class="control-label col-md-2 col-sm-2 col-xs-12" for="first-name">
							3 Wheeler
						</label>
						<div class="col-md-2 col-sm-2 col-xs-12">
							<input type="text" id="sp3w" name="sp3w"  class="numeric form-control col-md-7 col-xs-12" placeholder="Rupees" value="0">
						</div>
						<label class="control-label col-md-2 col-sm-2 col-xs-12" for="first-name">
							4 Wheeler
						</label>
						<div class="col-md-2 col-sm-2 col-xs-12">
							<input type="text" id="sp4w" name="sp4w"  class="numeric form-control col-md-7 col-xs-12" placeholder="Rupees"  value="0">
						</div>
					</div>
					
					
					<div class="form-group">
						 <strong>Open parking</strong> 
					</div>
					<div class="form-group">
						<label class="control-label col-md-2 col-sm-2 col-xs-12" for="first-name">
							2 Wheeler
						</label>
						<div class="col-md-2 col-sm-2 col-xs-12">
							<input type="text" id="op2w" name="op2w"  class="numeric form-control col-md-7 col-xs-12" placeholder="Rupees"  value="0">
						</div>
						<label class="control-label col-md-2 col-sm-2 col-xs-12" for="first-name">
							3 Wheeler
						</label>
						<div class="col-md-2 col-sm-2 col-xs-12">
							<input type="text" id="op3w" name="op3w"  class="numeric form-control col-md-7 col-xs-12" placeholder="Rupees"  value="0">
						</div>
						<label class="control-label col-md-2 col-sm-2 col-xs-12" for="first-name">
							4 Wheeler
						</label>
						<div class="col-md-2 col-sm-2 col-xs-12">
							<input type="text" id="op4w" name="op4w"  class="numeric form-control col-md-7 col-xs-12" placeholder="Rupees"  value="0">
						</div>
					</div>
					
					
					<div class="form-group">
					<hr/>
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name">NOC Charges<span class="required">*</span>
						</label>
						<div class="col-md-8 col-sm-8 col-xs-12">
							<input type="text" id="nocval" name="nocval"
								 class="numeric form-control col-md-7 col-xs-12" placeholder="Rupees"  value="0">
						</div>
					</div>
					
					
					<div class="form-group">
					<hr/>
						<label class="control-label col-md-4 col-sm-4 col-xs-12"
							for="first-name">Penalty Charges for Delayed Payment<span class="required">*</span>
						</label>
						<div class="col-md-4 col-sm-4 col-xs-12">
							<select name="pcdelaykey" id="pcdelaykey" class="form-control" onchange="">
									<option value="percent">Percentage</option>
									<option value="amount">Lumpsum Amount</option>
							</select>
						</div>
					 
						<div class="col-md-4 col-sm-4 col-xs-12">
							<input type="text" id="pcdelayval" name="pcdelayval"
								 class="form-control col-md-7 col-xs-12" value="0">
						</div>
					</div>
					
					<div class="form-group" align="right">
					<br/><br/><br/>
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
 
 <script>
 
 $(document).ready(function(){
	 getBillParamsByStructureid();
 });
 
 function changemttype(){
	 var mttype = $('#mttype').val();
	 if(mttype=="mtarea"){
		 $('#mtps').show();
		 $('#mtls').hide();
	 }else{
		 $('#mtls').show();
		 $('#mtps').hide();
	 }
 }
 
 $('.numeric').change(function(){
	 if(!validateNumber(this.value)){
		 alert('Only numeric value allowed');
		 this.value='';
	 }
		 
 });
 
 function getBillParamsByStructureid(){
		var billstructureid = '${billstructureid}';
		blockUI();
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/getBillParamsByStructureid.do",
	        data :"billstructureid="+billstructureid,
	        success: function(response){
				if(response.length>0){
					
					$('#billparamdataid').val(response.billparamdataid);
					$('#billstructureid').val(response.billstructureid);
					$('#pcdelayval').val(response.pcdelayval);
					$('#nocval').val(response.nocval);
					$('#op4w').val(response.op4w);
					$('#op3w').val(response.op3w);
					$('#op2w').val(response.op2w);
					$('#sp4w').val(response.sp4w);
					$('#sp3w').val(response.sp3w);
					$('#sp2w').val(response.sp2w);
					$('#mtpsqft').val(response.mtpsqft);
					$('#shop').val(response.shop);
					$('#mt3p5bhk').val(response.mt3p5bhk);
					$('#mt3bhk').val(response.mt3bhk);
					$('#mt2p5bhk').val(response.mt2p5bhk);
					$('#mt2bhk').val(response.mt2bhk);
					$('#mt1p5bhk').val(response.mt1p5bhk);
					$('#mt1bhk').val(response.mt1bhk);
					$('#mt1rk').val(response.mt1rk);
					 
					$('#mttype option[value="'+response.mttype+'"]').prop("selected",true).change();
					$('#pcdelaykey option[value="'+response.pcdelaykey+'"]').prop("selected",true).change();
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