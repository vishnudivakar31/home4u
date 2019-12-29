package com.home4u.hotelmanagement.service;

import com.home4u.hotelmanagement.models.Food;
import com.home4u.hotelmanagement.models.FoodType;
import com.home4u.hotelmanagement.repositories.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodService {
    @Autowired
    private FoodRepository foodRepository;

    public Food saveFood(Food food, long hotelId) {
        food.setActive(true);
        food.setHotelId(hotelId);
        foodRepository.save(food);
        return food;
    }

}
