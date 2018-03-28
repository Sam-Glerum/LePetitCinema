package com.example.icadi.lepetitcinema.Presentation;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.icadi.lepetitcinema.Domain.Ticket;
import com.example.icadi.lepetitcinema.R;

import java.util.ArrayList;

public class ETicketsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    public static String E_TICKET_FILM_NAME = "E_TICKET_FILM_NAME";
    public static String E_TICKET_SEAT_NUMBER = "E_TICKET_SEAT_NUMBER";
    public static String E_TICKET_CINEMA_ROOM = "E_TICKET_CINEMA_ROOM";
    public static String E_TICKET_DATE_TIME = "E_TICKET_DATE_TIME";

    private DrawerLayout drawerLayout;
    private ListView listView;
    private ArrayList<Ticket> tickets = new ArrayList<>();
    private ETicketsAdapter eTicketsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etickets);

        listView = findViewById(R.id.ticketsListView);

        Ticket ticket1 = new Ticket("Jumanji", "1", "3", "", null, "29-3-2018", "16:00");

        tickets.add(ticket1);

        eTicketsAdapter = new ETicketsAdapter(getApplicationContext(), getLayoutInflater(), tickets);
        listView.setAdapter(eTicketsAdapter);

        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent toDetail = new Intent(getApplicationContext(), ETicketDetailActivity.class);

        toDetail.putExtra(E_TICKET_FILM_NAME, tickets.get(i).getFilmName());
        toDetail.putExtra(E_TICKET_SEAT_NUMBER, tickets.get(i).getSeatNumber());
        toDetail.putExtra(E_TICKET_DATE_TIME, tickets.get(i).getDate() + " " + tickets.get(i).getTime());
        toDetail.putExtra(E_TICKET_CINEMA_ROOM, tickets.get(i).getCinemaRoom());

        startActivity(toDetail);
    }
}
