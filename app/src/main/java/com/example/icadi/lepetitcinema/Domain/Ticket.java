package com.example.icadi.lepetitcinema.Domain;

import java.io.File;

/**
 * Created by icadi on 27-3-18.
 */

public class Ticket {
    private int id;
    private String filmName;
    private String seatNumber;
    private String cinemaRoom;
    private String qrCodeData;
    private File qrCodeImage;
    private String date;
    private String time;

    public Ticket(String filmName, String seatNumber, String cinemaRoom, String qrCodeData, File qrCodeImage, String date, String time) {
        this.filmName = filmName;
        this.seatNumber = seatNumber;
        this.cinemaRoom = cinemaRoom;
        this.qrCodeData = qrCodeData;
        this.qrCodeImage = qrCodeImage;
        this.date = date;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getCinemaRoom() {
        return cinemaRoom;
    }

    public void setCinemaRoom(String cinemaRoom) {
        this.cinemaRoom = cinemaRoom;
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
