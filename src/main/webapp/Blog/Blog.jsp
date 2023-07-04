<%-- 
    Document   : Blog
    Created on : Jul 4, 2023, 9:13:55 PM
    Author     : devli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
      <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>JSP Page</title>
        <!--CSS-->
        <%@ include file="../Components/CSSComponent.jsp" %>
        <!--Javascript-->
        <%@ include file="../Components/JsComponent.jsp" %>                
      </head>
      <body>
        <!--navbar : Start-->
        <jsp:include page="../Components/headerComponent.jsp" />       
        <!--navbar: End-->
            <h1>Hello World!</h1>
      </body>
</html>
