<%-- 
    Document   : JsComponent
    Created on : Jun 14, 2023, 9:34:10 AM
    Author     : devli
--%>
<%
    String JsUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
        <!-- Javascript files-->
      <script>
          localStorage.setItem('redirectUrl', window.location.href);
      </script>        
      <script src="<%=JsUrl%>/js/jquery.min.js"></script>
      <script src="<%=JsUrl%>/js/popper.min.js"></script>
      <script src="<%=JsUrl%>/js/bootstrap.bundle.min.js"></script>
      <script src="<%=JsUrl%>/js/jquery-3.0.0.min.js"></script>
      <script src="<%=JsUrl%>/js/plugin.js"></script>
      <!-- sidebar -->
      <script src="<%=JsUrl%>/js/jquery.mCustomScrollbar.concat.min.js"></script>
      <script src="<%=JsUrl%>/js/custom.js"></script>
      <!-- javascript -->
      <script src="<%=JsUrl%>/js/owl.carousel.js"></script>
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