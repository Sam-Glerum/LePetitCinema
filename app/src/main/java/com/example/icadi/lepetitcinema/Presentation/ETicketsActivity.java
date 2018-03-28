package com.example.icadi.lepetitcinema.Presentation;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.icadi.lepetitcinema.Domain.Ticket;
import com.example.icadi.lepetitcinema.R;

import java.util.ArrayList;

public class ETicketsActivity extends AppCompatActivity {

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
    }
}
