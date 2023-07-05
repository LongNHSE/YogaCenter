/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.yogacenterproject.controller;

import com.mycompany.yogacenterproject.dao.LopHocDAO;
import com.mycompany.yogacenterproject.dao.PhongHocDAO;
import com.mycompany.yogacenterproject.dao.TrainerDAO;
import com.mycompany.yogacenterproject.dto.LopHocDTO;
import com.mycompany.yogacenterproject.dto.PhongHocDTO;
import com.mycompany.yogacenterproject.dto.TrainerDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Oalskad
 */
public class ClassUnassignedController extends HttpServlet {

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
            String action = request.getParameter("action");
            if (action.equals("Delete")) {
                deleteUnassignedClass(request, response);
            } else if (action.equals("Update")) {
                updateClassPage(request, response);
            } else if (action.equals("UpdateClass")) {
                updateClass(request, response);
            }
        }
    }

    public void deleteUnassignedClass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LopHocDAO lopHocDAO = new LopHocDAO();
        lopHocDAO.deleteClassUnassign(request.getParameter("maLopHoc"));
        RequestDispatcher rd = request.getRequestDispatcher("/AdminController?action=listClassUnassigned");
        rd.forward(request, response);
    }

    public void updateClassPage(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String classID = request.getParameter("maLopHoc");
        LopHocDAO lopHocDAO = new LopHocDAO();
        LopHocDTO lopHocDTO = lopHocDAO.searchClassById(classID);
        PhongHocDAO phongHocDAO = new PhongHocDAO();
        List<PhongHocDTO> listPhongHocDTO = new ArrayList<>();

        listPhongHocDTO = phongHocDAO.getListEmptyRoom(lopHocDAO.maSlotClassUnassigned(lopHocDTO.getMaLopHoc()), lopHocDAO.showThuWithStringArrayOfClassUnassigned(classID));

        request.setAttribute("lopHocDTO", lopHocDTO);
        request.setAttribute("listPhongHocDTO", listPhongHocDTO);
        RequestDispatcher rd = request.getRequestDispatcher("/Authorization/Admin/Class/ClassUnassignedUpdate.jsp");
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

        lopHocDTO.setSoLuongHV(soLuongHV);
        lopHocDAO.updateClass(lopHocDTO);

        RequestDispatcher rd = request.getRequestDispatcher("/AdminController?action=listClassUnassigned");
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
            Logger.getLogger(ClassUnassignedController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ClassUnassignedController.class.getName()).log(Level.SEVERE, null, ex);
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
