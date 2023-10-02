<%-- 
    Document   : NotLoginHeader
    Created on : Jul 4, 2023, 12:34:33 PM
    Author     : MY LAPTOP
--%>
<!--bootstrap-->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<link rel="icon" href="/images/logo4Shop.jpg" type="image/jpeg">
<!--for icon-->
<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
<!-- design background -->
<style>
    .background-radial-gradient {
        /*        background-color: hsl(218, 41%, 15%);
                background-image: radial-gradient(650px circle at 0% 0%,
                    hsl(218, 41%, 35%) 15%,
                    hsl(218, 41%, 30%) 35%,
                    hsl(218, 41%, 20%) 75%,
                    hsl(218, 41%, 19%) 80%,
                    transparent 100%),
                    radial-gradient(1250px circle at 100% 100%,
                    hsl(218, 41%, 45%) 15%,
                    hsl(218, 41%, 30%) 35%,
                    hsl(218, 41%, 20%) 75%,
                    hsl(218, 41%, 19%) 80%,
                    transparent 100%);*/
        background-color: #290022;
        background-image: radial-gradient(650px circle at 0% 0%,
            #fa005a 0%,
            #770033 20%,
            #4c0029 40%,
            #290022 75%,
            #290022 80%,
            transparent 100%),
            radial-gradient(1800px circle at 100% 100%,
            #fa005a 10%,
            #770033 25%,
            #4c0029 40%,
            #290022 60%,
            #290022 70%,
            transparent 100%);
    }

    #radius-shape-1 {
        height: 220px;
        width: 220px;
        top: -60px;
        left: -130px;
        background: radial-gradient(#ffc500, #db8800);
        overflow: hidden;
    }

    #radius-shape-2 {
        border-radius: 38% 62% 63% 37% / 70% 33% 67% 30%;
        bottom: -60px;
        right: -110px;
        width: 300px;
        height: 300px;
        background: radial-gradient(#ffc500, #db8800);
        overflow: hidden;
    }

    .bg-glass {
        background-color: hsla(0, 0%, 100%, 0.9) !important;
        backdrop-filter: saturate(200%) blur(25px);
    }
    ion-icon{
        font-size: 2rem;
    }
</style>

<script src=
        "https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js">
</script>
<script src=
        "https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js">
</script>
<script src=
        "https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js">
</script>

<script>
    $().ready(function () {

        $('#loginForm').validate({
            rules: {
                username: {
                    required: true,
                    maxlength: 50
                },
                password: {
                    required: true,
                    maxlength: 32
                }
            },
            messages: {
                username: {
                    required: "Please enter your username.",
                    maxlength: "Username should be maximum 50 characters."
                },
                password: {
                    required: "Please enter your password.",
                    maxlength: "Password should be maximum 32 characters."
                }
            },
            submitHandler: function (form) {
                // Form submission logic
                form.submit();
            }
        });
        $('#signupForm').validate({
            rules: {
                fullname: 'required',
                username: 'required',
                password: 'required',
                phone: {
                    required: true,
                    pattern: '[0-9]{10}'
                },
                email: {
                    required: true,
                    email: true
                },
                address: 'required'
            },
            messages: {
                fullname: 'Please enter your full name.',
                username: 'Please enter your username.',
                password: 'Please enter your password.',
                phone: {
                    required: 'Please enter your phone number.',
                    pattern: 'Please enter a valid phone number (10 digits).'
                },
                email: {
                    required: 'Please enter your email address.',
                    email: 'Please enter a valid email address.'
                },
                address: 'Please enter your address.'
            },
            submitHandler: function (form) {
                form.submit();
            }
        });
        
        $('#updateForm').validate({
            rules: {
                fullname: 'required',
                username: 'required',
                oldPassword: 'required',
                phone: {
                    required: true,
                    pattern: '[0-9]{10}'
                },
                email: {
                    required: true,
                    email: true
                },
                address: 'required'
            },
            messages: {
                fullname: 'Please enter your full name.',
                username: 'Please enter your username.',
                oldPassword: 'Please enter your old password.',
                phone: {
                    required: 'Please enter your phone number.',
                    pattern: 'Please enter a valid phone number (10 digits).'
                },
                email: {
                    required: 'Please enter your email address.',
                    email: 'Please enter a valid email address.'
                },
                address: 'Please enter your address.'
            },
            submitHandler: function (form) {
                form.submit();
            }
        });
        $('#productForm').validate({
            rules: {
                productName: 'required',
                productType: 'required',
                productPrice: {
                    required: true
//                    ,pattern: '^(0|[1-9]\d{0,2})(\.\d{2})?$'
                }
//                ,productImg: 'required'
            },
            messages: {
                productName: '*Please enter product name.',
                productType: '*Please enter product type.',
                productPrice: {
                    required: '*Please enter product price.'
//                    ,pattern: '*Please enter a valid product price.'
                }
//                ,productImg: '*Please enter product image.'
            },
            submitHandler: function (form) {
                form.submit();
            }
        });

    });
</script>