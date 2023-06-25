/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.yogacenterproject.controller;

import com.mycompany.yogacenterproject.dao.HoaDonDAO;
import com.mycompany.yogacenterproject.dao.HocVienDAO;
import com.mycompany.yogacenterproject.dao.LoaiLopHocDAO;
import com.mycompany.yogacenterproject.dao.LopHocDAO;
import com.mycompany.yogacenterproject.dao.LopHocImageDAO;
import com.mycompany.yogacenterproject.dao.PhongHocDAO;
import com.mycompany.yogacenterproject.dao.ScheduleDAO;
import com.mycompany.yogacenterproject.dao.SlotDAO;
import com.mycompany.yogacenterproject.dao.TrainerDAO;
import com.mycompany.yogacenterproject.dto.DateStartAndDateEnd;
import com.mycompany.yogacenterproject.dto.HoaDonDTO;
import com.mycompany.yogacenterproject.dto.HocVienDTO;
import com.mycompany.yogacenterproject.dto.LoaiLopHocDTO;

import com.mycompany.yogacenterproject.dto.LopHocDTO;
import com.mycompany.yogacenterproject.dto.LopHocIMGDTO;
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
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Oalskad
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
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
        String maLopHoc = "";
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        try {
            PrintWriter out = response.getWriter();
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
            } else if (action.equals("CheckEmptyRoom")) {
                checkPhongTrong(request, response);
                RequestDispatcher rd = request.getRequestDispatcher("Admin/Class/ClassSchedule.jsp");
                rd.forward(request, response);
            } else if (action.equals("classes")) {
                showClass(request, response);
            } else if (action.equals("payment")) {
                payment(request, response);
            } else if (action.equals("Class category information")) {

            }else if(action.equals("Register")){
                checkAvailability(request, response);
            }else if(action.equals("showDetails")){
                  showDetails(request, response);
           
            } else if (action.equals("CreateClassType")) {
//                out.print(action);
                createLoaiLopHoc(request, response);
                insertImg(request, response);
                insertThumbImg(request, response);

            } else if (action.equals("Class Detail")) {
//                out.print(action);
                classDetail(request, response);

            }
             /* TODO output your page here. You may use following sample code. */
        } catch (Exception e) {

        }

    }

    //ADD Image danh cho Create Class Type
    private void insertThumbImg(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

        LopHocImageDAO lopHocImageDAO = new LopHocImageDAO();
        List<byte[]> imageList = new ArrayList<>();
        LoaiLopHocDAO loaiLopHocDAO = new LoaiLopHocDAO();
        LopHocIMGDTO lopHocImageDTO = new LopHocIMGDTO();
        String tenLoaiLopHoc = request.getParameter("tenLoaiLopHoc").trim();
        String AUTO_IMG_ID = String.format(Constants.MA_IMG_FORMAT, (lopHocImageDAO.lastIDIndex() + 1));

        //HOCVIEN CONSTRUCTOR
        String maAnh = AUTO_IMG_ID;
        lopHocImageDTO.setMaAnh(maAnh);
        lopHocImageDTO.setMaLoaiLopHoc(loaiLopHocDAO.searchIdLoaiLopHoc(tenLoaiLopHoc));
        lopHocImageDTO.setTenAnh("THUMBNAIL");

        String imageThumbArray = request.getParameter("Thumbnails");
        List<String> listAnhThumb = new ArrayList<>();
        List<byte[]> imageListThumb = new ArrayList<>();

        String base64String = imageThumbArray.substring(imageThumbArray.indexOf(",") + 1);
        byte[] imageData = Base64.getDecoder().decode(base64String);
        imageListThumb.add(imageData);

        lopHocImageDAO.insertImageDataFromDatabase(imageListThumb, lopHocImageDTO);
    }

    private void insertImg(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        LopHocImageDAO lopHocImageDAO = new LopHocImageDAO();
        List<byte[]> imageList = new ArrayList<>();
        LoaiLopHocDAO loaiLopHocDAO = new LoaiLopHocDAO();
        LopHocIMGDTO lopHocImageDTO = new LopHocIMGDTO();
        String tenLoaiLopHoc = request.getParameter("tenLoaiLopHoc").trim();
        String AUTO_IMG_ID = String.format(Constants.MA_IMG_FORMAT, (lopHocImageDAO.lastIDIndex() + 1));

        //HOCVIEN CONSTRUCTOR
        String maAnh = AUTO_IMG_ID;
        lopHocImageDTO.setMaAnh(maAnh);
        lopHocImageDTO.setMaLoaiLopHoc(loaiLopHocDAO.searchIdLoaiLopHoc(tenLoaiLopHoc));

        String[] imageArray = request.getParameter("listImage").split("\\s+");
        List<String> listAnh = new ArrayList<>();
        for (String a : imageArray) {
            String base64String = a.substring(a.indexOf(",") + 1);
            byte[] imageData = Base64.getDecoder().decode(base64String);
            imageList.add(imageData);
            listAnh.add(a);
        }
        lopHocImageDAO.insertImageDataFromDatabase(imageList, lopHocImageDTO);

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
    public void dangKyLopHoc(HttpServletRequest request, HttpServletResponse response, String maLopHoc) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();

            HocVienDTO hocVienDTO = (HocVienDTO) session.getAttribute("hocVienDTO");
            Date ngayThanhToan = Date.valueOf(LocalDate.now());
            String maLoaiLopHoc = request.getParameter("returnID");
            LoaiLopHocDAO loaiLopHocDAO = new LoaiLopHocDAO();
            long hocPhi = Long.parseLong(loaiLopHocDAO.searchHocPhiLopHoc(maLoaiLopHoc).replaceAll("\\.", ""));

            HoaDonDAO hoaDonDAO = new HoaDonDAO();
            String AUTO_HOADON_ID = String.format(Constants.MA_HOADON_FORMAT, (hoaDonDAO.lastIDIndex()) + 1);
            String maHoaDon = AUTO_HOADON_ID;

            HoaDonDTO hoaDonDTO = new HoaDonDTO();
            hoaDonDTO.setMahoaDon(maHoaDon);
            hoaDonDTO.setMaHV(hocVienDTO.getMaHV());
            hoaDonDTO.setMaLopHoc(maLopHoc);
            hoaDonDTO.setGiaTien(hocPhi);
            hoaDonDTO.setNgayThanhToan(ngayThanhToan);

            hoaDonDAO.createHoaDonDTO(hoaDonDTO);
            LopHocDAO lopHocDAO = new LopHocDAO();
            lopHocDAO.increase(maLopHoc);

            createScheduleHv(request, response, hocVienDTO.getMaHV(), maLopHoc);

            RequestDispatcher rd = request.getRequestDispatcher("/ClassController?action=classes");
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
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
            loaiLopHocDTO.setDescription(request.getParameter("description").trim());
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
        listTrainer = trainerDAO.readListTrainerByTypeAndStatus(loaiLopHocDAO.searchIdLoaiLopHoc(lopHocDAO.IDLoaiLopHoc(maLopHoc)));
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
        scheduleDAO.deleteScheduleTemp(maLopHoc);
        trainerDAO.updateTrainerStatus(maTrainer, true);
        response.sendRedirect("Admin/Class/ClassController.jsp");

    }

    //Tao ScheduleHv
    //!!!SAU KHI TAO HOA DON XONG SE TAO SCHEDULEHv
    public void createScheduleHv(HttpServletRequest request, HttpServletResponse response, String maHV, String maLopHoc) throws SQLException, IOException, ServletException {

        ScheduleDAO scheduleDAO = new ScheduleDAO();
        ScheduleHvDTO scheduleHvDTO = new ScheduleHvDTO();

        scheduleHvDTO.setMaHV(maHV);
        scheduleHvDTO.setMaLopHoc(maLopHoc);

        scheduleDAO.createScheduleHV(maHV, maLopHoc);

        RequestDispatcher rd = request.getRequestDispatcher("/ClassController?action=classes");
        rd.forward(request, response);
    }

    // Show Class
    public void showClass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<LoaiLopHocDTO> listCate = new ArrayList<>();
        LoaiLopHocDAO loaiLopHocDAO = new LoaiLopHocDAO();
        listCate = loaiLopHocDAO.getAllLoaiLopHoc();
        request.setAttribute("listCate", listCate);
        RequestDispatcher rd = request.getRequestDispatcher("/Home/ClassCategories.jsp");
        rd.forward(request, response);

    }

    //Chon phong gom thuoc tinh slot va thu
    public void payment(HttpServletRequest request, HttpServletResponse response) {

    }
    
    public void chooseSlot(){
        
    }

    public void checkAvailability(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        LopHocDAO LopHocDAO = new LopHocDAO();
        String maLoaiLopHoc = request.getParameter("returnID");
        List<LopHocDTO> list = LopHocDAO.searchByType(maLoaiLopHoc);
        String error = "";
        String transfer = "";
        for (LopHocDTO x : list) {
            
                if (x.getSoLuongHvHienTai() < x.getSoLuongHV()) {
                    transfer = x.getMaLopHoc();
                    dangKyLopHoc(request, response, transfer);
                }
            
        }
        error = "Classes are fully reserved.";
        request.setAttribute("error", error);
        RequestDispatcher rd = request.getRequestDispatcher("/ClassController?action=classes");
        rd.forward(request, response);

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

    public void createClassInformation(HttpServletRequest request, HttpServletResponse response) {

    }

//    Show classes' details

    public void showDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String cid = request.getParameter("returnID");
//          Get class information
        LoaiLopHocDAO dao = new LoaiLopHocDAO();
        LoaiLopHocDTO classDetails = dao.getClassCateByID(cid);
        request.setAttribute("details", classDetails);
//          Get class images
        LopHocImageDAO imgdao = new LopHocImageDAO();
        List<LopHocIMGDTO> list = imgdao.getImageBasedOnTypeID(cid);
        request.setAttribute("imageListByID", list);
        RequestDispatcher rd = request.getRequestDispatcher("/Class/ClassDetail.jsp");
        rd.forward(request, response);
    }

    public void classDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String classID = request.getParameter("maLopHoc");
        LopHocDAO lopHocDAO = new LopHocDAO();
        LopHocDTO lopHocDTO = new LopHocDTO();
        lopHocDTO = lopHocDAO.searchClassById(classID);
        List<HocVienDTO> listHocVienDTO = new ArrayList<HocVienDTO>();
        HocVienDAO hocVienDAO = new HocVienDAO();
        listHocVienDTO = hocVienDAO.readListHocVienWithScheduleHV(classID);
        TrainerDAO trainerDAO = new TrainerDAO();
        TrainerDTO trainerDTO = trainerDAO.searchTrainerByClassID(classID);

        request.setAttribute("listHocVienDTO", listHocVienDTO);
        request.setAttribute("lopHocDTO", lopHocDTO);
        request.setAttribute("trainerDTO", trainerDTO);
        RequestDispatcher rd = request.getRequestDispatcher("/Admin/Class/ClassDetail.jsp");
        rd.forward(request, response);
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
