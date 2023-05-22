/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dto;

/**
 *
 * @author Oalskad
 */
public class RoomDTO {
    String maRoom;
    boolean status;

    public RoomDTO() {
    }

    public RoomDTO(String maRoom, boolean status) {
        this.maRoom = maRoom;
        this.status = status;
    }

    
    public String getMaRoom() {
        return maRoom;
    }

    public void setMaRoom(String maRoom) {
        this.maRoom = maRoom;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
    
    
}
