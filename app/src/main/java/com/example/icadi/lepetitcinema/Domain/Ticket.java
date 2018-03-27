package com.example.icadi.lepetitcinema.Domain;

import java.io.File;

/**
 * Created by icadi on 27-3-18.
 */

public class Ticket {
    private String qrCodeData;
    private File qrCodeImage;
    private String date;
    private String time;

    public Ticket(String qrCodeData, String date, String time) {
        this.qrCodeData = qrCodeData;
        this.date = date;
        this.time = time;
    }

    public String getQrCodeData() {
        return qrCodeData;
    }

    public void setQrCodeData(String qrCodeData) {
        this.qrCodeData = qrCodeData;
    }

    public File getQrCodeImage() {
        return qrCodeImage;
    }

    public void setQrCodeImage(File qrCodeImage) {
        this.qrCodeImage = qrCodeImage;
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
