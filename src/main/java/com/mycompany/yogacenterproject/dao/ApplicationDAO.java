/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dao;

import com.mycompany.yogacenterproject.dto.ApplicationDTO;
import com.mycompany.yogacenterproject.util.DBUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Oalskad
 */
public class ApplicationDAO {

    public void create(ApplicationDTO application) throws SQLException {
        String sql = "INSERT INTO application (maDon, maHV, maTrainer, maLopHoc, maApplicationType, Date, status,noiDung) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?,?)";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, application.getMaDon());
            ps.setString(2, application.getMaHV());
            ps.setString(3, application.getMaTrainer());
            ps.setString(4, application.getMaLopHoc());
            ps.setString(5, application.getMaApplicationType());
            ps.setDate(6, application.getDate());
            ps.setString(7, application.getStatus());
            ps.setString(8, application.getNoiDung());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ApplicationDTO> getAllApplications() throws SQLException {
        List<ApplicationDTO> applications = new ArrayList<>();
        String sql = "SELECT * FROM application";
        try (Connection conn = DBUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet resultSet = ps.executeQuery()) {
            while (resultSet.next()) {
                ApplicationDTO application = createApplicationFromResultSet(resultSet);
                applications.add(application);
            }
        }
        return applications;
    }

    private ApplicationDTO createApplicationFromResultSet(ResultSet resultSet) throws SQLException {
        String maDon = resultSet.getString("maDon");
        String maHV = resultSet.getString("maHV");
        String maTrainer = resultSet.getString("maTrainer");
        String maLopHoc = resultSet.getString("maLopHoc");
        String maApplicationType = resultSet.getString("maApplicationType");
        Date date = resultSet.getDate("Date");
        String status = resultSet.getString("status");

        return new ApplicationDTO(maDon, maHV, maTrainer, maLopHoc, maApplicationType, date, status);
    }

    public int lastIDIndexOfBlog() {
        String sql = "SELECT TOP 1 maDon FROM [dbo].[application] ORDER BY maDon DESC";
        int index = 0;
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                int numberOnly = Integer.parseInt(rs.getString("maDon").replaceAll("[^0-9]", ""));
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

    }

}
