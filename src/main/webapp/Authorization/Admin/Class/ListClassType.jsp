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
            .container{
                position: relative;
                left: 1000px;
            }
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


            }
            table {
                margin-left: 250px;
                width: 100%; /* Set the width of the table */
                border-collapse: collapse; /* Collapse the borders of table cells */
            }

            th, td {
                font-size: 15.5px;
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
                <table class="table">

                    <thead>
                        <tr class="Test">


                            <th scope="col">Class' Type ID</th>
                            <th scope="col">Class' Type Name</th>
                            <th scope="col">Base Price</th>
                            <th scope="col">Description ID</th>
                            <th scope="col">Availability</th>


                        </tr>
                    </thead>
                    <tbody>

                        <c:forEach items="${listCate}" var="Cate">
                            <tr>
                        <form action="<%=url%>/ClassController" method="POST">

                            <th scope="row">${Cate.maLoaiLopHoc}</th>
                            <td>${Cate.tenLoaiLopHoc} </td>
                            <td style="text-align: right">${Cate.getHocPhiWithDot()} </td>
                            <td>${Cate.maDescription} </td>
                            <c:choose>
                                <c:when test="${Cate.isStatus() == true}">
                                    <td style="background-color: #3FFF00; text-align: center">Active</td>
                                </c:when>
                                <c:otherwise>
                                    <td style="background-color: red; text-align: center">Inactive</td>
                                </c:otherwise>
                            </c:choose>

                            <td>  <input class="btn btn-outline-danger" type='submit'value="Update Class Type"name="action"   > </td>

                            <td > <input class="btn btn-outline-danger" type='submit' value="Change Status" name="action" /></td> 


                            <input type="hidden" name="maLoaiLopHoc" value="${Cate.maLoaiLopHoc}" >
                        </form>
                        </tr>
                    </c:forEach>       





                    </tbody>

                </table>
            </div>      
        </div>

        <script>
            function removeSelectRequired(button) {
                var selectElements = document.querySelectorAll(".cate");
                for (var i = 0; i < selectElements.length; i++) {
                    selectElements[i].required = false;
                }
            }

            document.addEventListener("DOMContentLoaded", function () {
                var selectElements = document.querySelectorAll(".cate");
                for (var i = 0; i < selectElements.length; i++) {
                    selectElements[i].required = true;
                }
            });


            document.getElementsByClassName("cate").required = true;
            const navMenu = document.querySelector("nav");
            // Find the desired element and assign it the "active" id
            const blogLiElement = navMenu.querySelector("#Class");
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
