package com.example.icadi.lepetitcinema.Domain;

import java.io.Serializable;

public class Review implements Serializable{

    private String reviewText;
    private String emailAddress;

    public String getReviewText() {
        return reviewText;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public Review (String emailAddress, String reviewText){
        this.emailAddress = emailAddress;
        this.reviewText=reviewText;

    }

    @Override
    public String toString() {
        return emailAddress + "\n" + reviewText + "\n\n";
    }
}
