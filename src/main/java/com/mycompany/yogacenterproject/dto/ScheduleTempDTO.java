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
public class ScheduleTempDTO {

    private String maLopHoc;
    private Date ngayHoc;
    private String maSlot;
    private String thu;
    private boolean status;

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public ScheduleTempDTO(String maLopHoc, Date ngayHoc, String maSlot, String thu) {
        this.maLopHoc = maLopHoc;
        this.ngayHoc = ngayHoc;
        this.maSlot = maSlot;
        this.thu = thu;
    }

    public ScheduleTempDTO() {
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
        return "ScheduleTempDTO{" + "maLopHoc=" + maLopHoc + ", ngayHoc=" + ngayHoc + ", maSlot=" + maSlot + ", thu=" + thu + '}';
    }

}
