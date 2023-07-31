/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


    // Hàm chuyển đổi ngày/giờ thành giờ Việt Nam
    function convertToVietnameseTime(dateString) {
        // Tạo đối tượng Date từ chuỗi ngày/giờ truyền vào
        const date = new Date(dateString);

        // Chuyển đổi sang múi giờ UTC+7 (giờ Việt Nam)
        date.setUTCHours(date.getUTCHours() + 7);

        // Định dạng giờ Việt Nam (HH:mm:ss dd-MM-yyyy)
        const options = {
            timeZone: 'Asia/Ho_Chi_Minh',
            hour12: false,
            year: 'numeric',
            month: '2-digit',
            day: '2-digit',
            hour: '2-digit',
            minute: '2-digit',
            second: '2-digit',
        };
        const formattedDate = date.toLocaleString('en-GB', options);

        return formattedDate;
    }

    // Lấy danh sách các phần tử có class "comment-date"
    const commentDates = document.querySelectorAll('.comment-date');

    // Chuyển đổi và cập nhật giờ Việt Nam cho mỗi phần tử
    commentDates.forEach(commentDate => {
        const originalDate = commentDate.textContent.substr(2); // Bỏ đi dấu "- " phía trước ngày/giờ
        const vietnameseTime = convertToVietnameseTime(originalDate);
        commentDate.textContent = `- ${vietnameseTime}`; // Cập nhật lại ngày/giờ thành giờ Việt Nam
    });