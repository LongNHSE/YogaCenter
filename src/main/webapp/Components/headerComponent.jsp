<%-- 
    Document   : headerComponent
    Created on : Jun 13, 2023, 3:06:10 PM
    Author     : devli
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%

    String headerUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<%--<%@ include file="../Components/CSSComponent.jsp" %>--%>

<style>
    /* CSS for the dropdown menu */
    body{
        /*font-family: 'Bitter', serif !important;*/
        font-family: 'Montserrat', sans-serif t;
        box-sizing: border-box;


    }
    .header-nav{
        background-color: #8b57fc;
        margin-bottom:20px
    }
    /*    .dropdown {
            position: relative;
            display: inline-block;
        }*/
    .dropdown-menu {
        min-width: 160px;
    }

    .dropdown-menu a {
        color: black;
        padding: 12px 16px;
        text-decoration: none;
        display: block;
    }

    .dropdown-menu a:hover {
        background-color: #f1f1f1;
    }

    .dropdown-content {
        display: none;
        position: absolute;
        background-color: #f9f9f9;
        min-width: 160px;
        box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
        z-index: 1;
    }

    .dropdown-content a {
        color: black;
        padding: 12px 16px;
        text-decoration: none;
        display: block;
    }

    .dropdown-content a:hover {
        background-color: #436775;
        color:#f1f1f1;
    }

    .dropdown:hover .dropdown-content {
        display: block;
    }
    .header-user{
        background-color: #87CEEB;
    }
    .navbar-nav{
        margin-left: 800px;
    }
    .navbar-expand-lg .navbar-nav .nav-link {
        padding-right: 10px;
        padding-left: 10px;
        color: #fff;
    font-size: 18px;
    }

</style>


<!-- header section start -->
<nav class="header-nav navbar navbar-expand-lg bg-pink text-center  ">
    <div class="logo"><a href="home.jsp"><img src="<%= headerUrl%>/images/logo.png"></a></div>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup"
            aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">

        <div class="navbar-nav">
            <a class="nav-item nav-link" href="<%= headerUrl%>/Public/home.jsp">Home</a>
            <a class="nav-item nav-link" href="<%= headerUrl%>/Public/about.jsp">About</a>
            <a class="nav-item nav-link" href="<%= headerUrl%>/ClassController?action=classes">Classes</a>


            <a class="nav-item nav-link" href="<%=headerUrl%>/BLogController?action=showBlogs"">Blog</a>
            <c:if test = "${sessionScope.hocVienDTO == null && sessionScope.trainerDTO == null}">
                <a class="nav-item nav-link" href="<%=headerUrl%>/Authentication/signin.jsp">Login</a>              

            </c:if>
            <c:if test = "${sessionScope.hocVienDTO != null}">
                <div class=" dropdown header-user " style="margin-top:5px" >
                    <button  class="btn btn-sm dropdown-toggle btn-user" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <span>${sessionScope.hocVienDTO.username}</span>
                    </button>
                    <div class="dropdown-content">

                        <a class="btn-menu-item " href="<%=headerUrl%>/Authorization/TraineePrivilege/profile.jsp">Profile</a>
                        <a class="btn-menu-item " href="<%=headerUrl%>/ProfileController?action=viewTransaction">View My Transaction</a>
                        <a class="btn-menu-item" href="<%=headerUrl%>/Public/changePass.jsp">Change password</a>
                        <a class="btn-menu-item" href="<%=headerUrl%>/ScheduleController">View my schedule</a>         
                        <a class="btn-menu-item" href="<%=headerUrl%>/BLogController?action=MyBlog">My Blog</a>
                        <a class="btn-menu-item" href="<%=headerUrl%>/ProfileController?action=classList">My Class</a>
                        <a class="btn-menu-item" href="<%=headerUrl%>/LoginController/signup?action=logout"><b style="color: black">Logout</b></a>
                    </div>

                </div> 
            </c:if>
            <c:if test = "${sessionScope.trainerDTO != null}">
                <div class="dropdown">
                    <button style="margin-top: 10px; margin-left: 100px; background: #be2532" class="btn btn-secondary btn-sm dropdown-toggle btn-user" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <span>${sessionScope.trainerDTO.username}</span>
                    </button>
                    <div class="dropdown-content">
                        <a class="btn-menu-item" href="<%=headerUrl%>/Authorization/TrainerPrivilege/profile.jsp">Profile</a>
                        <a class="btn-menu-item" href="<%=headerUrl%>/Public/changePass.jsp">Change password</a>
                        <a class="btn-menu-item" href="<%=headerUrl%>/TrainerScheduleController?action=TrainerSchedule">View Trainer schedule</a>
                        <a class="btn-menu-item" href="<%=headerUrl%>/BLogController/?action=MyBlog"><b style="color: black">My Blog</b></a>
                        <a class="btn-menu-item" href="<%=headerUrl%>/LoginController/signup?action=logout"><b style="color: black">Logout</b></a>
                    </div>
                </div>                  
            </c:if>

        </div>
    </div>
</nav>   

<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


