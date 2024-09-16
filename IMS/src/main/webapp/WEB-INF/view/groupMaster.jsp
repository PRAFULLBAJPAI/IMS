<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ page import="org.springframework.http.HttpStatus"%>
<%@ page import="com.doritech.api.Entity.GroupMasterEntity"%>
<%@ page import="com.doritech.api.DTO.GroupMasterDTO"%>
<%@ page import="com.doritech.api.Entity.ParamEntity"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Group Details</title>
<link rel="stylesheet" href="./externalCSS/bootstrap.min.css">
<link rel="stylesheet" href="./externalCSS/googleFonts.css">
<link rel="stylesheet" href="./css/groupMaster.css" />
<link rel="stylesheet" href="./css/question.css" />
<script src="./externalJS/fontawesome.js"></script>

<!-- DATATABLES CSS Links -->

<link rel="stylesheet"
	href="./externalCSS/dataTables.bootstrap4.min.css">
<link rel="stylesheet" href="./externalCSS/buttons.dataTables.min.css">
<link rel="stylesheet"
	href="./externalCSS/responsive.bootstrap4.min.css">
<link rel="stylesheet" href="./css/multilingual.css" />


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
<body style="font-size: 12px;">
	<div class="row mx-1">
		<div class="col-md-12">
			<div class="row">
				<div class="col-md-12">
					<h5 class="heading">Group Details</h5>
				</div>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-md-12">
			<div class="card-container">
				<div class="col-md-4">
					<div class="card" style="">
						<div class="">
							<table id="example"
								class="table table-striped table-bordered table-wrapped">
								<thead>
									<tr>
										<th>Group ID</th>
										<th>Group Name</th>
										<th>Date of Creation</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="group" items="${groupMaster.payload}">
										<tr>
											<td><a href="#"
												onclick="fetchGroupById('${group.groupId}')">${group.groupId}</a></td>
											<td><a href="#"
												onclick="fetchGroupById('${group.groupId}')">${group.groupName}</a></td>
											<td><a href="#"
												onclick="fetchGroupById('${group.groupId}')">${group.createdDate}</a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>

				<div class="col-md-8">
					<div class="card">
						<div class="mx-2">
							<div class="row">
								<div class="col-md-12">
									<div class="">
										<div class="mt-2"></div>
										<form id="groupForm" name="groupName">

											<div style="border: 1.75px solid #6595ff;">
												<b style="margin-left: 0.5%; color: #1649A2;">Group
													Master</b>
												<div class="mb-3 row" style="margin: 1%;">
													<label class="custom-field one col-md-6"> <input
														type="text" placeholder=""
														class="form-control form-control-sm" id="groupName"
														name="groupName" value="" /> <span class="placeholder">Group
															Name</span>
													</label> <label class="custom-field one col-md-6"> <input
														type="text" placeholder="" id="groupDescription"
														name="groupDescription"
														class="form-control form-control-sm " id="empName" /> <span
														class="placeholder">Group Description</span>
													</label>
												</div>
												<div class="mb-3 row" style="margin: 1%;">
													<div class="custom-field one col-md-3">
														<div class="input-boxx active-greyy">
															<label class="input-labell">Employee Name</label> <select
																class="form-control form-control-sm " name="empId"
																id="empId">
																<option value="#">--select--</option>
																<c:forEach var="employee" items="${employeeList}">
																	<option value="${employee.empId}">${employee.empId}
																		: ${employee.empName}</option>
																</c:forEach>
															</select>
														</div>
													</div>
													<label class="custom-field one col-md-3"> <input
														type="date" placeholder="" id="createdDate"
														name="createdDate" class="form-control form-control-sm "
														maxlength="10" /> <span
														class="placeholder">Date of Creation</span>
													</label>
												</div>
											</div>
											<div class="mt-1"></div>
											<div class="row">
												<div class="col-md-12">
													<div class="form-btn">
														<button type="button" class="btn btn-primary"
															onclick="saveNewGroup();">Save</button>
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

	<script src="./js/navBarToggle.js"></script>
	<script src="./externalJS/bootstrap.bundle.min.js"></script>
	<script src="./js/groupMaster.js"></script>
	<script src="./js/tmsDataTables.js"></script>
	<script src="./externalJS/multilingual.js"></script>
	<script src="./js/googleTranslateElementInit.js"></script>
</body>
</html>