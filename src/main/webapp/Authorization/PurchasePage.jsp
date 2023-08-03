<%-- 
    Document   : PurchasePage
    Created on : Aug 2, 2023, 1:10:16 AM
    Author     : Oalskad
--%>
<%
    String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@page import="com.mycompany.yogacenterproject.dto.DayAndSlot"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.mycompany.yogacenterproject.dao.LopHocDAO"%>
<%@page import="com.mycompany.yogacenterproject.dto.LopHocDTO"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="<%=url%>/css/ClassDetailStyle.css" rel="stylesheet" type="text/css"  > 

    </head>
    <body>
        <jsp:include page="../Components/headerComponent.jsp" />       


        <section class="vh-100 gradient-custom register-section">
            <div class="container py-5 h-100" >
                <div class="row justify-content-center align-items-center h-100">
                    <div class="col-12 col-lg-9 col-xl-10">
                        <div class="card shadow-2-strong card-registration register-form" style="border-radius: 50px;">
                            <div class="card-body p-4 p-md-5">
                                <h3 class="mb-4 pb-2 pb-md-0 mb-md-5 form-name">Class Registration Form</h3>

                                <input type="hidden" id="thisField" name="action" value="addUser" placeholder="Enter your OTP send through email" />
                                <div class="row">
                                    <div class="col-md-6 mb-4" >

                                        <div class="form-outline">
                                            <c:set value="${requestScope.lopHocDTO}" var="lopHocDTO"/>

                                            <input type="text" id="ClassID" class="form-control form-control-lg" name="ClassID" required="required" readonly value="${lopHocDTO.maLopHoc} - ${lopHocDTO.loaiLopHocDTO.tenLoaiLopHoc}"/>
                                            <label class="form-label" for="ClassID">ClassID</label>


                                        </div>

                                    </div>
                                    <div class="col-md-6 mb-4">

                                        <div class="form-outline">
                                            <input type="text" id="Trainer" class="form-control form-control-lg" name="Trainer" required="required" value="${lopHocDTO.trainerDTO.ho} ${lopHocDTO.trainerDTO.ten} " readonly/>
                                            <label class="form-label" for="Trainer">Trainer</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6 mb-4">
                                        <div class="form-outline">
                                            <input type="text" id="Slot" class="form-control form-control-lg" name="Slot" required="required" value="${lopHocDTO.slotDTO.timeStart}-${lopHocDTO.slotDTO.timeEnd}" readonly/>
                                            <label class="form-label" for="Slot">Slot</label>
                                        </div>


                                    </div>
                                    <div class="col-md-6 mb-4">

                                        <div class="form-outline">
                                            <input type="text" id="Days" class="form-control form-control-lg" name="Days" required="required" value="${lopHocDTO.printDays()}" readonly/>
                                            <label class="form-label" for="Days">Days</label>
                                        </div>

                                    </div>

                                </div>

                                <div class="row">
                                    <div class="col-md-6 mb-4 d-flex align-items-center">
                                        <c:set var="voucher" value="${voucherDTO}"/>
                                        <div class="form-outline datepicker w-100">

                                            <c:if test="${currentPrice==null}" >
                                                <input type="text" class="form-control form-control-lg" id="Fee" name="Fee" required="required" value="${lopHocDTO.loaiLopHocDTO.getHocPhiWithDot() } VND" readonly/>
                                                <label for="Fee" class="form-label">Fee</label>
                                            </c:if>
                                            <c:if test="${currentPrice!=null}" >
                                                <fmt:formatNumber value="${requestScope.details.getHocPhi()}" pattern="#,##0 VNĐ" var="formattedHocPhi"  />
                                                <s>${formattedHocPhi}</s>


                                                <c:set var="currentPrice" value="${currentPrice}"/>
                                                <fmt:formatNumber value="${currentPrice}" pattern="#,##0 VNĐ" var="currentPriceNew" />
                                                <p type="text" class="form-control form-control-lg" id="Fee" name="Fee" required="required" readonly><s>${lopHocDTO.loaiLopHocDTO.getHocPhiWithDot()}</s> VND - <b>Discount ${voucher.multiplier}%</b> </p> 

                                                <input type="text" class="form-control form-control-lg" id="Fee" name="Fee" required="required" value="${currentPrice} VND" readonly/>
                                                <label for="Fee" class="form-label">   
                                                    Fee</label>

                                            </c:if>
                                        </div>

                                    </div>
                                    <form action="<%=url%>/ClassController" method="POST">   
                                        <div class="col-md-6 mb-4">


                                            <c:if test="${voucher!=null}" >
                                                <div>
                                                    <input type="hidden" name="voucherID" value="${voucher.voucherID}" />

                                                </div>
                                            </c:if>
                                            <div class="voucher-check2">
                                                <input type="text"  class="form-control form-control-lg" name="voucher" value="${voucher.voucherName}" placeholder="Your voucher..."/>
                                                <button class="button2" type="submit" name="action" value="CheckVoucher" style="margin-top: 5px">
                                                    Check
                                                </button>
                                                <input type="hidden" name="maLopHoc" value="${lopHocDTO.maLopHoc}" />
                                            </div>

                                        </div>
                                    </form>
                                </div>

                                <form action="<%=url%>/ClassController" method="POST">
                                    <div class="mb-1">
                                        <input type="checkbox" class="form-check-inline" id="policyCheck" name="policyCheck" required="required">
                                        <label for="policyCheck" class="form-label">I agree with the <a href="" style="color: black">Term and Condition</a> and the <a href="" style="color: black">Privacy Policy</a> </label>
                                        <input type="hidden" name="maLopHoc" value="${lopHocDTO.maLopHoc}" />
                                        <input type="hidden" name="voucherID" value="${voucher.voucherID}" />

                                        <div class="mt-4 pt-2">
                                            <!--<input class="btn btn-outline-danger" type="submit" value="Submit" id="submit" disabled />-->
                                            <button class="button2" type="submit" name="action" value="payment" >
                                                Register now!
                                            </button>
                                        </div>
                                </form>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>



    </body>
</html>
