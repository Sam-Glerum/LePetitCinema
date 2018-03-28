package com.example.icadi.lepetitcinema.Presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.icadi.lepetitcinema.R;

public class SeatPickerActivity extends AppCompatActivity {

    ImageView[][] seatImageViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_picker);
        seatImageViews = getAllSeats();

        // Calculate and log the size of the 2d array
        int sizeOfSeatImageViews = (seatImageViews[0].length + seatImageViews[1].length +
        seatImageViews[3].length + seatImageViews[4].length + seatImageViews[5].length +
                seatImageViews[0].length);
        Log.i("SeatPickerActivity","size:" + sizeOfSeatImageViews);
    }

    /**
     * Returns the ImageViews of the seats displayed in the activity.
     * @return A 2d array of ImageViews.
     */
    public ImageView[][] getAllSeats() {
        ImageView[][] imageViews = new ImageView[6][9];

        for (int i = 0; i < 6; i++) {
            for (int ii = 0; ii < 9; ii++) {
                String seatId = ("r" + (i + 1) + "s" + (ii + 1));
                int resId = getResources().getIdentifier(seatId, "id", getPackageName());
                ImageView imageView = (ImageView) findViewById(resId);
                imageViews[i][ii]= imageView;
            }
        }

        return imageViews;
    }
}
