

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>YogaCenter Admin </title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">
        <link rel="stylesheet" href="css/home2.css" media="screen">
        <link rel="stylesheet" href="css/Home1.css" media="screen">
        <script class="u-script" type="text/javascript" src="home2.js" defer=""></script>
        <script class="u-script" type="text/javascript" src="home1.js" defer=""></script>
        <meta name="generator" content="Nicepage 5.6.2, nicepage.com">
        <meta name="referrer" content="origin">
        <link id="u-theme-google-font" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i|Open+Sans:300,300i,400,400i,500,500i,600,600i,700,700i,800,800i">




        <script type="application/ld+json">{
            "@context": "http://schema.org",
            "@type": "Organization",
            "name": "",
            "logo": "lmht.png"
            }</script>
        <meta name="theme-color" content="#478ac9">
        <meta property="og:title" content="Home">
        <meta property="og:type" content="website">
        <meta data-intl-tel-input-cdn-path="intlTelInput/"></head>

    <!-- Favicons -->
    <link href="img/favicon.ico" rel="icon">
    <link href="img/apple-favicon.png" rel="apple-touch-icon">

    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:100,200,300,400,500,600,700,800,900" rel="stylesheet"> 

    <!-- Vendor CSS File -->
    <link href="<%= url%>/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%= url%>/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%= url%>/vendor/animate/animate.min.css" rel="stylesheet">
    <link href="<%= url%>/vendor/slick/slick.css" rel="stylesheet">
    <link href="<%= url%>/vendor/slick/slick-theme.css" rel="stylesheet">
    <link href="<%= url%>/vendor/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

    <!-- Main Stylesheet File -->
    <link href="<%= url%>/css/hover-style.css" rel="stylesheet">
    <link href="<%= url%>/css/style.css" rel="stylesheet">
    <!-- Development version -->
    <script src="https://unpkg.com/@popperjs/core@2/dist/umd/popper.js"></script>

    <!-- Production version -->
    <script src="https://unpkg.com/@popperjs/core@2"></script>

</head>

<body>





    <header id="header">
        <div class="mobile-menu-btn"><i class="fa fa-bars"></i></div>
        <nav class="main-menu top-menu">
            <ul>
                <li><a href="<%= url%>/Admin/AdminHomepage.jsp">Home</a></li>

                <li>
                    <form action="ADlogoutController" method="POST">

                        <input class="btn btn-outline-danger"  type="submit" value="Logout"></input>

                    </form>
                </li>
            </ul>
        </nav>
    </header>
    <div class="center">
        <form action="<%=url%>/AdminController" method="POST">
            <input type="submit" value="ListTrainer" />
            <input type="hidden" value="listTrainer" name="action"/>
            <%=url%>
        </form>

    </div>



    <!-- Booking Javascript File -->
    <script src="js/booking.js"></script>
    <script src="js/jqBootstrapValidation.min.js"></script>

    <!-- Main Javascript File -->
    <script src="js/main.js"></script>
</body>
</html>