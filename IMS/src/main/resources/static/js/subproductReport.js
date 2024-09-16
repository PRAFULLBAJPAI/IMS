/********** SUGGESTIONS **********/

$(document).ready(function() {
	var selectedValues = {};

	var itemCodeColumnWidth = $(".col-md-2").first().outerWidth();

	$("#itemCode").select2({
		minimumInputLength: 1,
		placeholder: "Search by Item Code",
		dropdownAutoWidth: true,
		width: itemCodeColumnWidth,
		ajax: {
			url: "getAllSubProductsByPattern",
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

function refreshPage() {
	window.location.reload();
}