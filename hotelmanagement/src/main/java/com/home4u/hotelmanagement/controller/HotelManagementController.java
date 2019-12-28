package com.home4u.hotelmanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.home4u.hotelmanagement.models.AppUser;
import com.home4u.hotelmanagement.models.Hotel;
import com.home4u.hotelmanagement.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static com.home4u.hotelmanagement.utils.Constants.APPUSER_TAG;

@RestController
@RequestMapping("/hotels")
public class HotelManagementController {

    @Autowired
    private HotelService hotelService;

    @GetMapping
    public ResponseEntity<Object> getHotels(HttpServletRequest request) {
        AppUser user = new ObjectMapper().convertValue(request.getAttribute(APPUSER_TAG), AppUser.class);
        List<Hotel> hotels = hotelService.findHotels(user);
        return new ResponseEntity<>(hotels, HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("/{id}")
    public Hotel getHotel(HttpServletRequest request, @PathVariable("id") long id) {
        AppUser user = new ObjectMapper().convertValue(request.getAttribute(APPUSER_TAG), AppUser.class);
        return hotelService.findHotel(user, id);
    }

    @PostMapping
    public Hotel postHotel(HttpServletRequest request, @RequestBody Hotel hotel) {
        AppUser user = new ObjectMapper().convertValue(request.getAttribute(APPUSER_TAG), AppUser.class);
        return hotelService.saveHotel(hotel, user);
    }

    @PutMapping
    public Hotel putHotel(HttpServletRequest request, @RequestBody Hotel hotel) {
        AppUser user = new ObjectMapper().convertValue(request.getAttribute(APPUSER_TAG), AppUser.class);
        return hotelService.updateHotel(hotel, user);
    }

    @DeleteMapping
    @RequestMapping("/{id}/delete")
    public Hotel deleteHotel(HttpServletRequest request, @PathVariable("id") long id) {
        AppUser user = new ObjectMapper().convertValue(request.getAttribute(APPUSER_TAG), AppUser.class);
        return hotelService.setActive(id, user, false);
    }

    @GetMapping
    @RequestMapping("/{id}/revive")
    public Hotel reviveHotel(HttpServletRequest request, @PathVariable("id") long id) {
        AppUser user = new ObjectMapper().convertValue(request.getAttribute(APPUSER_TAG), AppUser.class);
        return hotelService.setActive(id, user, true);
    }
}
