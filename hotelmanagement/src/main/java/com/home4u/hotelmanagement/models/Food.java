package com.home4u.hotelmanagement.models;

import javax.persistence.*;

@Entity
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private double price;
    private long hotelId;
    private boolean active;
    @Enumerated(EnumType.STRING)
    private FoodType foodType;

    public Food() {
    }

    public Food(long id, String name, double price, long hotelId, boolean active, FoodType foodType) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.hotelId = hotelId;
        this.active = active;
        this.foodType = foodType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getHotelId() {
        return hotelId;
    }

    public void setHotelId(long hotelId) {
        this.hotelId = hotelId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }
}
