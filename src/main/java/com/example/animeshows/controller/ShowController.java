package com.example.animeshows.controller;

import com.example.animeshows.domain.Show;
import com.example.animeshows.dto.EpisodeDTO;
import com.example.animeshows.dto.ShowDTO;
import com.example.animeshows.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShowController {

    @Autowired
    private ShowService showService;

    @RequestMapping(value = "/shows",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Show createShow(@RequestBody ShowDTO show) {
        return showService.createShow(show);
    }

    @RequestMapping(value = "/shows/{id}",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Show addEpisode(@PathVariable String id, @RequestBody List<EpisodeDTO> episodeDTOs) {
        return showService.addEpisodes(id, episodeDTOs);
    }

    @RequestMapping(value = "/shows/{id}",
            method = RequestMethod.GET)
    public Show findById(@PathVariable("id") String id) {
        return showService.findById(id);
    }

    @RequestMapping(value = "/shows/{id}/name",
            method = RequestMethod.GET)
    public String getShowName(@PathVariable String id) {
        return showService.getShowName(id);
    }

    public void setShowService(ShowService showService) {
        this.showService = showService;
    }
}
