/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dao;

import com.mycompany.yogacenterproject.dto.HoaDonDTO;
import com.mycompany.yogacenterproject.dto.SemesterDTO;
import com.mycompany.yogacenterproject.util.DBUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.TreeMap;

/**
 *
 * @author Oalskad
 */
public class HoaDonDAO {

    public List<HoaDonDTO> listHoaDon(String maHV) {
        List<HoaDonDTO> listHoaDon = new ArrayList<HoaDonDTO>();

        String sql = "SELECT * FROM [dbo].[hoaDon] WHERE maHV = ? ";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maHV);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonDTO hoaDonDTO = new HoaDonDTO();
                hoaDonDTO.setMahoaDon(rs.getString("mahoaDon"));
                hoaDonDTO.setMaHV(rs.getString("maHV"));
                hoaDonDTO.setMaLopHoc(rs.getString("maLopHoc"));
                hoaDonDTO.setGiaTien(rs.getLong("giaTien"));
                hoaDonDTO.setNgayThanhToan(rs.getDate("ngayThanhToan"));
                listHoaDon.add(hoaDonDTO);
            }
            rs.close();
            conn.close();
            ps.close();
            return listHoaDon;
        } catch (SQLException e) {
        }
        return null;
    }

    //TAO Hoa DOn 
    public boolean createHoaDonDTO(HoaDonDTO hoaDonDTO) throws SQLException {
        String sql = "INSERT INTO [dbo].[hoaDon](mahoaDon, maHV, maLopHoc, giaTien, ngayThanhToan)"
                + "VALUES(?, ?, ?, ?, ?)";
        int check = 1;
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, hoaDonDTO.getMahoaDon());
            ps.setString(2, hoaDonDTO.getMaHV());
            ps.setString(3, hoaDonDTO.getMaLopHoc());
            ps.setFloat(4, hoaDonDTO.getGiaTien());
            ps.setDate(5, hoaDonDTO.getNgayThanhToan());

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

    public int lastIDIndex() {
        String sql = "SELECT TOP 1 mahoaDon FROM [hoaDon] ORDER BY mahoaDon DESC";
        int index = 0;
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                int numberOnly = Integer.parseInt(rs.getString("mahoaDon").replaceAll("[^0-9]", ""));
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

    public HashMap<Date, Integer> countReceipt() throws SQLException {
        SemesterDAO semesterDAO = new SemesterDAO();
        SemesterDTO semesterDTO = semesterDAO.getCurrentSemester();
        HashMap<Date, Integer> countReceipt = new HashMap<Date, Integer>();
        String sql = "select ngayThanhToan, count(maHoaDon) from hoaDon\n"
                + "where ngayThanhToan>=? and ngayThanhToan<= ? \n"
                + "group by ngayThanhToan "
                + "order by ngayThanhToan";
        PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
        stm.setDate(1, semesterDTO.getStartDate());
        stm.setDate(2, semesterDTO.getEndDate());
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            Date date = rs.getDate(1);
            int count = rs.getInt(2);
            countReceipt.put(date, count);

        }
        if (!countReceipt.containsKey(semesterDTO.getStartDate())) {
            Date date = semesterDTO.getStartDate();
            int count = 0;
            countReceipt.put(date, count);
        }
        if (!countReceipt.containsKey(semesterDTO.getEndDate())) {
            Date date = semesterDTO.getEndDate();
            int count = 0;
            countReceipt.put(date, count);
        }
        return countReceipt;
    }

    public TreeMap<String, Integer> countReceiptSemester() throws SQLException {
        List<SemesterDTO> listSemesterDTO = new ArrayList<>();

        SemesterDAO semesterDAO = new SemesterDAO();
        listSemesterDTO = semesterDAO.getAllSemester();

        TreeMap<String, Integer> countReceiptSemester = new TreeMap<String, Integer>();
        String sql = "select  sum(giaTien) from hoaDon\n"
                + "where ngayThanhToan>=? and ngayThanhToan<= ? \n"
               ;
        PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
        for (SemesterDTO semesterDTO : listSemesterDTO) {
            stm.setDate(1, semesterDTO.getStartDate());
            stm.setDate(2, semesterDTO.getEndDate());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                int count = rs.getInt(1);
                countReceiptSemester.put(semesterDTO.getCourses(), count);

            }

        }
        return countReceiptSemester;
    }

    public static void main(String[] args) throws SQLException {
        HoaDonDAO hoaDonDAO = new HoaDonDAO();
        HoaDonDTO x = new HoaDonDTO();
        System.out.println(hoaDonDAO.countReceiptSemester());
//        x.setMahoaDon("RC0001");
//        x.setMaHV("HV0001");
//        x.setGiaTien(20);
//        x.setNgayThanhToan(Date.valueOf("2023-06-30"));
//        x.setMaLopHoc("LOP0003");
//        hoaDonDAO.createHoaDonDTO(x);
//        System.out.println(hoaDonDAO.listHoaDon("HV0001"));
    }
}
