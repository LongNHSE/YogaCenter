/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dao;

import com.mycompany.yogacenterproject.dto.HocVienDTO;
import com.mycompany.yogacenterproject.util.Constants;
import com.mycompany.yogacenterproject.util.DBUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HocVienDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

//Read list của toàn bộ học viên
    public List<HocVienDTO> readListHocVien() {
        List<HocVienDTO> listHocVien = new ArrayList<>();
        try {
            String sql = "SELECT * FROM hocVien";
            PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String maHV = rs.getString("maHV");
                String Ho = rs.getString("Ho");
                String Ten = rs.getString("Ten");
                //////DEFINE LOCALDATE AND RECEIVING DATA
                Date date = rs.getDate("dob");
//                DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy"); ///FORMAT CHO DATE
                LocalDate dob = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
                //////////////////////////////////////////////////////////////////
                String username = rs.getString("username");
                String psw = rs.getString("psw");

                String maLoaiTK = rs.getString("maLoaiTK");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String gender = rs.getString("gender");

                HocVienDTO newTrainee = new HocVienDTO(maHV, Ho, Ten, dob, username, phone, psw, maLoaiTK, email, gender);
                newTrainee.setMaLopHoc(classOfTrainee(maHV));
                listHocVien.add(newTrainee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listHocVien;
    }

// Tìm kiếm details của 1 người dựa trên id
    public HocVienDTO searchHocVienById(String maHV) {
        try {
            String sql = "SELECT * FROM hocVien where maHV=?";
            PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
            stm.setString(1, maHV);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                String Ho = rs.getString("Ho");
                String Ten = rs.getString("Ten");
                ///////DATE
                Date date = rs.getDate("dob");
//                DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
                LocalDate dob = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();

                String username = rs.getString("username");
                String psw = rs.getString("psw");

                String maLoaiTK = rs.getString("maLoaiTK");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String gender = rs.getString("gender");
                HocVienDTO result = new HocVienDTO(maHV, Ho, Ten, dob, username, phone, psw, maLoaiTK, email, gender);
                return result;
            }
        } catch (SQLException e) {
            Logger.getLogger(HocVienDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

/// Add thêm học viên
    public void addHocVien(HocVienDTO newHocVien) {
        try {
            String sql = "Insert into hocVien(maHV,Ho,Ten,dob,username,psw,maLoaiTK,email,phone,gender)"
                    + "values (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
            stm.setString(1, newHocVien.getMaHV());
            stm.setString(2, newHocVien.getHo());
            stm.setString(3, newHocVien.getTen());
            stm.setDate(4, Date.valueOf(newHocVien.getDob()));
            stm.setString(5, newHocVien.getUsername());
            stm.setString(6, newHocVien.getPsw());
            stm.setString(7, newHocVien.getMaLoaiTK());
            stm.setString(8, newHocVien.getEmail());
            stm.setString(9, newHocVien.getPhone());
            stm.setString(10, newHocVien.getGender());
            stm.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(TrainerDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

///Update học viên
    public void updateHocVien(HocVienDTO upTrainee) {
        try {
            String sql = "Update hocVien set Ho=?,Ten=?,dob=?, username=?,phone=? WHERE maHV=?";
            PreparedStatement stmt = DBUtils.getConnection().prepareStatement(sql);
            stmt.setString(1, upTrainee.getHo());
            stmt.setString(2, upTrainee.getTen());
            stmt.setDate(3, Date.valueOf(upTrainee.getDob()));
            stmt.setString(4, upTrainee.getUsername());
            stmt.setString(5, upTrainee.getPhone());
            stmt.setString(6, upTrainee.getMaHV());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HocVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    ////Hủy diệt 1 đứa học sinh bằng id
    public void deleteHocVienById(String maHV) {
        try {
            String sql = "DELETE FROM hocVien where maHV =?";
            PreparedStatement stm;
            stm = DBUtils.getConnection().prepareStatement(sql);
            stm.setString(1, maHV);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HocVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
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
            int i = 0;
//            System.out.println("chay vao try");///////
            while (rs.next()) {
                HocVienDTO hocVienDTO = new HocVienDTO();
                if (i == 0) {
                    List<String> listMaLopHoc = classOfTrainee(rs.getString("maHV"));
                    hocVienDTO.setMaLopHoc(listMaLopHoc);
                    i++;
                }
//              String maHV, String Ho, String Ten, LocalDate dob, String username, String psw, String maLopHoc, String maLoaiTK, String email, String phone
                String maHV = rs.getString("maHV");
                String ho = rs.getString("Ho");
                String ten = rs.getString("Ten");
                String user = rs.getString("username");
                String phone = rs.getString("phone");
                String psw = rs.getString("psw");
                String maLoaiTk = rs.getString("maLoaiTk");
                String email = rs.getString("email");
                String gender = rs.getString("gender");
                ////////CONVERT DATE TO LOCALDATE
                Date date = rs.getDate("dob");
//                DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy"); ///FORMAT CHO DATE
                LocalDate dob = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
                //////////////////////////////////////////////////////////////////
                HocVienDTO loginUser = new HocVienDTO(maHV, ho, ten, dob, user, phone, psw, maLoaiTk, email, gender);
                return loginUser;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //GET ALL CLASS FROM 1 TRAINEE 
    public List<String> classOfTrainee(String maHv) {
        List<String> listMaLopHoc = new ArrayList();

        String querry = "SELECT maLopHoc\n"
                + "FROM ScheduleHV\n"
                + "WHERE maHV = ? \n"
                + "group by maLopHoc";
        try {
            conn = new DBUtils().getConnection(); // connect DB

            ps = conn.prepareStatement(querry);
            ps.setString(1, maHv);
            ResultSet rs;
            rs = ps.executeQuery();

            while (rs.next()) {
                String maLopHoc = rs.getString("maLopHoc");
                listMaLopHoc.add(maLopHoc);

            }
            return listMaLopHoc;
        } catch (SQLException e) {

        }
        return null;
    }

    //GENERATE ID 
    public int lastIDIndex() {
        String sql = "SELECT TOP 1 maHV FROM [hocVien] ORDER BY maHV DESC";
        int index = 0;
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                int numberOnly = Integer.parseInt(rs.getString("maHV").replaceAll("[^0-9]", ""));
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

    //KIEM TRA USERNAME CUA HOC VIEN DA TON TAI CHUA
    public boolean selectByUserName(String userName) {

        try {

            // Bước 1: tạo kết nối đến CSDL
            Connection con = DBUtils.getConnection();

            // Bước 2: tạo ra đối tượng statement
            String sql = "SELECT * FROM [dbo].[hocVien] where username=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, userName);

            // Bước 3: thực thi câu lệnh SQL
            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            // Bước 4:
            if (rs.next()) {

                return true;
            }

            // Bước 5:
            DBUtils.closeConnection(con);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    //KIEM TRA EMAIL DA TON TAI CHUA
    public boolean selectByHocVienEmail(String email) {

        try {

            // Bước 1: tạo kết nối đến CSDL
            Connection con = DBUtils.getConnection();

            // Bước 2: tạo ra đối tượng statement
            String sql = "SELECT * FROM [dbo].[hocVien] where email= ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, email);

            // Bước 3: thực thi câu lệnh SQL
            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            // Bước 4:
            if (rs.next()) {
//               
                return true;
            }

            // Bước 5:
            DBUtils.closeConnection(con);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    //Update user's password
    public HocVienDTO changePsw(String psw, String email) {
        String query = "UPDATE dbo.hocVien\n"
                + "set psw = ? where email = ? ";
        try {
            conn = new DBUtils().getConnection(); // connect DB
            ps = conn.prepareStatement(query);
            ps.setString(1, psw);
            ps.setString(2, email);
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return null;
    }

    public static void main(String[] args) {

        List<HocVienDTO> listHocVienDTO = new ArrayList<>();
        HocVienDAO hocVienDAO = new HocVienDAO();
        HocVienDTO hocVienDTO = new HocVienDTO();
//        hocVienDTO = hocVienDAO.login("longNiger", "123456");
//        hocVienDTO = hocVienDAO.searchHocVienById("HV0002");
//        hocVienDAO.updateHocVien(hocVienDTO);
        hocVienDTO = hocVienDAO.login("Oalskad", "Pugre11111");
        System.out.println(hocVienDTO.getMaHV());

//        boolean a = hocVienDAO.selectByHocVienEmail("cawegi5617@farebus.com");
//        if (a) {
//            System.out.println("Email exist");
//        } else {
//            System.out.println("Email not exist");
//        }
//        System.out.println(hocVienDTO.toString());
//        System.out.println(hocVienDAO.selectByHocVienEmail("Oalskad1904@gmail.com"));
//        String AUTO_HOCVIEN_ID = String.format(Constants.MA_HOCVIEN_FORMAT, hocVienDAO.lastIDIndex()+1 );
//Date a = Date.valueOf("2003-02-13");
//            hocVienDTO.setUsername("A");
//            hocVienDTO.setTen("Long");
//            hocVienDTO.setPsw("1234");
//            hocVienDTO.setPhone("123");
//            hocVienDTO.setMaLopHoc(null);
//            hocVienDTO.setMaLoaiTK("HOCVIEN");
//            hocVienDTO.setMaHV(AUTO_HOCVIEN_ID);
//            hocVienDTO.setHo("Nguyen");
//            hocVienDTO.setGender("Male");
//            hocVienDTO.setEmail("huylong2");
//            hocVienDTO.setDob(a);
//            hocVienDAO.addHocVien(hocVienDTO);
//        System.out.println(AUTO_HOCVIEN_ID);
//        HocVienDTO login = hocVienDAO.login("HV001", "abcd1234");
//        int a = hocVienDAO.lastIDIndex();
//        System.out.println(a);
    }
}
