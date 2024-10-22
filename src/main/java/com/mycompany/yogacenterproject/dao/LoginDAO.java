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
import java.time.LocalDate;

/**
 *
 * @author devli
 */
public class LoginDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
//    Login

    public HocVienDTO login(String username, String pass) {
        String querry = "SELECT * FROM hocVien\n"
                + "where username = ? and psw = ? ";
        try {
            conn = new DBUtils().getConnection(); // connect DB
            ps = conn.prepareStatement(querry);
            ps.setString(1, username);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            while (rs.next()) {
//              String maHV, String Ho, String Ten, Date dob, String username, String psw, String maLopHoc, String maLoaiTK, String email, String phone
                String maHV=rs.getString("maHV");
                String ho=rs.getString("Ho");
                String ten=rs.getString("Ten");
                String user =rs.getString("username");
                String phone=rs.getString("phone");
                String psw=rs.getString("psw");
                String maLoaiTk=rs.getString("maLoaiTk");
                String email=rs.getString("email");
                String gender=rs.getString("gender");
                HocVienDTO loginUser= new HocVienDTO(maHV, ho, ten, null, username, phone, psw, maLoaiTk, email, gender);
                return loginUser;
            }
        } catch (Exception e) {
        }
        return null;
    }
    
      public HocVienDTO login2() {
         
          
          
          String username = "O%";
        String querry = "SELECT * FROM hocVien\n"
                + "where username like " +"'"+ username+"'";
        try {
            conn = new DBUtils().getConnection(); // connect DB
            ps = conn.prepareStatement(querry);
            
//            ps.setString(1, username);
            
            rs = ps.executeQuery();
            while (rs.next()) {
//              String maHV, String Ho, String Ten, Date dob, String username, String psw, String maLopHoc, String maLoaiTK, String email, String phone
                String maHV=rs.getString("maHV");
                String ho=rs.getString("Ho");
                String ten=rs.getString("Ten");
                String user =rs.getString("username");
                String phone=rs.getString("phone");
                String psw=rs.getString("psw");
                String maLoaiTk=rs.getString("maLoaiTk");
                String email=rs.getString("email");
                String gender=rs.getString("gender");
                HocVienDTO loginUser= new HocVienDTO(maHV, ho, ten, null, username, phone, psw, maLoaiTk, email, gender);
                return loginUser;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static void main(String[] args) {
        LoginDAO dao = new LoginDAO();
//        HocVienDTO login = dao.login("devlindinh", "123456");
        HocVienDTO login = dao.login2();
        System.out.println(login);
//        System.out.println(login.toString());
    }
}
