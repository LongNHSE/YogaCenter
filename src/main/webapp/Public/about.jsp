<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Yogasan</title>
        <link href="<%=url%>/css/homeStyle.css" rel="stylesheet" type="text/css"/>
    </head>

    <body>
        <!-- header section start -->
        <jsp:include page="../Components/headerComponent.jsp" />

        <!-- header section end -->
        <!-- about section start -->
        <div class="about_section layout_padding">
            <div class="container">
                <div class="about_main">
                    <h1 class="about_text"> 💪 About us 🧘‍♂️</h1>
                    <p class="ipsum_text">Yoga San is a leading yoga center with a team of professional instructors and a variety of courses suitable for all individuals. We focus on the holistic development of our students, from cultivating physical flexibility to attaining inner tranquility of the soul.</p>
                    <p class="ipsum_text">With our dedicated and high-standard teaching methodology, we guarantee you a peaceful and enriching yoga experience at Yoga San. Come and explore the world of yoga with us, and unleash your maximum potential.</p>

                </div>
                <div class="about_bt_main">
                    <div class="about_bt"><a href="<%=url%>/BLogController?action=showBlogs">Discover our Blogs</a></div>
                    <div class="about_bt"><a href="<%= url%>/ClassController?action=classes">Discover our Class</a></div>
                    <c:if test = "${sessionScope.hocVienDTO == null && sessionScope.trainerDTO == null}">
                        <div class="about_bt"><a href="<%=url%>/Public/signin.js">Join Us</a></div>         

                    </c:if>        
                </div>
            </div>
        </div>
        <!-- about section end -->
        <jsp:include page="../Components/footerComponent.jsp" />       

        
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