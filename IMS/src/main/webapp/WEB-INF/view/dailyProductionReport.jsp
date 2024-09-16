<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ page import="org.springframework.http.HttpStatus"%>
<%@ page import="com.doritech.api.Entity.*"%>
<%@ page import="com.doritech.api.DTO.ItemRecievedDTO"%>
<%@ page import="com.doritech.api.Entity.ParamEntity"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>IMS</title>

<link rel="stylesheet" href="./css/dailyProductionReport.css" />
<link rel="stylesheet" href="./externalCSS/bootstrap.min.css" />
<script src="./externalJS/sweetalert2.all.min.js"></script>
<link href="./externalCSS/select2.min.css" rel="stylesheet" />
<script src="./externalJS/fontawesome.js"></script>

<!-- DATATABLES CSS Links -->
<link rel="stylesheet"
	href="./externalCSS/dataTables.bootstrap4.min.css">
<link rel="stylesheet" href="./externalCSS/buttons.dataTables.min.css">
<link rel="stylesheet"
	href="./externalCSS/responsive.bootstrap4.min.css">

<!-- DATATABLES JS Links -->
<script src="./externalJS/sweetalert2.all.min.js"></script>
<script src="./externalJS/jquery-3.7.0.js"></script>
<script src="./externalJS/select2.min.js"></script>
<script src="./externalJS/jquery.dataTables.min.js"></script>
<script src="./externalJS/dataTables.bootstrap4.min.js"></script>
<script src="./externalJS/responsive.bootstrap4.min.js"></script>
<script src="./externalJS/dataTables.responsive.min.js"></script>
<script src="./externalJS/dataTables.buttons.min.js"></script>
<script src="./externalJS/pdfmake.min.js"></script>
<script src="./externalJS/vfs_fonts.js"></script>
<script src="./externalJS/buttons.html5.min.js"></script>
<script src="./externalJS/buttons.print.min.js"></script>

<style>
body {
	transition: opacity 0.5s ease-in-out;
}
.right-align {
    text-align: right;
}
.left-align {
    text-align: left;
}
</style>
</head>
<body style="font-size: 12px; background-color: #DCECFE;">

	<div class="row mx-1">
		<div class="col-md-12">
			<div class="row">
				<div class="col-md-12">
					<h5 class="heading">Production Report</h5>
				</div>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-md-12">
			<div class="mt-1"></div>
			<div class="col-md-12">
				<div class="card">
					<div class="m-1">
						<div class="table-container">
							<b style="margin-left: 0.5%; color: #1649A2;">Search By Item
								Code/ Item Name</b>
							<div class="mb-2 row" style="margin: 1%;">
								<div class="custom-field one col-md-4">
									<div class="input-boxx active-greyy">
										<select class="input-11 form-control form-control-sm select2"
											id="itemCode" name="itemCode" placeholder="Item Code">
											<option value="">--select--</option>
										</select>
										<button type="button" id="clearButton"
										class="btn btn-primary btn-sm mx-2"
										style="background: #86A7FC; border: 1px solid #1649A2;"
										onclick="refreshPage();">Refresh</button>
									</div>
								</div>
								<div class="col-md-2 mt-2">
									
								</div>
								<div class="input-group rounded mt-2 col-md-6">
									<input
										class="form-control form-control-sm rounded typeahead mx-1"
										type="text" id="startDate" placeholder="Start Date"
										style="height: 32px;" onfocus="(this.type='date')"> <input
										class="form-control form-control-sm rounded typeahead mx-1"
										type="text" id="endDate" placeholder="End Date"
										style="height: 32px;" onfocus="(this.type='date')">
									<button class="input-group-text border-0 ml-2"
										id="filterButton"
										style="background-color: #86A7FC; color: #ffff; height: 32px;">
										<i class="fa fa-search" aria-hidden="true"></i>
									</button>
									<button class="input-group-text border-0 ml-2"
										style="background-color: #86A7FC; color: #ffff; height: 32px;"
										id="clearDateButton">
										<i class="fa-solid fa-broom fa-flip"></i>
									</button>
								</div>
							</div>
							<table id="example"
								class="table table-striped table-bordered table-wrapped"
								cellspacing="0" width="100%">
								<thead>
									<tr>
										<th class="col-md-1">Item Code</th>
										<th class="col-md-3">Item name</th>
										<th class="col-md-1">Production date</th>
										<th class="col-md-1">Qty. in Stock</th>
										<th class="col-md-1">Qty. Produced</th>
										<th class="col-md-4">Raw Items Used</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${productionItemsList.payload}"
										var="productionList">
										<tr>
											<td class="left-align">${productionList.itemCode}</td>
											<td class="left-align">${productionList.itemName}</td>
											<td class="left-align">${productionList.productionDate}</td>
											<td class="right-align">${productionList.quantityInStock}</td>
											<td class="right-align">${productionList.quantity}</td>
											<td class="left-align" id="item-details"></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
	$(document).ready(function() {
	    var table = $('#example').DataTable({
	        responsive: true,
	        paging: false,
	        scrollCollapse: true,
	        scrollY: '45vh',
	        dom: 'Bfrtip',
	        buttons: [
	            {
	                extend: 'copyHtml5',
	                text: '<i class="fa-solid fa-copy" style="color: #B197FC; font-size:18px;"></i>',
	                titleAttr: 'Copy'
	            },
	            {
	                extend: 'excelHtml5',
	                text: '<i class="fa fa-file-excel-o"></i>',
	                titleAttr: 'Excel'
	            },
	            {
	                extend: 'csvHtml5',
	                text: '<i class="fa-solid fa-file-csv" style="color: #1c76ba; font-size:18px;"></i>',
	                titleAttr: 'CSV',
	                exportOptions: {
	                    columns: [0, 1, 2, 3, 4, 5],
	                    format: {
	                        body: function(data, row, column, node) {
	                            if (column === 5) {
	                                // Format the "Raw Items Used" column data
	                                var rawItems = $(node).text().replace(/<[^>]*>/g, '').trim().replace(/\s{2,}/g, ' ').replace(/\n/g, ' ');
	                                // Insert commas after each raw item for readability
	                                rawItems = rawItems.replace(/\s*,\s*/g, ', ');
	                                return rawItems;
	                            }
	                            return data;
	                        }
	                    }
	                }
	            },
	            {
	                extend: 'pdfHtml5',
	                text: '<i class="fa-solid fa-file-pdf" style="color: #74C0FC; font-size:18px;"></i>',
	                titleAttr: 'PDF'
	            },
	            {
	                extend: 'print',
	                text: '<i class="fa-solid fa-print" style="color: #6595ff; font-size:18px;"></i>',
	                titleAttr: 'PRINT'
	            }
	        ],
	        order: [[5, 'desc']]
	    });

	    $('#filterButton').click(function() {
	        var startDate = $('#startDate').val();
	        var endDate = $('#endDate').val();

	        if (startDate !== '' && endDate !== '') {
	            $.fn.dataTable.ext.search.push(
	                function(settings, data, dataIndex) {
	                    var dateTime = data[2] || "";
	                    if (dateTime >= startDate && dateTime <= endDate) {
	                        return true;
	                    }
	                    return false;
	                }
	            );
	            table.draw();
	        } else {
	            alert('Please provide both start and end dates.');
	        }
	    });

	    $('#clearButton').click(function() {
	        $('#startDate').val('');
	        $('#endDate').val('');
	        $.fn.dataTable.ext.search.pop();
	        table.draw();
	    });

	    var rows = $('#example tbody tr');
	    rows.each(function() {
	        var itemCode = $(this).find('td').eq(0).text();
	        fetchItemDetails(itemCode, $(this));
	    });

	    $('#example').on('buttons-action', function(e, buttonApi, dataTable, node, config) {
	        if (buttonApi.text() === 'CSV') {
	            table.button('.buttons-csv').trigger();
	        }
	    });
	});



	function fetchItemDetails(itemCode, row) {
	    fetch("getAllRawItemsForSubFinalProductByProductCode?productCode=" + encodeURIComponent(itemCode))
	        .then(response => response.json())
	        .then(data => {
	            var itemDetailsCell = row.find("#item-details");
	            itemDetailsCell.html("");

	            var quantityProduced = parseFloat(row.find('td').eq(4).text());

	            if (data.payload && Array.isArray(data.payload)) {
	                data.payload.forEach(function(entry) {
	                    var entryList = $('<ul></ul>');
	                    var entityListItem = $('<li></li>');
	                    var entityList = $('<ol></ol>');

	                    var propertyMap = {
	                        "itemCode": "Item Code",
	                        "itemName": "Item Name",
	                        "itemQuantity": "Item Quantity"
	                    };

	                    for (var key in propertyMap) {
	                        if (propertyMap.hasOwnProperty(key)) {
	                            var listItem = $('<li></li>');
	                            var propertyName = $('<strong></strong>').text(propertyMap[key] + ": ");
	                            var propertyValue = $('<span></span>').text(entry[key]);
	                            listItem.append(propertyName).append(propertyValue);
	                            entityList.append(listItem);
	                        }
	                    }

	                    var totalQuantity = parseFloat(entry["itemQuantity"]) * quantityProduced;
	                    var totalQuantityListItem = $('<li></li>');
	                    var totalQuantityName = $('<strong></strong>').text("Total Quantity: ");
	                    var totalQuantityValue = $('<span></span>').text(totalQuantity.toFixed(2));
	                    totalQuantityListItem.append(totalQuantityName).append(totalQuantityValue);
	                    entityList.append(totalQuantityListItem);

	                    entityListItem.append(entityList);
	                    entryList.append(entityListItem);
	                    itemDetailsCell.append(entryList);
	                });
	            } else {
	                console.error("Invalid payload format:", data.payload);
	            }
	        })
	        .catch(error => {
	            console.error("Error fetching item details:", error);
	        });
	}

	</script>

	<script src="./externalJS/bootstrap.bundle.min.js"></script>
	<script src="./js/stockQuantityReport.js"></script>
	<!-- <script src="./js/onGoingActivityDataTable.js"></script> -->
	<script src="./externalJS/multilingual.js"></script>
	<script src="./js/googleTranslateElementInit.js"></script>

</body>
</html>
