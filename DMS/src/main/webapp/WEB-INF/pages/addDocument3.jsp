<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="row">
	<div class="col-md-12 col-sm-12 col-xs-12">

		<div class="dashboard_graph">

			<div class="row x_title">
				<div class="col-md-6">
					<h3>
						Add Document <small>Scan File</small>
					</h3>
				</div>
			</div>
			
			<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
			
			<!-- <OBJECT codetype="application/java"
			        classid="uk.co.mmscomputing.application.imageviewer.MainApp.class"
			        width="500" height="500">
			</OBJECT> -->
				<APPLET code="uk.co.mmscomputing.application.imageviewer.MainApp.class"
				type="applet" width="100%" style="min-height: 500px !important"
				ARCHIVE="mysql-connector-java-5.0.8-bin.jar,classes12.jar">
					<PARAM name="DepartmentId" value="0903000001"> 
					<PARAM name="UserId" value="1"> 
					<PARAM name="dbaseip" value="1"> 
					<PARAM name="dbuid" value="1"> 
					<PARAM name="dbpwd" value="1"> 
					<PARAM name="dbase" value="1">
					<PARAM name="java_arguments" value="-Xmx1000m -Dsun.java2d.noddraw=true"> 
				</APPLET>
 			</div>
 			</div>
			 			<!--  ARCHIVE="classes12.jar"  -->		
			<div class="clearfix"></div>
		</div>
	</div>
</div> 