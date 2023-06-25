/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.yogacenterproject.controller;

import com.mycompany.yogacenterproject.dao.LopHocImageDAO;
import com.mycompany.yogacenterproject.dto.LopHocIMGDTO;
import com.mycompany.yogacenterproject.util.Constants;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Oalskad
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class ImageController extends HttpServlet {

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
              String action = request.getParameter("action");
              try {
                    if(action.equals("showDetails")){
                          viewImgByID(request, response);
                    }
              } catch (Exception e) {
              }
//            insertImg(request, response);
//            viewImg(request, response);
//            String action = request.getParameter("action");
//
//            if (action.equals("insertImg")) {
//                insertImg(request, response);
//                viewImg(request, response);
//            } else if (action.equals("viewImg")) {
//                viewImg(request, response);
//            }
        }
    }

    private void insertImg(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        LopHocImageDAO lopHocImageDAO = new LopHocImageDAO();
        List<byte[]> imageList = new ArrayList<>();
        LopHocIMGDTO lopHocImageDTO = new LopHocIMGDTO();

        String AUTO_IMG_ID = String.format(Constants.MA_IMG_FORMAT, (lopHocImageDAO.lastIDIndex() + 1));

        //HOCVIEN CONSTRUCTOR
        String maAnh = AUTO_IMG_ID;
        lopHocImageDTO.setMaAnh(maAnh);

        String[] imageArray = request.getParameter("listImage").split("\\s+");
        List<String> listAnh = new ArrayList<>();
        for (String a : imageArray) {
            String base64String = a.substring(a.indexOf(",") + 1);
            byte[] imageData = Base64.getDecoder().decode(base64String);
            imageList.add(imageData);
            listAnh.add(a);
        }
        lopHocImageDAO.insertImageDataFromDatabase(imageList, lopHocImageDTO);
    }

    private void viewImg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        LopHocImageDAO lopHocImageDAO = new LopHocImageDAO();
        List<String> lis = lopHocImageDAO.getImageDataFromDatabase();
        request.setAttribute("imageList", lis);

        RequestDispatcher rs = request.getRequestDispatcher("/image.jsp");
        rs.forward(request, response);
    }
    private void viewImgByID(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
          String cid = request.getParameter("returnID");
          LopHocImageDAO dao = new LopHocImageDAO();
          List<LopHocIMGDTO> list = dao.getImageBasedOnTypeID(cid);
            request.setAttribute("imageListByID", list);
            RequestDispatcher rs = request.getRequestDispatcher("/Class/ClassDetail.jsp");
            rs.forward(request, response);        
    }
    
    private String getFileName(Part part) {
        String header = part.getHeader("content-disposition");
        for (String headerPart : header.split(";")) {
            if (headerPart.trim().startsWith("filename")) {
                return headerPart.substring(headerPart.indexOf('=') + 1).trim()
                        .replace("\"", "");
            }
        }
        return part.getName();
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
            Logger.getLogger(ImageController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ImageController.class.getName()).log(Level.SEVERE, null, ex);
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
