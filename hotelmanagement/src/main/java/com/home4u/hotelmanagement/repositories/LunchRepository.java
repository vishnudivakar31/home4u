package com.home4u.hotelmanagement.repositories;

import com.home4u.hotelmanagement.models.Lunch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LunchRepository extends JpaRepository<Lunch, Long> {
        List<Lunch> findAllByHotelId(long hotelId);
}
