/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dao;

import com.mycompany.yogacenterproject.dto.HoaDonDTO;
import com.mycompany.yogacenterproject.util.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

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
    
    public static void main(String[] args) {
        HoaDonDAO hoaDonDAO = new HoaDonDAO();
        System.out.println(hoaDonDAO.listHoaDon("HV0001"));
    }
}

