/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.yogacenterproject.controller;

import com.mycompany.yogacenterproject.dao.VoucherDAO;
import com.mycompany.yogacenterproject.dto.VoucherDTO;
import com.mycompany.yogacenterproject.util.Constants;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author iba
 */
public class VoucherController extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            String action = request.getParameter("action");

            if (action.equals("listHocVien")) {

            }
        }
    }

    public void addVoucher(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        VoucherDAO voucherDAO = new VoucherDAO();
        VoucherDTO voucherDTO = new VoucherDTO();

        String AUTO_VOUCHER_ID = String.format(Constants.VOUCHER_ID_FORMAT, (voucherDAO.lastIDIndex() + 1));
        String voucherName = request.getParameter("voucherName");
        long multiplier = Long.parseLong(request.getParameter("multiplier")) / 100;
        String errorMessage = "";
        boolean error = true;

        if (voucherDAO.getVoucherName(voucherName) != true) {
            if (multiplier < 1) {                                                                                   // correct statement
                voucherDTO.setVoucherID(AUTO_VOUCHER_ID);
                voucherDTO.setVoucherName(voucherName);
                voucherDTO.setMultiplier(multiplier);
                error = false;
                voucherDAO.addVoucher();
                RequestDispatcher rd = request.getRequestDispatcher("Authorization/Admin/AdminHomepage.jsp");
                rd.forward(request, response);
            } else {
                errorMessage = "Invalid discount";
            }
            request.setAttribute("errorMessage", errorMessage);
            RequestDispatcher rd = request.getRequestDispatcher("Authorization/Admin/AdminHomepage.jsp");
            rd.forward(request, response);
        }
    }

    public void applyVoucher(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        
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
        processRequest(request, response);
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
        processRequest(request, response);
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