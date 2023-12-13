package com.naty.farmathory;


public class Pill {
    private String pillName;
    private int dosage;

    public Pill(String pillName, int dosage) {
        this.pillName = pillName;
        this.dosage = dosage;
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
}

