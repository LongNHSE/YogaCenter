<%-- 
    Document   : CreateClassTypePage
    Created on : Jun 9, 2023, 1:15:35 PM
    Author     : Oalskad
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mycompany.yogacenterproject.dto.TrainerDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        %>
        <link href="css/signupCSS.css" rel="stylesheet" type="text/css"/>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Class Type</title>
    </head>
    <%
        List<TrainerDTO> listTrainer = (List<TrainerDTO>) request.getAttribute("listTrainer");

    %>
    <body>
        <section class="vh-100 gradient-custom">
            <div class="container py-5 h-100">
                <div class="row justify-content-center align-items-center h-100">
                    <div class="col-12 col-lg-9 col-xl-7">
                        <div class="card shadow-2-strong card-registration" style="border-radius: 15px;">
                            <div class="card-body p-4 p-md-5">
                                <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Create Class Type</h3>
                                <form action="<%=url%>/ClassController" method="POST">

                                    <div class="row">
                                        <div class="col-md-6 mb-4">

                                        <div class="form-outline">

                                                <select name="listLoaiLopHoc"  onchange="getHocPhi(this.value)" class="selected-control " onchange="getHocPhi(this.value)" required>
                                                    <option class="form-label" value=""> Please choose type of class</option>
                                                    <c:forEach items="${listTrainer}" var="Trainer">
                                                        <option class="form-label" value="${Trainer.maTrainer}">${Trainer.ten}</option>
                                                    </c:forEach>
                                                </select>
                                                <label class="form-label" for="LoaiLopHoc">Type of class</label>
                                            </div>

                                        </div>
                                        <div class="col-md-6 mb-4">

                                            <div class="form-outline">
                                                <div class="input-container">
                                                    <input type="number" id="hocPhi" name="hocPhi"  min="0" step="0.5">
                                                    <span class="currency">x1.000.000VND</span>
                                                    <label class="form-label" for="hocPhi">Hoc Phi</label>
                                                </div>
                                            </div>

                                            <div class="mb-1">
                                                <div class="mt-4 pt-2">
                                                    <input type="hidden" name="action" value="CreateClassType" />
                                                    <input class="btn btn-outline-danger" type="submit" value="Submit" id="submit"  />
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                        </section>
                        </body>
                        </html>
