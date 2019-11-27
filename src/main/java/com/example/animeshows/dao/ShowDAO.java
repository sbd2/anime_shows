package com.example.animeshows.dao;

import com.example.animeshows.domain.Show;
import com.example.animeshows.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShowDAO {

    @Autowired
    private ShowRepository showRepository;

    public Show save(Show show) {
        return showRepository.save(show);
    }

    public Show findById(String id) {
        return showRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Show findByName(String name) {
        return showRepository.findShowByName(name);
    }

    public void setShowRepository(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }
}
