/*-------------------------------------FORGOT PASSWORD-----------------------------------------------*/

function forgotPasswordModal() {
	var modal = document.getElementById("forgotPasswordModal");
	modal.style.display = "block";
}
function closeModal() {
	var modal = document.getElementById("forgotPasswordModal");
	modal.style.display = "none";
}
function openOtpModal() {
	var otpGenerationModal = document.getElementById("otpgenerationModal");
	var forgotPasswordModal = document.getElementById("forgotPasswordModal");

	forgotPasswordModal.style.display = "none";

	otpGenerationModal.style.display = "block";
}

const inputs = document.querySelectorAll(".otp-field input");

inputs.forEach((input, index) => {
	input.dataset.index = index;
	input.addEventListener("keyup", handleOtp);
	input.addEventListener("paste", handleOnPasteOtp);
});

function handleOtp(e) {

	const input = e.target;
	let value = input.value;
	let isValidInput = value.match(/[0-9a-z]/gi);
	input.value = "";
	input.value = isValidInput ? value[0] : "";

	let fieldIndex = input.dataset.index;
	if (fieldIndex < inputs.length - 1 && isValidInput) {
		input.nextElementSibling.focus();
	}

	if (e.key === "Backspace" && fieldIndex > 0) {
		input.previousElementSibling.focus();
	}
}

function handleOnPasteOtp(e) {
	const data = e.clipboardData.getData("text");
	const value = data.split("");
	if (value.length === inputs.length) {
		inputs.forEach((input, index) => (input.value = value[index]));

	}
}

/************ SEND OTP VIA EMAIL-ID ************/
var otp;

$(document).ready(function() {
	$("#otpModalButton").on("click", function() {
		var email = $("#email").val();

		if (!isValidEmail(email)) {
			Swal.fire({
				icon: 'error',
				title: 'Invalid Email',
				text: 'Please enter a valid email address.',
			});
			return;
		} else if (email === '') {
			Swal.fire({
				icon: 'error',
				title: 'Invalid Email',
				text: 'Please enter Email ID',
			});
			return;
		} else {
		}

		$.ajax({
			type: "GET",
			url: "sendOTP",
			data: { email: email },

			success: function(response) {
				if (response.statusCode === 200) {

					try {
						Swal.fire({
							icon: 'success',
							title: 'OTP Sent Successfully!',
						});
						otp = response.payload;
						openOtpGenerationModal();
					} catch (e) {
						showErrorAlert("Error parsing response");
						console.error(e);
					}
				} else if (response.statusCode === 404) {
					showErrorAlert("Email ID is not present in database.");
					console.error(e);
				} else {
					showErrorAlert("Error fetching Email ID");
					console.error(e);
				}
			},
			error: function(error) {
				showErrorAlert("Error making the request");
				console.log(error);
			}
		});
	});

	$('#submitOtpButton').click(function() {
		$('#forgotPasswordModal').modal('hide');
		var enteredOtp = '';
		$('.otp-field input').each(function() {
			enteredOtp += $(this).val();
		});

		if (enteredOtp == otp) {
			Swal.fire({
				icon: 'success',
				title: 'OTP Matched',

			});
			const showLogin = setTimeout(loginPage, 3000);
			function loginPage() {
				window.location.href = 'changePassword';
			}
		} else {
			Swal.fire({
				icon: 'error',
				title: 'Invalid OTP',
				text: 'Please enter correct OTP',
			});
		}
	});

	function showErrorAlert(message) {
		Swal.fire({
			icon: 'error',
			title: 'Oops...',
			text: message
		});
	}

	function isValidEmail(email) {
		var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
		return emailRegex.test(email);
	}
});


/************* VERIFY OTP MODAL JS ***************/
function openOtpGenerationModal() {
	var modal = document.getElementById("forgotPasswordModal");
	modal.style.display = "none";

	var OtpModal = document.getElementById("otpgenerationModal");
	OtpModal.style.display = "block";

}
function closeOtp() {
	var closeOtpModal = document.getElementById("otpgenerationModal");
	closeOtpModal.style.display = "none";
}


/*--------------------------------LOADING PAGE WAIT JS---------------------------------------------*/

function loading() {
	document.getElementById("loading").style.display = "block";
}

/*****************JS FOR USER LOGIN*******************/

$(document).ready(function() {
        try {
            function loginUser() {
                var userType = $("#userTypeDropdown").val();
                var email = $("#" + userType + "Email").val();
                var password = $("#pwd").val();

                if (!password && email) {
                    Swal.fire({
                        icon: 'error',
                        title: 'Error',
                        text: 'Please enter your password',
                    });
                    return;
                }

                var userData = {
                    email: email,
                    password: password,
                    empRole: userType
                };

                var loadingTimer;
                var progress = 0;
                var progressBar = $('<div class="progress-bar" role="progressbar" style="width: 0%;" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100"></div>');
                var loadingModal = Swal.fire({
                    title: 'Logging in...',
                    html: '<div class="progress"></div>',
                    onBeforeOpen: () => {
                        Swal.getHtmlContainer().find('.progress').append(progressBar);
                        loadingTimer = setInterval(function() {
                            progress += 1;
                            progressBar.css('width', progress + '%');
                        }, 30);
                    },
                    onAfterClose: () => {
                        clearInterval(loadingTimer);
                    },
                    allowOutsideClick: false,
                    showConfirmButton: false,
                    showCancelButton: false
                });

                $.ajax({
                    type: "POST",
                    url: "employeeLogin",
                    contentType: "application/json",
                    data: JSON.stringify(userData),
                    success: function(response) {
                        loadingModal.close();

                        if (response.statusCode === 200) {
                            if (response.payload.loginFlag === "1") {
                                Swal.fire({
                                    icon: 'success',
                                    title: 'Success',
                                    text: 'Login successful',
                                });

                                setTimeout(function() {
                                    window.location.href = './welcome';
                                }, 1000);
                            } else if (response.payload.loginFlag === "0") {
                                openForgotPasswordModal();
                            }
                        } else if (response.statusCode === 400) {
                            Swal.fire({
                                icon: 'error',
                                title: 'Error',
                                text: 'SORRY! You are not an active employee',
                            });
                        } else if (response.statusCode === 403) {
                            Swal.fire({
                                icon: 'error',
                                title: 'Error',
                                text: 'Wrong user type',
                            });
                        } else {
                            Swal.fire({
                                icon: 'error',
                                title: 'Error',
                                text: 'User does not exist in the database',
                            });
                        }
                    },
                    error: function(xhr, status, error) {
                        console.error("AJAX request failed: " + error);
                        loadingModal.close();
                        Swal.fire({
                            icon: 'error',
                            title: 'Error',
                            text: 'Unexpected error occurred',
                        });
                    }
                });
            }

            $("#pwd").on("keypress", function(event) {
                if (event.which === 13) { 
                    loginUser();
                }
            });

            $(".login-cta").on("click", loginUser);

        } catch (e) {
            console.log(e);
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Error occurred while User Login',
            });
        }
    });

function openForgotPasswordModal() {
	forgotPasswordModal();
}

