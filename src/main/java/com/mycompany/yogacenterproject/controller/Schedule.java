/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.controller;

import com.mycompany.yogacenterproject.dto.DateAndDay;
import com.mycompany.yogacenterproject.util.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormatSymbols;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Oalskad
 */
public class Schedule {
    
    public boolean createSchedule(String maLopHoc) throws SQLException{
        String sql = "INSERT INTO [dbo].[timeTable](maLopHoc, ngayHoc, thu) "
                + "VALUES(?,?,?)";
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        
        List<DateAndDay> listDate = new ArrayList();
        listDate = listDateAndDay("monday", "wednesday");
        
        
        for(DateAndDay x : listDate){
            ps.setString(1, maLopHoc);
            ps.setString(2, x.getDate());
            ps.setString(3, x.getDay());
            ps.executeUpdate();
            
        }

        return false;
    }
    
    public List<DateAndDay> listDateAndDay(String thu1,String thu2){
        LocalDate startDate = LocalDate.of(2023,6,5); // Get the current date
        LocalDate endDate = startDate.plusWeeks(5); // Calculate the end date

        LocalDate date = startDate;
        List<DateAndDay> listDate = new ArrayList();
        String a = thu1;
        String b = thu2;
        while (date.isBefore(endDate)) {
            LocalDate monday = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.valueOf(a.toUpperCase())));
            LocalDate wednesday = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.valueOf(b.toUpperCase())));

            System.out.println(monday.getDayOfWeek() +" "+ monday);
            System.out.println(wednesday.getDayOfWeek() +" "+ wednesday);
            
           
            DateAndDay DateofA = new DateAndDay(monday.getDayOfWeek().toString(), monday.toString());
            DateAndDay DateofB = new DateAndDay(wednesday.getDayOfWeek().toString(), wednesday.toString());
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




    Schedule schedule = new Schedule();
    schedule.createSchedule("LOP0001");
    }
}
