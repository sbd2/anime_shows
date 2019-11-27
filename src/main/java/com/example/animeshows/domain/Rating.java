package com.example.animeshows.domain;

public class Rating {

    private String showName;

    private int rating;

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object other) {
        return this.showName.equals(((Rating)other).getShowName());
    }
}
