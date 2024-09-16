/**
 * 
**/


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

function addNewItemIssue() {
	var tableBody = document.querySelector('#example tbody');
	var newRow = tableBody.insertRow();
	var itemCodeCell = newRow.insertCell();
	var itemNameCell = newRow.insertCell();
	var quantityInStockCell = newRow.insertCell();
	var quantityCell = newRow.insertCell();
	var actionsCell = newRow.insertCell();

	var inputId = `itemCode_${rowCounter}`;
	itemCodeCell.innerHTML = `
        <div class="input-group">
            <select id="${inputId}" class="input-11 form-control form-control-sm select2">
                <option>---select---</option>
            </select>
        </div>
    `;

	initializeSelect2ForRow(rowCounter);

	itemNameCell.innerHTML = `<input type="text" id="itemName_${rowCounter}" class="form-control form-control-sm" />`;
	quantityInStockCell.innerHTML = `<input type="text" id="quantityInStock_${rowCounter}" class="form-control form-control-sm" />`;
	quantityCell.innerHTML = '<input type="text" class="form-control form-control-sm" />';
	actionsCell.innerHTML = `
        <button type="button" class="btn btn-danger btn-sm ml-2" onclick="removeItemRow(this);">
            <i class="fa-regular fa-trash-can fa-lg"></i>
        </button>`;

	var addNewButton = document.createElement('button');
	addNewButton.setAttribute('type', 'button');
	addNewButton.classList.add('btn', 'btn-sm', 'ml-2');
	addNewButton.style.background = '#6595ff';
	addNewButton.style.color = '#ffff';
	addNewButton.setAttribute('onclick', 'addNewItemIssue();');
	addNewButton.innerHTML = '<i class="fa-solid fa-square-plus fa-lg"></i>';

	var addNewCell = newRow.insertCell();
	addNewCell.appendChild(addNewButton);

	rowCounter++;
}

function removeItemRow(button) {
	var row = button.parentNode.parentNode;
	row.parentNode.removeChild(row);
}

function saveData() {
    var isValid = validateFields();

    if (!isValid) {
        return; 
    }

    var tableBody = document.querySelector('#example tbody');
    var rows = tableBody.getElementsByTagName('tr');
    var itemList = [];

    for (var i = 0; i < rows.length; i++) {
        var row = rows[i];
        var itemData = collectRowData(row);
        itemList.push(itemData);
    }

    if (itemList.length > 0) {
        showLoadingIndicator();
        saveMultipleItems(itemList);

        var consumptionType = document.getElementById('consumptionType').value;

        if (consumptionType === "SUBPRODUCT" || consumptionType === "FINAL PRODUCT") {
        }
    } else {
        Swal.fire({
            icon: 'warning',
            title: 'Warning',
            text: 'No items to save.',
        });
    }
}

function collectRowData(row) {
    var itemCodeInput = row.cells[0].querySelector('select');
    var itemNameInput = row.cells[1].querySelector('input');
    var quantityInStockInput = row.cells[2].querySelector('input');
    var quantityInput = row.cells[3].querySelector('input');

    var itemCode = itemCodeInput.value;
    var itemName = itemNameInput.value;
    var quantityInStock = quantityInStockInput.value;
    var quantity = quantityInput.value;

    var dispatchId = document.getElementById('dispatchId').value;
    var clientId = document.getElementById('clientId').value;
    var dispatchDate = document.getElementById('dispatchDate').value;
    var invoiceNo = document.getElementById('invoiceNo').value;
    var invoiceDate = document.getElementById('invoiceDate').value;
    var remarks = document.getElementById('remarks').value;

    var rowData = {
        itemCode: itemCode,
        itemName: itemName,
        quantityInStock: quantityInStock,
        quantity: quantity,
        dispatchId: dispatchId,
        clientId: clientId,
        dispatchDate: dispatchDate,
        invoiceNo: invoiceNo,
        invoiceDate: invoiceDate,
        remarks: remarks,
    };
    return rowData;
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
		fetch('addMultipleDispatchLogs', {
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
					text: 'Item dispatched logs saved successfully!',
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
			url: "getItemByItemCode",
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
			return item.itemCode + " : " + item.itemName;
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
			url: "getItemByItemCode",
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

	// Change event handler
	$(`#${inputId}`).on("change", function() {
		var selectedOption = $(this).select2('data')[0];

		if (selectedOption) {
			$(`#itemName_${i}`).val(selectedOption.itemName);
			$(`#quantityInStock_${i}`).val(selectedOption.quantityInStock);
		}
	});
}

/*** ADD NEW ITEM AS SUBPRODUCT OR FINAL PRODUCT IN THE ITEM MASTER ***/

function saveItemMasterData() {
	var dispatchId = document.getElementById('dispatchId').value;
	var clientId = document.getElementById('clientId').value;
	var remarks = document.getElementById('remarks').value;
	var dispatchDate = document.getElementById('dispatchDate').value;
	var invoiceNo = document.getElementById('invoiceNo').value;
	var invoiceDate = document.getElementById('invoiceDate').value;

	var requestBody = {
		dispatchId: dispatchId,
		clientId: clientId,
		remarks: remarks,
		dispatchDate: dispatchDate,
		invoiceNo: invoiceNo,
		invoiceDate: invoiceDate
	};
}

/********* VALIDATIONS **********/
function validateFields() {
    var isValid = true;
    
    var dispatchId = document.getElementById('dispatchId').value;
    if (dispatchId === "") {
        Swal.fire({
            icon: 'info',
            title: 'Validation Error',
            text: 'Please Enter Dispatch Id.',
        });
        isValid = false;
        return isValid;
    }

    var clientId = document.getElementById('clientId').value;
    if (clientId === "") {
        Swal.fire({
            icon: 'info',
            title: 'Validation Error',
            text: 'Please select the Client Id',
        });
        isValid = false;
        return isValid;
    }

    var remarks = document.getElementById('remarks').value;
    if (remarks === "") {
        Swal.fire({
            icon: 'info',
            title: 'Validation Error',
            text: 'Please enter the Remarks.',
        });
        isValid = false;
        return isValid;
    }
    
    var dispatchDate = document.getElementById('dispatchDate').value;
    if (dispatchDate === "") {
        Swal.fire({
            icon: 'info',
            title: 'Validation Error',
            text: 'Please Enter Dispatch Date',
        });
        isValid = false;
        return isValid;
    }
    
    var invoiceNo = document.getElementById('invoiceNo').value;
    if (invoiceNo === "") {
        Swal.fire({
            icon: 'info',
            title: 'Validation Error',
            text: 'Please Enter Invoice No.',
        });
        isValid = false;
        return isValid;
    }
    
    var invoiceDate = document.getElementById('invoiceDate').value;
    if (invoiceDate === "") {
        Swal.fire({
            icon: 'info',
            title: 'Validation Error',
            text: 'Please Enter Invoice Date',
        });
        isValid = false;
        return isValid;
    }

    var tableBody = document.querySelector('#example tbody');
    var rows = tableBody.getElementsByTagName('tr');

    for (var i = 0; i < rows.length; i++) {
        var row = rows[i];
        var itemCodeInput = row.cells[0].querySelector('select');
        var quantityInput = row.cells[3].querySelector('input');
        

        if (itemCodeInput.value === "---select---") {
            Swal.fire({
                icon: 'info',
                title: 'Validation Error',
                text: 'Please select an Item Code for each row.',
            });
            isValid = false;
            return isValid;
        }

        if (quantityInput.value === "") {
            Swal.fire({
                icon: 'info',
                title: 'Validation Error',
                text: 'Please enter the Item Quantity for each row.',
            });
            isValid = false;
            return isValid;
        }
        
    }

    return isValid;
}