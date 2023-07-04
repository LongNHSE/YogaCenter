<%-- 
    Document   : Schedule
    Created on : May 31, 2023, 7:13:35 AM
    Author     : Oalskad
--%>

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





        <!-- Development version -->


        <!-- Production version -->

        <link href="<%=url%>/Authorization/Admin/cssAdmin/ScheduleStyle.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <title>JSP Page</title>

        <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">


        <link href="img/favicon.ico" rel="icon">
        <link href="img/apple-favicon.png" rel="apple-touch-icon">
        <link href="<%=url%>/Authorization/Admin/cssAdmin/newCascadeStyleSheet.css" rel="stylesheet" type="text/css"/>
        <script class="u-script" type="text/javascript" src="<%=url%>/js/home2.js" defer=""></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script class="u-script" type="text/javascript" src="<%=url%>/js/home1.js" defer=""></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">

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
            .weekRangeController{
                margin-left: 300px;
            }
            .container{
                min-width: 1200px;
                max-width: 1400px;
                margin-left: 285px;
            }

        </style>
    </head>
    <div class="Controller">
        <div class="wrapper">
            <nav class='animated bounceInDown bg-dark'>
                <ul>
                    <li><a href='<%=url%>/Authorization/Admin/AdminHomepage.jsp'>Profile</a></li>
                    <li id="active" class='sub-menu'><a href='#settings'><i class="fa-solid fa-school"></i>Class<div class='fa fa-caret-down right'></div></a>
                        <ul id="active">
                            <li ><a href='<%=url%>/AdminController?action=listLopHoc&page=1'>List Class</a></li>
                            <li ><a href='<%=url%>/AdminController?action=listClassUnassigned'>List Class Unassigned</a></li>
                            <li><a href='<%=url%>/ClassController?action=CheckEmptyRoom'>Create Class</a></li>
                            <li  id="active-element"><a href='<%=url%>/AdminController?action=ViewSchedule'>View Schedule</a></li>
                            <li><a href='<%=url%>/Authorization/Admin/Class/CreateClassTypePage.jsp'>Create Class Type</a></li>
                        </ul>
                    </li>
                    <li class='sub-menu'><a href='#message'>Trainee<div class='fa fa-caret-down right'></div></a>
                        <ul>
                            <li><a href="<%=url%>/AdminController?action=listHocVien">List Trainee</a></li>
                            <li><a href='#settings'>Submit a Ticket</a></li>
                            <li><a href='#settings'>Network Status</a></li>
                        </ul>
                    </li>
                    <li class='sub-menu'><a href='#message'>Trainer<div class='fa fa-caret-down right'></div></a>
                        <ul>
                            <li><a href="<%=url%>/AdminController?action=listHocVien">List Trainer</a></li>
                            <li><a href='<%=url%>/Authorization/Admin/Trainer/AddTrainer.jsp'>Add Trainer</a></li>
                            <li><a href='#settings'>Network Status</a></li>
                        </ul>
                    </li>
                    <li class='sub-menu'><a href='#message'>Application<div class='fa fa-caret-down right'></div></a>
                        <ul>
                            <li><a href="<%=url%>/AdminController?action=listHocVien">List Trainer</a></li>
                            <li><a href="">Add Trainer</a></li>
                            <li><a href='#settings'>Network Status</a></li>
                        </ul>
                    </li>
                    <li><a href='<%=url%>/LoginController?action=adminLogout'>Logout</a></li>
                </ul>
            </nav>
        </div>
        <div class="content">

        </div>         
    </div>
    <body>
        <!-- header section start -->

        <% List<ScheduleTempDTO> listScheduleTemp = (List<ScheduleTempDTO>) request.getAttribute("listScheduleTemp");
            List<SlotDTO> listSlot = (List<SlotDTO>) request.getAttribute("listSlot");
            List<LocalDate> listDate = (List<LocalDate>) request.getAttribute("listDate");
            List<DateStartAndDateEnd> weekRanges = (List<DateStartAndDateEnd>) request.getAttribute("weekRanges");
            List<ScheduleTrainerDTO> listScheduleTrainer = (List<ScheduleTrainerDTO>) request.getAttribute("listScheduleTrainer");

        %>
        <div class="weekRangeController">
            <form action="AdminController" method="post">
                <select name="weekRange">
                    <%                        for (DateStartAndDateEnd weekRange : weekRanges) {%>



                    <option id="schedule" value=<%=weekRange.getDateStart()%> <% if (weekRange.getDateStart().equals(listDate.get(0))) {%> selected <% }%>> <%=weekRange.getFormattedStartDate()%> - <%=weekRange.getFormattedEndDate()%> </option>;
                    <% }
                    %>
                </select>
                <input type="submit" value="Submit">
                <input class="btn btn-outline-danger" type="hidden" value="ViewSchedule" id="submit" name="action"  />
            </form>
        </div>









        <div class="container">
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
                                        List<String> listClassTemp = new ArrayList<String>();
                                        List<TrainerDTO> listClassTrainer = new ArrayList<>();

                                        boolean hasSchedule = false;
                                        LopHocDAO lopHocDAO = new LopHocDAO();
                                        String maLopHoc = "";
                                        String tenLopHoc = "";
                                        boolean hasTrainerSchedule = false;
                                        String maLopHocTrainer = "";
                                        String tenTrainer = "";

                                        String dayOfWeek = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US);
                                        for (ScheduleTempDTO ScheduleTempDTO : listScheduleTemp) {
                                            if (ScheduleTempDTO.getThu().equalsIgnoreCase(dayOfWeek) && ScheduleTempDTO.getMaSlot().equals(slot) && ScheduleTempDTO.getNgayHoc().equals(Date.valueOf(listDate.get(day)))) {
                                                hasSchedule = true;
                                                maLopHoc += ScheduleTempDTO.getMaLopHoc();
                                                tenLopHoc += lopHocDAO.tenLopHoc(lopHocDAO.IDLoaiLopHoc(ScheduleTempDTO.getMaLopHoc()));
                                                listClassTemp.add(maLopHoc);

                                            }

                                        }
                                        for (ScheduleTrainerDTO scheduleTrainerDTO : listScheduleTrainer) {
                                            if (scheduleTrainerDTO.getThu().equalsIgnoreCase(dayOfWeek) && scheduleTrainerDTO.getMaSlot().equals(slot) && scheduleTrainerDTO.getNgayHoc().equals(Date.valueOf(listDate.get(day)))) {
                                                hasTrainerSchedule = true;
                                                TrainerDTO trainerDTO = new TrainerDTO();
                                                trainerDTO.setMaLopHoc(scheduleTrainerDTO.getMaLopHoc());
                                                trainerDTO.setMaTrainer(scheduleTrainerDTO.getMaTrainer());
                                                listClassTrainer.add(trainerDTO);
                                            }
                                        }%>



                            <td>

                                <% if (hasSchedule) {
                                        for (String x : listClassTemp) {%>

                                <span class="bg-yellow padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13"><%=x%></span>


                                <% }
                                    }%>
                                <% if (hasTrainerSchedule) {%>
                                <% for (TrainerDTO trainer : listClassTrainer) {%>
                                <div class  
                                     ="bg-green" style="text-align: center; margin-bottom: 10px">
                                    <span 
                                        class  

                                        =" padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13"> <%= trainer.getMaLopHoc()%><br><%=trainer.getMaTrainer()%></span>
                                </div>

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
        <script>
            $(document).ready(function () {
                $('.sub-menu ul#active').show();
                $('li#active').find(".right").toggleClass("fa-caret-up fa-caret-down");
            });
            $('.sub-menu ul').hide();

            $(".sub-menu a").click(function () {
                $(this).parent(".sub-menu").children("ul").slideToggle("100");
                $(this).find(".right").toggleClass("fa-caret-up fa-caret-down");
            });
            //scroll slides on swipe for touch enabled devices

        </script>    
    </body>
    <!-- Javascript files-->


</html>
