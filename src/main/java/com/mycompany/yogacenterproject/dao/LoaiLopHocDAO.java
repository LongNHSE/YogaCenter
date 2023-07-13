/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dao;

import com.mycompany.yogacenterproject.dto.LoaiLopHocDTO;
import com.mycompany.yogacenterproject.dto.LopHocDTO;
import com.mycompany.yogacenterproject.dto.LopHocIMGDTO;
import com.mycompany.yogacenterproject.dto.LopHocIMGDTO;
import com.mycompany.yogacenterproject.util.DBUtils;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Oalskad
 */
public class LoaiLopHocDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public boolean createLoaiLopHoc(LoaiLopHocDTO loaiLopHocDTO) throws SQLException {
        String sql = "INSERT INTO [dbo].[loaiLopHoc](maLoaiLopHoc, tenLoaiLopHoc,[maDescription], hocPhi,status)"
                + "VALUES(?,?,?,?,?)";
        int row = 0;
        PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
        stm.setString(1, loaiLopHocDTO.getMaLoaiLopHoc());
        stm.setString(2, loaiLopHocDTO.getTenLoaiLopHoc());
        stm.setString(3, loaiLopHocDTO.getMaDescription());
        stm.setDouble(4, loaiLopHocDTO.getHocPhi());
        stm.setBoolean(5, true);

        row = stm.executeUpdate();

        return row > 0;

    }

    public boolean updateLoaiLopHoc(LoaiLopHocDTO loaiLopHoc) {
        String sql = "Update [dbo].[loaiLopHoc] set tenLoaiLopHoc=?, hocPhi=? "
                + "where maLoaiLopHoc=?";
        int check = 1;
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, loaiLopHoc.getTenLoaiLopHoc());
            ps.setDouble(2, loaiLopHoc.getHocPhi());
            ps.setString(3, loaiLopHoc.getMaLoaiLopHoc());
            check = ps.executeUpdate();

            ps.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e);
        }
        if (check == 1) {
            return true;
        } else {
            return false;
        }
    }

    //GENERATE ID 
    public int lastIDIndex() {
        String sql = "SELECT TOP 1 maLoaiLopHoc FROM [dbo].[loaiLopHoc] ORDER BY maLoaiLopHoc DESC";
        int index = 0;
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                int numberOnly = Integer.parseInt(rs.getString("maLoaiLopHoc").replaceAll("[^0-9]", ""));
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

    //CHECK TEN LOAI LOP HOC 
    public String searchTenLoaiLopHoc(String maLoaiLopHoc) {
        String sql = "SELECT tenLoaiLopHoc FROM [dbo].[loaiLopHoc] where maLoaiLopHoc = ?  ";
        String tenLoai = "";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maLoaiLopHoc);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                tenLoai = rs.getString("tenLoaiLopHoc");
                return tenLoai.trim();
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public boolean searchStatusLoaiLopHoc(String maLoaiLopHoc) {
        String sql = "SELECT status FROM [dbo].[loaiLopHoc] where maLoaiLopHoc = ?  ";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maLoaiLopHoc);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                return rs.getBoolean("status");
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }

//    Select ID to get information
    public LoaiLopHocDTO getClassCateByID(String maLoaiLopHoc) {
        String sql = "select * from [dbo].[loaiLopHoc] where [maLoaiLopHoc] = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maLoaiLopHoc);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LoaiLopHocDTO loaiLopHocDTO = new LoaiLopHocDTO();
                loaiLopHocDTO.setMaLoaiLopHoc(rs.getString("maLoaiLopHoc"));
                loaiLopHocDTO.setTenLoaiLopHoc(rs.getString("tenLoaiLopHoc"));
                loaiLopHocDTO.setMaDescription(rs.getString("maDescription"));
                loaiLopHocDTO.setHocPhi(rs.getDouble("hocPhi"));
                return loaiLopHocDTO;
            }
        } catch (Exception e) {
        }
        return null;
    }

    //SEARCH ID TYPE 
    public String searchIdLoaiLopHoc(String tenLoaiLopHoc) {
        String sql = "SELECT  maLoaiLopHoc FROM [dbo].[loaiLopHoc] where tenLoaiLopHoc = ?  ";
        String tenLoai = "";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, tenLoaiLopHoc);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                tenLoai = rs.getString("maLoaiLopHoc");
                return tenLoai.trim();
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    //READ LIST LOAI LOP HOC 
    public List<LoaiLopHocDTO> readLoaiLopHoc() {
        List<LoaiLopHocDTO> listLoaiLopHoc = new ArrayList<>();
        String sql = "SELECT * FROM [dbo].[loaiLopHoc] where status ='true'";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LoaiLopHocDTO loaiLopHocDTO = new LoaiLopHocDTO();
                loaiLopHocDTO.setMaLoaiLopHoc(rs.getString("maLoaiLopHoc"));
                loaiLopHocDTO.setTenLoaiLopHoc(rs.getString("tenLoaiLopHoc"));
                loaiLopHocDTO.setHocPhi(rs.getDouble("hocPhi"));
                listLoaiLopHoc.add(loaiLopHocDTO);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return listLoaiLopHoc;
    }

    //LAY GIA TIEN CUA LOAI LOP HOC
    public String searchHocPhiLopHoc(String maLoaiLopHoc) {
        String sql = "SELECT hocPhi FROM [dbo].[loaiLopHoc] where maLoaiLopHoc = ?";
        double hocPhi = 0;
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maLoaiLopHoc);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                hocPhi = rs.getDouble("hocPhi");

// Create a DecimalFormatSymbols instance for the default locale
                DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
                symbols.setGroupingSeparator('.');

// Create a DecimalFormat instance with the desired pattern and symbols
                DecimalFormat decimalFormat = new DecimalFormat("#,###", symbols);
                decimalFormat.setDecimalSeparatorAlwaysShown(false);
                return decimalFormat.format(hocPhi);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public long searchHocPhiLopHocWithDouble(String maLoaiLopHoc) {
        String sql = "SELECT hocPhi FROM [dbo].[loaiLopHoc] where maLoaiLopHoc = ?";
        long hocPhi = 0;
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maLoaiLopHoc);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                return hocPhi = rs.getLong("hocPhi");

            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return hocPhi;
    }

    // PRINT CLASSES' CATEGORIES
    public List<LopHocIMGDTO> getAllCategories() throws SQLException {

        List<LopHocIMGDTO> list = new ArrayList();
        String sql = "SELECT * FROM dbo.lopHocImg";
        PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            String maAnh = rs.getString("maAnh");
            String tenAnh = rs.getString("tenAnh");
            String URLAnh = rs.getString("image");
            String maLoaiLopHoc = rs.getString("maLoaiLopHoc");
            LopHocIMGDTO e = new LopHocIMGDTO();
            e.setMaAnh(maAnh);
            e.setTenAnh(tenAnh);
            e.setImage(URLAnh);
            e.setMaLoaiLopHoc(maLoaiLopHoc);
            list.add(e);

        }
        return list;
    }

    //LAY LIST LOAI LOP HOC GOM IMAGE
    public List<LoaiLopHocDTO> getAllLoaiLopHoc() throws SQLException, IOException {
        List<LoaiLopHocDTO> listLoaiLopHoc = new ArrayList<>();
        LopHocImageDAO lopHocImageDAO = new LopHocImageDAO();
        String sql = "SELECT * FROM loaiLopHoc where status='true'";
        PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            LoaiLopHocDTO loaiLopHocDTO = new LoaiLopHocDTO();
            String maLoaiLopHoc = rs.getString("maLoaiLopHoc");
            String tenLoaiLopHoc = rs.getString("tenLoaiLopHoc");

            double hocPhi = rs.getDouble("hocPhi");
            List<LopHocIMGDTO> lopHocIMGDTO = lopHocImageDAO.getImageBasedOnTypeID(maLoaiLopHoc);
            loaiLopHocDTO.setMaLoaiLopHoc(maLoaiLopHoc);
            loaiLopHocDTO.setTenLoaiLopHoc(tenLoaiLopHoc);

            loaiLopHocDTO.setHocPhi(hocPhi);
            loaiLopHocDTO.setImage(lopHocIMGDTO);
            loaiLopHocDTO.setStatus(rs.getBoolean("status"));
            loaiLopHocDTO.setMaDescription(rs.getString("maDescription"));
            listLoaiLopHoc.add(loaiLopHocDTO);

        }
        return listLoaiLopHoc;

    }

    public List<LoaiLopHocDTO> getAllLoaiLopHocAvailable() throws SQLException, IOException {
        List<LoaiLopHocDTO> listLoaiLopHoc = new ArrayList<>();
        LopHocImageDAO lopHocImageDAO = new LopHocImageDAO();
        String sql = "SELECT * FROM loaiLopHoc where status='true'";
        PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            LoaiLopHocDTO loaiLopHocDTO = new LoaiLopHocDTO();
            String maLoaiLopHoc = rs.getString("maLoaiLopHoc");
            String tenLoaiLopHoc = rs.getString("tenLoaiLopHoc");

            double hocPhi = rs.getDouble("hocPhi");
            List<LopHocIMGDTO> lopHocIMGDTO = lopHocImageDAO.getImageBasedOnTypeID(maLoaiLopHoc);
            loaiLopHocDTO.setMaLoaiLopHoc(maLoaiLopHoc);
            loaiLopHocDTO.setTenLoaiLopHoc(tenLoaiLopHoc);

            loaiLopHocDTO.setHocPhi(hocPhi);
            loaiLopHocDTO.setImage(lopHocIMGDTO);

            listLoaiLopHoc.add(loaiLopHocDTO);

        }
        return listLoaiLopHoc;

    }

    public void changeStatus(String maLoaiLopHoc, boolean status) {
        String sql = "Update [dbo].[loaiLopHoc] "
                + "Set [status] = ? "
                + "where maLoaiLopHoc =?";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBoolean(1, status);
            ps.setString(2, maLoaiLopHoc);

            ps.executeUpdate();

            ps.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public LoaiLopHocDTO searchLoaiLopHoc(String maLoaiLopHoc) throws SQLException, IOException {

        LopHocImageDAO lopHocImageDAO = new LopHocImageDAO();
        DescriptionDAO descriptionDAO = new DescriptionDAO();
        String sql = "SELECT * FROM loaiLopHoc where maLoaiLopHoc =?";
        PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
        stm.setString(1, maLoaiLopHoc);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            LoaiLopHocDTO loaiLopHocDTO = new LoaiLopHocDTO();
            maLoaiLopHoc = rs.getString("maLoaiLopHoc");
            String tenLoaiLopHoc = rs.getString("tenLoaiLopHoc");

            double hocPhi = rs.getDouble("hocPhi");
            List<LopHocIMGDTO> lopHocIMGDTO = lopHocImageDAO.getImageBasedOnTypeID(maLoaiLopHoc);
            loaiLopHocDTO.setMaLoaiLopHoc(maLoaiLopHoc);
            loaiLopHocDTO.setTenLoaiLopHoc(tenLoaiLopHoc);

            loaiLopHocDTO.setHocPhi(hocPhi);
            loaiLopHocDTO.setImage(lopHocIMGDTO);
            loaiLopHocDTO.setStatus(rs.getBoolean("status"));
            loaiLopHocDTO.setMaDescription(rs.getString("maDescription"));
            loaiLopHocDTO.setDescriptionDTO(descriptionDAO.readDescription(maLoaiLopHoc));
            return loaiLopHocDTO;
        }
        return null;

    }

    public static void main(String[] args) throws SQLException, IOException {
        LoaiLopHocDAO a = new LoaiLopHocDAO();
        List<LoaiLopHocDTO> listCate = new ArrayList<>();
        System.out.println(a.searchLoaiLopHoc("TYPE0001"));
//        System.out.println(a.searchStatusLoaiLopHoc("TYPE0001"));
//        a.changeStatus("TYPE0001", !a.searchStatusLoaiLopHoc("TYPE0001"));
//        LopHocDTO lopHocDTO = new LopHocDTO();
//        LopHocDAO lopHocDAO = new LopHocDAO();
//        lopHocDTO = lopHocDAO.searchClassById("LOP0003");
//        long subtotal = a.searchHocPhiLopHocWithDouble(lopHocDTO.getMaLoaiLopHoc()); // Replace with actual calculation based on lopHocDTO
//        long tax = 0; // Replace with actual calculation based on lopHocDTO
//        long shipping = 0; // Replace with actual calculation based on lopHocDTO
//        long totalAmount = subtotal + tax + shipping;
//        System.out.println(totalAmount);
//        System.out.println(String.valueOf(totalAmount));
//        listCate = a.getAllLoaiLopHoc();
//        for (LoaiLopHocDTO c : listCate) {
//            System.out.println(c);
//        }
//          LoaiLopHocDTO DTO = a.getClassCateByID("TYPE0004");
//          System.out.println(DTO.toString());

////        System.out.println(a.readLoaiLopHoc());
//        System.out.println(a.searchHocPhiLopHoc("TYPE0001"));
//        long b = 1200000;
//        LoaiLopHocDTO loaiLopHocDTO = new LoaiLopHocDTO("TYPE0003", " Iyengar yoga", b);
//        a.createLoaiLopHoc(loaiLopHocDTO);
//      List<LopHocIMG> listIMG = a.getAllCategories();
//      for (LopHocIMG o: listIMG){
//            System.out.println(o.toString());
//      }
    }
}
