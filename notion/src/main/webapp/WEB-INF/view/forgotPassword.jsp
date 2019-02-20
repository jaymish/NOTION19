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

    <title>Notion - Recover Password</title>
  
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

<body class="hold-transition bg-img" style="background-image: url(<%=request.getContextPath() %>/adminResources/images/bg.jpg);" data-overlay="1">
	
	<div class="container h-p100">
		<div class="row align-items-center justify-content-md-center h-p100">
			
			<div class="col-12">
				<div class="row no-gutters">
					<div class="col-lg-4 col-md-5 col-12">
						<div class="content-top-agile p-10">
							<h3 class="text-white mb-0">Reset Password</h3>								
						</div>
						<div class="p-30 content-bottom rounded box-shadowed" data-overlay="10">
							<form action="#" method="post">
								<div class="form-group">
									<div class="input-group mb-3">
										<div class="input-group-prepend">
											<span class="input-group-text  bg-transparent bt-0 bl-0 br-0"><i class="ti-email"></i></span>
										</div>
										<input type="email" id="email" class="form-control pl-15 bg-transparent bt-0 bl-0 br-0" placeholder="Your Email">
									</div>
								</div>
								  <div class="row">
									<div class="col-12 text-center">
									  <input type="button" value="Submit" class="btn btn-info btn-block margin-top-10" id="resetbtn" disabled="true" />
									</div>
									<!-- /.col -->
								  </div>
							</form>	
						</div>
					</div>
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
	
	 <!-- Sweet-Alert  -->
     <script src="<%=request.getContextPath() %>/adminResources/js/sweetalert.min.js"></script>
    
    <script>
    $("#email").keyup(function(){
    	var invalue = $("#email").val();
		
		var regex = new RegExp(
					/^(("[\w-\s]+")|([\w-]+(?:\.[\w-]+)*)|("[\w-\s]+")([\w-]+(?:\.[\w-]+)*))(@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)|(@\[?((25[0-5]\.|2[0-4][0-9]\.|1[0-9]{2}\.|[0-9]{1,2}\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\]?$)/i);

			if ((invalue == null) || (invalue == '') || (invalue == ' ')
					|| (invalue == '	')) {
					
				$("#resetbtn").attr("disabled",true);
					
					} else {
				if (regex.test(email.value)) {
					$("#resetbtn").attr("disabled",false);	
				} else {
					$("#resetbtn").attr("disabled",true);
				}
			}
    })
    $('#resetbtn').click(function(){
    	$('#resetbtn').val("Checking User...");
    	$.ajax({
			url : "${pageContext.request.contextPath}/checkUser",
			method : "GET",
			data : {
				username : $("#email").val()
			},
			success : function(msg){
				if(msg=="exist"){
					$('#resetbtn').val("Sending Mail...");
					$.ajax(
							{
								url : "${pageContext.request.contextPath}/resetPasswordLink",
								method : "GET",
								data : {
									email : $("#email").val()
								},
								success : function(msg){
									if(msg=="sent")
									{
										swal("Mail Sent!", "A link to reset password has been sent to this email id", "success");
										$('#resetbtn').val("Submit");
										$('#resetbtn').attr("disabled",true);
									}
								}
							});
				}
				else{
					swal("Error", "This User is not Registered!!", "error");
					$('#resetbtn').val("Submit");
				}
			}
		});
    });
    </script>
</body>
</html>