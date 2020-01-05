package com.home4u.hotelmanagement.repositories;

import com.home4u.hotelmanagement.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findAllByHotelId(long id);
}
