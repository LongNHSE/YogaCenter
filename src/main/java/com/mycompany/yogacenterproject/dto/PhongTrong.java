/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dto;

/**
 *
 * @author Oalskad
 */
public class PhongTrong {
    private int soPhongTrong;
    private String maSlot;
    private String thu;

    public PhongTrong() {
    }

    public PhongTrong(int soPhongTrong, String maSlot, String thu) {
        this.soPhongTrong = soPhongTrong;
        this.maSlot = maSlot;
        this.thu = thu;
    }

    public int getSoPhongTrong() {
        return soPhongTrong;
    }

    public void setSoPhongTrong(int soPhongTrong) {
        this.soPhongTrong = soPhongTrong;
    }

    public String getMaSlot() {
        return maSlot;
    }

    public void setMaSlot(String maSlot) {
        this.maSlot = maSlot;
    }

    public String getThu() {
        return thu;
    }

    public void setThu(String thu) {
        this.thu = thu;
    }
    
}
