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


<link rel="stylesheet" href="./css/itemIssue.css" />
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

.disabled-save {
	background-color: lightgrey;
	color: grey;
	cursor: not-allowed;
}
</style>
</head>
<body style="font-size: 12px; background-color: #DCECFE;">

	<div class="row mx-1">
		<div class="col-md-12">
			<div class="row">
				<div class="col-md-8">
					<h5 class="heading" style="height:auto;">SubProduct/Final Product Definition</h5>
				</div>
				<div class="col-md-4">
        <div class="searchBox">
            <input class="form-control" type="file" name="formFile" id="formFile">
            <button class="btn btn-sm importButton ml-1" onClick="importItemIssue();">
                <i class="fa-solid fa-upload"></i>
            </button>
        </div>
    </div>

			</div>
		</div>
	</div>
	<div class="row  mx-auto">
		<div class="col-md-12">
			<div class="card">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<div class="mb-2 row">
								<div class="input-group rounded mt-2 col-md-3 mb-1">
									<input class="form-control form-control-sm rounded typeahead"
										type="text" id="searchInput" placeholder="Search by item code">
									<div id="suggestionDropdown"></div>
									<button class="input-group-text border-0 ml-1"
										id="productCodeSeachButton" style="background-color: #1649A2;">
										<i class="fas fa-search" style="color: #ffff;"></i>
									</button>
								</div>
								<div class="input-group rounded mt-2 col-md-6 mb-1">
									<input class="form-control form-control-sm rounded typeahead"
										type="text" id="searchInputByName"
										placeholder="Search by item name">
									<div id="suggestionDropdownForName"></div>
									<button class="input-group-text border-0 ml-1" id=""
										style="background-color: #1649A2;"
										onclick="searchByProductName()">
										<i class="fas fa-search" style="color: #ffff;"></i>
									</button>
								</div>
								<div class="custom-field one col-md-2" style="margin-top: -3px;">
									<button type="button" id="clearButton"
										class="btn btn-primary btn-sm"
										style="background: #86A7FC; border: 1px solid #1649A2;"
										onclick="refreshPage();">Refresh</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="mt-1"></div>
	<div class="row">
		<div class="col-md-12">
			<div class="col-md-12">
				<div class="card">
					<div class="mx-2">
						<div class="row">
							<div class="col-md-12">
								<div class="mt-2"></div>
								<form id="itemIssueForm" name="itemIssueForm">
									<div style="border: 1.75px solid #6595ff;">
										<b style="margin-left: 0.5%; color: #1649A2;">Subproduct/Final
											Product Details</b>

										<div class="mb-3 row" style="margin: 1%;">
											<input type="hidden" id="empId" value="${empId}" /> <input
												type="hidden" id="modifiedBy" value="${empId}" /> <label
												class="custom-field one col-md-2"> <input
												type="text" placeholder=""
												class="form-control form-control-sm" id="issueId"
												value="${issueId}" readonly /> <span class="placeholder">Issue
													ID</span>
											</label>
											<div class="custom-field one col-md-3">
												<div class="input-boxx active-greyy">
													<label class="input-labell">Subproduct Code</label> <select
														class="input-11 form-control form-control-sm "
														id="productCode" name="productCode"
														data-placeholder="Subproduct Code">
														<option value="">--select--</option>
														<c:forEach items="${productCodeList}" var="product">
															<option value="${product.itemCode}"
																data-item-name="${product.itemName}">
																${product.itemCode} : ${product.itemName}</option>
														</c:forEach>
													</select>
												</div>
											</div>
											<label class="custom-field one col-md-4"> <input
												type="text" placeholder=""
												class="form-control form-control-sm " id="productName" /> <span
												class="placeholder">Subproduct Name</span>
											</label> <label class="custom-field one col-md-3"> <input
												type="date" placeholder=""
												class="form-control form-control-sm" id="issueDate" /> <span
												class="placeholder">Issue Date</span>
											</label>
											
										</div>
										<div class="mb-2 row" style="margin: 1%;">
										<div class="custom-field one col-md-3">
												<div class="input-boxx active-greyy">
													<label class="input-labell">Consumption Type</label> <select
														class="input-11 form-control" id="consumptionType"
														name="itemType">
														<option value="#">--select--</option>
														<option value="SUBPRODUCT">SUB-PRODUCT</option>
														<option value="FINAL PRODUCT">FINAL PRODUCT</option>
													</select>
												</div>
											</div>
											<div class="custom-field one col-md-3">
												<div class="input-boxx active-greyy">
													<label class="input-labell">Consumption For</label> <select
														class="input-11 form-control" id="consumptionFor"
														name="itemType">
														<option value="">--select--</option>
														<c:forEach items="${consumptionForList}"
															var="consumptionFor">
															<option value="${consumptionFor.descp1}">${consumptionFor.descp1}</option>
														</c:forEach>
													</select>
												</div>
											</div>
											<label class="custom-field one col-md-4"> <input
												type="text" placeholder=""
												class="form-control form-control-sm" id="remark" /> <span
												class="placeholder">Remarks</span>
											</label> <label class="custom-field one col-md-4"
												id="productNameField" style="display: none;"> <input
												type="text" placeholder=""
												class="form-control form-control-sm" id="productName" /> <span
												class="placeholder">Product Name</span>
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
				<div class="card p-2">
					<div class="" style="background-color:white; z-index:1000; margin-bottom:-5px;">
						<button type="button" class="btn btn-sm mb-1"
							style="float: right; background: #6595ff; color: #ffff"
							onclick="addNewItemIssue();">
							<i class="fa-solid fa-square-plus mr-1"></i>Add New
						</button>
					</div>
					
					<div class="mt-0">
						<div class="table-container"
							>
							<table id="example" class="table table-responsive table-striped table-bordered">
								<thead style="position:sticky; top:0; z-index:1;">
									<tr>
										<th class="col-md-1">Item Code</th>
										<th class="col-md-2">Item Name</th>
										<th class="col-md-2">Qty. in Stock</th>
										<th class="col-md-2">Min. Stock</th>
										<th class="col-md-2">Item Qty.</th>
										<th class="col-md-1">Deletion</th>
										<th class="col-md-1">Add New</th>
									</tr>
								</thead>
								
								<tbody>
									<tr>
										<td>
											<div class="input-boxx active-greyy">
												<select style="border: 1px solid #6595ff;"
													class="input-11 form-control select2 " id="itemCode"
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
										<td><input type="text" placeholder=""
											class="form-control form-control-sm" id="quantityInStock" /></td>
										<td><input type="text" placeholder=""
											class="form-control form-control-sm" id="minimumStock" /></td>
										<td><input type="text" placeholder=""
											class="form-control form-control-sm" id="itemQuantity" /></td>
										<td><button type="button"
												class="btn btn-danger btn-sm ml-2"
												onclick="removeItemRow(this);">
												<i class="fa-regular fa-trash-can"></i>
											</button></td>
										<td><button type="button" class="btn btn-sm ml-2"
												onclick="addNewItemIssue();"
												style="background: #6595ff; color: #ffff;">
												<i class="fa-solid fa-square-plus"></i>
											</button></td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="form-btn mt-1">
									<button type="button" class="btn btn-outline-info"
										onclick="updateData()">Edit</button>
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
		document.getElementById("productCodeSeachButton").addEventListener(
				"click", function() {
					var saveButton = document.getElementById("saveButton");
					saveButton.disabled = true;
					saveButton.classList.add("disabled-save");
				});

		function enableSaveButton() {
			var saveButton = document.getElementById("saveButton");
			saveButton.disabled = false;
			saveButton.classList.remove("disabled-save");
		}

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
	<script src="./js/itemIssue.js"></script>

</body>
</html>