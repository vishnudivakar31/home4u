package com.home4u.hotelmanagement.repositories;

import com.home4u.hotelmanagement.models.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingsRepository extends JpaRepository<Bookings, Long> {
    List<Bookings> findAllByUserId(long userId);
}
