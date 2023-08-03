/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.yogacenterproject.controller;

import PayPal.PaymentServices;
import com.mycompany.yogacenterproject.dao.ApplicationDAO;
import com.mycompany.yogacenterproject.dao.AttendanceDAO;
import com.mycompany.yogacenterproject.dao.DescriptionDAO;
import com.mycompany.yogacenterproject.dao.EmailController;
import com.mycompany.yogacenterproject.dao.HoaDonDAO;
import com.mycompany.yogacenterproject.dao.LoaiLopHocDAO;
import com.mycompany.yogacenterproject.dao.LopHocDAO;
import static com.mycompany.yogacenterproject.dao.LopHocDAO.compareLists;
import com.mycompany.yogacenterproject.dao.LopHocImageDAO;
import com.mycompany.yogacenterproject.dao.ScheduleDAO;
import com.mycompany.yogacenterproject.dao.TrainerDAO;
import com.mycompany.yogacenterproject.dto.ApplicationDTO;
import com.mycompany.yogacenterproject.dto.DayAndSlot;
import com.mycompany.yogacenterproject.dto.DescriptionDTO;
import com.mycompany.yogacenterproject.dto.HoaDonDTO;
import com.mycompany.yogacenterproject.dto.HocVienDTO;
import com.mycompany.yogacenterproject.dto.LoaiLopHocDTO;
import com.mycompany.yogacenterproject.dto.LopHocDTO;
import com.mycompany.yogacenterproject.dto.LopHocIMGDTO;
import com.mycompany.yogacenterproject.dto.TrainerDTO;
import com.mycompany.yogacenterproject.util.Constants;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.mail.EmailException;

/**
 *
 * @author Oalskad
 */
public class ExceptionController extends HttpServlet {

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
            throws ServletException, IOException, SQLException, EmailException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String action = request.getParameter("action");
            if (action.equals("Change Class")) {
                changeClassPage(request, response);
            } else if (action.equals("ChangeClassAction")) {
                changeClass(request, response);
            } else if (action.equals("Reserve")) {
                reserve(request, response);
            } else if (action.equals("Request Off")) {
                requestOff(request, response);
            } else if (action.equals("Approve and Change trainer")) {
                thongTinAssignPage(request, response);
            } else if (action.equals("AssignTrainer")) {
                assignTrainer(request, response);
            } else if (action.equals("Unapprove")) {
                unapproveAssignTrainer(request, response);
            } else if (action.equals("Request day off")) {
//                String maLopHoc = request.getParameter("maLopHoc");
//                Date date = Date.valueOf(request.getParameter("ngayHoc"));
//                String slot = request.getParameter("maSlot");
//                out.print(maLopHoc);
//                out.print(date);
//                out.print(slot);
                requestDayOff(request, response);
            } else if (action.equals("Approve day off and Change trainer")) {
                approveDayOffPage(request, response);
            } else if (action.equals("AssignTrainerDayOff")) {
                approveDayOff(request, response);
            }

        }
    }

    public void approveDayOff(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, EmailException {
        String maApplication = request.getParameter("maApplication");
        ApplicationDAO applicationDAO = new ApplicationDAO();
        ApplicationDTO applicationDTO = applicationDAO.search(maApplication);
        LopHocDAO lopHocDAO = new LopHocDAO();
        ScheduleDAO scheduleDAO = new ScheduleDAO();
        TrainerDAO trainerDAO = new TrainerDAO();
        TrainerDTO trainerDTO = applicationDTO.getTrainerDTO();
        String maTrainerNew = request.getParameter("maTrainer");
        String pattern = "Date: (\\d{4}-\\d{2}-\\d{2}) Slot: (\\w+)";
        Pattern regexPattern = Pattern.compile(pattern);

        // Create a Matcher object
        Matcher matcher = regexPattern.matcher(applicationDTO.getNoiDung());
//                Matcher matcher = pattern.matcher();

//                requestDayOff(request, response);
        if (matcher.find()) {
            String date = matcher.group(1); // Capture group 1 contains the date
            String slot = matcher.group(2);
            Date ngayHoc = Date.valueOf(date);

            scheduleDAO.updateTrainerDayOff(applicationDTO.getMaLopHoc(), maTrainerNew, ngayHoc, slot);
            EmailController.trainerDTOAssignDayOff(trainerDAO.readTrainer(maTrainerNew), lopHocDAO.searchClassById(applicationDTO.getMaLopHoc()),ngayHoc, slot);
            EmailController.approveResquestDayOff(trainerDTO,lopHocDAO.searchClassById(applicationDTO.getMaLopHoc()) , ngayHoc, slot);
            applicationDAO.updateStatusApprove(maApplication);
            response.sendRedirect("Authorization/Admin/Class/ClassController.jsp");
        } else {

        }

//        scheduleDAO.deleteScheduleTrainerOff(maLopHoc, applicationDTO.getMaTrainer());
    }

    public void approveDayOffPage(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String maApplication = request.getParameter("maApplication");
        ApplicationDAO applicationDAO = new ApplicationDAO();
        ApplicationDTO applicationDTO = applicationDAO.search(maApplication);
        ScheduleDAO scheduleDAO = new ScheduleDAO();
        TrainerDAO trainerDAO = new TrainerDAO();
        TrainerDTO trainerDTO = applicationDTO.getTrainerDTO();
        String pattern = "Date: (\\d{4}-\\d{2}-\\d{2}) Slot: (\\w+)";
        Pattern regexPattern = Pattern.compile(pattern);

        // Create a Matcher object
        Matcher matcher = regexPattern.matcher(applicationDTO.getNoiDung());
//                Matcher matcher = pattern.matcher();

//                requestDayOff(request, response);
        if (matcher.find()) {
            String date = matcher.group(1); // Capture group 1 contains the date
            String slot = matcher.group(2);
            Date ngayHoc = Date.valueOf(date);
            List<TrainerDTO> listTrainerDTO = scheduleDAO.listTrainerDTO(trainerDTO, slot, ngayHoc);
            List<TrainerDTO> listTrainerDTOAvailable = trainerDAO.readListTrainerByTypeAndStatus(trainerDTO.getTrainerType());
            for (TrainerDTO x : listTrainerDTOAvailable) {
                listTrainerDTO.add(x);
            }
            request.setAttribute("listTrainer", listTrainerDTO);

            RequestDispatcher rd = request.getRequestDispatcher("Authorization/Admin/Class/AssignTrainerDayOff.jsp");
            rd.forward(request, response);

        } else {

        }
    }

    public void requestOff(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, EmailException {
        HttpSession session = request.getSession();

        TrainerDTO trainerDTO = (TrainerDTO) session.getAttribute("trainerDTO");

        ApplicationDAO applicationDAO = new ApplicationDAO();
        String maLopHoc = request.getParameter("maLopHoc");
        LopHocDAO lopHocDAO = new LopHocDAO();

        if (lopHocDAO.getSoNgayDaDay(maLopHoc) > 2) {
            String popupMessage = maLopHoc;
            request.setAttribute("popupMessage", popupMessage);
            RequestDispatcher rd = request.getRequestDispatcher("/TrainerController?action=classList");
            rd.forward(request, response);
        } else {
            LocalDate currentDate = LocalDate.now();
            String AUTO_APPLICATION_ID = String.format(Constants.MA_APPLICATION_FORMAT, (applicationDAO.lastIDIndexOfBlog() + 1));

            //HOCVIEN CONSTRUCTOR
            String maApp = AUTO_APPLICATION_ID;
            ApplicationDTO applicationDTO = new ApplicationDTO();

            applicationDTO.setMaLopHoc(maLopHoc);
            applicationDTO.setNoiDung("Trainer " + trainerDTO.getMaTrainer() + " reserve class " + maLopHoc);
            applicationDTO.setMaApplicationType("TYPE0004");
            applicationDTO.setDate(Date.valueOf(currentDate));
            applicationDTO.setMaTrainer(trainerDTO.getMaTrainer());
            applicationDTO.setMaDon(maApp);
            applicationDTO.setStatus("Pending");
            applicationDAO.create(applicationDTO);
            String popupMessage = "The request has been sended successfully. Please check your mail for your response";
            request.setAttribute("popupMessageSuccessful", popupMessage);
            EmailController.requestOff(trainerDTO, lopHocDAO.searchClassById(applicationDTO.getMaLopHoc()));
            RequestDispatcher rd = request.getRequestDispatcher("/TrainerController?action=classList");
            rd.forward(request, response);
        }

    }

    public void requestDayOff(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, EmailException {
        HttpSession session = request.getSession();

        TrainerDTO trainerDTO = (TrainerDTO) session.getAttribute("trainerDTO");

        ApplicationDAO applicationDAO = new ApplicationDAO();
        String maLopHoc = request.getParameter("maLopHoc");
        Date date = Date.valueOf(request.getParameter("ngayHoc"));
        String slot = request.getParameter("maSlot");
        LopHocDAO lopHocDAO = new LopHocDAO();

        LocalDate currentDate = LocalDate.now();
        String AUTO_APPLICATION_ID = String.format(Constants.MA_APPLICATION_FORMAT, (applicationDAO.lastIDIndexOfBlog() + 1));

        //HOCVIEN CONSTRUCTOR
        String maApp = AUTO_APPLICATION_ID;
        ApplicationDTO applicationDTO = new ApplicationDTO();

        applicationDTO.setMaLopHoc(maLopHoc);
        applicationDTO.setNoiDung("Date: " + date + " Slot: " + slot);
        applicationDTO.setMaApplicationType("TYPE0005");
        applicationDTO.setDate(Date.valueOf(currentDate));
        applicationDTO.setMaTrainer(trainerDTO.getMaTrainer());
        applicationDTO.setMaDon(maApp);
        applicationDTO.setStatus("Pending");
        applicationDAO.create(applicationDTO);
        String popupMessage = "The request has been sended successfully. Please check your mail for your response";
        request.setAttribute("popupMessageSuccessful", popupMessage);
//            EmailController.requestOff(trainerDTO, lopHocDAO.searchClassById(applicationDTO.getMaLopHoc()));
        RequestDispatcher rd = request.getRequestDispatcher("/TrainerController?action=classList");
        rd.forward(request, response);

    }

    public void sendMailClassChange(HttpServletRequest request, HttpServletResponse response, LopHocDTO lopHocDTO, String maLopHocCu) throws EmailException, MalformedURLException {
        HttpSession session = request.getSession();
        if (session.getAttribute("hocVienDTO") != null) {
            String maLopHoc = request.getParameter("returnID");
            HocVienDTO hocVienDTO = (HocVienDTO) session.getAttribute("hocVienDTO");

            EmailController.ClassChange(lopHocDTO, hocVienDTO.getEmail(), maLopHocCu);
        }
    }

    public void changeClass(HttpServletRequest request, HttpServletResponse response) {
        try {
            boolean error = true;
            HttpSession session = request.getSession();
            LoaiLopHocDAO loaiLopHocDAO = new LoaiLopHocDAO();
            AttendanceDAO attendanceDAO = new AttendanceDAO();
            if (session.getAttribute("hocVienDTO") != null) {
                HocVienDTO hocVienDTO = (HocVienDTO) session.getAttribute("hocVienDTO");
                LopHocDTO lopHocDTO = new LopHocDTO();
                LopHocDAO lopHocDAO = new LopHocDAO();
                HoaDonDAO hoaDonDAO = new HoaDonDAO();
                ApplicationDAO applicationDAO = new ApplicationDAO();
                ApplicationDTO applicationDTO = new ApplicationDTO();
                String errorMessage = "";
                String maLopHocCu = request.getParameter("maLopHocCu");
                String maLopHoc = request.getParameter("maLopHoc");
                lopHocDTO = lopHocDAO.searchClassById(maLopHoc);
// Split the cleaned value into individual elements
                //                String maLopHoc = lopHocDAO.searchForPayment(maSlot, maLoaiLopHoc, thuList);
                ScheduleDAO scheduleDAO = new ScheduleDAO();
                if (!checkAvailability(request, response, maLopHoc)) {
                    error = false;
                    errorMessage += "Classes are fully reserved.";
                }
                if (!checkTraineeSchedule(request, response, lopHocDTO.getMaSlot(), lopHocDTO.getThuList(), hocVienDTO.getMaHV())) {
                    error = false;
                    errorMessage += "You already have a class scheduled for this time slot.";
                }
                if (error) {
                    lopHocDTO = lopHocDAO.searchClassById(maLopHoc);
                    scheduleDAO.deleteScheduleHVWithMaLopHoc(maLopHocCu, hocVienDTO.getMaHV());
                    attendanceDAO.deleteAttendaceHV(hocVienDTO.getMaHV(), maLopHocCu);
                    lopHocDAO.decrease(maLopHocCu);

                    lopHocDAO.increase(maLopHoc);
                    scheduleDAO.createScheduleHV(hocVienDTO.getMaHV(), maLopHoc);
                    attendanceDAO.createAttendance(scheduleDAO.readScheduleHvDTO(hocVienDTO.getMaHV()));
                    sendMailClassChange(request, response, lopHocDAO.searchClassById(maLopHoc), maLopHocCu);

                    LocalDate currentDate = LocalDate.now();

                    String AUTO_APPLICATION_ID = String.format(Constants.MA_APPLICATION_FORMAT, (applicationDAO.lastIDIndexOfBlog() + 1));

                    //HOCVIEN CONSTRUCTOR
                    String maApp = AUTO_APPLICATION_ID;
                    applicationDTO.setMaApplicationType("TYPE0001");
                    applicationDTO.setDate(Date.valueOf(currentDate));
                    applicationDTO.setMaHV(hocVienDTO.getMaHV());
                    applicationDTO.setMaDon(maApp);
                    applicationDTO.setStatus("Approved");
                    applicationDTO.setMaLopHoc(maLopHoc);

                    String noiDung = "Trainee " + hocVienDTO.getMaHV() + " change from class " + maLopHocCu + " to " + maLopHoc;
                    noiDung.toString();
                    applicationDTO.setNoiDung(noiDung);

                    applicationDAO.create(applicationDTO);

                    String popupMessage = "You have changed form class " + maLopHocCu + " to class " + maLopHoc + " successfully";
                    request.setAttribute("popupMessageSuccessful", popupMessage);

                    RequestDispatcher rd = request.getRequestDispatcher("/ProfileController?action=classList");
                    rd.forward(request, response);

                } else {
                    request.setAttribute("error", errorMessage);
                    changeClassPage(request, response);
                }
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("/Authentication/signin.jsp");
                rd.forward(request, response);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

    public void changeClassPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        HocVienDTO hocVienDTO = (HocVienDTO) session.getAttribute("hocVienDTO");
        String maLopHoc = request.getParameter("maLopHoc");
//        System.out.println(maLopHoc);
        LopHocDTO lopHocDTO = new LopHocDTO();
        LopHocDAO lopHocDAO = new LopHocDAO();
        lopHocDTO = lopHocDAO.searchClassById(maLopHoc);
//        System.out.println(lopHocDTO);
        String cid = lopHocDTO.getMaLoaiLopHoc();

//          Get class information
        LoaiLopHocDAO dao = new LoaiLopHocDAO();
        LoaiLopHocDTO classDetails = dao.getClassCateByID(cid);
//        LopHocDTO lopHocDTO = new LopHocDTO();
        List<LopHocDTO> listLopHocDTO = lopHocDAO.showClassesByType(cid);
        System.out.println(listLopHocDTO);
//        List<DayAndSlot> listDayAndSlot = new ArrayList<>();
//        for (int i = 0; i < listLopHocDTO.size(); i++) {
//            DayAndSlot dayAndSlot = new DayAndSlot();
//            String currentSlot = listLopHocDTO.get(i).getMaSlot();
//            List<String> thu = listLopHocDTO.get(i).getThuList();
//             System.out.println("ccc");
//             System.out.println(currentSlot + thu);
//             System.out.println(i);
//            if (i < 0) {
//                 System.out.println(i);
//                for (int j = 1; j <= i; j++) {
//                    System.out.println(listLopHocDTO.get(j).getMaSlot());
//                    if (currentSlot.equals(listLopHocDTO.get(j).getMaSlot())) {
//                        if (LopHocDAO.compareLists(listLopHocDTO.get(i).getThuList(), listLopHocDTO.get(j).getThuList())) {
//                            System.out.println(currentSlot + thu);
//                        }
//                    } else {
//
//                        dayAndSlot.setSlot(currentSlot);
//                        dayAndSlot.setTimeStart(listLopHocDTO.get(i).getTimeStart());
//                        dayAndSlot.setTimeEnd(listLopHocDTO.get(i).getTimeEnd());
//                        dayAndSlot.setDay(thu);
//                        listDayAndSlot.add(dayAndSlot);
//                    }
//                }
//            } else {
//
//                dayAndSlot.setSlot(currentSlot);
//                dayAndSlot.setTimeStart(listLopHocDTO.get(i).getTimeStart());
//                dayAndSlot.setTimeEnd(listLopHocDTO.get(i).getTimeEnd());
//                dayAndSlot.setDay(thu);
//                listDayAndSlot.add(dayAndSlot);
//            }
//
//        }
//        Set<DayAndSlot> uniqueDayAndSlots = new HashSet<>(listDayAndSlot);
//        List<DayAndSlot> distinctDayAndSlots = new ArrayList<>(uniqueDayAndSlots);
////        System.out.println(distinctDayAndSlots);
//        lopHocDTO = lopHocDAO.showSlotBy1Class(maLopHoc);
//        DayAndSlot dayAndSlota = new DayAndSlot();
//        dayAndSlota.setDay(lopHocDTO.getThuList());
//        dayAndSlota.setSlot(lopHocDTO.getMaSlot());
//        System.out.println("bbb");
//        System.out.println(uniqueDayAndSlots);
//        System.out.println("bbb");
//        System.out.println(distinctDayAndSlots);
//        List<DayAndSlot> distinctDayAndSlotsNew = new ArrayList<>();
//        for (DayAndSlot x : distinctDayAndSlots) {
//            if (x.getSlot().equalsIgnoreCase(dayAndSlota.getSlot()) && compareLists(x.getDay(), dayAndSlota.getDay())) {
//                System.out.println("aaaa");
//                System.out.println(x);
//            } else {
//                distinctDayAndSlotsNew.add(x);
//            }
//        }
        List<LopHocDTO> listClass = new ArrayList<>();
        listClass = lopHocDAO.searchClassByTypeID(cid);
        List<LopHocDTO> listClass2 = new ArrayList<>();
        for (LopHocDTO x : listClass) {
            if ((x.getSoBuoiDaDay() - lopHocDTO.getSoBuoiDaDay() >= -1 && x.getSoBuoiDaDay() - lopHocDTO.getSoBuoiDaDay() <= 1) && !x.getMaLopHoc().equals(lopHocDTO.getMaLopHoc())) {
                listClass2.add(x);
            }
        }
//        LopHocDTO lopHocDTO2 = lopHocDAO.searchClassById(maLopHoc);
        request.setAttribute("lopHocDTO", lopHocDTO);
        request.setAttribute("listClass", listClass2);
//        request.setAttribute("distinctDayAndSlots", distinctDayAndSlotsNew);
        request.setAttribute("cid", cid);

        RequestDispatcher rd = request.getRequestDispatcher("/Authorization/ChangeClass.jsp");
        rd.forward(request, response);
    }

    public void reserve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, EmailException {
        String maLopHoc = request.getParameter("maLopHoc");
        HttpSession session = request.getSession();
        HocVienDTO hocVienDTO = (HocVienDTO) session.getAttribute("hocVienDTO");
        ScheduleDAO scheduleDAO = new ScheduleDAO();
        AttendanceDAO attendanceDAO = new AttendanceDAO();
        LopHocDAO lopHocDAO = new LopHocDAO();
        LopHocDTO lopHocDTO = new LopHocDTO();
        ApplicationDAO applicationDAO = new ApplicationDAO();
        ApplicationDTO applicationDTO = new ApplicationDTO();

        lopHocDTO = lopHocDAO.searchClassById(maLopHoc);

        if (lopHocDAO.getSoNgayDaDay(maLopHoc) > 2) {
            String popupMessage = maLopHoc;
            request.setAttribute("popupMessage", popupMessage);
            request.getRequestDispatcher("/ProfileController?action=classList").forward(request, response);
        } else {
            lopHocDTO = lopHocDAO.searchClassById(maLopHoc);
            scheduleDAO.deleteScheduleHVWithMaLopHoc(maLopHoc, hocVienDTO.getMaHV());
            attendanceDAO.deleteAttendaceHV(hocVienDTO.getMaHV(), maLopHoc);
            lopHocDAO.decrease(maLopHoc);

            LocalDate currentDate = LocalDate.now();

            String AUTO_APPLICATION_ID = String.format(Constants.MA_APPLICATION_FORMAT, (applicationDAO.lastIDIndexOfBlog() + 1));

            //HOCVIEN CONSTRUCTOR
            String maApp = AUTO_APPLICATION_ID;
            applicationDTO.setMaApplicationType("TYPE0002");
            applicationDTO.setDate(Date.valueOf(currentDate));
            applicationDTO.setMaHV(hocVienDTO.getMaHV());
            applicationDTO.setMaDon(maApp);
            applicationDTO.setStatus("Approved(n)");
            applicationDTO.setMaLopHoc(maLopHoc);

            String noiDung = "Reserve class " + maLopHoc;
            noiDung.toString();
            applicationDTO.setNoiDung(noiDung);
            applicationDAO.create(applicationDTO);

            EmailController.ClassReserve(lopHocDTO, hocVienDTO.getEmail());
            String popupMessageSuccessful = "You reserved class " + maLopHoc + " successfully.";
            request.setAttribute("popupMessageSuccessful", popupMessageSuccessful);
            request.getRequestDispatcher("/ProfileController?action=classList").forward(request, response);
        }
    }

    public void thongTinAssignPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String maApplication = request.getParameter("maApplication");

        LoaiLopHocDAO loaiLopHocDAO = new LoaiLopHocDAO();
        LopHocDAO lopHocDAO = new LopHocDAO();
        String maLopHoc = request.getParameter("maLopHoc");
        TrainerDAO trainerDAO = new TrainerDAO();
        List<TrainerDTO> listTrainer = new ArrayList();

        listTrainer = trainerDAO.readListTrainerByTypeAndStatus(lopHocDAO.IDLoaiLopHoc(maLopHoc));

        request.setAttribute("listTrainer", listTrainer);
        request.setAttribute("maLopHoc", maLopHoc);
        request.setAttribute("maApplication", maApplication);
        RequestDispatcher rd = request.getRequestDispatcher("Authorization/Admin/Class/AssignTrainerOff.jsp");
        rd.forward(request, response);

    }

    //ASSIGN GIAO VIEN VAO SCHEDULE
    public void assignTrainer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, EmailException {
        String maLopHoc = request.getParameter("maLopHoc");
        String maApplication = request.getParameter("maApplication");
        ApplicationDAO applicationDAO = new ApplicationDAO();
        ApplicationDTO applicationDTO = applicationDAO.search(maApplication);
        String maTrainer = request.getParameter("listTrainer");
        LopHocDAO lopHocDAO = new LopHocDAO();

        TrainerDAO trainerDAO = new TrainerDAO();
        ScheduleDAO scheduleDAO = new ScheduleDAO();

        scheduleDAO.updateTrainerOff(maLopHoc, maTrainer);

//        scheduleDAO.deleteScheduleTrainerOff(maLopHoc, applicationDTO.getMaTrainer());
        trainerDAO.updateTrainerStatus(maTrainer, true);
        trainerDAO.updateTrainerStatus(applicationDTO.getMaTrainer(), false);
        EmailController.trainerDTOAssign(trainerDAO.readTrainer(maTrainer), lopHocDAO.searchClassById(maLopHoc));
        EmailController.approveResquestOff(trainerDAO.readTrainer(applicationDTO.getMaTrainer()), lopHocDAO.searchClassById(maLopHoc));
        applicationDAO.updateStatusApprove(maApplication);
        response.sendRedirect("Authorization/Admin/Class/ClassController.jsp");

    }

    public void unapproveAssignTrainer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, EmailException {
        String maLopHoc = request.getParameter("maLopHoc");
        String maApplication = request.getParameter("maApplication");
        ApplicationDAO applicationDAO = new ApplicationDAO();
        ApplicationDTO applicationDTO = applicationDAO.search(maApplication);
        String maTrainer = request.getParameter("listTrainer");
        LopHocDAO lopHocDAO = new LopHocDAO();

        TrainerDAO trainerDAO = new TrainerDAO();
        ScheduleDAO scheduleDAO = new ScheduleDAO();

        EmailController.unapproveResquestOff(trainerDAO.readTrainer(applicationDTO.getMaTrainer()), lopHocDAO.searchClassById(maLopHoc));

        applicationDAO.updateStatusUnapprove(maApplication);
        response.sendRedirect("Authorization/Admin/Application/ListApplication.jsp");

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
            Logger.getLogger(ExceptionController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EmailException ex) {
            Logger.getLogger(ExceptionController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ExceptionController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EmailException ex) {
            Logger.getLogger(ExceptionController.class.getName()).log(Level.SEVERE, null, ex);
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
