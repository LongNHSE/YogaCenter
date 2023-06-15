/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dto;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author Oalskad
 */
public class LoaiLopHocDTO {

    private String maLoaiLopHoc;
    private String tenLoaiLopHoc;
    private double hocPhi;

    public LoaiLopHocDTO() {
    }

    public LoaiLopHocDTO(String maLoaiLopHoc, String tenLoaiLopHoc, double hocPhi) {
        this.maLoaiLopHoc = maLoaiLopHoc;
        this.tenLoaiLopHoc = tenLoaiLopHoc;
        this.hocPhi = hocPhi;
    }

    public String getMaLoaiLopHoc() {
        return maLoaiLopHoc;
    }

    public void setMaLoaiLopHoc(String maLoaiLopHoc) {
        this.maLoaiLopHoc = maLoaiLopHoc;
    }

    public String getTenLoaiLopHoc() {
        return tenLoaiLopHoc;
    }

    public void setTenLoaiLopHoc(String tenLoaiLopHoc) {
        this.tenLoaiLopHoc = tenLoaiLopHoc;
    }

    public double getHocPhi() {
        return hocPhi;
    }

    public void setHocPhi(double hocPhi) {
        this.hocPhi = hocPhi;
    }

    public String getHocPhiWithDot() {
        double hocPhi = getHocPhi();

// Create a DecimalFormatSymbols instance for the default locale
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setGroupingSeparator('.');

// Create a DecimalFormat instance with the desired pattern and symbols
        DecimalFormat decimalFormat = new DecimalFormat("#,###", symbols);
        decimalFormat.setDecimalSeparatorAlwaysShown(false);

        return decimalFormat.format(hocPhi);
    }

    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("#");
        return "LoaiLopHocDTO{" + "maLoaiLopHoc=" + maLoaiLopHoc + ", tenLoaiLopHoc=" + tenLoaiLopHoc + ", hocPhi=" + getHocPhiWithDot() + '}';
    }

}