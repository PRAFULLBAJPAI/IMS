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


<link rel="stylesheet" href="./css/dispatchEntity.css" />
<link rel="stylesheet" href="./externalCSS/bootstrap.min.css" />
<link href="./externalCSS/select2.min.css" rel="stylesheet" />
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
					<h5 class="heading">Dispatch Details</h5>
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
								<form id="itemDispatchForm" name="itemDispatchForm">
									<div style="border: 1.75px solid #6595ff;">
										<b style="margin-left: 0.5%; color: #1649A2;">Dispatch Details</b>
										<div class="mb-3 row" style="margin: 1%;">
										<input type="hidden" id="empId" value="${empId}" /> 
											<label class="custom-field one col-md-2"> <input
												type="text" placeholder=""
												class="form-control form-control-sm" id="dispatchId"
												value="${dispatchId}" readonly /> <span class="placeholder">Dispatch
													ID</span>
											</label>
											<div class="custom-field one col-md-3">
												<div class="input-boxx active-greyy">
													<label class="input-labell">Client Name</label> <select
														class="input-11 form-control" id="clientId"
														name="clientId">
														<option value="">--select--</option>
														<c:forEach items="${clientList.payload}"
															var="client">
															<option value="${client.clientId}">${client.clientId} : ${client.clientName}</option>
														</c:forEach>
													</select>
												</div>
											</div>
											 <label class="custom-field one col-md-2"> <input
												type="date" placeholder=""
												class="form-control form-control-sm" id="dispatchDate" /> <span
												class="placeholder">Dispatch Date</span>
											</label>
											<label class="custom-field one col-md-3"> <input
												type="text" placeholder=""
												class="form-control form-control-sm" id="invoiceNo" /> <span
												class="placeholder">Invoice No.</span>
											</label>
											<label class="custom-field one col-md-2"> <input
												type="date" placeholder=""
												class="form-control form-control-sm" id="invoiceDate" /> <span
												class="placeholder">Invoice Date</span>
											</label>
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
							onclick="addNewItemIssue();">
							<i class="fa-solid fa-square-plus mr-1"></i>Add New
						</button>
					</div>
					<div class="mt-0" style="margin: .25rem;">

						<div class="table-container"
							style="max-height: 330px; overflow-y: auto;">

							<table id="example" class="table table-striped table-bordered">
								<thead style="position:sticky; top:0; z-index:1;">
									<tr>
										<th class="col-md-2">Item Code</th>
										<th class="col-md-5">Item Name</th>
										<th class="col-md-2">Qty. in Stock</th>
										<th class="col-md-2">Dispatch Qty.</th>
										<th class="col-md-1">Deletion</th>
										<th class="col-md-1">Add New</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>
											<div class="input-boxx active-greyy">
												<select style="border: 1px solid #6595ff;"
													class="input-11 form-control select2" id="itemCode"
													name="itemCode">
													<option>---select---</option>
												</select>
											</div>
										</td>
										<td>
											<div class="input-boxx active-greyy">
												<select style="border: 1px solid #6595ff;"
													class="input-11 form-control select2" id="itemName"
													name="itemName">
													<option>---select---</option>
												</select>
											</div>
										</td>
										<!-- <td><input type="text" placeholder=""
											class="form-control form-control-sm" id="itemName" /></td> -->
										<td><input type="text" placeholder=""
											class="form-control form-control-sm" id="quantityInStock" /></td>
										<td><input type="text" placeholder=""
											class="form-control form-control-sm" id="quantity" /></td>
										<td><button type="button"
												class="btn btn-danger btn-sm ml-2"
												onclick="removeItemRow(this);">
												<i class="fa-regular fa-trash-can fa-lg"></i>
											</button></td>
										<td><button type="button" class="btn btn-sm ml-2"
												onclick="addNewItemIssue();"
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
									<button type="button" id="saveButton" class="btn btn-primary"
										onclick="saveData();">Save</button>
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
		function updateProductName() {
			var productCodeDropdown = document.getElementById("productCode");
			var productNameField = document.getElementById("productName");

			var selectedOption = productCodeDropdown.options[productCodeDropdown.selectedIndex];
			var itemName = selectedOption.getAttribute("data-item-name");

			productNameField.value = itemName;
		}

		document.getElementById("productCode").onchange = updateProductName;
	</script>

	<script src="./js/navBarToggle.js"></script>
	<script src="./externalJS/bootstrap.bundle.min.js"></script>
	<script src="./js/dispatchEntity.js"></script>

</body>
</html>