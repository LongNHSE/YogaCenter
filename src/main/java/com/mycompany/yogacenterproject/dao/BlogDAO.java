/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dao;

import com.mycompany.yogacenterproject.dto.BlogDTO;
import com.mycompany.yogacenterproject.dto.BlogImgDTO;
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
                        blogDTO.setImage(blogImgDTO);
                        listBlog.add(blogDTO);
                  }               
            } catch (Exception e) {
            }
            return listBlog;
      }
            public List<BlogDTO> getAllBlogsUnapprove(){
            List<BlogDTO> listBlog = new ArrayList<>();
            BlogImageDAO daoImg = new BlogImageDAO();
            String sql = "SELECT * FROM [dbo].[blogPost] where status = 'fasle'";
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
                        blogDTO.setImage(blogImgDTO);
                        listBlog.add(blogDTO);
                  }               
            } catch (Exception e) {
            }
            return listBlog;
      }
      public static void main(String[] args) {
            BlogDAO dao = new BlogDAO();
            List<BlogDTO> listBlog = dao.getAllBlogs();
            for (BlogDTO blog : listBlog) {
                  System.out.println(blog.toString());
            }
      }
}
