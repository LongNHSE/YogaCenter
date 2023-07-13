/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dao;

import com.mycompany.yogacenterproject.dto.DateAndDay;
import com.mycompany.yogacenterproject.dto.LopHocDTO;
import com.mycompany.yogacenterproject.dto.ScheduleHvDTO;
import com.mycompany.yogacenterproject.dto.ScheduleTempDTO;
import com.mycompany.yogacenterproject.dto.ScheduleTrainerDTO;
import com.mycompany.yogacenterproject.util.DBUtils;
import com.mycompany.yogacenterproject.util.DateUtils;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oalskad
 */
public class ScheduleDAO {

    public List<ScheduleHvDTO> readScheduleHvDTO() throws SQLException {
        List<ScheduleHvDTO> listScheduleHv = new ArrayList<>();
        String sql = "SELECT * FROM [dbo].[ScheduleHV]";
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);

        try {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ScheduleHvDTO scheduleHvDTO = new ScheduleHvDTO();
                scheduleHvDTO.setMaHV(rs.getString("maHV"));
                scheduleHvDTO.setMaLopHoc(rs.getString("maLopHoc"));
                scheduleHvDTO.setMaSlot(rs.getString("maSlot"));
                scheduleHvDTO.setNgayHoc(rs.getDate("ngayHoc"));
                scheduleHvDTO.setThu(rs.getString("thu"));
                scheduleHvDTO.setStatus(rs.getBoolean("status"));
                listScheduleHv.add(scheduleHvDTO);

            }
        } catch (SQLException e) {
        }

        return listScheduleHv;
    }

    public List<ScheduleHvDTO> readScheduleHvDTO(String maHv) throws SQLException {
        List<ScheduleHvDTO> listScheduleHv = new ArrayList<>();
        String sql = "SELECT * FROM [dbo].[ScheduleHV] where maHV=?";
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);

        try {
            ps.setString(1, maHv);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ScheduleHvDTO scheduleHvDTO = new ScheduleHvDTO();
                scheduleHvDTO.setMaHV(rs.getString("maHV"));
                scheduleHvDTO.setMaLopHoc(rs.getString("maLopHoc"));
                scheduleHvDTO.setMaSlot(rs.getString("maSlot"));
                scheduleHvDTO.setNgayHoc(rs.getDate("ngayHoc"));
                scheduleHvDTO.setThu(rs.getString("thu"));
                scheduleHvDTO.setStatus(rs.getBoolean("status"));
                listScheduleHv.add(scheduleHvDTO);

            }
        } catch (SQLException e) {
        }

        return listScheduleHv;
    }

    //LIST SCHEDULE DA ASSIGN, CUA TRAINER
    public List<ScheduleTrainerDTO> readScheduleTrainer() throws SQLException {
        List<ScheduleTrainerDTO> listScheduleTrainer = new ArrayList<>();
        String sql = "SELECT * FROM [dbo].[ScheduleTrainer] ";
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);

        try {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                ScheduleTrainerDTO scheduleTrainerDTO = new ScheduleTrainerDTO();
                scheduleTrainerDTO.setMaTrainer(rs.getString("maTrainer"));
                scheduleTrainerDTO.setMaLopHoc(rs.getString("maLopHoc"));
                scheduleTrainerDTO.setMaSlot(rs.getString("maSlot"));
                scheduleTrainerDTO.setNgayHoc(rs.getDate("ngayHoc"));
                scheduleTrainerDTO.setThu(rs.getString("thu"));
                scheduleTrainerDTO.setStatus(rs.getBoolean("status"));
                listScheduleTrainer.add(scheduleTrainerDTO);

            }
        } catch (SQLException e) {
        }

        return listScheduleTrainer;
    }

    public List<ScheduleTrainerDTO> readScheduleTrainerWithType(String maLoaiLopHoc) throws SQLException {
        List<ScheduleTrainerDTO> listScheduleTrainer = new ArrayList<>();
        String sql = "select * from ScheduleTrainer inner join lopHoc on lopHoc.maLopHoc=ScheduleTrainer.maLopHoc\n"
                + "where maLoaiLopHoc =?";
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, maLoaiLopHoc);

        try {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                ScheduleTrainerDTO scheduleTrainerDTO = new ScheduleTrainerDTO();
                scheduleTrainerDTO.setMaTrainer(rs.getString("maTrainer"));
                scheduleTrainerDTO.setMaLopHoc(rs.getString("maLopHoc"));
                scheduleTrainerDTO.setMaSlot(rs.getString("maSlot"));
                scheduleTrainerDTO.setNgayHoc(rs.getDate("ngayHoc"));
                scheduleTrainerDTO.setThu(rs.getString("thu"));
                scheduleTrainerDTO.setStatus(rs.getBoolean("status"));
                listScheduleTrainer.add(scheduleTrainerDTO);

            }
        } catch (SQLException e) {
        }

        return listScheduleTrainer;
    }

    public List<ScheduleTrainerDTO> readScheduleTrainer(String maTrainer) throws SQLException {
        List<ScheduleTrainerDTO> listScheduleTrainer = new ArrayList<>();
        String sql = "SELECT * FROM [dbo].[ScheduleTrainer] where maTrainer =? ";
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);

        try {
            ps.setString(1, maTrainer);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                ScheduleTrainerDTO scheduleTrainerDTO = new ScheduleTrainerDTO();
                scheduleTrainerDTO.setMaTrainer(rs.getString("maTrainer"));
                scheduleTrainerDTO.setMaLopHoc(rs.getString("maLopHoc"));
                scheduleTrainerDTO.setMaSlot(rs.getString("maSlot"));
                scheduleTrainerDTO.setNgayHoc(rs.getDate("ngayHoc"));
                scheduleTrainerDTO.setThu(rs.getString("thu"));
                scheduleTrainerDTO.setStatus(rs.getBoolean("status"));
                listScheduleTrainer.add(scheduleTrainerDTO);

            }
        } catch (SQLException e) {
        }

        return listScheduleTrainer;
    }

    //LIST SCHEDULE CHAU ASSIGN TRAINER
    public List<ScheduleTempDTO> readScheduleTemp() throws SQLException {
        List<ScheduleTempDTO> listScheduleTemp = new ArrayList<>();
        String sql = "SELECT * FROM [dbo].[ScheduleTemp] ";
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);

        try {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                ScheduleTempDTO scheduleTempDTO = new ScheduleTempDTO();
                scheduleTempDTO.setMaLopHoc(rs.getString("maLopHoc"));
                scheduleTempDTO.setMaSlot(rs.getString("maSlot"));
                scheduleTempDTO.setNgayHoc(rs.getDate("ngayHoc"));
                scheduleTempDTO.setThu(rs.getString("thu"));
                scheduleTempDTO.setStatus(rs.getBoolean("status"));
                listScheduleTemp.add(scheduleTempDTO);

            }
        } catch (SQLException e) {
        }

        return listScheduleTemp;
    }

    //TAO SCHEDULEHV voi THAM SO MALOPHOC, MAHV, MASLOT
    public boolean createScheduleHV(String maHV, String maLopHoc) throws SQLException {
        String sql = "INSERT INTO ScheduleHV([maHV], [maLopHoc], [ngayHoc], [maSlot], [thu],[status])\n"
                + "               SELECT ?, [maLopHoc], [ngayHoc], [maSlot], [thu],[status]\n"
                + "               FROM [YogaCenter].[dbo].[ScheduleTrainer]\n"
                + "               WHERE ScheduleTrainer.maLopHoc = ?";
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, maHV);
        ps.setString(2, maLopHoc);

        ps.executeUpdate();

        return false;
    }

    //Parse du lieu tu ScheduleTemp sang Schedule Trainer sau khi Trainer assign hoac duoc assign 
    public boolean createScheduleTrainer(String maTrainer, LopHocDTO lopHocDTO) throws SQLException {
        String sql = "INSERT INTO ScheduleTrainer([maTrainer], [maLopHoc], [ngayHoc], [maSlot], [thu],[status])\n"
                + "SELECT ?, [maLopHoc], [ngayHoc], [maSlot], [thu],[status] \n"
                + "FROM [YogaCenter].[dbo].[ScheduleTemp]\n"
                + "WHERE ScheduleTemp.maLopHoc=? ;";
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, maTrainer);
        ps.setString(2, lopHocDTO.getMaLopHoc());

        ps.executeUpdate();

        return false;
    }

    //DELETE SCHEDULE TEMP AFTER ASIGN
    public boolean deleteScheduleTemp(String maLopHoc) throws SQLException {
        String sql = "DELETE FROM [dbo].[ScheduleTemp] where maLopHoc = ?";
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, maLopHoc);

        ps.executeUpdate();

        return false;
    }

    //DELETE SCHEDULE TRAINER (OR SCHEDULE OF COMPLETE CLASS)
    public void deleteScheduleTrainer(String maLopHoc) throws SQLException {
        String sql = "DELETE FROM [dbo].[ScheduleTrainer] where maLopHoc = ?";
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, maLopHoc);

        ps.executeUpdate();

    }

    public void deleteScheduleHV(String maLopHoc) throws SQLException {
        String sql = "DELETE FROM [dbo].[ScheduleHV] where maLopHoc = ?";
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, maLopHoc);

        ps.executeUpdate();

    }

    public void deleteScheduleHVWithMaLopHoc(String maLopHoc, String maHocVien) throws SQLException {
        String sql = "DELETE FROM [dbo].[ScheduleHV] where maLopHoc = ? and maHV =?";
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, maLopHoc);
        ps.setString(2, maHocVien);
        ps.executeUpdate();

    }

    public boolean createScheduleTemp(LopHocDTO lopHocDTO) throws SQLException {
        String sql = "INSERT INTO [dbo].[ScheduleTemp](maLopHoc, ngayHoc,maSlot, thu,[status]) "
                + "VALUES(?,?,?,?,?)";
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);

        List<DateAndDay> listDate = new ArrayList();

        listDate = listDateAndDay(lopHocDTO.getThu(), lopHocDTO.getNgayBatDau(), lopHocDTO.getSoBuoi());

        for (DateAndDay x : listDate) {

            ps.setString(1, lopHocDTO.getMaLopHoc());
            ps.setString(2, x.getDate());
            ps.setString(3, lopHocDTO.getMaSlot());
            ps.setString(4, x.getDay());
            ps.setBoolean(5, true);
            ps.executeUpdate();

        }

        return false;
    }

    public List<DateAndDay> listDateAndDay(String[] thu, Date initDate, int slot) {
        DateTimeFormatter df = new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("dd-MMM-yyyy").toFormatter(Locale.ENGLISH);

        //DOI DATE THANH LOCALDATE
        LocalDate localDate;
        if (initDate != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(initDate);
            localDate = LocalDate.of(calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH) + 1,
                    calendar.get(Calendar.DAY_OF_MONTH));
        } else {
            // Handle the case when date is null
            localDate = null;
        }

        LocalDate startDate = localDate;
//        LocalDate endDate = startDate.plusWeeks(weeksInterval); // Calculate the end date

        LocalDate date = startDate;
        List<DateAndDay> listDate = new ArrayList();
        while (listDate.size() < slot) {

            for (int a = 0; a < thu.length; a++) {
                if (!date.isAfter(date.with(TemporalAdjusters.nextOrSame(DayOfWeek.valueOf(thu[a].toUpperCase()))))) {
                    LocalDate date1 = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.valueOf(thu[a].toUpperCase())));
                    DateAndDay DateofA = new DateAndDay(date1.getDayOfWeek().toString(), date1.toString());
                    listDate.add(DateofA);
                    if (listDate.size() >= slot) {
                        break;
                    }
                } else {
                    LocalDate date1 = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.valueOf(thu[a].toUpperCase())));

                }
            }
            date = date.plusWeeks(1); // Move to the next week

        }

        return listDate;

    }

    public List<Date> listDate(String[] thu, Date initDate, int slot) {
        DateTimeFormatter df = new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("dd-MMM-yyyy").toFormatter(Locale.ENGLISH);

        //DOI DATE THANH LOCALDATE
        LocalDate localDate;
        if (initDate != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(initDate);
            localDate = LocalDate.of(calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH) + 1,
                    calendar.get(Calendar.DAY_OF_MONTH));
        } else {
            // Handle the case when date is null
            localDate = null;
        }

        LocalDate startDate = localDate;
//        LocalDate endDate = startDate.plusWeeks(weeksInterval); // Calculate the end date

        LocalDate date = startDate;
        List<Date> listDate = new ArrayList();
        while (listDate.size() < slot) {

            for (int a = 0; a < thu.length; a++) {
                if (!date.isAfter(date.with(TemporalAdjusters.nextOrSame(DayOfWeek.valueOf(thu[a].toUpperCase()))))) {
                    LocalDate date1 = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.valueOf(thu[a].toUpperCase())));
                    Date dateConvert = DateUtils.asDate(date1);
                    DateAndDay DateofA = new DateAndDay(date1.getDayOfWeek().toString(), date1.toString());
                    listDate.add(dateConvert);
                    if (listDate.size() >= slot) {
                        break;
                    }
                } else {
                    LocalDate date1 = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.valueOf(thu[a].toUpperCase())));

                }
            }
            date = date.plusWeeks(1); // Move to the next week

        }

        return listDate;

    }

    //CHECK IF THE TRAINEE ALREADY HAS CLASS IN THAT SLOT
    public boolean checkTraineeSchedule(String maSlot, String maHocVien, List<String> thuList) {
        try {

            String sql = "SELECT ScheduleHV.maLopHoc\n"
                    + "FROM ScheduleHV\n"
                    + "WHERE ScheduleHV.maSlot = ? AND  maHV= ? and ScheduleHV.thu IN (";
            for (int i = 0; i < thuList.size(); i++) {
                sql += "?";
                if (i < thuList.size() - 1) {
                    sql += ",";
                }
            }
            sql += ") and status ='true' \n"
                    + "GROUP BY ScheduleHV.maLopHoc";
            PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
            stm.setString(1, maSlot);
            stm.setString(2, maHocVien);

            for (int i = 0; i < thuList.size(); i++) {
                stm.setString(i + 3, thuList.get(i));

            }

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return false;
            }
        } catch (SQLException e) {
            Logger.getLogger(LopHocDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return true;
    }

    public boolean checkTraineeClass(String maHV, String maLoaiLophoc) {
        LocalDate currentDate = LocalDate.now();
        String sql = "SELECT * from ScheduleHV \n"
                + "inner join lopHoc on lopHoc.maLopHoc = ScheduleHV.maLopHoc\n"
                + "where maHV=? and maLoaiLopHoc=? and lopHoc.[status]='true' ";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maHV);
            ps.setString(2, maLoaiLophoc);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return false;
            }

        } catch (SQLException e) {
        }
        return true;
    }

    //Check expired schedule
    public void checkSchedule() {
        LocalDate currentDate = LocalDate.now();
        String sql = "UPDATE [dbo].[ScheduleTemp] SET [status] = 'false' WHERE ngayHoc < ? "
                + "UPDATE [dbo].[ScheduleTrainer] SET [status] = 'false' WHERE ngayHoc < ? "
                + "UPDATE [dbo].[ScheduleHV] SET [status] = 'false' WHERE ngayHoc < ? ";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, java.sql.Date.valueOf(currentDate));
            ps.setDate(2, java.sql.Date.valueOf(currentDate));
            ps.setDate(3, java.sql.Date.valueOf(currentDate));

            int rowsUpdated = ps.executeUpdate();
            System.out.println(rowsUpdated + " rows updated. Status set to false for past dates.");
        } catch (SQLException e) {
        }

    }
    //GET LAST DAY

    public Date getLastDate(String[] thu, Date initDate, int slot) {
        List<Date> listDate = new ArrayList();
        listDate = listDate(thu, initDate, slot);
        Date oldestDate = listDate.get(0);
        for (Date date2 : listDate) {
            if (date2.after(oldestDate)) {
                oldestDate = date2;
            }
        }

        return oldestDate;
    }

    public static void main(String[] args) throws SQLException, ParseException {

        ScheduleDAO schedule = new ScheduleDAO();

//        List<ScheduleTempDTO> listScheduleHv = schedule.readScheduleTemp();
//        List<ScheduleTrainerDTO> listScheduleTrainer = schedule.readScheduleTrainer();
//        LopHocDAO lopHocDAO = new LopHocDAO();
//        LopHocDTO lopHocDTO = new LopHocDTO();
//        lopHocDTO = lopHocDAO.searchClassById("LOP0003");
//        System.out.println(lopHocDTO);
//          List<LopHocDTO> listLopHocTemp = a.showClassesByType("TYPE0001");
        // Split the selected value to retrieve maSlot and thuList
//        String selectedMaSlot = "SL005";
//        LocalDate currentDate = LocalDate.now();
//        System.out.println(java.sql.Date.valueOf(currentDate));
//        schedule.checkSchedule();
        String selectedThuList = "[ MONDAY, WEDNESDAY]";
//        schedule.checkTraineeSchedule("SL002", "HV0001", selectedThuList);
//
//        // Remove the square brackets and spaces from the string
//        String cleanedValue = selectedThuList.replaceAll("[\\[\\]\\s]", "");
//
//// Split the cleaned value into individual elements
//        String[] elements = cleanedValue.split(",");
//
//// Convert the array to a List<String>
//        String maLoaiLopHoc = "TYPE0001";
//        String maSlot = selectedMaSlot;
//        List<String> thuList = new ArrayList<>(Arrays.asList(elements));
//
//        System.out.println(schedule.checkTraineeSchedule(maSlot, "HV0001", thuList));
//        Date date = new Date();
//        List<Date> listDate = new ArrayList<Date>();
//        for (ScheduleTempDTO scheduleTempDTO : listScheduleHv) {
//            listDate.add(scheduleTempDTO.getNgayHoc());
//        }
//         Date oldestDate = listDate.get(0);
//        for (Date date2 : listDate) {
//            if (date2.after(oldestDate )) {
//                oldestDate = date2;
//            }
//
//        }
//        boolean hasSchedule = false;
//        List<String> a = new ArrayList<>();
//        String maLopHoc = "";
//        String tenLopHoc = "";
//        LopHocDAO lopHocDAO = new LopHocDAO();
//        System.out.println(oldestDate);
//        for (String x : a) {
//            System.out.println(x);
//            System.out.println(x.getNgayHoc());
//            Date ld = Date.valueOf("2023-06-05");
//            x.getNgayHoc().compareTo(ld);
//            System.out.println(x.getNgayHoc().equals(ld));
    }

//        for (ScheduleTempDTO x : listScheduleHv) {
//            System.out.println(x.toString());
////            System.out.println(x.getNgayHoc());
//
////            Date ld = Date.valueOf("2023-06-05");
////            x.getNgayHoc().compareTo(ld);
////            System.out.println(x.getNgayHoc().equals(ld));
//        }
//        System.out.println(schedule.readScheduleHvDTO("HV0001").toString());
}
