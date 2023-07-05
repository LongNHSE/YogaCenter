/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dto;

import java.sql.Date;
import java.util.List;

public class LopHocDTO {

    private String maLopHoc;
    private int soLuongHV;
    private int soBuoi;
    private String maTrainer;
    private String maLoaiLopHoc;
    private String maSlot;
    private String maRoom;
    private int soLuongHvHienTai;
    private String timeStart, timeEnd;
    private List<String> thuList;
    private boolean status;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<String> getThuList() {
        return thuList;
    }

    public void setThuList(List<String> thuList) {
        this.thuList = thuList;
    }
    
    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    private Date ngayBatDau;

    private String[] thu;



    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public int getSoLuongHvHienTai() {
        return soLuongHvHienTai;
    }

    public void setSoLuongHvHienTai(int soLuongHvHienTai) {
        this.soLuongHvHienTai = soLuongHvHienTai;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public String[] getThu() {
        return thu;
    }

    public void setThu(String[] thu) {
        this.thu = thu;
    }

    public LopHocDTO() {
    }

    public LopHocDTO(String maLopHoc, int soLuongHV, int soBuoi, String maTrainer, String maLoaiLopHoc, String maSlot, String maRoom, Date ngayBatDau) {
        this.maLopHoc = maLopHoc;
        this.soLuongHV = soLuongHV;
        this.soBuoi = soBuoi;
        this.maTrainer = maTrainer;
        this.maLoaiLopHoc = maLoaiLopHoc;
        this.maSlot = maSlot;
        this.maRoom = maRoom;
        this.ngayBatDau = ngayBatDau;
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

    @Override
    public String toString() {

        return "LopHocDTO{" + "maLopHoc=" + maLopHoc + ", soLuongHV=" + soLuongHV + ", soBuoi=" + soBuoi + ", maTrainer=" + maTrainer + ", maLoaiLopHoc=" + maLoaiLopHoc + ", maSlot=" + maSlot + ", maRoom=" + maRoom + ", soLuongHvHienTai=" + soLuongHvHienTai + ", timeStart=" + timeStart + ", timeEnd=" + timeEnd + ", thuList=" + thuList + ", ngayBatDau=" + ngayBatDau + ", thu=" + thu + '}';

    }

   

}
