package com.pradeep.hotel_service.services.impl;

import com.pradeep.hotel_service.entities.Hotel;
import com.pradeep.hotel_service.exception.ResourceNotFoundException;
import com.pradeep.hotel_service.repository.HotelRepository;
import com.pradeep.hotel_service.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    HotelRepository repository;
    @Autowired
    HotelServiceImpl(HotelRepository repository){
        this.repository=repository;
    }

    @Override
    public Hotel createHotel(Hotel hotel) {
        return repository.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return repository.findAll();
    }

    @Override
    public Hotel getHotel(String hotelId) {
        return repository.findById(hotelId).
        orElseThrow(()->new ResourceNotFoundException("The hotel with id "+hotelId+" is not found on the server."));
    }

    @Override
    public void deleteHotel(String hotelId) {
        repository.deleteById(hotelId);
    }

    @Override
    public Hotel updateUser(String hotelId, Hotel updatedHotel) {
        Hotel user=getHotel(hotelId);
        Hotel updated=Hotel.builder()
                .hotelId(user.getHotelId())
                .name(updatedHotel.getName())
                .location(updatedHotel.getLocation())
                .about(updatedHotel.getAbout()).build();
        if (getHotel(hotelId)!=null){
            repository.save(updated);
        }
        return updated;
    }
}
