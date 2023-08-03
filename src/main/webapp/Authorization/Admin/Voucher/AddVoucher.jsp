<%-- 
    Document   : AddVoucher
    Created on : Aug 2, 2023, 1:03:59 AM
    Author     : nctop
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/signupCSS.css" rel="stylesheet" type="text/css"/>
        <link href="<%=url%>/Authorization/Admin/cssAdmin/image.css" rel="stylesheet" type="text/css"/>
        <meta charset="utf-8">
        <title>YogaCenter Admin</title>
        <link href="<%=url%>/Authorization/Admin/cssAdmin/newCascadeStyleSheet.css" rel="stylesheet" type="text/css"/>
        <script class="u-script" type="text/javascript" src="<%=url%>/Authorization/Admin/jsAdmin/home2.js" defer=""></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script class="u-script" type="text/javascript" src="<%=url%>/Authorization/Admin/jsAdmin/home1.js" defer=""></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
        <title>Create voucher</title>
    </head>
    <body>
        <div class="wrapper">
            <%@include file="../NavComponents.jsp" %>
        </div>

        <div class="container py-5 h-100">
            <form action="<%=url%>/VoucherController" method="POST">


                <div class="row justify-content-center align-items-center h-100">
                    <div class="col-12 col-lg-9 col-xl-7">
                        <div class="card shadow-2-strong card-registration" style="border-radius: 15px;">
                            <div class="card-body p-4 p-md-5">
                                <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Create Voucher</h3>

                                <div class="row">
                                    <div class="col-md-6 mb-4">

                                        <div class="form-outline">

                                            <input type="text" id="voucherName" class="form-control form-control-lg" name="voucherName" required="required"/>
                                            <label class="form-label" for="voucherName">Voucher Name</label>
                                            <!--<div style="color: red; font-weight: BOLD">
                                            <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
                                            <% if (errorMessage != null) {%> <%= errorMessage%> <% }%>
                                        </div>-->

                                        </div>
                                    </div>
                                    <div class="col-md-6 mb-4">

                                        <div class="form-outline">
                                            <input type="number" id="multiplier" class="form-control form-control-lg" name="multiplier" />
                                            <label class="form-label" for="multiplier">%Discount Value</label>
                                            <div style="color: red; font-weight: BOLD">
                                                <% if (errorMessage != null) {%> <%= errorMessage%> <% }%>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6 mb-4">
                                        <div class="form-outline">
                                            <div class="input-container">
                                                <input type="number" id="usageLimit" class="form-control form-control-lg" name="usageLimit" />
                                                <label class="form-label" for="usageLimit">Global Usage</label>
                                            </div>
                                        </div>                            
                                    </div>
                                    <div class="col-md-6 mb-4">
                                        <div class="form-outline">
                                            <div class="input-container">
                                                <input type="number" id="usageLimitPerUser" class="form-control form-control-lg" name="usageLimitPerUser" />
                                                <label class="form-label" for="usageLimitPerUser">Individual Usage</label>
                                            </div>
                                        </div>                            
                                    </div>
                                    <div class="mt-4 pt-2">
                                        <button class="btn btn-outline-danger" name="action" value="addVoucher" >Submit</button>
                                    </div

                                </div>
                            </div>
                        </div>
                    </div>
                </div>

        </div>
    </form>
</div>
</body>
</html>
