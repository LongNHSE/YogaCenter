/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

  document.addEventListener("DOMContentLoaded", function() {
    var carousel = document.getElementById("myCarousel");
    var carouselItems = carousel.getElementsByClassName("carousel-item");

    // Tự động chạy carousel khi trang được tải
    carouselItems[0].classList.add("active");
    carousel.getElementsByClassName("carousel-indicators")[0].getElementsByTagName("li")[0].classList.add("active");

    // Trigger sự kiện "click" lên các nút điều hướng của carousel
    for (var i = 0; i < carouselItems.length - 1; i++) {
      carousel.getElementsByClassName("carousel-control-next")[0].click();
    }
  });



