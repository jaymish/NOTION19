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
	<link rel="stylesheet" href="<%=request.getContextPath() %>/adminResources/css/bootstrap.css">
	
	<!-- Bootstrap extend-->
	<link rel="stylesheet" href="<%=request.getContextPath() %>/adminResources/css/bootstrap-extend.css">

	<!-- Theme style -->
	<link rel="stylesheet" href="<%=request.getContextPath() %>/adminResources/css/master_style.css">

	<!-- UltimatePro Admin skins -->
	<link rel="stylesheet" href="<%=request.getContextPath() %>/adminResources/css/_all-skins.css">
	
	<!--alerts CSS -->
    <link href="<%=request.getContextPath() %>/adminResources/css/sweetalert.css" rel="stylesheet" type="text/css">	

	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>

<body class="hold-transition bg-img" style="background-image: url(<%=request.getContextPath() %>/adminResources/images/bg.jpg);" data-overlay="1">
	
	<div class="container h-p100">
		<div class="row align-items-center justify-content-md-center h-p100">
			
			<div class="col-12">
				<div class="row no-gutters">
					<div class="col-lg-4 col-md-5 col-12">
						<div class="content-top-agile p-10">
							<h3 class="text-white mb-0">Recover Password</h3>								
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
									  <button type="button" class="btn btn-info btn-block margin-top-10" id="resetbtn" disabled="true">Reset</button>
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
	<script src="<%=request.getContextPath() %>/adminResources/js/jquery-3.3.1.js"></script>

	<!-- popper -->
	<script src="<%=request.getContextPath() %>/adminResources/js/popper.min.js"></script>

	<!-- Bootstrap 4.0-->
	<script src="<%=request.getContextPath() %>/adminResources/js/bootstrap.js"></script>
	
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
    	$.ajax(
				{
					url : "resetPasswordLink",
					method : "GET",
					data : {
						email : $("#email").val()
					}
				})
        swal("Mail Sent!", "A link to reset password has been sent to this email id", "success")
    });
    </script>
</body>
</html>