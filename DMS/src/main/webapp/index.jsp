<%
	response.setStatus(response.SC_MOVED_TEMPORARILY);
	response.setHeader("Location",request.getContextPath()+"/website"); 
%>
