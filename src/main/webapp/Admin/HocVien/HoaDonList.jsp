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
        <link href="img/favicon.ico" rel="icon">
        <link href="img/apple-favicon.png" rel="apple-touch-icon">

        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:100,200,300,400,500,600,700,800,900" rel="stylesheet"> 

        <!-- Vendor CSS File -->
        <link href="<%= url%>/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="<%= url%>/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet">
        <link href="<%= url%>/vendor/animate/animate.min.css" rel="stylesheet">
        <link href="<%= url%>/vendor/slick/slick.css" rel="stylesheet">
        <link href="<%= url%>/vendor/slick/slick-theme.css" rel="stylesheet">
        <link href="<%= url%>/vendor/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

        <!-- Main Stylesheet File -->
        <link href="<%= url%>/css/hover-style.css" rel="stylesheet">
        <link href="<%= url%>/css/style.css" rel="stylesheet">
    </head>
    <%

        List<HoaDonDTO> listHoaDon = (List<HoaDonDTO>) request.getAttribute("listHoaDon");

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
                    <td><%= hoaDonDTO.getMaLopHoc() %> </td>
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