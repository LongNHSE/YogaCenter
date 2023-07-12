<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<c:import url='<%= url + "/Components/JsComponent.jsp"%>' />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">


        <title>Profile Detail</title>
    <link href="Class/ScheduleStyle.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <title>JSP Page</title>
        <!-- bootstrap css -->
        <link rel="stylesheet" href="../css/bootstrap.min.css">
        <!-- style css -->

        <!-- Responsive-->

        <!-- fevicon -->
        <link rel="icon" href="images/fevicon.png" type="image/gif" />
        <!-- Scrollbar Custom CSS -->
        <link rel="stylesheet" href="../css/jquery.mCustomScrollbar.min.css">
        <!-- Tweaks for older IEs-->
        <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
        <!-- owl stylesheets -->
        <link rel="stylesheet" href="../css/owl.carousel.min.css">
        <link rel="stylesheet" href="../css/owl.theme.default.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css"
              media="screen">      

        <style type="text/css">
            body{
                background-color:#f2f6fc;
                color:#69707a;
            }
            .img-account-profile {
                height: 10rem;
            }
            .rounded-circle {
                border-radius: 50% !important;
            }
            .card {
                box-shadow: 0 0.15rem 1.75rem 0 rgb(33 40 50 / 15%);
            }
            .card .card-header {
                font-weight: 500;
            }
            .card-header:first-child {
                border-radius: 0.35rem 0.35rem 0 0;
            }
            .card-header {
                padding: 1rem 1.35rem;
                margin-bottom: 0;
                background-color: rgba(33, 40, 50, 0.03);
                border-bottom: 1px solid rgba(33, 40, 50, 0.125);
            }
            .form-control, .dataTable-input {
                display: block;
                width: 100%;
                padding: 0.875rem 1.125rem;
                font-size: 0.875rem;
                font-weight: 400;
                line-height: 1;
                color: #69707a;
                background-color: #fff;
                background-clip: padding-box;
                border: 1px solid #c5ccd6;
                -webkit-appearance: none;
                -moz-appearance: none;
                appearance: none;
                border-radius: 0.35rem;
                transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
            }

            .nav-borders .nav-link.active {
                color: #0061f2;
                border-bottom-color: #0061f2;
            }
            .nav-borders .nav-link {
                color: #69707a;
                border-bottom-width: 0.125rem;
                border-bottom-style: solid;
                border-bottom-color: transparent;
                padding-top: 0.5rem;
                padding-bottom: 0.5rem;
                padding-left: 0;
                padding-right: 0;
                margin-left: 1rem;
                margin-right: 1rem;
            }
        </style>
    </head>
    <body>
        <jsp:include page="${url}/Components/headerComponent.jsp" />          
     
        <div class="container-xl px-4 mt-4">

            <hr class="mt-0 mb-4">
            <div class="row">
                <!--      <div class="col-xl-4">
                
                      <div class="card mb-4 mb-xl-0">
                      <div class="card-header">Profile Picture</div>
                      <div class="card-body text-center">
                
                      <img class="img-account-profile rounded-circle mb-2" src="http://bootdey.com/img/Content/avatar/avatar1.png" alt>
                
                      <div class="small font-italic text-muted mb-4">JPG or PNG no larger than 5 MB</div>
                
                      <button class="btn btn-primary" type="button">Upload new image</button>
                      </div>
                      </div>
                
                      </div>-->
                <div class="col-xl-8">

                    <div class="card mb-4">
                        <div class="card-header">Account Details</div>
                        <div class="card-body">
                            <form method="POST" action="<%=url%>/ProfileController">
                                <input class="form-control" id="setMaHV" name="maHV" type="text" value="${hocVienDTO.maHV}" readonly>
                                <div class="mb-3">
                                    <label class="small mb-1" for="inputUsername" >Username</label>
                                    <input class="form-control" id="inputUsername" name="username" type="text" placeholder="" value="${hocVienDTO.username}" readonly>
                                </div>

                                <div class="row gx-3 mb-3">

                                    <div class="col-md-6">
                                        <label class="small mb-1" for="inputFirstName">Last Name</label>
                                        <input class="form-control" id="inputFirstName" type="text" name="ho" placeholder="" value="${hocVienDTO.ho}" readonly>
                                    </div>

                                    <div class="col-md-6">
                                        <label class="small mb-1" for="inputLastName">First name</label>
                                        <input class="form-control" id="inputLastName" name="ten" type="text" placeholder="Enter your last name" value="${hocVienDTO.ten}" readonly>
                                    </div>
                                </div>

<!--                                <div class="row gx-3 mb-3">
                                    <div class="col-md-12">
                                        <label class="small mb-1" for="inputOrgName">Organization name</label>
                                        <input class="form-control" id="inputOrgName" type="text" name="orgname" placeholder="Enter your organization name" value="Start Bootstrap" readonly>
                                    </div>
                                </div>-->

                                <div class="mb-3">
                                    <label class="small mb-1" for="inputEmailAddress">Email address</label>
                                    <input class="form-control" id="inputEmailAddress" name="email" type="email"  value="${hocVienDTO.email}" readonly>
                                </div>

                                <div class="row gx-3 mb-3">

                                    <div class="col-md-6">
                                        <label class="small mb-1" for="inputPhone">Phone number</label>
                                        <input class="form-control" id="inputPhone" name="phone" type="tel" placeholder="" value="${hocVienDTO.phone}" readonly>
                                    </div>

                                    <div class="col-md-6">
                                        <label class="small mb-1" for="inputBirthday">Birthday</label>
                                        <input class="form-control" id="inputBirthday" name="dob" type="text" name="birthday" placeholder="Enter your birthday" value="${hocVienDTO.dob}" readonly>
                                    </div>
                                </div>

                                <button class="btn btn-primary" type="submit" name="action" value="viewUpdateProfile" >Update</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>
        <script type="text/javascript">
        </script>
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
