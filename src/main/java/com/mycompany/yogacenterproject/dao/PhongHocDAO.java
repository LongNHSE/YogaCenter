/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dao;

import com.mycompany.yogacenterproject.dto.LopHocDTO;
import com.mycompany.yogacenterproject.dto.SlotDTO;
import com.mycompany.yogacenterproject.dto.PhongHocDTO;
import com.mycompany.yogacenterproject.dto.PhongTrong;
import com.mycompany.yogacenterproject.util.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Oalskad
 */
public class PhongHocDAO {

    public boolean updateRoom(String maRoom, boolean status) {
        String sql = " UPDATE [dbo].[Room]" + "SET"
                + "status = ?"
                + "WHERE maRoom = ?";
        int row = 0;
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBoolean(1, status);

            ps.setString(2, maRoom);
            row = ps.executeUpdate();

            ps.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return row > 0;
    }

    public List<PhongHocDTO> readRoom() {
        List<PhongHocDTO> listRoomDTO = new ArrayList<PhongHocDTO>();
        String sql = "SELECT * FROM [dbo].[room]";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PhongHocDTO roomDTO = new PhongHocDTO();
                roomDTO.setMaRoom(rs.getString(1));
                roomDTO.setStatus(rs.getBoolean(2));

                listRoomDTO.add(roomDTO);

                rs.close();
                ps.close();
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return listRoomDTO;

    }

    public PhongHocDTO searchByMaRoom(String maRoom) {
        String sql = "SELECT * FROM [dbo].[room] WHERE maRoom = ?";
        PhongHocDTO roomDTO = new PhongHocDTO();
        try {

            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maRoom);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                roomDTO.setMaRoom(rs.getString(1));
                roomDTO.setStatus(rs.getBoolean(2));

            }

            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e);
        }
        return roomDTO;
    }

    ////CHECK PHONG CO TRONG VAO SLOT DO KO TRA VE FALSE NEU PHONG TRONG
    public boolean checkRoomEmpty(String maRoom, String maSlot, String thu1, String thu2) {
        boolean check = true;
//        String sql = "SELECT lopHoc.maLopHoc, maSlot,lopHoc.maRoom, room.maRoom, timeTable.ngayHoc,timeTable.thu"
//                + "FROM ([dbo].[lopHoc] INNER JOIN [dbo].[room] On lopHoc.maRoom=room.maRoom)"
//                + "INNER JOIN [dbo].[timeTable] On lopHoc.maLopHoc=timeTable.maLopHoc"
//                + "WHERE lopHoc.maRoom =?  AND maSlot =? and thu=?";
        String sql = "SELECT lopHoc.maLopHoc, maSlot, lopHoc.maRoom, room.maRoom, ScheduleTemp.ngayHoc, ScheduleTemp.thu "
                + "FROM lopHoc "
                + "INNER JOIN room ON lopHoc.maRoom = room.maRoom "
                + "INNER JOIN ScheduleTemp ON lopHoc.maLopHoc = ScheduleTemp.maLopHoc "
                + "WHERE lopHoc.maRoom = ? AND maSlot = ? and thu=?";
        try {

            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maRoom);
            ps.setString(2, maSlot);
            ps.setString(3, thu1);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                return false;
            }

            ps.setString(1, maRoom);
            ps.setString(2, maSlot);
            ps.setString(3, thu2);
            rs = ps.executeQuery();
            if (!rs.next()) {
                return false;
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e);
        }

        return check;
    }
//RETURN PHONG HOC DANG TRONG 

    public PhongHocDTO getEmptyRoom(String maSlot, String thu1, String thu2) {
//        String sql = "SELECT room.maRoom\n"
//                + "    FROM room\n"
//                + "    WHERE room.maRoom NOT IN (\n"
//                + "    SELECT lopHoc.maRoom\n"
//                + "    FROM lopHoc\n"
//                + "    INNER JOIN ScheduleTemp ON lopHoc.maLopHoc = ScheduleTemp.maLopHoc\n"
//                + "    WHERE maSlot = ? AND (thu = ? or thu= ?))";

        String sql = "SELECT room.maRoom\n"
                + "FROM room\n"
                + "WHERE room.maRoom NOT IN (\n"
                + "    SELECT lopHoc.maRoom\n"
                + "    FROM lopHoc\n"
                + "    INNER JOIN ScheduleTemp ON lopHoc.maLopHoc = ScheduleTemp.maLopHoc\n"
                + "    WHERE maSlot = ? AND (thu = ? OR thu = ?)\n"
                + "	union\n"
                + "	SELECT lopHoc.maRoom\n"
                + "    FROM lopHoc\n"
                + "    INNER JOIN ScheduleTrainer ON lopHoc.maLopHoc = ScheduleTrainer.maLopHoc\n"
                + "	\n"
                + "    WHERE maSlot = ? AND (thu = ? OR thu = ?)\n"
                + "	\n"
                + ")";
        PhongHocDTO phongHocDTO = new PhongHocDTO();
        try {

            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maSlot);
            ps.setString(2, thu1);
            ps.setString(3, thu2);
            ps.setString(4, maSlot);
            ps.setString(5, thu1);
            ps.setString(6, thu2);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                phongHocDTO = new PhongHocDTO(rs.getString("maRoom"), true);
                return phongHocDTO;
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e);
        }
        return phongHocDTO;
    }
//RETURN PHONG HOC DANG TRONG 

    public PhongHocDTO getEmptyRoom2(String maSlot, String[] thuList) {
        StringBuilder thuParams = new StringBuilder();
        for (int i = 0; i < thuList.length; i++) {
            thuParams.append("?,");
        }
        thuParams.deleteCharAt(thuParams.length() - 1); // Remove the trailing comma

        String sql = "SELECT room.maRoom\n"
                + "FROM room\n"
                + "WHERE room.maRoom NOT IN (\n"
                + "    SELECT lopHoc.maRoom\n"
                + "    FROM lopHoc\n"
                + "    INNER JOIN ScheduleTemp ON lopHoc.maLopHoc = ScheduleTemp.maLopHoc\n"
                + "    WHERE maSlot = ? AND thu IN (" + thuParams + ")\n";

        if (thuList.length > 1) {
            sql += "    UNION\n"
                    + "    SELECT lopHoc.maRoom\n"
                    + "    FROM lopHoc\n"
                    + "    INNER JOIN ScheduleTrainer ON lopHoc.maLopHoc = ScheduleTrainer.maLopHoc\n"
                    + "    WHERE maSlot = ? AND thu IN (" + thuParams + ")\n";
        }

        sql += ")";

        PhongHocDTO phongHocDTO = new PhongHocDTO();
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            int index = 1;
            ps.setString(index++, maSlot);
            for (int i = 0; i < thuList.length; i++) {
                ps.setString(index++, thuList[i]);
            }

            if (thuList.length > 1) {
                ps.setString(index++, maSlot);
                for (int i = 0; i < thuList.length; i++) {
                    ps.setString(index++, thuList[i]);
                }
            }

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                phongHocDTO = new PhongHocDTO(rs.getString("maRoom"), true);
                return phongHocDTO;
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return phongHocDTO;
    }

    //
    public List<PhongHocDTO> getListEmptyRoom(String maSlot, String[] thuList) {
        StringBuilder thuParams = new StringBuilder();
        List<PhongHocDTO> listPhongHocDTO = new ArrayList<>();
        for (int i = 0; i < thuList.length; i++) {
            thuParams.append("?,");
        }
        thuParams.deleteCharAt(thuParams.length() - 1); // Remove the trailing comma

        String sql = "SELECT room.maRoom\n"
                + "FROM room\n"
                + "WHERE room.maRoom NOT IN (\n"
                + "    SELECT lopHoc.maRoom\n"
                + "    FROM lopHoc\n"
                + "    INNER JOIN ScheduleTemp ON lopHoc.maLopHoc = ScheduleTemp.maLopHoc\n"
                + "    WHERE maSlot = ? AND thu IN (" + thuParams + ")\n";

        if (thuList.length > 1) {
            sql += "    UNION\n"
                    + "    SELECT lopHoc.maRoom\n"
                    + "    FROM lopHoc\n"
                    + "    INNER JOIN ScheduleTrainer ON lopHoc.maLopHoc = ScheduleTrainer.maLopHoc\n"
                    + "    WHERE maSlot = ? AND thu IN (" + thuParams + ")\n";
        }

        sql += ")";

        PhongHocDTO phongHocDTO = new PhongHocDTO();
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            int index = 1;
            ps.setString(index++, maSlot);
            for (int i = 0; i < thuList.length; i++) {
                ps.setString(index++, thuList[i]);
            }

            if (thuList.length > 1) {
                ps.setString(index++, maSlot);
                for (int i = 0; i < thuList.length; i++) {
                    ps.setString(index++, thuList[i]);
                }
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                phongHocDTO = new PhongHocDTO(rs.getString("maRoom"), true);
                listPhongHocDTO.add(phongHocDTO);
            }

            rs.close();
            ps.close();
            conn.close();
            return listPhongHocDTO;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return listPhongHocDTO;
    }

    //RETURN SO PHONG TRONG
    public int soPhongTrong(String maSlot, String thu) throws SQLException {
//        String sql = "SELECT count(room.maRoom) as soPhongTrong\n"
//                + "FROM room\n"
//                + "WHERE room.maRoom NOT IN (\n"
//                + "    SELECT lopHoc.maRoom\n"
//                + "    FROM lopHoc\n"
//                + "    INNER JOIN ScheduleTemp ON lopHoc.maLopHoc = ScheduleTemp.maLopHoc\n"
//                + "    WHERE maSlot = ? AND thu = ? \n"
//                + ")";

        String sql = "SELECT COUNT(room.maRoom) AS soPhongTrong\n"
                + "FROM room\n"
                + "WHERE room.maRoom NOT IN (\n"
                + "    SELECT lopHoc.maRoom\n"
                + "    FROM lopHoc\n"
                + "    INNER JOIN ScheduleTrainer ON lopHoc.maLopHoc = ScheduleTrainer.maLopHoc\n"
                + "	\n"
                + "    WHERE ScheduleTrainer.maSlot = ? AND ScheduleTrainer.thu = ?\n"
                + "	union\n"
                + "	SELECT lopHoc.maRoom\n"
                + "    FROM lopHoc\n"
                + "    INNER JOIN ScheduleTemp ON lopHoc.maLopHoc = ScheduleTemp.maLopHoc\n"
                + "    WHERE ScheduleTemp.maSlot = ? AND ScheduleTemp.thu = ?\n"
                + ")";
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, maSlot);
        ps.setString(2, thu);
        ps.setString(3, maSlot);
        ps.setString(4, thu);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("soPhongTrong");
        }

        return 0;

    }

    public static void main(String[] args) throws SQLException {
        PhongHocDAO a = new PhongHocDAO();
        LopHocDAO lopHocDAO = new LopHocDAO();
        LopHocDTO lopHocDTO = new LopHocDTO();
        lopHocDTO = lopHocDAO.searchClassById("LOP0014");
//        Boolean b = a.checkRoomEmpty("RO0001", "SL001", "MONDAY", "WEDNESDAY");
//        System.out.println(a.getEmptyRoom("SL002", "TUESDAY", "WEDNESDAY").toString());
        String[] b = {"MONDAY2", "sWEDNESDAY"};
        
        System.out.println(a.getListEmptyRoom(lopHocDAO.maSlotClassUnassigned(lopHocDTO.getMaLopHoc()), lopHocDAO.showThuWithStringArrayOfClassUnassigned("LOP0014")));
//        System.out.println(lopHocDTO.getMaSlot());
//        System.out.println(lopHocDAO.showThuWithStringArray("LOP0001"));
//        System.out.println(a.getListEmptyRoom("SLOT0001", b));
    }
}
