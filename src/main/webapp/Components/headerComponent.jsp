<%-- 
    Document   : headerComponent
    Created on : Jun 13, 2023, 3:06:10 PM
    Author     : devli
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>

<style>
    /* CSS for the dropdown menu */
    .dropdown {
        position: relative;
        display: inline-block;
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
        background-color: #f1f1f1;
    }

    .dropdown:hover .dropdown-content {
        display: block;
    }
</style>
<!-- header section start -->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="logo"><a href="home.jsp"><img src="<%= baseUrl%>/images/logo.png"></a></div>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup"
            aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav">
            <a class="nav-item nav-link" href="<%= baseUrl%>/Public/home.jsp">Home</a>
        <a class="nav-item nav-link" href="<%= baseUrl%>/Public/about.jsp">About</a>
        <a class="nav-item nav-link" href="<%= baseUrl%>/ClassController?action=classes">Classes</a>
        <a class="nav-item nav-link" href="<%=baseUrl%>/Authentiation/schedule.jsp">Schedule</a>
        <a class="nav-item nav-link" href="<%=baseUrl%>/trainer.jsp">Trainer</a>
        <a class="nav-item nav-link" href="<%= baseUrl%>/Public/contact.jsp">Contact us</a>
            <c:if test = "${sessionScope.hocVienDTO == null}">
                <a class="nav-item nav-link" href="<%=baseUrl%>/Authentication/signin.jsp">Login</a>              
            </c:if>
            <c:if test = "${sessionScope.hocVienDTO != null}">
                <div class="dropdown">
                    <button style="margin-top: 10px; margin-left: 100px; background: #be2532" class="btn btn-secondary btn-sm dropdown-toggle btn-user" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <span>${sessionScope.hocVienDTO.username}</span>
                    </button>
                    <div class="dropdown-content">
                        <a class="btn-menu-item" href="<%=baseUrl%>/Authorization/TraineePrivilege/profile.jsp">Profile</a>
                        <a class="btn-menu-item" href="<%=baseUrl%>/Public/changePass.jsp">Change password</a>
                        <a class="btn-menu-item" href="<%=baseUrl%>/ScheduleController">View my schedule</a>                                                  
                        <a class="btn-menu-item" href="<%=baseUrl%>/LoginController/signup?action=logout"><b style="color: black">Logout</b></a>
                    </div>
                </div>                  
            </c:if>
        </div>
    </div>
</nav>   
<!-- header section end -->
