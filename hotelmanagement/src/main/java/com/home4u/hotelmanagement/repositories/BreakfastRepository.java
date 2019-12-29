package com.home4u.hotelmanagement.repositories;

import com.home4u.hotelmanagement.models.Breakfast;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BreakfastRepository extends JpaRepository<Breakfast, Long> {
    List<Breakfast> findAllByHotelId(long hotelId);
}
