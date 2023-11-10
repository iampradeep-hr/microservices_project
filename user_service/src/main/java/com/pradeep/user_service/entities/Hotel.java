package com.pradeep.user_service.entities;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {

    private String hotelId;
    private String name;
    private String location;
    private String about;
}
