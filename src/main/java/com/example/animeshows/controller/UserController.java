package com.example.animeshows.controller;

import com.example.animeshows.domain.User;
import com.example.animeshows.dto.RatingDTO;
import com.example.animeshows.dto.UserDTO;
import com.example.animeshows.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public User createuser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @RequestMapping(value = "/users/{userId}/ratings",
            method = RequestMethod.GET)
    public Map<String, Integer> getUserRatings(@PathVariable("userId") String userId) {
        return userService.getRatingsForUser(userId);
    }

    @RequestMapping(value = "/users/{userId}/ratings",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public User addRating(@PathVariable("userId") String userId, @RequestBody RatingDTO ratingDTO) {
        return userService.addRating(userId, ratingDTO);
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
