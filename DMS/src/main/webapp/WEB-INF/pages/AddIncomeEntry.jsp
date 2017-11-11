<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="col-md-12 col-sm-12 col-xs-12">
	<div class="x_panel tile">
		<div class="x_title">
			<h2>Add Income Entry</h2>
			<div class="pull-right">
				<select name="societyid" id="societyid" class="form-control"
					onchange="getMembersForSociety()">
					<option value="">-- select society --</option>
					<c:forEach items="${societyList}" var="myItem"
						varStatus="loopStatus">
						<option value="${myItem.societyid}">${myItem.societyname}</option>
					</c:forEach>
				</select>
			</div>
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
						<form id="addIPRFileForm" data-parsley-validate
							class="form-horizontal form-label-left" action="#" method="post"
							onsubmit="return false;">
							
							<input type="hidden" name="randomHash" id="randomHash" value="${randomHash}">

							<div class="form-group">
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Head<span class="required">*</span>
								</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<select name="doctypeid" id="doctypeid" class="form-control">
										<c:forEach items="${headList}" var="myItem"
											varStatus="loopStatus">
											<option value="${myItem.incexpmasterid}">${myItem.keyname}</option>
										</c:forEach>
									</select>
								</div>


								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Posting Under Head<span
									class="required">*</span>
								</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<select name="doctypeid" id="doctypeid" class="form-control">
										<c:forEach items="${postingHeadList}" var="myItem1"
											varStatus="loopStatus1">
											<option value="${myItem1.incexpmasterid}">${myItem1.keyname}</option>
										</c:forEach>
									</select>
								</div>

							</div>
							<div class="form-group">

								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Ledger<span class="required">*</span>
								</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<select name="doctypeid" id="doctypeid" class="form-control">
										<c:forEach items="${ledgerList}" var="myItem2"
											varStatus="loopStatus2">
											<option value="${myItem2.incexpmasterid}">${myItem2.keyname}</option>
										</c:forEach>
									</select>
								</div>


								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Tax<span class="required">*</span>
								</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<select name="doctypeid" id="doctypeid" class="form-control">
										<c:forEach items="${taxList}" var="myItem3"
											varStatus="loopStatus3">
											<option value="${myItem3.incexpmasterid}">${myItem3.keyname}</option>
										</c:forEach>
									</select>
								</div>

							</div>

							<br />

							<div class="form-group">

								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Purpose </label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<input type="text" id="floor" name="floor"
										class="form-control col-md-12 col-xs-12" placeholder="">
								</div>

								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Income Date </label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<input type="text" id="floor" name="floor"
										class="form-control col-md-12 col-xs-12 customdatepicker1"
										readonly="readonly">
								</div>

							</div>

							<br />

							<div class="form-group">

								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Basic Amount</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<input type="text" id="floor" name="floor"
										class="form-control col-md-12 col-xs-12" placeholder="">
								</div>


								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Tax Amount</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<input type="text" id="floor" name="floor"
										class="form-control col-md-12 col-xs-12" placeholder="">
								</div>

								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Total Amount</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<input type="text" id="floor" name="floor"
										class="form-control col-md-12 col-xs-12" placeholder="">
								</div>

							</div>

							<br />

							<div class="form-group">

								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Mode Of Payment<span class="required">*</span></label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<select class="form-control" name="tenantcompanytype"
											id="tenantcompanytype">
											<option value="cheque">Cheque</option>
											<option value="cash">Cash</option>
											<option value="netbanking">Netbanking</option>
										</select>
									</div>
								</div>

								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Bank Name/Acc No</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<input type="text" id="floor" name="floor"
										class="form-control col-md-12 col-xs-12" placeholder="">
								</div>

								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Cheque No/Txn No</label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<input type="text" id="floor" name="floor"
										class="form-control col-md-12 col-xs-12" placeholder="">
								</div>

							</div>

							<br />

							<div class="form-group">

								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Deposited By </label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<input type="text" id="floor" name="floor"
										class="form-control col-md-12 col-xs-12">
								</div>

								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Transaction Date </label>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<input type="text" id="floor" name="floor"
										class="form-control col-md-12 col-xs-12 customdatepicker1"
										readonly="readonly">
								</div>
							</div>

							<hr/>
							
							<div class="form-group">
								
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Attach Bill <span class="required">*</span>
								</label>
								<div class="col-md-3 col-sm-3 col-xs-12">
									<input type="file" name="INCbillFile" id="INCbillFile"/>
								</div>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<button id="btnBillUpload" type="button" class="btn btn-primary btn-sm">
										Upload Bill
									</button>
								</div> 
								
							</div>
							
							<div class="form-group">
								<div class="col-sm-4 col-sm-offset-2 col-xs-12">
								<div id="INCbillFileCont"></div>
								 
								</div>
							</div>
							
							<div class="form-group">
								
								<label class="control-label col-md-2 col-sm-2 col-xs-12"
									for="first-name">Attach Receipt <span class="required">*</span>
								</label>
								<div class="col-md-3 col-sm-3 col-xs-12">
									<input type="file" name="INCReceiptFile" id="INCReceiptFile"/>
								</div>
								<div class="col-md-2 col-sm-2 col-xs-12">
									<button id="btnReceiptUpload" type="button" class="btn btn-primary btn-sm">
										Upload Receipt
									</button>
								</div> 
								
							</div>
							
							<div class="form-group">
								<div class="col-sm-4 col-sm-offset-2 col-xs-12">
								<div id="INCreceiptFileCont"></div>
								</div>
							</div>
							
							<input type="hidden" name="fileType" id="fileType">
							
						</form>
					</div>
				</div>

			</div>
		</div>
	</div>
</div>



<script>

$(document).ready(function() {
	
	/*  if (!(isJpg(filename) || isPng(filename))) {
	    alert('Please browse a JPG/PNG file to upload ...');
	    return;
	} */ 
	
	 $('#btnBillUpload').on('click', function() {
	        //var filename = $.trim(file.val());
	       $('#fileType').val("INCbill");
	       
	       
	       if($('#INCbillFile').val().length<1)
		       return false;
	       
	        
	        blockUI();
	        $.ajax({
	            url: 'uploadIPRFiles.do',
	            type: "POST",
	            data: new FormData(document.getElementById("addIPRFileForm")),
	            enctype: 'multipart/form-data',
	            processData: false,
	            contentType: false
	          }).done(function(data) {
	        	  if(data.filename.length>0){
	        		  var files = '<span id="filespan'+ data.fileId +'"><i class="fa fa-file"></i> <a href="#" style="text-decoration: underline;">'+data.filename+'</a>  <a onclick="deleteIPRFile(\'' + data.fileId + '\')"><font color="red">[x]</font></a></span><br/>';
		        	  $('#INCbillFileCont').append(files); 
	        	  }
	        	  unblockUI(); 
	          }).fail(function(jqXHR, textStatus) {
	              alert('File upload failed ...');
	              unblockUI();
	          });
	    });
	
	
	 $('#btnReceiptUpload').on('click', function() {
	        //var filename = $.trim(file.val());
	        $('#fileType').val("INCReceipt");
	       
	        if($('#INCReceiptFile').val().length<1)
	 	       return false;
	        
	        blockUI();
	        $.ajax({
	            url: 'uploadIPRFiles.do',
	            type: "POST",
	            data: new FormData(document.getElementById("addIPRFileForm")),
	            enctype: 'multipart/form-data',
	            processData: false,
	            contentType: false
	          }).done(function(data) {
	        	  if(data.filename.length>0){
	        	  	var files = '<span id="filespan'+ data.fileId +'"><i class="fa fa-file"></i> <a href="#" style="text-decoration: underline;">'+data.filename+'</a>  <a onclick="deleteIPRFile(\'' + data.fileId + '\')"><font color="red">[x]</font></a></span><br/>';
	        	  	$('#INCreceiptFileCont').append(files);
	        	  }
	        	  unblockUI(); 
	          }).fail(function(jqXHR, textStatus) {
	              alert('File upload failed ...');
	              unblockUI();
	          });
	    });
	
	
});


function deleteIPRFile(fileId){
	blockUI();
	$.ajax({
        type: "GET",
        url: "<%=request.getContextPath()%>/deleteIPRFileByFileId.do",
        data :"filesid="+fileId,
        success: function(response){
        	if(response=='success') {
        		$('#filespan'+fileId).hide();
        		notify('success','SUCCESS','Deleted Successfully',2000);
        	}  
        	unblockUI();
        },
			error : function(e) {
				notify('error','ERROR','Error occured',2000);
				unblockUI();
			}
		});
	
	return false;
	
}
 
 
 
 
</script>