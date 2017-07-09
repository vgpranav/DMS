<div class="container">
			<div class="row">
			
				<nav class="navbar navbar-default" role="navigation">
					<div class="container-fluid">
						<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
					        <span class="sr-only">Toggle navigation</span>
					        <span class="icon-bar"></span>
					        <span class="icon-bar"></span>
					        <span class="icon-bar"></span>
					      </button>
							<div class="navbar-brand">
								<a href="index.jsp">
									<img src="<%= request.getContextPath() %>/resources/images/ods-logo-ws.png" class="hidden-sm hidden-xs" height="50" id="odslogo">
									<img src="<%= request.getContextPath() %>/resources/images/ods-logo-ws.png" class="hidden-lg hidden-md" height="30" id="odslogo">
								</a>
							</div>
						</div>
						
						 <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					      <ul class="nav nav-tabs navbar-right" role="tablist">
					         <li role="presentation"><a href="index.jsp">Home</a></li>
								<li ><a href="businessarea.jsp">Business Area</a></li>
								<li ><a href="services.jsp">Services</a></li>
								<!-- <li ><a href="feature.jsp">What We Do</a></li> -->
								<li ><a href="contactus.jsp">Contact</a></li>
								<li ><a href="<%= request.getContextPath() %>/login.do">Login</a></li>
								
					      </ul>
					    </div><!-- /.navbar-collapse -->
					 
					</div>			
				</nav>
				
			</div>
		</div>
		 