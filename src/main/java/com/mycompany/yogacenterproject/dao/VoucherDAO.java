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

        try (PreparedStatement ps = DBUtils.getConnection().prepareStatement(sql);) {
            ps.setString(1, voucherID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                voucherDTO.setVoucherID(rs.getString("voucherID"));
                voucherDTO.setVoucherName(rs.getString("voucherName"));
                voucherDTO.setMultiplier(rs.getInt("multiplier"));
                voucherDTO.setUsageLimit(rs.getInt("usageLimit"));
                voucherDTO.setUsageLimitPerUser(rs.getInt("usageLimitPerUser"));
                voucherDTO.setTotalUsage(rs.getInt("totalUsage"));
                return voucherDTO;
            }
        } catch (SQLException e) {

        }
        return null;
    }

    public VoucherDTO searchVoucherByName(String voucherName) throws SQLException {
        VoucherDTO voucherDTO = new VoucherDTO();
        String sql = "select * from [dbo].[Voucher]\n"
                + "where voucherName = ?";

        try (PreparedStatement ps = DBUtils.getConnection().prepareStatement(sql);) {
            ps.setString(1, voucherName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                voucherDTO.setVoucherID(rs.getString("voucherID"));
                voucherDTO.setVoucherName(rs.getString("voucherName"));
                voucherDTO.setMultiplier(rs.getInt("multiplier"));
                voucherDTO.setUsageLimit(rs.getInt("usageLimit"));
                voucherDTO.setUsageLimitPerUser(rs.getInt("usageLimitPerUser"));
                voucherDTO.setTotalUsage(rs.getInt("totalUsage"));
                return voucherDTO;
            }
        } catch (SQLException e) {

        }
        return null;
    }

//    public void addVoucher(VoucherDTO voucherDTO) throws SQLException {
//        String sql = "insert into [dbo].[Voucher]([voucherID],[voucherName],[multiplier],[usageLimit],[usageLimitPerUser])\n"
//                + "values (?,?,?,?,?)";
//        Connection conn = DBUtils.getConnection();
//        PreparedStatement ps = conn.prepareStatement(sql);
//
//        try {
//            ps.setString(1, voucherDTO.getVoucherID());
//            ps.setString(2, voucherDTO.getVoucherName());
//            ps.setInt(3, voucherDTO.getMultiplier());
//            ps.setInt(4, voucherDTO.getUsageLimit());
//            ps.setInt(5, voucherDTO.getUsageLimitPerUser());
//            ps.executeUpdate();
//        } catch (SQLException e) {
//
//        }
//
//    }
    public double getMultiplierByID(String voucherID) throws SQLException {
        int multiplier = 100;
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

    public void addVoucher(VoucherDTO newVoucher) {
        String sql = "INSERT INTO [dbo].[Voucher] (voucherID,voucherName,multiplier,usageLimit,usageLimitPerUser,totalUsage)"
                + "VALUES (?,?,?,?,?,0)";
        try {
            PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
            stm.setString(1, newVoucher.getVoucherID());
            stm.setString(2, newVoucher.getVoucherName());
            stm.setInt(3, newVoucher.getMultiplier());
            stm.setInt(4, newVoucher.getUsageLimit());
            stm.setInt(5, newVoucher.getUsageLimitPerUser());
            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void insertUserAndVoucher(String voucherID, String maHV) {
        String sql = "INSERT INTO UserVoucherUsage (VoucherID, maHV) "
                + "VALUES (?, ?)";

        try (PreparedStatement ps = DBUtils.getConnection().prepareStatement(sql)) {
            ps.setString(1, voucherID);
            ps.setString(2, maHV);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getUsageCountForIndividual(String voucherID, String maHV) {
        int usageCount = 0;
        String sql = "SELECT [usageCount]\n"
                + "FROM [dbo].[UserVoucherUsage]\n"
                + "WHERE maHV = ?\n"
                + "  AND VoucherID = ?";
        try (PreparedStatement ps = DBUtils.getConnection().prepareStatement(sql)) {
            ps.setString(1, maHV);
            ps.setString(2, voucherID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                usageCount = rs.getInt("usageCount");
                return usageCount;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void increaseIndividualUsageCount(String voucherID, String maHV) {
        String sql = "UPDATE UserVoucherUsage\n"
                + "SET usageCount = usageCount + 1\n"
                + "WHERE maHV = ?\n"
                + "  AND VoucherID = ?";
        try (PreparedStatement ps = DBUtils.getConnection().prepareStatement(sql)) {
            ps.setString(1, maHV);
            ps.setString(2, voucherID);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void increaseTotalUsageCount(String voucherID) {
        String sql = "UPDATE Voucher\n"
                + "SET totalUsage = totalUsage + 1\n"
                + "WHERE VoucherID = ?";
        try (PreparedStatement ps = DBUtils.getConnection().prepareStatement(sql)) {
            ps.setString(1, voucherID);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        VoucherDTO voucherDTO = new VoucherDTO();
//        VoucherDTO voucherDTO = new VoucherDTO("V0004", "test2", 50, 10, 1,2);
//        int index = voucherDAO.lastIDIndex();
//        System.out.println(index);
//        boolean voucher = voucherDAO.checkVoucherName("Yoga");
//        System.out.println(voucher);
//        voucherDAO.deleteVoucher("V0001");
        list = voucherDAO.listVouchers();
        for (VoucherDTO x : list) {
            System.out.println(x);
        }
//        voucherDTO = voucherDAO.searchVoucherByName("Yoga");
//        System.out.println(voucherDTO);

//    voucherDAO.addVoucher(voucherDTO);
//voucherDAO.deleteVoucher("V0003");
    }
}
