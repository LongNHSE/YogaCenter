/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dto;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author Oalskad
 */
public class SlotDTO {
    private String maSlot;
    private Time timeStart;
    private Time timeEnd;

    public SlotDTO() {
    }

    public SlotDTO(String maSlot, Time timeStart, Time timeEnd) {
        this.maSlot = maSlot;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public String getMaSlot() {
        return maSlot;
    }

    public void setMaSlot(String maSlot) {
        this.maSlot = maSlot;
    }

    public Time getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Time timeStart) {
        this.timeStart = timeStart;
    }

    public Time getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Time timeEnd) {
        this.timeEnd = timeEnd;
    }

    @Override
    public String toString() {
        return "Slot{" + "maSlot=" + maSlot + ", timeStart=" + timeStart + ", timeEnd=" + timeEnd + '}';
    }
    
    
    
 
    
    public static void main(String[] args) {
         Date javaDate = new Date();

        // Convert Java Date to SQL Time
        Time sqlTime = new Time(20,20,30);

        // Use the SQL Time in your SQL query or insert it into the database
        // Example: INSERT INTO your_table (time_column) VALUES (?)
        // Use a prepared statement to set the SQL Time value in the query.

        // Print the converted SQL Time
        System.out.println("SQL Time: " + sqlTime);
    }
}
