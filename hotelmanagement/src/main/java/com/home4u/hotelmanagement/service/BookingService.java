package com.home4u.hotelmanagement.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.home4u.hotelmanagement.models.DistanceSearchResult;
import com.home4u.hotelmanagement.models.Room;
import com.home4u.hotelmanagement.repositories.BookingsRepository;
import com.home4u.hotelmanagement.repositories.HotelRepository;
import com.home4u.hotelmanagement.repositories.RoomRepository;
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
    @Autowired
    private RoomRepository roomRepository;

    public List<DistanceSearchResult> searchHotels(double lat, double lon, double dist) {
        List<DistanceSearchResult> result = new ArrayList<>();
        List<Map<String, Object>> queryResult = hotelRepository.findHotelByDistance(lat, lon, dist);
        for(Map<String, Object> item : queryResult) {
            DistanceSearchResult distanceSearchResult = new ObjectMapper().convertValue(item, DistanceSearchResult.class);
            if(distanceSearchResult.getDistance() <= dist) {
                List<Room> rooms = roomRepository.findAllByHotelId(distanceSearchResult.getId());
                distanceSearchResult.setRoomList(rooms);
                result.add(distanceSearchResult);
            }
        }
        return result;
    }
}
