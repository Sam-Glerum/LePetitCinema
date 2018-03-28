package com.example.icadi.lepetitcinema.Domain;

import java.io.Serializable;

/**
 * Created by icadi on 27-3-18.
 */

public class Seat implements Serializable{
    private String number;
    private boolean isOccupied;

    public Seat(String number) {
        this.number = number;
        isOccupied = false;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }
}
