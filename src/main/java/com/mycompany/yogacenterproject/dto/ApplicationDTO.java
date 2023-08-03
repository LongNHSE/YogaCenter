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
public class ApplicationDTO {
    private String ApplicationType;
    private String maDon;
    private String maHV;
    private String maTrainer;
    private TrainerDTO trainerDTO;
    private HocVienDTO hocVienDTO;
    private String maLopHoc;
    private String maApplicationType;
    private String noiDung;
    private Date date;
    private String status;

    public TrainerDTO getTrainerDTO() {
        return trainerDTO;
    }

    public void setTrainerDTO(TrainerDTO trainerDTO) {
        this.trainerDTO = trainerDTO;
    }

    public HocVienDTO getHocVienDTO() {
        return hocVienDTO;
    }

    public void setHocVienDTO(HocVienDTO hocVienDTO) {
        this.hocVienDTO = hocVienDTO;
    }

    public String getApplicationType() {
        return ApplicationType;
    }

    public void setApplicationType(String ApplicationType) {
        this.ApplicationType = ApplicationType;
    }

    @Override
    public String toString() {
        return "ApplicationDTO{" + "ApplicationType=" + ApplicationType + ", maDon=" + maDon + ", maHV=" + maHV + ", maTrainer=" + maTrainer + ", maLopHoc=" + maLopHoc + ", maApplicationType=" + maApplicationType + ", noiDung=" + noiDung + ", date=" + date + ", status=" + status + '}';
    }

    public ApplicationDTO() {
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public ApplicationDTO(String maDon, String maHV, String maTrainer, String maLopHoc, String maApplicationType, Date date, String status) {
        this.maDon = maDon;
        this.maHV = maHV;
        this.maTrainer = maTrainer;
        this.maLopHoc = maLopHoc;
        this.maApplicationType = maApplicationType;
        this.date = date;
        this.status = status;
    }

    public String getMaDon() {
        return maDon;
    }

    public void setMaDon(String maDon) {
        this.maDon = maDon;
    }

    public String getMaHV() {
        return maHV;
    }

    public void setMaHV(String maHV) {
        this.maHV = maHV;
    }

    public String getMaTrainer() {
        return maTrainer;
    }

    public void setMaTrainer(String maTrainer) {
        this.maTrainer = maTrainer;
    }

    public String getMaLopHoc() {
        return maLopHoc;
    }

    public void setMaLopHoc(String maLopHoc) {
        this.maLopHoc = maLopHoc;
    }

    public String getMaApplicationType() {
        return maApplicationType;
    }

    public void setMaApplicationType(String maApplicationType) {
        this.maApplicationType = maApplicationType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
