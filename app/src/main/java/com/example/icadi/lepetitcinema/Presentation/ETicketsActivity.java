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

        Ticket jumanji = new Ticket("Jumanji", "1", "2", "", null, "29-3-2018", "16:00");
        Ticket fiftyShadesFreed = new Ticket("Fifty Shades Freed", "4", "2", "", null, "1-4-2018", "15:25");
        Ticket zootopia = new Ticket("Zootopia", "3", "1", "", null, "3-4-2018", "14:30");
        Ticket starWars = new Ticket("Star Wars: The Last Jedi", "26", "1", "", null, "5-4-2018", "13:20");
        Ticket blackPanther = new Ticket("Black Panther", "43", "2", "", null, "4-4-2018", "14:00");
        Ticket coco = new Ticket("Coco", "34", "2", "", null, "6-4-2018", "10:00");
        Ticket readyPlayerOne = new Ticket("Ready Player One", "48", "2", "", null, "4-4-2018", "14:00");
        Ticket tombRaider = new Ticket("Tomb Raider", "22", "1", "", null, "8-4-2018", "18:00");
        Ticket thorRagnarok = new Ticket("Thor: Ragnarok", "30", "1", "", null, "8-4-2018", "22:00");
        tickets.add(jumanji);
        tickets.add(fiftyShadesFreed);
        tickets.add(zootopia);
        tickets.add(starWars);
        tickets.add(blackPanther);
        tickets.add(coco);
        tickets.add(readyPlayerOne);
        tickets.add(tombRaider);
        tickets.add(thorRagnarok);


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
