/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.yogacenterproject.controller;

import com.mycompany.yogacenterproject.dao.HoaDonDAO;
import com.mycompany.yogacenterproject.dao.HocVienDAO;
import com.mycompany.yogacenterproject.dao.TrainerDAO;
import com.mycompany.yogacenterproject.dto.HoaDonDTO;
import com.mycompany.yogacenterproject.dto.HocVienDTO;
import com.mycompany.yogacenterproject.dto.TrainerDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
public class AdminController extends HttpServlet {

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
                listHocVienDTO(request, response);
            } else if (action.equals("detailHocVien")) {

            } else if (action.equals("View Receipt")) {
                listReceipt(request, response);
            } else if (action.equals("listTrainer")) {
                listTrainerDTO(request, response);
            } else if (action.equals("detailTrainer")) {

            } else if (action.equals("listLopHoc")) {

            } else if (action.equals("detailLopHoc")) {

            }

//            
//            
//            switch (action) {
//                case "listHocVienDTO":
//                    listHocVienDTO(request,response);
//                    break;
//                default:
//                    throw new AssertionError();
//            }
        }
    }

    public void listHocVienDTO(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<HocVienDTO> listHocVienDTO = new ArrayList<HocVienDTO>();
        HocVienDAO hocVienDAO = new HocVienDAO();
        listHocVienDTO = hocVienDAO.readListHocVien();
        request.setAttribute("listHocVienDTO", listHocVienDTO);
//        response.sendRedirect("./Admin/HocVien/HocVienList.jsp");
        RequestDispatcher rs = request.getRequestDispatcher("./Admin/HocVien/HocVienList.jsp");
        rs.forward(request, response);

    }

    public void listTrainerDTO(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<TrainerDTO> listTrainerDTO = new ArrayList<TrainerDTO>();
        TrainerDAO trainerDAO = new TrainerDAO();
        listTrainerDTO = trainerDAO.readListTrainer();
        request.setAttribute("listTrainerDTO", listTrainerDTO);
//        response.sendRedirect("./Admin/HocVien/HocVienList.jsp");
        RequestDispatcher rs = request.getRequestDispatcher("./Admin/Trainer/TrainerList.jsp");
        rs.forward(request, response);

    }

    public void listReceipt(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        List<HoaDonDTO> listHoaDon = new ArrayList<HoaDonDTO>();
        HoaDonDAO hoaDonDAO = new HoaDonDAO();
       
        listHoaDon = hoaDonDAO.listHoaDon(request.getParameter("maHV"));
         
        request.setAttribute("listHoaDon", listHoaDon);
        RequestDispatcher rs = request.getRequestDispatcher("./Admin/HocVien/HoaDonList.jsp");
        rs.forward(request, response);

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
