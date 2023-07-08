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


        <link href="<%=url%>/Blog/BlogCreateStyle.css" rel="stylesheet" type="text/css"/>

    </head>
    <body>
        <form action="<%=url%>/BLogController" method="post" enctype="multipart/form-data">
            <%@include  file="../Components/headerComponent.jsp" %>
            <div id="previewThumb" class="previewThumb">
            </div>
            <div class="Controller">
                <input type="file" id="fileInput" name="Banner" onchange="addThumbnailImage(this)">
                <input type="hidden" id="Thumbnails" name="Banner"  >
                <div class="blog">
                    <textarea type="text" class="title" name="title" placeholder="Blog title..."></textarea>
                    <textarea type="text" class="article" name="content" placeholder="Start writing here..."></textarea>
                    <input type="submit" value="submit">
                    <input type="hidden" name="action" value="CreateBlog">
                </div>
            </div>
        </form>
    </body>
    <script>
      



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
                        imgElement.className = 'banner';
                        imgElement.src = imgData;
                        var deleteButton = document.createElement('button');
                        deleteButton.className = 'deleteButton';
                        deleteButton.textContent = 'Xóa';
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
