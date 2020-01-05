package com.home4u.hotelmanagement.repositories;

import com.home4u.hotelmanagement.models.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;


public interface HotelRepository extends JpaRepository<Hotel, Long> {
    List<Hotel> findAllByUserId(long userId);
    @Query(value = "SELECT *, 3956 * 2 * ASIN(SQRT(POWER(SIN((?1 - abs(dest.lat)) * pi()/180 / 2),2) +COS(?1 * pi()/180 ) * COS(abs(dest.lat) * pi()/180) * POWER(SIN((?2 - dest.lon) * pi()/180 / 2), 2) )) as distance FROM hotel dest ORDER BY distance;",
            nativeQuery = true)
    List<Map<String, Object>> findHotelByDistance(double lat, double lon, double dist);
}
