package com.pradeep.rating_service.services.impl;

import com.pradeep.rating_service.entities.Rating;
import com.pradeep.rating_service.repository.RatingRepository;
import com.pradeep.rating_service.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    RatingRepository repository;
    @Autowired
    RatingServiceImpl(RatingRepository repository){
        this.repository=repository;
    }

    @Override
    public Rating createRating(Rating rating) {
        return repository.save(rating);
    }

    @Override
    public List<Rating> getAllRatings() {
        return repository.findAll();
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return repository.findByHotelId(hotelId);
    }

    @Override
    public void deleteRating(String ratingId) {
        repository.deleteById(ratingId);
    }
}
