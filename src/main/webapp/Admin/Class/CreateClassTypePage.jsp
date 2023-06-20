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
        <link href="css/signupCSS.css" rel="stylesheet" type="text/css"/>
        <link href="image.css" rel="stylesheet" type="text/css"/>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Class Type</title>
    </head>
    <body>
        <section class="vh-100 gradient-custom">
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
                                                <textarea name="description" id="description" style="font-family:sans-serif;font-size:0.5cm; width: 600px ; height: 100px"></textarea>
                                            </div>
                                        </div>
                                        <label class="form-label" for="description">Description</label>

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
        </section>
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
    </script>
</html>
