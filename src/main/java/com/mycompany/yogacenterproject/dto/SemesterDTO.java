/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dto;

import java.sql.Date;

/**
 *
 * @author Oalskad
 */
public class SemesterDTO {
    private int quarterID; 
    private Date startDate;
    private Date endDate;
    private String courses;

    public SemesterDTO() {
    }

    public int getQuarterID() {
        return quarterID;
    }

    public void setQuarterID(int quarterID) {
        this.quarterID = quarterID;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getCourses() {
        return courses;
    }

    public void setCourses(String courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "SemesterDTO{" + "quarterID=" + quarterID + ", startDate=" + startDate + ", endDate=" + endDate + ", courses=" + courses + '}';
    }
    
    
    
}
