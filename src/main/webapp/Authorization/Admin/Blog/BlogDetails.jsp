




<%-- 
    Document   : ListClass
    Created on : Jun 23, 2023, 7:36:18 PM
    Author     : Oalskad
--%>
<%@page import="com.mycompany.yogacenterproject.dto.BlogDTO"%>
<%@page import="com.mycompany.yogacenterproject.dto.LopHocDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>

<%
    String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>YogaCenter Admin</title>
        <link href="<%=url%>/BlogDetail.css" rel="stylesheet" type="text/css"/>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">
        <link href="<%=url%>/Authorization/Admin/cssAdmin/newCascadeStyleSheet.css" rel="stylesheet" type="text/css"/>
        <script class="u-script" type="text/javascript" src="<%=url%>/js/home2.js" defer=""></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script class="u-script" type="text/javascript" src="<%=url%>/js/jshome1.js" defer=""></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    </head>
    <body>

        <style>

            .center {
                text-align: center;
            }

            .pagination {
                display: inline-block;
            }

            .pagination a {
                color: black;
                float: left;
                padding: 8px 16px;
                text-decoration: none;
                transition: background-color .3s;
                border: 1px solid #ddd;
                margin: 0 4px;
            }

            .pagination a.active {
                background-color: #4CAF50;
                color: white;
                border: 1px solid #4CAF50;
            }

            .pagination a:hover:not(.active) {
                background-color: #ddd;
            }
            nav {
                position: relative;
                height: 100%;
                width: 250px;

            }
            body{
                height: 100%;
            }
            nav ul {
                position: relative;
                height: 100%;
                list-style: none;
                margin: 0;
                padding: 0;
            }
            nav ul li {
                /* Sub Menu */
            }
            nav ul li a {
                display: block;
                padding: 10px 15px;
                color: #fff;
                text-decoration: none;
                -webkit-transition: 0.2s linear;
                -moz-transition: 0.2s linear;
                -ms-transition: 0.2s linear;
                -o-transition: 0.2s linear;
                transition: 0.2s linear;
            }
            nav ul li a:hover {
                background: #1d4f71;
                color: #fff;
            }
            nav ul li a .fa {
                width: 16px;
                text-align: center;
                margin-right: 5px;
                float:right;
            }
            nav ul ul {
                background: rgba(0, 0, 0, 0.2);
            }
            nav ul li ul li a {

                border-left: 4px solid transparent;
                padding: 10px 20px;
            }
            nav ul li ul li a:hover {

                border-left: 4px solid #3498db;
            }
            .Controller{
                display: flex;
                position: relative;
            }

            #active-element{
                background: #1d4f71;
                color: #fff;

            }
            .wrapper{
                position:fixed;
                height: 100%;
                color: #fff;
            }
            .Controller .content{
                margin-left:250px;
            }
            .Table{
                margin-left: 30%;

            }


            th, td {
                font-size: 20px;
                padding: 10px; /* Add padding to table cells */
                text-align: left; /* Align text to the left in table cells */
                border: 1px solid #ccc; /* Add borders to table cells */
            }

            .Test th {
                background-color: #97EA5D; /* Set background color for table headers */
            }
            tr:nth-child(even) {
                background-color: #f9f9f9; /* Set background color for even rows */
            }

            tr:hover {
                background-color: #e6e6e6; /* Set background color for hovered rows */
            }</style>

        <%
            List<BlogDTO> listBlogDTO = (List<BlogDTO>) request.getAttribute("listBlogDTO");
        %>
        <div class="Controller">
            <div class="wrapper">

                <%@include file="../NavComponents.jsp" %>
            </div>
            <div class="Table">
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
                            <span style="color:#545554; font-size: 20px" class="blogContent">${blogDetails.content}</span>
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
                <section class="content-item" id="comments">
                    <div class="container">   
                        <div class="row">
                            <div class="col-sm-8">   

                                <c:if test = "${sessionScope.hocVienDTO != null || sessionScope.trainerDTO != null}">
                                    <div class="comment mt-4 text-justify float-left" style="border: none">
                                        <form action="<%=url%>/CommentController">
                                            <div class="d-flex flex-row align-items-start"><textarea class="form-control ml-1 shadow-none textarea" name="comment"></textarea></div>
                                            <div class="mt-2 text-right"><button class="btn btn-primary btn-sm shadow-none" type="submit">Post comment</button><button class="btn btn-outline-primary btn-sm ml-1 shadow-none" type="button">Cancel</button></div>

                                            <input type="hidden" name="returnID" value="${blogDetails.maBlog}" />
                                            <input type="hidden" name="action" value="postBlog" />



                                        </form>
                                    </div>
                                </c:if>

                                <h3>Comments</h3>
                                <c:forEach var="commentDTO" items="${requestScope.listComment}">
                                    <div class="comment mt-4 text-justify float-left">

                                        <form action="<%=url%>/CommentController">
                                            <button class="btn btn-primary btn-sm shadow-none" type="submit" style="margin-left: 595px;size: 100px">Delete</button>
                                            <input type="hidden" name="maComment" value="${commentDTO.maComment}" />
                                            <input type="hidden" name="returnID" value="${blogDetails.maBlog}" />
                                            <input type="hidden" name="action" value="deleteBlogAdmin" />
                                        </form>

                                        <img src="https://i.imgur.com/yTFUilP.jpg" alt="" class="rounded-circle" width="40" height="40">
                                        <c:if test="${commentDTO.hocVienDTO.username!=null}">
                                            <h2>${commentDTO.hocVienDTO.username}</h2>
                                        </c:if>
                                        <c:if test="${commentDTO.trainerDTO.username!=null}">
                                            <h2>${commentDTO.trainerDTO.ten}<bold style="color: greenyellow; font-size: 20px">(Trainer)</bold></h2>
                                                </c:if>
                                        <span>- ${commentDTO.date}</span>
                                        <br>
                                        <p>${commentDTO.noiDung}</p>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </section>                               

            </div>
        </div>      

        <form action="<%=url%>/BlogAdminController" method="POST">


            <input class="btn btn-outline-danger" type='submit' value="Delete" name="action" style="margin-left: 30%" />


            <input type="hidden" name="maBlog" value="${blogDetails.maBlog}" >
        </form>

        <style>
            #comments {
                width: 797px;
                
                margin-bottom: 20px;
            }
            #comments form {
                width: 1000px;

                margin-bottom: -29px;
            }
            .comments{
                margin-top: 5%;
                margin-left: 20px;
            }



            .comment{
                width: 700px;
                border: 1px solid rgba(16, 46, 46, 1);
                font-size: 20px;
                float: left;
                border-radius: 5px;
                padding-left: 40px;
                padding-right: 30px;
                padding-top: 15px;

            }
            .comment h2,.comment span,.darker h4,.darker span{
                display: inline;
            }

            .comment p,.comment span,.darker p,.darker span{

            }
        </style>
        <script>


            const navMenu = document.querySelector("nav");

            // Find the desired element and assign it the "active" id
            const blogLiElement = navMenu.querySelector("#Blog");
            if (blogLiElement) {
                const ulElement = blogLiElement.querySelector("ul");
                if (ulElement) {
                    ulElement.id = "active";
                }
            }

            $(document).ready(function () {
                $('.sub-menu ul#active').show();
                $('li#active').find(".right").toggleClass("fa-caret-up fa-caret-down");
            });

            $('.sub-menu ul').hide();

            $(".sub-menu a").click(function () {
                $(this).parent(".sub-menu").children("ul").slideToggle("100");
                $(this).find(".right").toggleClass("fa-caret-up fa-caret-down");
            });
        </script>
    </body>
</html>
