package com.home4u.hotelmanagement.repositories;

import com.home4u.hotelmanagement.models.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface BookingsRepository extends JpaRepository<Bookings, Long> {
    List<Bookings> findAllByUserId(long userId);
    @Query(value = "SELECT * FROM bookings WHERE check_in_date >= ?1 and check_out_date <= ?2", nativeQuery = true)
    List<Bookings> findByCheckinCheckoutDate(Date checkinDate, Date checkoutDate);
}
