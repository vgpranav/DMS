<!-- Bootstrap -->
<script src="<%= request.getContextPath() %>/resources/theme/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- bootstrap-progressbar -->
<script src="<%= request.getContextPath() %>/resources/theme/vendors/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
<!-- iCheck -->
<script src="<%= request.getContextPath() %>/resources/theme/vendors/iCheck/icheck.min.js"></script>
<!-- DateJS -->
<script src="<%= request.getContextPath() %>/resources/theme/vendors/DateJS/build/date.js"></script>
<!-- bootstrap-daterangepicker -->
<script src="<%= request.getContextPath() %>/resources/theme/vendors/moment/min/moment.min.js"></script>
<script src="<%= request.getContextPath() %>/resources/theme/vendors/bootstrap-daterangepicker/daterangepicker.js"></script>
<!-- Custom Theme Scripts -->
<script src="<%= request.getContextPath() %>/resources/theme/build/js/custom.js"></script>
<script src="<%= request.getContextPath() %>/resources/theme/vendors/pnotify/dist/pnotify.js"></script>
<script src="<%= request.getContextPath() %>/resources/theme/vendors/pnotify/dist/pnotify.buttons.js"></script>
<script src="<%= request.getContextPath() %>/resources/theme/vendors/pnotify/dist/pnotify.nonblock.js"></script>

<script src="<%= request.getContextPath() %>/resources/theme/vendors/jquery-ui-1.12.1/jquery-ui.js"></script>
<script src="<%= request.getContextPath() %>/resources/theme/vendors/datatables.net-bs/js/dataTables.bootstrap.js"></script>
<script src="<%= request.getContextPath() %>/resources/theme/vendors/datatables.net/js/jquery.dataTables.js"></script>

<script>
	function notify(type,title,message,delay){
		new PNotify({
       	 title: title,
            text: message,
            type: type,
            styling: 'bootstrap3',
            delay : delay
       });
	}
</script>
