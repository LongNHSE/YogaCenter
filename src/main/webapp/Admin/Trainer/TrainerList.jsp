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

        <!-- Vendor CSS File -->
        <link href="Admin/vendorAdmin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="Admin/vendorAdmin/font-awesome/css/font-awesome.min.css" rel="stylesheet">
        <link href="Admin/vendorAdmin/animate/animate.min.css" rel="stylesheet">
        <link href="Admin/vendorAdmin/slick/slick.css" rel="stylesheet">
        <link href="Admin/vendorAdmin/slick/slick-theme.css" rel="stylesheet">
        <link href="Admin/vendorAdmin/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

        <!-- Main Stylesheet File -->
        <link href="Admin/cssAdmin/hover-style.css" rel="stylesheet">
        <link href="Admin/cssAdmin/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <%

        List<TrainerDTO> listTrainerDTO = (List<TrainerDTO>) request.getAttribute("listTrainerDTO");

    %>

    <body>
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


        <table class="table">

            <thead>
                <tr>


                    <th scope="col">Ma Hoc Vien</th>
                    <th scope="col">HoVaTen</th>
                    <th scope="col">Username</th>
                    <th scope="col">Day of birth</th>


                    <th scope="col">Email</th>
                    <th scope="col">Phone</th>
                    <th scope="col">Salary</th>
                    <th scope="col">So Ngay Nghi</th>
                    <th scope="col">Trainer Type</th>

                </tr>
            </thead>
            <tbody>
                <% if (listTrainerDTO != null) {
                        for (TrainerDTO trainerDTO : listTrainerDTO) {
                %>
            <form action="UserUpdateController">
                <tr>
                    <th scope="row"><%= trainerDTO.getMaTrainer()%></th>
                    <td><%= trainerDTO.getHoVaTen()%> </td>
                    <td><%= trainerDTO.getUsername()%> </td>
                    <td><%= trainerDTO.getDob()%> </td>
                    <td><%= trainerDTO.getEmail()%> </td>
                    <td><%= trainerDTO.getPhone()%> </td>
                     <td><%= trainerDTO.getSalary()%> </td>
                     <td><%= trainerDTO.getSoNgayNghi()%> </td>
                    <td><%= trainerDTO.getTrainerType()%> </td>



                    <td>  <input class="btn btn-outline-danger" type='submit'value="View Receipt"name="action"  ></td>
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



    Footer Section Start 
    <div id="footer">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="social">
                        <a href=""><li class="fa fa-instagram"></li></a>
                        <a href=""><li class="fa fa-twitter"></li></a>
                        <a href=""><li class="fa fa-facebook-f"></li></a>
                    </div>
                </div>
                <div class="col-12">
                    <p>Copyright &#169; 2045 <a href="">Your Site Name</a> All Rights Reserved.</p>

                    /*** This template is free as long as you keep the footer author?s credit link/attribution link/backlink. If you'd like to use the template without the footer author?s credit link/attribution link/backlink, you can purchase the Credit Removal License from "https://htmlcodex.com/credit-removal". Thank you for your support. ***/
                    <p>Designed By <a href="https://htmlcodex.com">HTML Codex</a></p>
                </div>
            </div>
        </div>
    </div>
    Footer Section End 

    <a href="#" class="back-to-top"><i class="fa fa-chevron-up"></i></a>

    Vendor JavaScript File 

    Booking Javascript File 
    <script src="js/booking.js"></script>
    <script src="js/jqBootstrapValidation.min.js"></script>

    Main Javascript File 
    <script src="js/main.js"></script>
</body>
</html>