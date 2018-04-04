package com.example.icadi.lepetitcinema.Presentation;

import android.widget.ImageView;

import com.example.icadi.lepetitcinema.Domain.Seat;

public class SeatImageViewBundle {
    private Seat seat;
    private ImageView imageView;

    public SeatImageViewBundle(Seat seat, ImageView imageView) {
        this.seat = seat;
        this.imageView = imageView;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
