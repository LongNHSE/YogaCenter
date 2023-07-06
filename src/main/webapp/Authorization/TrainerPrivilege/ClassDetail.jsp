<%-- 
    Document   : newjsp
    Created on : May 23, 2023, 1:56:24 PM
    Author     : Oalskad
--%>



<%@page import="java.sql.Date"%>
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
            border-collapse: collapse; /* Collapse the borders of table cells */

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
    <%
        List<HocVienDTO> listHocVienDTO = (List<HocVienDTO>) request.getAttribute("listHocVienDTO");
        LopHocDTO lopHocDTO = (LopHocDTO) request.getAttribute("lopHocDTO");
        TrainerDTO trainerDTO = (TrainerDTO) request.getAttribute("trainerDTO");
        LoaiLopHocDAO loaiLopHocDAO = new LoaiLopHocDAO();
        Date ngayHoc = (Date) request.getAttribute("ngayHoc");
        String maSlot = (String) request.getAttribute("maSlot");

    %>

    <body>


        <jsp:include page="${url}/Components/headerComponent.jsp" />    
        <div class="Controller">
            <div class="Table">
                <div class="ClassDetail">
                    <div class="ClassName">
                        <div class="class-properties">
                            <div class="cf-title-03">
                                <h3>Class <%=lopHocDTO.getMaLopHoc()%> </h3>
                            </div>
                            <ul>
                                <li>
                                    <span class="property-name">Yoga:</span>
                                    <span class="property-value"><%=loaiLopHocDAO.searchTenLoaiLopHoc(lopHocDTO.getMaLoaiLopHoc())%></span>
                                </li>
                                <li>
                                    <span class="property-name">Trainer: </span>
                                    <span class="property-value"><%=trainerDTO.getHo()%> <%=trainerDTO.getTen()%></span>
                                </li>
                                <li>
                                    <span class="property-name">Number of Slots: </span>
                                    <span class="property-value"><%=lopHocDTO.getSoBuoi()%></span>
                                </li>
                                <li>
                                    <span class="property-name">Room: </span>
                                    <span class="property-value"><%=lopHocDTO.getMaRoom()%></span>
                                </li>
                                <li>
                                    <span class="property-name">Start date: </span>
                                    <span class="property-value"><%=lopHocDTO.getNgayBatDau()%></span>
                                </li>
                                <li>
                                    <span class="property-name">End date: </span>
                                    <span class="property-value"><c:out value="${requestScope.lastDay}"></c:out></span>
                                    </li>
                                </ul>
                            </div>

                        </div>
                        <div class="TrainerDetail">

                        </div>
                    </div>
                    <form action="<%=url%>/AttendanceController" method="POST">
                    <table class="table">
                        <div class="cf-title-03">
                            <h3>Number of trainees <%=listHocVienDTO.size()%>/<%=lopHocDTO.getSoLuongHV()%> </h3>
                        </div>
                        <thead>
                            <tr class="Test">


                                <th  scope="col">Ma Hoc Vien</th>
                                <th scope="col">Ho</th>
                                <th scope="col">Ten</th>

                                <th scope="col">Day of birth</th>



                                <th scope="col">Gender</th>


                            </tr>
                        </thead>
                        <tbody>
                            <% if (listHocVienDTO != null) {
                                    for (HocVienDTO hocVienDTO : listHocVienDTO) {
                            %>

                            <tr>
                                <th scope="row"><%= hocVienDTO.getMaHV()%></th>
                                <td><%= hocVienDTO.getHo()%> </td>
                                <td><%= hocVienDTO.getTen()%> </td>

                                <td><%= hocVienDTO.getDob()%> </td>

                                <td><%= hocVienDTO.getGender()%> </td>
                                <td><select name="attendance">
                                        <c:set var="getMaHV" value="<%= hocVienDTO.getMaHV()%>" />
                                        <c:forEach var="listAttendanceDTO" items="${requestScope.listAttendanceDTO}" >
                                            <c:if test="${listAttendanceDTO.maHV == getMaHV}">
                                                <option value="${listAttendanceDTO.status}">${listAttendanceDTO.status}</option>
                                                <option value="Attended">Attended</option>
                                                <option value="Absent">Absent</option>
                                            </c:if>

                                        </c:forEach>

                                    </select>
                                    <input type="hidden" name="maHV" value="<%= hocVienDTO.getMaHV()%>" >

                                </td>



                            </tr>

                            <% }
                            } else {%>
                            <tr>
                                <th scope="row"></th>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>

                        </tbody>
                        <% }%>
                    </table>
                    <input type="hidden" name="ngayHoc" value="<%= ngayHoc%>" >
                    <input type="hidden" name="maSlot" value="<%= maSlot%>" >
                    <input type="hidden" name="maLopHoc" value="<%= lopHocDTO.getMaLopHoc()%>" >
                    <input type="submit"  value="submit" >
                </form>
            </div>
            <script>  $(document).ready(function () {
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