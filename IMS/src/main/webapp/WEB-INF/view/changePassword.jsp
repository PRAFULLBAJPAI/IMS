<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>TMS</title>
<!-------------------------- CSS Links------------------------------>
<link rel="stylesheet" href="./externalCSS/fontawesome.css">
<link rel="stylesheet" href="./externalCSS/bootstrap.min.css">
<link rel="stylesheet" href="./externalCSS/googleFonts.css">
<link rel="stylesheet" href="./css/loginStyle.css" />
<script src="./externalJS/jquery-3.7.0.js"></script>
<script src="./externalJS/sweetalert2.all.min.js"></script>

</head>
<body>
	<nav class="navbar navbar-expand-md navbar-light bg-light fixed-top"
		style="height: 45px;">
		<div class="container">
			<h5 class="navbar-brand" href="#">TMS</h5>

			<div id="nav-bar">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item"><label class="nav-link" href="#"
						style="color: #610C9F;"> Digitals India Security Private
							Limited</label></li>
				</ul>
			</div>
		</div>
	</nav>


	<div class="login-container">
		<div class="login-form">
			<div class="login-form-inner">

				<h3>Change Password</h3>
				<!--<b style="margin-right:4%;" id="emailForUpdatePassword">${email}</b>-->
				<input type="email" id="emailForUpdatePassword"
					placeholder="Enter email" value="${email}" style="display: none;">

				<div class="login-form-group">

					<div style="height: 10px;"></div>
					<label for="newPassword">New Password<span
						class="required-star">*</span></label> <input type="password"
						placeholder="Enter new password" id="newPassword">
					<div class="login-form-group">
						<label for="pwd">Confirm Password <span
							class="required-star">*</span></label> <input autocomplete="off"
							type="password" placeholder="Re-enter Password" id="password">
					</div>
					<button type="button" class="rounded-button login-cta"
						id="changePasswordButton" onclick="changePassword()">Submit</button>

				</div>
			</div>
		</div>

		<div class="onboarding">
			<div class="swiper-container">
				<div class="swiper-wrapper">
					<div class="swiper-slide color-1">
						<img alt="password" src="./img/password.jpg">
					</div>
				</div>
				<!-- Add Pagination -->
				<div class="swiper-pagination"></div>
			</div>
		</div>
	</div>


	<nav class="navbar fixed-bottom navbar-light background">
		<div class="container-fluid d-flex justify-content-center">
			<p class="navbar-brand text-center custom-font-size"
				style="color: #ffff;">Doritech Consultancy & Software Solutions</p>
		</div>
	</nav>
	<script>
    	
    	</script>


	<script src="./externalJS/bootstrap.min.js"></script>
	<script src="./js/changePassword.js"></script>

</body>
</html>