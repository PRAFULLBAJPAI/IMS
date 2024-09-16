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
//alert(isItemCode);
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

	var issueId = document.getElementById('issueId').value;
	var issueDate = document.getElementById('issueDate').value;
	var consumptionType = document.getElementById('consumptionType').value;
	var consumptionFor = document.getElementById('consumptionFor').value;
	var remark = document.getElementById('remark').value;
	var empId = document.getElementById('empId').value;
	var productCode = document.getElementById('productCode').value;
	var productName = document.getElementById('productName').value;
	var modifiedBy = document.getElementById('modifiedBy').value;

	var rowData = {
		itemCode: itemCode,
		itemName: itemName,
		itemQuantity: itemQuantity,
		issueId: issueId,
		issueDate: issueDate,
		consumptionType: consumptionType,
		consumptionFor: consumptionFor,
		empId: empId,
		remark: remark,
		quantityInStock: quantityInStock,
		minimumStock: minimumStock,
		productCode: productCode,
		productName: productName,
		itemIssueId:0,
		modifiedBy: modifiedBy
	};
	//alert("Data Collected:---> " + JSON.stringify(rowData));

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
		fetch('issueMultipleItems', {
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
				//alert("Data sent in the request:---> " + JSON.stringify(data);
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

    // Validate Issue Date
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

    // Validate Consumption Type
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

    // Validate Consumption For
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

    // Validate Remark
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

    // Validate Item Details in Table
    var tableBody = document.querySelector('#example tbody');
    var rows = tableBody.getElementsByTagName('tr');

    for (var i = 0; i < rows.length; i++) {
        var row = rows[i];
        var itemCodeInput = row.cells[0].querySelector('select');
        var quantityInStockInput = row.cells[2].querySelector('input');
        var minimumStockInput = row.cells[3].querySelector('input');
        var itemQuantityInput = row.cells[4].querySelector('input');
        
        // Validate Item Code
        if (itemCodeInput.value === "---select---") {
            Swal.fire({
                icon: 'info',
                title: 'Validation Error',
                text: 'Please select an Item Code for each row.',
            });
            isValid = false;
            return isValid;
        }

        // Validate Item Quantity
        if (itemQuantityInput.value === "") {
            Swal.fire({
                icon: 'info',
                title: 'Validation Error',
                text: 'Please enter the Item Quantity for each row.',
            });
            isValid = false;
            return isValid;
        }
        
        // Validate Quantity in Stock against Minimum Stock
        var quantityInStock = parseInt(quantityInStockInput.value);
        var minimumStock = parseInt(minimumStockInput.value);
		/*
        if (quantityInStock < minimumStock) {
            Swal.fire({
                icon: 'info',
                title: 'Validation Error',
                text: 'Quantity in Stock should be greater than or equal to Minimum Stock for each item.',
            });
            isValid = false;
            return isValid;
        }
		*/
    }

    return isValid;
}


/*------------------------------------IMPORT ITEM ISSUE----------------------------------*/
function importItemIssue() {
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
    formData.append('itemIssueFile', file);

    fetch('importItemIssue', {
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

/******** REFRESH PAGE *********/
function refreshPage() {
	document.body.classList.add('hidden');
	setTimeout(function() {
		location.reload();
	}, 500);
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
		
		fetch(`getProductDetailsByProductCode?productCode=${searchTerm}`)
			.then(response => response.json())
			.then(data => {
				
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
	const matchingSuggestions = lastFetchedData.filter(item => item.productCode.toLowerCase().includes(searchTerm));
	

	if (matchingSuggestions.length > 0) {
		suggestionDropdown.innerHTML = '';
		matchingSuggestions.forEach(suggestion => {
			const suggestionItem = document.createElement('div');
			suggestionItem.classList.add('suggestionItem');
			suggestionItem.textContent = suggestion.productCode;
			suggestionItem.innerHTML = `${suggestion.productCode} : ${suggestion.productName}`;
			suggestionItem.addEventListener('click', () => {
				searchInput.value = suggestion.productCode;
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

/**
 * FETCH DATA TO EDIT SUB-PRODUCT/ FINAL PRODUCT DETAILS
*/
const searchButton = document.getElementById('productCodeSeachButton');
searchButton.addEventListener('click', function() {
    const productCode = document.getElementById('searchInput').value;

    fetch(`getProductDetailsByProductCode?productCode=${encodeURIComponent(productCode)}`)
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(data => {
        document.getElementById('issueId').value = data.payload[0].issueId;
        document.getElementById('productName').value = data.payload[0].productName;
        document.getElementById('productCode').value = data.payload[0].productCode;
        document.getElementById('issueDate').value = data.payload[0].issueDate;
        document.getElementById('consumptionType').value = data.payload[0].consumptionType;
        document.getElementById('consumptionFor').value = data.payload[0].consumptionFor;
        document.getElementById('remark').value = data.payload[0].remark;

        // Populate item details table
        const itemDetailsTableBody = document.getElementById('example').getElementsByTagName('tbody')[0];
        itemDetailsTableBody.innerHTML = '';

        data.payload.forEach(item => {
            const newRow = itemDetailsTableBody.insertRow();
            
            // Create and append input fields and select dropdowns to each cell
            const itemCodeCell = newRow.insertCell();
            const itemCodeSelect = document.createElement('select');
            itemCodeSelect.className = 'input-11 form-control select2';
            itemCodeSelect.id = 'itemCode';
            itemCodeSelect.name = 'itemCode';
            itemCodeCell.appendChild(itemCodeSelect);
            
            const itemNameCell = newRow.insertCell();
            const itemNameSelect = document.createElement('select');
            itemNameSelect.className = 'input-11 form-control select2';
            itemNameSelect.id = 'itemName';
            itemNameSelect.name = 'itemName';
            itemNameCell.appendChild(itemNameSelect);
            
            const quantityInStockCell = newRow.insertCell();
            const quantityInStockInput = document.createElement('input');
            quantityInStockInput.type = 'text';
            quantityInStockInput.className = 'form-control form-control-sm';
            quantityInStockInput.id = 'quantityInStock';
            // Set value for quantity in stock
            quantityInStockInput.value = item.quantityInStock;
            quantityInStockCell.appendChild(quantityInStockInput);
            
            const minimumStockCell = newRow.insertCell();
            const minimumStockInput = document.createElement('input');
            minimumStockInput.type = 'text';
            minimumStockInput.className = 'form-control form-control-sm';
            minimumStockInput.id = 'minimumStock';
            // Set value for minimum stock
            minimumStockInput.value = item.minimumStock;
            minimumStockCell.appendChild(minimumStockInput);
            
            const itemQuantityCell = newRow.insertCell();
            const itemQuantityInput = document.createElement('input');
            itemQuantityInput.type = 'text';
            itemQuantityInput.className = 'form-control form-control-sm';
            itemQuantityInput.id = 'itemQuantity';
            // Set value for item quantity
            itemQuantityInput.value = item.itemQuantity;
            itemQuantityCell.appendChild(itemQuantityInput);
            
            const deleteButtonCell = newRow.insertCell();
            deleteButtonCell.innerHTML = `
                <button type="button" class="btn btn-danger btn-sm ml-2" onclick="removeItemRow(this);">
                    <i class="fa-regular fa-trash-can fa-lg"></i>
                </button>
            `;
            
            const addButtonCell = newRow.insertCell();
            addButtonCell.innerHTML = `
                <button type="button" class="btn btn-sm ml-2" onclick="addNewItemIssue();" style="background: #6595ff; color: #ffff;">
                    <i class="fa-solid fa-square-plus fa-lg"></i>
                </button>
            `;

            // Populate item code select dropdown
            const itemCodeSelectElement = newRow.querySelector('#itemCode');
            const itemCodeOption = document.createElement('option');
            itemCodeOption.value = item.itemCode;
            itemCodeOption.textContent = item.itemCode;
            itemCodeSelectElement.appendChild(itemCodeOption);

            // Populate item name select dropdown
            const itemNameSelectElement = newRow.querySelector('#itemName');
            const itemNameOption = document.createElement('option');
            itemNameOption.value = item.itemName;
            itemNameOption.textContent = item.itemName;
            itemNameSelectElement.appendChild(itemNameOption);
        });

        // Refresh select2 to reflect changes
        $('.select2').select2();
    })
    .catch(error => {
        console.error('Error fetching data:', error);
    });
});

/*************************************************************************************************** */
/* SEARCH BAR FOR ITEM NAME */
/*************************************************************************************************** */
const searchInputByName = document.getElementById('searchInputByName');
const suggestionDropdownForName = document.getElementById('suggestionDropdownForName');
let lastSearchTermByName = '';
let lastFetchedDataByName = [];
searchInputByName.addEventListener('input', function() {
	const searchTermByName = searchInputByName.value.toLowerCase();
	if (searchTermByName !== lastSearchTermByName) {
		fetch(`getProductDetailsByProductName?productName=${searchTermByName}`)
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
		item.productName.toLowerCase().includes(searchTermByName) || item.productCode.toLowerCase().includes(searchTermByName)
	);
	if (matchingSuggestions.length > 0) {
		suggestionDropdownForName.innerHTML = '';
		matchingSuggestions.forEach(suggestion => {
			const suggestionItem = document.createElement('div');
			suggestionItem.classList.add('suggestionItem');
			suggestionItem.innerHTML = `${suggestion.productCode} : ${suggestion.productName}`;
			suggestionItem.addEventListener('click', () => {
								
				searchInputByName.value = suggestion.productName;
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

/**
 * EDIT SUBPRODUCT DEFINATION
*/

function updateData() {
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
        updateSubproductDefination(itemList);

    } else {
        Swal.fire({
            icon: 'warning',
            title: 'Warning',
            text: 'No items to save.',
        });
    }
}

function updateSubproductDefination(itemList) {
    try {
        fetch('updateSubproductDefination', {
            method: 'PUT',
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
                // Check for success response
                if (data && data.statusCode === 200) {
					alert(data.payload);
                    hideLoadingIndicator();
                    Swal.fire({
                        icon: 'success',
                        title: 'Success',
                        text: 'Subproduct Defination updated successfully!',
                    })
                    .then(() => {
                        window.location.reload();
                    });
                } else {
                    // Handle server error
                    throw new Error(data.message || 'Unknown error');
                }
            })
            .catch((error) => {
                hideLoadingIndicator();
                Swal.fire({
                    icon: 'error',
                    title: 'Error',
                    text: error.message || 'An error occurred while saving data. Please try again.',
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
