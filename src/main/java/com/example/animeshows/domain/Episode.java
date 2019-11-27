package com.example.animeshows.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "episodes")
public class Episode implements Comparable<Episode> {

    @Id
    private String id;

    private Long episodeNumber;

    private String name;

    @Override
    public int compareTo(Episode other) {
        return this.episodeNumber.compareTo(other.episodeNumber);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(Long episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
