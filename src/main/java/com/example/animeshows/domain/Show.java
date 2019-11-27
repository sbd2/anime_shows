package com.example.animeshows.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Document(collection = "shows")
public class Show {

    @Id
    private String id;
    @Indexed(name = "showName")
    private String name;

    private Set<Episode> episodes = new TreeSet<>();

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

    public Set<Episode> getEpisodes() {
        return episodes;
    }

    public void addEpisodes(List<Episode> episodes) {
        this.episodes.addAll(episodes);
    }
}
