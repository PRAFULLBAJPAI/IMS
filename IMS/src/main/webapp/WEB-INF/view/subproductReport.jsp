<%@page import="org.json.simple.ItemList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ page import="org.springframework.http.HttpStatus"%>
<%@ page import="com.doritech.api.Entity.*"%>
<%@ page import="com.doritech.api.DTO.EmployeeMasterDTO"%>
<%@ page import="com.doritech.api.Entity.ParamEntity"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>IMS</title>

<!-------------------------- CSS Links---------------------------- -->
<link rel="stylesheet" href="./externalCSS/bootstrap.min.css">
<link rel="stylesheet" href="./externalCSS/googleFonts.css">
<link rel="stylesheet" href="./css/subproductReport.css" />
<link href="./externalCSS/select2.min.css" rel="stylesheet" />
<link rel="stylesheet" href="./externalCSS/bootstrap.min.css" />
<script src="./externalJS/fontawesome.js"></script>
<!-- DATATABLES CSS Links -->
<link rel="stylesheet" href="./externalCSS/dataTables.bootstrap4.min.css">
<link rel="stylesheet" href="./externalCSS/buttons.dataTables.min.css">
<link rel="stylesheet" href="./externalCSS/responsive.bootstrap4.min.css">
<!-- DATATABLES JS Links -->
<script src="./externalJS/RecordRTC.js"></script>
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
					<h5 class="heading">Sub-Product/Final Product Definition Report</h5>
				</div>

			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div class="mt-1"></div>
			<div class="col-md-12">
				<div class="card ">
					<div class="mr-2 ml-2">
						<div class="row">
							<div class="col-md-12">
								<div class="mt-2"></div>
								<form id="filterItemIssued" name="filterItemIssue">
									<div style="border: 1.75px solid #6595ff;">
										<b style="margin-left: 0.5%; color: #1649A2;">Subproduct/
											Final Product details</b>
									
										<div class="mb-2 row" style="margin: 1%;">
								<div class="custom-field one col-md-6">
									<div class="input-boxx active-greyy">
										<select
														class="input-11 form-control form-control-sm select2"
														id="itemCode" name="itemCode" placeholder="Item Code">
														<option value="">--select--</option>
													</select>
										<button type="button" id="clearButton"
										class="btn btn-primary btn-sm mx-2"
										style="background: #86A7FC; border: 1px solid #1649A2;"
										onclick="refreshPage();">Clear</button>
									</div>
									
								</div>
								<div class="col-md-2 mt-2">
									
								</div>
							
							</div>
									</div>
								</form>
								<div class="mt-2"></div>
							</div>
						</div>
					</div>
				</div>
			</div>


			<div class="card mt-4" style="margin: 1.1%;">

				<table id="example"
					class="table table-striped table-bordered table-wrapped"
					cellspacing="0" width="100%">
					<thead >
						<tr>
							<th class="col-md-1">Product Code</th>
							<th class="col-md-2">Product Name</th>
							<th class="col-md-1">Product Type</th>
							<th class="col-md-1">Quantity in Stock</th>
							<th class="col-md-1">Minimum Stock</th>
							<th class="col-md-1">Unit Price</th>
							<th class="col-md-1">Entry Date</th>
							<th class="col-md-4 none">Item Details</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${itemList.payload}" var="entry">
							<tr>
								<td class="left-align">${entry.key.itemCode}</td>
								<td class="left-align">${entry.key.itemName}</td>
								<td class="left-align">${entry.key.itemType}</td>
								<td class="right-align">${entry.key.quantityInStock}</td>
								<td class="right-align">${entry.key.minimumStock}</td>
								<td class="right-align">${entry.key.unitPrice}</td>
								<td class="left-align">${entry.key.entryDate}</td>
								<td>
									<c:forEach items="${entry.value}" var="itemIssuedEntity">
										<ol>
												<li><strong>Item Code:</strong> ${itemIssuedEntity.itemCode}</li>
												<li><strong>Item Name:</strong> ${itemIssuedEntity.itemName}</li>
												<li><strong>Quantity (per Product):</strong> ${itemIssuedEntity.itemQuantity}</li>
												
											</ol>
											<div class="mt-2"></div>
									</c:forEach>
								 </td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	
	<script>
	/**
	 * 
	 */

	 $(document).ready(function() {
		    $('#example').DataTable( {
				responsive: true,
				   paging: false,
		        scrollCollapse: true,
		         scrollY: '40vh',
		        dom: 'Bfrtip',
		        buttons: [
		            {
		                extend:    'copyHtml5',
		                text:      '<i class="fa-solid fa-copy" style="color: #B197FC; font-size:18px;"></i>',
		                titleAttr: 'Copy'
		            },
		            {
		                extend:    'excelHtml5',
		                text:      '<i class="fa fa-file-excel-o"></i>',
		                titleAttr: 'Excel'
		            },
		            {
		                extend:    'csvHtml5',
		                text:      '<i class="fa-solid fa-file-csv " style="color: #1c76ba; font-size:18px;"></i>',
		                titleAttr: 'CSV'
		            },
		            {
		                extend:    'pdfHtml5',
		                text:      '<i class="fa-solid fa-file-pdf" style="color: #74C0FC; font-size:18px;"></i>',
		                titleAttr: 'PDF'
		            },
		            {
		                extend:    'print',
		                text:      '<i class="fa-solid fa-print" style="color: #6595ff; font-size:18px;"></i>',
		                titleAttr: 'PRINT'
		            }
		        ]
		   
		    } );
		} );
		
		
	</script>

	<script src="./externalJS/bootstrap.bundle.min.js"></script>
	<script src="./js/subproductReport.js"></script>
	<!-- <script src="./js/onGoingActivityDataTable.js"></script> -->
	<script src="./externalJS/multilingual.js"></script>
	<script src="./js/googleTranslateElementInit.js"></script>



</body>
</html>