<%-- 
    Document   : signup
    Created on : Mar 2, 2023, 12:03:46 PM
    Author     : Oalskad
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="<%=url%>/assets/css/signup_1Styles.css" rel="stylesheet" type="text/css"/>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Signup</title>
    </head>

    <c:url var="signupLink" value="${request.contextPath}/Authentication/signup"/>
    <body>
        <section class="vh-100 gradient-custom">
            <div class="container py-5 h-100">
                <div class="row justify-content-center align-items-center h-100">
                    <div class="col-12 col-lg-9 col-xl-7">
                        <div class="card shadow-2-strong card-registration register-form" style="border-radius: 15px;">
                            <div class="card-body p-4 p-md-5">                         
                                <h3 class="mb-4 pb-2 pb-md-0 mb-md-5 form-name">Registration Form</h3>
                                <form action="<%=url%>/LoginController/signup" method="POST">
                                            <div class="form-outline">
                                                <input type="email" id="emailAddress" class="form-control form-control-lg email-input" name="email" required="required" placeholder="Enter your email"/>
                                            </div>
                                            <div style="color: red; font-weight: BOLD">
                                                <% String errorMessageMail = (String) request.getAttribute("errorMessageMail"); %>
                                                <% if (errorMessageMail != null) {%> <%= errorMessageMail%> <% }%>
                                            </div>

                                            <div class="mb-1 term-section">
                                                <div>
                                                <input type="checkbox" class="form-check-inline term-checkbox" id="policyCheck" name="policyCheck" required="required">
                                                <label for="policyCheck" class="form-label">I agree with the <a href="" style="color: black">Term and Condition</a> and the <a href="" style="color: black">Privacy Policy</a> </label>
                                                 </div>
                                                <div class="mt-4 pt-2">
                                                    <input class="btn btn-outline-danger btn-submit" type="submit" value="Submit" id="submit"  />
                                                </div>
                                                <input type="hidden" id="thisField" name="action" value="OTPSend" />
                                            </div>
                              </form>
                            </div>
                        </div>
                        </section>
                        </body>


                        </html>
