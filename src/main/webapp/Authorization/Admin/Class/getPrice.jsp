<%-- 
    Document   : getPrice
    Created on : Jun 10, 2023, 10:48:11 AM
    Author     : Oalskad
--%>

<%@page import="com.mycompany.yogacenterproject.dao.LoaiLopHocDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


        <%
            LoaiLopHocDAO loaiLopHocDAO = new LoaiLopHocDAO();
            String maLoaiLopHoc = request.getParameter("maLoaiLopHoc");
            // Query the database or perform any necessary operations to get the price for the categoryId
            String price
                    = loaiLopHocDAO.searchHocPhiLopHoc(maLoaiLopHoc);
                   
            response.getWriter().write(String.valueOf(price));
        %>

