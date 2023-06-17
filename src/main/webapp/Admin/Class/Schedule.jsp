<%-- 
    Document   : Schedule
    Created on : May 31, 2023, 7:13:35 AM
    Author     : Oalskad
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.mycompany.yogacenterproject.dto.ScheduleTempDTO"%>
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
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="Class/ScheduleStyle.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" rel="stylesheet">
        <title>JSP Page</title>
        <!-- bootstrap css -->
        <link rel="stylesheet" href="../css/bootstrap.min.css">
        <!-- style css -->
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <!-- Responsive-->
        <link rel="stylesheet" href="../css/responsive.css">
        <!-- fevicon -->
        <link rel="icon" href="images/fevicon.png" type="image/gif" />
        <!-- Scrollbar Custom CSS -->
        <link rel="stylesheet" href="../css/jquery.mCustomScrollbar.min.css">
        <!-- Tweaks for older IEs-->
        <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
        <!-- owl stylesheets -->
        <link rel="stylesheet" href="../css/owl.carousel.min.css">
        <link rel="stylesheet" href="../css/owl.theme.default.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css"
              media="screen">        
    </head>
    <header id="header">
        <div class="mobile-menu-btn"><i class="fa fa-bars"></i></div>
        <nav class="main-menu top-menu">
            <ul>
                <li><a href="<%= url%>/Admin/AdminHomepage.jsp">Home</a></li>

                <li>
                    <form action="ADlogoutController" method="POST">

                        <input class="btn btn-outline-danger"  type="submit" value="Logout"></input>

                    </form>
                </li>
            </ul>
        </nav>
    </header>
    <body>
        <!-- header section start -->

        <% List<ScheduleTempDTO> listScheduleTemp = (List<ScheduleTempDTO>) request.getAttribute("listScheduleTemp");
            List<SlotDTO> listSlot = (List<SlotDTO>) request.getAttribute("listSlot");
            List<LocalDate> listDate = (List<LocalDate>) request.getAttribute("listDate");
            List<DateStartAndDateEnd> weekRanges = (List<DateStartAndDateEnd>) request.getAttribute("weekRanges");

        %>

        <form action="AdminController" method="post">
            <select name="weekRange">
                <%                        for (DateStartAndDateEnd weekRange : weekRanges) {%>



                <option id="schedule" value=<%=weekRange.getDateStart()%> <% if (weekRange.getDateStart().equals(listDate.get(0))) {%> selected <% }%>> <%=weekRange.getFormattedStartDate()%> - <%=weekRange.getFormattedEndDate()%> </option>;
                <% }
                %>
            </select>
            <input type="submit" value="Submit">
            <input class="btn btn-outline-danger" type="hidden" value="View Schedule" id="submit" name="action"  />
        </form>









        <div class="container">
            <form action="ScheduleController" method="post">

                <input type="submit" value="Submit">
            </form>
            <div class="timetable-img text-center">
                <img src="img/content/timetable.png" alt="">
            </div>
            <div class="table-responsive">
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
                            <td class="align-middle"> SLOT <%=i%> <br><%=slotDTO.getTimeStart()%> - <%=slotDTO.getTimeEnd()%></th>
                                <% for (int day = 0; day < 7; day++) {
                                        List<String> a = new ArrayList<String>();
                                        boolean hasSchedule = false;
                                        LopHocDAO lopHocDAO = new LopHocDAO();
                                        String maLopHoc = "";
                                        String tenLopHoc = "";
                                        String dayOfWeek = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US);
                                        for (ScheduleTempDTO ScheduleTempDTO : listScheduleTemp) {
                                            if (ScheduleTempDTO.getThu().equalsIgnoreCase(dayOfWeek) && ScheduleTempDTO.getMaSlot().equals(slot) && ScheduleTempDTO.getNgayHoc().equals(Date.valueOf(listDate.get(day)))) {
                                                hasSchedule = true;
                                                maLopHoc = ScheduleTempDTO.getMaLopHoc();
                                                tenLopHoc = lopHocDAO.tenLopHoc(lopHocDAO.IDLoaiLopHoc(ScheduleTempDTO.getMaLopHoc()));
                                                a.add(maLopHoc);

                                            }

                                        }%>



                            <td>

                                <% if (hasSchedule) {
                                        for (String x : a) {%>

                                <span class="bg-yellow padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13"><%=x %></span>
                                

                                <% }
                                    }%>
                            </td>
                            <%  calendar.add(Calendar.DAY_OF_WEEK, 1);
                                } %>



                        </tr>
                        <% i++;

                            }%>

                    </tbody>
                </table>
            </div>
        </div>

    </body>
    <!-- Javascript files-->
    <script src="../js/jquery.min.js"></script>
    <script src="../js/popper.min.js"></script>
    <script src="../js/bootstrap.bundle.min.js"></script>
    <script src="../js/jquery-3.0.0.min.js"></script>
    <script src="../js/plugin.js"></script>
    <!-- sidebar -->
    <script src="../js/jquery.mCustomScrollbar.concat.min.js"></script>
    <script src="../js/custom.js"></script>
    <!-- javascript -->
    <script src="../js/owl.carousel.js"></script>
    <script src="https:cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.js"></script>
    <script>
        $('#myCarousel').carousel({
            interval: false
        });

        //scroll slides on swipe for touch enabled devices

        $("#myCarousel").on("touchstart", function (event) {

            var yClick = event.originalEvent.touches[0].pageY;
            $(this).one("touchmove", function (event) {

                var yMove = event.originalEvent.touches[0].pageY;
                if (Math.floor(yClick - yMove) > 1) {
                    $(".carousel").carousel('next');
                } else if (Math.floor(yClick - yMove) < -1) {
                    $(".carousel").carousel('prev');
                }
            });
            $(".carousel").on("touchend", function () {
                $(this).off("touchmove");
            });
        });
    </script>    
</html>
