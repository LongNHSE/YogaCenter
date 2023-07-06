<%-- 
    Document   : Blog
    Created on : Jul 4, 2023, 9:13:55 PM
    Author     : devli
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
      <head>
            <title>JSP Page</title>
            <link href="BlogStyles.css" rel="stylesheet" type="text/css"/>
      </head>
      
      <body>
        <!--navbar : Start-->
        <jsp:include page="../Components/headerComponent.jsp" />       
        <!--navbar: End-->
      <section>
            <div class="container">
                  <div class="text-center mb-5">
                  <h5 class=" h1 header-title">Blog</h5>
                  <h2 class="display-20 display-md-18 display-lg-16 header-subtitle">Welcome to the Yoga Center Blog</h2>
                  </div>
            <div class="row">
<!--                  <c:forEach items = "${requestScope.listBlog}" var="a">
                        

            <div class="col-lg-4 col-md-6 mb-2-6">
            <article class="card card-style2">
                  <c:forEach items="${a.image}" var="imageData">
                        <div class="card-img">
                                        <c:if test="${not empty imageData.tenAnh and imageData.tenAnh.equalsIgnoreCase('THUMBNAIL')}">
                                            <img src="data:image/jpeg;base64,${imageData.image}" alt="" style="width: 100%; height: 100%;">
                                        </c:if>
                        <img class="rounded-top" src="https://www.bootdey.com/image/350x280/6A5ACD/000000" alt="...">
                        </div>                        
                  </c:forEach>

            <div class="card-body">
            <h3 class="h5"><a href="#!">${a.getTitle()}</a></h3>
            <p class="display-30">${a.getContent()}</p>
            <a href="#!" class="read-more">read more</a>
            </div>
            <div class="card-footer">
            <ul>
            <li><a href="#!"><i class="fas fa-user"></i>Brittany Hucks</a></li>
            <li><a href="#!"><i class="far fa-comment-dots"></i><span>26</span></a></li>
            </ul>
            </div>
            </article>
            </div>
                   </c:forEach>    -->
      <c:forEach items="${listBlog}" var="blog">
          <div class="col-lg-4 col-md-6 mb-2-6">
              <article class="card card-style2">
                  <!-- Display the blog image here if available -->
                  <c:forEach items="${blog.image}" var="image">
                      <div class="card-img">
                          <c:if test="${not empty image.tenAnh and image.tenAnh.equalsIgnoreCase('THUMBNAIL')}">
                              <img class="rounded-top" src="data:image/jpeg;base64,${image.image}" style="width: 100%; height: 100%;" alt="...">
                          </c:if>
                      </div>
                  </c:forEach>
                  <div class="card-body">
                      <h3 class="h5"><a href="#!">${blog.title}</a></h3>
                      <p class="display-30">${blog.content}</p>
                      <a href="#!" class="read-more">read more</a>
                  </div>
                  <div class="card-footer">
                      <ul>
                          <li><a href="#!"><i class="fas fa-user"></i>${blog.maHV}</a></li>
                          <!-- Display the number of comments or any other information -->
                      </ul>
                  </div>
              </article>
          </div>
      </c:forEach>                  
            </div>
            </div>
      </section>
      </body>
</html>
