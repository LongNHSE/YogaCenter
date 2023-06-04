<%-- 
    Document   : OTPCheck
    Created on : May 27, 2023, 11:24:57 AM
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
        <title>OTP CHECK</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
        <link href="../css/OTP.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
      <div class="login-dark">
          <form action="<%=url%>/LoginController/signup" method="POST">
              <h2 class="sr-only">Login Form</h2>
              <div class="illustration"><i class="icon ion-ios-locked-outline"></i></div>
              <div class="form-group">
                    <input type="text" id="OTP" class="form-control form-control-lg" name="OTP" required="required" placeholder="Enter your OTP">
              </div>
              <div class="form-group">
                    <input class="btn btn-primary btn-block" type="submit" value="Submit" id="submit" />
                    <input type="hidden" id="thisField" name="action" value="OTP">                    
              </div>
          </form>
      </div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
