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
                                    <h1 class="yoga_text ">Yoga For Beginners</h1>
                                    <h1 class="body_text">Boost Your Energy</h1>
                                    <p class="contrary_text">Contrary to popular belief, yoga is not just exercise. It's a holistic practice that combines physical movement, breath control, and mindfulness. Regular yoga practice can increase your energy levels, improve circulation, and promote overall vitality. </p>
                                </div>
                                <div class="carousel-item">
                                    <h1 class="yoga_text">Yoga For Flexibility</h1>
                                    <h1 class="body_text">Improve Your Flexibility</h1>
                                    <p class="contrary_text">Contrary to popular belief, you don't need to be flexible to do yoga. Yoga helps improve flexibility through gentle stretching, lengthening muscles, and increasing joint mobility. Regular yoga practice can enhance your range of motion and bring more ease to your body. </p>
                                </div>
                                <div class="carousel-item">
                                    <h1 class="yoga_text">Yoga For Stress Relief</h1>
                                    <h1 class="body_text">Find Inner Peace</h1>
                                    <p class="contrary_text">Contrary to popular belief, yoga is not just physical exercise. It's a powerful tool for managing stress and promoting mental well-being. Through deep breathing, meditation, and mindful movement, yoga helps calm the mind, reduce anxiety, and cultivate inner peace.</p>
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
                <div class="row customTest">
                    <div class="col-sm-3 col-trainer">
                        <div class="image_1"><img src="<%=url%>/images/img-1.png"></div>
                        <h2 class="meditation_text">Build Endurance</h2>
                        <p class="lorem_tetx">Balance of body and mind is what yoga brings to practitioners. Yoga requires perseverance and belief to achieve the desired state through personal effort. </p>
                    </div>
                    <div class="col-sm-3 col-trainer">
                        <div class="image_1"><img src="<%=url%>/images/img-2.png"></div>
                        <h2 class="meditation_text">Find Inner Peace</h2>
                        <p class="lorem_tetx">Yoga helps you break free from the fast-paced flow of life. Slow breathing, relaxation, gentle movements, and meditation help you deeply focus your mind, observe the changes in your body, understand your body better, and improve your health.</p>
                    </div>
                    <div class="col-sm-3 col-trainer">
                        <div class="image_1"><img src="<%=url%>/images/img-3.png"></div>
                        <h2 class="meditation_text">Health Conditions</h2>
                        <p class="lorem_tetx">Through yoga practice, deep and long breaths help relax the body, regulate organs harmoniously, balance and soothe any internal organ injuries. It improves lung elasticity, detoxifies the body, and eliminates toxins.</p>
                    </div>
                    <div class="col-sm-3 col-trainer">
                        <div class="image_1"><img src="<%=url%>/images/img-4.png"></div>
                        <h2 class="meditation_text">Joints and Bones</h2>
                        <p class="lorem_tetx">With each yoga practice, joints are fully mobilized, and this movement helps release lubricants that make the joints smoother. It directly affects the internal muscles, slows down the aging process, and improves bone-related issues, minimizing joint problems.</p>
                    </div>
                </div>
            </div>
        </div>
        <!-- trainer section end -->

        <div class="about_section layout_padding">
            <div class="container">
                <div class="about_main">
                    <h1 class="about_text"> 🎇 Yoga San  Center</h1>
                    <p class="ipsum_text">Welcome to Yoga San Center! We are committed to providing you with exceptional yoga experiences to help you relax, balance your body and mind, and enhance your overall health and well-being in your daily life.</p>
                </div>
                <div class="about_bt_main">
                    <div class="about_bt"><a href="<%=url%>/Public/about.jsp">Learn More</a></div>
                </div>
            </div>
        </div>

        <!-- about section end -->
 
        <!-- contact section start -->
        <div class="contact_section layout_padding">
            <div class="container ">
                <div class="row contact-map-row">
                    <div class="col-md-6 contact-column">
                        <h1 class="contact_text">Contact Us</h1>
                        <div class="social_icon">
                            <ul>
                                <li><a href="#"><img src="<%=url%>/images/fb-icon.png"></a></li>
                                <li><a href="#"><img src="<%=url%>/images/twitter-icon.png"></a></li>
                                <li><a href="#"><img src="<%=url%>/images/instagram-icon.png"></a></li>
                                <li><a href="#"><img src="<%=url%>/images/linkdin-icon.png"></a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md-6 map-column">
                        <div class="contact-info">
                            <div class="call_text"><img src="<%=url%>/images/call-icon.png"><span class="padding_left_0">+01 9876543210</span></div>
                            <div class="call_text"><img src="<%=url%>/images/mail-icon.png"><span class="padding_left_0">demo@gmail.com</span></div>
                        </div>

                          <div class="map">
                            <div class="map-responsive">
                                <iframe
                                    src="https://www.google.com/maps/embed/v1/place?key=AIzaSyA0s1a7phLN0iaD6-UE7m4qP-z21pH0eSc&q=Eiffel+Tower+Paris+France"
                                    width="600" height="250" frameborder="0" style="border:0; width: 100%;" allowfullscreen></iframe>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
                        
                        <style>

                        </style>
        <!-- contact section end -->
        <!-- copyright section start -->
<!--        <div class="copyright_section">
            <div class="container " style="margin-top: 20px">
                <p class="copyright_text ">Copyright 2019 All Right Reserved By.<a href="https://html.design"> Free html
                        Templates</a> Distributed By. <a href="https://themewagon.com">ThemeWagon </a></p>
            </div>
        </div>-->
                <jsp:include page="../Components/footerComponent.jsp" />        
    </body>
    <script src="<%=url%>/js/homeStyle.js"></script>
</html>
