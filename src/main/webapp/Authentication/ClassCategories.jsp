<%-- 
    Document   : ClassCategories
    Created on : Jun 13, 2023, 3:36:32 PM
    Author     : devli
--%>
<%
    String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Yogasan</title>
        <link href="<%=url%>/css/ClassCategoriesStyles.css" rel="stylesheet" type="text/css"/>    

    </head>
    <body>
        <!--navbar : Start-->
        <jsp:include page="../Components/headerComponent.jsp" />       
        <!--navbar: End-->


        <div class="container " style="margin-top:15px">
            <h1 class="text-center text-muted-h1">Peace of Mind</h1>
            <div class="row">
                <c:forEach items="${listCate}" var="a">
                    <div class="col-xs-6 col-md-4">
                            <div class="classCate tumbnail thumbnail-3" style="border: 2px solid #ccc; border-radius: 10px; padding: 10px;margin: 20px 20px;">
                                <c:forEach items="${a.image}" var ="imageData">
                                    <a href="ClassController?returnID=${a.getMaLoaiLopHoc()}">
                                        <c:if test="${not empty imageData.tenAnh and imageData.tenAnh.equalsIgnoreCase('THUMBNAIL')}">
                                            <img src="data:image/jpeg;base64,${imageData.image}" alt="" style="width: 100%; height: 100%;">
                                        </c:if>

                                    </a>
                                </c:forEach>
                                <div class="caption text-center">
                                    <h2 ><a href="#" style="text-decoration: none; color: #333;">${a.getTenLoaiLopHoc()}</a></h2>
                                    <span class="price"></span
                                    <input type="hidden" value="${a.getMaLoaiLopHoc()}" name="returnID">
                                    <form action="<%=url%>/ClassController" method="GET">
                                      <input type="hidden" name="returnID" value="${a.getMaLoaiLopHoc()}" />
                                      <input type="hidden" name="action" value="showDetails" />
                                      <button class="button" type="submit">Details</button>
                                    </form>

                                </div>
                            </div>

                    </div>       
          </c:forEach>
      </div>
      </div>
       <!--Container: End-->
         
      </body>

</html>
