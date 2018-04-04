package com.example.icadi.lepetitcinema.Domain;

public class Review {

    private String reviewText;
    private String emailaddress;

    public String getReviewText() {
        return reviewText;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public Review (String emailaddress, String reviewText){
        this.emailaddress=emailaddress;
        this.reviewText=reviewText;

    }

    @Override
    public String toString() {
        return emailaddress + "\n" + reviewText;
    }
}
