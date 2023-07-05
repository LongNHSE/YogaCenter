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
public class ScheduleHvDTO {

    private String maHV;
    private String maLopHoc;
    private Date ngayHoc;
    private String maSlot;
    private String thu;
    private boolean status;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ScheduleHvDTO() {
    }

    public ScheduleHvDTO(String maHV, String maLopHoc, Date ngayHoc, String maSlot, String thu) {
        this.maHV = maHV;
        this.maLopHoc = maLopHoc;
        this.ngayHoc = ngayHoc;
        this.maSlot = maSlot;
        this.thu = thu;
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

    public String getThu() {
        return thu;
    }

    public void setThu(String thu) {
        this.thu = thu;
    }

    @Override
    public String toString() {
        return "ScheduleHvDTO{" + "maHV=" + maHV + ", maLopHoc=" + maLopHoc + ", ngayHoc=" + ngayHoc + ", maSlot=" + maSlot + ", thu=" + thu + '}';
    }

}
