/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dao;

import com.mycompany.yogacenterproject.dto.LopHocDTO;
import com.mycompany.yogacenterproject.dto.SlotDTO;
import com.mycompany.yogacenterproject.util.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Oalskad
 */
public class SlotDAO {

    //CREATE NEW SLOT
    public boolean createSlot(String maSlot, Time timeStart, Time timeEnd) {
        String sql = "INSERT INTO [dbo].[Slot] (maSlot, timeStart, timeEnd)" + "VALUES(?, ?, ?)";

        // If ps.executeUpdate() co du lieu vao thi return 1 va return 0 neu ko co
        int row = 0;
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maSlot);
            ps.setTime(2, timeStart);
            ps.setTime(3, timeEnd);

            row = ps.executeUpdate();

            ps.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);

        }
        return row > 0;
    }

    //UPDATE SLOT
    public boolean updateSlot(Time timeStartNew, Time timeEndNew,String maSlot) {
        String sql = " UPDATE [dbo].[slot]" + "SET"
                + "timeStart = ?"
                + "timeEnd= ?"
                + "WHERE maSlot = ?";
        int row = 0;
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setTime(1, timeStartNew);
            ps.setTime(2, timeEndNew);
            ps.setString(3, maSlot);
            row = ps.executeUpdate();

            ps.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return row > 0;
    }

    //READ SLOT and RETURN LIST OF SLOTDTO
    public List<SlotDTO> readSlot() {
        List<SlotDTO> listSlotDTO = new ArrayList<SlotDTO>();
        String sql = "SELECT maSlot , CAST(timeStart AS VARCHAR(5)) AS TimeStart, CAST(timeEnd AS VARCHAR(5)) AS TimeEnd FROM [dbo].[slot]";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SlotDTO slotDTO = new SlotDTO();
                slotDTO.setMaSlot(rs.getString("maSlot"));
                slotDTO.setTimeStart(rs.getString("timeStart"));
                slotDTO.setTimeEnd(rs.getString("timeEnd"));

                listSlotDTO.add(slotDTO);
                
                
            }
            rs.close();
                ps.close();
                conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return listSlotDTO;

    }

    //RETURN 1 SLOT with maSlot
    public SlotDTO searchByMaSlot(String maSlot) {
        String sql = "SELECT * FROM [dbo].[slot] WHERE maSlot = ?";
        SlotDTO slotDTO = new SlotDTO();
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maSlot);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                slotDTO.setMaSlot(rs.getString(1));
                slotDTO.setTimeStart(rs.getString(2));
                slotDTO.setTimeEnd(rs.getString(3));
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e);
        }
        return slotDTO;
    }
    
    
    
    
    
    //DELETE SLOT WITH maSLot
//    public boolean deleteSlot(String maSlot){
//        
//    }
    
    
    
    

    public static void main(String[] args) {

        SlotDAO slotDAO = new SlotDAO();
//        Time timeStart = new Time(9, 15, 00);
//        Time timeEnd = new Time(10, 45, 00);
//        slotDAO.createSlot("SL0002", timeStart, timeEnd);
        List<SlotDTO> listSlotDTO = new ArrayList<SlotDTO>();
        listSlotDTO = slotDAO.readSlot();
        for (SlotDTO x : listSlotDTO) {
            System.out.println(x.toString());
            
           
        }
        
        
        List<SlotDTO> slotDTO = slotDAO.readSlot();
        System.out.println(slotDTO.toString());
        
        
    }
}
