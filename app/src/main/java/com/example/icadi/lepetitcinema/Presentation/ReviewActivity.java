package com.example.icadi.lepetitcinema.Presentation;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import com.example.icadi.lepetitcinema.R;

public class ReviewActivity extends AppCompatActivity {


    private TextView headerTextView;
    private TextView titleTextView;
    private String filmtitle;

    private TextView emailTextview;
    private TextInputEditText emailEnterTextView;

    private TextView enterReviewHeaderTextview;
    private TextInputEditText enterReviewTextView;

    private Button sendReview;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        setObjectVariables();
        filmtitle = (String) getIntent().getSerializableExtra(FilmDetailActivity.FILMTITLE);
        setObjectVariablesContent();

    }

    /**
     * This method sets the object variables with the matching view components.
     */
    private void setObjectVariables() {
        headerTextView = findViewById(R.id.reviewact_title);
        titleTextView = findViewById(R.id.filmtitle);
        emailTextview = findViewById(R.id.email);

        emailEnterTextView = findViewById(R.id.emailField);
        enterReviewTextView = findViewById(R.id.reviewfield);
        sendReview = findViewById(R.id.sendreviewbutton);
    }

    /**
     * This method sets the content of the object variables.
     */
    private void setObjectVariablesContent() {
        titleTextView.setText(filmtitle);
    }
}
