/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dao;

import com.mycompany.yogacenterproject.dto.HocVienDTO;
import com.mycompany.yogacenterproject.dto.TrainerDTO;
import com.mycompany.yogacenterproject.util.DBUtils;
import com.mycompany.yogacenterproject.util.DateUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TrainerDAO {

    //Trainer login 
    public TrainerDTO loginTrainer(String username, String psw) {
        List<TrainerDTO> listTrainer = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Trainer where username = ? and psw= ?";
            PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, psw);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                TrainerDTO trainerDTO = new TrainerDTO();
                String maTrainer = rs.getString("maTrainer");
                String Ho = rs.getString("Ho");
                String Ten = rs.getString("Ten");
                Date dob = rs.getDate("dob");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                long salary = rs.getLong("salary");

                int soNgayNghi = rs.getInt("soNgayNghi");
                boolean status = rs.getBoolean("status");
                String trainerType = rs.getString("trainerType");
                String maLoaiTK = rs.getString("maLoaiTK");

                trainerDTO.setMaTrainer(maTrainer);
                trainerDTO.setDob(DateUtils.asLocalDate(dob));
                trainerDTO.setHo(Ho);
                trainerDTO.setTen(Ten);
                trainerDTO.setEmail(email);
                trainerDTO.setPhone(phone);
                trainerDTO.setPsw(psw);
                trainerDTO.setSalary(salary);
                trainerDTO.setSoNgayNghi(soNgayNghi);
                trainerDTO.setStatus(status);
                trainerDTO.setTrainerType(trainerType);
                trainerDTO.setUsername(username);
                trainerDTO.setMaLoaiTK(maLoaiTK);

                return trainerDTO;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getSoNgayNghi(String maTrainer) {

        try {
            String sql = "SELECT COUNT(*) as [soNgayNghi]\n"
                    + "FROM (\n"
                    + "  SELECT ScheduleTrainer.maLopHoc, Attendance.ngayHoc\n"
                    + "  FROM Attendance\n"
                    + "  INNER JOIN ScheduleTrainer ON ScheduleTrainer.maLopHoc = Attendance.maLopHoc\n"
                    + "  WHERE Attendance.ngayHoc < ? AND Attendance.status = 'Unmarked attendance' AND maTrainer = ?\n"
                    + "  GROUP BY ScheduleTrainer.maLopHoc, Attendance.ngayHoc\n"
                    + ") AS groupedData;";
            LocalDate currentDate = LocalDate.now();
            PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
            stm.setDate(1, Date.valueOf(currentDate));
            stm.setString(2, maTrainer);

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("soNgayNghi");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getSoNgayNghi1Lop(String maTrainer,String maLopHoc) {

        try {
            String sql = "SELECT COUNT(*) as [soNgayNghi]\n"
                    + "FROM (\n"
                    + "  SELECT ScheduleTrainer.maLopHoc, Attendance.ngayHoc\n"
                    + "  FROM Attendance\n"
                    + "  INNER JOIN ScheduleTrainer ON ScheduleTrainer.maLopHoc = Attendance.maLopHoc\n"
                    + "  WHERE Attendance.ngayHoc < ? AND Attendance.status = 'Unmarked attendance' AND maTrainer = ? AND ScheduleTrainer.maLopHoc=?\n"
                    + "  GROUP BY ScheduleTrainer.maLopHoc, Attendance.ngayHoc\n"
                    + ") AS groupedData;";
            LocalDate currentDate = LocalDate.now();
            PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
            stm.setDate(1, Date.valueOf(currentDate));
            stm.setString(2, maTrainer);
            stm.setString(3, maLopHoc);

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("soNgayNghi");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void CheckClass() {
        LopHocDAO lopHocDAO = new LopHocDAO();
        List<String> listMaLopHoc = lopHocDAO.readListIDClass();
        try {
            String sql = "SELECT top 1 [status] FROM [dbo].[ScheduleTrainer]\n"
                    + "where maLopHoc=?\n"
                    + "order by ngayHoc desc";
            for (String maLopHoc : listMaLopHoc) {
                PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
                stm.setString(1, maLopHoc);
                ResultSet rs = stm.executeQuery();
                if (rs.next()) {
                    boolean status = rs.getBoolean("status");
                    if (status == false) {
                        updateTrainerStatus(maLopHoc, true);
                    }

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
////Read list của các trainer

    public List<TrainerDTO> readListTrainer() {
        List<TrainerDTO> listTrainer = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Trainer";
            PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String maTrainer = rs.getString("maTrainer");
                String Ho = rs.getString("Ho");
                String Ten = rs.getString("Ten");
                Date dob = rs.getDate("dob");
                LocalDate dobDate = DateUtils.asLocalDate(dob);
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                long salary = rs.getLong("salary");
                String username = rs.getString("username");
                String psw = rs.getString("psw");
                int soNgayNghi = rs.getInt("soNgayNghi");
                boolean status = rs.getBoolean("status");
                String trainerType = rs.getString("trainerType");
                String maLoaiTK = rs.getString("maLoaiTK");
                TrainerDTO newTrainer = new TrainerDTO(maTrainer, Ho, Ten, dobDate, phone, email, salary, username, psw, soNgayNghi, status, trainerType, maLoaiTK);
//                TrainerDTO newTrainer = new TrainerDTO(maTrainer, HoVaTen, dob, phone, email, salary, username, psw, soNgayNghi, status, trainerType, maLoaiTK);
//                
                listTrainer.add(newTrainer);
            }
            return listTrainer;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public TrainerDTO readTrainer(String maTrainer) {
        List<TrainerDTO> listTrainer = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Trainer where maTrainer=? ";
            PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
            stm.setString(1, maTrainer);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
//                String maTrainer = rs.getString("maTrainer");
                String Ho = rs.getString("Ho");
                String Ten = rs.getString("Ten");
                Date dob = rs.getDate("dob");
                LocalDate dobDate = DateUtils.asLocalDate(dob);
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                long salary = rs.getLong("salary");
                String username = rs.getString("username");
                String psw = rs.getString("psw");
                int soNgayNghi = rs.getInt("soNgayNghi");
                boolean status = rs.getBoolean("status");
                String trainerType = rs.getString("trainerType");
                String maLoaiTK = rs.getString("maLoaiTK");
                TrainerDTO newTrainer = new TrainerDTO(maTrainer, Ho, Ten, dobDate, phone, email, salary, username, psw, soNgayNghi, status, trainerType, maLoaiTK);
//                TrainerDTO newTrainer = new TrainerDTO(maTrainer, HoVaTen, dob, phone, email, salary, username, psw, soNgayNghi, status, trainerType, maLoaiTK);
//                
                return newTrainer;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
//READ LIST WITH RECORD    

    public List<TrainerDTO> readListTrainerWithRecord(int pageID, int total) {
        List<TrainerDTO> listTrainer = new ArrayList<>();
        try {
            String sql = " SELECT * FROM Trainer \n"
                    + " order by maTrainer\n"
                    + " OFFSET (? - 1) ROWS\n"
                    + " FETCH NEXT ? ROWS ONLY";
            PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
            stm.setInt(1, pageID);
            stm.setInt(2, total);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                String maTrainer = rs.getString("maTrainer");
                String Ho = rs.getString("Ho");
                String Ten = rs.getString("Ten");
                Date dob = rs.getDate("dob");
                LocalDate dobDate = DateUtils.asLocalDate(dob);
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                long salary = rs.getLong("salary");
                String username = rs.getString("username");
                String psw = rs.getString("psw");
                int soNgayNghi = getSoNgayNghi(maTrainer);
                boolean status = rs.getBoolean("status");
                String trainerType = rs.getString("trainerType");
                String gender = rs.getString("gender");
                String maLoaiTK = rs.getString("maLoaiTK");
                TrainerDTO newTrainer = new TrainerDTO(maTrainer, Ho, Ten, dobDate, phone, email, salary, username, psw, soNgayNghi, status, trainerType, maLoaiTK);
//                TrainerDTO newTrainer = new TrainerDTO(maTrainer, HoVaTen, dob, phone, email, salary, username, psw, soNgayNghi, status, trainerType, maLoaiTK);
                newTrainer.setGender(gender);
                listTrainer.add(newTrainer);
            }
            return listTrainer;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

////Read list cua cac trainer nhung dua tren dieu kien loai trainer
    public List<TrainerDTO> readListTrainerByTypeAndStatus(String trainerType) {
        List<TrainerDTO> listTrainer = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Trainer where trainerType = ? and status ='false'";
            PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
            stm.setString(1, trainerType);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                TrainerDTO trainerDTO = new TrainerDTO();
                String maTrainer = rs.getString("maTrainer");
                String Ho = rs.getString("Ho");
                String Ten = rs.getString("Ten");
                Date dob = rs.getDate("dob");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                long salary = rs.getLong("salary");
                String username = rs.getString("username");
                String psw = rs.getString("psw");
                int soNgayNghi = rs.getInt("soNgayNghi");
                boolean status = rs.getBoolean("status");
                String trainerTypeGet = rs.getString("trainerType");
                String maLoaiTK = rs.getString("maLoaiTK");

                trainerDTO.setMaTrainer(maTrainer);
                trainerDTO.setHo(Ho);
                trainerDTO.setTen(Ten);
                trainerDTO.setEmail(email);
                trainerDTO.setPhone(phone);
                trainerDTO.setPsw(psw);
                trainerDTO.setSalary(salary);
                trainerDTO.setSoNgayNghi(soNgayNghi);
                trainerDTO.setStatus(status);
                trainerDTO.setTrainerType(trainerType);
                trainerDTO.setUsername(username);
                trainerDTO.setMaLoaiTK(maLoaiTK);

                listTrainer.add(trainerDTO);
            }
            return listTrainer;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
//SEARCH TRAINER BY CLASS ID

    public TrainerDTO searchTrainerByClassID(String maLopHoc) {
        try {
            String sql = "SELECT Trainer.maTrainer, Trainer.Ho, Trainer.Ten FROM Trainer\n"
                    + "inner join ScheduleTrainer on ScheduleTrainer.maTrainer = Trainer.maTrainer\n"
                    + "where ScheduleTrainer.maLopHoc= ?\n"
                    + "GROUP BY  Trainer.maTrainer, Trainer.Ho, Trainer.Ten";
            PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
            stm.setString(1, maLopHoc);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                TrainerDTO trainerDTO = new TrainerDTO();
                String Ho = rs.getString("Ho");
                String Ten = rs.getString("Ten");
                String maTrainer = rs.getString("maTrainer");
                trainerDTO.setSoNgayNghi(getSoNgayNghi1Lop(maTrainer, maLopHoc));
                trainerDTO.setMaTrainer(maTrainer);
                trainerDTO.setHo(Ho);
                trainerDTO.setTen(Ten);
                return trainerDTO;
            }
        } catch (SQLException e) {
            Logger.getLogger(HocVienDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
//////Search Trainer by id

    public TrainerDTO searchTrainerById(String maTrainer) {
        try {
            String sql = "SELECT * FROM Trainer where maTrainer=?";
            PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
            stm.setString(1, maTrainer);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String HoVaTen = rs.getString("HoVaTen");
                Date dob = rs.getDate("dob");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                long salary = rs.getLong("salary");
                String username = rs.getString("username");
                String psw = rs.getString("psw");
                int soNgayNghi = rs.getInt("soNgayNghi");
                boolean status = rs.getBoolean("status");
                String trainerType = rs.getString("trainerType");
                String maLoaiTK = rs.getString("maLoaiTK");
//                return newTrainer;
            }
        } catch (SQLException e) {
            Logger.getLogger(HocVienDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
////Thêm Trainer

    public void addTrainer(TrainerDTO newTrainer) {
        try {
            String sql = "INSERT INTO [dbo].[Trainer] "
                    + "([maTrainer], [Ho], [Ten], [dob], [phone], [email], [salary], "
                    + "[username], [psw], [soNgayNghi], [status], [trainerType], "
                    + "[maLoaiTK], [gender]) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = DBUtils.getConnection().prepareStatement(sql);
            stmt.setString(1, newTrainer.getMaTrainer());
            stmt.setString(2, newTrainer.getHo());
            stmt.setString(3, newTrainer.getTen());
            stmt.setDate(4, Date.valueOf(newTrainer.getDob()));
            stmt.setString(5, newTrainer.getPhone());
            stmt.setString(6, newTrainer.getEmail());
            stmt.setLong(7, newTrainer.getSalary());
            stmt.setString(8, newTrainer.getUsername());
            stmt.setString(9, newTrainer.getPsw());
            stmt.setInt(10, newTrainer.getSoNgayNghi());
            stmt.setBoolean(11, newTrainer.getStatus());
            stmt.setString(12, newTrainer.getTrainerType());
            stmt.setString(13, newTrainer.getMaLoaiTK());
            stmt.setString(14, newTrainer.getGender());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

/////Hủy diệt Trainer
    public void deleteTrainerById(String maTrainer) {
        try {
            String sql = "DELETE FROM Trainer where maTrainer =?";
            PreparedStatement stm;
            stm = DBUtils.getConnection().prepareStatement(sql);
            stm.setString(1, maTrainer);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HocVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

////// Update Trainer
    public void updateTrainer(TrainerDTO upTrainer) {
        try {
            String sql = "Update Trainer set HoVaTen=?,dob=?,phone=?, email=?,salary=?,username=?,psw=?,"
                    + "soNgayNghi=?,status=?,trainerType=?,maLoaiTK=? WHERE maHV=?";
            PreparedStatement stmt = DBUtils.getConnection().prepareStatement(sql);
//            stmt.setString(1, upTrainer.getHoVaTen());
            stmt.setDate(2, Date.valueOf(upTrainer.getDob()));
            stmt.setString(3, upTrainer.getPhone());
            stmt.setString(4, upTrainer.getEmail());
            stmt.setLong(5, upTrainer.getSalary());
            stmt.setString(6, upTrainer.getUsername());
            stmt.setBoolean(7, upTrainer.getStatus());
            stmt.setString(8, upTrainer.getTrainerType());
            stmt.setString(9, upTrainer.getMaLoaiTK());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HocVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//Update Trainer Status

    public void updateTrainerStatus(String maTrainer, boolean status) {
        try {
            String sql = "Update Trainer set status = ? "
                    + "where maTrainer=? ";
            PreparedStatement stmt = DBUtils.getConnection().prepareStatement(sql);
//            stmt.setString(1, upTrainer.getHoVaTen());
            stmt.setBoolean(1, status);
            stmt.setString(2, maTrainer);

            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HocVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //LAST INDEX OF TRAINER LIST 
    public int lastIDIndex() {
        String sql = "SELECT TOP 1 maTrainer FROM [Trainer] ORDER BY maTrainer DESC";
        int index = 0;
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                int numberOnly = Integer.parseInt(rs.getString("maTrainer").replaceAll("[^0-9]", ""));
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

    //SEARCH TRAINER USERNAME FOR DUPLICATE CHECK
    public boolean selectByUserName(String userName) {

        try {

            // Bước 1: tạo kết nối đến CSDL
            Connection con = DBUtils.getConnection();

            // Bước 2: tạo ra đối tượng statement
            String sql = "SELECT * FROM [dbo].[Trainer] where username=?";
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

    public boolean selectByEmail(String email) {

        try {

            // Bước 1: tạo kết nối đến CSDL
            Connection con = DBUtils.getConnection();

            // Bước 2: tạo ra đối tượng statement
            String sql = "SELECT * FROM [dbo].[Trainer] where email=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, email);

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

    public int countRecord() {
        int count = 0;
        try {

            // Bước 1: tạo kết nối đến CSDL
            Connection con = DBUtils.getConnection();

            // Bước 2: tạo ra đối tượng statement
            String sql = "SELECT count(maTrainer) as COUNT_ROW\n"
                    + "from Trainer";
            PreparedStatement st = con.prepareStatement(sql);

            // Bước 3: thực thi câu lệnh SQL
            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            // Bước 4:
            if (rs.next()) {
                count = rs.getInt("COUNT_ROW");
                return count;
            }

            // Bước 5:
            DBUtils.closeConnection(con);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return count;
    }

    public static void main(String[] args) {
//        TrainerDAO trainerDAO = new TrainerDAO();
//        System.out.println(trainerDAO.readListTrainer().get(0).getSalary());
        TrainerDTO newTrainer = new TrainerDTO();
        newTrainer.setMaTrainer("TR001");
        newTrainer.setHo("John");
        newTrainer.setTen("Doe");

////        newTrainer.setDob("1990-01-01");
//        newTrainer.setPhone("123456789");
//        newTrainer.setEmail("john.doe@example.com");
//        newTrainer.setSalary(50000);
//        newTrainer.setUsername("johndoe");
//        newTrainer.setPsw("password");
//        newTrainer.setSoNgayNghi(5);
//        newTrainer.setStatus(true);
//        newTrainer.setTrainerType("Full-time");
//        newTrainer.setMaLoaiTK("LT001");
//        newTrainer.setGender("Male");
//        LoaiLopHocDAO loaiLopHocDAO = new LoaiLopHocDAO();
//        LopHocDAO lopHocDAO = new LopHocDAO();
//
        TrainerDAO trainerDAO = new TrainerDAO();
        System.out.println(trainerDAO.getSoNgayNghi1Lop("TR0003","LOP0016"));
//        System.out.println(trainerDAO.searchTrainerByClassID("LOP0001"));
//        List<TrainerDTO> listTrainer = new ArrayList();
//        trainerDAO.updateTrainerStatus("TR0004", false);
//        System.out.println();
//        listTrainer = trainerDAO.readListTrainerWithRecord(1, 2);
////        System.out.println(trainerDAO.selectByUserName("traine"));
////        listTrainer = trainerDAO.readListTrainerByTypeAndStatus(loaiLopHocDAO.searchTenLoaiLopHoc(lopHocDAO.IDLoaiLopHoc("LOP0001")));
////        System.out.println(loaiLopHocDAO.searchTenLoaiLopHoc(lopHocDAO.IDLoaiLopHoc("LOP0003")));
////        System.out.println(lopHocDAO.IDLoaiLopHoc("LOP0003"));
//        for (TrainerDTO x : listTrainer) {
//            System.out.println(x.getTen());
//        }

    }
}
