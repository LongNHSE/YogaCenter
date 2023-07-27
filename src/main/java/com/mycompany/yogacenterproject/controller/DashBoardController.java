/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.yogacenterproject.controller;

import com.google.gson.Gson;
import com.mycompany.yogacenterproject.dao.HoaDonDAO;
import com.mycompany.yogacenterproject.dao.LoaiLopHocDAO;
import com.mycompany.yogacenterproject.dao.SemesterDAO;
import com.mycompany.yogacenterproject.dto.SemesterDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Oalskad
 */
public class DashBoardController extends HttpServlet {

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
            LoaiLopHocDAO loaiLopHocDAO = new LoaiLopHocDAO();
            HashMap<String, Integer> countTrainee = new HashMap();
            countTrainee = loaiLopHocDAO.countTrainee();

            HoaDonDAO hoaDonDAO = new HoaDonDAO();
            HashMap<Date, Integer> countReceipt = new HashMap();

            SemesterDAO semesterDAO = new SemesterDAO();
            SemesterDTO semesterDTO = semesterDAO.getCurrentSemester();

            TreeMap<String, Integer> countRevenue = new TreeMap<>();
            countRevenue = hoaDonDAO.countReceiptSemester();
            countReceipt = hoaDonDAO.countReceipt();

            Gson gson = new Gson();

            String jsonCountTrainee = gson.toJson(countTrainee);
            String jsonCountReceipt = gson.toJson(countReceipt);
            String jsonSemesterDTO = gson.toJson(semesterDTO);
            String jsonCountRevenue = gson.toJson(countRevenue);
            response.setContentType("application/json");

            PrintWriter out = response.getWriter();
            out.println("{");
            out.println("\"traineeData\": " + jsonCountTrainee + ",");
            out.println("\"revenueData\": " + jsonCountRevenue + ",");
            out.println("\"semesterData\": " + jsonSemesterDTO + ",");
            out.println("\"receiptData\": " + jsonCountReceipt);
            out.println("}");

        } catch (SQLException ex) {
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
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
