/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dao;

import com.mycompany.yogacenterproject.dto.HocVienDTO;
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
                String maTrainer = rs.getString("maTrainer");
                String HoVaTen = rs.getString("HoVaTen");
                Date dob = rs.getDate("dob");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                long salary = rs.getLong("salary");
                String username = rs.getString("username");
                String psw = rs.getString("psw");
                int soNgayNghi = rs.getInt("soNgayNghi");
                boolean status = rs.getBoolean("status");
                String trainerType = rs.getString("trainerType");
                String maLoaiTK = rs.getString("maLoaiTK");
//                TrainerDTO newTrainer = new TrainerDTO(maTrainer, HoVaTen, dob, phone, email, salary, username, psw, soNgayNghi, status, trainerType, maLoaiTK);
//                
//                listTrainer.add(newTrainer);
            }
            return listTrainer;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
////Read list cua cac trainer nhung dua tren dieu kien loai trainer

    public List<TrainerDTO> readListTrainerByType(String trainerType) {
        List<TrainerDTO> listTrainer = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Trainer where trainerType = ?";
            PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
            stm.setString(1, trainerType);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                TrainerDTO trainerDTO = new TrainerDTO();
                String maTrainer = rs.getString("maTrainer");
                String Ho = rs.getString("Ho");
                String Ten = rs.getString("Ten");
                Date dob = rs.getDate("dob");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                long salary = rs.getLong("salary");
                String username = rs.getString("username");
                String psw = rs.getString("psw");
                int soNgayNghi = rs.getInt("soNgayNghi");
                boolean status = rs.getBoolean("status");
                String trainerTypeGet = rs.getString("trainerType");
                String maLoaiTK = rs.getString("maLoaiTK");
                
                trainerDTO.setMaTrainer(maTrainer);
                trainerDTO.setHo(Ho);
                trainerDTO.setTen(Ten);     
                trainerDTO.setEmail(email);
                trainerDTO.setPhone(phone);
                trainerDTO.setPsw(psw);
                trainerDTO.setSalary(salary);
                trainerDTO.setSoNgayNghi(soNgayNghi);
                trainerDTO.setStatus(status);
                trainerDTO.setTrainerType(trainerType);
                trainerDTO.setUsername(username);
                trainerDTO.setMaLoaiTK(maLoaiTK);
                
                listTrainer.add(trainerDTO);
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
                long salary = rs.getLong("salary");
                String username = rs.getString("username");
                String psw = rs.getString("psw");
                int soNgayNghi = rs.getInt("soNgayNghi");
                boolean status = rs.getBoolean("status");
                String trainerType = rs.getString("trainerType");
                String maLoaiTK = rs.getString("maLoaiTK");
//                return newTrainer;
            }
        } catch (SQLException e) {
            Logger.getLogger(HocVienDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
////Thêm Trainer

    public void addTrainer(TrainerDTO newTrainer) {
        try {
            String sql = "insert into Trainer(maTrainer,HoVaTen,dob,phone,email,salary,username,status,trainerType,maLoaiTK) "
                    + " values(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = DBUtils.getConnection().prepareStatement(sql);
            stmt.setString(1, newTrainer.getMaTrainer());
//            stmt.setString(2, newTrainer.getHoVaTen());
            stmt.setDate(3, newTrainer.getDob());
            stmt.setString(4, newTrainer.getPhone());
            stmt.setString(5, newTrainer.getEmail());
            stmt.setLong(6, newTrainer.getSalary());
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
//            stmt.setString(1, upTrainer.getHoVaTen());
            stmt.setDate(2, upTrainer.getDob());
            stmt.setString(3, upTrainer.getPhone());
            stmt.setString(4, upTrainer.getEmail());
            stmt.setLong(5, upTrainer.getSalary());
            stmt.setString(6, upTrainer.getUsername());
            stmt.setBoolean(7, upTrainer.getStatus());
            stmt.setString(8, upTrainer.getTrainerType());
            stmt.setString(9, upTrainer.getMaLoaiTK());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HocVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
//        TrainerDAO trainerDAO = new TrainerDAO();
//        System.out.println(trainerDAO.readListTrainer().get(0).getSalary());
        
          LoaiLopHocDAO loaiLopHocDAO = new LoaiLopHocDAO();
        LopHocDAO lopHocDAO = new LopHocDAO();
        
        TrainerDAO trainerDAO = new TrainerDAO();
        List<TrainerDTO> listTrainer = new ArrayList();
        
        listTrainer = trainerDAO.readListTrainerByType(loaiLopHocDAO.searchTenLoaiLopHoc(lopHocDAO.IDLoaiLopHoc("LOP0003")));
        System.out.println(loaiLopHocDAO.searchTenLoaiLopHoc(lopHocDAO.IDLoaiLopHoc("LOP0003")));
        System.out.println(lopHocDAO.IDLoaiLopHoc("LOP0003"));
        for(TrainerDTO x : listTrainer){
            System.out.println(x.getTen());
        }
    }
}
