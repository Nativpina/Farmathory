package com.naty.farmathory;


public class Pill {
    private String pillName;
    private int dosage;
    private String key;

    public Pill() {
        // Default constructor required for calls to DataSnapshot.getValue(Pill.class)
    }

    public Pill(String pillName, int dosage, String key) {
        this.pillName = pillName;
        this.dosage = dosage;
        this.key = key;
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
}

