<%-- 
    Document   : index
    Created on : May 21, 2023, 5:23:32 PM
    Author     : Oalskad
--%>

<%
    String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Start Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <form action="<%=url%>/OTPController" method="POST">
            <button class="btn btn-primary btn-block fa-lg gradient-custom-2 mb-3" type="submit">Log
                in</button>
        </form>
    </body>
</html>
