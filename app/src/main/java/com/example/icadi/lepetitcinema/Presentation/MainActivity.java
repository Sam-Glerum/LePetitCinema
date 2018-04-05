package com.example.icadi.lepetitcinema.Presentation;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toolbar;

import com.example.icadi.lepetitcinema.Domain.Film;
import com.example.icadi.lepetitcinema.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements APIManager.OnFilmAvailable,
        AdapterView.OnItemClickListener,
        NavigationView.OnNavigationItemSelectedListener,
        View.OnClickListener {

    private DrawerLayout mDrawerLayout;

    private ListView listView;
    private EditText searchBar;
    private Button searchButton;

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
        searchBar = findViewById(R.id.main_activity_search_bar);
        searchButton = findViewById(R.id.main_activity_search_button);
        searchButton.setOnClickListener(this);

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

        filmAdapter = new FilmAdapter(getApplicationContext(), getLayoutInflater(), films);
        listView.setAdapter(filmAdapter);

        // Set an onItemClickListener, which directs the user to the DetailActivity Page of the Film
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), FilmDetailActivity.class);
                intent.putExtra("Film", films.get(i).getName());
                startActivity(intent);
            }
        });

        callAPI();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                // set item as selected to persist highlight
                menuItem.setChecked(true);
                // close drawer when item is tapped
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    public void callAPI() {
        String[] urls = new String[]{"https://api.themoviedb.org/3/discover/movie?api_key=21900c2d8963dfde03c91e3bddc6009b"};
        APIManager apiManager = new APIManager(this);
        apiManager.execute(urls);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
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

    @Override
    public void onClick(View view) {
        ArrayList<Film> searchResults = new ArrayList<>();

        String searQuery = searchBar.getText().toString();

        if (!searQuery.equals("")) {
            for (Film film : films) {
                if (film.getName().toLowerCase().contains(searQuery.toLowerCase())) {
                    searchResults.add(film);
                }
            }
            searchBar.setText("");
            films.clear();
            films.addAll(searchResults);
            filmAdapter.notifyDataSetChanged();
        } else {
            films.clear();
            callAPI();
        }
    }
}
