/**
 * JS for ITEM PURCHASE
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

function addNewItemPurchase() {
    var tableBody = document.querySelector('#example tbody');
    var newRow = tableBody.insertRow();
    var itemCodeCell = newRow.insertCell();
    var itemNameCell = newRow.insertCell();
    var quantityInStockCell = newRow.insertCell();
    var itemQuantityCell = newRow.insertCell();
    var unitPriceCell = newRow.insertCell();
    var itemPriceCell = newRow.insertCell();
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
    itemQuantityCell.innerHTML = '<input type="text" class="form-control form-control-sm" />';
    unitPriceCell.innerHTML = `<input type="text" id="unitPrice_${rowCounter}" class="form-control form-control-sm" />`;
    itemPriceCell.innerHTML = '<input type="text" class="form-control form-control-sm" />';
    actionsCell.innerHTML = `
        <button type="button" class="btn btn-danger btn-sm ml-2" onclick="removeItemRow(this);">
            <i class="fa-regular fa-trash-can fa-lg"></i>
        </button>`;

    var addNewButton = document.createElement('button');
    addNewButton.setAttribute('type', 'button');
    addNewButton.classList.add('btn', 'btn-sm', 'ml-2');
    addNewButton.style.background = '#6595ff';
    addNewButton.style.color = '#ffff';
    addNewButton.setAttribute('onclick', 'addNewItemPurchase();');
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
	var itemQuantityInput = row.cells[3].querySelector('input');
	var unitPriceInput = row.cells[4].querySelector('input');
	var itemPriceInput = row.cells[5].querySelector('input');

	var itemCode = itemCodeInput.value;
	var itemName = itemNameInput.value;
	var quantityInStock = quantityInStockInput.value;
	var itemQuantity = itemQuantityInput.value;
	var unitPrice = unitPriceInput.value;
	var itemPrice = itemPriceInput.value.trim();

	var wareHouse = document.getElementById('wareHouse').value;
	var itemSource = document.getElementById('itemSource').value;
	var recieveId = document.getElementById('recieveId').value;
	var remarks = document.getElementById('remarks').value;
	var empId = document.getElementById('empId').value;
	var recievedDate = document.getElementById('recievedDate').value;
	var invoiceNo = document.getElementById('invoiceNo').value;
	var invoiceDate = document.getElementById('invoiceDate').value;

	var rowData = {
		itemCode: itemCode,
		itemName: itemName,
		itemQuantity: itemQuantity,
		itemPrice: itemPrice,
		unitPrice: unitPrice,
		itemSource: itemSource,
		wareHouse: wareHouse,
		recieveId: recieveId,
		recievedDate: recievedDate,
		invoiceNo: invoiceNo,
		invoiceDate: invoiceDate,
		empId: empId,
		remarks: remarks,
		quantityInStock: quantityInStock
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
		fetch('addMultipleReceivedItem', {
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
					text: 'Received Items saved successfully!',
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
							unitPrice: itemMasterDTO.unitPrice
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
				unitPrice: selectedOption.unitPrice
			};

			$("#itemName").val(selectedValues.itemName);
			$("#quantityInStock").val(selectedValues.quantityInStock);
			$("#unitPrice").val(selectedValues.unitPrice);
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
				console.log("Requesting data for term:", params.term);
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
							unitPrice: itemMasterDTO.unitPrice
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
			var selectedValues = {
				itemCode: selectedOption.id,
				itemName: selectedOption.itemName,
				quantityInStock: selectedOption.quantityInStock,
				unitPrice: selectedOption.unitPrice
			};

			$(`#itemName_${i}`).val(selectedValues.itemName);
			$(`#quantityInStock_${i}`).val(selectedValues.quantityInStock);
			$(`#unitPrice_${i}`).val(selectedValues.unitPrice);
		}
	});
}

/********* VALIDATIONS **********/
function validateFields() {
    var isValid = true;

    var recievedDate = document.getElementById('recievedDate').value;
    if (recievedDate === "") {
        Swal.fire({
            icon: 'info',
            title: 'Validation Error',
            text: 'Please enter the Receive Date.',
        });
        isValid = false;
        return isValid;
    }

    var invoiceNo = document.getElementById('invoiceNo').value;
    if (invoiceNo === "") {
        Swal.fire({
            icon: 'info',
            title: 'Validation Error',
            text: 'Please select the Invoice No.',
        });
        isValid = false;
        return isValid;
    }

    var invoiceDate = document.getElementById('invoiceDate').value;
    if (invoiceDate === "") {
        Swal.fire({
            icon: 'info',
            title: 'Validation Error',
            text: 'Please select the Invoice Date.',
        });
        isValid = false;
        return isValid;
    }
    
    var itemSource = document.getElementById('itemSource').value;
    if (itemSource === "") {
        Swal.fire({
            icon: 'info',
            title: 'Validation Error',
            text: 'Please select the Item Source.',
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
    
    var wareHouse = document.getElementById('wareHouse').value;
    if (wareHouse === "") {
        Swal.fire({
            icon: 'info',
            title: 'Validation Error',
            text: 'Please enter the Warehouse.',
        });
        isValid = false;
        return isValid;
    }

    var tableBody = document.querySelector('#example tbody');
    var rows = tableBody.getElementsByTagName('tr');

    for (var i = 0; i < rows.length; i++) {
        var row = rows[i];
        var itemCodeInput = row.cells[0].querySelector('select');
        var itemQuantityInput = row.cells[3].querySelector('input');
        var itemPriceInput = row.cells[5].querySelector('input');

        if (itemCodeInput.value === "---select---") {
            Swal.fire({
                icon: 'info',
                title: 'Validation Error',
                text: 'Please select an Item Code for each row.',
            });
            isValid = false;
            return isValid;
        }
        
        if (itemPriceInput.value === "") {
            Swal.fire({
                icon: 'info',
                title: 'Validation Error',
                text: 'Please select an Item Price for each row.',
            });
            isValid = false;
            return isValid;
        }

        if (itemQuantityInput.value === "") {
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







