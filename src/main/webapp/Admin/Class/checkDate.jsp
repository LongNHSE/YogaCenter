<%-- 
    Document   : checkDate
    Created on : Jun 12, 2023, 8:58:27 AM
    Author     : Oalskad
--%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%
    String dateErrorMessage = "";
    boolean error = true;
    String date = request.getParameter("initializeDate");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Parse the string into a LocalDate object
    LocalDate initializeDate = LocalDate.parse(date, formatter);
    LocalDate now = LocalDate.now().plusWeeks(1);
    if (initializeDate.isAfter(now) || initializeDate.isEqual(now)) {
        boolean check = true;
        response.getWriter().write(Boolean.toString(check));
    } else {
        boolean check = false;
        response.getWriter().write(Boolean.toString(check));
    }
%>