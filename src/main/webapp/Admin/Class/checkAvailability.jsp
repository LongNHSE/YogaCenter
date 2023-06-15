<%-- 
    Document   : checkAvailability
    Created on : Jun 11, 2023, 7:53:15 PM
    Author     : Oalskad
--%>

<%@page import="com.mycompany.yogacenterproject.dto.PhongHocDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.yogacenterproject.dao.PhongHocDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    PhongHocDAO phongHocDAO = new PhongHocDAO();
    String Slot = request.getParameter("slot");
//    List<String> weekdays = request.getParameter("weekday");
//    // Query the database or perform any necessary operations to get the price for the categoryId
//    String price
//            = loaiLopHocDAO.searchHocPhiLopHoc(maLoaiLopHoc);
//
//    response.getWriter().write(String.valueOf(price));

    String maslot = request.getParameter("slot");
    String[] weekdays = request.getParameterValues("weekday");
    String a = weekdays[0];
    List<PhongHocDTO> lophoc = new ArrayList<>();
    PhongHocDTO phongHocDTO = new PhongHocDTO();

    
    String[] weekdayss = a.split(",");
   
    // Process the slot and weekdays as needed
    // Example: Print the slot and weekdays
    
  
    boolean check = phongHocDAO.getEmptyRoom(maslot, weekdayss[0].toUpperCase(), weekdayss[1].toUpperCase()).isStatus();
     response.getWriter().write(Boolean.toString(check));
%>