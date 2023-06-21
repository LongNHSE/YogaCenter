/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dto;

/**
 *
 * @author devli
 */
public class LopHocIMGDTO {

    private String maAnh;
    private String tenAnh;
    private String image;
    private String maLoaiLopHoc;
    private String maHV;
    private String maLopHoc;

    public LopHocIMGDTO() {
    }

    public String getMaAnh() {
        return maAnh;
    }

    public void setMaAnh(String maAnh) {
        this.maAnh = maAnh;
    }

    public String getTenAnh() {
        return tenAnh;
    }

    public void setTenAnh(String tenAnh) {
        this.tenAnh = tenAnh;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMaLoaiLopHoc() {
        return maLoaiLopHoc;
    }

    public void setMaLoaiLopHoc(String maLoaiLopHoc) {
        this.maLoaiLopHoc = maLoaiLopHoc;
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

    @Override
    public String toString() {
        return "LopHocIMGDTO{" + "maAnh=" + maAnh + ", tenAnh=" + tenAnh + ", image=" + image + ", maLoaiLopHoc=" + maLoaiLopHoc + ", maHV=" + maHV + ", maLopHoc=" + maLopHoc + '}';
    }

    public LopHocIMGDTO(String maAnh, String tenAnh, String image, String maLoaiLopHoc, String maHV, String maLopHoc) {
        this.maAnh = maAnh;
        this.tenAnh = tenAnh;
        this.image = image;
        this.maLoaiLopHoc = maLoaiLopHoc;
        this.maHV = maHV;
        this.maLopHoc = maLopHoc;
    }

    
   

   

    

}
