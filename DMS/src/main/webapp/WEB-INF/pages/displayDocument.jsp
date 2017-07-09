<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
 <!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <link href="<%= request.getContextPath() %>/resources/theme/vendors/print.css" rel="stylesheet" media="print">
    
    
    
 <title><tiles:insertAttribute name="title" /> | Document Management System</title>
 <tiles:insertAttribute name="imports" />  
 <script type="text/javascript">
 	var picxx = [];
 </script>
 
 </head>
 <body>
 <br>	
<div class="col-md-12 col-sm-12 col-xs-12">
              <div class="x_panel tile">
                <div class="x_title">
                  <h2 class="no-print">Display Document</h2>
                  <ul class="nav navbar-right panel_toolbox no-print">
                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                    </li> 
                  </ul>
                  <div class="pull-right">
                  	<button class="btn btn-primary btn-md no-print" onclick="makePrintable()">Print Preview</button>
                  	<button class="btn btn-primary btn-md no-print" onclick="emailable()">Email</button>
                  </div>
                  <div class="clearfix"></div>
                </div>
                <div class="x_content">
                  <div class="dashboard-widget-content">
                  		
                  		 <c:set var="docIdx" value=""></c:set>
                  		 
                  		 
                  		 <c:forEach items="${dataList}" var="myItemX" varStatus="loopStatusX">
                  		 	<c:if test="${docIdx!=myItemX.documentid}">
								 <c:set var="docIdx" value="${myItemX.documentid}"></c:set>
								  	<hr/>	
								  <div class="row">
				                  	<div class="col-md-12 col-sm-12 col-xs-12">
										<c:forEach items="${dataList}" var="myItem" varStatus="loopStatus">
										 
										<c:if test="${docIdx==myItem.documentid}">
											
											<div class="col-md-4 col-sm-4 col-xs-12">
						  						<label class="control-label">${myItem.fieldname}</label>
						  						<div>${myItem.fieldvalue} </div> 
						  						<br>
						  					</div>
						  					
										</c:if>
										 	
										</c:forEach>                  	
				                  	</div>
		                  		</div>
		                  		
		                  		<hr/>
 		                  		<div class="row" id="printElement">
		                    		<c:forEach items="${docList}" var="myItem1" varStatus="loopStatus">
 		                    			<c:if test="${docIdx==myItem1.documentid}">
		                    				
		                    				 <script type="text/javascript">
		                    					picxx.push('${myItem.filename}');
			                    			 </script>
			                    			
											 <c:if test="${loopStatus.index%6==0}">
											 	<div class="clearfix"></div>
											 </c:if>
		                  		
							  				 <div id="fileCont${myItem1.fileid}" class="col-md-2 col-sm-2 col-xs-12 printable" align="center" style="border: thin solid #ccc">
												<c:if test="${sessionScope.deleteFlag==1}"><button class="no-print btn btn-xs btn-danger pull-right delBtn" onclick="deletePage('${myItem1.fileid}');return false;">x</button></c:if>
												<a class="ionimage2"   href="getFileToDisplay/${myItem1.filename}.do">
													<img  width="200" src="getFileToDisplayThumb/${myItem1.filename}.do">
												</a>
											 </div>
		                    			
		                    			</c:if>
								
									</c:forEach>
                    			</div>
                    	
                  		 	</c:if>
                  		 </c:forEach>
                  		
                  </div>
                </div>
              </div>
 </div>
 
 	<div id="emailDialog">
		<form method="post" action="#">
		  <br>
              <div>
                <input type="text" name="emailId" id="emailId" class="form-control" placeholder="Enter Email Id"/>
              </div>
              <div align="center">
              	<button class="btn btn-warning" onclick="sendEmail();return false;" style="margin-top: 10px;" id="otpbtn">Send Mail</button>
              </div>
 			</form>
	</div>

<input type="hidden" name="documentId" id="documentId" value="${documentid}">
 
 <!-- <button onclick="callIXZ();return false;">click</button> -->
 <script>
 
 function sendEmail(){
	 var emailId = $("#emailId").val();
	 var documentId = $("#documentId").val();
	 
	 if(validateEmail(emailId)){
		 blockUI();
			$.ajax({
		        type: "GET",
		        url: "<%=request.getContextPath()%>/sendDocumentAsMail.do",
		        data :"toEmailId="+emailId
	       				+"&documentId="+documentId,
		        success: function(response){
		        //alert()
		        	if(response=='success') {
		        		notify('success','Email Sent','Your Document Has been Sent',2000);
		        		$( "#emailDialog" ).dialog( "close" );
		        	} 
		        	 unblockUI();
		        },
					error : function(e) {
						notify('error','ERROR','Error occured',2000);
						 unblockUI();
					}
				});
	 }
	 
 }
 
 function validateEmail(email) {
	    var x = email;
	    var atpos = x.indexOf("@");
	    var dotpos = x.lastIndexOf(".");
	    if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length) {
	        alert("Not a valid e-mail address");
	        return false;
	    }
	    return true;
 }
 
 function callIXZ(){
	 $(".ionimage2").ionZoom();
 }
 	$(document).ready(function(){
 		$(".ionimage2").ionZoom();
 		
 		$( "#emailDialog" ).dialog({
			  autoOpen: false,
			  modal: true,
			  title: "Email this Document",
			  width: 270,
			  height: 250
		});
 		
 	});
 	
 	
  
 	function emailable(){
		$( "#emailDialog" ).dialog( "open" );
 	}
 	
 	 function makePrintable(){
 		/* $('.printable').removeClass('col-md-2');
 		$('.printable').removeClass('col-sm-2');
 		$('.printable').addClass('col-md-12');
 		$('.printable').addClass('col-sm-12');
 		$('.printable').attr('align','center');
 		$('.printable img').attr('width','98%'); */
 		/* $('.delBtn').hide(); */
 		
		/* var imgs = document.images,
	    len = imgs.length,
	    counter = 0;
	
		[].forEach.call( imgs, function( img ) {
		    img.addEventListener( 'load', incrementCounter, false );
		} ); */
		
 		/* parent.print(); */
 		  /* printJS('printElement', 'html')  */
 		/* $('.no-print').hide(); */
 		/* $('a.print-preview').trigger('click'); */
 		var documentId = $('#documentId').val();
 		popitup("<%=request.getContextPath()%>/displayDocumentFromSearchPreview.do?documentid="+documentId,'Print Preview')
 		 
 	}  
 	
 	 
 

	/* function incrementCounter() {
	    counter++;
	    if ( counter === len ) {
	 		 alert("All Images loaded");
	}
	} */
	
 	function deletePage(filesid){
 		if(confirm('Are you Sure?')){
			if(filesid.length < 1){
 			alert('Some Mandatory Fields  are Missing');
 			return false;
 		} else { 
 			blockUI();
 			$.ajax({
 		        type: "GET",
 		        url: "<%=request.getContextPath()%>/deleteDocumentPage.do",
 		       data :"filesid="+filesid,
 		        success: function(response){
 		        //alert()
  		        	if(response=='success') {
 		        		notify('success','SUCCESS','Page Deleted',1000);
 		        		$('#fileCont'+filesid).hide();
 		        	}  else {
 		        		notify('error','Failed','Failed to Delete page',1000);
 		        	}
 		        unblockUI();
 		        },
 					error : function(e) {
 						notify('error','ERROR','Error occured',2000);
 						unblockUI();
 					}
 				});
 		}
		}
 		return false;
 	}
 	
	
	function popitup(url,windowName) {
	       newwindow=window.open(url,windowName,'height=600,width=650');
	       if (window.focus) {newwindow.focus()}
	       return false;
	     }
	
 	
 </script>
  <tiles:insertAttribute name="importJScript" />
    
 </body>
</html>