package com.example.icadi.lepetitcinema.Domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by icadi on 27-3-18.
 */

public class Film implements Serializable {
    private String name;
    private String description;
    private double rating;
    private String posterImageUrl;
    private String backgroundImageUrl;
    public ArrayList<Review> reviewArrayList = new ArrayList<Review>();

    public Film(String name, String description, double rating, String posterImageUrl, String backgroundImageUrl) {
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.posterImageUrl = posterImageUrl;
        this.backgroundImageUrl = backgroundImageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getPosterImageUrl() {
        return posterImageUrl;
    }

    public String getBackgroundImageUrl() {
        return backgroundImageUrl;
    }

    public double getRating() {
        return rating;
    }

    public ArrayList<Review> getReviewArrayList() {
        return reviewArrayList;
    }

    @Override
    public String toString() {
        return "Film{" +

                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                ", posterImageUrl='" + posterImageUrl + '\'' +
                ", backgroundImageUrl='" + backgroundImageUrl + '\'' +
                '}';
    }
}
