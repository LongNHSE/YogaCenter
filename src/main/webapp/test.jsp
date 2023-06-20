<%-- 
    Document   : test
    Created on : Jun 19, 2023, 4:07:41 PM
    Author     : Oalskad
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<html>
    <head>
        <title>Tải lên ảnh</title>
    </head>
    <link href="image.css" rel="stylesheet" type="text/css"/>
    <body>
        <h1>Tải lên ảnh</h1>
        <form id="uploadForm" action="<%= url%>/ImageController" enctype="multipart/form-data" method="POST">
            <input type="file" id="fileInput" name="fileInput"  onchange="addImage(this)">
            <input type="text" id="listImage" name="listImage"> <!-- Hidden input for listImage parameter -->
            <input type="submit" value="Tải lên">
            <input type="hiden" value="inserImg" name="action">
        </form>
        <div id="preview" class="preview"></div>

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
    </body>
</html>
