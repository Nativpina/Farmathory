package com.naty.farmathory.model;


import java.io.Serializable;
import java.util.List;

public class Pill implements Serializable {
    private String pillName;
    private int dosage;
    private String key;
    private List<PillDate> pillDates;

    public Pill() {
        // Default constructor required for calls to DataSnapshot.getValue(Pill.class)
    }

    public Pill(String pillName, int dosage, String key, List<PillDate> pillDates) {
        this.pillName = pillName;
        this.dosage = dosage;
        this.key = key;
        this.pillDates = pillDates;
    }

    public String getPillName() {
        return pillName;
    }

    public void setPillName(String pillName) {
        this.pillName = pillName;
    }

    public int getDosage() {
        return dosage;
    }

    public void setDosage(int dosage) {
        this.dosage = dosage;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<PillDate> getPillDates() {
        return pillDates;
    }

    public void setPillDates(List<PillDate> pillDates) {
        this.pillDates = pillDates;
    }
}

