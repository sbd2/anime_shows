package com.example.animeshows.dto;

import com.example.animeshows.domain.Episode;

import java.util.List;

public class ShowDTO {
    private String name;
    private List<Episode> episodes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }
}
