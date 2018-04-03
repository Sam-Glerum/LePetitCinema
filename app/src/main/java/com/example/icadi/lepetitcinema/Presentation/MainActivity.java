package com.example.icadi.lepetitcinema.Presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.icadi.lepetitcinema.ApplicationLogic.APIManager;
import com.example.icadi.lepetitcinema.Domain.Film;
import com.example.icadi.lepetitcinema.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements APIManager.OnFilmAvailable,
        AdapterView.OnItemClickListener,
        NavigationView.OnNavigationItemSelectedListener{

    public static String FILM = "FILM";

    private DrawerLayout mDrawerLayout;

    private ListView listView;

    // ArrayList to store films
    private ArrayList<Film> films;

    // Adapter used to show films from films ArrayList
    private FilmAdapter filmAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar((android.support.v7.widget.Toolbar) findViewById(R.id.toolbar));

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        listView = findViewById(R.id.filmList);
        mDrawerLayout = findViewById(R.id.drawer_layout);

        // Fill arraylist with testdata
        films = new ArrayList<>();

        // Initalize the filmAdapter
        filmAdapter = new FilmAdapter(getApplicationContext(), getLayoutInflater(), films);

        // Link the filmAdapter to the listView
        listView.setAdapter(filmAdapter);

        // Set an onItemClickListener, which directs the user to the DetailActivity Page of the Film
        listView.setOnItemClickListener(this);

        String[] urls = new String[]{"https://api.themoviedb.org/3/discover/movie?api_key=21900c2d8963dfde03c91e3bddc6009b"};
        APIManager apiManager = new APIManager(this);
        apiManager.execute(urls);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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


    @Override
    public void onFilmAvailable(Film film) {
        if (!films.contains(film)) {
            films.add(film);
            Log.i("MainActivity", "Film added (" + film.toString() + ")");
            filmAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(getApplicationContext(), FilmDetailActivity.class);
        intent.putExtra(FILM, films.get(i));
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // set item as selected to persist highlight
        item.setChecked(true);
        System.out.println("Item" + item.getItemId());

        switch (item.getItemId()) {

            case R.id.etickets:
                // Open the E-Ticket page.
                startActivity(new Intent(getApplicationContext(), ETicketsActivity.class));
                break;

            case R.id.contact:
                // open the contact page when the contact item is selected
                startActivity(new Intent(getApplicationContext(), ContactActivity.class));
                break;

        }
        // close the drawer after the contact item is selected
        mDrawerLayout.closeDrawers();
        return true;
    }
}
