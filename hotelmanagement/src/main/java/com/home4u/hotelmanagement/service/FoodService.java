package com.home4u.hotelmanagement.service;

import com.home4u.hotelmanagement.models.Food;
import com.home4u.hotelmanagement.models.FoodType;
import com.home4u.hotelmanagement.repositories.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<Food> fetchFood(long hotelId, FoodType foodType) {
        List<Food> foods = foodRepository.findAllByHotelId(hotelId);
        List<Food> result = foods
                .stream()
                .filter(item -> item.getFoodType().equals(foodType))
                .collect(Collectors.toList());
        return result;
    }
}
