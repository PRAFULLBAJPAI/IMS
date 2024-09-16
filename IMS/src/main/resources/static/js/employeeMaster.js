/*-----------------------------IMPORT EMPLOYEE -: By Suyash -----------------------------------*/

function importEmployeeMaster() {
	var fileInput = document.getElementById('formFile');
	var file = fileInput.files[0];

	if (!file) {
		Swal.fire({
			icon: 'error',
			title: 'Oops...',
			text: 'Please choose a file.',
		});
		return;
	}

	var formData = new FormData();
	formData.append('employeeMasterFile', file);

	fetch('importEmployeeMaster', {
		method: 'POST',
		body: formData,
	})
		.then(response => {
			if (!response.ok) {
				throw new Error(`HTTP error! Status: ${response.status}`);
			}
			return response.text();
		})
		.then(data => {
			if (data.includes('saved')) {
				Swal.fire({
					icon: 'success',
					title: 'Import Successful',
					text: data,
				});
			} else {
				Swal.fire({
					icon: 'error',
					title: 'Failed',
					text: 'Import Failed. Please choose correct file.',
				});
			}
		})
		.catch(error => {
			console.error('Error during import:', error);
			Swal.fire({
				icon: 'error',
				title: 'Oops...',
				text: 'Import failed. Please try again.',
			});
		});
}



/*----------------------------------------------IMS EMPLOYEE MASTER JS-------------------------------------------------*/

$(document).ready(function() {
	$('#groupId').change(function() {
		var opval = $(this).val();
		if (opval == "otherGroup") {
			$('#groupMasterModal').modal("show");
		}
	});
});
function closeModal() {
	$('#groupMasterModal').modal("hide");
}

/************* SAVE NEW EMPLOYEE --- SWATI *****************/

function saveNewEmployee() {
	if (validateEmployeeFormData()) {
		try {
			var formData = {
				empId: $("#empId").val(),
				empName: $("#empName").val(),
				empRole: $("#empRole").val(),
				groupId: $("#groupId").val(),
				empPan: $("#empPan").val(),
				address: $("#address").val(),
				city: $("#city").val(),
				pin: $("#pin").val(),
				state: $("#state").val(),
				country: $("#country").val(),
				mobile: $("#mobile").val(),
				email: $("#email").val(),
				branch: $("#branch").val()
			};

			$.ajax({
				url: "saveEmployeeDetails",
				type: "POST",
				dataType: "json",
				contentType: "application/json",
				data: JSON.stringify(formData),
				success: function(data) {
					try {
						Swal.fire({
							icon: 'success',
							title: 'Success!',
							text: 'Employee saved successfully.',
						}).then(function() {
							location.reload();
						});

					} catch (successError) {
						console.error(successError);
					}
				},
				error: function(error) {
					alert("error");
					try {
						Swal.fire({
							icon: 'error',
							title: 'Error!',
							text: 'Failed to save Employee.',
						});
						console.error(error);
					} catch (errorHandlingError) {
						console.error(errorHandlingError);
					}
				}
			});
		} catch (outerError) {
			console.error(outerError);
		}
	}
}

function updateEmployee() {
    if (validateEmployeeFormData()) {
        var formData = {
            empId: $("#empId").val(),
            empName: $("#empName").val(),
            empRole: $("#empRole").val(),
            groupId: $("#groupId").val(),
            empPan: $("#empPan").val(),
            address: $("#address").val(),
            city: $("#city").val(),
            pin: $("#pin").val(),
            state: $("#state").val(),
            country: $("#country").val(),
            mobile: $("#mobile").val(),
            email: $("#email").val(),
            branch: $("#branch").val()
        };

        $.ajax({
            url: "updateEmployeeDetails",
            type: "PUT",
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(formData),
            success: function(data) {
                try {
                    if (data.statusCode === 200) {
                        Swal.fire({
                            icon: 'success',
                            title: 'Success!',
                            text: 'Employee details updated successfully.',
                        }).then(function() {
                            location.reload();
                        });
                    } else if (data.statusCode === 404) {
                        Swal.fire({
                            icon: 'error',
                            title: 'Error!',
                            text: data.message || 'Employee not found in the database.',
                        });
                    } else {
                        Swal.fire({
                            icon: 'error',
                            title: 'Error!',
                            text: data.message || 'Failed to update employee details.',
                        });
                    }
                } catch (error) {
                    console.error(error);
                }
            },
            error: function(xhr, status, error) {
                Swal.fire({
                    icon: 'error',
                    title: 'Error!',
                    text: 'Failed to update employee details. Please try again later.',
                });
                console.error(xhr.responseText);
                console.error(status);
                console.error(error);
            }
        });
    } else {
        Swal.fire({
            icon: 'error',
            title: 'Error!',
            text: 'Please fill out all required fields before updating employee details.',
        });
    }
}



function resetForm() {
	try {
		$("#empForm")[0].reset();
	} catch (resetError) {
		console.error(resetError);
	}
}


function validateEmployeeFormData() {
	try {
		errStr = "";
		if ($('#empName').val() == '') {
			Swal.fire({
				text: "Please enter Employee Name.",
				icon: "error",
			});
			return false;
		}
		if ($('#empRole').val() == '') {
			Swal.fire({
				text: "Please Select Employee Role.",
				icon: "error",
			});
			return false;
		}
		if ($('#empPan').val().length == '') {
			Swal.fire({
				text: "Please enter Employee PAN.",
				icon: "error",
			});
			return false;
		}
		if ($('#empPan').val().length > 10) {
			Swal.fire({
				text: "Please enter valid PAN.",
				icon: "error",
			});
			return false;
		}
		if ($('#address').val() == '') {
			Swal.fire({
				text: "Please enter Employee Address.",
				icon: "error",
			});
			return false;
		}
		if ($('#city').val() == '') {
			Swal.fire({
				text: "Please enter City.",
				icon: "error",
			});
			return false;
		}
		if ($('#pin').val() == '') {
			Swal.fire({
				text: "Please enter PinCode.",
				icon: "error",
			});
			return false;
		}
		if ($('#state').val() == '') {
			Swal.fire({
				text: "Please enter State.",
				icon: "error",
			});
			return false;
		}
		if ($('#country').val() == '') {
			Swal.fire({
				text: "Please enter Country.",
				icon: "error",
			});
			return false;
		}
		if ($('#mobile').val() == '') {
			Swal.fire({
				text: "Please enter Mobile Number.",
				icon: "error",
			});
			return false;
		}
		if ($('#email').val() == '') {
			Swal.fire({
				text: "Please enter Email.",
				icon: "error",
			});
			return false;
		}
		if ($('#branch').val() == '') {
			Swal.fire({
				text: "Please enter Branch.",
				icon: "error",
			});
			return false;
		}
		return true;
	} catch (err) {
		alert(err.message);
	}
}

/*****************JS TO FETCH EMPLOYEE BY EMPLOYEE ID****************** */
function fetchEmployee(empId) {

	$.ajax({
		type: "GET",
		url: "getEmployeeByEmpId",
		data: { empId: empId },
		success: function(response) {
			if (response.statusCode === 200) {
				var employee = response.payload;
				console.log(response.payload)

				$("#groupId").val(employee.groupId);
				$("#empId").val(employee.empId);
				$("#empName").val(employee.empName);
				$("#empRole").val(employee.empRole);
				$("#empPan").val(employee.empPan);
				$("#address").val(employee.address);
				$("#city").val(employee.city);
				$("#pin").val(employee.pin);
				$("#state").val(employee.state);
				$("#country").val(employee.country);
				$("#mobile").val(employee.mobile);
				$("#email").val(employee.email);
				$("#branch").val(employee.branch);


			} else {
				console.error("Error fetching details:", response.message);
			}
		},
		error: function(error) {
			console.error("Error fetching details:", error);
		}
	});
}
