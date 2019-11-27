package com.example.animeshows.dao;

import com.example.animeshows.domain.User;
import com.mongodb.Mongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
