<%-- 
    Document   : CreateClassTypePage
    Created on : Jun 9, 2023, 1:15:35 PM
    Author     : Oalskad
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="com.mycompany.yogacenterproject.dto.LoaiLopHocDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.yogacenterproject.dao.LoaiLopHocDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        %>
        <link href="css/signupCSS.css" rel="stylesheet" type="text/css"/>
        <link href="<%=url%>/Authorization/Admin/cssAdmin/image.css" rel="stylesheet" type="text/css"/>
        <meta charset="utf-8">
        <title>YogaCenter Admin</title>
        <link href="<%=url%>/Authorization/Admin/cssAdmin/image.css" rel="stylesheet" type="text/css"/>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">
        <link href="<%=url%>/Authorization/Admin/cssAdmin/newCascadeStyleSheet.css" rel="stylesheet" type="text/css"/>
        <script class="u-script" type="text/javascript" src="<%=url%>/Authorization/Admin/jsAdmin/home2.js" defer=""></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script class="u-script" type="text/javascript" src="<%=url%>/Authorization/Admin/jsAdmin/home1.js" defer=""></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
        <title>Create Class Type</title>
    </head>
    <body>
        <style>
            #active-element{
                background: #1d4f71;
                color: #fff;

            }
        </style>
        <div class="Controller">
            <div class="wrapper">
                <%@include file="../NavComponents.jsp" %>
            </div>
            <div class="container py-5 h-100">
                <form action="<%=url%>/TrainerController" method="POST" enctype="multipart/form-data">


                    <div class="row justify-content-center align-items-center h-100">
                        <div class="col-12 col-lg-9 col-xl-7">
                            <div class="card shadow-2-strong card-registration" style="border-radius: 15px;">
                                <div class="card-body p-4 p-md-5">
                                    <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Create Trainer</h3>

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
                                            <div class="form-outline">
                                                <div class="input-container">
                                                    <input type="number" id="Salary" name="Salary"  min="0" step="0.5">
                                                    <span class="currency">x1.000.000VND</span>
                                                    <label class="form-label" for="Salary">Salary</label>
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
                                                <label class="form-label" for="confirmPassword">Repeat Password</label>
                                                <span id='message'></span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6 mb-4">

                                            <div class="form-outline">
                                                <input type="text" id="Ho" class="form-control form-control-lg" name="Ho" />
                                                <label class="form-label" for="Ho">Last Name</label>
                                            </div>

                                        </div>
                                        <div class="col-md-6 mb-4">

                                            <div class="form-outline">
                                                <input type="text" id="Ten" class="form-control form-control-lg" name="Ten" />
                                                <label class="form-label" for="Ten">First Name</label>
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
                                        <% LoaiLopHocDAO loaiLopHocDAO = new LoaiLopHocDAO();
                                            List<LoaiLopHocDTO> listLoaiLopHoc = new ArrayList<>();
                                            listLoaiLopHoc = loaiLopHocDAO.getAllLoaiLopHoc();
                                        %>

                                        <div class="col-md-12 mb-4">

                                            <h6 class="mb-2 pb-1">Managable Class Type</h6>
                                            <% for (LoaiLopHocDTO x : listLoaiLopHoc) {%>
                                            <div class="form-check form-check-inline">
                                                <input class="form-check-input" type="radio" name="loaiLopHoc" id="<%=x.getMaLoaiLopHoc()%>"
                                                       value="<%=x.getMaLoaiLopHoc()%>" checked/>
                                                <label class="form-check-label" for="<%=x.getMaLoaiLopHoc()%>"><%=x.getTenLoaiLopHoc()%></label>
                                            </div>
                                            <% }%>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-6 mb-4 pb-2">

                                            <div class="form-outline">
                                                <input type="email" id="emailAddress"   class="form-control form-control-lg" name="email" required="required" />
                                                <label class="form-label" for="emailAddress">Email</label>
                                            </div>
                                            <div style="color: red; font-weight: BOLD">
                                                <% String errorMessageEmail = (String) request.getAttribute("errorMessageEmail"); %>
                                                <% if (errorMessageEmail != null) {%> <%= errorMessageEmail%> <% }%>
                                            </div>
                                        </div>
                                        <div class="col-md-6 mb-4 pb-2">

                                            <div class="form-outline">
                                                <input type="tel" placeholder="xxx-xxx-xxxx" pattern="[0-9]{10}" id="phoneNumber" class="form-control form-control-lg" name="phoneNumber" required="required" />
                                                <label class="form-label" for="phoneNumber">Phone Number</label>
                                            </div>

                                        </div>
                                    </div>
                                    <div class="row">
<!--                                        <div class="col-md-6 mb-4">
                                            <input type="file" id="fileInput" name="Thumbnail"  onchange="addThumbnailImage(this)">
                                            <input type="hidden" id="Thumbnails" name="Thumbnails"  >
                                            <label class="form-label" for="Thumbnail">Profile Picture</label>
                                            <div id="previewThumb" class="previewThumb"></div>
                                        </div>-->
                                    </div>
                                    <div class="mb-1">


                                        <div class="mt-4 pt-2">
                                            <input class="btn btn-outline-danger" type="submit" value="Submit" id="submit"  />
                                            <input type="hidden" name="action" value="addTrainer" />
                                        </div

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

            </div>
        </form>
    </div>
</body>
<script>
    
      birthdayDate.max = new Date().toISOString().split("T")[0];
    
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
//        var images = []; // Mảng chứa danh sách ảnh tải lên
//        var imagesValues = [];
//        function addImage(fileInput) {
//            var files = fileInput.files;
//            for (var i = 0; i < files.length; i++) {
//                var file = files[i];
//                var reader = new FileReader();
//
//                // Đọc file ảnh
//                reader.onload = (function (file) {
//                    return function (e) {
//                        var imgData = e.target.result;
//                        images.push(imgData); // Thêm dữ liệu ảnh vào mảng
//                        var imgElement = document.createElement('img');
//                        imgElement.className = 'imageStyle';
//                        imgElement.src = imgData;
//                        var deleteButton = document.createElement('button');
//                        deleteButton.className = 'deleteButton';
//                        deleteButton.textContent = 'Xóa';
//                        deleteButton.addEventListener('click', function () {
//                            var imageIndex = images.indexOf(imgData);
//                            if (imageIndex !== -1) {
//                                images.splice(imageIndex, 1); // Xóa ảnh khỏi mảng
//                                var imageContainer = this.parentNode;
//                                document.getElementById('listImage').value = images.join(" ");
//                                imageContainer.parentNode.removeChild(imageContainer); // Xóa cả container chứa ảnh và nút xóa khỏi giao diện
//                            }
//                        });
//                        var imageContainer = document.createElement('div');
//                        imageContainer.className = 'imageListStyle';
//                        imageContainer.appendChild(imgElement);
//                        imageContainer.appendChild(deleteButton);
//                        document.getElementById('preview').appendChild(imageContainer); // Hiển thị ảnh và nút xóa trên giao diện
//
//                        // Update the hidden input value with the image data
//                        document.getElementById('listImage').value = images.join(" ");
//                    };
//                })(file);
//
//                reader.readAsDataURL(file);
//            }
//        }
//
//
//
    function addThumbnailImage(fileInput) {
        var files = fileInput.files;

        // Clear the previewThumb container
        var previewThumb = document.getElementById('previewThumb');
        previewThumb.innerHTML = '';

        for (var i = 0; i < files.length; i++) {
            var file = files[i];
            var reader = new FileReader();

            // Đọc file ảnh
            reader.onload = (function (file) {
                return function (e) {
                    var imgData = e.target.result;

                    var imgElement = document.createElement('img');
                    imgElement.className = 'imageStyle';
                    imgElement.src = imgData;
                    var deleteButton = document.createElement('button');
                    deleteButton.className = 'deleteButton';
                    deleteButton.textContent = 'Xóa';
                    deleteButton.addEventListener('click', function () {
                        var imageContainer = this.parentNode;
                        imageContainer.parentNode.removeChild(imageContainer); // Xóa cả container chứa ảnh và nút xóa khỏi giao diện
                        document.getElementById('fileInput').value = '';
                        document.getElementById('Thumbnails').value = '';
                    });
                    var imageContainer = document.createElement('div');
                    imageContainer.className = 'imageListStyle';
                    imageContainer.appendChild(imgElement);
                    imageContainer.appendChild(deleteButton);
                    previewThumb.appendChild(imageContainer); // Hiển thị ảnh và nút xóa trên giao diện
                    document.getElementById('Thumbnails').value = imgData;
                };
            })(file);

            reader.readAsDataURL(file);
        }
    }
    const navMenu = document.querySelector("nav");

    // Find the desired element and assign it the "active" id
    const blogLiElement = navMenu.querySelector("#Trainer");
    if (blogLiElement) {
        const ulElement = blogLiElement.querySelector("ul");
        if (ulElement) {
            ulElement.id = "active";
        }
    }

    $(document).ready(function () {
        $('.sub-menu ul#active').show();
        $('li#active').find(".right").toggleClass("fa-caret-up fa-caret-down");
    });

    $('.sub-menu ul').hide();

    $(".sub-menu a").click(function () {
        $(this).parent(".sub-menu").children("ul").slideToggle("100");
        $(this).find(".right").toggleClass("fa-caret-up fa-caret-down");
    });
</script>
</html>
