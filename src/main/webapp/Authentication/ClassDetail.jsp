<%-- 
    Document   : ClassDetail
    Created on : Jun 8, 2023, 8:10:20 AM
    Author     : Oalskad
--%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@page import="com.mycompany.yogacenterproject.dto.DayAndSlot"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.mycompany.yogacenterproject.dao.LopHocDAO"%>
<%@page import="com.mycompany.yogacenterproject.dto.LopHocDTO"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<%
    String JsUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="<%=JsUrl%>/js/jquery.min.js"></script>
        <script src="<%=JsUrl%>/js/popper.min.js"></script>
        <script src="<%=JsUrl%>/js/bootstrap.bundle.min.js"></script>
        <script src="<%=JsUrl%>/js/jquery-3.0.0.min.js"></script>
        <script src="<%=JsUrl%>/js/plugin.js"></script>
        <!-- sidebar -->
        <script src="<%=JsUrl%>/js/jquery.mCustomScrollbar.concat.min.js"></script>
        <script src="<%=JsUrl%>/js/custom.js"></script>
        <!-- javascript -->
        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <script src="<%=JsUrl%>/js/owl.carousel.js"></script>
        <script src="https:cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.js"></script>
        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.bundle.min.js"></script>
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
        <!--CSS-->

        <style type="text/css">
            body{
                background:#eee;
                font-family: "Arial Unicode MS", Arial, sans-serif;
            }


            .product-content {
                border: 1px solid #dfe5e9;
                margin-bottom: 20px;
                margin-top: 12px;
                background: #fff
            }

            .product-content .carousel-control.left {
                margin-left: 0
            }

            .product-content .product-image {
                background-color: #fff;
                display: block;
                min-height: 238px;
                overflow: hidden;
                position: relative
            }

            .product-content .product-deatil {
                border-bottom: 1px solid #dfe5e9;
                padding-bottom: 17px;
                padding-left: 16px;
                padding-top: 16px;
                position: relative;
                background: #fff
            }

            .product-content .product-deatil h5 a {
                color: #2f383d;
                font-size: 15px;
                line-height: 19px;
                text-decoration: none;
                padding-left: 0;
                margin-left: 0
            }

            .product-content .product-deatil h5 a span {
                color: #9aa7af;
                display: block;
                font-size: 13px
            }

            .product-content .product-deatil span.tag1 {
                border-radius: 50%;
                color: #fff;
                font-size: 15px;
                height: 50px;
                padding: 13px 0;
                position: absolute;
                right: 10px;
                text-align: center;
                top: 10px;
                width: 50px
            }

            .product-content .product-deatil span.sale {
                background-color: #21c2f8
            }

            .product-content .product-deatil span.discount {
                background-color: #71e134
            }

            .product-content .product-deatil span.hot {
                background-color: #fa9442
            }

            .product-content .description {
                font-size: 12.5px;
                line-height: 20px;
                padding: 10px 14px 16px 19px;
                background: #fff
            }

            .product-content .product-info {
                padding: 11px 19px 10px 20px
            }

            .product-content .product-info a.add-to-cart {
                color: #2f383d;
                font-size: 13px;
                padding-left: 16px
            }

            .product-content name.a {
                padding: 5px 10px;
                margin-left: 16px
            }

            .product-info.smart-form .btn {
                padding: 6px 12px;
                margin-left: 12px;
                margin-top: -10px
            }

            .product-entry .product-deatil {
                border-bottom: 1px solid #dfe5e9;
                padding-bottom: 17px;
                padding-left: 16px;
                padding-top: 16px;
                position: relative
            }

            .product-entry .product-deatil h5 a {
                color: #2f383d;
                font-size: 15px;
                line-height: 19px;
                text-decoration: none
            }

            .product-entry .product-deatil h5 a span {
                color: #9aa7af;
                display: block;
                font-size: 13px
            }

            .load-more-btn {
                background-color: #21c2f8;
                border-bottom: 2px solid #037ca5;
                border-radius: 2px;
                border-top: 2px solid #0cf;
                margin-top: 20px;
                padding: 9px 0;
                width: 100%
            }

            .product-block .product-deatil p.price-container span,
            .product-content .product-deatil p.price-container span,
            .product-entry .product-deatil p.price-container span,
            .shipping table tbody tr td p.price-container span,
            .shopping-items table tbody tr td p.price-container span {
                color: #21c2f8;
                font-family: Lato, sans-serif;
                font-size: 24px;
                line-height: 20px
            }

            .product-info.smart-form .rating label {
                margin-top: 0
            }

            .product-wrap .product-image span.tag2 {
                position: absolute;
                top: 10px;
                right: 10px;
                width: 36px;
                height: 36px;
                border-radius: 50%;
                padding: 10px 0;
                color: #fff;
                font-size: 11px;
                text-align: center
            }

            .product-wrap .product-image span.sale {
                background-color: #57889c
            }

            .product-wrap .product-image span.hot {
                background-color: #a90329
            }

            .shop-btn {
                position: relative
            }

            .shop-btn>span {
                background: #a90329;
                display: inline-block;
                font-size: 10px;
                box-shadow: inset 1px 1px 0 rgba(0, 0, 0, .1), inset 0 -1px 0 rgba(0, 0, 0, .07);
                font-weight: 700;
                border-radius: 50%;
                padding: 2px 4px 3px!important;
                text-align: center;
                line-height: normal;
                width: 19px;
                top: -7px;
                left: -7px
            }

            .description-tabs {
                padding: 30px 0 5px!important
            }

            .description-tabs .tab-content {
                padding: 10px 0
            }

            .product-deatil {
                padding: 30px 30px 50px
            }

            .product-deatil hr+.description-tabs {
                padding: 0 0 5px!important
            }

            .product-deatil .carousel-control.left,
            .product-deatil .carousel-control.right {
                background: none!important
            }

            .product-deatil .glyphicon {
                color: #3276b1
            }

            .product-deatil .product-image {
                border-right: none!important
            }

            .product-deatil .name {
                font-size: 24px;
                font-weight: bold;
                color: #555;
                margin-bottom: 10px;
                margin-top: 0;
            }

            .product-deatil .name small {
                display: block
            }

            .product-deatil .name a {
                margin-left: 0
            }

            .product-deatil .price-container {
                font-size: 20px;
                font-weight: bold;
                color: #333;
                text-align: right;
            }

            .product-deatil .price-container small {
                font-size: 12px
            }

            .product-deatil .fa-2x {
                font-size: 16px!important
            }

            .product-deatil .fa-2x>h5 {
                font-size: 12px;
                margin: 0
            }

            .product-deatil .fa-2x+a,
            .product-deatil .fa-2x+a+a {
                font-size: 13px
            }

            .profile-message ul {
                list-style: none ;
            }

            .product-deatil .certified {
                margin-top: 10px
            }

            .product-deatil .certified ul {
                padding-left: 0
            }

            .product-deatil .certified ul li:not(first-child) {
                margin-left: -3px
            }

            .product-deatil .certified ul li {
                display: inline-block;
                background-color: #f9f9f9;
                border: 1px solid #ccc;
                padding: 13px 19px
            }

            .product-deatil .certified ul li:first-child {
                border-right: none
            }

            .product-deatil .certified ul li a {
                text-align: left;
                font-size: 12px;
                color: #6d7a83;
                line-height: 16px;
                text-decoration: none
            }

            .product-deatil .certified ul li a span {
                display: block;
                color: #21c2f8;
                font-size: 13px;
                font-weight: 700;
                text-align: center
            }

            .product-deatil .message-text {
                width: calc(100% - 70px)
            }

            @media only screen and (min-width:1024px) {
                .product-content div[class*=col-md-4] {
                    padding-right: 0
                }
                .product-content div[class*=col-md-8] {
                    padding: 0 13px 0 0
                }
                .product-wrap div[class*=col-md-5] {
                    padding-right: 0
                }
                .product-wrap div[class*=col-md-7] {
                    padding: 0 13px 0 0
                }
                .product-content .product-image {
                    border-right: 1px solid #dfe5e9
                }
                .product-content .product-info {
                    position: relative
                }
            }

            .message img.online {
                width:40px;
                height:40px;
            }
            .button {
                cursor: pointer;
                position: relative;
                padding: 10px 24px;
                font-size: 18px;
                color: #770737;
                border: 2px solid rgb(103, 49, 71);
                border-radius: 34px;
                background-color: transparent;
                font-weight: 600;
                transition: all 0.3s cubic-bezier(0.23, 1, 0.320, 1);
                overflow: hidden;
            }

            .button::before {
                content: '';
                position: absolute;
                inset: 0;
                margin: auto;
                width: 50px;
                height: 50px;
                border-radius: inherit;
                scale: 0;
                z-index: -1;
                background-color: rgb(218, 112, 214);
                transition: all 0.6s cubic-bezier(0.23, 1, 0.320, 1);
            }

            .button:hover::before {
                scale: 3.5;
            }

            .button:hover {
                color: #ffff;
                scale: 1.1;
                box-shadow: 0 0px 20px rgba(193, 163, 98,0.4);
            }

            .button:active {
                scale: 1;
            }


            .box {
                display:flex;
                align-items: center;
                position: absolute;
                top: 27px ;
                right: -265px;
                transform: translate(-50%, -50%);
            }

            .box select {
                background-color: rgb(218, 112, 214);
                color: #ffff;
                padding: 12px;
                padding-top: 20px;
                width: 350px;
                margin-right: 5px;
                border: none;
                font-size: 20px;
                box-shadow: 0 5px 25px rgba(0, 0, 0, 0.2);
                outline: none;
                -moz-appearance: none;
                -webkit-appearance: none;
                appearance: none;

            }

            .box::before {
                content: "\f13a";
                font-family: FontAwesome;
                position: absolute;
                top: 0;
                right: 0;
                width: 20%;
                height: 100%;
                text-align: center;
                font-size: 28px;
                line-height: 75px;
                color: rgba(255, 255, 255, 0.5);
                background-color: rgba(255, 255, 255, 0.1);
                pointer-events: none;
            }

            .box:hover::before {
                color: rgba(255, 255, 255, 0.6);
                background-color: rgba(255, 255, 255, 0.2);
            }

            .box select option {
                padding: 30px;
            }
            .box select option p {
                color: greenyellow;
                font-size: 16px
            }

            .title{
                width: 100%;
                height: auto;
            }
            .title h1 {
                color:rgb(191, 64, 191);
                border-bottom: 5px solid #554c86
            }
            .Description .description{
                color: #5a5a5a;
                font-size: 25px;
                text-align: left;
                line-height: 40px;
            }
            .Custom{
                margin-left: 98px;
            }

            .alert {
                padding: 20px;
                background-color: #ffff4d;
                color: black;
            }

            .closebtn {
                margin-left: 15px;
                color: black;
                font-weight: bold;
                float: right;
                font-size: 22px;
                line-height: 20px;
                cursor: pointer;
                transition: 0.3s;
            }

            .closebtn:hover {
                color: black;
            }
            .price-container{
                position: relative;
                padding: 20px;
                padding-left: 60%
            }
            .name{
                padding 20px;
                padding-left: 0%;
            }
            .row{
                padding 20px;
            }
            .box select{
                padding :20px;
                position: relative;
                left: 100px;
                border-radius: 20px;
                text-align: center;
            }
            .button{
                margin: 100px;
                margin-left: 59%;

            }

            .class-information{
                margin-left: 40px;
            }
            .class-information h1{
                text-align: center;

            }
            .infor-line{

                border-top: 2px solid #554c86;
            }
           
            .voucher-check input{
                position: absolute;
                top: 30%;
                left: 60%;
                padding: 10px;
                font-size: 20px;
                text-align: center;
                background-color:rgb(218, 112, 214);
                color:#fff ; 
                border-radius: 10px;
            }
            .voucher-check button{
                position: absolute;
                left:100%;
                bottom: 15%;
            }
            .voucher-note{
                position: relative;
                left: 130%;
                top: 10%;
                color:#FF0000;
                font-weight: bold;
                padding: 5px;
                width:100%;
                height: auto;
                
            }
            .Custom button{
                position:relative;
                top:75px;
                right: 5%
                
            }
        </style>            
    </head>

    <body>
        <!--navbar : Start-->
        <jsp:include page="../Components/headerComponent.jsp" />       
        <!--navbar: End-->
        <div class="container">
            <% String popupMessage = (String) request.getAttribute("popupMessage");
                String popupMessageSuccessful = (String) request.getAttribute("popupMessageSuccessful"); %>
            <% if (popupMessage != null) {%> <div id="myAlert" class="alert">
                <span class="closebtn" onclick="this.parentElement.style.display = 'none';">&times;</span> 
                <strong>!</strong>  ${popupMessage} 
            </div>

            <% }%>
            <div class="product-content product-wrap clearfix product-deatil">
                <div class="row">

                    <div class="col-md-5 col-sm-12 col-xs-12">
                        <div class="product-image">
                            <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                                <ol class="carousel-indicators">

                                    <c:forEach items="${requestScope.imageListByID}" var="imageData" varStatus="status">
                                        <li data-target="#carouselExampleIndicators" data-slide-to="${status.index}" class="${status.index == 0 ? 'active' : ''}"></li>
                                        </c:forEach>
                                </ol>
                                <div class="carousel-inner">

                                    <c:forEach items="${requestScope.imageListByID}" var="imageData" varStatus="status">
                                        <div class="carousel-item ${status.index == 0 ? 'active' : ''}">
                                            <img src="data:image/jpeg;base64,${imageData.image}" class="img-responsive" alt="" style="width: 100%;
                                                 height: 400px;">
                                        </div>
                                    </c:forEach>
                                </div>


                                <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                    <span class="sr-only"></span>
                                </a>
                                <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                    <span class="sr-only"></span>
                                </a>
                            </div>        
                        </div>
                    </div>



                    <div class="col-md-6 col-md-offset-1 col-sm-12 col-xs-12 class-information">
                        <h1 class="name">
                            ${requestScope.details.getTenLoaiLopHoc()}
                        </h1>
                        <hr class="infor-line"/>
                        <p class="price-container text-right">
                            <fmt:formatNumber value="${requestScope.details.getHocPhi()}" pattern="#,##0 VNĐ" var="formattedHocPhi" />
                            ${formattedHocPhi}
                        </p>
                        <hr class="infor-line"/>

                        <form action="<%=url%>/ClassController" method="POST">
                            <div class="row">

                                <div class="col-sm-12 col-md-6 col-lg-6 d-flex justify-content-center align-items-center">

                                    <div class="box">
                                        <select name="maSlot" required>
                                            <option  value=""> Please choose Slot</option>
                                            <c:forEach items="${requestScope.distinctDayAndSlots}" var="DayAndSlot" >
                                                <option name="maSlot" value="${DayAndSlot.getSlot()}|${DayAndSlot.getDay()}">
                                                    ${DayAndSlot.getSlot()} : ${DayAndSlot.timeStart}-${DayAndSlot.timeEnd}, ${DayAndSlot.day}
                                                </option>  
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <c:set var="voucher" value="${voucherDTO}"/>
                                    <c:if test="${voucher!=null}" >
                                        <div>
                                            <input type="text" name="voucherID" value="${voucher.voucherID}" />
                                            
                                        </div>
                                    </c:if>
                                    <div class="voucher-check">
                                        <input type="text" name="voucher" value="${voucher}" placeholder="Your voucher..."/>
                                        <button class="button" type="submit" name="action" value="CheckVoucher">
                                            Check
                                        </button>
                                    </div>
                                    <div class="voucher-note">
                                        <% String voucherMessage = (String) request.getAttribute("voucherMessage"); %>
                                        <% if (voucherMessage != null) {%> <%= voucherMessage%> <% }%>
                                    </div>


                                    <div class="Custom">
                                        <button class="button" type="submit" name="action" value="Register">
                                            Register now!
                                        </button>

                                        <% String cid = (String) request.getAttribute("cid");%>
                                        <input type="hidden" name="maLoaiLopHoc" value="<%=cid%>" />
                                        <input type="hidden" name="returnID" value="<%=cid%>" />
                                    </div>

                                </div>
                                <div style="width: 357px;
                                     position: absolute;
                                     margin-top: 10px;
                                     right: 223px;
                                     color: red;
                                     font-weight: BOLD;">

                                    <% String errorMessage = (String) request.getAttribute("error");%>
                                    <% if (errorMessage != null) {%> <%= errorMessage%> <% }%>

                                </div>


                            </div>
                            <div style="width: 357px;
                                 position: absolute;
                                 margin-top: 10px;
                                 right: 172px;
                                 color: red;
                                 font-weight: BOLD;">

                                <% errorMessage = (String) request.getAttribute("error");%>
                                <% if (errorMessage != null) {%> <%= errorMessage%> <% }%>

                            </div>
                        </form>

                    </div>
                </div>
                <c:set var="descriptionDTO" value="${descriptionDTO}" />
            </div>

            <div class="Description product-content product-wrap clearfix product-deatil">
                <div class="title">
                    <h1> 📖  ${descriptionDTO.title}</h1>
                </div>
                <div class="description">
                    ${descriptionDTO.content}
                </div>
            </div>



        </div>

        <jsp:include page="../Components/footerComponent.jsp" />   



        <!--        <script>
                    // Get the voucher code input element
                    const voucherCodeInput = document.getElementById('voucherCodeInput');
        
        // Add event listener to the voucher code input
                    voucherCodeInput.addEventListener('input', handleVoucherCodeChange);
        
                    function handleVoucherCodeChange() {
                        const voucherCode = voucherCodeInput.value;
        
                        // Make an AJAX request to the server
                        const xhr = new XMLHttpRequest();
                        xhr.open('GET', '/check-voucher?code=' + voucherCode);
                        xhr.onreadystatechange = function () {
                            if (xhr.readyState === XMLHttpRequest.DONE) {
                                if (xhr.status === 200) {
                                    // Parse the response JSON
                                    const response = JSON.parse(xhr.responseText);
        
                                    // Update the prices on the page
                                    document.getElementById('originalPrice').textContent = 'Original Price: $100';
                                    document.getElementById('discountedPrice').textContent = 'Discounted Price: $' + response.discountedPrice;
                                } else {
                                    console.error('Error: ' + xhr.status);
                                }
                            }
                        };
                        xhr.send();
                    }
        
                </script>-->

    </body>

</html>
