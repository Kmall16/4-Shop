<%@page import="Models.Customer"%>
<%@page import="DAOs.CustomerDAO"%>
<%@page import="Models.Product"%>
<%@page import="java.util.List"%>
<%@page import="DAOs.ProductDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%  ProductDAO proDAO = new ProductDAO();
    List<String> listType = proDAO.listType();
    request.getSession().setAttribute("listType", listType); //hien ra cac loai san pham
    List<Product> combo = proDAO.listCombo();
    request.getSession().setAttribute("listC", combo); //combo o trang index
%>
<%            Cookie[] cookies = request.getCookies();
    CustomerDAO cusDAO = new CustomerDAO();
    if (cookies != null) {
        for (Cookie cooky : cookies) {
            if (cooky.getName().equals("customer")) {
                Customer cus = cusDAO.getCus(cooky.getValue());
                request.getSession().setAttribute("role", cus);
                break;
            }
        }
    }
%>
<c:set var="size" value="${sessionScope.size}"></c:set>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
        <div class="container-fluid">
            <a class="navbar-brand" href="/product/home">
                <img src="/images/logo4Shop.jpg" alt="" width="60" height="60">
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Product
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <c:forEach items="${sessionScope.listType}" var="type">
                            <li><a class="dropdown-item" href="/product/type/${type}">${type}</a></li>
                            </c:forEach>
                        <!--<li><a class="dropdown-item" href="/cart/list">Cart</a></li>-->
                    </ul>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="/customer/aboutUs">About Us</a>
                </li>

                <c:if test="${sessionScope.role!=null}">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Account
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <c:choose>
                                <c:when test="${sessionScope.role.isAdmin()}"> <!-- neu la admin thi duoc update tat ca khach hang -->
                                    <li><a class="dropdown-item" href="/customer/updateCustomer">Update Customer</a></li>
                                    </c:when>
                                    <c:otherwise> <!-- khong phai admin chi duoc update thong tin ca nhan -->
                                    <li><a class="dropdown-item" href="/customer/update">Update</a></li>
                                    </c:otherwise>
                                </c:choose>


                            <li><a onclick="return confirm('This ${sessionScope.role.getName()}\'s account will be deleted forever. Are you sure?');" class="dropdown-item text-danger" href="/customer/delete">Remove/Delete</a></li>
                        </ul>
                    </li>
                </c:if>

                <c:if test="${sessionScope.role.isAdmin()}"> <!-- neu la admin thi duoc crud san pham -->
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Manage
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <!--add new product button-->
                            <li><a class="dropdown-item" href="/product/addnew">Add New</a></li>
                            <!-- dong phan cach -->
                            <li><hr class="dropdown-divider"></li>
                            <!-- update product button -->
                            <li><a class="dropdown-item" href="/product/updatePage">Update</a></li>
                        </ul>
                    </li>
                </c:if>

                <c:choose>
                    <c:when test="${sessionScope.role.isAdmin()}">
                        <li class="nav-item">
                            <a class="nav-link" href="/order/allList">Order</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item">
                            <a class="nav-link" href="/order/list">Order</a>
                        </li>
                    </c:otherwise>
                </c:choose>                    

            </ul>
            <form class="d-flex flex-grow-1 mt-1" action="/product" method="post">
                <!--input to search-->
                <input class="flex-grow-1 me-1 px-1" style="border-radius: 15px" name="txtSearch" class="form-control me-1" type="search" placeholder="Search" aria-label="Search" <c:if test="${oldSearch!=null}">value="${oldSearch}" </c:if>>
                    <!--search button-->
                    <!--<input name="btnSearch" class="btn btn-outline-success me-1" type="submit" value="Search">-->
                    <button title="Search" type="submit" name="btnSearch" value="Search" class="btn btn-outline-success me-1 d-flex align-items-center justify-content-center">
                        <ion-icon name="search-outline"></ion-icon>
                    </button>
                </form>

                <div class="d-flex mt-1">
                    <a href="/cart/list" class="btn btn-outline-warning px-1 d-flex align-items-center justify-content-center me-1" style="width: 11rem;">
                        <div class="d-flex align-items-center justify-content-center">
                            <ion-icon name="cart-outline"  ></ion-icon>
                        </div>
                        <div class="flex-grow-1">Shopping Cart
                        <c:if test="${requestScope.size!= 0}">
                            ${size}
                        </c:if>

                    </div>      

                </a>
                <c:if test="${sessionScope.role!=null}">  <!-- neu dang nhap roi thi duoc log out va cac chuc nang khac -->


                    <!--cart button-->

                    <!--update profile button-->
                    <a href="/customer/update" class="btn btn-outline-info d-flex align-items-center justify-content-center me-1" style="width: 10rem;">
                        <div class="d-flex align-items-center justify-content-center">
                            <ion-icon name="person-circle-outline"></ion-icon>
                        </div>
                        <div class="flex-grow-1">${sessionScope.role.getName()}</div>                       
                    </a>
                    <!--log out button-->
                    <a href="/customer/logout" class="btn btn-outline-danger d-flex align-items-center justify-content-center" style="width: 10rem;">                        
                        <div class="d-flex align-items-center justify-content-center">
                            <ion-icon name="log-out-outline" ></ion-icon>
                        </div>
                        <div class="flex-grow-1">Log Out</div> 
                    </a>



                </c:if>

                <c:if test="${sessionScope.role==null}">
                    <a href="/customer/login" class="btn btn-outline-primary d-flex align-items-center justify-content-center" style="width: 10rem;">                   
                        <div class="d-flex align-items-center justify-content-center">
                            <ion-icon name="log-in-outline"></ion-icon>
                        </div>
                        <div class="flex-grow-1">Log In</div> 
                    </a>
                </c:if>
            </div>
        </div>
    </div>
</nav>
<!--Do cao cua menu-->
<div style="height: 86px"></div> 
