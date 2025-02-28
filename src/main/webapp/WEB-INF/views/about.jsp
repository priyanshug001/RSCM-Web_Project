<!DOCTYPE html>
<%@page import="com.RSCM.models.UpdateAboutPageContentModel"%>
<html lang="en">
<head>
<!-- basic -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- mobile metas -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="viewport" content="initial-scale=1, maximum-scale=1">
<!-- site metas -->
<title>About - RSCM</title>
<meta name="keywords" content="">
<meta name="description" content="">
<meta name="author" content="">
<!-- bootstrap css -->
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<!-- style css -->
<link rel="stylesheet" type="text/css" href="css/style.css">
<!-- Responsive-->
<link rel="stylesheet" href="css/responsive.css">
<!-- fevicon -->
<link rel="icon" href="images/favicon.jpg" type="image/gif" />
<!-- Scrollbar Custom CSS -->
<link rel="stylesheet" href="css/jquery.mCustomScrollbar.min.css">
<!-- Tweaks for older IEs-->
<link rel="stylesheet"
	href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
<!-- owl stylesheets -->
<link rel="stylesheet" href="css/owl.carousel.min.css">
<link rel="stylesheet" href="css/owl.theme.default.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css"
	media="screen">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Karla:500">

<style>
body {
	font-family: 'Karla';
	font-weight: 500;
}
</style>
</head>
<body>
	<%
	UpdateAboutPageContentModel updateAboutPageContentModel = (UpdateAboutPageContentModel) request
			.getAttribute("UpdatedAboutPageData");
	%>

	<div class="header_section">
		<div class="header_left">
			<jsp:include page="navigation-bar.jsp"></jsp:include>
		</div>
	</div>
	<!--about section start -->
	<div class="container">
		<div class="row">
			<div class="col-md-8">
				<h1 class="services_taital">ABOUT US</h1>
				<p class="services_text"><%=updateAboutPageContentModel.getAboutUs()%></p>

			</div>
			<div class="col-md-4">
				<div>
					<img src="images/img-1.png" class="image_1">
				</div>
			</div>
		</div>
	</div>
	<br>
	<br>
	<div class="container">
		<div class="row">
			<div class="col-md-8">
				<h1 class="services_taital">Our Mission</h1>
				<p class="services_text"><%=updateAboutPageContentModel.getOurMission()%></p>

			</div>
			<div class="col-md-4">
				<div>
					<img src="images/img-1.png" class="image_1">
				</div>
			</div>
		</div>
	</div>
	<br>
	<br>
	<div class="container">
		<div class="row">
			<div class="col-md-8">
				<h1 class="services_taital">Our Vision</h1>
				<p class="services_text"><%=updateAboutPageContentModel.getOurVision()%></p>

			</div>
			<div class="col-md-4">
				<div>
					<img src="images/img-1.png" class="image_1">
				</div>
			</div>
		</div>
	</div>
	<br>
	<br>
	<div class="container">
		<div class="row">
			<div class="col-md-8">
				<h1 class="services_taital">WHAT WE DO</h1>
				<p class="services_text"><%=updateAboutPageContentModel.getWhatWeDO()%></p>

			</div>
			<div class="col-md-4">
				<div>
					<img src="images/img-1.png" class="image_1">
				</div>
			</div>
		</div>
	</div>
	<br>
	<br>
	<div class="container">
		<div class="row">
			<div class="col-md-8">
				<h1 class="services_taital">WHO WE ARE</h1>
				<p class="services_text"><%=updateAboutPageContentModel.getWhoWeAre()%></p>

			</div>
			<div class="col-md-4">
				<div>
					<img src="images/img-1.png" class="image_1">
				</div>
			</div>
		</div>
	</div>
	<br>
	<br>
	<div class="container">
		<div class="row">
			<div class="col-md-8">
				<h1 class="services_taital">WHY CHOOSE US</h1>
				<%
				if (updateAboutPageContentModel.getFirstWhyChooseUs() != null) {
				%>
				<p class="services_text">
					&#10004;
					<%=updateAboutPageContentModel.getFirstWhyChooseUs()%></p>
				<%
				}
				if (updateAboutPageContentModel.getSecondWhyChooseUs() != null) {
				%>
				<p class="services_text">
					&#10004;
					<%=updateAboutPageContentModel.getSecondWhyChooseUs()%></p>
				<%
				}
				if (updateAboutPageContentModel.getThirdWhyChooseUs() != null) {
				%>
				<p class="services_text">
					&#10004;
					<%=updateAboutPageContentModel.getThirdWhyChooseUs()%></p>
				<%
				}
				if (updateAboutPageContentModel.getFourthWhyChooseUs() != null) {
				%>
				<p class="services_text">
					&#10004;
					<%=updateAboutPageContentModel.getFourthWhyChooseUs()%></p>
				<%
				}
				if (updateAboutPageContentModel.getFifthWhyChooseUs() != null) {
				%>
				<p class="services_text">
					&#10004;
					<%=updateAboutPageContentModel.getFifthWhyChooseUs()%></p>
				<%
				}
				%>
			</div>
			<div class="col-md-4">
				<div>
					<img src="images/img-1.png" class="image_1">
				</div>
			</div>
		</div>
	</div><br><br><br><br>
	<!--about section end -->
	<jsp:include page="footer.jsp"></jsp:include>
	<!-- Javascript files-->
	<script src="js/jquery.min.js"></script>
	<script src="js/popper.min.js"></script>
	<script src="js/bootstrap.bundle.min.js"></script>
	<script src="js/jquery-3.0.0.min.js"></script>
	<script src="js/plugin.js"></script>
	<!-- sidebar -->
	<script src="js/jquery.mCustomScrollbar.concat.min.js"></script>
	<script src="js/custom.js"></script>
	<!-- javascript -->
	<script src="js/owl.carousel.js"></script>
	<script
		src="https:cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.js"></script>
</body>
</html>