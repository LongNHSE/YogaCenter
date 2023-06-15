<%-- 
    Document   : transaction
    Created on : Jun 4, 2023, 5:55:33 PM
    Author     : nctop
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Transaction Page</h1>
        ${sessionScope.hvDTO.username}
        <table border="1">
            <tbody>
                <tr>
                    <!--trong phan log in phai them set user de co the goi ra trong session Scope-->
                    <td>No</td> 
                    <td>Gia Tien</td>
                    <td>Ngay Thanh Toan</td>
                    <!--Consider co them ten lop hoc-->
                </tr>
                <c:forEach var="row" items="${sessionScope.listHoaDon}" varStatus="counter"> 
                    <tr>
                        <td>${counter.count}</td>
                        <td>${row.giaTien}</td>
                        <td>${row.ngayThanhToan}</td>
                        </tr>
                </c:forEach>
                    </tbody>
                </table>

            </body>
        </html>
