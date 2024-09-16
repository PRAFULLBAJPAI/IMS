/**
 * 
 */
let rowCounter = 1;

function initializeSelect2ForRow(index) {
	var inputId = `itemCode_${index}`;
	initializeSelect2(inputId, index);
}

document.addEventListener('DOMContentLoaded', function() {
	const initialRowCount = $('#example tbody tr').length;

	for (let i = 1; i <= initialRowCount; i++) {
		initializeSelect2ForRow(i);
	}
});

function addNewItemRow() {
	const tableBody = document.querySelector('#example tbody');
	const newRow = tableBody.insertRow();
	const itemCodeCell = newRow.insertCell();
	const itemNameCell = newRow.insertCell();
	const rawItemsUsedCell = newRow.insertCell();
	const quantityInStockCell = newRow.insertCell();
	const quantityCell = newRow.insertCell();
	const actionsCell = newRow.insertCell();

	const inputId = `itemCode_${rowCounter}`;
	const itemDetailsId = `item-details_${rowCounter}`;
	itemCodeCell.innerHTML = `
        <div class="input-group">
            <select id="${inputId}" class="input-11 form-control form-control-sm select2">
                <option>---select---</option>
            </select>
        </div>
    `;

	initializeSelect2ForRow(rowCounter);

	itemNameCell.innerHTML = `<input type="text" id="itemName_${rowCounter}" class="form-control form-control-sm" />`;
	rawItemsUsedCell.innerHTML = '';
	rawItemsUsedCell.id = itemDetailsId; 
	quantityInStockCell.innerHTML = `<input type="text" id="quantityInStock_${rowCounter}" class="form-control form-control-sm" />`;
	quantityCell.innerHTML = '<input type="text" class="form-control form-control-sm" />';
	actionsCell.innerHTML = `
        <button type="button" class="btn btn-danger btn-sm ml-2" onclick="removeItemRow(this);">
            <i class="fa-regular fa-trash-can fa-lg"></i>
        </button>`;

	const addNewButton = document.createElement('button');
	addNewButton.setAttribute('type', 'button');
	addNewButton.classList.add('btn', 'btn-sm', 'ml-2');
	addNewButton.style.background = '#6595ff';
	addNewButton.style.color = '#ffff';
	addNewButton.addEventListener('click', addNewItemRow);
	addNewButton.innerHTML = '<i class="fa-solid fa-square-plus fa-lg"></i>';

	const addNewCell = newRow.insertCell();
	addNewCell.appendChild(addNewButton);

	rowCounter++;
}

function removeItemRow(button) {
	var row = button.parentNode.parentNode;
	row.parentNode.removeChild(row);
}

/***Render suggestions for HTML Code***/
$(document).ready(function() {
	var selectedValues = {};

	var itemCodeColumnWidth = $(".col-md-2").first().outerWidth();

	$("#itemCode").select2({
		minimumInputLength: 1,
		placeholder: " ",
		dropdownAutoWidth: true,
		width: itemCodeColumnWidth,
		ajax: {
			url: "getItemByConsmptionType",
			dataType: 'json',
			data: function(params) {
				return {
					itemCode: params.term

				};
			},
			processResults: function(data) {
				return {
					results: $.map(data.payload, function(itemMasterDTO) {
						return {
							id: itemMasterDTO.itemCode,
							text: itemMasterDTO.itemCode + " : " + itemMasterDTO.itemName,
							itemCode: itemMasterDTO.itemCode,
							itemName: itemMasterDTO.itemName,
							quantityInStock: itemMasterDTO.quantityInStock,
						};
					})
				};
			},
		},
		formatSelection: function(item) {
			return item.itemCode + " : " + item.itemName;
		},
		formatResult: function(item) {
			return item.itemCode;
		}
	});

	$("#itemCode").on("change", function() {
		var selectedOption = $(this).select2('data')[0];

		if (selectedOption) {
			selectedValues = {
				productCode: selectedOption.id,
			};

			$("#itemName").val(selectedValues.productCode);

			$.ajax({
				url: "getAllRawItemsForSubFinalProductByProductCode",
				method: "GET",
				data: { productCode: selectedOption.id },
				success: function(data) {
					if (data.payload && Array.isArray(data.payload)) {
						var itemDetailsCell = document.getElementById('item-details');
						itemDetailsCell.innerHTML = '';

						data.payload.forEach(function(entry) {
							var entryList = document.createElement("ul");
							var entityListItem = document.createElement("li");
							var entityList = document.createElement("ol");

							var propertyMap = {
								"itemCode": "Item Code",
								"itemName": "Item Name",
								"itemQuantity": "Item Quantity"
							};

							for (var key in propertyMap) {
								if (propertyMap.hasOwnProperty(key)) {

									var listItem = document.createElement("li");
									var propertyName = document.createElement("strong");
									propertyName.textContent = propertyMap[key] + ": ";
									listItem.appendChild(propertyName);
									var propertyValue = document.createElement("span");
									propertyValue.textContent = entry[key];

									propertyName.appendChild(propertyValue);
									entityList.appendChild(listItem);
								}
							}

							entityListItem.appendChild(entityList);
							entryList.appendChild(entityListItem);

							itemDetailsCell.appendChild(entryList);
						});
					}
				},
				error: function(xhr, status, error) {
					// handle error
				}
			});



		}
	});

	$("#itemCode").on("change", function() {
		var selectedOption = $(this).select2('data')[0];

		if (selectedOption) {
			selectedValues = {
				itemCode: selectedOption.id,
				itemName: selectedOption.itemName,
				quantityInStock: selectedOption.quantityInStock,
			};

			$("#itemName").val(selectedValues.itemName);
			$("#quantityInStock").val(selectedValues.quantityInStock);
		}
	});

	$("#itemCode").on("input", function() {
	});
});

/***Render suggestions for Dynamic Row Creation***/

function initializeSelect2(inputId, i) {
	$(`#${inputId}`).select2({
		minimumInputLength: 1,
		placeholder: " ",
		dropdownAutoWidth: true,
		width: $(".col-md-2").first().outerWidth(),

		ajax: {
			url: "getItemByConsmptionType",
			dataType: 'json',
			data: function(params) {
				return {
					itemCode: params.term
				};
			},
			processResults: function(data) {
				return {
					results: $.map(data.payload, function(itemMasterDTO) {
						return {
							id: itemMasterDTO.itemCode,
							text: itemMasterDTO.itemCode + " : " + itemMasterDTO.itemName,
							itemCode: itemMasterDTO.itemCode,
							itemName: itemMasterDTO.itemName,
							quantityInStock: itemMasterDTO.quantityInStock,
						}
					})
				};
			},
		},
		formatSelection: function(item) {
			return item.itemCode + " : " + item.itemName;
		},
		formatResult: function(item) {
			return item.itemCode + " : " + item.itemName;
		}
	});

	$(`#${inputId}`).on("change", function() {
		var selectedOption = $(this).select2('data')[0];

		if (selectedOption) {
			selectedValues = {
				productCode: selectedOption.id,
			};

			$(`#${inputId}`).val(selectedValues.productCode);

			$.ajax({
				url: "getAllRawItemsForSubFinalProductByProductCode",
				method: "GET",
				data: { productCode: selectedOption.id },
				success: function(data) {
					//console.log("Received data:", data); 
					if (data.payload && Array.isArray(data.payload)) {
						var itemDetailsCell = document.getElementById(`item-details_${i}`);
						//console.log("Item details cell:", itemDetailsCell);
						itemDetailsCell.innerHTML = '';

						data.payload.forEach(function(entry) {
							var entryList = document.createElement("ul");
							var entityListItem = document.createElement("li");
							var entityList = document.createElement("ol");

							var propertyMap = {
								"itemCode": "Item Code",
								"itemName": "Item Name",
								"itemQuantity": "Item Quantity"
							};

							for (var key in propertyMap) {
								if (propertyMap.hasOwnProperty(key)) {

									var listItem = document.createElement("li");
									var propertyName = document.createElement("strong");
									propertyName.textContent = propertyMap[key] + ": ";
									listItem.appendChild(propertyName);
									var propertyValue = document.createElement("span");
									propertyValue.textContent = entry[key];

									propertyName.appendChild(propertyValue);
									entityList.appendChild(listItem);
								}
							}

							entityListItem.appendChild(entityList);
							entryList.appendChild(entityListItem);

							itemDetailsCell.appendChild(entryList);
						});
					}
				},
				error: function(xhr, status, error) {
					// handle error
				}
			});


		}
	});

	// Change event handler
	$(`#${inputId}`).on("change", function() {
		var selectedOption = $(this).select2('data')[0];

		if (selectedOption) {
			$(`#itemName_${i}`).val(selectedOption.itemName);
			$(`#quantityInStock_${i}`).val(selectedOption.quantityInStock);
		}
	});
}

/******** SAVE LOGS IN PRODUCTION ENTITY *********/
function saveData() {
	var tableBody = document.querySelector('#example tbody');
	var rows = tableBody.getElementsByTagName('tr');
	var itemList = [];

	var isAllFieldsFilled = true;

	for (var i = 0; i < rows.length; i++) {
		var row = rows[i];
		var itemData = collectRowData(row);
		if (!itemData) {
			isAllFieldsFilled = false;
			break;
		}
		itemList.push(itemData);
	}

	//alert(JSON.stringify(itemList));

	if (isAllFieldsFilled) {
		if (itemList.length > 0) {
			showLoadingIndicator();
			saveMultipleItems(itemList);
		} else {
			Swal.fire({
				icon: 'warning',
				title: 'Warning',
				text: 'No items to save.',
			});
		}
	} else {
		alert('Not all fields are filled.');
	}
}

function collectRowData(row) {
	try {
		var itemCodeInput = row.cells[0].querySelector('select');
		var itemNameInput = row.cells[1].querySelector('input');
		var quantityInStockInput = row.cells[3].querySelector('input');
		var quantityInput = row.cells[4].querySelector('input');

		if (!itemCodeInput) {
			console.error('Item Code is null');
			return null;
		}
		if (!itemNameInput) {
			console.error('Item Name is null');
			return null;
		}
		if (!quantityInStockInput) {
			console.error('Quantity in stock is null');
			return null;
		}
		if (!quantityInput) {
			console.error('Quantity Used is null');
			return null;
		}

		// Access values only if the elements exist
		var itemCode = itemCodeInput.value;
		var itemName = itemNameInput.value;
		var quantityInStock = quantityInStockInput.value;
		var quantity = quantityInput.value;

		var productId = document.getElementById('productId').value;
		var userId = document.getElementById('userId').value;
		var productionDate = document.getElementById('productionDate').value;

		var rowData = {
			itemCode: itemCode,
			itemName: itemName,
			quantity: quantity,
			productId: productId,
			userId: userId,
			quantityInStock: quantityInStock,
			productionDate: productionDate
		};

		return rowData;
	}
	catch (error) {
		console.error('Error in collectRowData:', error);
		return null;
	}
}

function showLoadingIndicator() {
	Swal.fire({
		title: 'Saving Data',
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

function saveMultipleItems(itemList) {
	try {
		fetch('addMultipleProductsLogs', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
			},
			body: JSON.stringify(itemList),
		})
			.then(response => {
				if (!response.ok) {
					throw new Error('Network response was not ok');
				}
				return response.json();
			})
			.then(data => {
				hideLoadingIndicator();
				Swal.fire({
					icon: 'success',
					title: 'Success',
					text: 'Production Logs saved successfully!',
				})
					.then(() => {
						window.location.reload();
					});
			})
			.catch((error) => {
				hideLoadingIndicator();
				Swal.fire({
					icon: 'error',
					title: 'Error',
					text: 'An error occurred while saving data. Please try again.',
				});
			});
	} catch (e) {
		hideLoadingIndicator();
		Swal.fire({
			icon: 'error',
			title: 'Exception',
			text: 'An exception occurred. Please check your input and try again.',
		});
	}
}
