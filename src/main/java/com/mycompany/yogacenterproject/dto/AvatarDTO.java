/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dto;

/**
 *
 * @author Oalskad
 */
public class AvatarDTO {
    private String maAvatar;
    private String image;
    private String maHV;
    private String maTrainer;

    public AvatarDTO() {
    }

    public String getMaAvatar() {
        return maAvatar;
    }

    public void setMaAvatar(String maAvatar) {
        this.maAvatar = maAvatar;
    }

    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "AvatarDTO{" + "maAvatar=" + maAvatar + ", image=" + image + ", maHV=" + maHV + ", maTrainer=" + maTrainer + '}';
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMaHV() {
        return maHV;
    }

    public void setMaHV(String maHV) {
        this.maHV = maHV;
    }

    public String getMaTrainer() {
        return maTrainer;
    }

    public void setMaTrainer(String maTrainer) {
        this.maTrainer = maTrainer;
    }
    
}
