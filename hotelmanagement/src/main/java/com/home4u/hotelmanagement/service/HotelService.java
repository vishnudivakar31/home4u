package com.home4u.hotelmanagement.service;

import com.home4u.hotelmanagement.models.AppUser;
import com.home4u.hotelmanagement.models.Hotel;
import com.home4u.hotelmanagement.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    public Hotel saveHotel(Hotel hotel, AppUser user) {
        hotel.setActive(true);
        hotel.setUserId(user.getId());
        hotel.setListedSince(new Date());
        hotelRepository.save(hotel);
        return hotel;
    }

    public Hotel findHotel(AppUser user, long id) {
        Hotel hotel = hotelRepository.findById(id).get();
        if(hotel.getUserId() == user.getId()) {
            return hotel;
        }
        return null;
    }

    public List<Hotel> findHotels(AppUser user) {
        return hotelRepository.findAllByUserId(user.getId());
    }

    public Hotel updateHotel(Hotel modifiedHotel, AppUser user) {
        Hotel hotel = hotelRepository.findById(modifiedHotel.getId()).get();
        if(modifiedHotel.getName() != null) {
            hotel.setName(modifiedHotel.getName());
        }
        if(modifiedHotel.getRooms() > 0) {
            hotel.setRooms(modifiedHotel.getRooms());
        }
        if(modifiedHotel.getLat() != 0.0d) {
            hotel.setLat(modifiedHotel.getLat());
        }
        if(modifiedHotel.getLon() != 0.0d) {
            hotel.setLon(modifiedHotel.getLon());
        }
        if(modifiedHotel.getHotelType() != null) {
            hotel.setHotelType(modifiedHotel.getHotelType());
        }
        hotelRepository.save(hotel);
        return hotel;
    }

    public Hotel setActive(long hotelId, AppUser user, Boolean status) {
        Hotel hotel = hotelRepository.findById(hotelId).get();
        if(hotel.getUserId() == user.getId()) {
            hotel.setActive(status);
            hotelRepository.save(hotel);
        }
        return hotel;
    }
}
