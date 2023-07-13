<%-- 
    Document   : homeDemo
    Created on : Jul 11, 2023, 12:58:35 PM
    Author     : devli
--%>
<%
    String homeUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="../Components/CSSHomeComponent.jsp" />
    </head>
    <body>
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
        
          <div class="trainer_section layout_padding">
    <div class="container">
      <div class="row">
        <div class="col-sm-3">
          <div class="image_1"><img src="images/img-1.png"></div>
          <h1 class="meditation_text">Meditation</h1>
          <p class="lorem_tetx">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
            incididunt </p>
        </div>
        <div class="col-sm-3">
          <div class="image_1"><img src="images/img-2.png"></div>
          <h1 class="meditation_text">Meditation</h1>
          <p class="lorem_tetx">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
            incididunt </p>
        </div>
        <div class="col-sm-3">
          <div class="image_1"><img src="images/img-3.png"></div>
          <h1 class="meditation_text">Meditation</h1>
          <p class="lorem_tetx">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
            incididunt </p>
        </div>
        <div class="col-sm-3">
          <div class="image_1"><img src="images/img-4.png"></div>
          <h1 class="meditation_text">Meditation</h1>
          <p class="lorem_tetx">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
            incididunt </p>
        </div>
      </div>
    </div>
  </div>
        
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
                      <div class="client_icon"><img src="images/client-icon.png"></div>
                    </div>
                    <div class="client_right">
                      <h1 class="carklo_text">carklo</h1>
                      <p class="lorem_dolar_text">Lorem ipsum dolor</p>
                    </div>
                  </div>
                  <div class="lorem_main">
                    <div class="top_arrow"><img src="images/top-arrow-icon.png"></div>
                    <p class="ipsum_dolor_text">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod
                      tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
                      exercitation ullamco laboris nisi ut aliquip ex ea commodo </p>
                  </div>
                </div>
                <div class="col-md-6">
                  <div class="client_icon">
                    <div class="client_left">
                      <div class="client_icon"><img src="images/client-icon.png"></div>
                    </div>
                    <div class="client_right">
                      <h1 class="carklo_text">carklo</h1>
                      <p class="lorem_dolar_text">Lorem ipsum dolor</p>
                    </div>
                  </div>
                  <div class="lorem_main">
                    <div class="top_arrow"><img src="images/top-arrow-icon.png"></div>
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
                      <div class="client_icon"><img src="images/client-icon.png"></div>
                    </div>
                    <div class="client_right">
                      <h1 class="carklo_text">carklo</h1>
                      <p class="lorem_dolar_text">Lorem ipsum dolor</p>
                    </div>
                  </div>
                  <div class="lorem_main">
                    <div class="top_arrow"><img src="images/top-arrow-icon.png"></div>
                    <p class="ipsum_dolor_text">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod
                      tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
                      exercitation ullamco laboris nisi ut aliquip ex ea commodo </p>
                  </div>
                </div>
                <div class="col-md-6">
                  <div class="client_icon">
                    <div class="client_left">
                      <div class="client_icon"><img src="images/client-icon.png"></div>
                    </div>
                    <div class="client_right">
                      <h1 class="carklo_text">carklo</h1>
                      <p class="lorem_dolar_text">Lorem ipsum dolor</p>
                    </div>
                  </div>
                  <div class="lorem_main">
                    <div class="top_arrow"><img src="images/top-arrow-icon.png"></div>
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
                      <div class="client_icon"><img src="images/client-icon.png"></div>
                    </div>
                    <div class="client_right">
                      <h1 class="carklo_text">carklo</h1>
                      <p class="lorem_dolar_text">Lorem ipsum dolor</p>
                    </div>
                  </div>
                  <div class="lorem_main">
                    <div class="top_arrow"><img src="images/top-arrow-icon.png"></div>
                    <p class="ipsum_dolor_text">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod
                      tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
                      exercitation ullamco laboris nisi ut aliquip ex ea commodo </p>
                  </div>
                </div>
                <div class="col-md-6">
                  <div class="client_icon">
                    <div class="client_left">
                      <div class="client_icon"><img src="images/client-icon.png"></div>
                    </div>
                    <div class="client_right">
                      <h1 class="carklo_text">carklo</h1>
                      <p class="lorem_dolar_text">Lorem ipsum dolor</p>
                    </div>
                  </div>
                  <div class="lorem_main">
                    <div class="top_arrow"><img src="images/top-arrow-icon.png"></div>
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
    </body>
    
         <jsp:include page="../Components/JSHomeComponent.jsp" />
</html>
