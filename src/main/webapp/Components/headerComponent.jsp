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
  <%--<%@ include file="../Components/CSSComponent.jsp" %>--%>

<style>
    /* CSS for the dropdown menu */
    body{
            font-family: 'Bitter', serif !important;
    }
    .header-nav{
          background-color: #E4D96F;
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

</style>

<!-- header section start -->
<nav class="header-nav navbar navbar-expand-lg bg-pink text-center  ">
    <div class="logo"><a href="home.jsp"><img src="<%= headerUrl%>/images/logo.png"></a></div>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup"
            aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav ml-auto mr-auto">

         <a class="nav-item nav-link text-dark" href="<%= headerUrl%>/Public/home.jsp">Home</a>
        <a class="nav-item nav-link text-dark" href="<%= headerUrl%>/Public/about.jsp">About</a>
        <a class="nav-item nav-link text-dark" href="<%= headerUrl%>/ClassController?action=classes">Classes</a>
        <a class="nav-item nav-link text-dark" href="<%=headerUrl%>/Authentiation/schedule.jsp">Schedule</a>
        <a class="nav-item nav-link text-dark" href="<%=headerUrl%>/BLogController?action=showBlogs">Blog</a>
        <a class="nav-item nav-link text-dark" href="<%= headerUrl%>/Public/contact.jsp">Contact us</a>
            <c:if test = "${sessionScope.hocVienDTO == null}">
                <a class="nav-item nav-link text-dark" href="<%=headerUrl%>/Authentication/signin.jsp">Login</a>              
            </c:if>
            <c:if test = "${sessionScope.hocVienDTO != null}">
                <div class=" dropdown header-user " style="margin-top:5px" >
                    <button  class="btn btn-sm dropdown-toggle btn-user" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <span>${sessionScope.hocVienDTO.username}</span>
                    </button>
                    <div class="dropdown-content">

                        <a class="btn-menu-item " href="<%=headerUrl%>/Authorization/TraineePrivilege/profile.jsp">Profile</a>
                        <a class="btn-menu-item" href="<%=headerUrl%>/Public/changePass.jsp">Change password</a>
                        <a class="btn-menu-item" href="<%=headerUrl%>/ScheduleController">View my schedule</a>                                                  
                        <a class="btn-menu-item" href="<%=headerUrl%>/LoginController/signup?action=logout"><b style="color: black">Logout</b></a>
                    </div>

                 </div> 
            </c:if>
                
        </div>
    </div>
</nav>   
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.min.js"></script>
            <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
            


