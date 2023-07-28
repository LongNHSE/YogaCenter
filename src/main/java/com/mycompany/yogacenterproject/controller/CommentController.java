/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.yogacenterproject.controller;

import com.mycompany.yogacenterproject.dao.CommentDAO;
import com.mycompany.yogacenterproject.dto.CommentDTO;
import com.mycompany.yogacenterproject.dto.HocVienDTO;
import com.mycompany.yogacenterproject.dto.TrainerDTO;
import com.mycompany.yogacenterproject.util.Constants;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
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
public class CommentController extends HttpServlet {

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
            insertComment(request, response);
        }
    }

    public void insertComment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String maLoaiLopHoc = request.getParameter("maLoaiLopHoc");
        try {
            CommentDAO commentDAO = new CommentDAO();
            CommentDTO commentDTO = new CommentDTO();
            HocVienDTO hocVienDTO = new HocVienDTO();
            TrainerDTO trainerDTO = new TrainerDTO();
            LocalDate currentDate = LocalDate.now();

            String comment = request.getParameter("comment");

            if (session.getAttribute("hocVienDTO") != null) {
                hocVienDTO = (HocVienDTO) session.getAttribute("hocVienDTO");

            }
            if (session.getAttribute("trainerDTO") != null) {
                trainerDTO = (TrainerDTO) session.getAttribute("trainerDTO");

            }
            commentDTO.setHocVienDTO(hocVienDTO);
            commentDTO.setTrainerDTO(trainerDTO);
            String AUTO_COMMENT_ID = String.format(Constants.MA_COMMENT_FORMAT, (commentDAO.lastIDIndex() + 1));

            //HOCVIEN CONSTRUCTOR
            String maComment = AUTO_COMMENT_ID;
            commentDTO.setDate(Date.valueOf(currentDate));
            commentDTO.setMaComment(maComment);
            commentDTO.setMaLoaiLopHoc(maLoaiLopHoc);
            commentDTO.setNoiDung(comment);
            commentDTO.setStatus(true);
            commentDAO.insertComment(commentDTO);

            response.sendRedirect("/YogaCenter/ClassController?returnID=" + maLoaiLopHoc + "&action=showDetails");
//            RequestDispatcher rd = request.getRequestDispatcher();
//            rd.forward(request, response);
        } catch (Exception e) {
            response.sendRedirect("/YogaCenter/ClassController?returnID=" + maLoaiLopHoc + "&action=showDetails");
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
