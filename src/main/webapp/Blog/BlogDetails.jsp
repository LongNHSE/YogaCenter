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
                                        <c:if  test="${blogDetails.hocVienDTO.username!=null}">
                                            <label>${blogDetails.hocVienDTO.username}</label>
                                        </c:if>
                                        <c:if  test="${blogDetails.trainerDTO!=null}">
                                            <label>${blogDetails.trainerDTO.username}</label>
                                            
                                        </c:if>
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

                        <!--                        <section class="content-item" id="comments">
                                                    <div class="container">   
                                                        <div class="row">
                                                            <div class="col-sm-8">   
                                                                <form>
                                                                    <h3 class="pull-left">New Comment</h3>
                                                                    <button type="submit" class="btn btn-normal pull-right">Submit</button>
                                                                    <fieldset>
                                                                        <div class="row">
                                                                            <div class="col-sm-3 col-lg-2 hidden-xs">
                                                                                <img class="img-responsive" src="https://bootdey.com/img/Content/avatar/avatar1.png" alt="">
                                                                            </div>
                                                                            <div class="form-group col-xs-12 col-sm-9 col-lg-10">
                                                                                <textarea class="form-control" id="message" placeholder="Your message" required=""></textarea>
                                                                            </div>
                                                                        </div>  	
                                                                    </fieldset>
                                                                </form>
                        
                                                                <h3>Comments</h3>
                        
                                                                 Iterate over comments 
                        <c:forEach var="comment" items="${blogCmt}">
                            <div class="media">
                                <a class="pull-left" href="#"><img class="media-object" src="https://bootdey.com/img/Content/avatar/avatar1.png" alt=""></a>
                                <div class="media-body">
                                    <h4 class="media-heading">Hello</h4>
                                    <p>${comment.commentContent}</p>
                                    <ul class="list-unstyled list-inline media-detail pull-left">
                                        <li><i class="fa fa-calendar"></i>${comment.createDate}</li>
                                    </ul>
                                    <ul class="list-unstyled list-inline media-detail pull-right">
                                        <li class=""><a href="">Like</a></li>
                                        <li class=""><a href="">Reply</a></li>
                                    </ul>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    </section>                            -->

                    </div>


                    <div class="col-lg-4 m-15px-tb blog-aside">
                        <div class="widget widget-latest-post">
                            <div class="widget-title">
                                <h3>Latest Post</h3>
                            </div>
                            <div class="widget-body">
                                <c:forEach items="${requestScope.blogLatest}" var="blog">
                                    <div class="latest-post-aside media">
                                        <div class="lpa-left media-body">
                                            <div class="lpa-title">
                                                <h5><a href="<%= baseUrl%>/BLogController?returnID=${blog.getMaBlog()}&action=showDetails">${blog.getTitle()}</a></h5>
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
                                <h3>Category</h3>
                            </div>  
                            <div class="single-category">
                                <ul class="list-unstyled">
                                    <c:forEach items="${blogCate}" var="cate" varStatus="status">                                     
                                        <li><a href="<%=baseUrl%>/BLogController?returnID=${cate.maCate}&action=showBlogCategory" " title="">${cate.tenCate} 
                                            </c:forEach>                               
                                </ul>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>

    </body>
    <style>
        .single-category li{
            padding: 10px;
        }
        .single-category a{
            text-decoration: none;
            color: black;

        }
        .single-category a:hover{
            color: #ff0000;

        }
    </style>
</html>