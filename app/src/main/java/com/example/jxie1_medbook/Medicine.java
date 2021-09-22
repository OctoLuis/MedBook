package com.example.jxie1_medbook;

import java.util.Date;

//    Class: Medicine
public class Medicine {
    // Class members:
    private Date dateStart;
    private String name;
    private Integer doseAmount;
    private String doseUnit;
    private Integer dailyFrequency;

    // Constructor:
    public Medicine(Date start, String name, Integer amount, String unit, Integer freq) {
        this.dateStart = start;
        this.setName(name);
        this.doseAmount = amount;
        this.doseUnit = unit;
        this.dailyFrequency = freq;
    }

    // Setters and Getters:
    public void setDateStart(Date start) {
        this.dateStart = start;
    }

    public Date getDateStart() {
        return this.dateStart;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setDoseAmount(Integer amount) {
        this.doseAmount = amount;
    }

    public Integer getDoseAmount() {
        return this.doseAmount;
    }

    public void setDoseUnit(String unit) {
        this.doseUnit = unit;
    }

    public String getDoseUnit() {
        return this.doseUnit;
    }

    public void setDailyFrequency(Integer freq) {
        this.dailyFrequency = freq;
    }

    public Integer getDailyFrequency() {
        return this.dailyFrequency;
    }

}
