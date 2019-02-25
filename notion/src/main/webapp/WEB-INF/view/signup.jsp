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

<title>Notion - Registration</title>

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

<script>
	function validatedata() {
		var pass = document.getElementById("pass")
		var pass2 = document.getElementById("pass2")
		var chkbox = document.getElementById("basic_checkbox_1")
		var subbtn = document.getElementById("subbtn")
		var matchpass = document.getElementById("passmatch")
		if (pass.value == pass2.value && chkbox.checked && pass.value.trim()!="") {
			matchpass.style.display = "none"
			subbtn.disabled = false
		} else if (pass.value == pass2.value && !chkbox.checked && pass.value.trim()!="") {
			matchpass.style.display = "none"
			subbtn.disabled = true
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
				<p class="auth-msg">Register Yourself for Notion</p>

				<form:form action="insertRegData" method="post" class="form-element"
					modelAttribute="regData" id="regform">
					<div class="form-group has-feedback controls">
						<form:input type="text" name="text" class="form-control"
							path="firstname" placeholder="First Name" id="firstname" required="required"
							data-validation-required-message="This field is required" />
						<span class="ion ion-person form-control-feedback "></span>
					</div>
					<div class="form-group has-feedback controls">
						<form:input type="text" name="text" class="form-control"
							path="lastname" placeholder="Last Name" required="required"
							data-validation-required-message="This field is required" />
						<span class="ion ion-person form-control-feedback "></span>
					</div>
					<div class="form-group has-feedback controls">
						<form:input type="email" name="email" id="email" class="form-control"
							path="loginVO.username" placeholder="Email" required="required"
							data-validation-required-message="This field is required" />
						<span class="ion ion-email form-control-feedback "></span> 
						<span id="errormsg"></span>
					</div>
					<div class="form-group has-feedback controls">
						<form:input type="password" name="password" class="form-control"
							path="loginVO.password" id="pass" placeholder="Password"
							required="required" pattern=".{5,12}" title="Password Must be 5-12 characters long!"
							data-validation-required-message="This field is required" />
						<span class="ion ion-locked form-control-feedback "></span><br />
					</div>
					<div class="form-group has-feedback controls">
						<input type="password" name="password2" class="form-control"
							id="pass2" onkeyup="validatedata()" placeholder="Retype password"
							required="required" data-validation-match-match="password" /> <span
							class="ion ion-md-checkmark form-control-feedback "></span> <span
							style="display: none; color: red" id="passmatch">Password
							doesn't match!!</span>
					</div>
					<div class="row">
						<div class="col-12">
							<div class="checkbox">
								<input type="checkbox" id="basic_checkbox_1"
									onclick="validatedata()"> <label for="basic_checkbox_1">I
									agree to the <a href="#" class="text-danger"><b>Terms</b></a>
								</label>
							</div>
						</div>
						<!-- /.col -->
						<div class="col-12 text-center">
							<input type="submit" value="Submit" class="btn btn-block mt-10 btn-success"
								id="subbtn" disabled="true"/>
						</div>
						<!-- /.col -->
					</div>
					<input type="hidden" value="${mailmsg}" id="msg"/>
				</form:form>

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
						Already have an account? <a href="login" class="text-info m-l-5">Sign
							In</a>
					</p>
				</div>

			</div>
			<div><font color="red">* Check your email after submitting for account verification mail. If you don't receive email within 10 minutes contact us at notion@ljinstitutes.edu.in</font></div>
		</div>
	</div>


	<!-- jQuery 3 -->
	<%-- <script src="<%=request.getContextPath() %>/adminResources/js/jquery-3.3.1.js"></script> --%>
	
	<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>

	<!-- Bootstrap 4.0-->
	<%-- <script src="<%=request.getContextPath() %>/adminResources/js/bootstrap.js"></script> --%>
	
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	
	<!-- Sweet-Alert  -->
    <script src="<%=request.getContextPath() %>/adminResources/js/sweetalert.min.js"></script>
	
	<script>
		$("#email").blur(function(){
			$("#errormsg").html("");
			var regex = new RegExp(
			/^(("[\w-\s]+")|([\w-]+(?:\.[\w-]+)*)|("[\w-\s]+")([\w-]+(?:\.[\w-]+)*))(@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)|(@\[?((25[0-5]\.|2[0-4][0-9]\.|1[0-9]{2}\.|[0-9]{1,2}\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\]?$)/i);
			if (regex.test(email.value)) {
				$.ajax({
					url : "${pageContext.request.contextPath}/checkUser",
					method : "GET",
					data : {
						username : $("#email").val()
					},
					success : function(msg){
						if(msg=="exist"){
							swal("Sorry", "This Username already Exist!!", "error");
							$("#email").val("");
							$("#email").focus();
						}
						/* else{
							swal("Notice","An email for verification will be sent to this email id, You won't be able to sign in until verification is complete","warning");
						} */
					}
				});
			}
			else {
				$("#errormsg").html("<font color=red>Enter Another Email ID</font>");
				$("#email").focus();
			}
		});
		
		$(document).ready(function(){
			$("#subbtn").val("Submit");
			var msg=$("#msg").val();
			if(msg=="sent"){
				swal("Link Sent", "An account verification link has been sent to your email id. You need to verify your email id before logging in", "Success");
			}
		})
		
		$("#subbtn").click(function(){
			$("#subbtn").val("Sending Mail...");
			$.ajax({
				url : "${pageContext.request.contextPath}/userVerification",
				method : "GET",
				data : {
					username : $("#email").val(),
					firstname : $("#firstname").val()
				},
				success : function(msg){
					if(msg=="sent"){
						$("#subbtn").submit();
					}
					else if(msg=="notsent"){
						swal("Error", "Could not send email. Please check your email id or contact us for support.", "error");
						$("#subbtn").val("Submit");
					}
				}
			});
			
		})
	</script>

</body>
</html>