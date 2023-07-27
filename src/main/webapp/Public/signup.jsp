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
        <link href="<%=url%>/css/signupCSS.css" rel="stylesheet" type="text/css"/>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Signup</title>
    </head>

    <c:url var="signupLink" value="${request.contextPath}/Authentication/signup"/>
    <body>
        <section class="vh-100 gradient-custom register-section">
            <div class="container py-5 h-100">
                <div class="row justify-content-center align-items-center h-100">
                    <div class="col-12 col-lg-9 col-xl-7">
                        <div class="card shadow-2-strong card-registration register-form" style="border-radius: 50px;">
                            <div class="card-body p-4 p-md-5">
                                <h3 class="mb-4 pb-2 pb-md-0 mb-md-5 form-name">Registration Form</h3>
                                <form action="<%=url%>/LoginController/signup" method="POST">
                                    <input type="hidden" id="thisField" name="action" value="addUser" placeholder="Enter your OTP send through email" />
                                    <div class="row">
                                        <div class="col-md-6 mb-4">

                                            <div class="form-outline">

                                                <input type="text" id="userName" class="form-control form-control-lg" name="username" required="required"/>
                                                <label class="form-label" for="userName">Username</label>
                                                <div style="color: red; font-weight: BOLD">
                                                    <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
                                                    <% if (errorMessage != null) {%> <%= errorMessage%> <% }%>
                                                </div>

                                            </div>

                                        </div>
                                        <div class="col-md-6 mb-4">

                                            <div class="form-outline">
                                                <input type="password" id="password" class="form-control form-control-lg" name="psw" required="required" onkeyup='check();'/>
                                                <label class="form-label" for="password">Password</label>
                                            </div>
                                            <div class="form-outline">
                                                <input type="password" id="confirmPassword" class="form-control form-control-lg" name="confirmPassword" required="required" onkeyup='check();'/>
                                                <label class="form-label" for="confirmPassword">Confirm password</label>
                                                <span id='message'></span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6 mb-4">

                                            <div class="form-outline">
                                                <input type="text" id="Ho" class="form-control form-control-lg" name="Ho" />
                                                <label class="form-label" for="Ho">Ho</label>
                                            </div>

                                        </div>
                                        <div class="col-md-6 mb-4">

                                            <div class="form-outline">
                                                <input type="text" id="Ten" class="form-control form-control-lg" name="Ten" />
                                                <label class="form-label" for="Ten">Ten</label>
                                            </div>

                                        </div>

                                    </div>

                                    <div class="row">
                                        <div class="col-md-6 mb-4 d-flex align-items-center">

                                            <div class="form-outline datepicker w-100">
                                                <input type="Date" class="form-control form-control-lg" id="birthdayDate" name="dateOfBirth" required="required" />
                                                <label for="birthdayDate" class="form-label">Birthday</label>
                                                <div style="color: red; font-weight: BOLD">
                                                    <% String errorMessageDate = (String) request.getAttribute("errorMessageDate"); %>
                                                    <% if (errorMessageDate != null) {%> <%= errorMessageDate%> <% }%>
                                                </div>
                                            </div>

                                        </div>
                                        <div class="col-md-6 mb-4">

                                            <h6 class="mb-2 pb-1">Gender: </h6>

                                            <div class="form-check form-check-inline">
                                                <input class="form-check-input" type="radio" name="gender" id="femaleGender"
                                                       value="Female" checked />
                                                <label class="form-check-label" for="femaleGender">Female</label>
                                            </div>

                                            <div class="form-check form-check-inline">
                                                <input class="form-check-input" type="radio" name="gender" id="maleGender"
                                                       value="Male" />
                                                <label class="form-check-label" for="maleGender">Male</label>
                                            </div>

                                            <div class="form-check form-check-inline">
                                                <input class="form-check-input" type="radio" name="gender" id="otherGender"
                                                       value="Other" />
                                                <label class="form-check-label" for="otherGender">Other</label>
                                            </div>

                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-6 mb-4 pb-2">
                                           
                                            <div class="form-outline">
                                                <input type="email" id="emailAddress" value="${sessionScope.email}"  class="form-control form-control-lg" name="email" required="required" disabled/>
                                                <label class="form-label" for="emailAddress">Email</label>
                                            </div>

                                        </div>
                                        <div class="col-md-6 mb-4 pb-2">

                                            <div class="form-outline">
                                                <input type="tel" placeholder="xxx-xxx-xxxx" pattern="[0-9]{10}" id="phoneNumber" class="form-control form-control-lg" name="phoneNumber" required="required" />
                                                <label class="form-label" for="phoneNumber">Phone Number</label>
                                            </div>

                                        </div>
                                    </div>
                                    <div class="mb-1">
                                        <input type="checkbox" class="form-check-inline" id="policyCheck" name="policyCheck" required="required">
                                        <label for="policyCheck" class="form-label">I agree with the <a href="" style="color: black">Term and Condition</a> and the <a href="" style="color: black">Privacy Policy</a> </label>


                                        <div class="mt-4 pt-2">
                                            <!--<input class="btn btn-outline-danger" type="submit" value="Submit" id="submit" disabled />-->
                                            <button class="submit-btn" value="Submit" id="submit" disabled>Submit</button>
                                        </div>
                                </form>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>

    <script>
        var check = function () {
            if (document.getElementById('password').value ==
                    document.getElementById('confirmPassword').value) {
                document.getElementById('message').style.color = 'green';
                document.getElementById('message').innerHTML = 'Matching';
                document.getElementById('message').style.fontWeight = 'bold';
                document.getElementById('submit').disabled = false;
            } else {
                document.getElementById('message').style.color = 'red';
                document.getElementById('message').innerHTML = 'Not matching';
                document.getElementById('message').style.fontWeight = 'bold';
                document.getElementById('submit').disabled = true;
            }
        }
    </script>
</html>
