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
        <c:set var="lopHocDTO" value="${requestScope.lopHocDTO}"></c:set>
            <!--navbar : Start-->
        <jsp:include page="../Components/headerComponent.jsp" />       
        <!--navbar: End-->
        <div class="Detail-con">

            <div>
                <h1>Change Class: </h1>
            </div>

            <div class="Detail">
                <p>
                    Class: <b><c:out value="${lopHocDTO.maLopHoc}"/> </b>  &nbsp;&nbsp;&nbsp;
                </p>
                <p>
                    Recent slot: <c:out value="${lopHocDTO.slotDTO.maSlot}"/> 
                </p>
                <p>Time: <b><c:out value="${lopHocDTO.slotDTO.timeStart}"/> - <c:out value="${lopHocDTO.slotDTO.timeEnd}"/></b></p>

                <p>
                    Day: <b>${lopHocDTO.printDays()}</b>
                </p>
            </div>
        </div>
        <div class="container">


            <div class="product-content product-wrap clearfix product-deatil">
                <div class="row">

                    <h1 class="name">
                        ${requestScope.details.getTenLoaiLopHoc()}
                    </h1>


                    <div class="row">



                        <table class="table" >

                            <thead>
                                <tr class="Test" style="text-align: center">


                                    <th scope="col">Class' ID</th>


                                    <th scope="col"> Attendees</th>
                                    <th scope="col">Trainer In Charge</th>
                                    <th scope="col">Slots</th>
                                    <th scope="col">Room</th>
                                    <th scope="col">Slot</th>
                                    <th scope="col">Days</th>
                                    <th scope="col">Initial Date</th>
                                </tr>
                            </thead>
                            <tbody>

                                <c:forEach items="${listClass}" var="listClass">
                                <form action="<%=url%>/ExceptionController" method="POST">
                                    <tr style="text-align: center">

                                        <th scope="row">${listClass.maLopHoc}</th>

                                        <td >${listClass.soLuongHvHienTai}/${listClass.soLuongHV} </td>

                                        <td><a href="#">${listClass.trainerDTO.ho} ${listClass.trainerDTO.ten}</a>  </td>
                                        <td>${listClass.soBuoiDaDay}/${listClass.soBuoi}</td>
                                        <td>${listClass.maRoom} </td>
                                        <td>${listClass.slotDTO.timeStart}-${listClass.slotDTO.timeEnd} </td>
                                        <td>${listClass.printDays()} </td>
                                        <td >${listClass.ngayBatDau} </td>
                                        <td ><button class="buttonClass" type="submit" name="action" value="ChangeClassAction">
                                                Change class
                                            </button> </td>
                                    </tr>

                                    <input type="hidden" name="maLopHoc" value="${listClass.maLopHoc}" />
                                    <input type="hidden" name="maLopHocCu" value="${lopHocDTO.maLopHoc}" />

                                </form>
                            </c:forEach>
                            </tbody>
                        </table>




                    </div>


                </div>

            </div>
        </div>


        <style>
            .Detail-con {
                /* display: flex; */
                position: sticky;
                justify-content: center;
                text-align: center;
                border: solid 0.1px;
                width: 80%;
                height: auto;
                left: 144px;
            }

            .Detail {
                position: inherit;
                display: inline-block;
                left: -110px;
                /* margin-top: 50px; */
                font-size: 20px;
            }

            .row{
                justify-content: center;
            }
        </style>

    </body>

</html>
