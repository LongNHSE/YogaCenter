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
public class HoaDonDTO {
    private String mahoaDon;
    private String maHV;
    private String maLopHoc;
    private long giaTien;
    private Date ngayThanhToan;
    
   
    public HoaDonDTO() {
    }

    public HoaDonDTO(String mahoaDon, String maHV, String maLopHoc, long giaTien, Date ngayThanhToan) {
        this.mahoaDon = mahoaDon;
        this.maHV = maHV;
        this.maLopHoc = maLopHoc;
        this.giaTien = giaTien;
        this.ngayThanhToan = ngayThanhToan;
    }

    public String getMahoaDon() {
        return mahoaDon;
    }

    public void setMahoaDon(String mahoaDon) {
        this.mahoaDon = mahoaDon;
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

    public long getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(long giaTien) {
        this.giaTien = giaTien;
    }

    public Date getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(Date ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    @Override
    public String toString() {
        return "HoaDonDTO{" + "mahoaDon=" + mahoaDon + ", maHV=" + maHV + ", maLopHoc=" + maLopHoc + ", giaTien=" + giaTien + ", ngayThanhToan=" + ngayThanhToan + '}';
    }
    
    
}
