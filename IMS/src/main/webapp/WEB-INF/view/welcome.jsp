<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.springframework.http.HttpStatus" %>
<%@ page import="com.doritech.api.Entity.MenuMasterEntity" %>
  
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>IMS</title>
<!-------------------------- CSS Links---------------------------- -->
<link rel="stylesheet" href="./externalCSS/bootstrap.min.css">
<link rel="stylesheet" href="./externalCSS/googleFonts.css">
<link rel="stylesheet" href="./css/welcomePage.css" />
<link rel="stylesheet" href="./css/multilingual.css" />
<script src="./externalJS/sweetalert2.all.min.js"></script>
<script src="./externalJS/fontawesome.js" ></script>

<style>
iframe{
 border:0;
 width:100%;
 margin-top:5px;
}

</style>
</head>
<body>
<div class='dashboard'>
    <div class="dashboard-nav">
        <header><a href="#!" class="menu-toggle"><i class="fas fa-bars"></i></a><a href="./homePage" target="adminRight" class="brand-logo"><i class="fa-solid fa-warehouse"></i> <span>IMS</span></a></header>
		<div class="mt-5"></div>
        <c:forEach items="${menuList.payload}" var="menu">
            <nav class="dashboard-nav-list">
                <c:choose>
                    <c:when test="${menu.parentId eq 0 and menu.childId eq 0}">
                        <a href="${menu.menuHandlerName}" class="dashboard-nav-item" target="adminRight">
                            <i class="${menu.menuIcon}"></i>${menu.menuName}
                        </a>
                    </c:when>
                    <c:when test="${menu.childId ne 0}">
                        <div class="submenu-container">
                            <a href="#" class="dashboard-nav-item with-submenu" style="font-size:12px;">
                                <i class="${menu.menuIcon}"></i>${menu.menuName}<i class="fa-sharp fa-solid fa-angle-down" style=" position:absolute; right:10%;"></i>
                            </a>
                            <div class="submenu" style="display: none;">
                                <ul style="margin-left:-17px;">
                                    <c:forEach items="${menuList.payload}" var="submenu">
                                        <c:if test="${submenu.parentId eq menu.childId}">
                                            <li>
                                                <a href="${submenu.menuHandlerName}" class="dashboard-nav-item submenu-item" target="adminRight" style="font-size:12px;">
                                                    <i class="${submenu.menuIcon}"></i>${submenu.menuName}
                                                </a>
                                            </li>
                                        </c:if>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </c:when>
                </c:choose>
            </nav>
        </c:forEach>

  
      
    <div class="nav-item-divider"></div>
        <footer class="company-footer">
        <p style="color:#ffff;">Powered By:</p>
        <img alt="doritech logo" src="./img/logo.png" class="logoCenter">
    </footer>
</div>
    
    <div class='dashboard-app'>
        <header class='dashboard-toolbar'><a href="#!" class="menu-toggle"><i class="fas fa-bars"></i></a><b>${paramClient.payload.descp1}</b>
        <form class="form-inline ml-auto">
       
    </form> &nbsp; &nbsp;&nbsp;
    
     <b style="margin-right:4%;">Welcome, ${loggedInUser}</b>
   <b style="margin-right:4%; margin-left:2%"><a href="#" class="" onclick="confirmLogout()"> Logout&nbsp;<i class="fas fa-sign-out-alt"></i></a></b>

    </header>
    
    <iframe src="./homePage" name="adminRight" id="adminRight" frameborder="0"></iframe>
  
    </div>
</div>

<!-- <script src="/externalJS/bootstrap.bundle.min.js"></script> -->
<script src="./externalJS/jquery-3.7.0.js"></script>
<script src="./js/welcomeToggle.js"></script>
<script src="./externalJS/multilingual.js"></script>
<script src="./js/googleTranslateElementInit.js"></script>

</body>
</html>