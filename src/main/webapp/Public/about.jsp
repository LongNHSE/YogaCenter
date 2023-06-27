<!DOCTYPE html>
<html lang="en">
<%
    String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<head>
  <!-- basic -->
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <!-- mobile metas -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="viewport" content="initial-scale=1, maximum-scale=1">
  <!-- site metas -->
  <title>About</title>
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
        <a class="nav-item nav-link" href="classes.jsp">Classes</a>
        <a class="nav-item nav-link" href="<%=url%>/Authentication/schedule.jsp">Schedule</a>
        <a class="nav-item nav-link" href="<%=url%>/trainer.jsp">Trainer</a>
        <a class="nav-item nav-link" href="contact.jsp">Contact us</a>
        <c:if test = "${sessionScope.user == null}">
            <a class="nav-item nav-link" href="<%=url%>/Public/signin.jsp">Login</a>              
        </c:if>
        <c:if test = "${sessionScope.user != null}">
            <div class="btn-group btn-user-menu">
                  <button style=" margin-left: 100px; background: #be2532 " class=" btn btn-secondary btn-sm dropdown-toggle btn-user" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                  
                  <span>${sessionScope.user.username}</span>
                  </button>
                  <div class="dropdown-menu btn-menu">
                       <a class="dropdown-item btn-menu-item" href="Authentiaction/profile.jsp">Profile</a>
                                                    <a class="dropdown-item btn-menu-item" href="changePass.jsp">Change password</a>
                                                    <a class="dropdown-item btn-menu-item" href="#">Something else here</a>
                                                    <a class="dropdown-item btn-menu-item" href="<%=url%>/LoginController/signup?action=logout">
                                                          <b style="color: black">Logout</b>
                                                    </a>
                  </div>
            </div>               
        </c:if>
        <a class="nav-item nav-link" href="#"><img src="<%=url%>/images/search-icon.png"></a>
      </div>
    </div>
  </nav>
  <!-- header section end -->
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