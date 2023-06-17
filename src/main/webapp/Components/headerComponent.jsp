<%-- 
    Document   : headerComponent
    Created on : Jun 13, 2023, 3:06:10 PM
    Author     : devli
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<!-- header section start -->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="logo"><a href="home.jsp"><img src="images/logo.png"></a></div>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup"
            aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav">
            <a class="nav-item nav-link" href="home.jsp">Home</a>
            <a class="nav-item nav-link" href="about.jsp">About</a>
            <a class="nav-item nav-link" href="ClassController?action=classes">Classes</a>
            <a class="nav-item nav-link" href="schedule.html">Schedule</a>
            <a class="nav-item nav-link" href="trainer.html">Trainer</a>
            <a class="nav-item nav-link" href="contact.html">Contact us</a>
            <c:if test = "${sessionScope.hocVienDTO == null}">
                <a class="nav-item nav-link" href="<%=baseUrl%>/Authentication/signin.jsp">Login</a>              
            </c:if>
            <c:if test = "${sessionScope.hocVienDTO != null}">
                <div class="btn-group btn-user-menu">
                    <button style=" margin-left: 100px; background: #be2532 " class=" btn btn-secondary btn-sm dropdown-toggle btn-user" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">

                        <span>${sessionScope.hocVienDTO.username}</span>
                    </button>
                    <div class="dropdown-menu btn-menu">
                        <a class="dropdown-item btn-menu-item" href="profile.jsp">Profile</a>
                        <a class="dropdown-item btn-menu-item" href="changePassword.jsp">Change password</a>
                        <a class="dropdown-item btn-menu-item" href="<%=baseUrl%>/ScheduleController">View my schedule</a>                                                    <a class="dropdown-item btn-menu-item" href="<%=baseUrl%>/ScheduleController">View my schedule</a>
                        <a class="dropdown-item btn-menu-item" href="profile.jsp">View my profile</a>
                        <a class="dropdown-item btn-menu-item" href="<%=baseUrl%>/LoginController/?action=logout"><b style="color: black">Logout</b></a>
                    </div>
                </div>                     
            </c:if>
        </div>
    </div>
</nav>   
<!-- header section end -->
