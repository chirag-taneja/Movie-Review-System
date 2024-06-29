package com.movie.booking.system.controller;

import com.movie.booking.system.dto.BookingDto;
import com.movie.booking.system.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    //get all booking

    @GetMapping("/list")
    public ResponseEntity<Object> getAllBooking()
    {
        return bookingService.getAllBooking();
    }



    //get by user id

    @GetMapping("/user")
    public ResponseEntity<Object> getAllBookingByUserId(@RequestParam long userId)
    {
        return bookingService.getAllBookingByUserId(userId);
    }


    //get by id
    @GetMapping("/{id}")
    public ResponseEntity<Object> getBookingById(@PathVariable long id)
    {
       return bookingService.getBookingById(id);
    }

    //post booking taking user id
    @PostMapping
    public ResponseEntity<Object> saveBooking(@RequestBody BookingDto bookingDto)
    {
        return bookingService.saveBooking(bookingDto);
    }
}
