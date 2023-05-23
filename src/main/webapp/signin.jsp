<%-- 
    Document   : signin
    Created on : May 23, 2023, 9:53:30 PM
    Author     : devli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <!--SIGN IN-->
    <div class="container">
      <div class="box">
        <div class="form sign_in">
          <h3>Sign In</h3>
          <span>or use your account</span>

          <form action="/LoginController" id="form_input" method="POST">
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
              <span>Forgot your password?</span>
            </div>

            <button class="btn bkg">Sign In</button>
          </form>
        </div>

          <!--SIGN UP-->
        <div class="form sign_up">
          <h3>Sign Up</h3>
          <span>or use your email for register</span>

          <form action="#" id="form_input">
            <div class="type">
              <input
                type="text"
                name="username"
                placeholder="Username"
                id="name"
                class="firstname"
              />
            </div>
            <div class="type">
              <input type="email" name="email" placeholder="Email" id="email" />
            </div>
            <div class="type">
              <input
                type="password"
                name="password"
                placeholder="Password"
                id="password"
              />
            </div>
            <div class="type">
              <input
                type="password"
                name="password_confirm"
                placeholder="Confirm password"
                id="password"
              />
            </div>

            <button class="btn bkg">Sign Up</button>
          </form>
        </div>
      </div>

      <div class="overlay">
        <div class="page page_signIn">
          <h3>Welcome Back!</h3>
          <p>To keep with us please login with your personal info</p>

          <button class="btn btnSign-in">
            Sign Up <i class="bi bi-arrow-right"></i>
          </button>
        </div>

        <div class="page page_signUp">
          <h3>Hello Friend!</h3>
          <p>Enter your personal details and start journey with us</p>

          <button class="btn btnSign-up">
            <i class="bi bi-arrow-left"></i> Sign In
          </button>
        </div>
      </div>
    </div>

    <!-- link script -->
    <script src="./assets/js/main.js"></script>
    </body>
</html>
