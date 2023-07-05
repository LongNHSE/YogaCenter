/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dto;

import java.sql.Date;

/**
 *
 * @author Oalskad
 */
public class AttendanceDTO {
    private String attendanceID;
    private String maHV;
    private String maLopHoc;
    private Date ngayHoc;
    private String maSlot;
    private String status;

    public AttendanceDTO() {
    }

    public AttendanceDTO(String attendanceID, String maHV, String maLopHoc, Date ngayHoc, String maSlot, String status) {
        this.attendanceID = attendanceID;
        this.maHV = maHV;
        this.maLopHoc = maLopHoc;
        this.ngayHoc = ngayHoc;
        this.maSlot = maSlot;
        this.status = status;
    }

    public String getAttendanceID() {
        return attendanceID;
    }

    public void setAttendanceID(String attendanceID) {
        this.attendanceID = attendanceID;
    }

    public String getMaHV() {
        return maHV;
    }

    public void setMaHV(String maHV) {
        this.maHV = maHV;
    }

    public String getMaLopHoc() {
        return maLopHoc;
    }

    public void setMaLopHoc(String maLopHoc) {
        this.maLopHoc = maLopHoc;
    }

    public Date getNgayHoc() {
        return ngayHoc;
    }

    public void setNgayHoc(Date ngayHoc) {
        this.ngayHoc = ngayHoc;
    }

    public String getMaSlot() {
        return maSlot;
    }

    public void setMaSlot(String maSlot) {
        this.maSlot = maSlot;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AttendanceDTO{" + "attendanceID=" + attendanceID + ", maHV=" + maHV + ", maLopHoc=" + maLopHoc + ", ngayHoc=" + ngayHoc + ", maSlot=" + maSlot + ", status=" + status + '}';
    }
    
}
