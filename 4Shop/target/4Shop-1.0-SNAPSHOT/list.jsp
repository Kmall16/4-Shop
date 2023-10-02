<%-- 
    Document   : list.jsp
    Created on : Jul 5, 2023, 3:53:38 PM
    Author     : MY LAPTOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <%@include file="Component/boostrap.jsp" %>
        <title>4SHOP</title>
       
    </head>
    <body>
        
        <%@include file="Component/header.jsp" %>        
        <div class="container-fluid background-radial-gradient" <c:if test="${sessionScope.listP.isEmpty()}">style="height: 100vh"</c:if>>
            <div class="row pb-4">
            <c:forEach items="${sessionScope.listP}" var="product">
                    <div class="col-12 col-lg-3 col-md-4 mb-4 ">
                        <div class="card h-100 position-relative mt-4 ms-3">
                            <c:if test="${sessionScope.role.isAdmin()}">

                                <a onclick="return confirm('The ${product.name} will be deleted forever. Are you sure?');" class="text-white  nav-link w-100" href="/product/delete/${product.id}">
                                    <span class="position-absolute translate-middle p-2 px-3 bg-danger border border-light rounded-circle">X</span>
                                </a>                                  

                            </c:if>
                            <img src="${product.imgURL}" alt="alt" class="card-img-top" style="height: 430px; object-fit: contain">
                            <div class="card-body" style="text-align: center">
                                <h4 ><a class="card-title" href="#" title="View Product" style="text-decoration: none">${product.name}</a></h4>
                                <p class="card-text">${product.type}</p>
                            </div>
                            <div class="card-footer">
                                <div class="row">
                                    <div class="col">
                                        <a href="#" class="btn btn-warning btn-block w-100">${product.price}$</a>
                                    </div>
                                    <div class="col">
                                        <a href="/cart/add/${product.id}" class="btn btn-danger btn-block w-100">Add</a>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                
                <c:if test="${sessionScope.listP.isEmpty()}">
                    <div style="text-align: center; color: white" >There's no product like "${oldSearch}"</div>
                </c:if>
            </div>
        </div>

        <%@include file="Component/footer.jsp" %>
    </body>
</html>
