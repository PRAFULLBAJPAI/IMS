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


<link rel="stylesheet" href="./css/itemPurchase.css" />
<link rel="stylesheet" href="./css/multilingual.css" />
<link rel="stylesheet" href="./externalCSS/bootstrap.min.css" />
<link href="./externalCSS/select2.min.css" rel="stylesheet" />
<!-- <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script> -->

<script src="./externalJS/jquery-3.7.0.js"></script>
<script src="./externalJS/select2.min.js"></script>
<script src="./externalJS/sweetalert2.all.min.js"></script>
<script src="./externalJS/fontawesome.js"></script>

<!-- DATATABLES CSS Links -->


<style type="text/css">
td a {
	color: black;
	text-decoration: none !important;
}

.select2 {
	width: 190px !important;
}
</style>
</head>
<body style="font-size: 12px; background-color: #DCECFE;">
	<div class="row mx-1">
		<div class="col-md-12">
			<div class="row">
				<div class="col-md-8">
					<h5 class="heading">Item Receive</h5>
				</div>
				<div class="col-md-4">
        <div class="searchBox">
            <input class="form-control" type="file" name="formFile" id="formFile">
            <button class="btn btn-sm importButton ml-1" onClick="importItemMaster();">
                <i class="fa-solid fa-upload"></i>
            </button>
        </div>
    </div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div class="col-md-12">
				<div class="card">
					<div class="mx-2">
						<div class="row">
							<div class="col-md-12">
								<div class="mt-2"></div>
								<form id="itemPurchaseForm" name="itemPurchaseForm">
									<div style="border: 1.75px solid #6595ff;">
										<b style="margin-left: 0.5%; color: #1649A2;">Item Receive
											Details</b>
										<div class="mb-2 row" style="margin: 1%;">
											<input type="hidden" id="empId" value="${employeeId}" /> <label
												class="custom-field one col-md-2"> <input
												type="text" placeholder=""
												class="form-control form-control-sm " id="recieveId"
												value="${receiveId}" readonly /> <span class="placeholder">Receive
													ID</span>
											</label> <label class="custom-field one col-md-2"> <input
												type="date" placeholder=""
												class="form-control form-control-sm " id="recievedDate" />
												<span class="placeholder">Receive Date</span>
											</label> <label class="custom-field one col-md-2"> <input
												type="text" placeholder=""
												class="form-control form-control-sm " id="invoiceNo" /> <span
												class="placeholder">Invoice No.</span>
											</label> <label class="custom-field one col-md-2"> <input
												type="date" placeholder=""
												class="form-control form-control-sm" id="invoiceDate" /> <span
												class="placeholder">Invoice Date</span>
											</label>
											<div class="custom-field one col-md-2">
												<div class="input-boxx active-greyy">
													<label class="input-labell">Item Source</label> <select
														class="input-11 form-control" id="itemSource"
														name="itemSource">
														<option id="itemSource" value="">--select--</option>
														<c:forEach items="${vendorList.payload}" var="vendor">
															<option id="itemSource" value="${vendor.vendorId}">${vendor.vendorId}->${vendor.vendorName}</option>
														</c:forEach>
													</select>
												</div>
											</div>
											<div class="col-md-2 custom-field one">
												<div class="input-boxx active-greyy">
													<label class="input-labell">Warehouse</label> <select
														class="input-11 form-control" id="wareHouse"
														name="wareHouse">
														<option id="wareHouse" value="">--select--</option>
														<c:forEach items="${warehouseList}" var="warehouse">
															<option id="wareHouse" value="${warehouse.descp1}">${warehouse.descp1}</option>
														</c:forEach>
													</select>
												</div>
											</div>
										</div>
										<div class="mb-2 row" style="margin: 1%;">
											<label class="custom-field one col-md-4"> <input
												type="text" placeholder=""
												class="form-control form-control-sm" id="remarks" /> <span
												class="placeholder">Remarks</span>
											</label>
										</div>
									</div>
								</form>
								<div class="mt-2"></div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="mt-1"></div>

			<div class="col-md-12">
				<div class="card">
					<div class="m-1" style="background-color:white; z-index:1000; margin-bottom:-5px !important;">
						<button type="button" class="btn btn-sm mb-1"
							style="float: right; background: #6595ff; color: #ffff"
							onclick="addNewItemPurchase();">
							<i class="fa-solid fa-square-plus mr-1"></i>Add New
						</button>
					</div>
					<div class="m-1">

						<div class="table-container"
							style="max-height: 320px; overflow-y: auto;">

							<table id="example" class="table table-striped table-bordered">
								<thead style="position:sticky; top:0; z-index:1;">
									<tr>
										<th class="col-md-2">Item Code</th>
										<th class="col-md-4">Item Name</th>
										<th class="col-md-2">Quantity In Stock</th>
										<th class="col-md-2">Item Quantity</th>
										<th class="col-md-1">Current Price per pc.</th>
										<th class="col-md-1">Updated Price per pc.</th>
										<th class="col-md-1">Deletion</th>
										<th class="col-md-1">Add New</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>
											<div class="input-boxx active-greyy">
												<select
													class="input-11 form-control form-control-sm select2"
													id="itemCode" name="itemCode">
													<option>---select---</option>
												</select>
											</div>
										</td>
										<td><input type="text" placeholder=""
											class="form-control form-control-sm" id="itemName" /></td>
										<td><input type="text" placeholder=""
											class="form-control form-control-sm" id="quantityInStock" />
										</td>
										<td><input type="text" placeholder=""
											class="form-control form-control-sm" id="itemQuantity" /></td>
										<td><input type="text" placeholder=""
											class="form-control form-control-sm" id="unitPrice" /></td>
										<td><input type="text" placeholder=""
											class="form-control form-control-sm" id="itemPrice" /></td>
										<td><button type="button"
												class="btn btn-danger btn-sm ml-2"
												onclick="removeItemRow(this);">
												<i class="fa-regular fa-trash-can fa-lg"></i>
											</button></td>
										<td><button type="button" id="addRowButton"
												class="btn btn-sm ml-2" onclick="addNewItemPurchase();"
												style="background: #6595ff; color: #ffff;">
												<i class="fa-solid fa-square-plus fa-lg"></i>
											</button></td>
									</tr>
								</tbody>
							</table>
						</div>

						<div class="row">
							<div class="col-md-12">
								<div class="form-btn mt-1">
									<button type="button" class="btn btn-primary"
										onclick="saveData()">Save</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
<div class="mt-2"></div>

	<script>
		
	</script>

	<script src="./js/navBarToggle.js"></script>
	<script src="./externalJS/bootstrap.bundle.min.js"></script>
	<script src="./js/itemPurchase.js"></script>
	<script src="./js/tmsDataTables.js"></script>

</body>
</html>