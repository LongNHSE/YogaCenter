<%-- 
    Document   : success
<<<<<<< HEAD
    Created on : May 26, 2023, 2:49:48 PM
=======
    Created on : May 27, 2023, 12:49:12 AM
>>>>>>> 9519572ea5189efd86be83c062d95fb02c3a8204
    Author     : devli
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
        <h1>SUCCESS</h1>
        <form action="<%=url%>/ProfileController?action=viewProfile&&maHocVien=${hocVienDTO.maHV}" method="POST">
            <button name="action" value="">
            Profile
            </button>
        </form>
            <form action="<%=url%>/ProfileController?action=viewTransaction&&maHocVien=${hocVienDTO.maHV}" method="POST">
            <button name="action" value="">
            Transaction History
            </button>
        </form>
    </body>
</html>
