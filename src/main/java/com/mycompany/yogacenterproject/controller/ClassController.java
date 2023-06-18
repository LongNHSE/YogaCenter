/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.yogacenterproject.controller;

import com.mycompany.yogacenterproject.dao.HoaDonDAO;
import com.mycompany.yogacenterproject.dao.HocVienDAO;
import com.mycompany.yogacenterproject.dao.LoaiLopHocDAO;
import com.mycompany.yogacenterproject.dao.LopHocDAO;
import com.mycompany.yogacenterproject.dao.PhongHocDAO;
import com.mycompany.yogacenterproject.dao.ScheduleDAO;
import com.mycompany.yogacenterproject.dao.SlotDAO;
import com.mycompany.yogacenterproject.dao.TrainerDAO;
import com.mycompany.yogacenterproject.dto.DateStartAndDateEnd;
import com.mycompany.yogacenterproject.dto.HoaDonDTO;
import com.mycompany.yogacenterproject.dto.HocVienDTO;
import com.mycompany.yogacenterproject.dto.LoaiLopHocDTO;

import com.mycompany.yogacenterproject.dto.LopHocDTO;
import com.mycompany.yogacenterproject.dto.LopHocIMG;
import com.mycompany.yogacenterproject.dto.ScheduleHvDTO;

import com.mycompany.yogacenterproject.dto.SlotDTO;
import com.mycompany.yogacenterproject.dto.TrainerDTO;
import com.mycompany.yogacenterproject.util.Constants;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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
public class ClassController extends HttpServlet {

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
        String action = request.getParameter("action");
        try (PrintWriter out = response.getWriter()) {
            if (action.equals("CreateClassPage")) {
                thongTinLopHocPage(request, response);
            } else if (action.equals("CreateClass")) {
//                PhongHocDAO phongHocDAO = new PhongHocDAO();
//                String slot = request.getParameter("slot");
//                String[] weekdays = request.getParameterValues("weekdays");
//                out.print(slot);
//                out.print(weekdays[0].toUpperCase());
//                out.print(weekdays[1].toUpperCase());
//                out.print(phongHocDAO.getEmptyRoom(slot, weekdays[0].toUpperCase(), weekdays[1].toUpperCase()));
                createLopHoc(request, response);
                response.sendRedirect("Admin/Class/ClassController.jsp");
            } else if (action.equals("Assign Trainer")) {
                thongTinAssignPage(request, response);
            } else if (action.equals("AssignTrainer")) {
                assignTrainer(request, response);
            } else if (action.equals("Check Empty Room")) {
                checkPhongTrong(request, response);
                RequestDispatcher rd = request.getRequestDispatcher("Admin/Class/ClassSchedule.jsp");
                rd.forward(request, response);
            } else if (action.equals("classes")) {
                showClass(request, response);
            }

//        if (action.equals("CreateClassType")) {
//            createLoaiLopHoc(request, response);
//
//        }
            /* TODO output your page here. You may use following sample code. */
        }
    }

    //GUI CAC LIST VA THONG TIN CAN THIET DE TAO LOP
    public void thongTinLopHocPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoaiLopHocDAO loaiLopHocDAO = new LoaiLopHocDAO();
        List<LoaiLopHocDTO> listLoaiLopHoc = new ArrayList<>();
        listLoaiLopHoc = loaiLopHocDAO.readLoaiLopHoc();
        request.setAttribute("listLoaiLopHoc", listLoaiLopHoc);
        String[] weekdays = request.getParameterValues("weekday");
        String slot = request.getParameter("slot");

        SlotDAO slotDAO = new SlotDAO();
        List<SlotDTO> listSlot = slotDAO.readSlot();
        request.setAttribute("listSlot", listSlot);
        request.setAttribute("weekdays", weekdays);
        request.setAttribute("slot", slot);
        RequestDispatcher rd = request.getRequestDispatcher("Admin/Class/CreateClassPage.jsp");
        rd.forward(request, response);

    }

    //DANG KY LOP 
    public void dangKyLopHoc(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        HocVienDTO hocVienDTO = (HocVienDTO) session.getAttribute("user");
        String maLopHoc = request.getParameter("maLopHoc");
        Date ngayThanhToan = null;
        ngayThanhToan = Date.valueOf(request.getParameter("ngayThanhToan"));
        long giaTien = Long.parseLong(request.getParameter("giaTien"));

        HoaDonDAO hoaDonDAO = new HoaDonDAO();
        String AUTO_HOADON_ID = String.format(Constants.MA_HOADON_FORMAT, (hoaDonDAO.lastIDIndex()) + 1);
        String maHoaDon = AUTO_HOADON_ID;

        HoaDonDTO hoaDonDTO = new HoaDonDTO();
        hoaDonDTO.setMahoaDon(maHoaDon);
        hoaDonDTO.setMaHV(hocVienDTO.getMaHV());
        hoaDonDTO.setMaLopHoc(maLopHoc);
        hoaDonDTO.setGiaTien(giaTien);
        hoaDonDTO.setNgayThanhToan(ngayThanhToan);
        try {
            hoaDonDAO.createHoaDonDTO(hoaDonDTO);
        } catch (SQLException e) {
        }

    }

    //TAO LOAI LOP HOC
    public void createLoaiLopHoc(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        LoaiLopHocDAO loaiLopHocDAO = new LoaiLopHocDAO();
        LoaiLopHocDTO loaiLopHocDTO = new LoaiLopHocDTO();

        String AUTO_MALOAILOPHOC_ID = String.format(Constants.MA_LOAILOPHOC_FORMAT, (loaiLopHocDAO.lastIDIndex() + 1));
        String maLoaiLopHoc = AUTO_MALOAILOPHOC_ID;
        String errorMessage = "";
        boolean check = true;

        String tenLoaiLopHoc = request.getParameter("tenLoaiLopHoc").trim();

        if (tenLoaiLopHoc.equals(loaiLopHocDAO.searchTenLoaiLopHoc(tenLoaiLopHoc))) {
            errorMessage += "Ten loai lop hoc da ton tai";
            check = false;
        }
        double hocPhi = Double.parseDouble(request.getParameter("hocPhi")) * 1000000;

        if (check == true) {
            loaiLopHocDTO.setHocPhi(hocPhi);
            loaiLopHocDTO.setMaLoaiLopHoc(maLoaiLopHoc);
            loaiLopHocDTO.setTenLoaiLopHoc(tenLoaiLopHoc);

            loaiLopHocDAO.createLoaiLopHoc(loaiLopHocDTO);
            response.sendRedirect("Admin/Class/ClassController.jsp");
        } else {
            request.setAttribute("errorMessage", errorMessage);
            RequestDispatcher rd = request.getRequestDispatcher("Admin/Class/CreateClassTypePage.jsp");
            rd.forward(request, response);
            try {
                rd.forward(request, response);
            } catch (IOException ex) {
                Logger.getLogger(ClassController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //TAO LOP HOC NHUNG CHUA ASSIGN GIAO VIEN check Ngay bat dau neu sai tra lai ve page
    public void createLopHoc(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        HttpSession session = request.getSession();
        LopHocDAO lopHocDAO = new LopHocDAO();
        LopHocDTO lopHocDTO = new LopHocDTO();
        ScheduleDAO scheduleDAO = new ScheduleDAO();
        String dateErrorMessage = "";
        boolean error = true;

        String AUTO_MALOPHOC_ID = String.format(Constants.MA_LOPHOC_FORMAT, (lopHocDAO.lastIDIndex() + 1));
        String maLopHoc = AUTO_MALOPHOC_ID;

        String maLoaiLopHoc = request.getParameter("listLoaiLopHoc");
        int soLuongHV = Integer.parseInt(request.getParameter("soLuongHV"));
        int soBuoi = Integer.parseInt(request.getParameter("soBuoi"));
        String maSlot = request.getParameter("slot");
        String date = request.getParameter("initializeDate");
        String[] weekdays = request.getParameterValues("weekdays");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Parse the string into a LocalDate object
        LocalDate initializeDate = LocalDate.parse(date, formatter);

        PhongHocDAO phongHocDAO = new PhongHocDAO();
        Date date2 = Date.valueOf(request.getParameter("initializeDate"));
        lopHocDTO.setMaLopHoc(maLopHoc);
        lopHocDTO.setMaLoaiLopHoc(maLoaiLopHoc);
        lopHocDTO.setMaSlot(maSlot);
        lopHocDTO.setMaRoom(phongHocDAO.getEmptyRoom(maSlot, weekdays[0].toUpperCase(), weekdays[1].toUpperCase()).getMaRoom());

        lopHocDTO.setSoBuoi(soBuoi);
        lopHocDTO.setSoLuongHV(soLuongHV);
        lopHocDTO.setNgayBatDau(date2);
        lopHocDTO.setThu(weekdays);

        lopHocDAO.addClass(lopHocDTO);
        scheduleDAO.createScheduleTemp(lopHocDTO);
        if (error == true) {

        } else {
            thongTinLopHocPage(request, response);
        }

    }

    //GUI LIST THONG TIN CAN THIET VAO ASSIGN PAGE
    public void thongTinAssignPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LoaiLopHocDAO loaiLopHocDAO = new LoaiLopHocDAO();
        LopHocDAO lopHocDAO = new LopHocDAO();
        String maLopHoc = request.getParameter("maLopHoc");
        TrainerDAO trainerDAO = new TrainerDAO();
        List<TrainerDTO> listTrainer = new ArrayList();
        listTrainer = trainerDAO.readListTrainerByTypeAndStatus(loaiLopHocDAO.searchTenLoaiLopHoc(lopHocDAO.IDLoaiLopHoc(maLopHoc)));
        request.setAttribute("listTrainer", listTrainer);
        request.setAttribute("maLopHoc", maLopHoc);

        RequestDispatcher rd = request.getRequestDispatcher("Admin/Class/AssignTrainer.jsp");
        rd.forward(request, response);

    }

    //ASSIGN GIAO VIEN VAO SCHEDULE
    public void assignTrainer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String maLopHoc = request.getParameter("maLopHoc");
        String maTrainer = request.getParameter("listTrainer");
        LopHocDAO lopHocDAO = new LopHocDAO();
        TrainerDAO trainerDAO = new TrainerDAO();
        ScheduleDAO scheduleDAO = new ScheduleDAO();
        scheduleDAO.createScheduleTrainer(maTrainer, lopHocDAO.searchClassById(maLopHoc));
        trainerDAO.updateTrainerStatus(maTrainer, true);
        response.sendRedirect("Admin/Class/ClassController.jsp");

    }

    //Tao ScheduleHv
    //!!!SAU KHI TAO HOA DON XONG SE TAO SCHEDULEHv
    public void createScheduleHv(HttpServletRequest request, HttpServletResponse response) {

    }

    // Show Class
    public void showClass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<LopHocIMG> listCate = new ArrayList<>();
        LoaiLopHocDAO loaiLopHocDAO = new LoaiLopHocDAO();
        listCate = loaiLopHocDAO.getAllCategories();
        request.setAttribute("listCate", listCate);
        RequestDispatcher rd = request.getRequestDispatcher("/Home/ClassCategories.jsp");
        rd.forward(request, response);
    }

    //Chon phong gom thuoc tinh slot va thu
    public void chooseRoom(HttpServletRequest request, HttpServletResponse response) {

    }

    public void checkPhongTrong(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        SlotDAO slotDAO = new SlotDAO();

        List<SlotDTO> listSlot = slotDAO.readSlot();
        request.setAttribute("listSlot", listSlot);

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
        date(request, response);
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
            Logger.getLogger(ClassController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ClassController.class.getName()).log(Level.SEVERE, null, ex);
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
