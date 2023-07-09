<%-- 
    Document   : CreateClassTypePage
    Created on : Jun 9, 2023, 1:15:35 PM
    Author     : Oalskad
--%>

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
                <nav class='animated bounceInDown bg-dark'>
                    <ul>
                        <li><a href='<%=url%>/Authorization/Admin/AdminHomepage.jsp'>Profile</a></li>
                        <li id="active" class='sub-menu'><a href='#settings'><i class="fa-solid fa-school"></i>Class<div class='fa fa-caret-down right'></div></a>
                            <ul id="active">
                                <li ><a href='<%=url%>/AdminController?action=listLopHoc&page=1'>List Class</a></li>
                                <li ><a href='<%=url%>/AdminController?action=listClassUnassigned'>List Class Unassigned</a></li>
                                <li ><a href='<%=url%>/ClassController?action=CheckEmptyRoom'>Create Class</a></li>
                                <li><a href='<%=url%>/AdminController?action=ViewSchedule'>View Schedule</a></li>
                                <li id="active-element"><a href='<%=url%>/Authorization/Admin/Class/CreateClassTypePage.jsp'>Create Class Type</a></li>
                            </ul>
                        </li>
                        <li class='sub-menu'><a href='#message'>Trainee<div class='fa fa-caret-down right'></div></a>
                            <ul>
                                <li><a href="<%=url%>/AdminController?action=listHocVien">List Trainee</a></li>
                                <li><a href='#settings'>Submit a Ticket</a></li>
                                <li><a href='#settings'>Network Status</a></li>
                            </ul>
                        </li>
                        <li class='sub-menu'><a href='#message'>Trainer<div class='fa fa-caret-down right'></div></a>
                            <ul>
                                <li><a href="<%=url%>/AdminController?action=listHocVien">List Trainer</a></li>
                                <li><a href='<%=url%>/Authorization/Admin/Trainer/AddTrainer.jsp'>Add Trainer</a></li>
                                <li><a href='#settings'>Network Status</a></li>
                            </ul>
                        </li>
                        <li class='sub-menu'><a href='#message'>Application<div class='fa fa-caret-down right'></div></a>
                            <ul>
                                <li><a href="<%=url%>/AdminController?action=listHocVien">List Trainer</a></li>
                                <li><a href="">Add Trainer</a></li>
                                <li><a href='#settings'>Network Status</a></li>
                            </ul>
                        </li>
                        <li id="Blog" class='sub-menu'><a href='#message'>Blog<div class='fa fa-caret-down right'></div></a>
                            <ul>
                                <li><a href="<%=url%>/BlogAdminController?action=ViewListBlogUnapprove">List Blog Unapproved</a></li>
                                <li><a href="">List Blog Approved</a></li>
                                <li><a href='#settings'>Network Status</a></li>
                            </ul>
                        </li>
                        <li><a href='<%=url%>/LoginController?action=adminLogout'>Logout</a></li>
                    </ul>
                </nav>
            </div>

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

                                                <input type="text" id="LoaiLopHoc" class="form-control form-control-lg" name="tenLoaiLopHoc" required="required"/>
                                                <label class="form-label" for="LoaiLopHoc">Ten Loai Lop Hoc</label>
                                                <div style="color: red; font-weight: BOLD">
                                                    <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
                                                    <% if (errorMessage != null) {%> <%= errorMessage%> <% }%>
                                                </div>

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


                                        </div>
                                    </div>
                                    <div class="row" style="margin-bottom: 50px">


                                        <div class="form-outline">
                                            <div class="align-self-xl-center">        
                                                <label for="title">Title:</label>
                                                <input type="text" id="title" name="title" required><br>
                                            </div>
                                        </div>
                                        <div class="form-outline">
                                            <div class="align-self-xl-center">        
                                                <textarea name="description" id="description" style="font-family:sans-serif;font-size:0.5cm; width: 600px ; height: 100px"></textarea>
                                            </div>
                                        </div>
                                        <label class="form-label" for="description">Description</label>

                                    </div>
                                    <div class="row">
                                        <div class="col-md-6 mb-4">
                                            <input type="file" id="fileInput" name="Thumbnail"  onchange="addThumbnailImage(this)">
                                            <input type="hidden" id="Thumbnails" name="Thumbnails"  >
                                            <label class="form-label" for="Thumbnail">Thumbnail</label>
                                            <div id="previewThumb" class="previewThumb"></div>
                                        </div>
                                    </div>
                                    <div class="row">


                                        <div class="col-md-6 mb-4">
                                            <input type="file" id="fileInput" name="fileInput"  onchange="addImage(this)">
                                            <input type="hidden" id="listImage" name="listImage"> <!-- Hidden input for listImage parameter -->
                                        </div>


                                        <div id="preview" class="preview"></div>
                                        <div class="mb-1">
                                            <div class="mt-4 pt-2">
                                                <input type="hidden" name="action" value="CreateClassType" />
                                                <input class="btn btn-outline-danger" type="submit" value="Submit" id="submit"  />
                                            </div>
                                        </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </body>
    <script>
        var images = []; // Mảng chứa danh sách ảnh tải lên
        var imagesValues = [];
        function addImage(fileInput) {
            var files = fileInput.files;
            for (var i = 0; i < files.length; i++) {
                var file = files[i];
                var reader = new FileReader();

                // Đọc file ảnh
                reader.onload = (function (file) {
                    return function (e) {
                        var imgData = e.target.result;
                        images.push(imgData); // Thêm dữ liệu ảnh vào mảng
                        var imgElement = document.createElement('img');
                        imgElement.className = 'imageStyle';
                        imgElement.src = imgData;
                        var deleteButton = document.createElement('button');
                        deleteButton.className = 'deleteButton';
                        deleteButton.textContent = 'Xóa';
                        deleteButton.addEventListener('click', function () {
                            var imageIndex = images.indexOf(imgData);
                            if (imageIndex !== -1) {
                                images.splice(imageIndex, 1); // Xóa ảnh khỏi mảng
                                var imageContainer = this.parentNode;
                                document.getElementById('listImage').value = images.join(" ");
                                imageContainer.parentNode.removeChild(imageContainer); // Xóa cả container chứa ảnh và nút xóa khỏi giao diện
                            }
                        });
                        var imageContainer = document.createElement('div');
                        imageContainer.className = 'imageListStyle';
                        imageContainer.appendChild(imgElement);
                        imageContainer.appendChild(deleteButton);
                        document.getElementById('preview').appendChild(imageContainer); // Hiển thị ảnh và nút xóa trên giao diện

                        // Update the hidden input value with the image data
                        document.getElementById('listImage').value = images.join(" ");
                    };
                })(file);

                reader.readAsDataURL(file);
            }
        }



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
