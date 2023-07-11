/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.yogacenterproject.controller;

import PayPal.PaymentServices;

import com.mycompany.yogacenterproject.dao.AttendanceDAO;
import com.mycompany.yogacenterproject.dao.DescriptionDAO;
import com.mycompany.yogacenterproject.dao.EmailController;
import com.mycompany.yogacenterproject.dao.HoaDonDAO;
import com.mycompany.yogacenterproject.dao.HocVienDAO;
import com.mycompany.yogacenterproject.dao.LoaiLopHocDAO;
import com.mycompany.yogacenterproject.dao.LopHocDAO;
import com.mycompany.yogacenterproject.dao.LopHocImageDAO;
import com.mycompany.yogacenterproject.dao.PhongHocDAO;
import com.mycompany.yogacenterproject.dao.ScheduleDAO;
import com.mycompany.yogacenterproject.dao.SemesterDAO;
import com.mycompany.yogacenterproject.dao.SlotDAO;
import com.mycompany.yogacenterproject.dao.TrainerDAO;
import com.mycompany.yogacenterproject.dto.AttendanceDTO;
import com.mycompany.yogacenterproject.dto.DateStartAndDateEnd;
import com.mycompany.yogacenterproject.dto.DayAndSlot;
import com.mycompany.yogacenterproject.dto.DescriptionDTO;
import com.mycompany.yogacenterproject.dto.HoaDonDTO;
import com.mycompany.yogacenterproject.dto.HocVienDTO;
import com.mycompany.yogacenterproject.dto.LoaiLopHocDTO;

import com.mycompany.yogacenterproject.dto.LopHocDTO;
import com.mycompany.yogacenterproject.dto.LopHocIMGDTO;
import com.mycompany.yogacenterproject.dto.PhongHocDTO;
import com.mycompany.yogacenterproject.dto.SemesterDTO;

import com.mycompany.yogacenterproject.dto.SlotDTO;
import com.mycompany.yogacenterproject.dto.TrainerDTO;
import com.mycompany.yogacenterproject.util.Constants;
import com.paypal.base.rest.PayPalRESTException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.mail.EmailException;

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
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        try {
            PrintWriter out = response.getWriter();
            if (action.equals("CreateClassPage")) {
                thongTinLopHocPage(request, response);
            } else if (action.equals("CreateClass")) {
                createLopHoc(request, response);
                response.sendRedirect("Authorization/Admin/Class/ClassController.jsp");
            } else if (action.equals("Assign Trainer")) {
                thongTinAssignPage(request, response);
            } else if (action.equals("AssignTrainer")) {
//                String maLopHoc = request.getParameter("maLopHoc");
//                String maTrainer = request.getParameter("listTrainer");
//                out.print(maLopHoc);
//                out.print(maTrainer);
                assignTrainer(request, response);
            } else if (action.equals("CheckEmptyRoom")) {
                checkPhongTrong(request, response);
                RequestDispatcher rd = request.getRequestDispatcher("Authorization/Admin/Class/ClassSchedule.jsp");
                rd.forward(request, response);
            } else if (action.equals("classes")) {
                showClass(request, response);
            } else if (action.equals("SuccessfulPayment")) {
                assignClassAfterPayment(request, response);

            } else if (action.equals("Register")) {
                payment(request, response);
            } else if (action.equals("showDetails")) {
                showDetails(request, response);
            } else if (action.equals("CreateClassType")) {
//                out.print(request.getParameter("description").trim());
                createLoaiLopHoc(request, response);
                insertImg(request, response);
                insertThumbImg(request, response);
            } else if (action.equals("Class Detail")) {
                classDetail(request, response);
            } else if (action.equals("Update")) {

                updateClassPage(request, response);
            } else if (action.equals("UpdateClass")) {
                updateClass(request, response);
            } else if (action.equals("Delete")) {
                deleteClass(request, response);
            } else if (action.equals("ClassDetailTrainee") || action.equals("Detail")) {
                classDetailTrainee(request, response);
            } else if (action.equals("ClassDetailTrainer")) {
                classDetailTrainer(request, response);
            } else if (action.equals("Change Status")) {
                changeStatusClassType(request, response);
            }
        } catch (Exception e) {

        }

    }

    private void changeStatusClassType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String maLoaiLopHoc = request.getParameter("maLoaiLopHoc");
        LoaiLopHocDAO loaiLopHocDAO = new LoaiLopHocDAO();
        boolean status = loaiLopHocDAO.searchStatusLoaiLopHoc(maLoaiLopHoc);
        loaiLopHocDAO.changeStatus(maLoaiLopHoc, !status);

        RequestDispatcher rd = request.getRequestDispatcher("./AdminController?action=listClassType");
        rd.forward(request, response);
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
        SemesterDAO semesterDAO = new SemesterDAO();
        SemesterDTO semesterDTO = semesterDAO.getCurrentSemester();
        listLoaiLopHoc = loaiLopHocDAO.readLoaiLopHoc();
        request.setAttribute("listLoaiLopHoc", listLoaiLopHoc);
        String[] weekdays = request.getParameterValues("weekday");
        String slot = request.getParameter("slot");

        SlotDAO slotDAO = new SlotDAO();
        List<SlotDTO> listSlot = slotDAO.readSlot();
        request.setAttribute("semesterDTO", semesterDTO);
        request.setAttribute("listSlot", listSlot);
        request.setAttribute("weekdays", weekdays);
        request.setAttribute("slot", slot);
        RequestDispatcher rd = request.getRequestDispatcher("Authorization/Admin/Class/CreateClassPage.jsp");
        rd.forward(request, response);

    }

    //TAO LOAI LOP HOC
    public void createLoaiLopHoc(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        LoaiLopHocDAO loaiLopHocDAO = new LoaiLopHocDAO();
        LoaiLopHocDTO loaiLopHocDTO = new LoaiLopHocDTO();
        DescriptionDAO descriptionDAO = new DescriptionDAO();
        DescriptionDTO descriptionDTO = new DescriptionDTO();

        String AUTO_MALOAILOPHOC_ID = String.format(Constants.MA_LOAILOPHOC_FORMAT, (loaiLopHocDAO.lastIDIndex() + 1));
        String maLoaiLopHoc = AUTO_MALOAILOPHOC_ID;
        String AUTO_DESCRIPTION_ID = String.format(Constants.MA_DESCRIPTION_FORMAT, (descriptionDAO.lastIDIndex() + 1));
        String maDescription = AUTO_DESCRIPTION_ID;
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
            loaiLopHocDTO.setMaDescription(maDescription);
            descriptionDTO.setMaDescription(maDescription);
            descriptionDTO.setTitle(request.getParameter("title").trim());
            descriptionDTO.setContent(request.getParameter("description").trim());

            descriptionDAO.createDescriptionDTO(descriptionDTO);
            loaiLopHocDAO.createLoaiLopHoc(loaiLopHocDTO);
            response.sendRedirect("Authorization/Admin/Class/ClassController.jsp");
        } else {
            request.setAttribute("errorMessage", errorMessage);
            RequestDispatcher rd = request.getRequestDispatcher("Authorization/Admin/Class/CreateClassTypePage.jsp");
            rd.forward(request, response);

        }
    }

    //TAO LOP HOC NHUNG CHUA ASSIGN GIAO VIEN check Ngay bat dau neu sai tra lai ve page
    public void createLopHoc(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        HttpSession session = request.getSession();
        LopHocDAO lopHocDAO = new LopHocDAO();
        LopHocDTO lopHocDTO = new LopHocDTO();
        ScheduleDAO scheduleDAO = new ScheduleDAO();
        String errorMessage = "";
        SemesterDAO semesterDAO = new SemesterDAO();
        SemesterDTO semesterDTO = new SemesterDTO();
        semesterDTO = semesterDAO.getCurrentSemester();
        String dateLimit = "";
        boolean error = true;

        String AUTO_MALOPHOC_ID = String.format(Constants.MA_LOPHOC_FORMAT, (lopHocDAO.lastIDIndex() + 1));
        String maLopHoc = AUTO_MALOPHOC_ID;

        String maLoaiLopHoc = request.getParameter("listLoaiLopHoc");
        int soLuongHV = Integer.parseInt(request.getParameter("soLuongHV"));
        int soBuoi = Integer.parseInt(request.getParameter("soBuoi"));

        String date = request.getParameter("initializeDate");
        String[] weekdays = request.getParameterValues("weekdays");
        String maSlot = request.getParameter("slot");
        Date date2 = Date.valueOf(request.getParameter("initializeDate"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (scheduleDAO.getLastDate(weekdays, date2, soBuoi).before(semesterDTO.getEndDate())) {
            // Parse the string into a LocalDate object
            LocalDate initializeDate = LocalDate.parse(date, formatter);

            PhongHocDAO phongHocDAO = new PhongHocDAO();

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
        } else {
            errorMessage += "The last date of this class exceeds the ending date of this semester.";
            LoaiLopHocDAO loaiLopHocDAO = new LoaiLopHocDAO();
            List<LoaiLopHocDTO> listLoaiLopHoc = new ArrayList<>();
            listLoaiLopHoc = loaiLopHocDAO.readLoaiLopHoc();
            request.setAttribute("listLoaiLopHoc", listLoaiLopHoc);
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String dateString = dateFormat.format(scheduleDAO.getLastDate(weekdays, date2, soBuoi));

            System.out.println("Formatted date string: " + dateString);
            String slot = request.getParameter("slot");

            SlotDAO slotDAO = new SlotDAO();
            List<SlotDTO> listSlot = slotDAO.readSlot();
            request.setAttribute("semesterDTO", semesterDTO);
            request.setAttribute("listSlot", listSlot);
            request.setAttribute("weekdays", weekdays);
            request.setAttribute("slot", slot);
            request.setAttribute("errorMessage", errorMessage);
            request.setAttribute("dateString", dateString);
            RequestDispatcher rd = request.getRequestDispatcher("Authorization/Admin/Class/CreateClassPage.jsp");
            rd.forward(request, response);
        }

    }

    //GUI LIST THONG TIN CAN THIET VAO ASSIGN PAGE
    public void thongTinAssignPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LoaiLopHocDAO loaiLopHocDAO = new LoaiLopHocDAO();
        LopHocDAO lopHocDAO = new LopHocDAO();
        String maLopHoc = request.getParameter("maLopHoc");
        TrainerDAO trainerDAO = new TrainerDAO();
        List<TrainerDTO> listTrainer = new ArrayList();

        listTrainer = trainerDAO.readListTrainerByTypeAndStatus(lopHocDAO.IDLoaiLopHoc(maLopHoc));

        request.setAttribute("listTrainer", listTrainer);
        request.setAttribute("maLopHoc", maLopHoc);

        RequestDispatcher rd = request.getRequestDispatcher("Authorization/Admin/Class/AssignTrainer.jsp");
        rd.forward(request, response);

    }

    //ASSIGN GIAO VIEN VAO SCHEDULE
    public void assignTrainer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, EmailException {
        String maLopHoc = request.getParameter("maLopHoc");
        String maTrainer = request.getParameter("listTrainer");
        LopHocDAO lopHocDAO = new LopHocDAO();

        TrainerDAO trainerDAO = new TrainerDAO();
        ScheduleDAO scheduleDAO = new ScheduleDAO();
        scheduleDAO.createScheduleTrainer(maTrainer, lopHocDAO.searchClassById(maLopHoc));
        scheduleDAO.deleteScheduleTemp(maLopHoc);
        trainerDAO.updateTrainerStatus(maTrainer, true);
        EmailController.trainerDTOAssign(trainerDAO.readTrainer(maTrainer), lopHocDAO.searchClassById(maLopHoc));
        response.sendRedirect("Authorization/Admin/Class/ClassController.jsp");

    }

    // Show Class
    public void showClass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<LoaiLopHocDTO> listCate = new ArrayList<>();
        LoaiLopHocDAO loaiLopHocDAO = new LoaiLopHocDAO();
        listCate = loaiLopHocDAO.getAllLoaiLopHocAvailable();
        request.setAttribute("listCate", listCate);
        RequestDispatcher rd = request.getRequestDispatcher("/Authentication/ClassCategories.jsp");
        rd.forward(request, response);

    }

    //TRA TIEN BANG MAU PAY WITH BLOOD IT IS RETRIBUTION
    public void payment(HttpServletRequest request, HttpServletResponse response)  {
        try {
            String maLoaiLopHoc = request.getParameter("maLoaiLopHoc");
            boolean error = true;
            HttpSession session = request.getSession();
            LoaiLopHocDAO loaiLopHocDAO = new LoaiLopHocDAO();
            AttendanceDAO attendanceDAO = new AttendanceDAO();
            if (session.getAttribute("hocVienDTO") != null) {
                HocVienDTO hocVienDTO = (HocVienDTO) session.getAttribute("hocVienDTO");
                LopHocDTO lopHocDTO = new LopHocDTO();
                LopHocDAO lopHocDAO = new LopHocDAO();
                HoaDonDAO hoaDonDAO = new HoaDonDAO();
                String errorMessage = "";
//
                String selectedValue = request.getParameter("maSlot");

                // Split the selected value to retrieve maSlot and thuList
                String[] parts = selectedValue.split("\\|");
                String selectedMaSlot = parts[0];
                String selectedThuList = parts[1];

                // Remove the square brackets and spaces from the string
                String cleanedValue = selectedThuList.replaceAll("[\\[\\]\\s]", "");

// Split the cleaned value into individual elements
                String[] elements = cleanedValue.split(",");

// Convert the array to a List<String>
                String maSlot = selectedMaSlot;
                List<String> thuList = new ArrayList<>(Arrays.asList(elements));

                String maLopHoc = lopHocDAO.searchForPayment(maSlot, maLoaiLopHoc, thuList);

                ScheduleDAO scheduleDAO = new ScheduleDAO();
                if (!checkAvailability(request, response, maLopHoc)) {
                    error = false;
                    errorMessage += "Classes are fully reserved.";
                }
                if (!checkTraineeSchedule(request, response, maSlot, thuList, hocVienDTO.getMaHV())) {
                    error = false;
                    errorMessage += "You already have a class scheduled for this time slot.";
                }
                //check availability before registering
                if (error) {
                    lopHocDTO = lopHocDAO.searchClassById(maLopHoc);
                    PaymentServices paymentServices = new PaymentServices();
                    String approvalLink = paymentServices.createPayment(lopHocDTO, hocVienDTO);
                    response.sendRedirect(approvalLink);
                } else {
                    request.setAttribute("error", errorMessage);
                    showDetails(request, response);
                }
            } else {
                String url = "/YogaCenter/ClassController?action=showDetails&returnID=" + maLoaiLopHoc;
                session.setAttribute("redirectUrl", url);
                RequestDispatcher rd = request.getRequestDispatcher("./Public/signin.jsp");
                rd.forward(request, response);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //CHECK IF THE TRAINEE ALREADY HAS CLASS IN THAT SLOT        //CHECK IF THE TRAINEE ALREADY HAS CLASS IN THAT SLOT

    public void assignClassAfterPayment(HttpServletRequest request, HttpServletResponse response) throws SQLException, NumberFormatException, ServletException, IOException, EmailException {
        HttpSession session = request.getSession();
        if (session.getAttribute("hocVienDTO") != null) {
            String maLopHoc = request.getParameter("returnID");
            HocVienDTO hocVienDTO = (HocVienDTO) session.getAttribute("hocVienDTO");

            Date ngayThanhToan = Date.valueOf(LocalDate.now());

            LopHocDAO lopHocDAO = new LopHocDAO();
            LoaiLopHocDAO loaiLopHocDAO = new LoaiLopHocDAO();
            HoaDonDAO hoaDonDAO = new HoaDonDAO();
            ScheduleDAO scheduleDAO = new ScheduleDAO();
            AttendanceDAO attendanceDAO = new AttendanceDAO();

            String maLoaiLopHoc = lopHocDAO.IDLoaiLopHoc(maLopHoc);

            long hocPhi = Long.parseLong(loaiLopHocDAO.searchHocPhiLopHoc(maLoaiLopHoc).replaceAll("\\.", ""));

            String AUTO_HOADON_ID = String.format(Constants.MA_HOADON_FORMAT, (hoaDonDAO.lastIDIndex()) + 1);
            String maHoaDon = AUTO_HOADON_ID;

            HoaDonDTO hoaDonDTO = new HoaDonDTO();
            hoaDonDTO.setMahoaDon(maHoaDon);
            hoaDonDTO.setMaHV(hocVienDTO.getMaHV());
            hoaDonDTO.setMaLopHoc(maLopHoc);
            hoaDonDTO.setGiaTien(hocPhi);
            hoaDonDTO.setNgayThanhToan(ngayThanhToan);

            hoaDonDAO.createHoaDonDTO(hoaDonDTO);

            lopHocDAO.increase(maLopHoc);

            scheduleDAO.createScheduleHV(hocVienDTO.getMaHV(), maLopHoc);
            attendanceDAO.createAttendance(scheduleDAO.readScheduleHvDTO(hocVienDTO.getMaHV()));
            sendMailClassRegister(request, response, lopHocDAO.getClassOfTrainee(maLopHoc));
            RequestDispatcher rd = request.getRequestDispatcher("/ClassController?action=classes");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("./Public/signin.jsp");
            rd.forward(request, response);

        }
    }

    public void sendMailClassRegister(HttpServletRequest request, HttpServletResponse response, LopHocDTO lopHocDTO) throws EmailException, MalformedURLException {
        HttpSession session = request.getSession();
        if (session.getAttribute("hocVienDTO") != null) {
            String maLopHoc = request.getParameter("returnID");
            HocVienDTO hocVienDTO = (HocVienDTO) session.getAttribute("hocVienDTO");

            EmailController.ClassRegister(lopHocDTO, hocVienDTO.getEmail());
        }
    }
    //CHECK IF THE TRAINEE ALREADY HAS CLASS IN THAT SLOT

    public boolean checkTraineeSchedule(HttpServletRequest request, HttpServletResponse response, String maSlot, List<String> thuList, String maHocVien) {
        ScheduleDAO scheduleDAO = new ScheduleDAO();
        return scheduleDAO.checkTraineeSchedule(maSlot, maHocVien, thuList);
    }

    public boolean checkAvailability(HttpServletRequest request, HttpServletResponse response, String maLopHoc) throws ServletException, IOException, SQLException {
        LopHocDAO lopHocDAO = new LopHocDAO();

        LopHocDTO list = lopHocDAO.searchClassById(maLopHoc);
        String error = "";

        if (list.getSoLuongHvHienTai() < list.getSoLuongHV()) {
            return true;
        }
        return false;

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

//    Show classes' details
    public void showDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String cid = request.getParameter("returnID");
        LopHocDAO lopHocDAO = new LopHocDAO();
//          Get class information
        LoaiLopHocDAO dao = new LoaiLopHocDAO();
        LoaiLopHocDTO classDetails = dao.getClassCateByID(cid);
//        LopHocDTO lopHocDTO = new LopHocDTO();
        List<LopHocDTO> listLopHocDTO = lopHocDAO.showClassesByType(cid);
        DescriptionDTO descriptionDTO = new DescriptionDTO();
        DescriptionDAO descriptionDAO = new DescriptionDAO();

        descriptionDTO = descriptionDAO.readDescription(cid);
        request.setAttribute("descriptionDTO", descriptionDTO);
        request.setAttribute("details", classDetails);
//          Get class images
        LopHocImageDAO imgdao = new LopHocImageDAO();
        List<LopHocIMGDTO> list = imgdao.getImageBasedOnTypeID(cid);
        request.setAttribute("imageListByID", list);

        List<DayAndSlot> listDayAndSlot = new ArrayList<>();
        for (int i = 0; i < listLopHocDTO.size(); i++) {
            DayAndSlot dayAndSlot = new DayAndSlot();
            String currentSlot = listLopHocDTO.get(i).getMaSlot();
            List<String> thu = listLopHocDTO.get(i).getThuList();
            if (i != 0) {
                for (int j = 1; j < i; j++) {
                    if (currentSlot.equals(listLopHocDTO.get(j).getMaSlot())) {
                        if (LopHocDAO.compareLists(listLopHocDTO.get(i).getThuList(), listLopHocDTO.get(j).getThuList())) {
//                            System.out.println(currentSlot + thu);
                        }
                    } else {

                        dayAndSlot.setSlot(currentSlot);
                        dayAndSlot.setTimeStart(listLopHocDTO.get(i).getTimeStart());
                        dayAndSlot.setTimeEnd(listLopHocDTO.get(i).getTimeEnd());
                        dayAndSlot.setDay(thu);
                        listDayAndSlot.add(dayAndSlot);
                    }
                }
            } else {

                dayAndSlot.setSlot(currentSlot);
                dayAndSlot.setTimeStart(listLopHocDTO.get(i).getTimeStart());
                dayAndSlot.setTimeEnd(listLopHocDTO.get(i).getTimeEnd());
                dayAndSlot.setDay(thu);
                listDayAndSlot.add(dayAndSlot);
            }

        }
        Set<DayAndSlot> uniqueDayAndSlots = new HashSet<>(listDayAndSlot);
        List<DayAndSlot> distinctDayAndSlots = new ArrayList<>(uniqueDayAndSlots);

        //
        request.setAttribute("distinctDayAndSlots", distinctDayAndSlots);
        request.setAttribute("cid", cid);

        RequestDispatcher rd = request.getRequestDispatcher("/Authentication/ClassDetail.jsp");
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
        Date lastDay = lopHocDAO.getLastDay(classID);
        request.setAttribute("listHocVienDTO", listHocVienDTO);
        request.setAttribute("lastDay", lastDay);
        request.setAttribute("lopHocDTO", lopHocDTO);
        request.setAttribute("trainerDTO", trainerDTO);
        RequestDispatcher rd = request.getRequestDispatcher("/Authorization/Admin/Class/ClassDetail.jsp");
        rd.forward(request, response);
    }

    public void updateClassPage(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String classID = request.getParameter("maLopHoc");
        LopHocDAO lopHocDAO = new LopHocDAO();
        LopHocDTO lopHocDTO = lopHocDAO.searchClassById(classID);
        PhongHocDAO phongHocDAO = new PhongHocDAO();
        List<PhongHocDTO> listPhongHocDTO = new ArrayList<>();

        listPhongHocDTO = phongHocDAO.getListEmptyRoom(lopHocDAO.maSlotClass(lopHocDTO.getMaLopHoc()), lopHocDAO.showThuWithStringArray(classID));
        TrainerDAO trainerDAO = new TrainerDAO();
        TrainerDTO trainerDTO = trainerDAO.searchTrainerByClassID(classID);

        List<TrainerDTO> listTrainer = new ArrayList();

        listTrainer = trainerDAO.readListTrainerByTypeAndStatus(lopHocDAO.IDLoaiLopHoc(classID));
        request.setAttribute("trainerDTO", trainerDTO);
        request.setAttribute("listTrainer", listTrainer);
        request.setAttribute("lopHocDTO", lopHocDTO);
        request.setAttribute("listPhongHocDTO", listPhongHocDTO);
        RequestDispatcher rd = request.getRequestDispatcher("/Authorization/Admin/Class/ClassUpdate.jsp");
        rd.forward(request, response);
    }

    public void updateClass(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String classID = request.getParameter("maLopHoc");

        String maRoom = request.getParameter("listPhongHocDTO");
        int soLuongHV = Integer.parseInt(request.getParameter("soLuongHV"));
        String listTrainer = request.getParameter("listTrainer");

        LopHocDAO lopHocDAO = new LopHocDAO();
        LopHocDTO lopHocDTO = lopHocDAO.searchClassById(classID);
        lopHocDTO.setMaRoom(maRoom);
        lopHocDTO.setMaTrainer(listTrainer);
        lopHocDTO.setSoLuongHV(soLuongHV);
        lopHocDAO.updateClass(lopHocDTO);

        RequestDispatcher rd = request.getRequestDispatcher("/AdminController?action=listLopHoc&page=1");
        rd.forward(request, response);
    }

    public void deleteClass(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String classID = request.getParameter("maLopHoc");
        ScheduleDAO scheduleDAO = new ScheduleDAO();
        LopHocDAO lopHocDAO = new LopHocDAO();
        scheduleDAO.deleteScheduleTrainer(classID);
        scheduleDAO.deleteScheduleHV(classID);
        lopHocDAO.deleteClassById(classID);

        RequestDispatcher rd = request.getRequestDispatcher("/AdminController?action=listLopHoc&page=1");
        rd.forward(request, response);
    }

    public void classDetailTrainee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String classID = request.getParameter("maLopHoc");
        LopHocDAO lopHocDAO = new LopHocDAO();
        LopHocDTO lopHocDTO = new LopHocDTO();
        lopHocDTO = lopHocDAO.searchClassById(classID);
        List<HocVienDTO> listHocVienDTO = new ArrayList<HocVienDTO>();
        HocVienDAO hocVienDAO = new HocVienDAO();
        listHocVienDTO = hocVienDAO.readListHocVienWithScheduleHV(classID);
        TrainerDAO trainerDAO = new TrainerDAO();
        TrainerDTO trainerDTO = trainerDAO.searchTrainerByClassID(classID);
        Date lastDay = lopHocDAO.getLastDay(classID);
        request.setAttribute("listHocVienDTO", listHocVienDTO);
        request.setAttribute("lastDay", lastDay);
        request.setAttribute("lopHocDTO", lopHocDTO);
        request.setAttribute("trainerDTO", trainerDTO);
        RequestDispatcher rd = request.getRequestDispatcher("/Authorization/TraineePrivilege/ClassDetail.jsp");
        rd.forward(request, response);
    }

    public void classDetailTrainer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String classID = request.getParameter("maLopHoc");
        String maSlot = request.getParameter("maSlot");
        Date ngayHoc = Date.valueOf(request.getParameter("ngayHoc"));
        AttendanceDAO attendanceDAO = new AttendanceDAO();
        List<AttendanceDTO> listAttendanceDTO = attendanceDAO.readAttendance(ngayHoc, maSlot, classID);
        LopHocDAO lopHocDAO = new LopHocDAO();
        LopHocDTO lopHocDTO = new LopHocDTO();
        lopHocDTO = lopHocDAO.searchClassById(classID);
        List<HocVienDTO> listHocVienDTO = new ArrayList<HocVienDTO>();
        HocVienDAO hocVienDAO = new HocVienDAO();
        listHocVienDTO = hocVienDAO.readListHocVienWithScheduleHV(classID);
        TrainerDAO trainerDAO = new TrainerDAO();
        TrainerDTO trainerDTO = trainerDAO.searchTrainerByClassID(classID);
        Date lastDay = lopHocDAO.getLastDay(classID);
        request.setAttribute("ngayHoc", ngayHoc);
        request.setAttribute("maSlot", maSlot);
        request.setAttribute("listHocVienDTO", listHocVienDTO);
        request.setAttribute("listAttendanceDTO", listAttendanceDTO);
        request.setAttribute("lastDay", lastDay);
        request.setAttribute("lopHocDTO", lopHocDTO);
        request.setAttribute("trainerDTO", trainerDTO);
        RequestDispatcher rd = request.getRequestDispatcher("/Authorization/TrainerPrivilege/ClassDetail.jsp");
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
