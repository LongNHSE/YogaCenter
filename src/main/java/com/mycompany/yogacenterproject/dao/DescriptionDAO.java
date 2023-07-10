/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dao;

import com.mycompany.yogacenterproject.dto.DescriptionDTO;
import com.mycompany.yogacenterproject.dto.HoaDonDTO;
import com.mycompany.yogacenterproject.util.Constants;
import com.mycompany.yogacenterproject.util.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Oalskad
 */
public class DescriptionDAO {

    public DescriptionDTO readDescription(String maLoaiLopHoc) {
        DescriptionDTO descriptionDTO = new DescriptionDTO();

        String sql = "Select [dbo].[description].maDescription, title, content from loaiLopHoc\n"
                + "inner join [dbo].[description] on [description].maDescription = loaiLopHoc.[maDescription]"
                + "where maLoaiLopHoc = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maLoaiLopHoc);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                descriptionDTO.setMaDescription(rs.getString("maDescription"));
                descriptionDTO.setContent(rs.getString("content"));
                descriptionDTO.setTitle(rs.getString("title"));
            }
            rs.close();
            conn.close();
            ps.close();
            return descriptionDTO;
        } catch (SQLException e) {
        }
        return descriptionDTO;
    }

    //TAO Hoa DOn 
    public boolean createDescriptionDTO(DescriptionDTO descriptionDTO) throws SQLException {
        String sql = "INSERT INTO [dbo].[description](maDescription, title, content)"
                + "VALUES(?, ?, ?)";
        int check = 1;
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, descriptionDTO.getMaDescription());
            ps.setString(2, descriptionDTO.getTitle());
            ps.setString(3, descriptionDTO.getContent());

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
        String sql = "SELECT TOP 1 maDescription FROM [dbo].[description] ORDER BY maDescription DESC";
        int index = 0;
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                int numberOnly = Integer.parseInt(rs.getString("maDescription").replaceAll("[^0-9]", ""));
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
        DescriptionDAO a = new DescriptionDAO();
        DescriptionDTO b = new DescriptionDTO();
        String AUTO_DESCRIPTION_ID = String.format(Constants.MA_DESCRIPTION_FORMAT, (a.lastIDIndex() + 1));
        String maDescription = AUTO_DESCRIPTION_ID;
        b.setMaDescription(maDescription);
        b.setContent("eqwe");
        b.setTitle("sadcvxv");
        a.createDescriptionDTO(b);
//        System.out.println(a.readDescription("TYPE0001"));

//        String thuListValue = "[THURSDAY, TUESDAY]";
//
//// Remove the square brackets and spaces from the string
//String cleanedValue = thuListValue.replaceAll("[\\[\\]\\s]", "");
//
//// Split the cleaned value into individual elements
//String[] elements = cleanedValue.split(",");
//
//// Convert the array to a List<String>
//List<String> thuList = new ArrayList<>(Arrays.asList(elements));
//
//// Print the thuList
//System.out.println(thuList.get(0));
    }
}
