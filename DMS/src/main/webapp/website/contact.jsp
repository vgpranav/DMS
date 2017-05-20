<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Our Digital Society</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/responsive-slider.css" rel="stylesheet">
	<link rel="stylesheet" href="css/animate.css">
	<link rel="stylesheet" href="css/font-awesome.min.css">
	<link href="css/style.css" rel="stylesheet">	
   
  </head>
  <body>
    <header>
		<jsp:include page="/website/header.jsp"></jsp:include>
	</header>
	
	<div class="container">
		<div class="col-lg-6">
		    <div id="sendmessage">Your message has been sent. Thank you!</div>
            <div id="errormessage"></div>
            <form action="" method="post" role="form" class="contactForm">
                <div class="form-group">
                    <input type="text" name="name" class="form-control" id="name" placeholder="Your Name" data-rule="minlen:4" data-msg="Please enter at least 4 chars" />
                    <div class="validation"></div>
                </div>
                <div class="form-group">
                    <input type="email" class="form-control" name="email" id="email" placeholder="Your Email" data-rule="email" data-msg="Please enter a valid email" />
                    <div class="validation"></div>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="subject" id="subject" placeholder="Subject" data-rule="minlen:4" data-msg="Please enter at least 8 chars of subject" />
                    <div class="validation"></div>
                </div>
                <div class="form-group">
                    <textarea class="form-control" name="message" rows="5" data-rule="required" data-msg="Please write something for us" placeholder="Message"></textarea>
                    <div class="validation"></div>
                </div>
                
                <div class="text-center"><button type="submit" class="btn btn-default">Send Message</button></div>
            </form>
            
		</div>
		<div class="col-lg-6">
			<div class="map">
				<div id="google-map" data-latitude="40.713732" data-longitude="-74.0092704"></div>
			</div>
		</div>
	</div>	
	
		<address>
			<strong>
			Written by:<a href="https://bootstrapmade.com" target="_blank">Gampang Prasetyo</a><br>
			Visit us at:<br>
			<a href="https://bootstrapmade.com" target="_blank">http://bootstrapmade.com</a><br>
			Box 564, Disneyland<br>
			USA<br>
			</strong>
		</address> 
	
	<hr>
	
	<!--start footer-->
	<footer>
		<jsp:include page="/website/footer.jsp"></jsp:include>
	</footer>
	<!--end footer-->
	
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="js/jquery-2.1.1.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    <script src="https://maps.google.com/maps/api/js?sensor=true"></script>
	<script src="js/responsive-slider.js"></script>
	<script src="js/wow.min.js"></script>
	<script>wow = new WOW({}).init();</script>
    <script>
        jQuery(document).ready(function( $ ) {
            //Google Map
            var get_latitude = $('#google-map').data('latitude');
            var get_longitude = $('#google-map').data('longitude');

            function initialize_google_map() {
                var myLatlng = new google.maps.LatLng(get_latitude, get_longitude);
                var mapOptions = {
                    zoom: 14,
                    scrollwheel: false,
                    center: myLatlng
                };
                var map = new google.maps.Map(document.getElementById('google-map'), mapOptions);
                var marker = new google.maps.Marker({
                    position: myLatlng,
                    map: map
                });
            }
            google.maps.event.addDomListener(window, 'load', initialize_google_map);
        });
    </script>
    <script src="contactform/contactform.js"></script>
    
</body>
</html>