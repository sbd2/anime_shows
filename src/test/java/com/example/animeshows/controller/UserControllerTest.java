package com.example.animeshows.controller;

import com.example.animeshows.dao.UserDAO;
import com.example.animeshows.domain.User;
import com.example.animeshows.dto.RatingDTO;
import com.example.animeshows.dto.UserDTO;
import com.example.animeshows.exception.BadRequestException;
import com.example.animeshows.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserControllerTest {
    private UserController userController;

    private UserDAO userDAO;

    @BeforeEach
    public void setup() {
        userDAO = Mockito.mock(UserDAO.class);
        userController = new UserController();
        UserService userService = new UserService();

        User user = new User();
        user.setId("someid");
        user.setName("name");

        when(userDAO.save(any())).thenReturn(user);
        when(userDAO.findById(any())).thenReturn(user);

        userService.setUserDAO(userDAO);
        userController.setUserService(userService);
    }

    @Test
    public void createUserWithoutRatings() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("name");

        User user = userController.createuser(userDTO);

        Assertions.assertNotNull(user);
        Assertions.assertNotNull(user.getId());
        Assertions.assertNotNull(user.getName());
    }

    @Test
    public void failWhenUserHasNoName() {
        Assertions.assertThrows(BadRequestException.class, () -> userController.createuser(new UserDTO()));
    }

    @Test
    public void addRatingToShow() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("name");

        User user = userController.createuser(userDTO);
        RatingDTO ratingDTO = new RatingDTO();
        ratingDTO.setShowName("showname");
        ratingDTO.setRating(1);

        user = userController.addRating(user.getId(), ratingDTO);

        Assertions.assertNotNull(user.getRatings());
        Assertions.assertEquals(1, user.getRatings().size());
    }

    @Test
    public void failWhenRatingHasNoShow() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("name");

        User user = userController.createuser(userDTO);
        RatingDTO ratingDTO = new RatingDTO();
        ratingDTO.setRating(1);

        Assertions.assertThrows(BadRequestException.class, () -> userController.addRating(user.getId(), ratingDTO));
    }

    @Test
    public void failWhenRatingHasNoRate() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("name");

        User user = userController.createuser(userDTO);
        RatingDTO ratingDTO = new RatingDTO();
        ratingDTO.setShowName("showname");

        Assertions.assertThrows(BadRequestException.class, () -> userController.addRating(user.getId(), ratingDTO));
    }

    @Test
    public void failWhenRatingIsGreaterThanFive() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("name");

        User user = userController.createuser(userDTO);
        RatingDTO ratingDTO = new RatingDTO();
        ratingDTO.setShowName("showname");
        ratingDTO.setRating(6);

        Assertions.assertThrows(BadRequestException.class, () -> userController.addRating(user.getId(), ratingDTO));
    }

    @Test
    public void ratingOverridesForSameShow() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("name");

        User user = userController.createuser(userDTO);
        RatingDTO ratingDTO1 = new RatingDTO();
        ratingDTO1.setShowName("showname");
        ratingDTO1.setRating(1);

        RatingDTO ratingDTO2 = new RatingDTO();
        ratingDTO2.setShowName("showname");
        ratingDTO2.setRating(3);

        user = userController.addRating(user.getId(), ratingDTO1);
        user = userController.addRating(user.getId(), ratingDTO2);

        Assertions.assertEquals(1, user.getRatings().size());
        Assertions.assertEquals(3, user.getRatings().get("showname"));
    }
}