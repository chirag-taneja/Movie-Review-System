package com.movie.booking.system.service;

import com.movie.booking.system.dto.BookingDto;
import com.movie.booking.system.entity.Booking;
import com.movie.booking.system.entity.Seat;
import com.movie.booking.system.entity.Show;
import com.movie.booking.system.entity.User;
import com.movie.booking.system.repo.BookingRepo;
import com.movie.booking.system.repo.SeatRepo;
import com.movie.booking.system.repo.ShowRepo;
import com.movie.booking.system.repo.UserRepo;
import com.movie.booking.system.util.SeatStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class BookingService {
    public final BookingRepo bookingRepo;
    public final UserRepo userRepo;
    public final ShowRepo showRepo;
    public final SeatRepo seatRepo;

    public ResponseEntity<Object> getAllBooking() {
        List<Booking> all = bookingRepo.findAll();
        return ResponseEntity.ok(all);
    }

    public ResponseEntity<Object> getAllBookingByUserId(long userId) {
        List<Booking> all = bookingRepo.findAllByUserUserId(userId);
        return ResponseEntity.ok(all);
    }

    public ResponseEntity<Object> getBookingById(long id) {
        if (bookingRepo.existsById(id)) {
            Booking booking = bookingRepo.findById(id).get();
            return new ResponseEntity<>(booking, HttpStatus.OK);
        } else {
            throw new RuntimeException("Booking not found with this id");
        }

    }

    public ResponseEntity<Object> saveBooking(BookingDto bookingDto) {
        Optional<User> byId = userRepo.findById(bookingDto.userId());

        // get show for which show this booking for
        Show show = showRepo.findById(bookingDto.showId()).get();
        Set<Seat> seats =new LinkedHashSet<>();
        for (Long aLong : bookingDto.seatIdForBooking()) {
            seats.add(seatRepo.findById(aLong).get());
        }
        Booking booking = Booking.builder()
                .user(byId.get())
                .show(show)
                .noOfSeatsBooked(bookingDto.noOfSeatsBooked())
                .seats(seats).bookingTime(new Date())
                .build();
        seats.forEach(i->i.setStatus(SeatStatus.BOOKED));
        List<Seat> seats1 = seatRepo.saveAll(seats);
        Booking save = bookingRepo.save(booking);
        return ResponseEntity.ok("Booking successfully");
    }
}
