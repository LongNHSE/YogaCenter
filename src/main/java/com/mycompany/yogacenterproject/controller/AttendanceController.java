/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.yogacenterproject.controller;

import com.mycompany.yogacenterproject.dao.AttendanceDAO;
import com.mycompany.yogacenterproject.util.DateUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Oalskad
 */
public class AttendanceController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            checkAttendance(request, response);

        }
    }

    public void checkAttendance(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String classID = request.getParameter("maLopHoc");
        LocalDate currentDate = LocalDate.now();
        Date ngayHoc = Date.valueOf(request.getParameter("ngayHoc"));
        String[] maHVs = request.getParameterValues("maHV");
        String[] attendances = request.getParameterValues("attendance");

        String maSlot = request.getParameter("maSlot");
        if (ngayHoc.equals(Date.valueOf(currentDate))) {
            AttendanceDAO attendanceDAO = new AttendanceDAO();
            for (int i = 0; i < maHVs.length; i++) {
                String maHV = maHVs[i];
                String attendance = attendances[i];
                attendanceDAO.updateAttendance(ngayHoc, maSlot, classID, maHV, attendance);
            }
            String popupMessageSuccessful = "Attendance has been taken successfully.";
            request.setAttribute("popupMessageSuccessful", popupMessageSuccessful);
            RequestDispatcher rd = request.getRequestDispatcher("/ClassController?action=ClassDetailTrainer&maLopHoc=" + classID + "&ngayHoc=" + ngayHoc + "&maSlot=" + maSlot);
            rd.forward(request, response);
        } else if (currentDate.isAfter(DateUtils.asLocalDate(ngayHoc))) {

            String popupMessage = "You can't take attendance now because it has already passed the due date.";

            request.setAttribute("popupMessage", popupMessage);
            RequestDispatcher rd = request.getRequestDispatcher("/ClassController?action=ClassDetailTrainer&maLopHoc=" + classID + "&ngayHoc=" + ngayHoc + "&maSlot=" + maSlot);
            rd.forward(request, response);
        } else {
            String popupMessage = "You can't take attendance now because it is not the due date.";
            request.setAttribute("popupMessage", popupMessage);
            RequestDispatcher rd = request.getRequestDispatcher("/ClassController?action=ClassDetailTrainer&maLopHoc=" + classID + "&ngayHoc=" + ngayHoc + "&maSlot=" + maSlot);
            rd.forward(request, response);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
