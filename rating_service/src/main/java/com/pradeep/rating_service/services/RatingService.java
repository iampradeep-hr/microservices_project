package com.pradeep.rating_service.services;


import com.pradeep.rating_service.entities.Rating;

import java.util.List;

public interface RatingService {

    //create
    Rating createRating(Rating rating);

    //get all ratings
    List<Rating> getAllRatings();

    //get all by user id
    List<Rating> getRatingByUserId(String userId);

    //get all by hotel
    List<Rating> getRatingByHotelId(String hotelId);

    void deleteRating(String ratingId);



}
