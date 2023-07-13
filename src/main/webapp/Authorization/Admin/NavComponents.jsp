<%-- 
    Document   : NavComponents
    Created on : Jul 7, 2023, 8:43:32 PM
    Author     : Oalskad
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<nav class='animated bounceInDown bg-dark'>
    <ul>
        <li><a href='<%=url%>/Authorization/Admin/AdminHomepage.jsp'>Profile</a></li>
        <li id="Class" class='sub-menu'><a href='#settings'><i class="fa-solid fa-school"></i>Class<div class='fa fa-caret-down right'></div></a>
            <ul >
                <li id="List Class"><a href='<%=url%>/AdminController?action=listLopHoc&page=1'>List Class</a></li>
                <li ><a href='<%=url%>/AdminController?action=listClassUnassigned'>List Class Unassigned</a></li>
                <li ><a href='<%=url%>/AdminController?action=listClassType'>List Class Type</a></li>
                <li><a href='<%=url%>/ClassController?action=CheckEmptyRoom'>Create Class</a></li>
                <li><a href='<%=url%>/AdminController?action=ViewSchedule'>View Schedule</a></li>
                <li><a href='<%=url%>/Authorization/Admin/Class/CreateClassTypePage.jsp'>Create Class Type</a></li>
            </ul>
        <li id="Voucher" class='sub-menu'><a href='#message'>Voucher<div class='fa fa-caret-down right'></div></a>
            <ul >
                <li><a href="<%=url%>/VoucherController?action=listVouchers">List Voucher</a></li>
                <li><a href='<%=url%>/Admin/Trainer/AddTrainer.jsp'>Add Voucher</a></li>
                <li><a href='#settings'>Kill niggers</a></li>
            </ul>
        </li>
        <li id="Trainee" class='sub-menu'><a href='#message'>Trainee<div class='fa fa-caret-down right'></div></a>
            <ul >
                <li><a href="<%=url%>/AdminController?action=listHocVien">List Trainee</a></li>
                <li><a href='#settings'>Submit a Ticket</a></li>
                <li><a href='#settings'>Network Status</a></li>
            </ul>
        </li>
        <li id="Trainer" class='sub-menu'><a href='#message'>Trainer<div class='fa fa-caret-down right'></div></a>
            <ul >
                <li><a href="<%=url%>/AdminController?action=listHocVien">List Trainer</a></li>
                <li><a href='<%=url%>/Admin/Trainer/AddTrainer.jsp'>Add Trainer</a></li>
                <li><a href='#settings'>Network Status</a></li>
            </ul>
        </li>
        <li  id="Application" class='sub-menu'><a href='#message'>Application<div class='fa fa-caret-down right'></div></a>
            <ul>
                <li><a href="<%=url%>/AdminController?action=listHocVien">List Trainer</a></li>
                <li><a href="">Add Trainer</a></li>
                <li><a href='#settings'>Network Status</a></li>
            </ul>
        </li>
        <li id="Blog" class='sub-menu'><a href='#message'>Blog<div class='fa fa-caret-down right'></div></a>
            <ul>
                <li><a href="<%=url%>/BlogAdminController?action=ViewListBlogUnapprove">List Blog Unapproved</a></li>
                <li><a href="<%=url%>/BlogAdminController?action=ViewListBlogApprove">List Blog Approved</a></li>
                <li><a href='#settings'>Network Status</a></li>
            </ul>
        </li>
        <li>
            <ul>
                <li></li>
                <li></li>
            </ul>
        </li>
        <li><a href='<%=url%>/LoginController?action=adminLogout'>Logout</a></li>

    </ul>
</nav>
