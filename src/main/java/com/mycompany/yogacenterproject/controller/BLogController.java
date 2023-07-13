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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author devli
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 4, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class BLogController extends HttpServlet {

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
        String action = request.getParameter("action");
        try {
            PrintWriter out = response.getWriter();
            if (action.equals("showImageList")) {
                showImageList(request, response);
            } else if (action.equals("showBlogs")) {
                showBlogs(request, response);
            } else if (action.equals("showDetails")) {
                showDetail(request, response);
            } else if (action.equals("MyBlog")) {
                viewMyBlog(request, response);
            } else if (action.equals("Delete")) {
                deleteBlog(request, response);
                viewMyBlog(request, response);
            } else if (action.equals("Update")) {
                viewMyBlog(request, response);
            } else if (action.equals("CreateBlog")) {
//                String content = request.getParameter("content");
//                // Chuyển đổi ký tự xuống dòng thành thẻ <br>
////                out.print( content ); //
                createBlog(request, response);
                viewMyBlog(request, response);
            } else if (action.equals("showBlogCategory")) {
                showBlogCategory(request, response);
            } else if (action.equals("Detail")) {
                showDetail2(request, response);
            }
        } catch (Exception e) {

        }

    }

    private void showBlogs(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BlogDAO dao = new BlogDAO();
        List<BlogDTO> listBlog = dao.getAllBlogs();
        List<BLogCateDTO> listCate = dao.getAllBlogCate();

        request.setAttribute("listCate", listCate);
        request.setAttribute("listBlog", listBlog);

        RequestDispatcher rd = request.getRequestDispatcher("/Blog/Blog.jsp");
        rd.forward(request, response);
    }

    private void showBlogCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        List<BLogCateDTO> listCate = dao.getAllBlogCate();

        request.setAttribute("listCate", listCate);
        request.setAttribute("listBlog", listBlogCate);
        RequestDispatcher rd = request.getRequestDispatcher("/Blog/Blog.jsp");
        rd.forward(request, response);
    }

    private void getBlogAuthor(HttpServletRequest request, HttpServletResponse response) {

    }

//    private void showDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String id = request.getParameter("returnID");
//        BlogDAO blogDAO = new BlogDAO();
//        BlogImageDAO blogImgDAO = new BlogImageDAO();
////          Get Blog Details
//        BlogDTO blogDetails = blogDAO.getBlogByID(id);
//        List<BlogDTO> blogLatest = blogDAO.getLatestPosts();
//        BlogImgDTO blogImg = blogImgDAO.getImageByBlogID(id);
//
//        request.setAttribute("blogImgDetails", blogImg);
//        request.setAttribute("blogDetails", blogDetails);
//        request.setAttribute("blogLatest", blogLatest);
//
//        RequestDispatcher rd = request.getRequestDispatcher("/Blog/BlogDetails.jsp");
//        rd.forward(request, response);
//    }
    private void showDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("returnID");
        BlogDAO blogDAO = new BlogDAO();
        BlogImageDAO blogImgDAO = new BlogImageDAO();
        CommentDAO cmtDAO = new CommentDAO();
//        id = "BL0002";

//          Get Blog Details
        BlogDTO blogDetails = blogDAO.getBlogByID(id);
        List<BlogDTO> blogLatest = blogDAO.getLatestPosts();
        BlogImgDTO blogImg = blogImgDAO.getImageByBlogID(id);
        List<BLogCateDTO> listCate = blogDAO.getAllBlogCate();
        List<CommentDTO> listCmt = cmtDAO.getAllCommentsByBlogID(id);

        request.setAttribute("blogImgDetails", blogImg);
        request.setAttribute("blogDetails", blogDetails);
        request.setAttribute("blogLatest", blogLatest);
        request.setAttribute("blogCate", listCate);
        request.setAttribute("blogCmt", listCmt);
        RequestDispatcher rd = request.getRequestDispatcher("/Blog/BlogDetails.jsp");
        rd.forward(request, response);
    }

    private void showDetail2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("maBlog");
        BlogDAO blogDAO = new BlogDAO();
        BlogImageDAO blogImgDAO = new BlogImageDAO();
//        id = "BL0002";
//          Get Blog Details
        BlogDTO blogDetails = blogDAO.getBlogByID(id);
        List<BlogDTO> blogLatest = blogDAO.getLatestPosts();
        BlogImgDTO blogImg = blogImgDAO.getImageByBlogID(id);
        List<BLogCateDTO> listCate = blogDAO.getAllBlogCate();

        request.setAttribute("blogImgDetails", blogImg);
        request.setAttribute("blogDetails", blogDetails);
        request.setAttribute("blogLatest", blogLatest);
        request.setAttribute("blogCate", listCate);
        RequestDispatcher rd = request.getRequestDispatcher("/Blog/BlogDetails.jsp");
        rd.forward(request, response);
    }

    //      -- Image : Start --
    private void showImageList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BlogImageDAO dao = new BlogImageDAO();
        List<String> listAnh = dao.getImageDataFromDatabase();
        request.setAttribute("listAnh", listAnh);
        request.getRequestDispatcher("/Blog/Blog.jsp").forward(request, response);
    }

    private void viewMyBlog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        BlogDAO blogDAO = new BlogDAO();
        if (session.getAttribute("hocVienDTO") != null) {
            HocVienDTO hocVienDTO = (HocVienDTO) session.getAttribute("hocVienDTO");
            List<BlogDTO> listBlog = blogDAO.getAllBlogsByMaHV(hocVienDTO.getMaHV());
            request.setAttribute("listBlog", listBlog);
            request.getRequestDispatcher("/Blog/MyBlogTrainee.jsp").forward(request, response);
        } else if (session.getAttribute("trainerDTO") != null) {
            TrainerDTO trainerDTO = (TrainerDTO) session.getAttribute("trainerDTO");
            List<BlogDTO> listBlog = blogDAO.getAllBlogsByMaTrainer(trainerDTO.getMaTrainer());
            request.setAttribute("listBlog", listBlog);
            request.getRequestDispatcher("/Blog/MyBlogTrainee.jsp").forward(request, response);
        }

    }

    private void deleteBlog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BlogDAO blogDAO = new BlogDAO();
        String maBlog = request.getParameter("maBlog");
        blogDAO.Delete(maBlog);

    }

    public void createBlog(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        BlogDAO blogDAO = new BlogDAO();
        BlogDTO blogDTO = new BlogDTO();
        LocalDate currentDate = LocalDate.now();
        String content = request.getParameter("content");
        content = content.replace("\n", "<br>");
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

    public void updateBlogPage(HttpServletRequest request, HttpServletResponse response) {
        BlogDAO blogDAO = new BlogDAO();
        String maBlog = request.getParameter("maBlog");
        BlogDTO blogDTO = blogDAO.getBlogByID(maBlog);
        request.setAttribute("blogDTO", blogDTO);

    }

    public void updateBlog(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        BlogDAO blogDAO = new BlogDAO();
        BlogDTO blogDTO = new BlogDTO();

        LocalDate currentDate = LocalDate.now();
        String content = request.getParameter("content");
        content = content.replace("\n", "<br>");
        String title = request.getParameter("title");
          String maBlog = request.getParameter("maBlog");
        HttpSession session = request.getSession();
        blogDTO.setContent(content);
        blogDTO.setTitle(title);
        blogDTO.setMaBlog(maBlog);
        blogDAO.updateBlog(blogDTO);

        viewMyBlog(request, response);
     
        
    
 
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
