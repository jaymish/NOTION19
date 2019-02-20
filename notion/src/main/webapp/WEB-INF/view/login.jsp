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

<title>Notion - Log in</title>

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
<body class="hold-transition bg-img"
	style="background-image: url(<%=request.getContextPath() %>/adminResources/images/bg.jpg)"
	data-overlay="3">

	<div class="auth-2-outer row align-items-center h-p100 m-0">
		<div class="auth-2">
			<div class="auth-logo font-size-30">
				<a href="#" class="text-dark"><b>NOTION</b>2k19</a>
			</div>
			<!-- /.login-logo -->
			<div class="auth-body">
				<p class="auth-msg">Sign In to Register for Events</p>

				<form action="j_spring_security_check" method="post" class="form-element">
					<div class="form-group has-feedback">
						<input type="email" name="username" class="form-control" placeholder="Email" required="required">
						<span class="ion ion-email form-control-feedback"></span>
					</div>
					<div class="form-group has-feedback">
						<input type="password" name="password" id="pass" class="form-control" placeholder="Password" required="required">
						<span class="ion ion-locked form-control-feedback"></span>
					</div>
					<div class="row">
						<!-- <div class="col-6">
							<div class="checkbox">
								<input type="checkbox" id="basic_checkbox_1"> <label
									for="basic_checkbox_1">Remember Me</label>
							</div>
						</div> -->
						<!-- /.col -->
						<div class="col-6">
							<div class="fog-pwd">
								<a href="forgotPassword" class="text-dark"><i
									class="ion ion-locked"></i> Forgot password?</a><br>
							</div>
						</div>
						<!-- /.col -->
						<div class="col-12 text-center">
							<button type="submit" class="btn btn-block mt-10 btn-success">SIGN
								IN</button>
						</div>
						<!-- /.col -->
					</div>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					<input type="hidden" value="${errormsg}" id="err"/>
				</form>

				<!-- <div class="text-center text-dark">
			  <p class="mt-50">- Sign With -</p>
			  <p class="gap-items-2 mb-20">
				  <a class="btn btn-social-icon btn-outline btn-facebook" href="#"><i class="fa fa-facebook"></i></a>
				  <a class="btn btn-social-icon btn-outline btn-twitter" href="#"><i class="fa fa-twitter"></i></a>
				  <a class="btn btn-social-icon btn-outline btn-google" href="#"><i class="fa fa-google-plus"></i></a>
				  <a class="btn btn-social-icon btn-outline btn-instagram" href="#"><i class="fa fa-instagram"></i></a>
				</p>	
			</div>
 -->
				<!-- /.social-auth-links -->

				<div class="margin-top-30 text-center">
					<p>
						Don't have an account? <a href="signup"
							class="text-info m-l-5">Sign Up</a>
					</p>
				</div>

			</div>
		</div>

	</div>


	<!-- jQuery 3 -->
	<%-- <script src="<%=request.getContextPath() %>/adminResources/js/jquery-3.3.1.js"></script> --%>
	
	<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>

	<!-- Bootstrap 4.0-->
	<%-- <script src="<%=request.getContextPath() %>/adminResources/js/bootstrap.js"></script> --%>
	
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	
	<!-- Crypto Js -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.9-1/crypto-js.min.js"></script>
	
	<!-- Sweet-Alert  -->
    <script src="<%=request.getContextPath() %>/adminResources/js/sweetalert.min.js"></script>
    
	<script>
		$("#pass").blur(function(){
			var pass=$("#pass").val();
			var hash=CryptoJS.MD5(pass);
			$("#pass").val(hash);
		})
		$(document).keypress(function(e){
			if(e.which == 13){
				var pass=$("#pass").val();
				var hash=CryptoJS.MD5(pass);
				$("#pass").val(hash);
			}
		})
		$(document).ready(function(){
			var err=$("#err").val();
			if(err=="true"){
				swal("Error","Either incorrect Username/Password or you are not verified. Please check your email for account verification","error");
			}
		})
	</script>

</body>
</html>