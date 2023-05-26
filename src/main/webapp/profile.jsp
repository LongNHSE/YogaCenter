<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table border="0">
            <tbody>
                <tr>
                    <!--trong phan log in phai them set user de co the goi ra trong session Scope-->
                    <td>Ho: ${sessionScope.user.Ho}</td> 
                    <td>Ten: ${sessionScope.user.Ten}</td>
                </tr>
                <tr>
                    <td>Email: ${sessionScope.user.email}</td>
                    <td>Phone: ${sessionScope.user.phone}</td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                </tr>
            </tbody>
        </table>

    </body>
</html>
