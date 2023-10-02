<%-- 
    Document   : order
    Created on : Jul 13, 2023, 7:49:13 PM
    Author     : MY LAPTOP
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>4SHOP</title>
         <%@include file="Component/boostrap.jsp" %>
    </head>
    <body>
        <%@include file="Component/header.jsp" %>
        <section class="background-radial-gradient overflow-hidden" style="height: 1000px">
            <div class="container px-4 py-5 px-md-5 text-center text-lg-start my-5">
                <div class="row gx-lg-5 align-items-center mb-5">
                    <table class="table table-hover table-bordered" style="text-align: center">
                        <thead class="table-dark">
                            <tr>
                                <th scope="col">Order ID</th>
                                <th scope="col">Customer Name</th>
                                <th scope="col">Total</th>
                                <th scope="col">Date</th>
                                <th scope="col">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${sessionScope.listOrd}" var="o">                                
                                <tr>
                                    <th scope="row">${o.orderID}</th>
                                    <td scope="row">${o.getCustomerName()}</th>
                                    <td  align="right"><fmt:formatNumber pattern="##.##" value="${o.total}"/>$</td>
                                    <td>${o.date}</td>
                                    <td>
                                        <a href="/order/detail/${o.orderID}" class="btn btn-outline-info">More</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </section>
        <%@include file="Component/footer.jsp" %>
    </body>
</html>
