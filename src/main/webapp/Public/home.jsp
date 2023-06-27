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
            <!--   basic 
            --><meta charset="utf-8">
              <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <!--   mobile metas -->
              <meta name="viewport" content="width=device-width, initial-scale=1">
              <meta name="viewport" content="initial-scale=1, maximum-scale=1">
               <!--site metas--> 
            <title>Yogasan</title>
            <!--CSS-->
            <%@ include file="../Components/CSSComponent.jsp" %>
            <!--Javascript-->
            <%@ include file="../Components/JsComponent.jsp" %>
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
                      <h1 class="yoga_text">Yoga For New</h1>
                      <h1 class="body_text">Body Energy</h1>
                      <p class="contrary_text">Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots
                        in a </p>
                      <div class="contact_bt"><a href="#">Contact Us</a></div>
                    </div>
                    <div class="carousel-item">
                      <h1 class="yoga_text">Yoga For New</h1>
                      <h1 class="body_text">Body Energy</h1>
                      <p class="contrary_text">Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots
                        in a </p>
                      <div class="contact_bt"><a href="#">Contact Us</a></div>
                    </div>
                    <div class="carousel-item">
                      <h1 class="yoga_text">Yoga For New</h1>
                      <h1 class="body_text">Body Energy</h1>
                      <p class="contrary_text">Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots
                        in a </p>
                      <div class="contact_bt"><a href="#">Contact Us</a></div>
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
                <p class="lorem_tetx">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
                  incididunt </p>
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
        <!-- client section start -->
        <div class="client_section layout_padding">
          <div class="container">
            <h1 class="costomer_taital">What our customer say</h1>
            <div id="main_slider" class="carousel slide" data-ride="carousel">
              <ol class="carousel-indicators">
                <li data-target="#main_slider" data-slide-to="0" class="active"></li>
                <li data-target="#main_slider" data-slide-to="1"></li>
                <li data-target="#main_slider" data-slide-to="2"></li>
              </ol>
              <div class="carousel-inner">
                <div class="carousel-item active">
                  <div class="client_section_2">
                    <div class="row">
                      <div class="col-md-6">
                        <div class="client_icon">
                          <div class="client_left">
                            <div class="client_icon"><img src="<%=url%>/images/client-icon.png"></div>
                          </div>
                          <div class="client_right">
                            <h1 class="carklo_text">carklo</h1>
                            <p class="lorem_dolar_text">Lorem ipsum dolor</p>
                          </div>
                        </div>
                        <div class="lorem_main">
                          <div class="top_arrow"><img src="<%=url%>/images/top-arrow-icon.png"></div>
                          <p class="ipsum_dolor_text">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod
                            tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
                            exercitation ullamco laboris nisi ut aliquip ex ea commodo </p>
                        </div>
                      </div>
                      <div class="col-md-6">
                        <div class="client_icon">
                          <div class="client_left">
                            <div class="client_icon"><img src="<%=url%>/images/client-icon.png"></div>
                          </div>
                          <div class="client_right">
                            <h1 class="carklo_text">carklo</h1>
                            <p class="lorem_dolar_text">Lorem ipsum dolor</p>
                          </div>
                        </div>
                        <div class="lorem_main">
                          <div class="top_arrow"><img src="<%=url%>/images/top-arrow-icon.png"></div>
                          <p class="ipsum_dolor_text">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod
                            tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
                            exercitation ullamco laboris nisi ut aliquip ex ea commodo </p>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="carousel-item">
                  <div class="client_section_2">
                    <div class="row">
                      <div class="col-md-6">
                        <div class="client_icon">
                          <div class="client_left">
                            <div class="client_icon"><img src="<%=url%>/images/client-icon.png"></div>
                          </div>
                          <div class="client_right">
                            <h1 class="carklo_text">carklo</h1>
                            <p class="lorem_dolar_text">Lorem ipsum dolor</p>
                          </div>
                        </div>
                        <div class="lorem_main">
                          <div class="top_arrow"><img src="<%=url%>/images/top-arrow-icon.png"></div>
                          <p class="ipsum_dolor_text">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod
                            tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
                            exercitation ullamco laboris nisi ut aliquip ex ea commodo </p>
                        </div>
                      </div>
                      <div class="col-md-6">
                        <div class="client_icon">
                          <div class="client_left">
                            <div class="client_icon"><img src="<%=url%>/images/client-icon.png"></div>
                          </div>
                          <div class="client_right">
                            <h1 class="carklo_text">carklo</h1>
                            <p class="lorem_dolar_text">Lorem ipsum dolor</p>
                          </div>
                        </div>
                        <div class="lorem_main">
                          <div class="top_arrow"><img src="<%=url%>/images/top-arrow-icon.png"></div>
                          <p class="ipsum_dolor_text">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod
                            tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
                            exercitation ullamco laboris nisi ut aliquip ex ea commodo </p>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="carousel-item">
                  <div class="client_section_2">
                    <div class="row">
                      <div class="col-md-6">
                        <div class="client_icon">
                          <div class="client_left">
                            <div class="client_icon"><img src="<%=url%>/images/client-icon.png"></div>
                          </div>
                          <div class="client_right">
                            <h1 class="carklo_text">carklo</h1>
                            <p class="lorem_dolar_text">Lorem ipsum dolor</p>
                          </div>
                        </div>
                        <div class="lorem_main">
                          <div class="top_arrow"><img src="<%=url%>/images/top-arrow-icon.png"></div>
                          <p class="ipsum_dolor_text">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod
                            tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
                            exercitation ullamco laboris nisi ut aliquip ex ea commodo </p>
                        </div>
                      </div>
                      <div class="col-md-6">
                        <div class="client_icon">
                          <div class="client_left">
                            <div class="client_icon"><img src="<%=url%>/images/client-icon.png"></div>
                          </div>
                          <div class="client_right">
                            <h1 class="carklo_text">carklo</h1>
                            <p class="lorem_dolar_text">Lorem ipsum dolor</p>
                          </div>
                        </div>
                        <div class="lorem_main">
                          <div class="top_arrow"><img src="<%=url%>/images/top-arrow-icon.png"></div>
                          <p class="ipsum_dolor_text">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod
                            tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
                            exercitation ullamco laboris nisi ut aliquip ex ea commodo </p>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <a class="carousel-control-prev" href="#main_slider" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
              </a>
              <a class="carousel-control-next" href="#main_slider" role="button" data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
              </a>
            </div>
          </div>
        </div>
        <!-- client section end -->
        <!-- pricing section start -->
        <div class="pricing_section layout_padding">
          <div class="container">
            <h1 class="pricing_taital">Our Pricing</h1>
            <div class="pricing_section_2">
              <div class="row">
                <div class="col-md-6">
                  <div class="plane_section">
                    <h1 class="starter_text">STARTER PLAN</h1>
                    <div class="number_text">50<span style="font-size: 16px;">/$</span></div>
                    <p class="lorem_amet_text">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed </p>
                  </div>
                </div>
                <div class="col-md-6">
                  <div class="plane_section">
                    <h1 class="starter_text">STARTER PLAN</h1>
                    <div class="number_text">50<span style="font-size: 16px;">/$</span></div>
                    <p class="lorem_amet_text">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed </p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- pricing section end -->
        <!-- contact section start -->
        <div class="contact_section layout_padding">
          <div class="container">
            <div class="row">
              <div class="col-md-6">
                <h1 class="contact_text">Contact Us</h1>
                <div class="mail_section">
                  <input type="text" class="mail_text" placeholder="Name" name="text">
                  <input type="text" class="mail_text" placeholder="Email" name="text">
                  <input type="text" class="mail_text" placeholder="Phone Number" name="text">
                  <textarea class="massage-bt" placeholder="Massage" rows="5" id="comment" name="Massage"></textarea>
                  <div class="send_bt"><a href="#">SEND</a></div>
                </div>
              </div>
              <div class="col-md-6">
                <div class="social_icon">
                  <ul>
                    <li><a href="#"><img src="<%=url%>/images/fb-icon.png"></a></li>
                    <li><a href="#"><img src="<%=url%>/images/twitter-icon.png"></a></li>
                    <li><a href="#"><img src="<%=url%>/images/instagram-icon.png"></a></li>
                    <li><a href="#"><img src="<%=url%>/images/linkdin-icon.png"></a></li>
                  </ul>
                </div>
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
          <div class="container">
            <p class="copyright_text">Copyright 2019 All Right Reserved By.<a href="https://html.design"> Free html
                Templates</a> Distributed By. <a href="https://themewagon.com">ThemeWagon </a></p>
          </div>
        </div>
        <!-- copyright section end -->
        <!-- Javascript files-->
<!--        <script src="<%=url%>/js/jquery.min.js"></script>
        <script src="<%=url%>/js/popper.min.js"></script>
        <script src="<%=url%>/js/bootstrap.bundle.min.js"></script>
        <script src="<%=url%>/js/jquery-3.0.0.min.js"></script>
        <script src="<%=url%>/js/plugin.js"></script>
         sidebar 
        <script src="<%=url%>/js/jquery.mCustomScrollbar.concat.min.js"></script>
        <script src="<%=url%>/js/custom.js"></script>
         javascript 
        <script src="<%=url%>/js/owl.carousel.js"></script>
        <script src="https:cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.js"></script>
        <script>
          $('#myCarousel').carousel({
            interval: false
          });

          //scroll slides on swipe for touch enabled devices

          $("#myCarousel").on("touchstart", function (event) {

            var yClick = event.originalEvent.touches[0].pageY;
            $(this).one("touchmove", function (event) {

              var yMove = event.originalEvent.touches[0].pageY;
              if (Math.floor(yClick - yMove) > 1) {
                $(".carousel").carousel('next');
              }
              else if (Math.floor(yClick - yMove) < -1) {
                $(".carousel").carousel('prev');
              }
            });
            $(".carousel").on("touchend", function () {
              $(this).off("touchmove");
            });
          });
        </script>-->
      </body>
</html>
