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

<title>Notion Admin - Dashboard</title>

<!-- Bootstrap 4.0-->
<%-- <link rel="stylesheet" href="<%=request.getContextPath() %>/adminResources/css/bootstrap.css"> --%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

<!-- Bootstrap extend-->
<link rel="stylesheet" href="<%=request.getContextPath() %>/adminResources/css/bootstrap-extend.css">

<!-- theme style -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/adminResources/css/master_style.css">

<!-- UltimatePro Admin skins -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/adminResources/css/_all-skins.css">

<!-- c3 CSS -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/adminResources/css/c3.min.css">

<!-- daterange picker -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/adminResources/css/daterangepicker.css">

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
						<div class="mr-auto w-p50">
							<h3 class="page-title">Analytics</h3>
							<div class="d-inline-block align-items-center">
								<nav>
									<ol class="breadcrumb">
										<li class="breadcrumb-item"><a href="#"><i
												class="mdi mdi-home-outline"></i></a></li>
									</ol>
								</nav>
							</div>
						</div>
						<div class="right-title w-170">
							<span class="subheader_daterange font-weight-600"
								id="dashboard_daterangepicker"> <span
								class="subheader_daterange-label"> <span
									class="subheader_daterange-title"></span> <span
									class="subheader_daterange-date text-primary"></span>
							</span> <a href="#" class="btn btn-sm btn-primary"> <i
									class="fa fa-angle-down"></i>
							</a>
							</span>
						</div>
					</div>
				</div>

				<!-- Main content -->
				<section class="content">

					<div class="row">
						<div class="col-md-6 col-lg-3">
							<div class="box">
								<div class="box-body">
									<div class="flexbox">
										<div id="barchart4"></div>
										<div>
											<h3 class="countnm mb-5">589</h3>
											<p class="mb-0">
												<i class="ion-ios-arrow-up text-success mr-10"></i>New Users
											</p>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-6 col-lg-3">
							<div class="box">
								<div class="box-body">
									<div class="flexbox">
										<div id="linearea3"></div>
										<div>
											<h3 class="countnm mb-5">%90</h3>
											<p class="mb-0">
												<i class="ion-ios-arrow-up text-success mr-10"></i>Yearly
												Usage
											</p>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-6 col-lg-3">
							<div class="box">
								<div class="box-body">
									<div class="flexbox">
										<div id="linechart4"></div>
										<div>
											<h3 class="countnm mb-5">%90</h3>
											<p class="mb-0">
												<i class="ion-ios-arrow-up text-success mr-10"></i>Impressions
											</p>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- <div class="col-md-6 col-lg-3">
				  <div class="box">	
					  <div class="box-body">
						  <div class="flexbox">
							  <div id="discretechart4"></div>
							  <div>
								  <h3 class="countnm mb-5">$4,581</h3>
								  <p class="mb-0">Today's sales</p>	  
							  </div>				
						  </div>
					  </div>
				  </div>
			  </div> -->

					</div>

					<div class="row">
						<div class="col-xl-4 col-12">

							<!-- Sparklines in colored card -->
							<div class="box bg-info">
								<div class="box-body">
									<div class="d-flex">
										<h3 class="font-weight-600 mb-0">85.4%</h3>
										<div class="list-icons ml-auto">
											<div class="list-icons-item dropdown">
												<a href="#"
													class="list-icons-item dropdown-toggle text-white"
													data-toggle="dropdown"><i class="fa fa-cog"></i></a>
												<div class="dropdown-menu dropdown-menu-right">
													<a href="#" class="dropdown-item">Update data</a> <a
														href="#" class="dropdown-item">Detailed log</a> <a
														href="#" class="dropdown-item">Statistics</a> <a href="#"
														class="dropdown-item">Clear list</a>
												</div>
											</div>
										</div>
									</div>

									<div>
										Current server loading
										<div class="font-size-16">85.6% avg</div>
									</div>
								</div>

								<div id="sparklines_color"></div>
							</div>
							<!-- /sparklines in colored card -->

							<div class="box bg-danger">
								<div class="box-body">
									<div class="d-flex">
										<div class="mr-20 align-self-center">
											<h1 class="text-white">
												<i class="ti-pie-chart"></i>
											</h1>
										</div>
										<div>
											<h4 class="box-title">Bandwidth usage</h4>
											<h6 class="text-white">July 2018</h6>
										</div>

									</div>

									<div class="row mt-20 align-items-center">
										<div class="col-4">
											<h3 class="text-white">75 GB</h3>
										</div>
										<div class="col-8 text-right">
											<div class="bandwidth"></div>
										</div>
									</div>
								</div>
							</div>


						</div>


						<div class="col-xl-8 col-12">
							<div class="box">
								<div class="box-header with-border">
									<h4 class="box-title">Overview</h4>
								</div>
								<div class="box-body">
									<div id="column-chart"></div>
								</div>
							</div>
						</div>

					</div>

					<div class="row">
						<div class="col-xl-6 col-12">
							<div class="box">
								<div class="box-body analytics-info">
									<h4 class="box-title">User Statistics</h4>
									<div id="rainfall" style="height: 400px;"></div>
								</div>
							</div>
						</div>
						<div class="col-xl-6 col-12">
							<!-- Chart -->
							<div class="box">
								<div class="box-body">
									<h4 class="box-title">Campaigns</h4>
									<div id="nested-pie" style="height: 400px;"></div>
								</div>
								<!-- /.box-body -->
							</div>
							<!-- /.box -->
						</div>

						<div class="col-12">
							<div class="box">
								<div class="box-header with-border">
									<h4 class="box-title">Weekly Status</h4>
								</div>

								<div class="box-body">

									<div class="row">
										<div class="col-12 col-lg-9">
											<div id="stacked-column" style="height: 400px;"></div>
										</div>
										<div class="col-12 col-lg-3">
											<h3 class="text-center mb-15">Goal Completion</h3>

											<div class="progress-group mb-40">
												<span class="progress-text">Add Products to Bag</span> <span
													class="progress-number"><b>140</b>/200</span>

												<div class="progress h-30">
													<div
														class="progress-bar progress-bar-info progress-bar-striped progress-bar-animated"
														style="width: 70%;"></div>
												</div>
											</div>
											<!-- /.progress-group -->
											<div class="progress-group mb-40">
												<span class="progress-text">Complete Purchase</span> <span
													class="progress-number"><b>300</b>/400</span>

												<div class="progress h-30">
													<div
														class="progress-bar progress-bar-danger progress-bar-striped progress-bar-animated"
														style="width: 75%"></div>
												</div>
											</div>
											<!-- /.progress-group -->
											<div class="progress-group mb-40">
												<span class="progress-text">Visit Page</span> <span
													class="progress-number"><b>400</b>/800</span>

												<div class="progress h-30">
													<div
														class="progress-bar progress-bar-success progress-bar-striped progress-bar-animated"
														style="width: 50%"></div>
												</div>
											</div>
											<!-- /.progress-group -->
											<div class="progress-group mb-40">
												<span class="progress-text">Send Inquiries</span> <span
													class="progress-number"><b>425</b>/500</span>

												<div class="progress h-30">
													<div
														class="progress-bar progress-bar-warning progress-bar-striped progress-bar-animated"
														style="width: 85%"></div>
												</div>
											</div>
											<!-- /.progress-group -->
										</div>
									</div>

									<div class="row mt-30">
										<div class="col-6 col-md-3">
											<div class="description-block border-right">
												<span class="text-success"><i class="fa fa-caret-up"></i>
													<span class="countnm per">17</span></span>
												<h5 class="description-header">$3,249.43</h5>
												<span class="description-text">TOTAL REVENUE</span>
											</div>
											<!-- /.description-block -->
										</div>
										<!-- /.col -->
										<div class="col-6 col-md-3">
											<div class="description-block border-right">
												<span class="text-warning"><i
													class="fa fa-caret-left"></i> <span class="countnm per">70</span></span>
												<h5 class="description-header">$2,376.90</h5>
												<span class="description-text">TOTAL COST</span>
											</div>
											<!-- /.description-block -->
										</div>
										<!-- /.col -->
										<div class="col-6 col-md-3">
											<div class="description-block border-right">
												<span class="text-primary"><i class="fa fa-caret-up"></i>
													<span class="countnm per">80</span></span>
												<h5 class="description-header">$1,795.53</h5>
												<span class="description-text">TOTAL PROFIT</span>
											</div>
											<!-- /.description-block -->
										</div>
										<!-- /.col -->
										<div class="col-6 col-md-3">
											<div class="description-block">
												<span class="text-danger"><i class="fa fa-caret-down"></i>
													<span class="countnm per">28</span></span>
												<h5 class="description-header">1800</h5>
												<span class="description-text">GOAL COMPLETIONS</span>
											</div>
											<!-- /.description-block -->
										</div>
									</div>
								</div>
							</div>
						</div>

					</div>

					<div class="row">
						<div class="col-xl-4 col-12">
							<!-- small box -->
							<div class="small-box box-inverse bg-img"
								style="background-image: url(<%=request.getContextPath() %>/adminResources/images/6.jpg);"
								data-overlay="5">
								<div class="inner">
									<h3>255</h3>

									<p>New Orders</p>
								</div>
								<div class="icon text-white">
									<i class="fa fa-shopping-bag"></i>
								</div>
								<a href="#" class="small-box-footer">More info <i
									class="fa fa-arrow-right"></i></a>
							</div>
						</div>
						<!-- ./col -->
						<div class="col-xl-4 col-12">
							<!-- small box -->
							<div class="small-box box-inverse bg-img"
								style="background-image: url(<%=request.getContextPath() %>/adminResources/images/9.jpg);"
								data-overlay="5">
								<div class="inner">
									<h3>
										67<sup style="font-size: 20px">%</sup>
									</h3>

									<p>Sales Rate</p>
								</div>
								<div class="icon text-white">
									<i class="fa fa-bar-chart"></i>
								</div>
								<a href="#" class="small-box-footer">More info <i
									class="fa fa-arrow-right"></i></a>
							</div>
						</div>
						<!-- ./col -->
						<div class="col-xl-4 col-12">
							<!-- small box -->
							<div class="small-box box-inverse bg-img"
								style="background-image: url(<%=request.getContextPath() %>/adminResources/images/8.jpg);"
								data-overlay="5">
								<div class="inner">
									<h3>78</h3>

									<p>Registrations</p>
								</div>
								<div class="icon text-white">
									<i class="fa fa-user-plus"></i>
								</div>
								<a href="#" class="small-box-footer">More info <i
									class="fa fa-arrow-right"></i></a>
							</div>
						</div>
						<!-- ./col -->

						<div class="col-lg-12">
							<div class="box">
								<div class="box-body analytics-info">
									<h4 class="box-title">Last Month Income</h4>
									<div id="simple-xy"></div>
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

	<!-- date-range-picker -->
	<script src="<%=request.getContextPath() %>/adminResources/js/moment.min.js"></script>
	<script src="<%=request.getContextPath() %>/adminResources/js/daterangepicker.js"></script>

	<!-- Sparkline -->
	<script src="<%=request.getContextPath() %>/adminResources/js/jquery.sparkline.min.js"></script>

	<!-- C3 Plugins -->
	<script src="<%=request.getContextPath() %>/adminResources/js/d3.min.js"></script>
	<script src="<%=request.getContextPath() %>/adminResources/js/c3.min.js"></script>

	<!-- eChart Plugins -->
	<script src="<%=request.getContextPath() %>/adminResources/js/echarts-en.min.js"></script>

	<!-- FastClick -->
	<script src="<%=request.getContextPath() %>/adminResources/js/fastclick.js"></script>

	<!-- UltimatePro Admin App -->
	<script src="<%=request.getContextPath() %>/adminResources/js/template.js"></script>

	<!-- UltimatePro Admin dashboard demo (This is only for demo purposes) -->
	<script src="<%=request.getContextPath() %>/adminResources/js/dashboard3.js"></script>
	<script src="<%=request.getContextPath() %>/adminResources/js/chart-dash3-int.js"></script>

	<!-- UltimatePro Admin for demo purposes -->
	<script src="<%=request.getContextPath() %>/adminResources/js/demo.js"></script>


</body>
</html>