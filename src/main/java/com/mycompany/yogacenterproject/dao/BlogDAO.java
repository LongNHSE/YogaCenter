/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dao;

import com.mycompany.yogacenterproject.dto.BLogCateDTO;
import com.mycompany.yogacenterproject.dto.BlogDTO;
import com.mycompany.yogacenterproject.dto.BlogImgDTO;
import com.mycompany.yogacenterproject.dto.HocVienDTO;
import com.mycompany.yogacenterproject.util.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author devli
 */
public class BlogDAO {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;      
     public List<BlogDTO> getAllBlogs(){
            List<BlogDTO> listBlog = new ArrayList<>();
            BlogImageDAO daoImg = new BlogImageDAO();
            String sql = "SELECT * FROM [dbo].[blogPost]";
            try {
                  conn = DBUtils.getConnection();
                  ps = conn.prepareStatement(sql);
                  rs = ps.executeQuery();
                  while(rs.next()){
                        BlogDTO blogDTO = new BlogDTO();
                        String maBlog = rs.getString("maBlog");
                        List<BlogImgDTO> blogImgDTO = daoImg.getImageDataByID(maBlog);
                        blogDTO.setMaBlog(maBlog);
                        blogDTO.setTitle(rs.getString("tieuDe"));
                        blogDTO.setContent(rs.getString("noiDung"));
                        blogDTO.setDate(rs.getString("ngayTaoPost"));
                        blogDTO.setMaHV(rs.getString("maHV"));
                        blogDTO.setStatus(rs.getBoolean("status"));
                        blogDTO.setMaCate(rs.getString("maCate"));
                        blogDTO.setImage(blogImgDTO);
                        listBlog.add(blogDTO);
                  }               
            } catch (Exception e) {
            }
            return listBlog;
      }
      public BlogDTO getBlogByID(String maBlog){
          BlogDTO blogDTO = new BlogDTO();
          String sql = "select * from [dbo].[blogPost] where [maBlog] = ? ";
          try {
                  conn = DBUtils.getConnection();
                  ps = conn.prepareStatement(sql);
                  ps.setString(1, maBlog);
                  rs = ps.executeQuery();       
                  while(rs.next()){
                      blogDTO = new BlogDTO();
                      blogDTO.setMaBlog(rs.getString("maBlog"));
                      blogDTO.setTitle(rs.getString("tieuDe"));
                      blogDTO.setContent(rs.getString("noiDung"));
                      blogDTO.setDate(rs.getString("ngayTaoPost"));
                      blogDTO.setMaHV(rs.getString("maHV"));
                      blogDTO.setStatus(rs.getBoolean("status"));
                        blogDTO.setMaCate(rs.getString("maCate"));
                      
                  }
                        rs.close();
                        ps.close();
                        conn.close();                  
          } catch (Exception e) {
          }
          return blogDTO;
      }
        public List<BlogDTO> getLatestPosts() {
            List<BlogDTO> latestPosts = new ArrayList<>();
            String sql = "SELECT TOP 4 * FROM [dbo].[blogPost] ORDER BY [ngayTaoPost] DESC";

            try {
                conn = DBUtils.getConnection();
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();

                while (rs.next()) {
                    BlogDTO blogDTO = new BlogDTO();
                    BlogImageDAO blogImgDAO = new BlogImageDAO();
                    blogDTO.setMaBlog(rs.getString("maBlog"));
                    blogDTO.setTitle(rs.getString("tieuDe"));
                    blogDTO.setContent(rs.getString("noiDung"));
                    blogDTO.setDate(rs.getString("ngayTaoPost"));
                    blogDTO.setMaHV(rs.getString("maHV"));
                    blogDTO.setStatus(rs.getBoolean("status"));
                    blogDTO.setMaCate(rs.getString("maCate"));

                    latestPosts.add(blogDTO);
                }

                rs.close();
                ps.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return latestPosts;
        }
        
        
            public List<BlogDTO> getBlogByCategoryID(String cid){
                List<BlogDTO> listBlog = new ArrayList<>();
                String sql = "SELECT * FROM [dbo].[blogPost] where [maCate]= ? ";
                try {
                        conn = DBUtils.getConnection();
                        ps = conn.prepareStatement(sql);
                        ps.setString(1, cid);
                        rs = ps.executeQuery();
                        
                        while(rs.next()){
                            BlogDTO blogDTO = new BlogDTO();
                            blogDTO.setMaBlog(rs.getString("maBlog"));
                            blogDTO.setTitle(rs.getString("tieuDe"));
                            blogDTO.setContent(rs.getString("noiDung"));
                            blogDTO.setDate(rs.getString("ngayTaoPost"));
                            blogDTO.setMaHV(rs.getString("maHV"));
                            blogDTO.setStatus(rs.getBoolean("status"));
                            blogDTO.setMaCate(rs.getString("maCate"));
                            listBlog.add(blogDTO);
                        }             
                        rs.close();
                        ps.close();
                        conn.close();
                                   
                } catch (Exception e) {
                }
                return listBlog;
            } 
         
            public List<BLogCateDTO> getAllBlogCate(){
                List<BLogCateDTO> listCate = new ArrayList<>();
                String sql = "SELECT * FROM [dbo].[blogCategories]";
                try {
                        conn = DBUtils.getConnection();
                        ps = conn.prepareStatement(sql);
                        rs = ps.executeQuery();
                        while(rs.next()){
                            BLogCateDTO bLogCateDTO = new BLogCateDTO();
                            bLogCateDTO.setMaCate(rs.getString("maCate"));
                            bLogCateDTO.setTenCate(rs.getString("tenCate"));
                            listCate.add(bLogCateDTO);
                        }
                        rs.close();
                        ps.close();
                        conn.close();                       
                } catch (Exception e) {
                }
                return listCate;
                
            }
      public String getAuthorNameByID(String maHV){
          String authorName = null;
          String sql = "SELECT ten FROM [dbo].[hocVien] WHERE maHV = ? ";
          try {
                  conn = DBUtils.getConnection();
                  ps = conn.prepareStatement(sql);
                  ps.setString(1, maHV);
                  rs = ps.executeQuery();
                  while(rs.next()){
                      authorName= rs.getString("Ten");
                  }                                  
                rs.close();
                ps.close();
                conn.close();
          } catch (Exception e) {
          }
          return authorName; 
      }
        public BlogDTO getBlogAuthor(String maBlog) {
           BlogDTO blogDTO = null;
           String sql = "SELECT b.maBlog, b.tieuDe, b.noiDung, b.ngayTaoPost, b.maHV, h.Ten\n" +
                        "FROM blogPost b\n" +
                        "INNER JOIN hocVien h ON b.maHV = h.maHV\n" +
                        "WHERE b.maBlog = ? ";
           try {
               conn = DBUtils.getConnection();
               ps = conn.prepareStatement(sql);
               ps.setString(1, maBlog);
               rs = ps.executeQuery();
               if (rs.next()) {
                   blogDTO = new BlogDTO();
                   blogDTO.setMaBlog(rs.getString("maBlog"));
                   blogDTO.setTitle(rs.getString("tieuDe"));
                   blogDTO.setContent(rs.getString("noiDung"));
                   blogDTO.setDate(rs.getString("ngayTaoPost"));
                   blogDTO.setMaHV(rs.getString("maHV"));
                   blogDTO.setStatus(rs.getBoolean("status"));
                   // Lấy tên tác giả dựa vào mã HV và gán vào blogDTO
                   String tenHV = getAuthorNameByID(rs.getString("maHV"));
                   blogDTO.setTenHV(tenHV);
               }
               rs.close();
               ps.close();
               conn.close();
           } catch (Exception e) {
               e.printStackTrace();
           }
           return blogDTO;
       }
        
        
      public static void main(String[] args) {
            BlogDAO dao = new BlogDAO();
            BlogDTO blogDTO = dao.getBlogByID("B0001");
            List<BlogDTO> listB = new ArrayList<>();
            List<BLogCateDTO> listCate = new ArrayList<>();
            listCate  =dao.getAllBlogCate();
            for( BLogCateDTO o: listCate){
                System.out.println(o.getTenCate());
            }
            
      }
}
