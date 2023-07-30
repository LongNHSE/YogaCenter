/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dao;

import com.mycompany.yogacenterproject.dto.LopHocIMGDTO;
import com.mycompany.yogacenterproject.dto.LopHocIMGDTO;
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
import java.util.UUID;

/**
 *
 * @author Oalskad
 */
public class LopHocImageDAO {

    public List<String> getImageDataFromDatabase() throws SQLException, IOException {
        byte[] imageData = null;
        List<String> listAnh = new ArrayList<>();
        try {
            // Load the JDBC driver (replace "your-driver-class" with the appropriate driver class for your database)

            // Create a connection to the database (replace "your-database-url", "username", and "password" with your actual connection details)
            Connection conn = DBUtils.getConnection();
            String sql = "SELECT [image] FROM lopHocImg ";
            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                imageData = resultSet.getBytes("image");
                String base64Image = Base64.getEncoder().encodeToString(imageData);
                listAnh.add(base64Image);

            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return listAnh;
    }

    //GET IMAGE BASED ON LOAILOPHOC ID
    public List<LopHocIMGDTO> getImageBasedOnTypeID(String maLoaiLopHoc) throws SQLException, IOException {
        byte[] imageData = null;
        List<LopHocIMGDTO> listAnh = new ArrayList<>();
        try {
            // Load the JDBC driver (replace "your-driver-class" with the appropriate driver class for your database)

            // Create a connection to the database (replace "your-database-url", "username", and "password" with your actual connection details)
            Connection conn = DBUtils.getConnection();
            String sql = "SELECT * FROM lopHocImg where maLoaiLopHoc = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, maLoaiLopHoc);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                LopHocIMGDTO lopHocIMGDTO = new LopHocIMGDTO();
                lopHocIMGDTO.setMaAnh(rs.getString("maAnh"));
                lopHocIMGDTO.setMaLoaiLopHoc(rs.getString("maLoaiLopHoc"));
                lopHocIMGDTO.setTenAnh(rs.getString("tenAnh"));

                imageData = rs.getBytes("image");
                String base64Image = Base64.getEncoder().encodeToString(imageData);
                lopHocIMGDTO.setImage(base64Image);
                listAnh.add(lopHocIMGDTO);

            }
            rs.close();
            statement.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return listAnh;
    }

    public void insertImageDataFromDatabase(List<byte[]> imageList, LopHocIMGDTO lopHocIMG) throws SQLException, IOException {

        List<String> listAnh = new ArrayList<>();
        try {
            // Load the JDBC driver (replace "your-driver-class" with the appropriate driver class for your database)

            // Create a connection to the database (replace "your-database-url", "username", and "password" with your actual connection details)
            Connection conn = DBUtils.getConnection();
            String sql = "insert into [dbo].[lopHocImg]( maAnh,tenAnh ,[image], maLoaiLopHoc,maHV, maLopHoc, maTrainer)"
                    + "VALUES( ?, ?, ?, ?, ?,?,?) ";
            PreparedStatement ps = conn.prepareStatement(sql);
            for (byte[] imageData : imageList) {

                String AUTO_IMG_ID = String.format(Constants.MA_IMG_FORMAT, (lastIDIndex() + 1));

                //HOCVIEN CONSTRUCTOR
                String maAnh = AUTO_IMG_ID;
                lopHocIMG.setMaAnh(maAnh);
                ps.setString(1, lopHocIMG.getMaAnh());
                ps.setString(2, lopHocIMG.getTenAnh());

                ps.setBytes(3, imageData);
                ps.setString(4, lopHocIMG.getMaLoaiLopHoc());
                ps.setString(5, lopHocIMG.getMaHV());
                ps.setString(6, lopHocIMG.getMaLopHoc());
                ps.setString(7, null);
                ps.executeUpdate();

            }

        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    public void UpdateImage(byte[] imageList, String ID) {
        List<String> listAnh = new ArrayList<>();
        try {
            // Load the JDBC driver (replace "your-driver-class" with the appropriate driver class for your database)

            // Create a connection to the database (replace "your-database-url", "username", and "password" with your actual connection details)
            Connection conn = DBUtils.getConnection();
            String sql = "Update [dbo].[lopHocImg] set [image] = ? where tenAnh='THUMBNAIL' and maLoaiLopHoc=? ";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setBytes(1, imageList);
            ps.setString(2, ID);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);

        }
    }

    public int lastIDIndex() {
        String sql = "SELECT TOP 1 maAnh FROM [dbo].[lopHocImg] ORDER BY maAnh DESC";
        int index = 0;
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                int numberOnly = Integer.parseInt(rs.getString("maAnh").replaceAll("[^0-9]", ""));
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

    public static String generateRandomString() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-", "");
    }

    public static void main(String[] args) throws SQLException, IOException {
        LopHocIMGDTO test = new LopHocIMGDTO();
        System.out.println(test);
        test.setMaAnh("ads");
        LopHocImageDAO a = new LopHocImageDAO();
        List<LopHocIMGDTO> b = a.getImageBasedOnTypeID("TYPE0004");
        for (LopHocIMGDTO c : b) {
            System.out.println(c.getTenAnh());
            if (c.getTenAnh() != null) {
                if (c.getTenAnh().equalsIgnoreCase("THUMBNAIL")) {
                    System.out.println(c);
                }
            }

        }
//        byte[] imageList = {123};
//        String randomString = generateRandomString();
//        byte[] byteData = randomString.getBytes();
//
//        List<byte[]> imageList2 = new ArrayList<>();
//
//        imageList2.add(byteData);
//        a.insertImageDataFromDatabase(imageList2, test);

    }
}
