<%-- 
    Document   : cart.jsp
    Created on : Jul 9, 2023, 1:48:48 PM
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
                                <th scope="col">No</th>
                                <th scope="col">Product Name</th>
                                <th scope="col">Product Price</th>
                                <th scope="col">Quantity</th>
                                <th scope="col">Sum</th>
                                <th scope="col">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set value="${sessionScope.cart}" var="o" />
                            <c:set var="stt" value="0"/>
                            <c:forEach items="${o.items}" var="i">                                
                                <c:set var="stt" value="${stt+1}"/>
                                <tr>
                                    <th scope="row">${stt}</th>
                                    <td>${i.product.name}</td>
                                    <td><fmt:formatNumber pattern="##.##" value="${i.product.price}"/>$</td>
                                    <td>
                                        <a href="/cart/down/${i.product.id}" class="btn btn-outline-primary">-</a>
                                        ${i.quantity}
                                        <a href="/cart/up/${i.product.id}" class="btn btn-outline-primary">+</a>
                                    </td>
                                    <td  align="right"><fmt:formatNumber pattern="##.##" value="${i.product.price*i.quantity}"/>$</td>
                                    <td>
                                        <form action="/cart" method="post">
                                            <input type="hidden" name="id" value="${i.product.id}">
                                            <input type="submit" name="btnReturn" value="Return Item" class="btn btn-outline-danger">
                                        </form>                                        
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                        <tfoot>
                            <tr>
                                <th>Sum</th>
                                <c:choose>
                                    <c:when test="${o.getTotalMoney()!=null}">
                                     <td colspan="4" align="right"><fmt:formatNumber pattern="##.##" value="${o.getTotalMoney()}"/>$</td>
                                    </c:when>
                                    <c:otherwise>
                                        <td colspan="4" align="right"><fmt:formatNumber pattern="##.##" value="0"/>$</td>
                                    </c:otherwise>
                                </c:choose>
                                
                                <td>
                                    <a onclick="return confirm('Are you sure to check out?');" href="/order/checkout" class="btn btn-outline-success">
                                        Check Out
                                    </a>                                    
                                </td>
                            </tr>
                        </tfoot>
                    </table>
                </div>
            </div>
        </section>
        <%@include file="Component/footer.jsp" %>
    </body>
</html>
