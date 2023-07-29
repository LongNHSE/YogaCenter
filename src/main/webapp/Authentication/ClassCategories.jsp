<%-- 
    Document   : ClassCategories
    Created on : Jun 13, 2023, 3:36:32 PM
    Author     : devli
--%>
<%
    String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Yogasan</title>
        <link href="<%=url%>/css/ClassCategoriesStyles.css" rel="stylesheet" type="text/css"/>    
    </head>
    <body>
        <!--navbar : Start-->
        <jsp:include page="../Components/headerComponent.jsp" />       
        <!--navbar: End-->


        <div class="container " style="margin-top:15px">
            <h1 class="text-center text-muted-h1">Peace of Mind</h1>
            <% String popupMessage = (String) request.getAttribute("popupMessage"); %>
            <% if (popupMessage != null) {%> <div id="myAlert" class="alert">
                <span class="closebtn" onclick="this.parentElement.style.display = 'none';">&times;</span> 
                <strong>!</strong>You have registered class ${popupMessage} successfully. Please check your mail for more information.
                <div class="progress-bar">
                    <div class="progress"></div>
                </div>
                <% }%>
            </div>
            <div class="row">
                <c:forEach items="${listCate}" var="a">
                    <div class="col-xs-6 col-md-4">
                        <div class="classCate tumbnail thumbnail-3" style="border: 2px solid #ccc; border-radius: 10px; padding: 10px;margin: 20px 20px;">
                            <c:forEach items="${a.image}" var ="imageData">
                                <a href="ClassController?returnID=${a.getMaLoaiLopHoc()}&action=showDetails">
                                    <c:if test="${not empty imageData.tenAnh and imageData.tenAnh.equalsIgnoreCase('THUMBNAIL')}">
                                        <img src="data:image/jpeg;base64,${imageData.image}" alt="" style="width: 100%; height: 100%;">
                                    </c:if>

                                </a>
                            </c:forEach>
                            <div class="caption text-center">
                                <h2 ><a href="#" style="text-decoration: none; color: #333;">${a.getTenLoaiLopHoc()}</a></h2>
                                <span class="price"></span
                                <input type="hidden" value="${a.getMaLoaiLopHoc()}" name="returnID">
                                <form action="<%=url%>/ClassController" method="GET">
                                    <input type="hidden" name="returnID" value="${a.getMaLoaiLopHoc()}" />
                                    <input type="hidden" name="action" value="showDetails" />
                                    <button class="button" type="submit">Details</button>
                                </form>

                            </div>
                        </div>

                    </div>       
                </c:forEach>
            </div>
        </div>
        <!--Container: End-->

    </body>
    <style>
        .progress {
            height: 4px;
            background-color: #4CAF50;
            width: 100%;
            position: absolute;
            top: 61px;
            left: 0;
            animation: progress-animation 5s linear;
        }

        @keyframes progress-animation {
            0% {
                width: 100%;
            }
            100% {
                width: 0;
            }
        }
        .header-nav {
            background-color: #8b57fc;
            margin-bottom: 0px;
        }
        .alert {
            padding: 20px;
            background-color: greenyellow;
            color: black;

        }

        .closebtn {
            margin-left: 15px;
            color: white;
            font-weight: bold;
            float: right;
            font-size: 22px;
            line-height: 20px;
            cursor: pointer;
            transition: 0.3s;
        }

        .closebtn:hover {
            color: black;
        }
    </style>

    <!--Container: End-->
    <jsp:include page="../Components/footerComponent.jsp" />                  
</body>
<script>
    // Function to close the alert message
    function closeAlert() {
        var alert = document.getElementById("myAlert");
        alert.style.display = "none";
    }

    // Function to automatically close the alert after 5 seconds
    function autoCloseAlert() {
        var alert = document.getElementById("myAlert");
        var progress = alert.querySelector(".progress");

        var duration = 500; // Duration in seconds
        var interval = 100; // Update interval in milliseconds
        var progressWidth = 100;

        var progressInterval = setInterval(function () {
            progressWidth -= (interval / (duration * 10)) * 100;
            progress.style.width = progressWidth + "%";

            if (progressWidth <= 0) {
                clearInterval(progressInterval);
                alert.style.display = "none";
            }
        }, interval);
    }

    // Call the autoCloseAlert function when the page has finished loading
    window.onload = autoCloseAlert;
</script>
</html>
