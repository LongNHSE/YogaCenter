/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.yogacenterproject.controller;

import com.mycompany.yogacenterproject.dao.HocVienDAO;
import com.mycompany.yogacenterproject.dao.HoaDonDAO;
import com.mycompany.yogacenterproject.dao.LopHocDAO;
import com.mycompany.yogacenterproject.dto.HoaDonDTO;
import com.mycompany.yogacenterproject.dto.HocVienDTO;
import com.mycompany.yogacenterproject.dto.LopHocDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProfileController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {PrintWriter out = response.getWriter();
            // log("chay vao process request");////////////////////
            String action = request.getParameter("action");
            if (action.equals("viewProfile")) {
                viewProfile(request, response, "profile");
            } else if (action.equals("viewTransaction")) {
                viewHocVienTransaction(request, response);
            } else if (action.equals("updateProfile")) {
                updateProfile(request, response);
            } else if (action.equals("viewUpdateProfile")) {
                log("action====" + action);
                viewProfile(request, response, "updateProfile");
            } else if (action.equals("classList")) {
//                HttpSession session = request.getSession();
//                HocVienDTO hocVienDTO = (HocVienDTO) session.getAttribute("hocVienDTO");
//                out.print(hocVienDTO.getMaHV());
                classList(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void classList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // View profile trainee
        //  log("chay vao view Profile");////////////////////
        HttpSession session = request.getSession();
        HocVienDTO hocVienDTO = (HocVienDTO) session.getAttribute("hocVienDTO");

        LopHocDAO lopHocDAO = new LopHocDAO();
        List<LopHocDTO> listLopHocDTO = new ArrayList<>();
        listLopHocDTO = lopHocDAO.getListClassOfTrainee(hocVienDTO.getMaHV());
        ///set Attribute
//        session.setAttribute("hocVienDTO", hocVienDTO);
        request.setAttribute("listLopHocDTO", listLopHocDTO);
        RequestDispatcher rd = request.getRequestDispatcher("./Authorization/TraineePrivilege/ClassList.jsp");
        rd.forward(request, response);
    }

    public void viewProfile(HttpServletRequest request, HttpServletResponse response, String whereTo) throws ServletException, IOException {        // View profile trainee
       // log("chay vao view Profile");////////////////////
        HttpSession session = request.getSession();
        HocVienDTO hocVienDTO = (HocVienDTO) session.getAttribute("hocVienDTO");
        HocVienDAO hocVienDAO = new HocVienDAO();
        request.setAttribute("hocVienDTO", hocVienDTO);
        if (whereTo.equalsIgnoreCase("updateProfile")) {
            RequestDispatcher rd = request.getRequestDispatcher("./Authorization/TraineePrivilege/updateProfile.jsp");
            rd.forward(request, response);
        } else if (whereTo.equalsIgnoreCase("profile")) {
            RequestDispatcher rd = request.getRequestDispatcher("./Authorization/TraineePrivilege/profile.jsp");
            rd.forward(request, response);
        }

        {

        }
    }

//////////SET MAHV HIDDEN MOI DEM VE DC, RELOAD TRANG VE STATELESS >< K CO CACH GIU MAHV SAU KHI NHAN SAVE CHANGE, TRANG CAP NHAT DUNG LUC   
    public void updateProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HocVienDAO hocVienDAO = new HocVienDAO();
        HttpSession session = request.getSession();
        String maHV = request.getParameter("maHV");
        log(maHV);///////
        String username = request.getParameter("username");
        String ho = request.getParameter("ho");
        String ten = request.getParameter("ten");
        String phone = request.getParameter("phone");

//////RECEIVING STRING DATE FROM JSP, THEN CONVERT TO DATE TYPE
        String date = request.getParameter("dob");
        Date dateTime = Date.valueOf(date);
        LocalDate dob = Instant.ofEpochMilli(dateTime.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
/////////////////////////////            
        HocVienDTO changeHocVien = (HocVienDTO) session.getAttribute("hocVienDTO");
        changeHocVien.setDob(dob);
        changeHocVien.setEmail(changeHocVien.getEmail());
        changeHocVien.setGender(changeHocVien.getGender());
        changeHocVien.setHo(ho);
        changeHocVien.setMaHV(maHV);
        changeHocVien.setTen(ten);
        changeHocVien.setPhone(phone);
        changeHocVien.setUsername(username);
        session.setAttribute("hocVienDTO", changeHocVien);
        hocVienDAO.updateHocVien(changeHocVien);
//        log(changeHocVien.toString());
        viewProfile(request, response,"profile");
    }

    public void viewHocVienTransaction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        HocVienDTO hocVienDTO = (HocVienDTO) session.getAttribute("hocVienDTO");
        HoaDonDAO hoaDonDAO = new HoaDonDAO();
        List<HoaDonDTO> listHoaDon = new ArrayList<HoaDonDTO>();
        listHoaDon = hoaDonDAO.listHoaDon(hocVienDTO.getMaHV());
        HocVienDAO hocVienDAO = new HocVienDAO();

        ///set Attribute
        session.setAttribute("hocVienDTO", hocVienDTO);
        session.setAttribute("listHoaDon", listHoaDon);
        RequestDispatcher rd = request.getRequestDispatcher("./Authorization/TraineePrivilege/transaction.jsp");
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
