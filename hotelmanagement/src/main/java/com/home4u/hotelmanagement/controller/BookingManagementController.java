package com.home4u.hotelmanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.home4u.hotelmanagement.models.AppUser;
import com.home4u.hotelmanagement.models.Bookings;
import com.home4u.hotelmanagement.models.DistanceSearchResult;
import com.home4u.hotelmanagement.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.home4u.hotelmanagement.utils.Constants.APPUSER_TAG;

@RestController
@RequestMapping("/operations")
public class BookingManagementController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/search")
    public List<DistanceSearchResult> findHotels(@RequestParam("lat") double lat,
                                                 @RequestParam("lon") double lon,
                                                 @RequestParam("dist") double dist,
                                                 @RequestParam("checkInDate") String checkInDateString,
                                                 @RequestParam("checkOutDate") String checkOutDateString) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date checkInDate = null;
        Date checkOutDate = null;
        try {
            checkInDate = simpleDateFormat.parse(checkInDateString);
            checkOutDate = simpleDateFormat.parse(checkOutDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return bookingService.searchHotels(lat, lon, dist, checkInDate, checkOutDate);
    }

    @PostMapping("/book")
    public Bookings bookHotel(HttpServletRequest request, @RequestBody Bookings bookings) {
        AppUser user = new ObjectMapper().convertValue(request.getAttribute(APPUSER_TAG), AppUser.class);
        return bookingService.bookHotel(user, bookings);
    }

    @DeleteMapping("/book/{id}")
    public Bookings cancelBooking(HttpServletRequest request, @PathVariable("id") long id) {
        AppUser user = new ObjectMapper().convertValue(request.getAttribute(APPUSER_TAG), AppUser.class);
        return bookingService.cancelBooking(id, user);
    }

}
