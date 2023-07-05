/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dto;

/**
 *
 * @author devli
 */
public class Blog {
      private String maBlog;
      private String title;
      private String content;
      private String date;
      private String maHV;

      public Blog() {
      }

      public Blog(String maBlog, String title, String content, String date, String maHV) {
            this.maBlog = maBlog;
            this.title = title;
            this.content = content;
            this.date = date;
            this.maHV = maHV;
      }

      public String getMaBlog() {
            return maBlog;
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

      @Override
      public String toString() {
            return "Blog{" + "maBlog=" + maBlog + ", title=" + title + ", content=" + content + ", date=" + date + ", maHV=" + maHV + '}';
      }
      
      
}
