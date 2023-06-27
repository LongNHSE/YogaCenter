<!DOCTYPE html>
<%
    String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>

<html lang="en">

<head>
  <!-- basic -->
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <!-- mobile metas -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="viewport" content="initial-scale=1, maximum-scale=1">
  <!-- site metas -->
  <title>Contact</title>
  <meta name="keywords" content="">
  <meta name="description" content="">
  <meta name="author" content="">
  <!-- bootstrap css -->
  <link rel="stylesheet" href="<%=url%>/css/bootstrap.min.css">
  <!-- style css -->
  <link rel="stylesheet" href="<%=url%>/css/style.css">
  <!-- Responsive-->
  <link rel="stylesheet" href="<%=url%>/css/responsive.css">
  <!-- fevicon -->
  <link rel="icon" href="<%=url%>/images/fevicon.png" type="image/gif" />
  <!-- Scrollbar Custom CSS -->
  <link rel="stylesheet" href="<%=url%>/css/jquery.mCustomScrollbar.min.css">
  <!-- Tweaks for older IEs-->
  <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
  <!-- owl stylesheets -->
  <link rel="stylesheet" href="<%=url%>/css/owl.carousel.min.css">
  <link rel="stylesheet" href="<%=url%>/css/owl.theme.default.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css"
    media="screen">
</head>
<!-- body -->

<body>
  <!-- header section start -->
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="logo"><a href="home.jsp"><img src="<%=url%>/images/logo.png"></a></div>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup"
      aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
      <div class="navbar-nav">
        <a class="nav-item nav-link" href="home.jsp">Home</a>
        <a class="nav-item nav-link" href="about.jsp">About</a>
        <a class="nav-item nav-link" href="ClassController?action=classes">Classes</a>
        <a class="nav-item nav-link" href="<%=url%>/Authentiation/schedule.jsp">Schedule</a>
        <a class="nav-item nav-link" href="<%=url%>/trainer.jsp">Trainer</a>
        <a class="nav-item nav-link" href="contact.jsp">Contact us</a>
        <a class="nav-item nav-link" href="signin.jsp">Login</a>
        <a class="nav-item nav-link" href="#"><img src="<%=url%>/images/search-icon.png"></a>
      </div>
    </div>
  </nav>
  <!-- header section end -->
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
  <script src="<%=url%>/js/jquery.min.js"></script>
  <script src="<%=url%>/js/popper.min.js"></script>
  <script src="<%=url%>/js/bootstrap.bundle.min.js"></script>
  <script src="<%=url%>/js/jquery-3.0.0.min.js"></script>
  <script src="<%=url%>/js/plugin.js"></script>
  <!-- sidebar -->
  <script src="<%=url%>/js/jquery.mCustomScrollbar.concat.min.js"></script>
  <script src="<%=url%>/js/custom.js"></script>
  <!-- javascript -->
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
  </script>
</body>

</html>