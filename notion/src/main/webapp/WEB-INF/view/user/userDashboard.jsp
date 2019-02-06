<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../adminResources/images/favicon.ico">

<title>Notion User - Dashboard</title>

<!-- Bootstrap 4.0-->
<link rel="stylesheet" href="../adminResources/css/bootstrap.css">

<!-- Bootstrap extend-->
<link rel="stylesheet" href="../adminResources/css/bootstrap-extend.css">

<!-- theme style -->
<link rel="stylesheet" href="../adminResources/css/master_style.css">

<!-- UltimatePro Admin skins -->
<link rel="stylesheet" href="../adminResources/css/_all-skins.css">

<!-- c3 CSS -->
<link rel="stylesheet" type="text/css" href="../adminResources/css/c3.min.css">

<!-- daterange picker -->
<link rel="stylesheet" href="../adminResources/css/daterangepicker.css">


<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->


</head>

<body class="hold-transition skin-light light-sidebar sidebar-mini">
	<div class="wrapper">

		<header class="main-header">
			<jsp:include page="header.jsp" />
		</header>

		<!-- Left side column. contains the logo and sidebar -->
		<aside class="main-sidebar">
			<!-- sidebar-->
			<section class="sidebar">
				<jsp:include page="userMenu.jsp" />
			</section>
		</aside>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<div class="container">
				<h3>Welcome ${regDetails.getFirstname()} ${regDetails.getLastname()}</h3>
				<!-- Content Header (Page header) -->
				<!-- <div class="content-header">
					<div class="d-flex align-items-center">
						<div class="mr-auto w-p50">
							<h3 class="page-title"></h3>
						</div>
						<div class="right-title w-170">
							<span class="subheader_daterange font-weight-600"
								id="dashboard_daterangepicker"> <span
								class="subheader_daterange-label"> <span
									class="subheader_daterange-title"></span> <span
									class="subheader_daterange-date text-primary"></span>
							</span> <a href="#" class="btn btn-sm btn-primary"> <i
									class="fa fa-angle-down"></i>
							</a>
							</span>
						</div>
					</div>
				</div> -->

				<!-- Main content -->
				<section class="content">
					<div Style="margin-bottom: 15px;padding: 4px 12px;background-color: #e7f3fe;border-left: 6px solid #2196F3">
  						<p><strong>Info:</strong> This is your Notion-2k19 Unique QRCode</p>
					</div>
					<img alt="QR" src="../qr/${userMail}.png">
				</section>
				<!-- /.content -->
			</div>
		</div>
		<!-- /.content-wrapper -->
		<footer class="main-footer">
			<jsp:include page="footer.jsp" />
		</footer>

	</div>
	<!-- ./wrapper -->



	<!-- jQuery 3 -->
	<script src="../adminResources/js/jquery-3.3.1.js"></script>

	<!-- popper -->
	<script src="../adminResources/js/popper.min.js"></script>

	<!-- Bootstrap 4.0-->
	<script src="../adminResources/js/bootstrap.js"></script>

	<!-- Slimscroll -->
	<script src="../adminResources/js/jquery.slimscroll.js"></script>

	<!-- date-range-picker -->
	<script src="../adminResources/js/moment.min.js"></script>
	<script src="../adminResources/js/daterangepicker.js"></script>

	<!-- Sparkline -->
	<script src="../adminResources/js/jquery.sparkline.min.js"></script>

	<!-- C3 Plugins -->
	<script src="../adminResources/js/d3.min.js"></script>
	<script src="../adminResources/js/c3.min.js"></script>

	<!-- eChart Plugins -->
	<script src="../adminResources/js/echarts-en.min.js"></script>

	<!-- FastClick -->
	<script src="../adminResources/js/fastclick.js"></script>

	<!-- UltimatePro Admin App -->
	<script src="../adminResources/js/template.js"></script>

	<!-- UltimatePro Admin dashboard demo (This is only for demo purposes) -->
	<script src="../adminResources/js/dashboard3.js"></script>
	<script src="../adminResources/js/chart-dash3-int.js"></script>

	<!-- UltimatePro Admin for demo purposes -->
	<script src="../adminResources/js/demo.js"></script>


</body>
</html>