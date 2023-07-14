/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
// Lấy tham chiếu đến phần tử .voucher-note
var voucherNote = document.querySelector('.voucher-note');

// Thiết lập timeout để ẩn phần tử sau 3 giây
setTimeout(function() {
    voucherNote.style.display = 'none';
}, 3000);

