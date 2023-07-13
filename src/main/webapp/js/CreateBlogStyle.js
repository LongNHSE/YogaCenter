/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
          function addThumbnailImage(fileInput) {
                    var files = fileInput.files;
                    var previewThumb = document.getElementById('previewThumb');
                    previewThumb.innerHTML = '';
                    var count = 0; // Biến đếm

                    for (var i = 0; i < files.length; i++) {
                      var file = files[i];
                      var reader = new FileReader();

                      reader.onload = (function (file) {
                        return function (e) {
                          var imgData = e.target.result;
                          var imgElement = document.createElement('img');
                          imgElement.className = 'img-thumbnail';
                          imgElement.src = imgData;
                          imgElement.onload = function () {

                          };

                          var deleteButton = document.createElement('button');
                          deleteButton.className = 'deleteButton';
                          deleteButton.textContent = 'Remove';
                          deleteButton.addEventListener('click', function () {
                            var imageContainer = this.parentNode;
                            imageContainer.parentNode.removeChild(imageContainer);
                            document.getElementById('fileInput').value = '';
                            document.getElementById('Thumbnails').value = '';
                          });
                        var fileInput = document.getElementById('fileInput');
                        fileInput.classList.add('hide');
                          var imageContainer = document.createElement('div');
                          imageContainer.className = 'imageListStyle';
                          imageContainer.id = 'imageContainer_' + count; // Tạo id duy nhất
                          count++; // Tăng biến đếm

                          imageContainer.appendChild(imgElement);
                          imageContainer.appendChild(deleteButton);
                          previewThumb.appendChild(imageContainer);
                          document.getElementById('Thumbnails').value = imgData;
                        };
                      })(file);

                      reader.readAsDataURL(file);
                    }
                  }

            // Lấy tham chiếu đến các phần tử input và textarea
            var titleInput = document.querySelector('textarea[name="title"]');
            var contentTextarea = document.querySelector('textarea[name="content"]');

            // Xóa nội dung khi nút "Cancel" được nhấn
            document.querySelector('.button-blog-cancel').addEventListener('click', function() {
                event.preventDefault(); // Ngăn chặn hành động mặc định của nút submit
              titleInput.value = '';
              contentTextarea.value = '';
            });


            document.getElementById('submitButton').addEventListener('click', function(event) {
                var fileInput = document.getElementById('fileInput');
                var titleInput = document.getElementById('titleInput');
                var contentInput = document.getElementById('contentInput');
                var alertMessage = document.getElementById('alertMessage');
                var progressBar = document.getElementById('progressBar');
                var headerNav = document.querySelector('.header-nav');
                if (!fileInput.value || !titleInput.value || !contentInput.value) {
                    headerNav.style.marginBottom = '0'; // Xóa margin-bottom của header-nav                    
                    event.preventDefault(); // Ngăn chặn việc submit nếu thông tin không đủ
                    alertMessage.style.display = 'block'; // Hiển thị thanh alert

                    // Tự động ẩn thanh alert sau 3 giây
                    setTimeout(function() {
                        alertMessage.style.display = 'none';
                    }, 3000);

                    // Chạy thanh thời gian trong 3 giây
                    progressBar.style.width = '100%';
                    setTimeout(function() {
                        progressBar.style.width = '0%';
                    }, 3000);
                }
            });

