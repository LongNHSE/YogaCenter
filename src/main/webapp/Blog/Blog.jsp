<%-- 
    Document   : Blog
    Created on : Jul 4, 2023, 9:13:55 PM
    Author     : devli
--%>

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
                  <div class="col-lg-4 col-md-6 mb-2-6">
                  <article class="card card-style2">
                        <div class="card-img">
                        <img class="rounded-top" src="https://www.bootdey.com/image/350x280/6A5ACD/000000" alt="...">
                        </div>
                  <div class="card-body">
                  <h3 class="h5"><a href="#!">Loft therapy taking care of your home</a></h3>
                  <p class="display-30">Loft therapy will be a thing of the past and here's why.</p>
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
                  
                  
            </div>
            </div>
      </section>
      </body>
</html>
