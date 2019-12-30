package com.home4u.hotelmanagement.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.home4u.hotelmanagement.models.DistanceSearchResult;
import com.home4u.hotelmanagement.models.Hotel;
import com.home4u.hotelmanagement.models.Room;
import com.home4u.hotelmanagement.models.SearchResponse;
import com.home4u.hotelmanagement.repositories.BookingsRepository;
import com.home4u.hotelmanagement.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BookingService {

    @Autowired
    private BookingsRepository bookingsRepository;
    @Autowired
    private HotelRepository hotelRepository;

    public List<DistanceSearchResult> searchHotels(double lat, double lon, double dist) {
        List<DistanceSearchResult> result = new ArrayList<>();
        List<Map<String, Object>> queryResult = hotelRepository.findHotelByDistance(lat, lon, dist);
        for(Map<String, Object> item : queryResult) {
            DistanceSearchResult distanceSearchResult = new ObjectMapper().convertValue(item, DistanceSearchResult.class);
            if(distanceSearchResult.getDistance() <= dist) {
                result.add(distanceSearchResult);
            }
        }
        return result;
    }
}
