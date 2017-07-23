<%@ page import="java.util.Calendar" %>
<%
	int year = Calendar.getInstance().get(Calendar.YEAR);
%>

<div class="col-sm-6 pull-left">
Our Digital Society &copy; <a href="#!" onclick="openConfDoc()"><%=year %></a> All Rights Reserved
</div>

<div class="col-sm-6 pull-right" align="right">
	<font color="#ccc"><small><em>Developed By Pranav V.G.</em></small></font>
</div>

<script>
	function openConfDoc(){
		var societyid = $('#societyidx').val();
		if(societyid!='' && societyid.length>0){
			window.open('ConfidentialDocListView.do?societyid='+societyid,'_self')
		}
	}
</script>