package com.example.animeshows.service;

import com.example.animeshows.dao.ShowDAO;
import com.example.animeshows.dao.UserDAO;
import com.example.animeshows.domain.Rating;
import com.example.animeshows.domain.User;
import com.example.animeshows.dto.RatingDTO;
import com.example.animeshows.dto.UserDTO;
import com.example.animeshows.exception.BadRequestException;
import com.example.animeshows.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ShowDAO showDAO;

    public User createUser(UserDTO userDTO) {
        validate(userDTO);

        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName(userDTO.getName());

        return userDAO.save(user);
    }

    private void validate(UserDTO userDTO) {
        if (userDTO.getName() == null || userDTO.getName().isEmpty()) {
            throw new BadRequestException("Username cannot be empty");
        }
    }

    public Map<String, Integer> getRatingsForUser(String userId) {
        return userDAO.findById(userId).getRatings();
    }

    public User addRating(String userId, RatingDTO ratingDTO) {
        validate(ratingDTO);

        Rating rating = new Rating();
        rating.setShowName(ratingDTO.getShowName());
        rating.setRating(ratingDTO.getRating());

        User user = userDAO.findById(userId);
        user.addRatings(rating);

        return userDAO.save(user);
    }

    private void validate(RatingDTO ratingDTO) {
        if(ratingDTO.getShowName() == null || ratingDTO.getShowName().isEmpty()) {
            throw new BadRequestException("Show name must no be empty");
        }
        if (ratingDTO.getRating() > 5 || ratingDTO.getRating() < 1) {
            throw new BadRequestException("Show rating must be a number between 0 and 5");
        }
        if (showDAO.findByName(ratingDTO.getShowName()) == null) {
            throw new EntityNotFoundException("That show does not exist");
        }
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
