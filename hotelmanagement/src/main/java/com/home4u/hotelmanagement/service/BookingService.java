package com.home4u.hotelmanagement.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.home4u.hotelmanagement.models.AppUser;
import com.home4u.hotelmanagement.models.Bookings;
import com.home4u.hotelmanagement.models.DistanceSearchResult;
import com.home4u.hotelmanagement.models.Room;
import com.home4u.hotelmanagement.repositories.BookingsRepository;
import com.home4u.hotelmanagement.repositories.HotelRepository;
import com.home4u.hotelmanagement.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private BookingsRepository bookingsRepository;
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private RoomRepository roomRepository;

    public List<DistanceSearchResult> searchHotels(double lat, double lon, double dist, Date checkInDate, Date checkOutDate) {
        List<DistanceSearchResult> result = new ArrayList<>();
        List<Map<String, Object>> queryResult = hotelRepository.findHotelByDistance(lat, lon, dist);
        List<Bookings> bookings = bookingsRepository.findByCheckinCheckoutDate(checkInDate, checkOutDate);
        for(Map<String, Object> item : queryResult) {
            DistanceSearchResult distanceSearchResult = new ObjectMapper().convertValue(item, DistanceSearchResult.class);
            if(distanceSearchResult.getDistance() <= dist) {
                List<Room> rooms = roomRepository.findAllByHotelId(distanceSearchResult.getId());
                List<Bookings> bookingsByHotelId = bookings
                        .stream()
                        .filter(bookings1 -> bookings1.getHotelId() == distanceSearchResult.getId())
                        .collect(Collectors.toList());
                List<Room> resultRoom = new ArrayList<>();
                for(Room room : rooms) {
                    if(bookingsByHotelId.size() == 0) {
                        resultRoom = rooms;
                        break;
                    } else {
                        for(Bookings bookItem : bookingsByHotelId) {
                            if(room.getId() != bookItem.getRoomId()) {
                                resultRoom.add(room);
                            }
                        }
                    }
                }

                distanceSearchResult.setRoomList(resultRoom);
                result.add(distanceSearchResult);
            }
        }
        return result;
    }

    public Bookings bookHotel(AppUser user, Bookings bookings) {
        bookings.setUserId(user.getId());
        bookings.setActive(true);
        bookingsRepository.save(bookings);
        return bookings;
    }
}
