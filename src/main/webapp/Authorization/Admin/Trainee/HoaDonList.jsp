<%-- 
    Document   : newjsp
    Created on : May 23, 2023, 1:56:24 PM
    Author     : Oalskad
--%>



<%@page import="com.mycompany.yogacenterproject.dto.HoaDonDTO"%>
<%@page import="com.mycompany.yogacenterproject.dto.HocVienDTO"%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

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

        <!-- Favicons -->
        <link href="img/favicon.ico" rel="icon">         <!--khong biet chay vao dau-->
        <link href="img/apple-favicon.png" rel="apple-touch-icon">         <!--khong biet chay vao dau-->

        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:100,200,300,400,500,600,700,800,900" rel="stylesheet"> 

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

        <script class="u-script" type="text/javascript" src="<%=url%>/js/home2.js" defer=""></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script class="u-script" type="text/javascript" src="<%=url%>/js/home1.js" defer=""></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">


    </head>
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
        List<HoaDonDTO> listHoaDon = (List<HoaDonDTO>) request.getAttribute("listHoaDon");

    %>

    <body>
        <div class="Controller">

            <div class="wrapper">
                <nav class='animated bounceInDown bg-dark'>
                    <ul>
                        <li><a href='<%=url%>/Authorization/Admin/AdminHomepage.jsp'>Profile</a></li>
                        <li  class='sub-menu'><a href='#settings'><i class="fa-solid fa-school"></i>Class<div class='fa fa-caret-down right'></div></a>
                            <ul >
                              <li ><a href='<%=url%>/AdminController?action=listLopHoc&page=1'>List Class</a></li>
                                <li ><a href='<%=url%>/AdminController?action=listClassUnassigned'>List Class Unassigned</a></li>
                                <li ><a href='<%=url%>/ClassController?action=CheckEmptyRoom'>Create Class</a></li>
                                <li><a href='<%=url%>/AdminController?action=ViewSchedule'>View Schedule</a></li>
                                <li><a href='<%=url%>/Authorization/Admin/Class/CreateClassTypePage.jsp'>Create Class Type</a></li>
                            </ul>
                        </li>
                        <li id="active" class='sub-menu'><a href='#message'>Trainee<div class='fa fa-caret-down right'></div></a>
                            <ul id="active">
                                <li id="active-element"><a href="<%=url%>/AdminController?action=listHocVien">List Trainee</a></li>
                                <li><a href='#settings'>Submit a Ticket</a></li>
                                <li><a href='#settings'>Network Status</a></li>
                            </ul>
                        </li>
                        <li><a href='#message'>Logout</a></li>
                    </ul>
                </nav>
            </div>

            <div class="Table">
                <table class="table">

                    <thead>
                        <tr class="Test">
                            <th scope="col">Ma Hoa Don</th>
                            <th scope="col">Ma Hoc Vien</th>
                            <th scope="col">Ma Lop Hoc</th>
                            <th scope="col">So Tien</th>
                            <th scope="col">Ngay Thanh Toan</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% if (listHoaDon != null) {
                                for (HoaDonDTO hoaDonDTO : listHoaDon) {
                        %>
                    <form action="<%=url%>/AdminController">
                        <tr>
                            <th scope="row"><%= hoaDonDTO.getMahoaDon()%></th>
                            <td><%= hoaDonDTO.getMaHV()%> </td>
                            <td><%= hoaDonDTO.getMaLopHoc()%> </td>
                            <td><%= hoaDonDTO.getGiaTien()%> </td>
                            <td><%= hoaDonDTO.getNgayThanhToan()%> </td>
                        </tr>
                    </form>
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