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
        <!--        <link rel="stylesheet" href="../css/jquery.mCustomScrollbar.min.css">
                 Tweaks for older IEs
                <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
                 owl stylesheets 
                <link rel="stylesheet" href="../css/owl.carousel.min.css">
                <link rel="stylesheet" href="../css/owl.theme.default.min.css">
                <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css"
                      media="screen">        -->
    </head>
    <body>
        <!-- header section start -->

        <%
            List<SlotDTO> listSlot = (List<SlotDTO>) request.getAttribute("listSlot");
            List<LocalDate> listDate = (List<LocalDate>) request.getAttribute("listDate");
            List<DateStartAndDateEnd> weekRanges = (List<DateStartAndDateEnd>) request.getAttribute("weekRanges");

        %>











        <div class="container">
            <div class="timetable-img text-center">
                <img src="img/content/timetable.png" alt="">
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
                                        message += "Hien Khong Co Phong Trong";
                                        phongTrong = false;
                                    } else {
                                        message += "Hien Dang Co " + soPhongTrong + " Phong Trong";
                                        phongTrong = true;
                                    }

                            %>



                        <td>

                            <% if (!phongTrong) {%>
                            <span class="bg-warning padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13"><%=message%></span>


                            <% } else {%>

                            <div onchange="checkSlot()">
                                <div style="align: center">
                                    <span class="bg-green padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13" style="text-align: center;"><%=message%></span>
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
                    <input class="btn btn-outline-danger" type="submit" value="Submit" id="submit"  />
                    <input class="btn btn-outline-danger" type="hidden" value="CreateClassPage" id="submit" name="action"  />
                </form>    
            </div>

        </div>

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
