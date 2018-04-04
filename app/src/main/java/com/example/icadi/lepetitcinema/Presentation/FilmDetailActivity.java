package com.example.icadi.lepetitcinema.Presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.icadi.lepetitcinema.Domain.Film;
import com.example.icadi.lepetitcinema.Domain.Review;
import com.example.icadi.lepetitcinema.R;
import com.squareup.picasso.Picasso;

public class FilmDetailActivity extends AppCompatActivity implements View.OnClickListener{

    private Film film;

    private TextView filmTitle;
    private TextView filmDescription;
    private TextView reviews;
    private TextView rating;

    private FloatingActionButton buyTicketsButton;
    private FloatingActionButton sendReviewButton;
    private ImageView filmBackgroundImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail);

        setObjectVariables();

        film = (Film) getIntent().getSerializableExtra(MainActivity.FILM);

        setObjectVariablesContent();

        // Set the on click listener of the order FAB.
        buyTicketsButton.setOnClickListener(this);
        sendReviewButton.setOnClickListener(this);

    }

    /**
     * This method sets the object variables with the matching view components.
     */
    private void setObjectVariables() {
        filmTitle = findViewById(R.id.detail_activity_film_title);
        filmDescription = findViewById(R.id.detail_activity_film_description);
        buyTicketsButton = findViewById(R.id.detail_activity_order_ticket_fab);
        filmBackgroundImage = findViewById(R.id.detail_activity_film_image);
        sendReviewButton = findViewById(R.id.detail_activity_review_fab);
        reviews = findViewById(R.id.detail_activity_reviews);
        rating = findViewById(R.id.detail_activity_rating);
    }

    /**
     * This method sets the content of the object variables.
     */
    private void setObjectVariablesContent() {
        Picasso
                .with(getApplicationContext())
                .load(film.getPosterImageUrl())
                .into(filmBackgroundImage);

        filmTitle.setText(film.getName());
        filmDescription.setText(film.getDescription());

        String allReviewsInString = "";
        for (Review review : film.getReviewArrayList()) {
            allReviewsInString += review;
        }

        reviews.setText(allReviewsInString);
        rating.setText("Waardering: " + film.getRating() + "/10");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.detail_activity_order_ticket_fab:
                Intent toSeatPicker = new Intent(getApplicationContext(), SeatPickerActivity.class);
                toSeatPicker.putExtra(SeatPickerActivity.FILMTITLE, film.getName());
                toSeatPicker.putExtra(SeatPickerActivity.FILM_IMAGE, film.getBackgroundImageUrl());
                startActivity(toSeatPicker);
                break;

            case R.id.detail_activity_review_fab:
                Intent toReview = new Intent(getApplicationContext(), ReviewActivity.class);
                toReview.putExtra(MainActivity.FILM, film);
                startActivity(toReview);
                break;
        }
    }
}
