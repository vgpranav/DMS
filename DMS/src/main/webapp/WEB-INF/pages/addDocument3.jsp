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
			 		<APPLET code="uk.co.mmscomputing.application.imageviewer.MainApp.class"
						type="applet" width="100%" style="min-height: 500px !important"
						ARCHIVE="mysql-connector-java-5.0.8-bin.jar,classes12.jar,commons-net-3.6.jar">
						<PARAM name="societyid" value="${societyid}"> 
						<PARAM name="doctypeid" value="${doctypeid}"> 
						<PARAM name="docsubtypeid" value="${docsubtypeid}"> 
						<PARAM name="documentId" value="${documentId}"> 
						<PARAM name="userid" value="1"> 
						<PARAM name="dbaseip" value="127.0.0.1:3306"> 
						<PARAM name="dbuid" value="root"> 
						<PARAM name="dbpwd" value="12345"> 
						<PARAM name="dbase" value="dms">
						<PARAM name="java_arguments" value="-Xmx1000m -Dsun.java2d.noddraw=true"> 
					</APPLET>
				
 				</div>
 			</div>
 			
			<div class="clearfix"></div>
			
		</div>
	</div>
</div> 