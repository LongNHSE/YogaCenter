<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Profile Page</h1>
        <table border="1">
            <tbody>
                <tr>
                    <!--trong phan log in phai them set user de co the goi ra trong session Scope-->
                    <td>Ho: ${sessionScope.hocVienDTO.ho}</td> 
                    <td>Ten: ${sessionScope.hocVienDTO.ten}</td>
                </tr>
                <tr>
                    <td>Email: ${sessionScope.hocVienDTO.email}</td>
                    <td>Phone: ${sessionScope.hocVienDTO.phone}</td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                </tr>
            </tbody>
        </table>

    </body>
</html>
