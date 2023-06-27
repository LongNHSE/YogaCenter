<%-- 
    Document   : newjsp
    Created on : May 23, 2023, 1:56:24 PM
    Author     : Oalskad
--%>



<%@page import="com.mycompany.yogacenterproject.dto.TrainerDTO"%>
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
        <link href="img/favicon.ico" rel="icon">
        <link href="img/apple-favicon.png" rel="apple-touch-icon">

        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:100,200,300,400,500,600,700,800,900" rel="stylesheet"> 

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

        <script class="u-script" type="text/javascript" src="home2.js" defer=""></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script class="u-script" type="text/javascript" src="<%=url%>/home1.js" defer=""></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">


    </head>
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
            width: 85.3%;

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
            List<TrainerDTO> listTrainerDTO = (List<TrainerDTO>) request.getAttribute("listTrainerDTO");
            int count = (int) request.getAttribute("count");
            int pageCount = (int) request.getAttribute("pageCount");

        %>

    <body>

        <div class="Controller">

            <div class="wrapper">
                <nav class='animated bounceInDown bg-dark'>
                    <ul>
                        <li><a href='<%=url%>/Admin/AdminHomepage.jsp'>Profile</a></li>
                        <li  class='sub-menu'><a href='#settings'><i class="fa-solid fa-school"></i>Class<div class='fa fa-caret-down right'></div></a>
                            <ul >
                                <li ><a href='<%=url%>/AdminController?action=listLopHoc&page=1'>List Class</a></li>
                                <li ><a href='<%=url%>/AdminController?action=listClassUnassigned'>List Class Unassigned</a></li>
                                <li ><a href='<%=url%>/ClassController?action=CheckEmptyRoom'>Create Class</a></li>
                                <li><a href='<%=url%>/AdminController?action=ViewSchedule'>View Schedule</a></li>
                                <li><a href='Admin/Class/CreateClassTypePage.jsp'>Create Class Type</a></li>
                            </ul>
                        </li>
                        <li  class='sub-menu'><a href='#message'>Trainee<div class='fa fa-caret-down right'></div></a>
                            <ul >
                                <li ><a href="<%=url%>/AdminController?action=listHocVien">List Trainee</a></li>
                                <li><a href='#settings'>Submit a Ticket</a></li>
                                <li><a href='#settings'>Network Status</a></li>
                            </ul>
                        </li>
                        <li id="active" class='sub-menu'><a href='#message'>Trainer<div class='fa fa-caret-down right'></div></a>
                            <ul id="active">
                                <li id="active-element"><a href="<%=url%>/AdminController?action=listTrainer&page=1">List Trainer</a></li>
                                <li ><a href='<%=url%>/Admin/Trainer/AddTrainer.jsp'>Add Trainer</a></li>
                                <li><a href='#settings'>Network Status</a></li>
                            </ul>
                        </li>
                        <li  class='sub-menu'><a href='#message'>Application<div class='fa fa-caret-down right'></div></a>
                            <ul >
                                <li ><a href="<%=url%>/AdminController?action=listHocVien">List Trainer</a></li>
                                <li><a href="<%=url%>/Admin/Trainer/AddTrainer.jsp">Add Trainer</a></li>
                        
                                <li><a href='#settings'>Network Status</a></li>
                            </ul>
                        </li>
                        <li><a href='<%=url%>/LoginController?action=adminLogout'>Logout</a></li>
                    </ul>
                </nav>
            </div>

            <div class="Table">
                <table class="table">

                    <thead>
                        <tr>


                            <th scope="col">Ma Trainer</th>
                            <th scope="col">Username</th>
                            <th scope="col">Password</th>
                            <th scope="col">Ho</th>
                            <th scope="col">Ten</th>
                            <th scope="col">Email</th>
                            <th scope="col">Phone</th>
                            <th scope="col">Day of birth</th>
                            <th scope="col">Salary</th>
                            <th scope="col">So Ngay Nghi</th>
                            <th scope="col">Trainer Type</th>
                            <th scope="col">Gender</th>
                            <th scope="col">Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% if (listTrainerDTO != null) {
                                for (TrainerDTO trainerDTO : listTrainerDTO) {
                        %>
                    <form action="<%=url%>/TrainerController">
                        <tr>
                            <th scope="row"><%= trainerDTO.getMaTrainer()%></th>
                            <td><%= trainerDTO.getUsername()%> </td>
                            <td><%= trainerDTO.getPsw()%> </td>
                            <td><%= trainerDTO.getHo()%> </td>
                            <td><%= trainerDTO.getTen()%> </td>
                            <td><%= trainerDTO.getEmail()%> </td>
                            <td><%= trainerDTO.getPhone()%> </td>
                            <td><%= trainerDTO.getDob()%> </td>
                            <td><%= trainerDTO.getSalary()%> </td>
                            <td><%= trainerDTO.getSoNgayNghi()%> </td>
                            <td><%= trainerDTO.getTrainerType()%> </td>

                            <td><%= trainerDTO.getGender()%> </td>
                            <% if (trainerDTO.getStatus() == false) {%>
                            <td style="color: red;font-weight:  bold">Unassigned </td>
                            <% } else {%>
                            <td style="color: #7FFF00; font-weight:  bold">Assigned </td>
                            <% }%>

                            <td>  <input class="btn btn-outline-danger" type='submit'value="UpdateStatus"name="action"  ></td>
                            <td>  <input class="btn btn-outline-danger" type='submit'value="Delete"name="action"  ></td>

                        <input type="hidden" name="maTrainer" value="<%= trainerDTO.getMaTrainer()%>" >

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