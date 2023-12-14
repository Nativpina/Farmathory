package com.naty.farmathory.model;


import java.io.Serializable;

public class PillDate implements Serializable {
    public static final String STATE_PENDING = "P";
    public static final String STATE_TAKEN = "T";
    public static final String STATE_NO_TAKEN = "N";
    public static final String STATE_CURRENT = "C";

    private String dateDosage;
    private String state;

    public PillDate() {
        // Default constructor required for calls to DataSnapshot.getValue(PillDate.class)
    }

    public PillDate(String dateDosage, String state) {
        this.dateDosage = dateDosage;
        this.state = state;
    }

    public String getDateDosage() {
        return dateDosage;
    }

    public void setDateDosage(String dateDosage) {
        this.dateDosage = dateDosage;
    }

    public String getState() {
        return state;
    }

    public String getValState() {
        String valState = "";
        switch (state) {
            case STATE_PENDING:
                valState = "Pendiente";
                break;
            case STATE_TAKEN:
                valState = "Tomado";
                break;
            case STATE_NO_TAKEN:
                valState = "No Tomado";
                break;
            case STATE_CURRENT:
                valState = "Actual";
                break;
        }

        return valState;
    }

    public void setState(String state) {
        this.state = state;
    }
}

