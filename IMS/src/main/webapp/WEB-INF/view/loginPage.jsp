<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>IMS</title>
<!-------------------------- CSS Links------------------------------>
<link rel="stylesheet" href="./externalCSS/fontawesome.css">
<link rel="stylesheet" href="./externalCSS/bootstrap.min.css">
<link rel="stylesheet" href="./externalCSS/googleFonts.css">
<link rel="stylesheet" href="./css/loginStyle.css" />
<script src="./externalJS/sweetalert2.all.min.js"></script>

</head>
<body>
	<nav class="navbar navbar-expand-md navbar-light bg-light fixed-top"
		style="height: 45px;">
		<div class="container">
			<img alt="logo" src="./img/logoIMS.png" class="navbar-brand logoImage">

			<div id="nav-bar">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item"><label class="nav-link" href="#"
						style="color: #1649A2; font-weight: bold">${paramClient.payload.descp1}</label>
					</li>
				</ul>
			</div>
		</div>
	</nav>


	<div class="login-container">
		<div class="login-form">
			<div class="login-form-inner">
				<h3>Login to IMS</h3>
				<div class="login-form-group">
					<div class="form-group">
						<div class="col-sm-16" style="font-size: 13px;">
							<b>User Type:&nbsp; &nbsp;</b> <select id="userTypeDropdown"
								onchange="userChange(this.value);"
								style="padding: 5px; border: 1px solid #65; border-radius: 4px;">
								<option value="admin" selected="selected">Admin</option>
								<option value="superAdmin">Super Admin</option>
								<option value="employee">Employee</option>
							</select>
						</div>

					</div>

					<label for="adminEmail" style="display: block;" id="labelAdmin">Admin<span
						class="required-star">*</span></label> <input type="text"
						placeholder="Enter email" id="adminEmail"> <label
						for="superAdminEmail" style="display: none;" id="labelSuperAdmin">Super
						Admin<span class="required-star">*</span>
					</label> <input type="text" placeholder="Enter email" id="superAdminEmail"
						style="display: none;"> <label for="employeeEmail"
						style="display: none;" id="labelEmployee">Employee<span
						class="required-star">*</span></label> <input type="text"
						placeholder="Enter email" style="display: none;"
						id="employeeEmail">


					<div class="login-form-group">
						<label for="pwd">Password <span class="required-star">*</span></label>
						<input autocomplete="off" type="password"
							placeholder="Enter Password" id="pwd">
					</div>

					<div class="login-form-group single-row">
						<div class="custom-check">
							<input autocomplete="off" type="checkbox" checked id="remember"><label
								for="remember">Remember Me</label>
						</div>
						<a href="#" class="link forgot-link" id="forgotPasswordLink"
							onclick="openForgotPasswordModal()">Forgot Password ?</a>
					</div>
					<button type="button" class="rounded-button login-cta"
						id="loginButton">Login</button>
				</div>
			</div>
		</div>

		<div class="onboarding">
		   <div class="swiper-container">
				<div class="swiper-wrapper">
				 <img alt="company's logo" src="${paramClient.payload.descp2}" style="width:20%; height:30px; position:relative; float:right;">
					<div class="swiper-slide color-1">
						<img alt="pictureLogin" src="./img/inventory.jpg">
					</div>
				</div>
				
				<div class="swiper-pagination"></div>
			</div>
		</div>


	<nav class="navbar fixed-bottom navbar-light background">
		<div class="container-fluid d-flex justify-content-center">
			<p class="navbar-brand text-center custom-font-size"
				style="color: #ffff;">${paramData.payload.descp1}</p>
		</div>
	</nav>


	<!---------------------------MODAL FOR FORGOT PASSWORD---------------------------------------------->
	<div class="modal" id="forgotPasswordModal">
		<div class="modal-dialog">
			<div class="modal-content">

				<div class="modal-header">
					<b class="modal-title"><span style="color: #1649A2;">Verify
							OTP</span></b>
					<button type="button" class="close" data-dismiss="modal"
						onclick="closeModal()">&times;</button>
				</div>
				<div class="modal-body" style="width: 60%; margin-left: 20%;">
					<div class='dashboard-content'>
						<div class='container'>
							<div class='card'>
								<div class='card-header'>
									<h5 style="color: #1649A2;">To Get OTP Enter Email ID</h5>
								</div>
								<div class='card-body'>
									<section class="get-in-touch">

										<form class="contact-form row" name="dialogBox">
											<div class="form-field col-md-10">

												<input id="email" name="email" class="input-text js-input"
													type="email" required> <label class="label">Email:</label>

											</div>

											<div class="form-field col-lg-12">

												<button class="submit-btn" type="button" id="otpModalButton">Submit</button>

											</div>
										</form>
									</section>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!---------------------- MODAL FOR ENTER OTP FOR CHANGING PASSWORD --------------------------->

	<div class="modal" id="otpgenerationModal"
		style="overflow-y: hidden; backdrop-filter: blur(2px);">
		<div class="modal-dialog">
			<div class="modal-content">

				<div class="modal-header">
					<b class="modal-title"><span style="color: #1649A2;">Verify
							OTP</span></b>
					<button type="button" class="close" data-dismiss="modal"
						onclick="closeModal()">&times;</button>
				</div>
				<div class="modal-body" style="width: 80%; margin-left: 10%;">
					<div class='dashboard-content'>
						<div class='container'>
							<div class='card'>
								<div class='card-header'>
									<h5 style="color: #1649A2;">VERIFY YOUR OTP</h5>
								</div>

								<input id="emailId" name="email" class="input-text js-input"
									type="email" readonly>

								<div class='card-body'>
									<section class="get-in-touch">

										<form class="contact-form row" name="dialogBox">

											<div class="form-field col-md-12">

												<div class="otp-field">
													<input type="text" maxlength="1" /> <input type="text"
														maxlength="1" /> <input type="text" maxlength="1" /> <input
														type="text" maxlength="1" />

												</div>
												<label class="label" for="enterOtp" id="enterOtp">Enter
													OTP:</label>

											</div>

											<div class="form-field col-lg-12">

												<button class="submit-btn" type="button"
													id="submitOtpButton">Submit</button>

											</div>
										</form>


									</section>

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script src="./externalJS/jquery-3.7.0.js"></script>
	<script src="./externalJS/bootstrap.min.js"></script>
	<script src="./js/loginPageScript.js"></script>
	<script src="./js/forgotPassword.js"></script>
</body>
</html>
