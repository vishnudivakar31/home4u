package com.home4u.hotelmanagement.models;

public class SearchResponse {
    private Hotel hotel;
    private Room room;

    public SearchResponse() {
    }

    public SearchResponse(Hotel hotel, Room room) {
        this.hotel = hotel;
        this.room = room;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
