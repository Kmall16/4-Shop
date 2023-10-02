<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="Component/boostrap.jsp" %>
        <style>
            .product-image {
                width: 100%;
                height: auto;

                margin-bottom: 1rem;
                border-radius: 10px;
            }

            .person-image {
                width: 100%;
                height: auto;

                margin-bottom: 1rem;
                border-radius: 10px;
            }
        </style>
        <title>4SHOP</title>

    </head>
    <body>
        <%@include file="Component/header.jsp" %>
        <div class="container-fluid background-radial-gradient">
            <div class="container px-4 py-5 px-md-5 text-center text-lg-start">
                <div class="row align-items-center">
                    <div class="position-relative">
                        <div id="radius-shape-1" class="position-absolute rounded-circle shadow-5-strong"></div>
                        <div id="radius-shape-2" class="position-absolute shadow-5-strong"></div>

                        <div class="card bg-glass mb-6">
                            <div class="card-body px-4 py-5 px-md-5">
                                <div class="row">
                                    <div class="col-md-6 mb-4">
                                        <div class="person-card">
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <img src="/images/AboutUs/person1.png" alt="Product 1" class="person-image">
                                                </div>
                                                <div class="col-md-6">
                                                    <h3>Đỗ Đăng Khoa</h3>
                                                    <p></p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6 mb-4">
                                        <div class="person-card">
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <img src="/images/AboutUs/person2.png" alt="Product 1" class="person-image">
                                                </div>
                                                <div class="col-md-6">
                                                    <h3>Nguyễn Anh Duyệt</h3>
                                                    <p></p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6 mb-4">
                                        <div class="person-card">
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <img src="/images/AboutUs/person3.png" alt="Product 1" class="person-image">
                                                </div>
                                                <div class="col-md-6">
                                                    <h3>Nguyễn Minh Khôi</h3>
                                                    <p></p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6 mb-4">
                                        <div class="person-card">
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <img src="/images/AboutUs/person4.png" alt="Product 1" class="person-image">
                                                </div>
                                                <div class="col-md-6">
                                                    <h3>Nguyễn Thị Trà My</h3>
                                                    <p></p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
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