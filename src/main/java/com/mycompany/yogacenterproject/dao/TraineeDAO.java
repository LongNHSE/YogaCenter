/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dao;

import com.mycompany.yogacenterproject.dto.TraineeDTO;
import com.mycompany.yogacenterproject.util.DBUtils;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TraineeDAO {

    public List<TraineeDTO> read() {
        List<TraineeDTO> listTrainee = new ArrayList<>();
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
                TraineeDTO newTrainee = new TraineeDTO(maHV, Ho, Ten, dob, username, psw, maLopHoc, maLoaiTK);
                listTrainee.add(newTrainee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listTrainee;
    }

    public TraineeDTO details(String maHV) {
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
                TraineeDTO newTrainee = new TraineeDTO(maHV, Ho, Ten, dob, username, psw, maLopHoc, maLoaiTK);
                return newTrainee;
            }
        } catch (SQLException e) {
            Logger.getLogger(TraineeDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

}
