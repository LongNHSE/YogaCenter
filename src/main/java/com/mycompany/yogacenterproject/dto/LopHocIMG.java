/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dto;

/**
 *
 * @author devli
 */
public class LopHocIMG {
      private String maIMG;
      private String tenIMG;
      private String UrlIMG;

      public LopHocIMG() {
      }

      public LopHocIMG(String maIMG, String tenIMG, String UrlIMG) {
            this.maIMG = maIMG;
            this.tenIMG = tenIMG;
            this.UrlIMG = UrlIMG;
      }

      public String getMaIMG() {
            return maIMG;
      }

      public void setMaIMG(String maIMG) {
            this.maIMG = maIMG;
      }

      public String getTenIMG() {
            return tenIMG;
      }

      public void setTenIMG(String tenIMG) {
            this.tenIMG = tenIMG;
      }

      public String getUrlIMG() {
            return UrlIMG;
      }

      public void setUrlIMG(String UrlIMG) {
            this.UrlIMG = UrlIMG;
      }

      @Override
      public String toString() {
            return "LopHocIMG{" + "maIMG=" + maIMG + ", tenIMG=" + tenIMG + ", UrlIMG=" + UrlIMG + '}';
      }
      
      
}