<%-- 
    Document   : Schedule
    Created on : May 31, 2023, 7:13:35 AM
    Author     : Oalskad
--%>

<%@page import="com.mycompany.yogacenterproject.dao.PhongHocDAO"%>
<%@page import="com.mycompany.yogacenterproject.dao.ScheduleDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<%@page import="com.mycompany.yogacenterproject.dao.LopHocDAO"%>
<%@page import="com.mycompany.yogacenterproject.dto.DateStartAndDateEnd"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.sql.Date"%>
<%@page import="com.mycompany.yogacenterproject.dto.HocVienDTO"%>
<%@page import="com.mycompany.yogacenterproject.dto.SlotDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.yogacenterproject.dto.ScheduleHvDTO"%>

<!DOCTYPE html>
<html>
    <head>
        <link href="<%=url%>/Authorization/Admin/cssAdmin/newCascadeStyleSheet.css" rel="stylesheet" type="text/css"/>
        <link href="<%=url%>/Authorization/Admin/cssAdmin/ScheduleStyle.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

        <script class="u-script" type="text/javascript" src="<%=url%>/js/home2.js" defer=""></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script class="u-script" type="text/javascript" src="<%=url%>/js/home1.js" defer=""></script>
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
            }</style>


        <div class="Controller">
            <%
                List<SlotDTO> listSlot = (List<SlotDTO>) request.getAttribute("listSlot");
                List<LocalDate> listDate = (List<LocalDate>) request.getAttribute("listDate");
                List<DateStartAndDateEnd> weekRanges = (List<DateStartAndDateEnd>) request.getAttribute("weekRanges");

            %>
            <div class="wrapper">
                <%@include file="../NavComponents.jsp" %>
            </div>
            <div class="content">
                <div class="container">
                    <div class="timetable-img text-center">
                        <img src="<%=url%>/img/content/timetable.png" alt="">
                    </div>
                    <div class="table-responsive">
                        <form action="ClassController">
                            <table class="table table-bordered text-center">
                                <thead>
                                    <tr class="bg-light-gray">
                                        <th class="text-uppercase" rowspan="2">Time
                                        </th>

                                        <th class="text-uppercase">Monday</th>
                                        <th class="text-uppercase">Tuesday</th>
                                        <th class="text-uppercase">Wednesday</th>
                                        <th class="text-uppercase">Thursday</th>
                                        <th class="text-uppercase">Friday</th>
                                        <th class="text-uppercase">Saturday</th>
                                        <th class="text-uppercase">Sunday</th>
                                    </tr>

                                    <tr class="bg-light-gray">

                                        <% for (LocalDate date : listDate) {%>

                                        <th class="text-uppercase"><%=date%></th>
                                            <% }%>
                                    </tr>
                                </thead>

                                <tbody>
                                    <% int i = 1;

                                        for (SlotDTO slotDTO : listSlot) {
                                            String lastSlot = Integer.toString(i);
                                            String slot = "SL00" + lastSlot;
                                            LocalDate firstDate = listDate.get(0);

                                            Calendar calendar = Calendar.getInstance();
                                            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

                                    %>

                                    <tr>
                                <input class="weekday" type="hidden"  name="slot" id="weekday"
                                       data-slot="<%=slot%>" value="<%=slot%>" 
                                       /> 
                                <td class="align-middle"> SLOT <%=i%> <br><%=slotDTO.getTimeStart()%> - <%=slotDTO.getTimeEnd()%></th>
                                    <% for (int day = 0; day < 7; day++) {
                                            String[] weekdays = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

                                            boolean phongTrong = false;
                                            ScheduleDAO scheduleDAO = new ScheduleDAO();
                                            String message = "";
                                            PhongHocDAO phongHocDAO = new PhongHocDAO();
                                            int soPhongTrong = phongHocDAO.soPhongTrong(slotDTO.getMaSlot(), weekdays[day]);
                                            String dayOfWeek = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US);
                                            if (soPhongTrong == 0) {
                                                message += "No Available Rooms Found!";
                                                phongTrong = false;
                                            } else {
                                                message += "There Are Currently " + soPhongTrong + " Empty Rooms";
                                                phongTrong = true;
                                            }

                                    %>



                                <td>

                                    <% if (!phongTrong) {%>
                                    <div style="align: center; background-color: yellow; ">
                                        <span class=" padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-black font-size16  xs-font-size13"><%=message%></span>
                                    </div>

                                    <% } else {%>

                                    <div onchange="checkSlot()">
                                        <div style="align: center; background-color: greenyellow; ">
                                            <span class=" padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-black font-size16  xs-font-size13" style="text-align: center;"><%=message%></span>
                                        </div>
                                        <input class="weekday" type="checkbox" name="weekday" id="weekday"
                                               data-slot="<%=slot%>" data-day="<%=dayOfWeek%>" value="<%=dayOfWeek%>"
                                               />
                                    </div>

                                    <% } %>

                                </td>
                                <%  calendar.add(Calendar.DAY_OF_WEEK, 1);

                                    } %>



                                </tr>
                                <% i++;

                                    }%>

                                </tbody>

                            </table>
                            <div style = "text-align:center; margin-bottom: 20px ">
                                <input  class="btn btn-outline-danger" type="submit" value="Submit" id="submit"  />
                                <input class="btn btn-outline-danger" type="hidden" value="CreateClassPage" id="submit" name="action"  />
                            </div>
                        </form>    
                    </div>

                </div>

            </div>         
        </div>


        <!-- header section start -->














    </body>

    <script>
        var checkedCount = 0;

        function checkSlot() {
            var checkboxes = document.getElementsByClassName("weekday");

            for (var i = 0; i < checkboxes.length; i++) {
                var innerCheckbox = checkboxes[i];
                var slot = innerCheckbox.getAttribute("data-slot");
                if (innerCheckbox.checked) {
                    var slotChecked = slot;
                }

                if (slotChecked != null) {
                    for (var j = 0; j < checkboxes.length; j++) {
                        var innerCheckbox = checkboxes[j];
                        var slot = innerCheckbox.getAttribute("data-slot");
                        if (slot !== slotChecked) {
                            innerCheckbox.disabled = true;
                            innerCheckbox.checked = false;
                        } else {
                            innerCheckbox.disabled = false;
                        }
                    }
                } else {
                    innerCheckbox.disabled = false;
                }

            }

        }
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
        s
        function handleCheckboxChange() {
            if (this.checked) {
                checkedCount++;
            } else {
                checkedCount--;
            }

            var checkboxes = document.querySelectorAll('input[name="weekday"]');
            const limit = 2;

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

            console.log(checkedCount);
        }

        function initializeCheckboxes() {
            var checkboxes = document.querySelectorAll('input[name="weekday"]');
            checkboxes.forEach(function (checkbox) {
                checkbox.addEventListener("change", handleCheckboxChange);
            });
        }

        window.onload = initializeCheckboxes();


        // Attach event listeners to checkboxes


        //    
    </script>    
</html>
