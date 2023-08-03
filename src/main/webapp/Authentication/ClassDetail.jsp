<%-- 
    Document   : ClassDetail
    Created on : Jun 8, 2023, 8:10:20 AM
    Author     : Oalskad
--%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@page import="com.mycompany.yogacenterproject.dto.DayAndSlot"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.mycompany.yogacenterproject.dao.LopHocDAO"%>
<%@page import="com.mycompany.yogacenterproject.dto.LopHocDTO"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<%
    String JsUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="<%=JsUrl%>/js/jquery.min.js"></script>
        <script src="<%=JsUrl%>/js/popper.min.js"></script>
        <script src="<%=JsUrl%>/js/bootstrap.bundle.min.js"></script>
        <script src="<%=JsUrl%>/js/jquery-3.0.0.min.js"></script>
        <script src="<%=JsUrl%>/js/plugin.js"></script>
        <!-- sidebar -->
        <script src="<%=JsUrl%>/js/jquery.mCustomScrollbar.concat.min.js"></script>
        <script src="<%=JsUrl%>/js/custom.js"></script>
        <!-- javascript -->
        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>

        <script src="<%=JsUrl%>/js/owl.carousel.js"></script>
        <script src="https:cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.js"></script>
        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.bundle.min.js"></script>
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
        <!--CSS-->
        <link href="<%=url%>/css/ClassDetailStyle.css" rel="stylesheet" type="text/css"  > 
        <style type="text/css">

        </style>            
    </head>

    <body>
        <!--navbar : Start-->
        <jsp:include page="../Components/headerComponent.jsp" />       
        <!--navbar: End-->
        <div class="container">
            <% String popupMessage = (String) request.getAttribute("popupMessage");
                String popupMessageSuccessful = (String) request.getAttribute("popupMessageSuccessful"); %>
            <% if (popupMessage != null) {%> <div id="myAlert" class="alert">
                <span class="closebtn" onclick="this.parentElement.style.display = 'none';">&times;</span> 
                <strong>!</strong>  ${popupMessage} 
            </div>

            <% }%>
            <div class="product-content product-wrap clearfix product-deatil">
                <div class="row">

                    <div class="col-md-5 col-sm-12 col-xs-12">
                        <div class="product-image">
                            <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                                <ol class="carousel-indicators">

                                    <c:forEach items="${requestScope.imageListByID}" var="imageData" varStatus="status">
                                        <li data-target="#carouselExampleIndicators" data-slide-to="${status.index}" class="${status.index == 0 ? 'active' : ''}"></li>
                                        </c:forEach>
                                </ol>
                                <div class="carousel-inner">

                                    <c:forEach items="${requestScope.imageListByID}" var="imageData" varStatus="status">
                                        <div class="carousel-item ${status.index == 0 ? 'active' : ''}">
                                            <img src="data:image/jpeg;base64,${imageData.image}" class="img-responsive" alt="" style="width: 100%;
                                                 height: 400px;">
                                        </div>
                                    </c:forEach>
                                </div>


                                <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                    <span class="sr-only"></span>
                                </a>
                                <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                    <span class="sr-only"></span>
                                </a>
                            </div>        
                        </div>
                    </div>



                    <div class="col-md-6 col-md-offset-1 col-sm-12 col-xs-12 class-information">
                        <h1 class="name">
                            ${requestScope.details.getTenLoaiLopHoc()}
                        </h1>
                        <hr class="infor-line"/>
                        <p class="price-container text-right">
                            <c:if test="${currentPrice==null}" >
                                <fmt:formatNumber value="${requestScope.details.getHocPhi()}" pattern="#,##0 VND" var="formattedHocPhi" />
                                ${formattedHocPhi} / 1 Slot

                            </c:if>
                            <c:if test="${currentPrice!=null}" >
                                <fmt:formatNumber value="${requestScope.details.getHocPhi()}" pattern="#,##0 VND" var="formattedHocPhi"  />
                                <s> ${formattedHocPhi} / 1 Slot</s>


                                <c:set var="currentPrice" value="${currentPrice}"/>

                                <fmt:formatNumber value="${currentPrice}" pattern="#,##0 VNĐ" var="currentPriceNew" />
                                ${currentPriceNew} / 1 Slot
                            </c:if>
                        </p>
                        <hr class="infor-line"/>

                        <form action="<%=url%>/ClassController" method="POST">
                            <div class="row">

                                <div class="col-sm-12 col-md-6 col-lg-6 d-flex justify-content-center align-items-center">

                                    <div class="box">
                                        <select name="maSlot" required>
                                            <option  value=""> Please choose Slot</option>
                                            <c:forEach items="${requestScope.distinctDayAndSlots}" var="DayAndSlot" >
                                                <option name="maSlot" value="${DayAndSlot.getSlot()}|${DayAndSlot.getDay()}">
                                                    ${DayAndSlot.getSlot()} : ${DayAndSlot.timeStart}-${DayAndSlot.timeEnd}, ${DayAndSlot.day}
                                                </option>  
                                            </c:forEach>
                                        </select>
                                    </div>



                                    <div class="Custom">



                                        <% String cid = (String) request.getAttribute("cid");%>
                                        <input type="hidden" name="maLoaiLopHoc" value="<%=cid%>" />
                                        <input type="hidden" name="returnID" value="<%=cid%>" />
                                        <button class="button2" type="submit" name="action" value="Register" >
                                            Register now!
                                        </button>
                                    </div>                
                                </div>
                                <div class="voucher-note">
                                    <% String voucherMessage = (String) request.getAttribute("voucherMessage"); %>
                                    <% if (voucherMessage != null) {%>⚠ <%= voucherMessage%> <% }%>
                                </div>  
                                <div style="
                                        width: 357px;
                                        position: absolute;
                                        margin-top: 171px;
                                        color: white;
                                        left: 100px;
                                        font-weight: BOLD;
                                        background: red;
                                        text-align: center;
                                        padding: 5px 12px;
                                     ">

                                    <% String errorMessage = (String) request.getAttribute("error");%>
                                    <% if (errorMessage != null) {%> <%= errorMessage%> <% }%>

                                </div>


                            </div>

                        </form>

                    </div>
                </div>
                <c:set var="descriptionDTO" value="${descriptionDTO}" />

            </div>

            <div class="ClassList product-content product-wrap clearfix product-deatil">
                <button class="collapsible">List Class</button>
                <div class="content">


                    <table class="table" >

                        <thead>
                            <tr class="Test" style="text-align: center">


                                <!--<th scope="col">Class' ID</th>-->


                                <th scope="col"> Attendees</th>
                                <th scope="col">Trainer</th>
                                <th scope="col">Slots</th>
                                <th scope="col">Room</th>
                                <th scope="col">Slot</th>
                                <th scope="col">Days</th>
                                <th scope="col">Create Date</th>
                            </tr>
                        </thead>
                        <tbody>

                            <c:forEach items="${listClass}" var="listClass">
                            <form action="<%=url%>/ClassController" method="Post">
                                <tr style="text-align: center">

                                    <!--<th scope="row">${listClass.maLopHoc}</th>-->

                                    <td >${listClass.soLuongHvHienTai}/${listClass.soLuongHV} </td>

                                    <!--<td><a href="#"  >${listClass.trainerDTO.ho} ${listClass.trainerDTO.ten}</a>  </td>-->
                                <td>
                                  <a href="#" onclick="openModal('${listClass.trainerDTO.ho} ${listClass.trainerDTO.ten}')">
                                    ${listClass.trainerDTO.ho} ${listClass.trainerDTO.ten}
                                  </a>
                                </td>


                                    <td>${listClass.soBuoiDaDay}/${listClass.soBuoi}</td>
                                    <td>${listClass.maRoom} </td>
                                    <td>${listClass.slotDTO.timeStart}-${listClass.slotDTO.timeEnd} </td>
                                    <td>${listClass.printDays()} </td>
                                    <td >${listClass.ngayBatDau} </td>
                                    <td ><button class="buttonClass" type="submit" name="action" value="RegisterWithClassID">
                                            Register now!
                                        </button> </td>
                                </tr>
                                <input type="hidden" name="returnID" value="<%=cid%>" />
                                <input type="hidden" name="maLopHoc" value="${listClass.maLopHoc}" />

                            </form>
                        </c:forEach>
                        </tbody>
                    </table>



                </div>
            </div>
            <div class="Description product-content product-wrap clearfix product-deatil">
                <div class="title">
                    <h1> 📖  ${descriptionDTO.title}</h1>
                </div>
                <div class="description">
                    ${descriptionDTO.content}
                </div>

            </div>
            <div class="Description product-content product-wrap clearfix product-deatil">

                <div class="col-sm-5 col-md-6 col-12 pb-4" >
                    <div class="header-section" >
                    <h1 class="comment-header">Comments</h1>
                    </div>


                    <c:if test = "${sessionScope.hocVienDTO != null || sessionScope.trainerDTO != null}">
                        <div class="comment mt-4 text-justify float-left " style="border: none">
                            <form action="<%=url%>/CommentController">
                                <div class="d-flex flex-row align-items-start"><textarea style="resize:none; padding:30px 0px;" class="form-control ml-1 shadow-none textarea" name="comment"></textarea></div>
                                <div class="mt-2 text-right btn-section">
                                    <button class="btn btn-outline-primary btn-sm ml-1 shadow-none cancel-btn" type="button">Cancel</button>
                                    <button class="btn btn-primary btn-sm shadow-none submit-btn" type="submit">Post comment</button>                                
                                </div>

                                <input type="hidden" name="maLoaiLopHoc" value="<%=cid%>" />
                                <input type="hidden" name="action" value="post" />



                            </form>
                        </div>
                    </c:if>
                    <c:forEach var="commentDTO" items="${requestScope.listComment}">
                        <div class="comment mt-4 text-justify float-left comment-content">
                            <c:choose>
                                <c:when test="${sessionScope.hocVienDTO.maHV == commentDTO.hocVienDTO.maHV && sessionScope.hocVienDTO.maHV != null}">
                                    <form action="<%=url%>/CommentController">
                                        <button class="btn delete-btn" type="submit">X</button>
                                        <input type="hidden" name="maComment" value="${commentDTO.maComment}" />
                                        <input type="hidden" name="returnID" value="${blogDetails.maBlog}" />
                                        <input type="hidden" name="action" value="deleteBlog" />
                                    </form>
                                </c:when>
                                <c:when test="${sessionScope.trainerDTO.maTrainer == commentDTO.trainerDTO.maTrainer && sessionScope.trainerDTO.maTrainer != null}">
                                    <form action="<%=url%>/CommentController">
                                        <button class="btn delete-btn" type="submit">X</button>
                                        <input type="hidden" name="maComment" value="${commentDTO.maComment}" />
                                        <input type="hidden" name="returnID" value="${blogDetails.maBlog}" />
                                        <input type="hidden" name="action" value="deleteBlog" />
                                    </form>
                                </c:when>
                                <c:otherwise>
                                </c:otherwise>
                            </c:choose>

                            <div class="comment-info">
                                <c:if test="${commentDTO.hocVienDTO.username != null}">
                                    <img src="data:image/jpeg;base64,${commentDTO.hocVienDTO.avatarDTO.image}" alt="" class="rounded-circle" width="40" height="40">
                                    <h3 class="author-name">${commentDTO.hocVienDTO.username}</h3>
                                </c:if>
                                <c:if test="${commentDTO.trainerDTO.username != null}">
                                    <img src="data:image/jpeg;base64,${commentDTO.trainerDTO.avatarDTO.image}" alt="" class="rounded-circle" width="40" height="40">
                                    <h3 class="author-name">${commentDTO.trainerDTO.ten}</h3><span style="color: #953553; font-size: 16px;; font-weight: 600;margin-bottom: 10px; margin-left: 10px; ">(Trainer)</span>
                                </c:if>
                            </div>
                            <br>
                            <div class="comment-detail">
                            <p>${commentDTO.noiDung}</p>
                            </div>
                        </div>
                    </c:forEach>

                </div>

            </div>


        </div>
        <style>
            /* CSS cho modal */
/* Modal */
.modal {
  display: none;
  position: fixed;
  z-index: 1;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  overflow: auto;
  background-color: rgba(0, 0, 0, 0.4);
}

/* Modal Content */
.modal-content {
  background-color: #fefefe;
  margin: 15% auto;
  padding: 20px;
  border: 1px solid #888;
  width: 60%;
  position: relative; /* Thêm thuộc tính này để làm nút Close tương đối với Modal Content */
}

/* Close button */
.close {
  position: absolute;
  top: 0;
  right: 0;
  font-size: 1.5rem;
  padding: 5px 10px;
  cursor: pointer;
}

            .close {
              color: #aaa;
              float: right;
              font-size: 28px;
              font-weight: bold;
              cursor: pointer;
            }

            .close:hover {
              color: black;
            }
.comment-content {
    position: relative;
    border-radius: 50px;
    border: 2px solid #000;
}

.comment-info {
    display: flex;
    align-items: center;
}

.comment-info img {
    margin-right: 10px;
}

.comment-date {
    color: gray;
}

.delete-btn {
    background-color: #8b57fc;
    border: none;
    padding: 0.5rem 1rem;
    font-size: 1rem;
    border-radius: 1rem;
    color: lightcoral;
    box-shadow: 0 0.4rem #dfd9d9;
    cursor: pointer;
}

.delete-btn:hover {
    background-color: lightcoral;
    color: white;
}

.delete-btn:focus {
    outline: none;
}

.comment-content p {
    margin-top: 10px;
}
.comment-date{
    margin-left: 50px;
    font-size: 15px;
    color: #b1b1b1b1;
}
.author-name{
        font-size: 24px;
    text-align: left;
    font-weight: 600;
}
 .submit-btn{
    cursor: pointer;
    position: relative;
    font-size: 18px;
    color: rgb(106, 90, 249);
    border: 2px solid rgb(106, 90, 249);
    border-radius: 34px;
    background-color: transparent;
    font-weight: 600;
    transition: all 0.3s cubic-bezier(0.23, 1, 0.320, 1);
    overflow: hidden;
}
.submit-btn::before{
    content: '';
    position: absolute;
    inset: 0;
    margin: auto;
    width: 50px;
    height: 50px;
    border-radius: inherit;
    scale: 0;
    z-index: -1;
    background-color: rgb(106, 90, 249);
    transition: all 0.6s cubic-bezier(0.23, 1, 0.320, 1);
}
.submit-btn:hover::before {
      scale: 3;
}
.submit-btn:hover {
    color: #ffff;
    scale: 1.1;
    box-shadow: 0 0px 20px rgba(106, 90, 249, 0.4);
}
.submit-btn:active {
  scale: 1;
}
.btn-section{
    display: flex;
    justify-content: space-between
}
.cancel-btn{
        cursor: pointer;
    position: relative;
    font-size: 18px;
    color:rgb(114, 47, 55);
    border: 2px solid rgb(114, 47, 55);
    border-radius: 34px;
    background-color: transparent;
    font-weight: 600;
    transition: all 0.3s cubic-bezier(0.23, 1, 0.320, 1);
    overflow: hidden;
}
.cancel-btn::before{
    content: '';
    position: absolute;
    inset: 0;
    margin: auto;
    width: 50px;
    height: 50px;
    border-radius: inherit;
    scale: 0;
    z-index: -1;
    background-color: rgb(114, 47, 55);
    transition: all 0.6s cubic-bezier(0.23, 1, 0.320, 1);
}
.cancel-btn:hover::before {
      scale: 3;
}
.cancel-btn:hover {
    color: #ffff;
    scale: 1.1;
    box-shadow: 0 0px 20px rgba(114, 47, 55,0.4);
}
.cancel-btn:active {
  scale: 1;
}
        </style>

        <jsp:include page="../Components/footerComponent.jsp" />   



        <script>
            var coll = document.getElementsByClassName("collapsible");
            var i;

            for (i = 0; i < coll.length; i++) {
                coll[i].addEventListener("click", function () {
                    this.classList.toggle("active");
                    var content = this.nextElementSibling;
                    if (content.style.maxHeight) {
                        content.style.maxHeight = null;
                    } else {
                        content.style.maxHeight = content.scrollHeight + "px";
                    }
                });
            }

                // JavaScript cho modal
                function openModal(trainerInfo) {
                  var modal = document.getElementById('trainerModal');
                  var trainerInfoParagraph = document.getElementById('trainerInfo');
                  trainerInfoParagraph.textContent = trainerInfo;
                  modal.style.display = 'block';
                }

                function closeModal() {
                  var modal = document.getElementById('trainerModal');
                  modal.style.display = 'none';
                }

        </script>
    </body>
<!-- Modal -->
<div id="trainerModal" class="modal">
  <div class="modal-content">
    <span class="close" onclick="closeModal()">&times;</span>
    <h2>Trainer Information</h2>
    <p id="trainerInfo">Trainer information will be displayed here</p>
  </div>
</div>
<!-- Modal Overlay -->
<div class="modal-overlay" onclick="closeModal()"></div>


</html>
