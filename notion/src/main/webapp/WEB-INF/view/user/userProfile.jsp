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

<title>Notion - User Profile</title>

<!-- Bootstrap 4.0-->
<%-- <link rel="stylesheet" href="<%=request.getContextPath() %>/adminResources/css/bootstrap.css"> --%>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

<!-- Bootstrap extend-->
<link rel="stylesheet" href="<%=request.getContextPath() %>/adminResources/css/bootstrap-extend.css">

<!-- theme style -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/adminResources/css/master_style.css">

<!-- skins -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/adminResources/css/_all-skins.css">

<!--alerts CSS -->
<link href="<%=request.getContextPath() %>/adminResources/css/sweetalert.css" rel="stylesheet" type="text/css">

</head>
<body class="hold-transition skin-light light-sidebar sidebar-mini">
	<div class="wrapper">

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<div class="container">
				<!-- Content Header (Page header) -->
				<div class="content-header">
					<div class="d-flex align-items-center">
						<div class="mr-auto">
							<h3 class="page-title">Complete Profile</h3>
							<div class="d-inline-block align-items-center">
								<nav>
									<ol class="breadcrumb">
										<li class="breadcrumb-item"><a href="Dashboard"><i
												class="mdi mdi-home-outline"></i></a></li>
										<li class="breadcrumb-item" aria-current="page">User</li>
										<li class="breadcrumb-item active" aria-current="page">Profile</li>
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
							<h4 class="box-title">Enter Remaining Details</h4>
							<!-- <h6 class="box-subtitle">Bootstrap Form Validation check the <a class="text-warning" href="#">official website </a></h6> -->
						</div>
						<!-- /.box-header -->
						<div class="box-body">
							<div class="row">
								<div class="col">
									<form:form novalidate="novalidate" action="insertUserProfile"
										modelAttribute="userProfileData" id="profileForm" method="POST">
										<div class="row">
											<div class="col-12">
												<form:hidden path="regVO.registrationId"/>
												<div class="form-group">
													<h5>
														First Name <span class="text-danger">*</span>
													</h5>
													<div class="controls">
														<form:input type="text" name="firstname"
															path="regVO.firstname" class="form-control"
															placeholder="Enter First Name" required="required"
															data-validation-required-message="This field is required" disabled="true"/>
													</div>
												</div>
												<div class="form-group">
													<h5>
														Last Name <span class="text-danger">*</span>
													</h5>
													<div class="controls">
														<form:input type="text" name="lasttname"
															path="regVO.lastname" class="form-control"
															placeholder="Enter Last Name" required="required"
															data-validation-required-message="This field is required" disabled="true"/>
													</div>
												</div>
												<div class="form-group">
													<h5>
														Enrollment/Roll No. <span class="text-danger">*</span>
													</h5>
													<div class="controls">
														<form:input type="text" name="enrollment" class="form-control" placeholder="Enter Enrollment/Roll No."
															path="enrollment" required="required" data-validation-containsnumber-regex="(\d)+"
															data-validation-containsnumber-message="No Characters Allowed, Only Numbers" />
													</div>
												</div>
												<div class="form-group">
													<h5>
														Semester <span class="text-danger">*</span>
													</h5>
													<div class="controls">
														<form:select name="semester" path="semester"
															class="form-control" required="required" id="sem"
															data-validation-required-message="This field is required">
															<option>Select Semester</option>
															<c:forEach begin="1" end="8" var="i">
																<form:option value="${i}">${i}</form:option>
															</c:forEach>
														</form:select>
													</div>
												</div>
												<div class="form-group">
													<h5>
														Institute <span class="text-danger">*</span>
													</h5>
													<div class="controls">
														<form:select name="institute" path="instituteVO.instituteId"
															class="form-control" required="required" id="institute"
															data-validation-required-message="This field is required">
															<option>Select Institute</option>
															<c:forEach items="${instituteLs}" var="j">
																<form:option value="${j.instituteId}">${j.instituteName}</form:option>
															</c:forEach>
														</form:select>
													</div>
												</div>
												<div class="form-group">
													<h5>
														Contact No. <span class="text-danger">*</span>
													</h5>
													<div class="controls">
														<form:input type="text" name="contact" class="form-control" placeholder="Contact"
															path="contact" required="required" data-validation-containsnumber-regex="(\d)+"
															minlength="10" maxlength="10"
															data-validation-containsnumber-message="No Characters Allowed, Only Numbers" />
													</div>
												</div>
											</div>
										</div>
										<div class="text-xs-right">
											<input type="submit" value="Submit" id="subbtn" class="btn btn-info"/>
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

	<!-- popper -->
	<%-- <script src="<%=request.getContextPath() %>/adminResources/js/popper.min.js"></script> --%>

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
	
	<!-- Sweet-Alert  -->
    <script src="<%=request.getContextPath() %>/adminResources/js/sweetalert.min.js"></script>
	
	<script>
		$(document).ready(function(){
			$("#subbtn").val("Submit");
		})
		$("#subbtn").click(function(){
			var semvalue = $("#sem :selected").text();
			var instvalue = $("#institute :selected").text();
			if(semvalue == "Select Semester"){
				$("#sem :selected").text("")
				swal("Error","Please select the semester","error");
				$("#sem").focus();
			}
			else if(instvalue == "Select Institute"){
				$("#institute :selected").text("");
				swal("Error","Please select an institute","error");
				$("#institute").focus();
			}
			else{
				$("#subbtn").val("Generating QR...").delay(500);
				$("#subbtn").val("Sending Mail...");
				$("#profileForm").submit();
			}
		})
	</script>

</body>
</html>