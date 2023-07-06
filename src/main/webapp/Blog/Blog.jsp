<%-- 
    Document   : Blog
    Created on : Jul 4, 2023, 9:13:55 PM
    Author     : devli
--%>
<%
     String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
      <head>
            <title>JSP Page</title>

            <link href="<%=baseUrl%>/BlogStyles.css" rel="stylesheet" type="text/css"/>
      </head>
      
      <body>
                         <jsp:include page="../Components/headerComponent.jsp" /> 
      <section>
            <div class="container">
                  <div class="text-center mb-5">
                  <h5 class=" h1 header-title">Blog</h5>
                  <h2 class="display-20 display-md-18 display-lg-16 header-subtitle">Welcome to the Yoga Center Blog</h2>
                  </div>
            <div class="row">
            <c:forEach items="${listBlog}" var="blog">
                  <div class="col-lg-4 col-md-6 mb-2-6 mb-4 mt-4">
                      <article class="card card-style2">
                          <!-- Display the blog image here if available -->
                          <c:set var="maxContentLength" value="100" />
                          <c:forEach items="${blog.image}" var="image">
                              <div class="card-img">
                                  <c:if test="${not empty image.tenAnh and image.tenAnh.equalsIgnoreCase('THUMBNAIL')}">
                                      <img class="rounded-top" src="data:image/jpeg;base64,${image.image}" style="width: 100%" alt="...">
                                  </c:if>
                              </div>
                          </c:forEach>
                          <div class="card-body">
                              <h1 class="blog-title"><a  class="blog-title-link"href="#!" >${blog.title}</a></h1>
                              <p class="display-30 blog-content">
                                    <%-- Kiểm tra độ dài nội dung --%>
                                    <c:choose>
                                        <%-- Nếu nội dung quá dài, rút ngắn lại --%>
                                        <c:when test="${fn:length(blog.content) > maxContentLength}">
                                            ${fn:substring(blog.content, 0, maxContentLength)}...
                                        </c:when>
                                        <%-- Nếu nội dung không quá dài, hiển thị toàn bộ --%>
                                        <c:otherwise>
                                            ${blog.content}
                                        </c:otherwise>
                                    </c:choose>
                              </p>                               <div class="card-ending">
                                    <div class="btn-more">
                                      <button class="custom-btn">
                                            More
                                      </button>                                   
                                    </div>
                                    <div>
                                          <span>Created by : <span class="text-primary">${blog.maHV}</span> </span> 
                                    </div>                      
                              </div>
                          </div>

                      </article>
                  </div>
      </c:forEach>                  
            </div>
            </div>
      </section>
      </body>
</html>
