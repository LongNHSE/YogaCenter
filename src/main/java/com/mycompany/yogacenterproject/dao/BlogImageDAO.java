/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dao;

import com.mycompany.yogacenterproject.dto.BlogImgDTO;
import com.mycompany.yogacenterproject.util.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 *
 * @author devli
 */
public class BlogImageDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<BlogImgDTO> getImageDataByID(String maBlog) {
        byte[] imageData = null;
        List<BlogImgDTO> listImg = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT * FROM [dbo].[blogImg] where [maBlog] = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, maBlog);
            rs = ps.executeQuery();

            while (rs.next()) {
                BlogImgDTO blogImgDTO = new BlogImgDTO();
                blogImgDTO.setMaAnh(rs.getString("maAnh"));
                blogImgDTO.setTenAnh(rs.getString("tenAnh"));
                imageData = rs.getBytes("image");
                String base64image = Base64.getEncoder().encodeToString(imageData);
                blogImgDTO.setImage(base64image);
                blogImgDTO.setMaBlog(rs.getString("maBlog"));
                listImg.add(blogImgDTO);
            }
        } catch (Exception e) {
        }
        return listImg;
    }

    public List<BlogImgDTO> getImageData() {
        byte[] imageData = null;
        List<BlogImgDTO> listImg = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT * FROM [dbo].[blogImg] ";
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {
                BlogImgDTO blogImgDTO = new BlogImgDTO();
                blogImgDTO.setMaAnh(rs.getString("maAnh"));
                blogImgDTO.setTenAnh(rs.getString("tenAnh"));
                imageData = rs.getBytes("image");
                String base64image = Base64.getEncoder().encodeToString(imageData);
                blogImgDTO.setImage(base64image);
                blogImgDTO.setMaBlog(rs.getString("maBlog"));
                listImg.add(blogImgDTO);
            }
        } catch (Exception e) {
        }
        return listImg;
    }

    public List<String> getImageDataFromDatabase() {
        byte[] imageData = null;
        List<String> listAnh = new ArrayList<>();
        String sql = "SELECT [image] FROM [dbo].[blogImg]";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                imageData = rs.getBytes("image");
                String base64Image = Base64.getEncoder().encodeToString(imageData);
                listAnh.add(base64Image);
            }
        } catch (Exception e) {
        }
        return listAnh;
    }

    public BlogImgDTO getImageByBlogID(String id) {
        byte[] imageData = null;
        BlogImgDTO blogImgDTO = new BlogImgDTO();
        String sql = "select * from [dbo].[blogImg] where [maBlog] = ? ";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                blogImgDTO = new BlogImgDTO();
                blogImgDTO.setMaAnh(rs.getString("maAnh"));
                blogImgDTO.setTenAnh(rs.getString("tenAnh"));

                imageData = rs.getBytes("image");
                String base64image = Base64.getEncoder().encodeToString(imageData);
                blogImgDTO.setImage(base64image);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
        }
        return blogImgDTO;
    }

    public List<BlogImgDTO> getImagesByBlogID(String id) {
        List<BlogImgDTO> images = new ArrayList<>();
        String sql = "SELECT * FROM [dbo].[blogImg] WHERE [maBlog] = ?";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                BlogImgDTO blogImgDTO = new BlogImgDTO();
                blogImgDTO.setMaAnh(rs.getString("maAnh"));
                blogImgDTO.setTenAnh(rs.getString("tenAnh"));

                byte[] imageData = rs.getBytes("image");
                String base64image = Base64.getEncoder().encodeToString(imageData);
                blogImgDTO.setImage(base64image);

                images.add(blogImgDTO);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return images;
    }
    
public byte[] getImagesDataByBlogID(String maBlog) {
    byte[] imageData = null;
        String sql = "SELECT image FROM blogImg WHERE maBlog = ?";
    try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, maBlog);
            rs = ps.executeQuery();
        // Process the result
        if (rs.next()) {
            imageData = rs.getBytes("image");
        }
    } catch (SQLException e) {
        // Handle any potential exceptions
        e.printStackTrace();
    } 

    return imageData;
}
    public static void main(String[] args) {
    String maBlog = "B0006"; // Replace with the desired maBlog value

    BlogImageDAO blogImgDAO = new BlogImageDAO();
    byte[] imageData = blogImgDAO.getImagesDataByBlogID(maBlog);

    if (imageData != null) {
        // Print or process the image data as needed
        System.out.println("Image data retrieved successfully.");
    } else {
        System.out.println("No image data found for the specified maBlog.");
    }
}

//     Test
}
