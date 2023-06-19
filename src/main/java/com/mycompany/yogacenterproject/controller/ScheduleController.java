    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.yogacenterproject.controller;

import com.mycompany.yogacenterproject.dao.HocVienDAO;
import com.mycompany.yogacenterproject.dao.ScheduleDAO;
import com.mycompany.yogacenterproject.dao.SlotDAO;
import com.mycompany.yogacenterproject.dto.DateStartAndDateEnd;
import com.mycompany.yogacenterproject.dto.HocVienDTO;
import com.mycompany.yogacenterproject.dto.ScheduleHvDTO;
import com.mycompany.yogacenterproject.dto.SlotDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Oalskad
 */
public class ScheduleController extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */

            Schedule(request, response);
            date(request, response);

            request.getRequestDispatcher("/Class/Schedule.jsp").forward(request, response);

        }
    }

    public void Schedule(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        HttpSession session = request.getSession();
        HocVienDAO hocVienDAO = new HocVienDAO();
        HocVienDTO hocVienDTO = (HocVienDTO) session.getAttribute("hocVienDTO");
        ScheduleDAO scheduleDAO = new ScheduleDAO();
        List<ScheduleHvDTO> listScheduleHv = scheduleDAO.readScheduleHvDTO(hocVienDTO.getMaHV());
        SlotDAO slotDAO = new SlotDAO();

        List<SlotDTO> listSlot = slotDAO.readSlot();
        request.setAttribute("listSlot", listSlot);
        request.setAttribute("listScheduleHv", listScheduleHv);
        String weekRange = request.getParameter("weekRange");
        LocalDate today = LocalDate.now();
        if (weekRange != null) {

            LocalDate localDate = LocalDate.parse(weekRange);
            today = localDate; // Get the current date
        }

        LocalDate monday = today.with(DayOfWeek.MONDAY);

        List<LocalDate> days = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            String a = (monday.plusDays(i).toString());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate datea = LocalDate.parse(a, formatter);
            days.add(datea);

        }
        request.setAttribute("listDate", days);
    }

    public void date(HttpServletRequest request, HttpServletResponse response) {
        LocalDate currentDateNow = LocalDate.now();
        LocalDate startDateOfMonth = currentDateNow.withDayOfMonth(1);
        LocalDate startDate = startDateOfMonth;
        LocalDate endDate = startDate.plusMonths(3).minusDays(1);

        List<DateStartAndDateEnd> weekRanges = new ArrayList<>();

        LocalDate currentDate = startDate;
        while (currentDate.isBefore(endDate) || currentDate.isEqual(endDate)) {
            DateStartAndDateEnd date = new DateStartAndDateEnd();
            LocalDate weekStart = currentDate.with(DayOfWeek.MONDAY);

            LocalDate weekEnd = currentDate.with(DayOfWeek.SUNDAY);

            String formattedStartDate = weekStart.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String formattedEndDate = weekEnd.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            date.setDateStart(weekStart);
            date.setDateEnd(weekEnd);
            date.setFormattedStartDate(formattedStartDate);
            date.setFormattedEndDate(formattedEndDate);
            weekRanges.add(date);

            currentDate = currentDate.plusWeeks(1);
        }

        request.setAttribute("weekRanges", weekRanges);
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
            Logger.getLogger(ScheduleController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ScheduleController.class.getName()).log(Level.SEVERE, null, ex);
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
