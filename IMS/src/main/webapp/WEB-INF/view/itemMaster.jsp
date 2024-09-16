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
<title>Item Details</title>
<link rel="stylesheet" href="./css/itemMaster.css" />
<link rel="stylesheet" href="./css/multilingual.css" />
<link rel="stylesheet" href="./externalCSS/bootstrap.min.css" />
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
<script src="./externalJS/jquery.dataTables.min.js"></script>
<script src="./externalJS/dataTables.bootstrap4.min.js"></script>
<script src="./externalJS/responsive.bootstrap4.min.js"></script>
<script src="./externalJS/dataTables.responsive.min.js"></script>
<script src="./externalJS/dataTables.buttons.min.js"></script>
<script src="./externalJS/pdfmake.min.js"></script>
<script src="./externalJS/vfs_fonts.js"></script>
<script src="./externalJS/buttons.html5.min.js"></script>
<script src="./externalJS/buttons.print.min.js"></script>

</head>
<body style="font-size: 12px; background-color: #DCECFE;">
	<div class="row mx-1">
		<div class="col-md-12">
			<div class="row">
				<div class="col-md-8">
					<h5 class="heading">Item Details</h5>
				</div>
				<div class="col-md-4">
					<div class="searchBox">
						<input class="form-control" type="file" name="formFile"
							id="formFile">
						<button class="btn btn-sm importButton ml-1"
							onClick="importItemMaster();">
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
									<button class="input-group-text border-0 ml-1" id=""
										style="background-color: #1649A2;"
										onclick="searchByItemCode()">
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
										onclick="searchByItemName()">
										<i class="fas fa-search" style="color: #ffff;"></i>
									</button>
								</div>
								<div class="custom-field one col-md-2" style="margin-top:-3px;">
									<button type="button" id="clearButton"
										class="btn btn-primary btn-sm mx-2"
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
	<div class="mt-2"></div>
	<div class="row mx-1">
		<div class="col-lg-12">
			<div class="card">
				<div class=" mt-3 mx-2">
					<div class="row">
						<div class="col-md-10 col-sm-12">
							<div style="height: 5px;"></div>

							<div style="border: 1.75px solid #6595ff;">
								<b style="margin-left: 0.5%; color: #1649A2;">Item Details </b>

								<div class="mb-2 row" style="margin: 0.5%;">
									<label class="custom-field one col-lg-2"> <input
										type="text" name="itemCode" id="itemCode" placeholder=""
										class="form-control form-control-sm" /> <span
										class="placeholder">Item Code</span>
									</label> <label class="custom-field one col-lg-7"> <input
										type="text" name="itemName" id="itemName" placeholder=""
										class="form-control form-control-sm" /> <span
										class="placeholder">Item Name</span>
									</label>


									<div class="custom-field one col-md-3">
										<div class="input-boxx active-greyy">
											<label class="input-labell">Item Type</label> <select
												class="input-11 form-control form-control-sm" id="itemType"
												name="itemType">
												<option id="itemType" value="">--select--</option>
												<c:forEach items="${itemTypeList}" var="itemTypeList">
													<option value="${itemTypeList.descp1}">${itemTypeList.descp1}</option>
												</c:forEach>
											</select>
										</div>

									</div>
								</div>

								<div class="mb-2 row" style="margin: 0.5%;">
									<div class="custom-field one col-md-4">
										<div class="input-boxx active-greyy">
											<label class="input-labell">Item Source</label> <select
												class="input-11 form-control form-control-sm"
												id="itemSource" name="itemSource">
												<option value="">--select--</option>
												<c:forEach items="${vendorMasterList.payload}"
													var="vendorMasterList">
													<option value="${vendorMasterList.vendorId}">${vendorMasterList.vendorName}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="custom-field one col-md-4">
										<div class="input-boxx active-greyy">
											<label class="input-labell">Item UOM</label> <select
												class="input-11 form-control form-control-sm" id="itemUom"
												name="itemUom">
												<option value="">--select--</option>
												<c:forEach items="${itemUomList}" var="itemUomList">
													<option value="${itemUomList.descp1}">${itemUomList.descp1}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="custom-field one col-md-4">
										<div class="input-boxx active-greyy">
											<label class="input-labell">Item Group</label> <select
												class="input-11 form-control form-control-sm" id="itemGroup"
												name="itemGroup" onfocus="setFocus(true)"
												onblur="setFocus(false)">
												<option value="">--select--</option>
												<option value="IT">IT</option>
												<option value="HR">HR</option>
												<option value="Sales">Sales</option>
											</select>
										</div>
									</div>
								</div>
							</div>
							<div class="mt-2"></div>
							<div style="border: 1.75px solid #6595ff;">
								<b style="margin-left: 0.5%; color: #1649A2;">Other Details</b>

								<div class="mb-2 row" style="margin: 0.5%;">
									<div class="custom-field one col-md-5">
										<div class="input-boxx active-greyy">
											<label class="input-labell">WAREHOUSE</label> <select
												class="input-11 form-control form-control-sm" id="warehouse"
												name="warehouse">
												<option value="">--select--</option>
												<c:forEach items="${warehouseList}" var="warehouseList">
													<option value="${warehouseList.descp1}">${warehouseList.descp1}</option>
												</c:forEach>
											</select>
										</div>

									</div>

									<label class="custom-field one col-lg-4"> <input
										type="text" name="status" id="status" placeholder=""
										class="form-control form-control-sm" value="Active" /> <span
										class="placeholder">Status</span>
									</label> <label class="custom-field one col-md-3"> <input
										type="number" name="minimumStock" id="minimumStock"
										placeholder="" class="form-control form-control-sm" /> <span
										class="placeholder">Minimum Stock</span>
									</label>

								</div>



								<div class="mb-2 row" style="margin: 0.5%;" style="display:none">
									<label class="custom-field one col-lg-0"> <input
										style="display: none" type="text" name="imageId" id="imageId"
										placeholder="" value="${imageId}"
										class="form-control form-control-sm" maxlength="10" /> <span
										class="placeholder" style="display: none">Image Id</span>
									</label> <label class="custom-field one col-lg-7"> <input
										type="text" name="description" id="description" placeholder=""
										class="form-control form-control-sm" /> <span
										class="placeholder">Description</span>
									</label>
									<div class="custom-field one col-md-2">
										<div class="input-boxx active-greyy">
											<label class="input-labell" for="hsnCode">HSN Code</label> <select
												class="input-11 form-control form-control-sm" id="hsnCode"
												name="hsnCode">
												<option value="">--select--</option>
												<c:forEach items="${hsnMaster.payload}" var="hsnMaster">
													<option value="${hsnMaster.hsnCode}">${hsnMaster.hsnCode}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<label class="custom-field one col-lg-3"> <input
										type="text" name="unitPrice" id="unitPrice" placeholder=""
										class="form-control form-control-sm" maxlength="10" /> <span
										class="placeholder">Unit Price</span>
									</label>
								</div>


								<div class="mb-2 row" style="margin: 0.5%;"display:none" ">
									<label class="custom-field one col-lg-0"> <input
										style="display: none" type="text" name="userId" id="userId"
										value="${empId}" placeholder=""
										class="form-control form-control-sm" readonly /> <span
										class="placeholder" style="display: none">User Id</span>
									</label> <label class="custom-field one col-lg-9"> <input
										type="text" name="remarks" id="remarks" placeholder=""
										class="form-control form-control-sm" /> <span
										class="placeholder">Remarks</span>
									</label><label class="custom-field one col-lg-3"> <input
										type="number" name="quantityInStock" id="quantityInStock"
										placeholder="" class="form-control form-control-sm" /> <span
										class="placeholder">Quantity In Stock</span>
									</label>
								</div>
								<div style="height: 10px;"></div>

							</div>
						</div>


						<div class="col-md-2 col-sm-12 mt-3">
							<div class="input-group">
								<span class="input-group-text"> <img src="./img/img.png"
									id="displayedImage" alt="Image Id Icon" width="120"
									height="120" id="image" />
								</span> <label class="custom-field one mt-3" style="width: 100%;">
									<input type="file" placeholder="" name="image" id="imageInput"
									class="form-control form-control-sm" accept="image/*" /> <span
									class="placeholder">Upload Image</span>
								</label>
							</div>
						</div>
						<div class="col-lg-12 m-1">
							<div class="row">
								<div class="col-md-12">
									<div class="form-btn">
										<button type="button" class="btn btn-outline-warning"
											onclick="deactivateItemMaster()" title="Deactivate Item">
											<i class="fa-solid fa-ban"></i>
										</button>
										<button type="button" class="btn btn-outline-danger"
											onclick="deleteItemMaster()" title="Delete Item">
											<i class="fas fa-trash"></i>
										</button>
										<button type="button" class="btn btn-outline-info"
											onclick="editItemImage()">Edit</button>
										<button type="button" class="btn btn-primary"
											onclick="saveNewImage()">Save</button>
									</div>
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
	<script src="./js/itemMaster.js"></script>
	<script src="./js/tmsDataTables.js"></script>
	<script src="./externalJS/multilingual.js"></script>
	<script src="./js/googleTranslateElementInit.js"></script>
</body>
</html>