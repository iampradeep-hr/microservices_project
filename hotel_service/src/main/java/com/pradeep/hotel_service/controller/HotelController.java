package com.pradeep.hotel_service.controller;

import com.pradeep.hotel_service.services.HotelService;
import com.pradeep.hotel_service.entities.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    HotelService service;

    @Autowired
    public HotelController(HotelService service) {
        this.service = service;
    }

    //create
    @PostMapping("/create-hotel")
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel Hotel) {
        Hotel Hotel1 = service.createHotel(Hotel);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Hotel1);
    }

    //single Hotel get
    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotel(@PathVariable String hotelId) {
        Hotel Hotel1 = service.getHotel(hotelId);
        return ResponseEntity.ok(Hotel1);
    }

    //all Hotel
    @GetMapping("/all-hotels")
    public ResponseEntity<List<Hotel>> getAllHotels() {
        List<Hotel> Hotel1 = service.getAllHotels();
        return ResponseEntity.ok(Hotel1);
    }

    //delete
    @DeleteMapping("/{hotelId}")
    public ResponseEntity<?> deleteHotel(@PathVariable String hotelId) {
        service.deleteHotel(hotelId);
        return ResponseEntity.ok("Hotel with id "+hotelId+" deleted successfully.");
    }

    //update
    @PutMapping("/{hotelId}")
    public ResponseEntity<?> updateHotel(@PathVariable String hotelId,@RequestBody Hotel Hotel) {
        Hotel Hotel1=service.updateUser(hotelId,Hotel);
        return ResponseEntity.ok(Hotel1);
    }


}
