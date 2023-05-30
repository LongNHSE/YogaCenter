/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dao;

import com.mycompany.yogacenterproject.dto.AdminDTO;
import com.mycompany.yogacenterproject.util.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Oalskad
 */
public class AdminDAO {

    //FUNCTION LOGIN AND RETURN ADMINDTO
    public AdminDTO login(String userName, String psw) {

        String sql = "SELECT * FROM [Admin] where username = ? and psw = ?";
        AdminDTO admin = new AdminDTO();
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, psw);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                admin.setMaADmin(rs.getString("maAdmin"));
                admin.setUserName(rs.getString("username"));
                admin.setPsw(rs.getString("psw"));
                return admin;
            }
            
            rs.close();
            ps.close();
            conn.close();
            
        } catch (SQLException ex) {
            System.out.println("Query error!" + ex.getMessage());
        }
        return null;
    }

    //CREATE NEW ADMIN
    public boolean createAdmin(String userName, String psw, String maAdmin) {
        String sql = "INSERT INTO [dbo].[Admin] (maAdmin,username, psw)" + "VALUES(?, ?, ?)";

        // If ps.executeUpdate() co du lieu vao thi return 1 va return 0 neu ko co
        int row = 0;
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maAdmin);
            ps.setString(2, userName);
            ps.setString(3, psw);

            row = ps.executeUpdate();

            ps.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);

        }
        return row > 0;
    }
    
    //DELETE ADMIN EXCEPT ADMIN SA
    public boolean deleteAdmin(String maAdmin){
        String sql = "DELETE FROM [dbo].[admin] WHERE maAdmin = ?";
        
        int row = 0;
        if(maAdmin != "AD0001"){
            try {
                Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, maAdmin);
                row = ps.executeUpdate();
                
                ps.close();
                conn.close();
                
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return row > 0;
    }
    

    //TESTING
    public static void main(String[] args) {
        AdminDAO adminDAO = new AdminDAO();
        AdminDTO adminDTO = new AdminDTO();
        adminDTO = adminDAO.login("admin1", "1234");
        System.out.println(adminDTO);
//adminDAO.deleteAdmin("AD0001");
//        adminDAO.createAdmin("sa2", "123123", "AD0002");
    }
}
