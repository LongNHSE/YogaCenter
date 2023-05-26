/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dao;

import com.mycompany.yogacenterproject.dto.HocVienDTO;
import com.mycompany.yogacenterproject.util.DBUtils;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HocVienDAO {
//Read list của toàn bộ học viên

    public List<HocVienDTO> readListHocVien() {
        List<HocVienDTO> listHocVien = new ArrayList<>();
        try {
            String sql = "SELECT * FROM hocVien";
            PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String maHV = rs.getString("maHV");
                String Ho = rs.getString("Ho");
                String Ten = rs.getString("Ten");
                Date dob = rs.getDate("dob");
                String username = rs.getString("username");
                String psw = rs.getString("psw");
                String maLopHoc = rs.getString("maLopHoc");
                String maLoaiTK = rs.getString("maLoaiTK");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                HocVienDTO newTrainee = new HocVienDTO(maHV, Ho, Ten, dob, username, psw, maLopHoc, maLoaiTK, email, phone);
                listHocVien.add(newTrainee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listHocVien;
    }

// Tìm kiếm details của 1 người dựa trên id
    public HocVienDTO searchHocVienById(String maHV) {
        try {
            String sql = "SELECT * FROM hocVien where maHV=?";
            PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
            stm.setString(1, maHV);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String Ho = rs.getString("Ho");
                String Ten = rs.getString("Ten");
                Date dob = rs.getDate("dob");
                String username = rs.getString("username");
                String psw = rs.getString("psw");
                String maLopHoc = rs.getString("maLopHoc");
                String maLoaiTK = rs.getString("maLoaiTK");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                HocVienDTO newTrainee = new HocVienDTO(maHV, Ho, Ten, dob, username, psw, maLopHoc, maLoaiTK, email, phone);
                return newTrainee;
            }
        } catch (SQLException e) {
            Logger.getLogger(HocVienDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

/// Add thêm học viên
    public void addHocVien(HocVienDTO newHocVien) {
        try {
            String sql = "Insert into hocVien(maHV,Ho,Ten,dob,username,psw,maLopHoc,maLoaiTK,email,phone)"
                    + "values (?,?,?,?,?,?,?,?,?)";
            PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
            stm.setString(1, newHocVien.getMaHV());
            stm.setString(2, newHocVien.getHo());
            stm.setString(3, newHocVien.getTen());
            stm.setDate(4, newHocVien.getDob());
            stm.setString(5, newHocVien.getUsername());
            stm.setString(6, newHocVien.getPsw());
            stm.setString(7, newHocVien.getMaLopHoc());
            stm.setString(8, newHocVien.getMaLoaiTK());
            stm.setString(9, newHocVien.getEmail());
            stm.setString(10, newHocVien.getPhone());
            stm.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(TrainerDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

///Update học viên
    public void updateHocVien(HocVienDTO upTrainee) {
        try {
            String sql = "Update hocVien set Ho=?,Ten=?,dob=?, username=?,psw=? WHERE maHV=?";
            PreparedStatement stmt = DBUtils.getConnection().prepareStatement(sql);
            stmt.setString(1, upTrainee.getHo());
            stmt.setString(2, upTrainee.getTen());
            stmt.setDate(3, upTrainee.getDob());
            stmt.setString(4, upTrainee.getUsername());
            stmt.setString(4, upTrainee.getPsw());
            stmt.setString(5, upTrainee.getMaHV());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HocVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    ////Hủy diệt 1 đứa học sinh bằng id
    public void deleteHocVienById(String maHV) {
        try {
            String sql = "DELETE FROM hocVien where maHV =?";
            PreparedStatement stm;
            stm = DBUtils.getConnection().prepareStatement(sql);
            stm.setString(1, maHV);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HocVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void main(String[] args) {
        List<HocVienDTO> listHocVienDTO = new ArrayList<>();
        HocVienDAO hocVienDAO = new HocVienDAO();
        listHocVienDTO = hocVienDAO.readListHocVien();
        System.out.println(listHocVienDTO);
    }
}
