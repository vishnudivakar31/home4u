package com.home4u.hotelmanagement.repositories;

import com.home4u.hotelmanagement.models.Dinner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DinnerRepository extends JpaRepository<Dinner, Long> {
    List<Dinner> findAllByHotelId(long hotelId);
}
