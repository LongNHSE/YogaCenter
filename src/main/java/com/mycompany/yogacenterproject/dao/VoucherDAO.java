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
        String sql = "SELECT * FROM [dbo].[Voucher]";
        try (PreparedStatement ps = DBUtils.getConnection().prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                VoucherDTO voucherDTO = new VoucherDTO();
                voucherDTO.setVoucherID(rs.getString("voucherID"));
                voucherDTO.setVoucherName(rs.getString("voucherName"));
                voucherDTO.setMultiplier(rs.getInt("multiplier"));
                voucherDTO.setUsageLimit(rs.getInt("usageLimit"));
                voucherDTO.setUsageLimitPerUser(rs.getInt("usageLimitPerUser"));
                voucherDTO.setTotalUsage(rs.getInt("totalUsage"));
                listVoucher.add(voucherDTO);
            }
        } catch (SQLException e) {
        }
        return listVoucher;
    }

    public VoucherDTO searchVoucherByID(String voucherID) throws SQLException {
        VoucherDTO voucherDTO = new VoucherDTO();
        String sql = "select * from [dbo].[Voucher]\n"
                + "where voucherID = ?";
        try(PreparedStatement ps = DBUtils.getConnection().prepareStatement(sql); 
                ResultSet rs = ps.executeQuery()){
            ps.setString(1, voucherID);
            while(rs.next()){
                voucherDTO.setVoucherID(rs.getString("voucherID"));
                voucherDTO.setVoucherName(rs.getString("voucherName"));
                voucherDTO.setMultiplier(rs.getInt("multiplier"));
                voucherDTO.setUsageLimit(rs.getInt("usageLimit"));
                voucherDTO.setUsageLimitPerUser(rs.getInt("usageLimitPerUser"));
                voucherDTO.setTotalUsage(rs.getInt("totalUsage"));
            }
        }catch(SQLException e){
            
        }
        return voucherDTO;
    }

    public void addVoucher(VoucherDTO voucherDTO) throws SQLException {
        String sql = "insert into [dbo].[Voucher]([voucherID],[voucherName],[multiplier],[usageLimit],[usageLimitPerUser])\n"
                + "values (?,?,?,?,?)";
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);

        try {
            ps.setString(1, voucherDTO.getVoucherID());
            ps.setString(2, voucherDTO.getVoucherName());
            ps.setInt(3, voucherDTO.getMultiplier());
            ps.setInt(4, voucherDTO.getUsageLimit());
            ps.setInt(5, voucherDTO.getUsageLimitPerUser());
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
                multiplier = rs.getInt("multiplier");
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

    public void updateVoucher(VoucherDTO voucherDTO) throws SQLException {
        String sql = "UPDATE Voucher \n"
                + "SET voucherName = ?, multiplier = ?, usageLimit = ?, usageLimitPerUser = ? \n"
                + "WHERE voucherID = ?";
        PreparedStatement ps = DBUtils.getConnection().prepareStatement(sql);
        try {
            ps.setString(1, voucherDTO.getVoucherName());
            ps.setInt(2, voucherDTO.getMultiplier());
            ps.setInt(3, voucherDTO.getUsageLimit());
            ps.setInt(4, voucherDTO.getUsageLimitPerUser());
            ps.setString(5, voucherDTO.getVoucherID());
            ps.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public boolean checkVoucherName(String voucherName) throws SQLException {
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
        List<VoucherDTO> list = new ArrayList<>();
//        VoucherDTO voucherDTO = new VoucherDTO("V0004", "test2", 50, 10, 1,2);
        int index = voucherDAO.lastIDIndex();
        System.out.println(index);
//        boolean nigger = voucherDAO.getVoucherName("nigger");
//        System.out.println(nigger);
//        voucherDAO.deleteVoucher("V0001");
//        list = voucherDAO.listVouchers();
//        for (VoucherDTO x : list) {
//            System.out.println(x);
//    }

//    voucherDAO.addVoucher(voucherDTO);
//voucherDAO.deleteVoucher("V0003");
    }
}
