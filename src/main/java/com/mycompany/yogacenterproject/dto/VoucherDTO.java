/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dto;

/**
 *
 * @author iba
 */
public class VoucherDTO {
    private String voucherID,voucherName;
    private long multiplier;

    public VoucherDTO(String voucherID, String voucherName, long multiplier) {
        this.voucherID = voucherID;
        this.voucherName = voucherName;
        this.multiplier = multiplier;
    }

    public VoucherDTO() {
    }

    public String getVoucherID() {
        return voucherID;
    }

    public void setVoucherID(String voucherID) {
        this.voucherID = voucherID;
    }

    public String getVoucherName() {
        return voucherName;
    }

    public void setVoucherName(String voucherName) {
        this.voucherName = voucherName;
    }

    public float getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(long multiplier) {
        this.multiplier = multiplier;
    }

    @Override
    public String toString() {
        return "VoucherDTO{" + "voucherID=" + voucherID + ", voucherName=" + voucherName + ", multiplier=" + multiplier + '}';
    }
    
}
