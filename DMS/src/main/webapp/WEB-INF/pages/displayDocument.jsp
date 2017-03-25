<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
                    		<c:forEach items="${docList}" var="myItem" varStatus="loopStatus">
                    
						<c:if test="${docId!=myItem.documentid}">
							<c:set var="docId" value="${myItem.documentid}"></c:set>
							<div class="clearfix"></div>
							<hr/>
							<b>Notie Date : ${myItem.createdon}</b>
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