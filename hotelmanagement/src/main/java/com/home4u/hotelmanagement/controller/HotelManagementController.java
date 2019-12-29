package com.home4u.hotelmanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.home4u.hotelmanagement.models.AppUser;
import com.home4u.hotelmanagement.models.Hotel;
import com.home4u.hotelmanagement.models.Room;
import com.home4u.hotelmanagement.service.HotelService;
import com.home4u.hotelmanagement.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static com.home4u.hotelmanagement.utils.Constants.APPUSER_TAG;

@RestController
@RequestMapping("/hotels")
public class HotelManagementController {

    @Autowired
    private HotelService hotelService;
    @Autowired
    private RoomService roomService;

    @GetMapping
    public ResponseEntity<Object> getHotels(HttpServletRequest request) {
        AppUser user = new ObjectMapper().convertValue(request.getAttribute(APPUSER_TAG), AppUser.class);
        List<Hotel> hotels = hotelService.findHotels(user);
        return new ResponseEntity<>(hotels, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Hotel getHotel(HttpServletRequest request, @PathVariable("id") long id) {
        AppUser user = new ObjectMapper().convertValue(request.getAttribute(APPUSER_TAG), AppUser.class);
        return hotelService.findHotel(user, id);
    }

    @PostMapping
    public Hotel postHotel(HttpServletRequest request, @RequestBody Hotel hotel) {
        AppUser user = new ObjectMapper().convertValue(request.getAttribute(APPUSER_TAG), AppUser.class);
        return hotelService.saveHotel(hotel, user);
    }

    @PutMapping
    public Hotel putHotel(HttpServletRequest request, @RequestBody Hotel hotel) {
        AppUser user = new ObjectMapper().convertValue(request.getAttribute(APPUSER_TAG), AppUser.class);
        return hotelService.updateHotel(hotel, user);
    }

    @DeleteMapping("/{id}")
    public Hotel deleteHotel(HttpServletRequest request, @PathVariable("id") long id) {
        AppUser user = new ObjectMapper().convertValue(request.getAttribute(APPUSER_TAG), AppUser.class);
        return hotelService.setActive(id, user, false);
    }

    @GetMapping("/{id}/revive")
    public Hotel reviveHotel(HttpServletRequest request, @PathVariable("id") long id) {
        AppUser user = new ObjectMapper().convertValue(request.getAttribute(APPUSER_TAG), AppUser.class);
        return hotelService.setActive(id, user, true);
    }

    @PostMapping("/{id}/rooms")
    public Room saveRoom(@PathVariable("id") long id, @RequestBody Room room) {
        return roomService.saveRoom(room, id);
    }

    @GetMapping("/{id}/rooms")
    public List<Room> getRooms(@PathVariable("id") long id) {
        return roomService.findAllRooms(id);
    }

    @GetMapping("/{id}/rooms/{roomId}")
    public ResponseEntity getRoomById(@PathVariable("id") long hotelId, @PathVariable("roomId") long roomId) {
        try {
            Room room = roomService.findRoomById(hotelId, roomId);
            return new ResponseEntity(room, HttpStatus.OK);
        } catch (NullPointerException e) {
            return new ResponseEntity("", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}/rooms/{roomId}")
    public ResponseEntity updateRoomById(@PathVariable("id") long hotelId, @PathVariable("roomId") long roomId, @RequestBody Room modifiedRoom) {
        try {
            Room room = roomService.updateRoomNyId(hotelId, roomId, modifiedRoom);
            return new ResponseEntity(room, HttpStatus.OK);
        } catch (NullPointerException e) {
            return new ResponseEntity("", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
