package com.example.icadi.lepetitcinema.Presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.icadi.lepetitcinema.ApplicationLogic.ImageManager;
import com.example.icadi.lepetitcinema.Domain.Film;
import com.example.icadi.lepetitcinema.Domain.Seat;
import com.example.icadi.lepetitcinema.R;

import java.util.ArrayList;

public class PaymentSimulationActivity extends AppCompatActivity implements View.OnClickListener{

    private Intent intent;

    private ImageView filmImage;
    private TextView filmTitle;
    private TextView amountOfTickets;
    private TextView cinemaRoom;
    private TextView seatNumber;
    private TextView price;
    private Button payTicketButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_simulation);

        // Set the object variables
        setObjectVariables();

        // Set the content of the object variables
        setViewComponentContent();
    }

    /**
     * This method sets the object variables by calling
     * the findViewById() method for every view component.
     */
    private void setObjectVariables() {
        Log.d("PaySimulationActivity", "setObjectVariables: inside method");

        filmImage = findViewById(R.id.payment_simulation_activity_film_image);
        filmTitle = findViewById(R.id.payment_simulation_activity_film_title);
        amountOfTickets = findViewById(R.id.payment_simulation_activity_amount_of_tickets);
        cinemaRoom = findViewById(R.id.payment_simulation_activity_cinema_room);
        seatNumber = findViewById(R.id.payment_simulation_activity_seat_numbers);
        price = findViewById(R.id.payment_simulation_activity_price);
        payTicketButton = findViewById(R.id.payment_simulation_activity_pay_ticket_button);
    }

    /**
     * This method sets the content of the view components.
     * This is done by getting the setText() method for every object variable.
     */
    @SuppressWarnings("unchecked")
    private void setViewComponentContent() {
        Log.d("PaySimulationActivity", "setViewComponentContent: inside method");

        intent = getIntent();

        new ImageManager(filmImage).execute(intent.getStringExtra(SeatPickerActivity.FILM_IMAGE));

        filmTitle.setText(intent.getStringExtra(SeatPickerActivity.FILMTITLE));

        amountOfTickets.setText(getString(R.string.paymentSimulationAmountTickets) + intent.getStringExtra(SeatPickerActivity.AMOUNTOFTICKETS));
        ArrayList<Seat> selectedSeats = (ArrayList<Seat>) intent.getSerializableExtra(SeatPickerActivity.SEATS);

        String seatNumbers = "";

        for (int i = 0; i < selectedSeats.size(); i++) {
            seatNumbers += selectedSeats.get(i).getNumber();
            if (i != selectedSeats.size()-1) {
                seatNumbers += ", ";
            } else {
                seatNumbers += "";
            }
        }

        seatNumber.setText(getString(R.string.paymentSimulationSeats) + " " + seatNumbers);
        price.setText("€ " + intent.getDoubleExtra(SeatPickerActivity.PRICE, 0.0));

        payTicketButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(getApplicationContext(), SuccessPaymentActivity.class));
    }
}
