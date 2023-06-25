<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>YogaCenter Admin</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">
        <link href="newCascadeStyleSheet.css" rel="stylesheet" type="text/css"/>
        <script class="u-script" type="text/javascript" src="home2.js" defer=""></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script class="u-script" type="text/javascript" src="home1.js" defer=""></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    </head>

    <body>
        
        
  
        <div class="Controller">
            <div class="wrapper">
                <nav class='animated bounceInDown bg-dark'>
                    <ul>
                        <li id="active-element"><a href='./ClassController.jsp'>Profile</a></li>
                        <li id="active" class='sub-menu'><a href='#settings'><i class="fa-solid fa-school"></i>Class<div class='fa fa-caret-down right'></div></a>
                            <ul id="active">
                                <li ><a href='<%=url%>/AdminController?action=listLopHoc&page=1'>List Class</a></li>
                                <li ><a href='<%=url%>/AdminController?action=listClassUnassigned'>List Class Unassigned</a></li>
                                <li><a href='<%=url%>/AdminController?action=ViewSchedule'>View Schedule</a></li>
                                <li><a href='./CreateClassTypePage.jsp'>Create Class Type</a></li>
                            </ul>
                        </li>
                        <li class='sub-menu'><a href='#message'>Trainee<div class='fa fa-caret-down right'></div></a>
                            <ul>
                                <li><a href="<%=url%>/AdminController?action=listHocVien">List Trainee</a></li>
                                <li><a href='#settings'>Submit a Ticket</a></li>
                                <li><a href='#settings'>Network Status</a></li>
                            </ul>
                        </li>
                         <li class='sub-menu'><a href='#message'>Trainer<div class='fa fa-caret-down right'></div></a>
                            <ul>
                                <li><a href="<%=url%>/AdminController?action=listHocVien">List Trainer</a></li>
                                <li><a href='<%=url%>/Admin/Trainer/AddTrainer.jsp'>Add Trainer</a></li>
                                <li><a href='#settings'>Network Status</a></li>
                            </ul>
                        </li>
                         <li class='sub-menu'><a href='#message'>Application<div class='fa fa-caret-down right'></div></a>
                            <ul>
                                <li><a href="<%=url%>/AdminController?action=listHocVien">List Trainer</a></li>
                                <li><a href="">Add Trainer</a></li>
                                <li><a href='#settings'>Network Status</a></li>
                            </ul>
                        </li>
                        <li><a href='<%=url%>/LoginController?action=adminLogout'>Logout</a></li>
                       
                    </ul>
                </nav>
            </div>
  <div class="content">
 </div>         
        </div>
        <script>
         

            $('.sub-menu ul').hide();

            $(".sub-menu a").click(function () {
                $(this).parent(".sub-menu").children("ul").slideToggle("100");
                $(this).find(".right").toggleClass("fa-caret-up fa-caret-down");
            });
        </script>

    </body>
</html>