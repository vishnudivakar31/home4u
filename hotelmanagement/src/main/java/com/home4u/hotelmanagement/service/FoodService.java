package com.home4u.hotelmanagement.service;

import com.home4u.hotelmanagement.models.Food;
import com.home4u.hotelmanagement.models.FoodType;
import com.home4u.hotelmanagement.repositories.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public Food getFood(long hotelId, long foodId) {
        Optional<Food> foodOptional = foodRepository.findById(foodId);
        if(foodOptional.isPresent()) {
            Food food = foodOptional.get();
            if (food.getHotelId() == hotelId) {
               return food;
            } else {
                throw new NullPointerException();
            }
        } else {
            throw new NullPointerException();
        }
    }

    public Food updateFood(long hotelId, long foodId, Food modifiedFood) {
        Food food = getFood(hotelId, foodId);
        if(modifiedFood.getName() != null) {
            food.setName(modifiedFood.getName());
        }
        if(modifiedFood.getPrice() > 0) {
            food.setPrice(modifiedFood.getPrice());
        }
        if(modifiedFood.getFoodType() != null) {
            food.setFoodType(modifiedFood.getFoodType());
        }
        foodRepository.save(food);
        return food;
    }

    public Food setActivitiy(long hotelId, long foodId, boolean status) {
        Food food = getFood(hotelId, foodId);
        food.setActive(status);
        foodRepository.save(food);
        return food;
    }
}
