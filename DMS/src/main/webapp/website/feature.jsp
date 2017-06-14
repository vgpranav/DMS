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
	<div class="feature">
		<div class="breadcrumbs">
			<h4>What we do</h4>
		</div>
		
		<div class="container">
			<div class="row">		
				<div class="col-md-12 bg-white">
					<p>Our Digital Society works with an agenda of making a difference in India. Being part of the Digital India program initiated by the government we focus on encouraging the more and more people to participate in the mission.
					<br><br>
					Other than this, our company also works towards helping commercial and housing societies convert into a digitized community so that can take advantage of complete transparency and public accountability.
					<br><br>
					Our company has initially launched the program for locations like Mumbai, Thane, Navi Mumbai, Raigard, Pune and Nasik. But we soon plan to expand our target market to other cities of Maharashtra, India as well. </p>
				</div> 
			</div>
		</div>
		
		
		
		<div class="breadcrumbs">
			<h4>Our Standard Operating Process</h4>
		</div>
		
		<div class="container">
			<div class="row bg-white">
				<div class="col-md-5 bg-white" align="center">
				<br/>
					<img src="<%= request.getContextPath() %>/resources/images/sop.jpg" height="250">
				</div>
				<div class="col-md-7 ">
					<p>The entire process of setting up the program to convert documents into online files is kept simple so that all the members of a society can work the software. Here is a step by step narration of how the documents are digitized:</p>
					<ol>
						<li>1. The company representatives of Our Digital Society visit each orgnization to demonstrate how it is operated to all the members. </li>
						<li>2. With the help of the management or the society members our representatives then identify the important files that need to be converted into digital ones. </li>
						<li>3. The next part is creating society database with proper indexing, heading, tagging and labeling which is done by our representative on the spot. The identified files are then uploaded to our cloud based system so that they can be converted. </li>
						<li>4. The next step involves creating all the login credentials which will be assigned to privileged members. This part to be taken care of by our team so that you do not have to register yourself.</li>
						<li>5. The last part is updating the system whenever needed and for this purpose our support teams provides after sales services on a regular basis. You can contact the customer care department to set up a representative visit whenever required. </li>
						<li></li>
					</ol>
					<br>
				</div>
			</div>		
		</div>
		
		<div class="breadcrumbs">
			<h4>Why makes our packages unique</h4>
		</div>
		
		<div class="container">
			<div class="row">
				<div class="col-md-12 bg-white">
					<p>Out of all the reason that make our programs unique being a paperless documentation system is the most beneficial. Being actively involved in the Digital India mission we look forward to creating a complete paperless society one day. 
					<br><br>
					Along with this providing complete transparency to all the members is also a big step towards the digital initiative by the Indian Government. Our software has been designed so that it is easy to use and can be accessed from anywhere with the help of internet connection which is another reason as to why our package is considered unique.
					<br><br>
					Our online program is also a cost efficient alternative to the traditional documentation method as it reduces the need of additional manpower and cuts down the cost of materials like stationary items. All in all it is a great way to use high-end digital technology for every type of society and community. 
					</p>
				</div>
			</div>		
		</div>
	</div>

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
	<script>wow = new WOW({}).init();</script>
    
    <style>
    	.bg-white{
    		background-color: white;
    	}
    </style>
</body>
</html>