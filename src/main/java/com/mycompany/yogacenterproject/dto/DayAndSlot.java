/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dto;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author Oalskad
 */
public class DayAndSlot {

    private List<String> day;
    private String slot;
    private String timeStart;
    private String timeEnd;

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public DayAndSlot() {
    }

    public List<String> getDay() {
        return day;
    }

    public void setDay(List<String> day) {
        this.day = day;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DayAndSlot that = (DayAndSlot) o;
        return Objects.equals(day, that.day)
                && Objects.equals(slot, that.slot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, slot);
    }

    @Override
    public String toString() {
        return "DayAndSlot{" + "day=" + day + ", slot=" + slot + ", timeStart=" + timeStart + ", timeEnd=" + timeEnd + '}';
    }

   

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }
}
