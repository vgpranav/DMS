<div class="sidebar-footer hidden-small">
              <!-- <a data-toggle="tooltip" data-placement="top" title="Settings">
                <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
              </a>
              <a data-toggle="tooltip" data-placement="top" title="FullScreen">
                <span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
              </a>
              <a data-toggle="tooltip" data-placement="top" title="Lock">
                <span class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
              </a>
              <a data-toggle="tooltip" data-placement="top" title="Logout" href="logout.do">
                <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
              </a> -->
<font color="#ccc">Session Expires in <span class="countdown"></span> mins</font> 
</div>

<script>

var timer2 = "10:01";
var interval = setInterval(function() {

	  var timer = timer2.split(':');
	  //by parsing integer, I avoid all extra string processing
	  var minutes = parseInt(timer[0], 10);
	  var seconds = parseInt(timer[1], 10);
	  --seconds;
	  minutes = (seconds < 0) ? --minutes : minutes;
	  if (minutes < 0) clearInterval(interval);
	  seconds = (seconds < 0) ? 59 : seconds;
	  seconds = (seconds < 10) ? '0' + seconds : seconds;
	  //minutes = (minutes < 10) ?  minutes : minutes;
	  $('.countdown').html(minutes + ':' + seconds);
	  timer2 = minutes + ':' + seconds;
	  if(timer2=="5:00")
		  notify('warning','Warning','Your Session will expire in 5 Mins',2000);
	  
	  //if(timer2=="0:05")
		  //logoutUser();
}, 1000);

 $('body *').click(function(){
	timer2 = "10:01"; 
}); 

function logoutUser(){
	window.location = "<%= request.getContextPath() %>/logout.do";
}
</script>