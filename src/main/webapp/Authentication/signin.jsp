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
                    <h3>Sign In</h3>
                    <span>or use your account</span>

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

                        <div class="forgot">
                            <a href="<%=url%>/Authentication/resetPass.jsp">Forgot your password?</a>
                        </div>

                        <button class="btn bkg">Sign In</button>                      
                        <input type="hidden" id="thisField" name="action" value="login">
                        <br>
                        <a href="<%=url%>/Authentication/adminLogin.jsp">Sign in as an Admin</a>
                    </form>
                    
                </div>

                <div class="form sign_up">
                    <h3>Sign Up</h3>
                    <span>or use your email for register</span>


                </div>
                    
            </div>

            <div class="overlay">
                <div class="page page_signIn">
                    <h3>Welcome Back!</h3>
                    <p>To keep with us please login with your personal info</p>




                    <a href="<%=url%>/Authentication/signup_1.jsp"><button class="btn bkg">Sign Up</button></a>


                </div>

            </div>

        </div>


        <!-- link script -->

    </body>
</html>
