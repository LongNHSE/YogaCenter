/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dao;

import com.mycompany.yogacenterproject.dto.AttendanceDTO;
import com.mycompany.yogacenterproject.dto.ScheduleHvDTO;
import com.mycompany.yogacenterproject.util.Constants;
import com.mycompany.yogacenterproject.util.DBUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oalskad
 */
public class AttendanceDAO {

    public void updateAttendance(Date ngayHoc, String maSlot, String maLopHoc, String maHocVien, String status) throws SQLException {
        List<AttendanceDTO> listAttendanceDTO = new ArrayList<>();
        String sql = "UPDATE [dbo].[Attendance] set status=?  "
                + "where ngayHoc=? and maSlot=? and maLopHoc =? and maHV = ?\n";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, status);
            ps.setDate(2, ngayHoc);
            ps.setString(3, maSlot);
            ps.setString(4, maLopHoc);
            ps.setString(5, maHocVien);

            int row = ps.executeUpdate();

            System.out.println(row);
        } catch (SQLException e) {
        }

    }

    public List<AttendanceDTO> readAttendance(Date ngayHoc, String maSlot, String maLopHoc) throws SQLException {
        List<AttendanceDTO> listAttendanceDTO = new ArrayList<>();
        String sql = "SELECT * from [dbo].[Attendance] where ngayHoc = ? and maSlot = ? and maLopHoc = ? ";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setDate(1, ngayHoc);
            ps.setString(2, maSlot);
            ps.setString(3, maLopHoc);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AttendanceDTO attendanceDTO = new AttendanceDTO();
                attendanceDTO.setAttendanceID(rs.getString("attendanceID"));
                attendanceDTO.setMaHV(rs.getString("maHV"));
                attendanceDTO.setMaLopHoc(rs.getString("maLopHoc"));
                attendanceDTO.setMaSlot(rs.getString("maSlot"));
                attendanceDTO.setStatus(rs.getString("status"));
                attendanceDTO.setNgayHoc(rs.getDate("ngayHoc"));
                listAttendanceDTO.add(attendanceDTO);
            }
            return listAttendanceDTO;
        } catch (SQLException e) {
        }

        return null;
    }

    public boolean createAttendance() throws SQLException {
        ScheduleDAO scheduleDAO = new ScheduleDAO();
        List<ScheduleHvDTO> listScheduleHV = scheduleDAO.readScheduleHvDTO();
        String sql = "INSERT INTO [dbo].[Attendance](attendanceID,maHV, [maLopHoc], [ngayHoc], [maSlot],[status]) \n"
                + "SELECT ?,?, ?, ?, ?,'Pending'\n";
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);

        for (ScheduleHvDTO scheduleHvDTO : listScheduleHV) {

            String AUTO_ATTENDANCE_ID = String.format(Constants.MA_ATTENDANCE_FORMAT, (lastIDIndex() + 1));

            //HOCVIEN CONSTRUCTOR
            String attendanceID = AUTO_ATTENDANCE_ID;

            ps.setString(1, attendanceID);
            ps.setString(2, scheduleHvDTO.getMaHV());
            ps.setString(3, scheduleHvDTO.getMaLopHoc());
            ps.setDate(4, scheduleHvDTO.getNgayHoc());
            ps.setString(5, scheduleHvDTO.getMaSlot());
            ps.executeUpdate();
        }

        return false;
    }

    public boolean createAttendance(List<ScheduleHvDTO> listScheduleHV) throws SQLException {
        ScheduleDAO scheduleDAO = new ScheduleDAO();
        String sql = "INSERT INTO [dbo].[Attendance](attendanceID,maHV, [maLopHoc], [ngayHoc], [maSlot],[status]) \n"
                + "SELECT ?,?, ?, ?, ?,'Pending'\n";
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);

        for (ScheduleHvDTO scheduleHvDTO : listScheduleHV) {

            String AUTO_ATTENDANCE_ID = String.format(Constants.MA_ATTENDANCE_FORMAT, (lastIDIndex() + 1));

            //HOCVIEN CONSTRUCTOR
            String attendanceID = AUTO_ATTENDANCE_ID;

            ps.setString(1, attendanceID);
            ps.setString(2, scheduleHvDTO.getMaHV());
            ps.setString(3, scheduleHvDTO.getMaLopHoc());
            ps.setDate(4, scheduleHvDTO.getNgayHoc());
            ps.setString(5, scheduleHvDTO.getMaSlot());
            ps.executeUpdate();
        }

        return false;
    }

    public int lastIDIndex() {
        String sql = "SELECT TOP 1 attendanceID FROM [dbo].[Attendance] ORDER BY attendanceID DESC";
        int index = 0;
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                int numberOnly = Integer.parseInt(rs.getString("attendanceID").replaceAll("[^0-9]", ""));
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

    public static void main(String[] args) throws SQLException {
        AttendanceDAO attendanceDAO = new AttendanceDAO();
        LocalDate date = LocalDate.now();
        System.out.println(attendanceDAO.readAttendance(Date.valueOf(date), "SL002", "LOP0003"));
    }
}
