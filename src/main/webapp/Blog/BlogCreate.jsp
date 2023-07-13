<%-- 
    Document   : CreateClassTypePage
    Created on : Jun 9, 2023, 1:15:35 PM
    Author     : Oalskad
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        %>


        <link href="<%=url%>/Blog/BlogCreateStyle.css" rel="stylesheet" type="text/css"/>

    </head>
    <body>
                    <%@include  file="../Components/headerComponent.jsp" %>
                    
                    <!--AlertMessage-->                                       
                    <div id="alertMessage" class="alert-message">
                        Vui lòng điền đầy đủ thông tin
                        <div id="progressBar" class="progress-bar"></div>
                    </div>
         
                    <div class="container">
                        <h1 class="blog-main-head">Create Your Own Blog</h1>
                         <div class="row align-items-start">
                             <div class="col-lg-8 m-15px-tb">
                                 <form action="<%=url%>/BLogController" method="post" enctype="multipart/form-data">                            
                                 <article class="article">

                                                   <div style="height:250px"id="previewThumb" class="previewThumb">

                                                   </div>
                                                 <div class="image-input">
                                                         <input class="input-style"  type="file" id="fileInput" name="Banner" onchange="addThumbnailImage(this)">                
                                                 </div>


                                                     <div class="Controller">

                                                       <input type="hidden" id="Thumbnails" name="Banner"  >
                                                       <div class="blog">
                                                           <div class="textarea-container">
                                                           <div class="input-title">
                                                             <textarea   class="input-style " id="titleInput" style="text-align: center; width: 100%; margin: 20px 0px; height: 50px"type="text" class="title" name="title" placeholder="Blog title"></textarea>                                                      
                                                           </div>
                                                               <div>
                                                                     <textarea   class="input-style" id="contentInput" style="height: 300px; width: 100%" type="text" class="article" name="content" placeholder="Start writing here..."></textarea>                                                          
                                                               </div>
                                                             </div>
                                                           <div class="button-in-blog">
                                                               <button class="button-blog-cancel"> 
                                                                   Cancel
                                                                 </button>
                                                                     <button class="button-blog" id="submitButton">
                                                                            <input type="hidden" name="action" value="CreateBlog">
                                                                            Submit
                                                                    </button>                                                           
                                                           </div>

                                                       </div>
                                                   </div>

                                 </article>
                                    </form>


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
                                         <h3>Latest Tags</h3>
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
                                         </div>
                                     </div>
                                 </div>

                             </div>
                         </div>

                     </div>     
    </body>
            <script src="<%=url%>/js/CreateBlogStyle.js"></script>
</html>
