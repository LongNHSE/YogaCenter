<%-- 
    Document   : BlogDetails
    Created on : Jul 4, 2023, 9:14:03 PM
    Author     : devli
--%>
<%
     String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
<!--            <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
              <div class="carousel-inner">
                <div class="carousel-item active">
                  <img class="d-block w-100" src="https://images.pexels.com/photos/2662116/pexels-photo-2662116.jpeg?auto=compress&cs=tinysrgb&w=1600" alt="First slide">
                </div>
                <div class="carousel-item">
                  <img class="d-block w-100" src="https://images.pexels.com/photos/2325446/pexels-photo-2325446.jpeg?auto=compress&cs=tinysrgb&w=1600" alt="Second slide">
                </div>
                <div class="carousel-item">
                  <img class="d-block w-100" src="https://images.pexels.com/photos/2387418/pexels-photo-2387418.jpeg?auto=compress&cs=tinysrgb&w=1600" alt="Third slide">
                </div>
              </div>
              <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
              </a>
              <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
              </a>
            </div>-->
        </div>
        <div class="article-title">
        <h6><a href="#">Lifestyle</a></h6>
        <h2 class="header-ken">${blogDetails.title}</h2>
        <div class="media">
        <div class="avatar">
        <img src="https://bootdey.com/img/Content/avatar/avatar1.png" title alt>
        </div>
        <div class="media-body">
        <label>${blogDetails.maHV}</label>
        <span>${blogDetails.date}</span>
        </div>
        </div>
        </div>
        <div class="article-content">
        <p style="color:#545554; font-size: 20px">${blogDetails.content}</p>
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
            <div class="widget-body">
                    <div class="latest-post-aside media">
                    <div class="lpa-left media-body">
                    <div class="lpa-title">
                    <h5><a href="#">Prevent 75% of visitors from google analytics</a></h5>
                    </div>
                    <div class="lpa-meta">
                    <a class="name" href="#">
                    Rachel Roth
                    </a>
                    <a class="date" href="#">
                    26 FEB 2020
                    </a>
                    </div>
                    </div>
                    <div class="lpa-right">
                    <a href="#">
                    <img src="https://www.bootdey.com/image/400x200/FFB6C1/000000" title alt>
                    </a>
                    </div>
                    </div>
            
            
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
        </div>

        </body>

</html>
