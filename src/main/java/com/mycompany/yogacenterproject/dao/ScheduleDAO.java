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
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Oalskad
 */
public class ScheduleDAO {

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
                listScheduleTemp.add(scheduleTempDTO);

            }
        } catch (SQLException e) {
        }

        return listScheduleTemp;
    }

    //TAO SCHEDULEHV voi THAM SO MALOPHOC, MAHV, MASLOT
    public boolean createScheduleHV(String maHV, String maLopHoc) throws SQLException {
        String sql = "INSERT INTO ScheduleHV([maHV], [maLopHoc], [ngayHoc], [maSlot], [thu])\n"
                + "SELECT ?, [maLopHoc], [ngayHoc], [maSlot], [thu]\n"
                + "FROM [YogaCenter].[dbo].[ScheduleTemp]\n"
                + "WHERE ScheduleTemp.maLopHoc=? ;";
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, maHV    );
        ps.setString(2, maLopHoc);

        ps.executeUpdate();

        return false;
    }

    //Parse du lieu tu ScheduleTemp sang Schedule Trainer sau khi Trainer assign hoac duoc assign 
    public boolean createScheduleTrainer(String maTrainer, LopHocDTO lopHocDTO) throws SQLException {
        String sql = "INSERT INTO ScheduleTrainer([maTrainer], [maLopHoc], [ngayHoc], [maSlot], [thu])\n"
                + "SELECT ?, [maLopHoc], [ngayHoc], [maSlot], [thu]\n"
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

    public boolean createScheduleTemp(LopHocDTO lopHocDTO) throws SQLException {
        String sql = "INSERT INTO [dbo].[ScheduleTemp](maLopHoc, ngayHoc,maSlot, thu) "
                + "VALUES(?,?,?,?)";
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);

        List<DateAndDay> listDate = new ArrayList();

        listDate = listDateAndDay(lopHocDTO.getThu(), lopHocDTO.getNgayBatDau(), lopHocDTO.getSoBuoi());

        for (DateAndDay x : listDate) {

            ps.setString(1, lopHocDTO.getMaLopHoc());
            ps.setString(2, x.getDate());
            ps.setString(3, lopHocDTO.getMaSlot());
            ps.setString(4, x.getDay());
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

    public static void main(String[] args) throws SQLException, ParseException {

        ScheduleDAO schedule = new ScheduleDAO();

        List<ScheduleTempDTO> listScheduleHv = schedule.readScheduleTemp();
        List<ScheduleTrainerDTO> listScheduleTrainer = schedule.readScheduleTrainer();
//        boolean hasSchedule = false;
//        List<String> a = new ArrayList<>();
//        String maLopHoc = "";
//        String tenLopHoc = "";
//        LopHocDAO lopHocDAO = new LopHocDAO();

        for (ScheduleTrainerDTO ScheduleTempDTO : listScheduleTrainer) {

            System.out.println(ScheduleTempDTO);

        }
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
