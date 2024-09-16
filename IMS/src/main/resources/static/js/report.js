function searchByItemType() {
    var itemType = $("#itemType").val();

    $.ajax({
        type: "GET",
        url: "getItemByType",
        data: { itemType: itemType },
        success: function (data) {
            console.log(data);
            if (data && data.payload) {
                var payload = data.payload;
                var table = $('#example').DataTable();
                table.clear().draw();

                if (Array.isArray(payload)) {
                    payload.forEach(function (itemMaster) {
                        var newRow = [
                            itemMaster.itemCode,
                            itemMaster.itemName,
                            itemMaster.itemType,
                            itemMaster.itemSource,
                            itemMaster.itemUom,
                            itemMaster.minimumStock,
                            itemMaster.itemGroup,
                            itemMaster.hsnCode,
                            itemMaster.warehouse,
                            itemMaster.description,
                            itemMaster.unitPrice,
                            itemMaster.quantityInStock,
                            itemMaster.status,
                            itemMaster.userId,
                            itemMaster.entryDate
                        ];
                    table.row.add(newRow).draw();
                    });
                } else {
                    console.error("Payload is not an array.");
                }
            } else {
                console.error("Payload is missing or empty.");
            }
        },
        error: function (error) {
            console.error("Error fetching data:", error);
        }
    });
}
