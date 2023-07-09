/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dao;

import com.mycompany.yogacenterproject.dto.DayAndSlot;
import com.mycompany.yogacenterproject.dto.LopHocDTO;
import com.mycompany.yogacenterproject.dto.HocVienDTO;
import com.mycompany.yogacenterproject.dto.PhongHocDTO;
import com.mycompany.yogacenterproject.util.DBUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LopHocDAO {

///Hiện danh sách lớp Khong chay duco
    public List<LopHocDTO> readListClass() {
        List<LopHocDTO> listClass = new ArrayList<>();
        try {
            String sql = "SELECT * FROM lopHoc";
            PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String maLopHoc = rs.getString("maLopHoc");
//                int soLuongHV = rs.getInt("soLuongHV");
//                int soBuoi = rs.getInt("soBuoi");
//
//                String maLoaiLopHoc = rs.getString("maLoaiLopHoc");
//                String maRoom = rs.getString("maRoom");
//                Date ngay = rs.getDate("ngay");
//                LopHocDTO displayClass = new LopHocDTO(maLopHoc, soLuongHV, soBuoi, maTrainer, maLoaiLopHoc, maSlot, maRoom, ngay);
//                displayClass.setSoLuongHvHienTai(rs.getInt("soLuongHvHienTai"));
//                listClass.add(displayClass);
            }
            return listClass;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Lay tat ca cac ma lop hoc co status true
    public List<String> readListIDClass() {
        List<String> listClass = new ArrayList<>();
        try {
            String sql = "SELECT * FROM lopHoc where status ='true'";
            PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String maLopHoc = rs.getString("maLopHoc");
//                int soLuongHV = rs.getInt("soLuongHV");
//                int soBuoi = rs.getInt("soBuoi");
//
//                String maLoaiLopHoc = rs.getString("maLoaiLopHoc");
//                String maRoom = rs.getString("maRoom");
//                Date ngay = rs.getDate("ngay");
//                LopHocDTO displayClass = new LopHocDTO(maLopHoc, soLuongHV, soBuoi, maTrainer, maLoaiLopHoc, maSlot, maRoom, ngay);
//                displayClass.setSoLuongHvHienTai(rs.getInt("soLuongHvHienTai"));
                listClass.add(maLopHoc);
            }
            return listClass;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //AUTO CHECK CLASS AVAILABILITY BASED ON LAST DATE OF SCHEDULE
    public void CheckClass() {
        List<String> listMaLopHoc = readListIDClass();
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
                        updateClassStatus(maLopHoc, false);
                    }

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Date getLastDay(String maLopHoc) {

        try {
            String sql = "SELECT top 1 ngayHoc FROM [dbo].[ScheduleTrainer]\n"
                    + "where maLopHoc=?\n"
                    + "order by ngayHoc desc";

            PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
            stm.setString(1, maLopHoc);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getDate("ngayHoc");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateClassStatus(String maLopHoc, boolean status) {
        try {
            String sql = "UPDATE [dbo].[lopHoc] SET [status] = ? where maLopHoc= ? ";

            PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
            stm.setBoolean(1, status);
            stm.setString(2, maLopHoc);
            int rowsUpdated = stm.executeUpdate();
            System.out.println(rowsUpdated + " rows updated. Status set to false for past dates.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//LAY DANH SACH THEO RECORD

    public List<LopHocDTO> readListClassRecord(int pageID, int total) {
        List<LopHocDTO> listClass = new ArrayList<>();
        try {
            String sql = "SELECT lopHoc.maLopHoc,lopHoc.status, lopHoc.soLuongHV, lopHoc.soBuoi, lopHoc.maLoaiLopHoc, lopHoc.maRoom, lopHoc.soLuongHvHienTai, lopHoc.ngay, ScheduleTrainer.maTrainer, ScheduleTrainer.maSlot\n"
                    + "FROM [dbo].[lopHoc]\n"
                    + "inner join ScheduleTrainer on ScheduleTrainer.maLopHoc = lopHoc.maLopHoc\n"
                    + "group by lopHoc.maLopHoc,lopHoc.status, lopHoc.soLuongHV, lopHoc.soBuoi, lopHoc.maLoaiLopHoc, lopHoc.maRoom, lopHoc.soLuongHvHienTai, lopHoc.ngay, ScheduleTrainer.maTrainer, ScheduleTrainer.maSlot\n"
                    + "ORDER BY lopHoc.maLopHoc\n"
                    + "\n"
                    + "OFFSET (? - 1) ROWS\n"
                    + "FETCH NEXT ? ROWS ONLY;";

            PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
            stm.setInt(1, pageID);
            stm.setInt(2, total);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String maLopHoc = rs.getString("maLopHoc");
                int soLuongHV = rs.getInt("soLuongHV");
                int soBuoi = rs.getInt("soBuoi");

                String maLoaiLopHoc = rs.getString("maLoaiLopHoc");
                String maRoom = rs.getString("maRoom");
                int soLuongHvHienTai = rs.getInt("soLuongHvHienTai");

                Date ngay = rs.getDate("ngay");
                String maTrainer = rs.getString("maTrainer");
                String maSlot = rs.getString("maSlot");
                boolean status = rs.getBoolean("status");

                LopHocDTO lopHocDTO = new LopHocDTO();
                lopHocDTO.setStatus(status);
                lopHocDTO.setMaLoaiLopHoc(maLoaiLopHoc);
                lopHocDTO.setMaLopHoc(maLopHoc);
                lopHocDTO.setMaRoom(maRoom);
                lopHocDTO.setMaSlot(maSlot);
                lopHocDTO.setMaTrainer(maTrainer);
                lopHocDTO.setNgayBatDau(ngay);
                lopHocDTO.setSoBuoi(soBuoi);
                lopHocDTO.setSoLuongHV(soLuongHV);
                lopHocDTO.setSoLuongHvHienTai(soLuongHvHienTai);

//                LopHocDTO displayClass = new LopHocDTO(maLopHoc, soLuongHV, soBuoi, maTrainer, maLoaiLopHoc, maSlot, maRoom, ngay);
//                displayClass.setSoLuongHvHienTai(rs.getInt("soLuongHvHienTai"));
                listClass.add(lopHocDTO);
            }
            return listClass;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
////Search class by id

    public LopHocDTO searchClassById(String maLopHoc) {
        try {
            String sql = "SELECT * FROM lopHoc where maLopHoc=?";
            PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
            stm.setString(1, maLopHoc);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int soLuongHV = rs.getInt("soLuongHV");
                int soBuoi = rs.getInt("soBuoi");
                String maLoaiLopHoc = rs.getString("maLoaiLopHoc");
                int soLuongHvHienTai = rs.getInt("soLuongHvHienTai");

                String maRoom = rs.getString("maRoom");
                boolean status = rs.getBoolean("status");
                Date ngay = rs.getDate("ngay");
                LopHocDTO foundClass = new LopHocDTO();
                foundClass.setSoLuongHV(soLuongHV);
                foundClass.setSoLuongHvHienTai(soLuongHvHienTai);
                foundClass.setMaLoaiLopHoc(maLoaiLopHoc);
                foundClass.setMaLopHoc(maLopHoc);
                foundClass.setMaRoom(maRoom);
                foundClass.setSoBuoi(soBuoi);
                foundClass.setNgayBatDau(ngay);
                foundClass.setStatus(status);

                return foundClass;
            }
        } catch (SQLException e) {
            Logger.getLogger(LopHocDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

//////Insert a class
    public void addClass(LopHocDTO newClass) {
        try {
            String sql = "insert into lopHoc(maLopHoc,soLuongHV,soBuoi,maLoaiLopHoc,maRoom,ngay,soLuongHvHienTai,[status])"
                    + "values(?,?,?,?,?,?,?,?)";
            PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
            stm.setString(1, newClass.getMaLopHoc());
            stm.setInt(2, newClass.getSoLuongHV());
            stm.setInt(3, newClass.getSoBuoi());
            stm.setString(4, newClass.getMaLoaiLopHoc());

            stm.setString(5, newClass.getMaRoom());
            stm.setDate(6, newClass.getNgayBatDau());
            stm.setInt(7, 0);
            stm.setBoolean(8, true);
            stm.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(LopHocDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

//TAO LOP HOC DUA TREN SLOT VA CHECK XEM SLOT DO PHONG CO TRONG KHONG
    public void addClassBasedOnRoom(LopHocDTO newClass) {
        try {
            String sql = "insert into lopHoc(maLopHoc,soLuongHV,soBuoi,maTrainer,maLoaiLopHoc,maSlot,maRoom,ngay,status)"
                    + "values(?,?,?,?,?,?,?,?,?)";
            PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
            stm.setString(1, newClass.getMaLopHoc());
            stm.setInt(2, newClass.getSoLuongHV());
            stm.setInt(3, newClass.getSoBuoi());
            stm.setString(4, newClass.getMaTrainer());
            stm.setString(5, newClass.getMaLoaiLopHoc());
            stm.setString(6, newClass.getMaSlot());
            stm.setString(7, newClass.getMaRoom());
            stm.setDate(8, newClass.getNgayBatDau());
            stm.setBoolean(9, newClass.isStatus());
        } catch (SQLException e) {
            Logger.getLogger(LopHocDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

/////Update Class (1 lớp có thể thay đổi giáo viên và mã phòng nhưng không thể thay đổi ngày, số buổi, loại lớp học)
    public void updateClass(LopHocDTO upClass) {
        try {
            String sql = "UPDATE lopHoc set soLuongHV=?,maRoom=? "
                    + "where maLopHoc=?\n"
                    + "UPDATE ScheduleTrainer set maTrainer =?\n"
                    + "where maLopHoc=?";
            PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
            stm.setInt(1, upClass.getSoLuongHV());
            stm.setString(2, upClass.getMaRoom());
            stm.setString(3, upClass.getMaLopHoc());
            stm.setString(4, upClass.getMaTrainer());
            stm.setString(5, upClass.getMaLopHoc());
            stm.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(LopHocDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

///Delete Class by id
    public void deleteClassById(String maLopHoc) {
        try {
            String sql = "DELETE FROM lopHoc where maLopHoc =?";
            PreparedStatement stm;
            stm = DBUtils.getConnection().prepareStatement(sql);
            stm.setString(1, maLopHoc);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LopHocDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//LAY ID LOAI LOP HOC 

    public String IDLoaiLopHoc(String maLopHoc) {
        try {
            String sql = "SELECT maLoaiLopHoc from [dbo].[lopHoc] where maLopHoc = ?";
            PreparedStatement stm;
            stm = DBUtils.getConnection().prepareStatement(sql);
            stm.setString(1, maLopHoc);
            ResultSet rs;
            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getString("maLoaiLopHoc");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LopHocDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

    //LAY TEN LOAI LOP HOC 
    public String tenLoaiLopHoc(String maLopHoc) {
        try {
            String sql = "SELECT tenLoaiLopHoc from [dbo].[lopHoc] where maLopHoc = ?";
            PreparedStatement stm;
            stm = DBUtils.getConnection().prepareStatement(sql);
            stm.setString(1, maLopHoc);
            ResultSet rs;
            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getString("tenLoaiLopHoc");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LopHocDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }
//LAY LOAI LOP BEN BAN LOAILOPHOC

    public String tenLopHoc(String maLoaiLopHoc) {
        try {
            String sql = "SELECT tenLoaiLopHoc from [dbo].[loaiLopHoc] where maLoaiLopHoc = ?";
            PreparedStatement stm;
            stm = DBUtils.getConnection().prepareStatement(sql);
            stm.setString(1, maLoaiLopHoc);
            ResultSet rs;
            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getString("tenLoaiLopHoc");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LopHocDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

    public List<String> showThu(String maLopHoc) throws SQLException {
        String sql = "select thu from [dbo].[ScheduleTrainer]\n"
                + "where maLopHoc = ?\n"
                + "group by thu";

        List<String> list = new ArrayList<>();
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maLopHoc);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("thu"));
            }
//            System.out.println(list);
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public String[] showThuWithStringArray(String maLopHoc) throws SQLException {
        String sql = "select thu from [dbo].[ScheduleTrainer]\n"
                + "where maLopHoc = ?\n"
                + "group by thu";

        List<String> list = new ArrayList<>();

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maLopHoc);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("thu"));
            }
            String[] stringArray = list.toArray(new String[0]);
            System.out.println(list);
            return stringArray;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public String[] showThuWithStringArrayOfClassUnassigned(String maLopHoc) throws SQLException {
        String sql = "select thu from [dbo].[ScheduleTemp]\n"
                + "where maLopHoc = ?\n"
                + "group by thu";

        List<String> list = new ArrayList<>();

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maLopHoc);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("thu"));
            }
            String[] stringArray = list.toArray(new String[0]);
            System.out.println(list);
            return stringArray;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<LopHocDTO> showClassesByType(String maLoaiLopHoc) {
        String sql = "Select ScheduleTrainer.maSlot,maTrainer,ScheduleTrainer.maLopHoc,"
                + "CAST(slot.timeStart AS VARCHAR(5)) AS timeStart ,CAST(slot.timeEnd AS VARCHAR(5)) AS timeEnd   from lopHoc\n"
                + "inner join ScheduleTrainer on ScheduleTrainer.maLopHoc = lopHoc.maLopHoc\n"
                + "inner join slot on slot.maSlot = ScheduleTrainer.maSlot\n"
                + "Where maLoaiLopHoc = ?\n"
                + "group by ScheduleTrainer.maSlot,maTrainer,ScheduleTrainer.maLopHoc,slot.timeStart,slot.timeEnd\n"
                + "ORDER BY ScheduleTrainer.maLopHoc";

        try {
            Connection conn = DBUtils.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maLoaiLopHoc);
            ResultSet rs = ps.executeQuery();
            ResultSet temp;
            String tempString;
            List<String> list = new ArrayList<>();
            List<LopHocDTO> listLopHocDTO = new ArrayList();
            while (rs.next()) {
                List<String> listThu = new ArrayList();
                LopHocDTO lopHocDTO = new LopHocDTO();
                lopHocDTO.setMaSlot(rs.getString("maSlot"));
                lopHocDTO.setMaTrainer(rs.getString("maTrainer"));
                lopHocDTO.setMaLopHoc(rs.getString("maLopHoc"));
                lopHocDTO.setTimeStart(rs.getString("timeStart"));
                lopHocDTO.setTimeEnd(rs.getString("timeEnd"));

                list = showThu(lopHocDTO.getMaLopHoc());
                lopHocDTO.setThuList(list);

                listLopHocDTO.add(lopHocDTO);
            }
//            System.out.println(listLopHocDTO);
            return listLopHocDTO;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    //GET SLOT CLASS COMPLETE BUT THE RETURN IS LIST<LOPHOCDTO>
    public List<LopHocDTO> showSlotByClassType(String maLoaiLopHoc) {
        String sql = "Select ScheduleTrainer.maSlot,\n"
                + "CAST(slot.timeStart AS VARCHAR(5)) AS timeStart ,CAST(slot.timeEnd AS VARCHAR(5)) AS timeEnd   from lopHoc\n"
                + "inner join ScheduleTrainer on ScheduleTrainer.maLopHoc = lopHoc.maLopHoc\n"
                + "inner join slot on slot.maSlot = ScheduleTrainer.maSlot\n"
                + "Where maLoaiLopHoc = ?\n"
                + "group by ScheduleTrainer.maSlot,slot.timeStart,slot.timeEnd\n"
                + "ORDER BY ScheduleTrainer.maSlot";

        try {
            Connection conn = DBUtils.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maLoaiLopHoc);
            ResultSet rs = ps.executeQuery();
            ResultSet temp;
            String tempString;
            List<String> list = new ArrayList<>();
            List<LopHocDTO> listLopHocDTO = new ArrayList();
            while (rs.next()) {
                List<String> listThu = new ArrayList();
                LopHocDTO lopHocDTO = new LopHocDTO();
                lopHocDTO.setMaSlot(rs.getString("maSlot"));
                lopHocDTO.setTimeStart(rs.getString("timeStart"));
                lopHocDTO.setTimeEnd(rs.getString("timeEnd"));

                list = showThu(lopHocDTO.getMaLopHoc());
                lopHocDTO.setThuList(list);

                listLopHocDTO.add(lopHocDTO);
            }
            System.out.println(listLopHocDTO);
            return listLopHocDTO;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    //LAY ID CUOI LIST
    public int lastIDIndex() {
        String sql = "SELECT TOP 1 maLopHoc FROM [dbo].[lopHoc] ORDER BY maLopHoc DESC";
        int index = 0;
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                int numberOnly = Integer.parseInt(rs.getString("maLopHoc").replaceAll("[^0-9]", ""));
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

    //LAY LIST LOP UNASSIGNED
    public List<LopHocDTO> listLopTemp() {
        List<LopHocDTO> listLopHoc = new ArrayList();
        String sql = "SELECT lopHoc.maLopHoc, lopHoc.maLoaiLopHoc, lopHoc.soLuongHV, lopHoc.maRoom, ScheduleTemp.maSlot, lopHoc.ngay,lopHoc.soLuongHvHienTai\n"
                + "FROM lopHoc\n"
                + "INNER JOIN ScheduleTemp ON lopHoc.maLopHoc = ScheduleTemp.maLopHoc\n"
                + "WHERE lopHoc.maLopHoc NOT IN (SELECT maLopHoc FROM ScheduleTrainer)\n"
                + "GROUP BY lopHoc.maLopHoc, lopHoc.maLoaiLopHoc, lopHoc.soLuongHV, lopHoc.maRoom, ScheduleTemp.maSlot, lopHoc.ngay,lopHoc.soLuongHvHienTai";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LopHocDTO lopHocDTO = new LopHocDTO();
                lopHocDTO.setMaLopHoc(rs.getString("maLopHoc"));
                lopHocDTO.setMaLoaiLopHoc(rs.getString("maLoaiLopHoc"));
                lopHocDTO.setMaRoom(rs.getString("maRoom"));
                lopHocDTO.setMaSlot(rs.getString("maSlot"));
                lopHocDTO.setNgayBatDau(rs.getDate("ngay"));
                lopHocDTO.setSoLuongHV(rs.getInt("soLuongHV"));
                lopHocDTO.setSoLuongHvHienTai(rs.getInt("soLuongHvHienTai"));
                listLopHoc.add(lopHocDTO);

            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return listLopHoc;
    }

    //DELETE CLASS UNASSIGN
    public void deleteClassUnassign(String maLopHoc) {
        String sql = "BEGIN TRANSACTION; "
                + "DELETE FROM ScheduleTemp WHERE maLopHoc = ?; "
                + "DELETE FROM lopHoc WHERE maLopHoc = ?; "
                + "COMMIT;";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maLopHoc);
            ps.setString(2, maLopHoc);
            ps.executeUpdate();
        } catch (SQLException e) {

        }

    }

    public void increase(String maLopHoc) {
        String sql = "update [dbo].[lopHoc]\n"
                + "set soLuongHvHienTai = soLuongHvHienTai + 1\n"
                + "where maLopHoc = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maLopHoc);
            ps.executeUpdate();
        } catch (SQLException e) {

        }
    }

    //LAY COUNT SO LUONG LOP HOC TORNG LoPHOC INNER JOIN SCHEDULETRAIN
    public int countRecord() {
        int count = 0;
        String sql = "SELECT COUNT(DISTINCT lopHoc.maLopHoc) AS count\n"
                + "FROM [dbo].[lopHoc]\n"
                + "INNER JOIN ScheduleTrainer ON ScheduleTrainer.maLopHoc = lopHoc.maLopHoc;";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count");
            }

            return count;
        } catch (SQLException e) {

        }
        return count;
    }

    //GET LOP HOC WITH MASLOT, MALOAILOPHOC, LIST<String>Thu
    public String searchForPayment(String maSlot, String maLoaiLopHoc, List<String> thuList) {
        try {

            String sql = "SELECT lopHoc.maLopHoc\n"
                    + "FROM lopHoc\n"
                    + "INNER JOIN ScheduleTrainer ON lopHoc.maLopHoc = ScheduleTrainer.maLopHoc\n"
                    + "WHERE maSlot = ?\n"
                    + "  AND maLoaiLopHoc = ?\n"
                    + "  AND thu IN (";
            for (int i = 0; i < thuList.size(); i++) {
                sql += "?";
                if (i < thuList.size() - 1) {
                    sql += ",";
                }
            }
            sql += ") AND lopHoc.soLuongHvHienTai < lopHoc.soLuongHV AND lopHoc.[status] ='true'\n"
                    + "  \n"
                    + "GROUP BY lopHoc.maLopHoc, lopHoc.soLuongHvHienTai\n"
                    + "HAVING COUNT(DISTINCT thu) = ? "
                    + "\n"
                    + "ORDER BY lopHoc.soLuongHvHienTai DESC;";
            PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
            stm.setString(1, maSlot);
            stm.setString(2, maLoaiLopHoc);
            int count = 0;
            for (int i = 0; i < thuList.size(); i++) {
                stm.setString(i + 3, thuList.get(i));
                System.out.println(thuList.get(i));
                count = i + 4;
            }
            stm.setInt(count, thuList.size());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String maLopHoc = rs.getString("maLopHoc");
                LopHocDTO lopHocDTO = searchClassById(maLopHoc);
                if (compareLists(thuList, showThu(maLopHoc))) {
                    return maLopHoc;
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(LopHocDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    //LAY MA SLOT TU CLASS DA HOAN THIEN
    public String maSlotClass(String maLopHoc) {
        String maSlot = null;
        String sql = "SELECT maSlot \n"
                + "FROM [dbo].[ScheduleTrainer]\n"
                + "where maLopHoc = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maLopHoc);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                maSlot = rs.getString("maSlot");
            }

            return maSlot;
        } catch (SQLException e) {

        }
        return maSlot;
    }

    //LAY MA SLOT TU CLASS UNASSIGNED 
    public String maSlotClassUnassigned(String maLopHoc) {
        String maSlot = null;
        String sql = "SELECT maSlot \n"
                + "FROM [dbo].[ScheduleTemp]\n"
                + "where maLopHoc = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maLopHoc);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                maSlot = rs.getString("maSlot");
            }

            return maSlot;
        } catch (SQLException e) {

        }
        return maSlot;
    }

    public static boolean compareLists(List<String> list1, List<String> list2) {
        // Check if the lists have different sizes
        if (list1.size() != list2.size()) {
            return false;
        }

        // Compare elements at corresponding indices
        for (int i = 0; i < list1.size(); i++) {
            String element1 = list1.get(i);
            String element2 = list2.get(i);

            if (!element1.equals(element2)) {
                return false;
            }
        }

        // Lists are equal
        return true;
    }

    public static void main(String[] args) throws SQLException {

        LopHocDAO a = new LopHocDAO();

        a.CheckClass();

//        String[] b = a.showThuWithStringArrayOfClassUnassigned("LOP0014");
//        System.out.println(b);
//        System.out.println(a.searchClassById("LOP0001"));
//        a.deleteClassUnassign("LOP0007");
//        System.out.println(a.lastIDIndex());
//        System.out.println(Math.ceil((a.countRecord() + 5 - 1) / 5));
//        double dividend = 9.0;
//        double divisor = 5.0;
//        double result = Math.ceil(dividend / divisor);
////        System.out.println(result);
        List<LopHocDTO> listLopHocTemp = a.showClassesByType("TYPE0001");
//
        // Split the selected value to retrieve maSlot and thuList
        String selectedMaSlot = "SL001";
        String selectedThuList = "[ MONDAY, WEDNESDAY]";

        // Remove the square brackets and spaces from the string
        String cleanedValue = selectedThuList.replaceAll("[\\[\\]\\s]", "");

// Split the cleaned value into individual elements
        String[] elements = cleanedValue.split(",");

// Convert the array to a List<String>
        String maLoaiLopHoc = "TYPE0001";
        String maSlot = selectedMaSlot;
        List<String> thuList = new ArrayList<>(Arrays.asList(elements));

        LopHocDAO lopHocDAO = new LopHocDAO();
//        System.out.print(lopHocDAO.searchForPayment(maSlot, maLoaiLopHoc, thuList));
        System.out.println(lopHocDAO.searchClassById("LOP0001"));
////////        Date aa = Date.valueOf(LocalDate.now());
//////        LopHocDTO lopHocDTO = new LopHocDTO();
//////        lopHocDTO.setMaLoaiLopHoc("TYPE0001");
//////        lopHocDTO.setMaLopHoc("TYPE0001");
//////        lopHocDTO.setMaRoom("RO0001");
//////        lopHocDTO.setNgayBatDau(aa);
////
//////        a.addClass(lopHocDTO);
//        System.out.println(listLopHocTemp.get(0).getThuList().equals(listLopHocTemp.get(1).getThuList()));
//        System.out.println(listLopHocTemp.get(0).getThuList());
//        System.out.println(listLopHocTemp.get(1).getThuList());

//        for (int i = 0; i < listLopHocTemp.size(); i++) {
//            List<String> currentThuList = listLopHocTemp.get(i).getThuList();
//            String currentSlot = listLopHocTemp.get(i).getMaSlot();
//            boolean isDuplicate = false;
//            for (String existingSlot : listSlot) {
//                if (existingSlot.equals(currentSlot)) {
//                    for (List<String> existingThuList : thulist) {
//                        if (compareLists(existingThuList, currentThuList)) {
//                            isDuplicate = true;
//                            break;
//                        }
//                    }
//                }
//            }
//            if (!isDuplicate) {
//                thulist.add(currentThuList);
//                listSlot.add(currentSlot);
//
//                dayAndSlot.setSlot(currentSlot);
//                listDayAndSlot.add(dayAndSlot);
//            }
////        }
//        LopHocDAO lopHocDAO = new LopHocDAO();
//        System.out.println(listLopHocTemp);
//        for (int i = 0; i < listLopHocTemp.size(); i++) {
//            boolean isDuplicate = false;
//            for (int j = 0; j < i; j++) {
//                if (!compareLists(listLopHocTemp.get(i).getThuList(), listLopHocTemp.get(j).getThuList())
//                        ) {
//                    isDuplicate = true;
//                    break;
//                }
//            }
//
//            if (!isDuplicate) {
//                System.out.println(listLopHocTemp.get(i).getThuList());
//            }
//        }
//        List<DayAndSlot> listDayAndSlot = new ArrayList<>();
//        for (int i = 0; i < listLopHocTemp.size(); i++) {
//            DayAndSlot dayAndSlot = new DayAndSlot();
//            String currentSlot = listLopHocTemp.get(i).getMaSlot();
//            List<String> thu = listLopHocTemp.get(i).getThuList();
//            if (i != 0) {
//                for (int j = 1; j < i; j++) {
//                    if (currentSlot.equals(listLopHocTemp.get(j).getMaSlot())) {
//                        if (compareLists(listLopHocTemp.get(i).getThuList(), listLopHocTemp.get(j).getThuList())) {
////                            System.out.println(currentSlot + thu);
//                        }
//                    } else {
//
//                        dayAndSlot.setSlot(currentSlot);
//
//                        dayAndSlot.setDay(thu);
//                        dayAndSlot.setTimeStart(listLopHocTemp.get(i).getTimeStart());
//                        dayAndSlot.setTimeEnd(listLopHocTemp.get(i).getTimeEnd());
//                        listDayAndSlot.add(dayAndSlot);
//                    }
//                }
//            } else {
//
//                dayAndSlot.setSlot(currentSlot);
//                dayAndSlot.setTimeStart(listLopHocTemp.get(i).getTimeStart());
//                dayAndSlot.setTimeEnd(listLopHocTemp.get(i).getTimeEnd());
//                dayAndSlot.setDay(thu);
//                listDayAndSlot.add(dayAndSlot);
//            }
//
//        }
//        Set<DayAndSlot> uniqueDayAndSlots = new HashSet<>(listDayAndSlot);
//        List<DayAndSlot> distinctDayAndSlots = new ArrayList<>(uniqueDayAndSlots);
//        for (DayAndSlot x : distinctDayAndSlots) {
//            System.out.println(x);
//        }
//        List<List<String>> thulist = new ArrayList<>();
//        for (int i = 0; i < listLopHocTemp.size(); i++) {
//
//            if (i != 0) {
//                for (List<String> xx : thulist) {
//                    if (!compareLists(xx, listLopHocTemp.get(i).getThuList())) {
//                        thulist.add(listLopHocTemp.get(i).getThuList());
//                    }
//
//                }
//            } else {
//                thulist.add(listLopHocTemp.get(i).getThuList());
//            }
//        }
//        for (DayAndSlot x : listDayAndSlot) {
//            System.out.println(x);
//        }
//        for (LopHocDTO x : listLopHocTemp) {
//            System.out.println(x.getThuList());
//            System.out.println(x.getMaSlot());
//        }
//        List<String> stringList = new ArrayList<>();
//        stringList.add("Hello");
//        stringList.add("World");
//        stringList.add("OpenAI");
//        double result = Math.ceil(dividend / divisor);
//        System.out.println(result);
//        List<LopHocDTO> listLopHocTemp = a.readListClassRecord(6, 5);
////        Date aa = Date.valueOf(LocalDate.now());
////        LopHocDTO lopHocDTO = new LopHocDTO();
////        lopHocDTO.setMaLoaiLopHoc("TYPE0001");
////        lopHocDTO.setMaLopHoc("TYPE0001");
////        lopHocDTO.setMaRoom("RO0001");
////        lopHocDTO.setNgayBatDau(aa);
//
//        String[] stringArray = stringList.toArray(new String[0]);
//
//// Print the elements of the string array
//        for (String str : stringArray) {
//            System.out.print(str);
//            System.out.println(stringArray.length);
//        }
//        System.out.println(a.searchClassById("LOP0003"));
//        List<LopHocDTO> list = a.searchByType("TYPE0001");
//        System.out.println(list);
    }
}
