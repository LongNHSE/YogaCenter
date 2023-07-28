/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dao;

import com.mycompany.yogacenterproject.dto.BlogDTO;
import com.mycompany.yogacenterproject.dto.CommentDTO;
import com.mycompany.yogacenterproject.util.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author devli
 */
public class CommentDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<CommentDTO> getAllComments() {
        List<CommentDTO> listCmt = new ArrayList<>();
        String sql = "SELECT * FROM [dbo].[Comment]";
        try {
            HocVienDAO hocVienDAO = new HocVienDAO();
            TrainerDAO trainerDAO = new TrainerDAO();
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                CommentDTO cmtDTO = new CommentDTO();

                BlogDTO blogDTO = new BlogDTO();
                cmtDTO.setMaComment(rs.getString("maComment"));
                cmtDTO.setHocVienDTO(hocVienDAO.searchHocVienById(rs.getString("maHV")));
                cmtDTO.setTrainerDTO(trainerDAO.searchTrainerById(rs.getString("maTrainer")));
                cmtDTO.setMaLopHoc(rs.getString("maLopHoc"));
                cmtDTO.setMaLoaiLopHoc(rs.getString("maLoaiLopHoc"));
                cmtDTO.setMaBlog(rs.getString("maBlog"));
                cmtDTO.setNoiDung(rs.getString("noiDung"));
                cmtDTO.setDate(rs.getDate("Date"));
                cmtDTO.setStatus(rs.getBoolean("status"));

                listCmt.add(cmtDTO);
            }
        } catch (Exception e) {
        }
        return listCmt;
    }

    public List<CommentDTO> getAllCommentsByBlogID(String bid) {
        List<CommentDTO> listCmt = new ArrayList<>();
        String sql = "SELECT * FROM [dbo].[Comment] where [maBlog] = ? ";
        try {
            HocVienDAO hocVienDAO = new HocVienDAO();
            TrainerDAO trainerDAO = new TrainerDAO();
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, bid);
            rs = ps.executeQuery();
            while (rs.next()) {
                CommentDTO cmtDTO = new CommentDTO();
                cmtDTO.setMaComment(rs.getString("maComment"));
                cmtDTO.setHocVienDTO(hocVienDAO.searchHocVienById(rs.getString("maHV")));
                cmtDTO.setTrainerDTO(trainerDAO.searchTrainerById(rs.getString("maTrainer")));
                cmtDTO.setMaLopHoc(rs.getString("maLopHoc"));
                cmtDTO.setMaLoaiLopHoc(rs.getString("maLoaiLopHoc"));
                cmtDTO.setMaBlog(rs.getString("maBlog"));
                cmtDTO.setNoiDung(rs.getString("noiDung"));
                cmtDTO.setDate(rs.getDate("Date"));
                cmtDTO.setStatus(rs.getBoolean("status"));
                listCmt.add(cmtDTO);
            }
        } catch (Exception e) {
        }
        return listCmt;
    }

    public List<CommentDTO> getAllCommentsByTypeClass(String bid) {
        List<CommentDTO> listCmt = new ArrayList<>();
        String sql = "SELECT * FROM [dbo].[Comment] where [maLoaiLopHoc] = ? ";
        try {
            HocVienDAO hocVienDAO = new HocVienDAO();
            TrainerDAO trainerDAO = new TrainerDAO();
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, bid);
            rs = ps.executeQuery();
            while (rs.next()) {
                CommentDTO cmtDTO = new CommentDTO();
                cmtDTO.setMaComment(rs.getString("maComment"));
                cmtDTO.setHocVienDTO(hocVienDAO.searchHocVienById(rs.getString("maHV")));
                cmtDTO.setTrainerDTO(trainerDAO.searchTrainerById(rs.getString("maTrainer")));
                cmtDTO.setMaLopHoc(rs.getString("maLopHoc"));
                cmtDTO.setMaLoaiLopHoc(rs.getString("maLoaiLopHoc"));
                cmtDTO.setMaBlog(rs.getString("maBlog"));
                cmtDTO.setNoiDung(rs.getString("noiDung"));
                cmtDTO.setDate(rs.getDate("Date"));
                cmtDTO.setStatus(rs.getBoolean("status"));
                listCmt.add(cmtDTO);
            }
        } catch (Exception e) {
        }
        return listCmt;
    }

    public boolean insertComment(CommentDTO commentDTO) {
        String sql = "INSERT INTO [dbo].[Comment] "
                + "([maComment], [maHV], [maTrainer], [maLopHoc], [maLoaiLopHoc], [maBlog], [noiDung], [Date], [status]) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, commentDTO.getMaComment());
            ps.setString(2, commentDTO.getHocVienDTO().getMaHV());
            ps.setString(3, commentDTO.getTrainerDTO().getMaTrainer());
            ps.setString(4, commentDTO.getMaLopHoc());
            ps.setString(5, commentDTO.getMaLoaiLopHoc());
            ps.setString(6, commentDTO.getMaBlog());
            ps.setString(7, commentDTO.getNoiDung());
            ps.setDate(8, commentDTO.getDate()); // Assuming "Date" is a java.util.Date object in the CommentDTO class.
            ps.setBoolean(9, commentDTO.isStatus());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            // Handle any exceptions here
            e.printStackTrace();
        }

        return false;
    }

    public int lastIDIndex() {
        String sql = "SELECT TOP 1 maComment FROM [Comment] ORDER BY maComment DESC";
        int index = 0;
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                int numberOnly = Integer.parseInt(rs.getString("maComment").replaceAll("[^0-9]", ""));
                index = numberOnly;
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return index;
    }

    public static void main(String[] args) {
        // Create an instance of your DAO class
        CommentDAO dao = new CommentDAO();
        System.out.println(dao.lastIDIndex());

        // Call the getAllComments function
//        List<CommentDTO> comments = dao.getAllComments();
        // Display the comments
    }

}
