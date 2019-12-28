package com.home4u.hotelmanagement.repositories;

import com.home4u.hotelmanagement.models.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface HotelRepository extends JpaRepository<Hotel, Long> {
    List<Hotel> findAllByUserId(long userId);
}
