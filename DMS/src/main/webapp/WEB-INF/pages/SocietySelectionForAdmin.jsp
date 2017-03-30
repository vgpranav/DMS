<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="col-md-12 col-sm-12 col-xs-12">
              <div class="x_panel tile">
                <div class="x_title">
                  <h2>Society Selection</h2>
                  <ul class="nav navbar-right panel_toolbox">
                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                    </li> 
                  </ul>
                  <div class="clearfix"></div>
                </div>
                <div class="x_content">
                  <div class="dashboard-widget-content">
                  <div class="row">
                  
                    <c:forEach items="${societyList}" var="myItem" varStatus="loopStatus">
						<%-- <option value="${myItem.societyid}">${myItem.societyname}</option> --%>
						<div class="col-sm-4 col-md-4 col-xs-12">
						<article class="media event">
							<a href="displayAdminPanelBySocId.do?societyid=${myItem.societyid}" class="btn">${myItem.societyname}</a>
						</article>
						</div>
					</c:forEach>
                  </div>
                  </div>
                </div>
              </div>
 </div>