package com.home4u.hotelmanagement.service;

import com.home4u.hotelmanagement.models.Room;
import com.home4u.hotelmanagement.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public Room saveRoom(Room room, Long hotelId) {
        room.setHotelId(hotelId);
        room.setActive(true);
        roomRepository.save(room);
        return room;
    }

    public List<Room> findAllRooms(Long hotelId) {
        return roomRepository.findAllByHotelId(hotelId);
    }

    public Room findRoomById(Long hotelId, Long roomId) {
        List<Room> rooms = roomRepository.findAllByHotelId(hotelId);
        Optional<Room> room = rooms
                .stream()
                .filter(item -> item.getId() == roomId).findFirst();
        if(room.isPresent()) {
            return room.get();
        } else {
            throw new NullPointerException();
        }
    }

    public Room updateRoomNyId(Long hotelId, Long roomId, Room modifiedRoom) {
        Room room = findRoomById(hotelId, roomId);
        if(modifiedRoom.getRoomNo() != null) {
            room.setRoomNo(modifiedRoom.getRoomNo());
        }
        if(modifiedRoom.getOccupancy() > 0) {
            room.setOccupancy(modifiedRoom.getOccupancy());
        }
        if(modifiedRoom.getPrice() > 0) {
            room.setPrice(modifiedRoom.getPrice());
        }
        roomRepository.save(room);
        return room;
    }
}

