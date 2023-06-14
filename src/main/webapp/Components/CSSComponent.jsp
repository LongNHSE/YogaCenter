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
  <link rel="stylesheet" href="<%=baseUrl%>/css/bootstrap.min.css">
   <!--style css--> 
  <link href="<%=baseUrl%>/css/style.css" rel="stylesheet" type="text/css"/>
   <!--Responsive-->
  <link rel="stylesheet" href="<%=baseUrl%>/css/responsive.css">
   <!--fevicon--> 
  <link rel="icon" href="<%=baseUrl%>/images/fevicon.png" type="image/gif" />
   <!--Scrollbar Custom CSS--> 
  <link rel="stylesheet" href="<%=baseUrl%>/css/jquery.mCustomScrollbar.min.css">
   <!--Tweaks for older IEs-->
  <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
   <!--owl stylesheets--> 
  <link rel="stylesheet" href="<%=baseUrl%>/css/owl.carousel.min.css">
  <link rel="stylesheet" href="<%=baseUrl%>/css/owl.theme.default.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css"
    media="screen">
