<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

 <title><tiles:insertAttribute name="title" /> | Document Management System</title>
 <tiles:insertAttribute name="imports" />  
 <tiles:insertAttribute name="importJScript" />  
 
 <br>	
<div class="col-md-12 col-sm-12 col-xs-12">
              <div class="x_panel tile">
                <div class="x_title">
                  <h2>Display Document</h2>
                  <ul class="nav navbar-right panel_toolbox">
                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                    </li> 
                  </ul>
                  <div class="pull-right">
                  	<button class="btn btn-primary btn-md" onclick="makePrintable()">Print</button>
                  	<button class="btn btn-primary btn-md" onclick="emailable()">Email</button>
                  </div>
                  <div class="clearfix"></div>
                </div>
                <div class="x_content">
                  <div class="dashboard-widget-content">
                  <div class="row">
                  	<div class="col-md-12 col-sm-12 col-xs-12">
						<c:forEach items="${dataList}" var="myItem" varStatus="loopStatus">
						 	<div class="col-md-4 col-sm-4 col-xs-12">
		  						<label class="control-label">${myItem.fieldname}</label>
		  						<div>${myItem.fieldvalue} </div> 
		  						<br>
		  					</div>
						</c:forEach>                  	
                  	</div>
                  </div>
                  
                    	<div class="row">
                    		<c:forEach items="${docList}" var="myItem" varStatus="loopStatus">
                    
						 <c:if test="${loopStatus.index%6==0}">
						 	<div class="clearfix"></div><hr>
						 </c:if>
						
						<div id="fileCont${myItem.fileid}" class="col-md-2 col-sm-2 col-xs-12 printable" align="center" style="border: thin solid #ccc">
							<button class="btn btn-xs btn-danger pull-right" onclick="deletePage('${myItem.fileid}');return false;">x</button>
							<img  class="img-fluid thumbs" width="200" src="data:image/jpeg;base64,${myItem.file}" />
							
						 </div>
						 
						
					</c:forEach>
                    	</div>
                  </div>
                </div>
              </div>
 </div>
 
 
 <script>
 	$(document).ready(function(){
 		 $(".thumbs").popImg();
 	});
 	
 	function emailable(){
 		alert("Error ! No default email id configured for this user")
 	}
 	
 	function makePrintable(){
 		$('.printable').removeClass('col-md-2');
 		$('.printable').removeClass('col-sm-2');
 		$('.printable').addClass('col-md-12');
 		$('.printable').addClass('col-sm-12');
 		$('.printable').attr('align','center');
 		$('.printable>img').attr('width','');
 		window.print();
 	}
 	
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
 	
 	
 </script>
