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
                  <h5 class="text-primary h6">Our Blog</h5>
                  <h2 class="display-20 display-md-18 display-lg-16">Most recent our blog</h2>
                  </div>
            <div class="row">
                  <div class="col-lg-4 col-md-6 mb-2-6">
                  <article class="card card-style2">
                  <div class="card-img">
                  <img class="rounded-top" src="https://www.bootdey.com/image/350x280/6A5ACD/000000" alt="...">
                  <div class="date"><span>15</span>Sep</div>
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
                  
                  <div class="col-lg-4 col-md-6 mb-2-6">
                  <article class="card card-style2">
                  <div class="card-img">
                  <img class="rounded-top" src="https://www.bootdey.com/image/350x280/FFB6C1/000000" alt="...">
                  <div class="date"><span>18</span>Aug</div>
                  </div>
                  <div class="card-body">
                  <h3 class="h5"><a href="#!">All you need to know about cleaning</a></h3>
                  <p class="display-30">Five common mistakes everyone makes in about cleaning.</p>
                  <a href="#!" class="read-more">read more</a>
                  </div>
                  <div class="card-footer">
                  <ul>
                  <li><a href="#!"><i class="fas fa-user"></i>Mark Abell</a></li>
                  <li><a href="#!"><i class="far fa-comment-dots"></i><span>28</span></a></li>
                  </ul>
                  </div>
                  </article>
                  </div>
                  
                  <div class="col-lg-4 col-md-6 mb-2-6">
                  <article class="card card-style2">
                  <div class="card-img">
                  <img class="rounded-top" src="https://www.bootdey.com/image/350x280/008080/000000" alt="...">
                  <div class="date"><span>24</span>May</div>
                  </div>
                  <div class="card-body">
                  <h3 class="h5"><a href="#!">This cleaning tips will haunt you forever</a></h3>
                  <p class="display-30">Seven difficult things you should know about haunt.</p>
                  <a href="#!" class="read-more">read more</a>
                  </div>
                  <div class="card-footer">
                  <ul>
                  <li><a href="#!"><i class="fas fa-user"></i>Curtis Chester</a></li>
                  <li><a href="#!"><i class="far fa-comment-dots"></i><span>18</span></a></li>
                  </ul>
                  </div>
                  </article>
                  </div>
                  
                  <div class="col-lg-4 col-md-6 mb-2-6 mb-lg-0">
                  <article class="card card-style2">
                  <div class="card-img">
                  <img class="rounded-top" src="https://www.bootdey.com/image/350x280/EE82EE/000000" alt="...">
                  <div class="date"><span>09</span>May</div>
                  </div>
                  <div class="card-body">
                  <h3 class="h5"><a href="#!">Five things to know about cleaning service office</a></h3>
                  <p class="display-30">Seven difficult things you should know about haunt.</p>
                  <a href="#!" class="read-more">read more</a>
                  </div>
                  <div class="card-footer">
                  <ul>
                  <li><a href="#!"><i class="fas fa-user"></i>Kathleen</a></li>
                  <li><a href="#!"><i class="far fa-comment-dots"></i><span>24</span></a></li>
                  </ul>
                  </div>
                  </article>
                  </div>
                  
                  <div class="col-lg-4 col-md-6 mb-2-6 mb-md-0">
                  <article class="card card-style2">
                  <div class="card-img">
                  <img class="rounded-top" src="https://www.bootdey.com/image/350x280/4682B4/000000" alt="...">
                  <div class="date"><span>14</span>Apr</div>
                  </div>
                  <div class="card-body">
                  <h3 class="h5"><a href="#!">Seven difficult things about cleaning service like</a></h3>
                  <p class="display-30">Seven difficult things you should know about haunt.</p>
                  <a href="#!" class="read-more">read more</a>
                  </div>
                  <div class="card-footer">
                  <ul>
                  <li><a href="#!"><i class="fas fa-user"></i>Admin</a></li>
                  <li><a href="#!"><i class="far fa-comment-dots"></i><span>09</span></a></li>
                  </ul>
                  </div>
                  </article>
                  </div>
                  
                  <div class="col-lg-4 col-md-6">
                  <article class="card card-style2">
                  <div class="card-img">
                  <img class="rounded-top" src="https://www.bootdey.com/image/350x280/87CEEB/000000" alt="...">
                  <div class="date"><span>26</span>Mar</div>
                  </div>
                  <div class="card-body">
                  <h3 class="h5"><a href="#!">The hidden agenda of window cleaning concept</a></h3>
                  <p class="display-30">Seven difficult things you should know about haunt.</p>
                  <a href="#!" class="read-more">read more</a>
                  </div>
                  <div class="card-footer">
                  <ul>
                  <li><a href="#!"><i class="fas fa-user"></i>Vickie</a></li>
                  <li><a href="#!"><i class="far fa-comment-dots"></i><span>11</span></a></li>
                  </ul>
                  </div>
                  </article>
                  </div>
            </div>
            </div>
      </section>
      </body>
</html>
