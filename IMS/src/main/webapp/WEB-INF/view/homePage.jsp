<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.springframework.http.HttpStatus"%>
<%@ page import="com.doritech.api.Entity.ParamEntity"%>
<%@ page import="com.doritech.api.Entity.ItemMasterEntity"%>
<%@ page import="com.doritech.api.DTO.ItemMasterDTO"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>IMS</title>
<!-------------------------- CSS Links---------------------------- -->
<link rel="stylesheet" href="./css/homePage.css" />
<link rel="stylesheet" href="./externalCSS/bootstrap.min.css" />
<script src="./externalJS/jquery-3.7.0.js"></script>
<script src="./externalJS/bootstrap.min.js"></script>
<script src="./externalJS/fontawesome.js"></script>
<script src="./externalJS/chart.min.js"></script>
<style>
iframe {
	border: 0;
	width: 100%;
}
.right-align {
    text-align: right;
}
.left-align {
    text-align: left;
}

</style>
</head>
<body style="">
	<span class="toggle"></span>
	<div class="cardBox ">
		<div id="totalCount" class="card" style="border: 1.5px solid #59D5E0;"
			onclick="resetTable()">
			<div>
				<div class="iconBox" style="float: right;">
					<img alt="total_product" type="icon" src="./img/total_products.png"
						class="" />
				</div>
				<div class="numbers" value="${totalCount}">${totalCount}</div>
				<div class="cardName">Total Products</div>
			</div>

		</div>
		<div id="rawCount" class="card" style="border: 1.5px solid #F4538A;"
			onclick="filterRowsByProductType('RAW')">
			<div>
				<div class="iconBox" style="float: right;">
					<img alt="raw_product" type="icon" src="./img/raw_item.png" class="" />
				</div>
				<div class="numbers" value="${rawCount}">${rawCount}</div>
				<div class="cardName">Raw Products</div>
			</div>

		</div>
		<div id="subproductCount" class="card"
			style="border: 1.5px solid #F5DD61;"
			onclick="filterRowsByProductType('SUBPRODUCT')">
			<div>
				<div class="iconBox" style="float: right;">
					<img alt="sub_product" type="icon" src="./img/sub_product.png"
						class="" />
				</div>
				<div class="numbers" value="${subproductCount}">${subproductCount}</div>
				<div class="cardName">Sub Products</div>
			</div>

		</div>
		<div id="finalProductCount" class="card"
			style="border: 1.5px solid #65B741;"
			onclick="filterRowsByProductType('FINAL PRODUCT')">
			<div>
				<div class="iconBox" style="float: right;">
					<img alt="final_product" type="icon" src="./img/final_product.png"
						class="" />
				</div>
				<div class="numbers" value="${finalProductCount}">${finalProductCount}</div>
				<div class="cardName">Final Products</div>
			</div>

		</div>
	</div>

				<div class=" details">
		<!-- complaint details list -->
		<div class="recentData">
			<div class="cardHeader">
				<h5 style="margin-left:2%; text-decoration: underline;">Product Details</h5>
			</div>
			<div class="tableWrapper">
			<table id="productTable" class="table table-responsive2 table-striped table-bordered" style="border: 0px solid #dee2e6;">
				<thead>
					<tr>
						<th class="col-md-2">Product Name</th>
						<th class="col-md-1">Product Type</th>
						<th class="col-md-2">Item Source</th>
						<th class="col-md-1">Quantity In Stock</th>
						<th class="col-md-1">Unit Price</th>
						<th class="col-md-2">Entry Date</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${productList.payload}" var="product">
						<tr>
							<td class="left-align">${product.itemName}</td>
							<td class="left-align">${product.itemType}</td>
							<td class="left-align">${product.itemSourceName}</td>
							<td class="right-align">${product.quantityInStock}</td>
							<td class="right-align">${product.unitPrice}</td>
							<td class="left-align">${product.entryDate}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div> 
</div>

	<script>
		$(document).ready(function() {
			function resetTable() {
				$("#productTable tbody tr").show();
			}
			function filterRowsByProductType(productType) {
				$("#productTable tbody tr").show();

				$("#productTable tbody tr").filter(function() {
					return $("td:eq(1)", this).text() !== productType;
				}).hide();
			}
			$("#totalCount").click(function() {
				resetTable();
			});

			$("#rawCount").click(function() {
				filterRowsByProductType("RAW");
			});
			$("#subproductCount").click(function() {
				filterRowsByProductType("SUBPRODUCT");
			});
			$("#finalProductCount").click(function() {
				filterRowsByProductType("FINAL PRODUCT");
			});
		});
	</script>

	
	<script src="./js/homePage.js"></script>
</body>
</html>