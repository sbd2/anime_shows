package com.example.animeshows.dao;

import com.example.animeshows.domain.Show;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepository extends MongoRepository<Show, String> {
    Show findShowByName(String name);
}
