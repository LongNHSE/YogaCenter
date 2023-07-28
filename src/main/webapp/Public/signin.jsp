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
                    <h3 class="welcome-form">Yoga San Welcome</h3>
                    <span class="message-form">Log in to our system</span>

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
                        <button class="btn bkg">Sign In</button>  
                        <div class="signin-footer">
                            <div class="admin-signin w-50 text-left">
                                <a href="<%=url%>/Public/adminLogin.jsp">Sign in as an Admin</a>               
                            </div>
                            <div class="forget-psw forgot">
                                <a href="<%=url%>/Public/resetPass.jsp">Forgot your password?</a>
                            </div>                        
                            <input type="hidden" id="thisField" name="action" value="login">                            
                        </div>
                    </form>
                    
                </div>

                <div class="form sign_up">
                    <h3>Sign Up</h3>
                    <span>or use your email for register</span>
                </div>
                    
            </div>

            <div class="overlay">
                <div class="page page_signIn ">
                    <h3>Sign up</h3>
                    <p>Create an account to Join the Community!</p>
                    <a href="<%=url%>/Public/signup_1.jsp"><button class="btn bkg  btn-signup">Sign Up</button></a>


                </div>

            </div>

        </div>


        <!-- link script -->

    </body>
</html>
