/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dao;

import com.mycompany.yogacenterproject.dto.VoucherDTO;
import com.mycompany.yogacenterproject.util.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author iba
 */
public class VoucherDAO {

    public List<VoucherDTO> listVouchers() throws SQLException {
        List<VoucherDTO> listVoucher = new ArrayList<>();
        VoucherDTO voucherDTO = new VoucherDTO();
        String sql = "select * from [dbo].[Voucher]\n'";
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                voucherDTO.setVoucherID(rs.getString("voucherID"));
                voucherDTO.setVoucherName(rs.getString("voucherName"));
                voucherDTO.setMultiplier(rs.getLong("multiplier"));
                listVoucher.add(voucherDTO);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
        }
        return listVoucher;
    }

    public void addVoucher() throws SQLException {
        String sql = "insert into [dbo].[Voucher]([voucherID],[voucherName],[multiplier])\n"
                + "values (?,?,?)";
        Connection conn = DBUtils.getConnection();
        VoucherDTO voucherDTO = new VoucherDTO();
        PreparedStatement ps = conn.prepareStatement(sql);

        try {
            ps.setString(1, voucherDTO.getVoucherID());
            ps.setString(2, voucherDTO.getVoucherName());
            ps.setFloat(3, voucherDTO.getMultiplier());
            ps.executeUpdate();
        } catch (SQLException e) {

        }

    }

    public long getMultiplierByID(String voucherID) throws SQLException {
        long multiplier = 1;
        String sql = "select [multiplier] from [dbo].[Voucher]\n"
                + "where [voucherID] = ?";
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, voucherID);
        ResultSet rs = ps.executeQuery();
        try {
            if (rs.next()) {
                multiplier = rs.getLong("multiplier");
            }
        } catch (SQLException e) {
        }
        return multiplier;
    }

    public void deleteVoucher(String voucherID) throws SQLException {
        String sql = "delete [dbo].[Voucher]\n"
                + "where [voucherID]=?";
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, voucherID);
        
        try {
            ps.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public int lastIDIndex() {
        String sql = "SELECT TOP 1 [voucherID]\n"
                + "FROM [dbo].[Voucher]\n"
                + "ORDER BY [voucherID] DESC";
        int index = 0;
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                int numberOnly = Integer.parseInt(rs.getString("voucherID").replaceAll("[^0-9]", ""));
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

    public boolean getVoucherName(String voucherName) throws SQLException {
        String sql = "select * from [dbo].[Voucher]\n"
                + "where [voucherName] like ?";
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        
        try {
            ps.setString(1, voucherName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true; //= it already existed
            }
        } catch (SQLException e) {

        }
        return false;
    }

    public static void main(String[] args) throws SQLException {
        VoucherDAO voucherDAO = new VoucherDAO();
//        int index = voucherDAO.lastIDIndex();
//        System.out.println(index);
//        boolean nigger = voucherDAO.getVoucherName("nigger");
//        System.out.println(nigger);
voucherDAO.deleteVoucher("V0001");

    }
}
