/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.yogacenterproject.controller;

import com.mycompany.yogacenterproject.dao.BlogDAO;
import com.mycompany.yogacenterproject.dao.BlogImageDAO;
import com.mycompany.yogacenterproject.dto.BLogCateDTO;
import com.mycompany.yogacenterproject.dto.BlogDTO;
import com.mycompany.yogacenterproject.dto.BlogImgDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author devli
 */
public class BLogController extends HttpServlet {

      /**
       * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
       *
       * @param request servlet request
       * @param response servlet response
       * @throws ServletException if a servlet-specific error occurs
       * @throws IOException if an I/O error occurs
       */
      protected void processRequest(HttpServletRequest request, HttpServletResponse response)
              throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            String action = request.getParameter("action");
            try {
                  if (action.equals("showImageList")) {
                        showImageList(request, response);
                          }
                  if(action.equals("showBlogs")){
                        showBlogs(request, response);
                  }
                  if(action.equals("showDetails")){
                      showDetail(request, response);
                  }
                  if(action.equals("showBlogCategory")){
                      showBlogCategory(request, response);
                  }
            } catch (Exception e) {
            }
      }
//      -- Blog: Start --
      private void showBlogs(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
            BlogDAO dao = new BlogDAO();
            List<BlogDTO> listBlog = dao.getAllBlogs();
             List<BLogCateDTO> listCate = dao.getAllBlogCate();
        
            request.setAttribute("listCate", listCate);             
            request.setAttribute("listBlog", listBlog);

            RequestDispatcher rd = request.getRequestDispatcher("/Blog/Blog.jsp");
            rd.forward(request, response);
      }
      private void showBlogCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
          String id = request.getParameter("returnID");  
           List<BlogDTO> listBlogCate;       
            BlogDAO dao = new BlogDAO();
            BlogImageDAO imgDAO = new BlogImageDAO();
//                if (id != null && !id.isEmpty()) {
//                    listBlogCate = dao.getBlogByCategoryID(id);
//                } else {
//                    listBlogCate = new ArrayList<>(); 
//                }
                if (id != null && !id.isEmpty()) {
                    listBlogCate = dao.getBlogByCategoryID(id);
                    for (BlogDTO blog : listBlogCate) {
                        List<BlogImgDTO> blogImages = imgDAO.getImagesByBlogID(blog.getMaBlog());
                        blog.setImage(blogImages); // Set danh sách ảnh cho mỗi bài viết
                    }
                } else {
                    listBlogCate = new ArrayList<>();
                }
             request.setAttribute("listBlog", listBlogCate); 
             RequestDispatcher rd = request.getRequestDispatcher("/Blog/Blog.jsp");
                rd.forward(request, response);
      }
      private void getBlogAuthor(HttpServletRequest request, HttpServletResponse response){
          
      }
      private void showDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
          String id = request.getParameter("returnID");
          BlogDAO blogDAO = new BlogDAO();
          BlogImageDAO blogImgDAO = new BlogImageDAO();
//          Get Blog Details
            BlogDTO blogDetails = blogDAO.getBlogByID(id);
            List<BlogDTO> blogLatest = blogDAO.getLatestPosts();
            BlogImgDTO blogImg = blogImgDAO.getImageByBlogID(id);

            
            request.setAttribute("blogImgDetails", blogImg);
            request.setAttribute("blogDetails", blogDetails);
            request.setAttribute("blogLatest", blogLatest);

            RequestDispatcher rd = request.getRequestDispatcher("/Blog/BlogDetails.jsp");
            rd.forward(request, response);            
      }

//      -- Blog: End --
      
      
//      -- Image : Start --
      private void showImageList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
            BlogImageDAO dao = new BlogImageDAO();
             List<String> listAnh = dao.getImageDataFromDatabase();
            request.setAttribute("listAnh", listAnh);
              request.getRequestDispatcher("/Blog/Blog.jsp").forward(request, response); 
      }
//      -- Image : End --


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
