/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.yogacenterproject.controller;

import com.mycompany.yogacenterproject.dao.BlogDAO;
import com.mycompany.yogacenterproject.dao.BlogImageDAO;
import com.mycompany.yogacenterproject.dao.CommentDAO;
import com.mycompany.yogacenterproject.dto.BLogCateDTO;
import com.mycompany.yogacenterproject.dto.BlogDTO;
import com.mycompany.yogacenterproject.dto.BlogImgDTO;
import com.mycompany.yogacenterproject.dto.CommentDTO;
import com.mycompany.yogacenterproject.dto.HocVienDTO;
import com.mycompany.yogacenterproject.dto.TrainerDTO;
import com.mycompany.yogacenterproject.util.Constants;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Oalskad
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class BlogAdminController extends HttpServlet {

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
            if (action.equals("ViewListBlogUnapprove")) {
                listBlogUnapprove(request, response);
            } else if (action.equals("CreateBlog")) {
                createBlog(request, response);
            } else if (action.equals("Delete")) {
                deleteBlog(request, response);
            } else if (action.equals("Approve")) {
                approveBlog(request, response);
            } else if (action.equals("Detail")) {
                showDetail(request, response);
            } else if (action.equals("ViewListBlogApprove")) {
                listBlogApprove(request, response);
            }
        }
    }

    public void listBlogUnapprove(HttpServletRequest request, HttpServletResponse response) {
        BlogDAO blogDAO = new BlogDAO();
        List<BlogDTO> listBlogDTO = blogDAO.getAllBlogsUnapprove();
        BlogImageDAO blogImageDAO = new BlogImageDAO();
        List<BlogImgDTO> listBlogImgDTO = blogImageDAO.getImageData();
        List<BLogCateDTO> listCate = blogDAO.getAllBlogCate();

        request.setAttribute("listCate", listCate);
        request.setAttribute("listBlogDTO", listBlogDTO);
        request.setAttribute("listBlogImgDTO", listBlogImgDTO);
        RequestDispatcher rd = request.getRequestDispatcher("Authorization/Admin/Blog/ListBlogUnapproved.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(BlogAdminController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BlogAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void listBlogApprove(HttpServletRequest request, HttpServletResponse response) {
        BlogDAO blogDAO = new BlogDAO();
        List<BlogDTO> listBlogDTO = blogDAO.getAllBlogsApprove();
        BlogImageDAO blogImageDAO = new BlogImageDAO();
        List<BlogImgDTO> listBlogImgDTO = blogImageDAO.getImageData();
        List<BLogCateDTO> listCate = blogDAO.getAllBlogCate();

        request.setAttribute("listCate", listCate);
        request.setAttribute("listBlogDTO", listBlogDTO);
        request.setAttribute("listBlogImgDTO", listBlogImgDTO);
        RequestDispatcher rd = request.getRequestDispatcher("Authorization/Admin/Blog/ListBlogApproved.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(BlogAdminController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BlogAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void approveBlog(HttpServletRequest request, HttpServletResponse response) {
        BlogDAO blogDAO = new BlogDAO();
        String maBlog = request.getParameter("maBlog");
        String maCate = request.getParameter("cate");
        blogDAO.approveBlog(maBlog, maCate);
        listBlogUnapprove(request, response);
    }

    public void createBlog(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        BlogDAO blogDAO = new BlogDAO();
        BlogDTO blogDTO = new BlogDTO();

        LocalDate currentDate = LocalDate.now();
        String content = request.getParameter("content");
        String title = request.getParameter("title");
        HttpSession session = request.getSession();
        String maHocVien = null;
        String maTrainer = null;
        if (session.getAttribute("hocVienDTO") != null) {
            HocVienDTO hocVienDTO = (HocVienDTO) session.getAttribute("hocVienDTO");
            maHocVien = hocVienDTO.getMaHV();
        } else if (session.getAttribute("trainerDTO") != null) {
            TrainerDTO trainerDTO = (TrainerDTO) session.getAttribute("trainerDTO");
            maTrainer = trainerDTO.getMaTrainer();
        }
        String AUTO_BLOG_ID = String.format(Constants.MA_BLOG_FORMAT, (blogDAO.lastIDIndexOfBlog() + 1));

        //HOCVIEN CONSTRUCTOR
        String maBlog = AUTO_BLOG_ID;
        blogDTO.setMaBlog(maBlog);
        blogDTO.setContent(content);
        blogDTO.setTitle(title);
        blogDTO.setNgayTaoPost(Date.valueOf(currentDate));
        blogDTO.setMaHV(maHocVien);
        blogDTO.setMaTrainer(maTrainer);
        blogDAO.createBlog(blogDTO);
        insertBanner(request, response, maBlog);
    }

    public void insertBanner(HttpServletRequest request, HttpServletResponse response, String maBlog) throws SQLException, IOException {
        BlogDAO blogDAO = new BlogDAO();
        String AUTO_IMG_ID = String.format(Constants.MA_IMG_FORMAT, (blogDAO.lastIDIndexOfBlogImg() + 1));

        //HOCVIEN CONSTRUCTOR
        String maAnh = AUTO_IMG_ID;

        BlogImgDTO blogImgDTO = new BlogImgDTO();
        blogImgDTO.setMaAnh(maAnh);
        blogImgDTO.setTenAnh("Banner");
        blogImgDTO.setMaBlog(maBlog);

        String imageThumbArray = request.getParameter("Banner");
        List<String> listAnhThumb = new ArrayList<>();
        List<byte[]> imageListThumb = new ArrayList<>();

        String base64String = imageThumbArray.substring(imageThumbArray.indexOf(",") + 1);
        byte[] imageData = Base64.getDecoder().decode(base64String);
        imageListThumb.add(imageData);

        blogDAO.insertImageDataFromDatabase(imageListThumb, blogImgDTO);
    }

    private void deleteBlog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BlogDAO blogDAO = new BlogDAO();
        String maBlog = request.getParameter("maBlog");
        BlogDTO blogDTO = blogDAO.getBlogByID(maBlog);
        blogDAO.Delete(maBlog);
        if (blogDTO.isStatus()) {
            listBlogApprove(request, response);
        } else {
            listBlogUnapprove(request, response);
        }
    }

    private void showDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("maBlog");
        BlogDAO blogDAO = new BlogDAO();
        BlogImageDAO blogImgDAO = new BlogImageDAO();
        CommentDAO cmtDAO = new CommentDAO();
//        id = "BL0002";
        CommentDAO commentDAO = new CommentDAO();
//        id = "BL0002";  
        List<CommentDTO> listComment = commentDAO.getAllCommentsByBlogId(id);
        request.setAttribute("listComment", listComment);
//          Get Blog Details
        BlogDTO blogDetails = blogDAO.getBlogByID(id);

        BlogImgDTO blogImg = blogImgDAO.getImageByBlogID(id);

        request.setAttribute("blogImgDetails", blogImg);
        request.setAttribute("blogDetails", blogDetails);

        RequestDispatcher rd = request.getRequestDispatcher("./Authorization/Admin/Blog/BlogDetails.jsp");
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
            Logger.getLogger(BlogAdminController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(BlogAdminController.class.getName()).log(Level.SEVERE, null, ex);
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
