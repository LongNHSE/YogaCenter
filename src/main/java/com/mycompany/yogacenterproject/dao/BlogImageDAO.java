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
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT [image] FROM [dbo].[blogImg]";
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

//     Test
    public static void main(String[] args) {
        BlogImageDAO dao = new BlogImageDAO();
        List<BlogImgDTO> listAnh = dao.getImageDataByID("B0003");

        for (BlogImgDTO listImage : listAnh) {
            System.out.println(listImage);
        }
    }
}
