<%-- 
    Document   : CreateClassTypePage
    Created on : Jun 9, 2023, 1:15:35 PM
    Author     : Oalskad
--%>
<%@page import="com.mycompany.yogacenterproject.dto.LopHocIMGDTO"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        %>
        <link href="<%=url%>/Authorization/Admin/cssAdmin/newCascadeStyleSheet.css" rel="stylesheet" type="text/css"/>
        <link href="<%=url%>/css/signupCSS.css" rel="stylesheet" type="text/css"/>
        <link href="<%=url%>/Authorization/Admin/cssAdmin/image.css" rel="stylesheet" type="text/css"/>
        <meta charset="utf-8">
        <title>YogaCenter Admin</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">
        <link href="newCascadeStyleSheet.css" rel="stylesheet" type="text/css"/>
        <script class="u-script" type="text/javascript" src="home2.js" defer=""></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script class="u-script" type="text/javascript" src="home1.js" defer=""></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
        <title>Create Class Type</title>
    </head>
    <body>

        <div class="Controller">
            <div class="wrapper">
                <%@include file="../NavComponents.jsp" %>
            </div>
            <c:set var="loaiLopHoc" value="${loaiLopHocDTO}"/>
            <div class="container py-5 h-100">
                <div class="row justify-content-center align-items-center h-100">
                    <div class="col-12 col-lg-9 col-xl-7">
                        <div class="card shadow-2-strong card-registration" style="border-radius: 15px;">
                            <div class="card-body p-4 p-md-5">
                                <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Create Class Type</h3>
                                <form action="<%=url%>/ClassController" method="POST" enctype="multipart/form-data">


                                    <div class="row">
                                        <div class="col-md-6 mb-4">

                                            <div class="form-outline">

                                                <input type="text" id="LoaiLopHoc" class="form-control form-control-lg" name="tenLoaiLopHoc" value="${loaiLopHoc.tenLoaiLopHoc}"/>
                                                <label class="form-label" for="LoaiLopHoc">Name of Class Type</label>


                                            </div>

                                        </div>
                                        <div class="col-md-6 mb-4">

                                            <div class="form-outline">
                                                <div class="input-container">
                                                    <input type="number" id="hocPhi" name="hocPhi"  min="0" step="0.1" value="${loaiLopHoc.getHocPhiWith3Number()}">
                                                    <span class="currency">x1.000.000VND</span>
                                                    <label class="form-label" for="hocPhi">Fee </label>
                                                </div>
                                            </div>


                                        </div>
                                    </div>
                                    <div class="row" style="margin-bottom: 50px">


                                        <div class="form-outline">
                                            <div class="align-self-xl-center">        
                                                <label for="title">Title:</label>
                                                <input type="text" id="title" name="title" value="${loaiLopHoc.descriptionDTO.title}"><br>
                                            </div>
                                        </div>
                                        <div class="form-outline">

                                            <div class="align-self-xl-center">        
                                                <textarea  class="input-style" name="description" id="contentInput" style="font-family:sans-serif;font-size:0.5cm; width: 600px ; height: 100px" placeholder="Start writing here..." >${loaiLopHoc.descriptionDTO.content}</textarea>
                                            </div>
                                        </div>

                                        <label class="form-label" for="description">Description</label>

                                    </div>
                                    <c:choose>
                                        <c:when test="${loaiLopHoc.image!=null}">
                                            <c:forEach items="${loaiLopHoc.image}" var="image">
                                                <c:if test="${image.tenAnh== 'THUMBNAIL'}">
                                                    <img id ="avatar" class="rounded-top" src="data:image/jpeg;base64,${image.image}" style="width: 100%; height: 200px; object-fit: cover; object-position: center;" alt="...">
                                                </c:if>
                                            </c:forEach>


                                        </c:when>    
                                        <c:otherwise>

                                            <img id ="avatar" class="rounded-top" src="https://t4.ftcdn.net/jpg/05/49/98/39/360_F_549983970_bRCkYfk0P6PP5fKbMhZMIb07mCJ6esXL.jpg" style="width: 100%; height: 200px; object-fit: cover; object-position: center;" alt="...">

                                        </c:otherwise>
                                    </c:choose>
                                    <div class="small font-italic text-muted mb-4">JPG or PNG no larger than 5 MB</div>

                                    <input class="browse-pic" type="file" id="fileInput" name="Banner" accept="image/png, image/gif, image/jpeg" onchange="addThumbnailImage(this)">


                                   
                                    <div class="row">




                                        <div id="preview" class="preview"></div>
                                        <div class="mb-1">
                                            <div class="mt-4 pt-2">
                                                <input type="hidden" name="action" value="UpdateClassType" />
                                                <input type="hidden" name="maLoaiLopHoc" value="${loaiLopHoc.maLoaiLopHoc}" />
                                                <input type="hidden" name="maDescription" value="${loaiLopHoc.maDescription}" />
                                                <input class="btn btn-outline-danger" type="submit" value="Update" id="submit"  />
                                            </div>
                                        </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <!-- ...existing code... -->


        <!-- ...existing code... -->

    </body>
    <script src="<%=url%>/js/CreateBlogStyle.js"></script>
    <script type="text/javascript" src="<%=url%>/libraries/ckeditor/ckeditor.js"></script>
    <script>
                                            CKEDITOR.replace('contentInput');
                                            CKEDITOR.editorConfig = function (config) {
                                                // Cấu hình ACF để cho phép thẻ <span> và không cho phép thẻ <p>
                                                config.allowedContent = 'span(*)';
                                                config.disallowedContent = 'p';
                                            };
    </script>
    <script>

        const navMenu = document.querySelector("nav");

        // Find the desired element and assign it the "active" id
        const blogLiElement = navMenu.querySelector("#Class");
        if (blogLiElement) {
            const ulElement = blogLiElement.querySelector("ul");
            if (ulElement) {
                ulElement.id = "active";
            }
        }
              

        function addThumbnailImage(fileInput) {
            var files = fileInput.files;

            // Clear the previewThumb container
            var previewThumb = document.getElementById('avatar');
            previewThumb.innerHTML = '';

            for (var i = 0; i < files.length; i++) {
                var file = files[i];
                var reader = new FileReader();

                // Đọc file ảnh
                reader.onload = (function (file) {
                    return function (e) {
                        var imgData = e.target.result;

                        var imgElement = document.createElement('img');
                        imgElement.className = 'banner';
                        previewThumb.src = imgData;
                        var deleteButton = document.createElement('button');
                        deleteButton.className = 'deleteButton';
                        deleteButton.textContent = 'Delete';
                        deleteButton.addEventListener('click', function () {
                            var imageContainer = this.parentNode;
                            imageContainer.parentNode.removeChild(imageContainer); // Xóa cả container chứa ảnh và nút xóa khỏi giao diện
                            document.getElementById('fileInput').value = '';
                            document.getElementById('Thumbnail').value = '';
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
            const uploadButton = document.getElementById('uploadButton');
            if (fileInput.files && fileInput.files[0]) {
                // Enable the button when a file is selected
                uploadButton.disabled = false;

                // You may add additional code here to display the selected image as a thumbnail
                // For example, you could add an <img> element to show a preview of the selected image.
            } else {
                // Disable the button if no file is selected
                uploadButton.disabled = true;
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
