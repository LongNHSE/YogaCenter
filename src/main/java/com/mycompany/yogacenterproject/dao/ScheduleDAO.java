/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dao;

import com.mycompany.yogacenterproject.dto.DateAndDay;
import com.mycompany.yogacenterproject.dto.ScheduleHvDTO;
import com.mycompany.yogacenterproject.util.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
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

    //TAO SCHEDULEHV voi THAM SO MALOPHOC, MAHV, MASLOT
    public boolean createScheduleHV(String maLopHoc, String maHV, String maSlot) throws SQLException {
        String sql = "INSERT INTO [dbo].[ScheduleHV](maHV,maLopHoc, ngayHoc,maSlot, thu) "
                + "VALUES(?,?,?,?,?)";
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);

        List<DateAndDay> listDate = new ArrayList();
//        listDate = listDateAndDay("monday", "wednesday");//ID required 

        for (DateAndDay x : listDate) {
            ps.setString(1, maHV);
            ps.setString(2, maLopHoc);
            ps.setString(3, x.getDate());
            ps.setString(4, maSlot);
            ps.setString(5, x.getDay());
            ps.executeUpdate();

        }

        return false;
    }

    public List<DateAndDay> listDateAndDay(String thu1, String thu2, String initDate, int weeksInterval, LocalDate date1, LocalDate date2) {
        DateTimeFormatter df = new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("dd-MMM-yyyy").toFormatter(Locale.ENGLISH);
        LocalDate startDate = LocalDate.parse(initDate, df); // Get the current date
        LocalDate endDate = startDate.plusWeeks(weeksInterval); // Calculate the end date

        LocalDate date = startDate;
        List<DateAndDay> listDate = new ArrayList();
        String a = thu1;
        String b = thu2;
        while (date.isBefore(endDate)) {
            date1 = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.valueOf(a.toUpperCase())));
            date2 = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.valueOf(b.toUpperCase())));

            System.out.println(date1.getDayOfWeek() + " " + date1);
            System.out.println(date2.getDayOfWeek() + " " + date2);

            DateAndDay DateofA = new DateAndDay(date1.getDayOfWeek().toString(), date.toString());
            DateAndDay DateofB = new DateAndDay(date2.getDayOfWeek().toString(), date2.toString());
            listDate.add(DateofA);
            listDate.add(DateofB);

            date = date.plusWeeks(1); // Move to the next week
        }

        return listDate;
    }
    
    

    public static void main(String[] args) throws SQLException {
        // Get the current week's calendar
//        LocalDate startDate = LocalDate.of(2023,6,5); // Get the current date
//        LocalDate endDate = startDate.plusWeeks(5); // Calculate the end date
//
//        LocalDate date = startDate;
//        List<DateAndDay> listDate = new ArrayList();
//        String a = "monday";
//        String b = "saturday";
//        while (date.isBefore(endDate)) {
//            LocalDate monday = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.valueOf(a.toUpperCase())));
//            LocalDate wednesday = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.valueOf(b.toUpperCase())));
//
//            System.out.println(monday.getDayOfWeek() +" "+ monday);
//            System.out.println(wednesday.getDayOfWeek() +" "+ wednesday);
//            
//           
//            DateAndDay DateofA = new DateAndDay(monday.getDayOfWeek().toString(), monday.toString());
//             DateAndDay DateofB = new DateAndDay(wednesday.getDayOfWeek().toString(), wednesday.toString());
//            listDate.add(DateofA);
//            listDate.add(DateofB);
//            
//            date = date.plusWeeks(1); // Move to the next week
//        }
//        
//        
//        
//        
//        for(DateAndDay x : listDate){
//            System.out.println(x.getDay());
//            System.out.println(x.getDate());
//            
//        }

        ScheduleDAO schedule = new ScheduleDAO();
        schedule.createScheduleHV("LOP0002", "HV0001", "SL002");

//         List<ScheduleHvDTO> listScheduleHv =schedule.readScheduleHvDTO("HV0001");
//         
//        for(ScheduleHvDTO x :listScheduleHv ){
//            System.out.println(x.toString());
////            System.out.println(x.getNgayHoc());
//            
////            Date ld = Date.valueOf("2023-06-05");
////            x.getNgayHoc().compareTo(ld);
////            System.out.println(x.getNgayHoc().equals(ld));
//            
//            
//        }
//        System.out.println(schedule.readScheduleHvDTO("HV0001").toString());

    }
}
