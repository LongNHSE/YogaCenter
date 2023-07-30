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
        <link href="<%=url%>/css/ClassDetailStyle.css" rel="stylesheet" type="text/css"  > 
        <style type="text/css">

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
                            <c:if test="${currentPrice==null}" >
                                <fmt:formatNumber value="${requestScope.details.getHocPhi()}" pattern="#,##0 VNĐ" var="formattedHocPhi" />
                                ${formattedHocPhi}

                            </c:if>
                            <c:if test="${currentPrice!=null}" >
                                <fmt:formatNumber value="${requestScope.details.getHocPhi()}" pattern="#,##0 VNĐ" var="formattedHocPhi"  />
                                <s>${formattedHocPhi}</s>


                            <c:set var="currentPrice" value="${currentPrice}"/>

                            <fmt:formatNumber value="${currentPrice}" pattern="#,##0 VNĐ" var="currentPriceNew" />
                            ${currentPriceNew}
                        </c:if>
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
                                            <input type="hidden" name="voucherID" value="${voucher.voucherID}" />

                                        </div>
                                    </c:if>
                                    <div class="voucher-check">
                                        <input type="text" name="voucher" value="${voucher.voucherName}" placeholder="Your voucher..."/>
                                        <button class="button" type="submit" name="action" value="CheckVoucher">
                                            Check
                                        </button>
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
                                <div class="voucher-note">
                                    <% String voucherMessage = (String) request.getAttribute("voucherMessage"); %>
                                    <% if (voucherMessage != null) {%>⚠ <%= voucherMessage%> <% }%>
                                </div>  
                                <div style="width: 357px;

                                     position: absolute;
                                     margin-top: 171px;
                                     right: 293px;
                                     color: red;
                                     font-weight: BOLD;
                                     ">

                                    <% String errorMessage = (String) request.getAttribute("error");%>
                                    <% if (errorMessage != null) {%> <%= errorMessage%> <% }%>

                                </div>


                            </div>

                        </form>

                    </div>
                </div>
                <c:set var="descriptionDTO" value="${descriptionDTO}" />
                <div id="alertMessage" class="alert-message">

                </div>
            </div>

            <div class="Description product-content product-wrap clearfix product-deatil">
                <div class="title">
                    <h1> 📖  ${descriptionDTO.title}</h1>
                </div>
                <div class="description">
                    ${descriptionDTO.content}
                </div>

            </div>
            <div class="Description product-content product-wrap clearfix product-deatil">

                <div class="col-sm-5 col-md-6 col-12 pb-4">
                    <h1>Comments</h1>



                    <c:if test = "${sessionScope.hocVienDTO != null || sessionScope.trainerDTO != null}">
                        <div class="comment mt-4 text-justify float-left" style="border: none">
                            <form action="<%=url%>/CommentController">
                                <div class="d-flex flex-row align-items-start"><textarea class="form-control ml-1 shadow-none textarea" name="comment"></textarea></div>
                                <div class="mt-2 text-right"><button class="btn btn-primary btn-sm shadow-none" type="submit">Post comment</button><button class="btn btn-outline-primary btn-sm ml-1 shadow-none" type="button">Cancel</button></div>

                                <input type="hidden" name="maLoaiLopHoc" value="<%=cid%>" />
                                <input type="hidden" name="action" value="post" />



                            </form>
                        </div>
                    </c:if>
                    <c:forEach var="commentDTO" items="${requestScope.listComment}">

                        <div class="comment mt-4 text-justify float-left">
                            <c:if test="${sessionScope.hocVienDTO.maHV ==commentDTO.hocVienDTO.maHV }">
                                <form action="<%=url%>/CommentController">
                                    <button class="btn btn-primary btn-sm shadow-none" type="submit" style="margin-left: 900px;size: 100px">Delete</button>
                                    <input type="hidden" name="maComment" value="${commentDTO.maComment}" />
                                    <input type="hidden" name="maLoaiLopHoc" value="<%=cid%>" />
                                    <input type="hidden" name="action" value="delete" />
                                </form>
                            </c:if>

                            <c:if test="${commentDTO.hocVienDTO.username!=null}">
                                <img src="data:image/jpeg;base64,${commentDTO.hocVienDTO.avatarDTO.image}" alt="" class="rounded-circle" width="40" height="40">

                                <h2>${commentDTO.hocVienDTO.username}</h2>
                            </c:if>
                            <c:if test="${commentDTO.trainerDTO.username!=null}">
                                <img src="data:image/jpeg;base64,${commentDTO.trainerDTO.avatarDTO.image}" alt="" class="rounded-circle" width="40" height="40">

                                <h2>${commentDTO.trainerDTO.ten}<bold style="color: greenyellow; font-size: 20px">(Trainer)</bold></h2>
                                    </c:if>
                            <span>- ${commentDTO.date}</span>
                            <br>
                            <p>${commentDTO.noiDung}</p>
                        </div>
                    </c:forEach>

                </div>

            </div>


        </div>
        <style>
            .comments{
                margin-top: 5%;
                margin-left: 20px;
            }



            .comment{
                width: 1000px;
                border: 1px solid rgba(16, 46, 46, 1);
                font-size: 20px;
                float: left;
                border-radius: 5px;
                padding-left: 40px;
                padding-right: 30px;
                padding-top: 15px;

            }
            .comment h2,.comment span,.darker h4,.darker span{
                display: inline;
            }

            .comment p,.comment span,.darker p,.darker span{

            }
        </style>

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
