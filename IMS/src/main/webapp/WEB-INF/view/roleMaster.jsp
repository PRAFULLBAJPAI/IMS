<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ page import="org.springframework.http.HttpStatus"%>
<%@ page import="com.doritech.api.Entity.MenuAccessEntity"%>
<%@ page import="com.doritech.api.Entity.ParamEntity"%>
<%@ page import="com.doritech.api.DTO.MenuAccessDTO"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>IMS</title>

<link rel="stylesheet" href="./externalCSS/bootstrap.min.css">
<link rel="stylesheet" href="./externalCSS/googleFonts.css">
<link rel="stylesheet" href="./css/roleMaster.css" />
<link rel="stylesheet" href="./css/multilingual.css" />
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
</head>
<body style="font-size: 14px; overflow-x:hidden;">
	<div class="col-md-12 mt-3">
			<div class="row">
				<div class="col-md-12">
					<h5 class="heading">Role Master</h5>
				</div>
				
				
			</div>
		</div>
	</div>

	<!--------------- This is button for Modal to add role-------------------------------------->
	<div class="card" style="margin: 0.5%;">

		<table id="example"
			class="table table-striped table-bordered table-wrapped">
			<thead>
				<tr>
					<th>Role</th>
					<th>View Access</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${rolesList.payload}" var="rolesList">
					<tr>
						<td value="${rolesList.serial}">${rolesList.serial.toUpperCase()}</td>
						<td><button class="submit-btn view-button"
								id="roleMasterModalButton" style="font-size: 12px;"
								onclick="loadMenuData('${rolesList.serial}')">View</button></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>


	<!--------------------------- Structure of role master---------------------------------------->
	<div class="modal" id="roleMasterModal">
		<div class="modal-dialog">
			<div class="modal-content">

				<div class="modal-header">
					<b class="modal-title" style="font-size: 14px;"><i
						class="fa-solid fa-pen-ruler"></i> <span style="color: #443ea2;">IMS</span></b>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<div class='dashboard-content'>
						<div class='container'>
							<div class='card'>
								<div class="card-header">
									<h5>Menu from Menu Master</h5>
								</div>
								<div class='card-body'>
									<section class="get-in-touch">

										<form class="contact-form row" name="roleMaster">
											<table id="example"
												class="table table-striped table-bordered table-wrapped menuTable"
												style="margin-left: 10%; width: 80%;">
												<thead>
													<tr>
														<th>Menu Id</th>
														<th>Menu Name</th>
														<th>Action</th>
													</tr>
												</thead>
												<tbody>

												</tbody>
											</table>
										</form>
									</section>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script src="./externalJS/bootstrap.bundle.min.js"></script>
	<script src="./js/roleMaster.js"></script>
	<script src="./js/tmsDataTables.js"></script>
	<script src="./externalJS/multilingual.js"></script>
	<script src="./js/googleTranslateElementInit.js"></script>
</body>
</html>