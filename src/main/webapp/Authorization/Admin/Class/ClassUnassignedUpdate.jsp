<%-- 
    Document   : signup
    Created on : Mar 2, 2023, 12:03:46 PM
    Author     : Oalskad
--%>
<%@page import="java.util.Date"%>
<%
    String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta charset="utf-8">
        <title>YogaCenter Admin</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">

        <script class="u-script" type="text/javascript" src="home2.js" defer=""></script>
        <link href="<%=url%>/Authorization/Admin/cssAdmin/newCascadeStyleSheet.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script class="u-script" type="text/javascript" src="home1.js" defer=""></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">

    </head>


    <body>
        <style>nav {
                position: relative;
                height: 100%;
                width: 250px;

            }
            body{
                height: 100%;
            }
            nav ul {
                position: relative;
                height: 100%;
                list-style: none;
                margin: 0;
                padding: 0;
            }
            nav ul li {
                /* Sub Menu */
            }
            nav ul li a {
                display: block;
                padding: 10px 15px;
                color: #fff;
                text-decoration: none;
                -webkit-transition: 0.2s linear;
                -moz-transition: 0.2s linear;
                -ms-transition: 0.2s linear;
                -o-transition: 0.2s linear;
                transition: 0.2s linear;
            }
            nav ul li a:hover {
                background: #1d4f71;
                color: #fff;
            }
            nav ul li a .fa {
                width: 16px;
                text-align: center;
                margin-right: 5px;
                float:right;
            }
            nav ul ul {
                background: rgba(0, 0, 0, 0.2);
            }
            nav ul li ul li a {

                border-left: 4px solid transparent;
                padding: 10px 20px;
            }
            nav ul li ul li a:hover {

                border-left: 4px solid #3498db;
            }
            .Controller{
                display: flex;
                position: relative;
            }

            #active-element{
                background: #1d4f71;
                color: #fff;

            }
            .wrapper{
                position:fixed;
                height: 100%;
                color: #fff;
            }
            .Controller .content{
                margin-left:250px;
            }
            .Class{

                width: 100%;

            }

        </style>
        <c:set var="lopHocDTO" value="${lopHocDTO}" />

        <div class="Controller">

            <div class="wrapper">
                <%@include file="../NavComponents.jsp" %>
            </div>
            <div class="Class">

                <form action="<%=url%>/ClassUnassignedController" method="POST">
                    <section class="vh-100 gradient-custom">
                        <div class="container py-5 h-100">
                            <div class="row justify-content-center align-items-center h-100">
                                <div class="col-12 col-lg-9 col-xl-7">
                                    <div class="card shadow-2-strong card-registration" style="border-radius: 15px;">
                                        <div class="card-body p-4 p-md-5">
                                            <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Create Class</h3>


                                            <div class="row">
                                                <div class="col-md-6 mb-4">
                                                    <div class="form-outline">
                                                        <input type="number" id="soLuongHV" class="form-control form-control-lg" name="soLuongHV" value="${lopHocDTO.soLuongHV}" required="required" />
                                                        <label class="form-label" for="soLuongHV">Number of Trainees</label>
                                                    </div>


                                                </div>

                                            </div>
                                            <div class="row" id="lichHoc" >
                                                <div class="col-md-6 mb-4 ">
                                                    <div class="form-outline">
                                                        <select name="listPhongHocDTO"  class="selected-control ">
                                                            <option class="form-label" value="${lopHocDTO.maRoom}">Recent Room : ${lopHocDTO.maRoom}</option>
                                                            <c:forEach items="${listPhongHocDTO}" var="listPhongHocDTO">
                                                                <option class="form-label" value="${listPhongHocDTO.maRoom}">${listPhongHocDTO.maRoom}</option>
                                                            </c:forEach>
                                                        </select>
                                                        <label class="form-label" for="Room">Room</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>





                                        <div class="mt-4 pt-2">
                                            <input class="btn btn-outline-danger" type="submit" value="Submit" id="submit"  />
                                            <input class="btn btn-outline-danger" type="hidden" value="UpdateClass" id="submit" name="action"  />
                                            <input class="btn btn-outline-danger" type="hidden" value="${lopHocDTO.maLopHoc}" id="submit" name="maLopHoc"  />
                                        </div>




                                    </div>
                                </div>
                            </div>
                        </div>
                        </div>
                    </section>
                </form>
            </div>

    </body>

    <script>




        const navMenu = document.querySelector("nav");

        // Find the desired element and assign it the "active" id
        const blogLiElement = navMenu.querySelector("#Class");
        if (blogLiElement) {
            const ulElement = blogLiElement.querySelector("ul");
            if (ulElement) {
                ulElement.id = "active";
            }
        }

        $(document).ready(function () {
            $('.sub-menu ul#active').show();
            $('li#active').find(".right").toggleClass("fa-caret-up fa-caret-down");
        });

        $('.sub-menu ul').hide();

        $(".sub-menu a").click(function () {
            $(this).parent(".sub-menu").children("ul").slideToggle("100");
            $(this).find(".right").toggleClass("fa-caret-up fa-caret-down");
        });
        function checkAvailability() {
            var slot = document.getElementById("Slot").value;
            var checkboxes = document.querySelectorAll('#dayInput input[type="checkbox"]:checked');
            var selectedDays = Array.from(checkboxes).map(function (checkbox) {
                return checkbox.value;
            });

            if (slot !== '' && selectedDays.length === 2) {
                var xhr = new XMLHttpRequest();
                xhr.open('GET', 'Admin/Class/checkAvailability.jsp?slot=' + slot + "&weekday=" + selectedDays.join(','), true);
                xhr.responseType = 'document'; // Set the response type to 'document' to handle the response as XML
                xhr.responseType = 'text';
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
                        var responseText = xhr.responseText.trim();

                        var availability = (responseText == 'true');

                        // Now you can use the 'availability' boolean as needed
                        if (availability) {
                            var checkRoomElement = document.getElementById('checkRoom');
                            checkRoomElement.textContent = "There are available rooms";
                            checkRoomElement.style.fontWeight = 'bold';
                            checkRoomElement.style.color = 'green';
                            var button = document.getElementById('submit'); // Replace 'yourButtonId' with the actual ID of your button
                            button.disabled = false;
                        } else {
                            var checkRoomElement = document.getElementById('checkRoom');
                            checkRoomElement.textContent = "There are no available rooms";
                            checkRoomElement.style.fontWeight = 'bold';
                            checkRoomElement.style.color = 'red';
                            var button = document.getElementById('submit'); // Replace 'yourButtonId' with the actual ID of your button
                            button.disabled = true;
                        }
                    }
                };
                xhr.send();
            } else {
                var checkRoomElement = document.getElementById('checkRoom');
                checkRoomElement.textContent = '';
            }
        }
        function checkDate() {

            var dateElement = document.getElementById('initializeDate');
            var initializeDate = dateElement.value;

            if (initializeDate !== '') {
                var xhr = new XMLHttpRequest();
                xhr.open('GET', 'Authorization/Admin/Class/checkDate.jsp?initializeDate=' + initializeDate, true);
                xhr.responseType = 'document'; // Set the response type to 'document' to handle the response as XML
                xhr.responseType = 'text';
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
                        var responseText = xhr.responseText.trim();

                        var availability = (responseText == 'true');

                        // Now you can use the 'availability' boolean as needed
                        if (!availability) {
                            var checkDateElement = document.getElementById('checkDate');
                            checkDateElement.textContent = "Classes must be initialized 1 weeks before it is publicized for attendance";
                            checkDateElement.style.fontWeight = 'bold';
                            checkDateElement.style.color = 'red';

                            var button = document.getElementById('submit'); // Replace 'yourButtonId' with the actual ID of your button
                            button.disabled = true;
                        } else {
                            var checkDateElement = document.getElementById('checkDate');
                            checkDateElement.textContent = '';
                            var button = document.getElementById('submit'); // Replace 'yourButtonId' with the actual ID of your button
                            button.disabled = false;
                        }
                    }
                };
                xhr.send();
            } else {
                var checkDateElement = document.getElementById('checkDate');
                checkDateElement.textContent = '';
            }
        }

        function getHocPhi(maLoaiLopHoc) {
            if (maLoaiLopHoc !== '') {
                var xhr = new XMLHttpRequest();
                xhr.open('GET', 'Authorization/Admin/Class/getPrice.jsp?maLoaiLopHoc=' + maLoaiLopHoc, true);
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
                        var priceElement = document.getElementById('price');
                        priceElement.textContent = xhr.responseText + 'VND';

                    }
                };
                xhr.send();
            } else {
                var priceElement = document.getElementById('price');
                priceElement.textContent = '';

            }
        }


        var check = function () {
            if (document.getElementById('password').value ==
                    document.getElementById('confirmPassword').value) {
                document.getElementById('message').style.color = 'green';
                document.getElementById('message').innerHTML = 'Matching';
                document.getElementById('message').style.fontWeight = 'bold';
                document.getElementById('submit').disabled = false;
            } else {
                document.getElementById('message').style.color = 'red';
                document.getElementById('message').innerHTML = 'Not matching';
                document.getElementById('message').style.fontWeight = 'bold';
                document.getElementById('submit').disabled = true;
            }
        }
        const checkboxes = document.querySelectorAll('input[name="weekday"]');
        const limit = 2; // Set the desired limit

        let checkedCount = 0;
        checkboxes.forEach(function (checkbox) {
            checkbox.addEventListener("change", function () {
                if (this.checked) {
                    checkedCount++;
                } else {
                    checkedCount--;
                }

                if (checkedCount >= limit) {
                    checkboxes.forEach(function (checkbox) {
                        if (!checkbox.checked) {
                            checkbox.disabled = true;
                        }
                    });
                } else {
                    checkboxes.forEach(function (checkbox) {
                        checkbox.disabled = false;
                    });
                }
            });
        });
    </script>
</html>
