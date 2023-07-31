/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.yogacenterproject.controller;

import com.mycompany.yogacenterproject.dao.EmailController;
import com.mycompany.yogacenterproject.dao.HocVienDAO;
import com.mycompany.yogacenterproject.dao.LopHocDAO;
import com.mycompany.yogacenterproject.dao.TrainerDAO;
import com.mycompany.yogacenterproject.dto.HocVienDTO;
import com.mycompany.yogacenterproject.dto.LopHocDTO;
import com.mycompany.yogacenterproject.dto.TrainerDTO;
import com.mycompany.yogacenterproject.util.Constants;
import com.mycompany.yogacenterproject.util.DateUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
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
import org.apache.commons.mail.EmailException;

/**
 *
 * @author Oalskad
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class TrainerController extends HttpServlet {

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
            throws ServletException, IOException, EmailException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            if (action.equals("addTrainer")) {
                addTrainer(request, response);
            } else if (action.equals("UpdateStatus")) {
                updateStatus(request, response);
            } else if (action.equals("updateProfile")) {
                updateStatus(request, response);
            } else if (action.equals("classList")) {
                classList(request, response);
            } else if (action.equals("classList")) {
                classList(request, response);
            }

        }
    }

    public void updateStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String maTrainer = request.getParameter("maTrainer");
        TrainerDAO trainerDAO = new TrainerDAO();
        trainerDAO.updateTrainerStatus(maTrainer, false);
        response.sendRedirect("AdminController?action=listTrainer&page=1");

    }

    public void addTrainer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, EmailException {
        HttpSession session = request.getSession();
        TrainerDAO trainerDAO = new TrainerDAO();
        String errorMessage = "";
        String errorMessageDate = "";
        String errorMessageEmail = "";
        boolean error = true;
        //DATE USING SQL.DATE
        Date dateOfBirth = null;

        //AUTO GENERATE HOCVIEN ID WITH PATTERN
        String AUTO_TRAINER_ID = String.format(Constants.MA_TRAINER_FORMAT, (trainerDAO.lastIDIndex() + 1));

        //HOCVIEN CONSTRUCTOR
        String maTrainer = AUTO_TRAINER_ID;
        String username = request.getParameter("username");
        String psw = request.getParameter("psw");
        String ho = request.getParameter("Ho");
        String ten = request.getParameter("Ten");
        long salary = Long.parseLong(request.getParameter("Salary"))*1000000;
        try {
            dateOfBirth = Date.valueOf(request.getParameter("dateOfBirth"));
        } catch (Exception e) {
            errorMessageDate = "invalid Date";
            error = false;
        }
        String phone = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String maloaiYoga = request.getParameter("loaiLopHoc");

        if (trainerDAO.selectByUserName(username)) {
            errorMessage += "Username has already taken";
            error = false;
        }
        if (trainerDAO.selectByEmail(email)) {
            errorMessageEmail += "Email has already taken";
            error = false;
        }
        request.setAttribute("errorMessage", errorMessage);
        request.setAttribute("errorMessageDate", errorMessageDate);
        request.setAttribute("errorMessageEmail", errorMessageEmail);

        if (error == true) {
            TrainerDTO trainerDTO = new TrainerDTO();
            trainerDTO.setMaTrainer(maTrainer);
            trainerDTO.setUsername(username);
            trainerDTO.setTrainerType(maloaiYoga);
            trainerDTO.setMaLoaiTK("TRAINER");
            trainerDTO.setPhone(phone);
            trainerDTO.setEmail(email);
            trainerDTO.setHo(ho);
            trainerDTO.setTen(ten);
            trainerDTO.setStatus(false);
            trainerDTO.setSoNgayNghi(0);
            trainerDTO.setSalary(salary);
            trainerDTO.setPsw(psw);
            LocalDate dob = DateUtils.asLocalDate(dateOfBirth);
            trainerDTO.setDob(dob);
            trainerDTO.setGender(gender);

            trainerDAO.addTrainer(trainerDTO);
            EmailController.trainerDTOCreate(trainerDTO);
            RequestDispatcher rd = request.getRequestDispatcher("./AdminController?action=listTrainer&page=1");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("./Authorization/Admin/Trainer/AddTrainer.jsp");

            rd.forward(request, response);
        }
    }

    public void classList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // View profile trainee
        //  log("chay vao view Profile");////////////////////
        HttpSession session = request.getSession();
        TrainerDTO trainerDTO = (TrainerDTO) session.getAttribute("trainerDTO");

        LopHocDAO lopHocDAO = new LopHocDAO();
        List<LopHocDTO> listLopHocDTO = new ArrayList<>();
        listLopHocDTO = lopHocDAO.getListClassOfTrainer(trainerDTO.getMaTrainer());
        ///set Attribute
//        session.setAttribute("hocVienDTO", hocVienDTO);
        request.setAttribute("listLopHocDTO", listLopHocDTO);
        RequestDispatcher rd = request.getRequestDispatcher("./Authorization/TrainerPrivilege/ClassList.jsp");
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
        } catch (EmailException ex) {
            Logger.getLogger(TrainerController.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (EmailException ex) {
            Logger.getLogger(TrainerController.class.getName()).log(Level.SEVERE, null, ex);
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
