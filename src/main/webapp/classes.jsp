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
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <!-- style css -->
  <link rel="stylesheet" href="css/style.css">
  <!-- Responsive-->
  <link rel="stylesheet" href="css/responsive.css">
  <!-- fevicon -->
  <link rel="icon" href="images/fevicon.png" type="image/gif" />
  <!-- Scrollbar Custom CSS -->
  <link rel="stylesheet" href="css/jquery.mCustomScrollbar.min.css">
  <!-- Tweaks for older IEs-->
  <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
  <!-- owl stylesheets -->
  <link rel="stylesheet" href="css/owl.carousel.min.css">
  <link rel="stylesheet" href="css/owl.theme.default.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css"
    media="screen">
</head>
<!-- body -->

<body>
  <!-- header section start -->
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="logo"><a href="home.html"><img src="images/logo.png"></a></div>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup"
      aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
      <div class="navbar-nav">
        <a class="nav-item nav-link" href="home.jsp">Home</a>
        <a class="nav-item nav-link" href="about.html">About</a>
        <a class="nav-item nav-link" href="classes.html">Classes</a>
        <a class="nav-item nav-link" href="schedule.html">Schedule</a>
        <a class="nav-item nav-link" href="trainer.html">Trainer</a>
        <a class="nav-item nav-link" href="contact.html">Contact us</a>
        <a class="nav-item nav-link" href="#">Login</a>
        <a class="nav-item nav-link" href="#"><img src="images/search-icon.png"></a>
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
  <script src="js/jquery.min.js"></script>
  <script src="js/popper.min.js"></script>
  <script src="js/bootstrap.bundle.min.js"></script>
  <script src="js/jquery-3.0.0.min.js"></script>
  <script src="js/plugin.js"></script>
  <!-- sidebar -->
  <script src="js/jquery.mCustomScrollbar.concat.min.js"></script>
  <script src="js/custom.js"></script>
  <!-- javascript -->
  <script src="js/owl.carousel.js"></script>
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