<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="<%=request.getContextPath() %>/adminResources/images/favicon.ico">

<title>Notion - Edit Event</title>

<!-- Bootstrap 4.0-->
<%-- <link rel="stylesheet" href="<%=request.getContextPath() %>/adminResources/css/bootstrap.css"> --%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

<!-- Bootstrap extend-->
<link rel="stylesheet" href="<%=request.getContextPath() %>/adminResources/css/bootstrap-extend.css">

<!-- theme style -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/adminResources/css/master_style.css">

<!-- UltimatePro Admin skins -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/adminResources/css/_all-skins.css">

<script>
	function viewMinMax()
	{
		var e=document.getElementById("partype")
		var type=e.options[e.selectedIndex].value
		var teammin=document.getElementById("teamMin")
		var teammax=document.getElementById("teamMax")
		if(type=="Team")
			{
				teammin.style.display=""
				teammax.style.display=""
			}
		else
			{
				teammin.style.display="none"
				teammax.style.display="none"
			}
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
							<h3 class="page-title">Event Details</h3>
							<div class="d-inline-block align-items-center">
								<nav>
									<ol class="breadcrumb">
										<li class="breadcrumb-item"><a href="Dashboard"><i
												class="mdi mdi-home-outline"></i></a></li>
										<li class="breadcrumb-item" aria-current="page">Edit Event</li>
										<li class="breadcrumb-item active" aria-current="page">Enter
											Details</li>
									</ol>
								</nav>
							</div>
						</div>
					</div>
				</div>

				<!-- Main content -->
				<section class="content">

					<!-- Basic Forms -->
					<div class="box">
						<div class="box-header with-border">
							<h4 class="box-title">Enter Event Details</h4>
							<!-- <h6 class="box-subtitle">Bootstrap Form Validation check the <a class="text-warning" href="#">official website </a></h6> -->
						</div>
						<!-- /.box-header -->
						<div class="box-body">
							<div class="row">
								<div class="col">
									<form:form novalidate="novalidate" action="updateEvent"
										modelAttribute="editEventData" method="POST">
										<form:hidden path="eventId"/>
										<form:hidden path="status"/>
										<div class="row">
											<div class="col-12">
												<div class="form-group">
													<h5>
														Name <span class="text-danger">*</span>
													</h5>
													<div class="controls">
														<form:input type="text" name="eventname" path="eventName"
															class="form-control" placeholder="Enter Name"
															required="required"
															data-validation-required-message="This field is required" />
													</div>
												</div>
												<div class="form-group">
													<h5>Description</h5>
													<div class="controls">
														<form:textarea name="eventdescription"
															path="eventDescription" class="form-control"
															placeholder="Write Description Here"></form:textarea>
													</div>
												</div>
												<div class="form-group">
													<h5>
														Type <span class="text-danger">*</span>
													</h5>
													<div class="controls">
														<form:select name="eventtype" path="eventType"
															class="form-control" placeholder="Type Of Event"
															required="required"
															data-validation-required-message="This field is required">
															<option>Select Type</option>
															<form:option value="Technical">Technical</form:option>
															<form:option value="Non-Technical">Non-Technical</form:option>
														</form:select>
													</div>
												</div>
												<div class="form-group">
													<h5>
														Branch <span class="text-danger">*</span>
													</h5>
													<div class="controls">
														<form:select name="eventbranch" path="eventBranch"
															class="form-control" placeholder="Type Of Event"
															required="required"
															data-validation-required-message="This field is required">
															<option>Select Branch</option>
															<form:option value="CE/IT/ICT">CE/IT/ICT</form:option>
															<form:option value="Mechanical/Auto">Mechanical/Auto</form:option>
															<form:option value="Civil">Civil</form:option>
															<form:option value="Chemical">Chemical</form:option>
															<form:option value="EC/IC">EC/IC</form:option>
															<form:option value="For All">For All</form:option>
														</form:select>
													</div>
												</div>
												<div class="form-group">
													<h5>
														Participation Type <span class="text-danger">*</span>
													</h5>
													<div class="controls">
														<form:select name="participationtype" id="partype"
															path="participationType" class="form-control"
															placeholder="Type Of Event" required="required"
															data-validation-required-message="This field is required" onchange="viewMinMax()">
															<option>Select Type</option>
															<form:option value="Individual">Individual</form:option>
															<form:option value="Team">Team</form:option>
														</form:select>
													</div>
												</div>
												<div class="form-group col-md-3" style="display:none" id="teamMin">
													<h5>
														Minimum Members <span class="text-danger">*</span>
													</h5>
													<div class="controls">
														<form:input type="number" name="teammin" path="teamMin"
															class="form-control" required="required"
															data-validation-required-message="This field is required" />
													</div>
												</div>
												<div class="form-group col-md-3" style="display:none" id="teamMax">
													<h5>
														Maximum Members <span class="text-danger">*</span>
													</h5>
													<div class="controls">
														<form:input type="number" name="teammax" path="teamMax"
															class="form-control" required="required"
															data-validation-required-message="This field is required" />
													</div>
												</div>
												<div class="form-group">
													<h5>
														Price <span class="text-danger">*</span>
													</h5>
													<div class="input-group">
														<span class="input-group-addon">Rs.</span> <form:input
															type="number" name="eventprice" path="eventPrice" class="form-control"
															required="required"
															data-validation-required-message="This field is required" />
														<span class="input-group-addon">.00</span>
													</div>
												</div>
											</div>
										</div>
										<div class="text-xs-right">
											<button type="submit" class="btn btn-info">Update</button>
											<button type="reset" class="btn btn-info">Reset</button>
										</div>
									</form:form>
								</div>
								<!-- /.col -->
							</div>
							<!-- /.row -->
						</div>
						<!-- /.box-body -->
					</div>
					<!-- /.box -->

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

	<!-- Slimscroll -->
	<script src="<%=request.getContextPath() %>/adminResources/js/jquery.slimscroll.js"></script>

	<!-- FastClick -->
	<script src="<%=request.getContextPath() %>/adminResources/js/fastclick.js"></script>

	<!-- UltimatePro Admin App -->
	<script src="<%=request.getContextPath() %>/adminResources/js/template.js"></script>

	<!-- UltimatePro Admin for demo purposes -->
	<script src="<%=request.getContextPath() %>/adminResources/js/demo.js"></script>

	<!-- Form validator JavaScript -->
	<script src="<%=request.getContextPath() %>/adminResources/js/validation.js"></script>
	<script src="<%=request.getContextPath() %>/adminResources/js/form-validation.js"></script>

</body>
</html>