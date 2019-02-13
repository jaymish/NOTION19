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

<title>Notion - Reset Password</title>

<!-- Bootstrap 4.0-->
<link rel="stylesheet" href="<%=request.getContextPath() %>/adminResources/css/bootstrap.css">

<!-- Bootstrap extend-->
<link rel="stylesheet" href="<%=request.getContextPath() %>/adminResources/css/bootstrap-extend.css">

<!-- theme style -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/adminResources/css/master_style.css">

<!-- UltimatePro Admin skins -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/adminResources/css/_all-skins.css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->

<script>
	function validatedata() {
		var pass = document.getElementById("pass")
		var pass2 = document.getElementById("pass2")
		var subbtn = document.getElementById("subbtn")
		var matchpass = document.getElementById("passmatch")
		if (pass.value == pass2.value) {
			matchpass.style.display = "none"
			subbtn.disabled = false
		} else {
			matchpass.style.display = ""
			subbtn.disabled = true
		}
	}
</script>

</head>
<body class="hold-transition bg-img"
	style="background-image: url(<%=request.getContextPath() %>/adminResources/images/bg.jpg)"
	data-overlay="3">

	<div class="auth-2-outer row align-items-center h-p100 m-0">
		<div class="auth-2">
			<div class="auth-logo font-size-30">
				<a href="../index.html" class="text-dark"><b>NOTION</b>2k19</a>
			</div>
			<!-- /.login-logo -->
			<div class="auth-body">
				<p class="auth-msg">Reset Your Password</p>

				<form:form action="updatePassword" method="post" class="form-element"
					modelAttribute="passwordReset">
					<div class="form-group has-feedback controls">
						<form:input type="email" name="email" id="email"
							class="form-control" path="username" placeholder="Email" required="required"
							data-validation-required-message="This field is required" disabled="true"/>
					</div>
					<div class="form-group has-feedback controls">
						<form:input type="password" name="password" class="form-control"
							path="password" id="pass" placeholder="Password"
							required="required" data-validation-required-message="This field is required" />
						<span class="ion ion-locked form-control-feedback "></span><br />
					</div>
					<div class="form-group has-feedback controls">
						<input type="password" name="password2" class="form-control"
							id="pass2" onkeyup="validatedata()" placeholder="Retype password"
							required="required" data-validation-match-match="password" /> <span
							class="ion ion-md-checkmark form-control-feedback "></span> 
							<span style="display: none; color: red" id="passmatch">Password doesn't match!!</span>
					</div>
					<div class="row">
						<div class="col-12 text-center">
							<button type="submit" class="btn btn-block mt-10 btn-success"
								id="subbtn" disabled=true>SUBMIT</button>
						</div>
						<!-- /.col -->
					</div>
				</form:form>

				<div class="margin-top-30 text-center">
					<p>
						Remember the Password? <a href="login" class="text-info m-l-5">Sign In</a>
					</p>
				</div>

			</div>
		</div>

	</div>


	<!-- jQuery 3 -->
	<script src="<%=request.getContextPath() %>/adminResources/js/jquery-3.3.1.js"></script>

	<!-- popper -->
	<script src="<%=request.getContextPath() %>/adminResources/js/popper.min.js"></script>

	<!-- Bootstrap 4.0-->
	<script src="<%=request.getContextPath() %>/adminResources/js/bootstrap.js"></script>

	<!-- Form validator JavaScript -->
	<script src="<%=request.getContextPath() %>/adminResources/js/validation.js"></script>
	<script src="<%=request.getContextPath() %>/adminResources/js/form-validation.js"></script>

</body>
</html>