<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ page import="org.springframework.http.HttpStatus"%>
<%@ page import="com.doritech.api.Entity.*"%>
<%@ page import="com.doritech.api.DTO.VendorMasterDTO"%>
<%@ page import="com.doritech.api.Entity.ParamEntity"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>IMS</title>


<link rel="stylesheet" href="./css/clientEntity.css" />
<link rel="stylesheet" href="./css/multilingual.css" />
<link rel="stylesheet" href="./externalCSS/bootstrap.min.css" />
<script src="./externalJS/fontawesome.js"></script>

<!-- DATATABLES CSS Links -->

<link rel="stylesheet" href="./externalCSS/dataTables.bootstrap4.min.css">
<link rel="stylesheet" href="./externalCSS/buttons.dataTables.min.css">
<link rel="stylesheet" href="./externalCSS/responsive.bootstrap4.min.css">

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
<style type="text/css">
td a {
	color: black;
	text-decoration: none !important;
}
</style>
</head>
<body style="font-size: 12px; background-color: #DCECFE;">

	<div class="col-md-12">

		<h5 class="heading">Client Master</h5>

	</div>

	<div class="row">
		<div class="col-md-12">
			<div class="card-container">
				<div class="col-md-4">
					<div class="card" style="">
						<div class="">
							<table id="example" class="table table-striped table-bordered">
								<thead>
									<tr>
										<th>Code</th>
										<th>Name</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${clientList.payload}" var="client">
										<tr>
											<td><a href="#"
												onclick="fetchClient('${client.clientId}')">${client.clientId}</a></td>
											<td><a href="#"
												onclick="fetchClient('${client.clientId}')">${client.clientName}</a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>

				<div class="col-md-8">
					<div class="card">
						<div class="container">
							<div class="row">
								<div class="col-md-12">
									<div class="">
										<div class="mt-2"></div>
										<form id="clientForm" name="clientForm">
											<div style="border: 1.75px solid #6595ff;">
												<b style="margin-left: 0.5%; color: #1649A2;">Client
													Details</b>
												<div class="mb-3 row" style="margin: 1%;">
													<label class="custom-field one col-md-2"> <input
														type="text" placeholder=""
														class="form-control form-control-sm" id="clientId"
														value="${clientId}" readonly /> <span
														class="placeholder">Client ID</span>
													</label> <label class="custom-field one col-md-4"> <input
														type="text" placeholder=""
														class="form-control form-control-sm " id="clientName" />
														<span class="placeholder">Client Name</span>
													</label> <label class="custom-field one col-md-4"> <input
														type="text" placeholder=""
														class="form-control form-control-sm "
														id="contactPerson" /> <span class="placeholder">Contact
															Person</span>
													</label>
													<label class="custom-field one col-md-2"> <input
														type="text" placeholder=""
														class="form-control form-control-sm" id="empId"
														value="${empId}" readonly /> <span class="placeholder">User
															ID</span>
													</label>
												</div>
												<div class="mb-3 row" style="margin: 1%;">
													 <label class="custom-field one col-md-4"> <input
														type="date" placeholder=""
														class="form-control form-control-sm " maxlength="10"
														id="entryDate" /> <span class="placeholder">Entry
															Date</span>
													</label>
												</div>
											</div>
											<div class="mt-2"></div>
											<div style="border: 1.75px solid #6595ff;">
												<b style="margin-left: 0.5%; color: #1649A2;">Address
													Details</b>
												<div class="mb-3 row" style="margin: 0.5%;">
													<label class="custom-field one col-md-12"> <input
														type="text" placeholder=""
														class="form-control form-control-sm " id="address" /> <span
														class="placeholder">Address</span>
													</label>
												</div>
												<div class="mb-3 row" style="margin: 0.5%;">
													<label class="custom-field one col-md-2"> <input
														type="text" placeholder=""
														class="form-control form-control-sm " id="pin" /> <span
														class="placeholder">Pincode</span>
													</label> <label class="custom-field one col-md-3"> <input
														type="text" placeholder=""
														class="form-control form-control-sm" maxlength="10"
														id="city" /> <span class="placeholder">City</span>
													</label> <label class="custom-field one col-md-3"> <input
														type="text" placeholder=""
														class="form-control form-control-sm " id="state" /> <span
														class="placeholder">State</span>
													</label> <label class="custom-field one col-md-4"> <input
														type="text" placeholder=""
														class="form-control form-control-sm " id="country" /> <span
														class="placeholder">Country</span>
													</label>
												</div>
											</div>
											<div class="mt-2"></div>
											<div style="border: 1.75px solid #6595ff;">
												<b style="margin-left: 0.5%; color: #1649A2;">Contact
													Details</b>
												<div class="mb-3 row" style="margin: 0.5%;">
													<label class="custom-field one col-md-4"> <input
														type="number" placeholder=""
														class="form-control form-control-sm " maxlength="10"
														id="mobile" /> <span class="placeholder">Mobile</span>
													</label> <label class="custom-field one col-md-6"> <input
														type="email" placeholder=""
														class="form-control form-control-sm" id="email" /> <span
														class="placeholder">E-mail</span>
													</label>
												</div>
											</div>
											<div class="mt-2"></div>
											<div style="border: 1.75px solid #6595ff;">
												<b style="margin-left: 0.5%; color: #1649A2;">Client
													Status</b>
												<div class="col-md-4">
													<select class="form-control form-control-sm " name="status"
														id="status">
														<option value="#">Select Status</option>
														<option value="Active">Active</option>
														<option value="Inactive">De-Active</option>
													</select>
												</div>
												<div style="height: 10px;"></div>
											</div>
											<div class="mt-1"></div>
											<div class="row">
												<div class="col-md-12">
													<div class="form-btn">
														<button type="button" class="btn btn-outline-warning"
															data-toggle="tooltip" data-placement="top" title="Edit"
															onclick="editClient()">
															<i class="fa-regular fa-pen-to-square"
																style="color: #FFD43B;"></i>
														</button>
														<button type="button" class="btn btn-primary"
															onclick="saveVendor();">Save</button>
														<button type="button" class="btn btn-danger"
															onclick="resetForm();">Reset</button>

													</div>
												</div>
											</div>
										</form>
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
	<script src="./js/clientEntity.js"></script>
	<script src="./js/tmsDataTables.js"></script>
	<script src="./externalJS/multilingual.js"></script>
	<script src="./js/googleTranslateElementInit.js"></script>
</body>
</html>