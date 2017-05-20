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
		<div class="col-lg-12">
			<div class="portfolios">
				
					<a href="img/portfolio/1.jpg" class="flipLightBox">
					<img src="img/portfolio/1.jpg" width="225" height="225" alt="Image 1" />
					<span><b>LightBox Group 1, Image 1</b> Text to accompany first lightbox image</span>
					</a>
			
					<a href="img/portfolio/2.jpg" class="flipLightBox">
					<img src="img/portfolio/2.jpg" width="225" height="225" alt="Image 2" />
					<span><b>LightBox Group 1, Image 2</b><br />Text to accompany 2nd lightbox image</span>
					</a>

					<a href="img/portfolio/3.jpg" class="flipLightBox">
					<img src="img/portfolio/3.jpg" width="225" height="225" alt="Image 3" />
					<span><b>LightBox Group 1, Three</b> Text to accompany the third lightbox image</span>
					</a>

					<a href="img/portfolio/4.jpg" class="flipLightBox">
					<img src="img/portfolio/4.jpg" width="225" height="225" alt="Image 4" />
					<span><b>The Group 1 Final LightBox</b> Text to accompany the last of the lighboxes</span>
					</a>
					
					<div class="portfolio">
						<a href="img/portfolio/5.jpg" class="flipLightBox">
						<img src="img/portfolio/5.jpg" width="225" height="225" alt="Image 1" />
						<span><b>LightBox Group 1, Image 1</b> Text to accompany first lightbox image</span>
						</a>
						
						<a href="img/portfolio/6.jpg" class="flipLightBox">
						<img src="img/portfolio/6.jpg" width="225" height="225" alt="Image 2" />
						<span><b>LightBox Group 1, Image 2</b><br />Text to accompany 2nd lightbox image</span>
						</a>

						<a href="img/portfolio/7.jpg" class="flipLightBox">
						<img src="img/portfolio/7.jpg" width="225" height="225" alt="Image 3" />
						<span><b>LightBox Group 1, Three</b> Text to accompany the third lightbox image</span>
						</a>

						<a href="img/portfolio/8.jpg" class="flipLightBox">
						<img src="img/portfolio/8.jpg" width="225" height="225" alt="Image 4" />
						<span><b>The Group 1 Final LightBox</b> Text to accompany the last of the lighboxes</span>
						</a>
					</div>	
				
			</div>
		</div>
	</div>

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
	<script src="js/responsive-slider.js"></script>
	<script src="js/wow.min.js"></script>
	<script type="text/javascript" src="js/fliplightbox.min.js"></script>
	<script type="text/javascript">$('body').flipLightBox()</script>
	<script>wow = new WOW({}).init();</script>
    
</body>
</html>