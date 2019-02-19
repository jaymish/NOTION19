<!-- Logo -->
<a href="Dashboard" class="logo"> <!-- mini logo -->
	<div class="logo-mini">
		<span class="dark-logo">
			<img src="<%=request.getContextPath() %>/adminResources/images/logo-dark_1.png" alt="">
		</span>
	</div> 
<!-- logo-->
	<div class="logo-lg">
		<span class="dark-logo">
			<img src="<%=request.getContextPath() %>/adminResources/images/logo-dark-text_1.png" alt="Notion Admin">
		</span>
	</div>
</a>
<!-- Header Navbar -->
<nav class="navbar navbar-static-top">
	<!-- Sidebar toggle button-->
	<div>
		<a href="#" class="sidebar-toggle" data-toggle="push-menu"
			role="button"> <i class="ti-align-left"></i>
		</a> <!-- <a id="toggle_res_search" data-toggle="collapse"
			data-target="#search_form" class="res-only-view"
			href="javascript:void(0);"><i class="mdi mdi-magnify"></i></a>
		<form id="search_form" role="search"
			class="top-nav-search pull-left collapse ml-20">
			<div class="input-group">
				<input type="text" name="example-input1-group2" class="form-control"
					placeholder="Search"> <span class="input-group-btn">
					<button type="button" class="btn  btn-default"
						data-target="#search_form" data-toggle="collapse"
						aria-label="Close" aria-expanded="true">
						<i class="mdi mdi-magnify"></i>
					</button>
				</span>
			</div>
		</form> -->
	</div>

	<div class="navbar-custom-menu r-side">
		<ul class="nav navbar-nav">
			<!-- User Account-->
			<li class="dropdown user user-menu"><a href="#"
				class="dropdown-toggle" data-toggle="dropdown"> <img
					src="<%=request.getContextPath() %>/adminResources/images/7.jpg" class="user-image rounded-circle"
					alt="User Image">
			</a>
				<ul class="dropdown-menu animated flipInX">
					<!-- User image -->
					<li class="user-header bg-img"
						style="background-image: url(<%=request.getContextPath() %>/adminResources/images/user-info.jpg)"
						data-overlay="3">
						<div class="flexbox align-self-center">
							<img src="<%=request.getContextPath() %>/adminResources/images/7.jpg"
								class="float-left rounded-circle" alt="User Image">
							<h4 class="user-name align-self-center">
								<span>${profileDetails.regVO.firstname} ${profileDetails.regVO.lastname}</span> <small>${userMail}</small>
							</h4>
						</div>
					</li>
					<!-- Menu Body -->
					<li class="user-body">
						<a class="dropdown-item" href="editProfile">
							<i class="ion ion-person"></i> Edit Profile
						</a>
						<div class="dropdown-divider"></div>
						<div class="p-10">
							<a href="logout" class="btn btn-sm btn-rounded btn-success">
								<i class="ion-log-out"></i> Logout
							</a>
						</div>
					</li>
				</ul>
			</li>
		</ul>
	</div>
</nav>