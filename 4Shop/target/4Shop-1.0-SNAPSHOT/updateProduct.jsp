<%-- 
    Document   : addNewProduct
    Created on : Jul 7, 2023, 1:53:26 PM
    Author     : MY LAPTOP
--%>

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
        <section class="background-radial-gradient overflow-hidden">
            <div class="container px-4 py-5 px-md-5 text-center text-lg-start my-5">
                <div class="row gx-lg-5 align-items-center mb-5">
                    <div class="col-lg-6 mb-5 mb-lg-0" style="z-index: 10">
                        <h1 class="my-5 display-5 fw-bold ls-tight" style="color: hsl(218, 81%, 95%)">
                            The best offer <br />
                            <span style="color: hsl(218, 81%, 75%)">for your style</span>
                        </h1>
                        <p class="mb-4 opacity-70" style="color: hsl(218, 81%, 85%)">

                            4Shop is a clothing store that provides a wide range of trendy and fashionable apparel. 
                            With our diverse selection of clothes, you can express your unique style and stay ahead of the fashion curve. 
                            We strive to offer high-quality garments that combine comfort, style, and affordability. 
                            Whether you're looking for casual wear, formal attire, or accessories to complete your look, 4Shop has got you covered. 
                            Experience the joy of shopping for the latest fashion trends at 4Shop and elevate your wardrobe to new heights.
                        </p>
                    </div>

                    <div class="col-lg-6 mb-5 mb-lg-0 position-relative">
                        <div id="radius-shape-1" class="position-absolute rounded-circle shadow-5-strong"></div>
                        <div id="radius-shape-2" class="position-absolute shadow-5-strong"></div>

                        <div class="card bg-glass">
                            <div class="card-body p-4 p-md-5">
                                <form method="post" enctype="multipart/form-data" action="/product"  id="productForm">
                                    <!-- Email input -->
                                    <div class="mb-4">
                                        <label class="form-label" for="form3Example3">ID:</label>
                                        <input readonly="" type="text" name="productID"  class=" form-control" value="${productToUpdate.id}"/>
                                    </div>
                                    
                                    <div class="mb-4">
                                        <label class="form-label" for="form3Example3">Name:</label>
                                        <input required type="text" name="productName"  class="form-control" value="${productToUpdate.name}"/>
                                    </div>

                                    <!-- Password input -->
                                    <div class="mb-4">
                                        <label class="form-label" for="form3Example4">Type:</label>
                                        <input required type="text" name="productType" class="form-control" value="${productToUpdate.type}"/>
                                    </div>

                                    <div class="mb-4">
                                        <label class="form-label" for="form3Example4">Price:</label>
                                        <input required type="number" pattern="^(0|[1-9]\d{0,2})(\.\d{2})?$" name="productPrice" class="form-control" value="${productToUpdate.price}"/>
                                    </div>

                                    <div class="mb-4">
                                        <label class="form-label" for="form3Example4">Old Image:</label>
                                        <img class="form-control" src="${productToUpdate.imgURL}" style="object-fit: contain" alt="alt"/>
                                    </div>
                                    
                                    <div class="mb-4">
                                        <label class="form-label" for="form3Example4">New Image:</label>
                                        <input type="file" name="productImg" class="form-control"/>
                                    </div>
                                    <!-- Submit button -->
                                    <div style="text-align: center">

                                        <input type="submit" class="btn btn-primary btn-block mb-4" name="btnUpdate" value="Update">
                                    </div>

                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <%@include file="Component/footer.jsp" %>
    </body>
</html>
