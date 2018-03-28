package com.example.icadi.lepetitcinema.Presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.icadi.lepetitcinema.R;

import java.util.ArrayList;

public class SeatPickerActivity extends AppCompatActivity {

    ArrayList<ImageView> seats = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_picker);
        seats = getAllSeats();
        Log.i("SeatPickerActivity","size = " + seats.size());
    }

    public ArrayList<ImageView> getAllSeats() {
        ArrayList<ImageView> imageViews = new ArrayList<>();

        int seatNumber = 1;
        int rowNumber = 1;

        for (int i = 0; i < 6; i++) {
            for (int ii = 0; ii < 9; ii++) {
                String seatId = ("r" + rowNumber + "s" + seatNumber);
                int resId = getResources().getIdentifier(seatId, "id", getPackageName());
                ImageView imageView = (ImageView) findViewById(resId);
                imageViews.add(imageView);
            }
            seatNumber = 1;
            rowNumber++;
        }

        return imageViews;
    }
}
