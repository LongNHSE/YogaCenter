<%-- 
    Document   : signup
    Created on : Mar 2, 2023, 12:03:46 PM
    Author     : Oalskad
--%>
<%
    String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="css/signupCSS.css" rel="stylesheet" type="text/css"/>
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
                        <div class="card shadow-2-strong card-registration" style="border-radius: 15px;">
                            <div class="card-body p-4 p-md-5">
                                <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Registration Form</h3>
                                <form action="<%=url%>/LoginController/signup" method="POST">
                                   
                                    <div class="row">
                                        <div class="col-md-6 mb-4 pb-2">

                                            <div class="form-outline">
                                                <input type="email" id="emailAddress" class="form-control form-control-lg" name="email" required="required"/>
                                                <label class="form-label" for="emailAddress">Email</label>
                                            </div>

                                        </div>

                                    </div>
                                    <div class="mb-1">
                                        <input type="checkbox" class="form-check-inline" id="policyCheck" name="policyCheck" required="required">
                                        <label for="policyCheck" class="form-label">I agree with the <a href="" style="color: black">Term and Condition</a> and the <a href="" style="color: black">Privacy Policy</a> </label>


                                        <div class="mt-4 pt-2">
                                            <input class="btn btn-outline-danger" type="submit" value="Submit" id="submit"  />
                                        </div>
                                        <input type="hidden" id="thisField" name="action" value="OTPSend" />


                                </form>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>

   
</html>
