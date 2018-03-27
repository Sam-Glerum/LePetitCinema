package com.example.icadi.lepetitcinema.Presentation;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toolbar;

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

        films = new ArrayList<Film>();
        films.add(new Film("Film 1", "Film beschrijving", 120));
        films.add(new Film("Film 2", "Film beschrijving", 120));
        films.add(new Film("Film 3", "Film beschrijving", 120));
        filmAdapter = new FilmAdapter(getApplicationContext(), getLayoutInflater(), films);
        listView.setAdapter(filmAdapter);

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

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
