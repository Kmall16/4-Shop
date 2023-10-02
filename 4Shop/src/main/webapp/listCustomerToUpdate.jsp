<%-- 
    Document   : listCustomerToUpdate
    Created on : Jul 12, 2023, 7:54:04 PM
    Author     : MY LAPTOP
--%>

<%@page import="Models.Customer"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DAOs.CustomerDAO"%>
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
        <%  cusDAO = new CustomerDAO();
            List<Customer> listCus = new ArrayList<>();
            listCus = cusDAO.getAllCustomer();
            request.getSession().setAttribute("listCus", listCus);
        %>
        <section class="background-radial-gradient overflow-hidden" style="height: 1000px">
            <div class="container px-4 py-5 px-md-5 text-center text-lg-start my-5">
                <div class="row gx-lg-5 align-items-center mb-5">
                    <table class="table table-hover table-bordered">
                        <thead class="table-dark">
                            <tr>
                                <th scope="col">No</th>
                                <th scope="col">Name</th>
                                <th scope="col">User Name</th>
                                <th scope="col">Phone</th>
                                <th scope="col">Email</th>
                                <th scope="col">Gender</th>
                                <th scope="col">Address</th>
                                <th scope="col">Role</th>
                                <th scope="col">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="stt" value="0"/>
                            <c:forEach items="${sessionScope.listCus}" var="cus">                                
                                <c:set var="stt" value="${stt+1}"/>
                                <tr>
                                    <th scope="row">${stt}</th>
                                    <td>${cus.name}</td>
                                    <td>${cus.userName}</td>
                                    <td>${cus.phone}</td>
                                    <td>${cus.email}</td>
                                    <td>${cus.gender}</td>
                                    <td>${cus.address}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${cus.isAdmin()}">
                                                Admin
                                            </c:when>
                                            <c:otherwise>
                                                Customer
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <div class="d-flex align-items-center justify-content-center">
                                            <!--<a class="btn btn-outline-primary" href="/customer/update/${cus.userName}">Update</a>-->
                                        <c:if test="${!cus.isAdmin()}">
                                            <a onclick="return confirm('The ${cus.name}\'s account will be an ADMIN. Are you sure?');" class="btn btn-outline-primary" href="/customer/admin/${cus.userName}">Be Admin</a>
                                        </c:if>
                                        
                                        <a onclick="return confirm('The ${cus.name}\'s account will be deleted forever. Are you sure?');" class="ms-1 btn btn-outline-danger" href="/customer/delete/${cus.id}">Delete</a>
                                        </div>
                                        
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
