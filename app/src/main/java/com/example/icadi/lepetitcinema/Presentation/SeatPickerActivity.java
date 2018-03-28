package com.example.icadi.lepetitcinema.Presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.icadi.lepetitcinema.Domain.Seat;
import com.example.icadi.lepetitcinema.R;

import java.util.ArrayList;

public class SeatPickerActivity extends AppCompatActivity implements View.OnClickListener {

    public final static String SEATS = "SEATS";
    public final static String FILMTITLE = "FILMTITLE";
    public final static String AMOUNTOFTICKETS = "AMOUNTOFTICKETS";
    public final static String PRICE = "PRICE";
//    public final static String CINEMAROOMNR = "CINEMAROOMNR";

    private ImageView[][] seatImageViews;
    private ArrayList<Seat> currentlySelectedSeats = new ArrayList<>();
    private TextView amountOfSeatsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_picker);

        amountOfSeatsTextView = (TextView) findViewById(R.id.seatPicker_textView_amountOfSeats);

        // Create 2d array for the ImageViews
        seatImageViews = getAllSeats();

        // Calculate and log the size of the 2d array
        int sizeOfSeatImageViews = (seatImageViews[0].length + seatImageViews[1].length +
                seatImageViews[3].length + seatImageViews[4].length + seatImageViews[5].length +
                seatImageViews[0].length);
        Log.i("SeatPickerActivity", "size:" + sizeOfSeatImageViews);
    }

    /**
     * Returns the ImageViews of the seats displayed in the activity.
     *
     * @return A 2d array of ImageViews.
     */
    public ImageView[][] getAllSeats() {
        ImageView[][] imageViews = new ImageView[6][9];

        for (int i = 0; i < 6; i++) {
            for (int ii = 0; ii < 9; ii++) {
                String seatId = ("r" + (i + 1) + "s" + (ii + 1));
                int resId = getResources().getIdentifier(seatId, "id", getPackageName());
                ImageView imageView = (ImageView) findViewById(resId);
                imageView.setOnClickListener(this);
                imageViews[i][ii] = imageView;
            }
        }
        return imageViews;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.seatPicker_button_buyTickets:
//                Intent toPayment = new Intent(getApplicationContext(), PaymentSimulationActivity.class);
//                toPayment.putExtra(SEATS, currentlySelectedSeats);
//                toPayment.putExtra(FILMTITLE, idk)
//                toPayment.putExtra(PRICE, price);
//                toPayment.putExtra(AMOUNTOFTICKETS, currentlySelectedSeats.size());
                break;

            default:
                ImageView imageView = findViewById(view.getId());
                if (imageView.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.cinemaseat_available).getConstantState())) {
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.cinemaseat_selected));
                    Seat seat = new Seat(getResources().getResourceEntryName(view.getId()));
                    currentlySelectedSeats.add(seat);
                    Log.i("SeatPickerActivity", "Added seat: " + seat.getNumber() + " to current selection, size is now: " + currentlySelectedSeats.size());

                } else if (imageView.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.cinemaseat_selected).getConstantState())) {
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.cinemaseat_available));
                    String seatNumber = getResources().getResourceEntryName(view.getId());

                    for (int i = 0; i < currentlySelectedSeats.size(); i++) {
                        Seat seat = currentlySelectedSeats.get(i);
                        if (seat.getNumber().equals(seatNumber)) {
                            currentlySelectedSeats.remove(seat);
                            Log.i("SeatPickerActivity", "Removed seat: " + seat.getNumber() + " from current selection, size is now: " + currentlySelectedSeats.size());
                        }
                    }

                } else if (imageView.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.cinemaseat_occupied).getConstantState())) {
                    Log.i("SeatPickerActivity", "The chosen seat is already occupied!");
                } else {
                    Log.i("SeatPickerActivity", "ERROR: Could not get drawable to check!");
                }

                amountOfSeatsTextView.setText("" + currentlySelectedSeats.size());

        }
    }

    public ImageView[][] getSeatImageViews() {
        return seatImageViews;
    }

    public void setSeatImageViews(ImageView[][] seatImageViews) {
        this.seatImageViews = seatImageViews;
    }

    public ArrayList<Seat> getCurrentlySelectedSeats() {
        return currentlySelectedSeats;
    }

    public void setCurrentlySelectedSeats(ArrayList<Seat> currentlySelectedSeats) {
        this.currentlySelectedSeats = currentlySelectedSeats;
    }
}
