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
<link rel="icon" href="<%=request.getContextPath() %>/adminResources/images/favicon.ico">

<title>Notion User - Dashboard</title>

<!-- Bootstrap 4.0-->
<%-- <link rel="stylesheet" href="<%=request.getContextPath() %>/adminResources/css/bootstrap.css"> --%>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

<!-- Bootstrap extend-->
<link rel="stylesheet" href="<%=request.getContextPath() %>/adminResources/css/bootstrap-extend.css">

<!-- theme style -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/adminResources/css/master_style.css">

<!-- skins -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/adminResources/css/_all-skins.css">

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

				<!-- Main content -->
				<section class="content">
					<div Style="margin-bottom: 15px;padding: 4px 12px;background-color: #e7f3fe;border-left: 6px solid #2196F3">
  						<p><strong>Info:</strong> This is your Notion-2k19 Unique QRCode for attendance.</p>
					</div>
					<img alt="QR" src="<%=request.getContextPath() %>/qr/${userMail}.png">
					<div Style="margin: 15px 0 15px 0;padding: 4px 12px;background-color: #ffffcc;border-left: 6px solid #ffeb3b">
  						<p><strong>Note:</strong> This QR will be activated after you select and complete payment of at least 1 event.</p>
					</div>
					<div>
						<h5><a href="selectEvents"><input type="button" class="btn btn-primary" value="CLICK HERE"/></a> to select events</h5>
					</div>
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
	<%-- <script src="<%=request.getContextPath() %>/adminResources/js/jquery-3.3.1.js"></script> --%>
	
	<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>

	<!-- Bootstrap 4.0-->
	<%-- <script src="<%=request.getContextPath() %>/adminResources/js/bootstrap.js"></script> --%>
	
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

	<!-- UltimatePro Admin App -->
	<script src="<%=request.getContextPath() %>/adminResources/js/template.js"></script>


</body>
</html>