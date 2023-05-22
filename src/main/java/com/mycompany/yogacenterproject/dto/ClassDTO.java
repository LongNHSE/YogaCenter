/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dto;

import java.sql.Date;

public class ClassDTO {
    private String maLopHoc;
    private int soLuongHV;
    private int soBuoi;
    private String maTrainer;
    private String maLoaiLopHoc;
    private String maSlot;
    private String maRoom;
    private Date ngay;

    public ClassDTO() {
    }

    public ClassDTO(String maLopHoc, int soLuongHV, int soBuoi, String maTrainer, String maLoaiLopHoc, String maSlot, String maRoom, Date ngay) {
        this.maLopHoc = maLopHoc;
        this.soLuongHV = soLuongHV;
        this.soBuoi = soBuoi;
        this.maTrainer = maTrainer;
        this.maLoaiLopHoc = maLoaiLopHoc;
        this.maSlot = maSlot;
        this.maRoom = maRoom;
        this.ngay = ngay;
    }

    public String getMaLopHoc() {
        return maLopHoc;
    }

    public void setMaLopHoc(String maLopHoc) {
        this.maLopHoc = maLopHoc;
    }

    public int getSoLuongHV() {
        return soLuongHV;
    }

    public void setSoLuongHV(int soLuongHV) {
        this.soLuongHV = soLuongHV;
    }

    public int getSoBuoi() {
        return soBuoi;
    }

    public void setSoBuoi(int soBuoi) {
        this.soBuoi = soBuoi;
    }

    public String getMaTrainer() {
        return maTrainer;
    }

    public void setMaTrainer(String maTrainer) {
        this.maTrainer = maTrainer;
    }

    public String getMaLoaiLopHoc() {
        return maLoaiLopHoc;
    }

    public void setMaLoaiLopHoc(String maLoaiLopHoc) {
        this.maLoaiLopHoc = maLoaiLopHoc;
    }

    public String getMaSlot() {
        return maSlot;
    }

    public void setMaSlot(String maSlot) {
        this.maSlot = maSlot;
    }

    public String getMaRoom() {
        return maRoom;
    }

    public void setMaRoom(String maRoom) {
        this.maRoom = maRoom;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    @Override
    public String toString() {
        return "ClassDTO{" + "maLopHoc=" + maLopHoc + ", soLuongHV=" + soLuongHV + ", soBuoi=" + soBuoi + ", maTrainer=" + maTrainer + ", maLoaiLopHoc=" + maLoaiLopHoc + ", maSlot=" + maSlot + ", maRoom=" + maRoom + ", ngay=" + ngay + '}';
    }
    
    
}
