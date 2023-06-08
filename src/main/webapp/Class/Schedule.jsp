<%-- 
    Document   : Schedule
    Created on : May 31, 2023, 7:13:35 AM
    Author     : Oalskad
--%>
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
    <body>
  <!-- header section start -->
      <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="logo"><a href="index.html"><img src="images/logo.png"></a></div>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup"
          aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
          <div class="navbar-nav">
            <a class="nav-item nav-link" href="home.jsp">Home</a>
            <a class="nav-item nav-link" href="about.html">About</a>
            <a class="nav-item nav-link" href="classes.html">Classes</a>
            <a class="nav-item nav-link" href="schedule.html">Schedule</a>
            <a class="nav-item nav-link" href="trainer.html">Trainer</a>
            <a class="nav-item nav-link" href="contact.html">Contact us</a>
            <c:if test = "${sessionScope.user == null}">
                <a class="nav-item nav-link" href="<%=url%>/Authentication/signin.jsp">Login</a>              
            </c:if>
            <c:if test = "${sessionScope.user != null}">
                <div class="btn-group btn-user-menu">
                      <button style=" margin-left: 100px; background: #be2532 " class=" btn btn-secondary btn-sm dropdown-toggle btn-user" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">

                      <span>${sessionScope.user.username}</span>
                      </button>
                      <div class="dropdown-menu btn-menu">
                           <a class="dropdown-item btn-menu-item" href="profile.jsp">Profile</a>
                                                        <a class="dropdown-item btn-menu-item" href="changePassword.jsp">Change password</a>
                                                        <a class="dropdown-item btn-menu-item" href="<%=url%>/ScheduleController">View my ass</a>
                                                        <a class="dropdown-item btn-menu-item" href="<%=url%>/LoginController/signup?action=logout">
                                                              <b style="color: black">Logout</b>
                                                        </a>
                      </div>
                </div>               
            </c:if>
            <a class="nav-item nav-link" href="#"><img src="images/search-icon.png"></a>
          </div>
        </div>
      </nav>
        <% List<ScheduleHvDTO> listScheduleHvDTO = (List<ScheduleHvDTO>) request.getAttribute("listScheduleHv");
            List<SlotDTO> listSlot = (List<SlotDTO>) request.getAttribute("listSlot");
            List<LocalDate> listDate = (List<LocalDate>) request.getAttribute("listDate");
            List<DateStartAndDateEnd> weekRanges = (List<DateStartAndDateEnd>) request.getAttribute("weekRanges");

        %>





        <div class="container">
            <form action="ScheduleController" method="post">
                <select name="weekRange">
                    <%                        for (DateStartAndDateEnd weekRange : weekRanges) { %>
                  
                   
                    <option id="schedule" value=<%=weekRange.getDateStart() %> <% if(weekRange.getDateStart().equals(listDate.get(0))){%> selected <% } %>> <%=weekRange.getFormattedStartDate()%> - <%=weekRange.getFormattedEndDate()%> </option>;
                        <% }
                    %>
                </select>
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
                                        boolean hasSchedule = false;
                                        LopHocDAO lopHocDAO = new LopHocDAO();
                                        String maLopHoc ="";
                                        String tenLopHoc = "";
                                        String dayOfWeek = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US);
                                        for (ScheduleHvDTO scheduleHocVienDTO : listScheduleHvDTO) {
                                            if (scheduleHocVienDTO.getThu().equalsIgnoreCase(dayOfWeek) && scheduleHocVienDTO.getMaSlot().equals(slot) && scheduleHocVienDTO.getNgayHoc().equals(Date.valueOf(listDate.get(day)))) {
                                                hasSchedule = true;
                                                maLopHoc = scheduleHocVienDTO.getMaLopHoc();
                                                tenLopHoc = lopHocDAO.tenLopHoc(lopHocDAO.IDLoaiLopHoc(scheduleHocVienDTO.getMaLopHoc()));
                                                break;

                                            }
                                        }%>



                            <td>

                                <% if (hasSchedule) { %>
                                 <span class="bg-yellow padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13"><%=tenLopHoc %></span>
                                <div class="margin-10px-top font-size14"><%=maLopHoc %></div>
                                
                                <% }%>
                            </td>
                            <%  calendar.add(Calendar.DAY_OF_WEEK, 1);
                                } %>



                        </tr>
                        <% i++;

                            }%>




                        <!--                        <tr>
                                                    <th class="align-middle">09:00am</th>
                        
                                                </tr>
                        
                                                <tr>
                                                    <td class="align-middle">10:00am</td>
                                                    <td>
                                                        <span class="bg-yellow padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">Music</span>
                                                        <div class="margin-10px-top font-size14">10:00-11:00</div>
                                                        <div class="font-size13 text-light-gray">Ivana Wong</div>
                                                    </td>
                                                    <td class="bg-light-gray">
                        
                                                    </td>
                                                    <td>
                                                        <span class="bg-purple padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">Art</span>
                                                        <div class="margin-10px-top font-size14">10:00-11:00</div>
                                                        <div class="font-size13 text-light-gray">Kate Alley</div>
                                                    </td>
                                                    <td>
                                                        <span class="bg-green padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">Yoga</span>
                                                        <div class="margin-10px-top font-size14">10:00-11:00</div>
                                                        <div class="font-size13 text-light-gray">Marta Healy</div>
                                                    </td>
                                                    <td>
                                                        <span class="bg-pink padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">English</span>
                                                        <div class="margin-10px-top font-size14">10:00-11:00</div>
                                                        <div class="font-size13 text-light-gray">James Smith</div>
                                                    </td>
                                                    <td class="bg-light-gray">
                        
                                                    </td>
                                                </tr>
                        
                                                <tr>
                                                    <td class="align-middle">11:00am</td>
                                                    <td>
                                                        <span class="bg-lightred padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">Break</span>
                                                        <div class="margin-10px-top font-size14">11:00-12:00</div>
                                                    </td>
                                                    <td>
                                                        <span class="bg-lightred padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">Break</span>
                                                        <div class="margin-10px-top font-size14">11:00-12:00</div>
                                                    </td>
                                                    <td>
                                                        <span class="bg-lightred padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">Break</span>
                                                        <div class="margin-10px-top font-size14">11:00-12:00</div>
                                                    </td>
                                                    <td>
                                                        <span class="bg-lightred padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">Break</span>
                                                        <div class="margin-10px-top font-size14">11:00-12:00</div>
                                                    </td>
                                                    <td>
                                                        <span class="bg-lightred padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">Break</span>
                                                        <div class="margin-10px-top font-size14">11:00-12:00</div>
                                                    </td>
                                                    <td>
                                                        <span class="bg-lightred padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">Break</span>
                                                        <div class="margin-10px-top font-size14">11:00-12:00</div>
                                                    </td>
                                                </tr>
                        
                                                <tr>
                                                    <td class="align-middle">12:00pm</td>
                                                    <td class="bg-light-gray">
                        
                                                    </td>
                                                    <td>
                                                        <span class="bg-purple padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">Art</span>
                                                        <div class="margin-10px-top font-size14">12:00-1:00</div>
                                                        <div class="font-size13 text-light-gray">Kate Alley</div>
                                                    </td>
                                                    <td>
                                                        <span class="bg-sky padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">Dance</span>
                                                        <div class="margin-10px-top font-size14">12:00-1:00</div>
                                                        <div class="font-size13 text-light-gray">Ivana Wong</div>
                                                    </td>
                                                    <td>
                                                        <span class="bg-yellow padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">Music</span>
                                                        <div class="margin-10px-top font-size14">12:00-1:00</div>
                                                        <div class="font-size13 text-light-gray">Ivana Wong</div>
                                                    </td>
                                                    <td class="bg-light-gray">
                        
                                                    </td>
                                                    <td>
                                                        <span class="bg-green padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">Yoga</span>
                                                        <div class="margin-10px-top font-size14">12:00-1:00</div>
                                                        <div class="font-size13 text-light-gray">Marta Healy</div>
                                                    </td>
                                                </tr>
                        
                                                <tr>
                                                    <td class="align-middle">01:00pm</td>
                                                    <td>
                                                        <span class="bg-pink padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">English</span>
                                                        <div class="margin-10px-top font-size14">1:00-2:00</div>
                                                        <div class="font-size13 text-light-gray">James Smith</div>
                                                    </td>
                                                    <td>
                                                        <span class="bg-yellow padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">Music</span>
                                                        <div class="margin-10px-top font-size14">1:00-2:00</div>
                                                        <div class="font-size13 text-light-gray">Ivana Wong</div>
                                                    </td>
                                                    <td class="bg-light-gray">
                        
                                                    </td>
                                                    <td>
                                                        <span class="bg-pink padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">English</span>
                                                        <div class="margin-10px-top font-size14">1:00-2:00</div>
                                                        <div class="font-size13 text-light-gray">James Smith</div>
                                                    </td>
                                                    <td>
                                                        <span class="bg-green padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">Yoga</span>
                                                        <div class="margin-10px-top font-size14">1:00-2:00</div>
                                                        <div class="font-size13 text-light-gray">Marta Healy</div>
                                                    </td>
                                                    <td>
                                                        <span class="bg-yellow padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">Music</span>
                                                        <div class="margin-10px-top font-size14">1:00-2:00</div>
                                                        <div class="font-size13 text-light-gray">Ivana Wong</div>
                                                    </td>
                                                </tr>-->
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
        }
        else if (Math.floor(yClick - yMove) < -1) {
          $(".carousel").carousel('prev');
        }
      });
      $(".carousel").on("touchend", function () {
        $(this).off("touchmove");
      });
    });
  </script>    
</html>
