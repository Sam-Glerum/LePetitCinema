package com.example.icadi.lepetitcinema.Presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.icadi.lepetitcinema.R;

public class ETicketDetailActivity extends AppCompatActivity {

    private TextView filmTitle;
    private TextView seatNumber;
    private TextView dateTime;
    private TextView cinemaRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eticket_detail);

        setObjectVariables();

        setContentObjectVariables();
    }

    /**
     * This method sets the object variables with the matching view components
     */
    private void setObjectVariables() {
        filmTitle = findViewById(R.id.eTicketFilmTitleDetail);
        seatNumber = findViewById(R.id.eTicketSeatNumberDetail);
        dateTime = findViewById(R.id.eTicketDateTimeDetail);
        cinemaRoom = findViewById(R.id.eTicketCinemaRoomDetail);
    }

    /**
     * This method sets the content of the view components
     */
    private void setContentObjectVariables() {
        Intent extras = getIntent();

        filmTitle.setText(extras.getStringExtra(ETicketsActivity.E_TICKET_FILM_NAME));
        seatNumber.setText("Seat number: " + extras.getStringExtra(ETicketsActivity.E_TICKET_SEAT_NUMBER));
        dateTime.setText("Date + time: " + extras.getStringExtra(ETicketsActivity.E_TICKET_DATE_TIME));
        cinemaRoom.setText("Cinema Room: " + extras.getStringExtra(ETicketsActivity.E_TICKET_CINEMA_ROOM));
    }
}
