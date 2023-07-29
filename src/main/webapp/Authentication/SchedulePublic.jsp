<%-- 
    Document   : Schedule
    Created on : May 31, 2023, 7:13:35 AM
    Author     : Oalskad
--%>

<%@page import="com.mycompany.yogacenterproject.dto.LoaiLopHocDTO"%>
<%@page import="com.mycompany.yogacenterproject.dto.LopHocDTO"%>
<%@page import="com.mycompany.yogacenterproject.dto.TrainerDTO"%>
<%@page import="com.mycompany.yogacenterproject.dto.ScheduleTrainerDTO"%>
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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <title>JSP Page</title>
        <!-- bootstrap css -->
        <link rel="stylesheet" href="../css/bootstrap.min.css">
        <!-- style css -->
      <link href="<%=url%>/css/SchedulePublicStyles.css" rel="stylesheet" type="text/css"/>
        <!-- Responsive-->

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



        <!-- Development version -->


        <!-- Production version -->



    </head>
    <div class="Controller">
        <div class="wrapper">
            <jsp:include page="../Components/headerComponent.jsp" />      
        </div>
                    <h1 class="schedule-title">Discover the Joy of Yoga with Our Class Schedule</h1>
        <div class="content">

        </div>         
    </div>
    <body>
        <!-- header section start -->

        <%
            List<SlotDTO> listSlot = (List<SlotDTO>) request.getAttribute("listSlot");
            List<LocalDate> listDate = (List<LocalDate>) request.getAttribute("listDate");
            List<DateStartAndDateEnd> weekRanges = (List<DateStartAndDateEnd>) request.getAttribute("weekRanges");
            List<ScheduleTrainerDTO> listScheduleTrainer = (List<ScheduleTrainerDTO>) request.getAttribute("listScheduleTrainer");
            List<LoaiLopHocDTO> listLoaiLopHoc = (List<LoaiLopHocDTO>) request.getAttribute("listLoaiLopHoc");

        %>










        <div class="container">
            <div class="weekRangeController">
                <form action="ClassController" method="post">
                    <select name="weekRange">
                        <%                        for (DateStartAndDateEnd weekRange : weekRanges) {%>



                        <option id="schedule" value=<%=weekRange.getDateStart()%> <% if (weekRange.getDateStart().equals(listDate.get(0))) {%> selected <% }%>> <%=weekRange.getFormattedStartDate()%> - <%=weekRange.getFormattedEndDate()%> </option>;
                        <% }
                        %>
                    </select>

                    <input class="btn btn-outline-danger" type="hidden" value="ViewSchedulePublic" id="submit" name="action"  />

                    <select name="loaiLopHoc">
                        <option id="schedule" value="all" >Class Type</option>;

                        <%                        for (LoaiLopHocDTO loaiLopHoc : listLoaiLopHoc) {%>



                        <option id="schedule" value=<%=loaiLopHoc.getMaLoaiLopHoc()%> ><%=loaiLopHoc.getTenLoaiLopHoc()%> </option>;
                        <% }
                        %>
                    </select>
                    <input type="submit" value="Select" class="text-center select-btn">
                    <input class="btn btn-outline-danger" type="hidden" value="ViewSchedulePublic" id="submit" name="action"  />
                </form>
            </div>
            <form action="ScheduleController" method="post">


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

                                        List<LopHocDTO> listClassDTO = new ArrayList<>();

                                        LopHocDAO lopHocDAO = new LopHocDAO();

                                        boolean hasTrainerSchedule = false;

                                        LopHocDTO lopHocDTO = new LopHocDTO();

                                        String dayOfWeek = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US);

                                        for (ScheduleTrainerDTO scheduleTrainerDTO : listScheduleTrainer) {
                                            if (scheduleTrainerDTO.getThu().equalsIgnoreCase(dayOfWeek) && scheduleTrainerDTO.getMaSlot().equals(slot) && scheduleTrainerDTO.getNgayHoc().equals(Date.valueOf(listDate.get(day)))) {
                                                hasTrainerSchedule = true;

                                                lopHocDTO = lopHocDAO.searchClassById(scheduleTrainerDTO.getMaLopHoc());
                                                listClassDTO.add(lopHocDTO);

                                            }
                                        }%>



                            <td>


                                <% if (hasTrainerSchedule) {%>
                                <% for (LopHocDTO lopHoc : listClassDTO) {%>
                                <a href="<%=url%>/ClassController?action=showDetails&returnID=<%=lopHoc.getMaLoaiLopHoc()%>">
                                     <div class="class-code lop-code" data-maLopHoc="<%= lopHoc.getMaLopHoc()%>">

                                        <div class="bg-green class-details" style="text-align: center; margin-bottom: 10px; border: solid 0.1px">
                                            <span class="padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-black font-size16 xs-font-size13"><%= lopHoc.getLoaiLopHocDTO().getTenLoaiLopHoc()%><br><%= lopHoc.getMaLopHoc()%></span>
                                        </div>
                                    </div>
                                </a>
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
        <style>
            .container, .container-lg, .container-md, .container-sm, .container-xl {
                max-width: 1662px;
            }
        </style>
        <style>


            .class-code.hovered .bg-green {
                background-color: greenyellow; /* Update with your desired highlight color */
            }
        </style>
        <script>
            var lopCodeElements = document.getElementsByClassName("lop-code");

            for (var i = 0; i < lopCodeElements.length; i++) {
                lopCodeElements[i].addEventListener("mouseover", function () {
                    var maLopHoc = this.getAttribute("data-maLopHoc");
                    var elements = document.querySelectorAll('[data-maLopHoc="' + maLopHoc + '"]');
                    for (var j = 0; j < elements.length; j++) {
                        elements[j].classList.add("hovered");
                    }
                });

                lopCodeElements[i].addEventListener("mouseout", function () {
                    var maLopHoc = this.getAttribute("data-maLopHoc");
                    var elements = document.querySelectorAll('[data-maLopHoc="' + maLopHoc + '"]');
                    for (var j = 0; j < elements.length; j++) {
                        elements[j].classList.remove("hovered");
                    }
                });
            }
        </script>
    </body>
    <!-- Javascript files-->


</html>
