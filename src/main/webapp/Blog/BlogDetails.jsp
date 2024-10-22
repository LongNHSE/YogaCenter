<%-- 
    Document   : BlogDetails
    Created on : Jul 4, 2023, 9:14:03 PM
    Author     : devli
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setTimeZone value="Asia/Ho_Chi_Minh" />
<fmt:setLocale value="vi_VN" />
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.mycompany.yogacenterproject.dao.BlogDAO" %>

<!DOCTYPE html>
<html>
    <head>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.bundle.min.js"></script>
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
                                        <c:if  test="${blogDetails.hocVienDTO.username!=null}">
                                            <img id ="avatar" class="rounded-top" src="data:image/jpeg;base64,${blogDetails.hocVienDTO.avatarDTO.image}" style="width: 100%; height: 200px; object-fit: cover; object-position: center;" alt="...">
                                        </c:if>
                                        <c:if  test="${blogDetails.trainerDTO!=null}">
                                            <img id ="avatar" class="rounded-top" src="data:image/jpeg;base64,${blogDetails.trainerDTO.avatarDTO.image}" style="width: 100%; height: 200px; object-fit: cover; object-position: center;" alt="...">

                                        </c:if>
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
                                <span style="color:#545554; font-size: 20px" class="blogContent">${blogDetails.content}</span>
                            </div>

                        </article>


                        <section class="content-item" id="comments">
                            <div class="container">
                                <h2 class="comments-title">Comments</h2>
                                <div class="divider"></div>                                
                                <div class="row">
                                    <c:if test = "${sessionScope.hocVienDTO != null || sessionScope.trainerDTO != null}">
                                        <div class="comment mt-4 text-justify float-left" style="border: none; width: 100%">
                                            <form action="<%=baseUrl%>/CommentController">

                                                <textarea id="commentTextArea" class="form-control" rows="2" placeholder="What are you thinking?"  name="comment" style="resize:none"></textarea>
                                                <div class="mar-top clearfix">
                                                    <div class=" btn-section"> 
                                                       <button class="cancel-btn" type="button" onclick="clearTextarea()">Cancel</button>
                                                     <button class= "submit-btn" type="submit"> Share</button>          
                                                    </div>
                                                   <input type="hidden" name="returnID" value="${blogDetails.maBlog}" />
                                                    <input type="hidden" name="action" value="postBlog" />                                                      
                                                </div>




                                            </form>
                                        </div>
                                    </c:if>

                                    <div>
                                        <c:forEach var="commentDTO" items="${requestScope.listComment}">
                                            <div class="comment mt-4 text-justify float-left comment-container">
                                                <div class="comment-header">
                                                    <c:if test="${commentDTO.hocVienDTO.username != null}">
                                                        <img src="data:image/jpeg;base64,${commentDTO.hocVienDTO.avatarDTO.image}" alt="" class="rounded-circle" width="40" height="40">
                                                        <h2 class="comment-author">${commentDTO.hocVienDTO.username}</h2>
                                                    </c:if>
                                                    <c:if test="${commentDTO.trainerDTO.username != null}">
                                                        <img src="data:image/jpeg;base64,${commentDTO.trainerDTO.avatarDTO.image}" alt="" class="rounded-circle" width="40" height="40">
                                                        <h2 class="comment-author">${commentDTO.trainerDTO.ten}</h2><p style="color: #953553; font-size: 16px;; font-weight: 600;margin-bottom: 10px; margin-left: 10px; ">(Trainer)</p>
                                                            </c:if>

                                                </div>
                                                <p class="comment-content">${commentDTO.noiDung}</p>
                                                

                                                <c:choose>

                                                    <c:when test="${sessionScope.hocVienDTO.maHV == commentDTO.hocVienDTO.maHV && sessionScope.hocVienDTO.maHV!=null  }">
                                                        <form action="<%=baseUrl%>/CommentController">

                                                            <button class="btn delete-btn" type="submit">X</button>
                                                            <input type="hidden" name="maComment" value="${commentDTO.maComment}" />
                                                            <input type="hidden" name="returnID" value="${blogDetails.maBlog}" />
                                                            <input type="hidden" name="action" value="deleteBlog" />
                                                        </form>
                                                    </c:when>

                                                    <c:when test="${sessionScope.trainerDTO.maTrainer == commentDTO.trainerDTO.maTrainer &&sessionScope.trainerDTO.maTrainer!=null}">
                                                        <form action="<%=baseUrl%>/CommentController">
                                                            <button class="btn delete-btn" type="submit">X</button>
                                                            <input type="hidden" name="maComment" value="${commentDTO.maComment}" />
                                                            <input type="hidden" name="returnID" value="${blogDetails.maBlog}" />
                                                            <input type="hidden" name="action" value="deleteBlog" />
                                                        </form>
                                                    </c:when>
                                                    <c:otherwise>

                                                    </c:otherwise>
                                                </c:choose>
                                            </div>
                                        </c:forEach>

                                    </div>
                                </div>
                        </section>     

                    </div>


                    <div class="col-lg-4 m-15px-tb blog-aside">
                        <div class="widget widget-latest-post">
                            <div class="widget-title">
                                <h3>Latest Post</h3>
                            </div>
                            <div class="widget-body">
                                <c:forEach items="${requestScope.blogLatest}" var="blog">
                                    <div class="latest-post-aside ">
                                        <div class="lpa-left media-body">
                                            <div class="lpa-title">
                                                <h5><a href="<%= baseUrl%>/BLogController?returnID=${blog.getMaBlog()}&action=showDetails">${blog.getTitle()}</a></h5>
                                            </div>
                                            <div class="lpa-meta">
                                                <a class="name" href="#">
                                                    ${blog.maHV}
                                                </a>
                                                <a class="date" href="#">
                                                    ${fn:substring(blog.date, 0, 19)}
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
    <script>
                                function clearTextarea() {
                    document.getElementById("commentTextArea").value = ""; // Gán giá trị rỗng cho textarea
                  }
    </script>
    <script src="<%=baseUrl%>/js/blogDetails.js">

        </html>