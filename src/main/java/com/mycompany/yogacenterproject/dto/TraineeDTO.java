/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dto;

import java.sql.Date;

public class TraineeDTO {
    private String maHV;
    private String Ho;
    private String Ten;
    private Date dob;
    private String username;
    private String psw;
    private String maLopHoc;
    private String maLoaiTK;

    public TraineeDTO() {
    }

    @Override
    public String toString() {
        return "TraineeDTO{" + "maHV=" + maHV + ", Ho=" + Ho + ", Ten=" + Ten + ", dob=" + dob + ", username=" + username + ", psw=" + psw + ", maLopHoc=" + maLopHoc + ", maLoaiTK=" + maLoaiTK + '}';
    }

    public TraineeDTO(String maHV, String Ho, String Ten, Date dob, String username, String psw, String maLopHoc, String maLoaiTK) {
        this.maHV = maHV;
        this.Ho = Ho;
        this.Ten = Ten;
        this.dob = dob;
        this.username = username;
        this.psw = psw;
        this.maLopHoc = maLopHoc;
        this.maLoaiTK = maLoaiTK;
    }

    public String getMaHV() {
        return maHV;
    }

    public void setMaHV(String maHV) {
        this.maHV = maHV;
    }

    public String getHo() {
        return Ho;
    }

    public void setHo(String Ho) {
        this.Ho = Ho;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String Ten) {
        this.Ten = Ten;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public String getMaLopHoc() {
        return maLopHoc;
    }

    public void setMaLopHoc(String maLopHoc) {
        this.maLopHoc = maLopHoc;
    }

    public String getMaLoaiTK() {
        return maLoaiTK;
    }

    public void setMaLoaiTK(String maLoaiTK) {
        this.maLoaiTK = maLoaiTK;
    }
    
    
}
