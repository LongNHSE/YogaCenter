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
    <style>




        .Table{
            position: relative;

        }
        table {

            width: 100%; /* Set the width of the table */
            /*            border-collapse: collapse;  Collapse the borders of table cells */

        }

        th, td {
            font-size: 15.5px;
            padding: 10px; /* Add padding to table cells */
            text-align: left; /* Align text to the left in table cells */
            border: 1px solid #ccc; /* Add borders to table cells */
        }

        .Test th {
            background-color: #97EA5D; /* Set background color for table headers */
        }

        tr:nth-child(even) {
            background-color: #f9f9f9; /* Set background color for even rows */
        }

        tr:hover {
            background-color: #e6e6e6; /* Set background color for hovered rows */
        }
    </style>    
    <body>
        <h1>Transaction Page</h1>
        <div class="Table">
            <table class="table">
                <thead>
                    <tr class="Test">
                        <!--trong phan log in phai them set user de co the goi ra trong session Scope-->
                        <th scope="col">No</td> 
                        <th scope="col">Gia Tien</td>
                        <th scope="col">Ngay Thanh Toan</td>
                        <!--Consider co them ten lop hoc-->
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="row" items="${sessionScope.listHoaDon}" varStatus="counter"> 
                        <tr>
                            <th>${counter.count}</td>
                            <td>${row.giaTien}</td>
                            <td>${row.ngayThanhToan}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
