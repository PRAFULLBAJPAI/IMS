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
.select2 {
	width: 190px !important;
}

td a {
	color: black;
	text-decoration: none !important;
}
</style>
</head>
<body style="font-size: 12px; background-color: #DCECFE;">

	<div class="row mx-1">
		<div class="col-md-12">
			<div class="row">
				<div class="col-md-12">
					<h5 class="heading">Production Entity</h5>
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
								<form id="itemIssueForm" name="itemIssueForm">
									<div style="border: 1.75px solid #6595ff;">
										<b style="margin-left: 0.5%; color: #1649A2;">Production
											Details</b>
										<div class="mb-2 row" style="margin: 1%;">
											<input type="hidden" id="userId" value="${empId}" /> <label
												class="custom-field one col-md-2"> <input
												type="text" placeholder=""
												class="form-control form-control-sm" id="productId"
												value="${productId}" readonly /> <span class="placeholder">Product
													ID</span>
											</label> <label class="custom-field one col-md-2"> <input
												type="date" placeholder=""
												class="form-control form-control-sm" id="productionDate" />
												<span class="placeholder">Production Date</span>
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
							onclick="addNewItemRow();">
							<i class="fa-solid fa-square-plus mr-1"></i>Add New
						</button>
					</div>
					<div class="m-1">

						<div class="table-container"
							style="max-height: 380px; overflow-y: auto;">

							<table id="example" class="table table-striped table-bordered">
								<thead style="position:sticky; top:0; z-index:1;">
									<tr>
										<th class="col-md-2">Item Code</th>
										<th class="col-md-4">Item Name</th>
										<th class="col-md-4">Raw Items Used</th>
										<th class="col-md-1">Quantity In Stock</th>
										<th class="col-md-1">Quantity</th>
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
											class="form-control form-control-sm" id="itemName" readonly /></td>
										<td id="item-details"></td>
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
												onclick="addNewItemRow();"
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
	<script src="./js/navBarToggle.js"></script>
	<script src="./externalJS/bootstrap.bundle.min.js"></script>
	<script src="./js/productionEntity.js"></script>

</body>
</html>