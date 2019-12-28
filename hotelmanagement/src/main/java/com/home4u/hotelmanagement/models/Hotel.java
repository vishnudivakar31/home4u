package com.home4u.hotelmanagement.models;

import javax.persistence.*;
import java.util.Date;

enum HotelType {
    NORMAL,
    THREE_STAR,
    FIVE_STAR,
    SEVEN_STAR
}

@Entity(name = "hotel")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long userId;
    private String name;
    private int rooms;
    private double lat;
    private double lon;
    @Enumerated(EnumType.STRING)
    private HotelType hotelType;
    private Boolean active;
    private Date listedSince;

    public Hotel() {
    }

    public Hotel(long id, long userId, String name, int rooms, double lat, double lon, HotelType hotelType, Boolean active, Date listedSince) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.rooms = rooms;
        this.lat = lat;
        this.lon = lon;
        this.hotelType = hotelType;
        this.active = active;
        this.listedSince = listedSince;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public HotelType getHotelType() {
        return hotelType;
    }

    public void setHotelType(HotelType hotelType) {
        this.hotelType = hotelType;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getListedSince() {
        return listedSince;
    }

    public void setListedSince(Date listedSince) {
        this.listedSince = listedSince;
    }
}
