<%-- 
    Document   : resetPass
    Created on : May 30, 2023, 2:50:01 PM
    Author     : devli
--%>
<%
    String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="../css/forgetPass.css" rel="stylesheet" type="text/css"/>        
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">      
    <body>
                  
            <div class="container d-flex flex-column ">
              <div class="row align-items-center justify-content-center
                  min-vh-100 g-0">
                <div class="col-12 col-md-8 col-lg-4 border-top border-3 border-primary">
                  <div class="card shadow-sm">
                    <div class="card-body">
                      <div class="mb-4">
                        <h5>Forgot Password?</h5>
                        <p class="mb-2">Enter your registered email ID to reset the password
                        </p>
                      </div>
                      <form  action="<%=url%>/LoginController/signup" method="post">
                        <div class="mb-3">
                          <label for="email" class="form-label">Email</label>
                           <input type="text" name="email" id="email" size="20" required="required">
                        </div>
                        <div class="mb-3 d-grid text-center">
                          <button type="submit" class="btn btn-primary">
                            Reset Password
                            <input type="hidden" id="thisField" name="action" value="resetPsw" /> 
                          </button>
                        </div>
                        <div>
                              <c:if test="${not empty errorMessageMail}">
                                    <div class="text-danger bo" role="alert"> 
                                          <p class="error"> <strong>${errorMessageMail}</strong> <a href ="<%=url%>/Authentication/signin.jsp">Return to login page.</a> </p>
                                    </div>                                      
                              </c:if>                              
                        </div>
                      </form>
                    </div>
                  </div>
                </div>
              </div>
            </div>
                      
                      
    </body>
</html>
