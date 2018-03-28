package com.example.icadi.lepetitcinema.Presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.icadi.lepetitcinema.Domain.Film;
import com.example.icadi.lepetitcinema.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    private ListView listView;
    // ArrayList to store films
    private ArrayList<Film> films;
    // Adapter used to show films from films ArrayList
    private FilmAdapter filmAdapter;
    // DB Handler

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar((android.support.v7.widget.Toolbar) findViewById(R.id.toolbar));

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        listView = (ListView) findViewById(R.id.filmList);


        mDrawerLayout = findViewById(R.id.drawer_layout);


        // Fill arraylist with testdata
        films = new ArrayList<Film>();
        films.add(new Film("Film 1", "Film beschrijving", 120));
        films.add(new Film("Film 2", "Film beschrijving", 120));
        films.add(new Film("Film 3", "Film beschrijving", 120));
        films.add(new Film("Film 4", "Film beschrijving", 120));
        films.add(new Film("Film 5", "Film beschrijving", 120));
        films.add(new Film("Film 6", "Film beschrijving", 120));
        films.add(new Film("Film 7", "Film beschrijving", 120));
        films.add(new Film("Film 8", "Film beschrijving", 120));
        films.add(new Film("Film 9", "Film beschrijving", 120));

        // Initalize the filmAdapter
        filmAdapter = new FilmAdapter(getApplicationContext(), getLayoutInflater(), films);
        // Link the filmAdapter to the listView
        listView.setAdapter(filmAdapter);

        // Set an onItemClickListener, which directs the user to the DetailActivity Page of the Film
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), FilmDetailActivity.class);
                intent.putExtra("Film", films.get(i));
                startActivity(intent);
            }
        });

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                // set item as selected to persist highlight
                menuItem.setChecked(true);
                System.out.println("Item" + menuItem.getItemId());
                switch (menuItem.getItemId()) {
                    case R.id.contact :
                        // open the contact page when the contact item is selected
                        startActivity(new Intent(getApplicationContext(), ContactActivity.class));
                }
                // close the drawer after the contact item is selected
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                // Open the drawer when the hamburger-menu is tapped
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
