package com.example.animeshows.controller;

import com.example.animeshows.dao.ShowDAO;
import com.example.animeshows.domain.Show;
import com.example.animeshows.dto.EpisodeDTO;
import com.example.animeshows.dto.ShowDTO;
import com.example.animeshows.exception.BadRequestException;
import com.example.animeshows.service.ShowService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class ShowControllerTest {

    private ShowController showController;

    private ShowDAO showDAO;

    @BeforeEach
    public void setup() {
        showDAO = Mockito.mock(ShowDAO.class);
        showController = new ShowController();
        ShowService showService = new ShowService();
        Show show = new Show();
        show.setId("someid");
        show.setName("test name");

        when(showDAO.save(any(Show.class))).thenReturn(show);
        when(showDAO.findById(anyString())).thenReturn(show);

        showService.setShowDAO(showDAO);
        showController.setShowService(showService);
    }

    @Test
    public void createShowWithoutEpisodes() {
        ShowDTO showDTO = new ShowDTO();
        showDTO.setName("test name");

        Show show = showController.createShow(showDTO);

        Assertions.assertNotNull(show);
        Assertions.assertNotNull(show.getId());
        Assertions.assertNotNull(show.getName());
    }

    @Test
    public void failWhenShowHasNoName() {
        Assertions.assertThrows(BadRequestException.class, () -> showController.createShow(new ShowDTO()));
    }

    @Test
    public void addEpisodeToExistingShow() {
        ShowDTO showDTO = new ShowDTO();
        showDTO.setName("test name");

        Show show = showController.createShow(showDTO);
        List<EpisodeDTO> episodeDTOS = new ArrayList<>();
        EpisodeDTO episodeDTO1 = new EpisodeDTO();
        episodeDTO1.setEpisodeNumber(1L);
        EpisodeDTO episodeDTO2 = new EpisodeDTO();
        episodeDTO2.setEpisodeNumber(2L);
        episodeDTOS.add(episodeDTO1);
        episodeDTOS.add(episodeDTO2);

        show = showController.addEpisode(show.getId(), episodeDTOS);

        Assertions.assertNotNull(show.getEpisodes());
        Assertions.assertEquals(2, show.getEpisodes().size());
    }

    @Test
    public void failWhenEpisodeHasNoEpisodeNumber() {
        Show show = showController.findById("someid");
        List<EpisodeDTO> episodeDTOS = new ArrayList<>();
        EpisodeDTO episodeDTO1 = new EpisodeDTO();
        episodeDTOS.add(episodeDTO1);

        Assertions.assertThrows(BadRequestException.class, () -> showController.addEpisode(show.getId(), episodeDTOS));
    }

    @Test
    public void findShowById() {
        Show show = showController.findById("someid");

        Assertions.assertNotNull(show);
        Assertions.assertEquals("someid", show.getId());
        Assertions.assertEquals("test name", show.getName());
        Assertions.assertNotNull(show.getEpisodes());
        Assertions.assertEquals(0, show.getEpisodes().size());
    }
}