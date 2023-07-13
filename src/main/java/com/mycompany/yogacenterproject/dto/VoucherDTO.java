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

    private String voucherID, voucherName;
    private int multiplier;
    private int usageLimit;
    private int usageLimitPerUser;
    private int totalUsage;

    public VoucherDTO() {
    }

    public VoucherDTO(String voucherID, String voucherName, int multiplier, int usageLimit, int usageLimitPerUser, int totalUsage) {
        this.voucherID = voucherID;
        this.voucherName = voucherName;
        this.multiplier = multiplier;
        this.usageLimit = usageLimit;
        this.usageLimitPerUser = usageLimitPerUser;
        this.totalUsage = 0;
    }

    @Override
    public String toString() {
        return "VoucherDTO{" + "voucherID=" + voucherID + ", voucherName=" + voucherName + ", multiplier=" + multiplier + ", usageLimit=" + usageLimit + ", usageLimitPerUser=" + usageLimitPerUser + ", totalUsage=" + totalUsage + '}';
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

    public int getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public int getUsageLimit() {
        return usageLimit;
    }

    public void setUsageLimit(int usageLimit) {
        this.usageLimit = usageLimit;
    }

    public int getUsageLimitPerUser() {
        return usageLimitPerUser;
    }

    public void setUsageLimitPerUser(int usageLimitPerUser) {
        this.usageLimitPerUser = usageLimitPerUser;
    }

    public int getTotalUsage() {
        return totalUsage;
    }

    public void setTotalUsage(int totalUsage) {
        this.totalUsage = totalUsage;
    }

}
