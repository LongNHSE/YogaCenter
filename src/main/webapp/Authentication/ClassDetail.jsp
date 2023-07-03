<%-- 
    Document   : ClassDetail
    Created on : Jun 8, 2023, 8:10:20 AM
    Author     : Oalskad
--%>
<%@page import="com.mycompany.yogacenterproject.dao.LopHocDAO"%>
<%@page import="com.mycompany.yogacenterproject.dto.LopHocDTO"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!--CSS-->
        <%@ include file="../Components/CSSComponent.jsp" %>
        <!--Javascript-->
        <%@ include file="../Components/JsComponent.jsp" %>
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
                margin-top: 0;
                margin-bottom: 0
            }

            .product-deatil .name small {
                display: block
            }

            .product-deatil .name a {
                margin-left: 0
            }

            .product-deatil .price-container {
                font-size: 24px;
                margin: 0;
                font-weight: 300
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
                scale: 3.5;
            }

            .button:hover {
                color: #212121;
                scale: 1.1;
                box-shadow: 0 0px 20px rgba(193, 163, 98,0.4);
            }

            .button:active {
                scale: 1;
            }


            .box {
                position: absolute;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
            }

            .box select {
                background-color: rgb(193, 163, 98);
                color: white;
                padding: 12px;
                padding-top: 20px;
                width: 350px;
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
                width: 500px;
                height: auto;
            }
            .title h1 {
                border-bottom: 5px solid #554c86
            }
            .Description .description{
                font-size: 25px;
                display: block;
                text-align: center;
                margin: 20px;
                line-height: 30px;
            }
        </style>            
    </head>

    <body>
        <!--navbar : Start-->
        <jsp:include page="../Components/headerComponent.jsp" />       
        <!--navbar: End-->
        <div class="container">

            <div class="product-content product-wrap clearfix product-deatil">
                <div class="row">

                    <div class="col-md-5 col-sm-12 col-xs-12">
                        <div class="product-image">
                            <div id="myCarousel-2" class="carousel slide">
                                <ol class="carousel-indicators">
                                    <c:forEach items="${requestScope.imageListByID}" var="imageData" varStatus="status">
                                        <li data-target="#myCarousel-2" data-slide-to="${status.index}" class="${status.index == 0 ? 'active' : ''}"></li>
                                        </c:forEach>
                                </ol>
                                <div class="carousel-inner">
                                    <c:forEach items="${requestScope.imageListByID}" var="imageData" varStatus="status">
                                        <div class="item ${status.index == 0 ? 'active' : ''}">
                                            <img src="data:image/jpeg;base64,${imageData.image}" class="img-responsive" alt="" style="width: 100%;
                                                 height: 400px;">
                                        </div>
                                    </c:forEach>
                                </div>
                                <a class="left carousel-control" href="#myCarousel-2" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a>
                                <a class="right carousel-control" href="#myCarousel-2" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
                            </div>            
                        </div>
                    </div>









                    <div class="col-md-6 col-md-offset-1 col-sm-12 col-xs-12">
                        <h1 class="name">
                            ${requestScope.details.getTenLoaiLopHoc()}
                        </h1>
                        <hr/>
                        <h3 class="price-container">
                            <fmt:formatNumber value="${requestScope.details.getHocPhi()}" pattern="#,##0 VNĐ" var="formattedHocPhi" />
                            ${formattedHocPhi}
                        </h3>
                        <hr/>

                        <hr/>

                        <form action="<%=url%>/ClassController">
                            <div class="row">

                                <div class="col-sm-12 col-md-6 col-lg-6 d-flex justify-content-center align-items-center">

                                    <div class="box">
                                        <select name="maSlot">
                                            <option  value=""> Please choose Slot</option>
                                            <%  List<LopHocDTO> listLopHocDTO = (List<LopHocDTO>) request.getAttribute("listLopHocDTO");
                                                LopHocDAO lopHocDAO = new LopHocDAO();
                                                for (int i = 0; i < listLopHocDTO.size(); i++) {
                                                    if (i != 0) {
                                                        if (!lopHocDAO.compareLists(listLopHocDTO.get(i).getThuList(), listLopHocDTO.get(i - 1).getThuList()) || !listLopHocDTO.get(i).getMaSlot().equals(listLopHocDTO.get(i - 1).getMaSlot())) {
                                            %>
                                            <option name="maSlot" value="<%= listLopHocDTO.get(i).getMaSlot() + "|" + listLopHocDTO.get(i).getThuList() %>">
                                                <%=listLopHocDTO.get(i).getMaSlot()%>:  <%=listLopHocDTO.get(i).getTimeStart()%> - <%=listLopHocDTO.get(i).getTimeEnd()%>, <%=listLopHocDTO.get(i).getThuList()%>
                                            
                                            </option>  
                                            <%
                                                }
                                            } else {
                                            %>

                                            <option name="maSlot" value="<%= listLopHocDTO.get(i).getMaSlot() + "|" + listLopHocDTO.get(i).getThuList() %>">
                                                <%=listLopHocDTO.get(i).getMaSlot()%>:  <%=listLopHocDTO.get(i).getTimeStart()%>-<%=listLopHocDTO.get(i).getTimeEnd()%>, <%=listLopHocDTO.get(i).getThuList()%>
                                            
                                            </option>  
                                            <%
                                                    }
                                                }
                                            %>
                                        </select>
                                    </div>





                                </div>

                                <div class="col-sm-12 col-md-6 col-lg-6 d-flex justify-content-center align-items-center">
                                    <button class="button" type="submit" name="action" value="Register">
                                        Register now!
                                    </button>
                                    <% String cid = (String) request.getAttribute("cid");%>
                                    <input type="hidden" name="maLoaiLopHoc" value="<%=cid%>" />
                                </div>

                            </div>
                        </form>

                    </div>
                </div>
                <c:set var="descriptionDTO" value="${descriptionDTO}" />
            </div>

            <div class="Description product-content product-wrap clearfix product-deatil">
                <div class="title">
                    <h1> ${descriptionDTO.title}</h1>
                </div>
                <div class="description">
                    ${descriptionDTO.content}
                </div>
            </div>



        </div>




    </body>

</html>
