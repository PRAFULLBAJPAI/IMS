/******** REFRESH PAGE *********/
function refreshPage() {
	document.body.classList.add('hidden');
	setTimeout(function() {
		location.reload();
	}, 500);
}
/****************************************************************************************************/
/*                       SEARCH BY ITEM CODE (USED FOR ITEM NAME)                                   */
/****************************************************************************************************/
function searchByItemName() {
	var itemName = $("#searchInputByName").val();
	$.ajax({
		type: "GET",
		url: "getItemByItemName",
		data: { itemName: itemName },
		success: function(data) {
			if (data && data.payload) {
				var item = data.payload[0];
				$("#itemCode").val(item.itemCode);
				$("#itemName").val(item.itemName);
				$("#itemType").val(item.itemType);
				$("#itemSource").val(item.itemSource);
				$("#itemUom").val(item.itemUom);
				$("#minimumStock").val(item.minimumStock);
				$("#itemGroup").val(item.itemGroup);
				$("#hsnCode").val(item.hsnCode);
				$("#warehouse").val(item.warehouse);
				$("#description").val(item.description);
				$("#imageId").val(item.imageId);
				$("#unitPrice").val(item.unitPrice);
				$("#quantityInStock").val(item.quantityInStock);
				$("#status").val(item.status);
				$("#userId").val(item.userId);
				$("#remarks").val(item.remarks);
				$("#entryDate").val(item.entryDate);

				/*var imageName = data.payload.imageName;
				if (imageName) {
					$("#imageInput").next('.placeholder').text(imageName);
				} else {
					$("#imageInput").next('.placeholder').text("Upload Image");
				}

				if (data.payload.image) {
					var base64Image = btoa(new Uint8Array(data.payload.image).reduce((data, byte) => data + String.fromCharCode(byte), ''));
					var imageUrl = "data:image;base64," + base64Image;
					$("#displayedImage").attr("src", imageUrl);
					$("#displayedImage").show();
				} else {
					$("#displayedImage").hide();
				}*/
			} else {
				console.error("Payload is missing or empty.");
			}
		},
		error: function(error) {
			console.error("Error fetching data:", error);
		}
	});
}

/* *************************************************************************************************/
/* SEARCH BY ITEM CODE */
/** ************************************************************************************************* */
function searchByItemCode() {
	var itemCode = $("#searchInput").val();
	$.ajax({
		type: "GET",
		url: "getItemByItemCode",
		data: { itemCode: itemCode },
		success: function(data) {
			if (data && data.payload) {
				var item = data.payload[0];
				$("#itemCode").val(item.itemCode);
				$("#itemName").val(item.itemName);
				$("#itemType").val(item.itemType);
				$("#itemSource").val(item.itemSource);
				$("#itemUom").val(item.itemUom);
				$("#minimumStock").val(item.minimumStock);
				$("#itemGroup").val(item.itemGroup);
				$("#hsnCode").val(item.hsnCode);
				$("#warehouse").val(item.warehouse);
				$("#description").val(item.description);
				$("#imageId").val(item.imageId);
				$("#unitPrice").val(item.unitPrice);
				$("#quantityInStock").val(item.quantityInStock);
				$("#status").val(item.status);
				$("#userId").val(item.userId);
				$("#remarks").val(item.remarks);
				$("#entryDate").val(item.entryDate);

				/*var imageName = data.payload.imageName;
				if (imageName) {
					$("#imageInput").next('.placeholder').text(imageName);
				} else {
					$("#imageInput").next('.placeholder').text("Upload Image");
				}

				if (data.payload.image) {
					var base64Image = btoa(new Uint8Array(data.payload.image).reduce((data, byte) => data + String.fromCharCode(byte), ''));
					var imageUrl = "data:image;base64," + base64Image;
					$("#displayedImage").attr("src", imageUrl);
					$("#displayedImage").show();
				} else {
					$("#displayedImage").hide();
				}*/
			} else {
				console.error("Payload is missing or empty.");
			}
		},
		error: function(error) {
			console.error("Error fetching data:", error);
		}
	});
}

/****************************************************************************************************/
/*                                     EDIT ITEM MASTER COMPLETE                                       */
/******************************************************************************************************/
function editItemImage() {
	var imageInput = $('#imageInput')[0];

	var formData = new FormData();
	formData.append('image', imageInput.files[0]);
	formData.append('imageId', $("#imageId").val());
	function editNewItem(callback) {
		var itemData = {
			itemCode: $("#itemCode").val(),
			itemName: $("#itemName").val(),
			itemType: $("#itemType").val(),
			itemSource: $("#itemSource").val(),
			itemUom: $("#itemUom").val(),
			minimumStock: parseInt($("#minimumStock").val()),
			itemGroup: $("#itemGroup").val(),
			hsnCode: $("#hsnCode").val(),
			warehouse: $("#warehouse").val(),
			description: $("#description").val(),
			imageId: $("#imageId").val(),
			unitPrice: parseFloat($("#unitPrice").val()),
			quantityInStock: parseInt($("#quantityInStock").val()),
			userId: $("#userId").val(),
			remarks: $("#remarks").val()
		};
		if (!validateItemFormData(itemData)) {
			return;
		}
		$.ajax({
			type: 'POST',
			url: 'editItemMaster',
			data: itemData,
			success: function(data) {
				if (data.statusCode == 200) {
					Swal.fire({
						title: "Item updated successfully.",
						text: "",
						icon: "success",
					}).then(function() {
						callback();
					});
				} else {
					Swal.fire({
						title: "Item detail save failed",
						text: "Please contact the administrator",
						icon: "error",
					});
				}
			},
		});
	}
	editNewItem(function() {
		$.ajax({
			type: 'POST',
			url: 'EditItemImage',
			data: formData,
			contentType: false,
			processData: false,
			success: function(response) {

				if (response.statusCode == 200) {
					location.reload();
				}
			},
			error: function(error) {
				location.reload();
				console.error(error);
			}
		});
	});
}



/** *************************************************************************************************** */
/* SAVE ITEM MASTER COMPLETE */
/** *************************************************************************************************** */
function saveNewImage() {
	var imageInput = $('#imageInput')[0];
	if (!imageInput || !imageInput.files || imageInput.files.length === 0) {
		Swal.fire({
			title: "Please select an image before saving.",
			icon: "warning",
		});
		return;
	}
	var formData = new FormData();
	formData.append('image', imageInput.files[0]);
	function saveNewItem(callback) {
		var itemData = {
			itemCode: $("#itemCode").val(),
			itemName: $("#itemName").val(),
			itemType: $("#itemType").val(),
			itemSource: $("#itemSource").val(),
			itemUom: $("#itemUom").val(),
			minimumStock: parseInt($("#minimumStock").val()),
			itemGroup: $("#itemGroup").val(),
			hsnCode: $("#hsnCode").val(),
			warehouse: $("#warehouse").val(),
			description: $("#description").val(),
			imageId: $("#imageId").val(),
			unitPrice: parseFloat($("#unitPrice").val()),
			quantityInStock: parseInt($("#quantityInStock").val()),
			userId: $("#userId").val(),
			remarks: $("#remarks").val()
		};
		if (!validateItemFormData(itemData)) {
			return;
		}
		$.ajax({
			type: 'POST',
			url: 'saveItemMaster',
			data: JSON.stringify(itemData),
			contentType: 'application/json',
			success: function(data) {
				if (data.statusCode == 200) {
					Swal.fire({
						title: "Item added successfully.",
						text: "",
						icon: "success",
					}).then(function() {
						callback();
					});
				} else {
					Swal.fire({
						title: "Item detail save failed",
						text: "Please contact the administrator",
						icon: "error",
					});
				}
			},
		});
	}
	saveNewItem(function() {
		$.ajax({
			type: 'POST',
			url: 'saveAndEditItemImage',
			data: formData,
			contentType: false,
			processData: false,
			success: function(response) {
				if (response.statusCode == 200) {
					location.reload();
					// Swal.fire({
					// title: "Item Image added successfully.",
					// text: "",
					// icon: "success",
					// });
				}
			},
			error: function(error) {
				Swal.fire({
					title: "Error Occurred!!",
					text: error.responseText,
					icon: "error",
				});
				console.error(error);
			}
		});
	});
}

function validateItemFormData(formData) {
	if (!formData.itemName || formData.itemName.trim() === "") {
		Swal.fire({
			title: "Item Name is required.",
			icon: "warning",
		});
		return false;
	}
	if (!formData.itemType || formData.itemType.trim() === "") {
		Swal.fire({
			title: "Item Type is required.",
			icon: "warning",
		});
		return false;
	}

	if (!formData.itemSource || formData.itemSource.trim() === "") {
		Swal.fire({
			title: "Item Source is required.",
			icon: "warning",
		});
		return false;
	}

	if (!formData.itemUom || formData.itemUom.trim() === "") {
		Swal.fire({
			title: "Item UOM is required.",
			icon: "warning",
		});
		return false;
	}

	if (isNaN(formData.minimumStock) || formData.minimumStock < 0) {
		Swal.fire({
			title: "Minimum Stock must be a non-negative number.",
			icon: "warning",
		});
		return false;
	}

	if (!formData.itemGroup || formData.itemGroup.trim() === "") {
		Swal.fire({
			title: "Item Group is required.",
			icon: "warning",
		});
		return false;
	}

	if (!formData.hsnCode || formData.hsnCode.trim() === "") {
		Swal.fire({
			title: "HSN Code is required.",
			icon: "warning",
		});
		return false;
	}

	if (!formData.warehouse || formData.warehouse.trim() === "") {
		Swal.fire({
			title: "Warehouse is required.",
			icon: "warning",
		});
		return false;
	}

	if (!formData.description || formData.description.trim() === "") {
		Swal.fire({
			title: "Description is required.",
			icon: "warning",
		});
		return false;
	}
	if (!/^\d+(\.\d{1,2})?$/.test(formData.unitPrice)) {
		Swal.fire({
			title: "Unit Price must be a valid number with up to two decimal places.",
			icon: "warning",
		});
		return false;
	}
	if (isNaN(formData.unitPrice) || formData.unitPrice <= 0) {
		Swal.fire({
			title: "Unit Price must be a valid positive number.",
			icon: "warning",
		});
		return false;
	}

	if (!Number.isInteger(formData.quantityInStock)
		|| formData.quantityInStock < 0) {
		Swal.fire({
			title: "Quantity in Stock must be a valid non-negative integer.",
			icon: "warning",
		});
		return false;
	}

	if (!formData.remarks || formData.remarks.trim() === "") {
		Swal.fire({
			title: "Remarks is required.",
			icon: "warning",
		});
		return false;
	}
	return true;
}
/** ************************************************************************************************* */
/* SEARCH BAR FOR ITEM CODE */
/** ************************************************************************************************* */
const searchInput = document.getElementById('searchInput');
const suggestionDropdown = document.getElementById('suggestionDropdown');
let lastSearchTerm = '';
let lastFetchedData = [];
searchInput.addEventListener('input', function() {
	const searchTerm = searchInput.value.toLowerCase();
	if (searchTerm !== lastSearchTerm) {
		fetch(`getItemByItemCode?itemCode=${searchTerm}`)
			.then(response => response.json())
			.then(data => {
				console.log('Fetched Data:', data);
				lastFetchedData = (data && data.payload && Array.isArray(data.payload)) ? data.payload : [];
				renderSuggestions(searchTerm);
			})
			.catch(error => console.error('Error fetching suggestions:', error))
			.finally(() => {
				lastSearchTerm = searchTerm;
			});
	} else {
		renderSuggestions(searchTerm);
	}
});
function renderSuggestions(searchTerm) {
	console.log('Rendering Suggestions:', lastFetchedData);
	if (searchTerm.trim() === '') {
		suggestionDropdown.style.display = 'none';
		return;
	}
	const matchingSuggestions = lastFetchedData.filter(item => item.itemCode.toLowerCase().includes(searchTerm));
	if (matchingSuggestions.length > 0) {
		suggestionDropdown.innerHTML = '';
		matchingSuggestions.forEach(suggestion => {
			const suggestionItem = document.createElement('div');
			suggestionItem.classList.add('suggestionItem');
			suggestionItem.textContent = suggestion.itemCode;
			suggestionItem.innerHTML = `${suggestion.itemCode} ${suggestion.itemName}`;
			suggestionItem.addEventListener('click', () => {
				searchInput.value = suggestion.itemCode;
				suggestionDropdown.style.display = 'none';
			});
			suggestionDropdown.appendChild(suggestionItem);
		});
		suggestionDropdown.style.display = 'block';
	} else {
		suggestionDropdown.style.display = 'none';
	}
}
document.addEventListener('click', function(event) {
	if (!searchInput.contains(event.target) && !suggestionDropdown.contains(event.target)) {
		suggestionDropdown.style.display = 'none';
	}
});
/** ************************************************************************************************* */
/* SEARCH BAR FOR ITEM NAME */
/** ************************************************************************************************* */
const searchInputByName = document.getElementById('searchInputByName');
const suggestionDropdownForName = document.getElementById('suggestionDropdownForName');
let lastSearchTermByName = '';
let lastFetchedDataByName = [];
searchInputByName.addEventListener('input', function() {
	const searchTermByName = searchInputByName.value.toLowerCase();
	if (searchTermByName !== lastSearchTermByName) {
		fetch(`getItemByItemName?itemName=${searchTermByName}`)
			.then(response => response.json())
			.then(data => {
				lastFetchedDataByName = (data && data.payload && Array.isArray(data.payload)) ? data.payload : [];
				renderSuggestionsByName(searchTermByName);
			})
			.catch(error => console.error('Error fetching suggestions:', error))
			.finally(() => {
				lastSearchTermByName = searchTermByName;
			});
	} else {
		renderSuggestionsByName(searchTermByName);
	}
});
function renderSuggestionsByName(searchTermByName) {
	if (searchTermByName.trim() === '') {
		suggestionDropdownForName.style.display = 'none';
		return;
	}
	const matchingSuggestions = lastFetchedDataByName.filter(item =>
		item.itemName.toLowerCase().includes(searchTermByName) || item.itemCode.toLowerCase().includes(searchTermByName)
	);
	if (matchingSuggestions.length > 0) {
		suggestionDropdownForName.innerHTML = '';
		matchingSuggestions.forEach(suggestion => {
			const suggestionItem = document.createElement('div');
			suggestionItem.classList.add('suggestionItem');
			suggestionItem.innerHTML = `${suggestion.itemCode} : ${suggestion.itemName}`;
			suggestionItem.addEventListener('click', () => {
				
				searchInputByName.value = suggestion.itemName;
				suggestionDropdownForName.style.display = 'none';
			});
			suggestionDropdownForName.appendChild(suggestionItem);
		});
		suggestionDropdownForName.style.display = 'block';
	} else {
		suggestionDropdownForName.style.display = 'none';
	}
}
document.addEventListener('click', function(event) {
	if (!searchInputByName.contains(event.target) && !suggestionDropdownForName.contains(event.target)) {
		suggestionDropdownForName.style.display = 'none';
	}
});
/** ************************************************************************************************* */
/* CHOOSE FILE IN IMAGE TAG */
/** ************************************************************************************************* */
document.getElementById('imageInput').addEventListener('change', function() {
	var displayedImage = document.getElementById('displayedImage');

	if (this.files && this.files[0]) {
		var reader = new FileReader();

		reader.onload = function(e) {
			displayedImage.src = e.target.result;
		};

		reader.readAsDataURL(this.files[0]);
	}
});


/** ************************************************************************************************* */
/* DELETE ITEM MASTER  */
/** ************************************************************************************************* */

function deleteItemMaster() {
	var itemCode = $("#itemCode").val();
	Swal.fire({
		title: "Are you sure you want to delete this item?",
		text: "You won't be able to revert this!",
		icon: "warning",
		showCancelButton: true,
		confirmButtonColor: "#d33",
		cancelButtonColor: "#3085d6",
		confirmButtonText: "Yes, delete it!",
	}).then((result) => {
		if (result.isConfirmed) {
			$.ajax({
				type: 'POST',
				url: 'deleteItemMaster',
				data: { itemCode: itemCode },
				success: function(response) {
					if (response.statusCode == 200) {
						Swal.fire({
							title: "Item deleted successfully.",
							text: "",
							icon: "success",
						}).then(function() {
							location.reload();
						});
					} else {
						Swal.fire({
							title: "Error Occurred!!",
							text: response.message,
							icon: "error",
						});
					}
				},
				error: function(error) {
					Swal.fire({
						title: "Error Occurred!!",
						text: error.responseText,
						icon: "error",
					});
					console.error(error);
				},
			});
		}
	});
}

/** ************************************************************************************************* */
/* DEACTIVATE ITEM MASTER */
/** ************************************************************************************************* */
function deactivateItemMaster() {
	var itemCode = $("#itemCode").val();

	Swal.fire({
		title: "Are you sure you want to deactivate this item?",
		icon: "warning",
		showCancelButton: true,
		confirmButtonColor: "#d33",
		cancelButtonColor: "#3085d6",
		confirmButtonText: "Yes, delete it!",
	}).then((result) => {
		if (result.isConfirmed) {
			$.ajax({
				type: 'POST',
				url: 'deactivateItemByCode',
				data: { itemCode: itemCode },
				success: function(response) {
					if (response.statusCode == 200) {
						Swal.fire({
							title: "Item Deactivated successfully.",
							text: "",
							icon: "success",
						}).then(function() {
							location.reload();
						});
					} else {
						Swal.fire({
							title: "Error Occurred!!",
							text: response.message,
							icon: "error",
						});
					}
				},
				error: function(error) {
					Swal.fire({
						title: "Error Occurred!!",
						text: error.responseText,
						icon: "error",
					});
					console.error(error);
				},
			});
		}
	});
}

/*------------------------------------IMPORT ITEM MASTER----------------------------------*/

function importItemMaster() {
	var fileInput = document.getElementById('itemFile');
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
	formData.append('itemMasterFile', file);

	fetch('importItemMaster', {
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
