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


<link rel="stylesheet" href="./css/rawItemsIssue.css" />
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
					<h5 class="heading">Issue Raw Items</h5>
				</div>
				<div class="col-md-4">
        <div class="searchBox">
            <input class="form-control" type="file" name="formFile" id="formFile">
            <button class="btn btn-sm importButton ml-1" onClick="importItemIssue();;">
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
										<b style="margin-left: 0.5%; color: #1649A2;">Raw Items
											Issue Details</b>

										<div class="mb-3 row" style="margin: 1%;">
											<input type="hidden" id="empId" value="${empId}" /> <label
												class="custom-field one col-md-2"> <input
												type="text" placeholder=""
												class="form-control form-control-sm" id="rawIssueId"
												value="${rawIssueId}" readonly /> <span class="placeholder">Issue
													ID</span>
											</label> <label class="custom-field one col-md-2"> <input
												type="date" placeholder=""
												class="form-control form-control-sm" id="issueDate" /> <span
												class="placeholder">Issue Date</span>
											</label>
											<div class="custom-field one col-md-2">
												<div class="input-boxx active-greyy">
													<label class="input-labell">Consumption Type</label> <select
														class="input-11 form-control" id="consumptionType"
														name="itemType">
														<option value="#">--select--</option>
														<option value="RAW">RAW</option>
														<option value="SUBPRODUCT">SUB-PRODUCT</option>
														<option value="FINAL PRODUCT">FINAL PRODUCT</option>
													</select>
												</div>
											</div>
											<div class="custom-field one col-md-2">
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
											</label>
										</div>
										<div class="mb-2 row" style="margin: 1%;"></div>
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
					<div class="m-1">
						<div class="table-container"
							style="max-height: 370px; overflow-y: auto;">

							<table id="example" class="table table-striped table-bordered">
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
	<script src="./js/navBarToggle.js"></script>
	<script src="./externalJS/bootstrap.bundle.min.js"></script>
	<script src="./js/rawItemsIssue.js"></script>

</body>
</html>