package com.example.animeshows.service;

import com.example.animeshows.dao.ShowDAO;
import com.example.animeshows.domain.Episode;
import com.example.animeshows.domain.Show;
import com.example.animeshows.dto.EpisodeDTO;
import com.example.animeshows.dto.ShowDTO;
import com.example.animeshows.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ShowService {

    @Autowired
    private ShowDAO showDAO;

    public void setShowDAO(ShowDAO showDAO) {
        this.showDAO = showDAO;
    }

    public Show createShow(ShowDTO showDTO) {
        validate(showDTO);

        Show show = new Show();
        List<Episode> episodes = showDTO.getEpisodes() != null ? showDTO.getEpisodes() : new ArrayList<>();

        show.setId(UUID.randomUUID().toString());
        show.setName(showDTO.getName());
        show.addEpisodes(episodes);
        return showDAO.save(show);
    }

    private void validate(ShowDTO show) {
        if (show.getName() == null || show.getName().isEmpty()) {
            throw new BadRequestException("Show name cannot be empty");
        }
    }

    public Show findById(String id) {
        return showDAO.findById(id);
    }

    public Show findByName(String name) {
        return showDAO.findByName(name);
    }

    public Show addEpisodes(String id, List<EpisodeDTO> episodeDTOs) {
        validate(episodeDTOs);

        Show show = showDAO.findById(id);
        List<Episode> episodes = new ArrayList<>();

        for (EpisodeDTO episodeDTO : episodeDTOs) {
            String episodeName = episodeDTO.getName() != null ? episodeDTO.getName() : "Episode" + episodeDTO.getEpisodeNumber();
            Episode episode = new Episode();
            episode.setId(UUID.randomUUID().toString());
            episode.setEpisodeNumber(episodeDTO.getEpisodeNumber());
            episode.setName(episodeName);
            episodes.add(episode);
        }

        show.addEpisodes(episodes);
        return showDAO.save(show);
    }

    private void validate(List<EpisodeDTO> episodeDTOs) {
        for (EpisodeDTO episodeDTO : episodeDTOs) {
            if (episodeDTO.getEpisodeNumber() == null || episodeDTO.getEpisodeNumber() < 0) {
                throw new BadRequestException("Episode number must be greater than 0");
            }
        }
    }

    public String getShowName(String id) {
        return showDAO.findById(id).getName();
    }
}
