package com.home4u.hotelmanagement.repositories;

import com.home4u.hotelmanagement.models.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findAllByHotelId(long hotelId);
}
