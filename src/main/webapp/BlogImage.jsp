<%-- 
    Document   : BlogImage
    Created on : Jul 6, 2023, 8:04:09 AM
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
            <title>JSP Page</title>
      </head>
      <body>
            <h1>Hello World!</h1>
                    <form action="<%=url%>/BLogController" >
                        <input type="hidden" name="action" value="showImageList">
                        <button type="submit">Show image</button>
                        </form>
                <ul>

    </ul>
      </body>
</html>
