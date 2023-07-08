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

            .Table{


            }
            table {

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
            List<BlogDTO> listBlogDTO = (List<BlogDTO>) request.getAttribute("listBlog");
        %>


        <%@include file="../Components/headerComponent.jsp" %>

        <div class="Table">
            <table class="table">

                <thead>
                    <tr class="Test">


                        <th scope="col">ID Blog</th>
                        <th scope="col">ID Trainee</th>
                        <!--                            <th scope="col">ID Trainer</th>-->
                        <th scope="col">Title</th>
                        <th scope="col">Date</th>
                        <th scope="col">Status</th>

                    </tr>
                </thead>
                <tbody>

                    <c:forEach items="${listBlog}" var="blog">
                        <tr>
                    <form action="<%=url%>/BLogController" method="POST">

                        <th scope="row">${blog.maBlog}</th>
                        <td>${blog.maHV} </td>
                        <td>${blog.title} </td>
                        <td>${blog.date} </td>
                        <td>${blog.status} </td>
                        <td>  <input class="btn btn-outline-danger" type='submit'value="Detail"name="action"    ></td> 
                        <td >  <input class="btn btn-outline-danger" type='submit'value="Update"name="action"    ></td> 
                        <td >  <input class="btn btn-outline-danger" type='submit'value="Delete"name="action"    ></td> 
                        <input type="hidden" name="maBlog" value="${blog.maBlog}" >
                    </form>
                    </tr>
                </c:forEach>       





                </tbody>

            </table>
        </div>      


        <script>


        </script>
    </body>
</html>