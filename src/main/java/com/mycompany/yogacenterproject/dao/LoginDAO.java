/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dao;

import com.mycompany.yogacenterproject.dto.HocVienDTO;
import com.mycompany.yogacenterproject.util.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author devli
 */
public class LoginDAO {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
//    Login
    public HocVienDTO login(String username, String pass){
        String querry = "SELECT * FROM hocVien\n" +
                "where username = ? and psw = ? ";
        try {
            conn = new DBUtils().getConnection(); // connect DB
            ps = conn.prepareStatement(querry);
            ps.setString(1, username);
            ps.setString(2, pass);
            rs=ps.executeQuery();
            while(rs.next()){
//              String maHV, String Ho, String Ten, Date dob, String username, String psw, String maLopHoc, String maLoaiTK, String email, String phone
                return new HocVienDTO(rs.getString("maHV"),rs.getString("Ho"), rs.getString("Ten"), rs.getDate("dob"),rs.getString("username"),rs.getString("phone"),rs.getString("psw"),rs.getString("maLopHoc"),rs.getString("maLoaiTk"),rs.getString("email"),rs.getString("gender"));
                
            }
        } catch (Exception e) {
        }
        return null;
    }    
    public static void main(String[] args) {
        LoginDAO dao = new LoginDAO();
        HocVienDTO login = dao.login("devlindinh", "123456");
        System.out.println(login.toString());
    }
}
