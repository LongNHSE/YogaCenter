/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.yogacenterproject.controller;

import com.mycompany.yogacenterproject.dao.HocVienDAO;
import com.mycompany.yogacenterproject.dto.HocVienDTO;
import com.mycompany.yogacenterproject.util.Constants;
import com.mycompany.yogacenterproject.util.Utils;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.mail.EmailException;

/**
 *
 * @author devli
 */
public class LoginController extends HttpServlet {

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
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String url = request.getContextPath();
        String action = request.getParameter("action");
        request.setAttribute("action", action);
        HttpSession session = request.getSession();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            if (action.equals("login")) {
                login(request, response);
            } else if (action.equals("OTPSend")) {
                String email = request.getParameter("email");
                //Session chưa login sẽ được set timeout là 60s
                session.setAttribute("email", email);
                OTPSend(email, request, response);
                
            } else if (action.equals("OTP")) {

                OTPCheck(request, response, request.getParameter("OTP"));
            } else {

                signup(request, response);
            }

        }
    }

    //FUNCTION TO SEND OTP
    public void OTPSend(String email, HttpServletRequest request, HttpServletResponse response) throws EmailException, MalformedURLException, ServletException, IOException {
        boolean error = true;
        String errorMessageMail = "";
        HocVienDAO hocVienDAO = new HocVienDAO();

        if (hocVienDAO.selectByHocVienEmail(email)) {
            errorMessageMail += "Email has already existed";
            error = false;
        }
        request.setAttribute("errorMessageMail", errorMessageMail);
        if (!hocVienDAO.selectByHocVienEmail(email)) {
            OTPController.generateOTP(email, request);
            request.getRequestDispatcher("/Authentication/OTPCheck.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/Authentication/signup_1.jsp").forward(request, response);
        }
    }

    //FUNCTION CHECK OTP
    public void OTPCheck(HttpServletRequest request, HttpServletResponse response, String OTP) throws ServletException, IOException {
        OTPController.checkOTP(request, response, OTP);
        if (OTPController.checkOTP(request, response, OTP)) {
            request.getRequestDispatcher("/Authentication/signup.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/Authentication/OTPCheck.jsp").forward(request, response);
        }
    }

    public void signup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        HocVienDAO hocVienDAO = new HocVienDAO();
        String errorMessage = "";
        String errorMessageDate = "";
        boolean error = true;
        //DATE USING SQL.DATE
        Date dateOfBirth = null;

        //AUTO GENERATE HOCVIEN ID WITH PATTERN
        String AUTO_HOCVIEN_ID = String.format(Constants.MA_HOCVIEN_FORMAT, (hocVienDAO.lastIDIndex() + 1));

        //HOCVIEN CONSTRUCTOR
        String maHV = AUTO_HOCVIEN_ID;
        String username = request.getParameter("username");
        String psw = request.getParameter("psw");
        String ho = request.getParameter("Ho");
        String ten = request.getParameter("Ten");

        try {
            dateOfBirth = Date.valueOf(request.getParameter("dateOfBirth"));
        } catch (Exception e) {
            errorMessageDate = "invalid Date";
            error = false;
        }
        String phone = request.getParameter("phoneNumber");
        String email = (String) session.getAttribute("email");
        String gender = request.getParameter("gender");

        if (hocVienDAO.selectByUserName(username)) {
            errorMessage += "Username has already taken";
            error = false;
        }
        request.setAttribute("errorMessage", errorMessage);
        request.setAttribute("errorMessageDate", errorMessageDate);

        if (error == true) {
            HocVienDTO hocVienDTO = new HocVienDTO();
            hocVienDTO.setUsername(username);
            hocVienDTO.setTen(ten);
            hocVienDTO.setPsw(psw);
            hocVienDTO.setPhone(phone);
            hocVienDTO.setMaLopHoc(null);
            hocVienDTO.setMaLoaiTK("HOCVIEN");
            hocVienDTO.setMaHV(maHV);
            hocVienDTO.setHo(ho);
            hocVienDTO.setGender(gender);
            hocVienDTO.setEmail(email);
            hocVienDTO.setDob(dateOfBirth);
            hocVienDAO.addHocVien(hocVienDTO);
            RequestDispatcher rd = request.getRequestDispatcher("/Authentication/signin.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("/Authentication/signup.jsp");
            rd.forward(request, response);
        }
    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HocVienDAO dao = new HocVienDAO();
        HocVienDTO hocVienDTO = dao.login(username, password);
        if (hocVienDTO == null) {
            request.getRequestDispatcher("/Authentication/signin.jsp").forward(request, response);
        } else {
            session.setAttribute("user", hocVienDTO);
            // set lại session time out là 5p
            session.setMaxInactiveInterval(300);

            request.getRequestDispatcher("/Authentication/success.jsp").forward(request, response);
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
        } catch (EmailException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
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
