<%
    String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>

<!DOCTYPE html>
<html lang="en">

<head>
  <!-- basic -->
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <!-- mobile metas -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="viewport" content="initial-scale=1, maximum-scale=1">
  <!-- site metas -->
  <title>Classes</title>
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