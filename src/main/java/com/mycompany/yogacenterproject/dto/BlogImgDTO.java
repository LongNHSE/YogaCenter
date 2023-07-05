/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dto;

/**
 *
 * @author devli
 */
public class BlogImgDTO {
      private String maAnh;
      private String tenAnh;
      private String image;
      private String maBlog;

      public BlogImgDTO() {
      }

      public BlogImgDTO(String maAnh, String tenAnh, String image, String maBlog) {
            this.maAnh = maAnh;
            this.tenAnh = tenAnh;
            this.image = image;
            this.maBlog = maBlog;
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

      public String getMaBlog() {
            return maBlog;
      }

      public void setMaBlog(String maBlog) {
            this.maBlog = maBlog;
      }

      @Override
      public String toString() {
            return "BlogImgDTO{" + "maAnh=" + maAnh + ", tenAnh=" + tenAnh + ", image=" + image + ", maBlog=" + maBlog + '}';
      }
      
      
}
