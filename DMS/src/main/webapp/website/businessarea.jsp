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
			<h4>Business Area</h4>
		</div>
		
		<div class="container">
			<div class="row">		
				<div class="col-md-12 bg-white">
					<p>With our document management system, Our Digital Society has created a unique tool that will allow users to manage their documentation needs easily and quickly online.
					<br><br>
					The tool can be beneficial for many sectors including everything from commercial to non-commercial organisations. We understand that each business area has a separate documentation requirement and that is why we have created a software that can handle it all. To give you an idea of all the different communities that can use these online tools here is a list:  
					</p>
				</div> 
			</div>
		</div>
		
		
		<div class="breadcrumbs">
			<h4>Housing Society</h4>
		</div>
		
		<div class="container">
			<div class="row bg-white">

				<div class="col-md-8 ">
					<p>
						One of the major users who will find our tool helpful are residential communities. With the increasing size of housing societies, it has become more and more difficult for the management of such organisations to keep track of the ever-increasing documents. But with our tool, managing documents becomes as easy as clicking a button.
					</p>
				</div>
				<div class="col-md-4" align="right">
					<img src="<%= request.getContextPath() %>/resources/images/buildings.jpg" style="max-height:200px; "/>
				</div>
			</div>		
		</div>
		
		<div class="breadcrumbs">
			<h4>Commercial Society</h4>
		</div>
		
		<div class="container">
			<div class="row">
				<div class="col-md-12 bg-white">
					<p>
						Commercial societies of large size who are looking for a simpler alternative for document management can also take advantage of our cloud-based software to the fullest. Communities like industrial estates, shopping malls, and business parks where business activities thrive are also the common targeted areas for our programs. Here are certain documentation needs that you can manage for each of these subcategories with our software.     
					</p>
				</div>
			</div>		
		</div>
		
		<div class="breadcrumbs">
			<h4>Industrial Estate</h4>
		</div>
		
		
		<div class="container">
			<div class="row  bg-white">
				<div class="col-md-8">
					<p>
						If you own an industrial estate and find it hard to keep track of your documentation, then our amazing tool can help you keep all your documentation up to date. Along with this, it will also save you a lot of time and energy that you spend hiring professionals for the same job.     
					</p>
				</div>
				<div class="col-md-4" align="right">
					<img src="<%= request.getContextPath() %>/resources/images/indestate.jpg" style="max-height:200px; "/>
				</div>
			</div>		
		</div>
		
		<div class="breadcrumbs">
			<h4>Business Parks</h4>
		</div>
		
		<div class="container">
			<div class="row bg-white">
				<div class="col-md-4" align="left">
					<img src="<%= request.getContextPath() %>/resources/images/business-park.jpg" style="max-height:200px; "/>
				</div>
				<div class="col-md-8 ">
					<p>
						Our software can also be the most suitable tool for recording registries and commercial contracts that you might have with the other companies in the business park. These documents can be updated as and when they are renewed so that you do not have to compromise with your records.     
					</p>
				</div>
			</div>		
		</div>
		
		<div class="breadcrumbs">
			<h4>Shopping Malls</h4>
		</div>
		
		<div class="container">
			<div class="row bg-white">
				<div class="col-md-8 ">
					<p>
						Shopping malls are another targeted area for our business. Most malls maintain records of rent agreements, lease agreements, invoicing and payments which can be made easy with the help of our online tools.      
					</p>
				</div>
				<div class="col-md-4" align="right">
					<img src="<%= request.getContextPath() %>/resources/images/shopping-mall.jpg" style="max-height:200px; "/>
				</div>
				
				
			</div>		
		</div>
		
		<div class="breadcrumbs">
			<h4>Builders and Developers</h4>
		</div>
		
		<div class="container">
			<div class="row">
				<div class="col-md-12 bg-white">
					<p>
						Whether it is storing your blueprints or client registry of your real estate property, with this software we can help you simplify the entire process. You can be sure that your data will be stored securely out of other people's reach and you will also be able to access it with an internet connection whenever and wherever needed.       
					</p>
				</div>
			</div>		
		</div>
		
		<div class="breadcrumbs">
			<h4>Architect Firms</h4>
		</div>
		
		<div class="container">
			<div class="row bg-white">
				<div class="col-md-8">
					<p>
						If you own an architect firm that creates hundreds of amazing designs every month and are looking to store them in a more systematic way than Our Digital Society is a perfect way to do that.        
					</p>
				</div>
				<div class="col-md-4" align="right">
					<img src="<%= request.getContextPath() %>/resources/images/architect.jpg" style="max-height:200px; "/>
				</div>
			</div>		
		</div>
		<br/><br/><br/><br/>
		
		
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