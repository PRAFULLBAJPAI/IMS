/**
 * MODAL FUNCTION FOR ADD EMPLOYEE MODAL: SWATI
 */
const openModalButton = document.getElementById('addVendorModalButton');
const vendorModal = document.getElementById('vendorMasterModal');

function openVendorModal() {
	vendorModal.style.display = 'block';
}


function closeVendorModal() {
	vendorModal.style.display = 'none';
	$("#vendorForm")[0].reset();


}

openModalButton.addEventListener('click', openVendorModal);

vendorModal.addEventListener('click', function(event) {
	if (event.target === vendorModal) {
		closeVendorModal();
	}
});

const closeModalButton = document.querySelector('#vendorMasterModal .close');
closeModalButton.addEventListener('click', closeVendorModal);

/*****************JS TO FETCH EMPLOYEE BY EMPLOYEE ID****************** */
function fetchVendor(vendorId) {

	$.ajax({
		type: "GET",
		url: "getVendorMasterById",
		data: { vendorId: vendorId },
		success: function(response) {

			if (response.statusCode === 200) {
				var vendor = response.payload;

				$("#vendorId").val(vendor.vendorId);
				$("#vendorName").val(vendor.vendorName);
				$("#contactPerson").val(vendor.contactPerson);
				$("#email").val(vendor.email);
				$("#mobile").val(vendor.mobile);
				$("#address").val(vendor.address);
				$("#city").val(vendor.city);
				$("#state").val(vendor.state);
				$("#country").val(vendor.country);
				$("#empId").val(vendor.empId);
				$("#entryDate").val(vendor.entryDate);
				$("#status").val(vendor.status);
				$("#pin").val(vendor.pin);

				openVendorModal();


			} else {
				console.error("Error fetching vendor details:", response.message);
			}
		},
		error: function(error) {
			console.error("Error fetching vendor details:", error);
		}
	});
}

// De-activate the Vendor
function deactivateVendor() {
	var selectedStatus = document.getElementById("branch").value;

	if (selectedStatus === "N") {
		var vendorId = document.getElementById("vendorId").value;

		var xhr = new XMLHttpRequest();
		xhr.open("PUT", "/deactivateVendorByVendorId?vendorId=" + vendorId, true);
		xhr.onreadystatechange = function() {
			if (xhr.readyState === 4) {
				if (xhr.status === 200) {
					Swal.fire({
						icon: 'success',
						title: 'Success',
						text: 'Vendor De-Activated successfully!',
					});
				} else {
					Swal.fire({
						icon: 'error',
						title: 'Error',
						text: 'Error:'+ xhr.statusText,
					})
				}
			}
		};
		xhr.send();
	} else {
		document.getElementById("vendorForm").submit();
	}
}

function resetForm() {
	document.getElementById("vendorForm").reset();
	//document.getElementById("vendorForm").reset();
}

function showLoadingIndicator() {
        Swal.fire({
            title: 'Updating Vendor',
            text: 'Please wait...',
            allowOutsideClick: false,
            showCancelButton: false,
            showConfirmButton: false,
            onBeforeOpen: () => {
                Swal.showLoading();
            },
        });
    }

function hideLoadingIndicator() {
    Swal.close();
}


/********** ADD VENDOR MASTER DATA ************/
function saveVendor() {
    var emptyField = validateForm();
    if (emptyField) {
        Swal.fire({
            icon: 'warning',
            title: 'Empty Field',
            text: 'Please fill in the ' + emptyField + ' field.',
        });
        return;
    }

    // Validate Status field
    var status = $('#status').val();
    if (status !== "Y" && status !== "N") {
        Swal.fire({
            icon: 'error',
            title: 'Invalid Status',
            text: 'Please select a valid Vendor Status (Active or De-Active).',
        });
        return;
    }

    // Validate Email format
    var email = $('#email').val();
    if (email && !isValidEmail(email)) {
        Swal.fire({
            icon: 'error',
            title: 'Invalid Email',
            text: 'Please enter a valid email address.',
        });
        return;
    }

    // Validate Mobile format
    var mobile = $('#mobile').val();
    if (mobile && !isValidMobile(mobile)) {
        Swal.fire({
            icon: 'error',
            title: 'Invalid Mobile Number',
            text: 'Please enter a valid 10-digit mobile number.',
        });
        return;
    }

    $('#loader').show();

    var formData = {
        vendorId: $('#vendorId').val(),
        vendorName: $('#vendorName').val(),
        contactPerson: $('#contactPerson').val(),
        empId: $('#empId').val(),
        entryDate: $('#entryDate').val(),
        address: $('#address').val(),
        pin: $('#pin').val(),
        city: $('#city').val(),
        state: $('#state').val(),
        country: $('#country').val(),
        mobile: $('#mobile').val(),
        email: $('#email').val(),
        status: status
    };

    $.ajax({
        type: 'POST',
        url: 'saveVendorMaster',
        contentType: 'application/json',
        data: JSON.stringify(formData),
        success: function (data) {
            Swal.fire({
                icon: 'success',
                title: 'Data Saved!',
                text: 'Your data has been successfully saved.',
            })
            .then(() => {
                window.location.reload();
            });
        },
        error: function (error) {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Error saving data. Please try again.',
            });
        }
    });
}

function isValidEmail(email) {
    // Use a regular expression to validate email format
    var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
}

function isValidMobile(mobile) {
    // Use a regular expression to validate mobile format (10 digits)
    var mobileRegex = /^\d{10}$/;
    return mobileRegex.test(mobile);
}

function isValidatePin(pin) {
    var pinRegex = /^\d{6}$/;
    return pinRegex.test(pin);
}


function validateForm() {
    var fields = [
        { id: 'vendorName', name: 'Vendor Name' },
        { id: 'contactPerson', name: 'Contact Person', maxLength: 200 },
        { id: 'empId', name: 'Employee ID' },
        { id: 'entryDate', name: 'Entry Date' },
        { id: 'address', name: 'Address' },
        { id: 'pin', name: 'Pincode' },
        { id: 'city', name: 'City' },
        { id: 'state', name: 'State' },
        { id: 'country', name: 'Country' },
        { id: 'mobile', name: 'Mobile' },
        { id: 'email', name: 'E-mail' },
        { id: 'status', name: 'Vendor Status' }
    ];

    for (var i = 0; i < fields.length; i++) {
        var fieldValue = $('#' + fields[i].id).val();

        // Check if the field has a maximum length defined
        if (fields[i].maxLength && fieldValue.length > fields[i].maxLength) {
            return fields[i].name + ' should not exceed ' + fields[i].maxLength + ' characters.';
        }

        if (!fieldValue || fieldValue.trim() === '') {
            return fields[i].name + ' is required.';
        }
    }

    return null;
}


    
    
/************ UPDATE VENDOR *************/
function editVendor() {
    var vendorId = $("#vendorId").val();
    var vendorName = $("#vendorName").val();
    var contactPerson = $("#contactPerson").val();
    var entryDate = $("#entryDate").val();
    var address = $("#address").val();
    var pin = $("#pin").val();
    var city = $("#city").val();
    var state = $("#state").val();
    var country = $("#country").val();
    var mobile = $("#mobile").val();
    var email = $("#email").val();
    var status = $("#status").val();
    var empId = $("#empId").val();

    var vendorMasterDTO = {
        vendorId: vendorId,
        vendorName: vendorName,
        contactPerson: contactPerson,
        entryDate: entryDate,
        address: address,
        pin: pin,
        city: city,
        state: state,
        country: country,
        mobile: mobile,
        email: email,
        status: status,
        empId: empId
    };

    // Show loader
    showLoadingIndicator();

    // AJAX request to updateVendorMaster API
    $.ajax({
        type: "PUT",
        url: "/updateVendorMaster",
        contentType: "application/json", // Set content type to JSON
        data: JSON.stringify(vendorMasterDTO), // Convert data to JSON string
        success: function (response) {
            // Hide loader
            hideLoadingIndicator();

            // Handle success response
            if (status === "N") {
                // Deactivated Swal
                Swal.fire({
                    icon: 'success',
                    title: 'Vendor Deactivated successfully',
                    showConfirmButton: false,
                    timer: 1500
                })
                .then(() => {
                    location.reload(true);
                });
            } else {
                // Updated Swal
                Swal.fire({
                    icon: 'success',
                    title: 'Vendor updated successfully',
                    showConfirmButton: false,
                    timer: 1500
                })
                .then(() => {
                    location.reload(true);
                });
            }
        },
        error: function (error) {
            // Hide loader
            hideLoadingIndicator();

            // Handle error response
            Swal.fire({
                icon: 'error',
                title: 'Error updating vendor',
                text: error.responseJSON.message,
            });
        }
    });
}


