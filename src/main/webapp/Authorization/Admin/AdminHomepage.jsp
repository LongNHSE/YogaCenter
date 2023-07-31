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
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <link href="<%=url%>/Authorization/Admin/cssAdmin/newCascadeStyleSheet.css" rel="stylesheet" type="text/css"/>
        <script class="u-script" type="text/javascript" src="<%=url%>/js/home2.js" defer=""></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script class="u-script" type="text/javascript" src="<%=url%>/js/jshome1.js" defer=""></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">

    </head>

    <body>


        <div class="Controller">
            <div class="wrapper">

                <%@include file="NavComponents.jsp" %>

            </div>

            <div class="container">
                <div class="row">


                    <div class="col">  <canvas class ="miniChart"id="myChart"></canvas></div>

                    <div class="col">    <canvas class ="miniChart" id="revenueChart" ></canvas></div>
                </div>
                <div class="row">
                    <div class="col-12">  <canvas id="receiptChart" style="width:1000px !important;
                                                  height:600px !important"></canvas></div>

                </div>
            </div>



        </div>
        <style>
            .col{
                border: 1px solid;
                margin: 2px
            }
            #myChart{
                width: 400px !important;
                height: auto !important;
                align-content: center !important;
                align-self: center !important;

                margin: auto;
                width: 50%;
                padding: 10px;
            }
            #receiptChart{
                width: 400px !important;
                height: auto !important;
                align-content: center !important;
                align-self: center !important;

                margin: auto;
                width: 50%;
                padding: 10px;
            }
            .container{
                display: inline-block;
                position: inherit;
                left: 242px;
                margin: 0px;
            }
        </style>
        <script src="<%=url%>/Authorization/Admin/chart ts-chart-script.js" type="text/javascript"></script>

        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
        <script>
            $('.sub-menu ul').hide();

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