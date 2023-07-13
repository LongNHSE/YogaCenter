<%-- 
    Document   : newjsp
    Created on : May 23, 2023, 1:56:24 PM
    Author     : Oalskad
--%>



<%@page import="com.mycompany.yogacenterproject.dao.LoaiLopHocDAO"%>
<%@page import="com.mycompany.yogacenterproject.dto.TrainerDTO"%>
<%@page import="com.mycompany.yogacenterproject.dto.LopHocDTO"%>
<%@page import="com.mycompany.yogacenterproject.dto.HocVienDTO"%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>YogaCenter</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">


    </head>
    <style>




        .Table{
            position: relative;

        }
        table {

            width: 100%; /* Set the width of the table */
            /*            border-collapse: collapse;  Collapse the borders of table cells */

        }

        th, td {
            font-size: 15.5px;
            padding: 10px; /* Add padding to table cells */
            text-align: left; /* Align text to the left in table cells */
            border: 1px solid #ccc; /* Add borders to table cells */
        }

        .Test th {
            background-color: #97EA5D; /* Set background color for table headers */
        }

        tr:nth-child(even) {
            background-color: #f9f9f9; /* Set background color for even rows */
        }

        tr:hover {
            background-color: #e6e6e6; /* Set background color for hovered rows */
        }
        .ClassDetail{
            margin-bottom: 100px;
        }
        /* === CODFE HEADING STYLE #3 === */
        .class-properties {
            width: 500px;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }/* === CODFE HEADING STYLE #3 === */
        .class-properties h1 {
            font-size: 24px;
            margin-bottom: 20px;
        }
        .class-properties ul {
            list-style-type: none;
            padding: 0;
            margin-bottom: 20px;
        }

        .class-properties li {
            margin-bottom: 10px;
        }

        .property-name {
            font-weight: bold;
        }

        .property-value {
            color: #555;
        }

        .cf-title-03 h3 {
            font-size: 28px;
            font-weight: 500;
            letter-spacing: 0;
            line-height: 1.5em;
            padding-bottom: 15px;
            position: relative;
        }
        .cf-title-03 h3:before {
            content: "";
            position: absolute;
            left: 0;
            bottom: 0;
            height: 5px;
            width: 55px;
            background-color: #111;
        }
        .cf-title-03 h3:after {
            content: "";
            position: absolute;
            left: 0;
            bottom: 2px;
            height: 1px;
            width: 95%;
            max-width: 255px;
            background-color: #333;
        }

    </style>


    <body>




        <jsp:include page="${url}/Components/headerComponent.jsp" />    
        <div class="Controller">
            <div class="Table">
                <table class="table">
                    <thead>
                        <tr class="Test">
                            <th  scope="col">ID Class</th>
                            <th scope="col">Type Of Yoga</th>
                            <th scope="col">Trainer</th>
                            <th scope="col">Slots</th>
                            <th scope="col">Date Start</th>
                            <th scope="col">Date End</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="lopHocDTO" items="${requestScope.listLopHocDTO}" >

                            <tr>
                                <th scope="row">${lopHocDTO.maLopHoc}</th>
                                <td>${lopHocDTO.loaiLopHocDTO.tenLoaiLopHoc} </td>
                                <td>${lopHocDTO.trainerDTO.ho} ${lopHocDTO.trainerDTO.ten} </td>
                                <td>${lopHocDTO.soBuoiDaDay}/${lopHocDTO.soBuoi}</td>
                                <td>${lopHocDTO.ngayBatDau}</td>
                                <td>${lopHocDTO.ngayKetThuc}</td>
                        <form action="<%=url%>/ClassController" method="POST">
                            <td> <input class="btn btn-outline-danger" type="submit" name="action" value="Detail" ></td>
                            <input type="hidden" name="maLopHoc" value="${lopHocDTO.maLopHoc}" >
                        </form>
                        <form action="<%=url%>/ExceptionController" method="POST">
                            <td> <input class="btn btn-outline-danger" type="submit" name="action" value="Request " ></td>
                            <td> <input class="btn btn-outline-danger" type="submit" name="action" value="Reserve" ></td>
                            <input type="hidden" name="maLopHoc" value="${lopHocDTO.maLopHoc}" >
                        </form>
                        </tr>

                    </c:forEach>
                    <% String popupMessage = (String) request.getAttribute("popupMessage");
                        String popupMessageSuccessful = (String) request.getAttribute("popupMessageSuccessful"); %>
                    <% if (popupMessage != null) {%> <div id="myAlert" class="alert">
                        <span class="closebtn" onclick="this.parentElement.style.display = 'none';">&times;</span> 
                        <strong>!!</strong> You can't reserve now because class ${popupMessage} has already started
                        <div class="progress-bar">
                            <div class="progress"></div>
                        </div>

                    </div>

                    <% }
                        if (popupMessageSuccessful != null) {%>  

                    <div id="myAlert" class="alert" style="background-color: greenyellow; color: black; ">
                        <span class="closebtn" onclick="this.parentElement.style.display = 'none';">&times;</span> 
                        <strong>!</strong> ${popupMessageSuccessful} 
                        <div class="progress-bar">
                            <div class="progress"></div>
                        </div>

                    </div>

                    <%}%>


                    </tbody>

                </table>
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
                    background-color: #f44336;
                    color: white;
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

            <script>
//                 // Function to close the alert message
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
                $(document).ready(function () {
                    $('.sub-menu ul#active').show();
                    $('li#active').find(".right").toggleClass("fa-caret-up fa-caret-down");
                });

                $('.sub-menu ul').hide();

                $(".sub-menu a").click(function () {
                    $(this).parent(".sub-menu").children("ul").slideToggle("100");
                    $(this).find(".right").toggleClass("fa-caret-up fa-caret-down");
                });</script>
    </body>
</html>