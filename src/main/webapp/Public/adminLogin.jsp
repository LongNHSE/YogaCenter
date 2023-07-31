<%-- 
    Document   : signin
    Created on : May 23, 2023, 9:53:30 PM
    Author     : devli
--%>
<%
    String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />

        <!-- link css -->
        <link rel="stylesheet" href="<%=url%>/assets/css/style.css" />
        <link rel="stylesheet" href="<%=url%>/css/adminSignin.css" />

        <!-- link icon -->
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.10.5/font/bootstrap-icons.min.css"
            integrity="sha512-ZnR2wlLbSbr8/c9AgLg3jQPAattCUImNsae6NHYnS9KrIwRdcY9DxFotXhNAKIKbAXlRnujIqUWoXXwqyFOeIQ=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"
            />

        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Form</title>
    </head>
    <body>
        <div class="container">
            <div class="box">
                <div class="form sign_in">
                    <h3>Welcome Adminastrator</h3>
                    <form action="<%=url%>/LoginController/login" id="form_input" method="POST">

                        <div class="type">
                            <input type="text" placeholder="Username" name="username" id="username" />
                        </div>
                        <div class="type">
                            <input
                                type="password"
                                placeholder="Password"
                                name="password"
                                id="password"
                                />
                        </div>

                        <div class="forgot change-user">
                           <a href="<%=url%>/Public/signin.jsp">Sign in as an User</a>
                        </div>
                        <input class="btn btn-outline-danger" type="submit" value="Submit" id="submit" disabled />
                        <button class="btn bkg" type="submit">Sign In</button>
                        <input type="hidden" id="thisField" name="action" value="adminLogin">             
                    </form>    
                </div>
            </div>
                        
                        <div class="overlay"> 
                            <img src="https://images.pexels.com/photos/13359846/pexels-photo-13359846.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1">
                                    <p>Welcome back admin</p>
                        </div>
                    
        </div>
        <!-- link script -->

    </body>
</html>
