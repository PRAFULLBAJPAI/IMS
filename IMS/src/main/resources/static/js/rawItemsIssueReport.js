/**
 * 
 */
/******** REFRESH PAGE *********/
function refreshPage() {
	document.body.classList.add('hidden');
	setTimeout(function() {
		location.reload();
	}, 500);
}

/********** SUGGESTIONS **********/

$(document).ready(function() {
	var selectedValues = {};

	var itemCodeColumnWidth = $(".col-md-2").first().outerWidth();

	$("#itemCode").select2({
		minimumInputLength: 1,
		placeholder: "Enter Item Code",
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
				itemCode: selectedOption.id,
				itemName: selectedOption.itemName,
				quantityInStock: selectedOption.quantityInStock,
			};

			$("#itemName").val(selectedValues.itemName);
			$("#quantityInStock").val(selectedValues.quantityInStock);

			$('#example').DataTable().search(selectedValues.itemCode).draw();
		}
	});

	$("#itemCode").on("input", function() {
		$('#example').DataTable().search('').draw();
	});
});


function sortTable() {
	var table, rows, switching, i, x, y, shouldSwitch;
	table = document.getElementById("example");
	switching = true;
	while (switching) {
		switching = false;
		rows = table.getElementsByTagName("tr");
		for (i = 1; i < (rows.length - 1); i++) {
			shouldSwitch = false;
			x = rows[i].getElementsByTagName("td")[2];
			y = rows[i + 1].getElementsByTagName("td")[2];
			if (new Date(x.innerHTML) < new Date(y.innerHTML)) {
				shouldSwitch = true;
				break;
			}
		}
		if (shouldSwitch) {
			rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
			switching = true;
		}
	}
}
window.onload = function() {
	sortTable();
};

