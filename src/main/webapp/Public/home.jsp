<%-- 
    Document   : home
    Created on : Jun 7, 2023, 7:21:10 AM
    Author     : devli
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Yogasan</title>
        <link href="<%=url%>/css/homeStyle.css" rel="stylesheet" type="text/css"/>
    </head>

   
    <body>
        <!-- header section start -->
        <jsp:include page="../Components/headerComponent.jsp" />
       
        <!-- header section end -->

        <!-- banner section start -->
        <div class="banner_section layout_padding">
            <div class="container">
                <section class="slide-wrapper">
                    <div class="container">
                        <div id="myCarousel" class="carousel slide" data-ride="carousel">
                            <!-- Indicators -->
                            <ol class="carousel-indicators">
                                <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                                <li data-target="#myCarousel" data-slide-to="1"></li>
                                <li data-target="#myCarousel" data-slide-to="2"></li>
                            </ol>
                            <!-- Wrapper for slides -->
                            <div class="carousel-inner">
                                <div class="carousel-item active">
                                    <h1 class="yoga_text ">Yoga For New</h1>
                                    <h1 class="body_text">Energy</h1>
                                    <p class="contrary_text">Find your positive energy and live happily with yourself from now on </p>
                                </div>
                                <div class="carousel-item">
                                    <h1 class="yoga_text">Yoga For New</h1>
                                    <h1 class="body_text">Body</h1>
                                    <p class="contrary_text">To have happy life, firstly you should have a healthy body</p>
                                </div>
                                <div class="carousel-item">
                                    <h1 class="yoga_text">Yoga For New</h1>
                                    <h1 class="body_text">People</h1>
                                    <p class="contrary_text">Joining us now and together to achieve the tranquility in our soul</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
        </div>
        <!-- banner section end -->
        <!-- trainer section start -->
        <div class="trainer_section layout_padding">
            <div class="container">
                <div class="row">
                    <div class="col-sm-3">
                        <div class="image_1"><img src="<%=url%>/images/img-1.png"></div>
                        <h1 class="meditation_text">Meditation</h1>
                        <p class="lorem_tetx"> Focus on relaxing the muscles of the spine and lower back as you breathe. </p>
                    </div>
                    <div class="col-sm-3">
                        <div class="image_1"><img src="<%=url%>/images/img-2.png"></div>
                        <h1 class="meditation_text">Meditation</h1>
                        <p class="lorem_tetx">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
                            incididunt </p>
                    </div>
                    <div class="col-sm-3">
                        <div class="image_1"><img src="<%=url%>/images/img-3.png"></div>
                        <h1 class="meditation_text">Meditation</h1>
                        <p class="lorem_tetx">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
                            incididunt </p>
                    </div>
                    <div class="col-sm-3">
                        <div class="image_1"><img src="<%=url%>/images/img-4.png"></div>
                        <h1 class="meditation_text">Meditation</h1>
                        <p class="lorem_tetx">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
                            incididunt </p>
                    </div>
                </div>
            </div>
        </div>
        <!-- trainer section end -->
        <!-- about section start -->
        <div class="about_section layout_padding">
            <div class="container">
                <div class="about_main">
                    <h1 class="about_text">About Us</h1>
                    <p class="ipsum_text">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt
                        ut labore et dolore magna aliqua. Ut enim ad minim veniamLorem ipsum dolor sit amet, consectetur adipiscing
                        elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniamLorem ipsum
                        dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna
                        aliqua. </p>
                </div>
                <div class="about_bt_main">
                    <div class="about_bt"><a href="#">About More</a></div>
                </div>
            </div>
        </div>
        <!-- about section end -->
        <!-- contact section start -->
        <div class="contact_section layout_padding">
            <div class="container">
                <div class="row">
                    <div class="col-md-6">
                        <h1 class="contact_text">Contact Us</h1>
                        <div class="mail_section">
                            <div class="social_icon">
                            <ul>
                                <li><a href="#"><img src="<%=url%>/images/fb-icon.png"></a></li>
                                <li><a href="#"><img src="<%=url%>/images/twitter-icon.png"></a></li>
                                <li><a href="#"><img src="<%=url%>/images/instagram-icon.png"></a></li>
                                <li><a href="#"><img src="<%=url%>/images/linkdin-icon.png"></a></li>
                            </ul>
                        </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        
                        <div class="map">
                            <div class="map-responsive">
                                <iframe
                                    src="https://www.google.com/maps/embed/v1/place?key=AIzaSyA0s1a7phLN0iaD6-UE7m4qP-z21pH0eSc&q=Eiffel+Tower+Paris+France"
                                    width="600" height="250" frameborder="0" style="border:0; width: 100%;" allowfullscreen></iframe>
                            </div>
                        </div>
                        <div class="call_text"><img src="<%=url%>/images/call-icon.png"><span class="padding_left_0">+01 9876543210</span>
                        </div>
                        <div class="call_text"><img src="<%=url%>/images/mail-icon.png"><span class="padding_left_0">demo@gmail.com</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- contact section end -->
        <!-- copyright section start -->
        <div class="copyright_section">
            <div class="container " style="margin-top: 20px">
                <p class="copyright_text ">Copyright 2019 All Right Reserved By.<a href="https://html.design"> Free html
                        Templates</a> Distributed By. <a href="https://themewagon.com">ThemeWagon </a></p>
            </div>
        </div>

    </body>
</html>
