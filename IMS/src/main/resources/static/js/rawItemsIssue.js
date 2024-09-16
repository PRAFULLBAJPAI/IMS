/**
 * for ITEMCODE
**/
let rowCounter = 1;


function initializeSelect2ForRowCounter(index) {
	var inputId = `itemCode_${index}`;
	initializeSelect2(inputId, index);
}

document.addEventListener('DOMContentLoaded', function() {
	const initialRowCount = $('#example tbody tr').length;
	for (let i = 0; i <= initialRowCount; i++) {
		initializeSelect2ForRowCounter(i);
	}
});

function addNewItemIssue(isItemCode) {
    var tableBody = document.querySelector('#example tbody');
    var newRow = tableBody.insertRow();
    var itemCodeCell = newRow.insertCell();
    var itemNameCell = newRow.insertCell();
    var quantityInStockCell = newRow.insertCell();
    var minimumStockCell = newRow.insertCell();
    var itemQuantityCell = newRow.insertCell();
    var actionsCell = newRow.insertCell();

    if (isItemCode) {

        var inputId = `itemCode_${rowCounter}`;
        
        itemCodeCell.innerHTML = `
            <div class="input-group">
                <select id="${inputId}" class="input-11 form-control form-control-sm select2">
                    <option>---select---</option>
                </select>
            </div>
        `;

        initializeSelect2ForRowCounter(rowCounter);
        
        itemNameCell.innerHTML = `<select id="itemName_${rowCounter}" class="form-control form-control-sm"></select>`;
    } else {
        var inputId = `itemName_${rowCounterItemName}`;
        var inputCode = `itemCode_${rowCounter}`;
        itemNameCell.innerHTML = `
            <div class="input-group">
                <select id="${inputId}" class="input-11 form-control form-control-sm select2">
                    <option>---select---</option>
                </select>
            </div>
        `;

        initializeSelect2ForRowIndex(rowCounterItemName);
        itemCodeCell.innerHTML = `
            <div class="input-group">
                <select id="${inputCode}" class="input-11 form-control form-control-sm select2">
                    <option>---select---</option>
                </select>
            </div>
        `;

        initializeSelect2ForRowCounter(rowCounter);

    }

    quantityInStockCell.innerHTML = `<input type="text" id="quantityInStock_${isItemCode ? rowCounter : rowCounterItemName}" class="form-control form-control-sm" />`;
    minimumStockCell.innerHTML = `<input type="text" id="minimumStock_${isItemCode ? rowCounter : rowCounterItemName}" class="form-control form-control-sm" />`;
    itemQuantityCell.innerHTML = '<input type="text" class="form-control form-control-sm" />';
    actionsCell.innerHTML = `
        <button type="button" class="btn btn-danger btn-sm ml-2" onclick="removeItemRow(this);">
            <i class="fa-regular fa-trash-can fa-lg"></i>
        </button>`;

    var addNewButton = document.createElement('button');
    addNewButton.setAttribute('type', 'button');
    addNewButton.classList.add('btn', 'btn-sm', 'ml-2');
    addNewButton.style.background = '#6595ff';
    addNewButton.style.color = '#ffff';
    addNewButton.setAttribute('onclick', 'addNewItemIssue(' + isItemCode + ');');
    addNewButton.innerHTML = '<i class="fa-solid fa-square-plus fa-lg"></i>';

    var addNewCell = newRow.insertCell();
    addNewCell.appendChild(addNewButton);

    if (isItemCode) {
        rowCounter++;
    } else {
		rowCounter++;
        rowCounterItemName++;
    }
}

/**
 * for ITEMNAME
**/
let rowCounterItemName = 1;

function initializeSelect2ForRowIndex(index) {
	var inputId = `itemName_${index}`;
	initializeSelect2ItemName(inputId, index);
}

document.addEventListener('DOMContentLoaded', function() {
	const initialRowCount = $('#example tbody tr').length;
	for (let i = 0; i <= initialRowCount; i++) {
		initializeSelect2ForRowIndex(i);
	}
});

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
	var itemNameInput = row.cells[1].querySelector('select');
	var quantityInStockInput = row.cells[2].querySelector('input');
	var minimumStockInput = row.cells[3].querySelector('input');
	var itemQuantityInput = row.cells[4].querySelector('input');

	var itemCode = itemCodeInput.value;
	var itemName = itemNameInput.value;
	var quantityInStock = quantityInStockInput.value;
	var minimumStock = minimumStockInput.value;
	var itemQuantity = itemQuantityInput.value;

	var rawIssueId = document.getElementById('rawIssueId').value;
	var issueDate = document.getElementById('issueDate').value;
	var consumptionType = document.getElementById('consumptionType').value;
	var consumptionFor = document.getElementById('consumptionFor').value;
	var remark = document.getElementById('remark').value;
	var empId = document.getElementById('empId').value;

	var rowData = {
		itemCode: itemCode,
		itemName: itemName,
		itemQuantity: itemQuantity,
		rawIssueId: rawIssueId,
		issueDate: issueDate,
		consumptionType: consumptionType,
		consumptionFor: consumptionFor,
		empId: empId,
		remark: remark,
		quantityInStock: quantityInStock,
		minimumStock: minimumStock,
		itemIssueId:0
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
		fetch('issueMultipleWithBOM', {
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
					text: 'Issued Items saved successfully!',
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

/***Render suggestions for HTML Code for itemcode***/
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
                            text: itemMasterDTO.itemCode,
                            itemName: itemMasterDTO.itemName,
                            quantityInStock: itemMasterDTO.quantityInStock,
                            minimumStock: itemMasterDTO.minimumStock
                        };
                    })
                };
            },
        },
        formatSelection: function(item) {
            return item.itemCode;
        },
        formatResult: function(item) {
            return item.itemCode;
        }
    });
$("#itemCode").on("change", function() {
    var selectedOption = $(this).select2('data')[0];
    if (selectedOption) {
        $("#quantityInStock").val(selectedOption.quantityInStock);
        $("#minimumStock").val(selectedOption.minimumStock);

       
        $("#itemName").val(null).trigger('change.select2');

      
        $("#itemName").append(new Option(selectedOption.itemName, selectedOption.itemName, true, true));
        $("#itemName").trigger('change.select2');
    }
});
    $("#itemCode").on("input", function() {
     
    });
});

/**Render suggestions for Dynamic Row Creation for itemcode**/
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
                            text: itemMasterDTO.itemCode,
                            itemCode: itemMasterDTO.itemCode,
                            itemName: itemMasterDTO.itemName,
                            quantityInStock: itemMasterDTO.quantityInStock,
                            minimumStock: itemMasterDTO.minimumStock
                        }
                    })
                };
            },
        },
        formatSelection: function(item) {
            return item.itemCode;
        },
        formatResult: function(item) {
            return item.itemCode;
        }
    });

    $(`#${inputId}`).on("change", function() {
        var selectedOption = $(this).select2('data')[0];

        if (selectedOption) {
            $(`#itemName_${i}`).empty(); 
            $(`#itemName_${i}`).append($('<option>', { 
                value: selectedOption.itemName,
                text : selectedOption.itemName 
            }));
            $(`#quantityInStock_${i}`).val(selectedOption.quantityInStock);
            $(`#minimumStock_${i}`).val(selectedOption.minimumStock);
        }
    });
}

/***Render suggestions for HTML Code for ITEMNAME***/
$(document).ready(function() {
    var selectedValues = {};

    var itemCodeColumnWidth = $(".col-md-2").first().outerWidth();

    $("#itemName").select2({
        minimumInputLength: 1,
        placeholder: " ",
        dropdownAutoWidth: true,
        width: itemCodeColumnWidth,
        ajax: {
            url: "getItemByItemName",
            dataType: 'json',
            data: function(params) {
                return {
                    itemName: params.term
                };
            },
            processResults: function(data) {
                return {
                    results: $.map(data.payload, function(itemMasterDTO) {
                        return {
                            id: itemMasterDTO.itemName,
                            text: itemMasterDTO.itemName,
                            itemCode: itemMasterDTO.itemCode,
                            itemName: itemMasterDTO.itemName,
                            quantityInStock: itemMasterDTO.quantityInStock,
                            minimumStock: itemMasterDTO.minimumStock
                        };
                    })
                };
            },
        },
        formatSelection: function(item) {
            return item.itemName;
        },
        formatResult: function(item) {
            return item.itemName;
        }
    });
$("#itemName").on("change", function() {
    var selectedOption = $(this).select2('data')[0];
    if (selectedOption) {
        $("#quantityInStock").val(selectedOption.quantityInStock);
        $("#minimumStock").val(selectedOption.minimumStock);

       
        $("#itemCode").val(null).trigger('change.select2');

      
        $("#itemCode").append(new Option(selectedOption.itemCode, selectedOption.itemCode, true, true));
        $("#itemCode").trigger('change.select2');
    }
});
    $("#itemName").on("input", function() {
     
    });
});

/***Render suggestions for Dynamic Row Creation FOR ITEM NAME***/
function initializeSelect2ItemName(inputId, i) {
    $(`#${inputId}`).select2({
        minimumInputLength: 1,
        placeholder: " ",
        dropdownAutoWidth: true,
        width: $(".col-md-2").first().outerWidth(),

        ajax: {
            url: "getItemByItemName",
            dataType: 'json',
            data: function(params) {
                return {
                    itemName: params.term
                };
            },
            processResults: function(data) {

                return {
                    results: $.map(data.payload, function(itemMasterDTO) {
                        return {
                            id: itemMasterDTO.itemName,
                            text: itemMasterDTO.itemName,
                            itemCode: itemMasterDTO.itemCode,
                            itemName: itemMasterDTO.itemName,
                            quantityInStock: itemMasterDTO.quantityInStock,
                            minimumStock: itemMasterDTO.minimumStock
                        }
                    })
                };
            },
        },
        formatSelection: function(item) {
            return item.itemName;
        },
        formatResult: function(item) {
            return item.itemName;
        }
    });

    $(`#${inputId}`).on("change", function() {
        var selectedOption = $(this).select2('data')[0];

        if (selectedOption) {
            $(`#itemCode_${i}`).empty(); 
            $(`#itemCode_${i}`).append($('<option>', { 
                value: selectedOption.itemCode,
                text : selectedOption.itemCode 
            }));
            $(`#quantityInStock_${i}`).val(selectedOption.quantityInStock);
            $(`#minimumStock_${i}`).val(selectedOption.minimumStock);
        }
    });
}

/********* VALIDATIONS **********/
function validateFields() {
    var isValid = true;

    var issueDate = document.getElementById('issueDate').value;
    if (issueDate === "") {
        Swal.fire({
            icon: 'info',
            title: 'Validation Error',
            text: 'Please enter the Issue Date.',
        });
        isValid = false;
        return isValid;
    }

    var consumptionType = document.getElementById('consumptionType').value;
    if (consumptionType === "") {
        Swal.fire({
            icon: 'info',
            title: 'Validation Error',
            text: 'Please select the Consumption Type.',
        });
        isValid = false;
        return isValid;
    }

    var consumptionFor = document.getElementById('consumptionFor').value;
    if (consumptionFor === "") {
        Swal.fire({
            icon: 'info',
            title: 'Validation Error',
            text: 'Please select the Consumption For.',
        });
        isValid = false;
        return isValid;
    }

    var remark = document.getElementById('remark').value;
    if (remark === "") {
        Swal.fire({
            icon: 'info',
            title: 'Validation Error',
            text: 'Please enter the Remarks.',
        });
        isValid = false;
        return isValid;
    }

    var tableBody = document.querySelector('#example tbody');
    var rows = tableBody.getElementsByTagName('tr');

    for (var i = 0; i < rows.length; i++) {
        var row = rows[i];
        var itemCodeInput = row.cells[0].querySelector('select');
        var quantityInStockInput = row.cells[2].querySelector('input');
        var minimumStockInput = row.cells[3].querySelector('input');
        var itemQuantityInput = row.cells[4].querySelector('input');

        if (itemCodeInput.value === "---select---") {
            Swal.fire({
                icon: 'info',
                title: 'Validation Error',
                text: 'Please select an Item Code for each row.',
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

        if (parseInt(minimumStockInput.value) > parseInt(quantityInStockInput.value)) {
            Swal.fire({
                icon: 'info',
                title: 'Validation Error',
                text: 'Item cannot be issued. Quantity in stock is less than minimum stock.',
            });
            isValid = false;
            return isValid;
        }
    }
    return isValid;
}


