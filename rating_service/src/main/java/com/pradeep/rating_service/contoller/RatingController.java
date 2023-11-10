package com.pradeep.rating_service.contoller;

import com.pradeep.rating_service.entities.Rating;
import com.pradeep.rating_service.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    private RatingService service;

    @Autowired
    RatingController(RatingService service) {
        this.service = service;
    }

    //create rating
    @PostMapping("/create-rating")
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createRating(rating));
    }

    //get by userid
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(service.getRatingByUserId(userId));
    }

    //get by hotelid
    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable String hotelId) {
        return ResponseEntity.ok(service.getRatingByHotelId(hotelId));
    }

    @DeleteMapping("/{ratingId}")
    public ResponseEntity<?> deleteRating(
            @PathVariable
            String ratingId
    ) {
        service.deleteRating(ratingId);
        return ResponseEntity.ok("Rating with the id "+ratingId+" deleted.");
    }

    @GetMapping("/all-ratings")
    public ResponseEntity<List<Rating>> getAllRatings() {
        return ResponseEntity.ok(service.getAllRatings());
    }


}
