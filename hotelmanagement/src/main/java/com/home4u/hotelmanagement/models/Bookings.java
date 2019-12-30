package com.home4u.hotelmanagement.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Bookings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long userId;
    private long hotelId;
    private long roomId;
    private long breakfastId;
    private int noOfBreakfast;
    private long lunchId;
    private int noOfLunch;
    private long dinnerId;
    private int noOfDinner;
    private Date checkInDate;
    private Date checkOutDate;
    private Boolean active;

    public Bookings() {
    }

    public Bookings(long id, long userId, long hotelId, long roomId, long breakfastId, int noOfBreakfast, long lunchId, int noOfLunch, long dinnerId, int noOfDinner, Date checkInDate, Date checkOutDate, Boolean active) {
        this.id = id;
        this.userId = userId;
        this.hotelId = hotelId;
        this.roomId = roomId;
        this.breakfastId = breakfastId;
        this.noOfBreakfast = noOfBreakfast;
        this.lunchId = lunchId;
        this.noOfLunch = noOfLunch;
        this.dinnerId = dinnerId;
        this.noOfDinner = noOfDinner;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.active = active;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getHotelId() {
        return hotelId;
    }

    public void setHotelId(long hotelId) {
        this.hotelId = hotelId;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public long getBreakfastId() {
        return breakfastId;
    }

    public void setBreakfastId(long breakfastId) {
        this.breakfastId = breakfastId;
    }

    public int getNoOfBreakfast() {
        return noOfBreakfast;
    }

    public void setNoOfBreakfast(int noOfBreakfast) {
        this.noOfBreakfast = noOfBreakfast;
    }

    public long getLunchId() {
        return lunchId;
    }

    public void setLunchId(long lunchId) {
        this.lunchId = lunchId;
    }

    public int getNoOfLunch() {
        return noOfLunch;
    }

    public void setNoOfLunch(int noOfLunch) {
        this.noOfLunch = noOfLunch;
    }

    public long getDinnerId() {
        return dinnerId;
    }

    public void setDinnerId(long dinnerId) {
        this.dinnerId = dinnerId;
    }

    public int getNoOfDinner() {
        return noOfDinner;
    }

    public void setNoOfDinner(int noOfDinner) {
        this.noOfDinner = noOfDinner;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
