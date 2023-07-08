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
                <div class="row">
                    <div class="col-md-9 mb40"">
                            <div class="text-center mb-5">
                            <h5 class=" h1 header-title">Blog</h5>
                            <h2 class="display-20 display-md-18 display-lg-16 header-subtitle">Welcome to the Yoga Center Blog</h2>
                            </div>
<!--                    <div class="row">
                    <c:forEach items="${listBlog}" var="blog">
                          <div class="col-lg-4 col-md-6 mb-2-6 mb-4 mt-4">
                              <div class="card-content"></div>
                              <article class="card card-style2">
                                   Display the blog image here if available 
                                  <c:set var="maxContentLength" value="100" />
                                  <c:forEach items="${blog.image}" var="image">
                                      <div class="card-img">
                                          <c:if test="${not empty image.tenAnh and image.tenAnh.equalsIgnoreCase('THUMBNAIL')}">
                                              <img class="rounded-top" src="data:image/jpeg;base64,${image.image}" style="width: 100%" alt="...">
                                          </c:if>
                                      </div>
                                  </c:forEach>
                                  <div class="card-body">
                                      <div class="title-container">                       
                                            <h5 class="blog-title"><a  class="blog-title-link"href="<%= baseUrl %>/BLogController?returnID=${blog.getMaBlog()}&action=showDetails" >${blog.title}</a></h5>
                                            <p class="display-30 blog-content">
                                                  <c:choose>
                                                      <c:when test="${fn:length(blog.content) > maxContentLength}">
                                                          ${fn:substring(blog.content, 0, maxContentLength)}...
                                                      </c:when>
                                                      <c:otherwise>
                                                          ${blog.content}
                                                      </c:otherwise>
                                                  </c:choose>
                                            </p>                               
                                            <div class="card-ending">
                                                  <div class="btn-more">
                                                      <form action="<%=baseUrl%>/BLogController">
                                                          <input type="hidden" name="returnID" value="${blog.getMaBlog()}">
                                                          <input type="hidden" name="action" value="showDetails">
                                                          <button class="custom-btn custom-form-btn">
                                                                More
                                                          </button>                                                
                                                      </form>

                                                  </div>
                                                  <div>
                                                        <span>Created by : <span class="text-primary">${blog.maHV}</span> </span> 
                                                  </div>                      
                                            </div>
                                     </div>       
                                  </div>

                              </article>
                          </div>
                            </c:forEach>                  
                    </div>                        -->
                    <div class="row">
                        <c:forEach items="${listBlog}" var="blog">
                            <div class="col-lg-6 col-md-6 mb-2-6 mb-4 mt-4">
                                <article class="card card-style2">
                                    <!-- Display the blog image here if available -->
                                    <c:set var="maxContentLength" value="100" />
                                    <c:forEach items="${blog.image}" var="image">
                                        <div class="card-img">
                                            <c:if test="${not empty image.tenAnh and image.tenAnh.equalsIgnoreCase('THUMBNAIL')}">
                                                <img class="rounded-top" src="data:image/jpeg;base64,${image.image}" style="width: 100%; height: 200px; object-fit: cover; object-position: center;" alt="...">
                                            </c:if>
                                        </div>
                                    </c:forEach>
                                    <div class="card-body d-flex flex-column justify-content-between">
                                        <div class="title-container">
                                            <h4 class="blog-title"><a class="blog-title-link" href="<%= baseUrl %>/BLogController?returnID=${blog.getMaBlog()}&action=showDetails">${blog.title}</a></h4>
                                        </div>
                                        <div class="content-container">
                                            <p class="display-30 blog-content">
                                                <c:choose>
                                                    <c:when test="${fn:length(blog.content) > maxContentLength}">
                                                        ${fn:substring(blog.content, 0, maxContentLength)}...
                                                    </c:when>
                                                    <c:otherwise>
                                                        ${blog.content}
                                                    </c:otherwise>
                                                </c:choose>
                                            </p>
                                        </div>
                                        <div class="card-ending">
                                            <div class="btn-more">
                                                <form action="<%=baseUrl%>/BLogController">
                                                    <input type="hidden" name="returnID" value="${blog.getMaBlog()}">
                                                    <input type="hidden" name="action" value="showDetails">
                                                    <button class="custom-btn custom-form-btn">More</button>
                                                </form>
                                            </div>
                                            <div>
                                                <span>Created by : <span class="text-primary">${blog.maHV}</span></span>
                                            </div>
                                        </div>
                                    </div>
                                </article>
                            </div>
                        </c:forEach>
                    </div>

                                                          
                    </div>
                    
                    <div class="col-md-3 mb40">
                        <h4 class="display-4">Categories</h4>
                        <ul class="list-unstyled categories">
                            <li><a href="#" class="display-5">Rent</a></li>
                            <li><a href="#" class="display-5">Sale</a></li>
                            <li class="active"><a href="#" class="display-5">Apartment</a>
                                <ul class="list-unstyled">
                                    <li><a href="#" class="display-6">Office</a></li>
                                    <li><a href="#" class="display-6">Godown</a></li>
                                    <li><a href="#" class="display-6">Garage</a></li>
                                </ul>
                            </li>
                            <li><a href="#" class="display-5">Top Rating</a></li>
                            <li><a href="#" class="display-5">Trending</a></li>
                            <li><a href="#" class="display-5">Newest Properties</a></li>
                        </ul>
                </div>

            </div>
      </section>
      </body>
</html>
