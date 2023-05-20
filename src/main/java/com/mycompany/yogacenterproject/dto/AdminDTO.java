package com.mycompany.yogacenterproject.dto;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Oalskad
 */
public class AdminDTO {

    private String maADmin;
    private String userName;
    private String psw;

    public AdminDTO() {
    }

    public AdminDTO(String maADmin, String userName, String psw) {
        this.maADmin = maADmin;
        this.userName = userName;
        this.psw = psw;
    }

    public String getMaADmin() {
        return maADmin;
    }

    public void setMaADmin(String maADmin) {
        this.maADmin = maADmin;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    @Override
    public String toString() {
        return "AdminDTO{" + "maADmin=" + maADmin + ", userName=" + userName + ", psw=" + psw + '}';
    }

}
