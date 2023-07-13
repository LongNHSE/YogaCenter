<%-- 
    Document   : BlogDetails
    Created on : Jul 4, 2023, 9:14:03 PM
    Author     : devli
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.mycompany.yogacenterproject.dao.BlogDAO" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>       
        <link href="<%=baseUrl%>/BlogDetail.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <jsp:include page="../Components/headerComponent.jsp" /> 
        <div class="blog-single gray-bg">
            <div class="container">
                <div class="row align-items-start">
                    <div class="col-lg-8 m-15px-tb">
                        <article class="article">
                            <div class="article-img">
                                <img src="data:image/jpeg;base64,${blogImgDetails.image}" style="width: 100%; object-fit: cover"title alt>
                            </div>
                            <div class="article-title">
                                <h2 class="">${blogDetails.title}</h2>
                                <div class="media">
                                    <div class="avatar">
                                        <img src="https://img.freepik.com/free-vector/man-meditating-with-flat-design_23-2147855145.jpg?w=826&t=st=1688749455~exp=1688750055~hmac=48facc0881188275dd2ef67632298bb734903e78636e4623d90d4437e01eaf74" title alt>
                                    </div>
                                    <div class="media-body">
                                        <label>${blogDetails.maHV}</label>
                                        <span>${blogDetails.date}</span>
                                    </div>
                                </div>
                            </div>
                            <div class="article-content">
                                <p style="color:#545554; font-size: 20px" class="blogContent">${blogDetails.content}</p>
                            </div>
                            <!--        <div class="nav tag-cloud">
                                    <a href="#">Design</a>
                                    <a href="#">Development</a>
                                    <a href="#">Travel</a>
                                    <a href="#">Web Design</a>
                                    <a href="#">Marketing</a>
                                    <a href="#">Research</a>
                                    <a href="#">Managment</a>
                                    </div>-->
                        </article>                    

                                </div>


                                <div class="col-lg-4 m-15px-tb blog-aside">
                                    <div class="widget widget-latest-post">
                                        <div class="widget-title">
                                            <h3>Latest Post</h3>
                                        </div>
                                        <div class="lpa-right">
                                                <img src="data:image/jpeg;base64,{BlogImageDAO.getImagesDataByBlogID(${blog.getMaBlog()})}" title alt>
                                        <div class="widget-body">
                                            <c:forEach items="${requestScope.blogLatest}" var="blog">
                                                <div class="latest-post-aside media">
                                                    <div class="lpa-left media-body">
                                                        <div class="lpa-title">
                                                            <h5><a href="#">${blog.getTitle()}</a></h5>
                                                        </div>
                                                        <div class="lpa-meta">
                                                            <a class="name" href="#">
                                                                ${blog.maHV}
                                                            </a>
                                                            <a class="date" href="#">
                                                                ${blog.date}
                                                            </a>
                                                        </div>
                                                    </div>
                                                    <div class="lpa-right">
                                                        <a href="#">

                                                            <img src="data:image/jpeg;base64,${BlogImageDAO.getImageByBlogID(blog.getMaBlog())}" title alt>
                                                        </a>
                                                    </div>
                                                </div>
                                            </c:forEach>            
                                        </div>
                                    </div>


                        <div class="widget widget-tags">
                            <div class="widget-title">
                                <h3>Latest Categories</h3>
                            </div>
                            <div class="widget-body">
                                <div class="nav tag-cloud">
                                    <a href="#">Design</a>
                                    <a href="#">Development</a>
                                    <a href="#">Travel</a>
                                    <a href="#">Web Design</a>
                                    <a href="#">Marketing</a>
                                    <a href="#">Research</a>
                                    <a href="#">Managment</a>
                                    <div class="widget widget-tags">
                                        <div class="widget-title">
                                            <h3>Category</h3>
                                        </div>
                                        <div class="single category">
                                            <ul class="list-unstyled">
                                                <c:forEach items="${blogCate}" var="cate" varStatus="status">                                     
                                                    <li><a href="<%= baseUrl%>/BLogController?returnID=${cate.maCate}&action=showBlogCategory" " title="">${cate.tenCate} 
                                                        </c:forEach>                               
                                            </ul>
                                        </div>
                                </div>
                            </div>
                    </div>
                </div>

                </body>

                </html>