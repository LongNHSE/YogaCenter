<%-- 
    Document   : process.jsp
    Created on : Jun 8, 2023, 11:04:27 PM
    Author     : Oalskad
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
        <select name="listLoaiLopHoc"  onchange="getHocPhi(this.value)">
            <c:forEach items="${listLoaiLopHoc}" var="LoaiLopHoc">
                <option value="${LoaiLopHoc.maLoaiLopHoc}">${LoaiLopHoc.tenLoaiLopHoc}</option>
            </c:forEach>
        </select>
        <p id="price">Price: </p>
    </body>

    <script>
        function getHocPhi(maLoaiLopHoc) {
            if (maLoaiLopHoc !== '') {
                var xhr = new XMLHttpRequest();
                xhr.open('GET', 'Admin/Class/getPrice.jsp?maLoaiLopHoc=' + maLoaiLopHoc, true);
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
                        var priceElement = document.getElementById('price');
                        priceElement.textContent = 'Price: ' + xhr.responseText;
                    }
                };
                xhr.send();
            } else {
                var priceElement = document.getElementById('price');
                priceElement.textContent = '';
            }
        }

    </script>
</html>
