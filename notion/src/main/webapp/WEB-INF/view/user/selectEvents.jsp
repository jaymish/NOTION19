<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<!-- <link rel="icon" href="../adminResources/images/favicon.ico"> -->

<title>Notion User - Select Events</title>

<!-- Bootstrap 4.0-->
<link rel="stylesheet" href="../adminResources/css/bootstrap.css">

<!-- Bootstrap extend-->
<link rel="stylesheet" href="../adminResources/css/bootstrap-extend.css">

<!-- theme style -->
<link rel="stylesheet" href="../adminResources/css/master_style.css">

<!-- UltimatePro Admin skins -->
<link rel="stylesheet" href="../adminResources/css/_all-skins.css">

<!-- Data Table-->
<link rel="stylesheet" type="text/css" href="../adminResources/datatables.min.css" />

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
				<!-- Content Header (Page header) -->
				<div class="content-header">
					<div class="d-flex align-items-center">
						<div class="mr-auto">
							<h3 class="page-title">Events</h3>
							<div class="d-inline-block align-items-center">
								<nav>
									<ol class="breadcrumb">
										<li class="breadcrumb-item"><a href="index.jsp"><i
												class="mdi mdi-home-outline"></i></a></li>
										<li class="breadcrumb-item" aria-current="page">Events</li>
										<li class="breadcrumb-item active" aria-current="page">Select
											Events</li>
									</ol>
								</nav>
							</div>
						</div>
					</div>
				</div>

				<!-- Main content -->
				<section class="content">
					<form novalidate="novalidate">
						<div class="row">
							<div class="col-12">
								<div class="box">
									<div class="box-header">
										<h4 class="box-title">List of Individual Events</h4>
									</div>
									<div class="box-body">
										<div class="table-responsive">
											<table id="complex_header"
												class="table table-bordered table-hover display nowrap margin-top-10 w-p100"
												style="width: 100%">
												<thead>
													<tr>
														<th>Action</th>
														<th colspan="4">Information</th>
													</tr>
													<tr>
														<th>Select</th>
														<th>Name</th>
														<th>Description</th>
														<th>Branch</th>
														<th>Price</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${individualEvents}" var="i">
														<tr>
															<td><a href="selectEvent?selectEventId=${i.eventId}"><input type="button" class="btn btn-primary" value="Select"/></a></td>
															<td>${i.eventName}</td>
															<td>${i.eventDescription}</td>
															<td>${i.eventBranch}</td>
															<td>${i.eventPrice}</td>
														</tr>
													</c:forEach>
												</tbody>
												<tfoot>
													<tr>
														<th>Select</th>
														<th>Name</th>
														<th>Description</th>
														<th>Branch</th>
														<th>Price</th>
													</tr>
												</tfoot>
											</table>
										</div>
									</div>
								</div>
							</div>

							<div class="col-12">
								<div class="box">
									<div class="box-header">
										<h4 class="box-title">List of Team Events</h4>
									</div>
									<div class="box-body">
										<div class="table-responsive">
											<table id="example1"
												class="table table-bordered table-hover display nowrap margin-top-10 w-p100"
												style="width: 100%">
												<thead>
													<tr>
														<th colspan="5">Information</th>
													</tr>
													<tr>
														<th>Select</th>
														<th>Name</th>
														<th>Description</th>
														<th>Branch</th>
														<th>Price</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${teamEvents}" var="i">
														<tr>
															<td><input type="checkbox" /></td>
															<td>${i.eventName}</td>
															<td>${i.eventDescription}</td>
															<td>${i.eventBranch}</td>
															<td>${i.eventPrice}</td>
														</tr>
													</c:forEach>
												</tbody>
												<tfoot>
													<tr>
														<th>Select</th>
														<th>Name</th>
														<th>Description</th>
														<th>Branch</th>
														<th>Price</th>
													</tr>
												</tfoot>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- /.row -->
					</form>
				</section>
				<!-- /.content -->

			</div>
		</div>
		<!-- /.content-wrapper -->

		<footer class="main-footer">
			<jsp:include page="footer.jsp" />
		</footer>
		<!-- Control Sidebar -->
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

	<!-- FastClick -->
	<script src="../adminResources/js/fastclick.js"></script>

	<!-- UltimatePro Admin App -->
	<script src="../adminResources/js/template.js"></script>

	<!-- UltimatePro Admin for demo purposes -->
	<script src="../adminResources/js/demo.js"></script>

	<!-- This is data table -->
	<script src="../adminResources/js/datatables.min.js"></script>

	<!-- UltimatePro Admin for Data Table -->
	<script src="../adminResources/js/data-table.js"></script>

	<!-- Form validator JavaScript -->
	<script src="../adminResources/js/validation.js"></script>
	<script src="../adminResources/js/form-validation.js"></script>

</body>
</html>