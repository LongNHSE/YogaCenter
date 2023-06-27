/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.yogacenterproject.controller;

import com.mycompany.yogacenterproject.dao.HocVienDAO;

import com.mycompany.yogacenterproject.dao.HoaDonDAO;
import com.mycompany.yogacenterproject.dao.HocVienDAO;
import com.mycompany.yogacenterproject.dao.LopHocDAO;
import com.mycompany.yogacenterproject.dao.ScheduleDAO;
import com.mycompany.yogacenterproject.dao.SlotDAO;
import com.mycompany.yogacenterproject.dao.TrainerDAO;
import com.mycompany.yogacenterproject.dto.DateStartAndDateEnd;
import com.mycompany.yogacenterproject.dto.HoaDonDTO;
import com.mycompany.yogacenterproject.dto.HocVienDTO;
import com.mycompany.yogacenterproject.dto.LopHocDTO;
import com.mycompany.yogacenterproject.dto.ScheduleHvDTO;
import com.mycompany.yogacenterproject.dto.ScheduleTempDTO;
import com.mycompany.yogacenterproject.dto.ScheduleTrainerDTO;
import com.mycompany.yogacenterproject.dto.SlotDTO;
import com.mycompany.yogacenterproject.dto.TrainerDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Oalskad
 */
public class AdminController extends HttpServlet {

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
            HttpSession session = request.getSession();
            String action = request.getParameter("action");

            if (action.equals("listHocVien")) {
                listHocVienDTO(request, response);
            } else if (action.equals("detailHocVien")) {

            } else if (action.equals("View Receipt")) {
                listReceipt(request, response);
            } else if (action.equals("listTrainer")) {
                listTrainerDTO(request, response);
            } else if (action.equals("detailTrainer")) {

            } else if (action.equals("listLopHoc")) {
                listClass(request, response);
            } else if (action.equals("detailLopHoc")) {

            } else if (action.equals("listClassUnassigned")) {
                listClassUnassigned(request, response);
            } else if (action.equals("ViewSchedule")) {
                listSchedule(request, response);
                date(request, response);
                RequestDispatcher rd = request.getRequestDispatcher("./Authorization/Admin/Class/Schedule.jsp");
                rd.forward(request, response);

            }
        }
    }

    public void listHocVienDTO(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<HocVienDTO> listHocVienDTO = new ArrayList<HocVienDTO>();
        HocVienDAO hocVienDAO = new HocVienDAO();
        listHocVienDTO = hocVienDAO.readListHocVien();
        request.setAttribute("listHocVienDTO", listHocVienDTO);
//        response.sendRedirect("./Authorization/Admin/Trainee/HocVienList.jsp");
        RequestDispatcher rs = request.getRequestDispatcher("./Authorization/Admin/Trainee/HocVienList.jsp");
        rs.forward(request, response);

    }

    public void listTrainerDTO(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<TrainerDTO> listTrainerDTO = new ArrayList<TrainerDTO>();
        TrainerDAO trainerDAO = new TrainerDAO();

        int pageID = Integer.parseInt(request.getParameter("page"));
        // 1 Page has 10 classes
        int total = 5;
        int pageCount = pageID;
        if (pageID == 1) {

        } else {
            pageID = pageID - 1;
            pageID = pageID * total + 1;
        }
        listTrainerDTO = trainerDAO.readListTrainerWithRecord(pageID, total);
        int count = (int) Math.ceil((trainerDAO.countRecord() + total - 1) / total);
        request.setAttribute("listTrainerDTO", listTrainerDTO);
        request.setAttribute("count", count);
        request.setAttribute("pageCount", pageCount);
        RequestDispatcher rd = request.getRequestDispatcher("./Authorization/Admin/Trainer/TrainerList.jsp");
        rd.forward(request, response);
    }

    public void listReceipt(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<HoaDonDTO> listHoaDon = new ArrayList<HoaDonDTO>();
        HoaDonDAO hoaDonDAO = new HoaDonDAO();

        listHoaDon = hoaDonDAO.listHoaDon(request.getParameter("maHV"));

        request.setAttribute("listHoaDon", listHoaDon);
        RequestDispatcher rs = request.getRequestDispatcher("./Authorization/Admin/Trainee/HoaDonList.jsp");
        rs.forward(request, response);

    }

    public void listClassUnassigned(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<LopHocDTO> listLopHocTemp = new ArrayList();
        LopHocDAO lopHocDAO = new LopHocDAO();
        listLopHocTemp = lopHocDAO.listLopTemp();

        request.setAttribute("listLopHocTemp", listLopHocTemp);
        RequestDispatcher rd = request.getRequestDispatcher("./Authorization/Admin/Class/ListClassUnassigned.jsp");
        rd.forward(request, response);

    }

    //THIS CLASS USE PAGINATION METHOD
    public void listClass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<LopHocDTO> listLopHocDTO = new ArrayList<>();
        LopHocDAO lopHocDAO = new LopHocDAO();
        int pageID = Integer.parseInt(request.getParameter("page"));
        // 1 Page has 10 classes
        int total = 5;
        int pageCount = pageID;
        if (pageID == 1) {

        } else {
            pageID = pageID - 1;
            pageID = pageID * total + 1;
        }
        listLopHocDTO = lopHocDAO.readListClassRecord(pageID, total);
        int count = (int) Math.ceil((lopHocDAO.countRecord() + total - 1) / total);
        request.setAttribute("listLopHocDTO", listLopHocDTO);
        request.setAttribute("count", count);
        request.setAttribute("pageCount", pageCount);
        RequestDispatcher rd = request.getRequestDispatcher("./Authorization/Admin/Class/ListClass.jsp");
        rd.forward(request, response);

    }

    public void listSchedule(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<ScheduleTempDTO> listScheduleTemp = new ArrayList();
        LopHocDAO lopHocDAO = new LopHocDAO();
        List<ScheduleTrainerDTO> listScheduleTrainer = new ArrayList();
        ScheduleDAO scheduleDAO = new ScheduleDAO();
        listScheduleTemp = scheduleDAO.readScheduleTemp();
        listScheduleTrainer = scheduleDAO.readScheduleTrainer();
        SlotDAO slotDAO = new SlotDAO();

        List<SlotDTO> listSlot = slotDAO.readSlot();
        request.setAttribute("listSlot", listSlot);
        request.setAttribute("listScheduleTrainer", listScheduleTrainer);

        request.setAttribute("listScheduleTemp", listScheduleTemp);
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
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
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
