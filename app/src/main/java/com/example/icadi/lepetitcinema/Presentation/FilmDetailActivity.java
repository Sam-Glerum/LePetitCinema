package com.example.icadi.lepetitcinema.Presentation;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.icadi.lepetitcinema.ApplicationLogic.ImageManager;
import com.example.icadi.lepetitcinema.Domain.Film;
import com.example.icadi.lepetitcinema.R;

public class FilmDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private Film film;

    private TextView filmTitle;
    private TextView filmDescription;
    private ImageView filmBackgroundImage;
    private Button buyTicketsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail);

        setObjectVariables();
        
        film = (Film) getIntent().getSerializableExtra(MainActivity.FILM);

        setObjectVariablesContent();
        buyTicketsButton = (Button)findViewById(R.id.filmDetail_button_buyTickets);
        buyTicketsButton.setOnClickListener(this);
    }

    /**
     * This method sets the object variables with the matching view components.
     */
    private void setObjectVariables() {
        filmTitle = findViewById(R.id.film_detail_title);
        filmDescription = findViewById(R.id.film_detail_textView_description);
        filmBackgroundImage = (ImageView) findViewById(R.id.filmDetail_imageView_film);
    }

    private void setObjectVariablesContent() {
        filmTitle.setText(film.getName());
        filmDescription.setText(film.getDescription());
        new ImageManager(filmBackgroundImage).execute(film.getBackgroundImageUrl());
        filmBackgroundImage.setImageURI(Uri.parse(film.getBackgroundImageUrl()));

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.filmDetail_button_buyTickets:
                Intent toSeatPicker = new Intent(getApplicationContext(), SeatPickerActivity.class);
                toSeatPicker.putExtra(SeatPickerActivity.FILMTITLE, film.getName());
                startActivity(toSeatPicker);
                break;

            case R.id.filmdetail_button_writeReview:
                break;
        }
    }
}
