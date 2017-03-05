<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="row">
	<div class="col-md-12 col-sm-12 col-xs-12">

		<div class="dashboard_graph">

			<div class="row x_title">
				<div class="col-md-6">
					<h3>
						Create Committee<small></small>
					</h3>
				</div>
				<div class="pull-right">
					<select name="societyid" id="societyid" class="form-control" onchange="getMembersForSociety()">
						<option value=""> -- select society -- </option>
						<c:forEach items="${societyList}" var="myItem" varStatus="loopStatus">
							<option value="${myItem.societyid}">${myItem.societyname}</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br /> 
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br /> 
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br /> aaaaaaaaaaaaa

			<div class="clearfix"></div>
		</div>
	</div>
</div>