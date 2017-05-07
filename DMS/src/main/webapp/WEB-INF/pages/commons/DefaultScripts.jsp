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
<script src="<%= request.getContextPath() %>/resources/theme/vendors/bootstrap-daterangepicker/daterangepicker.js"></script>


<script src="<%= request.getContextPath() %>/resources/theme/vendors/popupimage/popImg.js"></script>


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
	
	
	function editMode(){
		$('.emdiv').remove();
		$('form').parent().prepend( "<div class='emdiv'><h2><span class='label label-warning label-xs '><i class='fa fa-circle-o-notch fa-spin fa-sm fa-fw'></i> Edit Mode</span></h2></div>")
		.css({"background-color":"#ffffe0"});
	}
	
	function blockUI(){
		$.blockUI({ message: '<h2><img width="30" src="<%=request.getContextPath()%>/resources/images/spin.gif"> Just a moment...</h2>' });
	}
	
	function unblockUI(){
		$.unblockUI();
	}
	
	$('.btn').click(function(){
		var that = $(this);
		$(that).attr('disabled','disabled');
		setTimeout(function(){ 
			$(that).removeAttr('disabled')
		}, 1000 );
	});
	
	
	function genericRemove(genId,typeVal,pkName,callback){
		
		if(confirm('Are you Sure?')){
			if(genId.length < 1){
 			alert('Some Mandatory Fields  are Missing');
 			return false;
 		} else { 
 			blockUI();
 			$.ajax({
 		        type: "GET",
 		        url: "<%=request.getContextPath()%>/genericRemove.do",
 		       data :"genId="+genId
 		      		+"&pkName="+pkName
 		      		+"&typeVal="+typeVal,
 		        success: function(response){
 		        //alert()
  		        	if(response=='success') {
 		        		notify('success','SUCCESS','Removed Successfully',2000);
 		        		if(callback=='reload')
 		        			location.reload();
 		        		else
 		        			callback();
 		        	}  else {
 		        		notify('error','Failed','Failed to Remove Member',2000);
 		        	}
 		        unblockUI();
 		        },
 					error : function(e) {
 						notify('error','ERROR','Error occured',2000);
 						unblockUI();
 					}
 				});
 		}
		}
 		return false;
	}
	
</script>
