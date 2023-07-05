/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dto;

/**
 *
 * @author devli
 */
public class BLogCate {
      private String maCate;
      private String tenCate;

      public BLogCate() {
      }

      public BLogCate(String maCate, String tenCate) {
            this.maCate = maCate;
            this.tenCate = tenCate;
      }

      public String getMaCate() {
            return maCate;
      }

      public void setMaCate(String maCate) {
            this.maCate = maCate;
      }

      public String getTenCate() {
            return tenCate;
      }

      public void setTenCate(String tenCate) {
            this.tenCate = tenCate;
      }

      @Override
      public String toString() {
            return "BLogCate{" + "maCate=" + maCate + ", tenCate=" + tenCate + '}';
      }
      
      
}
