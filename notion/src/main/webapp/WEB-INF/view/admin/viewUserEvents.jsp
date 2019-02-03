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

<title>Notion Admin - View Selected Events</title>

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

<script>
	function removeSel(id)
	{
		id=parseInt(id)
		var htp = new XMLHttpRequest();
		htp.onreadystatechange = function() {
			location.reload(true);
		}
		htp.open("GET", "removeSelectedEvent?selectedEventId=" + id, true)
		htp.send();
	}
	function removeReg(id)
	{
		id=parseInt(id)
		var htp = new XMLHttpRequest();
		htp.onreadystatechange = function() {
			location.reload(true);
		}
		htp.open("GET", "removeSelectedEvent?selectedEventId=" + id, true)
		htp.send();
		alert("Complete Refund First!!");
	}
</script>	
	
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
				<jsp:include page="adminMenu.jsp" />
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
										<li class="breadcrumb-item active" aria-current="page">Selected
											Events</li>
									</ol>
								</nav>
							</div>
						</div>
					</div>
				</div>

				<!-- Main content -->
				<section class="content">
						<div class="row">
							<div class="col-12">
								<div class="box">
									<div class="box-header">
										<h4 class="box-title">List of Selected Events</h4>
									</div>
									<div class="box-body">
										<div class="table-responsive">
											<table id="example1"
												class="table table-bordered table-hover display nowrap margin-top-10 w-p100"
												style="width: 100%">
												<thead>
													<tr>
														<th colspan="4">Information</th>
														<th>Action</th>
													</tr>
													<tr>
														<th>Name</th>
														<th>Type</th>
														<th>Price</th>
														<th>Payment</th>
														<th>Remove</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${selectedEventsLs}" var="i">
														<tr>
															<td>${i.eventVO1.eventName}</td>
															<td>${i.eventVO1.eventType}</td>
															<td>${i.eventVO1.eventPrice}</td>
															<td style="color:red">${i.paymentStatus}</td>
															<td><input type="button" class="btn btn-danger" value="X Remove" onclick="removeSel(${i.userEventId})"/></td>
														</tr>
													</c:forEach>
												</tbody>
												<tfoot>
													<tr>
														<td colspan="2">Total Pending</td>
														<td colspan="3" style="font-weight:bolder">${totalPending}</td>
													</tr>
												</tfoot>
											</table>
										</div>
									</div>
								</div>
							</div>
							<div style="margin:0 0 30px 15px">
								<a href="collectPayment"><input type="button" class="btn btn-primary" value="Collect"/></a>
							</div>
							<div class="col-12">
								<div class="box">
									<div class="box-header">
										<h4 class="box-title">List of Registered Events</h4>
									</div>
									<div class="box-body">
										<div class="table-responsive">
											<table id="complex_header"
												class="table table-bordered table-hover display nowrap margin-top-10 w-p100"
												style="width: 100%">
												<thead>
													<tr>
														<th colspan="4">Information</th>
														<th>Action</th>
													</tr>
													<tr>
														<th>Name</th>
														<th>Type</th>
														<th>Price</th>
														<th>Payment</th>
														<th>Remove</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${registeredEventsLs}" var="i">
														<tr>
															<td>${i.eventVO1.eventName}</td>
															<td>${i.eventVO1.eventType}</td>
															<td>${i.eventVO1.eventPrice}</td>
															<td style="color:green">${i.paymentStatus}</td>
															<td><input type="button" class="btn btn-danger" value="X Remove" onclick="removeReg(${i.userEventId})"/></td>
														</tr>
													</c:forEach>
												</tbody>
												<tfoot>
													<tr>
														<td colspan="2">Total Paid</td>
														<td colspan="3" style="font-weight:bolder">${totalPaid}</td>
													</tr>
												</tfoot>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- /.row -->
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