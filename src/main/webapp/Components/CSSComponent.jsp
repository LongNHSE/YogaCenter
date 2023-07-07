<%-- 
    Document   : CSSComponent
    Created on : Jun 14, 2023, 9:48:12 AM
    Author     : devli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
     String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
   <!--bootstrap css-->
  <link href="https://netdna.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">   
  <link rel="stylesheet" href="<%=baseUrl%>/css/bootstrap.min.css">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.min.css" rel="stylesheet">
   <!--style css--> 
  <link href="<%=baseUrl%>/css/style.css" rel="stylesheet" type="text/css"/>
   <!--Responsive-->
  <link rel="stylesheet" href="<%=baseUrl%>/css/responsive.css">
   <!--fevicon-->
   <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" />
   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.1/css/all.min.css" integrity="sha256-2XFplPlrFClt0bIdPgpz8H7ojnk10H69xRqd9+uTShA=" crossorigin="anonymous" />
  <link rel="icon" href="<%=baseUrl%>/images/fevicon.png" type="image/gif" />
   <!--Scrollbar Custom CSS--> 
  <link rel="stylesheet" href="<%=baseUrl%>/css/jquery.mCustomScrollbar.min.css">
   <!--Tweaks for older IEs-->
  <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
   <!--owl stylesheets--> 
  <link rel="stylesheet" href="<%=baseUrl%>/css/owl.carousel.min.css">
  <link rel="stylesheet" href="<%=baseUrl%>/css/owl.theme.default.min.css">

