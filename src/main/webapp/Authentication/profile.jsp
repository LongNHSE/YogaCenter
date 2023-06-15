<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<!DOCTYPE html>
<html style="font-size: 16px;" lang="en"><head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta charset="utf-8">
        <meta name="keywords" content="">
        <meta name="description" content="">
        <title>Home</title>
        <link rel="stylesheet" href="<%=url%>/css/profile1.css" media="screen">
        <script class="u-script" type="text/javascript" src="<%=url%>/js/profile1.js" defer=""></script>
        <script class="u-script" type="text/javascript" src="<%=url%>/js/profile2.js" defer=""></script>
        <meta name="generator" content="">
        <link id="u-theme-google-font" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i|Open+Sans:300,300i,400,400i,500,500i,600,600i,700,700i,800,800i">

        <script type="application/ld+json">{
            "@context": "http://schema.org",
            "@type": "Organization",
            "name": ""
            }</script>
        <meta name="theme-color" content="#478ac9">
        <meta property="og:title" content="Home">
        <meta property="og:type" content="website">
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
        <meta data-intl-tel-input-cdn-path="intlTelInput/"></head>

    <header id="header">
     
w

        <div class="mobile-menu-btn"><i class="fa fa-bars"></i></div>
        <nav class="main-menu top-menu">
            <ul>
                <li ><a href="./HomePage">Home</a></li>
                <li><a href="about.html">About Us</a></li>
                <li><a href="room.html">Rooms</a></li>
                <li><a href="amenities.html">Amenities</a></li>
                <li><a href="booking.html">Booking</a></li>
                <li><a href="contact.html">Contact Us</a></li>
                    <c:if test="${sessionScope.userDTO != null}">
                    <li class="active">
                        <a href="/Profile.jsp">
                            ${sessionScope.userDTO.userName} 
                        </a></li>
                    </li>
                </c:if>
                <c:if test="${sessionScope.hvDTO != null}">
                    <li>
                        <form action="/Hotel/Access/logout" method="POST">

                            <input class="btn btn-outline-danger"  type="submit" value="Logout"></input>

                        </form>
                    </li>
                </c:if>

                <c:if test="${sessionScope.hvDTO == null}">
                    <li>
                        <div class="">
                            <a class="btn btn-outline-danger" href="<%=url%>">Login</a>
                        </div>
                    </li>
                </c:if>
            </ul>
        </nav>
    </header>
    <body class="u-body u-xl-mode" data-lang="en"><header class="u-clearfix u-header u-image u-header" id="sec-3e88" data-image-width="3840" data-image-height="2400"><div class="u-clearfix u-sheet u-sheet-1">
                <div class="u-clearfix u-expanded-width u-gutter-0 u-layout-wrap u-layout-wrap-1">
                    <div class="u-layout">
                        <div class="u-layout-col">
                            <div class="u-container-style u-gradient u-layout-cell u-size-30 u-layout-cell-1">
                                <div class="u-border-2 u-border-grey-75 u-border-no-bottom u-container-layout u-container-layout-1">
                                    <img src="<%=url%>/images/rikka-takanashi.gif" alt="" class="u-image u-image-circle u-image-1" data-image-width="1280" data-image-height="1280">
                                    <form action="/Hotel/Profile/details"method="post">
                                    <p class="u-text u-text-1">
                                        <a class="u-border-1 u-border-active-palette-2-base u-border-hover-palette-1-base u-border-no-left u-border-no-right u-border-no-top u-btn u-button-link u-button-style u-none u-text-body-color u-text-hover-palette-1-base u-btn-1" ><button class="btn btn-outline-danger" type="submit">Show receipt details</button><br>
                                        </a>
                                    </p>
                                    </form>
                                    <p class="u-text u-text-2">
                                        <a class="u-border-1 u-border-active-palette-2-base u-border-hover-palette-1-base u-border-no-left u-border-no-right u-border-no-top u-btn u-button-link u-button-style u-none u-text-body-color u-text-hover-palette-1-base u-btn-2" href="<%= url%>/userUpdate.jsp"><button class="btn btn-outline-danger" value="Edit profile">Edit profile </button><br>
                                        </a>
                                    </p>
                                </div>
                            </div>
                            <div class="u-container-style u-gradient u-layout-cell u-size-30 u-layout-cell-2">
                                <div class="u-border-2 u-border-grey-75 u-container-layout u-container-layout-2">
                                    <p class="u-text u-text-3">Ho: ${sessionScope.hocVienDTO.ho}</b> <br>
                                    </p>
                                    <p class="u-text u-text-4">Ten: ${sessionScope.hocVienDTO.ten}</b> <br>
                                    </p>
                                         <p class="u-text u-text-3">Gender : <b>${sessionScope.hocVienDTO.gender}</b>  <br>
                                    </p>
                                    <p class="u-text u-text-3">Date of birth : <b>${sessionScope.hocVienDTO.dob}</b>  <br>
                                    </p>
                                    <div class="u-border-3 u-border-palette-1-base u-container-style u-group u-group-1">
                                        <div class="u-container-layout u-valign-middle u-container-layout-3">
                                            <p class="u-text u-text-default u-text-5">Dictum non consectetur a erat nam at. Aliquam malesuada bibendum arcu vitae elementum curabitur vitae. Tellus mauris a diam maecenas sed enim ut sem. Ipsum faucibus vitae aliquet nec ullamcorper sit amet risus nullam. Pretium nibh ipsum consequat nisl vel pretium. In eu mi bibendum neque egestas congue. Vitae ultricies leo integer malesuada nunc. Nibh praesent tristique magna sit amet purus gravida. Diam volutpat commodo sed egestas. Gravida dictum fusce ut placerat orci nulla pellentesque.</p>
                                        </div>
                                    </div >
                                    <p class="u-text u-text-7" >Email: ${sessionScope.hocVienDTO.email}</b>  <br>
                                    </p>
                                    <p class="u-text u-text-8">Phone: ${sessionScope.hocVienDTO.phone}</b><br>
                                    </p>
                                    <p class="u-text u-text-9">Username: <b>${sessionScope.hocVienDTO.username}</b><br>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div></header> 


        <footer class="u-align-center u-clearfix u-footer u-grey-80 u-footer" id="sec-4c20"><div class="u-clearfix u-sheet u-sheet-1">
                <p class="u-small-text u-text u-text-variant u-text-1">Designed by SWP Group2-Deadline Runner</p>
            </div></footer>

    </body></html>