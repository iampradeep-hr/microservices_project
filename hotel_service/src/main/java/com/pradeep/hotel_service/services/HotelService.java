package com.pradeep.hotel_service.services;

import com.pradeep.hotel_service.entities.Hotel;

import java.util.List;

public interface HotelService {
    //create
    Hotel createHotel(Hotel hotel);

    //get all
    List<Hotel> getAllHotels();

    //get single
    Hotel getHotel(String hotelId);

    //delete hotel
    void deleteHotel(String hotelId);

    //update hotel
    Hotel updateUser(String hotelId, Hotel updatedHotel);


}
