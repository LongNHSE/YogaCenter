<%-- 
    Document   : Schedule
    Created on : May 31, 2023, 7:13:35 AM
    Author     : Oalskad
--%>

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
        <link href="ScheduleStyle.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" rel="stylesheet">
        <title>JSP Page</title>
    </head>
    <body>

        <% List<ScheduleHvDTO> listScheduleHvDTO = (List<ScheduleHvDTO>) request.getAttribute("listScheduleHv");
            List<SlotDTO> listSlot = (List<SlotDTO>) request.getAttribute("listSlot");%>





        <div class="container">
            <div class="timetable-img text-center">
                <img src="img/content/timetable.png" alt="">
            </div>
            <div class="table-responsive">
                <table class="table table-bordered text-center">
                    <thead>
                        <tr class="bg-light-gray">
                            <th class="text-uppercase">Time
                            </th>
                            <th class="text-uppercase">Monday</th>
                            <th class="text-uppercase">Tuesday</th>
                            <th class="text-uppercase">Wednesday</th>
                            <th class="text-uppercase">Thursday</th>
                            <th class="text-uppercase">Friday</th>
                            <th class="text-uppercase">Saturday</th>
                            <th class="text-uppercase">Sunday</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% int i = 1;
                            
                            for (SlotDTO slotDTO : listSlot) {
                                String lastSlot = Integer.toString(i);
                                String slot = "SL00" + lastSlot;
                                Date ld = Date.valueOf("2023-06-05");
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                        %>
                        <tr>
                            <td class="align-middle"> SLOT <%=i%> <br><%=slotDTO.getTimeStart()%> - <%=slotDTO.getTimeEnd()%></th>
                                <% for (int day = 0; day < 7; day++) {
                                        boolean hasSchedule = false;

                                        
                                        String dayOfWeek = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US);
                                        for (ScheduleHvDTO scheduleHocVienDTO : listScheduleHvDTO) {
                                if (scheduleHocVienDTO.getThu().equalsIgnoreCase(dayOfWeek) && scheduleHocVienDTO.getMaSlot().equals(slot)) { 
                                hasSchedule=true;
                                break;
    
    }}%>


    
                            <td>
                               
                                <% if(hasSchedule){ %>
                                <span class="bg-yellow padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">Music</span>
                                <div class="margin-10px-top font-size14">10:00-11:00</div>
                                <div class="font-size13 text-light-gray">Ivana Wong</div>
                                <% }%>
                            </td>
                            <%  calendar.add(Calendar.DAY_OF_WEEK, 1); } %>



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
</html>
