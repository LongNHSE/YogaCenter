/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dao;

import com.mycompany.yogacenterproject.dto.AvatarDTO;
import com.mycompany.yogacenterproject.dto.BlogImgDTO;
import com.mycompany.yogacenterproject.util.Constants;
import com.mycompany.yogacenterproject.util.DBUtils;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 *
 * @author Oalskad
 */
public class AvatarDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public AvatarDTO getImageDataByID(String maHV) {
        byte[] imageData = null;

        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT * FROM [dbo].[avatar] where [maHV] = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, maHV);
            rs = ps.executeQuery();

            while (rs.next()) {
                AvatarDTO avatarDTO = new AvatarDTO();
                avatarDTO.setMaAvatar(rs.getString("maAvatar"));

                imageData = rs.getBytes("image");
                String base64image = Base64.getEncoder().encodeToString(imageData);
                avatarDTO.setImage(base64image);
                avatarDTO.setMaHV(rs.getString("maHV"));
                avatarDTO.setMaTrainer(rs.getString("maTrainer"));
                return avatarDTO;
            }
            if (!rs.next()) {
                return getDefault();
            }
        } catch (Exception e) {
        }
        return null;
    }

    public AvatarDTO getImageDataByTrainerID(String maTrainer) {
        byte[] imageData = null;

        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT * FROM [dbo].[avatar] where [maTrainer] = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, maTrainer);
            rs = ps.executeQuery();

            while (rs.next()) {
                AvatarDTO avatarDTO = new AvatarDTO();
                avatarDTO.setMaAvatar(rs.getString("maAvatar"));

                imageData = rs.getBytes("image");
                String base64image = Base64.getEncoder().encodeToString(imageData);
                avatarDTO.setImage(base64image);
                avatarDTO.setMaHV(rs.getString("maHV"));
                avatarDTO.setMaTrainer(rs.getString("maTrainer"));
                return avatarDTO;
            }
            if (!rs.next()) {
                return getDefault();
            }
        } catch (Exception e) {
        }
        return null;
    }

    public AvatarDTO getDefault() {
        byte[] imageData = null;

        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT * FROM [dbo].[avatar] where [maAvatar] = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "DEFAULT");
            rs = ps.executeQuery();

            while (rs.next()) {
                AvatarDTO avatarDTO = new AvatarDTO();
                avatarDTO.setMaAvatar(rs.getString("maAvatar"));

                imageData = rs.getBytes("image");
                String base64image = Base64.getEncoder().encodeToString(imageData);
                avatarDTO.setImage(base64image);
                avatarDTO.setMaHV(rs.getString("maHV"));
                avatarDTO.setMaTrainer(rs.getString("maTrainer"));
                return avatarDTO;
            }
            if (!rs.next()) {

            }
        } catch (Exception e) {
        }
        return null;
    }

    public void insertImageDataFromDatabase(byte[] imageList, AvatarDTO avatarDTO) throws SQLException, IOException {

        List<String> listAnh = new ArrayList<>();
        try {
            // Load the JDBC driver (replace "your-driver-class" with the appropriate driver class for your database)

            // Create a connection to the database (replace "your-database-url", "username", and "password" with your actual connection details)
            Connection conn = DBUtils.getConnection();
            String sql = "insert into [dbo].[avatar]( maAvatar,maHV ,[image], maTrainer)"
                    + "VALUES( ?, ?, ?, ?) ";
            PreparedStatement ps = conn.prepareStatement(sql);

            String AUTO_IMG_ID = String.format(Constants.MA_IMG_FORMAT, (lastIDIndexOfAvatar() + 1));

            //HOCVIEN CONSTRUCTOR
            String maAnh = AUTO_IMG_ID;
            avatarDTO.setMaAvatar(maAnh);
            ps.setString(1, avatarDTO.getMaAvatar());
            ps.setString(2, avatarDTO.getMaHV());
            ps.setBytes(3, imageList);
            ps.setString(4, avatarDTO.getMaTrainer());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);

        }

    }

    public void UpdateImage(byte[] imageList, AvatarDTO avatarDTO) {
        List<String> listAnh = new ArrayList<>();
        try {
            // Load the JDBC driver (replace "your-driver-class" with the appropriate driver class for your database)

            // Create a connection to the database (replace "your-database-url", "username", and "password" with your actual connection details)
            Connection conn = DBUtils.getConnection();
            String sql = "Update [dbo].[avatar] set [image] = ? where maHV=? or maTrainer=? ";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setBytes(1, imageList);
            ps.setString(2, avatarDTO.getMaHV());
            ps.setString(3, avatarDTO.getMaTrainer());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);

        }
    }

    public int lastIDIndexOfAvatar() {
        String sql = "SELECT TOP 1 maAvatar FROM [dbo].[avatar] ORDER BY maAvatar DESC";
        int index = 0;
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                int numberOnly = Integer.parseInt(rs.getString("maAvatar").replaceAll("[^0-9]", ""));
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
        AvatarDAO avatarDAO = new AvatarDAO();
        byte[] imageList = null;
        AvatarDTO avatarDTO = new AvatarDTO();
        avatarDTO.setMaHV("HV0003");
//        System.out.println(avatarDAO.getImageDataByID("HV0002").getMaAvatar().equals(avatarDAO.getDefault().getMaAvatar()));
        avatarDAO.UpdateImage(imageList, avatarDTO);
    }
}
