/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dao;

import com.mycompany.yogacenterproject.dto.hocVienDTO;
import com.mycompany.yogacenterproject.dto.TrainerDTO;
import com.mycompany.yogacenterproject.util.DBUtils;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TrainerDAO {
////Read list của các trainer
    public List<TrainerDTO> readListTrainer() {
        List<TrainerDTO> listTrainer = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Trainer";
            PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String maTrainer= rs.getString("maTrainer");
                String HoVaTen = rs.getString("HoVaTen");
                Date dob = rs.getDate("dob");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String salary = rs.getString("salary");
                String username = rs.getString("username");
                String psw = rs.getString("psw");
                int soNgayNghi = rs.getInt("soNgayNghi");
                boolean status = rs.getBoolean("status");
                String trainerType = rs.getString("trainerType");
                String maLoaiTK = rs.getString("maLoaiTK");
                TrainerDTO newTrainer=  new TrainerDTO(maTrainer, HoVaTen, dob, phone, email, soNgayNghi, username, psw, soNgayNghi, status, trainerType, maLoaiTK);
                listTrainer.add(newTrainer);
            }
            return listTrainer;
        } catch (SQLException e) {
            e.printStackTrace();
        }  
        return null;
}
//////Search Trainer by id
     public TrainerDTO searchTrainerById(String maTrainer) {
        try {
            String sql = "SELECT * FROM Trainer where maTrainer=?";
            PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
            stm.setString(1, maTrainer);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String HoVaTen = rs.getString("HoVaTen");
                Date dob = rs.getDate("dob");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String salary = rs.getString("salary");
                String username = rs.getString("username");
                String psw = rs.getString("psw");
                int soNgayNghi = rs.getInt("soNgayNghi");
                boolean status = rs.getBoolean("status");
                String trainerType = rs.getString("trainerType");
                String maLoaiTK = rs.getString("maLoaiTK");
                TrainerDTO newTrainer=  new TrainerDTO(maTrainer, HoVaTen, dob, phone, email, soNgayNghi, username, psw, soNgayNghi, status, trainerType, maLoaiTK);
                return newTrainer; 
            } 
        } catch (SQLException e) {
            Logger.getLogger(HocVienDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
////Thêm Trainer
     public void addTrainer(TrainerDTO newTrainer){
        try {
            String sql = "insert into Trainer(maTrainer,HoVaTen,dob,phone,email,salary,username,status,trainerType,maLoaiTK) "
                    + " values(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = DBUtils.getConnection().prepareStatement(sql);
            stmt.setString(1, newTrainer.getMaTrainer());
            stmt.setString(2, newTrainer.getHoVaTen());
            stmt.setDate(3, newTrainer.getDob());
            stmt.setString(4, newTrainer.getPhone());
            stmt.setString(5, newTrainer.getEmail());
            stmt.setFloat(6, newTrainer.getSalary());
            stmt.setString(7, newTrainer.getUsername());
            stmt.setBoolean(8, newTrainer.getStatus());
            stmt.setString(9, newTrainer.getTrainerType());
            stmt.setString(10, newTrainer.getMaLoaiTK());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
/////Hủy diệt Trainer
public void deleteTrainerById(String maTrainer) {
        try {
            String sql = "DELETE FROM Trainer where maTrainer =?";
            PreparedStatement stm;
            stm = DBUtils.getConnection().prepareStatement(sql);
            stm.setString(1, maTrainer);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HocVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

////// Update Trainer
 public void updateTrainer(TrainerDTO upTrainer) {
        try {
            String sql = "Update Trainer set HoVaTen=?,dob=?,phone=?, email=?,salary=?,username=?,psw=?,"
                    + "soNgayNghi=?,status=?,trainerType=?,maLoaiTK=? WHERE maHV=?";
            PreparedStatement stmt = DBUtils.getConnection().prepareStatement(sql);
            stmt.setString(1, upTrainer.getHoVaTen());
            stmt.setDate(2, upTrainer.getDob());
            stmt.setString(3, upTrainer.getPhone());
            stmt.setString(4, upTrainer.getEmail());
            stmt.setFloat(5, upTrainer.getSalary());
            stmt.setString(6, upTrainer.getUsername());
            stmt.setBoolean(7, upTrainer.getStatus());
            stmt.setString(8, upTrainer.getTrainerType());
            stmt.setString(9, upTrainer.getMaLoaiTK());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HocVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }     
}