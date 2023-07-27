/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dto;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.Locale;


public class TrainerDTO {
private String maTrainer;    
private String ho;
private String ten;
private LocalDate dob;
private String phone;    
private String email;
private long salary;
private String username;
private String psw;
private int soNgayNghi;
private Boolean status;
private String trainerType;
private String maLoaiTK;
private String maLopHoc;
private String gender;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMaLopHoc() {
        return maLopHoc;
    }

    public void setMaLopHoc(String maLopHoc) {
        this.maLopHoc = maLopHoc;
    }
    public TrainerDTO(String maTrainer, String ho, String ten, LocalDate dob, String phone, String email, long salary, String username, String psw, int soNgayNghi, Boolean status, String trainerType, String maLoaiTK) {
        this.maTrainer = maTrainer;
        this.ho = ho;
        this.ten = ten;
        this.dob = dob;
        this.phone = phone;
        this.email = email;
        this.salary = salary;
        this.username = username;
        this.psw = psw;
        this.soNgayNghi = soNgayNghi;
        this.status = status;
        this.trainerType = trainerType;
        this.maLoaiTK = maLoaiTK;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    @Override
    public String toString() {
        return "TrainerDTO{" + "maTrainer=" + maTrainer + ", ho=" + ho + ", ten=" + ten + ", dob=" + dob + ", phone=" + phone + ", email=" + email + ", salary=" + salary + ", username=" + username + ", psw=" + psw + ", soNgayNghi=" + soNgayNghi + ", status=" + status + ", trainerType=" + trainerType + ", maLoaiTK=" + maLoaiTK + '}';
    }

 
    

    

    public TrainerDTO() {
    }

    public String getMaTrainer() {
        return maTrainer;
    }

    public void setMaTrainer(String maTrainer) {
        this.maTrainer = maTrainer;
    }

   
    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
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

    public int getSoNgayNghi() {
        return soNgayNghi;
    }

    public void setSoNgayNghi(int soNgayNghi) {
        this.soNgayNghi = soNgayNghi;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getTrainerType() {
        return trainerType;
    }

    public void setTrainerType(String trainerType) {
        this.trainerType = trainerType;
    }

    public String getMaLoaiTK() {
        return maLoaiTK;
    }

    public void setMaLoaiTK(String maLoaiTK) {
        this.maLoaiTK = maLoaiTK;
    }
      public String getHocPhiWithDot() {
        double hocPhi = getSalary();

// Create a DecimalFormatSymbols instance for the default locale
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setGroupingSeparator('.');

// Create a DecimalFormat instance with the desired pattern and symbols
        DecimalFormat decimalFormat = new DecimalFormat("#,###", symbols);
        decimalFormat.setDecimalSeparatorAlwaysShown(false);

        return decimalFormat.format(hocPhi);
    }
    

   
   


}
