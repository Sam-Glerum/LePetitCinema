package com.example.icadi.lepetitcinema.Domain;

/**
 * Created by icadi on 27-3-18.
 */

public class Seat {
    private int number;
    private boolean isOccupied;

    public Seat(int number) {
        this.number = number;
        isOccupied = false;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }
}
