/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dao;

import com.mycompany.yogacenterproject.dto.DescriptionDTO;
import com.mycompany.yogacenterproject.dto.SemesterDTO;
import com.mycompany.yogacenterproject.util.DBUtils;
import com.mycompany.yogacenterproject.util.DateUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Oalskad
 */
public class SemesterDAO {

    public SemesterDTO readDescription(String quarterID) {
        SemesterDTO semesterDTO = new SemesterDTO();

        String sql = "SELECT * FROM [dbo].[Semester] where quarterID = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, quarterID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                semesterDTO.setQuarterID(rs.getInt("quarterID"));
                semesterDTO.setStartDate(rs.getDate("startDate"));
                semesterDTO.setEndDate(rs.getDate("endDate"));
                semesterDTO.setCourses(rs.getString("courses"));
            }
            rs.close();
            conn.close();
            ps.close();
            return semesterDTO;
        } catch (SQLException e) {
        }
        return semesterDTO;
    }

    public SemesterDTO getCurrentSemester() {
        SemesterDTO semesterDTO = new SemesterDTO();
        LocalDate currentDate = LocalDate.now();

        Date date = new Date();
        Date startDate = new Date();
        Date endDate = new Date();
        date = DateUtils.asDate(currentDate);

        String sql = "SELECT * FROM [dbo].[Semester]";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                startDate = rs.getDate("startDate");
                endDate = rs.getDate("endDate");
                if ((date.after(startDate) && date.before(endDate)) || date.equals(startDate) || date.equals(rs)) {
                    semesterDTO.setQuarterID(rs.getInt("quarterID"));
                    semesterDTO.setCourses(rs.getString("courses"));
                    semesterDTO.setStartDate(rs.getDate("startDate"));
                    semesterDTO.setEndDate(rs.getDate("endDate"));

                }
//                System.out.println( (date.after(startDate) && date.before(endDate))|| date.equals(startDate));
//                System.out.println(startDate);
//                System.out.println(endDate);

            }
            rs.close();
            conn.close();
            ps.close();
            return semesterDTO;
        } catch (SQLException e) {
        }
        return semesterDTO;

    }

    public List<SemesterDTO> getAllSemester() {
        List<SemesterDTO> listSemester = new ArrayList<>();
        String sql = "SELECT * FROM [dbo].[Semester]  order by courses";
        Date startDate = new Date();
        Date endDate = new Date();
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                SemesterDTO semesterDTO = new SemesterDTO();
                startDate = rs.getDate("startDate");
                endDate = rs.getDate("endDate");

                semesterDTO.setQuarterID(rs.getInt("quarterID"));
                semesterDTO.setCourses(rs.getString("courses"));
                semesterDTO.setStartDate(rs.getDate("startDate"));
                semesterDTO.setEndDate(rs.getDate("endDate"));
                //                System.out.println( (date.after(startDate) && date.before(endDate))|| date.equals(startDate));
                //                System.out.println(startDate);
                //                System.out.println(endDate);
                listSemester.add(semesterDTO);
            }
            rs.close();
            conn.close();
            ps.close();
            return listSemester;
        } catch (SQLException e) {
        }
        return listSemester;
    }

    public static void main(String[] args) {
        SemesterDAO semesterDAO = new SemesterDAO();
        System.out.println(semesterDAO.getCurrentSemester());
        LocalDate currentDate = LocalDate.now();

        Date date = new Date();
        date = DateUtils.asDate(currentDate);
        System.out.println(currentDate);
//        System.out.println(date);
        System.out.println(semesterDAO.getAllSemester());
    }

}
