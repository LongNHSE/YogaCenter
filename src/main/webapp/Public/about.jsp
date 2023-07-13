<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Yogasan</title>
        <link href="<%=url%>/css/homeStyle.css" rel="stylesheet" type="text/css"/>
    </head>

    <body>
        <!-- header section start -->
        <jsp:include page="../Components/headerComponent.jsp" />
       
        <!-- header section end -->
  <!-- about section start -->
  <div class="about_section layout_padding">
    <div class="container">
      <div class="about_main">
        <h1 class="about_text">Về Yoga San</h1>
        <p class="ipsum_text"> Yoga San là một trung tâm yoga hàng đầu với đội ngũ giảng viên chuyên nghiệp và các khóa học đa dạng phù hợp với mọi đối tượng. Chúng tôi tập trung vào sự phát triển toàn diện của học viên, từ việc rèn luyện cơ thể linh hoạt đến trạng thái tĩnh lặng của tâm hồn.</p>
         <p class="ipsum_text">Với phương pháp giảng dạy tận tâm và chuẩn mực, chúng tôi đảm bảo bạn sẽ có một trải nghiệm yoga an lành và bổ ích tại Yoga San. Hãy đến và khám phá thế giới của yoga cùng chúng tôi và khám phá tiềm năng tối đa của bạn. </p>

      </div>
      <div class="about_bt_main">
        <div class="about_bt"><a href="<%=url%>/BLogController?action=showBlogs">Discover our Blogs</a></div>
        <div class="about_bt"><a href="<%= url%>/ClassController?action=classes">Discover our Class</a></div>
            <c:if test = "${sessionScope.hocVienDTO == null && sessionScope.trainerDTO == null}">
                      <div class="about_bt"><a href="<%=url%>/Public/signin.js">Join Us</a></div>         

            </c:if>        
      </div>
    </div>
  </div>
  <!-- about section end -->
                  <jsp:include page="../Components/footerComponent.jsp" />        
  </script>
</body>

</html>