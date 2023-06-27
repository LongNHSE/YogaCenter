<%-- 
    Document   : OTPTest
    Created on : May 21, 2023, 4:44:24 PM
    Author     : Oalskad
--%>
<%
    String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
    </head>
    <body>
       
        <h1>Your OTP: ${OTP}</h1>
        asdasdsd
    </body>
</html>
