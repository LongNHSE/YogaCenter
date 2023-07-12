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
        <jsp:include page="../Components/headerComponent.jsp" />   
        
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
                    } else if (Math.floor(yClick - yMove) < -1) {
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