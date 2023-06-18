/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dao;

import com.mycompany.yogacenterproject.dto.LoaiLopHocDTO;
import com.mycompany.yogacenterproject.dto.LopHocIMG;
import com.mycompany.yogacenterproject.util.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
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
        String sql = "INSERT INTO [dbo].[loaiLopHoc](maLoaiLopHoc, tenLoaiLopHoc, hocPhi)"
                + "VALUES(?,?,?)";
        int row = 0;
        PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
        stm.setString(1, loaiLopHocDTO.getMaLoaiLopHoc());
        stm.setString(2, loaiLopHocDTO.getTenLoaiLopHoc());
        stm.setDouble(3, loaiLopHocDTO.getHocPhi());

        row = stm.executeUpdate();

        return row > 0;

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

    //READ LIST LOAI LOP HOC 
    public List<LoaiLopHocDTO> readLoaiLopHoc() {
        List<LoaiLopHocDTO> listLoaiLopHoc = new ArrayList<>();
        String sql = "SELECT * FROM [dbo].[loaiLopHoc]";

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
    public String searchHocPhiLopHoc(String maLopHoc) {
        String sql = "SELECT hocPhi FROM [dbo].[loaiLopHoc] where maLoaiLopHoc = ?";
        double hocPhi = 0;
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maLopHoc);
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

    // PRINT CLASSES' CATEGORIES
    public List<LopHocIMG> getAllCategories() throws SQLException{
          
          List<LopHocIMG> list = new ArrayList();
          String sql = "SELECT * FROM dbo.lopHocImg";
          PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
          ResultSet rs = stm.executeQuery();
          while(rs.next()){
                String maAnh = rs.getString("maAnh");
                String tenAnh = rs.getString("tenAnh");
                String URLAnh = rs.getString("URLAnh");
                String maLoaiLopHoc = rs.getString("maLoaiLopHoc");
                LopHocIMG e = new LopHocIMG();
                e.setMaIMG(maAnh);
                e.setTenIMG(tenAnh);
                e.setUrlIMG(URLAnh);
                e.setMaLoaiLopHoc(maLoaiLopHoc);
                list.add(e);
          }
          return list;
    }
    public static void main(String[] args) throws SQLException {
        LoaiLopHocDAO a = new LoaiLopHocDAO();
        System.out.println(a.searchTenLoaiLopHoc("TYPE0001"));

//        System.out.println(a.readLoaiLopHoc());
        System.out.println(a.searchHocPhiLopHoc("TYPE0001"));
//        long b = 1200000;
//        LoaiLopHocDTO loaiLopHocDTO = new LoaiLopHocDTO("TYPE0003", " Iyengar yoga", b);
//        a.createLoaiLopHoc(loaiLopHocDTO);
      List<LopHocIMG> listIMG = a.getAllCategories();
      for (LopHocIMG o: listIMG){
            System.out.println(o.toString());
      }
      
    }
}
