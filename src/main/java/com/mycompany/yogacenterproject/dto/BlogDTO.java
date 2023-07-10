/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dto;

import java.sql.Date;
import java.time.LocalDate;

import java.text.ParseException;

import java.util.List;
import java.text.SimpleDateFormat;

import java.util.TimeZone;

/**
 *
 * @author devli
 */
public class BlogDTO {

    private String maBlog;
    private String title;
    private String content;
    private String date;
    private String maHV;
    private boolean status;
    private List<BlogImgDTO> image;
    private String tenHV;
    private String maTrainer;
    private String maCate;
    private Date ngayCapNhat;
    private Date ngayTaoPost;

    public BlogDTO() {
    }

    public String getMaBlog() {
        return maBlog;
    }

    @Override
    public String toString() {
        return "BlogDTO{" + "maBlog=" + maBlog + ", title=" + title + ", content=" + content + ", date=" + date + ", maHV=" + maHV + ", status=" + status + ", image=" + image + ", tenHV=" + tenHV + ", maTrainer=" + maTrainer + ", maCate=" + maCate + ", ngayCapNhat=" + ngayCapNhat + ", ngayTaoPost=" + ngayTaoPost + '}';
    }

    public void setMaBlog(String maBlog) {
        this.maBlog = maBlog;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMaHV() {
        return maHV;
    }

    public void setMaHV(String maHV) {
        this.maHV = maHV;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<BlogImgDTO> getImage() {
        return image;
    }

    public void setImage(List<BlogImgDTO> image) {
        this.image = image;
    }

    public String getTenHV() {
        return tenHV;
    }

    public void setTenHV(String tenHV) {
        this.tenHV = tenHV;
    }

    public String getMaTrainer() {
        return maTrainer;
    }

    public void setMaTrainer(String maTrainer) {
        this.maTrainer = maTrainer;
    }

    public String getMaCate() {
        return maCate;
    }

    public void setMaCate(String maCate) {
        this.maCate = maCate;
    }

    public Date getNgayCapNhat() {
        return ngayCapNhat;
    }

    public void setNgayCapNhat(Date ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }

    public Date getNgayTaoPost() {
        return ngayTaoPost;
    }

    public void setNgayTaoPost(Date ngayTaoPost) {
        this.ngayTaoPost = ngayTaoPost;
    }

}
