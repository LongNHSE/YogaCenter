/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.yogacenterproject.controller;

import com.mycompany.yogacenterproject.dao.HocVienDAO;
import com.mycompany.yogacenterproject.dto.HocVienDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        HttpSession session = request.getSession();

        String email = request.getParameter("email");
        session.setAttribute("email", email);
        if (action.equals("login")) {
            login(request, response);
        } else if (action.equals("OTPSend")) {
            
            OTPSend(email, request);
            request.getRequestDispatcher("/Authentication/OTPCheck.jsp").forward(request, response);
        } else if (action.equals("OTP")) {
            OTPCheck(request, response, request.getParameter("OTP"));
        }

//        switch (action) {
//            case "login":
//                login(request, response);
//                break;
//            case "OTPSend":
//                OTPSend(email, request);
////                request.getRequestDispatcher("/Authentication/OTPCheck.jsp").forward(request, response);
//
//                break;
//            case "OTP":
//                OTPCheck(request, response, request.getParameter("OTP"));
//                break;
//            default:
//                throw new AssertionError();
//        }
    }

    public void OTPSend(String email, HttpServletRequest request) throws EmailException, MalformedURLException, ServletException, IOException {
        OTPController.generateOTP(email, request);
    }

    public void OTPCheck(HttpServletRequest request, HttpServletResponse response, String OTP) throws ServletException, IOException {
        OTPController.checkOTP(request, response, OTP);
        if (OTPController.checkOTP(request, response, OTP)) {
            request.getRequestDispatcher("/Authentication/signup.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/Authentication/OTPCheck.jsp").forward(request, response);
        }
    }

    public void signup(HttpServletRequest request, HttpServletResponse response) throws EmailException, MalformedURLException {
        HocVienDAO hocVienDAO = new HocVienDAO();

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
