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
<link rel="icon" href="<%=request.getContextPath() %>/adminResources/images/favicon.ico">

<title>Notion Admin - Event Registrations</title>

<!-- Bootstrap 4.0-->
<%-- <link rel="stylesheet" href="<%=request.getContextPath() %>/adminResources/css/bootstrap.css"> --%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

<!-- Bootstrap extend-->
<link rel="stylesheet" href="<%=request.getContextPath() %>/adminResources/css/bootstrap-extend.css">

<!-- theme style -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/adminResources/css/master_style.css">

<!-- UltimatePro Admin skins -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/adminResources/css/_all-skins.css">

<!-- Data Table-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/adminResources/datatables.min.css" />

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
										<li class="breadcrumb-item" aria-current="page">Event</li>
										<li class="breadcrumb-item active" aria-current="page">Registrations</li>
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
										<h4 class="box-title">Payment Completed</h4>
									</div>
									<div class="box-body">
										<div class="table-responsive">
											<table id="example"
												class="table table-bordered table-hover display nowrap margin-top-10 w-p100"
												style="width: 100%">
												<thead>
													<tr>
														<th colspan="6">Information</th>
													</tr>
													<tr>
														<th>Event</th>
														<th>Name</th>
														<th>Enrollment</th>
														<th>Semester</th>
														<th>Institute</th>
														<th>Contact</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${paymentComplete}" var="i">
														<tr>
															<td>${i.eventVO1.eventName}</td>
															<td>${i.userProfileVO.regVO.firstname} ${i.userProfileVO.regVO.lastname}</td>
															<th>${i.userProfileVO.enrollment}</th>
															<td>${i.userProfileVO.semester}</td>
															<td>${i.userProfileVO.instituteVO.instituteName}</td>
															<td>${i.userProfileVO.contact}</td>
														</tr>
													</c:forEach>
												</tbody>
												<tfoot>
													<tr>
														<th>Event</th>
														<th>Name</th>
														<th>Enrollment</th>
														<th>Semester</th>
														<th>Institute</th>
														<th>Contact</th>
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
										<h4 class="box-title">Teams</h4>
									</div>
									<div class="box-body">
										<div class="table-responsive">
											<table id="example1"
												class="table table-bordered table-hover display nowrap margin-top-10 w-p100"
												style="width: 100%">
												<thead>
													<tr>
														<th colspan="7">Information</th>
													</tr>
													<tr>
														<th>Event</th>
														<th>Name</th>
														<th>Member 1</th>
														<th>Member 2</th>
														<th>Member 3</th>
														<th>Member 4</th>
														<th>Member 5</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${teamMembersLs}" var="i">
														<tr>
															<td>${i.eventVO1.eventName}</td>
															<td>${i.userProfileVO.regVO.firstname} ${i.userProfileVO.regVO.lastname}</td>
															<th>${i.teamMember1}</th>
															<td>${i.teamMember2}</td>
															<td>${i.teamMember3}</td>
															<td>${i.teamMember4}</td>
															<td>${i.teamMember5}</td>
														</tr>
													</c:forEach>
												</tbody>
												<tfoot>
													<tr>
														<th>Event</th>
														<th>Name</th>
														<th>Member 1</th>
														<th>Member 2</th>
														<th>Member 3</th>
														<th>Member 4</th>
														<th>Member 5</th>
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
										<h4 class="box-title">Payment Pending</h4>
									</div>
									<div class="box-body">
										<div class="table-responsive">
											<table id="example6"
												class="table table-bordered table-hover display nowrap margin-top-10 w-p100"
												style="width: 100%">
												<thead>
													<tr>
														<th colspan="6">Information</th>
													</tr>
													<tr>
														<th>Event</th>
														<th>Name</th>
														<th>Enrollment</th>
														<th>Semester</th>
														<th>Institute</th>
														<th>Contact</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${paymentPending}" var="i">
														<tr>
															<td>${i.eventVO1.eventName}</td>
															<td>${i.userProfileVO.regVO.firstname} ${i.userProfileVO.regVO.lastname}</td>
															<th>${i.userProfileVO.enrollment}</th>
															<td>${i.userProfileVO.semester}</td>
															<td>${i.userProfileVO.instituteVO.instituteName}</td>
															<td>${i.userProfileVO.contact}</td>
														</tr>
													</c:forEach>
												</tbody>
												<tfoot>
													<tr>
														<th>Event</th>
														<th>Name</th>
														<th>Enrollment</th>
														<th>Semester</th>
														<th>Institute</th>
														<th>Contact</th>
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
	<%-- <script src="<%=request.getContextPath() %>/adminResources/js/jquery-3.3.1.js"></script> --%>
	<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>

	<!-- Bootstrap 4.0-->
	<%-- <script src="<%=request.getContextPath() %>/adminResources/js/bootstrap.js"></script> --%>	
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

	<!-- Slimscroll -->
	<script src="<%=request.getContextPath() %>/adminResources/js/jquery.slimscroll.js"></script>

	<!-- FastClick -->
	<script src="<%=request.getContextPath() %>/adminResources/js/fastclick.js"></script>

	<!-- UltimatePro Admin App -->
	<script src="<%=request.getContextPath() %>/adminResources/js/template.js"></script>

	<!-- UltimatePro Admin for demo purposes -->
	<script src="<%=request.getContextPath() %>/adminResources/js/demo.js"></script>

	<!-- This is data table -->
	<script src="<%=request.getContextPath() %>/adminResources/js/datatables.min.js"></script>

	<!-- UltimatePro Admin for Data Table -->
	<script src="<%=request.getContextPath() %>/adminResources/js/data-table.js"></script>

	<!-- Form validator JavaScript -->
	<script src="<%=request.getContextPath() %>/adminResources/js/validation.js"></script>
	<script src="<%=request.getContextPath() %>/adminResources/js/form-validation.js"></script>

</body>
</html>