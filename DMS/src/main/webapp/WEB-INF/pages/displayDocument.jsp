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
                    
						<c:if test="${docId!=myItem.documentid}">
							<c:set var="docId" value="${myItem.documentid}"></c:set>
							<div class="clearfix"></div>
							<hr/>
							 
							<hr/>
						</c:if>
						
						<div class="col-md-6 col-sm-6 col-xs-12">
						<img  class="img-fluid" width="500" src="data:image/jpeg;base64,${myItem.file}"/>
						 </div>
					</c:forEach>
                    	</div>
                  </div>
                </div>
              </div>
 </div>
