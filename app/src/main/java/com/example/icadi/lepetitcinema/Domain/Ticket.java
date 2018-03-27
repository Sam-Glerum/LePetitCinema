package com.example.icadi.lepetitcinema.Domain;

/**
 * Created by icadi on 27-3-18.
 */

public class Ticket {
    private int[][] qrCode;
    private String date;
    private String time;

    public Ticket(int[][] qrCode, String date, String time) {
        this.qrCode = qrCode;
        this.date = date;
        this.time = time;
    }

    public int[][] getQrCode() {
        return qrCode;
    }

    public void setQrCode(int[][] qrCode) {
        this.qrCode = qrCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
