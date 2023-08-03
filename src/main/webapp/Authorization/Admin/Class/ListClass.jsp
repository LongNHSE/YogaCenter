<%-- 
    Document   : ListClass
    Created on : Jun 23, 2023, 7:36:18 PM
    Author     : Oalskad
--%>
<%@page import="com.mycompany.yogacenterproject.dto.LopHocDTO"%>

<%@page import="java.util.List"%>

<%
    String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>YogaCenter Admin</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">
        <link href="<%=url%>/Authorization/Admin/cssAdmin/newCascadeStyleSheet.css" rel="stylesheet" type="text/css"/>
        <script class="u-script" type="text/javascript" src="<%=url%>/js/home2.js" defer=""></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script class="u-script" type="text/javascript" src="<%=url%>/js/jshome1.js" defer=""></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    </head>
    <body>

        <style>

            .center {
                text-align: center;
            }

            .pagination {
                display: inline-block;
            }

            .pagination a {
                color: black;
                float: left;
                padding: 8px 16px;
                text-decoration: none;
                transition: background-color .3s;
                border: 1px solid #ddd;
                margin: 0 4px;
            }

            .pagination a.active {
                background-color: #4CAF50;
                color: white;
                border: 1px solid #4CAF50;
            }

            .pagination a:hover:not(.active) {
                background-color: #ddd;
            }
            nav {
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
            .Table{


            }
            table {
                margin-left: 250px;
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
            }</style>

        <%
            List<LopHocDTO> listLopHocDTO = (List<LopHocDTO>) request.getAttribute("listLopHocDTO");
            int count = (int) request.getAttribute("count");
            int pageCount = (int) request.getAttribute("pageCount");
        %>
        <div class="Controller">
            <div class="wrapper">
                <%@include file="../NavComponents.jsp" %>            </div>
            <div class="Table">
                <table class="table">

                    <thead>
                        <tr class="Test">


                            <th scope="col">Class' ID</th>
                            <th scope="col">Type of Class</th>
                            <th scope="col">Max No. of Attendees</th>
                            <th scope="col">Current No. of Attendees</th>
                            <th scope="col">Trainer In Charge</th>
                            <th scope="col">Slots</th>
                            <th scope="col">Room's ID</th>
                            <th scope="col">Slot's ID</th>
                            <th scope="col">Initial Date</th>
                            <th scope="col">Status</th>

                        </tr>
                    </thead>
                    <tbody>
                        <% if (listLopHocDTO != null) {
                                for (LopHocDTO lopHocDTO : listLopHocDTO) {
                        %>
                    <form action="<%=url%>/ClassController" method="POST">
                        <tr>
                            <th scope="row"><%= lopHocDTO.getMaLopHoc()%></th>
                            <td><%= lopHocDTO.getMaLoaiLopHoc()%> </td>
                            <td style="text-align: right"><%= lopHocDTO.getSoLuongHV()%> </td>
                            <td style="text-align: right"><%= lopHocDTO.getSoLuongHvHienTai()%> </td>
                            <td><%= lopHocDTO.getTrainerDTO().getHo()%> <%= lopHocDTO.getTrainerDTO().getTen()%> </td>
                            <td><%=lopHocDTO.getSoBuoiDaDay()%>/<%=lopHocDTO.getSoBuoi()%></td>
                            <td><%= lopHocDTO.getMaRoom()%> </td>
                            <td><%= lopHocDTO.getMaSlot()%> </td>
                            <td style="text-align: right"><%= lopHocDTO.getNgayBatDau()%> </td>
                            <% if (lopHocDTO.isStatus() == true) {%>
                            <td style="background-color: #3FFF00; text-align: center">Active </td> <%} else {%>
                            <td style="background-color: red; text-align: center">Inactive </td> <% }%>
                            <td>  <input class="btn btn-outline-danger" type='submit'value="Class Detail"name="action" ></td>

                            <td >  <input class="btn btn-outline-danger" type='submit'value="Update"name="action"  ></td>

                            <% if (lopHocDTO.isStatus() == false || lopHocDTO.getSoLuongHvHienTai() < 1) {%>
                            <td >  <input class="btn btn-outline-danger" type='submit'value="Delete"name="action"    ></td> <%} else {%>
                            <td>  <input class="btn btn-outline-danger" type='submit'value="Delete"name="action"    disabled=""></td> <% }%>


                        <input type="hidden" name="maLopHoc" value="<%= lopHocDTO.getMaLopHoc()%>" >
                    </form>

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
            </div>      
        </div>
        <div class="center">
            <div class="pagination">

                <div class="pagination">
                    <a href="#">&laquo;</a>




                    <% for (int i = 1; i <= count; i++) {%>
                    <% if (i == pageCount) {%>
                    <a href='<%=url%>/AdminController?action=listLopHoc&page=<%=i%>' class="active"><%=i%></a>
                    <% } else {%>
                    <a href='<%=url%>/AdminController?action=listLopHoc&page=<%=i%>'><%=i%></a>
                    <% }
                        }%>
                    <a href="#">&raquo;</a>
                </div>
            </div>
        </div>
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
        </script>
    </body>
</html>
