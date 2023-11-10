package com.pradeep.user_service.services.impl;

import com.pradeep.user_service.entities.Hotel;
import com.pradeep.user_service.entities.Rating;
import com.pradeep.user_service.entities.User;
import com.pradeep.user_service.exception.ResourceNotFoundException;
import com.pradeep.user_service.repository.UserRepository;
import com.pradeep.user_service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService {
    UserRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User createUser(User user) {
        return repository.save(user);
    }

    @Override
    public List<User> getAllUser() {

        return repository.findAll();
    }

    @Override
    public User getUser(String userId) {
        User user = repository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User with given id " + userId + " is not found on the server."));


        //fetch ratings from rating service
        //https://localhost:8083/ratings/user/506482af-9a02-4af0-b101-6a1e9da9142c

       Rating[] ratingsOfUser = restTemplate.
                getForObject(
                        "http://localhost:8083/ratings/user/" + user.getUserId(),
                        Rating[].class);

        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

        List<Rating> ratingList=ratings.stream().map(rating -> {
            //api call
            ResponseEntity<Hotel> forEntity=restTemplate.getForEntity(
                    "http://localhost:8082/hotels/"+rating.getHotelId(),
                    Hotel.class
            );
            Hotel hotel=forEntity.getBody();
            rating.setHotel(hotel);
           return rating;
        }).collect(Collectors.toList());


        user.setRatings(ratingList);


        return user;
    }

    @Override
    public void deleteUser(String userId) {
        repository.deleteById(userId);
    }


    @Override
    public User updateUser(String userId, User updatedUser) {
        User user = getUser(userId);
        User updated = User.builder()
                .userId(user.getUserId())
                .name(updatedUser.getName())
                .email(updatedUser.getEmail())
                .about(updatedUser.getAbout()).build();
        if (user.getUserId() != null) {
            repository.save(updated);
        }
        return updated;
    }
}
