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
        <meta http-equiv="X-UA-Compatible" />

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
        <% String popupMessage = (String) request.getAttribute("popupMessage"); %>
        <% if (popupMessage != null) {%> 
        <div id="myAlert" class="alert">
            <span class="closebtn" onclick="this.parentElement.style.display = 'none';">&times;</span> 
            <strong>!</strong> ${popupMessage}
            <div class="progress-bar">
                <div class="progress"></div>
            </div>
            <% }%>
        </div>
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

        <style>
            /* Styles for the progress bar */
            .progress-bar {
                width: 102%;
                background-color: #ddd;
                height: 3px;
                position: relative;
                top: 13px;
            }

            /* Remove the top and left positioning from .progress, it will be relative to .progress-bar */
            .progress {
                height: 100%;
                background-color: #4CAF50;
                width: 100%; /* Start with full width */
                animation: progress-animation 5s linear;
            }

            @keyframes progress-animation {
                0% {
                    width: 100%;
                }
                100% {
                    width: 0;
                }
            }

            /* Style for the header navigation */
            .header-nav {
                background-color: #8b57fc;
                margin-bottom: 0; /* Set margin-bottom to 0 */
            }

            /* Styles for the alert container */
            .alert {
                padding: 15px; /* Reduce padding to make the alert smaller */
                background-color: #f44336;
                color: white;
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                z-index: 9999;
            }

            /* Styles for the close button */
            .closebtn {
                margin-left: 15px;
                color: white;
                font-weight: bold;
                float: right;
                font-size: 22px;
                line-height: 20px;
                cursor: pointer;
                transition: 0.3s;
            }

            .closebtn:hover {
                color: black;
            }

        </style>
        <script>
            //                 // Function to close the alert message
            function closeAlert() {
                var alert = document.getElementById("myAlert");
                alert.style.display = "none";
            }

            // Function to automatically close the alert after 5 seconds
            function autoCloseAlert() {
                var alert = document.getElementById("myAlert");
                var progress = alert.querySelector(".progress");

                var duration = 500; // Duration in seconds
                var interval = 100; // Update interval in milliseconds
                var progressWidth = 100;

                var progressInterval = setInterval(function () {
                    progressWidth -= (interval / (duration * 10)) * 100;
                    progress.style.width = progressWidth + "%";

                    if (progressWidth <= 0) {
                        clearInterval(progressInterval);
                        alert.style.display = "none";
                    }
                }, interval);
            }

            // Call the autoCloseAlert function when the page has finished loading
            window.onload = autoCloseAlert;
            $(document).ready(function () {
                $('.sub-menu ul#active').show();
                $('li#active').find(".right").toggleClass("fa-caret-up fa-caret-down");
            });

            $('.sub-menu ul').hide();

            $(".sub-menu a").click(function () {
                $(this).parent(".sub-menu").children("ul").slideToggle("100");
                $(this).find(".right").toggleClass("fa-caret-up fa-caret-down");
            });</script>
        <!-- link script -->

    </body>
</html>
