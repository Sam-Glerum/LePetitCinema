package com.example.icadi.lepetitcinema.Presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.icadi.lepetitcinema.Domain.Film;
import com.example.icadi.lepetitcinema.Domain.Review;
import com.example.icadi.lepetitcinema.R;

public class ReviewActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView titleTextView;

    private TextInputEditText emailEnterTextView;
    private TextInputEditText enterReviewTextView;

    private Film film;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        setObjectVariables();
        setObjectVariablesContent();
    }

    /**
     * This method sets the object variables with the matching view components.
     */
    private void setObjectVariables() {
        film = (Film) getIntent().getSerializableExtra(MainActivity.FILM);

        titleTextView = findViewById(R.id.filmtitle);

        emailEnterTextView = findViewById(R.id.emailField);
        enterReviewTextView = findViewById(R.id.reviewfield);
        Button sendReview = findViewById(R.id.sendreviewbutton);

        sendReview.setOnClickListener(this);
    }

    /**
     * This method sets the content of the object variables.
     */
    private void setObjectVariablesContent() {
        titleTextView.setText(film.getName());
    }

    @Override
    public void onClick(View v) {
        composeReview();

        Intent toDetail = new Intent(getApplicationContext(), FilmDetailActivity.class);
        toDetail.putExtra(MainActivity.FILM, film);
        startActivity(toDetail);
    }

    private void composeReview() {
        String emailToDisplay = emailEnterTextView.getText().toString();
        String reviewToDisplay = enterReviewTextView.getText().toString();
        Review newReview = new Review(emailToDisplay, reviewToDisplay);
        film.reviewArrayList.add(newReview);
    }

}
