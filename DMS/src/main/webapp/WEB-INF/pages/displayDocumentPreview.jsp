<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
 <!DOCTYPE html>
<html lang="en">
  <head>
     <title><tiles:insertAttribute name="title" /> | Document Management System</title>
	 <script src="<%= request.getContextPath() %>/resources/theme/vendors/printjs/print.min.js"></script>
	 <link href="<%= request.getContextPath() %>/resources/theme/vendors/printjs/print.min.css" rel="stylesheet">
 </head>
 <body>
 <div align="center"><button onclick="printPage()">Print</button></div>
 <table id="printElement">
                  		
                  		 <c:set var="docIdx" value=""></c:set>
                  		 
                  		 
                  		 <c:forEach items="${dataList}" var="myItemX" varStatus="loopStatusX">
                  		 	<c:if test="${docIdx!=myItemX.documentid}">
								 
								 	<c:set var="docIdx" value="${myItemX.documentid}"></c:set>
									
									
										<%-- <c:forEach items="${dataList}" var="myItem" varStatus="loopStatus">
										 
										<c:if test="${docIdx==myItem.documentid}">
											
											<div class="col-md-4 col-sm-4 col-xs-12">
						  						<label class="control-label">${myItem.fieldname}</label>
						  						<div>${myItem.fieldvalue} </div> 
						  						<br>
						  					</div>
						  					
										</c:if>
										 	
										</c:forEach>        --%>           	
		                  		
 		                  		
		                    		<c:forEach items="${docList}" var="myItem1" varStatus="loopStatus">
 		                    			<c:if test="${docIdx==myItem1.documentid}">
		                    				 <tr>
		                    				 	<td>
		                    				 		<img width="98%" src="getFileToDisplay/${myItem1.filename}.do">
		                    				 	</td>
		                    				 </tr>
		                    			</c:if>
								
									</c:forEach>
									
									
                    	
                  		 	</c:if>
                  		 </c:forEach>
                  		
</table>                 
  

<input type="hidden" name="documentId" id="documentId" value="${documentid}">
  
 <script>
 
	
	function printPage(){
		/* printJS('printElement', 'html') */
		window.print();
	}
 	
 </script>
    
    
 </body>
</html>