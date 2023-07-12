<%-- 
    Document   : ClassCategories
    Created on : Jun 13, 2023, 3:36:32 PM
    Author     : devli
--%>
<%
    String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!--   basic 
        --><meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!--   mobile metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="initial-scale=1, maximum-scale=1">
        <!--site metas--> 
        <title>Yogasan</title>
        <!--CSS-->
        <%--<%@ include file="../Components/CSSComponent.jsp" %>--%>
        <!--Javascript-->
        <%--<%@ include file="../Components/JsComponent.jsp" %>--%>            
        <style type="text/css">


            /*
      * @subsection Shop
      */
            .classCate {
                padding-top: 5px;
                padding-bottom: 5px;
                margin-left: auto;
                margin-right: auto;
            }

            .classCate .caption {
                margin-top: 15px;
            }

            .classCate .caption h6 {
                color: #455a64;
            }

            .classCate .caption .price + .price {
                margin-left: 15px;
            }

            .classCate .tumbnail {
                box-shadow: 0 5px 25px 0 transparent;
                transition: 0.3s linear;
                padding-top: 0;
            }

            .classCate.tumbnail img:hover {
                box-shadow: 0 5px 25px 0 rgba(0, 0, 0, 0.2);
            }
            .classCate.tumbnail:hover {
                transform: scale(1.1); /* Kích thước tăng lên 110% khi hover */
                border-color: #ff0000; /* Màu border khi hover */
            }
            .single-product span {
                display: inline-block;
            }

            .single-product .rating .fa-star,
            .single-product .rating .fa-star-o {
                font-size: 16px;
                color: #f7d4a0;
                margin-left: 2px;
            }

            .single-product .rating + * {
                margin-left: 15px;
            }

            .single-product h1.h1-variant-2 {
                margin-bottom: 20px;
            }

            .single-product .caption:before {
                content: "";
                height: 100%;
                display: inline-block;
                vertical-align: middle;
            }

            .single-product .caption span {
                display: inline-block;
                vertical-align: middle;
            }

            .single-product .caption .price {
                font-weight: 400;
            }

            .single-product .caption .price.sale {
                color: #e75854;
                font-size: 33px;
            }

            .single-product .caption * + .price {
                margin-left: 10.8%;
            }

            @media (max-width: 1199px) {
                .single-product .caption * + .price {
                    margin-left: 7.8%;
                }
            }

            .single-product .caption * + .quantity {
                margin-left: 26px;
            }

            .single-product .caption .info-list {
                border-bottom: 1px solid #f3f3ed;
                border-top: 1px solid #f3f3ed;
                font-family: Montserrat, sans-serif;
                padding-top: 26px;
                padding-bottom: 26px;
                text-align: left;
            }

            .single-product .caption .info-list dt,
            .single-product .caption .info-list dd {
                display: inline-block;
                line-height: 25px;
                padding-top: 10px;
                padding-bottom: 10px;
            }

            .single-product .caption .info-list dt {
                letter-spacing: 0.08em;
                font-size: 12px;
                color: #a7b0b4;
                width: 35%;
                text-transform: uppercase;
            }

            .single-product .caption .info-list dd {
                font-size: 15px;
                color: #565452;
                width: 62.5%;
            }

            .single-product .caption .share span.small {
                margin-top: 9px;
            }

            @media (max-width: 991px) {
                .single-product .caption .share span.small {
                    display: block;
                    margin-bottom: 15px;
                }
            }

            @media (max-width: 767px) {
                .single-product .table-mobile tr {
                    padding-top: 0;
                }
                .single-product .table-mobile tr:before {
                    display: none;
                }
            }

            .price {
                display: inline-block;
                font-size: 15px;
                font-family: Montserrat, sans-serif;
                font-weight: 700;
                letter-spacing: 0.02em;
                color: #2b2f3e;
            }

            .price.sale {
                color: #e75854;
            }

            .price del {
                color: #b0bec5;
            }

            .quantity {
                text-align: center;
                font-family: Montserrat, sans-serif;
                font-size: 12px;
                background: #eceff1;
                padding-top: 5px;
                padding-bottom: 5px;
                width: 82px;
                height: auto;
                display: inline-block;
            }

            .quantity span {
                display: inline-block;
            }

            .quantity .num {
                width: 26px;
            }

            .quantity [class*="fa-"] {
                padding-top: 4px;
                width: 22px;
                padding-bottom: 4px;
                color: #b0bec5;
                cursor: pointer;
            }

            .quantity [class*="fa-"]:hover {
                color: #455a64;
            }
            /*            .button {
                            position: relative;
                            overflow: hidden;
                            height: 3rem;
                            padding: 0 2rem;
                            border-radius: 1.5rem;
                            background: #3d3a4e;
                            background-size: 400%;
                            color: #fff;
                            border: none;
                        }
            
                        .button:hover::before {
                            transform: scaleX(1);
                        }
            
                        .button-content {
                            position: relative;
                            z-index: 1;
                        }
            
                        .button::before {
                            content: '';
                            position: absolute;
                            top: 0;
                            left: 0;
                            transform: scaleX(0);
                            transform-origin: 0 50%;
                            width: 100%;
                            height: inherit;
                            border-radius: inherit;
                            background: linear-gradient(
                                82.3deg,
                                rgba(150, 93, 233, 1) 10.8%,
                                rgba(99, 88, 238, 1) 94.3%
                                );
                            transition: all 0.475s;
                        }*/
            .button {
                cursor: pointer;
                position: relative;
                padding: 10px 24px;
                font-size: 18px;
                color: rgb(193, 163, 98);
                border: 2px solid rgb(193, 163, 98);
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
                background-color: rgb(193, 163, 98);
                transition: all 0.6s cubic-bezier(0.23, 1, 0.320, 1);
            }

            .button:hover::before {
                scale: 3;
            }

            .button:hover {
                color: #212121;
                scale: 1.1;
                box-shadow: 0 0px 20px rgba(193, 163, 98,0.4);
            }

            .button:active {
                scale: 1;
            }



        </style>            
    </head>
    <body>
        <!--navbar : Start-->
        <jsp:include page="../Components/headerComponent.jsp" />       
        <!--navbar: End-->


        <!--Container: Start-->

        <div class="container bootstrap snipets " style="margin-top:15px">
            <h1 class="text-center text-muted">CÔNG NGHỆ ĐỂ AN LẠC</h1>
            <% String popupMessage = (String) request.getAttribute("popupMessage"); %>
            <% if (popupMessage != null) {%> <div id="myAlert" class="alert">
                <span class="closebtn" onclick="this.parentElement.style.display = 'none';">&times;</span> 
                <strong>!</strong> You have registered class ${popupMessage} successfully. Please check your mail for more information.
                <div class="progress-bar">
                    <div class="progress"></div>
                </div>
                <% }%>
            </div>
            <div class="row">
                <c:forEach items="${requestScope.listCate}" var="a">
                    <div class="col-xs-6 col-md-4">
<!--                        <form action="<%=url%>/ImageController}">-->
                        <div class="classCate tumbnail thumbnail-3" style="border: 2px solid #ccc; border-radius: 10px; padding: 10px;margin: 20px 20px;">
                            <c:forEach items="${a.image}" var ="imageData">
                                <a href="ClassController?returnID=${a.getMaLoaiLopHoc()}">
                                    <c:if test="${not empty imageData.tenAnh and imageData.tenAnh.equalsIgnoreCase('THUMBNAIL')}">
                                        <img src="data:image/jpeg;base64,${imageData.image}" alt="" style="width: 100%; height: 100%;">
                                    </c:if>

                                </a>
                            </c:forEach>
                            <div class="caption text-center">
                                <h2 ><a href="#" style="text-decoration: none; color: #333;">${a.getTenLoaiLopHoc()}</a></h2>
                                <span class="price"></span
                                <input type="hidden" value="${a.getMaLoaiLopHoc()}" name="returnID">
                                <!--                                          <button class="button" type="submit" name="action" value="showDetails"></button>
                                                                              <span class="button-content">DETAILS</span>
                                                                          </button>  
                                -->
                                <form action="<%=url%>/ClassController" method="GET">
                                    <input type="hidden" name="returnID" value="${a.getMaLoaiLopHoc()}" />
                                    <input type="hidden" name="action" value="showDetails" />
                                    <button class="button" type="submit">Details</button>
                                </form>


                                <!-- Pass the class ID as a hidden input field -->
                                <!--<input type="hidden" name="classID" value="${classID}" />-->
                                <!--                                    <button class="button " type="submit" name="action" value="checkID">
                                                                        <span class="button-content">DETAILS</span>
                                                                    </button>-->
                                <!--                                    ${error} LAM THEO MESSAGE -->
                            </div>
                        </div>
                        <!--                        </form>-->
                    </div>       
                </c:forEach>
            </div>      
        </div>
        <style>
            .progress {
                height: 4px;
                background-color: #4CAF50;
                width: 100%;
                position: absolute;
                top: 61px;
                left: 0;
                animation: progress-animation 5s linear;
            }

            @keyframes progress-animation {
                0% {
                    width: 100%;
                }
                100% {
                    width: 0;
                }
            }
            .header-nav {
                background-color: #8b57fc;
                margin-bottom: 0px;
            }
            .alert {
                padding: 20px;
                background-color: greenyellow;
                color: black;
                
            }

            .closebtn {
                margin-left: 15px;
                color: white;
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
        </style>

        <!--Container: End-->
        <jsp:include page="../Components/footerComponent.jsp" />                  
    </body>
    <script>
        // Function to close the alert message
        function closeAlert() {
            var alert = document.getElementById("myAlert");
            alert.style.display = "none";
        }

        // Function to automatically close the alert after 5 seconds
        function autoCloseAlert() {
            var alert = document.getElementById("myAlert");
            var progress = alert.querySelector(".progress");

            var duration = 500; // Duration in seconds
            var interval = 100; // Update interval in milliseconds
            var progressWidth = 100;

            var progressInterval = setInterval(function () {
                progressWidth -= (interval / (duration * 10)) * 100;
                progress.style.width = progressWidth + "%";

                if (progressWidth <= 0) {
                    clearInterval(progressInterval);
                    alert.style.display = "none";
                }
            }, interval);
        }

        // Call the autoCloseAlert function when the page has finished loading
        window.onload = autoCloseAlert;
    </script>
</html>
