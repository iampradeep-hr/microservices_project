package com.pradeep.rating_service.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("user_ratings")
public class Rating {
    @MongoId
    private String ratingId;
    private String userId;
    private String hotelId;
    private int rating;
    private String feedback;

}
