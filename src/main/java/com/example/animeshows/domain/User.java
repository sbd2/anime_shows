package com.example.animeshows.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@Document(collection = "users")
public class User {
    @Id
    private String id;

    @Indexed
    private String name;

    private Map<String, Integer> ratings = new HashMap<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Integer> getRatings() {
        return ratings;
    }

    public void addRatings(Rating rating) {
        this.ratings.put(rating.getShowName(), rating.getRating());
    }
}
