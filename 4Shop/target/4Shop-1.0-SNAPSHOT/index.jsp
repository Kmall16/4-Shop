<%-- 
    Document   : index
    Created on : Jul 11, 2023, 2:46:04 PM
    Author     : MY LAPTOP
--%>
<%@page import="DAOs.CustomerDAO"%>
<%@page import="Models.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="Component/boostrap.jsp" %>
        <title>4Shop</title>
    </head>
    <body>        
        <%@include file="Component/header.jsp" %>
        
        <div class="container-fluid background-radial-gradient px-0">
            <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
                <div class="carousel-indicators">
                    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
                    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
                </div>
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <a  href="/product/list">
                            <img src="/images/banner01.jpg" class="d-block w-100 h-50" alt="..." style="object-fit: contain">

                        </a>
                    </div>
                    <div   class="carousel-item">
                        <a href="/cart/add/26">
                            <img src="/images/banner02.jpg" class="d-block w-100 h-50" alt="...">
                        </a>

                    </div>
                    <div  class="carousel-item">
                        <a href="/cart/add/28">
                            <img src="/images/banner03.jpg" class="d-block w-100 h-50" alt="...">
                        </a>

                    </div>
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>
            </div>

            <div class="container px-4 py-5 px-md-5 text-center text-lg-start">
                <div class="row align-items-center">
                    <div class="position-relative">
                        <div id="radius-shape-1" class="position-absolute rounded-circle shadow-5-strong"></div>
                        <div id="radius-shape-2" class="position-absolute shadow-5-strong"></div>

                        <div class="card bg-glass mb-6">
                            <div class="card-header">
                                <h2 class="mb-1">Combo Siêu Hạt Dẻ</h2>
                            </div>
                            <div class="card-body px-4 py-5 px-md-5">
                                <div class="row">
                                    <c:forEach items="${listC}" var="proCombo">
                                        <div class="col-12 col-lg-3 col-md-6 mb-4">
                                            <div class="product-card">
                                                <img src="${proCombo.imgURL}" alt="Product 1" class="card-img-top" style="height: 300px; object-fit: contain">

                                                <p>${proCombo.name}</p><p><del>${proCombo.price+10}$</del><strong>${proCombo.price}</strong></p>
                                                <a  href="/cart/add/${proCombo.id}" class="btn btn-outline-danger">
                                                    Add to Cart
                                                </a>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="Component/footer.jsp" %>
    </body>
</html>
