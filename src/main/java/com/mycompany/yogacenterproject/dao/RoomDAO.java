/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dao;

import com.mycompany.yogacenterproject.dto.SlotDTO;
import com.mycompany.yogacenterproject.dto.RoomDTO;
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
public class RoomDAO {

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

    public List<RoomDTO> readRoom() {
        List<RoomDTO> listRoomDTO = new ArrayList<RoomDTO>();
        String sql = "SELECT * FROM [dbo].[room]";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                RoomDTO roomDTO = new RoomDTO();
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

    public RoomDTO searchByMaRoom(String maRoom) {
        String sql = "SELECT * FROM [dbo].[room] WHERE maRoom = ?";
        RoomDTO roomDTO = new RoomDTO();
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
}
