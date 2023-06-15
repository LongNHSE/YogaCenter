/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dto;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class HocVienDTO {

    private String maHV;
    private String Ho;
    private String Ten;
    private LocalDate dob;
    private String username;
    private String phone;
    private String psw;
    private List<String> maLopHoc;
    private String maLoaiTK;
    private String email;
    private String gender;

    public List getMaLopHoc() {
        if (maLopHoc != null) {
            return maLopHoc;
        } else {
            return null;
        }
    }

    public void setMaLopHoc(List maLopHoc) {
        this.maLopHoc = maLopHoc;
    }

    public String generateString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < maLopHoc.size(); i++) {
            stringBuilder.append(maLopHoc.get(i));
            if (i < maLopHoc.size() - 1) {
                stringBuilder.append(", ");
            }
        }
        return stringBuilder.toString();
    }

    public String printClass() {
        StringBuilder htmlBuilder = new StringBuilder();
        for (String lopHoc : maLopHoc) {
            htmlBuilder.append(lopHoc).append(" ");

        }
        return htmlBuilder.toString();
    }

    public HocVienDTO() {
    }

    public HocVienDTO(String maHV, String Ho, String Ten, LocalDate dob, String username, String phone, String psw, String maLoaiTK, String email, String gender) {
        this.maHV = maHV;
        this.Ho = Ho;
        this.Ten = Ten;
        this.dob = dob;
        this.username = username;
        this.phone = phone;
        this.psw = psw;
        this.maLoaiTK = maLoaiTK;
        this.email = email;
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public String getMaLoaiTK() {
        return maLoaiTK;
    }

    public void setMaLoaiTK(String maLoaiTK) {
        this.maLoaiTK = maLoaiTK;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "HocVienDTO{" + "maHV=" + maHV + ", Ho=" + Ho + ", Ten=" + Ten + ", dob=" + dob + ", username=" + username + ", phone=" + phone + ", psw=" + psw + ", maLopHoc=" + maLopHoc + ", maLoaiTK=" + maLoaiTK + ", email=" + email + ", gender=" + gender + '}';
    }

}
