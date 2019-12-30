package com.home4u.hotelmanagement.models;

import java.util.Date;
import java.util.List;

public class DistanceSearchResult {
    private Double distance;
    private long id;
    private long user_id;
    private String name;
    private int rooms;
    private double lat;
    private double lon;
    private HotelType hotel_type;
    private Boolean active;
    private Date listed_since;
    private List<Room> roomList;

    public DistanceSearchResult() {
    }

    public DistanceSearchResult(Double distance, long id, long user_id, String name, int rooms, double lat, double lon, HotelType hotel_type, Boolean active, Date listed_since, List<Room> roomList) {
        this.distance = distance;
        this.id = id;
        this.user_id = user_id;
        this.name = name;
        this.rooms = rooms;
        this.lat = lat;
        this.lon = lon;
        this.hotel_type = hotel_type;
        this.active = active;
        this.listed_since = listed_since;
        this.roomList = roomList;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
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

    public HotelType getHotel_type() {
        return hotel_type;
    }

    public void setHotel_type(HotelType hotel_type) {
        this.hotel_type = hotel_type;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getListed_since() {
        return listed_since;
    }

    public void setListed_since(Date listed_since) {
        this.listed_since = listed_since;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }
}
