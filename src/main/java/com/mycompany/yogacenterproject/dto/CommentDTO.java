/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dto;

import java.sql.Date;

/**
 *
 * @author devli
 */
public class CommentDTO {
   private String maComment;
   private String maHV;
   private String maTrainer;
   private String maLopHoc;
   private String maLoaiLopHoc;
   private String maBlog;
   private String noiDung;
   private Date date;
   private boolean status;
   private HocVienDTO hocVienDTO;
   private TrainerDTO trainerDTO;

    public HocVienDTO getHocVienDTO() {
        return hocVienDTO;
    }

    public void setHocVienDTO(HocVienDTO hocVienDTO) {
        this.hocVienDTO = hocVienDTO;
    }

    public TrainerDTO getTrainerDTO() {
        return trainerDTO;
    }

    public void setTrainerDTO(TrainerDTO trainerDTO) {
        this.trainerDTO = trainerDTO;
    }
    

    public CommentDTO() {
    }

    public String getMaComment() {
        return maComment;
    }

    public void setMaComment(String maComment) {
        this.maComment = maComment;
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

    public String getMaLoaiLopHoc() {
        return maLoaiLopHoc;
    }

    public void setMaLoaiLopHoc(String maLoaiLopHoc) {
        this.maLoaiLopHoc = maLoaiLopHoc;
    }

    public String getMaBlog() {
        return maBlog;
    }

    public void setMaBlog(String maBlog) {
        this.maBlog = maBlog;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CommentDTO{" + "maComment=" + maComment + ", maHV=" + maHV + ", maTrainer=" + maTrainer + ", maLopHoc=" + maLopHoc + ", maLoaiLopHoc=" + maLoaiLopHoc + ", maBlog=" + maBlog + ", noiDung=" + noiDung + ", date=" + date + ", status=" + status + '}';
    }

    

 

 
}
