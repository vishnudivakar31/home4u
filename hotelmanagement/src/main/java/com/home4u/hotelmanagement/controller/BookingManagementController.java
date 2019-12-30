package com.home4u.hotelmanagement.controller;

import com.home4u.hotelmanagement.models.DistanceSearchResult;
import com.home4u.hotelmanagement.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/operations")
public class BookingManagementController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/search")
    public List<DistanceSearchResult> findHotels(@RequestParam("lat") double lat, @RequestParam("lon") double lon, @RequestParam("dist") double dist) {
        return bookingService.searchHotels(lat, lon, dist);
    }
}
